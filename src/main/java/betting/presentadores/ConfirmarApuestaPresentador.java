package betting.presentadores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import betting.dominio.Apuesta;
import betting.dominio.Carrera;
import betting.dominio.Credencial;
import betting.dominio.Jugador;
import betting.dominio.Login;
import betting.dominio.ModalidadDeApuesta;
import betting.dominio.Participacion;
import betting.dtos.ConfirmarApuestaDTO;
import betting.servicio.FachadaServicios;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/confirmarApuesta")
public class ConfirmarApuestaPresentador {

    @PostMapping("/inicializarVista")
    public Commands inicializarVista(HttpSession session,
            @RequestParam int numeroCarrera,
            @RequestParam int numeroParticipacion,
            @RequestParam String nombreModalidad,
            @RequestParam double monto) {

        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginJugador.html"));
        }

        Jugador jugador = (Jugador) login.getUsuario();
        Carrera carrera = FachadaServicios.getInstancia().getCarreraDisponible(numeroCarrera);
        if (carrera == null) {
            return Commands.create(new Command("error", "No hay carrera disponible"));
        }

        Participacion participacion = null;
        for (Participacion p : carrera.getParticipaciones()) {
            if (p.getNumero() == numeroParticipacion) {
                participacion = p;
                break;
            }
        }
        if (participacion == null) {
            return Commands.create(new Command("error", "No hay participacion disponible"));
        }

        ModalidadDeApuesta modalidad = null;
        for (ModalidadDeApuesta m : FachadaServicios.getInstancia().getModalidades()) {
            if (m.getNombre().equals(nombreModalidad)) {
                modalidad = m;
                break;
            }
        }
        if (modalidad == null) {
            return Commands.create(new Command("error", "No hay modalidad disponible"));
        }

        return Commands.create(
                new Command("apuesta", new ConfirmarApuestaDTO(carrera, participacion, modalidad, monto, jugador)));
    }

    @PostMapping("/descartar")
    public Commands descartar(HttpSession session) {
        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginJugador.html"));
        }
        return Commands.create(new Command("redirigir", "tableroJugador.html"));
    }

    @PostMapping("/confirmar")
    public Commands confirmar(HttpSession session,
            @RequestParam int numeroCarrera,
            @RequestParam int numeroParticipacion,
            @RequestParam String nombreModalidad,
            @RequestParam double monto,
            @RequestParam String contrasena) {

        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginJugador.html"));
        }

        Jugador jugador = (Jugador) login.getUsuario();

        if (!jugador.esValida(new Credencial(jugador.getNombreUsuario(), contrasena))) {
            return Commands.create(new Command("error", "Contraseña incorrecta"));
        }

        Carrera carrera = FachadaServicios.getInstancia().getCarreraDisponible(numeroCarrera);
        if (carrera == null) {
            return Commands.create(new Command("error", "Esta carrera ya no recibe apuestas"));
        }

        Participacion participacion = null;
        for (Participacion p : carrera.getParticipaciones()) {
            if (p.getNumero() == numeroParticipacion) {
                participacion = p;
                break;
            }
        }

        ModalidadDeApuesta modalidad = null;
        for (ModalidadDeApuesta m : FachadaServicios.getInstancia().getModalidades()) {
            if (m.getNombre().equals(nombreModalidad)) {
                modalidad = m;
                break;
            }
        }

        if (modalidad == null) {
            return Commands.create(new Command("error", "No hay modalidad disponible"));
        }

        if (monto < 1) {
            return Commands.create(new Command("error", "Monto inválido"));
        }

        Apuesta apuesta = new Apuesta(jugador, participacion, modalidad, monto);
        double costo = modalidad.calcularCosto(apuesta);

        if (jugador.getSaldo() < costo) {
            return Commands.create(new Command("error", "Saldo insuficiente"));
        }

        FachadaServicios.getInstancia().realizarApuesta(jugador, participacion, modalidad, monto);

        return Commands.create(new Command("redirigir", "tableroJugador.html"));
    }

}
