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
					RegisterProvidedService frame = new RegisterProvidedService();
					frame.setVisible(true);
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
		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Serviço", "Realizado por", "Valor", "Data" });
		final JTable table = new JTable(modelo);

		/*
		 * Getting an instance of a finished service to populate the table with
		 * the its informations.
		 */
		try {
			ProvidedServiceController servicoController = ProvidedServiceController
					.getInstance();
			ProvidedService servico = new ProvidedService();
			ResultSet rs = servicoController
					.mostrarServicosPrestadosCadastrados(servico);
			while (rs.next()) {
				String[] dados = new String[4];
				dados[0] = rs.getString("nome");
				dados[1] = rs.getString("barbeiro");
				dados[2] = rs.getString("preco");
				dados[3] = servico.ConverterDataParaABNT(rs.getString("data"));
				modelo.addRow(dados);
			}
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		} catch (ParseException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		scrollPane.setViewportView(table);

		/*
		 * Add a mouse clicked event. When the Novo Button is clicked, it goes
		 * to a new window, which is NewProvidedService, and dispose this one
		 * that is not needed.
		 */
		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				NewProvidedService frame = new NewProvidedService();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnNovo.setBounds(380, 24, 94, 23);
		contentPane.add(btnNovo);

		/*
		 * Add a mouse clicked event. When the Pesquisar Button is clicked, it
		 * goes to a new window, which is SearchProvidedService, and dispose
		 * this one that is not needed.
		 */
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchProvidedService frame = new SearchProvidedService();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnPesquisar.setBounds(380, 58, 94, 23);
		contentPane.add(btnPesquisar);

		/*
		 * Add an action performed event. When the Remover Button is clicked, it
		 * access the database and remove the finished service that is selected
		 * in the table.
		 */
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					mostrarMensagemDeErro("Selecione um Serviço para remover");
				} catch (ServiceException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ParseException e) {
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnRemover.setBounds(380, 92, 94, 23);
		contentPane.add(btnRemover);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is MainMenu.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(380, 228, 94, 23);
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
