package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESReplicacaoComumServico;

/**
 * Implementacao base de todos os servicos do sistema ReplicacaoClientesBO
 * 
 * @author Jarbasjstefanini
 */
public abstract class CAPESReplicacaoComumServicoEJB extends BancoobServicoEJB implements
		CAPESReplicacaoComumServico {

	/**
	 * Recupera o número da cooperativa a partir do idInstituicao informado.
	 * 
	 * @param instituicao
	 *            A instituição.
	 * @return O número da cooperativa a partir do idInstituicao informado.
	 */
	protected Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterNumeroCooperativa(idInstituicao);
	}

	/**
	 * Recupera o login do usuário logado
	 *
	 * @return o usuário
	 */
	protected String recuperarLoginSemDominio() {
		
		UsuarioBancoob usuario = obterUsuario();
		return usuario.getLogin();
	}
	
	/**
	 * Obter usuario.
	 *
	 * @return UsuarioBancoob
	 */
	protected UsuarioBancoob obterUsuario() {
		UsuarioBancoob usuarioBancoob = null;
		InformacoesUsuario usuario = InformacoesUsuario.getInstance();
		if (usuario != null) {
			usuarioBancoob = new UsuarioBancoob();
			usuarioBancoob.setCooperativa(usuario.getCooperativa());
			usuarioBancoob.setDominio(usuario.getDominio());
			usuarioBancoob.setIdInstituicao(usuario.getIdInstituicao());
			usuarioBancoob.setIdUnidadeInstituicao(usuario.getIdUnidadeInstituicao());
			usuarioBancoob.setLogin(usuario.getLogin());
			usuarioBancoob.setPac(usuario.getPac());
		}
		return usuarioBancoob;
	}
}
