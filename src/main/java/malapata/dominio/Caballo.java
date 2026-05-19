package malapata.dominio;

import lombok.Getter;

public class Caballo {
    
    @Getter
    private String nombre;

    public Caballo(String nombre){
        this.nombre = nombre;
    }
}
