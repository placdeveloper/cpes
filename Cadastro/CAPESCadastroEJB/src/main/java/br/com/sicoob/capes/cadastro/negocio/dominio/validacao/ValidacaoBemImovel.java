package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ProdutividadeDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.BemImovelProprietaroParticipanteProdutividadeException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataMenorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TamanhoCampoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ValorForaLimiteException;
import br.com.sicoob.capes.comum.negocio.enums.TipoLocalizacaoBemImovelEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Classe para valida��o do bem im�vel.
 * 
 * @author bruno.carneiro
 */
public class ValidacaoBemImovel extends ValidacaoBem<BemImovel> {
	
	private static final int TAMANHO_INFORMACOES_GERAIS = 1000;
	private static final int TAMANHO_DENOMINACAO = 50;
	private static final int TAMANHO_COMPLEMENTO = 25;
	private static final int TAMANHO_NUMERO = 6;
	private static final int TAMANHO_INCRA = 13 ; 
	private static final int TAMANHO_MATRICULA = 6; 
	private static final int TAMANHO_NIRF = 8; 
	private static final int TAMANHO_ROTEIRO = 250; 

	/**
	 * M�todo construtor.
	 */
	public ValidacaoBemImovel() {
		super();
	}
	
	/**
	 * M�todo construtor.
	 */
	public ValidacaoBemImovel(boolean alteracao) {
		super(alteracao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(BemImovel entidade) throws BancoobException {
		if (entidade.getTipoLocalizacaoBem() == null || entidade.getTipoLocalizacaoBem().getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo localiza��o");
		}

		if (entidade.getTipoUsoBem() == null || entidade.getTipoUsoBem().getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo uso bem");
		}

		if (entidade.getTipoBem() == null || entidade.getTipoBem().getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo bem");
		}

		if (StringUtils.isBlank(entidade.getDescricao())) {
			throw new CampoNaoInformadoException("Descri��o");
		}
		
		if (StringUtils.isBlank(entidade.getDenominacao())) {
			throw new CampoNaoInformadoException("Denomina��o");
		}

		if (!entidade.getValorNaoInformado() && entidade.getValor() == null) {
			throw new CampoNaoInformadoException("Valor");
		}

		if (entidade.getAreaTotal() == null) {
			throw new CampoNaoInformadoException("�rea total");
		}

		if (entidade.getUnidadeMedida() == null) {
			throw new CampoNaoInformadoException("Unidade medida");
		}
		
		if (entidade.getIdLocalidade() == null || entidade.getIdLocalidade().equals(NumberUtils.INTEGER_ZERO)) {
			throw new CampoNaoInformadoException("Munic�pio");
		}
		
//		if (StringUtils.isBlank(entidade.getNumero())) {
//			throw new CampoNaoInformadoException("N�mero");
//		}

//		if (entidade.getIdLogradouro() == null) {
//			throw new CampoNaoInformadoException("Endere�o");
//		}
		
		// Verifica se a �rea de posse dos propriet�rios foi informada.
		if(entidade.getProprietarios() != null && entidade.getProprietarios().size() > 0) {
			for(BemPessoaCompartilhamento bpc : entidade.getProprietarios()) {
				if(bpc.getAreaPosse() == null) {
					throw new CampoNaoInformadoException("�rea posse do propriet�rio");
				}
			}
		}
		
		if (entidade.getRelacionamentoPessoas() != null && entidade.getRelacionamentoPessoas().size() > 0) {
			for (BemImovelTipoRelacionamentoPessoa bitrp : entidade.getRelacionamentoPessoas()) {
				if (bitrp.getTipoRelacionamento() == null || bitrp.getTipoRelacionamento().getCodigo() == null) {
					throw new CampoNaoInformadoException("Tipo de relacionamento do parceiro");
				}

				if (bitrp.getAreaPosse() == null) {
					throw new CampoNaoInformadoException("�rea de posse do parceiro");
				}
				
				validarDatasParceiro(bitrp.getDataInicioRelacionamento(), bitrp.getDataFimRelacionamento());
			}
		}

		if (isBemAvancado(entidade)) {
			TipoLocalizacaoBemImovelEnum tipoLocalizacao = TipoLocalizacaoBemImovelEnum.obterPorCodigo(entidade.getTipoLocalizacaoBem().getCodigo());
			
			if (TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao)) {
				if (entidade.getIdPessoaCompartilhamentoCartorio() == null) {
					throw new CampoNaoInformadoException("Cart�rio");
				}

				if (StringUtils.isBlank(entidade.getMatricula())) {
					throw new CampoNaoInformadoException("Matr�cula");
				}
			} else if (TipoLocalizacaoBemImovelEnum.RURAL.equals(tipoLocalizacao)) {
				if (StringUtils.isEmpty(entidade.getNirf())) {
					throw new CampoNaoInformadoException("NIRF");
				}

				if (StringUtils.isEmpty(entidade.getIncra())) {
					throw new CampoNaoInformadoException("INCRA");
				}
				
				if (StringUtils.isEmpty(entidade.getDescricaoRoteiro()) && entidade.getLatitude() == null && entidade.getLongitude() == null) {
					throw new CampoNaoInformadoException("Roteiro de acesso ou localiza��o");
				}

				if ((StringUtils.isEmpty(entidade.getDescricaoRoteiro()) && (entidade.getLatitude() == null || entidade.getLongitude() == null))) {
					throw new CampoNaoInformadoException("Latitude e longitude");
				}
			}
		}

		if (entidade instanceof BemImovelAvaliacao) {
			BemImovelAvaliacao bemImovelAvaliacao = (BemImovelAvaliacao) entidade;

			if (bemImovelAvaliacao.getIdPessoaCompartilhamentoAvaliador() == null) {
				throw new CampoNaoInformadoException("Avaliador");
			}

			if (bemImovelAvaliacao.getDataAvaliacao() == null) {
				throw new CampoNaoInformadoException("Data avalia��o");
			}

			if (bemImovelAvaliacao.getValorAvaliacao() == null) {
				throw new CampoNaoInformadoException("Valor avalia��o");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(BemImovel entidade) throws BancoobException {
		if(alteracao) {
			validarProdutividadesAssociadas(entidade);
		}
		validarTamanhoCampos(entidade);
		validarAreaPosseParticipantes(entidade);
		validarAreaPosseProprietarios(entidade);
		validarAreaPosseProprietariosParticipantes(entidade);
		validarBemComParticipantesSemProprietarios(entidade);
		if (isBemAvancado(entidade)) {
			if (TipoLocalizacaoBemImovelEnum.isUrbano(entidade.getTipoLocalizacaoBem().getCodigo())) {
				validarInclusaoBemUrbano(entidade);
			} else {
				validarInclusaoBemRural(entidade);
			}
		}
	}

	/**
	 * Faz a valida��o do tamanho dos campos.
	 * 
	 * @param entidade
	 */
	private void validarTamanhoCampos(BemImovel entidade) throws BancoobException {
		
		if (entidade.getDescricao() != null && entidade.getDescricao().length() > TAMANHO_DESCRICAO) {
			throw new TamanhoCampoInvalidoException("Descri��o", TAMANHO_DESCRICAO);
		}
		
		if (entidade.getInformacoesGerais() != null && entidade.getInformacoesGerais().length() > TAMANHO_INFORMACOES_GERAIS) {
			throw new TamanhoCampoInvalidoException("Informa��es Gerais", TAMANHO_INFORMACOES_GERAIS);
		}

		if (entidade.getDenominacao() != null && entidade.getDenominacao().length() > TAMANHO_DENOMINACAO) {
			throw new TamanhoCampoInvalidoException("Denomina��o", TAMANHO_DENOMINACAO);
		}

		if (entidade.getNumero() != null && entidade.getNumero().length() > TAMANHO_NUMERO) {
			throw new TamanhoCampoInvalidoException("N�mero", TAMANHO_NUMERO);
		}

		if (entidade.getComplemento() != null && entidade.getComplemento().length() > TAMANHO_COMPLEMENTO) {
			throw new TamanhoCampoInvalidoException("Complemento", TAMANHO_COMPLEMENTO);
		}

		if (entidade.getIncra() != null && entidade.getIncra().length() > TAMANHO_INCRA) {
			throw new TamanhoCampoInvalidoException("INCRA", TAMANHO_INCRA);
		}

		if (entidade.getMatricula() != null && entidade.getMatricula().length() > TAMANHO_MATRICULA) {
			throw new TamanhoCampoInvalidoException("Matr�cula", TAMANHO_MATRICULA);
		}

		if (entidade.getNirf() != null && entidade.getNirf().length() > TAMANHO_NIRF) {
			throw new TamanhoCampoInvalidoException("NIRF", TAMANHO_NIRF);
		}

		if (entidade.getDescricaoRoteiro() != null && entidade.getDescricaoRoteiro().length() > TAMANHO_ROTEIRO) {
			throw new TamanhoCampoInvalidoException("Roteiro de acesso", TAMANHO_ROTEIRO);
		}

		if (entidade.getAreaTotal() != null) {
			if (entidade.getAreaTotal().precision() > 13 || entidade.getAreaTotal().scale() > 4
					|| (entidade.getAreaTotal().compareTo(BigDecimal.valueOf(0.0001)) < 0)
					|| (entidade.getAreaTotal().compareTo(BigDecimal.valueOf(9999999.9999)) > 0)) {
				throw new ValorForaLimiteException("�rea total", "0.0001", "9999999.9999");
			}
		}
	}

	/**
	 * M�todo para verifica��o se um bem do tipo urbano j� foi cadastrado.
	 * (Cart�rio e matr�cula).
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void validarInclusaoBemUrbano(BemImovel entidade) throws BancoobException {
		// Cria os filtros para a consulta.
		BemImovel filtro = new BemImovel();
		filtro.setIdPessoaCompartilhamentoCartorio(entidade.getIdPessoaCompartilhamentoCartorio());
		filtro.setMatricula(entidade.getMatricula());

		verificarExistenciaBemValidacao(entidade, criarCriterios(filtro));
	}

	/**
	 * M�todo para verifica��o se um bem do tipo rural j� foi cadastrado. (NIRF
	 * e Incra).
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void validarInclusaoBemRural(BemImovel entidade) throws BancoobException {
		// Cria os filtros para a consulta.
//		BemImovel filtro = new BemImovel();
//		filtro.setNirf(entidade.getNirf());
//		filtro.setIncra(entidade.getIncra());
		
		BemImovel filtro = new BemImovel();
		filtro.setIdPessoaCompartilhamentoCartorio(entidade.getIdPessoaCompartilhamentoCartorio());
		filtro.setMatricula(entidade.getMatricula());

		verificarExistenciaBemValidacao(entidade, criarCriterios(filtro));
	}

	/**
	 * Configura a classe ConsultaDto pra ser usada na consulta.
	 * 
	 * @param filtro
	 * @return
	 */
	private ConsultaDto<Bem> criarCriterios(Bem filtro) {
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
	private void verificarExistenciaBemValidacao(BemImovel entidade, ConsultaDto<Bem> criterios) throws BancoobException {
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
	 * Realiza a valida��o das datas de relacionamento do parceiro.
	 * 
	 * @param dataInicioRelacionamento
	 * @param dataFimRelacionamento
	 * @throws BancoobException
	 */
	private void validarDatasParceiro(Date dataInicioRelacionamento, Date dataFimRelacionamento) throws BancoobException {
		// A data in�cio � obrigat�ria.
		if (dataInicioRelacionamento == null) {
			throw new CampoNaoInformadoException("Data in�cio relacionamento do parceiro");
		}

		// Se a data fim de relacionamento estiver preenchida, faremos as valida��es.
		if (dataFimRelacionamento != null) {

			// A data fim deve ser no m�nimo um dia a frente da data in�cio.
//			Date dataFutura = DataUtil.incrementarData(dataFimRelacionamento, Calendar.DATE, 1);
			long dias = obterDirefencaEntreDatas(dataInicioRelacionamento, dataFimRelacionamento);
			if (dias < NumberUtils.LONG_ONE) {
				throw new CAPESCadastroNegocioException("A data fim do relacionamento do parceiro deve ter no m�nimo um dia a frente da data de in�cio de relacionamento.");
			}
			
			// A data fim pode ser igual a data corrente, mas n�o pode ser inferior
			if (DataUtil.configurarPrimeiraDataIntervalo(new Date()).after(DataUtil.configurarPrimeiraDataIntervalo(dataFimRelacionamento))) {
				throw new DataMenorException("Data fim de relacionamento", "Data atual");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isBemAvancado(BemImovel entidade) {
//		TipoLocalizacaoBemImovelEnum tipoLocalizacao = TipoLocalizacaoBemImovelEnum.obterPorCodigo(entidade.getTipoLocalizacaoBem().getCodigo());
//		if (TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao)) {
//			return (!StringUtils.isEmpty(entidade.getMatricula()) && entidade.getIdPessoaCompartilhamentoCartorio() != null);
//		} else if (TipoLocalizacaoBemImovelEnum.RURAL.equals(tipoLocalizacao)) {
//			return (!StringUtils.isEmpty(entidade.getNirf()) && !StringUtils.isEmpty(entidade.getIncra()) && !StringUtils.isEmpty(entidade.getDescricaoRoteiro()));
//		}
		return (!StringUtils.isEmpty(entidade.getMatricula()) && entidade.getIdPessoaCompartilhamentoCartorio() != null);
	}
	
	/**
	 * Validar �rea de posse dos propriet�rios do bem im�vel.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	protected void validarAreaPosseProprietarios(BemImovel entidade) throws BancoobException {
		BigDecimal total = BigDecimal.ZERO.setScale(4);
		BigDecimal zero = BigDecimal.ZERO.setScale(4);

		if (CollectionUtils.isNotEmpty(entidade.getProprietarios())) {
			for (BemPessoaCompartilhamento bemPessoaCompartilhamento : entidade.getProprietarios()) {
				if (bemPessoaCompartilhamento.getAreaPosse() != null) {
					total = total.add(bemPessoaCompartilhamento.getAreaPosse());
				}
			}
		}

		if (!total.equals(zero) && total.compareTo(entidade.getAreaTotal()) > 0) {
			throw new CAPESCadastroNegocioException("A �rea de posse dos propriet�rios � maior que a �rea total do bem im�vel");
		}
	}
	
	/**
	 * Valida a �rea de posse dos participantes do bem im�vel.
	 * @param entidade
	 * @throws BancoobException
	 */
	protected void validarAreaPosseParticipantes(BemImovel entidade) throws BancoobException {
		BigDecimal total = BigDecimal.ZERO.setScale(4);
		BigDecimal zero = BigDecimal.ZERO.setScale(4);

		if (CollectionUtils.isNotEmpty(entidade.getRelacionamentoPessoas())) {
			for (BemImovelTipoRelacionamentoPessoa bemImovelTipoRelacionamentoPessoa : entidade.getRelacionamentoPessoas()) {
				if (bemImovelTipoRelacionamentoPessoa.getAreaPosse() != null) {
					total = total.add(bemImovelTipoRelacionamentoPessoa.getAreaPosse());
				}
			}
		}

		if (!total.equals(zero) && total.compareTo(entidade.getAreaTotal()) > 0) {
			throw new CAPESCadastroNegocioException("A �rea de posse dos participantes � maior que a �rea total do bem im�vel.");
		}
	}
	
	/**
	 * Valida se a �rea de posse dos participantes e dos propriet�rios � maior
	 * que a �rea total do im�vel.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	protected void validarAreaPosseProprietariosParticipantes(BemImovel entidade) throws BancoobException {
		BigDecimal total = BigDecimal.ZERO.setScale(4);
		BigDecimal zero = BigDecimal.ZERO.setScale(4);

		if (CollectionUtils.isNotEmpty(entidade.getProprietarios())) {
			for (BemPessoaCompartilhamento bemPessoaCompartilhamento : entidade.getProprietarios()) {
				if (bemPessoaCompartilhamento.getAreaPosse() != null) {
					total = total.add(bemPessoaCompartilhamento.getAreaPosse());
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(entidade.getRelacionamentoPessoas())) {
			for (BemImovelTipoRelacionamentoPessoa bemImovelTipoRelacionamentoPessoa : entidade.getRelacionamentoPessoas()) {
				if (bemImovelTipoRelacionamentoPessoa.getAreaPosse() != null) {
					total = total.add(bemImovelTipoRelacionamentoPessoa.getAreaPosse());
				}
			}
		}

		if (!total.equals(zero) && total.compareTo(entidade.getAreaTotal()) > 0) {
			throw new CAPESCadastroNegocioException("A �rea de posse dos propriet�rios e parceiros � maior que a �rea total do bem im�vel");
		}
	}
	
	/**
	 * Verifica se o bem possui participantes vinculados sem a exist�ncia de
	 * propriet�rios.
	 * 
	 * @param entidade
	 */
	private void validarBemComParticipantesSemProprietarios(BemImovel entidade) throws BancoobException {
		int quantidadeProprietarios = 0;
		if (entidade.getProprietarios() != null && entidade.getProprietarios().size() > 0) {
			for (BemPessoaCompartilhamento bpc : entidade.getProprietarios()) {
				if (bpc.getIdPessoaCompartilhamento() != null && !bpc.getMarcadoExclusao()) {
					quantidadeProprietarios++;
				}
			}
		}

		if (quantidadeProprietarios == 0 && (entidade.getRelacionamentoPessoas() != null && entidade.getRelacionamentoPessoas().size() > 0)) {
			throw new CAPESCadastroNegocioException("N�o � poss�vel excluir todos os propriet�rios, pois existem parceiros vinculados ao mesmo.");
		}
	}
	
	/**
	 * Calcula a diferen�a em dias entre as datas. (podendo ser negativo).
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	private long obterDirefencaEntreDatas(Date dataInicio, Date dataFim) {
		long diferenca = dataFim.getTime() - dataInicio.getTime();
		long dias = diferenca / (1000 * 60 * 60 * 24);
		return dias;
	}
	
	/**
	 * Verifica se alguma pessoa que est� saindo do relacionamento do bem possui
	 * alguma produtividade.
	 * 
	 * @param objeto o bem a ser verificado.
	 * @throws BancoobException 
	 */
	private void validarProdutividadesAssociadas(BemImovel bemImovel) throws BancoobException {
		List<Long> listaRemovidos = new ArrayList<Long>();
		listaRemovidos.addAll(obterListaProprietariosExcluidos(bemImovel));
		listaRemovidos.addAll(obterListaParceirosExcluidos(bemImovel));

		if (!listaRemovidos.isEmpty()) {
			ProdutividadeDelegate produtividadeDelegate = CAPESCadastroFabricaDelegates.getInstance().criarProdutividadeDelegate();
			List<String> produtividades = produtividadeDelegate.listarProdutividadesAssociadas(bemImovel, listaRemovidos);
			if (produtividades != null && !produtividades.isEmpty()) {
				throw new BemImovelProprietaroParticipanteProdutividadeException(StringUtils.join(produtividades.toArray(), ", "));
			}
		}
	}
	
	/**
	 * Recupera os propriet�rios que est�o marcados para a exclus�o.
	 * 
	 * @param objeto
	 * @return
	 */
	private List<Long> obterListaProprietariosExcluidos(BemImovel bemImovel) throws BancoobException {
		List<Long> lista = new ArrayList<Long>();
		for (BemPessoaCompartilhamento proprietario : bemImovel.getProprietarios()) {
			if (proprietario.getMarcadoExclusao()) {
				lista.add(proprietario.getIdPessoaCompartilhamento());
			}
		}
		return lista;
	}
	
	/**
	 * Recupera as pessoas que est�o marcadas para a exclus�o.
	 * 
	 * @param objeto
	 * @return
	 */
	private List<Long> obterListaParceirosExcluidos(BemImovel bemImovel) throws BancoobException {
		Set<BemImovelTipoRelacionamentoPessoa> parceirosAtuais = bemImovel.getRelacionamentoPessoas();
		List<BemImovelTipoRelacionamentoPessoa> parceirosAnteriores = CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate().obterRelacionamentosBemImovel(bemImovel.getId());

		Collection<BemImovelTipoRelacionamentoPessoa> parceirosExcluidos = ReflexaoUtils.subtrairColecao(parceirosAnteriores, parceirosAtuais, "idPessoaCompartilhamento");
		List<Long> lista = new ArrayList<Long>();
		for (BemImovelTipoRelacionamentoPessoa parceiro : parceirosExcluidos) {
			lista.add(parceiro.getIdPessoaCompartilhamento());
		}
		return lista;
	}
	
}