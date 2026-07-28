package betting.presentadores;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import betting.dominio.Administrador;
import betting.dominio.Credencial;
import betting.dominio.Login;
import betting.dtos.CredencialDTO;
import betting.excepciones.AutenticacionException;
import betting.servicio.FachadaServicios;

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
            Administrador administrador = FachadaServicios.getInstancia().autenticarAdministrador(credencial);
            session.setAttribute("usuarioAdministrador", administrador);
            return Commands.create(new Command("redirigir", "tableroAdmin.html"));
       } catch (AutenticacionException e) {
            return Commands.create(new Command("error", e.getMessage()));
       }
    }
    
    @PostMapping("/logout")
    public Commands logout(HttpSession session) {
        Login loginJugador = (Login) session.getAttribute("loginUsuario");
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");

          if(loginJugador != null){
               FachadaServicios.getInstancia().logout(loginJugador);
               session.invalidate();
               return Commands.create(new Command("redirigir", "loginJugador.html"));    
          } 
          
          if(admin != null){
               FachadaServicios.getInstancia().logoutAdministrador(admin);
               session.invalidate();
               return Commands.create(new Command("redirigir", "loginAdmin.html"));    
          }

          session.invalidate();
          return Commands.create(new Command("redirigir", "loginAdmin.html"));
        
        
    }
    
    
}
