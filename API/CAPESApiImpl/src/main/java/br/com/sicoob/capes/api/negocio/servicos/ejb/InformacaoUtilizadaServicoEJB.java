package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InformacaoUtilizadaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InformacaoUtilizadaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.InformacaoUtilizadaDelegate;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;
import br.com.sicoob.capes.negocio.entidades.pk.InformacaoUtilizadaPK;

/**
 * EJB contendo servicos relacionados a InformacaoUtilizada.
 */
@Stateless
@Local(InformacaoUtilizadaServicoLocal.class)
@Remote(InformacaoUtilizadaServicoRemote.class)
public class InformacaoUtilizadaServicoEJB extends CAPESApiServicoEJB implements InformacaoUtilizadaServicoLocal, InformacaoUtilizadaServicoRemote {

	/** O atributo informacaoUtilizadaDelegateCadastro. */
	private transient InformacaoUtilizadaDelegate informacaoUtilizadaDelegateCadastro = CAPESCadastroFabricaDelegates.getInstance()
			.criarInformacaoUtilizadaDelegate();

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluirInformacaoUtilizada(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException {
		InformacaoUtilizada entidade = converterEntidade(informacaoUtilizadaVO);
		
		ConsultaDto<InformacaoUtilizada> criterios = new ConsultaDto<InformacaoUtilizada>();
		criterios.setFiltro(entidade);
		List<InformacaoUtilizada> listaRetorno = informacaoUtilizadaDelegateCadastro.pesquisar(criterios).getResultado();
		
		if(listaRetorno == null || listaRetorno.isEmpty()){
			informacaoUtilizadaDelegateCadastro.incluir(entidade);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirInformacaoUtilizada(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException {
		InformacaoUtilizada entidade = converterEntidade(informacaoUtilizadaVO);
		informacaoUtilizadaDelegateCadastro.excluirEntidade(entidade);
	}

	/**
	 * Converter entidade.
	 *
	 * @param vo o valor de vo
	 * @return InformacaoUtilizada
	 */
	private InformacaoUtilizada converterEntidade(InformacaoUtilizadaVO vo) {
		InformacaoUtilizada retorno = null;

		if (vo != null) {
			InformacaoUtilizadaPK pk = new InformacaoUtilizadaPK();
			pk.setCodigoInformacao(vo.getCodigoInformacao());
			pk.setCodigoTipoInformacao(vo.getCodigoTipoInformacao());
			pk.setCodigoUtilizaInformacao(vo.getCodigoUtilizaInformacao());

			retorno = new InformacaoUtilizada();
			retorno.setId(pk);
		}

		return retorno;
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return null;
	}

}