package betting.dominio;

public class ModalidadSuper extends ModalidadDeApuesta {
    
    public ModalidadSuper(){
        super("Super");
    }

    @Override 
    public double calcularCosto(Apuesta apuesta){
        return apuesta.getMonto() * 2;
    }

    @Override 
    public double calcularPago(Apuesta apuesta){
       if(apuesta.getParticipacion().getDividendo() >=2){
            return apuesta.getMonto() * apuesta.getParticipacion().getDividendo() * 3;
        }
        return apuesta.getMonto() * apuesta.getParticipacion().getDividendo() * 4;
    }
}
