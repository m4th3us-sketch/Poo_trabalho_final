package academia.exception;


public class EntidadeNaoEncontradaException extends AcademiaException {

    public EntidadeNaoEncontradaException(String entidade, int id) {
        super("ENTIDADE_NAO_ENCONTRADA",
              entidade + " com ID " + id + " não encontrado(a).");
    }
}
