package malapata.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import malapata.dominio.Carrera;
import malapata.dominio.Jornada;

@Getter
@Setter
public class JornadaDTO {

    private String fecha;
    private List<CarreraDTO> carrerasFinalizadas;
    private List<CarreraDTO> proximasCarreras;
    private int cantidadCarreras;
    private int cantidadCarrerasFinalizadas;
    private int cantidadCarrerasProximas;

    private double totalApostado;
    private double totalPagado;
    private double balance;
    private double totalComisiones;

    public JornadaDTO() {

    }

    public JornadaDTO(Jornada jornada, double comision) {
        this.fecha = jornada.getFecha().toString();
        this.cantidadCarreras = jornada.getCarreras().size();

        List<Carrera> finalizadas = new ArrayList<>();
        List<Carrera> proximas = new ArrayList<>();

        for (Carrera c : jornada.getCarreras()) {
            if (c.getEstado().equals("Finalizada")) {
                finalizadas.add(c);
            } else {
                proximas.add(c);
            }
        }

        this.carrerasFinalizadas = CarreraDTO.fromLista(finalizadas);
        this.proximasCarreras = CarreraDTO.fromLista(proximas);
        this.cantidadCarrerasFinalizadas = carrerasFinalizadas.size();
        this.cantidadCarrerasProximas = proximasCarreras.size();
        this.totalApostado = jornada.calcularTotalApostado();
        this.totalPagado = jornada.calcularTotalPagado();
        this.balance = totalApostado - totalPagado;
        this.totalComisiones = totalApostado * comision;
    }
}