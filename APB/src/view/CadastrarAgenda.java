/**
 * CadastrarAgenda
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
public class CadastrarAgenda extends JFrame {

	private JPanel contentPane;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarAgenda frame = new CadastrarAgenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public CadastrarAgenda() {
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
		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Nome", "Telefone", "Descri\u00E7\u00E3o" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		final JTable table = new JTable(modelo);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		/*
		 * Getting an instance of a Agenda to populate the table with the
		 * address book informations.
		 */
		try {
			AddressBookController agendaController = AddressBookController.getInstance();
			AddressBook contato = new AddressBook();
			ResultSet rs = agendaController.mostrarContatosCadastrados(contato);
			while (rs.next()) {
				String[] dados = new String[3];
				dados[0] = rs.getString("nome");
				dados[1] = rs.getString("telefone");
				dados[2] = rs.getString("descricao");
				modelo.addRow(dados);
			}
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		scrollPane.setViewportView(table);

		/*
		 * Add a mouse clicked event. When the Novo Button is clicked, it
		 * creates a new window, which is NovoContato.
		 */
		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				NovoContato frame;
				try {
					frame = new NovoContato();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		});
		btnNovo.setBounds(455, 24, 94, 23);
		contentPane.add(btnNovo);

		/*
		 * Add a mouse clicked event. When the Pesquisar Button is clicked, it
		 * creates a new window, which is PesquisarContato.
		 */
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				PesquisarContato frame = new PesquisarContato();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnPesquisar.setBounds(455, 58, 94, 23);
		contentPane.add(btnPesquisar);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is Administrativo.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Administrativo frame = new Administrativo();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(455, 399, 94, 23);
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
