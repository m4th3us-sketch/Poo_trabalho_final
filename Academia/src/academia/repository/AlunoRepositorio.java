package academia.repository;

import academia.model.Aluno;
import java.util.Optional;

public class AlunoRepositorio extends RepositorioBase<Aluno> {

    public AlunoRepositorio(String dataDir) {
        super(dataDir + "/alunos.txt");
    }

    @Override
    protected Aluno fromFileString(String linha) {
        return Aluno.fromFileString(linha);
    }
    
    public Optional<Aluno> buscarPorCpf(String cpf) {
        return lista.stream().filter(a -> a.getCpf().equals(cpf)).findFirst();
    }
}
