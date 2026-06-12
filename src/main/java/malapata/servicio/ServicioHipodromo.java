package malapata.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import malapata.dominio.*;

public class ServicioHipodromo {

    @Getter
    private Hipodromo hipodromo;

    public ServicioHipodromo() {
        this.hipodromo = new Hipodromo(0.10);
        this.cargarDatos();
    }

    private void cargarDatos() {
        cargarCaballos();
        cargarUsuarios();
        cargarModalidades();
        cargarJornadas();

    }

    private void cargarCaballos() {
        hipodromo.getCaballos().add(new Caballo("ROMANTICO"));
        hipodromo.getCaballos().add(new Caballo("INVASOR"));
        hipodromo.getCaballos().add(new Caballo("SIR FEVER"));
        hipodromo.getCaballos().add(new Caballo("RELENTO"));
        hipodromo.getCaballos().add(new Caballo("SUABLENANAV TH"));
        hipodromo.getCaballos().add(new Caballo("AJUSTE FISCAL"));
    }

    private void cargarUsuarios() {
        hipodromo.getJugadores().add(new Jugador("j1", "Usuario Jugador", "j1", 2000));
        hipodromo.getJugadores().add(new Jugador("j2", "Usuario Jugador N2", "j2", 5000));
        hipodromo.getJugadores().add(new Jugador("j3", "Usuario Jugador N3", "j3", 100));

        hipodromo.getAdministradores().add(new Administrador("a1", "Usuario Administrador", "a1"));
        hipodromo.getAdministradores().add(new Administrador("a2", "Usuario Administrador N2", "a2"));
    }

    private void cargarModalidades() {
        hipodromo.getModalidades().add(new ModalidadSimple());
        hipodromo.getModalidades().add(new ModalidadTriple());
        hipodromo.getModalidades().add(new ModalidadSuper());
    }

    private void cargarJornadas() {
        LocalDate hoy = LocalDate.now();

        // 1 carrera con fecha de semana posterior
        Jornada jornadaFutura = new Jornada(hoy.plusWeeks(1));

        Carrera carreraFutura = new Carrera(1, "Gran Premio Polla de Potrancas");
        carreraFutura.agregarParticipacion(hipodromo.getCaballos().get(0), 1);
        carreraFutura.agregarParticipacion(hipodromo.getCaballos().get(1), 2);
        jornadaFutura.agregarCarrera(carreraFutura);

        hipodromo.getJornadas().add(jornadaFutura);

        // 2 carreras con fecha de semana anterior
        Jornada jornadaPasada = new Jornada(hoy.minusWeeks(1));

        // carrera 1
        Carrera carreraPasada1 = new Carrera(1, "Gran Premio Pedro Piñeyrúa");
        carreraPasada1.agregarParticipacion(hipodromo.getCaballos().get(0), 1);
        carreraPasada1.agregarParticipacion(hipodromo.getCaballos().get(1), 2);
        carreraPasada1.agregarParticipacion(hipodromo.getCaballos().get(2), 3);

        for (Participacion p : carreraPasada1.getParticipaciones()) {
            int cantidadApuestas = (int) (Math.random() * 11) + 10;
            for (int i = 0; i < cantidadApuestas; i++) {
                agregarApuestas(p, hipodromo.getJugadores().get(i % 3), hipodromo.getModalidades().get(i % 3),
                        ((int) (Math.random() * 9900) + 100));
            }
        }

        carreraPasada1.setEstado(new EstadoCerrada());
        carreraPasada1.recalcularDividendos(hipodromo.getComision());
        jornadaPasada.agregarCarrera(carreraPasada1);

        // carrera 2
        Carrera carreraPasada2 = new Carrera(2, "Gran Premio Criterium");
        carreraPasada2.agregarParticipacion(hipodromo.getCaballos().get(3), 1);
        carreraPasada2.agregarParticipacion(hipodromo.getCaballos().get(4), 2);
        carreraPasada2.agregarParticipacion(hipodromo.getCaballos().get(5), 3);

        for (Participacion p : carreraPasada2.getParticipaciones()) {
            int cantidadApuestas = (int) (Math.random() * 11) + 10;
            for (int i = 0; i < cantidadApuestas; i++) {
                agregarApuestas(p, hipodromo.getJugadores().get(i % 3), hipodromo.getModalidades().get(i % 3),
                        ((int) (Math.random() * 9900) + 100) * 10);
            }
        }

        carreraPasada2.setEstado(new EstadoCerrada());
        carreraPasada2.recalcularDividendos(hipodromo.getComision());
        jornadaPasada.agregarCarrera(carreraPasada2);

        hipodromo.getJornadas().add(jornadaPasada);

        // 3 carreras con fecha del dia actual
        Jornada jornadaHoy = new Jornada(hoy);

        // carrera 1
        Carrera carrera1 = new Carrera(1, "Gran Premio José Pedro Ramírez");
        carrera1.agregarParticipacion(hipodromo.getCaballos().get(0), 1);
        carrera1.agregarParticipacion(hipodromo.getCaballos().get(1), 2);
        jornadaHoy.agregarCarrera(carrera1);

        // carrera 2
        Carrera carrera2 = new Carrera(2, "Gran Premio Ciudad de Montevideo");
        carrera2.agregarParticipacion(hipodromo.getCaballos().get(2), 1);
        carrera2.agregarParticipacion(hipodromo.getCaballos().get(3), 2);
        jornadaHoy.agregarCarrera(carrera2);

        // carrera 3
        Carrera carrera3 = new Carrera(3, "Gran Premio Maroñas");
        carrera3.agregarParticipacion(hipodromo.getCaballos().get(4), 1);
        carrera3.agregarParticipacion(hipodromo.getCaballos().get(5), 2);

        jornadaHoy.agregarCarrera(carrera3);

        hipodromo.getJornadas().add(jornadaHoy);

    }

