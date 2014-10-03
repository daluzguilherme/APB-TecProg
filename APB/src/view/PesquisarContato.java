/**
 * PesquisarContato
 * This class provides a GUI to search all the information 
 * about an entry in the address book contact.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.AgendaController;
import exception.BarbeiroException;
import model.AdressBook;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class PesquisarContato extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisarContato frame = new PesquisarContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public PesquisarContato() {
		inicializarComponentes();
	}

	/**
	 * This void method starts all the components.
	 */
	public void inicializarComponentes() {
		setTitle("Pesquisar Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 115);
		contentPane.add(scrollPane);

		/* Creating a table to show the contacts informations. */
		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Nome", "Telefone", "Descrição" });
		final JTable table = new JTable(modelo);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(82, 137, 342, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(20, 137, 66, 14);
		contentPane.add(lblPesquisar);

		/*
		 * Add a mouse clicked event. When the PesquisarNome Button is clicked,
		 * it searches the contact by its name.
		 */
		JButton btnPesquisarNome = new JButton("Pesquisar Nome");
		btnPesquisarNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {

					for (int i = 0; i < modelo.getRowCount(); i++) {
						modelo.removeRow(i);
					}

					AdressBook contato = new AdressBook();
					AgendaController agendaController = AgendaController
							.getInstance();
					contato.setNome(textField.getText());
					ResultSet rs = agendaController.pesquisarPorNome(contato);

					while (rs.next()) {
						String[] dados = new String[3];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("telefone");
						dados[2] = rs.getString("descricao");
						modelo.addRow(dados);
					}
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (BarbeiroException e) {
					mostrarMensagemDeErro(e.getMessage());
				}
			}
		});
		btnPesquisarNome.setBounds(82, 168, 160, 23);
		contentPane.add(btnPesquisarNome);

		/*
		 * Add a mouse clicked event. When the PesquisarTelefone Button is
		 * clicked, it searches the contact by its telephone number.
		 */
		JButton btnPesquisarTelefone = new JButton("Pesquisar Telefone");
		btnPesquisarTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {

					for (int i = 0; i < modelo.getRowCount(); i++) {
						modelo.removeRow(i);
					}

					AdressBook contato = new AdressBook();
					AgendaController agendaController = AgendaController
							.getInstance();
					contato.setTelefone(textField.getText());
					ResultSet rs = agendaController
							.pesquisarPorTelefone(contato);

					while (rs.next()) {
						String[] dados = new String[3];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("telefone");
						dados[2] = rs.getString("descricao");

						modelo.addRow(dados);

						table.updateUI();
					}
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (BarbeiroException e) {
					mostrarMensagemDeErro(e.getMessage());
				}
			}
		});
		btnPesquisarTelefone.setBounds(264, 168, 160, 23);
		contentPane.add(btnPesquisarTelefone);

		/*
		 * Add a mouse clicked event. When the Alterar Button is clicked, it
		 * goes to a new window, which is AlterarContato, and dispose this one
		 * that is not needed.
		 */
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AdressBook.setTempNome(modelo.getValueAt(
							table.getSelectedRow(), 0).toString());
					dispose();
					AlterarContato frame = new AlterarContato();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (ArrayIndexOutOfBoundsException e1) {
					mostrarMensagemDeErro("Selecione um contato para alterar");
				}
			}
		});
		btnAlterar.setBounds(98, 228, 89, 23);
		contentPane.add(btnAlterar);

		/*
		 * Add a mouse clicked event. When the Romover Button is clicked, it
		 * removes the selected entry in the database. 
		 */
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					String nome = (String) table.getValueAt(
							table.getSelectedRow(), 0);
					String telefone = (String) table.getValueAt(
							table.getSelectedRow(), 1);
					AdressBook agenda = new AdressBook();
					agenda.setNome(nome);
					agenda.setTelefone(telefone);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Remover " + nome + " da lista?");

					if (confirmacao == JOptionPane.YES_OPTION) {
						AgendaController agendaController = AgendaController
								.getInstance();
						agendaController.excluir(agenda);

						dispose();
						PesquisarContato frame = new PesquisarContato();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				} catch (ArrayIndexOutOfBoundsException e1) {
					mostrarMensagemDeErro("Selecione um contato para remover");
				} catch (BarbeiroException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (SQLException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				}
			}
		});
		btnRemover.setBounds(216, 228, 89, 23);
		contentPane.add(btnRemover);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
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
		btnVoltar.setBounds(335, 228, 89, 23);
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
		JOptionPane.showMessageDialog(null, informacao, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
