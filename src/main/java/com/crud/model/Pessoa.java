package com.crud.model;

/**
 * Classe que representa uma Pessoa no sistema.
 * Contém os dados básicos: nome, email e telefone.
 */
public class Pessoa {
    private String nome;
    private String email;
    private String telefone;

    /**
     * Construtor padrão.
     */
    public Pessoa() {
    }

    /**
     * Construtor com parâmetros.
     * 
     * @param nome Nome da pessoa
     * @param email Email da pessoa
     * @param telefone Telefone da pessoa
     */
    public Pessoa(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna uma representação em string da pessoa.
     * 
     * @return String formatada com os dados da pessoa
     */
    @Override
    public String toString() {
        return "Nome: " + nome + " | Email: " + email + " | Telefone: " + telefone;
    }

    /**
     * Converte a pessoa para formato de linha no arquivo.
     * Formato: nome|email|telefone
     * 
     * @return String formatada para persistência
     */
    public String toFileFormat() {
        return nome + "|" + email + "|" + telefone;
    }

    /**
     * Cria uma instância de Pessoa a partir de uma linha do arquivo.
     * 
     * @param linha Linha do arquivo no formato: nome|email|telefone
     * @return Instância de Pessoa ou null se a linha for inválida
     */
    public static Pessoa fromFileFormat(String linha) {
        if (linha == null || linha.trim().isEmpty()) {
            return null;
        }

        String[] partes = linha.split("\\|");
        if (partes.length != 3) {
            return null;
        }

        return new Pessoa(partes[0].trim(), partes[1].trim(), partes[2].trim());
    }

    /**
     * Compara duas pessoas pelo email (chave única).
     * 
     * @param obj Objeto a ser comparado
     * @return true se os emails forem iguais
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) obj;
        return email != null && email.equals(pessoa.email);
    }

    /**
     * Retorna o hash code baseado no email.
     * 
     * @return Hash code do email
     */
    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
