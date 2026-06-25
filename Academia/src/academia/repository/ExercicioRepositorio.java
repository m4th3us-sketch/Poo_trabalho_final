package academia.repository;

import academia.model.Exercicio;
import java.util.List;
import java.util.stream.Collectors;

public class ExercicioRepositorio extends RepositorioBase<Exercicio> {

    public ExercicioRepositorio(String dataDir) {
        super(dataDir + "/exercicios.txt");
    }

    @Override
    protected Exercicio fromFileString(String linha) {
        return Exercicio.fromFileString(linha);
    }

    public List<Exercicio> listarPorFicha(int fichaTreinoId) {
        return lista.stream()
                .filter(e -> e.getFichaTreinoId() == fichaTreinoId)
                .collect(Collectors.toList());
    }
}
