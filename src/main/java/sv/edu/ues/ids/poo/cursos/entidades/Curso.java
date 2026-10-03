package sv.edu.ues.ids.poo.cursos.entidades;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String codigo;

	@JoinColumn(name = "id_instructor")
	@ManyToOne(optional = false)
	private Instructor instructor;

	@Column(unique = true)
	private String nombre;

	private String descripcion;

	// region Getters & Setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	// endregion

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", instructor=" + instructor + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
