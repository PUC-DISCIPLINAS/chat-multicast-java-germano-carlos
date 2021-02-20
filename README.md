# Projeto Java - Multicast

🏁 Tópicos
=================

   * [Sobre](#sobre)
      * [Descrição do Projeto](#Descrição)
      * [Pré-requisitos](#Pré-requisitos)
      * [Classes](#Classes)
   * [Tecnologias](#Tecnologias)
   * [Autor](#Autor)
   * [License](#License)

<h4 align="center"> 
	🚀 Projeto já finalizado !
</h4>

## Sobre o projeto
### Descrição do Projeto
Foi desenvolvido uma aplicação de chat utilizando o protocolo Multicasting através da linguagem JAVA.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java JDK 1.8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html).
Além disto é bom ter um editor para trabalhar com o código como [IntelliJ](https://www.jetbrains.com/idea/promo/?gclid=CjwKCAiAg8OBBhA8EiwAlKw3ktUhkS8ZI1F5ElPAJEMQCRwynFNh9jq8Dp4qb4IfxpzqJ4ZJJbNIyBoChAoQAvD_BwE)

### 🎲 Rodando o Back End (Servidor)

```bash
# Clone este repositório
$ git clone <https://github.com/PUC-DISCIPLINAS/chat-multicast-java-germano-carlos.git>

# Acesse a pasta de arquivos binários do projeto no terminal/cmd
$ cd out/production/chat-multicast-java-germano-carlos

# Complie o projeto através do comando java
$ java ServidorPrincipal

# O servidor inciará na porta:6789
# Visualize se a porta foi devidamente liberada e o servidor está sendo executado
$ netstat -ano -p tcp |find "6789"
```

### 🎲 Rodando o Back End (Cliente)

```bash
# Clone este repositório
$ git clone <https://github.com/PUC-DISCIPLINAS/chat-multicast-java-germano-carlos.git>

# Acesse a pasta de arquivos binários do projeto no terminal/cmd
$ cd out/production/chat-multicast-java-germano-carlos

# Complie o projeto através do comando java
$ java Main

# Você será redirecionado para um menu com as opções de escolhas de usabilidade.
# Assim que você escolher um endereço multicasting para entrada, a porta padrão será a 6788

# Visualize se a porta foi devidamente liberada e o servidor está sendo executado
$ netstat -ano -p tcp |find "6788"
```
## Classes
#### 📚 Servidor Principal
Classe que será responsável por "levantar" um servidor UDP que receberá todo os dados de usuários/salas/conexões e consequentemente a alocação "in memory" enquanto escutando a porta 6789.
#### 📕 Main
Classe que será responsável por inicializar a aplicação do Cliente. A mesma conseguirá solicitar ao Cliente as ações através de um menu.
#### 📘 MainController
Classe que será responsável por intermediar a comunicação entre a comunicação Cliente / Servidor.
#### 📗 ClienteUDP
Classe que será responsável por enviar mensagens do Cliente para o Servidor, e capturar a mensagem devolvida por ele, e exibir em tela as tratativas para o Cliente.
#### 📙 Multicast
Classe que conterá atributos referentes ao protocolo Multicast. Precisaremos de alocar essa informação para conseguirmos referenciar este objeto a Classe Sala. Além de ser responsável por startar uma thread para que em loop infinito consiga capturar todas as mensagens enviadas dentro do grupo Multicast
#### 📒 ServidorMultiCasting
Classe que será responsável por ser o "listerner" das mensagens trafegadas dentro do grupo multicast. Dentro dessa classe, a mesma será responsável por enviar toda notificação para o grupo multicast.
#### 📓 Participante
Classe que conterá atributos referentes aos participantes / usuários utilizadores da nossa aplicação de chat
#### 📔 Sala
Classe que conterá atributos referentes as salas de chat criadas. Precisaremos também de fazer a comunicação desta classe ao Participante, uma vez que o participante ao adentrar uma sala ele se torna um usuário Ativo.

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:
- [Java](https://expo.io/)

## Autor

<a href="https://www.linkedin.com/in/carlos-germano/">
 <img style="border-radius: 50%;" src="https://pbs.twimg.com/profile_images/1350631414790828036/CCJUE61E_400x400.jpg" width="100px;" alt=""/>
 <br />
 <sub><b>Carlos Germano</b></sub></a>

Feito por Carlos Germano 👋🏽 Entre em contato!

[![Twitter Badge](https://img.shields.io/badge/-@germano__carlos-1ca0f1?style=flat-square&labelColor=1ca0f1&logo=twitter&logoColor=white&link=https://twitter.com/germano__carlos)](https://twitter.com/germano__carlos) 
[![Linkedin Badge](https://img.shields.io/badge/-Carlos-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/carlos-germano/)](https://www.linkedin.com/in/carlos-germano/)
[![Gmail Badge](https://img.shields.io/badge/-germano.carlos2712@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:germano.carlos2712@gmail.com)](mailto:tgmarinho@gmail.com)

## License
[MIT](https://choosealicense.com/licenses/mit/)