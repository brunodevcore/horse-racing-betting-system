package malapata.dominio;

import java.time.LocalDate;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class Jornada {

    @Getter
    private LocalDate fecha;
    
    @Getter
    private List<Carrera> carreras;

    public Jornada(LocalDate fecha){
        this.fecha = fecha;
        this.carreras = new ArrayList<>();
    }
    
}
