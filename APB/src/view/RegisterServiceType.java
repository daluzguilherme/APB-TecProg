/**
 * RegisterProvidedService
 * 
 * This class provides a GUI to save informations 
 * of a type of the service.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import control.ServiceTypeController;
import model.ServiceType;
import exception.ServiceException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class RegisterServiceType extends JFrame {

	private JPanel contentPane;
	private static String temporaryServiceTypeName;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterServiceType newRegisterServiceType = new RegisterServiceType();
					newRegisterServiceType.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public RegisterServiceType() {
		setTitle("Tipo de Servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 360, 240);
		contentPane.add(scrollPane);

		/* Creating a table to show the type of services informations. */
		final DefaultTableModel tableModel = new DefaultTableModel(null,
				new String[] { "Serviço", "Valor" });
		final JTable table = new JTable(tableModel);

		/*
		 * Getting an instance of a service type to populate the table with the
		 * its informations.
		 */
		try {
			ServiceTypeController serviceTypeController = ServiceTypeController
					.getInstance();
			ServiceType serviceType = new ServiceType();
			ResultSet serviceTypeResultSet = serviceTypeController
					.mostrarTipoServicoCadastrados(serviceType);
			while (serviceTypeResultSet.next()) {
				String[] serviceTypeData = new String[2];
				serviceTypeData[0] = serviceTypeResultSet.getString("nome");
				serviceTypeData[1] = serviceTypeResultSet.getString("preco");
				tableModel.addRow(serviceTypeData);
			}
		} catch (SQLException e) {
			showErrorMessage(e.getMessage());
		}

		scrollPane.setViewportView(table);

		/*
		 * Add a mouse clicked event. When the Novo Button is clicked, it goes
		 * to a new window, which is NewServiceType, and dispose this one that
		 * is not needed.
		 */
		JButton btnNewServiceType = new JButton("Novo");
		btnNewServiceType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				dispose();
				NewServiceType newNewServiceTypeWindow = new NewServiceType();
				newNewServiceTypeWindow.setVisible(true);
				newNewServiceTypeWindow.setLocationRelativeTo(null);

			}
		});
		btnNewServiceType.setBounds(380, 24, 94, 23);
		contentPane.add(btnNewServiceType);

		/*
		 * Add a mouse clicked event. When the Alterar Button is clicked, it
		 * goes to a new window, which is AlterServiceType, and dispose this one
		 * that is not needed. A temporary value of the TipoServico model class
		 * is set here to be used in the new window displayed.
		 */
		JButton btnUpdateServiceType = new JButton("Alterar");
		btnUpdateServiceType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ServiceType.setTempNome(tableModel.getValueAt(
							table.getSelectedRow(), 0).toString());
					AlterServiceType newAlterServiceTypeWindow = new AlterServiceType();
					newAlterServiceTypeWindow.setVisible(true);
					newAlterServiceTypeWindow.setLocationRelativeTo(null);
					dispose();
				} catch (ServiceException e1) {
					showErrorMessage(e1.getMessage());
				} catch (ArrayIndexOutOfBoundsException e1) {
					showErrorMessage("Selecione um Tipo de Serviço");
				}
			}
		});
		btnUpdateServiceType.setBounds(380, 58, 94, 23);
		contentPane.add(btnUpdateServiceType);

		/*
		 * Add a mouse clicked event. When the Remover Button is clicked, it
		 * access the database and remove the type of service that is selected
		 * in the table.
		 */
		JButton btnRemoveServiceType = new JButton("Remover");
		btnRemoveServiceType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String serviceTypeName = (String) table.getValueAt(
						table.getSelectedRow(), 0);
				ServiceType serviceType = new ServiceType();

				try {
					serviceType.setNomeTipoServico(serviceTypeName);
				} catch (ServiceException e1) {
					e1.printStackTrace();
				}

				int confirmation = JOptionPane.showConfirmDialog(null,
						"Remover " + serviceTypeName + " da lista?");

				if (confirmation == JOptionPane.YES_OPTION) {
					ServiceTypeController serviceTypeController = ServiceTypeController
							.getInstance();
					try {
						serviceTypeController.excluir(serviceType);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					dispose();
					RegisterServiceType newRegisterServiceTypeWindow = new RegisterServiceType();
					newRegisterServiceTypeWindow.setVisible(true);
					newRegisterServiceTypeWindow.setLocationRelativeTo(null);
				}

			}
		});
		btnRemoveServiceType.setBounds(380, 92, 94, 23);
		contentPane.add(btnRemoveServiceType);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is Administrative.
		 */
		JButton btnReturn = new JButton("Voltar");
		btnReturn.setBounds(380, 228, 94, 23);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Administrative newAdministrativeWindow = new Administrative();
				newAdministrativeWindow.setVisible(true);
				newAdministrativeWindow.setLocationRelativeTo(null);
			}
		});
		contentPane.add(btnReturn);
	}

	/**
	 * This method returns the temporary name of a service type.
	 * 
	 * @return temporaryServiceTypeName 
	 * 			  A String type variable that contains the
	 *         	  temporary name of a service type.
	 */
	public static String getTemporaryServiceTypeName() {
		return temporaryServiceTypeName;
	}

	/**
	 * This method shows an error message.
	 * 
	 * @param errorMessage
	 *            A String type variable that contains the error message to be
	 *            shown to the user.
	 */
	private void showErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
