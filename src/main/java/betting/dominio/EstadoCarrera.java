package betting.dominio;

public interface EstadoCarrera {

    void abrir(Carrera carrera);
    void cerrar(Carrera carrera);
    void finalizar(Carrera carrera, Participacion ganador);
    void realizarApuesta(Carrera carrera);

    String getNombre();
}
