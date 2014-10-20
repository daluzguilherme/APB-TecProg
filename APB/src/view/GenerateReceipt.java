/**
 * GenerateReceipt
 * This class provides a GUI to generate a receipt of all 
 * the services done by a barber in the current day of work.
 */

package view;

import java.awt.EventQueue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import control.BarberController;
import control.ReceiptController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.javadocx.CreateDocx;

@SuppressWarnings("serial")
public class GenerateReceipt extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDataInicial;
	private JTextField textFieldDataFinal;
	private double total = 0;
	private String numero;

	/* Defining some static information to be shown in the receipt. */
	private static String RAZAO_SOCIAL = "BARBEARIA DO ONOFRE LTDA - ME";
	private static String RECIBO_PAGAMENTO = "RECIBO PAGAMENTO ALUGUEL BENS MÓVEIS";
	private static String LINHA = "____________________________________________________________";
	private static String LOCAL_E_DATA = "                    Brasília - DF  ____/____/________";

	/**
	 * This method converts a date information to Brazilian ABNT format.
	 * 
	 * @param data
	 *            This variable carries a date information to be formated.
	 * @return databr This variable returns a date information in the Brazilian
	 *         ABNT format.
	 * @throws ParseException
	 */
	public String ConverterDataParaABNT(String data) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataISO = sdf.parse(data);

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		String databr = sdf2.format(dataISO);

		return databr;
	}

	/**
	 * This method converts a date information to Brazilian ABNT format without
	 * bars.
	 * 
	 * @param data
	 *            This variable carries a date information to be formated.
	 * @return databr This variable returns a date information in the Brazilian
	 *         ABNT format without bars.
	 * @throws ParseException
	 */
	public String ConverterDataParaABNTSemBarra(String data)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataISO = sdf.parse(data);

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		String databr = sdf2.format(dataISO);

		return databr;
	}

	/**
	 * This method converts a date information to ISO format.
	 * 
	 * @param data
	 *            This variable carries a date information to be formated.
	 * @return databr This variable returns a date information in ISO format.
	 * @throws ParseException
	 */
	private String ConverterDataParaISO(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataABNT = sdf.parse(data);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String dataISO = sdf2.format(dataABNT);

		return dataISO;
	}

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReceipt frame = new GenerateReceipt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public GenerateReceipt() throws ParseException {
		setTitle("Gerar Recibo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* Create a combo box to put barber informations. */
		final JComboBox comboBoxBarbeiros = new JComboBox();
		comboBoxBarbeiros.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione um barbeiro" }));
		comboBoxBarbeiros.setBounds(10, 32, 304, 26);
		contentPane.add(comboBoxBarbeiros);

		/* Populating the combo box with the barber informations. */
		try {
			ResultSet rs = BarberController.getInstance().pesquisar();
			while (rs.next()) {
				comboBoxBarbeiros.addItem(rs.getString("cadeira") + " - "
						+ rs.getString("nome"));
			}
		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		}

		final MaskFormatter mascaraFormatoData = new MaskFormatter("##/##/####");

		textFieldDataInicial = new JFormattedTextField(mascaraFormatoData);
		textFieldDataInicial.setBounds(10, 110, 124, 20);
		contentPane.add(textFieldDataInicial);
		textFieldDataInicial.setColumns(10);

		JLabel lblDataDeInicio = new JLabel("Data Inicial");
		lblDataDeInicio.setBounds(36, 89, 86, 14);
		contentPane.add(lblDataDeInicio);

		textFieldDataFinal = new JFormattedTextField(mascaraFormatoData);
		textFieldDataFinal.setBounds(190, 110, 124, 20);
		contentPane.add(textFieldDataFinal);
		textFieldDataFinal.setColumns(10);

		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(215, 89, 86, 14);
		contentPane.add(lblDataFinal);

		/*
		 * Add a mouse clicked event. When the GenerateReceipt Button is clicked, it
		 * generates a receipt with all the services informations of the day of
		 * that one barber chosen in the combo box.
		 */
		JButton btnGerarRecibo = new JButton("Gerar Recibo");
		btnGerarRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReceiptController reciboController = ReceiptController
						.getInstance();
				try {
					if (comboBoxBarbeiros.getSelectedIndex() != 0) {
						CreateDocx docx = new CreateDocx("docx");

						HashMap paramsCabeca = new HashMap();
						HashMap paramsTitulo = new HashMap();
						HashMap paramsValor = new HashMap();
						HashMap paramsTexto = new HashMap();
						HashMap paramsLinhaAssinatura = new HashMap();
						HashMap paramsTexto4 = new HashMap();
						HashMap paramsQuebraLinha = new HashMap();
						HashMap paramsAssinaturaBarbeiro = new HashMap();

						paramsCabeca.put("b", "single");
						paramsCabeca.put("jc", "center");
						paramsCabeca.put("font", "Arial");

						paramsTitulo.put("b", "single");
						paramsTitulo.put("jc", "center");
						paramsTitulo.put("font", "Arial");

						paramsValor.put("b", "single");
						paramsValor.put("jc", "center");
						paramsValor.put("font", "Arial");

						paramsTexto.put("font", "Arial");
						paramsTexto.put("align", "justify");

						paramsLinhaAssinatura.put("jc", "center");

						paramsTexto4.put("jc", "center");
						paramsTexto4.put("b", "single");

						paramsAssinaturaBarbeiro.put("jc", "center");
						paramsAssinaturaBarbeiro.put("b", "single");

						final String dataInicialIso = ConverterDataParaISO(textFieldDataInicial
								.getText());
						final String dataFinalIso = ConverterDataParaISO(textFieldDataFinal
								.getText());

						String[] nome = comboBoxBarbeiros.getSelectedItem()
								.toString().split(" - ");

						ResultSet rs = reciboController.getInstance()
								.pesquisarServicosDoBarbeiro(nome[1],
										dataInicialIso, dataFinalIso);
						while (rs.next()) {
							numero = rs.getString("preco").replace(",", ".");
							double valor = Double.parseDouble(numero);
							total = total + (valor / 2);
						}

						DecimalFormat decimal = new DecimalFormat("##0.00");

						String dataInic = textFieldDataInicial.getText();
						String dataFin = textFieldDataFinal.getText();

						String valor = ("VALOR R$ " + decimal.format(total));

						String texto = "                    Recebi do Sr. "
								+ nome[1] + " a importância supra de R$ "
								+ (decimal.format(total)) + ", "
								+ "referente ao Aluguel do período de "
								+ dataInic + " até " + dataFin
								+ ", conforme CONTRATO de locação "
								+ "de bens móveis, firmado entre as partes.";
						String texto2 = "                    Por ser verdade assino o presente RECIBO para"
								+ " os fins de direitos, de acordo com a lei.";

						docx.addText(RAZAO_SOCIAL, paramsCabeca);
						docx.addText(RECIBO_PAGAMENTO, paramsTitulo);
						docx.addText(valor, paramsValor);
						docx.addBreak("line");
						docx.addText(texto, paramsTexto);
						docx.addText(texto2, paramsTexto);
						docx.addText(LOCAL_E_DATA, paramsTexto);
						docx.addBreak("line");
						docx.addText(LINHA, paramsLinhaAssinatura);
						docx.addText(RAZAO_SOCIAL, paramsTexto4);
						docx.addBreak("line");
						docx.addText(LINHA, paramsLinhaAssinatura);
						docx.addText(nome[1], paramsAssinaturaBarbeiro);

						docx.createDocx("Recibo " + nome[1] + " "
								+ ConverterDataParaABNTSemBarra(dataInicialIso)
								+ " - "
								+ ConverterDataParaABNTSemBarra(dataFinalIso));

						GenerateReceipt frame = new GenerateReceipt();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione o um barbeiro");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGerarRecibo.setBounds(202, 175, 112, 35);
		contentPane.add(btnGerarRecibo);

		/*
		 * Add a mouse clicked event. When the Voltar Button is clicked, it
		 * returns the the previous window, which is Administrative.
		 */
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Administrative frame = new Administrative();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(10, 175, 112, 35);
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