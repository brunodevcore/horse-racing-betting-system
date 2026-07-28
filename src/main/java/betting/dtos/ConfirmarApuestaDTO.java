package betting.dtos;

import betting.dominio.Apuesta;
import betting.dominio.Carrera;
import betting.dominio.Jugador;
import betting.dominio.ModalidadDeApuesta;
import betting.dominio.Participacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmarApuestaDTO {
    
    private int numeroCarrera;
    private String nombreCarrera;
    private int numeroCaballo;
    private String nombreCaballo;
    private double dividendoActual;
    private double monto;
    private String tipoApuesta;
    private double montoAPagar;
    private double montoSiGana;

    public ConfirmarApuestaDTO() {}

    public ConfirmarApuestaDTO(Carrera carrera, Participacion participacion, ModalidadDeApuesta modalidad, double monto, Jugador jugador) {
    this.numeroCarrera = carrera.getNumero();
    this.nombreCarrera = carrera.getNombre();
    this.numeroCaballo = participacion.getNumero();
    this.nombreCaballo = participacion.getCaballo().getNombre();
    this.dividendoActual = participacion.getDividendo();
    this.monto = monto;
    this.tipoApuesta = modalidad.getNombre();
    
    Apuesta apuestaTemporalApuesta = new Apuesta(jugador, participacion, modalidad, monto);
    this.montoAPagar = modalidad.calcularCosto(apuestaTemporalApuesta);
    this.montoSiGana = modalidad.calcularPago(apuestaTemporalApuesta);
}
}
