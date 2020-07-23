/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EmpreendimentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.EmpreendimentoInativoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TempoSuperiorCemAnosException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ValorCampoComparacaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ValorForaLimiteException;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;

/**
 * Validação para a produtividade.
 * @author Erico.Junior
 */
public class ValidacaoProdutividade extends ValidacaoEntidadeCadastroAbstract<Produtividade> {

	/** A constante VARIACAO_SAFRA. */
	private static final int VARIACAO_SAFRA = 100;
	
	/** A constante PERCENTUAL_FRUSTRACAO. */
	private static final String PERCENTUAL_FRUSTRACAO = "Percentual de Frustração";
	
	/** A constante EMPREENDIMENTO. */
	private static final String EMPREENDIMENTO = "Empreendimento";
	
	/** A constante RENDA_BRUTA. */
	private static final String RENDA_BRUTA = "Renda Bruta";
	
	/** A constante PERCENTUAL_REBATE. */
	private static final String PERCENTUAL_REBATE = "Percentual de Rebate";
	
	/** A constante PRECO_MEDIO. */
	private static final String PRECO_MEDIO = "Preço médio";
	
	/** A constante AREA_QUANTIDADE. */
	private static final String AREA_QUANTIDADE = "Área / Quantidade";
	
	/** A constante PRODUCAO. */
	private static final String PRODUCAO = "Produção";
	
	/** A constante ANO_INICIO. */
	private static final String ANO_INICIO = "Ano Início";
	
	/** A constante ANO_FIM. */
	private static final String ANO_FIM = "Ano Fim";
	
	/** A constante PRODUTOR. */
	private static final String PRODUTOR = "Produtor";
	
	/** A constante CEM. */
	private static final BigDecimal CEM = BigDecimal.valueOf(100);
	
	/** O atributo empreendimento. */
	private Empreendimento empreendimento;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(Produtividade entidade)
			throws BancoobException {
		
		if(!isValorNaoNuloMaiorQueZero(entidade.getAnoInicioSafra()) 
				|| !isValorNaoNuloMaiorQueZero(entidade.getAnoFimSafra()) ) {
			throw new CampoNaoInformadoException("Ano Safra");
		}
		
		empreendimento = consultarEmpreendimento(entidade);
		if(empreendimento == null || empreendimento.getCodigo() == null) {
			throw new CampoNaoInformadoException(EMPREENDIMENTO);
		}
		
		validarObrigatoriedadeImovel(empreendimento, entidade.getBemImovel());
		if(entidade.getProducao() == null) {
			throw new CampoNaoInformadoException(PRODUCAO);
		}
		
		validarObrigatoriedadeArea(empreendimento, entidade.getQuantidadeOuArea());
		if(entidade.getPercentualRebate() == null) {
			throw new CampoNaoInformadoException(PERCENTUAL_REBATE);
		}
		
		if(entidade.getValorPrecoMedio() == null) {
			throw new CampoNaoInformadoException(PRECO_MEDIO);
		}

		if(entidade.getValorRendaBruta() == null) {
			throw new CampoNaoInformadoException(RENDA_BRUTA);
		}
		
		if(entidade.getProducao() == null){
			throw new CampoNaoInformadoException(PRODUTOR);
		}
	}
	
	/**
	 * Consultar empreendimento.
	 *
	 * @param produtividade o valor de produtividade
	 * @return Empreendimento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Empreendimento consultarEmpreendimento(Produtividade produtividade) throws BancoobException {
		
		Empreendimento empreendimento = null;
		if(produtividade.getEmpreendimento() != null) {
			EmpreendimentoDelegate delegate = 
					CAPESCadastroFabricaDelegates.getInstance().criarEmpreendimentoDelegate();
			empreendimento = delegate.obter(produtividade.getEmpreendimento().getCodigo());
		}
		
		return empreendimento; 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(Produtividade produtividade)
			throws BancoobException {

		ValidacaoCnae validacaoCnae = new ValidacaoCnae();
		validacaoCnae.validar(produtividade.getPessoaCompartilhamento());

		validarAnoSafra(produtividade);
		validarEmpreendimento();
		validarMaiorQueZero(produtividade.getProducao(), PRODUCAO);
		validarPercentualRebate(produtividade);
		validarMaiorQueZero(produtividade.getValorPrecoMedio(), PRECO_MEDIO);
		validarMaiorQueZero(produtividade.getValorRendaBruta(), RENDA_BRUTA);
	}

	/**
	 * O método Validar ano safra.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarAnoSafra(Produtividade entidade) throws BancoobException {

		Calendar calendar = Calendar.getInstance();
		int anoAtual = calendar.get(Calendar.YEAR);
		int anoMinimo = anoAtual - VARIACAO_SAFRA;
		int anoMaximo = anoAtual + VARIACAO_SAFRA;

		Short anoInicioSafra = entidade.getAnoInicioSafra();
		Short anoFimSafra = entidade.getAnoFimSafra();
		
		if(anoInicioSafra >= anoFimSafra) {
			throw new ValorCampoComparacaoException(ANO_FIM, "maior", ANO_INICIO);
		}
		
		if(anoInicioSafra > anoMaximo || anoInicioSafra < anoMinimo) {
			throw new TempoSuperiorCemAnosException(ANO_INICIO);
		}

		if(anoFimSafra > anoMaximo || anoFimSafra < anoMinimo) {
			throw new TempoSuperiorCemAnosException(ANO_FIM);
		}
	}
	
	/**
	 * O método Validar empreendimento.
	 *
	 * @throws EmpreendimentoInativoException lança a exceção EmpreendimentoInativoException
	 */
	private void validarEmpreendimento() throws EmpreendimentoInativoException{
		
		if (empreendimento != null && Boolean.FALSE.equals(empreendimento.getAtivo())) {
			throw new EmpreendimentoInativoException();
		}
	}
	
