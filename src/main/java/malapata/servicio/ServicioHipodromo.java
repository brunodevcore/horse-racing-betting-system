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

        // 1 carrera con fecha de semana posterior
        Jornada jornadaFutura = new Jornada(hoy.plusWeeks(1));

        Carrera carreraFutura = new Carrera(1, "Gran Premio Polla de Potrancas");
        carreraFutura.agregarParticipacion(hipodromo.getCaballos().get(0),1);
        carreraFutura.agregarParticipacion(hipodromo.getCaballos().get(1),2);
        jornadaFutura.agregarCarrera(carreraFutura);

        hipodromo.getJornadas().add(jornadaFutura);

        // 2 carreras con fecha de semana anterior
        Jornada jornadaPasada = new Jornada(hoy.minusWeeks(1));

        // carrera 1
        Carrera carreraPasada1 = new Carrera(1, "Gran Premio Pedro Piñeyrúa");
        carreraPasada1.agregarParticipacion(hipodromo.getCaballos().get(0), 1);
        carreraPasada1.agregarParticipacion(hipodromo.getCaballos().get(1), 2);
        carreraPasada1.agregarParticipacion(hipodromo.getCaballos().get(2), 3);

        for(Participacion p : carreraPasada1.getParticipaciones()){
            int cantidadApuestas = (int)(Math.random() * 11) + 10;
            for(int i = 0; i < cantidadApuestas ; i++){
                agregarApuestas(p, hipodromo.getJugadores().get(i % 3), hipodromo.getModalidades().get(i % 3), (int)(Math.random() * 9900) + 100);
            }
        }

        carreraPasada1.cerrar();
        jornadaPasada.agregarCarrera(carreraPasada1);

        // carrera 2
        Carrera carreraPasada2 = new Carrera(2, "Gran Premio Criterium");
        carreraPasada2.agregarParticipacion(hipodromo.getCaballos().get(3), 1);
        carreraPasada2.agregarParticipacion(hipodromo.getCaballos().get(4), 2);
        carreraPasada2.agregarParticipacion(hipodromo.getCaballos().get(5), 3);
        
        for(Participacion p : carreraPasada2.getParticipaciones()){
            int cantidadApuestas = (int)(Math.random() * 11) + 10;
            for(int i = 0; i < cantidadApuestas ; i++){
                agregarApuestas(p, hipodromo.getJugadores().get(i % 3), hipodromo.getModalidades().get(i % 3), (int)(Math.random() * 9900) + 100);
            }
        }

        carreraPasada2.cerrar();
        jornadaPasada.agregarCarrera(carreraPasada2);

        hipodromo.getJornadas().add(jornadaPasada);

        // 3 carreras con fecha del dia actual
        Jornada jornadaHoy = new Jornada(hoy);

        // carrera 1
        Carrera carrera1 = new Carrera(1 , "Gran Premio José Pedro Ramírez");
        carrera1.agregarParticipacion(hipodromo.getCaballos().get(0), 1);
        carrera1.agregarParticipacion(hipodromo.getCaballos().get(1), 2);
        jornadaHoy.agregarCarrera(carrera1);

        // carrera 2
        Carrera carrera2 = new Carrera(2 , "Gran Premio Ciudad de Montevideo");
        carrera2.agregarParticipacion(hipodromo.getCaballos().get(2), 1);
        carrera2.agregarParticipacion(hipodromo.getCaballos().get(3), 2);
        jornadaHoy.agregarCarrera(carrera2);

        // carrera 3
        Carrera carrera3 = new Carrera(3 , "Gran Premio Maroñas");
        carrera3.agregarParticipacion(hipodromo.getCaballos().get(4), 1);
        carrera3.agregarParticipacion(hipodromo.getCaballos().get(5), 2);
    
        jornadaHoy.agregarCarrera(carrera3);

        hipodromo.getJornadas().add(jornadaHoy);
  
    }

     private void agregarApuestas(Participacion participacion, Jugador jugador, ModalidadDeApuesta modalidad, double monto){
        Apuesta apuesta = new Apuesta(jugador, participacion, modalidad, monto);
        participacion.agregarApuestas(apuesta);
    }

}
