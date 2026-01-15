# Documentação do Sistema CRUD

Bem-vindo à documentação completa do Sistema CRUD desenvolvido em Java puro.

## Índice

1. [Arquitetura do Sistema](01-arquitetura.md)
   - Visão geral da estrutura
   - Camadas do sistema
   - Princípios aplicados
   - Fluxo de dados

2. [Decisões de Design](02-decisoes-design.md)
   - Escolhas técnicas e suas justificativas
   - Padrões utilizados
   - Alternativas consideradas

3. [Fluxos de Execução](03-fluxos-execucao.md)
   - Detalhamento de cada operação CRUD
   - Fluxogramas das operações
   - Tratamento de erros

## Visão Geral do Projeto

Este é um sistema CRUD (Create, Read, Update, Delete) simples desenvolvido em Java puro, sem uso de frameworks externos. O objetivo principal é o aprendizado da linguagem Java e dos conceitos fundamentais de orientação a objetos.

### Funcionalidades

- ✅ Cadastrar novas pessoas (nome, email, telefone)
- ✅ Listar todas as pessoas cadastradas
- ✅ Buscar pessoa por email
- ✅ Atualizar dados de uma pessoa
- ✅ Remover pessoa do sistema

### Características Técnicas

- **Linguagem**: Java puro (sem frameworks)
- **Persistência**: Arquivo de texto (.txt)
- **Interface**: Menu interativo no terminal
- **Validação**: Validação completa de dados de entrada
- **Arquitetura**: Separação de responsabilidades em camadas

### Estrutura do Projeto

```
crud/
├── src/main/java/com/crud/
│   ├── Main.java                    # Classe principal
│   ├── model/
│   │   └── Pessoa.java             # Modelo de dados
│   ├── service/
│   │   └── PessoaService.java      # Lógica de negócio
│   ├── repository/
│   │   └── PessoaRepository.java   # Persistência
│   └── util/
│       └── Validador.java          # Validações
├── data/
│   └── pessoas.txt                 # Arquivo de dados
└── docs/
    ├── README.md                   # Este arquivo
    ├── 01-arquitetura.md           # Arquitetura
    ├── 02-decisoes-design.md       # Decisões de design
    └── 03-fluxos-execucao.md       # Fluxos de execução
```

## Como Usar

### Compilação

```bash
javac -d bin src/main/java/com/crud/**/*.java
```

### Execução

```bash
java -cp bin com.crud.Main
```

### Uso do Sistema

1. Execute o programa
2. Escolha uma opção do menu:
   - **1**: Cadastrar nova pessoa
   - **2**: Listar todas as pessoas
   - **3**: Buscar pessoa por email
   - **4**: Atualizar pessoa
   - **5**: Remover pessoa
   - **0**: Sair

## Conceitos Aplicados

### Orientação a Objetos
- Classes e objetos
- Encapsulamento
- Herança (implícita via Object)
- Polimorfismo

### Princípios SOLID
- **S**ingle Responsibility Principle
- **O**pen/Closed Principle (preparado para extensão)
- **L**iskov Substitution Principle
- **I**nterface Segregation (não aplicado diretamente, mas preparado)
- **D**ependency Inversion

### Boas Práticas
- Código limpo e legível
- Separação de responsabilidades
- Tratamento de erros
- Validação de dados
- Documentação adequada

## Aprendizados

Este projeto demonstra:
- Estruturação de projetos Java
- Manipulação de arquivos
- Validação de dados
- Interface de linha de comando
- Organização de código em pacotes
- Tratamento de exceções
- Princípios de design

## Melhorias Futuras

Possíveis melhorias que podem ser implementadas:
- Sistema de backup automático
- Busca avançada (por nome, telefone)
- Paginação para listas grandes
- Sistema de logs
- Tratamento de concorrência
- Interface gráfica (GUI)
- Migração para banco de dados
- Testes unitários

## Observações

- O arquivo de dados é criado automaticamente no diretório `data/`
- O email é usado como chave única
- Dados são normalizados antes de salvar (trim, lowercase)
- Operações destrutivas requerem confirmação

---

**Desenvolvido para aprendizado de Java e conceitos de OOP**
