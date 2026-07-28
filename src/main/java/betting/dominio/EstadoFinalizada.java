package betting.dominio;
import betting.excepciones.CarreraException;

public class EstadoFinalizada implements EstadoCarrera {
    @Override
    public String getNombre() {
        return "Finalizada";
    }

    @Override
    public void abrir(Carrera carrera) {
        throw new CarreraException("No se puede abrir una carrera finalizada.");
    }

    @Override
    public void cerrar(Carrera carrera) {
        throw new CarreraException("No se puede cerrar una carrera finalizada.");
    }

    @Override
    public void finalizar(Carrera carrera, Participacion ganador) {
        throw new CarreraException("La carrera ya está finalizada.");
    }

    @Override
    public void realizarApuesta(Carrera carrera) {
        throw new CarreraException("No se pueden realizar apuestas en una carrera finalizada.");
    }
    
}
