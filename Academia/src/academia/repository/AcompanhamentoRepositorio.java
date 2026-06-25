package academia.repository;

import academia.model.Acompanhamento;
import java.util.List;
import java.util.stream.Collectors;

public class AcompanhamentoRepositorio extends RepositorioBase<Acompanhamento> {

    public AcompanhamentoRepositorio(String dataDir) {
        super(dataDir + "/acompanhamentos.txt");
    }

    @Override
    protected Acompanhamento fromFileString(String linha) {
        return Acompanhamento.fromFileString(linha);
    }
  
    public List<Acompanhamento> listarPorAluno(int alunoId) {
        return lista.stream()
                .filter(a -> a.getAlunoId() == alunoId)
                .collect(Collectors.toList());
    }
}
