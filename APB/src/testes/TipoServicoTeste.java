package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import model.TipoServico;

import org.junit.Before;
import org.junit.Test;

import exception.ServicoException;


public class TipoServicoTeste {
	
	TipoServico  servico =  new TipoServico();
	
	@Before
	public void setUp(){
		try {
			servico.setNomeTipoServico("Corte");
			servico.setPreco("14,50");
		} catch (ServicoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getterDeNomeDeveRetornarValorPassado(){
		assertEquals("Corte", servico.getNomeTipoServico());
	}
	
	@Test
	public void getterDePrecoDeveRetornarValorPassado(){
		assertEquals("14,50", servico.getPreco());
	}

	@Test (expected = NullPointerException.class)
	public void setterDePrecoNaoPodeSerNulo() throws ServicoException {
		servico.setPreco(null);
		Assert.fail("Deve lançar exceção");
	}

	@Test (expected = NullPointerException.class)
	public void setterDeNomeNaoPodeSerNulo() throws ServicoException {
		servico.setNomeTipoServico(null);
		Assert.fail("Deve lançar exceção");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setterDePrecoNaoPodeSerInvalido() throws ServicoException {
		servico.setPreco("14.50%");
		Assert.fail("Deve lançar exceção");
	}

	@Test (expected =  ServicoException.class)
	public void setterDePrecoServicoNaoPodeSerEmBranco() throws ServicoException {
		servico.setPreco("");
		Assert.fail("Deve lançar exceção");
	}

	@Test (expected =  ServicoException.class)
	public void setterDeNomeServicoNaoPodeSerEmBranco() throws ServicoException {
		servico.setNomeTipoServico("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado() throws ServicoException {
		assertEquals("Corte", TipoServico.getTempNome());
	}
	
	@Test (expected = NullPointerException.class)
	public void setterDeTempNomeNaoPodeSerNulo() throws ServicoException {
		TipoServico.setTempNome(null);
		Assert.fail("Deve lançar exceção");
	}
	
	
	@Test (expected = ServicoException.class)
	public void setterDeTempNomeNaoPodeSerEmBranco() throws ServicoException {
		TipoServico.setTempNome("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test
	public void tempNomeValido() {
		try {
			TipoServico.setTempNome("Barba");
		} catch (ServicoException e) {
			e.printStackTrace();
			Assert.fail("Não Deve lançar exceção");
		}
		assertEquals("Barba", TipoServico.getTempNome());
	}

}
