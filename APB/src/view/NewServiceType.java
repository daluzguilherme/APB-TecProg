/**
 * NovoTiporServico
 * This class provides a GUI to save a 
 * new type of service offered by the barber shop.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.ServiceTypeController;

import exception.ServiceException;

import model.ServiceType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


@SuppressWarnings("serial")
public class NewServiceType extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldServico;
	private JTextField textFieldPreco;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewServiceType frame = new NewServiceType();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public NewServiceType() {
		setTitle("Cadastar novo tipo de servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblServico = new JLabel("Serviço:");
		lblServico.setBounds(29, 33, 46, 14);
		contentPane.add(lblServico);

		textFieldServico = new JTextField();
		textFieldServico.setBounds(100, 30, 170, 20);
		contentPane.add(textFieldServico);
		textFieldServico.setColumns(10);

		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(100, 63, 170, 20);
		contentPane.add(textFieldPreco);
		textFieldPreco.setColumns(10);

		JLabel lblPreco = new JLabel("Pre\u00E7o (R$):");
		lblPreco.setBounds(29, 65, 65, 17);
		contentPane.add(lblPreco);
		
		/*
		 * Add a mouse clicked event. When the Salvar Button is clicked, it
		 * takes the strings in all fields and creates a new entry in the
		 * database.
		 */
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ServiceType tipoServico = new ServiceType();
					tipoServico.setNomeTipoServico(textFieldServico.getText());
					tipoServico.setPreco(textFieldPreco.getText());

					ServiceTypeController tipoServicoController = ServiceTypeController
							.getInstance();
					tipoServicoController.inserir(tipoServico);

					JOptionPane.showMessageDialog(null, "Serviço "
							+ textFieldServico.getText()
							+ " foi cadastrado com sucesso");

					dispose();
					RegisterServiceType frame = new RegisterServiceType();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (ServiceException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (IllegalArgumentException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
				}
			}
		});
		btnSalvar.setBounds(29, 108, 89, 23);
		contentPane.add(btnSalvar);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is RegisterBarber.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterServiceType frame = new RegisterServiceType();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnVoltar.setBounds(181, 108, 89, 23);
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
