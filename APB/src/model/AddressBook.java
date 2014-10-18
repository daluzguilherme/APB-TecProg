/**
 * Agenda
 * This class gets and sets the attributes of the address book of possible barbers 
 * interested in working in the barber shop.
 */

package model;

import exception.BarberException;

public class AddressBook {

	/*Specifying the attributes of the class Agenda.*/
	private String name;
	private String phoneNumber;
	private String description;
	private static String tempName;

	private final String INVALID_NAME = "Nome Inv�lido";
	private final String BLANK_NAME = "Nome em Branco";
	private final String INVALID_PHONE_NUMBER = "Telefone Inv�lido";
	private final String BLANK_PHONE_NUMBER = "Telefone em Branco";

	public AddressBook() {

	}

	/* Constructor method of the class Agenda.*/
	public AddressBook(String name, String phoneNumber, String description) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.description = description;
	}

	/**
	 * This method gets a name.
	 * @return The content in the name field.
	 */
	public String getNome() {
		return name;
	}

	/**
	 * This method gets a phone number.
	 * @return The content in the phoneNumber field.
	 */
	public String getTelefone() {
		return phoneNumber;
	}

	/**
	 * This method gets a description.
	 * @return The content in the description field.
	 */
	public String getDescricao() {
		return description;
	}

	/**
	 * This method modifies the name field.
	 * @param name A barber name.
	 * @throws BarberException It ensures that every parameter passed is valid.
	 */
	public void setNome(String name) throws BarberException {
		if ("".equals(name))
			throw new BarberException(BLANK_NAME);
		else if (name.matches("^[[ ]|\\p{L}*]+$"))
			this.name = name;
		else
			throw new BarberException(INVALID_NAME);
	}

	/** This method modifies the phone number field to a valid phone number.
	 * @param phoneNumber A barber phone number.
	 * @throws BarberException It ensures that every parameter passed is valid.
	 */
	public void setTelefone(String phoneNumber) throws BarberException {
		if ("".equals(phoneNumber))
			throw new BarberException(BLANK_PHONE_NUMBER);
		else if (phoneNumber.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
			this.phoneNumber = phoneNumber;
		else
			throw new BarberException(INVALID_PHONE_NUMBER);
	}

	/**
	 * This method modifies the description field.
	 * @param description A field reserved for the owner of the barber shop put remarks that he
	 *  thinks is relevant .
	 */
	public void setDescricao(String description) {
		this.description = description;
	}

	/**
	 * This method modifies the field name to a temporary variable.
	 * @return The content in the  temporary name field.
	 */
	public static String getTempNome() {
		return tempName;
	}

	/**
	 * This method modifies the temporary name field.
	 * @param tempName A temporary name. 
	 */
	public static void setTempNome(String tempName) {
		AddressBook.tempName = tempName;
	}

}
