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

import control.ServicoPrestadoController;
import model.ServicoPrestado;
import exception.ServicoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CadastrarServicoPrestado extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarServicoPrestado frame = new CadastrarServicoPrestado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastrarServicoPrestado() {
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

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Serviço", "Realizado por", "Valor", "Data" });
		final JTable table = new JTable(modelo);

		try {
			ServicoPrestadoController servicoController = ServicoPrestadoController.getInstance();
			ServicoPrestado servico = new ServicoPrestado();
			ResultSet rs = servicoController.mostrarServicosPrestadosCadastrados(servico);
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

		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				NovoServicoPrestado frame = new NovoServicoPrestado();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnNovo.setBounds(380, 24, 94, 23);
		contentPane.add(btnNovo);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PesquisarServicoPrestado frame = new PesquisarServicoPrestado();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnPesquisar.setBounds(380, 58, 94, 23);
		contentPane.add(btnPesquisar);

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
					ServicoPrestado servico = new ServicoPrestado();
					servico.setNomeServico(nome);
					servico.setNomeBarbeiro(barbeiro);
					servico.setPreco(valor);
					servico.setData(data);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Remover " + nome + " da lista?");

					if (confirmacao == JOptionPane.YES_OPTION) {
						ServicoPrestadoController servicoController = ServicoPrestadoController
								.getInstance();
						servicoController.excluir(servico);

						dispose();
						CadastrarServicoPrestado frame = new CadastrarServicoPrestado();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					mostrarMensagemDeErro("Selecione um Serviço para remover");
				} catch (ServicoException e) {
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

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(380, 228, 94, 23);
		contentPane.add(btnVoltar);
	}

	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
