package academia.exception;


public class AcademiaException extends RuntimeException {

    private final String codigo;

    
    public AcademiaException(String codigo, String mensagem) {
        super(mensagem);
        this.codigo = codigo;
    }

    public AcademiaException(String codigo, String mensagem, Throwable causa) {
        super(mensagem, causa);
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + getMessage();
    }
}
