package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaFisicaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaJuridicaDTO;
import br.com.sicoob.capes.cadastro.negocio.dominio.pessoa.FabricaPessoaCompartilhamento;
import br.com.sicoob.capes.comum.negocio.enums.TipoPerfilCadastroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * A Classe ConversorPessoa.
 * 
 * @author bruno.carneiro
 */
public class ConversorPessoa extends ConversorAbstrato<PessoaCompartilhamento, PessoaDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaCompartilhamento instanciarEntidade(PessoaDTO dto) {
		FabricaPessoaCompartilhamento fabricaPessoaCompartilhamento = FabricaPessoaCompartilhamento.obterFabricaPessoaCompartilhamento(dto.getCodigoTipoPessoa());
		return fabricaPessoaCompartilhamento.criarPessoaCompartilhamento();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaDTO instanciarDTO(PessoaCompartilhamento entidade) {
		PessoaDTO retorno = null;

		if (TipoPessoaEnum.isPessoaFisica(entidade.getPessoa().getTipoPessoa().getCodTipoPessoa())) {
			retorno = new PessoaFisicaDTO();
		} else {
			retorno = new PessoaJuridicaDTO();
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(PessoaCompartilhamento entidade, PessoaDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao());
		if (pessoaCompartilhamento != null) {
			entidade.setId(pessoaCompartilhamento.getId());
		}

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setId(dto.getCodigoTipoPessoa());

		Pessoa pessoa = new Pessoa();
		pessoa.setCpfCnpj(dto.getCpfCnpj());
		pessoa.setIdPessoa(dto.getIdPessoa());
		pessoa.setTipoPessoa(tipoPessoa);
		entidade.setPessoa(pessoa);

		entidade.setNomePessoa(dto.getNomePessoa());
		entidade.setNomeApelido(dto.getNomeApelido());
		entidade.setNomeCompleto(dto.getNomeCompleto());
		
		entidade.setDataInclusaoSFN(criarDateTimeDB(dto.getDataInclusaoSFN()));
		entidade.setDataInclusaoSistema(criarDateTimeDB(dto.getDataInclusaoSistema()));
		entidade.setDataRenovacaoCadastral(criarDateTimeDB(dto.getDataRenovacaoCadastral()));

		Short codigoPerfilCadastro = TipoPerfilCadastroEnum.SIMPLES.getCodigo();
		if (dto.getCodigoPerfilCadastro() != null) {
			codigoPerfilCadastro = dto.getCodigoPerfilCadastro();
		}

		PerfilCadastro perfilCadastro = new PerfilCadastro();
		perfilCadastro.setCodigo(codigoPerfilCadastro);
		entidade.setPerfilCadastro(perfilCadastro);

		AtividadeEconomica atividadeEconomica = new AtividadeEconomica();
		atividadeEconomica.setCodigo(dto.getCodigoAtividadeEconomica());
		entidade.setAtividadeEconomica(atividadeEconomica);

		if (dto.getCodigoCnae() != null) {
			CnaeFiscal cnae = new CnaeFiscal();
			cnae.setCodigo(dto.getCodigoCnae());
			entidade.setCnae(cnae);
		}

		// Preenche os dados de pessoa física.
		if (entidade instanceof PessoaFisica) {
			PessoaFisicaDTO dtoPessoaFisica = (PessoaFisicaDTO) dto;
			PessoaFisica pessoaFisica = (PessoaFisica) entidade;

			pessoaFisica.setDataNascimento(criarDateTimeDB(dtoPessoaFisica.getDataNascimento()));
			pessoaFisica.setDataEmissaoDocumento(criarDateTimeDB(dtoPessoaFisica.getDataEmissaoDocumento()));

			if (dtoPessoaFisica.getIdPessoaConjuge() != null && dtoPessoaFisica.getIdInstituicaoConjuge() != null) {
				pessoaFisica.setConjuge(obterPessoaCompartilhamento(dtoPessoaFisica.getIdPessoaConjuge(), dtoPessoaFisica.getIdInstituicaoConjuge()));
			}

			if (dtoPessoaFisica.getCodigoTipoDocumento() != null) {
				TipoDocumentoIdentificacao tipoDocumento = new TipoDocumentoIdentificacao();
				tipoDocumento.setCodigo(dtoPessoaFisica.getCodigoTipoDocumento());
				pessoaFisica.setTipoDocumento(tipoDocumento);
			}

			if (dtoPessoaFisica.getCodigoOcupacaoProfissional() != null) {
				OcupacaoProfissional ocupacaoProfissional = new OcupacaoProfissional();
				ocupacaoProfissional.setIdOcupacaoProfissional(dtoPessoaFisica.getCodigoOcupacaoProfissional());
				pessoaFisica.setOcupacaoProfissional(ocupacaoProfissional);
			}

			if (dtoPessoaFisica.getCodigoEstadoCivil() != null) {
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setCodigo(dtoPessoaFisica.getCodigoEstadoCivil());
				pessoaFisica.setEstadoCivil(estadoCivil);
			}

			if (dtoPessoaFisica.getCodigoRegimeCasamento() != null) {
				RegimeCasamento regimeCasamento = new RegimeCasamento();
				regimeCasamento.setCodigo(dtoPessoaFisica.getCodigoRegimeCasamento());
				pessoaFisica.setRegimeCasamento(regimeCasamento);
			}

			if (dtoPessoaFisica.getCodigoVinculoEmpregaticio() != null) {
				VinculoEmpregaticio vinculoEmpregaticio = new VinculoEmpregaticio();
				vinculoEmpregaticio.setCodigo(dtoPessoaFisica.getCodigoVinculoEmpregaticio());
				pessoaFisica.setVinculoEmpregaticio(vinculoEmpregaticio);
			}

			if (dtoPessoaFisica.getCodigoNacionalidade() != null) {
				Nacionalidade nacionalidade = new Nacionalidade();
				nacionalidade.setCodigo(dtoPessoaFisica.getCodigoNacionalidade());
				pessoaFisica.setNacionalidade(nacionalidade);
			}

			if (dtoPessoaFisica.getCodigoGrauInstrucao() != null) {
				GrauInstrucao grauInstrucao = new GrauInstrucao();
				grauInstrucao.setCodigo(dtoPessoaFisica.getCodigoGrauInstrucao());
				pessoaFisica.setGrauInstrucao(grauInstrucao);
			}
			
		// Preenche os dados de pessoa jurídica.
		} else if (entidade instanceof PessoaJuridica) {
			PessoaJuridicaDTO dtoPessoaJuridica = (PessoaJuridicaDTO) dto;
			PessoaJuridica pessoaJuridica = (PessoaJuridica) entidade;

			if (dtoPessoaJuridica.getCodigoFormaConstituicao() != null) {
				TipoFormaConstituicao tipoFormaConstituicao = new TipoFormaConstituicao();
				tipoFormaConstituicao.setCodigo(dtoPessoaJuridica.getCodigoFormaConstituicao());
				pessoaJuridica.setFormaConstituicao(tipoFormaConstituicao);
			}

			pessoaJuridica.setDataConstituicao(criarDateTimeDB(dtoPessoaJuridica.getDataConstituicao()));
			pessoaJuridica.setDataRegistroJuntaComercial(criarDateTimeDB(dtoPessoaJuridica.getDataRegistroJuntaComercial()));
			pessoaJuridica.setDataUltimaAlteracaoContratoSocial(criarDateTimeDB(dtoPessoaJuridica.getDataUltimaAlteracaoContratoSocial()));
			pessoaJuridica.setDataRegistroRepresentacao(criarDateTimeDB(dtoPessoaJuridica.getDataRegistroRepresentacao()));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(PessoaDTO dto, PessoaCompartilhamento entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
		dto.setIdPessoa(entidade.getPessoa().getId());
	}

}