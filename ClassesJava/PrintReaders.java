/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Esta classe é responsável por apresentar uma nova janela no ecrã <br/>
 * onde figura uma tabela com informações de todos os leitores registados <br/>
 * actualmente na biblioteca
 */
public class PrintReaders extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1;
    private JLabel jLabel1;
    private JTable tabela;
    private JScrollPane areaRolamento;

    /**
     *
     * @param biblioteca Recebe a biblioteca de onde se pretende conhecer os leitores que existem
     */
    public PrintReaders(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        initComponents();
    }

    /**
     * Função responsável por criar e adicionar os listeners aos componentes da janela <br/>
     * No caso de ainda não existirem leitores é apresentada uma mensagem na própria janela com <br/>
     * essa mesma informação, caso contrário é gerada uma nova janela com uma tabela contendo <br/>
     * os dados de todos os leitores da biblioteca
     */
    private void initComponents()
    {
        String[] titulos = {"Nome","Categoria","Nº Leitor", "Morada", "Telefone","Mail","Password","Data Nascimento","Requisicoes Actuais"};
        jLabel1 = new JLabel("LEITORES");
        jButton1 = new JButton("RETROCEDER");
        this.setLayout(new BorderLayout(10,10));
        this.setMinimumSize( new Dimension(500,450));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Receber um array de Object[] com todas as informações de cada leitor da biblioteca
        Object [][] info = this.biblioteca.informacaoLeitores();

        // se ainda nao existirem leitores registados
        if ( info == null )
        {
            JLabel jLabel2 = new JLabel("AINDA NÃO HÁ LEITORES REGISTADOS");
            this.add("Center", jLabel2);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // caso contrário, criamos a tabela
        else
        {
            this.tabela = new JTable(info,titulos);
            this.areaRolamento = new JScrollPane(tabela);
            tabela.setPreferredScrollableViewportSize(new Dimension(325, 80));
            this.add("Center", areaRolamento);
        }

        jButton1.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        this.add("North", jLabel1);
        this.add("South", jButton1);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVisible(true);

        pack();
    }

    /**
     *
     * @param evt Foi detectado um clique de rato no botão 'Retroceder' <br/>
     *            terminamos esta janela e voltamos à anterior, que permaneceu aberta
     */
    private void jButton1MouseClicked(MouseEvent evt) {
        this.dispose();
    }
}
