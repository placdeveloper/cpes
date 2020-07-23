/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.TransicaoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.cadastro.negocio.vo.TransicaoPessoaVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Business delegate para o TransicaoPessoa.
 * 
 * @author Juan.Damasceno
 */
public class TransicaoPessoaDelegate extends
		CAPESCadastroCrudDelegate<TransicaoPessoa, TransicaoPessoaServico> {
	
	/**
	 * Consulta a entidade TransicaoPessoa usando idInstituicao, idUnidadeInst e idPessoaLegado 
	 * como filtro.
	 * @param transicao 
	 * @return o resultado da consulta
	 */
	public List<TransicaoPessoa> listar(TransicaoPessoa transicao) {
		return getServico().listar(transicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TransicaoPessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTransicaoPessoaServico();
	}

	/**
	 * Lista todas as transi��es da pessoa informada. Utilizado na altera��o do CPF/CNPJ.
	 * @param pessoa A pessoa a ser consultada.
	 * @return lista com todas as transi��es da pessoa, independente do compartilhamento.
	 */
	public List<TransicaoPessoaVO> listarTodasTransicoes(Pessoa pessoa) {
		return getServico().listarTodasTransicoes(pessoa);
	}
	
	/**
	 * Verifica se existe alguma refer�ncia para a institui��o na TransicaoPessoa
	 * @param idInstituicao id da Institui��o que deseja verificar.
	 * @return Boolean verdadeiro caso a institui��o esteja cadastrada na TransicaoPessoa ou false caso n�o esteja.
	 */
	public Boolean verificarInstituicao(Integer idInstituicao){
		return getServico().verificarInstituicao(idInstituicao);
	}
	
	/**
	 * Valida se a pessoa possui relacionamento com o Bancoob
	 * 
	 * @throws BancoobException
	 */
	public void validarPessoaPossuiRelacionamentoBancoob(PessoaCompartilhamento pessoa)
			throws BancoobException {
		getServico().validarPessoaPossuiRelacionamentoBancoob(pessoa);
	}

	/**
	 * Lista as transi��es da pessoa compartilhamento para replica��o.
	 * @param pessoaCompartilhamento 
	 * @return
	 */
	public List<TransicaoPessoa> listarTransicoesParaReplicacao(PessoaCompartilhamento pessoaCompartilhamento) {
		return getServico().listarTransicoesParaReplicacao(pessoaCompartilhamento);
	}
	
	/**
	 * Verifica se a pessoaCompartilhamento possui relacionamento com o Bancoob ou n�o.
	 * @param pessoa A pessoa a ser verificada.
	 * @return se a pessoaCompartilhamento possui relacionamento com o Bancoob ou n�o.
	 * @throws BancoobException Caso ocorra algum problema.
	 */
	public boolean possuiRelacionamentoBancoob(PessoaCompartilhamento pessoa) 
			throws BancoobException {
		return getServico().possuiRelacionamentoBancoob(pessoa);
	}

	/**
	 * Recupera a transi��o com o Bancoob se existir ou nulo caso contr�rio.
	 * @param pessoa A pessoa da qual se deseja a trabsi��o.
	 * @return a transi��o com o Bancoob se existir ou nulo caso contr�rio.
	 * @throws BancoobException Caso ocorra algum problema.
	 */
	public TransicaoPessoa obterTransicaoBancoob(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().obterTransicaoBancoob(pessoa);
	}
	
	/**
	 * Obt�m a transi��o de determinada {@link Pessoa} em uma determinada {@link Instituicao}
	 * 
	 * @param pessoa
	 *            a pessoa
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @return a transi��o
	 * @throws BancoobException
	 *             se houver alguma exce��o
	 */
	public TransicaoPessoa obterTransicaoPorPessoaInstituicao(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException {
		return getServico().obterTransicaoPorPessoaInstituicao(pessoa, idInstituicao);
	}
	
	/**
	 * Lista as transi��es de uma PessoaCompartilhamento
	 * @param pessoaCompartilhamento A pessoa  
	 * @return as transi��es de uma PessoaCompartilhamento.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	public List<TransicaoPessoa> listar(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return getServico().listar(pessoaCompartilhamento);
	}

	/**
	 * Verifica se eh incorporacao finalizada.
	 *
	 * @param idInsituicaoIncorporadora o valor de id insituicao incorporadora
	 * @param idInsituicaoIncorporada o valor de id insituicao incorporada
	 * @return {@code true}, se for incorporacao finalizada
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public Boolean isIncorporacaoFinalizada(Integer idInsituicaoIncorporadora,
			Integer idInsituicaoIncorporada) throws BancoobException {
		return getServico().isIncorporacaoFinalizada(idInsituicaoIncorporadora, idInsituicaoIncorporada);
	}
	
	/**
	 * Obt�m a quantidade de pessoas em uma institui��o
	 * 
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @return a quantidade de pessoas
	 */
	public Integer buscarQuantidadePessoasInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().buscarQuantidadePessoasInstituicao(idInstituicao);
	}

	/**
	 * Buscar quantidades pessoas alteracao grupo.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoCompartilhamento o valor de codigo compartilhamento
	 * @return Map
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public Map<String, Integer> buscarQuantidadesPessoasAlteracaoGrupo(
			Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException {
		return getServico().buscarQuantidadesPessoasAlteracaoGrupo(idInstituicao, codigoCompartilhamento);
	}
	
	/**
	 * Obt�m as pessoas de uma determinada institui��o que existem em um determinado grupo de
	 * compartilhamento
	 * 
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @param codigoCompartilhamento
	 *            o c�digo do {@link CompartilhamentoCadastro}
	 * @return as pessoas
	 */
	public List<TransicaoPessoa> buscarPessoasExistentesGrupo(Integer idInstituicao,
			Short codigoCompartilhamento) throws BancoobException {
		return getServico().buscarPessoasExistentesGrupo(idInstituicao, codigoCompartilhamento);
	}

	/**
	 * Recupera os dados ({@link TransicaoPessoa} de origem e destino), para altera��o de grupo de
	 * compartilhamento, das pessoas que j� est�o no grupo de destino
	 * @param criterios os crit�rios da busca
	 * 
	 * @return lista com os dados
	 * @throws BancoobException
	 */
	public ConsultaDto<DadosAlteracaoGrupoVO> buscarDadosAlteracaoGrupoPessoasNoDestino(
			ConsultaDto<DadosAlteracaoGrupoVO> criterios) throws BancoobException {
		return getServico().buscarDadosAlteracaoGrupoPessoasNoDestino(criterios);
	}
	
	/**
	 * Obt�m a transicao por idPessoa e Idinstituicao
	 * @param idPessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		return getServico().obterTransicaoPorPessoaCompartilhamentoInstituicao(idPessoaCompartilhamento, idInstituicao);
	}

}