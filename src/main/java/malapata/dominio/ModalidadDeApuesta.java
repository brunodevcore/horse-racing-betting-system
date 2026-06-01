package malapata.dominio;

import lombok.Getter;

public abstract class ModalidadDeApuesta {
    
    @Getter
    private String nombre;

    public ModalidadDeApuesta(String nombre){
        this.nombre = nombre;
    }

    public abstract double calcularCosto(Apuesta apuesta);
    public abstract double calcularPago(Apuesta apuesta);
}
