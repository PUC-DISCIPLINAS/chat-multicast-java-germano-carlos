import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Inicializa o Servidor Padrão
        ServidorUDP serverPrincipal = new ServidorUDP();
        Thread thread = new Thread(serverPrincipal);
        thread.start();

        boolean loop = true;
        do {
            // Disponibiliza o Menu de opções.
            System.out.println("Bem vindo ao bate-papo da UOL");
            System.out.println("Primeiramente precisamos de entender o que você deseja, tudo bem?");
            System.out.println("1 - Listar salas de bate papo disponíveis");
            System.out.println("2 - Criar uma nova sala de bate papo");
            System.out.println("3 - Entrar em uma sala de bate papo");
            System.out.println("4 - Listar participantes de uma sala de bate papo");
            System.out.println("0 - Sair e desconectar");

            Scanner s = new Scanner(System.in);
            int opcao = s.nextInt();

            switch (opcao) {
                case 1:
                    serverPrincipal.listarSalas();
                    break;
                case 2:
                    serverPrincipal.criarSala();
                    break;
                case 3:
                    serverPrincipal.entrarSala();
                    break;
                case 4:
                    serverPrincipal.listarParticipantes();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Opção não encontrada, por favor refazer o processo de seleção");
                    break;
            }
        } while (loop);

    }
}
