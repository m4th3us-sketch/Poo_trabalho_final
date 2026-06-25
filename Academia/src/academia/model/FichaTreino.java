package academia.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FichaTreino implements Persistivel {

    private static int contadorId = 1;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private int alunoId;
    private String descricao;
    private LocalDate dataCriacao;
    private String objetivo;
    private boolean ativa;
    private List<Exercicio> exercicios = new ArrayList<>();
   
    public FichaTreino(int id, int alunoId, String descricao, LocalDate dataCriacao,
                       String objetivo, boolean ativa) {
        this.id = id;
        this.alunoId = alunoId;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.objetivo = objetivo;
        this.ativa = ativa;
        if (id >= contadorId) contadorId = id + 1;
    }


    public FichaTreino(String descricao, LocalDate dataCriacao, String objetivo) {
        this(contadorId++, 0, descricao, dataCriacao, objetivo, true);
    }

    @Override
    public String toFileString() {
        return id + "|" + alunoId + "|" + descricao + "|"
                + dataCriacao.format(FMT) + "|" + objetivo + "|" + ativa;
    }

   
    public static FichaTreino fromFileString(String linha) {
        String[] p = linha.split("\\|");
        return new FichaTreino(Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                p[2], LocalDate.parse(p[3], FMT), p[4], Boolean.parseBoolean(p[5]));
    }

    
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("==== FICHA DE TREINO #").append(id).append(" ====\n");
        sb.append("Descrição : ").append(descricao).append("\n");
        sb.append("Objetivo  : ").append(objetivo).append("\n");
        sb.append("Criação   : ").append(dataCriacao.format(FMT)).append("\n");
        sb.append("Ativa     : ").append(ativa ? "Sim" : "Não").append("\n");
        sb.append("Aluno ID  : ").append(alunoId > 0 ? alunoId : "Não associada").append("\n");
        sb.append("Exercícios:\n");
        for (Exercicio e : exercicios) {
            sb.append("  - ").append(e).append("\n");
        }
        return sb.toString();
    }

    public static void resetContador(int valor) { contadorId = valor; }

    @Override public int getId() { return id; }
    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
    public List<Exercicio> getExercicios() { return exercicios; }
    public void setExercicios(List<Exercicio> exercicios) { this.exercicios = exercicios; }

    @Override
    public String toString() {
        return String.format("[%d] %s | Obj: %s | Aluno: %s | Ativa: %s",
                id, descricao, objetivo, alunoId > 0 ? "#" + alunoId : "livre", ativa);
    }
}
