package malapata.dominio;

public class ModalidadSuper extends ModalidadDeApuesta {
    
    public ModalidadSuper(){
        super("Super");
    }

    @Override 
    public double calcularCosto(Apuesta apuesta){
        return apuesta.getMonto() * 2;
    }

    @Override 
    public double calcularPago(Apuesta apuesta, Participacion participacion){
        if(participacion.getDividendo() >= 2){
            return apuesta.getMonto() * participacion.getDividendo() * 3;
        }
        return apuesta.getMonto() * participacion.getDividendo() * 4;
    }
}
