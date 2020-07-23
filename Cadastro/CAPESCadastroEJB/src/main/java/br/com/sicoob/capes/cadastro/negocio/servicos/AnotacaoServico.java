/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaSumarioAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Define as opera��es do servi�o de manipula��o de anota��es. 
 * @author Erico.Junior
 */
public interface AnotacaoServico extends CAPESCadastroCrudServico<Anotacao> {

	/**
	 * Consulta as anota��es para o filtro.
	 * @param filtro O filtro para a consulta.
	 * @return Lista de anota��es para o filtro informado.
	 */
	List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro);
	
	/**
	 * M�todo para incluir uma lista de anota��es.
	 * @param anotacoes a lista de anota��es para inclus�o..
	 * @throws BancoobException Caso ocorra alguma exce��o na inclus�o.
	 */
	List<Anotacao> incluir(List<Anotacao> anotacoes) throws BancoobException;
	
	/**
	 * Baixa uma anota��o.
	 * @param anotacao A anota��o a ser baixada.
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o. 
	 */
	void baixarAnotacao(Anotacao anotacao) throws BancoobException;
	
	/**
	 * Baixa uma anota��o.
	 * 
	 * @param anotacao
	 * @param usuarioOperacao
	 * @throws BancoobException
	 */
	void baixarAnotacao(Anotacao anotacao, String usuarioOperacao) throws BancoobException;
	
	/**
	 * Baixa as anota��es informadas com o tipo TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA.
	 * @param anotacoes As anota��es a serem baixadas.
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o.
	 */
	void baixarAnotacoes(List<Anotacao> anotacoes) throws BancoobException;
	
	/**
	 * Baixa as anota��es com o tipo da baixa informado.
	 * @param anotacoes As anota��es a serem baixadas.
	 * @param tipoBaixaEnum O tipo da baixa autom�tica.
	 * @return As anota��es baixadas.
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o.
	 */
	List<Anotacao> baixarAnotacoesAutomaticas(List<Anotacao> anotacoes, TipoBaixaEnum tipoBaixaEnum) 
			throws BancoobException;
	
	/**
	 * Flexibilida a anota��o.
	 * @param anotacao A anota��o a ser flexibilidada.
	 * @throws BancoobException Caso ocorra alguma exce��o ao flexibilizar.
	 */
	void flexibilizar(Anotacao anotacao) throws BancoobException;
	
	
	/**
	 * Recupera o sum�rio das anota��es de uma pessoa.
	 * @param pessoa A pessoa da qual se deseja o sum�rio das anota��es.
	 * @return O dto com o resultado do sum�rio das anota��es de uma pessoa.
	 * @throws BancoobException Caso ocorra alguma exce��o na consulta.  
	 */
	ConsultaSumarioAnotacaoDTO pesquisarSumarioAnotacao(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Lista as anota��es a partir do filtro informado.
	 * @param criterios Os crit�rios utilizados na consulta.
	 * @return A lista de anota��es para o filtro informado.
	 */
	List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios);

	/**
	 * Lista as anota��es a partir do filtro informado para gera��o de relat�rio
	 * 
	 * @param criterios
	 *            os filtros
	 * @return A lista de anota��es
	 */
	List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException;
	
	/**
	 * Importa a anota��o para a pessoa.
	 * @param anotacao
	 * @return
	 * @throws BancoobException
	 */
	Anotacao importar(Anotacao anotacao) throws BancoobException;
	
	/**
	 * Verifica se o cadastro da pessoa (CPF/CNPJ) est� regular
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Inclui uma anota��o
	 * 
	 * @param anotacao
	 * @param plataformaAtendimento
	 *            Se a inclus�o vier da plataforma de atendimento, o servi�o
	 *            valida se a cooperativa possui alguma restri��o para o tipo de
	 *            anota��o informada.
	 * @return
	 * @throws BancoobException
	 */
	Anotacao incluirAnotacao(Anotacao anotacao, boolean plataformaAtendimento) throws BancoobException;
	
	/**
	 * Baixa as anota��es externas.
	 * @param anotacoes As anota��es a serem baixadas.
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o.
	 */
	void baixarAnotacoesExternas(PessoaCompartilhamento pessoaCompartilhamento, OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException;

	/**
	 * Baixa anotacao da pessoa conforme o tipo.
	 * @param obter
	 * @param pessoa
	 */
	void baixarAnotacaoPessoaPorTipo(TipoAnotacao obter, PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * 
	 * @param dataLimite
	 * @param codigoGrupoTipoAnotacao
	 * @param paginaAtual
	 * @param tamanhoPagina
	 * @return
	 */
	ConsultaDto<Anotacao> obterAnotacoesVencidasPorGrupoTipoAnotacao(Date dataLimite, Short codigoGrupoTipoAnotacao, int paginaAtual, int tamanhoPagina) throws BancoobException;

	/**
	 * Baixa anota��o sem realizar nenhuma valida��o.
	 * 
	 * @param anotacao
	 */
	void baixarAnotacaoSemValidar(Anotacao anotacao) throws BancoobException;
}
