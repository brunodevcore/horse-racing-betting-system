package malapata.presentadores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import malapata.dominio.Jugador;    
import malapata.dominio.Login;
import malapata.servicio.FachadaServicios;
import malapata.dtos.ApuestaDTO;
import malapata.dtos.CarreraDTO;
import malapata.dtos.JugadorDTO;
import malapata.dominio.Apuesta;
import java.util.List;


@RestController
@RequestMapping("/jugador")
public class JugadorPresentador {
    
    @PostMapping("/inicializarVista")
    public Commands inicializarVista(HttpSession session) {
        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginJugador.html"));
        }
        Jugador jugador = (Jugador) login.getUsuario();
        List<Apuesta> apuestas = FachadaServicios.getInstancia().getApuestasJugador(jugador);

        return Commands.create(
            new Command("jugador", new JugadorDTO(jugador, apuestas)),
            new Command("carrerasDisponibles", CarreraDTO.fromLista(FachadaServicios.getInstancia().getCarrerasDisponibles())),
            new Command("modalidades", FachadaServicios.getInstancia().getModalidades()),
            new Command("misApuestas", ApuestaDTO.fromLista(apuestas))
        );
    }
}
