/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.NacionalidadeDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.NacionalidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * Conversor para as pessoas físicas.
 * @author Erico.Junior
 */
public class ConversorPessoaFisica extends ConversorPessoa<Pessoa, 
		br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica> {

	/** A constante TAMANHO_NACIONALIDADE. */
	private static final int TAMANHO_NACIONALIDADE = 20;

	/**
	 * Recupera o código do tipo de sexo 
	 * @param sexo A representação do tipo de sexo no cadastro único.
	 * @return o código do tipo de sexo 
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
	 * Obter nacionalidade.
	 *
	 * @param codigoNacionalidade o valor de codigo nacionalidade
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private String obterNacionalidade(Short codigoNacionalidade) throws BancoobException {
		
		String nomeNacionalidade = null;

		NacionalidadeDelegate delegate = 
				CAPESCadastroFabricaDelegates.getInstance().criarNacionalidadeDelegate();
		Nacionalidade nacionalidade = delegate.obter(codigoNacionalidade);
		
		if(nacionalidade != null) {
			nomeNacionalidade = nacionalidade.getDescricao();
			if(nomeNacionalidade != null && nomeNacionalidade.length() > TAMANHO_NACIONALIDADE) {
				nomeNacionalidade = nomeNacionalidade.substring(0, TAMANHO_NACIONALIDADE);
			}
		}
		
		return nomeNacionalidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Pessoa instanciarEntidade() {
		return new PessoaFisica();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Pessoa converter(br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica entidade,
			Pessoa pessoa, Instituicao instituicao, Integer numPessoa) throws BancoobException {
		
		Short tipoDocumento = null;
		Integer codigoProfissao = null;
		Short estadoCivil = null;
		Short regimeCasamento = null;
		Short grauInstrucao = null;
		Short vinculoEmpregaticio = null;
		Short codigoNacionalidade = null;
		String descNacionalidade = null;
		
		if(entidade.getTipoDocumento() != null) {
			tipoDocumento = entidade.getTipoDocumento().getCodigo();
		}
		if(entidade.getOcupacaoProfissional() != null) {
			codigoProfissao = entidade.getOcupacaoProfissional().getId();
		}
		if(entidade.getEstadoCivil() != null) {
			estadoCivil = entidade.getEstadoCivil().getCodigo();
		}
		if(entidade.getRegimeCasamento() != null) {
			regimeCasamento = entidade.getRegimeCasamento().getCodigo();
		}
		if(entidade.getGrauInstrucao() != null) {
			grauInstrucao = entidade.getGrauInstrucao().getCodigo();
		}
		if(entidade.getVinculoEmpregaticio() != null) {
			vinculoEmpregaticio = entidade.getVinculoEmpregaticio().getCodigo();
		}
		if(entidade.getNacionalidade() != null) {
			codigoNacionalidade = entidade.getNacionalidade().getCodigo();
			descNacionalidade = obterNacionalidade(codigoNacionalidade);
		}
		
		String descNaturalidade = null;
		if(codigoNacionalidade != null){
			if (NacionalidadeEnum.isBrasileira(codigoNacionalidade) && entidade.getIdNaturalidade() != null) {
				LOCIntegracaoDelegate localidadeDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
				descNaturalidade = localidadeDelegate.obterNomeLocalidade(entidade.getIdNaturalidade());
			} else {
				descNaturalidade = entidade.getDescNaturalidade();
			}
		}
		
		// Replicação
		PessoaFisica pessoaFisica = (PessoaFisica) preencherDadosPessoa(pessoa, entidade);
		if(entidade.getConjuge() != null) {
			pessoaFisica.setNomeConjuge(entidade.getConjuge().getNomeCompleto());
			pessoaFisica.setCpfConjuge(entidade.getConjuge().getPessoa().getCpfCnpj());
		}
		
		pessoaFisica.setProfissaoAlterada(houveAlteracaoProfissao(pessoaFisica, codigoProfissao)); // antes de alterar
		pessoaFisica.setDataNascimento(entidade.getDataNascimento());
		pessoaFisica.setEmancipado(entidade.getMenorEmancipado());
		pessoaFisica.setNomePai(entidade.getNomePai());
		pessoaFisica.setNomeMae(entidade.getNomeMae());
		pessoaFisica.setNumeroRG(entidade.getNumeroDocumento());
		pessoaFisica.setOrgaoExpedidor(entidade.getOrgaoExpedidorDocumento());
		pessoaFisica.setUfOrgaoExpedidor(entidade.getUfOrgaoExpedidorDocumento());
		pessoaFisica.setDataEmissaoRG(entidade.getDataEmissaoDocumento());
		pessoaFisica.setTipoSexo(obterTipoSexo(entidade.getTipoSexo()));
		pessoaFisica.setQuantidadeDependentes(entidade.getQuantidadeDependentes());
		pessoaFisica.setMantemVinculoEstavel(entidade.getUniaoEstavel());
		pessoaFisica.setNaturalidade(descNaturalidade);
		pessoaFisica.setTipoDocumento(tipoDocumento);
		pessoaFisica.setCodigoProfissao(codigoProfissao);
		pessoaFisica.setEstadoCivil(estadoCivil);
		pessoaFisica.setRegimeCasamento(regimeCasamento);
		pessoaFisica.setGrauInstrucao(grauInstrucao);
		pessoaFisica.setVinculoEmpregaticio(vinculoEmpregaticio);
		pessoaFisica.setCodigoNacionalidade(codigoNacionalidade);
		pessoaFisica.setNacionalidade(descNacionalidade);
		return pessoaFisica;
	}

	/**
	 * Houve alteracao profissao.
	 *
	 * @param pessoaFisica o valor de pessoa fisica
	 * @param codigoProfissaoNovo o valor de codigo profissao novo
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean houveAlteracaoProfissao(PessoaFisica pessoaFisica, Integer codigoProfissaoNovo){
		
		Integer codProfissao = pessoaFisica.getCodigoProfissao();
		
		if(codProfissao == null) {
			codProfissao = 0;
		}
		
		if(codigoProfissaoNovo == null) {
			codigoProfissaoNovo = 0;
		}

		return !codProfissao.equals(codigoProfissaoNovo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Pessoa, ?> obterDelegate() {
		return obterFabricaDelegates().criarPessoaDelegate();
	}
}
