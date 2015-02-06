package projecto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Classe que representa um utilizador da biblioteca, leitor ou administrador
 */
public abstract class Utilizador implements Serializable
{
    protected String nome,morada,telefone,mail,password,dataNascimento;
    protected ArrayList<Requisicao> requisicoesAntigas;
    protected ArrayList<Requisicao> requisicoesActuais;
    protected Biblioteca biblioteca;

    public Utilizador( String nome, String morada, String telefone, String mail, String password , String dataNascimento ,Biblioteca biblio )
    {
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.mail = mail;
        this.password = password;
        this.biblioteca = biblio;
        this.dataNascimento = dataNascimento;
        this.requisicoesActuais = new ArrayList<Requisicao>();
        this.requisicoesAntigas = new ArrayList<Requisicao>();
    }

    /**
     *
     * @param biblio recebe uma biblioteca que terá de ser associada a este utilizador <br/>
     *
     * quando estamos a ler os utilizadores dos ficheiros chamamos esta função. <br/>
     * As requisições são associadas a este utilizador no momento em que são lidas do ficheiro
     */
    public void actualiza( Biblioteca biblio )
    {
        this.requisicoesActuais.clear();
        this.requisicoesAntigas.clear();
        this.biblioteca = biblio;
    }

    /**
     *
     * @param u recebe um utilizador u
     * @return true se este utilizador e o utilizador u são o mesmo. <br/>
     *         false caso contrário
     */
    public boolean equals ( Utilizador u )
    {
        return ( this.nome.equals(u.nome) );
    }

    /**
     *
     * @param r  recebe uma requisição lida de um ficheiro. <br/>
     *
     * se essa requisição já estiver terminada vai para o array das Requisições Antigas,
     * caso contrário para para as Requisições Actuais
     */
    public void adicionaRequisicaoDoFicheiro( Requisicao r )
    {
        if ( r.isTerminada() )
            this.requisicoesAntigas.add(r);
        else
            this.requisicoesActuais.add(r);
    }

    /**
     * Função Responsável por Requisitar um Produto para este Utilizador
     * @param dados String que é um ISBN se o produto a requisitar for um livro ou um título se se for um DVD
     * @param livro variável booleana ( true se for para requisitar um livro, false para dvd )
     * @return ResultadoRequisicao.INEXISTENTE quando nao é encontrado nenhum produto com os parâmetros inseridos <br/>
     *         ResultadoRequisicao.UNAVAILABLE quando esse produto já se encontra requisitado actualmente <br/>
     *         ResultadoRequisicao.OK quando a requisição foi feita com sucesso <br/>
     *         ResultadoRequisicao.MAX quando o utilizador já atingiu o maximo de requisições, não podendo requisitar mais <br/>
     */
    public ResultadoRequisicao requisitaProduto ( String dados , boolean livro )
    {
        Produto objecto = null;

        if ( livro ) // se se pretender requisitar um livro pesquisamos pelo ISBN
        {
            for ( Produto p : this.biblioteca.getProdutos() )
            {
                if ( p instanceof Livro )
                    if ( ((Livro) p).getISBN().equals(dados))
                        objecto = p;
            }
        }

        else  // se se pretender requisitar um DVD pesquisamos pelo nome
        {
            for ( Produto p : this.biblioteca.getProdutos() )
            {
                if ( p instanceof DVD )
                    if ( p.getTitulo().equals(dados))
                        objecto = p;
            }
        }

        // Agora que temos o produto, começar as verificações
        if ( objecto == null )
            return ResultadoRequisicao.INEXISTENTE;

        if ( this.podeRequisitar() )  // verificar se o maximo de requisiçoes ainda nao foi atingido
        {
            if ( objecto.requisicaoActual == null ) // se o objecto estiver disponivel para requisitar
            {
                Requisicao nova = new Requisicao( this, objecto );
                this.requisicoesActuais.add( nova );  // adicionar a requisicao à lista de requisicoes deste utilizador
                this.biblioteca.getRequisicoesActuais().add( nova );  // adicionar a requisição à lista de requisiçoes actuais da biblioteca
                objecto.requisicaoActual = nova;   // marcar esta requisição como a requisiçao actual do produto

                return ResultadoRequisicao.OK;
            }

            else
                return ResultadoRequisicao.UNAVAILABLE;
        }

        return ResultadoRequisicao.MAX;
    }

    /**
     *
     * @return um array bidimensional em que cada indíce corresponde a um array com informações de uma Requisição <NºRequisicao,Tipo Produto,Título,Data Requisição,Data Entrega>
     *
     */
    public Object[][] informacaoRequisicoesActuais()
    {
        Object[][] requisicoes = new Object[this.requisicoesActuais.size()][5];
        int cont=0;

        for ( Requisicao r: this.requisicoesActuais )
        {
            Object[] temp = new Object[5];

            temp[0] = cont+1;

            if ( r.getProduto() instanceof Livro )
                temp[1] = "Livro";
            else
                temp[1] = "DVD";

            temp[2] = r.getProduto().getTitulo();
            temp[3] = new SimpleDateFormat("dd-MM-yyyy").format( r.getDataRequisicao().getTime() ) ;
            if ( this instanceof Leitor )
                temp[4] = new SimpleDateFormat("dd-MM-yyyy").format( r.dataEntrega().getTime()) ;

            requisicoes[cont++] = temp;
        }

        // se nao existirem requisicoes actuais
        if ( this.requisicoesActuais.isEmpty() )
            return null;

        return requisicoes;
    }

    /**
     * Função Responsável por Devolver um Produto requisitado actualmente por este Utilizador
     * @param nome nome do produto a devolver
     * @param livro tipo de produto a devolver ( true para livro e false para dvd )
     * @return true se a devolução tiver sido bem sucedida, false caso contrário ( nomeadamente
     * quando não é encontrado nenhum produto com aqueles parâmetros )
     */
    public boolean devolveProduto ( String nome, boolean livro )
    {
        Produto produto;

        if ( livro ) // se for p devolver um livro
            produto = this.biblioteca.encontraLivro(nome);
        else
            produto = this.biblioteca.encontraDVD(nome);

        if ( produto != null )
        {
            biblioteca.getRequisicoesAntigas().add( produto.getRequisicaoActual() ); // adicionar esta requisição às requisiçoes terminadas da biblioteca
            biblioteca.getRequisicoesActuais().remove(produto.getRequisicaoActual()); // e apaga-la do array das requisiçoes actuais
            this.requisicoesActuais.remove(produto.getRequisicaoActual());
            this.requisicoesAntigas.add(produto.getRequisicaoActual());
            produto.getRequisicaoActual().setTerminada(true);
            produto.requisicaoActual = null;
            return true;
        }

        return false;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public ArrayList<Requisicao> getRequisicoesActuais() {
        return requisicoesActuais;
    }

    public abstract ArrayList<Requisicao> requisicoesDVDAtraso();
    public abstract ArrayList<Requisicao> livrosAtraso();
    public abstract boolean podeRequisitar();

}
