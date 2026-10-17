package sv.edu.ues.ids.poo.cursos.servicios.interfaces;

import sv.edu.ues.ids.poo.cursos.entidades.Instructor;
import java.util.List;

public interface IInstructorServicio {
	public List<Instructor> obtenerTodos();

	public Instructor guardar(Instructor instructor);

	public void eliminarPorId(Long id);

	public Instructor buscarPorCodigo(String codigo) throws Exception;
}
