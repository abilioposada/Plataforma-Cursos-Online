package sv.edu.ues.ids.poo.cursos.entidades.identificadores;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public record IdInscripcion(String codigoCurso, Long idEstudiante) implements Serializable {
}
