package academia.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Pagamento implements Persistivel {

    private static int contadorId = 1;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int id;
    private int alunoId;
    private double valor;
    private LocalDate dataPagamento;
    private String formaPagamento;
    private String status;

    public Pagamento(int id, int alunoId, double valor, LocalDate dataPagamento,
                     String formaPagamento, String status) {
        this.id = id;
        this.alunoId = alunoId;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.status = status;
        if (id >= contadorId) contadorId = id + 1;
    }

 
    public Pagamento(int alunoId, double valor, LocalDate dataPagamento,
                     String formaPagamento, String status) {
        this(contadorId++, alunoId, valor, dataPagamento, formaPagamento, status);
    }

 
    @Override
    public String toFileString() {
        return id + "|" + alunoId + "|" + valor + "|"
                + dataPagamento.format(FMT) + "|" + formaPagamento + "|" + status;
    }

 
    public static Pagamento fromFileString(String linha) {
        String[] p = linha.split("\\|");
        return new Pagamento(Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                Double.parseDouble(p[2]), LocalDate.parse(p[3], FMT), p[4], p[5]);
    }

    public static void resetContador(int valor) { contadorId = valor; }

    @Override public int getId() { return id; }
    public int getAlunoId() { return alunoId; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("[%d] Aluno#%d | R$ %.2f | %s | %s | %s",
                id, alunoId, valor, dataPagamento.format(FMT), formaPagamento, status);
    }
}
