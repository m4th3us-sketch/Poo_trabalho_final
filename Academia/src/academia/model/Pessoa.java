package academia.model;

public abstract class Pessoa {

    protected int id;
    protected String nome;
    protected String cpf;
    protected String telefone;
    
    public Pessoa(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public abstract String toFileString();

   
    public int getId() { 
    	return id; }
    public String getNome() { 
    	return nome; }
    public void setNome(String nome) {
    	this.nome = nome; }
    public String getCpf() { 
    	return cpf; }
    public void setCpf(String cpf) { 
    	this.cpf = cpf; }
    public String getTelefone() { 
    	return telefone; }
    public void setTelefone(String telefone) { 
    	this.telefone = telefone; }

    @Override
    public String toString() {
        return String.format("[%d] %s | CPF: %s | Tel: %s", id, nome, cpf, telefone);
    }
}
