package betting.presentadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import betting.dominio.Apuesta;
import betting.dominio.Jugador;
import betting.dominio.Login;
import betting.dtos.ApuestaDTO;
import betting.dtos.CarreraDTO;
import betting.dtos.JugadorDTO;
import betting.observer.Observable;
import betting.observer.Observador;
import betting.servicio.FachadaServicios;
import jakarta.servlet.http.HttpSession;

import java.util.List;


@RestController
@RequestMapping("/jugador")
@Scope("session")
public class JugadorPresentador implements Observador {
    
    private ConexionNavegador conexionNavegador;
    private Jugador jugadorActual;

    public JugadorPresentador(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
        FachadaServicios.getInstancia().subscribir(this);
    }

    @Override
    public void actualizar(betting.observer.Observable observable, Object evento) {
        if(evento == Observable.Evento.ESTADO_ACTUALIZADO || evento == Observable.Evento.APUESTA_REALIZADA) {
            if(jugadorActual != null) {
                List<Apuesta> apuestas = FachadaServicios.getInstancia().getApuestasJugador(jugadorActual);
                conexionNavegador.enviarCommands(
                    Commands.create(
                        new Command("jugador", new JugadorDTO(jugadorActual, apuestas)),
                        new Command("modalidades", FachadaServicios.getInstancia().getModalidades()),
                        new Command("carrerasDisponibles", CarreraDTO.fromLista(FachadaServicios.getInstancia().getCarrerasDisponibles())),
                        new Command("misApuestas", ApuestaDTO.fromLista(apuestas))
                    )
                );
            }
        }
    }

    @PostMapping("/inicializarVista")
    public Commands inicializarVista(HttpSession session) {
        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginJugador.html"));
        }
        jugadorActual = (Jugador) login.getUsuario();
        List<Apuesta> apuestas = FachadaServicios.getInstancia().getApuestasJugador(jugadorActual);

        return Commands.create(
            new Command("jugador", new JugadorDTO(jugadorActual, apuestas)),
            new Command("modalidades", FachadaServicios.getInstancia().getModalidades()),
            new Command("carrerasDisponibles", CarreraDTO.fromLista(FachadaServicios.getInstancia().getCarrerasDisponibles())),
            new Command("misApuestas", ApuestaDTO.fromLista(apuestas))
        );
    }

    @GetMapping("/registrarSSE")
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }
}

