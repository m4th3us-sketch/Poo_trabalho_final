package academia.service;

import academia.exception.EntidadeNaoEncontradaException;
import academia.exception.FichaSemExercicioException;
import academia.model.Exercicio;
import academia.model.FichaTreino;
import academia.repository.AlunoRepositorio;
import academia.repository.ExercicioRepositorio;
import academia.repository.FichaTreinoRepositorio;

import java.time.LocalDate;
import java.util.List;

public class FichaTreinoService {

    private final FichaTreinoRepositorio fichaRepo;
    private final ExercicioRepositorio exercicioRepo;
    private final AlunoRepositorio alunoRepo;

    public FichaTreinoService(FichaTreinoRepositorio fichaRepo,
                              ExercicioRepositorio exercicioRepo,
                              AlunoRepositorio alunoRepo) {
        this.fichaRepo = fichaRepo;
        this.exercicioRepo = exercicioRepo;
        this.alunoRepo = alunoRepo;
    }

    public FichaTreino cadastrar(String descricao, String objetivo) {
        FichaTreino ficha = new FichaTreino(descricao, LocalDate.now(), objetivo);
        fichaRepo.salvar(ficha);
        return ficha;
    }


    public FichaTreino associarAluno(int fichaId, int alunoId) {
        FichaTreino ficha = buscarPorId(fichaId);
        alunoRepo.buscarPorId(alunoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", alunoId));
        List<Exercicio> exs = exercicioRepo.listarPorFicha(fichaId);
        if (exs.isEmpty()) throw new FichaSemExercicioException(fichaId);
        ficha.setAlunoId(alunoId);
        fichaRepo.atualizar(ficha);
        return ficha;
    }


    public Exercicio adicionarExercicio(int fichaId, String nome, int series, int repeticoes) {
        buscarPorId(fichaId);
        Exercicio ex = new Exercicio(fichaId, nome, series, repeticoes);
        exercicioRepo.salvar(ex);
        return ex;
    }

    public void removerExercicio(int exercicioId) {
        exercicioRepo.excluir(exercicioId);
    }


    public String imprimir(int fichaId) {
        FichaTreino ficha = buscarPorId(fichaId);
        ficha.setExercicios(exercicioRepo.listarPorFicha(fichaId));
        return ficha.imprimir();
    }

    /** @return todas as fichas */
    public List<FichaTreino> listarTodos() { return fichaRepo.listarTodos(); }

  
    public FichaTreino buscarPorId(int id) {
        return fichaRepo.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("FichaTreino", id));
    }

  
    public List<FichaTreino> listarPorAluno(int alunoId) {
        return fichaRepo.listarPorAluno(alunoId);
    }


    public void desativar(int fichaId) {
        FichaTreino ficha = buscarPorId(fichaId);
        ficha.setAtiva(false);
        fichaRepo.atualizar(ficha);
    }

 
    public void excluir(int fichaId) {
        buscarPorId(fichaId);
        exercicioRepo.listarPorFicha(fichaId)
                .forEach(e -> exercicioRepo.excluir(e.getId()));
        fichaRepo.excluir(fichaId);
    }
}
