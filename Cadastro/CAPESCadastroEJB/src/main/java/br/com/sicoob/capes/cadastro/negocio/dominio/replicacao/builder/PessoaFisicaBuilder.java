/**
 *
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.builder;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.NacionalidadeDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;

/**
 * @author erico.junior
 *
 */
public class PessoaFisicaBuilder extends PessoaBuilder
	<PessoaFisica, br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void atribuirInformacoesEspecificas(PessoaFisica pessoaLegado,
			br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica pessoa) throws BancoobException {

		pessoaLegado.setNomePai(pessoa.getNomePai());
		pessoaLegado.setNomeMae(pessoa.getNomeMae());
		pessoaLegado.setDataNascimento(pessoa.getDataNascimento());
		pessoaLegado.setNaturalidade(obterNomeLocalidade(pessoa.getIdNaturalidade()));
		pessoaLegado.setNumeroRG(pessoa.getNumeroDocumento());
		pessoaLegado.setOrgaoExpedidor(pessoa.getOrgaoExpedidorDocumento());
		pessoaLegado.setUfOrgaoExpedidor(pessoa.getUfOrgaoExpedidorDocumento());
		pessoaLegado.setDataEmissaoRG(pessoa.getDataEmissaoDocumento());
		pessoaLegado.setQuantidadeDependentes(pessoa.getQuantidadeDependentes());
		pessoaLegado.setMantemVinculoEstavel(pessoa.getUniaoEstavel());
		pessoaLegado.setEmancipado(pessoa.getMenorEmancipado());
		pessoaLegado.setTipoSexo(obterTipoSexo(pessoa.getTipoSexo()));

		if(pessoa.getNacionalidade() != null) {
			pessoaLegado.setCodigoNacionalidade(pessoa.getNacionalidade().getCodigo());
			pessoaLegado.setNacionalidade(obterNacionalidade(pessoa.getNacionalidade()));
		}
		if(pessoa.getOcupacaoProfissional() != null) {
			pessoaLegado.setCodigoProfissao(pessoa.getOcupacaoProfissional().getId());
		}

		if(pessoa.getEstadoCivil() != null) {
			pessoaLegado.setEstadoCivil(pessoa.getEstadoCivil().getCodigo());
		}
		if(pessoa.getRegimeCasamento() != null) {
			pessoaLegado.setRegimeCasamento(pessoa.getRegimeCasamento().getCodigo());
		}
		if(pessoa.getGrauInstrucao() != null) {
			pessoaLegado.setGrauInstrucao(pessoa.getGrauInstrucao().getCodigo());
		}
		if(pessoa.getVinculoEmpregaticio() != null) {
			pessoaLegado.setVinculoEmpregaticio(pessoa.getVinculoEmpregaticio().getCodigo());
		}
		if(pessoa.getTipoDocumento() != null) {
			pessoaLegado.setTipoDocumento(pessoa.getTipoDocumento().getCodigo());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaFisica instanciarPessoa() {
		return new PessoaFisica();
	}

	/**
	 * Obter tipo sexo.
	 *
	 * @param sexo o valor de sexo
	 * @return Short
	 */
	private Short obterTipoSexo(Character sexo) {

		Short tipoSexo = null;
		if(TipoSexoEnum.FEMININO.getCodigo().equals(sexo)){
			tipoSexo = Short.valueOf("0");
		} else if(TipoSexoEnum.MASCULINO.getCodigo().equals(sexo)){
			tipoSexo = Short.valueOf("1");
		}
		return tipoSexo;
	}

	/**
	 * Recupera o nome da localidade a partir do identificador informado.
	 *
	 * @param idLocalidade
	 *            O identificador da localidade.
	 * @return o nome da localidade a partir do identificador informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na consulta.
	 */
	private String obterNomeLocalidade(Integer idLocalidade) throws BancoobException {

		String nomeLocalidade = null;
		if (idLocalidade != null) {
			LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance()
					.criarLOCIntegracaoDelegate();
			LOCIntegracaoLocalidadeVO localidade = locDelegate.obterLocalidade(idLocalidade);
			if (localidade != null) {
				nomeLocalidade = localidade.getNomeLimpo();
			}
		}
		return nomeLocalidade;
	}

	/**
	 * Obter nacionalidade.
	 *
	 * @param nacionalidade o valor de nacionalidade
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private String obterNacionalidade(Nacionalidade nacionalidade) throws BancoobException {

		String nomeNacionalidade = null;

		if(nacionalidade != null) {

			Short codigoNacionalidade = nacionalidade.getCodigo();
			nomeNacionalidade = nacionalidade.getDescricao();

			if(nomeNacionalidade == null || StringUtils.isEmpty(nomeNacionalidade)) {

				NacionalidadeDelegate delegate =
						CAPESCadastroFabricaDelegates.getInstance().criarNacionalidadeDelegate();
				Nacionalidade encontrada = delegate.obter(codigoNacionalidade);

				if(encontrada != null) {
					nomeNacionalidade = encontrada.getDescricao();
				}
			}
		}

		return nomeNacionalidade;
	}

}
