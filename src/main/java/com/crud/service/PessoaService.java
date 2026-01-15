package com.crud.service;

import com.crud.model.Pessoa;
import com.crud.repository.PessoaRepository;
import com.crud.util.Validador;
import java.io.IOException;
import java.util.List;

/**
 * Classe de serviço que contém a lógica de negócio do CRUD.
 * Coordena as operações entre validação e persistência.
 */
public class PessoaService {
    
    private PessoaRepository repository;

    /**
     * Construtor que inicializa o repositório.
     */
    public PessoaService() {
        this.repository = new PessoaRepository();
    }

    /**
     * Cria uma nova pessoa no sistema.
     * 
     * @param nome Nome da pessoa
     * @param email Email da pessoa
     * @param telefone Telefone da pessoa
     * @return Mensagem de sucesso ou erro
     */
    public String criar(String nome, String email, String telefone) {
        try {
            // Valida os dados de entrada
            String erro = Validador.validarDados(nome, email, telefone);
            if (erro != null) {
                return erro;
            }

            // Verifica se o email já existe
            Pessoa existente = repository.buscarPorEmail(email);
            if (existente != null) {
                return "Erro: Já existe uma pessoa cadastrada com este email!";
            }

            // Cria e salva a nova pessoa
            Pessoa novaPessoa = new Pessoa(nome.trim(), email.trim().toLowerCase(), telefone.trim());
            boolean sucesso = repository.adicionar(novaPessoa);
            
            if (sucesso) {
                return "Pessoa cadastrada com sucesso!";
            } else {
                return "Erro ao cadastrar pessoa!";
            }
        } catch (IOException e) {
            return "Erro ao acessar o arquivo de dados: " + e.getMessage();
        }
    }

    /**
     * Lista todas as pessoas cadastradas.
     * 
     * @return Lista de pessoas ou mensagem de erro
     */
    public String listarTodas() {
        try {
            List<Pessoa> pessoas = repository.lerTodas();
            
            if (pessoas.isEmpty()) {
                return "Nenhuma pessoa cadastrada ainda.";
            }

            StringBuilder resultado = new StringBuilder();
            resultado.append("\n=== LISTA DE PESSOAS ===\n");
            resultado.append(String.format("%-30s | %-30s | %-15s\n", "NOME", "EMAIL", "TELEFONE"));
            resultado.append("----------------------------------------------------------------------------\n");
            
            for (int i = 0; i < pessoas.size(); i++) {
                Pessoa p = pessoas.get(i);
                resultado.append(String.format("%-30s | %-30s | %-15s\n", 
                    p.getNome(), p.getEmail(), p.getTelefone()));
            }
            
            resultado.append("\nTotal: " + pessoas.size() + " pessoa(s) cadastrada(s).\n");
            
            return resultado.toString();
        } catch (IOException e) {
            return "Erro ao ler o arquivo de dados: " + e.getMessage();
        }
    }

    /**
     * Busca uma pessoa pelo email.
     * 
     * @param email Email da pessoa a ser buscada
     * @return Dados da pessoa ou mensagem de erro
     */
    public String buscarPorEmail(String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return "Email não pode estar vazio!";
            }

            Pessoa pessoa = repository.buscarPorEmail(email.trim());
            
            if (pessoa == null) {
                return "Pessoa não encontrada com o email informado.";
            }

            return "\n=== PESSOA ENCONTRADA ===\n" + pessoa.toString() + "\n";
        } catch (IOException e) {
            return "Erro ao ler o arquivo de dados: " + e.getMessage();
        }
    }

    /**
     * Atualiza os dados de uma pessoa existente.
     * 
     * @param email Email da pessoa a ser atualizada
     * @param novoNome Novo nome (pode ser null para manter o atual)
     * @param novoTelefone Novo telefone (pode ser null para manter o atual)
     * @return Mensagem de sucesso ou erro
     */
    public String atualizar(String email, String novoNome, String novoTelefone) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return "Email não pode estar vazio!";
            }

            Pessoa pessoa = repository.buscarPorEmail(email.trim());
            
            if (pessoa == null) {
                return "Pessoa não encontrada com o email informado.";
            }

            // Atualiza apenas os campos fornecidos
            if (novoNome != null && !novoNome.trim().isEmpty()) {
                if (!Validador.validarNome(novoNome)) {
                    return "Nome inválido! Deve ter pelo menos 2 caracteres.";
                }
                pessoa.setNome(novoNome.trim());
            }

            if (novoTelefone != null && !novoTelefone.trim().isEmpty()) {
                if (!Validador.validarTelefone(novoTelefone)) {
                    return "Telefone inválido! Deve conter pelo menos 10 dígitos.";
                }
                pessoa.setTelefone(novoTelefone.trim());
            }

            boolean sucesso = repository.atualizar(pessoa);
            
            if (sucesso) {
                return "Pessoa atualizada com sucesso!";
            } else {
                return "Erro ao atualizar pessoa!";
            }
        } catch (IOException e) {
            return "Erro ao acessar o arquivo de dados: " + e.getMessage();
        }
    }

    /**
     * Remove uma pessoa do sistema pelo email.
     * 
     * @param email Email da pessoa a ser removida
     * @return Mensagem de sucesso ou erro
     */
    public String remover(String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return "Email não pode estar vazio!";
            }

            boolean sucesso = repository.remover(email.trim());
            
            if (sucesso) {
                return "Pessoa removida com sucesso!";
            } else {
                return "Pessoa não encontrada com o email informado.";
            }
        } catch (IOException e) {
            return "Erro ao acessar o arquivo de dados: " + e.getMessage();
        }
    }
}
