/**
 * 
 */
package Gerir_evento;

/**
 * 
 */
public class Inscricao {
    public enum EstadoInscricao {
        PENDENTE, CONFIRMADA, CANCELADA
    }

    private Participante participante;
    private Evento evento;
    private EstadoInscricao estado;

    public Inscricao(Participante participante, Evento evento) {
        this.participante = participante;
        this.evento = evento;
        this.estado = EstadoInscricao.PENDENTE; // Estado inicial padrão ou seja sempre pendente 
    }

    public Participante getParticipante() {
        return participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public EstadoInscricao getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscricao estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return participante.getNome() + " -> " + evento.getTitulo() + " | " + estado + " |";
    }
}