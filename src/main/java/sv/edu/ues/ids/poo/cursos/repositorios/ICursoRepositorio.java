package sv.edu.ues.ids.poo.cursos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.ids.poo.cursos.entidades.Curso;

public interface ICursoRepositorio extends JpaRepository<Curso, Long> {
	public java.util.Optional<Curso> findByCodigo(String codigo);

	public void deleteByCodigo(String codigo);
}
