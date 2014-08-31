/**
 * Relatorio
 * This class refers to the profit report and barbers' productivity.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.RelatorioException;

public class Relatorio {

	/* Specifying the attributes of the class Relatorio. */
	private String dataInicial;
	private String dataFinal;
	private String barbeiro;
	private String tipoServico;

	/* Error messages and alerts */
	private final String DATA_FINAL_BRANCO = "Data final em Branco";
	private final String DATA_INICIAL_BRANCO = "Data inicial em Branco";
	private final String BARBEIRO_BRANCO = "Barbeiro em Branco";
	private final String TIPO_SERVICO_BRANCO = "Tipo do Serviço em Branco";
	
	/*
	 *  Constructor method of the class Relatorio. 
	 *  Has messages that alert the users if there are blank fields.
	 * */ 
	public Relatorio(String dataInicial, String dataFinal, String barbeiro,
			String tipoServico) throws RelatorioException {
		
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.barbeiro = barbeiro;
		this.tipoServico = tipoServico;

		if (this.dataInicial == null)
			throw new IllegalArgumentException(DATA_INICIAL_BRANCO);

		if (this.dataFinal == null)
			throw new IllegalArgumentException(DATA_FINAL_BRANCO);

		if (this.barbeiro == null)
			throw new IllegalArgumentException(BARBEIRO_BRANCO);

		if (this.tipoServico == null)
			throw new IllegalArgumentException(TIPO_SERVICO_BRANCO);
	}

	public Relatorio() {
	}

	/* This method gets a starting date. */
	public String getDataInicial() {
		return dataInicial;
	}
	
	/* This method modifies the starting date field to a valid starting date. */
	public void setDataInicial(String dataInicial) throws RelatorioException,
			NullPointerException, ParseException {
		if (dataInicial == null)
			throw new NullPointerException(DATA_INICIAL_BRANCO);
		else if ("".equals(dataInicial))
			throw new AssertionError(DATA_INICIAL_BRANCO);
		else {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataIso = sdf.parse(dataInicial);

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String dataISO = sdf2.format(dataIso);

			this.dataInicial = dataISO;
		}

	}

	/* This method gets a starting date. */ 
	public String getDataFinal() {
		return dataFinal;
	}

	/* method modifies the end date field to a valid end date. */
	public void setDataFinal(String dataFinal) throws RelatorioException, NullPointerException,
			ParseException {
		
		if (dataFinal == null)
			throw new NullPointerException(DATA_FINAL_BRANCO);
		else if ("".equals(dataFinal))
			throw new AssertionError(DATA_FINAL_BRANCO);
		else {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataIso = sdf.parse(dataFinal);

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String dataISO = sdf2.format(dataIso);
			
			this.dataFinal = dataISO;
		}
	}

	/* This method gets a barber. */
	public String getBarbeiro() {
		return barbeiro;
	}


	/*
	 * This method modifies the barber field.
	 * And RelatorioException it ensures that every parameter passed is valid.
	 * */
	public void setBarbeiro(String barbeiro) throws RelatorioException {
		if (barbeiro == null)
			throw new NullPointerException(BARBEIRO_BRANCO);
		else if ("".equals(barbeiro))
			throw new AssertionError(BARBEIRO_BRANCO);
		else
			this.barbeiro = barbeiro;
	}

	/* This method gets a service type. */
	public String getTipoServico() {
		return tipoServico;
	}

	/* method modifies the service type field to a valid service type. */
	public void setTipoServico(String tipoServico) throws RelatorioException {
		if (tipoServico == null)
			throw new NullPointerException(TIPO_SERVICO_BRANCO);
		else if ("".equals(tipoServico))
			throw new AssertionError(TIPO_SERVICO_BRANCO);
		else
			this.tipoServico = tipoServico;
	}
	
	/*This method converts the date to ABNT */
	public String ConverterDataParaABNT(String data) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataISO = sdf.parse(data);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		String databr = sdf2.format(dataISO);
		
		return databr;
	}
	

}
