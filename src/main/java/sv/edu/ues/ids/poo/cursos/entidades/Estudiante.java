package sv.edu.ues.ids.poo.cursos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Persona {
	@Column(unique = true)
	private String carnet;
	private Date fechaRegistro;

	// region Getters & Setters
	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	// endregion

	@Override
	public String toString() {
		return "Estudiante [carnet=" + carnet + ", fechaRegistro=" + fechaRegistro + ", persona=" + super.toString() + "]";
	}
}
