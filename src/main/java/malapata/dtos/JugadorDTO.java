package malapata.dtos;

import lombok.Getter;
import lombok.Setter;
import malapata.dominio.Jugador;
import malapata.dominio.Apuesta;
import java.util.List;

@Getter
@Setter
public class JugadorDTO {
    private String nombreCompleto;
    private double saldo;
    private double totalApostado;
    private double totalGanado;

    public JugadorDTO() {

    }

    public JugadorDTO(Jugador jugador, List<Apuesta> apuestas) {
        this.nombreCompleto = jugador.getNombreCompleto();
        this.saldo = jugador.getSaldo();
        this.totalApostado = 0;
        this.totalGanado = 0;
        for (Apuesta apuesta : apuestas) {
            this.totalApostado += apuesta.getMonto();
            this.totalGanado += apuesta.getMontoCobrado();
        }
    }
}
