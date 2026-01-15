# Fluxos de Execução

Este documento descreve os fluxos de execução das principais operações do sistema CRUD.

## Fluxo Geral do Sistema

```
Início
  ↓
Main.main()
  ↓
Inicializar PessoaService
  ↓
Loop Principal (Menu)
  ↓
  ├─→ Opção 1: Criar Pessoa
  ├─→ Opção 2: Listar Pessoas
  ├─→ Opção 3: Buscar Pessoa
  ├─→ Opção 4: Atualizar Pessoa
  ├─→ Opção 5: Remover Pessoa
  └─→ Opção 0: Sair
```

## 1. Fluxo de Criação (Create)

```
Usuário seleciona opção 1
  ↓
Main.criarPessoa()
  ↓
Solicita: nome, email, telefone
  ↓
PessoaService.criar(nome, email, telefone)
  ↓
Validador.validarDados()
  ├─→ Inválido → Retorna mensagem de erro
  └─→ Válido → Continua
  ↓
PessoaRepository.buscarPorEmail()
  ├─→ Email existe → Retorna erro
  └─→ Email não existe → Continua
  ↓
Criar nova instância Pessoa
  ↓
PessoaRepository.adicionar(pessoa)
  ↓
PessoaRepository.lerTodas()
  ↓
Adiciona pessoa à lista
  ↓
PessoaRepository.salvarTodas(lista)
  ↓
Escreve no arquivo data/pessoas.txt
  ↓
Retorna mensagem de sucesso
  ↓
Main exibe resultado
```

### Detalhamento do Processo de Persistência

```
PessoaRepository.adicionar()
  ↓
lerTodas() → Lê arquivo linha por linha
  ↓
Para cada linha:
  Pessoa.fromFileFormat(linha)
    ↓
    Split por "|"
    ↓
    Cria objeto Pessoa
  ↓
Adiciona nova pessoa à lista
  ↓
salvarTodas() → Reescreve arquivo completo
  ↓
Para cada pessoa:
  pessoa.toFileFormat()
    ↓
    Formato: "nome|email|telefone"
  ↓
Escreve linha no arquivo
```

## 2. Fluxo de Listagem (Read All)

```
Usuário seleciona opção 2
  ↓
Main.listarPessoas()
  ↓
PessoaService.listarTodas()
  ↓
PessoaRepository.lerTodas()
  ↓
Verifica se arquivo existe
  ├─→ Não existe → Retorna lista vazia
  └─→ Existe → Continua
  ↓
Abre arquivo para leitura
  ↓
Para cada linha do arquivo:
  Pessoa.fromFileFormat(linha)
  ↓
Adiciona à lista
  ↓
Retorna lista de pessoas
  ↓
PessoaService formata saída
  ↓
Cria tabela formatada com colunas
  ↓
Retorna string formatada
  ↓
Main exibe resultado
```

## 3. Fluxo de Busca (Read One)

```
Usuário seleciona opção 3
  ↓
Main.buscarPessoa()
  ↓
Solicita email
  ↓
PessoaService.buscarPorEmail(email)
  ↓
Valida se email não está vazio
  ├─→ Vazio → Retorna erro
  └─→ Preenchido → Continua
  ↓
PessoaRepository.buscarPorEmail(email)
  ↓
PessoaRepository.lerTodas()
  ↓
Para cada pessoa na lista:
  Compara email (case-insensitive)
  ├─→ Encontrou → Retorna pessoa
  └─→ Não encontrou → Continua
  ↓
Retorna null se não encontrado
  ↓
PessoaService formata resultado
  ↓
Retorna dados da pessoa ou mensagem de erro
  ↓
Main exibe resultado
```

## 4. Fluxo de Atualização (Update)

