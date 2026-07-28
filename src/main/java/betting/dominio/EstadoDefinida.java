package betting.dominio;
import betting.excepciones.CarreraException;

public class EstadoDefinida implements EstadoCarrera {
    
    @Override
    public String getNombre() {
        return "Definida";
    }

    @Override
    public void abrir(Carrera carrera) {
        carrera.setEstado(new EstadoAbierta());
    }

    @Override
    public void cerrar(Carrera carrera) {
        throw new CarreraException("No se puede cerrar una carrera que no ha sido abierta.");
    }

    @Override
    public void finalizar(Carrera carrera, Participacion ganador) {
        throw new CarreraException("No se puede finalizar una carrera que no ha sido abierta.");
    }

    @Override
    public void realizarApuesta(Carrera carrera) {
        throw new CarreraException("No se pueden realizar apuestas en una carrera que no ha sido abierta.");
    }
}
