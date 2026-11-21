package sv.edu.ues.ids.poo.cursos.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.ids.poo.cursos.servicios.CursoServicio;
import sv.edu.ues.ids.poo.cursos.entidades.Curso;

@RestController
@RequestMapping("/cursos")
public class CursosControlador {
	@Autowired
	CursoServicio servicio;

	@GetMapping
	public List<Curso> inicio() {
		return servicio.obtenerTodos();
	}

	@GetMapping("/{codigo}")
	public Curso mostrar(@PathVariable String codigo) throws Exception {
		return servicio.buscarPorCodigo(codigo);
	}

	@PostMapping
	public Curso guardar(@RequestBody Curso curso) {
		return servicio.guardar(curso);
	}

	@PutMapping("/{codigo}")
	public Curso actualizar(@PathVariable String codigo, @RequestBody Curso curso) throws Exception {
		curso.setCodigo(codigo);
		return servicio.guardar(curso);
	}

	@DeleteMapping("/{codigo}")
	public void destruir(@PathVariable String codigo) {
		try {
			Curso curso = servicio.buscarPorCodigo(codigo);
			servicio.eliminarPorCodigo(curso.getCodigo());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
