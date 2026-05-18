package malapata.modelo;

import lombok.Getter;

public abstract class Usuario {
    
    @Getter
    private String nombreUsuario;

    @Getter
    private String nombreCompleto;

    private String contrasena;

    public Usuario(String nombreUsuario, String nombreCompleto, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.contrasena = contrasena;
    }

    public boolean esValida(String nombreUsuario, String contrasena){
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }
}
