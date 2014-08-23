package model;

import exception.BarbeiroException;

public class Agenda {

	private String nome;
	private String telefone;
	private String descricao;
	private static String tempNome;

	private final String NOME_INVALIDO = "Nome Inválido";
	private final String NOME_BRANCO = "Nome em Branco";
	private final String TELEFONE_INVALIDO = "Telefone Inválido";
	private final String TELEFONE_BRANCO = "Telefone em Branco";

	public Agenda() {

	}

	public Agenda(String nome, String telefone, String descricao) {
		this.nome = nome;
		this.telefone = telefone;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setNome(String nome) throws BarbeiroException {
		if ("".equals(nome))
			throw new BarbeiroException(NOME_BRANCO);
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
			this.nome = nome;
		else
			throw new BarbeiroException(NOME_INVALIDO);
	}

	public void setTelefone(String telefone) throws BarbeiroException {
		if ("".equals(telefone))
			throw new BarbeiroException(TELEFONE_BRANCO);
		else if (telefone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
			this.telefone = telefone;
		else
			throw new BarbeiroException(TELEFONE_INVALIDO);
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String getTempNome() {
		return tempNome;
	}

	public static void setTempNome(String tempNome) {
		Agenda.tempNome = tempNome;
	}

}