```
Usuário seleciona opção 4
  ↓
Main.atualizarPessoa()
  ↓
Solicita: email, novoNome, novoTelefone
  ↓
PessoaService.atualizar(email, novoNome, novoTelefone)
  ↓
Valida se email não está vazio
  ├─→ Vazio → Retorna erro
  └─→ Preenchido → Continua
  ↓
PessoaRepository.buscarPorEmail(email)
  ├─→ Não encontrado → Retorna erro
  └─→ Encontrado → Continua
  ↓
Se novoNome fornecido:
  Validador.validarNome(novoNome)
    ├─→ Inválido → Retorna erro
    └─→ Válido → Atualiza nome
  ↓
Se novoTelefone fornecido:
  Validador.validarTelefone(novoTelefone)
    ├─→ Inválido → Retorna erro
    └─→ Válido → Atualiza telefone
  ↓
PessoaRepository.atualizar(pessoaAtualizada)
  ↓
PessoaRepository.lerTodas()
  ↓
Encontra pessoa na lista pelo email
  ↓
Substitui pessoa na lista
  ↓
PessoaRepository.salvarTodas(lista)
  ↓
Reescreve arquivo completo
  ↓
Retorna mensagem de sucesso
  ↓
Main exibe resultado
```

## 5. Fluxo de Remoção (Delete)

```
Usuário seleciona opção 5
  ↓
Main.removerPessoa()
  ↓
Solicita email
  ↓
Solicita confirmação (s/n)
  ├─→ Não confirmado → Cancela operação
  └─→ Confirmado → Continua
  ↓
PessoaService.remover(email)
  ↓
Valida se email não está vazio
  ├─→ Vazio → Retorna erro
  └─→ Preenchido → Continua
  ↓
PessoaRepository.remover(email)
  ↓
PessoaRepository.lerTodas()
  ↓
Remove pessoa da lista (removeIf)
  ├─→ Não encontrado → Retorna false
  └─→ Encontrado → Retorna true
  ↓
PessoaRepository.salvarTodas(lista)
  ↓
Reescreve arquivo completo (sem a pessoa removida)
  ↓
Retorna mensagem de sucesso ou erro
  ↓
Main exibe resultado
```

## 6. Fluxo de Validação

```
Validador.validarDados(nome, email, telefone)
  ↓
Validador.validarNome(nome)
  ├─→ null ou vazio → false
  ├─→ trim().length() < 2 → false
  └─→ OK → true
  ↓
Validador.validarEmail(email)
  ├─→ null ou vazio → false
  ├─→ Não match com regex → false
  └─→ OK → true
  ↓
Validador.validarTelefone(telefone)
  ├─→ null ou vazio → false
  ├─→ Remove caracteres especiais
  ├─→ Não match com regex → false
  ├─→ length < 10 → false
  └─→ OK → true
  ↓
Se todas válidas → Retorna null
Se alguma inválida → Retorna mensagem de erro específica
```

## Tratamento de Erros

### Erro de I/O
```
Operação de leitura/escrita
  ↓
IOException lançada
  ↓
Capturada no Service
  ↓
Convertida em mensagem amigável
  ↓
Retornada ao Main
  ↓
Exibida ao usuário
```

### Erro de Validação
```
Dados de entrada
  ↓
Validador verifica
  ↓
Dados inválidos
  ↓
Retorna mensagem específica
  ↓
Service retorna mensagem
  ↓
Main exibe ao usuário
  ↓
Operação não é executada
```

## Pontos de Atenção

1. **Reescrita Completa do Arquivo**: Toda operação de escrita reescreve o arquivo inteiro. Para arquivos muito grandes, isso pode ser ineficiente.

2. **Sem Tratamento de Concorrência**: Se múltiplas instâncias do programa rodarem simultaneamente, pode haver corrupção de dados.

3. **Case-Insensitive para Email**: Buscas e comparações de email são case-insensitive para melhor experiência do usuário.

4. **Normalização Automática**: Dados são normalizados (trim, lowercase) antes de salvar.

5. **Confirmação para Remoção**: Operação destrutiva requer confirmação explícita do usuário.
