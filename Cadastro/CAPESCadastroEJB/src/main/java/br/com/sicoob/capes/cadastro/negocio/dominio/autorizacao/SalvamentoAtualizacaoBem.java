package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * Classe para salvamento da atualização de Bens.
 * 
 * @author Bruno.Carneiro
 */
public class SalvamentoAtualizacaoBem extends SalvamentoAtualizacaoGenerico {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Autorizacao execute(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao) throws BancoobException {

		String idRegistroControlado = aprovavel.getClass().getSimpleName() + "#" + aprovavel.getId();
		String mensagemRollback = getClass().getSimpleName() + ".execute: rollback salvamento atualizacao ";

		try {
			Instituicao instituicao = obterInstituicaoUsuarioLogado();
			LOGGER.debug(getClass().getSimpleName(), ": instituicao usuario logado - ", instituicao.toString());
			
			Autorizacao autorizacao = criarAutorizacao(aprovavel, tipoOperacao, instituicao);
			marcarEmAlteracao(aprovavel, autorizacao);
			salvarAtualizacao(aprovavel, autorizacao, tipoOperacao);
			
			return salvarAutorizacao(autorizacao);
		} catch (NegocioException e) {
			LOGGER.erro(e, mensagemRollback + idRegistroControlado);
			throw new CAPESCadastroNegocioException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void marcarEmAlteracao(Aprovavel aprovavel, Autorizacao autorizacao) throws BancoobException {
		Bem bem = (Bem) aprovavel;
		for(BemPessoaCompartilhamento bemPessoaCompartilhamento : bem.getProprietarios()) {
			super.marcarEmAlteracao(bemPessoaCompartilhamento, autorizacao);
		}
		
		super.marcarEmAlteracao(aprovavel, autorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void salvarAtualizacao(Aprovavel aprovavel, Autorizacao autorizacao, TipoOperacaoEnum tipoOperacao) throws BancoobException {
		Aprovavel novoRegistro = null;
		if (!TipoOperacaoEnum.E.equals(tipoOperacao)) {

			// obtem a classe aprovavel
			final Class<Aprovavel> classeAprovavel = (Class<Aprovavel>) aprovavel.getClass();

			// cria o novo registro com os dados do alterados
			novoRegistro = criarNovoRegistro(aprovavel, autorizacao.getInstituicaoOrigem(), classeAprovavel);
			
			Bem bemPrincipal = (Bem) aprovavel;
			Bem bemNovo = (Bem) novoRegistro;
			criarBemPessoaCompartilhamento(autorizacao, bemPrincipal, bemNovo);
			criarRelacionamentosBemImovel(bemPrincipal, bemNovo);
			
			CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> bemDelegate = CAPESCadastroFabricaDelegates.getInstance().criarDelegate(bemPrincipal.getClass());
			// salva o novo registro
			novoRegistro = (Aprovavel) bemDelegate.incluir(bemNovo);
			
			// Atualiza a referência do objeto antigo.
			if (ReflexaoUtils.getValorAtributo(aprovavel, "id") == null) {
				ReflexaoUtils.setPropriedade(aprovavel, "id", novoRegistro.getId());
			}

			// preenche o ID do registro novo na autorizacao
			autorizacao.setIdRegistroNovo(((CAPESEntidade<Long>) novoRegistro).getId());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Aprovavel criarNovoRegistro(Aprovavel aprovavel, Instituicao instAtualizacao, Class<Aprovavel> classeAprovavel) {
		// instancia e preenche os dados da entidade com as alteracoes
		Aprovavel novoRegistro = ReflexaoUtils.construirObjetoPorClasse(classeAprovavel);
		ReflexaoUtils.copiarPropriedades(novoRegistro, aprovavel, "id", "idBem", "proprietarios", "relacionamentoPessoas");
		novoRegistro.setIdInstituicaoAtualizacao(instAtualizacao.getIdInstituicao());
		novoRegistro.setVerificarAutorizacao(false);

		Vigente vigente = novoRegistro;
		vigente.setGravarHistorico(false);
		vigente.setDataHoraInicio(null);

		return novoRegistro;
	}
	
	/**
	 * Cria os novos registros de bemPessoaCompartilhamento, para o 'novo' bem.
	 * 
	 * @param autorizacao
	 * @param bemPrincipal
	 * @param bemNovo
	 */
	private void criarBemPessoaCompartilhamento(Autorizacao autorizacao, Bem bemPrincipal, Bem bemNovo) {
		List<BemPessoaCompartilhamento> lista = new ArrayList<BemPessoaCompartilhamento>();
		for(BemPessoaCompartilhamento bemPessoaCompartilhamento : bemPrincipal.getProprietarios()) {
			lista.add(criarNovoRegistroBemPessoaCompartilhamento(bemPessoaCompartilhamento, autorizacao.getInstituicaoOrigem(), bemNovo));
		}
		
		bemNovo.setProprietarios(lista);
	}
	
	/**
	 * Se for um bem do tipo Imóvel, cria os tipos relacionamento para o mesmo.
	 * 
	 * @param bemPrincipal
	 * @param bemNovo
	 * @throws BancoobException
	 */
	private void criarRelacionamentosBemImovel(Bem bemPrincipal, Bem bemNovo) throws BancoobException {
		if (bemPrincipal instanceof BemImovel) {
			BemImovel bemImovelPrincipal = (BemImovel) bemPrincipal;

			Set<BemImovelTipoRelacionamentoPessoa> relacionamentos = null;
			if (Hibernate.isInitialized(bemImovelPrincipal.getRelacionamentoPessoas())) {
				relacionamentos = bemImovelPrincipal.getRelacionamentoPessoas();
			} else {
				BemDelegate bemDelegate = CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
				relacionamentos = new HashSet<BemImovelTipoRelacionamentoPessoa>(bemDelegate.obterRelacionamentosBemImovel(bemPrincipal.getIdBem()));
			}

			Set<BemImovelTipoRelacionamentoPessoa> lista = new HashSet<BemImovelTipoRelacionamentoPessoa>();
			for (BemImovelTipoRelacionamentoPessoa tipoRelacionamento : relacionamentos) {
				BemImovelTipoRelacionamentoPessoa novoRegistro = ReflexaoUtils.construirObjetoPorClasse(BemImovelTipoRelacionamentoPessoa.class);
				ReflexaoUtils.copiarPropriedades(novoRegistro, tipoRelacionamento, "id", "idBemImovelRelacionamento");
				novoRegistro.setBemImovel((BemImovel) bemNovo);
				lista.add(novoRegistro);
			}
			((BemImovel) bemNovo).setRelacionamentoPessoas(lista);
		}
	}
	
	/**
	 * Cria os novos registros de bemPessoaCompartilhamento, para o 'novo' bem.
	 * 
	 * @param bemPessoaCompartilhamento
	 * @param instAtualizacao
	 * @param bem
	 * @return
	 */
	private BemPessoaCompartilhamento criarNovoRegistroBemPessoaCompartilhamento(BemPessoaCompartilhamento bemPessoaCompartilhamento, Instituicao instAtualizacao, Bem bem) {
		// instancia e preenche os dados da entidade com as alteracoes
		BemPessoaCompartilhamento novoRegistro = ReflexaoUtils.construirObjetoPorClasse(bemPessoaCompartilhamento.getClass());
		ReflexaoUtils.copiarPropriedades(novoRegistro, bemPessoaCompartilhamento, getPropriedadesIgnoradas("idBemPessoaCompartilhamento"));
		novoRegistro.setIdInstituicaoAtualizacao(instAtualizacao.getIdInstituicao());
		novoRegistro.setVerificarAutorizacao(false);
		novoRegistro.setBem(bem);

		Vigente vigente = novoRegistro;
		vigente.setGravarHistorico(false);
		vigente.setDataHoraInicio(null);

		return novoRegistro;
	}
	
}