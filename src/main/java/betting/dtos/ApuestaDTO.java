package betting.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import betting.dominio.Apuesta;
import betting.dominio.Carrera;
import betting.dominio.Jornada;

@Getter
@Setter
public class ApuestaDTO {
    private String fechaCarrera;
    private int numeroCarrera;
    private String nombreCarrera;   
    private int numeroCaballo;
    private String nombreCaballo;
    private double monto;
    private String tipoApuesta;
    private double montoCobrado;
    private double dividendo;
    private String estado;

    public ApuestaDTO(Apuesta apuesta) {
        Carrera carrera = apuesta.getParticipacion().getCarrera();
        Jornada jornada = carrera.getJornada();
        this.fechaCarrera = jornada.getFecha().toString();
        this.numeroCarrera = carrera.getNumero();
        this.nombreCarrera = carrera.getNombre();
        this.numeroCaballo = apuesta.getParticipacion().getNumero();
        this.nombreCaballo = apuesta.getParticipacion().getCaballo().getNombre();
        this.monto = apuesta.getMonto();
        this.tipoApuesta = apuesta.getModalidad().getNombre();
        this.montoCobrado = apuesta.getMontoCobrado();
        this.dividendo = apuesta.getParticipacion().getDividendo();
        this.estado = carrera.getEstado().equals("Finalizada") ? "Finalizada" : "Pendiente";

    }

    public static List<ApuestaDTO> fromLista(List<Apuesta> apuestas) {
        List<ApuestaDTO> apuestasDTO = new ArrayList<>();
        for (Apuesta apuesta : apuestas) {
            apuestasDTO.add(new ApuestaDTO(apuesta));
        }
        return apuestasDTO;
    }
}
