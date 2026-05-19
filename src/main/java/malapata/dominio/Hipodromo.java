package malapata.dominio;

import lombok.Getter;
import java.util.List;
import java.util.ArrayList;

public class Hipodromo {
    
    @Getter
    private double comision;

    @Getter
    private List<Jornada> jornadas;

    @Getter 
    private List<Jugador> jugadores;

    @Getter
    private List<Administrador> administradores;

    @Getter
    private List<ModalidadDeApuesta> modalidades;

    @Getter
    private List<Caballo> caballos;

    public Hipodromo(double comision){
        this.comision = comision;
        this.jornadas = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.modalidades = new ArrayList<>();
        this.caballos = new ArrayList<>();
    }
}
