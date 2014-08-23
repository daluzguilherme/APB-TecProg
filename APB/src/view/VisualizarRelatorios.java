package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import model.Relatorio;

import control.RelatorioController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import view.PesquisarRelatorio;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import exception.RelatorioException;

@SuppressWarnings("serial")
public class VisualizarRelatorios extends JFrame {

	private JPanel contentPane;
	private double total = 0;
	private String numero;
	List<String> servicos = new ArrayList<String>();
	private int contador = 0;
	private int numeroTotalDeServicos = 0;
	private double valorTotalDoServico = 0;
	private double valorTotalASerPAgo = 0;
	private double total2 = 0;

	DecimalFormat decimal = new DecimalFormat("##0.00");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarRelatorios frame = new VisualizarRelatorios();
					frame.setVisible(true);
				} catch (Exception e) {
					mostrarMensagemDeErro(e.getMessage());
				}
			}
		});
	}

	public VisualizarRelatorios() throws SQLException, RelatorioException,
			NullPointerException, ParseException {
		setTitle("Relat\u00F3rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 660, 486);
		contentPane.add(scrollPane);

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Nome do Serviço", "Quantidade", "Valor total",
						"Valor recebido" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		final JTable table = new JTable(modelo);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);

		RelatorioController relatorioController = RelatorioController
				.getInstance();

		Relatorio relatorio = new Relatorio();

		if (PesquisarRelatorio.tipoBusca == 1) {

			relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);

			ResultSet rs = relatorioController.pesquisarPorBarbeiro(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;

						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));
				
				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				modelo.addRow(dados);

				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;
			}
		}
		if (PesquisarRelatorio.tipoBusca == 2) {

			relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);
			relatorio.setTipoServico(PesquisarRelatorio.servico);

			ResultSet rs = relatorioController
					.pesquisarPorBarbeiroEServico(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;

						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));

				modelo.addRow(dados);
				
				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				
				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;
			}
		}
		if (PesquisarRelatorio.tipoBusca == 3) {

			relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);
			relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
			relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

			ResultSet rs = relatorioController
					.pesquisarPorDataEBarbeiro(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;

						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));

				modelo.addRow(dados);
				
				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				
				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;
			}
		}
		if (PesquisarRelatorio.tipoBusca == 4) {

			relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);
			relatorio.setTipoServico(PesquisarRelatorio.servico);
			relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
			relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

			ResultSet rs = relatorioController
					.pesquisarPorDataBarbeiroEServico(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;
						
						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));

				modelo.addRow(dados);

				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				
				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;

			}
		}
		if (PesquisarRelatorio.tipoBusca == 5) {

			relatorio.setTipoServico(PesquisarRelatorio.servico);

			ResultSet rs = relatorioController.pesquisarPorServico(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;

						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));

				modelo.addRow(dados);

				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				
				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;
			}
		}
		if (PesquisarRelatorio.tipoBusca == 6) {

			relatorio.setTipoServico(PesquisarRelatorio.servico);
			relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
			relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

			ResultSet rs = relatorioController
					.pesquisarPorDataEServico(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;

						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));

				modelo.addRow(dados);

				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				
				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;
			}
		}
		if (PesquisarRelatorio.tipoBusca == 7) {

			relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
			relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

			ResultSet rs = relatorioController.pesquisarPorData(relatorio);

			while (rs.next()) {

				if (servicos.contains(rs.getString("nome")) == false) {
					servicos.add(rs.getString("nome"));
					contador++;
				}
			}

			for (int i = 0; i < contador; i++) {
				rs.beforeFirst();
				while (rs.next()) {
					if (servicos.get(i).equals(rs.getString("nome"))) {
						numero = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numero);
						valorTotalDoServico = valorTotalDoServico + valor;

						numeroTotalDeServicos++;
					}
				}

				String[] dados = new String[4];
				dados[0] = servicos.get(i);
				dados[1] = Integer.toString(numeroTotalDeServicos);
				dados[2] = Double.toString(valorTotalDoServico)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalDoServico));
				valorTotalASerPAgo = valorTotalDoServico / 2;
				dados[3] = Double.toString(valorTotalASerPAgo)
						.replace(".", ",")
						.valueOf(decimal.format(valorTotalASerPAgo));

				modelo.addRow(dados);

				total = total + valorTotalDoServico;
				total2 = total2 + valorTotalASerPAgo;
				
				numeroTotalDeServicos = 0;
				valorTotalASerPAgo = 0;
				valorTotalDoServico = 0;
			}
		}
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					PesquisarRelatorio.tipoBusca = 0;
					PesquisarRelatorio frame = new PesquisarRelatorio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (ParseException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				}
			}
		});
		btnPesquisar.setBounds(680, 13, 94, 62);
		contentPane.add(btnPesquisar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnVoltar.setBounds(680, 527, 94, 23);
		contentPane.add(btnVoltar);

		JPanel panel = new JPanel();
		panel.setBounds(10, 529, 660, 22);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLucroTotal = new JLabel("Valor total pesquisado:");
		lblLucroTotal.setBounds(6, 4, 174, 14);
		panel.add(lblLucroTotal);

		JLabel lblValor = new JLabel("R$ "
				+ String.valueOf(decimal.format(total)));
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblValor.setBounds(476, 4, 174, 14);
		panel.add(lblValor);

		final JPanel painelGrafico = new JPanel();
		painelGrafico.setBounds(10, 10, 660, 486);
		contentPane.add(painelGrafico);
		painelGrafico.setVisible(true);

		if (PesquisarRelatorio.tipoBusca != 0) {
			try {
				CategoryDataset cds;
				cds = createDatasetRelatorio();
				String titulo = "Total Por Dia";
				String eixoy = "Valores";
				String txt_legenda = "Ledenda:";
				boolean legenda = true;
				boolean tooltips = true;
				boolean urls = true;
				JFreeChart graf = ChartFactory.createBarChart(titulo,
						txt_legenda, eixoy, cds, PlotOrientation.VERTICAL,
						legenda, tooltips, urls);
				ChartPanel myChartPanel = new ChartPanel(graf, true);
				myChartPanel.setSize(painelGrafico.getWidth(),
						painelGrafico.getHeight());
				myChartPanel.setVisible(true);
				painelGrafico.removeAll();
				painelGrafico.add(myChartPanel);
				painelGrafico.revalidate();
				painelGrafico.repaint();

			} catch (SQLException e) {
				mostrarMensagemDeErro(e.getMessage());
			} catch (RelatorioException e) {
				mostrarMensagemDeErro(e.getMessage());
			}

		}

		JButton btnGrafico = new JButton("Gr\u00E1fico");
		btnGrafico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (PesquisarRelatorio.tipoBusca != 0) {
					painelGrafico.setVisible(true);
					scrollPane.setVisible(false);
				} else
					JOptionPane
							.showMessageDialog(null,
									"Você deve fazer uma busca para visualizar o gráfico.");
			}
		});
		btnGrafico.setBounds(680, 159, 94, 62);
		contentPane.add(btnGrafico);

		JButton btnTabela = new JButton("Tabela");
		btnTabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				painelGrafico.setVisible(false);
				scrollPane.setVisible(true);
			}
		});
		btnTabela.setBounds(680, 86, 94, 62);
		contentPane.add(btnTabela);
		
		JPanel painelTotalPago = new JPanel();
		painelTotalPago.setBounds(10, 509, 660, 22);
		contentPane.add(painelTotalPago);
		painelTotalPago.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Valor total a ser pago para o barbeiro:");
		lblNewLabel.setBounds(6, 4, 235, 14);
		painelTotalPago.add(lblNewLabel);
		
		JLabel lblvalorTotalDoBarbeiro = new JLabel("R$ "
				+ String.valueOf(decimal.format(total2)));
		lblvalorTotalDoBarbeiro.setVerticalAlignment(SwingConstants.BOTTOM);
		lblvalorTotalDoBarbeiro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblvalorTotalDoBarbeiro.setBounds(476, 4, 174, 14);
		painelTotalPago.add(lblvalorTotalDoBarbeiro);
	}

	private CategoryDataset createDatasetRelatorio() throws SQLException,
			RelatorioException, NullPointerException, ParseException {

		Relatorio relatorio = new Relatorio();
		ResultSet rs = null;

		if (PesquisarRelatorio.tipoBusca != 0) {
			if (PesquisarRelatorio.tipoBusca == 1) {
				relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);

				rs = RelatorioController.getInstance().pesquisarPorBarbeiro(
						relatorio);
			}
			if (PesquisarRelatorio.tipoBusca == 2) {
				relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);
				relatorio.setTipoServico(PesquisarRelatorio.servico);

				rs = RelatorioController.getInstance()
						.pesquisarPorBarbeiroEServico(relatorio);
			}
			if (PesquisarRelatorio.tipoBusca == 3) {
				relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);
				relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
				relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

				rs = RelatorioController.getInstance()
						.pesquisarPorDataEBarbeiro(relatorio);
			}
			if (PesquisarRelatorio.tipoBusca == 4) {
				relatorio.setBarbeiro(PesquisarRelatorio.barbeiro);
				relatorio.setTipoServico(PesquisarRelatorio.servico);
				relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
				relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

				rs = RelatorioController.getInstance()
						.pesquisarPorDataBarbeiroEServico(relatorio);
			}
			if (PesquisarRelatorio.tipoBusca == 5) {
				relatorio.setTipoServico(PesquisarRelatorio.servico);

				rs = RelatorioController.getInstance().pesquisarPorServico(
						relatorio);
			}
			if (PesquisarRelatorio.tipoBusca == 6) {
				relatorio.setTipoServico(PesquisarRelatorio.servico);
				relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
				relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

				rs = RelatorioController.getInstance()
						.pesquisarPorDataEServico(relatorio);
			}
			if (PesquisarRelatorio.tipoBusca == 7) {
				relatorio.setDataFinal(PesquisarRelatorio.dataFinal);
				relatorio.setDataInicial(PesquisarRelatorio.dataInicial);

				rs = RelatorioController.getInstance().pesquisarPorData(
						relatorio);
			}
		}

		List<String> dias = new ArrayList<String>();

		while (rs.next())
			if (dias.contains(rs.getString("data")) == false)
				dias.add(rs.getString("data"));

		double totalPorDia = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < dias.size(); i++) {
			rs.beforeFirst();

			while (rs.next())
				if (rs.getString("data").equals(dias.get(i)))
					totalPorDia += Double.parseDouble(rs.getString("preco")
							.replace(",", "."));

			dataset.addValue(totalPorDia, dias.get(i), dias.get(0) + " - "
					+ dias.get(dias.size() - 1));
			totalPorDia = 0;
		}

		return dataset;
	}

	private static void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
