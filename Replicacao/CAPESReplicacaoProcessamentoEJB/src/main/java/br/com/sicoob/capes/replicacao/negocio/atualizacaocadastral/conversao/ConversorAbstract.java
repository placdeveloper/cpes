/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * @author Erico.Junior
 * 
 */
public abstract class ConversorAbstract<R extends EntidadeReplicavel<?>, 
	E extends CAPESEntidade<?> & Replicavel> extends Conversor<R,E> {
	
	/** O atributo fabrica. */
	private transient CAPESReplicacaoComumFabricaDelegates fabrica = 
			CAPESReplicacaoComumFabricaDelegates.getInstance();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public R obterEntidadeReplicacao(AtualizacaoCadastralDTO<E> dto)
			throws BancoobException {

		Character tipoOperacao = dto.getTipoOperacao();
		Instituicao instituicao = dto.getInstituicao();		
		Integer idPessoaLegado = dto.getIdPessoaLegado();
		E entidadeCadastro = dto.getEntidadeCadastro();
		R replicavel = null;
		
		if (TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO.getTipoOperacao().equals(tipoOperacao)) {
			replicavel = obterEntidadeParaAlteracao(instituicao, idPessoaLegado, entidadeCadastro);
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_EXCLUSAO.getTipoOperacao().equals(tipoOperacao)) {
			replicavel = obterEntidadeParaExclusao(instituicao, idPessoaLegado, entidadeCadastro);
		} else if (TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO.getTipoOperacao().equals(tipoOperacao)) {
			replicavel = obterEntidadeParaInclusao(instituicao, idPessoaLegado, entidadeCadastro);
		}
		
		return replicavel;	
	}	
	
	
	/**
	 * Obter entidade para inclusao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public R obterEntidadeParaInclusao(Instituicao instituicao, Integer numPessoa, E entidadeCapes) 
			throws BancoobException {
		R entidade = instanciarEntidade();
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}

	/**
	 * Obter entidade para alteracao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public R obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, E entidadeCapes) 
			throws BancoobException {

		R entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		R entidade = obterDelegate().obterPorIdDB2(entidadeConsulta, instituicao.getIdInstituicao());
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}

	/**
	 * Obter entidade para exclusao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public R obterEntidadeParaExclusao(Instituicao instituicao, Integer numPessoa, E entidadeCapes) 
			throws BancoobException {
		
		R entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		return obterDelegate().obterPorIdDB2(entidadeConsulta, instituicao.getIdInstituicao());
	}

	/**
	 * Instanciar entidade.
	 *
	 * @return R
	 */
	protected abstract R instanciarEntidade();

	/**
	 * Converter.
	 *
	 * @param entidadeCapes o valor de entidade capes
	 * @param novaEntidade o valor de nova entidade
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected abstract R converter(E entidadeCapes, R novaEntidade,
			Instituicao instituicao, Integer numPessoa) throws BancoobException;

	/**
	 * Obter delegate.
	 *
	 * @return EntidadeReplicavelDelegate
	 */
	protected abstract EntidadeReplicavelDelegate<R, ?> obterDelegate();

	/**
	 * Obter fabrica delegates.
	 *
	 * @return CAPESReplicacaoComumFabricaDelegates
	 */
	protected CAPESReplicacaoComumFabricaDelegates obterFabricaDelegates() {
		return fabrica;
	}
}
