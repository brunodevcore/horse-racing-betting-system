package malapata.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import malapata.dominio.Jornada;

@Getter
@Setter
public class JornadaDTO {

    private LocalDate fecha;
    private List<CarreraDTO> carrerasFinalizadas;
    private List<CarreraDTO> proximasCarreras;
    private int cantidadCarreras;
    private int cantidadCarrerasFinalizadas;
    private int cantidadCarrerasProximas;

    public JornadaDTO() {

    }

    public JornadaDTO(Jornada jornada) {
        this.fecha = jornada.getFecha();
        this.cantidadCarreras = jornada.getCarreras().size();
        this.carrerasFinalizadas = CarreraDTO.fromLista(
            jornada.getCarreras().stream()
                .filter(c -> c.getEstado().equals("Finalizada"))
                .toList()
        );

        this.proximasCarreras = CarreraDTO.fromLista(
            jornada.getCarreras().stream()
                .filter(c -> !c.getEstado().equals("Finalizada"))
                .toList()
        );
        this.cantidadCarrerasFinalizadas = carrerasFinalizadas.size();
        this.cantidadCarrerasProximas = proximasCarreras.size();
    }
}