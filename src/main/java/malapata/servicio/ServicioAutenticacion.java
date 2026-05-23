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

    public Administrador autenticarAdministrador(Credencial credencial) throws AutenticacionException{
        for(Administrador administrador : hipodromo.getAdministradores()){
            if(administrador.esValida(credencial)){
                if(administrador.isEstaLogueado()){
                    throw new AutenticacionException("El administrador ya tiene una sesión activa");
                }
                administrador.setEstaLogueado(true);
                return administrador;
            }
        }
        throw new AutenticacionException("Acceso denegado");
    }

    public void logout(Login login){
        logins.remove(login);
    }

    public void logoutAdministrador(Administrador administrador){
       administrador.setEstaLogueado(false);
    }

}
