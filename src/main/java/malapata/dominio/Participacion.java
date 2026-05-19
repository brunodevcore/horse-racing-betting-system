package malapata.dominio;

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

    public Participacion(int numero, Caballo caballo){
        this.numero = numero;
        this.caballo = caballo;
        this.dividendo = 0;
    }

}
