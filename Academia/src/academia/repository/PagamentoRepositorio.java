package academia.repository;

import academia.model.Pagamento;
import java.util.List;
import java.util.stream.Collectors;

public class PagamentoRepositorio extends RepositorioBase<Pagamento> {

    public PagamentoRepositorio(String dataDir) {
        super(dataDir + "/pagamentos.txt");
    }

    @Override
    protected Pagamento fromFileString(String linha) {
        return Pagamento.fromFileString(linha);
    }
    
    public List<Pagamento> listarPorAluno(int alunoId) {
        return lista.stream()
                .filter(p -> p.getAlunoId() == alunoId)
                .collect(Collectors.toList());
    }
}
