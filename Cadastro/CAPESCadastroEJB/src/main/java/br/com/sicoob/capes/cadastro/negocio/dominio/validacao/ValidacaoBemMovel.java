package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBemMovelEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TamanhoCampoInvalidoException;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;

/**
 * Classe para valida��o do bem m�vel.
 * 
 * @author bruno.carneiro
 */
public class ValidacaoBemMovel extends ValidacaoBem<BemMovel> {
	
	private static final int TAMANHO_CHASSI = 50;
	private static final int TAMANHO_RENAVAM = 11;
	private static final int TAMANHO_PLACA = 7;
	private static final int TAMANHO_INSCRICAO_EMBARCACAO = 50;
	private static final int TAMANHO_MATRICULA_AERONAVE = 50;
	
	/**
	 * M�todo construtor.
	 */
	public ValidacaoBemMovel() {
		super();
	}
	
	/**
	 * M�todo construtor.
	 */
	public ValidacaoBemMovel(boolean alteracao) {
		super(alteracao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(BemMovel entidade) throws BancoobException {
		if (entidade.getTipoBem() == null || entidade.getTipoBem().getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo bem");
		}

		if (StringUtils.isBlank(entidade.getDescricao())) {
			throw new CampoNaoInformadoException("Descri��o");
		}

		if (!entidade.getValorNaoInformado() && entidade.getValor() == null) {
			throw new CampoNaoInformadoException("Valor");
		}

		if (isBemAvancado(entidade)) {
			TipoBemMovelEnum tipoBemMovelEnum = TipoBemMovelEnum.obterPorCodigo(entidade.getTipoBem().getCodigo());

			if (TipoBemMovelEnum.AERONAVE.equals(tipoBemMovelEnum)) {
				if (StringUtils.isBlank(entidade.getMatriculaAeronave())) {
					throw new CampoNaoInformadoException("Matr�cula aeronave");
				}

				if (entidade.getAnoConstrucaoAeronave() == null) {
					throw new CampoNaoInformadoException("Ano constru��o aeronave");
				}
			}

			if (TipoBemMovelEnum.EMBARCACAO.equals(tipoBemMovelEnum)) {
				if (StringUtils.isBlank(entidade.getInscricaoEmbarcacao())) {
					throw new CampoNaoInformadoException("Inscri��o embarca��o");
				}

				if (entidade.getAnoConstrucaoEmbarcacao() == null) {
					throw new CampoNaoInformadoException("Ano constru��o embarca��o");
				}
			}

			if (TipoBemMovelEnum.VEICULO.equals(tipoBemMovelEnum)) {
				if (entidade.getTipoChassi() == null || entidade.getTipoChassi().getCodigo() == null) {
					throw new CampoNaoInformadoException("Tipo chassi");
				}

				if (StringUtils.isBlank(entidade.getNumeroChassi())) {
					throw new CampoNaoInformadoException("N�mero chassi");
				}

				if (entidade.getAnoFabricacaoAutomovel() == null) {
					throw new CampoNaoInformadoException("Ano fabrica��o");
				}

				if (entidade.getAnoModeloAutomovel() == null) {
					throw new CampoNaoInformadoException("Ano modelo");
				}
				
				// Se algum dos campos estiver preenchido, os demais n�o poder�o estar vazios.
				if (StringUtils.isNotBlank(entidade.getRenavam()) || StringUtils.isNotBlank(entidade.getPlaca()) || StringUtils.isNotBlank(entidade.getUf())) {
					if (StringUtils.isBlank(entidade.getRenavam())) {
						throw new CampoNaoInformadoException("Renavam");
					}

					if (StringUtils.isBlank(entidade.getPlaca())) {
						throw new CampoNaoInformadoException("Placa");
					}

					if (StringUtils.isBlank(entidade.getUf())) {
						throw new CampoNaoInformadoException("UF");
					}
				}
				
//				if (entidade.getTipoCorAutomovel() == null || entidade.getTipoCorAutomovel().getCodigo() == null) {
//					throw new CampoNaoInformadoException("Cor");
//				}
			}
		}

		if (entidade instanceof BemMovelAvaliacao) {
			BemMovelAvaliacao bemMovelAvaliacao = (BemMovelAvaliacao) entidade;
			
			if (TipoBemMovelEnum.VEICULO.getCodigo().equals(entidade.getTipoBem().getCodigo())) {
				if(bemMovelAvaliacao.getTipoEstadoConservacao() == null || bemMovelAvaliacao.getTipoEstadoConservacao().getCodigo() == null) {
					throw new CampoNaoInformadoException("Estado de conserva��o");
				}
			}

			if (bemMovelAvaliacao.getIdPessoaCompartilhamentoAvaliador() == null) {
				throw new CampoNaoInformadoException("Avaliador");
			}

			if (bemMovelAvaliacao.getDataAvaliacao() == null) {
				throw new CampoNaoInformadoException("Data avalia��o");
			}

			if (bemMovelAvaliacao.getValorAvaliacao() == null) {
				throw new CampoNaoInformadoException("Valor avalia��o");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(BemMovel entidade) throws BancoobException {
		validarTamanhoCampos(entidade);
		if (isBemAvancado(entidade)) {
			verificarChassiVeiculo(entidade);
			verificarInscricaoEmbarcacao(entidade);
			verificarMatriculaAeronave(entidade);
		}
	}

	/**
	 * Faz a valida��o do tamanho dos campos.
	 * 
	 * @param entidade
	 */
	private void validarTamanhoCampos(BemMovel entidade) throws BancoobException {
		if (entidade.getDescricao() != null && entidade.getDescricao().length() > TAMANHO_DESCRICAO) {
			throw new TamanhoCampoInvalidoException("Denomina��o", TAMANHO_DESCRICAO);
		}
		
		if (entidade.getNumeroChassi() != null && entidade.getNumeroChassi().length() > TAMANHO_CHASSI) {
			throw new TamanhoCampoInvalidoException("Chassi", TAMANHO_CHASSI);
		}
		
		if (entidade.getRenavam() != null && entidade.getRenavam().length() > TAMANHO_RENAVAM) {
			throw new TamanhoCampoInvalidoException("Renavam", TAMANHO_RENAVAM);
		}
		
		if (entidade.getPlaca() != null && entidade.getPlaca().length() > TAMANHO_PLACA) {
			throw new TamanhoCampoInvalidoException("Placa", TAMANHO_PLACA);
		}
		
		if (entidade.getInscricaoEmbarcacao() != null && entidade.getInscricaoEmbarcacao().length() > TAMANHO_INSCRICAO_EMBARCACAO) {
			throw new TamanhoCampoInvalidoException("Inscri��o embarca��o", TAMANHO_INSCRICAO_EMBARCACAO);
		}
		
		if (entidade.getMatriculaAeronave() != null && entidade.getMatriculaAeronave().length() > TAMANHO_MATRICULA_AERONAVE) {
			throw new TamanhoCampoInvalidoException("Matr�cula aeronave", TAMANHO_MATRICULA_AERONAVE);
		}
	}

	/**
	 * Verifica se o chassi j� foi cadastrado.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void verificarChassiVeiculo(BemMovel entidade) throws BancoobException {
		if (entidade != null && TipoBemMovelEnum.VEICULO.getCodigo().equals(entidade.getTipoBem().getCodigo())) {

			// Cria os filtros para a consulta.
			BemMovel filtro = new BemMovel();
			filtro.setNumeroChassi(entidade.getNumeroChassi());

			// Realiza a valida��o
			verificarExistenciaBemValidacao(entidade, criarCriterios(filtro));
		}
	}

	/**
	 * Verifica se a inscri��o da embarca��o informada j� foi cadastrada.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void verificarInscricaoEmbarcacao(BemMovel entidade) throws BancoobException {
		if (entidade != null && TipoBemMovelEnum.EMBARCACAO.getCodigo().equals(entidade.getTipoBem().getCodigo())) {

			// Cria os filtros para a consulta.
			BemMovel filtro = new BemMovel();
			filtro.setInscricaoEmbarcacao(entidade.getInscricaoEmbarcacao());

			// Realiza a valida��o
			verificarExistenciaBemValidacao(entidade, criarCriterios(filtro));
		}
	}

	/**
	 * Verifica se a matr�cula da aeronave j� foi cadastrada.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void verificarMatriculaAeronave(BemMovel entidade) throws BancoobException {
		if (entidade != null && TipoBemMovelEnum.AERONAVE.getCodigo().equals(entidade.getTipoBem().getCodigo())) {

			// Cria os filtros para a consulta.
			BemMovel filtro = new BemMovel();
			filtro.setMatriculaAeronave(entidade.getMatriculaAeronave());

			// Realiza a valida��o
			verificarExistenciaBemValidacao(entidade, criarCriterios(filtro));
		}
	}

	/**
	 * Configura a classe ConsultaDto pra ser usada na consulta.
	 * 
	 * @param filtro
	 * @return
	 */
	private ConsultaDto<Bem> criarCriterios(BemMovel filtro) {
		ConsultaDto<Bem> criterios = new ConsultaDto<Bem>();
		criterios.setFiltro(filtro);
		criterios.setTipoProcura("VIGENTES");
		return criterios;
	}

	/**
	 * Verifica o retorno da consulta a partir dos crit�rios, validando a
	 * exist�ncia de um bem do tipo pesquisado.
	 * 
	 * @param entidade
	 * @param criterios
	 * @throws BancoobException
	 */
	private void verificarExistenciaBemValidacao(BemMovel entidade, ConsultaDto<Bem> criterios) throws BancoobException {
		// Realiza a consulta.
		BemDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
		ConsultaDto<Bem> retorno = delegate.pesquisar(criterios);

		if (retorno != null) {
			List<Bem> lista = retorno.getResultado();
			
			// Remove a entidade atual da lista, para o caso de ser uma altera��o.
			for (Iterator<Bem> iterator = lista.iterator(); iterator.hasNext();) {
				Bem bem = iterator.next();
			    if (bem.getId().equals(entidade.getId())) {
			        iterator.remove();
			    }
			}

			// Verifica se a lista ainda possui registros.
			if (CollectionUtils.isNotEmpty(lista)) {
				throw new RegistroJaCadastradoException();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isBemAvancado(BemMovel entidade) {
		if (entidade != null) {
			return(!StringUtils.isEmpty(entidade.getNumeroChassi()) || !StringUtils.isEmpty(entidade.getInscricaoEmbarcacao())	|| !StringUtils.isEmpty(entidade.getMatriculaAeronave()));
		}
		return false;
	}

}