    private void agregarApuestas(Participacion participacion, Jugador jugador, ModalidadDeApuesta modalidad,
            double monto) {
        Apuesta apuesta = new Apuesta(jugador, participacion, modalidad, monto);
        participacion.agregarApuestas(apuesta);
    }

    public Jornada getJornadaActual() {
        LocalDate hoy = LocalDate.now();
        Jornada jornadaActual = null;

        for (Jornada j : hipodromo.getJornadas()) {
            if (!j.getFecha().isAfter(hoy)) {
                if (jornadaActual == null || j.getFecha().isAfter(jornadaActual.getFecha())) {
                    jornadaActual = j;
                }
            }
        }
        return jornadaActual;
    }

    public Jornada getJornadaAnterior(LocalDate fecha) {
        Jornada anterior = null;

        for (Jornada j : hipodromo.getJornadas()) {
            if (j.getFecha().isBefore(fecha)) {
                if (anterior == null || j.getFecha().isAfter(anterior.getFecha())) {
                    anterior = j;
                }
            }
        }
        return anterior;
    }

    public Jornada getJornadaSiguiente(LocalDate fecha) {
        Jornada siguiente = null;

        for (Jornada j : hipodromo.getJornadas()) {
            if (j.getFecha().isAfter(fecha)) {
                if (siguiente == null || j.getFecha().isBefore(siguiente.getFecha())) {
                    siguiente = j;
                }
            }
        }
        return siguiente;
    }

    public Carrera getCarrera(LocalDate fechaJornada, int numeroCarrera) {
        for (Jornada j : hipodromo.getJornadas()) {
            if (j.getFecha().equals(fechaJornada)) {
                for (Carrera c : j.getCarreras()) {
                    if (c.getNumero() == numeroCarrera) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public List<Apuesta> getApuestasJugador(Jugador jugador) {
        List<Apuesta> apuestasJugador = new ArrayList<>();

        for (Jornada j : hipodromo.getJornadas()) {
            for (Carrera c : j.getCarreras()) {
                for (Participacion p : c.getParticipaciones()) {
                    for (Apuesta a : p.getApuestas()) {
                        if (a.getJugador().equals(jugador)) {
                            apuestasJugador.add(a);
                        }
                    }
                }
            }
        }
        return apuestasJugador;
    }

    public List<Carrera> getCarrerasDisponibles() {
        List<Carrera> carrerasDisponibles = new ArrayList<>();

        for (Jornada j : hipodromo.getJornadas()) {
            for (Carrera c : j.getCarreras()) {
                if (c.getEstado().equals("Abierta") || c.getEstado().equals("Estable")) {
                    carrerasDisponibles.add(c);
                }
            }
        }
        return carrerasDisponibles;
    }

    public Carrera getCarreraDisponible(int numeroCarrera) {
        for (Jornada j : hipodromo.getJornadas()) {
            for (Carrera c : j.getCarreras()) {
                if (c.getNumero() == numeroCarrera &&
                        (c.getEstado().equals("Abierta") || c.getEstado().equals("Estable"))) {
                    return c;
                }
            }
        }
        return null;
    }

    public void realizarApuesta(Jugador jugador, Participacion participacion, ModalidadDeApuesta modalidad,
            double monto) {
        Apuesta apuesta = new Apuesta(jugador, participacion, modalidad, monto);
        double costo = modalidad.calcularCosto(apuesta);
        jugador.descontarSaldo(costo);
        participacion.agregarApuestas(apuesta);
        participacion.getCarrera().recalcularDividendos(hipodromo.getComision());
        participacion.getCarrera().realizarApuesta();
    }
}
