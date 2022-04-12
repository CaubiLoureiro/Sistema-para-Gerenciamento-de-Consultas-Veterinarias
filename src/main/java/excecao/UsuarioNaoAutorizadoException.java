package excecao;

public class UsuarioNaoAutorizadoException extends RuntimeException{
    
    private final static long serialVersionUID = 1;

    public UsuarioNaoAutorizadoException() {
        super();
    }

    public UsuarioNaoAutorizadoException(String msg) {
        super(msg);
    }
}