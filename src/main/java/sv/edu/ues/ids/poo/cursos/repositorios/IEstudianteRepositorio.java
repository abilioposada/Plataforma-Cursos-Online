package sv.edu.ues.ids.poo.cursos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.edu.ues.ids.poo.cursos.entidades.Estudiante;

public interface IEstudianteRepositorio extends JpaRepository<Estudiante, Long> {
	public Estudiante findByCarnet(String carnet);
}
