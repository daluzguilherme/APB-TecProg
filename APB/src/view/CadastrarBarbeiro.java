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

import model.Barbeiro;
import control.BarbeiroController;
import exception.BarbeiroException;

@SuppressWarnings("serial")
public class CadastrarBarbeiro extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarBarbeiro frame = new CadastrarBarbeiro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastrarBarbeiro() {
		inicializarComponentes();
	}

	public void inicializarComponentes() {
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

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Nome", "CPF", "RG", "Telefone", "Cadeira" });
		final JTable table = new JTable(modelo);

		try {
			BarbeiroController barbeiroController = BarbeiroController.getInstance();
			Barbeiro barbeiro = new Barbeiro();
			ResultSet rs = barbeiroController.mostrarBarbeirosCadastrados(barbeiro);
			while (rs.next()) {
				String[] dados = new String[5];
				dados[0] = rs.getString("nome");
				dados[1] = rs.getString("cpf");
				dados[2] = rs.getString("rg");
				dados[3] = rs.getString("telefone");
				dados[4] = rs.getString("cadeira");
				modelo.addRow(dados);
			}
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		scrollPane.setViewportView(table);

		JButton botaoNovo = new JButton("Novo");
		botaoNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				NovoBarbeiro frame;
				try {
					frame = new NovoBarbeiro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
	
			}
		});
		botaoNovo.setBounds(494, 11, 158, 28);
		contentPane.add(botaoNovo);

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Barbeiro.setTempNome(modelo.getValueAt(table.getSelectedRow(), 0).toString());
					AlterarBarbeiro frame = new AlterarBarbeiro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (ArrayIndexOutOfBoundsException e1) {
					mostrarMensagemDeErro("Selecione um Barbeiro para Alterar");
				}
			}
		});
		botaoAlterar.setBounds(494, 50, 158, 28);
		contentPane.add(botaoAlterar);

		JButton botaoRemover = new JButton("Remover");
		botaoRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String nome = (String) table.getValueAt(table.getSelectedRow(), 0);
					Barbeiro barbeiro = new Barbeiro();
					barbeiro.setNome(nome);
					
					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Remover " + nome + " da lista?");

					if (confirmacao == JOptionPane.YES_OPTION) {
						BarbeiroController barbeiroController = BarbeiroController.getInstance();
						barbeiroController.excluir(barbeiro);

						dispose();
						CadastrarBarbeiro frame = new CadastrarBarbeiro();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					mostrarMensagemDeErro("Selecione um Barbeiro para remover");
				} catch (BarbeiroException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				}
			}
		});
		botaoRemover.setBounds(494, 89, 158, 28);
		contentPane.add(botaoRemover);
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Administrativo frame = new Administrativo();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		botaoVoltar.setBounds(494, 412, 158, 28);
		contentPane.add(botaoVoltar);
	}

	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
