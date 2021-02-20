import com.sun.org.apache.xpath.internal.operations.Mult;

import java.io.Console;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class MainController extends Thread {

    protected ClienteUDP clientUDP = new ClienteUDP();

    public void MainController() {  }

    public void listarSalas() {
        String retorno = clientUDP.sendMessageUDPtoServerPrincipal("ListarSalas");
        System.out.println(retorno);
    }
    public void criarSala() {
        //Coleta as informações necessárias para a criação da sala
        try{
            System.out.println("Para criar uma nova sala, precisaremos de coletar alguns dados");

            String idParticipante = this.coletarInformacoesParticipante();
            String enderecoMultiCast = this.coletarInformacoesSala();
            this.associaParticipanteSala(idParticipante,enderecoMultiCast);

            System.out.println("Volte ao menu principal e insira a nova sala para conexão");

            Thread.sleep(1500);
        } catch (Exception e){
            System.out.println("Erro ao criar Sala: " + e.getMessage());
        }

    }
    private void associaParticipanteSala(String cpf, String enderecoMultiCast) {
        String associado = clientUDP.sendMessageUDPtoServerPrincipal("AssociarParticipanteSala:"+cpf+":"+enderecoMultiCast);
    }
    private String coletarInformacoesParticipante() {
        Scanner scannerDados = new Scanner(System.in);

        System.out.println("Qual é o seu CPF? Favor inserir sem pontos e traços");
        String cpf = scannerDados.nextLine();

        System.out.println("Qual é o seu nome?");
        String nomeUsuario = scannerDados.nextLine();

        String msg = clientUDP.sendMessageUDPtoServerPrincipal("CriarParticipante:"+cpf+":"+nomeUsuario);
        if(msg.equals("Criado"))
            System.out.println("O participante foi criada com sucesso");
        else
            System.out.println("O participante não foi criada");

        return cpf;
    }
    private String coletarInformacoesSala() throws UnknownHostException {
        Scanner scannerDados = new Scanner(System.in);

        System.out.println("Qual o endereço multicast desejado? Favor inserir no seguinte modelo: 228.5.6.7");
        String enderecoMultiCast = scannerDados.nextLine();

        System.out.println("Qual o nome da sua sala?");
        String nomeSala = scannerDados.nextLine();

        String salaCriada = clientUDP.sendMessageUDPtoServerPrincipal("CriarSala:"+enderecoMultiCast+":"+nomeSala);
        if(salaCriada.equals("Criado"))
            System.out.println("A sala foi criada com sucesso");
        else
            System.out.println("A sala não foi criada");

        return enderecoMultiCast;
    }
    public void entrarSala(String enderecoMultiCast) {

        Scanner s = new Scanner(System.in);
        System.out.println("Para conseguirmos te redirecionar, confirme por gentileza seu CPF");
        String cpfCheck = s.nextLine();

        String dados = clientUDP.sendMessageUDPtoServerPrincipal("EntrarChat:"+enderecoMultiCast+":"+cpfCheck);

        if(dados.equals("Sala Inexistente")) {
            System.out.println("Não foi possível localizar a sala solicitada. Favor validar se a mesma existe");
        }
        else if(dados.equals("Participante Inexistente")) {
            System.out.println("Participante não localizado, vamos criar um novo cadastro?");
            String cpfPrimario = this.coletarInformacoesParticipante();
            this.associaParticipanteSala(cpfPrimario, enderecoMultiCast);
            this.entrarSala(enderecoMultiCast);
        }
        else {
            String[] informacoesComplementares = dados.split(":");
            Multicast m = new Multicast(enderecoMultiCast, 6788, true);
            this.capturaMensagens(informacoesComplementares[0], informacoesComplementares[1], enderecoMultiCast);
        }


    }
    public void listarParticipantes() {
        String ret = clientUDP.sendMessageUDPtoServerPrincipal("ListarParticipantes");
        System.out.println(ret);
    }
    private void capturaMensagens(String cpf, String nome, String multiCast) {
        System.out.println("Para conversar na sala, favor digitar logo abaixo");
        System.out.println("Caso queira sair do chat, digite /quit");
        Scanner s = new Scanner(System.in);

        boolean sair = false;
        StringBuilder msg = new StringBuilder(s.nextLine());
        do {
            if(msg.toString().equals("/quit"))
                sair = true;
            else
            {
                try {
                    msg.insert(0, nome + ": ");
                    ServidorMultiCasting.initialize(msg.toString(),multiCast,6788);
                    msg = new StringBuilder(s.nextLine());
                } catch (IOException e) {
                    System.out.println("IOException: " + e.getMessage());
                }
            }
        } while(!sair);


    }
}
