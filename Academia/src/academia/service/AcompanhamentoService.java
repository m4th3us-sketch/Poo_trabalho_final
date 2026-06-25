package academia.service;

import academia.exception.EntidadeNaoEncontradaException;
import academia.model.Acompanhamento;
import academia.repository.AcompanhamentoRepositorio;
import academia.repository.AlunoRepositorio;

import java.time.LocalDate;
import java.util.List;


public class AcompanhamentoService {

    private final AcompanhamentoRepositorio repositorio;
    private final AlunoRepositorio alunoRepositorio;
    
    public AcompanhamentoService(AcompanhamentoRepositorio repositorio,
                                  AlunoRepositorio alunoRepositorio) {
        this.repositorio = repositorio;
        this.alunoRepositorio = alunoRepositorio;
    }
    
    public Acompanhamento registrar(int alunoId, LocalDate data,
                                    String observacoes, String evolucao) {
        alunoRepositorio.buscarPorId(alunoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", alunoId));
        Acompanhamento ac = new Acompanhamento(alunoId, data, observacoes, evolucao);
        repositorio.salvar(ac);
        return ac;
    }

    public List<Acompanhamento> listarPorAluno(int alunoId) {
        return repositorio.listarPorAluno(alunoId);
    }

    public List<Acompanhamento> listarTodos() { 
    	return repositorio.listarTodos(); }

    public Acompanhamento buscarPorId(int id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Acompanhamento", id));
    }


    public Acompanhamento atualizar(int id, String observacoes, String evolucao) {
        Acompanhamento ac = buscarPorId(id);
        ac.setObservacoes(observacoes);
        ac.setEvolucao(evolucao);
        repositorio.atualizar(ac);
        return ac;
    }

    public void excluir(int id) {
        buscarPorId(id);
        repositorio.excluir(id);
    }
}
