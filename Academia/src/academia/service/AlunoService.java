package academia.service;

import academia.exception.CpfDuplicadoException;
import academia.exception.EntidadeNaoEncontradaException;
import academia.model.Aluno;
import academia.repository.AlunoRepositorio;

import java.time.LocalDate;
import java.util.List;


public class AlunoService {

    private final AlunoRepositorio repositorio;

    public AlunoService(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Aluno cadastrar(String nome, String cpf, LocalDate dataNascimento,
                           String telefone, String email) {
        repositorio.buscarPorCpf(cpf).ifPresent(a -> { throw new CpfDuplicadoException(cpf); });
        Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email);
        repositorio.salvar(aluno);
        return aluno;
    }

    public Aluno buscarPorId(int id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", id));
    }


    public List<Aluno> listarTodos() { 
    	return repositorio.listarTodos(); 
    	}

    public Aluno atualizar(int id, String nome, String telefone,
                           String email, LocalDate dataNascimento) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        aluno.setDataNascimento(dataNascimento);
        repositorio.atualizar(aluno);
        return aluno;
    }

    public void excluir(int id) {
        buscarPorId(id);
        repositorio.excluir(id);
    }
}
