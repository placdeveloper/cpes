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
 *            O servi�o que herda de {@link BemServico}
 */
public class BemDelegate extends EntidadeCadastroDelegate<Bem, BemServico> {
	
	@Override
	protected BemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarBemDelegate();
	}
	
	/**
	 * Obt�m as posses do bem para exibi��o do componente 'Consultar Posses' da
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
	 * Obt�m a lista de {@code ProprietarioBemVO} para a tela de edi��o,
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
	 * Obt�m os valores do bem e avalia��o para exibi��o na tela principal.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException {
		return getServico().obterValoresBensPessoa(idPessoaCompartilhamento);
	}

	/**
	 * Adiciona um bem interno do tipo 'SEM PATRIM�NIO'
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
	 * Obt�m o tipo da classifica��o do bem informado.
	 * 
	 * @param idBem
	 * @return {@code TipoClassificacaoBemEnum} enum com o tipo da classifica��o
	 *         do bem informado.
	 * @throws BancoobException
	 */
	public TipoClassificacaoBemEnum obterTipoClassificacaoBem(Long idBem) throws BancoobException {
		return getServico().obterTipoClassificacaoBem(idBem);
	}

	/**
	 * Obt�m os relacionamentos ({@code BemImovelTipoRelacionamentoPessoa}) do
	 * bem im�vel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException {
		return getServico().obterRelacionamentosBemImovel(idBem);
	}
	
	/**
	 * Obt�m os im�veis rurais da pessoa para preenchimento da combo na tela de
	 * produtividade.
	 * 
	 * <p><b>ATEN��O:</b> esse m�todo traz uma inst�ncia de bem im�vel com apenas
	 * algumas informa��es b�sicas.</p>
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException {
		return getServico().obterImoveisRurais(idPessoaCompartilhamento);
	}

	/**
	 * Verifica/Compartilha os associados do bem com a institui��o logada.
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
	 * Verifica se o bem j� foi incluido para a pessoa informada.
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