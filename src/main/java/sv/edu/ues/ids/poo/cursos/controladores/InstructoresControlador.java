package sv.edu.ues.ids.poo.cursos.controladores;

import sv.edu.ues.ids.poo.cursos.entidades.Instructor;
import sv.edu.ues.ids.poo.cursos.servicios.InstructorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/instructores")
public class InstructoresControlador {
	@Autowired
	private InstructorServicio servicio;

	@GetMapping
	public List<Instructor> inicio() {
		return servicio.obtenerTodos();
	}

	@GetMapping("/{codigo}")
	public Instructor buscarPorCodigo(@PathVariable String codigo) throws Exception {
		return servicio.buscarPorCodigo(codigo);
	}

	@PostMapping
	public Instructor guardar(@RequestBody Instructor instructor) {
		return servicio.guardar(instructor);
	}

	@PutMapping("/{codigo}")
	public Instructor actualizar(@PathVariable String codigo, @RequestBody Instructor instructor) {
		try {
			instructor.setId(servicio.buscarPorCodigo(codigo).getId());
			return servicio.guardar(instructor);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	@DeleteMapping("/{codigo}")
	public void destroy(@PathVariable String codigo) {
		try {
			Instructor instructor = servicio.buscarPorCodigo(codigo);
			servicio.eliminarPorId(instructor.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
