package sv.edu.ues.ids.poo.cursos.servicios;

import sv.edu.ues.ids.poo.cursos.entidades.Instructor;
import sv.edu.ues.ids.poo.cursos.repositorios.IInstructorRepositorio;
import sv.edu.ues.ids.poo.cursos.servicios.interfaces.IInstructorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorServicio implements IInstructorServicio {
	@Autowired
	private IInstructorRepositorio repositorio;

	@Override
	public List<Instructor> obtenerTodos() {
		return repositorio.findAll();
	}

	@Override
	public Instructor guardar(Instructor instructor) {
		return repositorio.save(instructor);
	}

	@Override
	public void eliminarPorId(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public Instructor buscarPorCodigo(String codigo) throws Exception {
		return repositorio.findByCodigo(codigo);
	}

}
