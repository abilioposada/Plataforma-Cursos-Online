package sv.edu.ues.ids.poo.cursos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.ids.poo.cursos.entidades.Inscripcion;
import sv.edu.ues.ids.poo.cursos.entidades.identificadores.IdInscripcion;

public interface IInscripcionRepositorio extends JpaRepository<Inscripcion, IdInscripcion> {}
