package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.integracao.negocio.servicos.CAPESIntegracaoServico;

/**
 * Implementacao base de todos os servicos do sistema CAPESIntegracao
 * 
 * @author bruno.carneiro
 */
public abstract class CAPESIntegracaoServicoEJB extends BancoobServicoEJB implements CAPESIntegracaoServico {

	/**
	 * Obter usuario.
	 * 
	 * @return UsuarioBancoob
	 */
	protected UsuarioBancoob obterUsuario() {

		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();

		if (informacoes == null) {
			throw new UnsupportedOperationException("O InformacoesUsuarioCAPES não foi instanciado!");
		}

		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setCooperativa(informacoes.getCooperativa());
		usuarioBancoob.setIdInstituicao(informacoes.getIdInstituicao());
		usuarioBancoob.setIdUnidadeInstituicao(informacoes.getIdUnidadeInstituicao());
		usuarioBancoob.setLogin(informacoes.getLogin());
		usuarioBancoob.setPac(informacoes.getPac());

		return usuarioBancoob;
	}
}