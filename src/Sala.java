import java.net.InetAddress;

public class Sala {
    private InetAddress id;
    private String nome;
    private Multicast multicast;

    public Sala(InetAddress id,String nome, Multicast multicast){
        this.id = id;
        this.nome = nome;
        this.multicast = multicast;
    }

    public InetAddress getId() { return id; }
    public void setId(InetAddress id) {this.id = id;}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMulticast(Multicast multicast) { this.multicast = multicast; }
    public Multicast getMulticast() { return multicast; }
}
