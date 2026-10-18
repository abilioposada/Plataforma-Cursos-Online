package sv.edu.ues.ids.poo.cursos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.edu.ues.ids.poo.cursos.entidades.Instructor;

public interface IInstructorRepositorio extends JpaRepository<Instructor, Long> {
	public Instructor findByCodigo(String codigo);
}
