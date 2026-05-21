package malapata.presentadores;



import jakarta.servlet.http.HttpSession;
import malapata.dominio.Credencial;
import malapata.dominio.Jugador;
import malapata.dominio.Login;
import malapata.dtos.CredencialDTO;
import malapata.excepciones.AutenticacionException;
import malapata.servicio.FachadaServicios;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AutenticacionPresentador {
    
    @PostMapping("/loginJugador")
    public Commands loginJugador(HttpSession session, CredencialDTO credencialDTO) {
       try{
            Credencial credencial = credencialDTO.toCredencial();
            Login login = FachadaServicios.getInstancia().autenticarJugador(credencial);
            session.setAttribute("loginUsuario", login);
            return Commands.create(new Command("redirigir", "tableroJugador.html"));
       } catch (AutenticacionException e) {
            return Commands.create(new Command("error", e.getMessage()));
       }
    }

    @PostMapping("/loginAdmin")
    public Commands loginAdmin(HttpSession session, CredencialDTO credencialDTO) {
       try{
            Credencial credencial = credencialDTO.toCredencial();
            Login login = FachadaServicios.getInstancia().autenticarAdministrador(credencial);
            session.setAttribute("loginUsuario", login);
            return Commands.create(new Command("redirigir", "tableroAdmin.html"));
       } catch (AutenticacionException e) {
            return Commands.create(new Command("error", e.getMessage()));
       }
    }
    
    @PostMapping("/logout")
    public Commands logout(HttpSession session) {
        Login login = (Login) session.getAttribute("loginUsuario");
        FachadaServicios.getInstancia().logout(login);
        session.removeAttribute("loginUsuario");
        session.invalidate();
        if(login.getUsuario() instanceof Jugador){
            return Commands.create(new Command("redirigir", "loginJugador.html"));
        }
        return Commands.create(new Command("redirigir", "loginAdmin.html"));
    }
    
    
}
