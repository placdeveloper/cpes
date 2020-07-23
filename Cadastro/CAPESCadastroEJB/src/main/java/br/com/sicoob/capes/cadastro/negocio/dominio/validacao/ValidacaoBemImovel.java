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
 * Classe para validação do bem imóvel.
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
	 * Método construtor.
	 */
	public ValidacaoBemImovel() {
		super();
	}
	
	/**
	 * Método construtor.
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
			throw new CampoNaoInformadoException("Tipo localização");
		}

		if (entidade.getTipoUsoBem() == null || entidade.getTipoUsoBem().getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo uso bem");
		}

		if (entidade.getTipoBem() == null || entidade.getTipoBem().getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo bem");
		}

		if (StringUtils.isBlank(entidade.getDescricao())) {
			throw new CampoNaoInformadoException("Descrição");
		}
		
		if (StringUtils.isBlank(entidade.getDenominacao())) {
			throw new CampoNaoInformadoException("Denominação");
		}

		if (!entidade.getValorNaoInformado() && entidade.getValor() == null) {
			throw new CampoNaoInformadoException("Valor");
		}

		if (entidade.getAreaTotal() == null) {
			throw new CampoNaoInformadoException("Área total");
		}

		if (entidade.getUnidadeMedida() == null) {
			throw new CampoNaoInformadoException("Unidade medida");
		}
		
		if (entidade.getIdLocalidade() == null || entidade.getIdLocalidade().equals(NumberUtils.INTEGER_ZERO)) {
			throw new CampoNaoInformadoException("Município");
		}
		
//		if (StringUtils.isBlank(entidade.getNumero())) {
//			throw new CampoNaoInformadoException("Número");
//		}

//		if (entidade.getIdLogradouro() == null) {
//			throw new CampoNaoInformadoException("Endereço");
//		}
		
		// Verifica se a área de posse dos proprietários foi informada.
		if(entidade.getProprietarios() != null && entidade.getProprietarios().size() > 0) {
			for(BemPessoaCompartilhamento bpc : entidade.getProprietarios()) {
				if(bpc.getAreaPosse() == null) {
					throw new CampoNaoInformadoException("Área posse do proprietário");
				}
			}
		}
		
		if (entidade.getRelacionamentoPessoas() != null && entidade.getRelacionamentoPessoas().size() > 0) {
			for (BemImovelTipoRelacionamentoPessoa bitrp : entidade.getRelacionamentoPessoas()) {
				if (bitrp.getTipoRelacionamento() == null || bitrp.getTipoRelacionamento().getCodigo() == null) {
					throw new CampoNaoInformadoException("Tipo de relacionamento do parceiro");
				}

				if (bitrp.getAreaPosse() == null) {
					throw new CampoNaoInformadoException("Área de posse do parceiro");
				}
				
				validarDatasParceiro(bitrp.getDataInicioRelacionamento(), bitrp.getDataFimRelacionamento());
			}
		}

		if (isBemAvancado(entidade)) {
			TipoLocalizacaoBemImovelEnum tipoLocalizacao = TipoLocalizacaoBemImovelEnum.obterPorCodigo(entidade.getTipoLocalizacaoBem().getCodigo());
			
			if (TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao)) {
				if (entidade.getIdPessoaCompartilhamentoCartorio() == null) {
					throw new CampoNaoInformadoException("Cartório");
				}

				if (StringUtils.isBlank(entidade.getMatricula())) {
					throw new CampoNaoInformadoException("Matrícula");
				}
			} else if (TipoLocalizacaoBemImovelEnum.RURAL.equals(tipoLocalizacao)) {
				if (StringUtils.isEmpty(entidade.getNirf())) {
					throw new CampoNaoInformadoException("NIRF");
				}

				if (StringUtils.isEmpty(entidade.getIncra())) {
					throw new CampoNaoInformadoException("INCRA");
				}
				
				if (StringUtils.isEmpty(entidade.getDescricaoRoteiro()) && entidade.getLatitude() == null && entidade.getLongitude() == null) {
					throw new CampoNaoInformadoException("Roteiro de acesso ou localização");
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
				throw new CampoNaoInformadoException("Data avaliação");
			}

			if (bemImovelAvaliacao.getValorAvaliacao() == null) {
				throw new CampoNaoInformadoException("Valor avaliação");
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
	 * Faz a validação do tamanho dos campos.
	 * 
	 * @param entidade
	 */
	private void validarTamanhoCampos(BemImovel entidade) throws BancoobException {
		
		if (entidade.getDescricao() != null && entidade.getDescricao().length() > TAMANHO_DESCRICAO) {
			throw new TamanhoCampoInvalidoException("Descrição", TAMANHO_DESCRICAO);
		}
		
		if (entidade.getInformacoesGerais() != null && entidade.getInformacoesGerais().length() > TAMANHO_INFORMACOES_GERAIS) {
			throw new TamanhoCampoInvalidoException("Informações Gerais", TAMANHO_INFORMACOES_GERAIS);
		}

		if (entidade.getDenominacao() != null && entidade.getDenominacao().length() > TAMANHO_DENOMINACAO) {
			throw new TamanhoCampoInvalidoException("Denominação", TAMANHO_DENOMINACAO);
		}

		if (entidade.getNumero() != null && entidade.getNumero().length() > TAMANHO_NUMERO) {
			throw new TamanhoCampoInvalidoException("Número", TAMANHO_NUMERO);
		}

		if (entidade.getComplemento() != null && entidade.getComplemento().length() > TAMANHO_COMPLEMENTO) {
			throw new TamanhoCampoInvalidoException("Complemento", TAMANHO_COMPLEMENTO);
		}

		if (entidade.getIncra() != null && entidade.getIncra().length() > TAMANHO_INCRA) {
			throw new TamanhoCampoInvalidoException("INCRA", TAMANHO_INCRA);
		}

		if (entidade.getMatricula() != null && entidade.getMatricula().length() > TAMANHO_MATRICULA) {
			throw new TamanhoCampoInvalidoException("Matrícula", TAMANHO_MATRICULA);
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
				throw new ValorForaLimiteException("Área total", "0.0001", "9999999.9999");
			}
		}
	}

	/**
	 * Método para verificação se um bem do tipo urbano já foi cadastrado.
	 * (Cartório e matrícula).
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
	 * Método para verificação se um bem do tipo rural já foi cadastrado. (NIRF
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
	 * Verifica o retorno da consulta a partir dos critérios, validando a
	 * existência de um bem do tipo pesquisado.
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
			// Remove a entidade atual da lista, para o caso de ser uma alteração.
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
	 * Realiza a validação das datas de relacionamento do parceiro.
	 * 
	 * @param dataInicioRelacionamento
	 * @param dataFimRelacionamento
	 * @throws BancoobException
	 */
	private void validarDatasParceiro(Date dataInicioRelacionamento, Date dataFimRelacionamento) throws BancoobException {
		// A data início é obrigatória.
		if (dataInicioRelacionamento == null) {
			throw new CampoNaoInformadoException("Data início relacionamento do parceiro");
		}

		// Se a data fim de relacionamento estiver preenchida, faremos as validações.
		if (dataFimRelacionamento != null) {

			// A data fim deve ser no mínimo um dia a frente da data início.
//			Date dataFutura = DataUtil.incrementarData(dataFimRelacionamento, Calendar.DATE, 1);
			long dias = obterDirefencaEntreDatas(dataInicioRelacionamento, dataFimRelacionamento);
			if (dias < NumberUtils.LONG_ONE) {
				throw new CAPESCadastroNegocioException("A data fim do relacionamento do parceiro deve ter no mínimo um dia a frente da data de início de relacionamento.");
			}
			
			// A data fim pode ser igual a data corrente, mas não pode ser inferior
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
	 * Validar área de posse dos proprietários do bem imóvel.
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
			throw new CAPESCadastroNegocioException("A área de posse dos proprietários é maior que a área total do bem imóvel");
		}
	}
	
	/**
	 * Valida a área de posse dos participantes do bem imóvel.
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
			throw new CAPESCadastroNegocioException("A área de posse dos participantes é maior que a área total do bem imóvel.");
		}
	}
	
	/**
	 * Valida se a área de posse dos participantes e dos proprietários é maior
	 * que a área total do imóvel.
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
			throw new CAPESCadastroNegocioException("A área de posse dos proprietários e parceiros é maior que a área total do bem imóvel");
		}
	}
	
	/**
	 * Verifica se o bem possui participantes vinculados sem a existência de
	 * proprietários.
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
			throw new CAPESCadastroNegocioException("Não é possível excluir todos os proprietários, pois existem parceiros vinculados ao mesmo.");
		}
	}
	
	/**
	 * Calcula a diferença em dias entre as datas. (podendo ser negativo).
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
	 * Verifica se alguma pessoa que está saindo do relacionamento do bem possui
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
	 * Recupera os proprietários que estão marcados para a exclusão.
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
	 * Recupera as pessoas que estão marcadas para a exclusão.
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