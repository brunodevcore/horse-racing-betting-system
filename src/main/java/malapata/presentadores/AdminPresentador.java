package malapata.presentadores;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import malapata.dominio.Login;
import malapata.dominio.Jornada;    
import malapata.dtos.JornadaDTO;
import malapata.servicio.FachadaServicios;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/admin")
public class AdminPresentador {

    @PostMapping("/inicializarVista")
    public Commands inicializarVista(HttpSession session) {

        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        Jornada jornada = FachadaServicios.getInstancia().getJornadaActual();    
        if(jornada == null){
            return Commands.create(new Command("error", "No hay jornadas definidas en el sistema"));
        }
        session.setAttribute("fechaJornada", jornada.getFecha());
        return Commands.create(
            new Command("nombreAdmin", login.getUsuario().getNombreCompleto()),
            new Command("jornada", new JornadaDTO(jornada))
        );      
    }

    @PostMapping("/jornada/anterior")
    public Commands jornadaAnterior(HttpSession session) {
        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaActual = (LocalDate) session.getAttribute("fechaJornada");
        Jornada jornada = FachadaServicios.getInstancia().getJornadaAnterior(fechaActual);
        if(jornada == null){
            return Commands.create(new Command("error", "No hay jornadas anteriores a la seleccionada"));
        }
        session.setAttribute("fechaJornada", jornada.getFecha());
        return Commands.create(new Command("jornada", new JornadaDTO(jornada))
        );

    }

    @PostMapping("/jornada/siguiente")
    public Commands jornadaSiguiente(HttpSession session) {
        Login login = (Login) session.getAttribute("loginUsuario");
        if (login == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaActual = (LocalDate) session.getAttribute("fechaJornada");
        Jornada jornada = FachadaServicios.getInstancia().getJornadaSiguiente(fechaActual);
        if(jornada == null){
            return Commands.create(new Command("error", "No hay jornadas siguientes a la seleccionada"));
        }
        session.setAttribute("fechaJornada", jornada.getFecha());
        return Commands.create(new Command("jornada", new JornadaDTO(jornada))
        );

    }
    
}