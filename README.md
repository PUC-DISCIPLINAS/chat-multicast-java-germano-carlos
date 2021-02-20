# Projeto Java - Multicast
## Descrição do Projeto
<p align="center">Foi desenvolvido uma aplicação de chat utilizando o protocolo Multicasting através da linguagem JAVA.</p>

🏁 Tópicos
=================
<!--ts-->
   * [Sobre](#Sobre)
      * [Pre Requisitos](#pre-requisitos)
      * [Local files](#local-files)
   * [Tests](#testes)
   * [Tecnologias](#tecnologias)
<!--te-->

<h4 align="center"> 
	🚀 Projeto já finalizado !
</h4>

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

### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:
- [Java](https://expo.io/)

### Autor

<a href="https://www.linkedin.com/in/carlos-germano/">
 <img style="border-radius: 50%;" src="https://pbs.twimg.com/profile_images/1350631414790828036/CCJUE61E_400x400.jpg" width="100px;" alt=""/>
 <br />
 <sub><b>Carlos Germano</b></sub></a>

Feito por Carlos Germano 👋🏽 Entre em contato!

[![Twitter Badge](https://img.shields.io/badge/-@germano__carlos-1ca0f1?style=flat-square&labelColor=1ca0f1&logo=twitter&logoColor=white&link=https://twitter.com/germano__carlos)](https://twitter.com/germano__carlos) 
[![Linkedin Badge](https://img.shields.io/badge/-Carlos-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/carlos-germano/)](https://www.linkedin.com/in/carlos-germano/)
[![Gmail Badge](https://img.shields.io/badge/-germano.carlos2712@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:germano.carlos2712@gmail.com)](mailto:tgmarinho@gmail.com)
