package academia.repository;

import academia.model.FichaTreino;
import java.util.List;
import java.util.stream.Collectors;

public class FichaTreinoRepositorio extends RepositorioBase<FichaTreino> {

    public FichaTreinoRepositorio(String dataDir) {
        super(dataDir + "/fichas.txt");
    }

    @Override
    protected FichaTreino fromFileString(String linha) {
        return FichaTreino.fromFileString(linha);
    }

    public List<FichaTreino> listarPorAluno(int alunoId) {
        return lista.stream()
                .filter(f -> f.getAlunoId() == alunoId && f.isAtiva())
                .collect(Collectors.toList());
    }
}
