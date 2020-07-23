/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.builder;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;

/**
 * @author erico.junior
 * 
 */
public class PessoaJuridicaBuilder extends PessoaBuilder<PessoaJuridica, 
		br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void atribuirInformacoesEspecificas(PessoaJuridica pessoaLegado,
			br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica pessoa) throws BancoobException {

		pessoaLegado.setDataConstituicao(pessoa.getDataConstituicao());
		pessoaLegado.setDataRegistroAlteracaoContrato(pessoa.getDataUltimaAlteracaoContratoSocial());
		pessoaLegado.setDataRegistroAta(pessoa.getDataRegistroRepresentacao());
		pessoaLegado.setDataRegistroOrgaoCompetente(pessoa.getDataRegistroJuntaComercial());
		
		pessoaLegado.setInscricaoEstadual(pessoa.getInscricaoEstadual());
		pessoaLegado.setNumeroArquivamentoAlteracaoContrato(
				pessoa.getNumeroUltimaAlteracaoContratoSocial());
		pessoaLegado.setNumeroRegistroAta(pessoa.getNumeroRegistroRepresentacao());
		pessoaLegado.setNumeroRegistroOrgaoCompetente(pessoa.getNumeroRegistroJuntaComercial());
		pessoaLegado.setResumoContratoSocial(pessoa.getContratoSocial());
		pessoaLegado.setValorCapitalSocial(pessoa.getValorCapitalSocial());
		
		if(pessoa.getFormaConstituicao() != null) {
			pessoaLegado.setCodigoConstituicao(pessoa.getFormaConstituicao().getCodigo());
		}
		if(pessoa.getTipoEmpresa() != null) {
			pessoaLegado.setTipoEmpresa(pessoa.getTipoEmpresa().getCodigo());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaJuridica instanciarPessoa() {
		return new PessoaJuridica();
	}
}
