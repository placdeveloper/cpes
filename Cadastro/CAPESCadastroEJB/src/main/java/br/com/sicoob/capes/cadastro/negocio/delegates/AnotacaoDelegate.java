/*
 * SICOOB
 * 
 * AnotacaoDelegate.java(br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate)
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaSumarioAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum;
import br.com.sicoob.capes.cadastro.negocio.servicos.AnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Business delegate para as anota��es.
 * 
 * @author Erico.Junior
 */
public class AnotacaoDelegate extends
		CAPESCadastroCrudDelegate<Anotacao, AnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarAnotacaoServico();
	}

	/**
	 * Consulta as anota��es para o filtro.
	 * 
	 * @param filtro
	 *            O filtro para a consulta.
	 * @return Lista de anota��es para o filtro informado.
	 */
	public List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro) {
		return getServico().listarAnotacoesPorFiltro(filtro);
	}

	/**
	 * M�todo para incluir uma lista de anota��es.
	 * 
	 * @param anotacoes
	 *            a lista de anota��es para inclus�o..
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na inclus�o.
	 */
	public List<Anotacao> incluir(List<Anotacao> anotacoes) throws BancoobException {
		return getServico().incluir(anotacoes);
	}

	/**
	 * Baixa as anota��es informadas com o tipo
	 * TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA.
	 * 
	 * @param anotacoes
	 *            As anota��es a serem baixadas.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	public void baixarAnotacoes(List<Anotacao> anotacoes) throws BancoobException {
		getServico().baixarAnotacoes(anotacoes);
	}

	/**
	 * Baixa uma anota��o manual.
	 * 
	 * @param anotacao
	 *            A anota��o a ser baixada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	public void baixarAnotacao(Anotacao anotacao) throws BancoobException {
		getServico().baixarAnotacao(anotacao);
	}
	
	/**
	 * Baixa uma anota��o manual.
	 * 
	 * @param anotacao
	 * @param usuarioOperacao
	 * @throws BancoobException
	 */
	public void baixarAnotacao(Anotacao anotacao, String usuarioOperacao) throws BancoobException {
		getServico().baixarAnotacao(anotacao, usuarioOperacao);
	}

	/**
	 * Baixa as anota��es com o tipo da baixa informado.
	 * @param anotacoes As anota��es a serem baixadas.
	 * @param tipoBaixaEnum O tipo da baixa autom�tica.
	 * @return As anota��es baixadas.
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o.
	 */
	public List<Anotacao> baixarAnotacoesAutomaticas(List<Anotacao> anotacoes, 
			TipoBaixaEnum tipoBaixaEnum) throws BancoobException {
		return getServico().baixarAnotacoesAutomaticas(anotacoes, tipoBaixaEnum);
	}

	/**
	 * Flexibilida a anota��o.
	 * 
	 * @param anotacao
	 *            A anota��o a ser flexibilidada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o ao felxibilizar.
	 */
	public void flexibilizar(Anotacao anotacao) throws BancoobException {
		getServico().flexibilizar(anotacao);
	}

	/**
	 * Recupera o sum�rio das anota��es de uma pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa da qual se deseja o sum�rio das anota��es.
	 * @return O dto com o resultado do sum�rio das anota��es de uma pessoa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na consulta.
	 */
	public ConsultaSumarioAnotacaoDTO pesquisarSumarioAnotacao(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return getServico().pesquisarSumarioAnotacao(pessoa);
	}

	/**
	 * Lista as anota��es a partir do filtro informado.
	 * @param criterios Os crit�rios utilizados na consulta.
	 * @return A lista de anota��es para o filtro informado.
	 */
	public List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios){
		return getServico().listarAnotacoesSisbr(criterios);
	}
	
	/**
	 * Listar anotacoes para relatorio.
	 * 
	 * @param criterios
	 *            the criterios
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException {
		return getServico().listarAnotacoesParaRelatorio(criterios);
	}

	/**
	 * Importa a anota��o para a pessoa.
	 * @param anotacao
	 * @return
	 * @throws BancoobException
	 */
	public Anotacao importar(Anotacao anotacao) throws BancoobException{
		return getServico().importar(anotacao);
	}
	
	/**
	 * Verifica se o cadastro da pessoa (CPF/CNPJ) est� regular
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return getServico().isCadastroReceitaRegular(pessoaCompartilhamento);
	}
	
	/**
	 * Baixa as anota��es externas.
	 * 
	 * @param anotacoes
	 *            As anota��es a serem baixadas.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	public void baixarAnotacoesExternas(PessoaCompartilhamento pessoaCompartilhamento, OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException{
		getServico().baixarAnotacoesExternas(pessoaCompartilhamento, origemInformacao, dto);
	}
	/**
	 * Baixa anotacao da pessoa conforme o tipo.
	 * @param obter
	 * @param pessoa
	 */
	void baixarAnotacaoPessoaPorTipo(TipoAnotacao tipo, PessoaCompartilhamento pessoa) throws BancoobException{
		getServico().baixarAnotacaoPessoaPorTipo(tipo, pessoa);
	}

	/**
	 * 
	 * @param dataLimite
	 * @param codigoGrupoTipoAnotacao
	 * @param paginaAtual
	 * @param tamanhoPagina
	 * @return
	 */
	public ConsultaDto<Anotacao> obterAnotacoesVencidasPorGrupoTipoAnotacao(Date dataLimite, Short codigoGrupoTipoAnotacao, int paginaAtual, int tamanhoPagina) throws BancoobException{
		return getServico().obterAnotacoesVencidasPorGrupoTipoAnotacao(dataLimite, codigoGrupoTipoAnotacao, paginaAtual, tamanhoPagina);
	}

	/**
	 * Baixa uma anota��o.
	 * 
	 * @param anotacao
	 *            A anota��o a ser baixada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	public void baixarAnotacaoSemValidar(Anotacao anotacao) throws BancoobException {
		getServico().baixarAnotacaoSemValidar(anotacao);
	}
}
