package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.BemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.PosseBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ProprietarioBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe base para os delegates do {@code Bem}
 * 
 * @author bruno.carneiro
 * 
 * @param <E>
 *            A Classe que herda de {@link Bem}
 * @param <S>
 *            O serviço que herda de {@link BemServico}
 */
public class BemDelegate extends EntidadeCadastroDelegate<Bem, BemServico> {
	
	@Override
	protected BemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarBemDelegate();
	}
	
	/**
	 * Obtém as posses do bem para exibição do componente 'Consultar Posses' da
	 * tela principal.
	 * 
	 * @param idBem
	 * @param tipoClassificacaoBem
	 * @return
	 * @throws BancoobException
	 */
	public PosseBemVO obterPossesBem(Long idBem, TipoClassificacaoBem tipoClassificacaoBem) throws BancoobException {
		return getServico().obterPossesBem(idBem, tipoClassificacaoBem);
	}

	/**
	 * Obtém a lista de {@code ProprietarioBemVO} para a tela de edição,
	 * utilizado pelo componente de pesquisa de bens.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public List<ProprietarioBemVO> obterProprietariosBem(Long idBem) throws BancoobException {
		return getServico().obterProprietariosBem(idBem);
	}

	/**
	 * Obtém os valores do bem e avaliação para exibição na tela principal.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException {
		return getServico().obterValoresBensPessoa(idPessoaCompartilhamento);
	}

	/**
	 * Adiciona um bem interno do tipo 'SEM PATRIMÔNIO'
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	public void adicionarBemSemPatrimonio(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		getServico().adicionarBemSemPatrimonio(pessoaCompartilhamento);
	}

	/**
	 * Adiciona um bem interno do tipo 'RECUSOU-SE A INFORMAR'
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	public void adicionarBemRecusouInformar(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		getServico().adicionarBemRecusouInformar(pessoaCompartilhamento);
	}

	/**
	 * Obtém o tipo da classificação do bem informado.
	 * 
	 * @param idBem
	 * @return {@code TipoClassificacaoBemEnum} enum com o tipo da classificação
	 *         do bem informado.
	 * @throws BancoobException
	 */
	public TipoClassificacaoBemEnum obterTipoClassificacaoBem(Long idBem) throws BancoobException {
		return getServico().obterTipoClassificacaoBem(idBem);
	}

	/**
	 * Obtém os relacionamentos ({@code BemImovelTipoRelacionamentoPessoa}) do
	 * bem imóvel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException {
		return getServico().obterRelacionamentosBemImovel(idBem);
	}
	
	/**
	 * Obtém os imóveis rurais da pessoa para preenchimento da combo na tela de
	 * produtividade.
	 * 
	 * <p><b>ATENÇÃO:</b> esse método traz uma instância de bem imóvel com apenas
	 * algumas informações básicas.</p>
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException {
		return getServico().obterImoveisRurais(idPessoaCompartilhamento);
	}

	/**
	 * Verifica/Compartilha os associados do bem com a instituição logada.
	 * 
	 * @param idBem
	 */
	public void verificarCompartilhamentoAssociados(Long idBem) throws BancoobException {
		getServico().verificarCompartilhamentoAssociados(idBem);
	}
	
	/**
	 * Consulta os bens de uma determinada pessoa compartilhamento
	 * 
	 * @param idPessoaCompartilhamento
	 * @param tipoClassificacao
	 * @return List<Bem>
	 * @throws BancoobException
	 */
	public List<Bem> obterBensPorPessoaCompartilhamento(Long idPessoaCompartilhamento, Short tipoClassificacao)  throws BancoobException {
		return getServico().obterBensPorPessoaCompartilhamento(idPessoaCompartilhamento, tipoClassificacao);
	}
	
	/**
	 * Consulta os bensImoveis de uma determinada pessoa compartilhamento
	 * 
	 * @param idPessoaCompartilhamento
	 * @return List<Bem>
	 * @throws BancoobException
	 */
	public List<Bem> obterBensImoveisPorPessoaCompartilhamento(Long idPessoaCompartilhamento)  throws BancoobException {
		return getServico().obterBensImoveisPorPessoaCompartilhamento(idPessoaCompartilhamento);
	}
	
	/**
	 * Verifica se o bem já foi incluido para a pessoa informada.
	 * 
	 * @param idBem
	 * @param idPessoaCompartilhamento
	 * @return
	 */
	public boolean verificarExistenciaBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException {
		return getServico().verificarExistenciaBemInterno(idBem, idPessoaCompartilhamento);
	}

	public boolean verificarBemEmGarantia(Long idBem) throws BancoobException {
		return getServico().verificarBemEmGarantia(idBem);
	}

	/**
	 * Realiza o desbloqueio do bem.
	 * 
	 * @param idBem
	 * @throws BancoobException
	 */
	public void desbloquearBem(Long idBem) throws BancoobException {
		getServico().desbloquearBem(idBem);
	}

}