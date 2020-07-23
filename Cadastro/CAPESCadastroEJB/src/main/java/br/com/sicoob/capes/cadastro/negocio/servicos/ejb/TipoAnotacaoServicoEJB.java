/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAplicacaoEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoCapturaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.SelecioneUmaCooperativaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.MapaTipoAnotacaoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAnotacaoDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CooperativaAnotacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoCaptura;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.pk.CooperativaAnotacaoPK;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * Implementa as operações do serviço de tipo de anotações.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local({ TipoAnotacaoServicoLocal.class })
@Remote({ TipoAnotacaoServicoRemote.class })
public class TipoAnotacaoServicoEJB extends CAPESCadastroCrudServicoEJB<TipoAnotacao> implements TipoAnotacaoServicoRemote, TipoAnotacaoServicoLocal {

	@Inject
	@Default
	protected TipoAnotacaoDAO tipoAnotacaoDao;

	@Inject
	@Default
	protected MapaTipoAnotacaoDAO mapaTipoAnotacaoDao;

	/** O atributo log. */
	private ISicoobLogger log = getLogger();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoAnotacao> listarTiposAnotacaoManualAtivos(TipoPessoa tipoPessoa) {
		log.debug("Listando tipos de anotação com captura manual");
		TipoCaptura tipoCaptura = new TipoCaptura();
		tipoCaptura.setIdTipoCaptura(TipoCapturaEnum.MANUAL.getCodigo());

		ConsultaDto<TipoAnotacao> consultaDto = new ConsultaDto<TipoAnotacao>();
		TipoAnotacao tipoAnotacao = new TipoAnotacao();
		tipoAnotacao.setTipoCaptura(tipoCaptura);
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		tipoAnotacao.setInstituicao(instituicao);

		if (TipoPessoaEnum.isPessoaFisica(tipoPessoa.getCodTipoPessoa())) {
			tipoAnotacao.setIdTipoAplicacao(TipoAplicacaoEnum.PESSOA_FISICA.getIdTipoAplicacao());
		} else if (TipoPessoaEnum.isPessoaJuridica(tipoPessoa.getCodTipoPessoa())) {
			tipoAnotacao.setIdTipoAplicacao(TipoAplicacaoEnum.PESSOA_JURIDICA.getIdTipoAplicacao());
		}
		
		consultaDto.setFiltro(tipoAnotacao);

		return tipoAnotacaoDao.listarTiposAnotacaoAtivos(consultaDto);
	}

	/**
	 * Obtem a saida do Tipo de Anotação informada.
	 * 
	 * @param objeto Tipo de Anotação
	 * @return Texto de saida.
	 * @throws BancoobException
	 */
	@Override
	public String obterSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException {
		return tipoAnotacaoDao.obterSaidaTipoAnotacao(objeto);
	}
	
