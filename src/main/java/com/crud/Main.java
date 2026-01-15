package com.crud;

import com.crud.service.PessoaService;
import java.util.Scanner;

/**
 * Classe principal do sistema CRUD.
 * Responsável pela interface de menu interativo no terminal.
 */
public class Main {
    
    private static PessoaService pessoaService;
    private static Scanner scanner;

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        pessoaService = new PessoaService();
        scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("    SISTEMA CRUD - GERENCIAMENTO      ");
        System.out.println("========================================\n");
        
        boolean executando = true;
        
        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    criarPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    buscarPessoa();
                    break;
                case 4:
                    atualizarPessoa();
                    break;
                case 5:
                    removerPessoa();
                    break;
                case 0:
                    executando = false;
                    System.out.println("\nEncerrando o sistema...");
                    System.out.println("Obrigado por usar o Sistema CRUD!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.\n");
            }
            
            if (executando) {
                pausar();
            }
        }
        
        scanner.close();
    }

    /**
     * Exibe o menu principal do sistema.
     */
    private static void exibirMenu() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastrar nova pessoa");
        System.out.println("2. Listar todas as pessoas");
        System.out.println("3. Buscar pessoa por email");
        System.out.println("4. Atualizar pessoa");
        System.out.println("5. Remover pessoa");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    /**
     * Lê a opção escolhida pelo usuário.
     * 
     * @return Número da opção escolhida
     */
    private static int lerOpcao() {
        try {
            String entrada = scanner.nextLine().trim();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1; // Retorna valor inválido
        }
    }

    /**
     * Solicita os dados e cria uma nova pessoa.
     */
    private static void criarPessoa() {
        System.out.println("\n=== CADASTRAR NOVA PESSOA ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        String resultado = pessoaService.criar(nome, email, telefone);
        System.out.println("\n" + resultado);
    }

    /**
     * Lista todas as pessoas cadastradas.
     */
    private static void listarPessoas() {
        System.out.println("\n=== LISTAR TODAS AS PESSOAS ===");
        String resultado = pessoaService.listarTodas();
        System.out.println(resultado);
    }

    /**
     * Busca uma pessoa pelo email.
     */
    private static void buscarPessoa() {
        System.out.println("\n=== BUSCAR PESSOA POR EMAIL ===");
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        
        String resultado = pessoaService.buscarPorEmail(email);
        System.out.println(resultado);
    }

    /**
     * Atualiza os dados de uma pessoa existente.
     */
    private static void atualizarPessoa() {
        System.out.println("\n=== ATUALIZAR PESSOA ===");
        System.out.print("Digite o email da pessoa a ser atualizada: ");
        String email = scanner.nextLine();
        
        System.out.print("Novo nome (pressione Enter para manter o atual): ");
        String novoNome = scanner.nextLine();
        
        System.out.print("Novo telefone (pressione Enter para manter o atual): ");
        String novoTelefone = scanner.nextLine();
        
        String resultado = pessoaService.atualizar(email, novoNome, novoTelefone);
        System.out.println("\n" + resultado);
    }

    /**
     * Remove uma pessoa do sistema.
     */
    private static void removerPessoa() {
        System.out.println("\n=== REMOVER PESSOA ===");
        System.out.print("Digite o email da pessoa a ser removida: ");
        String email = scanner.nextLine();
        
        System.out.print("Tem certeza que deseja remover? (s/n): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacao.equals("s") || confirmacao.equals("sim")) {
            String resultado = pessoaService.remover(email);
            System.out.println("\n" + resultado);
        } else {
            System.out.println("\nOperação cancelada.");
        }
    }

    /**
     * Pausa a execução aguardando o usuário pressionar Enter.
     */
    private static void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
        System.out.println();
    }
}
