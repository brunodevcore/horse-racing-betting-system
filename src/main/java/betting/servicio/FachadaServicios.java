package betting.servicio;

import java.time.LocalDate;
import java.util.List;

import betting.dominio.Administrador;
import betting.dominio.Apuesta;
import betting.dominio.Carrera;
import betting.dominio.Credencial;
import betting.dominio.Jornada;
import betting.dominio.Jugador;
import betting.dominio.Login;
import betting.dominio.ModalidadDeApuesta;
import betting.dominio.Participacion;
import betting.excepciones.AutenticacionException;
import betting.observer.Observable;

public class FachadaServicios extends Observable {

    private static FachadaServicios instancia;

    private ServicioHipodromo servicioHipodromo;
    private ServicioAutenticacion servicioAutenticacion;

    private FachadaServicios() {
        this.servicioHipodromo = new ServicioHipodromo();
        this.servicioAutenticacion = new ServicioAutenticacion(servicioHipodromo.getHipodromo());
    }

    public static FachadaServicios getInstancia() {
        if (instancia == null) {
            instancia = new FachadaServicios();
        }
        return instancia;
    }

    public Login autenticarJugador(Credencial credencial) throws AutenticacionException {
        return servicioAutenticacion.auntenticarJugador(credencial);
    }

    public Administrador autenticarAdministrador(Credencial credencial) throws AutenticacionException {
        return servicioAutenticacion.autenticarAdministrador(credencial);
    }

    public void logout(Login login) {
        servicioAutenticacion.logout(login);
    }

    public void logoutAdministrador(Administrador administrador) {
        servicioAutenticacion.logoutAdministrador(administrador);
    }

    public Jornada getJornadaActual() {
        return servicioHipodromo.getJornadaActual();
    }

    public Jornada getJornadaAnterior(LocalDate fecha) {
        return servicioHipodromo.getJornadaAnterior(fecha);
    }

    public Jornada getJornadaSiguiente(LocalDate fecha) {
        return servicioHipodromo.getJornadaSiguiente(fecha);
    }

    public Carrera getCarrera(LocalDate fechaJornada, int numeroCarrera) {
        return servicioHipodromo.getCarrera(fechaJornada, numeroCarrera);
    }

    public double getComision() {
        return servicioHipodromo.getHipodromo().getComision();
    }

    public List<Apuesta> getApuestasJugador(Jugador jugador) {
        return servicioHipodromo.getApuestasJugador(jugador);
    }

    public List<Carrera> getCarrerasDisponibles() {
        return servicioHipodromo.getCarrerasDisponibles();
    }

    public List<ModalidadDeApuesta> getModalidades() {
        return servicioHipodromo.getHipodromo().getModalidades();
    }

    public Carrera getCarreraDisponible(int numeroCarrera) {
        return servicioHipodromo.getCarreraDisponible(numeroCarrera);
    }

    public void realizarApuesta(Jugador jugador, Participacion participacion, ModalidadDeApuesta modalidad, double monto) {
        servicioHipodromo.realizarApuesta(jugador, participacion, modalidad, monto);
    }
}
