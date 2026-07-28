package betting.dominio;

public class ModalidadSimple extends ModalidadDeApuesta {
    
    public ModalidadSimple()  {
        super("Simple");
    }

    @Override
    public double calcularCosto(Apuesta apuesta){
        return apuesta.getMonto();
    }

    @Override
    public double calcularPago(Apuesta apuesta){
        return apuesta.getMonto() * apuesta.getParticipacion().getDividendo();
    }
}
