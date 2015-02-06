package projecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class DeleteLeitor extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1,jButton2;
    private JLabel jLabel2, jLabel3, jLabel4;
    private JTextField caixa;

    public DeleteLeitor(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(500, 450));

        // TITULOS
        jLabel2 = new JLabel("MENU DE ADMINISTRADOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("ELIMINAR LEITOR");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos, BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Número de Leitor");
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

        jButton2 = new JButton("ELIMINAR");
        botoes.add(jButton2);

        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        //

        //
        this.add("North", painelTitulos);
        this.add("South", botoes);
        this.setVisible(true);
        //
    }

    private void jButton2MouseClicked( MouseEvent evt)
    {
        int numeroLeitor;
        try {
            numeroLeitor = this.caixa.getText().isEmpty()  ? 0: Integer.parseInt(this.caixa.getText());
        } catch (Exception e ) {
            JOptionPane.showMessageDialog(this, "Dados Inválidos");
            return;
        }

        Leitor a = this.biblioteca.encontraLeitor(numeroLeitor);

        if ( a == null )
        {
            JOptionPane.showMessageDialog(this, "Leitor Não Encontrado");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this,"De certeza que quer eliminar o Leitor " + a.getNome() + " ?");

        if ( opcao == 0 )
        {
            ((Administrador)this.biblioteca.getUtilizadorActual()).eliminaLeitor(a);
            JOptionPane.showMessageDialog(this,"Leitor Eliminado do Sistema");
        }

        this.dispose();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }
}
