package sv.edu.ues.ids.poo.cursos.controladores;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Controlador global de errores de la API.
 *
 * Intercepta las excepciones lanzadas por cualquier controlador y las convierte
 * en una respuesta JSON uniforme con el código HTTP correspondiente
 * (400, 403, 404, 405, 409, 415, 500, etc.).
 */
@Hidden
@RestControllerAdvice
public class ControladorErrores {
	private static final Logger log = LoggerFactory.getLogger(ControladorErrores.class);

	/** Estructura de la respuesta de error que recibe el cliente. */
	public record ErrorRespuesta(
			LocalDateTime fecha,
			int estado,
			String error,
			String mensaje,
			String ruta,
			Map<String, String> detalles) {
	}

	// ------------------------------------------------------------------
	// 400 - Bad Request
	// ------------------------------------------------------------------

	/** Falla la validación de @Valid en el cuerpo de la petición. */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorRespuesta> manejarValidacion(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		Map<String, String> detalles = new LinkedHashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(e -> detalles.put(e.getField(), e.getDefaultMessage()));
		return construir(HttpStatus.BAD_REQUEST, "Los datos enviados no son válidos.", request, detalles);
	}

	/**
	 * Falla la validación de parámetros (@RequestParam, @PathVariable con
	 * restricciones).
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorRespuesta> manejarRestricciones(ConstraintViolationException ex,
			HttpServletRequest request) {
		Map<String, String> detalles = new LinkedHashMap<>();
		ex.getConstraintViolations()
				.forEach(v -> detalles.put(v.getPropertyPath().toString(), v.getMessage()));
		return construir(HttpStatus.BAD_REQUEST, "Los parámetros enviados no son válidos.", request, detalles);
	}

	/** JSON mal formado o con tipos incorrectos. */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorRespuesta> manejarJsonInvalido(HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.BAD_REQUEST, "El cuerpo de la petición está mal formado.", request, null);
	}

	/** Falta un parámetro obligatorio en la URL. */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorRespuesta> manejarParametroFaltante(MissingServletRequestParameterException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.BAD_REQUEST,
				"Falta el parámetro obligatorio '" + ex.getParameterName() + "'.", request, null);
	}

	/**
	 * Tipo de parámetro incorrecto (por ejemplo, texto donde se esperaba un
	 * número).
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorRespuesta> manejarTipoIncorrecto(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.BAD_REQUEST,
				"El parámetro '" + ex.getName() + "' tiene un valor o tipo incorrecto.", request, null);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorRespuesta> manejarArgumentoIlegal(IllegalArgumentException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
	}

	// ------------------------------------------------------------------
	// 403 - Forbidden
	// ------------------------------------------------------------------

	/**
	 * Acceso denegado (lanzar SecurityException desde la capa de servicio cuando
	 * corresponda).
	 */
	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<ErrorRespuesta> manejarAccesoDenegado(SecurityException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.FORBIDDEN, "No tiene permisos para realizar esta acción.", request, null);
	}

	// ------------------------------------------------------------------
	// 404 - Not Found
	// ------------------------------------------------------------------

	/** Ruta inexistente. */
	@ExceptionHandler({ NoHandlerFoundException.class, NoResourceFoundException.class })
	public ResponseEntity<ErrorRespuesta> manejarRutaNoEncontrada(Exception ex, HttpServletRequest request) {
		return construir(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.", request, null);
	}

	/** Registro inexistente (por ejemplo, Optional.orElseThrow()). */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorRespuesta> manejarElementoNoEncontrado(NoSuchElementException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.NOT_FOUND, "No se encontró el registro solicitado.", request, null);
	}

	// ------------------------------------------------------------------
	// 405 / 409 / 415
	// ------------------------------------------------------------------

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorRespuesta> manejarMetodoNoPermitido(HttpRequestMethodNotSupportedException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.METHOD_NOT_ALLOWED,
				"El método " + ex.getMethod() + " no está permitido para esta ruta.", request, null);
	}

	/**
	 * Violación de llaves únicas, llaves foráneas, NOT NULL, etc. en SQL Server.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorRespuesta> manejarIntegridad(DataIntegrityViolationException ex,
			HttpServletRequest request) {
		log.warn("Violación de integridad de datos: {}", ex.getMostSpecificCause().getMessage());
		return construir(HttpStatus.CONFLICT,
				"La operación viola una restricción de la base de datos (dato duplicado o relacionado).",
				request, null);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorRespuesta> manejarMediaTypeNoSoportado(HttpMediaTypeNotSupportedException ex,
			HttpServletRequest request) {
		return construir(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "El tipo de contenido enviado no es soportado.",
				request, null);
	}

	// ------------------------------------------------------------------
	// Errores lanzados manualmente con ResponseStatusException (cualquier código)
	// ------------------------------------------------------------------

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorRespuesta> manejarResponseStatus(ResponseStatusException ex,
			HttpServletRequest request) {
		HttpStatus estado = HttpStatus.resolve(ex.getStatusCode().value());
		if (estado == null) {
			estado = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		String mensaje = ex.getReason() != null ? ex.getReason() : estado.getReasonPhrase();
		return construir(estado, mensaje, request, null);
	}

	// ------------------------------------------------------------------
	// 500 - Internal Server Error
	// ------------------------------------------------------------------

	/**
	 * Errores de acceso a la base de datos (conexión caída, SQL inválido, etc.).
	 */
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorRespuesta> manejarErrorBaseDatos(DataAccessException ex,
			HttpServletRequest request) {
		log.error("Error de base de datos en {}", request.getRequestURI(), ex);
		return construir(HttpStatus.INTERNAL_SERVER_ERROR,
				"Ocurrió un error al acceder a la base de datos.", request, null);
	}

	/**
	 * Cualquier otra excepción no prevista. No se expone el detalle interno al
	 * cliente.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorRespuesta> manejarErrorGeneral(Exception ex, HttpServletRequest request) {
		if (ex instanceof ErrorResponse er) {
			HttpStatus estado = HttpStatus.resolve(er.getStatusCode().value());
			if (estado != null) {
				return construir(estado, estado.getReasonPhrase(), request, null);
			}
		}
		log.error("Error no controlado en {}", request.getRequestURI(), ex);
		return construir(HttpStatus.INTERNAL_SERVER_ERROR,
				"Ocurrió un error interno en el servidor.", request, null);
	}

	// ------------------------------------------------------------------

	private ResponseEntity<ErrorRespuesta> construir(HttpStatus estado, String mensaje,
			HttpServletRequest request, Map<String, String> detalles) {
		ErrorRespuesta cuerpo = new ErrorRespuesta(
				LocalDateTime.now(),
				estado.value(),
				estado.getReasonPhrase(),
				mensaje,
				request.getRequestURI(),
				detalles == null ? Map.of() : detalles);
		return ResponseEntity.status(estado).body(cuerpo);
	}
}
