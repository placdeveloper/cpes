package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoCompartilhamentoDAO;
import br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * Classe que implementa a persistencia de Referencia.
 *
 * @author Juan.Damasceno
 *
 */
public class GrupoCompartilhamentoDAOImpl extends CAPESCadastroCrudDao<GrupoCompartilhamento> implements GrupoCompartilhamentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "CONSULTAR_GRUPO_COMPARTILHAMENTO_POR_INSTITUICAO";
	
	/** A constante VERIRICAR_COMPARTILHAMENTO_CADASTRO. */
	private static final String VERIRICAR_COMPARTILHAMENTO_CADASTRO = "VERIRICAR_COMPARTILHAMENTO_CADASTRO";
	
	/** O atributo isContinue. */
	private boolean isContinue;
	
	/** O atributo sciIntegracaoDelegate. */
	private transient SCIIntegracaoDelegate sciIntegracaoDelegate = null;
	
	/**
	 * Instancia um novo GrupoCompartilhamentoDAOImpl.
	 */
	public GrupoCompartilhamentoDAOImpl() {
		super(GrupoCompartilhamento.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<GrupoCompartilhamentoVO> listarInstuicoesPorCompartilhamento(ConsultaDto<GrupoCompartilhamento> consultaDto) throws BancoobException {
		ConsultaDto<GrupoCompartilhamentoVO> retorno = new ConsultaDto<GrupoCompartilhamentoVO>();
		List<GrupoCompartilhamentoVO> listaRetorno = new ArrayList<GrupoCompartilhamentoVO>();
		
		sciIntegracaoDelegate = obterSCIIntegracaoDelegate();
		
		GrupoCompartilhamento filtro = (GrupoCompartilhamento) consultaDto.getFiltro();
		if(filtro.getIdInstituicao() != null){
			
			//Verifica se existe uma instituição com o código informado.
			InstituicaoVO instituicao = sciIntegracaoDelegate.obterInstituicaoPorNumeroCooperativa(filtro.getIdInstituicao());
			if(instituicao == null){
				throw new CAPESCadastroNegocioException("Não existe nenhuma instituição com o código informado.");
			}
			
			filtro.setIdInstituicao(instituicao.getIdInstituicao());
			consultaDto.setFiltro(filtro);
		}
		
		ConsultaDto<GrupoCompartilhamento> consulta = pesquisar(GrupoCompartilhamento.class, consultaDto, "LISTAR_GRUPOCOMPARTILHAMENTO");
		
		retorno.setOrdenacao(consulta.getOrdenacao());
		retorno.setPagina(consulta.getPagina());
		retorno.setProcurarPor(consulta.getProcurarPor());
		retorno.setTamanhoPagina(consulta.getTamanhoPagina());
		retorno.setTipoProcura(consulta.getTipoProcura());
		retorno.setTamanhoPagina(consulta.getTamanhoPagina());
		retorno.setTotalRegistros(consulta.getTotalRegistros());
		
		List<GrupoCompartilhamento> lista = consulta.getResultado();
		
		for(GrupoCompartilhamento g : lista){
			GrupoCompartilhamentoVO grupo = new GrupoCompartilhamentoVO();
			
			grupo.setDataHoraInicial(g.getDataHoraInicio());
			grupo.setCodigo(g.getCompartilhamentoCadastro().getCodigo());
			grupo.setDescricao(g.getCompartilhamentoCadastro().getDescricao());
			grupo.setIdGrupoCompartilhamento(g.getIdGrupoCompartilhamento());				
			grupo.setIdInstituicao(g.getIdInstituicao());
			grupo.setIntegracaoSrf(g.getIntegracaoSrf());
			
			InstituicaoVO instituicao = sciIntegracaoDelegate.obterInstituicaoPorId(grupo.getIdInstituicao());
			if(instituicao == null){
				listaRetorno.add(grupo);
				continue;
			}
			
			//Instuicao
			grupo.setNomeInstituicao(instituicao.getNome());
			grupo.setNumero(String.valueOf(instituicao.getNumero()));

			//Recuperar Unidade Instituição
			List<UnidadeInstituicaoVO> listUnidadeInstituicao = sciIntegracaoDelegate.listarUnidadesInstituicao(g.getIdInstituicao()/*resultSetGrupoCompart.getInt("idInstituicao")*/, true);
			
			//Recuperar Unidade da Instituição				
			if(!listUnidadeInstituicao.isEmpty()){
				UnidadeInstituicaoVO inidadeInstituicao = listUnidadeInstituicao.get(0);
				grupo.setNumCNPJ(inidadeInstituicao.getNumCNPJ());
				grupo.setSiglaInstituicao(inidadeInstituicao.getSiglaUnidade());										
			}
			
			listaRetorno.add(grupo);
		}
						
		retorno.setResultado(listaRetorno);
		
		return retorno;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GrupoCompartilhamentoVO> listarInstuicoesSemGrupo() throws BancoobException {
		List<GrupoCompartilhamentoVO> listaRetorno = new ArrayList<GrupoCompartilhamentoVO>();
		try {			
			
			GrupoCompartilhamentoVO grupo = null;
						
			sciIntegracaoDelegate = obterSCIIntegracaoDelegate();			
			Integer tipoCooperativa = 3;
			Integer codSituacaoInst = 1;
			
			List<InstituicaoVO> listCooperativas = sciIntegracaoDelegate.listarInstituicoesPorTipo(tipoCooperativa);			
			List<GrupoCompartilhamento> listGrupoCompartilhamento =  listar();
			
			//Obter Instituição 1
			InstituicaoVO bancoob = sciIntegracaoDelegate.obterInstituicaoPorId(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
			listCooperativas.add(bancoob);
			
			Iterator<InstituicaoVO> iteratorInst = listCooperativas.iterator();
			Iterator<GrupoCompartilhamento> iteratorGrupCompart;
			
			while (iteratorInst.hasNext()) {
				InstituicaoVO instituicao = iteratorInst.next();
								
				//Filtrar por tipo Cooperativas e Instituições ativas
				if (!instituicao.getIdInstituicao().equals(Constantes.Comum.ID_INSTITUICAO_BANCOOB)
				        && (!instituicao.getCodigoTipoInstituicao().equals(tipoCooperativa) 
				        		|| !instituicao.getCodigoSituacaoInst().equals(codSituacaoInst))) {
					continue;
				}
				
				isContinue = false;
				iteratorGrupCompart = listGrupoCompartilhamento.iterator();
				while (iteratorGrupCompart.hasNext()) {
					GrupoCompartilhamento grupoCompartilhamento = iteratorGrupCompart.next();
					if(grupoCompartilhamento.getIdInstituicao().equals(instituicao.getIdInstituicao())){
						isContinue = true;
						break;
					}
				}
				if(isContinue){
					continue;
				}
				
				Integer idInstituicao = instituicao.getIdInstituicao();
				
				grupo = new GrupoCompartilhamentoVO();
				grupo.setIdInstituicao(idInstituicao);
				grupo.setNomeInstituicao(instituicao.getNome());
				grupo.setNumero(String.valueOf(instituicao.getNumero()));

				//Recuperar Unidade Instituição
				List<UnidadeInstituicaoVO> listUnidadeInstituicao = sciIntegracaoDelegate.listarUnidadesInstituicao(idInstituicao, true);
				//Recuperar Unidade da Instituição				
				if(!listUnidadeInstituicao.isEmpty()){
					UnidadeInstituicaoVO inidadeInstituicao = listUnidadeInstituicao.get(0);
					grupo.setNumCNPJ(inidadeInstituicao.getNumCNPJ());
					grupo.setSiglaInstituicao(inidadeInstituicao.getSiglaUnidade());										
					
				}else{
					listaRetorno.add(grupo);
					continue;
				}
				listaRetorno.add(grupo);
			}

		} catch (BancoobException excecao) {
			throw new BancoobException(excecao);
		}
		
		return listaRetorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GrupoCompartilhamento> listar(
			ConsultaDto<GrupoCompartilhamento> criterios) throws BancoobException {

		List<GrupoCompartilhamento> lista;
		Comando comando = getComando(NOME_COMANDO_PESQUISAR);
		
		try {
			
			comando.adicionarVariavel("criterios", criterios);
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			query.setFlushMode(FlushModeType.COMMIT);
			lista = query.getResultList();
		} finally {
			fecharComando(comando);
		}
		
		return lista;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean verificarCompartilhamentoCadastro(Short idCompartilhamentoCadastro) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(VERIRICAR_COMPARTILHAMENTO_CADASTRO);
			comando.adicionarVariavel("idCompartilhamentoCadastro", idCompartilhamentoCadastro);
			comando.configurar();
			return criarQuery(comando).getResultList().size() > 0;
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * Obter sci integracao delegate.
	 *
	 * @return SCIIntegracaoDelegate
	 */
	private SCIIntegracaoDelegate obterSCIIntegracaoDelegate() {
		return CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
	}	

}