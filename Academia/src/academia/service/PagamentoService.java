package academia.service;

import academia.exception.EntidadeNaoEncontradaException;
import academia.exception.PagamentoInvalidoException;
import academia.model.Pagamento;
import academia.repository.AlunoRepositorio;
import academia.repository.PagamentoRepositorio;

import java.time.LocalDate;
import java.util.List;


public class PagamentoService {

    private final PagamentoRepositorio repositorio;
    private final AlunoRepositorio alunoRepositorio;

    public PagamentoService(PagamentoRepositorio repositorio,
                            AlunoRepositorio alunoRepositorio) {
        this.repositorio = repositorio;
        this.alunoRepositorio = alunoRepositorio;
    }


    public Pagamento registrar(int alunoId, double valor, LocalDate dataPagamento,
                               String formaPagamento) {
        alunoRepositorio.buscarPorId(alunoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", alunoId));
        if (valor <= 0) throw new PagamentoInvalidoException("valor deve ser maior que zero.");
        if (formaPagamento == null || formaPagamento.isBlank())
            throw new PagamentoInvalidoException("forma de pagamento não informada.");

        Pagamento pag = new Pagamento(alunoId, valor, dataPagamento, formaPagamento, "PAGO");
        repositorio.salvar(pag);
        return pag;
    }

    
    public List<Pagamento> listarPorAluno(int alunoId) {
        return repositorio.listarPorAluno(alunoId);
    }

    public List<Pagamento> listarTodos() { 
    	return repositorio.listarTodos(); 
    	}

    public Pagamento buscarPorId(int id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento", id));
    }

    public void cancelar(int id) {
        Pagamento p = buscarPorId(id);
        p.setStatus("CANCELADO");
        repositorio.atualizar(p);
    }


    public void excluir(int id) {
        buscarPorId(id);
        repositorio.excluir(id);
    }
}
