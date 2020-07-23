package br.com.sicoob.capes.cadastro.negocio.estrategias;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Estratégia para aprovação de {@link Bem}.
 */
public class EstrategiaAutorizacaoAprovarBem extends EstrategiaAutorizacaoAprovar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		tratarAprovavel(aprovavel);
		tratarHistorico(aprovavel, false);
		//anexarDocumentosDossie(autorizacao);
		
		Bem bem = (Bem) aprovavel;
		for(BemPessoaCompartilhamento bemPessoaCompartilhamento : bem.getProprietarios()){
			bemPessoaCompartilhamento.setPessoaResponsavel(Boolean.FALSE);
			bemPessoaCompartilhamento.setDataHoraInicio(new DateTimeDB());
			bemPessoaCompartilhamento.setIdInstituicaoAtualizacao(null);
			tratarAprovavel(bemPessoaCompartilhamento);
			tratarHistorico(bemPessoaCompartilhamento, false);
			anexarDocumentosDossie(autorizacao, bemPessoaCompartilhamento);
		}
		
		alterar(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());

		// prepara o objeto para ser persistido
		Aprovavel entidadeAlteracao = obterEntidadeAlteracao(tipoAutorizacao, aprovavel);
		ReflexaoUtils.copiarPropriedades(entidadeAlteracao, aprovavel, "id", "idBem", "idRegistroControlado");
		ReflexaoUtils.setPropriedade(entidadeAlteracao, "idBem", autorizacao.getIdRegistroAntigo());
		tratarAprovavel(entidadeAlteracao);
		
		atualizarListasAlteracao(entidadeAlteracao, aprovavel);

		tratarAlteracao(autorizacao, aprovavel, entidadeAlteracao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel, Aprovavel entidadeAlteracao) throws BancoobException {
		//anexarDocumentosDossie(autorizacao);
		
		Bem bem = (Bem) aprovavel;
		for(BemPessoaCompartilhamento bemPessoaCompartilhamento : bem.getProprietarios()){
			anexarDocumentosDossie(autorizacao, bemPessoaCompartilhamento);
		}

		// realiza a alteração do registro
		alterar(entidadeAlteracao);

		// exclui o registro novo
		excluirDadosAprovacao(aprovavel);
	}
	
	/**
	 * Atualiza as listas para a alteração do bem.
	 * 
	 * @param entidadeAlteracao
	 * @param aprovavel
	 * @throws BancoobException
	 */
	private void atualizarListasAlteracao(Aprovavel entidadeAlteracao, Aprovavel aprovavel) throws BancoobException {
		Bem novoRegistro = (Bem) entidadeAlteracao;

		// atualizando a referência do novo registro criado a listas existentes. Tratamento feito
		// quando se utiliza o hibernate para realização de Cascade
		List<BemPessoaCompartilhamento> proprietarios = atualizarProprietarios(novoRegistro.getProprietarios(), novoRegistro);
		novoRegistro.setProprietarios(proprietarios);
		
		if(novoRegistro instanceof BemImovel) {
			BemImovel bemImovel = (BemImovel) novoRegistro;
			Set<BemImovelTipoRelacionamentoPessoa> relacionamentosImovel = atualizarParticipantesBemImovel(bemImovel, aprovavel);
			bemImovel.setRelacionamentoPessoas(relacionamentosImovel);
		}
	}
	
	/**
	 * Atualiza a lista dos proprietários do bem.
	 * 
	 * @param lista
	 * @param novoRegistro
	 * @return
	 */
	private List<BemPessoaCompartilhamento> atualizarProprietarios(List<BemPessoaCompartilhamento> lista, Bem novoRegistro) {
		List<BemPessoaCompartilhamento> retorno = new ArrayList<BemPessoaCompartilhamento>();

		if (lista != null && !lista.isEmpty()) {
			for (BemPessoaCompartilhamento bpc : lista) {
				BemPessoaCompartilhamento novoBPC = ReflexaoUtils.construirObjetoPorClasse(bpc.getClass());
				ReflexaoUtils.copiarPropriedades(novoBPC, bpc, "id", "idBemPessoaCompartilhamento");
				novoBPC.setDataHoraInicio(new DateTimeDB());
				novoBPC.setIdInstituicaoAtualizacao(null);
				novoBPC.setPessoaResponsavel(Boolean.FALSE);
				novoBPC.setBem(novoRegistro);
				retorno.add(novoBPC);
			}
		}
		return retorno;
	}
	
	/**
	 * Atualia a lista dos participantes do bem imóvel.
	 * 
	 * @param bemImovel
	 * @param aprovavel
	 * @return
	 * @throws BancoobException
	 */
	private Set<BemImovelTipoRelacionamentoPessoa> atualizarParticipantesBemImovel(BemImovel bemImovel, Aprovavel aprovavel) throws BancoobException {
		Set<BemImovelTipoRelacionamentoPessoa> retorno = new HashSet<BemImovelTipoRelacionamentoPessoa>();

		BemImovel bemAlteracao = (BemImovel) aprovavel;

		if (bemAlteracao != null && bemAlteracao.getRelacionamentoPessoas() != null && !bemAlteracao.getRelacionamentoPessoas().isEmpty()) {
			for (BemImovelTipoRelacionamentoPessoa tipo : bemAlteracao.getRelacionamentoPessoas()) {
				BemImovelTipoRelacionamentoPessoa tipoRelacionamento = new BemImovelTipoRelacionamentoPessoa();
				ReflexaoUtils.copiarPropriedades(tipoRelacionamento, tipo, "id", "idBemImovelRelacionamento", "bemImovel");
				tipoRelacionamento.setBemImovel(bemImovel);
				retorno.add(tipoRelacionamento);
			}
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Aprovavel obterEntidadeAlteracao(TipoAutorizacaoEntidadeEnum tipoAutorizacao, Aprovavel aprovavel) {
		return ReflexaoUtils.construirObjetoPorClasse(aprovavel.getClass());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void alterar(Aprovavel aprovavel) throws BancoobException {
		LOGGER.debug("Alterando ", aprovavel.getTipoAutorizacao().name(), ": ", String.valueOf(aprovavel.getId()));

		Bem bem = (Bem) aprovavel;
		obterBemDelegate().alterar(bem);
	}

	/**
	 * Anexa os documentos ao dossiê.
	 * 
	 * @param autorizacao
	 * @param aprovavel
	 * @throws BancoobException
	 */
	private void anexarDocumentosDossie(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		if ((autorizacao.getDocumentos() != null) && !autorizacao.getDocumentos().isEmpty()) {
			LOGGER.info("Anexando documentos ao dossie: ", autorizacao.getIdRegistroControlado(), "(", String.valueOf(autorizacao.getIdAutorizacao()), ")");

			GEDIntegracaoDTO dadosGED = obterVoGED(autorizacao, aprovavel);
			GEDIntegracaoDelegate delegateGED = getFabricaDelegatesIntegracao().criarGEDIntegracaoDelegate();
//			if (!delegateGED.isDossieExistente(dadosGED)) {
//				LOGGER.info("Dossie nao encontrado. Criando dossie da pessoa: ", aprovavel.getPessoaCompartilhamento().getPessoa().getIdPessoa().toString());
//				delegateGED.criarDossie(dadosGED);
//			}

			delegateGED.associarDocumentosDossie(dadosGED);
		}
	}
	
	/**
	 * Faz o tratamento da classe aprovavel.
	 * 
	 * @param aprovavel
	 */
	private void tratarAprovavel(Aprovavel aprovavel) {
		aprovavel.setIdInstituicaoAtualizacao(null);
		aprovavel.setVerificarAutorizacao(false);
	}
	
	/**
	 * Obtém o VO do ged para inclusão de documentos ao dossiê.
	 * 
	 * @param autorizacao
	 * @param aprovavel
	 * @return
	 */
	private GEDIntegracaoDTO obterVoGED(Autorizacao autorizacao, Aprovavel aprovavel) {
		GEDIntegracaoDTO dadosGED = new GEDIntegracaoDTO();

		Pessoa pessoa = aprovavel.getPessoaCompartilhamento().getPessoa();
		Instituicao instituicao = autorizacao.getInstituicaoDestino();

		dadosGED.setIdPessoa(pessoa.getIdPessoa());
		dadosGED.setCpfCnpj(pessoa.getCpfCnpj());
		dadosGED.setCodTipoPessoa(pessoa.getTipoPessoa().getCodTipoPessoa());
		dadosGED.setCodCompartilhamentoCadastro(aprovavel.getPessoaCompartilhamento().getCodCompartilhamentoCadastro());
		dadosGED.setIdRegistroControlado(autorizacao.getIdRegistroControlado());
		
		//TODO: Verificar
		dadosGED.setIdInstituicaoDestino(instituicao.getIdInstituicao());
		dadosGED.setIdUnidadeInstDestino(instituicao.getIdUnidadeInst());
		
		for (AutorizacaoDocumento autorizacaoDocumento : autorizacao.getDocumentos()) {
			DocumentoComprobatorio documento = autorizacaoDocumento.getDocumento();
			if (documento != null) {
				dadosGED.addIdDocumento(documento.getIdDocumento());
			}
		}
		return dadosGED;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluir(Aprovavel aprovavel) throws BancoobException {
		LOGGER.debug("Excluindo ", aprovavel.getTipoAutorizacao().name(), ": ", String.valueOf(aprovavel.getId()));

		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate;
		delegate = CAPESCadastroFabricaDelegates.getInstance().criarDelegate(aprovavel.getClass());
		delegate.excluirEntidade((CAPESEntidade<?>) aprovavel);
	}
	
	/**
	 * Obtém o delegate dos bens.
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private BemDelegate obterBemDelegate() throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
	}
	
}