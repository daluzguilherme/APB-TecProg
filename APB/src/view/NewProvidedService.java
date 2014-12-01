/**
 * NewProvidedService
 * This class provides a GUI to save a 
 * new service finished by a barber.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import control.ProvidedServiceController;
import exception.ServiceException;
import model.ProvidedService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import dao.FactoryConnection;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class NewProvidedService extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldServicePriceNumber;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewProvidedService newNewProvidedServiceWindow = new NewProvidedService();
					newNewProvidedServiceWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/* Public method to create the frame. */
	public NewProvidedService() {
		setTitle("Criar nova presta\u00E7\u00E3o de servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStringService = new JLabel("Servi\u00E7o:");
		lblStringService.setBounds(27, 25, 46, 14);
		contentPane.add(lblStringService);

		JLabel lblStringProvidedBy = new JLabel("Realizado por:");
		lblStringProvidedBy.setBounds(27, 56, 92, 14);
		contentPane.add(lblStringProvidedBy);

		JLabel lblStringPrice = new JLabel("Pre\u00E7o (R$):");
		lblStringPrice.setBounds(27, 87, 71, 14);
		contentPane.add(lblStringPrice);

		textFieldServicePriceNumber = new JTextField();
		textFieldServicePriceNumber.setColumns(10);
		textFieldServicePriceNumber.setBounds(129, 84, 114, 20);
		contentPane.add(textFieldServicePriceNumber);

		/* Create a combo box to put all the barber's name. */
		final JComboBox comboBoxBarber = new JComboBox();
		comboBoxBarber.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione um barbeiro" }));
		comboBoxBarber.setBounds(129, 53, 289, 20);
		contentPane.add(comboBoxBarber);

		/*
		 * Some refactoring is needed here. Accessing the database direct from a
		 * view class.
		 */

		/*
		 * Getting the price value from the database to fill a text field.
		 */
		final JComboBox comboBoxTypeOfService = new JComboBox();
		comboBoxTypeOfService.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Connection connection;
				if (comboBoxTypeOfService.getSelectedIndex() != 0)
					try {
						String[] typeOfServiceNameSplit = comboBoxTypeOfService.getSelectedItem()
								.toString().split(" - ");
						connection = FactoryConnection.getInstance()
								.getConnection();
						java.sql.PreparedStatement pst1 = connection
								.prepareStatement("SELECT preco FROM tipoServico WHERE nome = \""
										+ typeOfServiceNameSplit[1] + "\";");
						ResultSet rs1 = pst1.executeQuery();
						rs1.next();

						textFieldServicePriceNumber.setText(rs1.getString("preco"));
					} catch (SQLException e) {
						showErrorMessage(e.getMessage());
					}
			}

		});

		/*
		 * Populating the combo box Barbeiro with all the barber's names and
		 * chair. Populating the combo box Servico with the type of the service
		 * done by the barber to the client.
		 */
		comboBoxTypeOfService.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione um tipo de servi\u00E7o" }));
		comboBoxTypeOfService.setMaximumRowCount(4);
		comboBoxTypeOfService.setBounds(129, 22, 289, 20);
		contentPane.add(comboBoxTypeOfService);
		try {
			int typeOfServiceCounter = 0;
			Connection connection = FactoryConnection.getInstance()
					.getConnection();
			java.sql.PreparedStatement pst = connection
					.prepareStatement("SELECT nome, cadeira FROM barbeiro ORDER BY cadeira;");
			java.sql.PreparedStatement pst2 = connection
					.prepareStatement("SELECT nome FROM tiposervico;");
			ResultSet rs = pst.executeQuery();
			ResultSet rs2 = pst2.executeQuery();

			while (rs.next()) {
				String barberName = rs.getString("nome");
				String barberChair = rs.getString("cadeira");
				comboBoxBarber.addItem(barberChair + " - " + barberName);
			}
			while (rs2.next()) {
				typeOfServiceCounter++;
				String typeOfServiceName = rs2.getString("nome");
				comboBoxTypeOfService.addItem(typeOfServiceCounter + " - " + typeOfServiceName);
			}

		} catch (SQLException e) {
			showErrorMessage(e.getMessage());
		}

		/*
		 * Add a mouse clicked event. When the Salvar Button is clicked, it
		 * takes the strings in all fields and creates a new entry in the
		 * database.
		 */
		JButton buttonRegisterNewProvidedService = new JButton("Salvar");
		buttonRegisterNewProvidedService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (comboBoxTypeOfService.getSelectedIndex() == 0)
						JOptionPane.showMessageDialog(null,
								"Você deve selecionar um tipo de serviço.");
					else if (comboBoxBarber.getSelectedIndex() == 0)
						JOptionPane.showMessageDialog(null,
								"Você deve selecionar um barbeiro.");
					else {
						String formatedIsoDate;
						Date systemDateInformation = new Date();
						SimpleDateFormat isoDateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						formatedIsoDate = isoDateFormat.format(systemDateInformation);

						String[] selectedTypeOfServiceInformationSplit = comboBoxTypeOfService.getSelectedItem()
								.toString().split(" - ");
						String[] selectedBarberInformationSplit = comboBoxBarber.getSelectedItem()
								.toString().split(" - ");

						ProvidedService providedService = new ProvidedService();

						providedService.setNomeBarbeiro(selectedBarberInformationSplit[1]);
						providedService.setNomeServico(selectedTypeOfServiceInformationSplit[1]);
						providedService.setPreco(textFieldServicePriceNumber.getText());
						providedService.setData(formatedIsoDate);

						ProvidedServiceController providedServiceController = ProvidedServiceController
								.getInstance();
						providedServiceController.inserir(providedService);

						JOptionPane.showMessageDialog(null,
								"Serviço criado com sucesso");

						comboBoxBarber.setSelectedIndex(0);
						comboBoxTypeOfService.setSelectedIndex(0);

						textFieldServicePriceNumber.setText("");
					}
				} catch (ServiceException e) {
					showErrorMessage(e.getMessage());
				} catch (SQLException e) {
					showErrorMessage(e.getMessage());
				} catch (ParseException e) {
					showErrorMessage(e.getMessage());
				}

			}
		});
		buttonRegisterNewProvidedService.setBounds(27, 129, 89, 23);
		contentPane.add(buttonRegisterNewProvidedService);

		/*
		 * Add a mouse clicked event. When the LimparCampos Button is clicked,
		 * it clears all the text fields.
		 */
		JButton buttonClearTextFields = new JButton("Limpar Campos");
		buttonClearTextFields.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldServicePriceNumber.setText("");
				comboBoxBarber.setSelectedIndex(0);
				comboBoxTypeOfService.setSelectedIndex(0);
			}
		});
		buttonClearTextFields.setBounds(152, 129, 148, 23);
		contentPane.add(buttonClearTextFields);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is RegisterBarber.
		 */
		JButton buttonReturn = new JButton("Voltar");
		buttonReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				RegisterProvidedService newRegisterProvidedServiceWindow = new RegisterProvidedService();
				newRegisterProvidedServiceWindow.setVisible(true);
				newRegisterProvidedServiceWindow.setLocationRelativeTo(null);
			}
		});
		buttonReturn.setBounds(329, 129, 89, 23);
		contentPane.add(buttonReturn);
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
