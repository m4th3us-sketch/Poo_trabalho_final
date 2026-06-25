package academia.model;

public class Exercicio implements Persistivel {

    private static int contadorId = 1;
    private int id;
    private int fichaTreinoId;
    private String nome;
    private int series;
    private int repeticoes;

    
    public Exercicio(int id, int fichaTreinoId, String nome, int series, int repeticoes) {
        this.id = id;
        this.fichaTreinoId = fichaTreinoId;
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
        if (id >= contadorId) contadorId = id + 1;
    }

    public Exercicio(int fichaTreinoId, String nome, int series, int repeticoes) {
        this(contadorId++, fichaTreinoId, nome, series, repeticoes);
    }

    @Override
    public String toFileString() {
        return id + "|" + fichaTreinoId + "|" + nome + "|" + series + "|" + repeticoes;
    }

    public static Exercicio fromFileString(String linha) {
        String[] p = linha.split("\\|");
        return new Exercicio(Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
    }

    public static void resetContador(int valor) { contadorId = valor; }

    @Override public int getId() { return id; }
    public int getFichaTreinoId() { return fichaTreinoId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getSeries() { return series; }
    public void setSeries(int series) { this.series = series; }
    public int getRepeticoes() { return repeticoes; }
    public void setRepeticoes(int repeticoes) { this.repeticoes = repeticoes; }

    @Override
    public String toString() {
        return String.format("[%d] %s — %dx%d", id, nome, series, repeticoes);
    }
}
