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
 * Define as operações do serviço de manipulação de anotações. 
 * @author Erico.Junior
 */
public interface AnotacaoServico extends CAPESCadastroCrudServico<Anotacao> {

	/**
	 * Consulta as anotações para o filtro.
	 * @param filtro O filtro para a consulta.
	 * @return Lista de anotações para o filtro informado.
	 */
	List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro);
	
	/**
	 * Método para incluir uma lista de anotações.
	 * @param anotacoes a lista de anotações para inclusão..
	 * @throws BancoobException Caso ocorra alguma exceção na inclusão.
	 */
	List<Anotacao> incluir(List<Anotacao> anotacoes) throws BancoobException;
	
	/**
	 * Baixa uma anotação.
	 * @param anotacao A anotação a ser baixada.
	 * @throws BancoobException Caso ocorra alguma exceção na alteração. 
	 */
	void baixarAnotacao(Anotacao anotacao) throws BancoobException;
	
	/**
	 * Baixa uma anotação.
	 * 
	 * @param anotacao
	 * @param usuarioOperacao
	 * @throws BancoobException
	 */
	void baixarAnotacao(Anotacao anotacao, String usuarioOperacao) throws BancoobException;
	
	/**
	 * Baixa as anotações informadas com o tipo TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA.
	 * @param anotacoes As anotações a serem baixadas.
	 * @throws BancoobException Caso ocorra alguma exceção na alteração.
	 */
	void baixarAnotacoes(List<Anotacao> anotacoes) throws BancoobException;
	
	/**
	 * Baixa as anotações com o tipo da baixa informado.
	 * @param anotacoes As anotações a serem baixadas.
	 * @param tipoBaixaEnum O tipo da baixa automática.
	 * @return As anotações baixadas.
	 * @throws BancoobException Caso ocorra alguma exceção na alteração.
	 */
	List<Anotacao> baixarAnotacoesAutomaticas(List<Anotacao> anotacoes, TipoBaixaEnum tipoBaixaEnum) 
			throws BancoobException;
	
	/**
	 * Flexibilida a anotação.
	 * @param anotacao A anotação a ser flexibilidada.
	 * @throws BancoobException Caso ocorra alguma exceção ao flexibilizar.
	 */
	void flexibilizar(Anotacao anotacao) throws BancoobException;
	
	
	/**
	 * Recupera o sumário das anotações de uma pessoa.
	 * @param pessoa A pessoa da qual se deseja o sumário das anotações.
	 * @return O dto com o resultado do sumário das anotações de uma pessoa.
	 * @throws BancoobException Caso ocorra alguma exceção na consulta.  
	 */
	ConsultaSumarioAnotacaoDTO pesquisarSumarioAnotacao(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Lista as anotações a partir do filtro informado.
	 * @param criterios Os critérios utilizados na consulta.
	 * @return A lista de anotações para o filtro informado.
	 */
	List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios);

	/**
	 * Lista as anotações a partir do filtro informado para geração de relatório
	 * 
	 * @param criterios
	 *            os filtros
	 * @return A lista de anotações
	 */
	List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException;
	
	/**
	 * Importa a anotação para a pessoa.
	 * @param anotacao
	 * @return
	 * @throws BancoobException
	 */
	Anotacao importar(Anotacao anotacao) throws BancoobException;
	
	/**
	 * Verifica se o cadastro da pessoa (CPF/CNPJ) está regular
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Inclui uma anotação
	 * 
	 * @param anotacao
	 * @param plataformaAtendimento
	 *            Se a inclusão vier da plataforma de atendimento, o serviço
	 *            valida se a cooperativa possui alguma restrição para o tipo de
	 *            anotação informada.
	 * @return
	 * @throws BancoobException
	 */
	Anotacao incluirAnotacao(Anotacao anotacao, boolean plataformaAtendimento) throws BancoobException;
	
	/**
	 * Baixa as anotações externas.
	 * @param anotacoes As anotações a serem baixadas.
	 * @throws BancoobException Caso ocorra alguma exceção na alteração.
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
	 * Baixa anotação sem realizar nenhuma validação.
	 * 
	 * @param anotacao
	 */
	void baixarAnotacaoSemValidar(Anotacao anotacao) throws BancoobException;
}
