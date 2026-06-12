package malapata.dtos;

import java.util.ArrayList;
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
    private double totalApostado;
    private int cantidadApuestas;

    public ParticipacionDTO() {

    }

    public ParticipacionDTO(Participacion participacion) {
        this.numero = participacion.getNumero();
        this.nombreCaballo = participacion.getCaballo().getNombre();
        this.dividendo = participacion.getDividendo();
        this.totalApostado = participacion.calcularTotalApostado();
        this.cantidadApuestas = participacion.getApuestas().size();
    }

    public static List<ParticipacionDTO> fromLista(List<Participacion> participaciones) {
        List<ParticipacionDTO> lista = new ArrayList<>();
        for (Participacion p : participaciones) {
            lista.add(new ParticipacionDTO(p));
        }
        return lista;
    }

}
