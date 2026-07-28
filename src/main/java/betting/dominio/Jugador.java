package betting.dominio;

import lombok.Getter;
import lombok.Setter;

public class Jugador extends Usuario{
    
    @Getter
    @Setter
    private double saldo;

    public Jugador(String nombreUsuario, String nombreCompleto, String contrasena, double saldo){
        super(nombreUsuario, nombreCompleto, contrasena);
        this.saldo = saldo;
    }

    public void descontarSaldo(double monto) {
    this.saldo -= monto;
}
}
