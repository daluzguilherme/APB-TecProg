/**
 * NewContact
 * This class provides a GUI to save a new contact 
 * information in the address book.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import control.AddressBookController;
import exception.BarberException;
import model.AddressBook;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class NewContact extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldDescricao;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewContact frame = new NewContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public NewContact() throws ParseException {
		inicializarComponentes();
	}

	/**
	 * This void method starts all the components.
	 */
	public void inicializarComponentes() throws ParseException {
		setTitle("Novo Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MaskFormatter mascraFormatTel = new MaskFormatter("(##)####-####");

		/*
		 * Add a mouse clicked event. When the Salvar Button is clicked, it
		 * takes the strings in all fields and creates a new entry in the
		 * database.
		 */
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AddressBook agenda = new AddressBook();
					agenda.setNome(textFieldNome.getText());
					agenda.setTelefone(textFieldTelefone.getText());
					agenda.setDescricao(textFieldDescricao.getText());

					AddressBookController agendaController = AddressBookController
							.getInstance();
					agendaController.incluir(agenda);

					JOptionPane.showMessageDialog(null, "Contato "
							+ textFieldNome.getText()
							+ " foi adicionado com sucesso");

					textFieldNome.setText("");
					textFieldTelefone.setText("");
					textFieldDescricao.setText("");

					dispose();
					RegisterAddressBook frame = new RegisterAddressBook();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (SQLException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (BarberException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				}
			}

		});

		btnSalvar.setBounds(26, 218, 109, 33);
		contentPane.add(btnSalvar);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is RegisterBarber.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RegisterAddressBook frame = new RegisterAddressBook();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});

		btnVoltar.setBounds(166, 218, 100, 33);
		contentPane.add(btnVoltar);

		/*
		 * Add a mouse clicked event. When the LimparCampos Button is clicked,
		 * it clears all the text fields.
		 */
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldNome.setText("");
				textFieldTelefone.setText("");
				textFieldDescricao.setText("");
			}
		});

		btnLimparCampos.setBounds(287, 218, 125, 33);
		contentPane.add(btnLimparCampos);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(85, 23, 327, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldTelefone = new JFormattedTextField(mascraFormatTel);
		textFieldTelefone.setBounds(85, 67, 327, 20);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(85, 117, 327, 44);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(22, 26, 46, 14);
		contentPane.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(22, 70, 64, 14);
		contentPane.add(lblTelefone);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(22, 117, 64, 14);
		contentPane.add(lblDescricao);
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
