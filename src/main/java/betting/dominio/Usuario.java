package betting.dominio;

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

    public boolean esValida(Credencial credencial){
        return this.nombreUsuario.equals(credencial.getNombreUsuario()) && this.contrasena.equals(credencial.getContrasena());
    }
}
