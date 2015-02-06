package projecto;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe que representa um Leitor da biblioteca, derivada da classe Utilizador.
 * É uma classe abstracta, uma vez que não existem Leitores mas sim Alunos e Professores,
 * classes que por sua vez derivam desta
 */
public abstract class Leitor extends Utilizador
{
    protected static int numLeitorActual = 1;
    protected int numLeitor;

    public Leitor( String nome, String morada, String telefone, String mail, String password ,String data, Biblioteca biblio )
    {
        super ( nome, morada , telefone, mail, password , data, biblio);
        this.numLeitor = numLeitorActual++;
    }

    /**
     *
     * @param u recebe um utilizador u
     * @return true se este leitor corresponder ao utilizador passado como parâmetro,
     * caso contrário retorna false
     */
    public boolean equals ( Utilizador u )
    {
        return u instanceof Leitor && (this.numLeitor == ((Leitor) u).numLeitor);
    }

    /**
     * Função responsável por indicar se é permitido a este Leitor requisitar mais produtos,
     * de acordo com o número de requisicoes que já tem
     * @return true se puder requisitar, false caso não possa
     */
    public boolean podeRequisitar()
    {
        return ( this.requisicoesActuais.size() < maximoProdutos() );
    }

    /**
     * Função responsável por identificar todas as requisições de DVD em atraso
     * @return um array list com as requisições de DVD que estão atrasadas
     */
    public ArrayList<Requisicao> requisicoesDVDAtraso()
    {
        Calendar dataActual = Calendar.getInstance();
        ArrayList<Requisicao> atrasados = new ArrayList<Requisicao>();

        for ( Requisicao r: this.requisicoesActuais )
            if ( r.getProduto() instanceof DVD )    // se for um livro
            {
                if ( r.dataEntrega().before(dataActual)) // se ja tiver passado o prazo para entregar
                    atrasados.add(r);
            }

        return atrasados;
    }

    /**
     * Função responsável por identificar todas as requisições de DVD em atraso
     * @return um array list com as requisições de DVD que estão atrasadas
     */
    public ArrayList<Requisicao> livrosAtraso()
    {
        Calendar dataActual = Calendar.getInstance();
        ArrayList<Requisicao> atrasados = new ArrayList<Requisicao>();

        for ( Requisicao r: this.requisicoesActuais )
            if ( r.getProduto() instanceof Livro )    // se for um livro
                if ( r.dataEntrega().before(dataActual)) // se ja tiver passado o prazo para entregar
                    atrasados.add(r);

        return atrasados;
    }

    public int getNumLeitor() {
        return numLeitor;
    }

    public abstract int maximoTempo();
    public abstract int maximoProdutos();

}
