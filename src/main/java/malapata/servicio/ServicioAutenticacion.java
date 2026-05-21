package malapata.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import malapata.dominio.*;
import malapata.excepciones.AutenticacionException;;

public class ServicioAutenticacion {
    
    private Hipodromo hipodromo;
    private List<Login> logins;

    public ServicioAutenticacion(Hipodromo hipodromo){
        this.hipodromo = hipodromo;
        this.logins = new ArrayList<>();

    }

    public Login auntenticarJugador(Credencial credencial) throws AutenticacionException{
        for(Jugador jugador : hipodromo.getJugadores()){
            if(jugador.esValida(credencial)){
                Login login = new Login(new Date(), jugador);
                logins.add(login);
                return login;
            }
        }
        throw new AutenticacionException("Acceso denegado");
    }

    public Login autenticarAdministrador(Credencial credencial) throws AutenticacionException{
        for(Administrador administrador : hipodromo.getAdministradores()){
            if(administrador.esValida(credencial)){
                for(Login login : logins){
                    if(login.getUsuario().equals(administrador)){
                        throw new AutenticacionException("El administrador ya esta logueado ");
                    }
                }
                Login login = new Login(new Date(), administrador);
                logins.add(login);
                return login; 
            }
        }
        throw new AutenticacionException("Acceso denegado");
    }

    public void logout(Login login){
        logins.remove(login);
    }

}
