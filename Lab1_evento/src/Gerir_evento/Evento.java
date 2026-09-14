package Gerir_evento;

public class Evento {
    private String titulo;
    private String data;
    private String local;
    private int lotacaoMaxima;

    public Evento(String titulo, String data, String local, int lotacaoMaxima) {
        this.titulo = titulo;
        this.data = data;
        this.local = local;
        this.lotacaoMaxima = lotacaoMaxima;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    @Override
    public String toString() {//para dpois listar 
        return "| Evento " + titulo + " | Data: " + data + " | Local: " + local + " | Lotação Máx: " + lotacaoMaxima+" |";
    }
}