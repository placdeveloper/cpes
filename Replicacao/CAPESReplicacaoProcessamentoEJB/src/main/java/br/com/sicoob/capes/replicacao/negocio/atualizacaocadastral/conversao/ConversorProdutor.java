/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TipoBeneficiarioSicor;
import br.com.sicoob.capes.negocio.entidades.legado.CategoriaProdutor;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Produtor;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * Conversor de Produtor.
 * @author Erico.Junior
 */
public class ConversorProdutor extends
		ConversorAbstract<Produtor, br.com.sicoob.capes.negocio.entidades.vigente.Produtor> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Produtor instanciarEntidade() {
		return new Produtor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Produtor converter(
			br.com.sicoob.capes.negocio.entidades.vigente.Produtor entidadeCapes,
			Produtor produtor, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);

		CategoriaProdutor categoria = new CategoriaProdutor();
		categoria.setIdCategoria(entidadeCapes.getCategoria().getCodigo());
		
		TipoBeneficiarioSicor tipoBeneficiarioSicor = entidadeCapes.getTipoBeneficiarioSicor();

		produtor.setCategoria(categoria);
		produtor.setCodigoInscricao(entidadeCapes.getCodigoInscricao());
		produtor.setNumPessoa(pessoa.getId());
		produtor.setCodigoTipoBeneficiarioSicor(tipoBeneficiarioSicor != null ? tipoBeneficiarioSicor.getCodigo() : null);

		return produtor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Produtor obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, 
			br.com.sicoob.capes.negocio.entidades.vigente.Produtor entidadeCapes) 
			throws BancoobException {

		Produtor entidade = obterDelegate().obter(numPessoa, instituicao.getIdInstituicao());
		
		if(entidade == null) {
			entidade = new Produtor();
		}
		
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Produtor obterEntidadeParaExclusao(Instituicao instituicao, Integer numPessoa,
			br.com.sicoob.capes.negocio.entidades.vigente.Produtor entidadeCapes) 
			throws BancoobException {
		
		Produtor entidade = instanciarEntidade();
		entidade.setIdSQL(numPessoa);
		return entidade;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Produtor, ?> obterDelegate() {
		return obterFabricaDelegates().criarProdutorDelegate();
	}

}
