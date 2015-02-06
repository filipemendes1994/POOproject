package projecto;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Esta classe é responsável pela janela inicial do programa <br/>
 * A sua função é receber dados do utilizador - nome e password - e autenticar o utilizador no <br/>
 * sistema caso os seus dados se encontrem na base de dados
 */
public class Login extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1, jButton2;
    private JFormattedTextField jFormattedTextField1;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private JPasswordField jPasswordField1;

    /**
     *
     * @param biblioteca Recebe a biblioteca que servirá como base de dados a todos os processos seguintes <br/>
     *                   e que foi criada na classe Main, que por sua vez chamou a presente classe
     */
    public Login(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        this.setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    /**
     * Esta função é responsável por criar os componentes da janela e adicioná-los, bem como criar os seus listeners
     */
    private void initComponents()
    {
        jButton2 = new JButton("SAIR");
        jButton1 = new JButton("ENTRAR");

        jLabel2 = new JLabel();
        jLabel2.setText("PASSWORD:");
        jLabel1 = new JLabel();
        jLabel1.setText("NOME:");
        jFormattedTextField1 = new JFormattedTextField();
        jPasswordField1 = new JPasswordField();

        jLabel3 = new JLabel();
        jLabel3.setText("LOGIN");
        jLabel4 = new JLabel();
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("BEM-VINDO À BIBLIOTECA");

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jButton2)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 179,GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jFormattedTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(46, 46, 46)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                    .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     *
     * @param evt Esta função é chamada quando é detectado um clique de rato no botão jButton2 ( Sair ) <br/>
     *            Indica que o user quer sair do programa, por isso guardamos os dados da biblioteca em ficheiros <br/>
     *            e terminamos o programa
     */
    private void jButton2ActionPerformed(ActionEvent evt)
    {
        this.biblioteca.save();
        System.exit(0);
    }

    /**
     *
     * @param evt Esta função é chamada quando é detectado um clique de rato no botão jButton1 ( Entrar ) <br/>
     *            Tem como objectivo ler os dados introduzidos pelos utilizadores e verificar se é um utilizador válido <br/>
     *            Enquanto não for autenticado, o user ficará permanentemente na janela de Login, caso contrário é-lhe <br/>
     *            apresentado um menu de opções, que é diferente para Leitores e Administradores. Esta função é também <br/>
     *            responsável por ver se o utilizador logado é administrador ou leitor, apresentando-lhe o menu correcto
     */
    private void jButton1ActionPerformed(ActionEvent evt)
    {
        String nome = this.jFormattedTextField1.getText();
        String password = new String(this.jPasswordField1.getPassword());

        // logar utilizador
        Utilizador actual = biblioteca.login(nome, password);

        if( actual!=null )
        {
            this.setVisible(false);

            if(actual instanceof Leitor)
                new MenuLeitor( this.biblioteca , null).setVisible(true);
            else
                new MenuAdmin( this.biblioteca).setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(this, "Dados Inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
    }

}
