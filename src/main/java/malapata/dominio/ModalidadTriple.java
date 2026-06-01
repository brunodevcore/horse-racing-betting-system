package malapata.dominio;

public class ModalidadTriple extends ModalidadDeApuesta {
    
    public ModalidadTriple(){
        super("Triple");
    }

    @Override
    public double calcularCosto(Apuesta apuesta){
        return apuesta.getMonto() * 1.5;
    }

    @Override
    public double calcularPago(Apuesta apuesta){
        if(apuesta.getParticipacion().calcularTotalApostado() >= 100000) {
            return apuesta.getMonto() * apuesta.getParticipacion().getDividendo() * 3;
        }
        return apuesta.getMonto() * apuesta.getParticipacion().getDividendo() * 2; 
    }
}
