/**
 * Barbeiro
 * This class gets and sets the attributes barbers working in the barber shop.
 */
package model;

import exception.BarberException;

public class Barber {

	/* Specifying the attributes of the class Barbeiro. */
	private String name;
	private String cpf;
	private String idNumber;
	private String phoneNumber;
	private String chair;
	private static String tempName;
	
	/* Error messages and alerts */
	private final String INVALID_NAME = "Nome Inválido";
	private final String BLANK_NAME = "Nome em Branco";
	private final String INVALID_CPF = "CPF Inválido";
	private final String BLANK_CPF = "CPF em Branco";
	private final String BLANK_ID_NUMBER = "RG em Branco";
	private final String INVALID_ID_NUMBER = "RG Inválido";
	private final String INVALID_PHONE_NUMBER = "Telefone Inválido";
	private final String BLANK_PHONE_NUMBER = "Telefone em Branco";
	private final String INVALID_CHAIR = "Cadeira Inválida";
	private final String BLANK_CHAIR = "Campo Cadeira em Branco";

	public Barber() {}

	/*
	 *  Constructor method of the class Barbeiro. 
	 *  Has messages that alert the users if there are blank fields.
	 * */ 
	
	public Barber(String name, String cpf, String idNumber, String phoneNumber,
			String chair) throws BarberException {
		this.name = name;
		this.cpf = cpf;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.chair = chair;

		if (this.name == null)
			throw new IllegalArgumentException(BLANK_NAME);

		if (this.cpf == null)
			throw new IllegalArgumentException(BLANK_CPF);

		if (this.idNumber == null)
			throw new IllegalArgumentException(BLANK_ID_NUMBER);

		if (this.phoneNumber == null)
			throw new IllegalArgumentException(BLANK_PHONE_NUMBER);

		if (this.chair == null)
			throw new IllegalArgumentException(BLANK_CHAIR);
	}

	/* This method gets a name. */
	public String getNome() {
		return name;
	}

	/* This method gets a CPF. */
	public String getCpf() {
		return cpf;
	}

	/* This method gets a RG. */
	public String getRg() {
		return idNumber;
	}

	/* This method gets a phone number. */
	public String getTelefone() {
		return phoneNumber;
	}

	/* This method gets a chair. */
	public String getCadeira() {
		return chair;
	}

	/*
	 * This method modifies the name field.
	 * And BarbeiroException it ensures that every parameter passed is valid.
	 * */
	public void setNome(String name) throws BarberException {
		if (name == null)
			throw new NullPointerException(BLANK_NAME);
		else if ("".equals(name))
			throw new BarberException(BLANK_NAME);
		else if (name.matches("^[[ ]|\\p{L}*]+$"))
			this.name= name;
		else
			throw new BarberException(INVALID_NAME);
	}

	/*
	 * This method modifies the CPF field.
	 * And BarbeiroException it ensures that every parameter passed is valid.
	 */
	public void setCpf(String cpf) throws BarberException {

		try {
			if (cpf == null)
				throw new NullPointerException(BLANK_CPF);
			else if ("".equals(cpf))
				throw new AssertionError(BLANK_CPF);
			else if (cpf.matches("[\\d]{3,3}.[\\d]{3,3}.[\\d]{3,3}-[\\d]{2,2}$"))
				cpf = cpf.split("[\\. | -]")[0] + cpf.split("[\\. | -]")[1]
				+ cpf.split("[\\. | -]")[2] + cpf.split("[\\. | -]")[3];
			if (this.validarCpf(cpf))
				this.cpf = cpf;
			else
				throw new BarberException(INVALID_CPF);
		} catch (AssertionError e) {
			throw new BarberException(INVALID_CPF);
		}
	}

	/*
	 * This method modifies the RG field.
	 * And BarbeiroException it ensures that every parameter passed is valid.
	 */
	public void setRg(String idNumber) throws BarberException {
		if (idNumber == null)
			throw new NullPointerException(BLANK_ID_NUMBER);
		else if ("".equals(idNumber))
			throw new BarberException(BLANK_ID_NUMBER);
		else if (idNumber.matches("^[[ ]|\\p{L}*]+$"))
			throw new AssertionError(INVALID_ID_NUMBER);
		else if (idNumber.matches("^[0-9]*$"))
			this.idNumber = idNumber;
		else
			throw new AssertionError(INVALID_ID_NUMBER);
	}

	
	/* This method modifies the phone number field to a valid phone number. */
	public void setTelefone(String phoneNumber) throws BarberException {
		if (phoneNumber == null)
			throw new NullPointerException(BLANK_PHONE_NUMBER);
		else if ("".equals(phoneNumber))
			throw new BarberException(BLANK_PHONE_NUMBER);
		else if (phoneNumber.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
			this.phoneNumber = phoneNumber;
		else
			throw new AssertionError(INVALID_PHONE_NUMBER);
	}

	public void setCadeira(String chair) throws BarberException {
		if (chair == null)
			throw new NullPointerException(BLANK_CHAIR);
		else if ("".equals(chair))
			throw new BarberException(BLANK_CHAIR);
		else if ("0".equals(chair) || chair.matches("^[[ ]|\\p{L}*]+$"))
			throw new AssertionError(INVALID_CHAIR);
		else if (chair.matches("^[0-9]{0,2}$"))
			this.chair = chair;
		else
			throw new BarberException(INVALID_CHAIR);
	}
	
	/* This method modifies the field name to a temporary variable. */
	public static String getTempNome() {
		return tempName;
	}

	/* This method modifies the temporary name field. */
	public static void setTempNome(String tempName) {
		Barber.tempName = tempName;
	}

	/* This method validates CPF number */
	private boolean validarCpf(String cpf) {
		int d1, d2;
		int digit1, digit2, rest;
		int CPFdigit;
		String result;

		d1 = d2 = digit1 = digit2 = rest = 0;

		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			CPFdigit = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

			d1 = d1 + (11 - nCount) * CPFdigit;
			d2 = d2 + (12 - nCount) * CPFdigit;
		};

		rest = d1 % 11;

		if (rest < 2)
			digit1 = 0;
		else
			digit1 = 11 - rest;

		d2 += 2*digit1;
		rest = (d2 % 11);

		if (rest < 2)
			digit2 = 0;
		else
			digit2 = 11 - rest;

		String check = cpf.substring(cpf.length() - 2, cpf.length());
		result = String.valueOf(digit1) + String.valueOf(digit2);

		return check.equals(result);
	}
}
