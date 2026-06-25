package academia.exception;


public class FichaSemExercicioException extends AcademiaException {

    public FichaSemExercicioException(int fichaId) {
        super("FICHA_SEM_EXERCICIO",
              "A ficha de treino #" + fichaId + " não possui exercícios cadastrados.");
    }
}
