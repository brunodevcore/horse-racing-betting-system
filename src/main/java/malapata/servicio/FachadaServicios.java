package malapata.servicio;

import malapata.dominio.Credencial;
import malapata.dominio.Login;
import malapata.excepciones.AutenticacionException;

public class FachadaServicios {
    
    private static FachadaServicios instancia;

    private ServicioHipodromo servicioHipodromo;
    private ServicioAutenticacion servicioAutenticacion;

    private FachadaServicios(){
        this.servicioHipodromo = new ServicioHipodromo();
        this.servicioAutenticacion = new ServicioAutenticacion(servicioHipodromo.getHipodromo());
    }

    public static FachadaServicios getInstancia(){
        if(instancia == null){
            instancia = new FachadaServicios();
        }
        return instancia;
    }

    public Login autenticarJugador(Credencial credencial) throws AutenticacionException {
        return servicioAutenticacion.auntenticarJugador(credencial);
    }

    public Login autenticarAdministrador(Credencial credencial) throws AutenticacionException {
        return servicioAutenticacion.autenticarAdministrador(credencial);
    }

    public void logout(Login login){
        servicioAutenticacion.logout(login);
    }
}
