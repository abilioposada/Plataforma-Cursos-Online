package sv.edu.ues.ids.poo.cursos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "instructores")
public class Instructor extends Persona {
	@Column(unique = true)
	private String codigo;
	private Date fechaContratacion;

	// region Getters & Setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	// endregion

	@Override
	public String toString() {
		return "Instructor [codigo=" + codigo + ", fechaContratacion=" + fechaContratacion + ", persona=" + super.toString() + "]";
	}
}
