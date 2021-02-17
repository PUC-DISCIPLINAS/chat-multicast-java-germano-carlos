import java.net.InetAddress;

public class Participante {

    private String id;
    private String nome;
    private Sala sala;

    public Participante() {

    }

    public Participante(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() { return id; }
    public void setId(String id) {this.id = id;}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public void setSala(Sala sala) { this.sala = sala;  }
    public Sala getSala() { return sala; }
}
