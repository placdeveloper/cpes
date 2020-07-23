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
 * Business delegate para as anotações.
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
	 * Consulta as anotações para o filtro.
	 * 
	 * @param filtro
	 *            O filtro para a consulta.
	 * @return Lista de anotações para o filtro informado.
	 */
	public List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro) {
		return getServico().listarAnotacoesPorFiltro(filtro);
	}

	/**
	 * Método para incluir uma lista de anotações.
	 * 
	 * @param anotacoes
	 *            a lista de anotações para inclusão..
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na inclusão.
	 */
	public List<Anotacao> incluir(List<Anotacao> anotacoes) throws BancoobException {
		return getServico().incluir(anotacoes);
	}

	/**
	 * Baixa as anotações informadas com o tipo
	 * TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA.
	 * 
	 * @param anotacoes
	 *            As anotações a serem baixadas.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	public void baixarAnotacoes(List<Anotacao> anotacoes) throws BancoobException {
		getServico().baixarAnotacoes(anotacoes);
	}

	/**
	 * Baixa uma anotação manual.
	 * 
	 * @param anotacao
	 *            A anotação a ser baixada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	public void baixarAnotacao(Anotacao anotacao) throws BancoobException {
		getServico().baixarAnotacao(anotacao);
	}
	
	/**
	 * Baixa uma anotação manual.
	 * 
	 * @param anotacao
	 * @param usuarioOperacao
	 * @throws BancoobException
	 */
	public void baixarAnotacao(Anotacao anotacao, String usuarioOperacao) throws BancoobException {
		getServico().baixarAnotacao(anotacao, usuarioOperacao);
	}

	/**
	 * Baixa as anotações com o tipo da baixa informado.
	 * @param anotacoes As anotações a serem baixadas.
	 * @param tipoBaixaEnum O tipo da baixa automática.
	 * @return As anotações baixadas.
	 * @throws BancoobException Caso ocorra alguma exceção na alteração.
	 */
	public List<Anotacao> baixarAnotacoesAutomaticas(List<Anotacao> anotacoes, 
			TipoBaixaEnum tipoBaixaEnum) throws BancoobException {
		return getServico().baixarAnotacoesAutomaticas(anotacoes, tipoBaixaEnum);
	}

	/**
	 * Flexibilida a anotação.
	 * 
	 * @param anotacao
	 *            A anotação a ser flexibilidada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção ao felxibilizar.
	 */
	public void flexibilizar(Anotacao anotacao) throws BancoobException {
		getServico().flexibilizar(anotacao);
	}

	/**
	 * Recupera o sumário das anotações de uma pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa da qual se deseja o sumário das anotações.
	 * @return O dto com o resultado do sumário das anotações de uma pessoa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na consulta.
	 */
	public ConsultaSumarioAnotacaoDTO pesquisarSumarioAnotacao(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return getServico().pesquisarSumarioAnotacao(pessoa);
	}

	/**
	 * Lista as anotações a partir do filtro informado.
	 * @param criterios Os critérios utilizados na consulta.
	 * @return A lista de anotações para o filtro informado.
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
	 * Importa a anotação para a pessoa.
	 * @param anotacao
	 * @return
	 * @throws BancoobException
	 */
	public Anotacao importar(Anotacao anotacao) throws BancoobException{
		return getServico().importar(anotacao);
	}
	
	/**
	 * Verifica se o cadastro da pessoa (CPF/CNPJ) está regular
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return getServico().isCadastroReceitaRegular(pessoaCompartilhamento);
	}
	
	/**
	 * Baixa as anotações externas.
	 * 
	 * @param anotacoes
	 *            As anotações a serem baixadas.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
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
	 * Baixa uma anotação.
	 * 
	 * @param anotacao
	 *            A anotação a ser baixada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	public void baixarAnotacaoSemValidar(Anotacao anotacao) throws BancoobException {
		getServico().baixarAnotacaoSemValidar(anotacao);
	}
}
