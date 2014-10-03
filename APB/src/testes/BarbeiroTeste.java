package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import exception.BarberException;
import exception.ServiceException;
import model.AddressBook;
import model.Barber;

import org.junit.Before;
import org.junit.Test;


public class BarbeiroTeste {

	Barber barbeiro;
	
	@Before
	public void setUp() {
		try {
			barbeiro =  new Barber();
			barbeiro.setNome("Alessandro");
			barbeiro.setRg("418757896");
			barbeiro.setTelefone("3389-9085");
			barbeiro.setCpf("02919594150");
			barbeiro.setCadeira("10");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirNomeNuloPassandoPeloSetter() {
		try {
			barbeiro.setNome(null);
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirCPFNuloPassandoPeloSetter() {
		try {
			barbeiro.setCpf(null);
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirRGNuloPassandoPeloSetter() {
		try {
			barbeiro.setRg(null);
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirTelefoneNuloPassandoPeloSetter() {
		try {
			barbeiro.setTelefone(null);
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirCadeiraNuloPassandoPeloSetter() {
		try {
			barbeiro.setCadeira(null);
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComNomeNulo() {
		try {
			new Barber(null, "493.751.185-84", "2258256", "3389-9085", "10");
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComCpfNulo() {
		try {
			new Barber("Alessandro", null, "2258256", "3389-9085", "10");
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComRgNulo() {
		try {
			new Barber("Alessandro", "493.751.185-84", null, "3389-9085", "10");
		} catch (BarberException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComTelefoneNulo() {
		try {
			new Barber("Alessandro", "493.751.185-84", "2258256", null, "10");
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComCadeiraNulo() {
		try {
			new Barber("Alessandro", "493.751.185-84", "2258256", "3389-9085", null);
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = AssertionError.class)
	public void cpfNaoPodePassarQuandoInvalido() {
		try {
			barbeiro.setCpf("000000000");
			fail();
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = AssertionError.class)
	public void rgNaoPodeConterLetras() {
		try {
			barbeiro.setRg("4654654ASD");
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = AssertionError.class)
	public void numeroDaCadeiraNaoPodeSerUmaLetra() {
		try {
			barbeiro.setCadeira("asd");
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = AssertionError.class)
	public void numeroDoTelefoneNaoPodeConterLetras() {
		try {
			barbeiro.setTelefone("65465a4");
		} catch (BarberException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeParaGetterDeNomeDeBarbeiro() {
		assertEquals("Alessandro", barbeiro.getNome());
	}
	
	@Test
	public void testeParaGetterDeCPFDeBarbeiro() {
		assertEquals("02919594150", barbeiro.getCpf());
	}
	
	@Test
	public void testeParaGetterDeRGDeBarbeiro() {
		assertEquals("418757896", barbeiro.getRg());
	}
	
	@Test
	public void testeParaGetterDeTelefoneDeBarbeiro() {
		assertEquals("3389-9085", barbeiro.getTelefone());
	}
	
	@Test
	public void testeParaGetterDeCadeiraDeBarbeiro() {
		assertEquals("10", barbeiro.getCadeira());
	}
	
	@Test
	public void testeParaGetterDeTempNomeDeBarbeiro() {
		assertEquals(null, Barber.getTempNome());
	}
	
	@Test
	public void setDeBarbeiroDeveFuncionar() {
		try {
			barbeiro.setNome("Alessandro");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (BarberException e) {
			e.printStackTrace();
		}
		assertEquals("Alessandro", barbeiro.getNome());
	}
	
	@Test (expected = BarberException.class)
	public void nomeComNumero() throws BarberException {
		barbeiro.setNome("J040");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected =  BarberException.class)
	public void cpfPassadoEmBranco() throws BarberException {
		barbeiro.setCpf("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected =  BarberException.class)
	public void cpfInvalido() throws BarberException {
		barbeiro.setCpf("123.654.456-75");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected =  AssertionError.class)
	public void rgPassadoComLetras() throws BarberException {
		barbeiro.setRg("asasa");
		Assert.fail("Deve lançar uma exceção");
	}
	@Test (expected =  BarberException.class)
	public void rgPassadoEmBrancro() throws BarberException {
		barbeiro.setRg("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected =  BarberException.class)
	public void nomePassadoEmBrancro() throws BarberException {
		barbeiro.setNome("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected =  BarberException.class)
	public void telefonePassadoEmBrancro() throws BarberException {
		barbeiro.setTelefone("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected =  BarberException.class)
	public void cadeiraPassadoEmBrancro() throws BarberException {
		barbeiro.setCadeira("");
		Assert.fail("Deve lançar uma exceção");
	}
	@Test (expected =  AssertionError.class)
	public void cadeiraPassadoComoZero() throws BarberException {
		barbeiro.setCadeira("0");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected = BarberException.class)
	public void cadeiraPassadoComMaisDeDoisDigitos() throws BarberException {
		barbeiro.setCadeira("1000");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado() throws ServiceException {
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo() throws ServiceException {
		Barber.setTempNome(null);
		Assert.fail("Deve lançar uma exceção");
	}
	
	
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco() {
		Barber.setTempNome("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	@Test (expected = AssertionError.class)
	public void tempNomeValido() throws BarberException {
		Barber.setTempNome("João");
		assertEquals("João", AddressBook.getTempNome());
	}
}
