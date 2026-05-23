package malapata.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import malapata.dominio.Participacion;

@Getter
@Setter
public class ParticipacionDTO {
  
    private int numero;
    private String nombreCaballo;
    private double dividendo;

    public ParticipacionDTO() {

    }

    public ParticipacionDTO(Participacion participacion) {
        this.numero = participacion.getNumero();
        this.nombreCaballo = participacion.getCaballo().getNombre();
        this.dividendo = participacion.getDividendo();
    }

    public static List<ParticipacionDTO> fromLista(List<Participacion> participaciones) {
        return participaciones.stream()
            .map(ParticipacionDTO::new)
            .toList();
    }

}
