/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemOnusPK;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemOnusDelegate;

/**
 * Conversor de referência do Cadastro único em referência da replicação.
 * 
 * @author Erico.Junior
 */
public class ConversorBemOnus extends ConversorBemAbstract<BemOnus, BemOnusPK, 
		br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemOnus instanciarEntidade() {
		return new BemOnus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemOnus converter(
			br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus entidade,
			BemOnus bemOnus, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {
		
		 br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem = entidade.getBem();
		
		 // Replicação
		 BemOnusPK pk = obterChave(bemOnus, bem, instituicao);
		 bemOnus.setIdOnusDb2(entidade.getId());
		 bemOnus.setDataPrevistaLiberacao(entidade.getDataPrevistaLiberacao());
		 bemOnus.setDescricao(entidade.getDescricao());
		 bemOnus.setNomeInstituicaoCredora(entidade.getNomeInstituicaoCredora());
		 bemOnus.setNumeroGrau(entidade.getNumeroGrau());
		 bemOnus.setValor(obterValor(entidade.getValor()));
		 bemOnus.setIdSQL(pk);
		 return bemOnus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemOnusDelegate obterDelegate() {
		return obterFabricaDelegates().criarBemOnusDelegate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected BemOnusPK obterChave(BemOnus bemOnus, 
			br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem,
			Instituicao instituicao) throws BancoobException {

		BemOnusPK pk = bemOnus.getBemOnusPK();
		
		if(pk == null) {
			Bem bemSQL = obterBem(bem, instituicao);
			Integer seqRegistro = obterDelegate().obterMaxSequencialPorPessoa(bemSQL) + 1;
			
			pk = new BemOnusPK();
			pk.setBem(bemSQL);
			pk.setSeqBemOnus(seqRegistro.shortValue());
		}
		return pk;
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
	public BemOnus obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus entidadeCapes) 
			throws BancoobException {

		BemOnus entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		List<BemOnus> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		BemOnus entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = listEntidade.get(0);
		}
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}
}