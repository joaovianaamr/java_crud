# Sistema CRUD em Java

Sistema CRUD (Create, Read, Update, Delete) desenvolvido em Java puro para aprendizado da linguagem e conceitos de orientação a objetos.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido seguindo boas práticas de programação orientada a objetos, sem uso de frameworks externos. O objetivo é demonstrar conceitos fundamentais de Java através de uma aplicação prática.

## 🚀 Funcionalidades

- ✅ Cadastrar novas pessoas (nome, email, telefone)
- ✅ Listar todas as pessoas cadastradas
- ✅ Buscar pessoa por email
- ✅ Atualizar dados de uma pessoa
- ✅ Remover pessoa do sistema

## 🛠️ Tecnologias

- **Java** (puro, sem frameworks)
- **Persistência**: Arquivo de texto (.txt)
- **Interface**: Menu interativo no terminal

## 📁 Estrutura do Projeto

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
│   └── pessoas.txt                 # Arquivo de dados (criado automaticamente)
└── docs/
    └── [Documentação completa]
```

## 💻 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado

### Compilação

```bash
# Windows
javac -d bin -encoding UTF-8 src\main\java\com\crud\**\*.java

# Linux/Mac
javac -d bin -encoding UTF-8 src/main/java/com/crud/**/*.java
```

### Execução

```bash
# Windows
java -cp bin com.crud.Main

# Linux/Mac
java -cp bin com.crud.Main
```

## 📖 Uso do Sistema

Ao executar o programa, você verá um menu com as seguintes opções:

1. **Cadastrar nova pessoa**: Adiciona uma nova pessoa ao sistema
2. **Listar todas as pessoas**: Exibe todas as pessoas cadastradas
3. **Buscar pessoa por email**: Busca uma pessoa específica pelo email
4. **Atualizar pessoa**: Atualiza nome ou telefone de uma pessoa
5. **Remover pessoa**: Remove uma pessoa do sistema
0. **Sair**: Encerra o programa

## ✨ Características

- ✅ Validação completa de dados (email, telefone, nome)
- ✅ Tratamento de erros adequado
- ✅ Código organizado e modular
- ✅ Separação de responsabilidades (SOLID)
- ✅ Interface amigável no terminal
- ✅ Persistência em arquivo de texto

## 📚 Documentação

A documentação completa está disponível na pasta `docs/`:
- [Arquitetura do Sistema](docs/01-arquitetura.md)
- [Decisões de Design](docs/02-decisoes-design.md)
- [Fluxos de Execução](docs/03-fluxos-execucao.md)

## 🎯 Conceitos Demonstrados

- Orientação a Objetos
- Princípios SOLID
- Manipulação de arquivos
- Validação de dados
- Tratamento de exceções
- Estruturação de projetos Java

## 📝 Observações

- O email é usado como chave única para identificar pessoas
- O arquivo de dados é criado automaticamente no diretório `data/`
- Dados são normalizados antes de salvar (espaços removidos, email em minúsculas)
- Operações de remoção requerem confirmação

## 🔄 Melhorias Futuras

- Sistema de backup automático
- Busca avançada (por nome ou telefone)
- Paginação para listas grandes
- Sistema de logs
- Testes unitários

---

**Desenvolvido para aprendizado de Java e conceitos de OOP**
