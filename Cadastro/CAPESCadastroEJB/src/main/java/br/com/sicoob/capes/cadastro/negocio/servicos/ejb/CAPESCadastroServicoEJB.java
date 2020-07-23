package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroServico;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Implementacao base de todos os servicos do sistema CadastroUnicoClientesComum
 * 
 * @author Jarbasjstefanini
 */
public abstract class CAPESCadastroServicoEJB extends BancoobServicoEJB implements CAPESCadastroServico {

	/**
	 * Recupera a instituição do usuário logado.
	 * @return a instituição do usuário logado.
	 */
	protected Instituicao obterInstituicaoUsuarioLogado() {

		UsuarioBancoob usuario = obterUsuario();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		instituicao.setNumero(String.valueOf(usuario.getCooperativa()));
		return instituicao;
	}

	/**
	 * Recupera o usuário logado
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