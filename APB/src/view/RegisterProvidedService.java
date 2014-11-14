/**
 * RegisterProvidedService
 * This class provides a GUI to save informations 
 * of a service that a barber did.
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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.ProvidedServiceController;
import model.ProvidedService;
import exception.ServiceException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterProvidedService extends JFrame {

	private JPanel contentPane;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterProvidedService newRegisterProvidedService = new RegisterProvidedService();
					newRegisterProvidedService.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public RegisterProvidedService() {
		setTitle("Servi\u00E7os Prestados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 360, 240);
		contentPane.add(scrollPane);

		/*
		 * Creating a table to show the services informations.
		 */
		final DefaultTableModel tableModel = new DefaultTableModel(null,
				new String[] { "Serviço", "Realizado por", "Valor", "Data" });
		final JTable table = new JTable(tableModel);

		/*
		 * Getting an instance of a finished service to populate the table with
		 * the its informations.
		 */
		try {
			ProvidedServiceController providedServiceController = ProvidedServiceController
					.getInstance();
			ProvidedService providedService = new ProvidedService();
			ResultSet providedServiceResultSet = providedServiceController
					.mostrarServicosPrestadosCadastrados(providedService);
			while (providedServiceResultSet.next()) {
				String[] providedServiceData = new String[4];
				providedServiceData[0] = providedServiceResultSet.getString("nome");
				providedServiceData[1] = providedServiceResultSet.getString("barbeiro");
				providedServiceData[2] = providedServiceResultSet.getString("preco");
				providedServiceData[3] = providedService.ConverterDataParaABNT(providedServiceResultSet.getString("data"));
				tableModel.addRow(providedServiceData);
			}
		} catch (SQLException e) {
			showErrorMessage(e.getMessage());
		} catch (ParseException e) {
			showErrorMessage(e.getMessage());
		}

		scrollPane.setViewportView(table);

		/*
		 * Add a mouse clicked event. When the Novo Button is clicked, it goes
		 * to a new window, which is NewProvidedService, and dispose this one
		 * that is not needed.
		 */
		JButton btnNewProvidedService = new JButton("Novo");
		btnNewProvidedService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				NewProvidedService newNewProvidedServiceWindow = new NewProvidedService();
				newNewProvidedServiceWindow.setVisible(true);
				newNewProvidedServiceWindow.setLocationRelativeTo(null);
			}
		});
		btnNewProvidedService.setBounds(380, 24, 94, 23);
		contentPane.add(btnNewProvidedService);

		/*
		 * Add a mouse clicked event. When the Pesquisar Button is clicked, it
		 * goes to a new window, which is SearchProvidedService, and dispose
		 * this one that is not needed.
		 */
		JButton btnSearchProvidedService = new JButton("Pesquisar");
		btnSearchProvidedService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchProvidedService newSearchProvidedServiceWindow = new SearchProvidedService();
				newSearchProvidedServiceWindow.setVisible(true);
				newSearchProvidedServiceWindow.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSearchProvidedService.setBounds(380, 58, 94, 23);
		contentPane.add(btnSearchProvidedService);

		/*
		 * Add an action performed event. When the Remover Button is clicked, it
		 * access the database and remove the finished service that is selected
		 * in the table.
		 */
		JButton btnRemoveProvidedService = new JButton("Remover");
		btnRemoveProvidedService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String providedServiceName = (String) table.getValueAt(
							table.getSelectedRow(), 0);
					String responsableBarber = (String) table.getValueAt(
							table.getSelectedRow(), 1);
					String providedServiceValue = (String) table.getValueAt(
							table.getSelectedRow(), 2);
					String providedServiceDate = (String) table.getValueAt(
							table.getSelectedRow(), 3);
					ProvidedService servico = new ProvidedService();
					servico.setNomeServico(providedServiceName);
					servico.setNomeBarbeiro(responsableBarber);
					servico.setPreco(providedServiceValue);
					servico.setData(providedServiceDate);

					int confirmation = JOptionPane.showConfirmDialog(null,
							"Remover " + providedServiceName + " da lista?");

					if (confirmation == JOptionPane.YES_OPTION) {
						ProvidedServiceController servicoController = ProvidedServiceController
								.getInstance();
						servicoController.excluir(servico);

						dispose();
						RegisterProvidedService newRegisterProvidedService = new RegisterProvidedService();
						newRegisterProvidedService.setVisible(true);
						newRegisterProvidedService.setLocationRelativeTo(null);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					showErrorMessage("Selecione um Serviço para remover");
				} catch (ServiceException e) {
					showErrorMessage(e.getMessage());
				} catch (SQLException e) {
					showErrorMessage(e.getMessage());
				} catch (ParseException e) {
					showErrorMessage(e.getMessage());
				}

			}
		});
		btnRemoveProvidedService.setBounds(380, 92, 94, 23);
		contentPane.add(btnRemoveProvidedService);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is MainMenu.
		 */
		JButton btnReturn = new JButton("Voltar");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				MainMenu newMainMenuWindow = new MainMenu();
				newMainMenuWindow.setVisible(true);
				newMainMenuWindow.setLocationRelativeTo(null);
			}
		});
		btnReturn.setBounds(380, 228, 94, 23);
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
		JOptionPane.showMessageDialog(null, errorMessage, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
