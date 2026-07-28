package betting.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import betting.dominio.Apuesta;
import betting.dominio.Jugador;

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
