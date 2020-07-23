/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemPossePK;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemPosseDelegate;

/**
 * Conversor de referência do Cadastro único em referência da replicação.
 * 
 * @author Juan.Damasceno
 */
public class ConversorBemPosse extends ConversorBemAbstract<BemPosse, BemPossePK, 
		br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPosse instanciarEntidade() {
		return new BemPosse();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPosse converter(
			br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse entidade,
			BemPosse bemPosse, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem = entidade.getBem();

		// Replicação
		BemPossePK bemPossePK = obterChave(bemPosse, bem, instituicao);
		bemPosse.setIdPosseDb2(entidade.getId());
		bemPosse.setQuantiadadeArea(obterValor(entidade.getArea()));
		bemPosse.setCodTipoPosseTerra(entidade.getTipoPosseBem().getCodigo());
		bemPosse.setIdSQL(bemPossePK);
		bemPosse.setNumPessoaParceira(obterIdPessoaLegado(entidade.getIdPessoaCompartilhamentoParceiro(), instituicao.getIdInstituicao()));
		return bemPosse;
	}

	private Integer obterIdPessoaLegado(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		TransicaoPessoaDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
		TransicaoPessoa transicao = delegate.obterTransicaoPorPessoaCompartilhamentoInstituicao(idPessoaCompartilhamento, idInstituicao);
		return transicao.getIdPessoaLegado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPosseDelegate obterDelegate() {
		return obterFabricaDelegates().criarBemPosseDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPossePK obterChave(BemPosse bemPosse, 
			br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem,
			Instituicao instituicao) throws BancoobException {

		BemPossePK pk = bemPosse.getBemPossePK();
		
		if(pk == null) {
			Bem bemSQL = obterBem(bem, instituicao);
			Integer seqRegistro = obterDelegate().obterMaxSequencial(bemSQL) + 1;
			
			pk = new BemPossePK();
			pk.setBem(bemSQL);
			pk.setSeqBemPosse(seqRegistro.shortValue());
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
	public BemPosse obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse entidadeCapes) 
			throws BancoobException {

		BemPosse entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		List<BemPosse> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		BemPosse entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = listEntidade.get(0);
		}
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}
	
}