package betting.presentadores;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import betting.dominio.Administrador;
import betting.dominio.Jornada;
import betting.dtos.JornadaDTO;
import betting.observer.Observable;
import betting.observer.Observador;
import betting.servicio.FachadaServicios;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/admin")
@Scope("session")
public class AdminPresentador implements Observador {

    private ConexionNavegador conexionNavegador;
    private Jornada jornadaActual;

    public AdminPresentador(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
        FachadaServicios.getInstancia().subscribir(this);
    }

    @Override
    public void actualizar(Observable observable, Object evento) {
        if (evento == Observable.Evento.APUESTA_REALIZADA || evento == Observable.Evento.ESTADO_ACTUALIZADO) {
            if (jornadaActual != null) {
                conexionNavegador.enviarCommands(
                        Commands.create(new Command("jornada",
                                new JornadaDTO(jornadaActual, FachadaServicios.getInstancia().getComision()))));
            }
        }
    }

    @PostMapping("/inicializarVista")
    public Commands inicializarVista(HttpSession session) {

        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        Jornada jornada = FachadaServicios.getInstancia().getJornadaActual();
        if (jornada == null) {
            return Commands.create(new Command("error", "No hay jornadas definidas en el sistema"));
        }
        session.setAttribute("fechaJornada", jornada.getFecha());
        this.jornadaActual = jornada;
        return Commands.create(
                new Command("nombreAdmin", admin.getNombreCompleto()),
                new Command("jornada", new JornadaDTO(jornada, FachadaServicios.getInstancia().getComision())));
    }

    @PostMapping("/jornada/anterior")
    public Commands jornadaAnterior(HttpSession session) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaActual = (LocalDate) session.getAttribute("fechaJornada");
        Jornada jornada = FachadaServicios.getInstancia().getJornadaAnterior(fechaActual);
        if (jornada == null) {
            return Commands.create(new Command("error", "No hay jornadas anteriores a la seleccionada"));
        }
        session.setAttribute("fechaJornada", jornada.getFecha());
        this.jornadaActual = jornada;
        return Commands
                .create(new Command("jornada", new JornadaDTO(jornada, FachadaServicios.getInstancia().getComision())));

    }

    @PostMapping("/jornada/siguiente")
    public Commands jornadaSiguiente(HttpSession session) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
        if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaActual = (LocalDate) session.getAttribute("fechaJornada");
        Jornada jornada = FachadaServicios.getInstancia().getJornadaSiguiente(fechaActual);
        if (jornada == null) {
            return Commands.create(new Command("error", "No hay jornadas siguientes a la seleccionada"));
        }
        session.setAttribute("fechaJornada", jornada.getFecha());
        this.jornadaActual = jornada;
        return Commands
                .create(new Command("jornada", new JornadaDTO(jornada, FachadaServicios.getInstancia().getComision())));

    }

    @GetMapping("/registrarSSE")
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }

}