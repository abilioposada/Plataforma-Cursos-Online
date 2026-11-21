package sv.edu.ues.ids.poo.cursos.servicios;

import sv.edu.ues.ids.poo.cursos.entidades.Curso;
import sv.edu.ues.ids.poo.cursos.entidades.Instructor;
import sv.edu.ues.ids.poo.cursos.repositorios.ICursoRepositorio;
import sv.edu.ues.ids.poo.cursos.repositorios.IInstructorRepositorio;
import sv.edu.ues.ids.poo.cursos.servicios.interfaces.ICursoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CursoServicio implements ICursoServicio {
	@Autowired
	private ICursoRepositorio repositorio;

	@Autowired
	private IInstructorRepositorio instructorRepositorio;

	@Override
	public List<Curso> obtenerTodos() {
		return repositorio.findAll();
	}

	@Override
	public Curso guardar(Curso curso) {
		Long idInstructor = curso.getInstructor().getId();

		Instructor instructor = instructorRepositorio.findById(idInstructor)
				.orElseThrow(() -> new EntityNotFoundException("No existe el instructor con id: " + idInstructor));

		curso.setInstructor(instructor);
		return repositorio.save(curso);
	}

	@Override
	public void eliminarPorCodigo(String codigo) {
		repositorio.deleteByCodigo(codigo);
	}

	@Override
	public Curso buscarPorCodigo(String codigo) throws Exception {
		return repositorio.findByCodigo(codigo)
				.orElseThrow(() -> new Exception("Curso no encontrado con el código: " + codigo));
	}
}
