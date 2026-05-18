package malapata.modelo;

import lombok.Getter;
import lombok.Setter;

public class Apuesta {
    
    @Getter
    private Jugador jugador;

    @Getter
    private Participacion participacion;

    @Getter
    private ModalidadDeApuesta modalidad;
    
    @Getter
    private double monto;

    @Getter
    @Setter
    private double montoCobrado;

    public Apuesta(Jugador jugador, Participacion participacion, ModalidadDeApuesta modalidad, double monto){
        this.jugador = jugador;
        this.participacion = participacion;
        this.modalidad = modalidad;
        this.monto = monto;
        this.montoCobrado = 0;

    }

}
