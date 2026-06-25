package academia.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Aluno extends Pessoa implements Persistivel {

    private static int contadorId = 1;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate dataNascimento;
    private String email;

    public Aluno(int id, String nome, String cpf, LocalDate dataNascimento,
                 String telefone, String email) {
        super(id, nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        if (id >= contadorId) contadorId = id + 1;
    }

    public Aluno(String nome, String cpf, LocalDate dataNascimento,
                 String telefone, String email) {
        this(contadorId++, nome, cpf, dataNascimento, telefone, email);
    }

    @Override
    public String toFileString() {
        return id + "|" + nome + "|" + cpf + "|"
                + dataNascimento.format(FMT) + "|" + telefone + "|" + email;
    }

    public static Aluno fromFileString(String linha) {
        String[] p = linha.split("\\|");
        return new Aluno(Integer.parseInt(p[0]), p[1], p[2],
                LocalDate.parse(p[3], FMT), p[4], p[5]);
    }

    public static void resetContador(int valor) { contadorId = valor; }

    public static int getContadorId() { return contadorId; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("[%d] %s | CPF: %s | Nasc: %s | Tel: %s | Email: %s",
                id, nome, cpf, dataNascimento.format(FMT), telefone, email);
    }
}
