package br.com.sicoob.capes.cadastro.fachada.bem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.fachada.EntidadeCadastroFachada;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;

/**
 * Fachada base para as classes que herdam de {@link Bem}
 * 
 * @author bruno.carneiro
 * 
 * @param <T>
 *            A classe que herda de {@link Bem}
 */
public abstract class BemFachada extends EntidadeCadastroFachada<Bem> {

	protected static final String CHAVE_BEM = "bem";

	/**
	 * Construtor padrão da fachada.
	 * 
	 * @param chaveMapa
	 */
	public BemFachada(String chaveMapa) {
		super(chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem obterEntidade(RequisicaoReqDTO dto) {
		return (Bem) dto.getDados().get(chaveMapa);
	}

	/**
	 * Método utilizado pelo componente de pesquisa de bens para recuperar o bem
	 * a partir do seu código.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterPorCodigo(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		try {
			Bem entidade = obterEntidade(dto);
			ConsultaDto<Bem> criterios = new ConsultaDto<Bem>();
			criterios.setFiltro(entidade);
			retorno.getDados().put(NOME_PADRAO_LISTA, obterDelegate().pesquisar(criterios).getResultado());
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
	 * {@inheritDoc}
	 */
	@Override
	protected BemDelegate obterDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Bem entidade = obterEntidade(dto);
			Bem entidadePersistente = consultarEntidade(entidade);
			
			AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = obterFabricaDelegates().criarAutorizacaoCadastroDelegate();
			entidadePersistente.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(entidadePersistente));
			return obterMapRetorno(this.chaveMapa, entidadePersistente);
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
		return null;
	}
	
	/**
	 * Obtém as definições do GED (Siglas das chaves de negócio).
	 * 
	 * @param dto
	 * @return
	 */
	protected List<DefinicoesComponenteGedVO> obterDefinicoesGED(RequisicaoReqDTO dto) {
		Integer codTipoPessoa = (Integer) dto.getDados().get("idTipoPessoa");
		List<DefinicoesComponenteGedVO> listaDefinicoesGed = new ArrayList<DefinicoesComponenteGedVO>();
		
		if(codTipoPessoa != null) {
			Set<String> chavesNegocio = new LinkedHashSet<String>();
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
			chavesNegocio.add(Constantes.Negocio.GED_CHAVE_TIPO_CLASSIFICACAO_BEM);
			chavesNegocio.add(Constantes.Negocio.GED_CHAVE_TIPO_BEM);

			List<String> listaSiglas = new ArrayList<String>();
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_BEM);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_LAUDO_AVALIACAO_BEM);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_CERTIFICADO_CADASTRO_IMOVEL_RURAL);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_IMPOSTO_SOBRE_PROPRIEDADE_TERRITORIAL);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_CONTRATO_POSSE_USO_IMOVEL_BENEFICIADO);
			
			for (String sigla : listaSiglas) {
				DefinicoesComponenteGedVO definicaoComponenteGedVO = new DefinicoesComponenteGedVO();
				definicaoComponenteGedVO.setIdTipoPessoa(codTipoPessoa.shortValue());
				definicaoComponenteGedVO.setSiglaTipoDocumento(sigla);
				definicaoComponenteGedVO.setChavesNegocio(chavesNegocio);
				listaDefinicoesGed.add(definicaoComponenteGedVO);
			}
		}
		return listaDefinicoesGed;
	}

}