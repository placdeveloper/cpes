package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.cadastro.negocio.excecao.InformacaoExistenteException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.BemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

@Stateless
@Remote(StepServico.class)
public class BemSemPatrimonioStepEJB extends CAPESStepBase {
	
	@EJB
	private BemServicoLocal servico;
	
	@EJB
	private PessoaCompartilhamentoServicoLocal pessoaServico;
	
	@EJB
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico; 
	
	@Override
	protected final RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		Long idPessoa = contexto.getParametrosStep().get("idPessoacompartilhamento").getValor();
		Integer idInstituicao = contexto.getParametrosStep().get("idInstituicao").getValor(); 
		
		preencherInformacoesUsuario(idInstituicao);
		PessoaCompartilhamento pessoa = pessoaServico.obter(idPessoa);
		
		try {
			servico.adicionarBemSemPatrimonio(pessoa);
		} catch (InformacaoExistenteException e) {
			getLogger().debug(e.getMessage());
		}
		return sucesso();
	}
	
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
		usuarioBancoob.setLogin("capes-sempatrimonio");
		InformacoesUsuario info = new InformacoesUsuario(usuarioBancoob);
		InformacoesUsuario.INSTANCIA.set(info);

		GrupoCompartilhamento grupo = grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);

		InformacoesUsuarioCAPES.setInstance(new InformacoesUsuarioCAPES(info, grupo.getCompartilhamentoCadastro().getCodigo()));
	}
}
