package sv.edu.ues.ids.poo.cursos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.ids.poo.cursos.servicios.interfaces.IInscripcionServicio;
import sv.edu.ues.ids.poo.cursos.repositorios.IInscripcionRepositorio;
import sv.edu.ues.ids.poo.cursos.entidades.Inscripcion;
import sv.edu.ues.ids.poo.cursos.entidades.identificadores.IdInscripcion;
import java.util.List;

@Service
public class InscripcionServicio implements IInscripcionServicio {
	@Autowired
	private IInscripcionRepositorio repositorio;

	@Override
	public List<Inscripcion> obtenerTodos() {
		return repositorio.findAll();
	}

	@Override
	public Inscripcion guardar(Inscripcion inscripcion) {
		return repositorio.save(inscripcion);
	}

	@Override
	public void eliminarPorId(IdInscripcion id) {
		repositorio.deleteById(id);
	}

	@Override
	public Inscripcion buscarPorId(IdInscripcion id) throws Exception {
		return repositorio.findById(id).orElseThrow(() -> new Exception("Inscripcion no encontrada"));
	}
}
