/*
 * SICOOB
 * 
 * AtualizacaoCadastroBeneficiarioHandler.java(br.com.sicoob.capes.processamento.negocio.dominio.inss.cor.AtualizacaoCadastroBeneficiarioHandler)
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.cor;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.enums.EstadoCivilEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;
import br.com.sicoob.capes.comum.util.IdadeUtil;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;
import br.com.sicoob.capes.processamento.util.BeneficiarioUtil;

/**
 * Atualiza o cadastro do beneficiário, caso ele exista e esteja com os dados incompletos.
 * 
 * @author erico.junior
 */
public class AtualizacaoCadastroBeneficiarioHandler extends BeneficiarioINSSAbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaFisica processarRequisicao(RequisicaoBeneficiarioDTO requisicao) throws BancoobException {

		PessoaFisica pessoaExistente = requisicao.getPessoa();
		PessoaFisica pessoaINSS = BeneficiarioUtil.obterPessoaFisica(requisicao.getBeneficiario(),
				requisicao.getDataProduto());

		boolean deveAtualizar = atualizarDados(pessoaExistente, pessoaINSS);
		if (deveAtualizar) {
			getDelegate().alterarBeneficiarioINSS(pessoaExistente);
		}
		if (getProximo() != null) {
			pessoaExistente = getProximo().processarRequisicao(requisicao);
		}
		return pessoaExistente;
	}

	/**
	 * Atualizar dados.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarDados(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		deveAtualizar = atualizarDataNascimento(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarNacionalidade(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarNaturalidade(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarTipoDocumento(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarNumeroDocumento(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarOrgaoExpedidorDocumento(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarUfOrgaoExpedidorDocumento(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarDataEmissaoDocumento(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarNomePai(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarVinculoEmpregaticio(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarGrauInstrucao(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarSexo(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarOcupacaoProfissional(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarEstadoCivil(pessoaExistente, pessoaINSS);
		deveAtualizar = atualizarMenoEmancipado(pessoaExistente);
		return deveAtualizar;
	}

	/**
	 * Atualizar tipo documento.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarTipoDocumento(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		TipoDocumentoIdentificacao tipoDocumento = pessoaExistente.getTipoDocumento();
		if (tipoDocumento == null || tipoDocumento.getCodigo() != null) {
			pessoaExistente.setTipoDocumento(pessoaINSS.getTipoDocumento());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar numero documento.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarNumeroDocumento(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (StringUtils.isBlank(pessoaExistente.getNumeroDocumento())) {
			pessoaExistente.setNumeroDocumento(pessoaINSS.getNumeroDocumento());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar orgao expedidor documento.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarOrgaoExpedidorDocumento(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (StringUtils.isBlank(pessoaExistente.getOrgaoExpedidorDocumento())) {
			pessoaExistente.setOrgaoExpedidorDocumento(pessoaINSS.getOrgaoExpedidorDocumento());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar uf orgao expedidor documento.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarUfOrgaoExpedidorDocumento(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (StringUtils.isBlank(pessoaExistente.getUfOrgaoExpedidorDocumento())) {
			pessoaExistente.setUfOrgaoExpedidorDocumento(pessoaINSS.getUfOrgaoExpedidorDocumento());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar data emissao documento.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarDataEmissaoDocumento(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (pessoaExistente.getDataEmissaoDocumento() == null) {
			pessoaExistente.setDataEmissaoDocumento(pessoaINSS.getDataEmissaoDocumento());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar nome pai.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarNomePai(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (StringUtils.isBlank(pessoaExistente.getNomePai())) {
			pessoaExistente.setNomePai(pessoaINSS.getNomePai());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar vinculo empregaticio.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarVinculoEmpregaticio(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (pessoaExistente.getVinculoEmpregaticio() == null) {
			pessoaExistente.setVinculoEmpregaticio(pessoaINSS.getVinculoEmpregaticio());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar grau instrucao.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarGrauInstrucao(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (pessoaExistente.getGrauInstrucao() == null) {
			pessoaExistente.setGrauInstrucao(pessoaINSS.getGrauInstrucao());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar sexo.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarSexo(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		Character sexo = pessoaExistente.getTipoSexo();
		if (!TipoSexoEnum.MASCULINO.getCodigo().equals(sexo) && !TipoSexoEnum.FEMININO.getCodigo().equals(sexo)) {
			pessoaExistente.setTipoSexo(pessoaINSS.getTipoSexo());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Conforme solicitação em 31/08/2012
	 * 
	 * @param pessoaExistente
	 * @param pessoaINSS
	 * @return
	 */
	private boolean atualizarOcupacaoProfissional(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (pessoaExistente.getOcupacaoProfissional() == null) {
			pessoaExistente.setOcupacaoProfissional(pessoaINSS.getOcupacaoProfissional());
			deveAtualizar = true;
		}
		if (Boolean.FALSE.equals(pessoaExistente.getOcupacaoProfissional().getAtivo())) {
			pessoaExistente.setOcupacaoProfissional(pessoaINSS.getOcupacaoProfissional());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar estado civil.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarEstadoCivil(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if (pessoaExistente.getEstadoCivil() == null) {
			pessoaExistente.setEstadoCivil(pessoaINSS.getEstadoCivil());
			deveAtualizar = true;
		}
		if (isCasadoOuUniaoEstavel(pessoaExistente) && pessoaExistente.getRegimeCasamento() == null) {
			pessoaExistente.setEstadoCivil(pessoaINSS.getEstadoCivil());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Conforme solicitação em 19/09/2012
	 * 
	 * @param pessoaExistente
	 * @return {@code true} se deve atualizar
	 */
	private boolean atualizarMenoEmancipado(PessoaFisica pessoaExistente) {

		boolean deveAtualizar = false;
		if (IdadeUtil.isMenorIdade(pessoaExistente.getDataNascimento())) {
			pessoaExistente.setMenorEmancipado(true);
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar data nascimento.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarDataNascimento(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;

		if (pessoaExistente.getDataNascimento() == null) {
			pessoaExistente.setDataNascimento(pessoaINSS.getDataNascimento());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar nacionalidade.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarNacionalidade(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		Nacionalidade nacionalidade = pessoaExistente.getNacionalidade();
		if (nacionalidade == null || nacionalidade.getCodigo() == null) {
			pessoaExistente.setNacionalidade(pessoaINSS.getNacionalidade());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Atualizar naturalidade.
	 * 
	 * @param pessoaExistente
	 *            the pessoa existente
	 * @param pessoaINSS
	 *            the pessoa inss
	 * @return true, em caso de sucesso
	 */
	private boolean atualizarNaturalidade(PessoaFisica pessoaExistente, PessoaFisica pessoaINSS) {

		boolean deveAtualizar = false;
		if ((pessoaExistente.getIdNaturalidade() == null || pessoaExistente.getIdNaturalidade() == 0)) {
			pessoaExistente.setIdNaturalidade(pessoaINSS.getIdNaturalidade());
			deveAtualizar = true;
		}
		return deveAtualizar;
	}

	/**
	 * Verifica se é casado ou uniao estavel.
	 * 
	 * @param pessoa
	 *            the pessoa
	 * @return true, se for casado ou uniao estavel
	 */
	private boolean isCasadoOuUniaoEstavel(PessoaFisica pessoa) {

		EstadoCivil estadoCivil = pessoa.getEstadoCivil();
		return estadoCivil != null && EstadoCivilEnum.CASADO.getCodigo().equals(estadoCivil.getCodigo())
				|| EstadoCivilEnum.UNIAO_ESTAVEL.getCodigo().equals(estadoCivil.getCodigo());
	}

}