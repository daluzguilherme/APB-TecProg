/**
 * Barbeiro
 * This class gets and sets the attributes barbers working in the barber shop.
 */
package model;

import exception.BarberException;

public class Barber {

	/* Specifying the attributes of the class Barbeiro. */
	private String nome;
	private String cpf;
	private String rg;
	private String telefone;
	private String cadeira;
	private static String tempNome;
	
	/* Error messages and alerts */
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

	public Barber() {}

	/*
	 *  Constructor method of the class Barbeiro. 
	 *  Has messages that alert the users if there are blank fields.
	 * */ 
	public Barber(String nome, String cpf, String rg, String telefone,
			String cadeira) throws BarberException {
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

	/* This method gets a name. */
	public String getNome() {
		return nome;
	}

	/* This method gets a CPF. */
	public String getCpf() {
		return cpf;
	}

	/* This method gets a RG. */
	public String getRg() {
		return rg;
	}

	/* This method gets a phone number. */
	public String getTelefone() {
		return telefone;
	}

	/* This method gets a chair. */
	public String getCadeira() {
		return cadeira;
	}

	/*
	 * This method modifies the name field.
	 * And BarbeiroException it ensures that every parameter passed is valid.
	 * */
	public void setNome(String nome) throws BarberException {
		if (nome == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(nome))
			throw new BarberException(NOME_BRANCO);
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
			this.nome= nome;
		else
			throw new BarberException(NOME_INVALIDO);
	}

	/*
	 * This method modifies the CPF field.
	 * And BarbeiroException it ensures that every parameter passed is valid.
	 */
	public void setCpf(String cpf) throws BarberException {

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
				throw new BarberException(CPF_INVALIDO);
		} catch (AssertionError e) {
			throw new BarberException(CPF_INVALIDO);
		}
	}

	/*
	 * This method modifies the RG field.
	 * And BarbeiroException it ensures that every parameter passed is valid.
	 */
	public void setRg(String rg) throws BarberException {
		if (rg == null)
			throw new NullPointerException(RG_BRANCO);
		else if ("".equals(rg))
			throw new BarberException(RG_BRANCO);
		else if (rg.matches("^[[ ]|\\p{L}*]+$"))
			throw new AssertionError(RG_INVALIDO);
		else if (rg.matches("^[0-9]*$"))
			this.rg = rg;
		else
			throw new AssertionError(RG_INVALIDO);
	}

	
	/* This method modifies the phone number field to a valid phone number. */
	public void setTelefone(String telefone) throws BarberException {
		if (telefone == null)
			throw new NullPointerException(TELEFONE_BRANCO);
		else if ("".equals(telefone))
			throw new BarberException(TELEFONE_BRANCO);
		else if (telefone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
			this.telefone = telefone;
		else
			throw new AssertionError(TELEFONE_INVALIDO);
	}

	public void setCadeira(String cadeira) throws BarberException {
		if (cadeira == null)
			throw new NullPointerException(CADEIRA_BRANCO);
		else if ("".equals(cadeira))
			throw new BarberException(CADEIRA_BRANCO);
		else if ("0".equals(cadeira) || cadeira.matches("^[[ ]|\\p{L}*]+$"))
			throw new AssertionError(CADEIRA_INVALIDA);
		else if (cadeira.matches("^[0-9]{0,2}$"))
			this.cadeira = cadeira;
		else
			throw new BarberException(CADEIRA_INVALIDA);
	}
	
	/* This method modifies the field name to a temporary variable. */
	public static String getTempNome() {
		return tempNome;
	}

	/* This method modifies the temporary name field. */
	public static void setTempNome(String tempNome) {
		Barber.tempNome = tempNome;
	}

	/* This method validates CPF number */
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
