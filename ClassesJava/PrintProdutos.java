package projecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrintProdutos extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1;
    private JLabel jLabel1;
    private JTable tabela;
    private JScrollPane areaRolamento;

    public PrintProdutos( Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        String[] titulos = {"Tipo", "Nome", "Ano", "Editora", "Cota", "ISBN", "Autor", "Requisitado Actualmente"};
        this.jLabel1 = new JLabel("PRODUTOS");
        this.jButton1 = new JButton("Retroceder");
        this.setLayout(new BorderLayout(10,10));
        this.setMinimumSize( new Dimension(500,450));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Object [][] info = this.biblioteca.informacaoProdutos();

        // se nao existirem administradores
        if ( info == null )
        {
            JLabel jLabel2 = new JLabel("NÃO HÁ PRODUTOS ACTUALMENTE");
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

    private void jButton1MouseClicked(MouseEvent evt) {
        this.setVisible(false);
    }
}
