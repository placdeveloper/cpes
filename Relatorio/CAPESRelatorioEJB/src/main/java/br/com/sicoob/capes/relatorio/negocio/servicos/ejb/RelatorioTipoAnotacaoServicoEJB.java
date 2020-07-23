/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioTipoAnotacao;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Implementa as operações do serviço de tipo de anotações.
 * 
 * @author Erico.Junior
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioTipoAnotacaoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	/** O atributo delegate. */
	private TipoAnotacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoAnotacaoDelegate();

	/**
	 * {@inheritDoc}
	 * @throws BancoobException 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		List<TipoAnotacao> lista = delegate.listar();
		
		if (lista == null || lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		
		RelatorioTipoAnotacao relatorio = new RelatorioTipoAnotacao(lista);
		return relatorio.gerarRelatorio(rDto);
	}
	
}