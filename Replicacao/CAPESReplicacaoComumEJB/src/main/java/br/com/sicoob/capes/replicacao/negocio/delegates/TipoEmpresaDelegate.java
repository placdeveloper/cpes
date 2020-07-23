package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.TipoEmpresa;
import br.com.sicoob.capes.replicacao.negocio.servicos.TipoEmpresaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

public class TipoEmpresaDelegate extends EntidadeDominioReplicavelDelegate<TipoEmpresa, TipoEmpresaServico> {

	@Override
	protected TipoEmpresaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarTipoEmpresaServico();
	}

	public TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda renda) throws BancoobException {
		return getServico().obterTipoEmpresaPorFaixaRendaAnual(renda);
	}
	
}