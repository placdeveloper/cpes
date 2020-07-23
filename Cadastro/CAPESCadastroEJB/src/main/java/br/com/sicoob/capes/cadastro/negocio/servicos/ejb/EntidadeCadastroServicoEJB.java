package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ItemExcluidoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.EntidadeCadastroServico;
import br.com.sicoob.capes.cadastro.persistencia.dao.EntidadeCadastroDaoIF;
import br.com.sicoob.capes.comum.negocio.annotation.Autorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IdEntidadeAprovavel;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * 
 * Classe que deve ser estendida pelos serviços das entidades que possuem uma data de vigência. 
 * 
 * @author Juan.Damasceno
 *
 * @param <T> entidade que herda de {@link EntidadeCadastroBase} e implenta {@link Vigente}
 */
public abstract class EntidadeCadastroServicoEJB<T extends EntidadeCadastroBase & Vigente> 
	extends CAPESCadastroCrudServicoEJB<T> implements EntidadeCadastroServico<T> {
	
	/**
	 * @see br.com.bancoob.negocio.servicos.BancoobCrudServico#incluir(
	 * br.com.bancoob.negocio.entidades.BancoobEntidade)
	 * @param objeto o objeto a ser incluído.
	 * @throws BancoobException Caso ocorra alguma exceção na inclusão.
	 */
	@Override
	@Autorizar(atualizacao = TipoOperacaoEnum.I, metodoValidacao="validarIncluir")
	public T incluir(T objeto) throws BancoobException {

		validarIncluir(objeto);
		return super.incluir(objeto);
	}

	/**
	 * Efetua a alteração da entidade, caso o registro não esteja pendente de aprovação a 
	 * dataHoraInicio é atualizada. 
	 * @param objeto o objeto a alterado.
	 * @throws BancoobException Caso ocorra alguma exceção na inclusão.
	 */
	@Override
	@Autorizar(atualizacao = TipoOperacaoEnum.A, metodoValidacao="validarAlterar")
	public void alterar(T objeto) throws BancoobException {	
		
		validarAlterar(objeto);
		super.alterar(objeto);
	}

	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#excluir(java.io.Serializable)
	 * @param chave A chave do registro que será excluído.
	 */
	@Override
	@Autorizar(atualizacao = TipoOperacaoEnum.E, metodoValidacao = "validarExcluir")
	public void excluirEntidade(T objeto) throws BancoobException {
		validarExcluir(objeto.getId());
		super.excluirEntidade(objeto);
	}
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#excluir(java.io.Serializable)
	 * @param chave A chave do registro que será excluído.
	 */
	@Override
	@Autorizar(atualizacao = TipoOperacaoEnum.E, metodoValidacao = "validarExcluir")
	public void excluir(@IdEntidadeAprovavel Serializable chave) throws BancoobException {
		validarExcluir(chave);
		try {
			super.excluir(chave);
		} catch (BancoobException e) {
			throw new ItemExcluidoException(e);
		}			
	}

	/**
	 * {@inheritDoc}
	 */
	public void marcarEmAlteracao(T objeto, Integer idInstituicaoAtualizacao) throws BancoobException{
		getDAO().marcarEmAlteracao(objeto, idInstituicaoAtualizacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected abstract EntidadeCadastroDaoIF<T> getDAO();
	
	/**
	 * Método utilizado na validação da exclusão da entidade.
	 * 
	 * @param chave
	 *            O ID do objeto que será excluído.
	 */
	public void validarExcluir(Serializable chave) throws BancoobException {
		
	}
	
	/**
	 * Método utilizado na validação da exclusão da entidade.
	 * 
	 * @param objeto
	 *            O objeto que será excluído.
	 */
	public void validarExcluir(T objeto) throws BancoobException {
		validarExcluir(objeto.getId());
	}

	/**
	 * Método utilizado na validação da inclusão da entidade.
	 * 
	 * @param objeto
	 *            O objeto que será incluído.
	 */
	public abstract void validarIncluir(T objeto) throws BancoobException;
	
	/**
	 * Método utilizado na validação da alteração da entidade.
	 * 
	 * @param objeto
	 *            O objeto que será alterado.
	 */
	public abstract void validarAlterar(T objeto) throws BancoobException;
	
	/**
	 * Verifica se o registro é vigente.
	 * 
	 * @param registro
	 * @return
	 */
	protected boolean isRegistroVigente(Aprovavel registro) {
		return registro != null ? (registro.getDataHoraInicio() != null && registro.getIdInstituicaoAtualizacao() == null) : false;
	}
	
}