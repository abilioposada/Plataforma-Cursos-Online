package sv.edu.ues.ids.poo.cursos.entidades;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import sv.edu.ues.ids.poo.cursos.entidades.identificadores.IdInscripcion;

@Entity
@Table(name = "inscripciones")
public class Inscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IdInscripcion id = new IdInscripcion();

	@MapsId("codigoCurso")
	@ManyToOne(optional = false)
	@JoinColumn(name = "codigo_curso")
	private Curso curso;

	@MapsId("idEstudiante")
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_estudiante")
	private Estudiante estudiante;

	private Date fechaHora;
	private Double promedio;

	// region Getters & Setters
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
	// endregion

	@Override
	public String toString() {
		return "Inscripcion[id=" + id + ", curso=" + curso + ", estudiante=" + estudiante + ", fechaHora=" + fechaHora
				+ ", promedio=" + promedio + ']';
	}
}
