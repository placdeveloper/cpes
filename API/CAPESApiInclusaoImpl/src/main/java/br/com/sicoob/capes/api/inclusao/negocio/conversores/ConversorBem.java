package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemImovelDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemMovelDTO;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoNegocioException;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoLocalizacaoBemImovelEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe ConversorBem.
 * 
 * @author bruno.carneiro
 */
public class ConversorBem extends ConversorAbstrato<Bem, BemDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem instanciarEntidade(BemDTO dto) {
		Bem retorno = null;

		if (dto.getCodigoTipoClassificacaoBem() != null) {
			String[] propriedadesAvaliacao = new String[] { 
					"valorCompraVenda", "valorAvaliacao", "dataCompraVenda", "dataAvaliacao", "idPessoaAvaliador", "idInstituicaoAvaliador" };

			if (TipoClassificacaoBemEnum.isImovel(dto.getCodigoTipoClassificacaoBem())) {
				BemImovelDTO bemImovelDTO = (BemImovelDTO) dto;
				boolean imovelAvaliacao = validarAlgumaPropriedadePreenchida(dto, propriedadesAvaliacao);

				if (imovelAvaliacao) {
					boolean urbano = TipoLocalizacaoBemImovelEnum.isUrbano(bemImovelDTO.getCodigoTipoLocalizacaoBem());
					if (urbano) {
						retorno = new BemImovelAvaliacaoUrbano();
					} else {
						retorno = new BemImovelAvaliacaoRural();
					}
				} else {
					retorno = new BemImovel();
				}

			} else {
				boolean bemMovelAvaliacao = validarAlgumaPropriedadePreenchida(dto, propriedadesAvaliacao);
				if (bemMovelAvaliacao) {
					retorno = new BemMovelAvaliacao();
				} else {
					retorno = new BemMovel();
				}
			}
		} else {
			retorno = new Bem();
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemDTO instanciarDTO(Bem entidade) {
		BemDTO retorno = null;
		if (TipoClassificacaoBemEnum.BEM_IMOVEL.getCodigo().equals(entidade.getTipoClassificacaoBem().getCodigo())) {
			retorno = new BemImovelDTO();
		} else if (TipoClassificacaoBemEnum.BEM_MOVEL.getCodigo().equals(entidade.getTipoClassificacaoBem().getCodigo())) {
			retorno = new BemMovelDTO();
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Bem entidade, BemDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoClassificacaoBem tipoClassificacaoBem = new TipoClassificacaoBem();
		tipoClassificacaoBem.setCodigo(dto.getCodigoTipoClassificacaoBem());
		entidade.setTipoClassificacaoBem(tipoClassificacaoBem);

		// Se o bem for do tipo IMÓVEL, preenche as informações.
		if (TipoClassificacaoBemEnum.isImovel(dto.getCodigoTipoClassificacaoBem())) {
			BemImovelDTO bemImovelDTO = (BemImovelDTO) dto;

			if (entidade instanceof BemImovelAvaliacaoUrbano) {
				BemImovelAvaliacaoUrbano bemImovelAvaliacaoUrbano = (BemImovelAvaliacaoUrbano) entidade;

				if (bemImovelDTO.getCodigoTipoImplantacaoBemImovel() != null) {
					TipoImplantacaoBemImovel tipoImplantacaoBemImovel = new TipoImplantacaoBemImovel();
					tipoImplantacaoBemImovel.setCodigo(bemImovelDTO.getCodigoTipoImplantacaoBemImovel());
					bemImovelAvaliacaoUrbano.setTipoImplantacaoBemImovel(tipoImplantacaoBemImovel);
				}

				if (bemImovelDTO.getCodigoTipoEstadoConservacao() != null) {
					TipoEstadoConservacaoBem tipoEstadoConservacao = new TipoEstadoConservacaoBem();
					tipoEstadoConservacao.setCodigo(bemImovelDTO.getCodigoTipoEstadoConservacao());
					bemImovelAvaliacaoUrbano.setTipoEstadoConservacao(tipoEstadoConservacao);
				}

				if (bemImovelDTO.getCodigoTipoPadraoAcabamentoBemImovel() != null) {
					TipoPadraoAcabamentoBemImovel tipoPadraoAcabamentoBemImovel = new TipoPadraoAcabamentoBemImovel();
					tipoPadraoAcabamentoBemImovel.setCodigo(bemImovelDTO.getCodigoTipoPadraoAcabamentoBemImovel());
					bemImovelAvaliacaoUrbano.setTipoPadraoAcabamentoBemImovel(tipoPadraoAcabamentoBemImovel);
				}

				if (bemImovelDTO.getCodigoTipoServicoCondominialBemImovel() != null) {
					TipoServicoCondominialBemImovel tipoServicoCondominialBemImovel = new TipoServicoCondominialBemImovel();
					tipoServicoCondominialBemImovel.setCodigo(bemImovelDTO.getCodigoTipoServicoCondominialBemImovel());
					bemImovelAvaliacaoUrbano.setTipoServicoCondominialBemImovel(tipoServicoCondominialBemImovel);
				}
			}
			
			if (entidade instanceof BemImovelAvaliacao) {
				BemImovelAvaliacao bemImovelAvaliacao = (BemImovelAvaliacao) entidade;

				if (bemImovelDTO.getIdPessoaAvaliador() != null && bemImovelDTO.getIdInstituicaoAvaliador() != null) {
					PessoaCompartilhamento pessoaCompartilhamentoAvaliador = obterPessoaCompartilhamento(bemImovelDTO.getIdPessoaAvaliador(), bemImovelDTO.getIdInstituicaoAvaliador());
					if (pessoaCompartilhamentoAvaliador != null) {
						bemImovelAvaliacao.setIdPessoaCompartilhamentoAvaliador(pessoaCompartilhamentoAvaliador.getId());
					}
				}
			}

			if (entidade instanceof BemImovel) {
				BemImovel bemImovel = (BemImovel) entidade;

				if (bemImovelDTO.getCodigoUnidadeMedida() != null) {
					UnidadeMedida unidadeMedida = new UnidadeMedida();
					unidadeMedida.setCodigo(bemImovelDTO.getCodigoUnidadeMedida());
					bemImovel.setUnidadeMedida(unidadeMedida);
				}

				if (bemImovelDTO.getCodigoTipoLocalizacaoBem() != null) {
					TipoLocalizacaoBem tipoLocalizacaoBem = new TipoLocalizacaoBem();
					tipoLocalizacaoBem.setCodigo(bemImovelDTO.getCodigoTipoLocalizacaoBem());
					bemImovel.setTipoLocalizacaoBem(tipoLocalizacaoBem);
				}

				if (bemImovelDTO.getCodigoTipoBem() != null) {
					TipoBemImovel tipoBem = new TipoBemImovel();
					tipoBem.setCodigo(bemImovelDTO.getCodigoTipoBem());
					bemImovel.setTipoBem(tipoBem);
				}

				if (bemImovelDTO.getCodigoTipoUsoBem() != null) {
					TipoUsoBemImovel tipoUsoBem = new TipoUsoBemImovel();
					tipoUsoBem.setCodigo(bemImovelDTO.getCodigoTipoUsoBem());
					bemImovel.setTipoUsoBem(tipoUsoBem);
				}

				if (bemImovelDTO.getIdPessoaCartorio() != null && bemImovelDTO.getIdInstituicaoPessoaCartorio() != null) {
					PessoaCompartilhamento pessoaCompartilhamentoCartorio = obterPessoaCompartilhamento(bemImovelDTO.getIdPessoaCartorio(), bemImovelDTO.getIdInstituicaoPessoaCartorio());
					if (pessoaCompartilhamentoCartorio != null) {
						bemImovel.setIdPessoaCompartilhamentoCartorio(pessoaCompartilhamentoCartorio.getId());

					}
				}
			}
		}
		
		// Se o bem for do tipo MÓVEL, Preenche as informações.
		else {
			BemMovelDTO bemMovelDTO = (BemMovelDTO) dto;

			if (entidade instanceof BemMovelAvaliacao) {
				BemMovelAvaliacao bemMovelAvaliacao = (BemMovelAvaliacao) entidade;

				if (bemMovelDTO.getCodigoTipoEstadoConservacao() != null) {
					TipoEstadoConservacaoBem tipoEstadoConservacao = new TipoEstadoConservacaoBem();
					tipoEstadoConservacao.setCodigo(bemMovelDTO.getCodigoTipoEstadoConservacao());
					bemMovelAvaliacao.setTipoEstadoConservacao(tipoEstadoConservacao);
				}
				
				if (bemMovelDTO.getIdPessoaAvaliador() != null && bemMovelDTO.getIdInstituicaoAvaliador() != null) {
					PessoaCompartilhamento pessoaCompartilhamentoAvaliador = obterPessoaCompartilhamento(bemMovelDTO.getIdPessoaAvaliador(), bemMovelDTO.getIdInstituicaoAvaliador());
					if (pessoaCompartilhamentoAvaliador != null) {
						bemMovelAvaliacao.setIdPessoaCompartilhamentoAvaliador(pessoaCompartilhamentoAvaliador.getId());
					}
				}
			}

			if (entidade instanceof BemMovel) {
				BemMovel bemMovel = (BemMovel) entidade;
				
				if (bemMovelDTO.getCodigoTipoBem() != null) {
					TipoBemMovel tipoBemMovel = new TipoBemMovel();
					tipoBemMovel.setCodigo(bemMovelDTO.getCodigoTipoBem());
					bemMovel.setTipoBem(tipoBemMovel);
				}

				if (bemMovelDTO.getCodigoTipoChassi() != null) {
					TipoChassiBem tipoChassi = new TipoChassiBem();
					tipoChassi.setCodigo(bemMovelDTO.getCodigoTipoChassi());
					bemMovel.setTipoChassi(tipoChassi);
				}

				if (bemMovelDTO.getCodigoTipoCorAutomovel() != null) {
					TipoCorAutomovelBem tipoCorAutomovel = new TipoCorAutomovelBem();
					tipoCorAutomovel.setCodigo(bemMovelDTO.getCodigoTipoCorAutomovel());
					bemMovel.setTipoCorAutomovel(tipoCorAutomovel);
				}
			}
		}

		// Obtém a pessoa
		PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao());
		getLogger().debug("Pessoa compartilhamento encontrada para preenchimento do bem: ", String.valueOf(pessoaCompartilhamento != null));
		
		if (pessoaCompartilhamento != null) {
			BemPessoaCompartilhamento bpc = new BemPessoaCompartilhamento();
			bpc.setPessoaCompartilhamento(pessoaCompartilhamento);
			bpc.setIdPessoaCompartilhamento(pessoaCompartilhamento.getId());
			bpc.setPessoaResponsavel(Boolean.TRUE);
			bpc.setPercentualProprietario(new BigDecimal(100));
			bpc.setBem(entidade);

			List<BemPessoaCompartilhamento> proprietarios = new ArrayList<BemPessoaCompartilhamento>();
			proprietarios.add(bpc);
			entidade.setProprietarios(proprietarios);
		} else {
			throw new CAPESApiInclusaoNegocioException("A pessoa informada no BEM não foi encontrada.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(BemDTO dto, Bem entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
	}

	/**
	 * Verifica se pelo menos uma propriedade foi preenchida.
	 * 
	 * @param dto
	 * @param propriedades
	 * @return
	 */
	private boolean validarAlgumaPropriedadePreenchida(BemDTO dto, String... propriedades) {
		for (String propriedade : propriedades) {
			Object valorAtributo = ReflexaoUtils.getValorAtributo(dto, propriedade);
			if (valorAtributo != null) {
				return true;
			}
		}
		return false;
	}

}