package entidades;


public class Cliente extends Pessoa {

    private float pesoBagagem;
    private byte numBebes;

    public Cliente(int codigo, String nome, String apelido, String nacionalidade, String email, String dataDeNascimento,
            String telefonePrimario, String telefoneSecundario, String endereco, byte numBebes) {
        
        super(codigo, nome, apelido, nacionalidade, email, dataDeNascimento, telefonePrimario, telefoneSecundario, endereco);
        this.numBebes = numBebes;
    }

    public float getPesoBagagem() {
        return pesoBagagem;
    }

    public byte getNumBebes() {
        return numBebes;
    }

    public void setPesoBagagem(float pesoBagagem) {
        this.pesoBagagem = pesoBagagem;
    }
    
    @Override
    public String toString() {
        return super.toString() + ";" + pesoBagagem + ";" + numBebes;
    }    
}
