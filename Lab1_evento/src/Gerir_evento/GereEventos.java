/**
 * 
 */
package Gerir_evento;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 */
public class GereEventos {

	private List<Evento> eventos;
    private List<Participante> participantes;
    private List<Inscricao> inscricoes;

    public GereEventos() {
        this.eventos = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
    }

    // Registar evento
    public void registarEvento(Evento e) {
        eventos.add(e);
        System.out.println("Evento '" + e.getTitulo() + "' registado com sucesso!");
    }

    // Registar participante
    public void registarParticipante(Participante p) {
        participantes.add(p);
        System.out.println("Participante '" + p.getNome() + "' registado com sucesso!");
    }

    // Registar inscrição impedindo duplicados e respeitando a lotação
    public boolean registarInscricao(Participante p, Evento e) {
        // Verificar se já existe inscrição deste participante neste evento
        for (Inscricao i : inscricoes) {
            if (i.getParticipante().getEmail().equals(p.getEmail()) && 
                i.getEvento().getTitulo().equals(e.getTitulo()) && 
                i.getEstado() != Inscricao.EstadoInscricao.CANCELADA) {
                System.out.println("Erro: O participante já está inscrito neste evento!");
                return false;
            }
        }

        // Verificar vagas disponíveis
        if (obterVagasDisponiveis(e) <= 0) {
            System.out.println("Erro: O evento já atingiu a lotação máxima!");
            return false;
        }

        Inscricao novaInscricao = new Inscricao(p, e);
        novaInscricao.setEstado(Inscricao.EstadoInscricao.CONFIRMADA);
        inscricoes.add(novaInscricao);
        System.out.println("Inscrição efetuada e CONFIRMADA com sucesso!");
        return true;
    }

    // Confirmar ou Cancelar inscrição
    public void alterarEstadoInscricao(String emailParticipante, String tituloEvento, Inscricao.EstadoInscricao novoEstado) {
        for (Inscricao i : inscricoes) {
            if (i.getParticipante().getEmail().equalsIgnoreCase(emailParticipante) &&
                i.getEvento().getTitulo().equalsIgnoreCase(tituloEvento)) {
                i.setEstado(novoEstado);
                System.out.println("Estado da inscrição alterado para: " + novoEstado);
                return;
            }
        }
        System.out.println("Inscrição não encontrada.");
    }

    // Listar participantes confirmados de um evento
    public void listarParticipantesConfirmados(Evento e) {
        System.out.println("\n--- Participantes Confirmados no Evento: " + e.getTitulo() + " ---");
        boolean encontrou = false;
        for (Inscricao i : inscricoes) {
            if (i.getEvento().getTitulo().equals(e.getTitulo()) && i.getEstado() == Inscricao.EstadoInscricao.CONFIRMADA) {
                System.out.println("- " + i.getParticipante().getNome() + " (" + i.getParticipante().getEmail() + ")");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum participante confirmado até ao momento.");
        }
    }

    // Indicar o número de vagas disponíveis
    public int obterVagasDisponiveis(Evento e) {
        int confirmados = 0;
        for (Inscricao i : inscricoes) {
            if (i.getEvento().getTitulo().equals(e.getTitulo()) && i.getEstado() == Inscricao.EstadoInscricao.CONFIRMADA) {
                confirmados++;
            }
        }
        return e.getLotacaoMaxima() - confirmados;
    }

    // Apresentar o evento com maior número de inscrições confirmadas
    public Evento obterEventoComMaisInscricoesConfirmadas() {
        if (eventos.isEmpty()) return null;

        Evento maisPopular = null;
        int maxInscricoes = -1;

        for (Evento e : eventos) {
            int count = 0;
            for (Inscricao i : inscricoes) {
                if (i.getEvento().getTitulo().equals(e.getTitulo()) && i.getEstado() == Inscricao.EstadoInscricao.CONFIRMADA) {
                    count++;
                }
            }
            if (count > maxInscricoes) {
                maxInscricoes = count;
                maisPopular = e;
            }
        }
        return maisPopular;
    }

    // Getters auxiliares para o menu
    public List<Evento> getEventos() { 
    	return eventos; 
    	}
    public List<Participante> getParticipantes() {
    	return participantes; 
    	}
	
}
