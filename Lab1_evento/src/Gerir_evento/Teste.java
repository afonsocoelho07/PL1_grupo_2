package Gerir_evento;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        GereEventos gestor = new GereEventos();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTÃO DE EVENTOS (UPT)     ");
            System.out.println("==========================================");
            System.out.println("1. Registar Evento");
            System.out.println("2. Registar Participante");
            System.out.println("3. Registar Inscrição");
            System.out.println("4. Cancelar Inscrição");
            System.out.println("5. Listar Participantes Confirmados de um Evento");
            System.out.println("6. Ver Vagas Disponíveis num Evento");
            System.out.println("7. Evento com Maior Número de Inscrições Confirmadas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
            } else {
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Título do Evento: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Data (ex: 01/01/2026): ");
                    String data = scanner.nextLine();
                    System.out.print("Local: ");
                    String local = scanner.nextLine();
                    System.out.print("Lotação Máxima: ");
                    int lotacao = scanner.nextInt();
                    scanner.nextLine();
                    gestor.registarEvento(new Evento(titulo, data, local, lotacao));
                    break;

                case 2:
                    System.out.print("Nome do Participante: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email do Participante: ");
                    String email = scanner.nextLine();
                    gestor.registarParticipante(new Participante(nome, email));
                    break;

                case 3:
                	if (gestor.getEventos().size() == 0 || gestor.getParticipantes().size() == 0) {
                	    System.out.println("É necessário ter pelo menos 1 evento e 1 participante registados!");
                	    break;
                	}

                	System.out.print("Email do Participante: ");
                	String emailInsc = scanner.nextLine();
                	System.out.print("Título do Evento: ");
                	String tituloInsc = scanner.nextLine();

                	// Procura o participante com loop tradicional
                	Participante pEncontrado = null;
                	for (Participante p : gestor.getParticipantes()) {
                	    if (p.getEmail().equalsIgnoreCase(emailInsc)) {
                	        pEncontrado = p;
                	        break;
                	    }
                	}

                	// Procura o evento com loop tradicional
                	Evento eEncontrado = null;
                	for (Evento e : gestor.getEventos()) {
                	    if (e.getTitulo().equalsIgnoreCase(tituloInsc)) {
                	        eEncontrado = e;
                	        break;
                	    }
                	}

                	// Validação dos resultados
                	if (pEncontrado == null) {
                	    System.out.println("Erro: Participante não encontrado com esse email!");
                	} else if (eEncontrado == null) {
                	    System.out.println("Erro: Evento não encontrado com esse título!");
                	} else {
                	    gestor.registarInscricao(pEncontrado, eEncontrado);
                	}
                	break;

                case 4:
                    System.out.print("Email do Participante: ");
                    String emailCanc = scanner.nextLine();
                    System.out.print("Título do Evento: ");
                    String tituloCanc = scanner.nextLine();
                    gestor.alterarEstadoInscricao(emailCanc, tituloCanc, Inscricao.EstadoInscricao.CANCELADA);
                    break;

                case 5:
                	System.out.print("Título do Evento: ");
                	String tituloList = scanner.nextLine();
                	Evento eList = null;
                	for (Evento e : gestor.getEventos()) {
                	    if (e.getTitulo().equalsIgnoreCase(tituloList)) {
                	        eList = e;
                	        break;
                	    }
                	}

                	if (eList != null) {
                	    gestor.listarParticipantesConfirmados(eList);
                	} else {
                	    System.out.println("Evento não encontrado!");
                	}
                	break;

                case 6:
                	System.out.print("Título do Evento: ");
                	String tituloVagas = scanner.nextLine();
                	Evento eVagas = null;
                	for (Evento e : gestor.getEventos()) {
                	    if (e.getTitulo().equalsIgnoreCase(tituloVagas)) {
                	        eVagas = e;
                	        break;
                	    }
                	}

                	if (eVagas != null) {
                	    System.out.println("Vagas disponíveis para '" + eVagas.getTitulo() + "': " + gestor.obterVagasDisponiveis(eVagas));
                	} else {
                	    System.out.println("Evento não encontrado!");
                	}
                	break;

                case 7:
                    Evento topEvento = gestor.obterEventoComMaisInscricoesConfirmadas();
                    if (topEvento != null) {
                        System.out.println("Evento mais concorrido: " + topEvento.getTitulo() + " (Lotação: " + topEvento.getLotacaoMaxima() + ")");
                    } else {
                        System.out.println("Ainda não existem inscrições confirmadas em nenhum evento.");
                    }
                    break;

                case 0:
                    System.out.println("A encerrar o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}