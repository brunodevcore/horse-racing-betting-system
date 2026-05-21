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

    public Participacion(int numero, Caballo caballo){
        this.numero = numero;
        this.caballo = caballo;
        this.dividendo = 0;
        this.apuestas = new ArrayList<>();
    }

    public void agregarApuestas(Apuesta apuesta){
        apuestas.add(apuesta);
    }

}
