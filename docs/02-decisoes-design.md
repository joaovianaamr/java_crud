# Decisões de Design

Este documento explica as principais decisões de design tomadas durante o desenvolvimento do sistema CRUD.

## 1. Escolha do Email como Chave Única

**Decisão**: Utilizar o email como identificador único para cada pessoa.

**Justificativa**:
- Email é naturalmente único por pessoa
- Facilita buscas e operações de atualização/remoção
- Não requer geração de IDs artificiais
- Simplifica a implementação sem banco de dados

**Implementação**:
- Métodos `equals()` e `hashCode()` baseados em email
- Validação de duplicidade no cadastro
- Busca, atualização e remoção por email

## 2. Formato de Persistência: Arquivo de Texto

**Decisão**: Usar arquivo de texto simples (.txt) com formato delimitado.

**Justificativa**:
- Atende ao requisito do projeto (sem frameworks)
- Simples de implementar e debugar
- Fácil de visualizar e editar manualmente
- Adequado para aprendizado

**Formato Escolhido**: `nome|email|telefone`
- Delimitador `|` é pouco comum em dados pessoais
- Fácil de parsear com `split()`
- Uma linha por registro

**Alternativas Consideradas**:
- JSON: Requer biblioteca externa (não permitido)
- CSV: Delimitador `,` pode conflitar com dados
- XML: Mais complexo para este caso de uso

## 3. Estrutura de Camadas

**Decisão**: Separar em camadas bem definidas (Model, Repository, Service, UI).

**Justificativa**:
- Facilita manutenção e evolução
- Permite testar cada camada isoladamente
- Segue princípios SOLID
- Preparado para futuras mudanças (ex: trocar arquivo por banco)

**Benefícios**:
- Se precisar mudar de arquivo para banco, só altera `PessoaRepository`
- Lógica de negócio isolada em `PessoaService`
- Validações centralizadas em `Validador`

## 4. Tratamento de Erros

**Decisão**: Retornar mensagens de erro como String ao invés de lançar exceções.

**Justificativa**:
- Interface mais amigável para o usuário
- Evita interrupção abrupta do programa
- Mensagens claras e específicas
- Facilita tratamento na camada de apresentação

**Exceções**:
- `IOException` ainda é lançada internamente no Repository
- Capturada e convertida em mensagem amigável no Service

## 5. Validação de Dados

**Decisão**: Criar classe utilitária `Validador` com métodos estáticos.

**Justificativa**:
- Validações centralizadas e reutilizáveis
- Fácil adicionar novas regras
- Código limpo e organizado
- Métodos estáticos não requerem instância

**Padrões de Validação**:
- **Email**: Regex para formato padrão de email
- **Telefone**: Aceita vários formatos (com/sem parênteses, traços, espaços)
- **Nome**: Mínimo de 2 caracteres, não vazio

## 6. Menu Interativo

**Decisão**: Menu em loop com opções numéricas.

**Justificativa**:
- Interface simples e intuitiva
- Não requer bibliotecas externas
- Fácil de usar e entender
- Adequado para aprendizado

**Características**:
- Menu sempre visível
- Pausa após cada operação
- Confirmação para operações destrutivas (remoção)
- Opção de sair (0)

## 7. Normalização de Dados

**Decisão**: Normalizar dados ao salvar (trim, lowercase para email).

**Justificativa**:
- Consistência nos dados armazenados
- Evita duplicatas por diferenças de formatação
- Melhora experiência do usuário
- Facilita buscas

**Normalizações Aplicadas**:
- `trim()`: Remove espaços no início/fim
- `toLowerCase()`: Email sempre em minúsculas
- Espaços múltiplos não são tratados (poderia ser melhorado)

## 8. Criação Automática de Diretório

**Decisão**: Criar diretório `data/` automaticamente se não existir.

**Justificativa**:
- Melhor experiência do usuário
- Não requer configuração manual
- Funciona em qualquer ambiente
- Evita erros de arquivo não encontrado

## 9. Formatação de Saída

**Decisão**: Usar `String.format()` para alinhar colunas na listagem.

**Justificativa**:
- Saída mais legível e profissional
- Colunas alinhadas facilitam leitura
- Não requer bibliotecas externas
- Formatação consistente

## 10. Métodos de Conversão na Classe Model

**Decisão**: Incluir `toFileFormat()` e `fromFileFormat()` na classe `Pessoa`.

**Justificativa**:
- Encapsula lógica de serialização
- Facilita mudanças futuras no formato
- Mantém responsabilidade no modelo
- Código mais limpo no Repository

## Considerações para Melhorias Futuras

1. **Tratamento de Concorrência**: Arquivo pode ser corrompido com múltiplas instâncias
2. **Backup Automático**: Criar backup antes de operações de escrita
3. **Busca Avançada**: Buscar por nome ou telefone além de email
4. **Paginação**: Para listas muito grandes
5. **Logging**: Sistema de logs para debug
6. **Configuração**: Arquivo de configuração para caminho do arquivo
