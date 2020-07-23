package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.api.inclusao.negocio.conversores.Conversor;
import br.com.sicoob.capes.api.inclusao.negocio.conversores.FabricaConversores;
import br.com.sicoob.capes.api.inclusao.negocio.dto.CAPESApiInclusaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ExecutarProcedimentoAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoNegocioException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoRuntimeException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.PropriedadeObrigatoriaNaoInformadaException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.RegistroPendenteAprovacaoException;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.CAPESApiInclusaoServico;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizarDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe base dos serviços EJB.
 * 
 * @param <D>
 *            O DTO base do serviço.
 * @param <E>
 *            A Entidade base do serviço.
 * 
 * @author bruno.carneiro
 */
public abstract class CAPESApiInclusaoServicoEJB<D extends RegistroInclusaoDTO<?>, E extends CAPESEntidade<?>> extends BancoobServicoEJB implements CAPESApiInclusaoServico<D> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public D incluir(D dto) throws BancoobException {
		getLogger().debug("Iniciando a inclusao do objeto: ", dto.getClass().getName());
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));

		try {
			// Valida se o DTO contém as informações necessárias para a inclusão do registro.
			validarInclusao(dto);
			
			// Preenche as informações do usuário logado para ser usado durante a sessão.
			preencherInformacoesUsuario(dto);
			
			// Converte o DTO em uma entidade para que seja feita a alteração.
			E entidade = obterEntidade(dto);
			
			// Realiza a inclusão e converte a entidade de retorno para o DTO.
			D retorno = obterDTO(realizarInclusao(entidade, dto));
			
			// Remove as informações do usuário logado.
			removerInformacoesUsuario();
			
			// Retorna o DTO.
			return retorno;
			
		// Se o registro já tiver sido incluído, vamos cancelar a autorização pendente.
		} catch (br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException e) {
			//FIXME bruno.carneiro: Não foi necessário executar o cancelamento de uma autorização de inclusão. 
//			getLogger().debug("[CAPES Api Inclusao] Erro ao incluir: ", e.getMessage());
//			
//			// Converte o DTO em uma entidade para que seja feita a alteração.
//			E entidade = obterEntidade(dto);
//			
//			// Adiciona à entidade o ID do registro que veio da exceção. 
//			ReflexaoUtils.setPropriedade(entidade, "id", e.getIdRegistro());
//			
//			// Realiza o cancelamento da autorização.
//			boolean autorizacaoCancelada = cancelarFluxoAutorizacao(entidade, dto.getJustificativaCancelamentoAutorizacao());
//			
//			// Caso alguma autorização tenha sido cancelada, tentamos executar a operação novamente.
//			if (autorizacaoCancelada) {
//				incluir(dto);
//			// Caso contrário, realmente já existe um registro com essas dados negociais, então lançamos a exceção para o usuário.
//			} else {
				CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
//			}
		// Caso venha a exceção com o registro pendente de autorização, convertemos em uma mensagem mais "amigável" para o usuário.
		} catch (br.com.sicoob.capes.cadastro.negocio.excecao.RegistroPendenteAprovacaoException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao incluir: ", e.getMessage());
			RegistroPendenteAprovacaoException.lancarExcecao(e.getMessage());
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao incluir: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(D dto) throws BancoobException {
		getLogger().debug("Iniciando a alteracao do objeto: ", dto.getClass().getName());
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));
		try {
			// Valida se o DTO contém as informações necessárias para a alteração do registro.
			validarAlteracao(dto);
			
			// Preenche as informações do usuário logado para ser usado durante a sessão.
			preencherInformacoesUsuario(dto);
			
			// Converte o DTO em uma entidade para que seja feita a alteração.
			E entidade = obterEntidade(dto);
			
			// Obtém a entidade do banco para a alteração.
			E entidadeBanco = obterEntidadeBanco(entidade.getId());
			
			// Remove o objeto da sessão. (Caso o fluxo de dupla conferencia esteja ligado, não altera a referência vinculada à sessão).
			removerObjetoSessao(entidadeBanco);
			
			// Copia as propriedades informadas no DTO para a entidade do banco.
			copiarPropriedadesAlteracao(entidadeBanco, entidade);
			
			// Realiza a alteração.
			realizarAlteracao(entidadeBanco, dto);
			
			// Remove as informações do usuário logado.
			removerInformacoesUsuario();
		} catch (br.com.sicoob.capes.cadastro.negocio.excecao.RegistroPendenteAprovacaoException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao alterar: ", e.getMessage());
			RegistroPendenteAprovacaoException.lancarExcecao(e.getMessage());
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao alterar: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluir(D dto) throws BancoobException {
		getLogger().debug("Iniciando a exclusao do objeto: ", dto.getClass().getName());
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));
		try {
			// Valida se o DTO tem as informações necessárias para realizar a exclusão do registro.
			validarExclusao(dto);
			
			// Preenche as informações do usuário logado para ser usado durante a sessão.
			preencherInformacoesUsuario(dto);
			
			// Obtém a entidade do banco para a exclusão.
			E entidade = obterEntidadeBanco(dto.getId());
			
			// Se a entidade realmente existir, faz a exclusão.
			if(entidade != null){
				realizarExclusao(entidade);
			}
			
			// Remove as informações do usuário logado.
			removerInformacoesUsuario();
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao excluir: ", e.getMessage());
			throw new CAPESApiInclusaoNegocioException(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao excluir");
			throw new CAPESApiInclusaoException(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao excluir");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao excluir");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String encaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException {
		getLogger().debug("Iniciando o encaminhamento do objeto: ", dto.getClass().getName());
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));
		String idRegistroControlado = null;
		
		try {
			// Valida se o DTO tem as informações necessárias para encaminhar.
			validarPropriedadesEncaminharAutorizacao(dto);
			
			// Preenche as informações do usuário logado para ser usado durante a sessão.
			preencherInformacoesUsuario(dto);

			// Obtém a classe do serviço utilizado.
			Class<?> classe = ReflexaoUtils.obterParametroGenerico(getClass(), 1);
			
			// Obtém o tipo de entidade usada na autorização.
			TipoAutorizacaoEntidadeEnum tipoAutorizacaoEntidadeEnum = TipoAutorizacaoEntidadeEnum.obterPorClasse(classe);

			// Caso a entidade informada seja encontrada.
			if (tipoAutorizacaoEntidadeEnum != null) {
				ConsultaAutorizacaoDTO criterios = new ConsultaAutorizacaoDTO();
				criterios.setIdPessoaSelecionada(dto.getIdPessoa());
				criterios.setIdInstituicaoUsuario(dto.getIdInstituicaoRegistro());
				criterios.setIdRegistro(dto.getIdRegistro());
				
				// Atualiza os responsáveis pela autorização (instituição de destino)
				obterDelegateAutorizar().atualizarResponsavelAutorizacao(criterios);
				
				// Encaminha as autorizações
				obterDelegateAutorizar().encaminharAutorizacoes(criterios);

				// Obtém o registro do banco para saber o IDREGISTROCONTROLADO
				EntidadeCadastroDelegate<?, ?> delegateEntidade = (EntidadeCadastroDelegate<?, ?>) obterFabricaCadastro().criarDelegate(classe);
				Aprovavel aprovavel = (Aprovavel) delegateEntidade.obter(dto.getIdRegistro());
				
				if(aprovavel != null){
					idRegistroControlado = aprovavel.getIdRegistroControlado();
				}
			}
			
			// Remove as informações do usuário logado.
			removerInformacoesUsuario();
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao encaminhar a autorizacao: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao encaminhar a autorizacao");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao encaminhar a autorizacao");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao encaminhar a autorizacao");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
		return idRegistroControlado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void executarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException {
		getLogger().debug("Iniciando a execucao do procedimento do objeto: ", dto.getClass().getName());
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));
		try {
			// Verifica se o DTO possui as informações para executar o procedimento.
			validarPropriedadesExecutarProcedimentoAutorizacao(dto);
			
			// Preenche as informações do usuário logado para ser usado durante a sessão.
			preencherInformacoesUsuario(dto);

			// Instancia a nova entidade.
			E entidade = instanciarEntidadeAprovacao();

			if (entidade != null && entidade instanceof Aprovavel) {
				// Copia o ID do registro para a entidade.
				ReflexaoUtils.setPropriedade(entidade, "id", dto.getIdRegistro());

				// Obtém a autorização de acordo com as informações.
				AutorizacaoDelegate autorizacaoDelegate = obterFabricaCadastro().criarAutorizacaoDelegate();
				Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade((Aprovavel) entidade);

				if (autorizacao != null) {
					// Obter a ocorrencia da ativadade.
					OcorrenciaAtividadeVO procedimentoVO = new OcorrenciaAtividadeVO();
					procedimentoVO.setIdOcorrenciaAtividade(dto.getCodigoOcorrenciaAtividade());
					procedimentoVO.setNomeProcedimento(dto.getNomeProcedimento());

					// Executa o procedimento informado.
					AutorizarDelegate autorizarDelegate = obterDelegateAutorizar();
					autorizarDelegate.executarProcedimento(autorizacao, procedimentoVO, dto.getJustificativa());
				}
			}
			
			// Remove as informações do usuário logado.
			removerInformacoesUsuario();
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao aprovar a autorizacao: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao aprovar a autorizacao");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao aprovar a autorizacao");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao aprovar a autorizacao");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cancelarFluxoAutorizacao(D dto, String justificativa) throws BancoobException {
		getLogger().debug("Cancelando o fluxo de autorizacao da classe =", dto.getClass() + " e id=", String.valueOf(dto.getId()));
		try {
			// Verifica se foram informadas as propriedades necessárias para o cancelamento
			validarPropriedades(dto, new String[] { "id", "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao" });

			// Se não foi informada uma justificativa para o cancelamento, lançamos uma exceção
			if (justificativa == null || justificativa.length() == 0 || "".equals(justificativa.trim())) {
				throw new PropriedadeObrigatoriaNaoInformadaException("justificativa");
			}

			// Preenche as informações do usuário logado.
			preencherInformacoesUsuario(dto);
			
			// Converte o DTO em uma entidade;
			E entidade = obterEntidade(dto);

			if (entidade instanceof Aprovavel) {
				// Obtém a autorização do registro informado.
				AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
				Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade((Aprovavel) entidade);
				
				getLogger().debug("Foi encontrada autorizacao para cancelamento: ", String.valueOf(autorizacao != null ? true : false));
				
				// Se a autorização foi encontrada, cancelamos o fluxo.
				if (autorizacao != null) {
					getLogger().debug("ID da Autorizacao = ", String.valueOf(autorizacao.getId()));
					autorizacaoDelegate.cancelarAutorizacaoVencida(autorizacao, justificativa);
				}
			}
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao cancelar o fluxo de autorizacao: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao cancelar o fluxo de autorizacao");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao cancelar o fluxo de autorizacao");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao cancelar o fluxo de autorizacao");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}

	/**
	 * Instancia a entidade para a aprovação;
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected E instanciarEntidadeAprovacao() {
		Class<?> classe = ReflexaoUtils.obterParametroGenerico(getClass(), 1);
		E entidade = (E) ReflexaoUtils.instanciar(classe);
		return entidade;
	}

	/**
	 * Método que realiza a inclusão pelo delegate correto.
	 * 
	 * @param entidade
	 *            A entidade a ser incluida.
	 * @param dto
	 *            O DTO para ser usado em algum caso específico.
	 * 
	 * @return A entidade que foi incluida.
	 * @throws BancoobException
	 */
	protected E realizarInclusao(E entidade, D dto) throws BancoobException {
		getLogger().debug("Realizando a inclusao da entidade: ", entidade.getClass().getName());
		return obterDelegate().incluir(entidade);
	}

	/**
	 * Método que realiza a alteração pelo delegate correto.
	 * 
	 * @param entidade
	 *            A entidade a ser alterada
	 * @param dto
	 *            O DTO para ser usado em algum caso específico.
	 * @throws BancoobException
	 */
	protected void realizarAlteracao(E entidade, D dto) throws BancoobException {
		getLogger().debug("Realizando a alteracao da entidade: ", entidade.getClass().getName());
		obterDelegate().alterar(entidade);
	}
	
	/**
	 * Método que realiza a exclusão do registro pelo delegate correto.
	 * 
	 * @param entidade
	 *            A entidade a ser excluida
	 * @throws BancoobException
	 */
	protected void realizarExclusao(E entidade) throws BancoobException {
		getLogger().debug("Realizando a exclusao da entidade: ", entidade.getClass().getName());
		obterDelegate().excluirEntidade(entidade);
	}

	/**
	 * Método que obtém a entidade do banco
	 * 
	 * @param id
	 *            O identificador do registro
	 * @return
	 * @throws BancoobException
	 */
	protected E obterEntidadeBanco(Serializable id) throws BancoobException {
		getLogger().debug("Obtendo a entidade do banco: ", String.valueOf(id));
		return obterDelegate().obter(id);
	}
	
	/**
	 * Remove o objeto da sessão.
	 * 
	 * Solução para alteração do objeto que estava na sessão e passa
	 * pelo fluxo de aprovação. Ao alterar o objeto com o fluxo ligado, as
	 * alterações feitas no objeto vinculado à sessão eram enviadas para o
	 * banco, ignorando o fluxo.
	 * 
	 * @param entidadeBanco
	 * @throws BancoobException 
	 */
	protected void removerObjetoSessao(E entidadeBanco) throws BancoobException {
		obterDelegate().removerObjetoSessao(entidadeBanco);
	}

	/**
	 * Obtém a pessoa compartilhamento por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 *            O identificador da pessoa
	 * @param idInstituicao
	 *            o identificador da instituição
	 * @return a {@code PessoaCompartilhamento}
	 * @throws BancoobException
	 */
	protected PessoaCompartilhamento obterPessoaCompartilhamento(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		getLogger().debug("Obtendo a pessoaCompartilhamento. idPessoa=", String.valueOf(idPessoa), " idInstituicao=", String.valueOf(idInstituicao));
		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = obterFabricaCadastro().criarPessoaCompartilhamentoDelegate();
		return pessoaCompartilhamentoDelegate.consultarPorIdPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Copia as propriedades para a alteração da entidade.
	 * 
	 * @param entidadeFonte
	 * @param entidadeBanco
	 */
	protected void copiarPropriedadesAlteracao(E entidadeDestino, E entidadeFonte) {
		ReflexaoUtils.copiarPropriedades(entidadeDestino, entidadeFonte, "idInstituicaoAtualizacao", "idRegistroControlado", "codigoSituacaoAprovacao", "situacaoAprovacao");
	}

	/**
	 * Obtém o delegate à partir da entidade informada.
	 * 
	 * @param entidade
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	protected CAPESCadastroCrudDelegate<E, ?> obterDelegate() throws BancoobException {
		Class<?> classe = ReflexaoUtils.obterParametroGenerico(getClass(), 1);
		return (CAPESCadastroCrudDelegate<E, ?>) obterFabricaCadastro().criarDelegate(classe);
	}

	/**
	 * Obtém o delegate Autorizar
	 * 
	 * @return a instância do {@code AutorizarDelegate}
	 * @throws BancoobException
	 */
	protected AutorizarDelegate obterDelegateAutorizar() throws BancoobException {
		return obterFabricaCadastro().criarAutorizarDelegate();
	}

	/**
	 * Obtém a fábrica de delegates do projeto de Cadastro.
	 * 
	 * @return A instância da fábrica de delegates.
	 */
	protected CAPESCadastroFabricaDelegates obterFabricaCadastro() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}

	/**
	 * Obtém a entidade à partir do DTO informado.
	 * 
	 * @param dto
	 *            O DTO para ser convertido em uma entidade.
	 * @return A entidade convertida à partir do dto.
	 * @throws BancoobException
	 */
	protected E obterEntidade(D dto) throws BancoobException {
		getLogger().debug("Convertendo o DTO para entidade");
		Conversor<E, D> conversor = obterConversor(dto);
		E entidade = conversor.obterEntidade(dto);
		getLogger().debug("DTO convertido com sucesso.");
		return entidade;
	}

	/**
	 * Obtém o DTO à partir da entidade informada.
	 * 
	 * @param entidade
	 *            A entidade para ser convertida em um DTO.
	 * @return O DTO convertido à partir da entidade.
	 * @throws BancoobException
	 */
	protected D obterDTO(E entidade) throws BancoobException {
		getLogger().debug("Convertendo a entidade para DTO");
		Conversor<E, D> conversor = obterConversor(entidade);
		D dto = conversor.obterDTO(entidade);
		getLogger().debug("Entidade convertida com sucesso.");
		return dto;
	}

	/**
	 * Obtém o conversor correto à partir do DTO.
	 * 
	 * @param dto
	 *            O DTO para criação do conversor.
	 * @return o conversor correto.
	 */
	@SuppressWarnings({ "unchecked" })
	private Conversor<E, D> obterConversor(D dto) {
		FabricaConversores<E, D> fabrica = (FabricaConversores<E, D>) FabricaConversores.obterInstancia();
		Conversor<E, D> conversor = fabrica.obterConversor(dto);
		return conversor;
	}

	/**
	 * Obtém o conversor correto à partir da entidade.
	 * 
	 * @param entidade
	 *            A entidade para criação do conversor.
	 * @return o conversor correto.
	 */
	@SuppressWarnings({ "unchecked" })
	private Conversor<E, D> obterConversor(E entidade) {
		FabricaConversores<E, D> fabrica = (FabricaConversores<E, D>) FabricaConversores.obterInstancia();
		Conversor<E, D> conversor = fabrica.obterConversor(entidade);
		return conversor;
	}

	/**
	 * Preenche as informações do usuário para preencher as informações do
	 * cadastro tanto na criação dos fluxos, quanto na inclusão das informações.
	 * 
	 * @param dto
	 *            O DTO com as informações necessárias para a criação do
	 *            {@code InformacoesUsuarioCAPES}
	 * @throws BancoobException
	 */
	@SuppressWarnings("deprecation")
	protected void preencherInformacoesUsuario(CAPESApiInclusaoDTO dto) throws BancoobException {
		getLogger().debug("Preenchendo as informacoes do usuario: ", obterInformacoesObjeto(dto));
		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setIdInstituicao(dto.getIdInstituicao().toString());
		usuarioBancoob.setIdUnidadeInstituicao(dto.getIdUnidadeInst().toString());
		usuarioBancoob.setLogin(dto.getIdUsuarioAprovacao());
		InformacoesUsuario info = new InformacoesUsuario(usuarioBancoob);
		InformacoesUsuario.INSTANCIA.set(info);

		GrupoCompartilhamentoDelegate grupoCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarGrupoCompartilhamentoDelegate();
		GrupoCompartilhamento grupo = grupoCompartilhamentoDelegate.obterPorInstituicao(dto.getIdInstituicao());

		InformacoesUsuarioCAPES.setInstance(new InformacoesUsuarioCAPES(info, grupo.getCompartilhamentoCadastro().getCodigo()));
	}
	
	/**
	 * Remove a instância do informações usuário.
	 * 
	 * @throws BancoobException
	 */
	protected void removerInformacoesUsuario() throws BancoobException {
		InformacoesUsuario.INSTANCIA.remove();
		InformacoesUsuarioCAPES.removeInstance();
	}
	
	/**
	 * Obtém as informações do usuário.
	 * 
	 * @return {@code UsuarioBancoob} com as informações.
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
	
	/**
	 * Realiza a validação do DTO antes da inclusão.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem validadas.
	 * @throws BancoobException
	 */
	protected void validarInclusao(D dto) throws BancoobException {
		validarPropriedadesBasicas(dto);
		validarPropriedadesObrigatoriasInclusao(dto);
	}

	/**
	 * Realiza a validação do DTO antes da alteração.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem validadas.
	 * @throws BancoobException
	 */
	protected void validarAlteracao(D dto) throws BancoobException {
		validarPropriedadesBasicas(dto);
		validarPropriedadesObrigatoriasAlteracao(dto);
	}
	
	/**
	 * Realiza a validação do DTO antes da exclusão.
	 * 
	 * @param dto
	 *            O DTO com as informações para serem validadas.
	 * @throws BancoobException
	 */
	private void validarExclusao(D dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao", "id" });
	}

	/**
	 * Realiza a validação das propriedades básicas que devem sempre ser
	 * informadas nas operações.
	 * 
	 * @param dto
	 *            O DTO com as informações a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesBasicas(D dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idPessoa", "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao" });
	}

	/**
	 * Realiza a validação das propriedades informadas para encaminhar uma
	 * autorização
	 * 
	 * @param dto
	 *            O DTO com as informações a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesEncaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idUsuarioAprovacao", "idInstituicao", "idUnidadeInst", "idRegistro", "idPessoa" });
	}

	/**
	 * Realiza a validação das propriedades informadas para a execução de um
	 * procedimento
	 * 
	 * @param dto
	 *            O DTO com as informações a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesExecutarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idUsuarioAprovacao", "idInstituicao", "idUnidadeInst", "idRegistro", "codigoOcorrenciaAtividade", "nomeProcedimento" });
	}

	/**
	 * Realiza a validação das propriedades obrigatórias para inclusão de uma
	 * entidade.
	 * 
	 * @param dto
	 *            O DTO com as informações a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesObrigatoriasInclusao(D dto) throws BancoobException {
		validarPropriedades(dto, obterPropriedadesObrigatoriasInclusao());
	}

	/**
	 * Realiza a validação das propriedades obrigatórias para alteração de uma
	 * entidade.
	 * 
	 * @param dto
	 *            O DTO com as informações a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesObrigatoriasAlteracao(D dto) throws BancoobException {
		validarPropriedades(dto, obterPropriedadesObrigatoriasAlteracao());
	}
	
	/**
	 * Valida as propriedades
	 * 
	 * @param dto
	 *            O DTO com as informações a serem validadas.
	 * @param propriedades
	 *            As propriedades a serem verificadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedades(CAPESApiInclusaoDTO dto, String[] propriedades) throws BancoobException {
		List<String> propriedadesNaoPreenchidades = verificarPropriedades(dto, propriedades);
		
		if (propriedadesNaoPreenchidades != null && !propriedadesNaoPreenchidades.isEmpty()) {
			throw new PropriedadeObrigatoriaNaoInformadaException(ReflexaoUtils.juntarInformacoes(propriedadesNaoPreenchidades.toArray(), ", "));
		}
	}

	/**
	 * Método para ser preenchido pelos serviços com o nome das propriedades
	 * obrigatórias para inclusão.
	 * 
	 * @return {@code String[]} com o nome das propriedades obrigatórias para a
	 *         inclusão.
	 */
	protected abstract String[] obterPropriedadesObrigatoriasInclusao();

	/**
	 * Método para ser preenchido pelos serviços com o nome das propriedades
	 * obrigatórias para alteração.
	 * 
	 * @return {@code String[]} com o nome das propriedades obrigatórias para a
	 *         alteração.
	 */
	protected abstract String[] obterPropriedadesObrigatoriasAlteracao();
	
	/**
	 * Retorna uma {@code String} com as informações do objeto com a formatação
	 * abaixo:
	 * 
	 * <pre>
	 * Pessoa@182f0db[
	 *    nome=Maria,
	 *    sobrenome=Tereza,
	 *    sexo=F
	 *  ]
	 * </pre>
	 * 
	 * @param objeto
	 *            o objeto com as informações para serem extraídas.
	 * @return {@code String} com as informações do objeto
	 */
	protected String obterInformacoesObjeto(Object objeto) {
		return ToStringBuilder.reflectionToString(objeto, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	/**
	 * Método que valida as propriedades obrigatórias no DTO.
	 * 
	 * @param dto
	 *            O DTO a ter as propriedades verificadas.
	 * @param propriedades
	 *            O nome das propriedades a serem verificadas.
	 * 
	 * @return {@code Boolean} se as informações são válidas ou não.
	 */
	private List<String> verificarPropriedades(CAPESApiInclusaoDTO dto, String... propriedades) {
		List<String> retorno = new ArrayList<String>();
		if (propriedades != null) {
			getLogger().debug("[CAPES Api Inclusao] validando as propriedades: ", Arrays.toString(propriedades));

			for (String propriedade : propriedades) {
				getLogger().debug("[CAPES Api Inclusao] validando a propriedade: ", propriedade);
				Object valor = ReflexaoUtils.getValorAtributo(dto, propriedade);

				if (valor == null) {
					retorno.add(propriedade);
				}

				if (valor instanceof String) {
					String texto = (String) valor;
					if (texto.trim().length() == 0) {
						retorno.add(propriedade);
					}
				}
			}
		}
		return retorno;
	}
	
}