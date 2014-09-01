/**
 * AlterarContato
 * This class provides a GUI to change information from the address 
 * book of possible barbers interested in working in the barber shop.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import control.AgendaController;
import exception.BarbeiroException;
import model.Agenda;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AlterarContato extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldDescricao;
	private String nome;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarContato frame = new AlterarContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public AlterarContato() {
		setTitle("Alterar Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(83, 22, 341, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(83, 53, 341, 20);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(83, 84, 341, 41);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 25, 46, 14);
		contentPane.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 56, 46, 14);
		contentPane.add(lblTelefone);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(10, 97, 63, 14);
		contentPane.add(lblDescricao);

		/*
		 * Getting an instance of a Agenda to be populated with a query from
		 * database.
		 */
		try {
			Agenda contato = new Agenda();
			AgendaController agendaController = AgendaController.getInstance();
			contato.setNome(Agenda.getTempNome());
			ResultSet rs = agendaController.pesquisarPorNome(contato);

			while (rs.next()) {
				textFieldNome.setText(rs.getString("nome"));
				textFieldTelefone.setText(rs.getString("telefone"));
				textFieldDescricao.setText(rs.getString("descricao"));
			}
			nome = textFieldNome.getText();
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		} catch (BarbeiroException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		/*
		 * Add an action performed event. When the SalvarAlteracao Button is
		 * clicked, it takes the strings from the text fields and saves them in
		 * in the database.
		 */
		JButton btnSalvarAlteracao = new JButton("Salvar Altera\u00E7\u00E3o");
		btnSalvarAlteracao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Agenda agenda = new Agenda();
					agenda.setNome(textFieldNome.getText());
					agenda.setTelefone(textFieldTelefone.getText());
					agenda.setDescricao(textFieldDescricao.getText());

					AgendaController AgendaController = control.AgendaController
							.getInstance();
					AgendaController.alterar(nome, agenda);

					JOptionPane.showMessageDialog(null, "Agenda "
							+ textFieldNome.getText()
							+ " foi alterado com sucesso");

					dispose();
					CadastrarAgenda frame = new CadastrarAgenda();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (BarbeiroException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (SQLException k) {
					mostrarMensagemDeErro(k.getMessage());
				}
			}

		});
		btnSalvarAlteracao.setBounds(83, 136, 153, 31);
		contentPane.add(btnSalvarAlteracao);

		/*
		 * Add an action performed event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is CadastrarAgenda.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose();
				CadastrarAgenda frame = new CadastrarAgenda();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(259, 136, 165, 31);
		contentPane.add(btnVoltar);
	}

	/**
	 * This method shows an error message.
	 * 
	 * @param informacao
	 *            A String type variable that contains the error message to be
	 *            shown to the user.
	 */
	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
