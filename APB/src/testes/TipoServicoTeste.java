package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import model.ServiceType;

import org.junit.Before;
import org.junit.Test;

import exception.ServiceException;


public class TipoServicoTeste {
	
	ServiceType  servico =  new ServiceType();
	
	@Before
	public void setUp(){
		try {
			servico.setNomeTipoServico("Corte");
			servico.setPreco("14,50");
		} catch (ServiceException e) {
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
	public void setterDePrecoNaoPodeSerNulo() throws ServiceException {
		servico.setPreco(null);
		Assert.fail("Deve lançar exceção");
	}

	@Test (expected = NullPointerException.class)
	public void setterDeNomeNaoPodeSerNulo() throws ServiceException {
		servico.setNomeTipoServico(null);
		Assert.fail("Deve lançar exceção");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setterDePrecoNaoPodeSerInvalido() throws ServiceException {
		servico.setPreco("14.50%");
		Assert.fail("Deve lançar exceção");
	}

	@Test (expected =  ServiceException.class)
	public void setterDePrecoServicoNaoPodeSerEmBranco() throws ServiceException {
		servico.setPreco("");
		Assert.fail("Deve lançar exceção");
	}

	@Test (expected =  ServiceException.class)
	public void setterDeNomeServicoNaoPodeSerEmBranco() throws ServiceException {
		servico.setNomeTipoServico("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado() throws ServiceException {
		assertEquals("Corte", ServiceType.getTempNome());
	}
	
	@Test (expected = NullPointerException.class)
	public void setterDeTempNomeNaoPodeSerNulo() throws ServiceException {
		ServiceType.setTempNome(null);
		Assert.fail("Deve lançar exceção");
	}
	
	
	@Test (expected = ServiceException.class)
	public void setterDeTempNomeNaoPodeSerEmBranco() throws ServiceException {
		ServiceType.setTempNome("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test
	public void tempNomeValido() {
		try {
			ServiceType.setTempNome("Barba");
		} catch (ServiceException e) {
			e.printStackTrace();
			Assert.fail("Não Deve lançar exceção");
		}
		assertEquals("Barba", ServiceType.getTempNome());
	}

}
