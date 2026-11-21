package sv.edu.ues.ids.poo.cursos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.ids.poo.cursos.servicios.InscripcionServicio;
import sv.edu.ues.ids.poo.cursos.entidades.Inscripcion;
import sv.edu.ues.ids.poo.cursos.entidades.identificadores.IdInscripcion;
import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionesControlador {
	@Autowired
	private InscripcionServicio servicio;

	@GetMapping
	public List<Inscripcion> inicio() {
		return servicio.obtenerTodos();
	}

	@GetMapping("/{codigo}/{id}")
	public Inscripcion obtenerPorId(@PathVariable("codigo") String codigo, @PathVariable("id") Long id)
			throws Exception {
		return servicio.buscarPorId(new IdInscripcion(codigo, id));
	}

	@PostMapping
	public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
		return servicio.guardar(inscripcion);
	}

	@DeleteMapping("/{codigo}/{id}")
	public void eliminar(@PathVariable("codigo") String codigo, @PathVariable("id") Long id) {
		servicio.eliminarPorId(new IdInscripcion(codigo, id));
	}

	@PutMapping("/{codigo}/{id}")
	public Inscripcion actualizar(@PathVariable("codigo") String codigo, @PathVariable("id") Long id,
			@RequestBody Inscripcion inscripcion) {
		inscripcion.setId(new IdInscripcion(codigo, id));
		return servicio.guardar(inscripcion);
	}
}