	/**
	 * O método Validar percentual rebate.
	 *
	 * @param produtividade o valor de produtividade
	 * @throws ValorForaLimiteException lança a exceção ValorForaLimiteException
	 */
	private void validarPercentualRebate(Produtividade produtividade) throws ValorForaLimiteException{ 
		
		BigDecimal percentual = produtividade.getPercentualRebate();
		if(percentual.compareTo(BigDecimal.ZERO) < 0 || percentual.compareTo(CEM) > 0) {
			throw new ValorForaLimiteException(PERCENTUAL_REBATE, "0", "100");
		}
	}	
	
	/**
	 * O método Validar obrigatoriedade imovel.
	 *
	 * @param empreendimento o valor de empreendimento
	 * @param imovel o valor de imovel
	 * @throws CampoNaoInformadoException lança a exceção CampoNaoInformadoException
	 */
	private void validarObrigatoriedadeImovel(Empreendimento empreendimento, BemImovel imovel) 
			throws CampoNaoInformadoException {
		
		if(Boolean.TRUE.equals(empreendimento.getExigeImovel()) 
				&& (imovel == null || imovel.getIdBem() == null)) {
			throw new CampoNaoInformadoException("Imóvel");
		}
	}
	
	/**
	 * O método Validar obrigatoriedade area.
	 *
	 * @param empreendimento o valor de empreendimento
	 * @param qtdOuArea o valor de qtd ou area
	 * @throws CampoNaoInformadoException lança a exceção CampoNaoInformadoException
	 * @throws ValorCampoComparacaoException lança a exceção ValorCampoComparacaoException
	 */
	private void validarObrigatoriedadeArea(Empreendimento empreendimento, BigDecimal qtdOuArea) 
			throws CampoNaoInformadoException, ValorCampoComparacaoException {
		
		if(Boolean.TRUE.equals(empreendimento.getExigeArea())) { 
			if(qtdOuArea == null) {
				throw new CampoNaoInformadoException(AREA_QUANTIDADE);
			} 
			validarMaiorQueZero(qtdOuArea, AREA_QUANTIDADE);
		}
	}
	
	/**
	 * O método Validar maior que zero.
	 *
	 * @param valor o valor de valor
	 * @param nomeCampo o valor de nome campo
	 * @throws ValorCampoComparacaoException lança a exceção ValorCampoComparacaoException
	 */
	private void validarMaiorQueZero(BigDecimal valor, String nomeCampo) throws ValorCampoComparacaoException {
		if(valor != null && valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorCampoComparacaoException(nomeCampo, "maior", "zero");
		}
	}
	
	/**
	 * Verifica se eh valor nao nulo maior que zero.
	 *
	 * @param valor o valor de valor
	 * @return {@code true}, se for valor nao nulo maior que zero
	 */
	private boolean isValorNaoNuloMaiorQueZero(Short valor){
		return valor != null && valor > 0;
	}	

	/**
	 * O método Validar frustracao.
	 *
	 * @param percentual o valor de percentual
	 * @param dataOcorrencia o valor de data ocorrencia
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarFrustracao(BigDecimal percentual, Date dataOcorrencia) throws BancoobException {
		
		if(percentual == null) {
			throw new CampoNaoInformadoException(PERCENTUAL_FRUSTRACAO);
		}
		
		if(dataOcorrencia == null) {
			throw new CampoNaoInformadoException("Data Ocorrência");
		}		
		
		if(percentual.compareTo(BigDecimal.ONE) < 0 || percentual.compareTo(CEM) > 0) {
			throw new ValorForaLimiteException(PERCENTUAL_FRUSTRACAO, "1", "100");
		}
	}
	
	/**
	 * Recupera o valor de empreendimento.
	 *
	 * @return o valor de empreendimento
	 */
	protected Empreendimento getEmpreendimento() {
		return empreendimento;
	}
}
