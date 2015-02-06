/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class ProdutosNaoRequisitados extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1;
    private JLabel jLabel1;
    private JTable tabela;
    private JScrollPane areaRolamento;

    public ProdutosNaoRequisitados( Biblioteca biblioteca )
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        String[] titulos = {"Produto","Titulo", "Ano", "Editora"};
        this.jLabel1 = new JLabel("PRODUTOS NÃO REQUISITADOS NO MÊS DE " + mesPassado().toUpperCase() );
            this.jButton1 = new JButton("RETROCEDER");
        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(500, 450));

        Object [][] info = this.biblioteca.produtosNaoRequisitados();

        // se nao existirem administradores
        if ( info == null )
        {
            JLabel jLabel2 = new JLabel("NÃO FORAM ENCONTRADOS PRODUTOS");
            this.add("Center", jLabel2);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        }

        else
        {
            this.tabela = new JTable(info,titulos);
            this.areaRolamento = new JScrollPane(tabela);
            tabela.setPreferredScrollableViewportSize( new Dimension(325,80));
            this.add("Center", areaRolamento);
        }

        jButton1.addMouseListener(new MouseAdapter() {
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
     * @return  String correspondente ao nome do mês passado
     */
    private String mesPassado()
    {
        String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        //obter mes actual e mes passado
        int mesPassado;
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);

        if ( mesActual == 0 )
            mesPassado = 11;

        else
            mesPassado = mesActual - 1;

        return meses[mesPassado];
    }

    private void jButton1MouseClicked(MouseEvent evt) {
        this.setVisible(false);
    }
}