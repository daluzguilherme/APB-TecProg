package model;

import exception.ServicoException;

public class TipoServico {

	private String nomeTipoServico;
	private String preco;
	private static String tempNome;

	private final static String NOME_BRANCO = "Nome do Serviço em Branco";
	private final String PRECO_INVALIDO = "Preço Inválido";
	private final String PRECO_BRANCO = "Preço em Branco";

	
	public TipoServico(){
	}
	
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	public String getPreco() {
		return preco;
	}
	
	public static String getTempNome() {
		return tempNome;
	}

	public void setNomeTipoServico(String nomeTipoServico) throws ServicoException {
		if (nomeTipoServico == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(nomeTipoServico))
			throw new ServicoException(NOME_BRANCO);
		else
			this.nomeTipoServico = nomeTipoServico;
	}

	public void setPreco(String preco) throws ServicoException {
		if (preco == null)
			throw new NullPointerException(PRECO_INVALIDO);
		else if ("".equals(preco))
			throw new ServicoException(PRECO_BRANCO);
		else if (preco.matches("[\\d]{1,3},[\\d]{1,2}"))
			this.preco = preco;
		else
			throw new IllegalArgumentException("Preço deve ser no formato: **,** ");
	}

	public static void setTempNome(String tempNome) throws ServicoException {
		if (tempNome == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(tempNome))
			throw new ServicoException(NOME_BRANCO);
		else
			TipoServico.tempNome = tempNome;
	}
}
