package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.replicacao.negocio.servicos.TransicaoReplicacaoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESServicoReplicacaoServiceLocator;

/**
 * Businesse delegate de TransicaoReplicacao
 * @author Juan.Damasceno
 *
 */
public class TransicaoReplicacaoDelegate extends 
	CAPESServicoReplicacaoCrudDelegate<TransicaoReplicacao, TransicaoReplicacaoServico>{
	
	/**
	 * Lista as TransicaoReplicacao que ainda não foram replicadas.
	 * @return as TransicaoReplicacao que ainda não foram replicadas.
	 */
	public List<TransicaoReplicacao> listarNaoReplicados() {
		return getServico().listarNaoReplicados();
	}
	
	/**
	 * Lista as TransicaoReplicacao que ainda não foram replicadas.
	 * @return as TransicaoReplicacao que ainda não foram replicadas.
	 */
	public void removerReplicados(Date data) throws BancoobException {
		getServico().removerReplicados(data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TransicaoReplicacaoServico localizarServico() {
		return CAPESServicoReplicacaoServiceLocator.getInstance().localizarServicoTransacaoReplicacao();
	}
}