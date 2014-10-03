/**
 * TipoServico
 * This class refers to the services type on barber shop.
 */
package model;

import exception.ServicoException;

public class ServiceType {

	/* Specifying the attributes of the class TipoServico. */
	private String nomeTipoServico;
	private String preco;
	private static String tempNome;

	/* Error messages and alerts */
	private final static String NOME_BRANCO = "Nome do Servi�o em Branco";
	private final String PRECO_INVALIDO = "Pre�o Inv�lido";
	private final String PRECO_BRANCO = "Pre�o em Branco";

	
	public ServiceType(){
	}
	
	/* This method gets a service type name.*/
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	/* This method gets a price of service provided.*/
	public String getPreco() {
		return preco;
	}
	
	/* This method modifies the field name to a temporary variable. */
	public static String getTempNome() {
		return tempNome;
	}

	/*
	 * This method modifies the service type name field.
	 * And ServicoException it ensures that every parameter passed is valid.
	 * */
	public void setNomeTipoServico(String nomeTipoServico) throws ServicoException {
		if (nomeTipoServico == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(nomeTipoServico))
			throw new ServicoException(NOME_BRANCO);
		else
			this.nomeTipoServico = nomeTipoServico;
	}

	/* This method modifies the price field to a valid price.*/
	public void setPreco(String preco) throws ServicoException {
		if (preco == null)
			throw new NullPointerException(PRECO_INVALIDO);
		else if ("".equals(preco))
			throw new ServicoException(PRECO_BRANCO);
		else if (preco.matches("[\\d]{1,3},[\\d]{1,2}"))
			this.preco = preco;
		else
			throw new IllegalArgumentException("Pre�o deve ser no formato: **,** ");
	}

	/* This method modifies the temporary name field to a valid temporary name. */
	public static void setTempNome(String tempNome) throws ServicoException {
		if (tempNome == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(tempNome))
			throw new ServicoException(NOME_BRANCO);
		else
			ServiceType.tempNome = tempNome;
	}
}
