/* 
 * Sicoob
 * RelacionamentoPessoaDelegate.java 
 * Criado em: 24/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.negocio.servicos.RelacionamentoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Delegate para {@link RelacionamentoPessoa}
 *
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
public class RelacionamentoPessoaDelegate extends
	EntidadeCadastroDelegate<RelacionamentoPessoa, RelacionamentoPessoaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarRelacionamentoPessoaServico();
	}
	
	/**
	 * Pesquisar relacionamentos exercidos.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 */
	public ConsultaDto<RelacionamentoPessoa> pesquisarRelacionamentosExercidos(
			ConsultaDto<RelacionamentoPessoa> criterios) {
		return localizarServico().pesquisarRelacionamentosExercidos(criterios);
	}

	/**
	 * Recupera relacionamentos com as seguintes características iguais:
	 * <ul>
	 * <li>são entre as mesmas pessoas</li>
	 * <li>possuem o mesmo tipo de relacionamento</li>
	 * <li>são da mesma instituição</li>
	 * </ul>
	 * 
	 * <p><b>** IMPORTANTE! **</b></p> 
	 * <p>Esta consulta <b>não</b> é paginada!!</p>
	 * 
	 * @param pessoa
	 *            a pessoa que possui o relacionamento
	 * @param pessoaRelacionada
	 *            a pessoa relacionada
	 * @param tipoRelacionamento
	 *            o tipo de relacionamento entre as pessoas
	 * @param idInstituicao
	 *            o ID da instituição das pessoas
	 */
	public List<RelacionamentoPessoa> pesquisarRelacionamentosSemelhantes(Pessoa pessoa,
			Pessoa pessoaRelacionada, TipoRelacionamentoPessoa tipoRelacionamento,
			Integer idInstituicao) {
		
		return getServico().pesquisarRelacionamentosSemelhantes(pessoa, pessoaRelacionada,
				tipoRelacionamento, idInstituicao);
	}

	/**
	 * Obtém a data atual do movimento do produto CADASTRO DE CLIENTES DO SFN -
	 * CCS (ID 41)
	 * @param idInstituicao 
	 * 
	 * @return {@link RetornoDTO} com a data atual do movimento
	 */
	public Date obterDataMovimentoCCS(Integer idInstituicao) throws BancoobException {

		return getServico().obterDataMovimentoCCS(idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void excluirEntidade(RelacionamentoPessoa objeto) throws BancoobException {
		getServico().excluirEntidade(objeto);
	}
	
	/**
	 * Recuperar relacionamentos incorporacao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<RelacionamentoPessoa> recuperarRelacionamentosIncorporacao(ConsultaDto<RelacionamentoPessoa> criterios)
			throws BancoobException {
		return getServico().recuperarRelacionamentosIncorporacao(criterios);
	}

	/**
	 * Incluir.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @param incluirReverso o valor de incluir reverso
	 * @return RelacionamentoPessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento, Boolean incluirReverso)
			throws BancoobException {
		return getServico().incluir(relacionamento, incluirReverso);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento)
			throws BancoobException {
		return getServico().incluir(relacionamento);
	}
	
	/**
	 * Pesquisar relacionamentos vigentes por filtro.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamento) throws BancoobException{
		return getServico().pesquisarRelacionamentosVigentesPorFiltro(relacionamento);
	}
	
	/**
	 * O método Gerar relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void gerarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException{
		getServico().gerarRelacionamentoReverso(relacionamento);
	}
	
	/**
	 * O método Alterar relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void alterarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException {
		getServico().alterarRelacionamentoReverso(relacionamento);
	}
	
	/**
	 * O método Excluir relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluirRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException{
		getServico().excluirRelacionamentoReverso(relacionamento);
	}
	
	/**
	 * @param pessoa
	 * @return
	 * @throws BancoobException
	 */
	public void verificaPendenciasPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException{
		getServico().verificaPendenciasPessoaRelacionada(relacionamento);
	}
	
	public Boolean validarTransicaoPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException {
		return getServico().validarTransicaoPessoaRelacionada(relacionamento);
	}
	
	public List<RelacionamentoPessoaBase> listarHistoricoEspecifico(
			ConsultaDtoCUC<RelacionamentoPessoa> criterios) throws BancoobException {
		return getServico().listarHistoricoEspecifico(criterios);
	}
	
}