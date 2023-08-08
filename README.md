# Documentação do Projeto React-Native

## d2mini - Exemplo React-Native utilizando a biblioteca da IT FAST

O projeto **d2mini** é um exemplo de aplicação React-Native que utiliza a biblioteca da IT FAST para interagir com equipamentos de automação, como o TecToy Automação: V2, V2Pro, D2 Mini, D2s, T2 Mini, T2s, K2 e K2 Mini. Ele demonstra como se comunicar com esses dispositivos e realizar a automação de tarefas. Abaixo estão as instruções para configurar e executar o projeto.

### Configuração

1. **Configuração do SDK Android:**

   - Dentro da pasta `android`, crie um arquivo chamado `local.properties`.
   - Abra o arquivo `local.properties` e adicione a seguinte linha, substituindo `caminhoCompletoSDK` pelo caminho completo para o SDK Android na sua máquina:
     ```
     sdk.dir = /caminhoCompletoSDK/Android/SDK
     ```
     **Para Windows:**
     ```
     sdk.dir = C:\\caminhoCompletoSDK\\Android\\SDK
     ```
     **Para macOS:**
     ```
     sdk.dir = /Users/caminhoCompletoSDK/Library/Android/sdk
     ```

2. **Configuração do Equipamento:**

   - Navegue até `android -> app -> src -> main -> java -> com -> d2mini`.
   - Abra o arquivo `It4rModule.java`.
   - Na linha 15, localize o seguinte trecho de código:
     ```java
     tecToy = new TecToy(Dispositivo.D2_MINI, context);
     ```
   - Substitua `Dispositivo.D2_MINI` pelo equipamento específico que você está utilizando. Por exemplo, para um TecToy Automação V2, substitua por `Dispositivo.V2`.

### Compilação e Execução

Para compilar e executar o projeto, siga os passos abaixo:

1. Certifique-se de ter o [Node.js](https://nodejs.org/) instalado na sua máquina.

2. Navegue até a raiz do projeto `d2mini` pelo terminal.

3. Execute o seguinte comando para instalar as dependências:
   ```
   npm install
   ```

4. Após a instalação das dependências, execute o seguinte comando para iniciar o aplicativo no dispositivo Android:
   ```
   npm run android
   ```

Isso compilará e executará o aplicativo no emulador ou dispositivo Android conectado.

---

## itPharma-main - Exemplo de Terminal de Consulta para Farmácias

O projeto **itPharma-main** é um exemplo temático de um aplicativo React-Native que simula um terminal de consulta para farmácias. Ele utiliza um leitor de código de barras para ler produtos e consultar informações como disponibilidade, preço, bula e classificação. Este projeto demonstra como criar uma interface de usuário intuitiva para interagir com sistemas de consulta de produtos.

Este projeto não requer configurações adicionais relacionadas a equipamentos específicos e pode ser executado seguindo os mesmos passos mencionados na seção anterior para compilação e execução de projetos React-Native.

Lembre-se de que este exemplo é temático e serve como referência para a implementação de um terminal de consulta em uma farmácia, utilizando tecnologias de leitura de código de barras e exibição de informações relevantes aos usuários.

Para mais detalhes sobre a implementação específica do projeto `itPharma-main`, consulte o código-fonte e a documentação interna do projeto.

---

Essa documentação fornece instruções claras para configurar e executar os projetos `d2mini` e `itPharma-main`. Certifique-se de seguir as etapas com cuidado para garantir uma experiência suave ao trabalhar com esses exemplos de aplicativos React-Native.
