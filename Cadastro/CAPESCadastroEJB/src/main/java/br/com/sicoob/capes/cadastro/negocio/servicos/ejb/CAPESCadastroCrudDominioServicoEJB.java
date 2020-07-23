/* 
 * Sicoob
 * RelatorioDominioServicoEJB.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ValorForaLimiteException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudDominioServico;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDominioDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.excecao.EntidadeExistenteException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * <p>
 * Implementa��o padr�o do contrato de servi�os CRUD para dom�nios de todo o sistema
 * CadastroUnicoClientesComum.
 * </p>
 * <p>
 * Acrescenta funcionalidades para gera��o de relat�rios que listam todos os registros de uma
 * determinada entidade
 * </p>
 * 
 * 06/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 * @param <T>
 *            Classe da hier�rquia de {@link CAPESEntidade}
 */
public abstract class CAPESCadastroCrudDominioServicoEJB<T extends CAPESEntidade<Short>>
		extends CAPESCadastroCrudServicoEJB<T> implements
		CAPESCadastroCrudDominioServico<T> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short pesquisarProximoCodigo() throws BancoobException {
		CAPESCadastroCrudDominioDaoIF<T> dao = 
			(CAPESCadastroCrudDominioDaoIF<T>) getDAO();
		return dao.pesquisarProximoCodigo();
	}

	/**
	 * O m�todo Validar incluir.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void validarIncluir(T objeto) throws BancoobException {
		Short codigo = objeto.getId();
		if ((codigo.compareTo((short) 0) <= 0) || (codigo.compareTo(Short.MAX_VALUE) >= 0)) {
			throw new ValorForaLimiteException("C�digo", "1", "32766");
		}

		validarCodigoJaUtilizado(objeto.getId());
	}

	/**
	 * Verifica se o c�digo j� foi utilizado.
	 * 
	 * @throws BancoobException
	 */
	protected void validarCodigoJaUtilizado(Serializable codigo) throws BancoobException {
		T entidade = getDAO().obter(codigo);
		if (entidade != null) {
			throw new CAPESCadastroNegocioException("O c�digo informado j� existe.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T incluir(T objeto) throws BancoobException {
		
		validarIncluir(objeto);
		
		try {
			return super.incluir(objeto);
		} catch (EntidadeExistenteException e) {
			throw new NegocioException("MN119", e);
		}
	}
	
	/**
	 * O m�todo Validar alterar.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void validarAlterar(T objeto) throws BancoobException {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(T objeto) throws BancoobException {
		validarAlterar(objeto);
		super.alterar(objeto);
	}
	
}