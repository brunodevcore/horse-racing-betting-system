package malapata.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import malapata.dominio.Carrera;

@Getter
@Setter
public class CarreraDTO {
    
    private int numero;
    private String nombre;
    private String estado;
    private int cantidadCaballos;
    private String nombreGanador;

    public CarreraDTO(){

    }

    public CarreraDTO(Carrera carrera){
        this.numero = carrera.getNumero();
        this.nombre = carrera.getNombre();
        this.estado = carrera.getEstado();
        this.cantidadCaballos = carrera.getParticipaciones().size();
        this.nombreGanador = carrera.getGanador() != null ? carrera.getGanador().getNombre() : null;    
    }

    public static List<CarreraDTO> fromLista(List<Carrera> carreras) {
        return carreras.stream()
            .map(CarreraDTO::new)
            .toList();
    }
}
