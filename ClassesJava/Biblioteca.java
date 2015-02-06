package projecto;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;

/**
 * Classe que funciona como base de dados para to do o programa, guardando
 * informação sobre requisições antigas, requisições actuais, utilizadores, produtos e
 * ainda uma referência para o utilizador que está actualmente logado no sistema
 */
public class Biblioteca implements Serializable
{
    private ArrayList<Requisicao> requisicoesActuais;
    private ArrayList<Requisicao> requisicoesAntigas;
    private ArrayList<Utilizador> utilizadores;
    private ArrayList<Produto> produtos;
    private Utilizador utilizadorActual;

    /**
     * Criação de uma biblioteca; os seus arrays são inicializados e preenchidos com dados dos ficheiros
     * correspondentes. No caso de não existir nenhum administrador, é criado um por defeito
     */
    public Biblioteca()
    {
        this.requisicoesActuais = new ArrayList<Requisicao>();
        this.requisicoesAntigas = new ArrayList<Requisicao>();
        this.produtos = new ArrayList<Produto>();
        this.utilizadores = new ArrayList<Utilizador>();

        loadProdutos();
        loadUtilizadores();
        loadRequisicoes();

        int numAdmin=0;

        for ( Utilizador u: this.utilizadores )
            if ( u instanceof Administrador )
                numAdmin++;

        if ( numAdmin == 0 )
            this.utilizadores.add( new Administrador("manager", "Coimbra", "123", "mleal", "manager", "p", "22-5-1994", this));
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {}

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {}

    /**
     * Função responsável por encontrar um utilizador no sistema, recebendo um nome e uma password
     * @param nome nome do utilizador
     * @param pass password do mesmo utilizador
     * @return o utilizador com o nome e password correspondentes aos passados como parâmetros. No caso de
     * nenhum ser encontrado retorna null
     *
     */
    public Utilizador login(String nome, String pass)
    {
        for ( Utilizador u: this.getUtilizadores() )
            if ( u.nome.equals(nome) )
                if ( u.password.equals(pass) ) {
                    this.utilizadorActual = u ;
                    return u;
                }
        return null;
    }

    /**
     * Função responsável por encontrar um livro na biblioteca com um dado nome
     * @param nome nome do livro a encontrar
     * @return o livro, se for encontrado, ou null, caso nenhum seja encontrado
     */
    public Livro encontraLivro ( String nome )
    {
        for ( Produto p : this.produtos )
            if ( p instanceof Livro )
                if ( p.getTitulo().equals(nome) )
                    return (Livro)p;

        return null;
    }

    /**
     * Função responsável por encontrar um DVD na biblioteca com um dado nome
     * @param nome nome do DVD a encontrar
     * @return o dvd, quando encontrado, caso contrário retorna null
     */
    public DVD encontraDVD ( String nome )
    {
        for ( Produto p : this.produtos )
            if ( p instanceof DVD )
                if ( p.getTitulo().equals(nome) )
                    return (DVD)p;

        return null;
    }

    /**
     * Função Responsável por escrever dados das requisições da biblioteca num ficheiro
     * @throws IOException para quando houver problemas a escrever no ficheiro
     */
    public void saveRequisicoes() throws IOException
    {
        FileOutputStream requisicao = new FileOutputStream("Requisicoes.bin");
        ObjectOutputStream requisicaoOut = new ObjectOutputStream(requisicao);

        // escreveremos no mesmo ficheiros as requisicoes antigas e as actuais
        requisicaoOut.writeInt(this.requisicoesAntigas.size()+this.requisicoesActuais.size());

        for ( Requisicao r : requisicoesActuais )
            r.objectWrite(requisicaoOut);

        for ( Requisicao r : requisicoesAntigas )
            r.objectWrite(requisicaoOut);

        requisicaoOut.close();
    }

    /**
     * Função Responsável por ler de um ficheiro dados sobre requisições para a biblioteca preencher
     * os seus arrays de Requisições Antigas e Requisições Actuais
     */
    public void loadRequisicoes()
    {
        FileInputStream requisicao = null;
        try {
            requisicao = new FileInputStream("Requisicoes.bin");
            ObjectInputStream requisicaoIn = new ObjectInputStream(requisicao);
            int numRequisicoes = requisicaoIn.readInt();

            for ( int i=0; i < numRequisicoes ; i++ )
                new Requisicao().fromObject(requisicaoIn,this);

            requisicaoIn.close();

        } catch (Exception ignored) {}
    }

    /**
     * Função Responsável por guardar dados de utilizadores da biblioteca num ficheiro
     * @throws IOException para quando existirem erros a escrever no ficheiro
     */
    public void saveUtilizadores() throws IOException
    {
        int numAdmin=0,numLeitor=0;
        FileOutputStream userA = new FileOutputStream("Administradores.bin");
        ObjectOutputStream userOutA= new ObjectOutputStream(userA);

        for ( Utilizador u: this.utilizadores )
            if ( u instanceof Administrador )
                numAdmin++;
            else
                numLeitor++;

        userOutA.writeInt(Administrador.getNumAdminActual());
        userOutA.writeInt(numAdmin);

        FileOutputStream user = new FileOutputStream("Leitores.bin");
        ObjectOutputStream userOut = new ObjectOutputStream(user);

        userOut.writeInt(Leitor.numLeitorActual);
        userOut.writeInt(numLeitor);

        for ( Utilizador u: this.utilizadores )
            if ( u instanceof Administrador )
                userOutA.writeObject(u);
            else
                userOut.writeObject(u);

        // fechar ficheiros que abrimos
        userOutA.close();
        userOut.close();
    }

    /**
     * Função responsável por ler dados de utilizadores de um ficheiro para preencher o
     * array dos utilizadores
     */
    public void loadUtilizadores()
    {
        int numAdmin=0,numLeitores=0;

        try
        {
            FileInputStream usersA = new FileInputStream("Administradores.bin");
            ObjectInputStream usersInA = new ObjectInputStream(usersA);
            Administrador.numAdminActual = usersInA.readInt();

            numAdmin = usersInA.readInt();

            // adicionar ao array os administradores
            for ( int cont = 0; cont < numAdmin ; cont++ )
            {
                Administrador u = (Administrador)usersInA.readObject();
                u.actualiza(this);
                this.utilizadores.add(u);
            }

            FileInputStream users = new FileInputStream("Leitores.bin");
            ObjectInputStream usersIn = new ObjectInputStream(users);
            Leitor.numLeitorActual = usersIn.readInt();
            numLeitores = usersIn.readInt();

            // adicionar ao array os leitores
            for ( int cont = 0; cont < numLeitores ; cont++ )
            {
                Leitor u = (Leitor)usersIn.readObject();
                u.actualiza(this);
                this.utilizadores.add(u);
            }

            // TERMINAR COM OS UTILIZADORES
            usersInA.close();
            usersIn.close();

        } catch ( Exception ignored){}


    }

    /**
     * Função Responsável por escrever dados dos produtos da biblioteca num ficheiro de texto
     * @throws IOException para se houver erros na escrita do ficheiro
     */
    public void saveProdutos() throws IOException
    {
        BufferedWriter livros = new BufferedWriter(new FileWriter("livros.txt"));
        BufferedWriter dvds = new BufferedWriter( new FileWriter("dvds.txt"));

        for ( Produto p: this.produtos )
            if ( p instanceof Livro )
                livros.write( p.getLine() + "\n");
            else
                dvds.write( p.getLine() + "\n");

        livros.close();
        dvds.close();
    }

    /**
     * Função responsável por ler dados de produtos de um ficheiro de texto para preencher
     * o array de produtos da biblioteca
     */
    public void loadProdutos()
    {
        try {
            BufferedReader livros = new BufferedReader(new FileReader("livros.txt"));
            BufferedReader dvds = new BufferedReader(new FileReader("dvds.txt"));

            String linha;
            while ( (linha = livros.readLine()) != null )
            {
                Livro l = new Livro(linha);
                if ( !l.isInvalid() )
                    this.produtos.add(l);
            }

            while ( (linha = dvds.readLine()) != null )
            {
                DVD d = new DVD(linha);
                if ( !d.isInvalid() )
                    this.produtos.add(d);
            }

            livros.close();
            dvds.close();

        } catch (FileNotFoundException ignored){} catch (IOException ignored) {}
    }

    /**
     * Função Responsável por encontrar DVDs com um dado título e devolver todos os resultados
     * @param titulo titulo de DVD a procurar
     * @return array bidimensional em que cada indíce corresponde a um array com informações
     * de um DVD : <título,ano,editora,nºrequisiçoes,se está requisitado actualmente>
     */
    public Object[][] consultaDVDTitulo ( String titulo  )
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();

        int i;

        for ( Produto produto : this.produtos )
            if ( produto instanceof DVD )
                if ( produto.getTitulo().equals(titulo) )
                {
                    Object[] p = new Object[5];
                    i=0;
                    p[i++] = produto.getTitulo();
                    p[i++] = produto.getAno();
                    p[i++] = produto.getEditora();
                    p[i++] = produto.requisicoesAntigas.size();
                    if ( produto.requisicaoActual != null )
                        p[i] = "Sim";
                    else
                        p[i] = "Nao";

                    produtos.add(p);
                }

        // se nao tiverem sido encontrados nenhuns
        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][5];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função Responsável por encontrar Livros com um dado título e devolver todos os resultados
     * @param titulo titulo do livro a procurar
     * @return array bidimensional em que cada indíce corresponde a um array com informações
     * de um livro : <título,ano,editora,nºrequisiçoes,se está requisitado actualmente,cota,ISBN,autor>
     */
    public Object[][] consultaLivroTitulo ( String titulo  )
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        int i;

