package model;

import exception.BarbeiroException;

public class Barbeiro {

	private String nome;
	private String cpf;
	private String rg;
	private String telefone;
	private String cadeira;
	private static String tempNome;

	private final String NOME_INVALIDO = "Nome Inválido";
	private final String NOME_BRANCO = "Nome em Branco";
	private final String CPF_INVALIDO = "CPF Inválido";
	private final String CPF_BRANCO = "CPF em Branco";
	private final String RG_BRANCO = "RG em Branco";
	private final String RG_INVALIDO = "RG Inválido";
	private final String TELEFONE_INVALIDO = "Telefone Inválido";
	private final String TELEFONE_BRANCO = "Telefone em Branco";
	private final String CADEIRA_INVALIDA = "Cadeira Inválida";
	private final String CADEIRA_BRANCO = "Campo Cadeira em Branco";

	public Barbeiro() {}

	public Barbeiro(String nome, String cpf, String rg, String telefone,
			String cadeira) throws BarbeiroException {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.cadeira = cadeira;

		if (this.nome == null)
			throw new IllegalArgumentException(NOME_BRANCO);

		if (this.cpf == null)
			throw new IllegalArgumentException(CPF_BRANCO);

		if (this.rg == null)
			throw new IllegalArgumentException(RG_BRANCO);

		if (this.telefone == null)
			throw new IllegalArgumentException(TELEFONE_BRANCO);

		if (this.cadeira == null)
			throw new IllegalArgumentException(CADEIRA_BRANCO);
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCadeira() {
		return cadeira;
	}

	public void setNome(String nome) throws BarbeiroException {
		if (nome == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(nome))
			throw new BarbeiroException(NOME_BRANCO);
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
			this.nome= nome;
		else
			throw new BarbeiroException(NOME_INVALIDO);
	}

	public void setCpf(String cpf) throws BarbeiroException {
		// Exemplo CPF válido: 493.751.185-84
		try {
			if (cpf == null)
				throw new NullPointerException(CPF_BRANCO);
			else if ("".equals(cpf))
				throw new AssertionError(CPF_BRANCO);
			else if (cpf.matches("[\\d]{3,3}.[\\d]{3,3}.[\\d]{3,3}-[\\d]{2,2}$"))
				cpf = cpf.split("[\\. | -]")[0] + cpf.split("[\\. | -]")[1]
				+ cpf.split("[\\. | -]")[2] + cpf.split("[\\. | -]")[3];
			if (this.validarCpf(cpf))
				this.cpf = cpf;
			else
				throw new BarbeiroException(CPF_INVALIDO);
		} catch (AssertionError e) {
			throw new BarbeiroException(CPF_INVALIDO);
		}
	}

	public void setRg(String rg) throws BarbeiroException {
		if (rg == null)
			throw new NullPointerException(RG_BRANCO);
		else if ("".equals(rg))
			throw new BarbeiroException(RG_BRANCO);
		else if (rg.matches("^[[ ]|\\p{L}*]+$"))
			throw new AssertionError(RG_INVALIDO);
		else if (rg.matches("^[0-9]*$"))
			this.rg = rg;
		else
			throw new AssertionError(RG_INVALIDO);
	}

	
	public void setTelefone(String telefone) throws BarbeiroException {
		if (telefone == null)
			throw new NullPointerException(TELEFONE_BRANCO);
		else if ("".equals(telefone))
			throw new BarbeiroException(TELEFONE_BRANCO);
		else if (telefone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
			this.telefone = telefone;
		else
			throw new AssertionError(TELEFONE_INVALIDO);
	}

	public void setCadeira(String cadeira) throws BarbeiroException {
		if (cadeira == null)
			throw new NullPointerException(CADEIRA_BRANCO);
		else if ("".equals(cadeira))
			throw new BarbeiroException(CADEIRA_BRANCO);
		else if ("0".equals(cadeira) || cadeira.matches("^[[ ]|\\p{L}*]+$"))
			throw new AssertionError(CADEIRA_INVALIDA);
		else if (cadeira.matches("^[0-9]{0,2}$"))
			this.cadeira = cadeira;
		else
			throw new BarbeiroException(CADEIRA_INVALIDA);
	}
	
	public static String getTempNome() {
		return tempNome;
	}

	public static void setTempNome(String tempNome) {
		Barbeiro.tempNome = tempNome;
	}

	private boolean validarCpf(String cpf) {
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String result;

		d1 = d2 = digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

			d1 = d1 + (11 - nCount) * digitoCPF;
			d2 = d2 + (12 - nCount) * digitoCPF;
		};

		resto = d1 % 11;

		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2*digito1;
		resto = (d2 % 11);

		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		String verific = cpf.substring(cpf.length() - 2, cpf.length());
		result = String.valueOf(digito1) + String.valueOf(digito2);

		return verific.equals(result);
	}
}
