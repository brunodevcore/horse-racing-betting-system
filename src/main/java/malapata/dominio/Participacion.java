package malapata.dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Participacion {

    @Getter
    private int numero;

    @Getter
    private Caballo caballo;

    @Getter
    @Setter
    private double dividendo;

    @Getter
    private List<Apuesta> apuestas;

    @Getter
    private Carrera carrera;

    public Participacion(int numero, Caballo caballo) {
        this.numero = numero;
        this.caballo = caballo;
        this.dividendo = 0;
        this.apuestas = new ArrayList<>();
        this.carrera = null;
    }

    public void agregarApuestas(Apuesta apuesta) {
        apuestas.add(apuesta);
    }

    public double calcularTotalApostado() {
        double total = 0;
        for (Apuesta apuesta : apuestas) {
            total += apuesta.getMonto();
        }
        return total;
    }

    public double calcularTotalPagado() {
        double total = 0;
        for (Apuesta apuesta : apuestas) {
            total += apuesta.getMontoCobrado();
        }
        return total;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void recalcularDividendo(double totalCarrera, double comision) {
        if (apuestas.isEmpty()) {
            this.dividendo = 0;
            return;
        }
        double totalAlCaballo = calcularTotalApostado();
        double dividendoCalculado = (totalCarrera * (1 - comision)) / totalAlCaballo;
        this.dividendo = dividendoCalculado > 1 ? dividendoCalculado : 0;
    }

}
