package betting.dominio;

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
    
    public void agregarCarrera(Carrera carrera){
        carrera.setJornada(this);
        carreras.add(carrera);
    }
    
    public double calcularTotalApostado(){
        double total = 0;
        for (Carrera c : carreras) {
            total += c.calcularTotalApostado();
        }
        return total;
    }

    public double calcularTotalPagado(){
        double total = 0;
        for (Carrera c : carreras) {
            total += c.calcularTotalPagado();
        }
        return total;
    }   

}
