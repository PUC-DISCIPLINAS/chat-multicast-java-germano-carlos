import java.io.IOException;
import java.net.*;
import java.util.*;

public class ServidorUDP extends Thread {
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
            Participante p = this.coletarInformacoesParticipante();
            Sala s = this.coletarInformacoesSala();
            this.associaParticipanteSala(p,s);

            System.out.println("Sala '" + s.getId() + "|" +s.getNome() + "' - Criada com Sucesso");
        } catch (Exception e){
            System.out.println("UnknownHostException: " + e.getMessage());
        }

    }
    private void associaParticipanteSala(Participante p, Sala s) {
        p.setSala(s);
        this.participantesList.add(p);
        this.salasList.add(s);
    }
    private Participante coletarInformacoesParticipante() {
        Scanner scannerDados = new Scanner(System.in);

        System.out.println("Para criar uma nova sala, precisaremos de coletar alguns dados");
        System.out.println("Qual é o seu nome?");
        String nomeUsuario = scannerDados.nextLine();

        System.out.println("Qual é o seu CPF?");
        String cpf = scannerDados.nextLine();

        return new Participante(cpf,nomeUsuario);
    }
    private Sala coletarInformacoesSala() throws UnknownHostException {
        Scanner scannerDados = new Scanner(System.in);

        boolean existeMultiCast = true;
        String enderecoMultiCast = "";
        while(existeMultiCast)
        {
            System.out.println("Qual o endereço multicast desejado? Favor inserir no seguinte modelo: 228.5.6.7");
            enderecoMultiCast = scannerDados.nextLine();
            if(validaEnderecoMultiCast(enderecoMultiCast))
                System.out.println("Endereço multicast já existente, caso deseje entrar nessa sala selecione a opção do menu anterior!");
            else
                existeMultiCast = false;
        }

        System.out.println("Qual o nome da sua sala?");
        String nomeSala = scannerDados.nextLine();

        return new Sala(InetAddress.getByName(enderecoMultiCast), nomeSala);
    }
    private boolean validaEnderecoMultiCast(String enderecoMultiCast) {
        if(salasList.size() == 0)
            return false;
        else
        {
            for(Sala s : salasList )
                if(s.getNome().equals(enderecoMultiCast))
                    return true;
            return false;
        }
    }
    public void entrarSala() {

    }
    public void listarParticipantes() {}
}
