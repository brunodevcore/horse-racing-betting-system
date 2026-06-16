package malapata.dominio;
import malapata.excepciones.CarreraException;

public class EstadoCerrada implements EstadoCarrera {

    @Override
    public String getNombre() {
        return "Cerrada";
    }

    @Override
    public void abrir(Carrera carrera) {
        throw new CarreraException("No se puede abrir una carrera que ya está cerrada.");
    }

    @Override
    public void cerrar(Carrera carrera) {
        throw new CarreraException("La carrera ya está cerrada.");
    }

    @Override
    public void finalizar(Carrera carrera, Participacion ganador) {
        carrera.setGanador(ganador);
        carrera.setEstado(new EstadoFinalizada());
        carrera.setHoraFinalizacion();                                                         
        carrera.pagarApuestasGanadoras();
        
    }

    @Override
    public void realizarApuesta(Carrera carrera) {
        throw new CarreraException("No se pueden realizar apuestas en una carrera cerrada.");
    }
    
}
