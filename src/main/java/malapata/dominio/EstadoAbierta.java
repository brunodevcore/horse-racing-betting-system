package malapata.dominio;

import malapata.excepciones.CarreraException;

public class EstadoAbierta implements EstadoCarrera {

    @Override
    public String getNombre() {
        return "Abierta";
    }

    @Override
    public void abrir(Carrera carrera) {
        throw new CarreraException("La carrera ya está abierta.");
    }

    @Override
    public void cerrar(Carrera carrera) {
        throw new CarreraException("No se puede cerrar una carrera que está abierta. Primero debe finalizarla.");
    }

    @Override
    public void finalizar(Carrera carrera, Participacion ganador) {
        throw new CarreraException("No se puede finalizar una carrera abierta.");
    }

    @Override
    public void realizarApuesta(Carrera carrera) {
        for (Participacion p : carrera.getParticipaciones()) {
            if (p.getDividendo() <= 1) {
                return; 
            }
        }
        carrera.setEstado(new EstadoEstable());
    }

}
