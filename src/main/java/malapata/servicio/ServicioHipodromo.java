package malapata.servicio;

import java.time.LocalDate;

import malapata.dominio.*;

public class ServicioHipodromo {
    
    private Hipodromo hipodromo;

    public ServicioHipodromo(){
        this.hipodromo = new Hipodromo(0.10);
        this.cargarDatos();
    }

    private void cargarDatos(){
        cargarCaballos();
        cargarUsuarios();
        cargarModalidades();
        cargarJornadas();
    }

    private void cargarCaballos(){
        hipodromo.getCaballos().add(new Caballo("ROMANTICO"));
        hipodromo.getCaballos().add(new Caballo("INVASOR"));
        hipodromo.getCaballos().add(new Caballo("SIR FEVER"));
        hipodromo.getCaballos().add(new Caballo("RELENTO"));
        hipodromo.getCaballos().add(new Caballo("SUABLENANAV TH"));
        hipodromo.getCaballos().add(new Caballo("AJUSTE FISCAL"));
    }

    private void cargarUsuarios(){
        hipodromo.getJugadores().add(new Jugador("j1", "Usuario Jugador", "j1", 2000));
        hipodromo.getJugadores().add(new Jugador("j2", "Usuario Jugador N2", "j2", 5000));
        hipodromo.getJugadores().add(new Jugador("j3", "Usuario Jugador N3", "j3", 100));
    
        hipodromo.getAdministradores().add(new Administrador("a1", "Usuario Administrador", "a1"));
        hipodromo.getAdministradores().add(new Administrador("a2", "Usuario Administrador N2", "a2"));
    }

    private void cargarModalidades(){
        hipodromo.getModalidades().add(new ModalidadSimple());
        hipodromo.getModalidades().add(new ModalidadTriple());
        hipodromo.getModalidades().add(new ModalidadSuper());
    }

    private void cargarJornadas(){
        LocalDate hoy = LocalDate.now();

        // 1 carrera con semana posterior
        Jornada jornadaFutura = new Jornada(hoy.plusWeeks(1));

        Carrera carreraFutura = new Carrera(1, "Gran Premio Polla de Potrancas");
        carreraFutura.agregarParticipacion(hipodromo.getCaballos().get(0),1);
        carreraFutura.agregarParticipacion(hipodromo.getCaballos().get(1),2);
        jornadaFutura.agregarCarrera(carreraFutura);

        hipodromo.getJornadas().add(jornadaFutura);
  
    }

}
