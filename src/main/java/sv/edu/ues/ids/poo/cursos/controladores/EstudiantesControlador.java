package sv.edu.ues.ids.poo.cursos.controladores;

import sv.edu.ues.ids.poo.cursos.entidades.Estudiante;
import sv.edu.ues.ids.poo.cursos.servicios.EstudianteServicio;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudiantesControlador {
	@Autowired
	private EstudianteServicio servicio;

	@GetMapping
	public List<Estudiante> inicio() {
		return servicio.obtenerTodos();
	}

	@GetMapping("/{carnet}")
	public Estudiante buscarPorCarnet(@PathVariable String carnet) throws Exception {
		return servicio.buscarPorCarnet(carnet);
	}

	@PostMapping
	public Estudiante guardar(@RequestBody Estudiante estudiante) {
		return servicio.guardar(estudiante);
	}

	@PutMapping("/{carnet}")
	public Estudiante actualizar(@PathVariable String carnet, @RequestBody Estudiante estudiante) {
		try {
			estudiante.setId(servicio.buscarPorCarnet(carnet).getId());
			return servicio.guardar(estudiante);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	@DeleteMapping("/{carnet}")
	public void destroy(@PathVariable String carnet) {
		try {
			Estudiante estudiante = servicio.buscarPorCarnet(carnet);
			servicio.eliminarPorId(estudiante.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
