package projecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class DeliverProduct extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1,jButton2;
    private JLabel jLabel2, jLabel3, jLabel4, jLabel6;
    private JTextField caixa;
    private JTable tabelaRequisicoes;
    private JScrollPane areaRolamento1;
    private JPanel painelTabelas;
    private Object [][] requisicoesActuais;

    public DeliverProduct(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(500, 450));
        painelTabelas = new JPanel();
        painelTabelas.setLayout( new BoxLayout( painelTabelas, BoxLayout.Y_AXIS));

        // TITULOS
        jLabel2 = new JLabel("MENU DE LEITOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("DEVOLVER PRODUTO");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos, BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Número Requisição");
        caixa = new JTextField(10);
        JPanel pesquisa = new JPanel();
        pesquisa.add(jLabel4);
        pesquisa.add(caixa);

        painelTitulos.add(pesquisa);
        //

        // BOTÕES
        JPanel botoes = new JPanel();

        jButton1 = new JButton("RETROCEDER");
        botoes.add(jButton1);

        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2 = new JButton("DEVOLVER");
        botoes.add(jButton2);

        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        //

        // IMPRIMIR INFORMAÇAO
        JPanel total = new JPanel();
        total.setLayout( new BoxLayout( total, BoxLayout.Y_AXIS ));

        JPanel temp1 = new JPanel();
        jLabel6 = new JLabel("Não existem requisições actualmente");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setVisible(false);
        temp1.add(jLabel6);
        total.add(temp1);

        painelTabelas.add(total);

        // Tabelas
        String[] titulosLivro = {"Nº Requisição","Tipo","Título","Data Requisição", "Data Entrega" };
        DefaultTableModel tmLivros = new DefaultTableModel(titulosLivro,0);
        tabelaRequisicoes = new JTable(tmLivros);
        areaRolamento1 = new JScrollPane(tabelaRequisicoes);
        tabelaRequisicoes.setPreferredScrollableViewportSize(new Dimension(450, 100));
        imprimeTabelaRequisicoes();
        painelTabelas.add(areaRolamento1);
        //

        //
        this.add("North", painelTitulos);
        this.add("Center" , painelTabelas );
        this.add("South", botoes);
        this.setVisible(true);
        //
    }

    private void imprimeTabelaRequisicoes()
    {
        this.requisicoesActuais = this.biblioteca.getUtilizadorActual().informacaoRequisicoesActuais();

        if ( requisicoesActuais == null )
        {
            jLabel6.setVisible(true);
            areaRolamento1.setVisible(false);
            jLabel4.setVisible(false);
            caixa.setVisible(false);
        }
        else
        {
            jLabel6.setVisible(false);
            jLabel4.setVisible(true);
            caixa.setVisible(true);

            DefaultTableModel tm2 = (DefaultTableModel) tabelaRequisicoes.getModel();
            int numRows = tm2.getRowCount();

            // apagar tabela anterior, se existente
            for ( int i =0; i<numRows ; i++ )
                tm2.removeRow(0);

            for ( Object[] r : requisicoesActuais )
                tm2.addRow(r);

            areaRolamento1.setVisible(true);
        }
    }

    private void jButton2MouseClicked( MouseEvent evt)
    {
        int requisicao;
        try {
            requisicao = this.caixa.getText().isEmpty()  ? 0: Integer.parseInt(this.caixa.getText());
        } catch (Exception e ) {
            JOptionPane.showMessageDialog(this, "Dados Inválidos");
            return;
        }


        if ( requisicao < 1 || requisicao > this.requisicoesActuais.length )
        {
            JOptionPane.showMessageDialog(this, "Requisição Não Existente");
            return;
        }

        this.biblioteca.getUtilizadorActual().devolveProduto( (String)this.requisicoesActuais[requisicao-1][2], this.requisicoesActuais[requisicao-1][1].equals("Livro") );

        JOptionPane.showMessageDialog(this,"Produto entregue com sucesso!");
        this.dispose();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }
}
