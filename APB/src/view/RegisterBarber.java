/**
 * RegisterBarber
 * This class provides a GUI to save informations 
 * of a barber that is working in the barber shop.
 */

package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Barber;
import control.BarberController;
import exception.BarberException;

@SuppressWarnings("serial")
public class RegisterBarber extends JFrame {

	private JPanel contentPane;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterBarber newRegisterBarberWindow = new RegisterBarber();
					newRegisterBarberWindow.setVisible(true);
					newRegisterBarberWindow.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public RegisterBarber() {
		initializeComponents();
	}

	/**
	 * This void method starts all the components.
	 */
	public void initializeComponents() {
		setTitle("Barbeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 474, 429);
		contentPane.add(scrollPane);

		/*
		 * Creating a table to show the barber informations.
		 */
		final DefaultTableModel tableModel = new DefaultTableModel(null,
				new String[] { "Nome", "CPF", "RG", "Telefone", "Cadeira" });
		final JTable table = new JTable(tableModel);

		/*
		 * Getting an instance of a barber to populate the table with the its
		 * informations.
		 */
		try {
			BarberController barberController = BarberController
					.getInstance();
			Barber barber = new Barber();
			ResultSet barberResultSet = barberController
					.mostrarBarbeirosCadastrados(barber);
			while (barberResultSet.next()) {
				String[] barberData = new String[5];
				barberData[0] = barberResultSet.getString("nome");
				barberData[1] = barberResultSet.getString("cpf");
				barberData[2] = barberResultSet.getString("rg");
				barberData[3] = barberResultSet.getString("telefone");
				barberData[4] = barberResultSet.getString("cadeira");
				tableModel.addRow(barberData);
			}
		} catch (SQLException e) {
			showErrorMessage(e.getMessage());
		}

		scrollPane.setViewportView(table);

		/*
		 * Add a mouse clicked event. When the Novo Button is clicked, it
		 * creates a new window, which is NewBarber.
		 */
		JButton btnNewBarber = new JButton("Novo");
		btnNewBarber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				NewBarber newBarberWindow;
				try {
					newBarberWindow = new NewBarber();
					newBarberWindow.setVisible(true);
					newBarberWindow.setLocationRelativeTo(null);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewBarber.setBounds(494, 11, 158, 28);
		contentPane.add(btnNewBarber);

		/*
		 * Add a mouse clicked event. When the Alterar Button is clicked, it
		 * goes to a new window, which is AlterarBArbeiro, and dispose this one
		 * that is not needed.
		 */
		JButton btnUpdate = new JButton("Alterar");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Barber.setTempNome(tableModel.getValueAt(
							table.getSelectedRow(), 0).toString());
					AlterBarber newAlterBarberWindow = new AlterBarber();
					newAlterBarberWindow.setVisible(true);
					newAlterBarberWindow.setLocationRelativeTo(null);
					dispose();
				} catch (ArrayIndexOutOfBoundsException e1) {
					showErrorMessage("Selecione um Barbeiro para Alterar");
				}
			}
		});
		btnUpdate.setBounds(494, 50, 158, 28);
		contentPane.add(btnUpdate);

		/*
		 * Add a mouse clicked event. When the Remover Button is clicked, it
		 * access the database and remove the barber that is selected in the
		 * table.
		 */
		JButton btnRemove = new JButton("Remover");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String barberName = (String) table.getValueAt(
							table.getSelectedRow(), 0);
					Barber barber = new Barber();
					barber.setNome(barberName);

					int confirmation = JOptionPane.showConfirmDialog(null,
							"Remover " + barberName + " da lista?");

					if (confirmation == JOptionPane.YES_OPTION) {
						BarberController barberController = BarberController
								.getInstance();
						barberController.excluir(barber);

						dispose();
						RegisterBarber newRegisterBarberWindow = new RegisterBarber();
						newRegisterBarberWindow.setVisible(true);
						newRegisterBarberWindow.setLocationRelativeTo(null);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					showErrorMessage("Selecione um Barbeiro para remover");
				} catch (BarberException e) {
					showErrorMessage(e.getMessage());
				} catch (SQLException e) {
					showErrorMessage(e.getMessage());
				}
			}
		});
		btnRemove.setBounds(494, 89, 158, 28);
		contentPane.add(btnRemove);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is Administrative.
		 */
		JButton btnReturn = new JButton("Voltar");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Administrative newAdministrativeWindow = new Administrative();
				newAdministrativeWindow.setVisible(true);
				newAdministrativeWindow.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnReturn.setBounds(494, 412, 158, 28);
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
