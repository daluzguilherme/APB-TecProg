/**
 * RegisterAddressBook
 * This class provides a GUI to save a contact of a 
 * barber in the address book.
 */

package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.AddressBook;
import control.AddressBookController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class RegisterAddressBook extends JFrame {

	private JPanel contentPane;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAddressBook registerAddresBook = new RegisterAddressBook();
					registerAddresBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public RegisterAddressBook() {
		setTitle("Agenda de Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 435, 401);
		contentPane.add(scrollPane);

		/*
		 * Creating a table to show the address book information.
		 */
		final DefaultTableModel tableModel = new DefaultTableModel(null,
				new String[] { "Nome", "Telefone", "Descri\u00E7\u00E3o" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		final JTable table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		/*
		 * Getting an instance of a Agenda to populate the table with the
		 * address book informations.
		 */
		try {
			AddressBookController addressBookController = AddressBookController.getInstance();
			AddressBook addressBookContact = new AddressBook();
			ResultSet addressBookResultSet = addressBookController.mostrarContatosCadastrados(addressBookContact);
			while (addressBookResultSet.next()) {
				String[] addressBookData = new String[3];
				addressBookData[0] = addressBookResultSet.getString("nome");
				addressBookData[1] = addressBookResultSet.getString("telefone");
				addressBookData[2] = addressBookResultSet.getString("descricao");
				tableModel.addRow(addressBookData);
			}
		} catch (SQLException e) {
			showErrorMessage(e.getMessage());
		}

		scrollPane.setViewportView(table);

		/*
		 * Add a mouse clicked event. When the Novo Button is clicked, it
		 * creates a new window, which is NewContact.
		 */
		JButton btnRegisterNewContact = new JButton("Novo");
		btnRegisterNewContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				NewContact newContactWindow;
				try {
					newContactWindow = new NewContact();
					newContactWindow.setVisible(true);
					newContactWindow.setLocationRelativeTo(null);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		});
		btnRegisterNewContact.setBounds(455, 24, 94, 23);
		contentPane.add(btnRegisterNewContact);

		/*
		 * Add a mouse clicked event. When the Pesquisar Button is clicked, it
		 * creates a new window, which is SearchContact.
		 */
		JButton btnSearchContact = new JButton("Pesquisar");
		btnSearchContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SearchContact searchContactWindow = new SearchContact();
				searchContactWindow.setVisible(true);
				searchContactWindow.setLocationRelativeTo(null);
			}
		});
		btnSearchContact.setBounds(455, 58, 94, 23);
		contentPane.add(btnSearchContact);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is Administrative.
		 */
		JButton btnReturn = new JButton("Voltar");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Administrative administrativeWindow = new Administrative();
				administrativeWindow.setVisible(true);
				administrativeWindow.setLocationRelativeTo(null);
			}
		});
		btnReturn.setBounds(455, 399, 94, 23);
		contentPane.add(btnReturn);
	}

	/**
	 * This method shows an error message.
	 * 
	 * @param errorMessage
	 *            A String type variable that contains the error message to be
	 *            shown to the user.
	 */
	private void showErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
