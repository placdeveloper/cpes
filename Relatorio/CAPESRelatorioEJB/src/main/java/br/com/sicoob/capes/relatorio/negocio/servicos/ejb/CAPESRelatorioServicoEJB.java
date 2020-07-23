package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.relatorio.negocio.servicos.CAPESRelatorioServico;

/**
 * Implementacao base de todos os servicos do sistema CAPESRelatorio
 * 
 * @author Jarbasjstefanini
 */
public abstract class CAPESRelatorioServicoEJB extends BancoobServicoEJB implements CAPESRelatorioServico {
	
	/**
	 * Recupera a institui��o do usu�rio logado.
	 * @return a institui��o do usu�rio logado.
	 */
	protected Instituicao obterInstituicaoUsuarioLogado() {
		InformacoesUsuarioCAPES usuario = InformacoesUsuarioCAPES.getInstance();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		return instituicao;
	}
	
	/**
	 * Recupera a institui��o do usu�rio para os relat�rios em processamento.
	 * 
	 * @return a institui��o do usu�rio.
	 */
	protected Instituicao obterInstituicaoUsuarioRelatorio() {
		InformacoesUsuario usuario = InformacoesUsuario.getInstance();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		return instituicao;
	}
	
	/**
	 * 
	 * @return
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
	
	/**
	 * Obter instituicao por id.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return InstituicaoVO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	protected InstituicaoVO obterInstituicaoPorId(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciIntegracaoDelegate.obterInstituicaoPorId(idInstituicao);
	}

}