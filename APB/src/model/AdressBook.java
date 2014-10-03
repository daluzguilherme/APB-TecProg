/**
 * Agenda
 * This class gets and sets the attributes of the address book of possible barbers 
 * interested in working in the barber shop.
 */

package model;

import exception.BarbeiroException;

public class AdressBook {

	/*Specifying the attributes of the class Agenda.*/
	private String nome;
	private String telefone;
	private String descricao;
	private static String tempNome;

	private final String NOME_INVALIDO = "Nome Inv�lido";
	private final String NOME_BRANCO = "Nome em Branco";
	private final String TELEFONE_INVALIDO = "Telefone Inv�lido";
	private final String TELEFONE_BRANCO = "Telefone em Branco";

	public AdressBook() {

	}

	/* Constructor method of the class Agenda.*/
	public AdressBook(String nome, String telefone, String descricao) {
		this.nome = nome;
		this.telefone = telefone;
		this.descricao = descricao;
	}

	/**
	 * This method gets a name.
	 * @return The content in the name field.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * This method gets a phone number.
	 * @return The content in the phoneNumber field.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * This method gets a description.
	 * @return The content in the description field.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * This method modifies the name field.
	 * @param nome A barber name.
	 * @throws BarbeiroException It ensures that every parameter passed is valid.
	 */
	public void setNome(String nome) throws BarbeiroException {
		if ("".equals(nome))
			throw new BarbeiroException(NOME_BRANCO);
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
			this.nome = nome;
		else
			throw new BarbeiroException(NOME_INVALIDO);
	}

	/** This method modifies the phone number field to a valid phone number.
	 * @param telefone A barber phone number.
	 * @throws BarbeiroException It ensures that every parameter passed is valid.
	 */
	public void setTelefone(String telefone) throws BarbeiroException {
		if ("".equals(telefone))
			throw new BarbeiroException(TELEFONE_BRANCO);
		else if (telefone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
			this.telefone = telefone;
		else
			throw new BarbeiroException(TELEFONE_INVALIDO);
	}

	/**
	 * This method modifies the description field.
	 * @param descricao A field reserved for the owner of the barber shop put remarks that he
	 *  thinks is relevant .
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * This method modifies the field name to a temporary variable.
	 * @return The content in the  temporary name field.
	 */
	public static String getTempNome() {
		return tempNome;
	}

	/**
	 * This method modifies the temporary name field.
	 * @param tempNome A temporary name. 
	 */
	public static void setTempNome(String tempNome) {
		AdressBook.tempNome = tempNome;
	}

}
