package malapata.dtos;

import lombok.Data;
import malapata.dominio.Credencial;

@Data
public class CredencialDTO {

    private String nombreUsuario;
    private String contrasena;
    
    public CredencialDTO(){}

    public Credencial toCredencial(){
        return new Credencial(nombreUsuario, contrasena);
    }
}
