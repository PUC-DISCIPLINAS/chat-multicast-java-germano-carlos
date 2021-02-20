import javax.management.MBeanRegistration;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorPrincipal extends Thread {

    public static void main(String[] args) {
        List<Sala> salasList = new ArrayList<Sala>();
        List<Participante> participantesList = new ArrayList<Participante>();

        try {
            DatagramSocket aSocket = new DatagramSocket(6789);
            System.out.println("Servidor: ouvindo porta UDP/6789.");


            // Mantem Servidor ativo, aguardando requisição
            //noinspection InfiniteLoopStatement
            while (true) {
                byte[] buffer = new byte[1000];
                // Recupera a instrução enviada
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String message = new String(request.getData()).trim();

                System.out.println("Recebido:" + message);

                String mensagem = processaMensagem(message, salasList, participantesList);

                // Devolve a mensagem desejada
                byte[] mensagemByte = mensagem.getBytes();
                DatagramPacket reply = new DatagramPacket(mensagemByte, mensagemByte.length, request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

    }

    private static String processaMensagem(String mensagem, List<Sala> s,List<Participante> p) {
        String[] mensagemParametros = mensagem.split(":");
        String acaoPrincipal = mensagemParametros[0];
        String retorno = "";

        try {
            switch (acaoPrincipal) {
                case "CriarParticipante":
                    if (!ValidaParticipanteExistente(p, mensagemParametros[1]))
                    {
                        p.add(new Participante(mensagemParametros[1], mensagemParametros[2]));
                        retorno = "Criado";
                    }
                    else
                        retorno = "Não criado";
                    break;
                case "CriarSala":

                    if (!ValidaSalaExistente(s, mensagemParametros[1]))
                    {
                        s.add(new Sala(InetAddress.getByName(mensagemParametros[1]),mensagemParametros[2],
                            new Multicast(InetAddress.getByName(mensagemParametros[1]).getHostAddress(),6788,false)));
                        retorno = "Criado";
                    }
                    else
                        retorno = "Não Criado";
                    break;
                case "AssociarParticipanteSala":
                    boolean associado = AssociaParticipanteSala(p,s,mensagemParametros[1], mensagemParametros[2]);
                    if(associado)
                        retorno = "Criado";
                    else
                        retorno = "Não Criado";
                    break;
                case "ListarSalas":
                    retorno = ListarSalas(s);
                    break;
                case "EntrarChat":
                    retorno = EntrarChat(p,s,mensagemParametros[1],mensagemParametros[2]);
                    break;
                default:
                    System.out.println("Não localizado nenhuma opção para isso");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return retorno;
    }

    private static String ListarSalas(List<Sala> salasList) {
        StringBuilder t = new StringBuilder();
        if (salasList.size() == 0)
            return ("Infelizmente nenhuma sala foi criada ainda. Volte ao menu e crie uma :)");
        else
        {
            for (int i = 0; i < salasList.size(); i++) {
                t.append(i).append(1).append("| Id da sala:").append(salasList.get(i).getId()).append("| Nome da sala: ").append(salasList.get(i).getNome());
            }
        }

        return t.toString();
    }
    private static boolean ValidaParticipanteExistente(List<Participante> participantesList, String cpf) {
        if (participantesList.size() > 0)
            for (Participante p : participantesList)
                if (p.getId().equals(cpf))
                    return true;
            return false;
    }
    private static boolean ValidaSalaExistente (List<Sala> salasList, String ipMultiCast) {
        if (salasList.size() > 0)
            for (Sala s : salasList)
                if (s.getId().getHostAddress().equals(ipMultiCast))
                    return true;
        return false;
    }
    private static Participante RetornaParticipanteExistente(List<Participante> participantesList, String cpf) {
        if (participantesList.size() > 0)
            for (Participante p : participantesList)
                if (p.getId().equals(cpf))
                    return p;
        return null;
    }
    private static Sala RetornaSalaExistente (List<Sala> salasList, String ipMultiCast) {
        if (salasList.size() > 0)
            for (Sala s : salasList)
                if (s.getId().getHostAddress().equals(ipMultiCast))
                    return s;
        return null;
    }
    private static boolean AssociaParticipanteSala(List<Participante> participantesList, List<Sala> salasList, String cpf, String ipMultiCast) {
        Participante p = RetornaParticipanteExistente(participantesList, cpf);
        Sala s = RetornaSalaExistente(salasList,ipMultiCast);

        if (p != null && s != null) {
            p.setSala(s);
            return true;
        } else
            return false;
    }
    private static String EntrarChat(List<Participante> participantesList, List<Sala> salasList, String ipMultiCast, String cpfParticipante) {
        Sala s = RetornaSalaExistente(salasList,ipMultiCast);
        Participante p = RetornaParticipanteExistente(participantesList, cpfParticipante);

        if(s == null)
            return "Sala Inexistente";
        if(p == null)
            return "Participante Inexistente";

        return p.getId() + ":" + p.getNome();
    }
}
