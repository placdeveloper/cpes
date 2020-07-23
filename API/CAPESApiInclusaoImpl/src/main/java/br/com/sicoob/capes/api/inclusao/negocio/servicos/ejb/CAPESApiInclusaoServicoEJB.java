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
 * Classe base dos servi�os EJB.
 * 
 * @param <D>
 *            O DTO base do servi�o.
 * @param <E>
 *            A Entidade base do servi�o.
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
			// Valida se o DTO cont�m as informa��es necess�rias para a inclus�o do registro.
			validarInclusao(dto);
			
			// Preenche as informa��es do usu�rio logado para ser usado durante a sess�o.
			preencherInformacoesUsuario(dto);
			
			// Converte o DTO em uma entidade para que seja feita a altera��o.
			E entidade = obterEntidade(dto);
			
			// Realiza a inclus�o e converte a entidade de retorno para o DTO.
			D retorno = obterDTO(realizarInclusao(entidade, dto));
			
			// Remove as informa��es do usu�rio logado.
			removerInformacoesUsuario();
			
			// Retorna o DTO.
			return retorno;
			
		// Se o registro j� tiver sido inclu�do, vamos cancelar a autoriza��o pendente.
		} catch (br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException e) {
			//FIXME bruno.carneiro: N�o foi necess�rio executar o cancelamento de uma autoriza��o de inclus�o. 
//			getLogger().debug("[CAPES Api Inclusao] Erro ao incluir: ", e.getMessage());
//			
//			// Converte o DTO em uma entidade para que seja feita a altera��o.
//			E entidade = obterEntidade(dto);
//			
//			// Adiciona � entidade o ID do registro que veio da exce��o. 
//			ReflexaoUtils.setPropriedade(entidade, "id", e.getIdRegistro());
//			
//			// Realiza o cancelamento da autoriza��o.
//			boolean autorizacaoCancelada = cancelarFluxoAutorizacao(entidade, dto.getJustificativaCancelamentoAutorizacao());
//			
//			// Caso alguma autoriza��o tenha sido cancelada, tentamos executar a opera��o novamente.
//			if (autorizacaoCancelada) {
//				incluir(dto);
//			// Caso contr�rio, realmente j� existe um registro com essas dados negociais, ent�o lan�amos a exce��o para o usu�rio.
//			} else {
				CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
//			}
		// Caso venha a exce��o com o registro pendente de autoriza��o, convertemos em uma mensagem mais "amig�vel" para o usu�rio.
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
			// Valida se o DTO cont�m as informa��es necess�rias para a altera��o do registro.
			validarAlteracao(dto);
			
			// Preenche as informa��es do usu�rio logado para ser usado durante a sess�o.
			preencherInformacoesUsuario(dto);
			
			// Converte o DTO em uma entidade para que seja feita a altera��o.
			E entidade = obterEntidade(dto);
			
			// Obt�m a entidade do banco para a altera��o.
			E entidadeBanco = obterEntidadeBanco(entidade.getId());
			
			// Remove o objeto da sess�o. (Caso o fluxo de dupla conferencia esteja ligado, n�o altera a refer�ncia vinculada � sess�o).
			removerObjetoSessao(entidadeBanco);
			
			// Copia as propriedades informadas no DTO para a entidade do banco.
			copiarPropriedadesAlteracao(entidadeBanco, entidade);
			
			// Realiza a altera��o.
			realizarAlteracao(entidadeBanco, dto);
			
			// Remove as informa��es do usu�rio logado.
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
			// Valida se o DTO tem as informa��es necess�rias para realizar a exclus�o do registro.
			validarExclusao(dto);
			
			// Preenche as informa��es do usu�rio logado para ser usado durante a sess�o.
			preencherInformacoesUsuario(dto);
			
			// Obt�m a entidade do banco para a exclus�o.
			E entidade = obterEntidadeBanco(dto.getId());
			
			// Se a entidade realmente existir, faz a exclus�o.
			if(entidade != null){
				realizarExclusao(entidade);
			}
			
			// Remove as informa��es do usu�rio logado.
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
			// Valida se o DTO tem as informa��es necess�rias para encaminhar.
			validarPropriedadesEncaminharAutorizacao(dto);
			
			// Preenche as informa��es do usu�rio logado para ser usado durante a sess�o.
			preencherInformacoesUsuario(dto);

			// Obt�m a classe do servi�o utilizado.
			Class<?> classe = ReflexaoUtils.obterParametroGenerico(getClass(), 1);
			
			// Obt�m o tipo de entidade usada na autoriza��o.
			TipoAutorizacaoEntidadeEnum tipoAutorizacaoEntidadeEnum = TipoAutorizacaoEntidadeEnum.obterPorClasse(classe);

			// Caso a entidade informada seja encontrada.
			if (tipoAutorizacaoEntidadeEnum != null) {
				ConsultaAutorizacaoDTO criterios = new ConsultaAutorizacaoDTO();
				criterios.setIdPessoaSelecionada(dto.getIdPessoa());
				criterios.setIdInstituicaoUsuario(dto.getIdInstituicaoRegistro());
				criterios.setIdRegistro(dto.getIdRegistro());
				
				// Atualiza os respons�veis pela autoriza��o (institui��o de destino)
				obterDelegateAutorizar().atualizarResponsavelAutorizacao(criterios);
				
				// Encaminha as autoriza��es
				obterDelegateAutorizar().encaminharAutorizacoes(criterios);

				// Obt�m o registro do banco para saber o IDREGISTROCONTROLADO
				EntidadeCadastroDelegate<?, ?> delegateEntidade = (EntidadeCadastroDelegate<?, ?>) obterFabricaCadastro().criarDelegate(classe);
				Aprovavel aprovavel = (Aprovavel) delegateEntidade.obter(dto.getIdRegistro());
				
				if(aprovavel != null){
					idRegistroControlado = aprovavel.getIdRegistroControlado();
				}
			}
			
			// Remove as informa��es do usu�rio logado.
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
			// Verifica se o DTO possui as informa��es para executar o procedimento.
			validarPropriedadesExecutarProcedimentoAutorizacao(dto);
			
			// Preenche as informa��es do usu�rio logado para ser usado durante a sess�o.
			preencherInformacoesUsuario(dto);

			// Instancia a nova entidade.
			E entidade = instanciarEntidadeAprovacao();

			if (entidade != null && entidade instanceof Aprovavel) {
				// Copia o ID do registro para a entidade.
				ReflexaoUtils.setPropriedade(entidade, "id", dto.getIdRegistro());

				// Obt�m a autoriza��o de acordo com as informa��es.
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
			
			// Remove as informa��es do usu�rio logado.
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
			// Verifica se foram informadas as propriedades necess�rias para o cancelamento
			validarPropriedades(dto, new String[] { "id", "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao" });

			// Se n�o foi informada uma justificativa para o cancelamento, lan�amos uma exce��o
			if (justificativa == null || justificativa.length() == 0 || "".equals(justificativa.trim())) {
				throw new PropriedadeObrigatoriaNaoInformadaException("justificativa");
			}

			// Preenche as informa��es do usu�rio logado.
			preencherInformacoesUsuario(dto);
			
			// Converte o DTO em uma entidade;
			E entidade = obterEntidade(dto);

			if (entidade instanceof Aprovavel) {
				// Obt�m a autoriza��o do registro informado.
				AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
				Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade((Aprovavel) entidade);
				
				getLogger().debug("Foi encontrada autorizacao para cancelamento: ", String.valueOf(autorizacao != null ? true : false));
				
				// Se a autoriza��o foi encontrada, cancelamos o fluxo.
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
	 * Instancia a entidade para a aprova��o;
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
	 * M�todo que realiza a inclus�o pelo delegate correto.
	 * 
	 * @param entidade
	 *            A entidade a ser incluida.
	 * @param dto
	 *            O DTO para ser usado em algum caso espec�fico.
	 * 
	 * @return A entidade que foi incluida.
	 * @throws BancoobException
	 */
	protected E realizarInclusao(E entidade, D dto) throws BancoobException {
		getLogger().debug("Realizando a inclusao da entidade: ", entidade.getClass().getName());
		return obterDelegate().incluir(entidade);
	}

	/**
	 * M�todo que realiza a altera��o pelo delegate correto.
	 * 
	 * @param entidade
	 *            A entidade a ser alterada
	 * @param dto
	 *            O DTO para ser usado em algum caso espec�fico.
	 * @throws BancoobException
	 */
	protected void realizarAlteracao(E entidade, D dto) throws BancoobException {
		getLogger().debug("Realizando a alteracao da entidade: ", entidade.getClass().getName());
		obterDelegate().alterar(entidade);
	}
	
	/**
	 * M�todo que realiza a exclus�o do registro pelo delegate correto.
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
	 * M�todo que obt�m a entidade do banco
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
	 * Remove o objeto da sess�o.
	 * 
	 * Solu��o para altera��o do objeto que estava na sess�o e passa
	 * pelo fluxo de aprova��o. Ao alterar o objeto com o fluxo ligado, as
	 * altera��es feitas no objeto vinculado � sess�o eram enviadas para o
	 * banco, ignorando o fluxo.
	 * 
	 * @param entidadeBanco
	 * @throws BancoobException 
	 */
	protected void removerObjetoSessao(E entidadeBanco) throws BancoobException {
		obterDelegate().removerObjetoSessao(entidadeBanco);
	}

	/**
	 * Obt�m a pessoa compartilhamento por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 *            O identificador da pessoa
	 * @param idInstituicao
	 *            o identificador da institui��o
	 * @return a {@code PessoaCompartilhamento}
	 * @throws BancoobException
	 */
	protected PessoaCompartilhamento obterPessoaCompartilhamento(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		getLogger().debug("Obtendo a pessoaCompartilhamento. idPessoa=", String.valueOf(idPessoa), " idInstituicao=", String.valueOf(idInstituicao));
		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = obterFabricaCadastro().criarPessoaCompartilhamentoDelegate();
		return pessoaCompartilhamentoDelegate.consultarPorIdPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Copia as propriedades para a altera��o da entidade.
	 * 
	 * @param entidadeFonte
	 * @param entidadeBanco
	 */
	protected void copiarPropriedadesAlteracao(E entidadeDestino, E entidadeFonte) {
		ReflexaoUtils.copiarPropriedades(entidadeDestino, entidadeFonte, "idInstituicaoAtualizacao", "idRegistroControlado", "codigoSituacaoAprovacao", "situacaoAprovacao");
	}

	/**
	 * Obt�m o delegate � partir da entidade informada.
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
	 * Obt�m o delegate Autorizar
	 * 
	 * @return a inst�ncia do {@code AutorizarDelegate}
	 * @throws BancoobException
	 */
	protected AutorizarDelegate obterDelegateAutorizar() throws BancoobException {
		return obterFabricaCadastro().criarAutorizarDelegate();
	}

	/**
	 * Obt�m a f�brica de delegates do projeto de Cadastro.
	 * 
	 * @return A inst�ncia da f�brica de delegates.
	 */
	protected CAPESCadastroFabricaDelegates obterFabricaCadastro() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}

	/**
	 * Obt�m a entidade � partir do DTO informado.
	 * 
	 * @param dto
	 *            O DTO para ser convertido em uma entidade.
	 * @return A entidade convertida � partir do dto.
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
	 * Obt�m o DTO � partir da entidade informada.
	 * 
	 * @param entidade
	 *            A entidade para ser convertida em um DTO.
	 * @return O DTO convertido � partir da entidade.
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
	 * Obt�m o conversor correto � partir do DTO.
	 * 
	 * @param dto
	 *            O DTO para cria��o do conversor.
	 * @return o conversor correto.
	 */
	@SuppressWarnings({ "unchecked" })
	private Conversor<E, D> obterConversor(D dto) {
		FabricaConversores<E, D> fabrica = (FabricaConversores<E, D>) FabricaConversores.obterInstancia();
		Conversor<E, D> conversor = fabrica.obterConversor(dto);
		return conversor;
	}

	/**
	 * Obt�m o conversor correto � partir da entidade.
	 * 
	 * @param entidade
	 *            A entidade para cria��o do conversor.
	 * @return o conversor correto.
	 */
	@SuppressWarnings({ "unchecked" })
	private Conversor<E, D> obterConversor(E entidade) {
		FabricaConversores<E, D> fabrica = (FabricaConversores<E, D>) FabricaConversores.obterInstancia();
		Conversor<E, D> conversor = fabrica.obterConversor(entidade);
		return conversor;
	}

	/**
	 * Preenche as informa��es do usu�rio para preencher as informa��es do
	 * cadastro tanto na cria��o dos fluxos, quanto na inclus�o das informa��es.
	 * 
	 * @param dto
	 *            O DTO com as informa��es necess�rias para a cria��o do
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
	 * Remove a inst�ncia do informa��es usu�rio.
	 * 
	 * @throws BancoobException
	 */
	protected void removerInformacoesUsuario() throws BancoobException {
		InformacoesUsuario.INSTANCIA.remove();
		InformacoesUsuarioCAPES.removeInstance();
	}
	
	/**
	 * Obt�m as informa��es do usu�rio.
	 * 
	 * @return {@code UsuarioBancoob} com as informa��es.
	 */
	protected UsuarioBancoob obterUsuario() {
		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();

		if (informacoes == null) {
			throw new UnsupportedOperationException("O InformacoesUsuarioCAPES n�o foi instanciado!");
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
	 * Realiza a valida��o do DTO antes da inclus�o.
	 * 
	 * @param dto
	 *            O DTO com as informa��es para serem validadas.
	 * @throws BancoobException
	 */
	protected void validarInclusao(D dto) throws BancoobException {
		validarPropriedadesBasicas(dto);
		validarPropriedadesObrigatoriasInclusao(dto);
	}

	/**
	 * Realiza a valida��o do DTO antes da altera��o.
	 * 
	 * @param dto
	 *            O DTO com as informa��es para serem validadas.
	 * @throws BancoobException
	 */
	protected void validarAlteracao(D dto) throws BancoobException {
		validarPropriedadesBasicas(dto);
		validarPropriedadesObrigatoriasAlteracao(dto);
	}
	
	/**
	 * Realiza a valida��o do DTO antes da exclus�o.
	 * 
	 * @param dto
	 *            O DTO com as informa��es para serem validadas.
	 * @throws BancoobException
	 */
	private void validarExclusao(D dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao", "id" });
	}

	/**
	 * Realiza a valida��o das propriedades b�sicas que devem sempre ser
	 * informadas nas opera��es.
	 * 
	 * @param dto
	 *            O DTO com as informa��es a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesBasicas(D dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idPessoa", "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao" });
	}

	/**
	 * Realiza a valida��o das propriedades informadas para encaminhar uma
	 * autoriza��o
	 * 
	 * @param dto
	 *            O DTO com as informa��es a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesEncaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idUsuarioAprovacao", "idInstituicao", "idUnidadeInst", "idRegistro", "idPessoa" });
	}

	/**
	 * Realiza a valida��o das propriedades informadas para a execu��o de um
	 * procedimento
	 * 
	 * @param dto
	 *            O DTO com as informa��es a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesExecutarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idUsuarioAprovacao", "idInstituicao", "idUnidadeInst", "idRegistro", "codigoOcorrenciaAtividade", "nomeProcedimento" });
	}

	/**
	 * Realiza a valida��o das propriedades obrigat�rias para inclus�o de uma
	 * entidade.
	 * 
	 * @param dto
	 *            O DTO com as informa��es a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesObrigatoriasInclusao(D dto) throws BancoobException {
		validarPropriedades(dto, obterPropriedadesObrigatoriasInclusao());
	}

	/**
	 * Realiza a valida��o das propriedades obrigat�rias para altera��o de uma
	 * entidade.
	 * 
	 * @param dto
	 *            O DTO com as informa��es a serem validadas.
	 * @throws BancoobException
	 */
	protected void validarPropriedadesObrigatoriasAlteracao(D dto) throws BancoobException {
		validarPropriedades(dto, obterPropriedadesObrigatoriasAlteracao());
	}
	
	/**
	 * Valida as propriedades
	 * 
	 * @param dto
	 *            O DTO com as informa��es a serem validadas.
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
	 * M�todo para ser preenchido pelos servi�os com o nome das propriedades
	 * obrigat�rias para inclus�o.
	 * 
	 * @return {@code String[]} com o nome das propriedades obrigat�rias para a
	 *         inclus�o.
	 */
	protected abstract String[] obterPropriedadesObrigatoriasInclusao();

	/**
	 * M�todo para ser preenchido pelos servi�os com o nome das propriedades
	 * obrigat�rias para altera��o.
	 * 
	 * @return {@code String[]} com o nome das propriedades obrigat�rias para a
	 *         altera��o.
	 */
	protected abstract String[] obterPropriedadesObrigatoriasAlteracao();
	
	/**
	 * Retorna uma {@code String} com as informa��es do objeto com a formata��o
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
	 *            o objeto com as informa��es para serem extra�das.
	 * @return {@code String} com as informa��es do objeto
	 */
	protected String obterInformacoesObjeto(Object objeto) {
		return ToStringBuilder.reflectionToString(objeto, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	/**
	 * M�todo que valida as propriedades obrigat�rias no DTO.
	 * 
	 * @param dto
	 *            O DTO a ter as propriedades verificadas.
	 * @param propriedades
	 *            O nome das propriedades a serem verificadas.
	 * 
	 * @return {@code Boolean} se as informa��es s�o v�lidas ou n�o.
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