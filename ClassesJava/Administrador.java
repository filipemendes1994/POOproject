package projecto;
import java.util.ArrayList;

/**
 * A Classe Administrador deriva da classe Utilizador, adoptando todos os seus métodos e <br/>
 * variáveis, acrescentando novas variáveis e métodos, nomeadamente aqueles que permitem fazer <br/>
 * operações não permitidas a Leitores
 */
public class Administrador extends Utilizador
{
    private String categoriaProfissional;
    protected static int numAdminActual = 1;
    private int numAdmin;

    /**
     * Cria um novo administrador no sistema
     * @param nome
     * @param morada
     * @param telefone
     * @param mail
     * @param password
     * @param categoria variável apenas de administradores, indicando a sua categoria profissional
     * @param birth
     * @param biblio
     *
     */
    public Administrador( String nome, String morada, String telefone, String mail, String password, String categoria, String birth, Biblioteca biblio )
    {
        super ( nome, morada , telefone, mail, password, birth, biblio );
        this.numAdmin = numAdminActual++;   // a numeração dos administradores é feita independentemente da dos leitores
        this.categoriaProfissional = categoria;
    }

    /**
     *
     * @param u recebe um utilizador
     * @return true se o administrador actual e o utilizador u forem o mesmo
     *         false caso contrário
     */
    public boolean equals ( Utilizador u )
    {
        return u instanceof Administrador && (this.numAdmin == ((Administrador) u).getNumAdmin());
    }

    /**
     * Função que recebe todos os dados necessários à criação de um livro, criando-o e adicionando-o <br/>
     * ao Array List de produtos da biblioteca a que pertence <br/>
     * @param nome
     * @param Editora
     * @param ano
     * @param autor
     * @param ISBN
     * @param cota
     */
    public void adicionaLivro ( String nome , String Editora , String ano , String autor, String ISBN, String cota )
    {
        this.biblioteca.getProdutos().add(new Livro(nome,autor,Editora,ano,cota,ISBN));
    }

    /**
     * Função que recebe todos os dados necessários à criação de um DVD, criando-o e adicionando-o <br/>
     * ao Array List de produtos da biblioteca a que pertence <br/>
     * @param nome
     * @param editora
     * @param ano
     */
    public void adicionaDVD ( String nome, String editora , String ano )
    {
        this.biblioteca.getProdutos().add(new DVD( nome, editora , ano));
    }

    /**
     * Função que recebe todos os dados necessários à criação de um administrador, criando-o e adicionando-o <br/>
     * ao Array List de utilizadores da biblioteca a que este administrador pertence <br/>
     * @param nome
     * @param morada
     * @param telefone
     * @param mail
     * @param password
     * @param birth
     * @param categoria
     */
    public void criaAdministrador ( String nome , String morada, String telefone, String mail , String password, String birth, String categoria )
    {
        this.biblioteca.getUtilizadores().add( new Administrador( nome, morada, telefone, mail, password, categoria, birth,this.biblioteca));
    }

    /**
     * Função que recebe todos os dados necessários à criação de um professor, criando-o e adicionando-o <br/>
     * ao Array List de utilizadores da biblioteca a que este administrador pertence <br/>
     * @param nome
     * @param morada
     * @param telefone
     * @param mail
     * @param data
     * @param password
     */
    public void criaProfessor ( String nome , String morada, String telefone, String mail , String data, String password )
    {
        this.biblioteca.getUtilizadores().add( new Professor( nome, morada, telefone, mail, password , data, this.biblioteca ));
    }

    /**
     * Função que recebe todos os dados necessários à criação de um aluno, criando-o e adicionando-o <br/>
     * ao Array List de utilizadores da biblioteca a que este administrador pertence <br/>
     * @param nome
     * @param morada
     * @param telefone
     * @param mail
     * @param data
     * @param password
     */
    public void criaAluno ( String nome , String morada, String telefone, String mail , String data, String password )
    {
        this.biblioteca.getUtilizadores().add( new Aluno( nome, morada, telefone, mail, password , data, this.biblioteca ));
    }

    /**
     * Função responsável por eliminar um Administrador
     * @param a recebe o administrador que é para eliminar
     */
    public void eliminaAdministrador ( Administrador a )
    {
        this.biblioteca.getUtilizadores().remove(a);
    }

    /**
     * Função responsável por eliminar um Leitor
     * @param a recebe o leitor que é para eliminar
     */
    public void eliminaLeitor (Leitor a )
    {
        this.biblioteca.getUtilizadores().remove(a);
    }

    /**
     * Função responsável por eliminar um produto do Array List dos produtos da biblioteca
     * @param dados String que corresponde a um ISBN no caso de se pretender eliminar um livro ou a um titulo <br/>
     *              caso se pretenda eliminar um DVD
     * @param livro variável booleana que indica o tipo de produto a eliminar ( true para livro e false para dvd )
     * @return ResultadoRequisicao.OK quando o produto foi eliminado com sucesso
     *         ResultadoRequisicao.UNAVAILABLE quando o produto está requisitado actualmente, não <br/>
     *         podendo,por isso,ser eliminado
     *         ResultadoRequisicao.INEXISTENTE quando não foi encontrado nenhum produto através dos <br/>
     *         parâmetros passados
     */
    public ResultadoRequisicao eliminaProduto ( String dados, boolean livro )
    {
        Produto objecto = null;

        if ( livro ) // se for um livro , procurar pelo ISBN
        {
            for ( Produto p: this.biblioteca.getProdutos() )
                if ( ((Livro)p).getISBN().equals(dados) )
                    objecto=p;
        }

        else // se for um DVD, procurar pelo título
        {
            for ( Produto p: this.biblioteca.getProdutos() )
                if ( p instanceof DVD )
                    if ( p.getTitulo().equals(dados) )
                        objecto=p;
        }

        // Agora que temos o produto, começar as verificações
        if ( objecto == null )
            return ResultadoRequisicao.INEXISTENTE;

        if ( objecto.getRequisicaoActual() != null )
            return ResultadoRequisicao.UNAVAILABLE;

        this.biblioteca.getProdutos().remove(objecto);
        return ResultadoRequisicao.OK;
    }

    public static int getNumAdminActual() {
        return numAdminActual;
    }

    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public int getNumAdmin() {
        return numAdmin;
    }

    /**
     *
     * @return true sempre, pois Administradores não têm máximo de produtos <br/>
     * para as suas requisições
     */
    public boolean podeRequisitar() {
        return true;
    }

    /**
     *
     * @return null sempre, pois Administradores não têm prazo de entrega <br/>
     * para as suas requisições
     */
    public ArrayList<Requisicao> requisicoesDVDAtraso()
    {
        return null;
    }

    /**
     *
     * @return null sempre, pois Administradores não têm prazo de entrega <br/>
     * para as suas requisições
     */
    public ArrayList<Requisicao> livrosAtraso()
    {
        return null;
    }
}
