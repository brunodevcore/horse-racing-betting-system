package betting.dominio;

import lombok.Getter;
import java.util.Date;

public class Login {
    
    @Getter
    private Date fechaHoraIngreso;

    @Getter
    private Usuario usuario;

    public Login(Date fechaHoraIngreso, Usuario usuario){
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.usuario = usuario;
    }
}
