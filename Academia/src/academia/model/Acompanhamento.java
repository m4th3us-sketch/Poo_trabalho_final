package academia.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Acompanhamento implements Persistivel {

    private static int contadorId = 1;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private int alunoId;
    private LocalDate data;
    private String observacoes;
    private String evolucao;

    public Acompanhamento(int id, int alunoId, LocalDate data,
                          String observacoes, String evolucao) {
        this.id = id;
        this.alunoId = alunoId;
        this.data = data;
        this.observacoes = observacoes;
        this.evolucao = evolucao;
        if (id >= contadorId) contadorId = id + 1;
    }

    
    public Acompanhamento(int alunoId, LocalDate data, String observacoes, String evolucao) {
        this(contadorId++, alunoId, data, observacoes, evolucao);
    }

   
    @Override
    public String toFileString() {
        return id + "|" + alunoId + "|" + data.format(FMT) + "|"
                + observacoes.replace("|", "[PIPE]") + "|"
                + evolucao.replace("|", "[PIPE]");
    }

   
    public static Acompanhamento fromFileString(String linha) {
        String[] p = linha.split("\\|", 5);
        return new Acompanhamento(
                Integer.parseInt(p[0]),
                Integer.parseInt(p[1]),
                LocalDate.parse(p[2], FMT),
                p[3].replace("[PIPE]", "|"),
                p[4].replace("[PIPE]", "|"));
    }

    public static void resetContador(int valor) { contadorId = valor; }

    @Override public int getId() { return id; }
    public int getAlunoId() { return alunoId; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getEvolucao() { return evolucao; }
    public void setEvolucao(String evolucao) { this.evolucao = evolucao; }

    @Override
    public String toString() {
        return String.format("[%d] Aluno#%d | %s | Obs: %s | Evol: %s",
                id, alunoId, data.format(FMT), observacoes, evolucao);
    }
}
