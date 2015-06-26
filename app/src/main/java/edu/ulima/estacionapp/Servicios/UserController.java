package edu.ulima.estacionapp.Servicios;

import edu.ulima.estacionapp.Bean.Usuario;

public class UserController {
    private static Usuario usuario;
    private static UserController instance;

    public static UserController getInstance(){
        if(instance==null)
            instance=new UserController();
        return instance;
    }
    public void UserController(){
        usuario = new Usuario();
    }
    public void setUsuario(Usuario user){
        usuario = new Usuario(user.getNombre(),user.getCorreo(),user.getType());
    }
    public Usuario getUsuario(){
        return usuario;
    }
}
