package malapata.modelo;

public class ModalidadSuper extends ModalidadDeApuesta {
    
    public ModalidadSuper(){
        super("Super");
    }

    @Override 
    public double calcularCosto(double monto){
        return monto * 2;
    }

    @Override 
    public double calcularPago(double monto, double dividendo, double totalApostadoAlCaballo){
        if(dividendo >= 2){
            return monto * dividendo * 3;
        }
        return monto * dividendo * 4;
    }
}
