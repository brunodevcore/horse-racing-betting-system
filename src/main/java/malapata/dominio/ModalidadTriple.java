package malapata.dominio;

public class ModalidadTriple extends ModalidadDeApuesta {
    
    public ModalidadTriple(){
        super("Triple");
    }

    @Override
    public double calcularCosto(double monto){
        return monto * 1.5;
    }

    @Override
    public double calcularPago(double monto, double dividendo, double totalApostadoAlCaballo){
        if(totalApostadoAlCaballo >= 100000) {
            return monto * dividendo * 3;
        }
        return monto * dividendo * 2; 
    }
}
