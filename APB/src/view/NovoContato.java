package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import control.AgendaController;
import exception.BarbeiroException;
import model.Agenda;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class NovoContato extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldDescricao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoContato frame = new NovoContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NovoContato() throws ParseException {
		inicializarComponentes();
	}

	public void inicializarComponentes() throws ParseException {
		setTitle("Novo Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MaskFormatter mascraFormatTel = new MaskFormatter("(##)####-####");

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Agenda agenda = new Agenda();
					agenda.setNome(textFieldNome.getText());
					agenda.setTelefone(textFieldTelefone.getText());
					agenda.setDescricao(textFieldDescricao.getText());

					AgendaController agendaController = AgendaController.getInstance();
					agendaController.incluir(agenda);

					JOptionPane.showMessageDialog(null, "Contato "
							+ textFieldNome.getText()
							+ " foi adicionado com sucesso");
					
					textFieldNome.setText("");
					textFieldTelefone.setText("");
					textFieldDescricao.setText("");
					
					dispose();
					CadastrarAgenda frame =  new CadastrarAgenda();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (SQLException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (BarbeiroException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				}
			}

		});

		btnSalvar.setBounds(26, 218, 109, 33);
		contentPane.add(btnSalvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				CadastrarAgenda frame = new CadastrarAgenda();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});

		btnVoltar.setBounds(166, 218, 100, 33);
		contentPane.add(btnVoltar);

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldNome.setText("");
				textFieldTelefone.setText("");
				textFieldDescricao.setText("");
			}
		});

		btnLimparCampos.setBounds(287, 218, 125, 33);
		contentPane.add(btnLimparCampos);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(85, 23, 327, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldTelefone = new JFormattedTextField(mascraFormatTel);
		textFieldTelefone.setBounds(85, 67, 327, 20);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(85, 117, 327, 44);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(22, 26, 46, 14);
		contentPane.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(22, 70, 64, 14);
		contentPane.add(lblTelefone);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(22, 117, 64, 14);
		contentPane.add(lblDescricao);
	}
	
	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
