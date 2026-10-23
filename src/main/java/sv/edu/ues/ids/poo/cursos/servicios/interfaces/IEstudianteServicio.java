package sv.edu.ues.ids.poo.cursos.servicios.interfaces;

import sv.edu.ues.ids.poo.cursos.entidades.Estudiante;
import java.util.List;

public interface IEstudianteServicio {
	public List<Estudiante> obtenerTodos();

	public Estudiante guardar(Estudiante estudiante);

	public void eliminarPorId(Long id);

	public Estudiante buscarPorCarnet(String carnet) throws Exception;
}
