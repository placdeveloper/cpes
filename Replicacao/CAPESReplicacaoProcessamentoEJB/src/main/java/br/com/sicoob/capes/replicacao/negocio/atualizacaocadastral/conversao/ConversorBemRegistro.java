/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;


import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemRegistroPK;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemRegistroDelegate;

/**
 * Conversor de referência do Cadastro único em referência da replicação.
 * 
 * @author Erico.Junior
 */
public class ConversorBemRegistro extends ConversorBemAbstract<BemRegistro, BemRegistroPK,  
	br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemRegistro instanciarEntidade() {
		return new BemRegistro();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemRegistro converter(
			br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro entidade,
			BemRegistro bemRegistro, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem = entidade.getBem();

		// Replicação
		BemRegistroPK pk = obterChave(bemRegistro, bem, instituicao);
		bemRegistro.setIdRegistroDb2(entidade.getId());
		bemRegistro.setAreaMatricula(obterValor(entidade.getAreaMatricula()));
		bemRegistro.setFolha(entidade.getFolha());
		bemRegistro.setIncra(entidade.getIncra());
		bemRegistro.setIpr(entidade.getIpr());
		bemRegistro.setLivro(entidade.getLivro());
		bemRegistro.setMatriculaRegistro(entidade.getMatriculaRegistro());
		bemRegistro.setObservacaoMatricula(entidade.getObservacaoMatricula());
		bemRegistro.setDataUltimaMatricula(entidade.getDataUltimaMatricula());
		bemRegistro.setIdSQL(pk);
		return bemRegistro;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemRegistroDelegate obterDelegate() {
		return obterFabricaDelegates().criarBemRegistroDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	protected BemRegistroPK obterChave(BemRegistro bemRegistro, 
			br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem,
			Instituicao instituicao) throws BancoobException {

		BemRegistroPK pk = bemRegistro.getBemRegistroPK();
		
		if(pk == null) {
			Bem bemSQL = obterBem(bem, instituicao);
			Integer seqRegistro = obterDelegate().obterMaxSequencial(bemSQL) + 1;
			
			pk = new BemRegistroPK();
			pk.setBem(bemSQL);
			pk.setSeqBemRegistro(seqRegistro.shortValue());
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
	public BemRegistro obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro entidadeCapes) 
			throws BancoobException {

		BemRegistro entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		List<BemRegistro> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		BemRegistro entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = listEntidade.get(0);
		}
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}

}