package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFormaConstituicaoEsferaAdministrativaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFormaConstituicaoEsferaAdministrativaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFormaConstituicaoEsferaAdministrativaDAO;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa;
import br.com.sicoob.capes.negocio.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK;

/**
 * EJB contendo servicos relacionados a TipoFormaConstituicaoEsferaAdministrativa.
 */
@Stateless
@Local({ TipoFormaConstituicaoEsferaAdministrativaServicoLocal.class })
@Remote({ TipoFormaConstituicaoEsferaAdministrativaServicoRemote.class })
public class TipoFormaConstituicaoEsferaAdministrativaServicoEJB extends CAPESCadastroCrudServicoEJB<TipoFormaConstituicaoEsferaAdministrativa> implements
		TipoFormaConstituicaoEsferaAdministrativaServicoRemote, TipoFormaConstituicaoEsferaAdministrativaServicoLocal {

	@Inject
	@Default
	protected TipoFormaConstituicaoEsferaAdministrativaDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFormaConstituicaoEsferaAdministrativaDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void gravarAlteracoes(Integer codigoEsfera, List<TipoFormaConstituicao> listaConstituicaoSelecionados) throws BancoobException {
		
		ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> consultaDto = prepararConsultaConstituicaoVinculada(codigoEsfera.shortValue());
		List<TipoFormaConstituicaoEsferaAdministrativa> listaExclucao = listar(consultaDto);
		excluirRegistrosAntigos(listaExclucao);
		incluirRegistrosNovos(codigoEsfera, listaConstituicaoSelecionados);
	}
	
	/**
	 * O método Incluir registros novos.
	 *
	 * @param codigoEsfera o valor de codigo esfera
	 * @param listaConstituicaoSelecionados o valor de lista constituicao selecionados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirRegistrosNovos(Integer codigoEsfera, List<TipoFormaConstituicao> listaConstituicaoSelecionados) throws BancoobException {
		for (TipoFormaConstituicao tipoFormaConstituicao : listaConstituicaoSelecionados) {
			TipoFormaConstituicaoEsferaAdministrativa novaEntidade = new TipoFormaConstituicaoEsferaAdministrativa();
			TipoFormaConstituicaoEsferaAdministrativaPK id = new TipoFormaConstituicaoEsferaAdministrativaPK();
			id.setCodigoEsferaAdministrativa(codigoEsfera.shortValue());
			id.setCodigoTipoFormaConstituicao(tipoFormaConstituicao.getCodigo());
			novaEntidade.setId(id);
			incluir(novaEntidade);
		}
	}

	/**
	 * O método Excluir registros antigos.
	 *
	 * @param listaExclucao o valor de lista exclucao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void excluirRegistrosAntigos(List<TipoFormaConstituicaoEsferaAdministrativa> listaExclucao) throws BancoobException {
		for (TipoFormaConstituicaoEsferaAdministrativa tipoFormaConstituicaoEsferaAdministrativa : listaExclucao) {
			excluirEntidade(tipoFormaConstituicaoEsferaAdministrativa);
		}
	}

	/**
	 * Preparar consulta constituicao vinculada.
	 *
	 * @param codigo o valor de codigo
	 * @return ConsultaDto
	 */
	private ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> prepararConsultaConstituicaoVinculada(Short codigo) {
		ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> consultaDto = new ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa>();
		TipoFormaConstituicaoEsferaAdministrativa filtro = new TipoFormaConstituicaoEsferaAdministrativa();
		TipoFormaConstituicaoEsferaAdministrativaPK id = new TipoFormaConstituicaoEsferaAdministrativaPK();
		id.setCodigoEsferaAdministrativa(codigo);
		filtro.setId(id);
		consultaDto.setFiltro(filtro);
		return consultaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<EsferaAdministrativaVO> obterEsferasAdministrativasPorIdFormaConstituicao(Short idFormaConstituicao) throws BancoobException {
		List<EsferaAdministrativaVO> retorno = null;
		ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> consultaDto = new ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa>();
		TipoFormaConstituicaoEsferaAdministrativa filtro = new TipoFormaConstituicaoEsferaAdministrativa();
		TipoFormaConstituicaoEsferaAdministrativaPK id = new TipoFormaConstituicaoEsferaAdministrativaPK();
		id.setCodigoTipoFormaConstituicao(idFormaConstituicao);
		filtro.setId(id);
		consultaDto.setFiltro(filtro);

		List<TipoFormaConstituicaoEsferaAdministrativa> lista = listar(consultaDto);

		if (lista != null && lista.size() > 0) {
			retorno = new ArrayList<EsferaAdministrativaVO>();

			ADMIntegracaoDelegate admDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
			for (TipoFormaConstituicaoEsferaAdministrativa objeto : lista) {
				retorno.add(admDelegate.obterEsferaAdministrativa(objeto.getId().getCodigoEsferaAdministrativa()));
			}
		}

		return retorno;
	}

}