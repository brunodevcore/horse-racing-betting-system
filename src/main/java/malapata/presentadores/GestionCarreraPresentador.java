package malapata.presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.servlet.http.HttpSession;
import malapata.dominio.Administrador;
import malapata.dominio.Carrera;
import malapata.dominio.Participacion;
import malapata.servicio.FachadaServicios;
import malapata.dtos.CarreraDTO;

import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import malapata.observer.Observador;
import malapata.observer.Observable;

@RestController
@RequestMapping("/gestionCarrera")
@Scope("session")
public class GestionCarreraPresentador implements Observador {

    private ConexionNavegador conexionNavegador;
    private Carrera carreraActual;

    public GestionCarreraPresentador(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
        FachadaServicios.getInstancia().subscribir(this);
    }

    @Override
    public void actualizar(Observable observable, Object evento) {
        if (evento == Observable.Evento.ESTADO_ACTUALIZADO || evento == Observable.Evento.APUESTA_REALIZADA) {
            if (carreraActual != null) {
                conexionNavegador.enviarCommands(Commands.create(new Command("carrera", new CarreraDTO(carreraActual))));
            }

        }
    }

    @PostMapping("/inicializarVista")
    public Commands inicializarVistas(HttpSession session, @RequestParam int numeroCarrera) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaJornada = (LocalDate) session.getAttribute("fechaJornada");
        Carrera carrera = FachadaServicios.getInstancia().getCarrera(fechaJornada, numeroCarrera);
        if (carrera == null) {
            return Commands.create(new Command("error", "No hay carrera seleccionada"));
        }
        session.setAttribute("numeroCarrera", numeroCarrera);
        carreraActual = carrera;
        return Commands.create(new Command("carrera", new CarreraDTO(carrera)));

    }

    @PostMapping("/abrir")
    public Commands abrir(HttpSession session) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaJornada = (LocalDate) session.getAttribute("fechaJornada");
        int numeroCarrera = (int) session.getAttribute("numeroCarrera");
        Carrera carrera = FachadaServicios.getInstancia().getCarrera(fechaJornada, numeroCarrera);
        carrera.abrir();
        FachadaServicios.getInstancia().notificar(Observable.Evento.ESTADO_ACTUALIZADO);
        return Commands.create(new Command("carrera", new CarreraDTO(carrera)));
    }

    @PostMapping("/cerrar")
    public Commands cerrar(HttpSession session) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaJornada = (LocalDate) session.getAttribute("fechaJornada");
        int numeroCarrera = (int) session.getAttribute("numeroCarrera");
        Carrera carrera = FachadaServicios.getInstancia().getCarrera(fechaJornada, numeroCarrera);
        carrera.cerrar();
        FachadaServicios.getInstancia().notificar(Observable.Evento.ESTADO_ACTUALIZADO);
        return Commands.create(new Command("carrera", new CarreraDTO(carrera)));
    }

    @PostMapping("/finalizar")
    public Commands finalizar(HttpSession session, @RequestParam int numeroParticipacion) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaJornada = (LocalDate) session.getAttribute("fechaJornada");
        int numeroCarrera = (int) session.getAttribute("numeroCarrera");
        Carrera carrera = FachadaServicios.getInstancia().getCarrera(fechaJornada, numeroCarrera);

        Participacion ganador = null;
        for (Participacion p : carrera.getParticipaciones()) {
            if (p.getNumero() == numeroParticipacion) {
                ganador = p;
                break;
            }
        }
        if (ganador == null) {
            return Commands.create(new Command("error", "Debe indicar el caballo ganador de la carrera"));
        }
        carrera.finalizar(ganador);
        FachadaServicios.getInstancia().notificar(Observable.Evento.ESTADO_ACTUALIZADO);
        return Commands.create(new Command("carrera", new CarreraDTO(carrera)));
    }

    @GetMapping("/registrarSSE")
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }
}
