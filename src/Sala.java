import java.net.InetAddress;

public class Sala {
    private InetAddress id;
    private String nome;

    public Sala(InetAddress id,String nome){
        this.id = id;
        this.nome = nome;
    }

    public InetAddress getId() { return id; }
    public void setId(InetAddress id) {this.id = id;}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }


}
