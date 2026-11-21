package sv.edu.ues.ids.poo.cursos.servicios.interfaces;

import sv.edu.ues.ids.poo.cursos.entidades.Curso;
import java.util.List;

public interface ICursoServicio {
	public List<Curso> obtenerTodos();

	public Curso guardar(Curso curso);

	public void eliminarPorCodigo(String codigo);

	public Curso buscarPorCodigo(String codigo) throws Exception;
}