        for ( Produto produto : this.produtos )
            if ( produto instanceof Livro )
                if ( produto.getTitulo().equals(titulo) )
                {
                    Object[] p = new Object[8];
                    i=0;
                    p[i++] = produto.getTitulo();
                    p[i++] = produto.getAno();
                    p[i++] = produto.getEditora();
                    p[i++] = produto.requisicoesAntigas.size();
                    if ( produto.requisicaoActual != null )
                        p[i++] = "Sim";
                    else
                        p[i++] = "Nao";

                    p[i++] = ((Livro) produto).getCota();
                    p[i++] = ((Livro) produto).getISBN();
                    p[i] = ((Livro) produto).getAutor();

                    produtos.add(p);
                }

        // se nao tiver sido encontrado nenhum livro
        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][8];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função Responsável por encontrar livros com um dado autor e devolver todos os resultados
     * @param autor titulo de DVD a procurar
     * @return array bidimensional em que cada indíce corresponde a um array com informações
     * de um livro : <título,ano,editora,nºrequisiçoes,se está requisitado actualmente>
     */
    public Object[][] consultaLivroAutor ( String autor  )
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        int i;

        for ( Produto produto : this.produtos )
            if ( produto instanceof Livro )
                if ( ((Livro) produto).getAutor().equals(autor) )
                {
                    Object[] p = new Object[8];
                    i=0;
                    p[i++] = produto.getTitulo();
                    p[i++] = produto.getAno();
                    p[i++] = produto.getEditora();
                    p[i++] = produto.requisicoesAntigas.size();
                    if ( produto.requisicaoActual != null )
                        p[i++] = "Sim";
                    else
                        p[i++] = "Nao";

                    p[i++] = ((Livro) produto).getCota();
                    p[i++] = ((Livro) produto).getISBN();
                    p[i] = ((Livro) produto).getAutor();

                    produtos.add(p);
                }

        // se nao tiver sido encontrado nenhum livro
        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][8];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função responsável por retornar dados estatísticos para um mês passado como argumento. <br/>
     * Calcula o número médio de requisições e o dia do mês com mais requisições
     * @param mes O mês, na gama [1,12], para o qual queremos fazer a estatística
     * @return Array de dois elementos, sendo o primeiro o número médio de requisições e o segundo o dia do mês com mais requisições
     */
    public float[] dadosEstatisticos ( int mes )
    {
        int[] requisicoesDia = new int[31]; // automaticamente inicializado a zero
        float[] infoFinal = new float[2];
        int totalRequisicoes = 0;
        int maiorNumeroRequisicoes = 0;

        // preencher o array com as requisicoes de cada dia do mes
        for ( Requisicao r : this.requisicoesAntigas )
            if ( r.getDataRequisicao().get(Calendar.MONTH) == mes-1 ) // queremos apenas a do mês passado como argumento
            {
                totalRequisicoes++;
                requisicoesDia[r.getDataRequisicao().get(Calendar.DAY_OF_MONTH)-1]++;
            }

        for ( Requisicao r : this.requisicoesActuais )
                if ( r.getDataRequisicao().get(Calendar.MONTH) == mes-1 )
                {
                    totalRequisicoes++;
                    requisicoesDia[r.getDataRequisicao().get(Calendar.DAY_OF_MONTH)-1]++;
                }

        // calcular valor médio de livros requisitados por dia
        float valorMedio = ((float)totalRequisicoes)/requisicoesDia.length;
        infoFinal[0] = valorMedio;

        //encontrar o dia com mais requisicoes
        int temp = 0;
        for ( int v=0; v < requisicoesDia.length; v++ )
            if ( requisicoesDia[v] > maiorNumeroRequisicoes )
            {
                maiorNumeroRequisicoes = requisicoesDia[v];
                temp = v+1;
            }

        infoFinal[1] = (float)temp;

        return infoFinal;
    }

    /**
     * Função responsável por listar todos os produtos que não foram requisitados no mês passado
     * @return um array bidimensional, em que cada um dos seus indíces é um array com informações do produto
     * que não foi requisitado <Tipo de Produto,Titulo,Ano,Editora>
     */
    public Object[][] produtosNaoRequisitados()
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        boolean existe = false;

        for ( Produto p: this.produtos )
        {
            if ( p.requisicoesMesPassado() > 0 )
                    existe = true;

            if ( !existe )     // se nao tiver sido requisitado no mes passado, imprimir
            {
                Object[] pr  = new Object[4];

                if ( p instanceof Livro )
                    pr[0] = "Livro";
                else
                    pr[0] = "DVD";

                pr[1] = p.getTitulo();
                pr[2] = p.getAno();
                pr[3] = p.getEditora();

                produtos.add(pr);
            }
        }

        // se nao existirem produtos que nao tenham sido requisitados
        if ( produtos.isEmpty() )
            return null;

        Object[][] produtosFinal = new Object[produtos.size()][4];

        for ( int i=0; i < produtos.size(); i++ )
            produtosFinal[i] = produtos.get(i);

        return produtosFinal;
    }

    /**
     * Função Responsável por Encontrar todos os DVD em atraso
     * @return array bidimensional em que cada indice corresponde a um array com informaçao do DVD
     * <Nome,Leitor que requisitou,Data de Requisiçao,Suposta Data de Entrega>
     */
    public Object[][] dvdAtraso()
    {
        ArrayList<Object[]> atrasados = new ArrayList<Object[]>();

        for ( Utilizador u: this.utilizadores )
        {
            if ( u instanceof Leitor )
            {
                ArrayList<Requisicao> atrasadosUser;
                atrasadosUser = u.requisicoesDVDAtraso();

                if ( atrasadosUser != null )
                {
                    for ( Requisicao r : atrasadosUser )
                    {
                        Object[] temp = new Object[4];

                        // NOME DO PRODUTO
                        temp[0] = "DVD - " + r.getProduto().getTitulo();

                        // NOME DO UTILIZADOR
                        temp[1] = "Leitor - " + ((Leitor) u).getNumLeitor();

                        // DATA DE REQUISICAO
                        temp[2] = new SimpleDateFormat("dd-MM-yyyy").format(r.getDataRequisicao().getTime());

                        // DATA DE ENTREGA
                        Calendar dataEntrega = r.dataEntrega();
                        temp[3] = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrega.getTime());

                        atrasados.add(temp);
                    }
                }
            }
        }

        if ( atrasados.isEmpty() )
            return null;

        Object [][] dvdFinal = new Object[atrasados.size()][4];

        for ( int k=0; k< atrasados.size() ; k++ )
            dvdFinal[k] = atrasados.get(k);

        return dvdFinal;
    }

    /**
     * Função Responsável por Encontrar todos os livros em atraso
     * @return array bidimensional em que cada indice corresponde a um array com informaçao do livro
     * <Nome,Leitor que requisitou,Data de Requisiçao,Suposta Data de Entrega>
     */
    public Object[][] livrosAtraso()
    {
        ArrayList<Object[]> atrasados = new ArrayList<Object[]>();

        for ( Utilizador u: this.utilizadores )
        {
            if ( u instanceof Leitor )
            {
                ArrayList<Requisicao> atrasadosUser;
                atrasadosUser = u.livrosAtraso();

                if ( atrasadosUser != null )
                {
                    for ( Requisicao r : atrasadosUser )
                    {
                        Object[] temp = new Object[4];

                        // NOME DO PRODUTO
                        temp[0] = "Livro - " + r.getProduto().getTitulo();

                        // NOME DO UTILIZADOR
                        temp[1] = "Leitor - " + ((Leitor) u).getNumLeitor();

                        // DATA DE REQUISICAO
                        temp[2] = new SimpleDateFormat("dd-MM-yyyy").format(r.getDataRequisicao().getTime());

                        // DATA DE ENTREGA
                        Calendar dataEntrega = r.dataEntrega();
                        temp[3] = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrega.getTime());

                        atrasados.add(temp);
                    }
                }
            }
        }

        if ( atrasados.isEmpty() )
            return null;

        Object [][] livroFinal = new Object[atrasados.size()][4];

        for ( int k=0; k< atrasados.size() ; k++ )
            livroFinal[k] = atrasados.get(k);

        return livroFinal;
    }

    /**
     * Função responsável por devolver o livro mais requisitado do último mês
     * @return Array com informações do livro encontrado: <Titulo,Autor,Ano,Editora,Cota,ISBN,Nº de Requisicoes>
     */
    public Object[] livroMaisRequisitado()
    {
        int maiorNumero = -1;
        Livro temp = null;

        // começar a procurar livros na lista de requisicoes antigas
        for ( Requisicao r : this.requisicoesAntigas )
        {
            if ( r.getProduto() instanceof Livro ) // so nos interessam os livros
                // so nos interessam livros que tenham sido requisitados no mes passado
                if ( r.getProduto().requisicoesMesPassado() > maiorNumero )
                    temp = ((Livro)r.getProduto());
        }

        // temos ainda de procurar na lista das requisicoes actuais, pois ainda podemos encontrar alguma do mes passado
        for ( Requisicao r : this.requisicoesActuais )
        {
            if ( r.getProduto() instanceof Livro ) // so nos interessam os livros
                // so nos interessam livros que tenham sido requisitados no mes passado
                if ( r.getProduto().requisicoesMesPassado() > maiorNumero )
                {
                    temp = ((Livro)r.getProduto());
                    maiorNumero = r.getProduto().requisicoesMesPassado();
                }
        }

        // preencher array com informações que queremos que apareçam na tabela
        if ( temp != null )
        {
            Object[] dados = new Object[7];
            dados[0] = temp.getTitulo();
            dados[1] = temp.getAutor();
            dados[2] = temp.getAno();
            dados[3] = temp.getEditora();
            dados[4] = temp.getCota();
            dados[5] = temp.getISBN();
            dados[6] = maiorNumero;
            return dados;
        }

        return null;
    }

    /**
     * Função responsável por devolver o dvd mais requisitado do último mês
     * @return Array com informações do dvd encontrado: <Titulo,Ano,Editora,Nº de Requisicoes>
     */
    public Object[] dvdMaisRequisitado()
    {
        int maiorNumero = -1;
        DVD temp = null;

        // começar a procurar livros na lista de requisicoes antigas
        for ( Requisicao r : this.requisicoesAntigas )
        {
            if ( r.getProduto() instanceof DVD ) // so nos interessam os livros
                // so nos interessam livros que tenham sido requisitados no mes passado
                if ( r.getProduto().requisicoesMesPassado() > maiorNumero )
                    temp = ((DVD)r.getProduto());
        }

        // temos ainda de procurar na lista das requisicoes actuais, pois ainda podemos encontrar alguma do mes passado
        for ( Requisicao r : this.requisicoesActuais )
        {
            if ( r.getProduto() instanceof DVD ) // so nos interessam os livros
                // so nos interessam livros que tenham sido requisitados no mes passado
                if ( r.getProduto().requisicoesMesPassado() > maiorNumero )
                {
                    temp = ((DVD)r.getProduto());
                    maiorNumero = r.getProduto().requisicoesMesPassado();
                }
        }

        // preencher array com informações que queremos que apareçam na tabela
        if ( temp != null )
        {
            Object[] dados = new Object[4];
            dados[0] = temp.getTitulo();
            dados[1] = temp.getAno();
            dados[2] = temp.getEditora();
            dados[3] = maiorNumero;
            return dados;
        }

        return null;
    }

    /**
     * Função responsável por devolver informação de todos os Administradores da Biblioteca
     * @return array bidimensional onde a cada indice corresponde um array com as informações
     * de cada administrador <Nome,Numero,Morada,Telefone,Mail,Password,Data Nascimento, Categoria, Nº Requisicoes Actuais>
     */
    public Object[][] informacaoAdministradores()
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        int i;

        for ( Utilizador u : utilizadores )
            if ( u instanceof Administrador )
            {
                Object[] p = new Object[9];
                i =0;
                p[i++] = u.getNome();
                p[i++] = ((Administrador)u).getNumAdmin();
                p[i++] = u.getMorada();
                p[i++] = u.getTelefone();
                p[i++] = u.getMail();
                p[i++] = u.getPassword();
                p[i++] = u.getDataNascimento();
                p[i++] = ((Administrador)u).getCategoriaProfissional();
                p[i]   = u.getRequisicoesActuais().size();

                produtos.add(p);
            }

        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][9];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função responsável por devolver informação de todos os Leitores da Biblioteca
     * @return array bidimensional onde a cada indice corresponde um array com as informações
     * de cada leitor <Nome,Tipo de Leitor,Numero,Morada,Telefone,Mail,Password,Data Nascimento,Nº Requisicoes Actuais>
     */
    public Object[][] informacaoLeitores()
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        int i;

        for ( Utilizador u : utilizadores )
            if ( u instanceof Leitor )
            {
                Object[] p = new Object[9];
                i =0;
                p[i++] = u.getNome();

                if ( ((Leitor) u).maximoProdutos() == 5 )
                    p[i++] = "Professor";
                else
                    p[i++] = "Aluno";

                p[i++] = ((Leitor) u).getNumLeitor();
                p[i++] = u.getMorada();
                p[i++] = u.getTelefone();
                p[i++] = u.getMail();
                p[i++] = u.getPassword();
                p[i++] = u.getDataNascimento();
                p[i]   = u.getRequisicoesActuais().size();

                produtos.add(p);
            }

        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][9];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função responsável por devolver informação de todos os produtos da Biblioteca
     * @return array bidimensional onde a cada indice corresponde um array com as informações
     * de cada produto, que divergem de livro para dvd
     */
    public Object[][] informacaoProdutos()
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        int i;

        for ( Produto p : this.produtos )
        {
            Object[] pr = new Object[8];
            i =0;

            if ( p instanceof Livro )
            {
                pr[i++] = "Livro";
                pr[i++] = p.getTitulo();
                pr[i++] = p.getAno();
                pr[i++] = p.getEditora();
                pr[i++] = ((Livro)p).getCota();
                pr[i++] = ((Livro)p).getISBN();
                pr[i++] = ((Livro)p).getAutor();
                if ( p.requisicaoActual == null )
                    pr[i] = "Não";
                else
                    pr[i] = "Sim";
            }

            else // se for DVD
            {
                pr[i++] = "DVD";
                pr[i++] = p.getTitulo();
                pr[i++] = p.getAno();
                pr[i] = p.getEditora();
                if ( p.requisicaoActual == null )
                    pr[pr.length-1] = "Não";
                else
                    pr[pr.length-1] = "Sim";

            }

            produtos.add(pr);
        }

        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][8];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função responsável por devolver informação de todos as requisições da Biblioteca
     * @return array bidimensional onde a cada indice corresponde um array com as informações
     * de cada requisicao <Tipo Produto,Tipo Utilizador + Numero,Data Requisicao>
     */
    public Object[][] informacaoRequisicoesActuais()
    {
        ArrayList<Object[]> produtos = new ArrayList<Object[]>();
        int i;

        for ( Requisicao r : requisicoesActuais )
        {
            Object[] p = new Object[3];
            i =0;

            // ADICIONAR TIPO DE PRODUTO E SUA IDENTIFICAÇAO
            if ( r.getProduto() instanceof DVD )
                p[i++] = ( "DVD '" + r.getProduto().getTitulo() + "'");
            else
                p[i++] = ( "Livro '" + ((Livro)r.getProduto()).getISBN() + "'");

            // ADICIONAR TIPO DE UTILIZADOR E O SEU NUMERO
            if  ( r.getUtilizador() instanceof Leitor )
                if ( ((Leitor) r.getUtilizador()).maximoProdutos() == 5 )
                    p[i++] = ( "Professor - " + ((Leitor) r.getUtilizador()).numLeitor );
                else
                    p[i++] = ( "Aluno - " + ((Leitor) r.getUtilizador()).numLeitor );

            else
                p[i++] = ( "Administrador - " + ((Administrador) r.getUtilizador()).getNumAdmin() );

            // ADICIONAR DATA DA REQUISICAO
            p[i] = r.getDataRequisicao().get(Calendar.DAY_OF_MONTH) + "/" + (r.getDataRequisicao().get(Calendar.MONTH)+1) + "/" + r.getDataRequisicao().get(Calendar.YEAR);

            produtos.add(p);
        }

        if ( produtos.isEmpty() )
            return null;

        Object [][] produtosFinal = new Object[produtos.size()][3];

        for ( int k=0; k<produtos.size() ; k++ )
            produtosFinal[k] = produtos.get(k);

        return produtosFinal;
    }

    /**
     * Função responsável pela escrita em ficheiros de dados referentes aos produtos,utilizadores e requisicoes da biblioteca
     */
    public void save() {
        try {
            saveProdutos();
            saveUtilizadores();
            saveRequisicoes();
        } catch (IOException ignore) {}
    }

    /**
     * Função responsável por encontrar um produto, dado um id
     * @param id recebe o id
     * @return retorna o produto com esse id ou null quando nenhum é encontrado
     */
    public Produto encontraProdutoID( int id )
    {
        for ( Produto p: this.produtos )
            if ( p.getId() == id )
                return p;

        return null;
    }

    /**
     * Função responsável por adicionar uma requisição a um dos arrays de requisições, consoante já esteja terminada ou não
     * @param r recebe uma requisição
     */
    public void adicionaRequisicaoDoFicheiro( Requisicao r )
    {
        if ( r.isTerminada() )
            this.requisicoesAntigas.add(r);
        else
            this.requisicoesActuais.add(r);
    }

    /**
     * Função responsável por procurar um utilizador que seja o mesmo que o utilizador passado como parâmetro
     * @param other recebe um utilizador
     * @return retorna null se nenhum for encontrado, caso contrário retorna o utilizador do sistema que corresponde ao utilizador
     * passado como argumento
     */
    public Utilizador encontraUtilizador ( Utilizador other )
    {
        for ( Utilizador u: this.utilizadores )
            if ( u.equals(other))
                return u;

        return null;
    }

    /**
     * Função que encontra um Administrador através de um número de Administrador
     * @param numAdmin recebe o número de administrador a procurar
     * @return o administrador quando encontrado , caso contrário retorna null
     */
    public Administrador encontraAdministrador ( int numAdmin )
    {
        for ( Utilizador u: this.utilizadores )
            if ( u instanceof Administrador )
                if ( ((Administrador)u).getNumAdmin() == numAdmin )
                    return (Administrador)u;

        return null;
    }
    /**
     * Função que encontra um Administrador através de um número de Administrador
     * @param numLeitor recebe o número de administrador a procurar
     * @return o administrador quando encontrado , caso contrário retorna null
     */
    public Leitor encontraLeitor ( int numLeitor )
    {
        for ( Utilizador u: this.utilizadores )
            if ( u instanceof Leitor )
                if ( ((Leitor)u).getNumLeitor() == numLeitor )
                    return (Leitor)u;

        return null;
    }

    public ArrayList<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public ArrayList<Requisicao> getRequisicoesAntigas() {
        return requisicoesAntigas;
    }

    public ArrayList<Requisicao> getRequisicoesActuais() {
        return requisicoesActuais;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public Utilizador getUtilizadorActual() {
        return utilizadorActual;
    }
}