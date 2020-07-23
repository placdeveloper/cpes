package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import javax.ejb.EJB;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.cadastro.negocio.excecao.CadastroJaExisteInstituicaoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;

public abstract class CompartilharCadastroGrupoEconomicoStep extends CAPESStepBase {

	@EJB
	private transient ReplicacaoCadastroServicoLocal servicoReplicacao;
	
	@EJB
	private transient GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico; 

	@Override
	protected final RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		final Long idPessoaCompartilhamento = contexto.getParametrosStep().get("idPessoaCompartilhamento").getValor();
		final Integer idInstituicao = getParametroDinamico(contexto);
		preencherInformacoesUsuario(idInstituicao);
		try {
			compartilharCadastro(contexto, idPessoaCompartilhamento);
		} catch (CadastroJaExisteInstituicaoException e) {
			getLogger().alerta(e.getMessage());
		}
		return sucesso();
	}
	
	/**
	 * 
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	@SuppressWarnings("deprecation")
	private void preencherInformacoesUsuario(Integer idInstituicao) throws BancoobException {
		getLogger().debug("Preenchendo as informacoes do usuario");
		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setIdInstituicao(idInstituicao.toString());
		final Integer idUnidadeInst;
		if(idInstituicao.equals(Constantes.Comum.ID_INSTITUICAO_BANCOOB)) {
			idUnidadeInst = Constantes.Comum.ID_UNIDADEINST_AGENCIA_SEDE;
		} else {
			idUnidadeInst = Constantes.Comum.ID_UNIDADEINST_ZERO;
		}
		usuarioBancoob.setIdUnidadeInstituicao(idUnidadeInst.toString());
		usuarioBancoob.setLogin("capes-grupoeconomico");
		InformacoesUsuario info = new InformacoesUsuario(usuarioBancoob);
		InformacoesUsuario.INSTANCIA.set(info);

		GrupoCompartilhamento grupo = grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);

		InformacoesUsuarioCAPES.setInstance(new InformacoesUsuarioCAPES(info, grupo.getCompartilhamentoCadastro().getCodigo()));
	}
	
	/**
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	protected abstract PessoaCompartilhamento compartilharCadastro(ContextoExecucao contexto, Long idPessoaCompartilhamento) throws BancoobException;

	@SuppressWarnings("unchecked")
	protected <T> T getParametroDinamico(ContextoExecucao contexto) {
		return (T) contexto.getParametroDinamico().getValor();
	}

}