	/**
	 * Verifica se o Tipo Anotação possui texto de saida.
	 * 
	 * @param objeto Tipo de Anotação
	 * @return <code>true</true> Se possuir
	 * @throws BancoobException
	 */
	@Override
	public boolean possuiSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException {
		return StringUtils.isNotBlank(obterSaidaTipoAnotacao(objeto));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoAnotacao> getDAO() {
		return tipoAnotacaoDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		TipoAnotacao tipoAnotacao = obter(chave);
		tipoAnotacao.setAtivo(false);
		tipoAnotacao.setDataHoraAlteracao(new Date());
		tipoAnotacao.setUsuarioAlteracao(recuperarLoginSemDominio());

		// Exclusão lógica
		super.alterar(tipoAnotacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoAnotacao incluir(TipoAnotacao objeto) throws BancoobException {
		validarTipoAnotacao(objeto);

		TipoAnotacao tipoAnotacao = getDAO().obter(objeto.getCodTipoAnotacao());
		if (tipoAnotacao != null) {
			throw new CAPESCadastroNegocioException("O código informado já existe.");
		}

		tipoAnotacao = super.incluir(objeto);
		tratarInstituicoes(tipoAnotacao);
		alterar(tipoAnotacao);
		return tipoAnotacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(TipoAnotacao objeto) throws BancoobException {
		validarTipoAnotacao(objeto);
		tratarInstituicoes(objeto);
		tratarMapaTipoAnotacao(objeto);
		super.alterar(objeto);
	}

	/**
	 * O método Validar tipo anotacao.
	 *
	 * @param objeto o valor de objeto
	 * @throws SelecioneUmaCooperativaException lança a exceção SelecioneUmaCooperativaException
	 */
	private void validarTipoAnotacao(TipoAnotacao objeto) throws SelecioneUmaCooperativaException {
		if (objeto.getValidaUsoAnotacao() == Boolean.TRUE && (objeto.getInstituicoes() == null || objeto.getInstituicoes().isEmpty())) {
			throw new SelecioneUmaCooperativaException();
		}
	}

	/**
	 * O método Tratar mapa tipo anotacao.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void tratarMapaTipoAnotacao(TipoAnotacao objeto) throws BancoobException {
		List<MapaTipoAnotacao> obterMapasTipoAnotacaoPorTipoAnotacao = mapaTipoAnotacaoDao.obterMapasTipoAnotacaoPorTipoAnotacao(objeto);
		for (MapaTipoAnotacao mapaTipoAnotacao : obterMapasTipoAnotacaoPorTipoAnotacao) {
			mapaTipoAnotacaoDao.excluir(mapaTipoAnotacao.getId());
		}
		for (MapaTipoAnotacao mapaTipoAnotacao : objeto.getMapasTipoAnotacao()) {
			MapaTipoAnotacao anotacao = new MapaTipoAnotacao();
			anotacao.setCodigoTipoOrigemInformacao(mapaTipoAnotacao.getCodigoTipoOrigemInformacao());
			anotacao.setTipoAnotacao(mapaTipoAnotacao.getTipoAnotacao());
			anotacao.setTipoConsultaOrigem(mapaTipoAnotacao.getTipoConsultaOrigem());
			mapaTipoAnotacaoDao.incluir(anotacao);
		}
		objeto.setMapasTipoAnotacao(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoAnotacao carregarInstituicoes(TipoAnotacao tipoAnotacao) throws BancoobException {
		if (tipoAnotacao != null) {
			SCIIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			List<InstituicaoVO> listaInstituicoes = delegate.obterInsituicoesPorId(obterIdsInstituicoes(tipoAnotacao.getId()));
			tipoAnotacao.setInstituicoes(listaInstituicoes);
		}
		return tipoAnotacao;
	}

	/**
	 * Obter ids instituicoes.
	 *
	 * @param idTipoAnotacao o valor de id tipo anotacao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Integer> obterIdsInstituicoes(Integer idTipoAnotacao) throws BancoobException {
		List<Integer> retorno = null;

		TipoAnotacao tipoAnotacao = obter(idTipoAnotacao);

		if (tipoAnotacao != null && tipoAnotacao.getCooperativasAnotacao().size() > 0) {
			retorno = new ArrayList<Integer>();
			for (CooperativaAnotacao cooperativaAnotacao : tipoAnotacao.getCooperativasAnotacao()) {
				retorno.add(cooperativaAnotacao.getId().getIdInstituicao());
			}
		}
		return retorno;
	}

	/**
	 * O método Tratar instituicoes.
	 *
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void tratarInstituicoes(TipoAnotacao tipoAnotacao) throws BancoobException {
		Set<CooperativaAnotacao> lista = new HashSet<CooperativaAnotacao>();
		if (tipoAnotacao != null) {
			if (tipoAnotacao.getInstituicoes() != null) {
				for (InstituicaoVO instituicao : tipoAnotacao.getInstituicoes()) {
					CooperativaAnotacaoPK pk = new CooperativaAnotacaoPK();
					pk.setCodTipoAnotacao(tipoAnotacao.getId());
					pk.setIdInstituicao(instituicao.getIdInstituicao());
					CooperativaAnotacao cooperativaAnotacao = new CooperativaAnotacao();
					cooperativaAnotacao.setPk(pk);
					lista.add(cooperativaAnotacao);
				}
			}
			tipoAnotacao.setCooperativasAnotacao(lista);
		}
	}

}