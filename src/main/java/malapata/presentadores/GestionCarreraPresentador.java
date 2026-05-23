package malapata.presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import malapata.dominio.Administrador;
import malapata.dominio.Carrera;
import malapata.servicio.FachadaServicios;
import malapata.dtos.CarreraDTO;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/gestionCarrera")
public class GestionCarreraPresentador {
    
    @PostMapping("inicializarVista")
    public Commands inicializarVistas(HttpSession session, @RequestParam int numeroCarrera) {
        Administrador admin = (Administrador) session.getAttribute("usuarioAdministrador");
            if (admin == null) {
            return Commands.create(new Command("redirigir", "loginAdmin.html"));
        }
        LocalDate fechaJornada = (LocalDate) session.getAttribute("fechaJornada");
        Carrera carrera = FachadaServicios.getInstancia().getCarrera(fechaJornada, numeroCarrera);
        if(carrera == null){
            return Commands.create(new Command("error", "No hay carrera seleccionada"));
        }
        session.setAttribute("numeroCarrera", numeroCarrera);
        return Commands.create(
            new Command("carrera", new CarreraDTO(carrera))
        );

    }
    

}
