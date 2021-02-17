import com.sun.org.apache.xpath.internal.operations.Mult;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class MainController extends Thread {

    protected DatagramSocket aSocket;
    protected DatagramPacket request;
    protected List<Sala> salasList = new ArrayList<Sala>();
    protected List<Participante> participantesList = new ArrayList<Participante>();

    public void ServidorUDP() {  }
    public void run(){ this.initialize(); }
    public void initialize() {
        try {
            this.aSocket = new DatagramSocket(6789);
            System.out.println("Servidor: ouvindo porta UDP/6789.");
            byte[] buffer = new byte[1000];

            // Mantem Servidor ativo, aguardando requisição
            //noinspection InfiniteLoopStatement
            while (true) {
                this.request = new DatagramPacket(buffer, buffer.length);
                this.aSocket.receive(this.request);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
    public void listarSalas() {
        if(salasList.size() == 0)
            System.out.println("Infelizmente nenhuma sala foi criada ainda. Volte ao menu e crie uma :)");
        else
            for (int i = 0; i < salasList.size(); i++ ) {
                System.out.println(i+1 + "| Id da sala:" + salasList.get(i).getId() + "| Nome da sala: " + salasList.get(i).getNome());
            }
    }
    public void criarSala() {
        //Coleta as informações necessárias para a criação da sala
        try{
            System.out.println("Para criar uma nova sala, precisaremos de coletar alguns dados");

            Participante p = this.coletarInformacoesParticipante();
            Sala s = this.coletarInformacoesSala();
            this.associaParticipanteSala(p,s);

            System.out.println("Sala '" + s.getId() + "|" +s.getNome() + "' - Criada com Sucesso");
            System.out.println("Volte ao menu principal e insira a nova sala para conexão");

            Thread.sleep(3000);
        } catch (Exception e){
            System.out.println("Erro ao criar Sala: " + e.getMessage());
        }

    }
    private void associaParticipanteSala(Participante p, Sala s) {
        p.setSala(s);
        if(!participantesList.contains(p))
            this.participantesList.add(p);
        if(!salasList.contains(s))
            this.salasList.add(s);
    }
    private Participante coletarInformacoesParticipante() {
        Scanner scannerDados = new Scanner(System.in);

        System.out.println("Qual é o seu CPF? Favor inserir sem pontos e traços");
        String cpf = scannerDados.nextLine();

        if(participantesList.size() > 0)  {
            for(Participante p : participantesList)
                if(p.getId().equals(cpf))
                    return p;
        }

        System.out.println("Qual é o seu nome?");
        String nomeUsuario = scannerDados.nextLine();

        return new Participante(cpf,nomeUsuario);
    }
    private Sala coletarInformacoesSala() throws UnknownHostException {
        Scanner scannerDados = new Scanner(System.in);

        boolean existeMultiCast = true; String enderecoMultiCast = "";
        while(existeMultiCast)
        {
            System.out.println("Qual o endereço multicast desejado? Favor inserir no seguinte modelo: 228.5.6.7");
            enderecoMultiCast = scannerDados.nextLine();
            if(this.validaEnderecoMultiCast(enderecoMultiCast))
                System.out.println("Endereço multicast já existente, caso deseje entrar nessa sala selecione a opção do menu anterior!");
            else
                existeMultiCast = false;
        }

        System.out.println("Qual o nome da sua sala?");
        String nomeSala = scannerDados.nextLine();

        return new Sala(InetAddress.getByName(enderecoMultiCast), nomeSala, new Multicast(InetAddress.getByName(enderecoMultiCast).getHostAddress(), 6789, false));
    }
    private boolean validaEnderecoMultiCast(String enderecoMultiCast) {
        if(salasList.size() == 0)
            return false;
        else
        {
            for(Sala s : salasList )
                if(s.getId().getHostAddress().equals(enderecoMultiCast))
                    return true;
            return false;
        }
    }
    public void entrarSala(String enderecoMultiCast) {
        Participante p = coletarInformacoesParticipante();
        Sala s = this.procurarSalaPorId(enderecoMultiCast);

        if(s != null) {
            this.associaParticipanteSala(p,s);
            Multicast m = new Multicast(enderecoMultiCast, 6789, true);
        }
        else
            System.out.println("Não foi possível localizar a sala solicitada. Favor validar se a mesma existe");
    }
    private Sala procurarSalaPorId(String enderecoMultiCast) {
        boolean existe = this.validaEnderecoMultiCast(enderecoMultiCast);

        if(!existe)
            return null;
        else
        {
            for(Sala s : salasList )
                if(s.getId().getHostAddress().equals(enderecoMultiCast))
                    return s;

        }

        return null;
    }
    public void listarParticipantes() {}


}
