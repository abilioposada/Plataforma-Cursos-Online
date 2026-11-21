package sv.edu.ues.ids.poo.cursos.servicios.interfaces;

import sv.edu.ues.ids.poo.cursos.entidades.identificadores.IdInscripcion;
import sv.edu.ues.ids.poo.cursos.entidades.Inscripcion;
import java.util.List;

public interface IInscripcionServicio {
	public List<Inscripcion> obtenerTodos();

	public Inscripcion guardar(Inscripcion inscripcion);

	public void eliminarPorId(IdInscripcion id);

	public Inscripcion buscarPorId(IdInscripcion id) throws Exception;
}
