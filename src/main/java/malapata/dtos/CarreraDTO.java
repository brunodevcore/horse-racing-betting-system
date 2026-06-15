package malapata.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import malapata.dominio.Carrera;
import malapata.dominio.Participacion;

@Getter
@Setter
public class CarreraDTO {

    private int numero;
    private String nombre;
    private String estado;
    private int cantidadCaballos;
    private String nombreGanador;
    private List<ParticipacionDTO> participaciones;
    private double totalApostado;
    private String horaFinalizacion;
    private double totalPagado;
    private double dividendoGanador;
    private int cantidadApuestas;

    public CarreraDTO() {

    }

    public CarreraDTO(Carrera carrera) {
        this.numero = carrera.getNumero();
        this.nombre = carrera.getNombre();
        this.estado = carrera.getEstado();
        this.cantidadCaballos = carrera.getParticipaciones().size();
        this.nombreGanador = carrera.getGanador() != null ? carrera.getGanador().getCaballo().getNombre() : null;
        this.participaciones = ParticipacionDTO.fromLista(carrera.getParticipaciones());
        this.totalApostado = carrera.calcularTotalApostado();
        this.horaFinalizacion = carrera.getHoraFinalizacion() != null ? carrera.getHoraFinalizacion().toLocalTime().toString().substring(0, 8) : null;
        this.totalPagado = carrera.calcularTotalPagado();
        this.dividendoGanador = carrera.getGanador() != null ? carrera.getGanador().getDividendo() : 0;
        int total = 0;
        for (Participacion p : carrera.getParticipaciones()) {
            total += p.getApuestas().size();
        }
        this.cantidadApuestas = total;
    }

    public static List<CarreraDTO> fromLista(List<Carrera> carreras) {
        List<CarreraDTO> lista = new ArrayList<>();
        for (Carrera c : carreras) {
            lista.add(new CarreraDTO(c));
        }
        return lista;
    }
}
