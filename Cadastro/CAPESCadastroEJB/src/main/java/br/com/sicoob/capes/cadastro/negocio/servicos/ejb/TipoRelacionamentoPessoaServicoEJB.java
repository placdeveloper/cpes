/* 
 * Sicoob
 * TipoRelacionamentoPessoaServicoEJB.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoTipoRelacionamentoPessoa;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRelacionamentoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRelacionamentoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoRelacionamentoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(TipoRelacionamentoPessoaServicoLocal.class)
@Remote(TipoRelacionamentoPessoaServicoRemote.class)
public class TipoRelacionamentoPessoaServicoEJB extends
		CAPESCadastroCrudDominioServicoEJB<TipoRelacionamentoPessoa> implements
		TipoRelacionamentoPessoaServicoRemote, TipoRelacionamentoPessoaServicoLocal {

	@Inject
	@Default
	private TipoRelacionamentoPessoaDAO tipoRelacionamentoPessoaDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoRelacionamentoPessoaDAO getDAO() {
		return this.tipoRelacionamentoPessoaDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(TipoRelacionamentoPessoa tipoRelacionamento)
			throws BancoobException {
		
		super.validarIncluir(tipoRelacionamento);
		validarTipoRelacionamento(tipoRelacionamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(TipoRelacionamentoPessoa tipoRelacionamento)
			throws BancoobException {
		
		super.validarAlterar(tipoRelacionamento);
		validarTipoRelacionamento(tipoRelacionamento);
	}
	
	/**
	 * O m�todo Validar tipo relacionamento.
	 *
	 * @param tipoRelacionamento o valor de tipo relacionamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	private void validarTipoRelacionamento(TipoRelacionamentoPessoa tipoRelacionamento) throws BancoobException {

		TipoRelacionamentoPessoa reverso = tipoRelacionamento.getRelacionamentoReverso();
		if (reverso != null) {
			tipoRelacionamento.setRelacionamentoReverso(obter(reverso.getCodigo()));
		}
		new ValidacaoTipoRelacionamentoPessoa().validar(tipoRelacionamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoRelacionamentoPessoa incluir(TipoRelacionamentoPessoa objeto)
			throws BancoobException {
		
		validarIncluir(objeto);
		
		TipoRelacionamentoPessoa reverso = objeto.getRelacionamentoReverso();
		
		// se o reverso n�o � o mesmo
		if ((reverso != null)
				&& (!reverso.getCodigo().equals(objeto.getCodigo()))) {
			alterarReversoSeValido(objeto);
		}
		return super.incluir(objeto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void alterar(TipoRelacionamentoPessoa objeto, TipoRelacionamentoPessoa reversoOriginal)
			throws BancoobException {
		
		validarAlterar(objeto);
		
		TipoRelacionamentoPessoa reverso = objeto.getRelacionamentoReverso();
		
		// se possui reverso
		if (reverso != null) {
			if (reversoOriginal == null) {
				alterarReversoSeValido(objeto);
			} else if (!reverso.getCodigo().equals(reversoOriginal.getCodigo())) {
				reversoOriginal.setRelacionamentoReverso(null);
				alterar(reversoOriginal);
				alterarReversoSeValido(objeto);
			}
		} else {
			
			// se tinha reverso antes desta altera��o e n�o � "o mesmo"
			if ((reversoOriginal != null)
					&& !reversoOriginal.getCodigo().equals(objeto
							.getCodigo())) {
				reversoOriginal.setRelacionamentoReverso(null);
				alterar(reversoOriginal);
			}
		}
		alterar(objeto);
	}

	/**
	 * Realiza a altera��o do reverso, caso ele n�o seja reverso de nenhum outro registro
	 * 
	 * @param objeto
	 *            O objeto que possui o reverso a ser salvo
	 */
	private void alterarReversoSeValido(final TipoRelacionamentoPessoa objeto)
			throws BancoobException {

		TipoRelacionamentoPessoa reverso = objeto.getRelacionamentoReverso();

		// se o reverso n�o estava associado � outro registro
		if (reverso.getRelacionamentoReverso() == null) {
			reverso.setRelacionamentoReverso(objeto);
			alterar(reverso);
		} else {
			throw new NegocioException("MN024");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TipoRelacionamentoPessoa> pesquisarPorTiposPessoa(
			TipoPessoa tipoPessoaRelacionamento, TipoPessoa tipoPessoaRelacionada)
			throws BancoobException {
		
		return getDAO().pesquisarPorTiposPessoa(tipoPessoaRelacionamento, tipoPessoaRelacionada);
	}

	public List<TipoRelacionamentoPessoa> pesquisarTiposRelacionamentosProdutosBancoob()
			throws BancoobException {
		return getDAO().pesquisarTiposRelacionamentosProdutosBancoob();
	}

}
