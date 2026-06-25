package academia.exception;

public class CpfDuplicadoException extends AcademiaException {

    public CpfDuplicadoException(String cpf) {
        super("CPF_DUPLICADO",
              "Já existe um aluno cadastrado com o CPF: " + cpf);
    }
}
