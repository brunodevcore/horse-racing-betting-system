package betting.dominio;
import betting.excepciones.CarreraException;

public class EstadoEstable implements EstadoCarrera {

    @Override
    public String getNombre() {
        return "Estable";
    }

    @Override
    public void abrir(Carrera carrera) {
        throw new CarreraException("No se puede abrir esta carrera.");
    }

    @Override
    public void cerrar(Carrera carrera) {
        carrera.setEstado(new EstadoCerrada());
    }

    @Override
    public void finalizar(Carrera carrera, Participacion ganador) {
        throw new CarreraException("No se puede finalizar esta carrera.");
    }

    @Override
    public void realizarApuesta(Carrera carrera) {
       for (Participacion p : carrera.getParticipaciones()) {
        if (p.getDividendo() <= 1) {
            carrera.setEstado(new EstadoAbierta());
            return;
        }
    }
    }

}