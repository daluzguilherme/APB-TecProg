/**
 * VisualizarRelatorio
 * This class provides a GUI to see some types of reports.
 */

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

import model.Report;

import control.ReportController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import view.SearchReceipt;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import exception.ReportException;

@SuppressWarnings("serial")
public class ShowReceipt extends JFrame {

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

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowReceipt frame = new ShowReceipt();
					frame.setVisible(true);
				} catch (Exception e) {
					mostrarMensagemDeErro(e.getMessage());
				}
			}
		});
	}

	/* Public method to create the frame. */
	public ShowReceipt() throws SQLException, ReportException,
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

		/* Create a table with the services, quantity done and values involved. */
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

		ReportController relatorioController = ReportController
				.getInstance();

		/*
		 * Populate the table according to the report type of search selected.
		 */
		Report relatorio = new Report();

		if (SearchReceipt.tipoBusca == 1) {

			relatorio.setBarbeiro(SearchReceipt.barbeiro);

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
		if (SearchReceipt.tipoBusca == 2) {

			relatorio.setBarbeiro(SearchReceipt.barbeiro);
			relatorio.setTipoServico(SearchReceipt.servico);

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
		if (SearchReceipt.tipoBusca == 3) {

			relatorio.setBarbeiro(SearchReceipt.barbeiro);
			relatorio.setDataFinal(SearchReceipt.dataFinal);
			relatorio.setDataInicial(SearchReceipt.dataInicial);

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
		if (SearchReceipt.tipoBusca == 4) {

			relatorio.setBarbeiro(SearchReceipt.barbeiro);
			relatorio.setTipoServico(SearchReceipt.servico);
			relatorio.setDataFinal(SearchReceipt.dataFinal);
			relatorio.setDataInicial(SearchReceipt.dataInicial);

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
		if (SearchReceipt.tipoBusca == 5) {

			relatorio.setTipoServico(SearchReceipt.servico);

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
		if (SearchReceipt.tipoBusca == 6) {

			relatorio.setTipoServico(SearchReceipt.servico);
			relatorio.setDataFinal(SearchReceipt.dataFinal);
			relatorio.setDataInicial(SearchReceipt.dataInicial);

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
		if (SearchReceipt.tipoBusca == 7) {

			relatorio.setDataFinal(SearchReceipt.dataFinal);
			relatorio.setDataInicial(SearchReceipt.dataInicial);

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

		/*
		 * Add a mouse clicked event. When the Pesquisar Button is clicked, it
		 * goes to a new window, which is SearchReceipt, and dispose this
		 * one that is not needed.
		 */
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SearchReceipt.tipoBusca = 0;
					SearchReceipt frame = new SearchReceipt();
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

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is MainMenu.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenu frame = new MainMenu();
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

		/* Creates the graphical panel. */
		final JPanel painelGrafico = new JPanel();
		painelGrafico.setBounds(10, 10, 660, 486);
		contentPane.add(painelGrafico);
		painelGrafico.setVisible(true);

		/*
		 * If any type of search is selected, it will create a graphic.
		 */
		if (SearchReceipt.tipoBusca != 0) {
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
			} catch (ReportException e) {
				mostrarMensagemDeErro(e.getMessage());
			}

		}

		/*
		 * Add a mouse clicked event. When the Grafico Button is clicked, it
		 * will set visible or hide the graphic of the search.
		 */
		JButton btnGrafico = new JButton("Gr\u00E1fico");
		btnGrafico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (SearchReceipt.tipoBusca != 0) {
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
		/*
		 * Add a mouse clicked event. When the Tabela Button is clicked, it will
		 * set visible or hide the table content of the search.
		 */
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

		JLabel lblNewLabel = new JLabel(
				"Valor total a ser pago para o barbeiro:");
		lblNewLabel.setBounds(6, 4, 235, 14);
		painelTotalPago.add(lblNewLabel);

		JLabel lblvalorTotalDoBarbeiro = new JLabel("R$ "
				+ String.valueOf(decimal.format(total2)));
		lblvalorTotalDoBarbeiro.setVerticalAlignment(SwingConstants.BOTTOM);
		lblvalorTotalDoBarbeiro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblvalorTotalDoBarbeiro.setBounds(476, 4, 174, 14);
		painelTotalPago.add(lblvalorTotalDoBarbeiro);
	}

	/**
	 * A private method to create a Jfreechart dataset of Relatorio which is
	 * used to create the graphic image.
	 * 
	 * @return dataset The return is a Category Dataset with all the information
	 *         needed to create the graphic image.
	 * @throws SQLException
	 * @throws ReportException
	 * @throws NullPointerException
	 * @throws ParseException
	 */
	private CategoryDataset createDatasetRelatorio() throws SQLException,
			ReportException, NullPointerException, ParseException {

		Report relatorio = new Report();
		ResultSet rs = null;

		if (SearchReceipt.tipoBusca != 0) {
			if (SearchReceipt.tipoBusca == 1) {
				relatorio.setBarbeiro(SearchReceipt.barbeiro);

				rs = ReportController.getInstance().pesquisarPorBarbeiro(
						relatorio);
			}
			if (SearchReceipt.tipoBusca == 2) {
				relatorio.setBarbeiro(SearchReceipt.barbeiro);
				relatorio.setTipoServico(SearchReceipt.servico);

				rs = ReportController.getInstance()
						.pesquisarPorBarbeiroEServico(relatorio);
			}
			if (SearchReceipt.tipoBusca == 3) {
				relatorio.setBarbeiro(SearchReceipt.barbeiro);
				relatorio.setDataFinal(SearchReceipt.dataFinal);
				relatorio.setDataInicial(SearchReceipt.dataInicial);

				rs = ReportController.getInstance()
						.pesquisarPorDataEBarbeiro(relatorio);
			}
			if (SearchReceipt.tipoBusca == 4) {
				relatorio.setBarbeiro(SearchReceipt.barbeiro);
				relatorio.setTipoServico(SearchReceipt.servico);
				relatorio.setDataFinal(SearchReceipt.dataFinal);
				relatorio.setDataInicial(SearchReceipt.dataInicial);

				rs = ReportController.getInstance()
						.pesquisarPorDataBarbeiroEServico(relatorio);
			}
			if (SearchReceipt.tipoBusca == 5) {
				relatorio.setTipoServico(SearchReceipt.servico);

				rs = ReportController.getInstance().pesquisarPorServico(
						relatorio);
			}
			if (SearchReceipt.tipoBusca == 6) {
				relatorio.setTipoServico(SearchReceipt.servico);
				relatorio.setDataFinal(SearchReceipt.dataFinal);
				relatorio.setDataInicial(SearchReceipt.dataInicial);

				rs = ReportController.getInstance()
						.pesquisarPorDataEServico(relatorio);
			}
			if (SearchReceipt.tipoBusca == 7) {
				relatorio.setDataFinal(SearchReceipt.dataFinal);
				relatorio.setDataInicial(SearchReceipt.dataInicial);

				rs = ReportController.getInstance().pesquisarPorData(
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
	
	/**
	 * This method shows an error message.
	 * 
	 * @param informacao
	 *            A String type variable that contains the error message to be
	 *            shown to the user.
	 */
	private static void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
