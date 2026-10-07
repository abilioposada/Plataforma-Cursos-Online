package sv.edu.ues.ids.poo.cursos.entidades.identificadores;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class IdInscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoCurso;
	private Long idEstudiante;

	public IdInscripcion() {
	}

	public IdInscripcion(String codigoCurso, Long idEstudiante) {
		this.codigoCurso = codigoCurso;
		this.idEstudiante = idEstudiante;
	}

	// region Getters & Setters
	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public Long getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	// endregion

	@Override
	public int hashCode() {
		return Objects.hash(codigoCurso, idEstudiante);
	}

	@Override
	public boolean equals(Object objeto) {
		if (this == objeto)
			return true;
		if (objeto == null || getClass() != objeto.getClass())
			return false;
		IdInscripcion o = (IdInscripcion) objeto;

		return Objects.equals(codigoCurso, o.codigoCurso) && Objects.equals(idEstudiante, o.idEstudiante);
	}
}
