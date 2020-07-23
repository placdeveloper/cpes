/*
 * SICOOB
 * 
 * AtualizarAnotacoesSisbrServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.AtualizarAnotacoesSisbrServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.api.negocio.anotacao.fabrica.FabricaAnotacoesSisbr;
import br.com.sicoob.capes.api.negocio.excecao.AnotacaoSisbrParaBaixaNaoEncontradaException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.AtualizarAnotacoesSisbrServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.AtualizarAnotacoesSisbrServicoRemote;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoMigradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.OrigemTipoAnotacaoDiferenteSisbrException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TipoAnotacaoAutomaticaNaoEncontradoException;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Servico utilizado para atualizar as anotações do sisbr.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { AtualizarAnotacoesSisbrServicoLocal.class })
@Remote( { AtualizarAnotacoesSisbrServicoRemote.class })
public class AtualizarAnotacoesSisbrServicoEJB extends CAPESApiServicoEJB
		implements AtualizarAnotacoesSisbrServicoRemote, AtualizarAnotacoesSisbrServicoLocal {

	/** O atributo fabrica. */
	private final transient CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo anotacao delegate. */
	private final transient AnotacaoDelegate anotacaoDelegate = fabrica.criarAnotacaoDelegate();
	
	/** O atributo pessoa delegate. */
	private final transient PessoaCompartilhamentoDelegate pessoaDelegate = 
			fabrica.criarPessoaCompartilhamentoDelegate();

	/** Entity manager usado para a persistência dos dados. */
    @PersistenceContext(unitName = "emCapes")
    private EntityManager emCapes;
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Long> baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException {

		validarTipoAnotacao(dto.getCodigoTipoAnotacao());

		ConsultaAnotacaoSisbrDTO criterios = obterFiltroAnotacoesSisbr(dto);

		List<AnotacaoSisbr> anostacoesSisbr = anotacaoDelegate.listarAnotacoesSisbr(criterios);
		List<Anotacao> anotacoes = new ArrayList<Anotacao>();

		if (anostacoesSisbr == null || anostacoesSisbr.isEmpty()) {
			throw new AnotacaoSisbrParaBaixaNaoEncontradaException();
		}

		for (AnotacaoSisbr anotacaoSisbr : anostacoesSisbr) {
			anotacaoSisbr.setDescObservacao(dto.getObservacao());
			anotacoes.add(anotacaoSisbr);
		}

		anotacoes = anotacaoDelegate.baixarAnotacoesAutomaticas(anotacoes, TipoBaixaEnum.BAIXA_AUTOMATICA_SISBR);

		List<Long> retorno = new ArrayList<Long>();
		if (anotacoes != null && anotacoes.size() > NumberUtils.INTEGER_ZERO) {
			for (Anotacao anotacao : anotacoes) {
				retorno.add(anotacao.getId());
			}
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException {

		validarTipoAnotacao(dto.getCodigoTipoAnotacao());

		PessoaCompartilhamento pessoa = consultarPessoaCompartilhamento(dto);
		FabricaAnotacoesSisbr fabricaAnotacoes = FabricaAnotacoesSisbr.getInstance();
		List<Anotacao> anotacoes = fabricaAnotacoes.criarAnotacoes(dto, pessoa);

		anotacoes = anotacaoDelegate.incluir(anotacoes);
		return anotacoes.get(0).getId();
	}

	/**
	 * Consulta a pessoa no compartilhamento a partir do número da pessoa no legado e da instituição
	 * informados no dto.
	 * 
	 * @param dto
	 *            O dto com as informações para consultar a pessoa.
	 * 
	 * @return A pessoa consultada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */	
	private PessoaCompartilhamento consultarPessoaCompartilhamento(AnotacaoSisbrDTO dto) throws BancoobException {
		return pessoaDelegate.consultarPessoaPorIdPessoaLegado(dto.getNumPessoa(),
				obterInstituicao(dto)); 
	}
	
	/**
	 * Valida se o tipo da anotação foi informado e se a origem é SISBR.
	 * 
	 * @param codigoTipo
	 *            O código do tipo da anotação.
	 * @throws CAPESProcessamentoException
	 *             Caso ocorra alguma exceção.
	 * @throws CAPESCadastroComumNegocioException
	 */
	private void validarTipoAnotacao(Integer codigoTipo) throws 
			TipoAnotacaoAutomaticaNaoEncontradoException, OrigemTipoAnotacaoDiferenteSisbrException{

		TipoAnotacaoAutomaticaEnum tipoAnotacao = TipoAnotacaoAutomaticaEnum
				.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(codigoTipo);

		if (tipoAnotacao == null) {
			throw new TipoAnotacaoAutomaticaNaoEncontradoException();
		}
		if (!OrigemInformacaoEnum.SISBR.equals(tipoAnotacao.getOrigem())) {
			throw new OrigemTipoAnotacaoDiferenteSisbrException();
		}
	}

	/**
	 * Recupera a instituição com as informações do dto.
	 * 
	 * @param dto
	 * @return
	 */
	private Instituicao obterInstituicao(AnotacaoSisbrDTO dto) {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(dto.getIdInstituicao());
		instituicao.setIdUnidadeInst(dto.getIdUnidadeInst());
		return instituicao;
	}

	/**
	 * Monta o filtro para a consulta das anotações que serão baixadas a partir
	 * do dto informado.
	 * 
	 * @param dto
	 *            O dto com as informações para a consulta.
	 * @return o filtro para a consulta das anotações que serão baixadas a
	 *         partir do dto informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private ConsultaAnotacaoSisbrDTO obterFiltroAnotacoesSisbr(AnotacaoSisbrDTO dto)
			throws BancoobException {
		
		PessoaCompartilhamento pessoa;
		
		try {
			pessoa = consultarPessoaCompartilhamento(dto);
		} catch (ClienteNaoMigradoException ex) {
			throw new BancoobException(ex.getMessage());
		}

		OrigemInformacao origemInformacao = new OrigemInformacao();
		origemInformacao.setIdOrigemInfo(OrigemInformacaoEnum.SISBR.getIdOrigemInformacao());

		TipoAnotacao tipoAnotacao = new TipoAnotacao();
		tipoAnotacao.setCodTipoAnotacao(dto.getCodigoTipoAnotacao());

		AnotacaoSisbr anotacao = new AnotacaoSisbr();
		anotacao.setPessoaCompartilhamento(pessoa); 
		anotacao.setInstituicaoModalidadeProduto(obterInstituicao(dto));
		anotacao.setIdProduto(dto.getIdProduto());
		anotacao.setIdModalidadeProduto(dto.getIdModalidadeProduto());
		anotacao.setNumeroContrato(dto.getNumeroContrato());
		anotacao.setTipoAnotacao(tipoAnotacao);
		anotacao.setOrigemInformacao(origemInformacao);
		
		Date dataVencimento = dto.getDataVencimento();
		if(dataVencimento != null) {
			anotacao.setDataVencimento(new DateTimeDB(dataVencimento.getTime()));
		}

		ConsultaAnotacaoSisbrDTO criterios = new ConsultaAnotacaoSisbrDTO();
		criterios.setFiltro(anotacao);

		return criterios;
	}

	/**
     * @return the emCapes
     */
    protected EntityManager getEmCapes() {
    	return emCapes;
    }

	@Override
	protected CAPESApiDao obterDAO() {
		return null;
	}

}
