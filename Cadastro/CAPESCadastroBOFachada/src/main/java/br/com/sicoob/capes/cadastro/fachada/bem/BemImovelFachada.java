package br.com.sicoob.capes.cadastro.fachada.bem;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoRelacionamentoBemImovelDelegate;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;

/**
 * Fachada dos bens imóveis.
 * 
 * @author bruno.carneiro
 */
@RemoteService
public class BemImovelFachada extends BemFachada {

	/**
	 * Método construtor
	 */
	public BemImovelFachada() {
		super("bemImovel");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDefinicoes(dto);
		try {
			retorno.getDados().put("listaTiposLocalizacao", obterFabricaDelegates().criarTipoLocalizacaoBemDelegate().listar());
			retorno.getDados().put("listaTipoLogradouro", IntegracaoUtil.converterTiposLogradouro(CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate().listarTiposLogradouro()));

			retorno.getDados().put("listaTiposImplantacao", obterFabricaDelegates().criarTipoImplantacaoBemImovelDelegate().listar());
			retorno.getDados().put("listaTiposEstadoConservacao", obterFabricaDelegates().criarTipoEstadoConservacaoDelegate().listar(Boolean.TRUE));
			retorno.getDados().put("listaTiposPadraoAcabamento", obterFabricaDelegates().criarTipoPadraoAcabamentoBemImovelDelegate().listar());
			retorno.getDados().put("listaTiposServicosCondominiais", obterFabricaDelegates().criarTipoServicoCondominialBemImovelDelegate().listar());

			retorno.getDados().put("listaTiposOnus", obterFabricaDelegates().criarTipoOnusBemDelegate().listar());
			
			retorno.getDados().put("definicoesComponenteGED", obterDefinicoesGED(dto));
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}

	/**
	 * Obtém os tipo de uso do bem a partir do {@code TipoLocalizacaoBem}
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterTiposUsoBem(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			
			TipoLocalizacaoBem tipoLocalizacaoBem = (TipoLocalizacaoBem) dto.getDados().get("tipoLocalizacaoBem");

			ConsultaDto<TipoUsoBemImovel> criterios = new ConsultaDto<TipoUsoBemImovel>();
			TipoUsoBemImovel filtro = new TipoUsoBemImovel();
			filtro.setTipoLocalizacaoBem(tipoLocalizacaoBem);
			criterios.setFiltro(filtro);

			ConsultaDto<TipoUsoBemImovel> consulta = obterFabricaDelegates().criarTipoUsoBemDelegate().pesquisar(criterios);

			retorno.getDados().put("listaTiposUsoBem", consulta.getResultado());
			
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return retorno;
	}

	/**
	 * Obtém as informações da tela de associar parceiro.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesAssociarParceiro(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		TipoRelacionamentoBemImovelDelegate tipoRelacionamentoBemImovelDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoRelacionamentoBemImovelDelegate();
		retorno.getDados().put("listaTiposRelacionamento", tipoRelacionamentoBemImovelDelegate.listar());

		return retorno;
	}

	/**
	 * Obtém a lista dos tipos de relacionamento.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterTipoRelacionamentos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			Bem bem = (Bem) dto.getDados().get(CHAVE_BEM);
			List<BemImovelTipoRelacionamentoPessoa> relacionamentos = obterDelegate().obterRelacionamentosBemImovel(bem.getIdBem());
			retorno.getDados().put("relacionamentos", relacionamentos);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 

		return retorno;
	}

}