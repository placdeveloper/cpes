package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.filtros.FiltroRelacionamentoPessoa;
import br.com.sicoob.capes.api.negocio.vo.DadosRegistroVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaPoderVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.RelacionamentoPessoaDAO;

/**
 * A Classe RelacionamentoPessoaDAOImpl.
 */
public class RelacionamentoPessoaDAOImpl extends CAPESApiDAO<RelacionamentoPessoaVO> implements RelacionamentoPessoaDAO {
	
	/** A constante TIPO_RELACIONAMENTO_CONJUGE. */
	private static final List<Short> TIPO_RELACIONAMENTO_CONJUGE = Arrays.asList((short)2);
	
	/** A constante TIPOS_RELACIONAMENTO_QUADRO_SOCIETARIO. */
	private static final List<Short> TIPOS_RELACIONAMENTO_QUADRO_SOCIETARIO = Arrays.asList((short)8, (short)9);
	
	/**
	 * Instancia um novo RelacionamentoPessoaDAOImpl.
	 */
	public RelacionamentoPessoaDAOImpl(){
		super("CONSULTAR_RELACIONAMENTOS_PESSOA");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RelacionamentoPessoaVO> obterRelacionamentosConjugesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterPorPessoaInstituicaoTipos(idPessoa, idInstituicao, TIPO_RELACIONAMENTO_CONJUGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RelacionamentoPessoaVO> obterRelacionamentosQuadroSocietarioPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterPorPessoaInstituicaoTipos(idPessoa, idInstituicao, TIPOS_RELACIONAMENTO_QUADRO_SOCIETARIO);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RelacionamentoPessoaVO> obterRelacionamentosPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Short codigoTipoRelacionamento) throws BancoobException {
		return obterPorPessoaInstituicaoTipos(idPessoa, idInstituicao, Arrays.asList(codigoTipoRelacionamento));
	}
	
	/**
	 * Obter por pessoa instituicao tipos.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigosTipoRelacionamento o valor de codigos tipo relacionamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	private List<RelacionamentoPessoaVO> obterPorPessoaInstituicaoTipos(Integer idPessoa, Integer idInstituicao, List<Short> codigosTipoRelacionamento) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("tiposRelacionamento", codigosTipoRelacionamento);
			comando.configurar();
			
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<RelacionamentoPessoaPoderVO> obterPoderes(Long idRelacionamento) {
		Comando comando = null;
		try {
			comando = getComando("CONSULTAR_RELACIONAMENTOS_PESSOA_PODER");
			comando.adicionarVariavel("idRelacionamento", idRelacionamento);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosRegistroVO obterDadosRegistro(Long idRelacionamento) {
		Comando comando = null;
		try {
			comando = getComando("CONSULTAR_REGISTRO_RELACIONAMENTOS");
			comando.adicionarVariavel("idRelacionamento", idRelacionamento);
			comando.configurar();

			return (DadosRegistroVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	@Override
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return "PESQUISAR_RELACIONAMENTOS_PESSOA";
	}
	
	@Override
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return "PESQUISAR_QUANTIDADE_RELACIONAMENTOS_PESSOA";
	}

	@SuppressWarnings({ "unchecked" })
	public List<RelacionamentoPessoaVO> obterByPessoaTipoCompartilhado(
			Integer idPessoa, Short codigoTipoRelacionamento,
			Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		FiltroRelacionamentoPessoa filtro = new FiltroRelacionamentoPessoa();
		filtro.setIdPessoa(idPessoa);
		filtro.setIdInstituicao(idInstituicao);
		filtro.setCodigoTipoRelacionamento(codigoTipoRelacionamento);
		
		ConsultaDto<FiltroConsultaAPIAbstrato> criterios = new ConsultaDto<FiltroConsultaAPIAbstrato>();
		criterios.setFiltro(filtro);
		
		try{
			comando = getComando("PESQUISAR_RELACIONAMENTOS_PESSOA_ESPECIFICO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();
			
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	
	@SuppressWarnings({ "unchecked" })
	public ConsultaDto<RelacionamentoPessoaVO> pesquisarPaginadoEspecifico(
			ConsultaDto<FiltroConsultaAPIAbstrato> criterios)
			throws BancoobException {

		ConsultaDto<RelacionamentoPessoaVO> consultaDto = new ConsultaDto<RelacionamentoPessoaVO>();
		consultaDto.setTamanhoPagina(criterios.getTamanhoPagina());
		consultaDto.setPagina(criterios.getPagina());

		Comando comando = null;
		Comando comandoQuantidade = null;
		try {
			comando = getComando("PESQUISAR_RELACIONAMENTOS_PESSOA_ESPECIFICO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();

			Query query = criarQuery(comando);
			query.setFirstResult(criterios.getPagina() * criterios.getTamanhoPagina());
			query.setMaxResults(criterios.getTamanhoPagina());

			consultaDto.setResultado(query.getResultList());
			comandoQuantidade = getComando("PESQUISAR_QUANTIDADE_RELACIONAMENTOS_PESSOA_ESPECIFICO");
			comandoQuantidade.adicionarVariavel(CRITERIOS, criterios);
			comandoQuantidade.configurar();
			int quantidade = ((Number) criarQuery(comandoQuantidade).getSingleResult()).intValue();

			consultaDto.setTotalRegistros(quantidade);

			return consultaDto;
		} finally {
			fecharComando(comando);
			fecharComando(comandoQuantidade);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<RelacionamentoPessoaVO> pesquisarEspecifico(
			FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		ConsultaDto<FiltroConsultaAPIAbstrato> criterios = new ConsultaDto<FiltroConsultaAPIAbstrato>();
		criterios.setFiltro(filtro);
		Comando comando = null;
		try{			
			comando = getComando("PESQUISAR_RELACIONAMENTOS_PESSOA_ESPECIFICO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();
			
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
}