/**
 * AlterBarber
 * This class provides a GUI to change the chosen 
 * barber information.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import control.BarberController;
import exception.BarberException;

import model.Barber;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AlterBarber extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldRg;
	private JTextField textFieldTelefone;
	private JTextField textFieldCadeira;
	private String nome;
	private JTextField textFieldCpf;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterBarber frame = new AlterBarber();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public AlterBarber() {

		setTitle("Alterar Barbeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(92, 11, 354, 20);
		contentPane.add(textFieldNome);

		JLabel labelNome = new JLabel("Nome:");
		labelNome.setBounds(21, 14, 46, 14);
		contentPane.add(labelNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(21, 43, 31, 14);
		contentPane.add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(92, 40, 354, 20);
		contentPane.add(textFieldCpf);
		textFieldCpf.setColumns(10);

		textFieldRg = new JTextField();
		textFieldRg.setColumns(10);
		textFieldRg.setBounds(92, 71, 354, 20);
		contentPane.add(textFieldRg);

		JLabel labelRg = new JLabel("RG:");
		labelRg.setBounds(21, 77, 46, 14);
		contentPane.add(labelRg);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(92, 102, 354, 20);
		contentPane.add(textFieldTelefone);

		JLabel labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(21, 108, 61, 14);
		contentPane.add(labelTelefone);

		textFieldCadeira = new JTextField();
		textFieldCadeira.setColumns(10);
		textFieldCadeira.setBounds(92, 133, 354, 20);
		contentPane.add(textFieldCadeira);

		JLabel labelCadeira = new JLabel("Cadeira:");
		labelCadeira.setBounds(21, 139, 61, 14);
		contentPane.add(labelCadeira);

		/*
		 * Getting an instance of a Barbeiro to populate with
		 * a query from database.
		 */
		try {
			Barber barbeiro = new Barber();
			BarberController barbeiroController = BarberController
					.getInstance();
			barbeiro.setNome(Barber.getTempNome());

			ResultSet rs = barbeiroController.pesquisarPorNome(barbeiro);

			while (rs.next()) {
				textFieldNome.setText(rs.getString("nome"));
				textFieldCpf.setText(rs.getString("cpf"));
				textFieldRg.setText(rs.getString("rg"));
				textFieldTelefone.setText(rs.getString("telefone"));
				textFieldCadeira.setText(rs.getString("cadeira"));
			}
			nome = textFieldCpf.getText();
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		} catch (BarberException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		/*
		 * Add an action performed event. When the Salvar Button is
		 * clicked, it takes the strings from the text fields and saves
		 * them in in the database.
		 */
		JButton buttonSalvar = new JButton("Salvar");
		buttonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Barber barbeiro = new Barber();
					barbeiro.setNome(textFieldNome.getText());
					barbeiro.setCpf(textFieldCpf.getText());
					barbeiro.setRg(textFieldRg.getText());
					barbeiro.setTelefone(textFieldTelefone.getText());
					barbeiro.setCadeira(textFieldCadeira.getText());

					BarberController barbeiroController = BarberController
							.getInstance();
					barbeiroController.alterar(nome, barbeiro);

					JOptionPane.showMessageDialog(null, "Barbeiro "
							+ textFieldNome.getText()
							+ " foi alterado com sucesso");

					dispose();
					RegisterBarber frame = new RegisterBarber();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (BarberException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (SQLException k) {
					mostrarMensagemDeErro(k.getMessage());
				}
			}
		});
		buttonSalvar.setBounds(10, 196, 125, 23);
		contentPane.add(buttonSalvar);
		
		/*
		 * Add an action performed event. When the Limpar Button is
		 * clicked, it clears the fields.
		 */
		JButton buttonLimpar = new JButton("Limpar Campos");
		buttonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				textFieldRg.setText("");
				textFieldTelefone.setText("");
				textFieldCadeira.setText("");
			}
		});
		buttonLimpar.setBounds(308, 196, 138, 23);
		contentPane.add(buttonLimpar);
		
		/*
		 * Add an action performed event. When the Voltar Button is
		 * clicked, it returns the the previous window, which is RegisterBarber.
		 */
		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterBarber frame = new RegisterBarber();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		buttonVoltar.setBounds(158, 196, 125, 23);
		contentPane.add(buttonVoltar);

	}
	
	/**
	 * This method shows an error message.
	 * 
	 * @param informacao
	 *            A String type variable that contains the error message to be
	 *            shown to the user.
	 */
	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
