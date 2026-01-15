# Arquitetura do Sistema CRUD

## Visão Geral

Este documento descreve a arquitetura do sistema CRUD desenvolvido em Java puro, seguindo princípios de orientação a objetos e boas práticas de desenvolvimento.

## Estrutura de Camadas

O sistema foi organizado em camadas bem definidas, seguindo o padrão de separação de responsabilidades:

```
┌─────────────────────────────────┐
│         Main (UI)               │  ← Interface com o usuário
└──────────────┬──────────────────┘
               │
┌──────────────▼──────────────────┐
│      PessoaService              │  ← Lógica de negócio
└──────────────┬──────────────────┘
               │
       ┌───────┴────────┐
       │                │
┌──────▼──────┐  ┌─────▼─────────┐
│ Validador   │  │ PessoaRepository│  ← Validação e Persistência
└─────────────┘  └─────┬──────────┘
                       │
              ┌────────▼────────┐
              │   Pessoa (Model)│  ← Entidade de dados
              └─────────────────┘
```

## Camadas do Sistema

### 1. Camada de Apresentação (UI)
**Classe: `Main`**
- Responsável pela interação com o usuário
- Exibe menus e opções
- Captura entrada do usuário
- Formata e exibe resultados

### 2. Camada de Serviço (Service)
**Classe: `PessoaService`**
- Contém a lógica de negócio
- Coordena operações entre validação e persistência
- Trata erros e retorna mensagens apropriadas
- Garante a integridade das operações CRUD

### 3. Camada de Repositório (Repository)
**Classe: `PessoaRepository`**
- Responsável pela persistência de dados
- Gerencia leitura e escrita no arquivo de texto
- Abstrai detalhes de acesso a dados
- Implementa operações de CRUD em nível de persistência

### 4. Camada de Validação (Util)
**Classe: `Validador`**
- Valida dados de entrada
- Verifica formatos de email, telefone e nome
- Retorna mensagens de erro específicas
- Mantém regras de validação centralizadas

### 5. Camada de Modelo (Model)
**Classe: `Pessoa`**
- Representa a entidade de dados
- Contém atributos: nome, email, telefone
- Implementa métodos de conversão para persistência
- Define igualdade baseada em email (chave única)

## Princípios Aplicados

### Single Responsibility Principle (SRP)
Cada classe tem uma única responsabilidade:
- `Main`: Interface com usuário
- `PessoaService`: Lógica de negócio
- `PessoaRepository`: Persistência
- `Validador`: Validação
- `Pessoa`: Representação de dados

### Separation of Concerns
As preocupações estão claramente separadas:
- Interface separada da lógica
- Lógica separada da persistência
- Validação isolada e reutilizável

### Dependency Inversion
A camada de serviço depende de abstrações (repository), facilitando futuras mudanças na implementação de persistência.

## Fluxo de Dados

1. **Entrada do Usuário** → `Main` captura dados
2. **Validação** → `Validador` verifica dados
3. **Processamento** → `PessoaService` executa lógica
4. **Persistência** → `PessoaRepository` salva/recupera dados
5. **Resposta** → Resultado retorna ao usuário via `Main`

## Estrutura de Diretórios

```
src/main/java/com/crud/
├── Main.java                    # Ponto de entrada
├── model/
│   └── Pessoa.java             # Entidade
├── service/
│   └── PessoaService.java      # Lógica de negócio
├── repository/
│   └── PessoaRepository.java   # Persistência
└── util/
    └── Validador.java          # Validações

data/
└── pessoas.txt                 # Arquivo de persistência
```

## Vantagens desta Arquitetura

1. **Manutenibilidade**: Código organizado e fácil de entender
2. **Testabilidade**: Cada camada pode ser testada independentemente
3. **Extensibilidade**: Fácil adicionar novas funcionalidades
4. **Reutilização**: Componentes podem ser reutilizados
5. **Clareza**: Responsabilidades bem definidas
