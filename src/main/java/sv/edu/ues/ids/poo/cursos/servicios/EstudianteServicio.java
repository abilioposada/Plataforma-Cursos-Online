package sv.edu.ues.ids.poo.cursos.servicios;

import sv.edu.ues.ids.poo.cursos.servicios.interfaces.IEstudianteServicio;
import sv.edu.ues.ids.poo.cursos.entidades.Estudiante;
import sv.edu.ues.ids.poo.cursos.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteServicio implements IEstudianteServicio {
	@Autowired
	private IEstudianteRepositorio repositorio;

	@Override
	public List<Estudiante> obtenerTodos() {
		return repositorio.findAll();
	}

	@Override
	public Estudiante guardar(Estudiante estudiante) {
		return repositorio.save(estudiante);
	}

	@Override
	public void eliminarPorId(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public Estudiante buscarPorCarnet(String carnet) throws Exception {
		return repositorio.findByCarnet(carnet);
	}
}
