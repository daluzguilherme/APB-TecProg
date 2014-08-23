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

import control.TipoServicoController;
import model.TipoServico;
import exception.ServicoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CadastrarTipoServico extends JFrame {

	private JPanel contentPane;
	private static String nomeTemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTipoServico frame = new CadastrarTipoServico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarTipoServico() {
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

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Serviço", "Valor" });
		final JTable table = new JTable(modelo);
		try {
			TipoServicoController servicoController = TipoServicoController.getInstance();
			TipoServico servico= new TipoServico();
			ResultSet rs = servicoController.mostrarTipoServicoCadastrados(servico);
			while (rs.next()) {
				String[] dados = new String[5];
				dados[0] = rs.getString("nome");
				dados[1] = rs.getString("preco");
				modelo.addRow(dados);
			}
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		scrollPane.setViewportView(table);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				dispose();
				NovoTipoServico frame = new NovoTipoServico();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);

			}
		});
		btnNovo.setBounds(380, 24, 94, 23);
		contentPane.add(btnNovo);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					TipoServico.setTempNome(modelo.getValueAt(table.getSelectedRow(), 0).toString());
					AlterarTipoServico frame = new AlterarTipoServico();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (ServicoException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (ArrayIndexOutOfBoundsException e1) {
					mostrarMensagemDeErro("Selecione um Tipo de Serviço");
				}
			}
		});
		btnAlterar.setBounds(380, 58, 94, 23);
		contentPane.add(btnAlterar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nome = (String) table.getValueAt(table.getSelectedRow(),	0);
				TipoServico tipoServico = new TipoServico();
				
				try {	
					tipoServico.setNomeTipoServico(nome);
				} catch (ServicoException e1) {
					e1.printStackTrace();
				}

				int confirmacao = JOptionPane.showConfirmDialog(null,
						"Remover " + nome + " da lista?");

				if (confirmacao == JOptionPane.YES_OPTION) {
					TipoServicoController tipoServicoController = TipoServicoController.getInstance();
					try {
						tipoServicoController.excluir(tipoServico);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					dispose();
					CadastrarTipoServico frame = new CadastrarTipoServico();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				}

			}
		});
		btnRemover.setBounds(380, 92, 94, 23);
		contentPane.add(btnRemover);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(380, 228, 94, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Administrativo frame = new Administrativo();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		contentPane.add(btnVoltar);
	}

	public static String getNomeTemp() {
		return nomeTemp;
	}

	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
