/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;

/**
 * Conversor de pessoa jurídica.
 * @author Erico.Junior
 */
public class ConversorPessoaJuridica extends ConversorPessoa<Pessoa, 
		br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Pessoa instanciarEntidade() {
		return new PessoaJuridica();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Pessoa converter(
			br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica entidade,
			Pessoa pessoa, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		Short codigoConstituicao = null;
		Short tipoEmpresa = null;
		
		if(entidade.getFormaConstituicao() != null) {
			codigoConstituicao = entidade.getFormaConstituicao().getCodigo();
		}
		if(entidade.getTipoEmpresa() != null) {
			tipoEmpresa = entidade.getTipoEmpresa().getCodigo();
		}

		// Replicação
		PessoaJuridica pj = (PessoaJuridica) preencherDadosPessoa(pessoa, entidade);
		pj.setValorCapitalSocial(obterValor(entidade.getValorCapitalSocial()));
		pj.setNumeroRegistroOrgaoCompetente(entidade.getNumeroRegistroJuntaComercial());
		pj.setNumeroRegistroAta(entidade.getNumeroRegistroRepresentacao());
		pj.setNumeroArquivamentoAlteracaoContrato(
				entidade.getNumeroUltimaAlteracaoContratoSocial());
		pj.setInscricaoEstadual(entidade.getInscricaoEstadual());
		pj.setCodigoConstituicao(codigoConstituicao);
		pj.setCodigoEsferaAdministrativa(entidade.getCodigoEsferaAdministrativa());
		pj.setDataConstituicao(entidade.getDataConstituicao());
		pj.setDataRegistroOrgaoCompetente(entidade.getDataRegistroJuntaComercial());
		pj.setDataRegistroAlteracaoContrato(entidade.getDataUltimaAlteracaoContratoSocial());
		pj.setDataRegistroAta(entidade.getDataRegistroRepresentacao());
		pj.setTipoEmpresa(tipoEmpresa);
		pj.setResumoContratoSocial(entidade.getContratoSocial());
		return pj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Pessoa, ?> obterDelegate() {
		return obterFabricaDelegates().criarPessoaDelegate();
	}

}
