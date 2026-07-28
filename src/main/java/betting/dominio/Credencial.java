package betting.dominio;

import lombok.Getter;
import lombok.Setter;

public class Credencial {
    
    @Getter
    @Setter
    private String nombreUsuario;

    @Getter
    @Setter
    private String contrasena;

    public Credencial(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }
}
