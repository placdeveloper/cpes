/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.Validacao;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoProdutividade;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum;
import br.com.sicoob.capes.cadastro.negocio.proxies.ProdutividadeProxy;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ProdutividadeServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ProdutividadeServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ProdutividadeDAO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.annotation.IdEntidadeAprovavel;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoSemiAutomaticaEnum;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorNumerico;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ ProdutividadeServicoLocal.class })
@Remote({ ProdutividadeServicoRemote.class })
@IntegracaoGedGft
public class ProdutividadeServicoEJB extends
		EntidadeCadastroServicoEJB<Produtividade> implements
		ProdutividadeServicoRemote, ProdutividadeServicoLocal {

	@Inject
	@Default
	private ProdutividadeDAO dao;

	/** O atributo servicoAnotacao. */
	@EJB(mappedName = "capes/cadastro/AnotacaoServico")
	private transient AnotacaoServicoLocal servicoAnotacao;
	
	@EJB
	private transient BemAntigoServicoLocal bemAntigoServico;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Produtividade incluir(Produtividade produtividade) throws BancoobException {
		tratarValores(produtividade);
		produtividade.setSituacao(SituacaoProdutividadeEnum.EM_ABERTO.getCodigo());
		tratarBemAntigo(produtividade);
		Produtividade retorno =  super.incluir(produtividade);
		
		if(isRegistroVigente(produtividade)){
			if(produtividade.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(produtividade, produtividade.getPessoaCompartilhamento().getPessoa().getIdPessoa(), produtividade.getIdUsuarioEnvio());
			}
		}
		
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Produtividade objeto) throws BancoobException {
		tratarValores(objeto);
		tratarBemAntigo(objeto);
		super.alterar(objeto);
		
		Produtividade produtividade = obter(objeto.getId());
		if(isRegistroVigente(produtividade)){
			if(objeto.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(produtividade, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(Produtividade produtividade) throws BancoobException {
		super.excluirEntidade(produtividade);
	
		if(isRegistroVigente(produtividade)){
			if(produtividade.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(produtividade, produtividade.getPessoaCompartilhamento().getPessoa().getIdPessoa(), produtividade.getIdUsuarioEnvio());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Produtividade objeto) throws BancoobException {
		validarProdutividade(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Produtividade objeto) throws BancoobException {
		validarProdutividade(objeto);
	}

	/**
	 * O método Validar produtividade.
	 *
	 * @param produtividade o valor de produtividade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarProdutividade(Produtividade produtividade)
			throws BancoobException {
		Validacao<Produtividade> validacao = new ValidacaoProdutividade();
		validacao.validar(produtividade);
	}

	/**
	 * Trata os valores expressos em BigDecimal devido ao bug reportado.
	 * @param produtividade
	 */
	private void tratarValores(Produtividade produtividade) {
		produtividade.setProducao(tratarValor(produtividade.getProducao()));
		produtividade.setQuantidadeOuArea(tratarValor(produtividade.getQuantidadeOuArea()));
		produtividade.setPercentualRebate(tratarValor(produtividade.getPercentualRebate()));
		produtividade.setValorRendaBruta(tratarValor(produtividade.getValorRendaBruta()));
		produtividade.setValorPrecoMedio(tratarValor(produtividade.getValorPrecoMedio()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadeDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProdutividadeProxy> listar(ConsultaProdutividadeDTO criterios)
			throws BancoobException {

		List<ProdutividadeProxy> retorno = new ArrayList<ProdutividadeProxy>();
		List<Produtividade> lista = super.listar(criterios);

		if (lista != null) {

			ProdutividadeProxy proxy = null;
			for (Produtividade produtividade : lista) {
				proxy = new ProdutividadeProxy();
				proxy.setIdProdutividade(produtividade.getIdProdutividade());
				proxy.setAnoFimSafra(produtividade.getAnoFimSafra());
				proxy.setAnoInicioSafra(produtividade.getAnoInicioSafra());
				proxy.setDescEmpreendimento(produtividade.getEmpreendimento()
						.getDescricao());
				proxy.setProducao(produtividade.getProducao());
				proxy.setQuantidadeOuArea(produtividade.getQuantidadeOuArea());
				proxy.setValorPrecoMedio(produtividade.getValorPrecoMedio());
				proxy.setValorRendaBruta(produtividade.getValorRendaBruta());
				if (produtividade.getBemImovel() != null) {
					proxy.setDenominacaoImovel(produtividade.getBemImovel()
							.getDenominacao());
				}
				proxy.setCodSituacao(produtividade.getSituacao());
				proxy.setDescSituacao(SituacaoProdutividadeEnum
						.recuperarDescricao(produtividade.getSituacao()));
				proxy.setIdInstituicaoAtualizacao(produtividade.getIdInstituicaoAtualizacao());
				proxy.setDataHoraInicio(produtividade.getDataHoraInicio());
				proxy.setCodigoSituacaoAprovacao(produtividade.getCodigoSituacaoAprovacao());
				proxy.setIdRegistroControlado(produtividade.getIdRegistroControlado());
				retorno.add(proxy);
			}
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public void finalizarExploracao(ProdutividadeDTO dto, @IdEntidadeAprovavel Long idProdutividade)
			throws BancoobException {

		Produtividade produtividade = super.obter(dto.getIdProdutividade());
		SituacaoProdutividadeEnum situacao = null;
		
		if (Boolean.TRUE.equals(dto.getHouveFrustracao())) {
			validarFinalizarComFrustracao(dto);
			situacao = SituacaoProdutividadeEnum.FINALIZADO_FRUSTRACAO;
			incluirAnotacaoFrustracaoSafra(produtividade, dto);
		} else {
			situacao = SituacaoProdutividadeEnum.FINALIZADO_SUCESSO;
		}

		produtividade.setDataFinalizacao(new DateTimeDB());
		produtividade.setSituacao(situacao.getCodigo());
		alterar(produtividade);
	}

	/**
	 * O método Validar finalizar com frustracao.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarFinalizarComFrustracao(ProdutividadeDTO dto) throws BancoobException{
		ValidacaoProdutividade validacao = new ValidacaoProdutividade();
		validacao.validarFrustracao(dto.getPercentualFrustracao(), dto.getDataOcorrencia());
	}
		
	/**
	 * Inclui a anotação de frustração de safra.
	 * 
	 * @param produtividade
	 * @param dto
	 * @throws BancoobException
	 */
	private void incluirAnotacaoFrustracaoSafra(Produtividade produtividade, ProdutividadeDTO dto) throws BancoobException {
		DateTimeDB dataOcorencia = new DateTimeDB(dto.getDataOcorrencia().getTime());
		TipoAnotacaoDelegate tipoAnotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoAnotacaoDelegate();
		TipoAnotacao tipoAnotacao = tipoAnotacaoDelegate.obter(TipoAnotacaoSemiAutomaticaEnum.SISBR_FRUSTRACAO_SAFRA.getCodTipoAnotacao());

		Anotacao anotacao = new Anotacao();
		anotacao.setTipoAnotacao(tipoAnotacao);
		anotacao.setDataHoraAnotacao(new DateTimeDB());
		anotacao.setDataHoraOcorrencia(dataOcorencia);
		anotacao.setDescObservacao(obterDescricaoAnotacao(produtividade, dto.getPercentualFrustracao()));
		anotacao.setPessoaCompartilhamento(produtividade.getPessoaCompartilhamento());

		servicoAnotacao.incluir(anotacao);
	}

	/**
	 * Gera a descrição da anotação de frustração de safra. - Observação : -
	 * Denominação do imóvel; - Código e descrição do empreendimento; -
	 * Município e UF do Imóvel; - Percentual da Frustação; Ex.:Chácara Paraízo,
	 * 124536-Cana de Açúcar, Goianésia-GO, Frustação de : ??%.
	 * 
	 * @param produtividade
	 *            A produtividade frustrada
	 * @param percentual
	 *            O percentual da frustração.
	 * @return A descrição para a anotação de frustração de safra.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private String obterDescricaoAnotacao(Produtividade produtividade, BigDecimal percentual) 
			throws BancoobException {

		BemImovel imovel = produtividade.getBemImovel();
		Empreendimento empreendimento = produtividade.getEmpreendimento();
		StringBuilder builder = new StringBuilder();

		if (imovel != null) {
			builder.append(imovel.getDenominacao());
			builder.append(", ");
		}

		builder.append(empreendimento.getCodigo());
		builder.append("-");
		builder.append(empreendimento.getDescricao());
		builder.append(", ");

		if (imovel != null && imovel.getIdLocalidade() != null) {
			builder.append(obterLocalidadeImovel(imovel));
			builder.append(", ");
		}

		String valorPercentual = new FormatadorNumerico().formatar(percentual, null);
		builder.append("FRUSTRAÇÃO DE: ");
		builder.append(valorPercentual);
		builder.append("%.");
		return builder.toString().toUpperCase(Locale.getDefault());
	}

	/**
	 * Recupera a descrição da localidade do imóvel. Ex: Goianésia-GO
	 * 
	 * @param imovel
	 *            O imóvel utilizado para obter a localidade.
	 * @return a descrição da localidade do imóvel.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private String obterLocalidadeImovel(BemImovel imovel) throws BancoobException {
		LOCIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		Localidade localidade = IntegracaoUtil.converterLocalidade(delegate.obterLocalidade(imovel.getIdLocalidade()));

		StringBuilder builder = new StringBuilder();
		if (localidade != null) {
			builder.append(localidade.getNome());
			builder.append("-");
			builder.append(localidade.getUf().getSiglaUF());
		}

		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Produtividade> listarProdutividadesEmAberto(PessoaCompartilhamento pessoaCompartilhamento)
			throws BancoobException {
		
		Produtividade produtividade = new Produtividade();
		produtividade.setPessoaCompartilhamento(pessoaCompartilhamento);
		
		List<Short> situacoes = new ArrayList<Short>();
		situacoes.add(SituacaoProdutividadeEnum.EM_ABERTO.getCodigo());		
		
		ConsultaProdutividadeDTO criterios = new ConsultaProdutividadeDTO();
		criterios.setSituacoes(situacoes);
		criterios.setFiltro(produtividade);
		return super.listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> listarProdutividadesAssociadas(BemImovel bemImovel, List<Long> idsPessoaCompartilhamento) throws BancoobException {
		return getDAO().listarProdutividadesAssociadas(bemImovel, idsPessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public Produtividade importar(Produtividade produtividade)
			throws BancoobException {
		validarIncluir(produtividade);
		return getDAO().incluir(produtividade);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean produtorPossuiDependencia(Produtor produtor) throws BancoobException {
		return getDAO().produtorPossuiDependencia(produtor);
	}
	
	private void tratarBemAntigo(Produtividade produtividade) throws BancoobException {
		// Obtém os bem, caso ele seja o proprietário do Bem.
		List<Bem> listaBensProprietario = bemAntigoServico.obterPorIdBemNovo(produtividade.getPessoaCompartilhamento(), produtividade.getIdBemImovel());

		Long idBemAntigo = null;
		// Se ele for o proprietário, atualizamos a produtividade com o IDBEMANTIGO do bem dele.
		if (CollectionUtils.isNotEmpty(listaBensProprietario)) {
			Bem bemAntigoProprietario = listaBensProprietario.get(0);
			idBemAntigo = bemAntigoProprietario.getId();
			
		// Caso ele não seja o proprietário do bem (Parceiro), adicionaremos um ID de um bem de um proprietário qualquer.
		} else {
			List<Bem> listaBensAntigosVinculadosBemNovo = bemAntigoServico.obterPorIdBemNovo(null, produtividade.getIdBemImovel());
			if (CollectionUtils.isNotEmpty(listaBensAntigosVinculadosBemNovo)) {
				Bem bemQualquer = listaBensAntigosVinculadosBemNovo.get(0);
				idBemAntigo = bemQualquer.getId();
			}
		}
		produtividade.setIdBemAntigo(idBemAntigo);
	}
	
	public List<String> listarProdutividadesPorIdBem(Long idBem) throws BancoobException {
		return getDAO().listarProdutividadesPorIdBem(idBem);
	}

}