package malapata.dominio;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Carrera {

    @Getter
    private int numero;

    @Getter
    private String nombre;

    @Getter
    private String estado;

    @Getter
    private List<Participacion> participaciones;

    @Getter
    private Participacion ganador;

    public Carrera(int numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
        this.estado = "Definida";
        this.participaciones = new ArrayList<>();
        this.ganador = null;
    }

    public void agregarParticipacion(Caballo caballo, int numero){
        participaciones.add(new Participacion(numero, caballo));
    }

    public void abrir(){
        this.estado = "Abierta";
    }

    public void cerrar(){
        this.estado = "Cerrada";
    }

    public void finalizar(Participacion ganador){
        this.estado = "Finalizada";
        this.ganador = ganador;
    }
}
