/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class AddReader extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1, jButton6;
    private JCheckBox jCheckBox1, jCheckBox2;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12;
    private JPasswordField jPasswordField1, jPasswordField2;
    private JTextField jTextField1, jTextField2, jTextField3, jTextField5, jTextField7, jTextField9, jTextField10;

    public AddReader(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        // TITULOS
        jLabel1 = new JLabel("MENU DE ADMINISTRADOR");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2 = new JLabel("ADICIONAR LEITOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        //

        jTextField5 = new JTextField();
        jLabel5 = new JLabel("MAIL");
        jLabel6 = new JLabel("TELEFONE");
        jLabel3 = new JLabel("NOME");
        jLabel4 = new JLabel("MORADA");
        jTextField3 = new JTextField();
        jTextField2 = new JTextField();
        jTextField1 = new JTextField();
        jPasswordField2 = new JPasswordField();
        jTextField7 = new JTextField();
        jPasswordField1 = new JPasswordField();
        jLabel9 = new JLabel("PASSWORD");
        jLabel8 = new JLabel("DATA DE NASCIMENTO");
        jLabel10 = new JLabel("CONFIRMAR PASSWORD");
        jTextField9 = new JTextField();
        jLabel12 = new JLabel("/");
        jTextField10 = new JTextField();
        jLabel11 = new JLabel("/");

        // BOTÕES
        jButton6 = new JButton("RETROCEDER");
        jButton1 = new JButton("CONFIRMAR");

        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        //

        // CHECK BOX
        jCheckBox2 = new JCheckBox("PROFESSOR");
        jCheckBox1 = new JCheckBox("ALUNO");

        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        //

        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField1, GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, GroupLayout.Alignment.LEADING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPasswordField2)
                                            .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jCheckBox1)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jPasswordField2, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jPasswordField1,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap())
        );

        pack();
    }

    // CLICAR NO BOTAO 'RETROCEDER'
    private void jButton6MouseClicked(MouseEvent evt)
    {
        this.setVisible(false);
    }

    private void jCheckBox2MouseClicked(MouseEvent evt)
    {
        if(jCheckBox2.isSelected())
        jCheckBox1.setSelected(false);
    }

    private void jCheckBox2StateChanged(ChangeEvent evt)
    {
        if(jCheckBox2.isSelected())
            jCheckBox1.setSelected(false);
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt)
    {
        if(jCheckBox2.isSelected())
            jCheckBox1.setSelected(false);
    }

    private void jCheckBox1MouseClicked(MouseEvent evt)
    {
        if(jCheckBox1.isSelected())
            jCheckBox2.setSelected(false);
    }

    private void jCheckBox1StateChanged(ChangeEvent evt)
    {
        if(jCheckBox1.isSelected())
            jCheckBox2.setSelected(false);
    }

    private void jCheckBox1ActionPerformed(ActionEvent evt)
    {
        if(jCheckBox1.isSelected())
            jCheckBox2.setSelected(false);
    }

    // FOI CLICADO O BOTAO "CONFIRMAR", TEMOS DE VERIFICAR OS DADOS INSERIDOS
    private void jButton1MouseClicked(MouseEvent evt)
    {
        String nome = this.jTextField1.getText();
        String morada = this.jTextField2.getText();
        String mail = this.jTextField3.getText();
        String telefone = this.jTextField5.getText();
        int dia = this.jTextField7.getText().isEmpty()  ? 0: Integer.parseInt(this.jTextField7.getText());
        int mes = this.jTextField9.getText().isEmpty()  ? 0: Integer.parseInt(this.jTextField9.getText());
        int ano = this.jTextField10.getText().isEmpty() ? 0: Integer.parseInt(this.jTextField10.getText());
        String password = new String(this.jPasswordField1.getPassword());
        String confirmacao = new String(this.jPasswordField2.getPassword());
        
        if( !nome.isEmpty() && !morada.isEmpty() && !mail.isEmpty() && !telefone.isEmpty() )
        {
            if ( dia>0&&dia<32 &&mes>0&&mes<13 &&ano>0&&ano<2013)
            {
                if(password.equals(confirmacao))
                {
                    String data = Integer.toString(dia) + "-" + Integer.toString(mes) + "-" + Integer.toString(ano);

                    if(jCheckBox1.isSelected())
                    {
                        ((Administrador)this.biblioteca.getUtilizadorActual()).criaAluno(nome,morada,telefone,mail,data, password);
                        JOptionPane.showMessageDialog(this,"Dados Inseridos Com Sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                    }
                    else if(jCheckBox2.isSelected())
                    {
                        ((Administrador)this.biblioteca.getUtilizadorActual()).criaProfessor(nome, morada, telefone, mail, data, password);
                        JOptionPane.showMessageDialog(this,"Dados Inseridos Com Sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                    }
                    else
                        JOptionPane.showMessageDialog(this,"Escolher Categoria de Leitor", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(this,"Passwords Não Correspondem", "Erro",JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this,"Data Inválida","Erro",JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(this,"Dados Incompletos","Erro",JOptionPane.ERROR_MESSAGE);
    }
}
