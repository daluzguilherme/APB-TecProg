/**
 * SearchProvidedService
 * This class provides a GUI to search all services
 *  done by a certain barber.
 */

package view;

import java.awt.EventQueue;
import model.ProvidedService;

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

import control.ProvidedServiceController;

import dao.FactoryConnection;
import exception.ServiceException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class SearchProvidedService extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Connection connection;
	private static String tempNome;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchProvidedService frame = new SearchProvidedService();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public SearchProvidedService() {
		inicializarComponentes();
	}

	/**
	 * This void method starts all the components.
	 */
	public void inicializarComponentes() {
		setTitle("Pesquisar Servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 464, 115);
		contentPane.add(scrollPane);

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Servi�o", "Realizado por", "Valor", "Data" });
		final JTable table = new JTable(modelo);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(82, 137, 392, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(20, 137, 66, 14);
		contentPane.add(lblPesquisar);

		/*
		 * Add an action performed event. When the PesquisarServico Button is
		 * clicked, it searches the services done by its name.
		 */
		JButton btnPesquisarServico = new JButton("Pesquisar Servi�o");
		btnPesquisarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ProvidedService servico = new ProvidedService();
					servico.setNomeServico(textField.getText());

					connection = FactoryConnection.getInstance()
							.getConnection();
					ResultSet rs = connection.createStatement().executeQuery(
							"SELECT nome, preco, barbeiro, data FROM servicoprestado WHERE nome = '"
									+ servico.getNomeServico()
									+ "' ORDER BY data;");

					while (rs.next()) {
						String[] dados = new String[4];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("barbeiro");
						dados[2] = rs.getString("preco");
						dados[3] = servico.ConverterDataParaABNT(rs
								.getString("data"));
						modelo.addRow(dados);
					}
				} catch (ServiceException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ParseException e) {
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnPesquisarServico.setBounds(10, 168, 148, 23);
		contentPane.add(btnPesquisarServico);

		/*
		 * Add a mouse clicked event. When the PesquisarBarbeiro Button is
		 * clicked, it searches the services done by the name of the barber who
		 * finished it.
		 */
		JButton btnPesquisarBarbeiro = new JButton("Pesquisar Barbeiro");
		btnPesquisarBarbeiro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ProvidedService servico = new ProvidedService();
					servico.setNomeBarbeiro(textField.getText());

					connection = FactoryConnection.getInstance()
							.getConnection();
					ResultSet rs = connection.createStatement().executeQuery(
							"SELECT nome, preco, barbeiro, data FROM servicoprestado WHERE barbeiro = '"
									+ servico.getNomeBarbeiro()
									+ "' ORDER BY data;");

					while (rs.next()) {
						String[] dados = new String[4];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("barbeiro");
						dados[2] = rs.getString("preco");
						dados[3] = servico.ConverterDataParaABNT(rs
								.getString("data"));
						modelo.addRow(dados);
					}
				} catch (ServiceException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ParseException e) {
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnPesquisarBarbeiro.setBounds(168, 168, 148, 23);
		contentPane.add(btnPesquisarBarbeiro);

		/*
		 * Add a mouse clicked event. When the Romover Button is clicked, it
		 * removes the selected entry in the database.
		 */
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String nome = (String) table.getValueAt(
							table.getSelectedRow(), 0);
					String barbeiro = (String) table.getValueAt(
							table.getSelectedRow(), 1);
					String valor = (String) table.getValueAt(
							table.getSelectedRow(), 2);
					String data = (String) table.getValueAt(
							table.getSelectedRow(), 3);
					ProvidedService servico = new ProvidedService();
					servico.setNomeServico(nome);
					servico.setNomeBarbeiro(barbeiro);
					servico.setPreco(valor);
					servico.setData(data);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Remover " + nome + " da lista?");

					if (confirmacao == JOptionPane.YES_OPTION) {
						ProvidedServiceController servicoController = ProvidedServiceController
								.getInstance();
						servicoController.excluir(servico);

						dispose();
						RegisterProvidedService frame = new RegisterProvidedService();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					mostrarMensagemDeErro("Selecione um Servi�o para remover");
				} catch (ServiceException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ParseException e) {
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnRemover.setBounds(123, 228, 89, 23);
		contentPane.add(btnRemover);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is RegisterProvidedService.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RegisterProvidedService frame = new RegisterProvidedService();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(279, 228, 89, 23);
		contentPane.add(btnVoltar);

		/*
		 * Add a mouse clicked event. When the PesquisarData Button is clicked,
		 * it searches the services done by date wished.
		 */
		JButton btnPesquisarData = new JButton("Pesquisar Data");
		btnPesquisarData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ProvidedService servico = new ProvidedService();
					servico.setData(textField.getText());

					connection = FactoryConnection.getInstance()
							.getConnection();
					ResultSet rs = connection.createStatement().executeQuery(
							"Select nome, preco, barbeiro, data from servicoprestado where data = '"
									+ servico.getData() + "' order by data;");

					while (rs.next()) {
						String[] dados = new String[4];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("barbeiro");
						dados[2] = rs.getString("preco");
						dados[3] = servico.ConverterDataParaABNT(rs
								.getString("data"));
						modelo.addRow(dados);
					}
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ParseException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ServiceException e) {
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnPesquisarData.setBounds(326, 168, 148, 23);
		contentPane.add(btnPesquisarData);
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

	/**
	 * This method return a temporary name.
	 * 
	 * @return tempNome A String type variable that contains a temporary name.
	 */
	public static String getTempNome() {
		return tempNome;
	}
}
