package malapata.dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Carrera {

    @Getter
    private int numero;

    @Getter
    private String nombre;

    private EstadoCarrera estado;

    @Getter
    private List<Participacion> participaciones;

    @Getter
    @Setter
    private Participacion ganador;

    public Carrera(int numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
        this.estado = new EstadoDefinida();
        this.participaciones = new ArrayList<>();
        this.ganador = null;
    }

    public String getEstado() {
        return estado.getNombre();
    }

    public void setEstado(EstadoCarrera estado) {
        this.estado = estado;
    }

    public void agregarParticipacion(Caballo caballo, int numero){
        participaciones.add(new Participacion(numero, caballo));
    }

    public void abrir(){
        estado.abrir(this);
    }

    public void cerrar(){
        estado.cerrar(this);
    }

    public void finalizar(Participacion ganador){
        estado.finalizar(this, ganador);
    }

    public void realizarApuesta(){
        estado.realizarApuesta(this);
    }

    public double calcularTotalApostado(){
        double total = 0;
        for (Participacion p : participaciones) {
            total += p.calcularTotalApostado();       }
        return total;
    }

    public double calcularTotalPagado(){
        double total = 0;
        for (Participacion p : participaciones) {
            total += p.calcularTotalPagado();
        }
        return total;
    }
}
