# MineUtils

Plugin utilitário para Minecraft desenvolvido em Java utilizando a API Bukkit/Spigot/Paper.

## Funcionalidades

- Sistema de reports de jogadores
- Comandos utilitários
- Armazenamento em banco de dados
- Sistema de permissões configurável

## Requisitos

- Java 25
- Servidor Paper / Spigot / Bukkit compatível
- Gradle

## Instalação

1. Baixe o `.jar` na aba **Releases** ou **Actions** do GitHub.
2. Coloque o arquivo na pasta:

```txt
plugins/
```

3. Inicie ou reinicie o servidor.

## Compilando o projeto

Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

Build local:

Windows:

```bash
gradlew build
```

Linux/macOS:

```bash
./gradlew build
```

O arquivo compilado será gerado em:

```txt
build/libs/
```

## Comandos

| Comando | Descrição |
|----------|------------|
| `/report` | Reportar um jogador |
| `/reports` | Listar reports |

## Permissões

| Permissão | Descrição |
|------------|------------|
| `ws.reports` | Permite visualizar comandos de report |

## Tecnologias

- Java
- Gradle
- Bukkit / Spigot / Paper API
- GitHub Actions

## Autor

Desenvolvido por **Weslei23**.
