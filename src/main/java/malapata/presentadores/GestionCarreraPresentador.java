package malapata.presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import malapata.dominio.Administrador;
import malapata.dominio.Carrera;
import malapata.dominio.Participacion;
import malapata.servicio.FachadaServicios;
import malapata.dtos.CarreraDTO;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/gestionCarrera")
public class GestionCarreraPresentador {

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
        return Commands.create(
                new Command("carrera", new CarreraDTO(carrera)));

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
        return Commands.create(new Command("carrera", new CarreraDTO(carrera)));
    }

}
