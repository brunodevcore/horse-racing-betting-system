package malapata.dominio;

import lombok.Getter;

public abstract class ModalidadDeApuesta {
    
    @Getter
    private String nombre;

    public ModalidadDeApuesta(String nombre){
        this.nombre = nombre;
    }

    public abstract double calcularCosto(double monto);
    public abstract double calcularPago(double monto, double dividento, double totalApostadoAlCaballo);
}
