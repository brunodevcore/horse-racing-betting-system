package betting.dtos;

import betting.dominio.Credencial;
import lombok.Data;

@Data
public class CredencialDTO {

    private String nombreUsuario;
    private String contrasena;
    
    public CredencialDTO(){}

    public Credencial toCredencial(){
        return new Credencial(nombreUsuario, contrasena);
    }
}
