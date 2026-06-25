package academia.repository;

import academia.exception.AcademiaException;
import academia.model.Persistivel;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositorioBase<T extends Persistivel> implements Repositorio<T> {


    protected List<T> lista = new ArrayList<>();
    protected final String caminhoArquivo;

    protected RepositorioBase(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        carregarDoArquivo();
    }

    protected abstract T fromFileString(String linha);

    @Override
    public void salvar(T entidade) {
        lista.add(entidade);
        gravarNoArquivo();
    }

    @Override
    public Optional<T> buscarPorId(int id) {
        return lista.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(lista);
    }

    @Override
    public void atualizar(T entidade) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == entidade.getId()) {
                lista.set(i, entidade);
                gravarNoArquivo();
                return;
            }
        }
        throw new AcademiaException("ENTIDADE_NAO_ENCONTRADA",
                "Entidade com ID " + entidade.getId() + " não encontrada para atualização.");
    }

    @Override
    public void excluir(int id) {
        boolean removido = lista.removeIf(e -> e.getId() == id);
        if (!removido) {
            throw new AcademiaException("ENTIDADE_NAO_ENCONTRADA",
                    "Entidade com ID " + id + " não encontrada para exclusão.");
        }
        gravarNoArquivo();
    }

    @Override
    public void carregarDoArquivo() {
        lista.clear();
        Path path = Paths.get(caminhoArquivo);
        if (!Files.exists(path)) return;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isBlank()) lista.add(fromFileString(linha));
            }
        } catch (IOException e) {
            throw new AcademiaException("ERRO_LEITURA",
                    "Falha ao ler arquivo: " + caminhoArquivo, e);
        }
    }

    @Override
    public void gravarNoArquivo() {
        try {
            Files.createDirectories(Paths.get(caminhoArquivo).getParent());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
                for (T e : lista) {
                    bw.write(e.toFileString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new AcademiaException("ERRO_GRAVACAO",
                    "Falha ao gravar arquivo: " + caminhoArquivo, e);
        }
    }

    /** @return quantidade de entidades na lista */
    public int tamanho() { return lista.size(); }
}
