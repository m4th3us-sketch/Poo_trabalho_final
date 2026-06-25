package academia.repository;

import academia.model.Persistivel;
import java.util.List;
import java.util.Optional;


public interface Repositorio<T extends Persistivel> {

    void salvar(T entidade);
    Optional<T> buscarPorId(int id);
    List<T> listarTodos();

    void atualizar(T entidade);
    void excluir(int id);
    void carregarDoArquivo();
    void gravarNoArquivo();
}
