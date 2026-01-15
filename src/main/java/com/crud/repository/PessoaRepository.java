package com.crud.repository;

import com.crud.model.Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência de dados em arquivo de texto.
 * Implementa operações de leitura e escrita no arquivo.
 */
public class PessoaRepository {
    
    private static final String ARQUIVO_DADOS = "data/pessoas.txt";
    private File arquivo;

    /**
     * Construtor que inicializa o arquivo de dados.
     * Cria o diretório e arquivo se não existirem.
     */
    public PessoaRepository() {
        this.arquivo = new File(ARQUIVO_DADOS);
        criarDiretorioSeNecessario();
    }

    /**
     * Cria o diretório 'data' se ele não existir.
     */
    private void criarDiretorioSeNecessario() {
        File diretorio = arquivo.getParentFile();
        if (diretorio != null && !diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    /**
     * Lê todas as pessoas do arquivo.
     * 
     * @return Lista de pessoas lidas do arquivo
     * @throws IOException Se ocorrer erro na leitura do arquivo
     */
    public List<Pessoa> lerTodas() throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();
        
        if (!arquivo.exists()) {
            return pessoas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pessoa pessoa = Pessoa.fromFileFormat(linha);
                if (pessoa != null) {
                    pessoas.add(pessoa);
                }
            }
        }
        
        return pessoas;
    }

    /**
     * Salva uma lista de pessoas no arquivo.
     * 
     * @param pessoas Lista de pessoas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarTodas(List<Pessoa> pessoas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Pessoa pessoa : pessoas) {
                writer.write(pessoa.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Adiciona uma nova pessoa ao arquivo.
     * 
     * @param pessoa Pessoa a ser adicionada
     * @return true se a pessoa foi adicionada com sucesso
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public boolean adicionar(Pessoa pessoa) throws IOException {
        List<Pessoa> pessoas = lerTodas();
        
        // Verifica se já existe uma pessoa com o mesmo email
        for (Pessoa p : pessoas) {
            if (p.getEmail().equalsIgnoreCase(pessoa.getEmail())) {
                return false; // Email já existe
            }
        }
        
        pessoas.add(pessoa);
        salvarTodas(pessoas);
        return true;
    }

    /**
     * Busca uma pessoa pelo email.
     * 
     * @param email Email da pessoa a ser buscada
     * @return Pessoa encontrada ou null se não existir
     * @throws IOException Se ocorrer erro na leitura do arquivo
     */
    public Pessoa buscarPorEmail(String email) throws IOException {
        List<Pessoa> pessoas = lerTodas();
        
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getEmail().equalsIgnoreCase(email)) {
                return pessoa;
            }
        }
        
        return null;
    }

    /**
     * Atualiza os dados de uma pessoa existente.
     * 
     * @param pessoaAtualizada Pessoa com os dados atualizados
     * @return true se a pessoa foi atualizada com sucesso
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public boolean atualizar(Pessoa pessoaAtualizada) throws IOException {
        List<Pessoa> pessoas = lerTodas();
        
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getEmail().equalsIgnoreCase(pessoaAtualizada.getEmail())) {
                pessoas.set(i, pessoaAtualizada);
                salvarTodas(pessoas);
                return true;
            }
        }
        
        return false; // Pessoa não encontrada
    }

    /**
     * Remove uma pessoa do arquivo pelo email.
     * 
     * @param email Email da pessoa a ser removida
     * @return true se a pessoa foi removida com sucesso
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public boolean remover(String email) throws IOException {
        List<Pessoa> pessoas = lerTodas();
        
        boolean removido = pessoas.removeIf(p -> p.getEmail().equalsIgnoreCase(email));
        
        if (removido) {
            salvarTodas(pessoas);
        }
        
        return removido;
    }
}
