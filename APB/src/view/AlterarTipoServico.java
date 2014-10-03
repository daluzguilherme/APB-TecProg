/**
 * AlterarTipoServico
 * This class provides a GUI to change the chosen 
 * type of service information.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import control.ServiceTypeController;
import exception.ServiceException;
import model.ServiceType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AlterarTipoServico extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private String nome;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarTipoServico frame = new AlterarTipoServico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public AlterarTipoServico() {
		setTitle("Alterar Tipo Servico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(121, 11, 289, 20);
		contentPane.add(textFieldNome);

		JLabel labelNome = new JLabel("Tipo de servi\u00E7o:");
		labelNome.setBounds(21, 14, 90, 14);
		contentPane.add(labelNome);

		textFieldPreco = new JTextField();
		textFieldPreco.setColumns(10);
		textFieldPreco.setBounds(121, 42, 289, 20);
		contentPane.add(textFieldPreco);

		JLabel labelCadeira = new JLabel("Pre\u00E7o:");
		labelCadeira.setBounds(21, 45, 61, 14);
		contentPane.add(labelCadeira);

		/*
		 * Getting an instance of a TipoServico to be populated with a query
		 * from database.
		 */
		try {
			ServiceType tiposervico = new ServiceType();
			ServiceTypeController servicoController = ServiceTypeController
					.getInstance();
			tiposervico.setNomeTipoServico(ServiceType.getTempNome());
			ResultSet rs = servicoController.pesquisarPorNome(tiposervico);

			while (rs.next()) {
				textFieldNome.setText(rs.getString("nome"));
				textFieldPreco.setText(rs.getString("preco"));
			}
			nome = textFieldNome.getText();
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		} catch (ServiceException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		/*
		 * Add an action performed event. When the Salvar Button is clicked, it
		 * takes the strings from the text fields and saves them in in the
		 * database.
		 */
		JButton buttonSalvar = new JButton("Salvar");
		buttonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServiceType tipoServico = new ServiceType();
					tipoServico.setNomeTipoServico(textFieldNome.getText());
					tipoServico.setPreco(textFieldPreco.getText());

					ServiceTypeController tipoServicoController = ServiceTypeController
							.getInstance();
					tipoServicoController.alterar(nome, tipoServico);

					JOptionPane.showMessageDialog(null, "Tipo de Serviço "
							+ textFieldNome.getText()
							+ " foi alterado com sucesso");

					dispose();
					CadastrarTipoServico frame = new CadastrarTipoServico();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (ServiceException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (SQLException k) {
					mostrarMensagemDeErro(k.getMessage());
				}
			}
		});
		buttonSalvar.setBounds(10, 86, 124, 23);
		contentPane.add(buttonSalvar);

		/*
		 * Add an action performed event. When the Limpar Button is clicked, it
		 * clears the fields.
		 */
		JButton buttonLimpar = new JButton("Limpar Campos");
		buttonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				textFieldPreco.setText("");
			}
		});
		buttonLimpar.setBounds(282, 86, 128, 23);
		contentPane.add(buttonLimpar);

		/*
		 * Add an action performed event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is CadastrarTipoServico.
		 */
		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CadastrarTipoServico frame = new CadastrarTipoServico();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		buttonVoltar.setBounds(144, 86, 128, 23);
		contentPane.add(buttonVoltar);
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
