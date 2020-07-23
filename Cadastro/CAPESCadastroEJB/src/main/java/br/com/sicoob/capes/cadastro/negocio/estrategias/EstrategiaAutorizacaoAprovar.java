package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;

/**
 * 15/02/2011
 *
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoAprovar extends EstrategiaAutorizacaoFinalizacao {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		
		aprovavel.setIdInstituicaoAtualizacao(null);
		aprovavel.setVerificarAutorizacao(false);
		tratarHistorico(aprovavel, false);
		anexarDocumentosDossie(autorizacao);
		alterar(aprovavel);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
		
		// recupera a annotação para acessar o nome do ID
		CamposAutorizacao camposAutorizacao = tipoAutorizacao.getTipo().getAnnotation(CamposAutorizacao.class);
		
		// prepara o objeto para ser persistido
		Aprovavel entidadeAlteracao = obterEntidadeAlteracao(tipoAutorizacao, aprovavel);
		ReflexaoUtils.copiarPropriedades(entidadeAlteracao, aprovavel, "id", camposAutorizacao.id());
		ReflexaoUtils.setPropriedade(entidadeAlteracao, camposAutorizacao.id(), autorizacao.getIdRegistroAntigo());
		entidadeAlteracao.setIdInstituicaoAtualizacao(null);
		entidadeAlteracao.setVerificarAutorizacao(false);
		
		tratarAlteracao(autorizacao, aprovavel, entidadeAlteracao);
	}
	
	/**
	 * O método Tratar alteracao.
	 *
	 * @param autorizacao
	 *            o valor de autorizacao
	 * @param aprovavel
	 *            o valor de aprovavel
	 * @param entidadeAlteracao
	 *            o valor de entidade alteracao
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel, Aprovavel entidadeAlteracao) throws BancoobException {
		
		anexarDocumentosDossie(autorizacao);
		
		// realiza a alteração do registro
		alterar(entidadeAlteracao);
		
		// exclui o registro novo
		excluirDadosAprovacao(aprovavel);
		
	}
	
	/**
	 * Obter entidade alteracao.
	 *
	 * @param tipoAutorizacao
	 *            o valor de tipo autorizacao
	 * @param aprovavel
	 *            o valor de aprovavel
	 * @return Aprovavel
	 */
	protected Aprovavel obterEntidadeAlteracao(TipoAutorizacaoEntidadeEnum tipoAutorizacao, Aprovavel aprovavel) {
		return (Aprovavel) ReflexaoUtils.construirObjetoPorClasse(tipoAutorizacao.getTipo());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarExclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		aprovavel.setIdInstituicaoAtualizacao(null);
		aprovavel.setVerificarAutorizacao(false);
		desanexarDocumentosDossie(autorizacao, aprovavel);
		excluir(aprovavel);
	}
	
	/**
	 * O método Anexar documentos dossie.
	 *
	 * @param autorizacao
	 *            o valor de autorizacao
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected void anexarDocumentosDossie(Autorizacao autorizacao) throws BancoobException {
		
		if ((autorizacao.getDocumentos() != null) && !autorizacao.getDocumentos().isEmpty()) {
			LOGGER.info("Anexando documentos ao dossie: ", autorizacao.getIdRegistroControlado(), "(", String.valueOf(autorizacao.getIdAutorizacao()), ")");
			
			GEDIntegracaoDTO dadosGED = IntegracaoUtil.criarGEDIntegracaoDTO(autorizacao);
			
			GEDIntegracaoDelegate delegateGED = getFabricaDelegatesIntegracao().criarGEDIntegracaoDelegate();
//			if (!delegateGED.isDossieExistente(dadosGED)) {
//				LOGGER.info("Dossie nao encontrado. Criando dossie da pessoa: ", autorizacao.getPessoa().getIdPessoa().toString());
//				delegateGED.criarDossie(dadosGED);
//			}
			
			delegateGED.associarDocumentosDossie(dadosGED);
		}
	}
	
	/**
	 * O método Desanexar documentos dossie.
	 *
	 * @param autorizacao
	 *            o valor de autorizacao
	 * @param aprovavel
	 *            o valor de aprovavel
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected void desanexarDocumentosDossie(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		
		if (aprovavel instanceof Comprovavel) {
			LOGGER.info("Desanexando documentos do dossie: ", autorizacao.getIdRegistroControlado(), "(", String.valueOf(autorizacao.getIdAutorizacao()), ")");
			GEDIntegracaoDelegate delegateGED = getFabricaDelegatesIntegracao().criarGEDIntegracaoDelegate();
			delegateGED.desassociarDocumentosDossie(autorizacao.getIdRegistroControlado(), autorizacao.getPessoa().getTipoPessoa().getCodTipoPessoa());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirAutorizacao(Autorizacao autorizacao) throws BancoobException {
		Long idAutorizacao = autorizacao.getIdAutorizacao();
		LOGGER.debug("Excluindo a autorizacao: ", String.valueOf(idAutorizacao));
		getFabricaDelegatesComum().criarAutorizacaoDelegate().excluir(idAutorizacao);
	}
	
}
