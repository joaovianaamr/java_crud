package com.crud.util;

import java.util.regex.Pattern;

/**
 * Classe utilitária para validação de dados de entrada.
 * Implementa validações para nome, email e telefone.
 */
public class Validador {

    // Padrão para validação de email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    // Padrão para validação de telefone (aceita vários formatos)
    private static final Pattern TELEFONE_PATTERN = Pattern.compile(
        "^[\\d\\s()+-]{10,15}$"
    );

    /**
     * Valida o nome da pessoa.
     * 
     * @param nome Nome a ser validado
     * @return true se o nome for válido
     */
    public static boolean validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        // Nome deve ter pelo menos 2 caracteres e não conter apenas espaços
        return nome.trim().length() >= 2;
    }

    /**
     * Valida o email da pessoa.
     * 
     * @param email Email a ser validado
     * @return true se o email for válido
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Valida o telefone da pessoa.
     * 
     * @param telefone Telefone a ser validado
     * @return true se o telefone for válido
     */
    public static boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            return false;
        }
        // Remove espaços e caracteres especiais para validação
        String telefoneLimpo = telefone.replaceAll("[\\s()-]", "");
        return TELEFONE_PATTERN.matcher(telefoneLimpo).matches() && telefoneLimpo.length() >= 10;
    }

    /**
     * Valida todos os campos de uma pessoa.
     * 
     * @param nome Nome a ser validado
     * @param email Email a ser validado
     * @param telefone Telefone a ser validado
     * @return Mensagem de erro ou null se tudo estiver válido
     */
    public static String validarDados(String nome, String email, String telefone) {
        if (!validarNome(nome)) {
            return "Nome inválido! Deve ter pelo menos 2 caracteres.";
        }
        if (!validarEmail(email)) {
            return "Email inválido! Use o formato: exemplo@dominio.com";
        }
        if (!validarTelefone(telefone)) {
            return "Telefone inválido! Deve conter pelo menos 10 dígitos.";
        }
        return null;
    }
}
