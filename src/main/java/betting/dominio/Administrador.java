package betting.dominio;

import lombok.Getter;
import lombok.Setter;

public class Administrador extends Usuario{

    @Getter
    @Setter
    private boolean estaLogueado = false;
    
    public Administrador(String nombreUsuario, String nombreCompleto, String contrasena){
        super(nombreUsuario, nombreCompleto, contrasena);
    }
}
