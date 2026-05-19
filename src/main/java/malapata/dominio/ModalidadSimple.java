package malapata.dominio;

public class ModalidadSimple extends ModalidadDeApuesta {
    
    public ModalidadSimple()  {
        super("Simple");
    }

    @Override
    public double calcularCosto(double monto){
        return monto;
    }

    @Override
    public double calcularPago(double monto, double dividendo, double totalApostadoAlCaballo){
        return monto * dividendo;
    }
}
