package malapata.modelo;

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
    private Caballo ganador;

    public Carrera(int numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
        this.estado = "Definida";
        this.participaciones = new ArrayList<>();
        this.ganador = null;
    }
}
