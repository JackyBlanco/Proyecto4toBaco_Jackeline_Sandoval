package proyecto4tobaco;

public class usuario {
    String nombreUsuario, contraUsuario;
    public usuario(String nombreUsuario, String contraUsuario) {
        
        this.nombreUsuario = nombreUsuario;
        this.contraUsuario = contraUsuario;
    }
    public String getName() {
        return this.nombreUsuario;
    }
    public String getPass() {
        return this.contraUsuario;
    }
}
