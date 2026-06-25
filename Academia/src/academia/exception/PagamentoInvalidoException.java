package academia.exception;

public class PagamentoInvalidoException extends AcademiaException {

    public PagamentoInvalidoException(String detalhe) {
        super("PAGAMENTO_INVALIDO", "Pagamento inválido: " + detalhe);
    }
}
