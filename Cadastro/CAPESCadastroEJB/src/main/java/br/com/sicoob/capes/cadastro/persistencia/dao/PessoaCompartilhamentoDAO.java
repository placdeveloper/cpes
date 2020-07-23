/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaPessoaDTO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * @author erico.junior
 * 
 */
public interface PessoaCompartilhamentoDAO extends EntidadeCadastroDaoIF<PessoaCompartilhamento> {

	/**
	 * Consulta a pessoa compartilhada a partir do cnpj/cpf informado.
	 * 
	 * @param idInstituicao
	 * 
	 * @param documento
	 *            O CNPJ ou CPF da pessoa.
	 * @return A pessoa no compartilhamento.
	 */
	PessoaCompartilhamento consultarPessoaPorDocumento(Short codCompartilhamentoCadastro, String documento);

	/**
	 * Consulta a pessoa a partir dos dados informados na transicao.
	 * 
	 * @param transicao
	 *            A transicao da pessoa do legado para o CUC.
	 * @return A pessoa encontrada na transicao.
	 */
	PessoaCompartilhamento consultarPessoa(TransicaoPessoa transicao);
	
	
	
	/**
	 * Pesquisar codigo compartilhamento.
	 *
	 * @param instituicao o valor de instituicao
	 * @return Integer
	 */
	Integer pesquisarCodigoCompartilhamento(Instituicao instituicao);

	/**
	 * Pesquisa pessoas com relacionamento na instituicao do usuario logado, utilizando os criterios informados.
	 * 
	 * @param criterios
	 *            Os criterios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaProxy(ConsultaDto<PessoaPlataformaVO> criterios)
			throws BancoobException;

	
	/**
	 * Pesquisa pessoas com relacionamento na instituicao do usuario logado, utilizando os criterios informados.
	 * 
	 * Retorna as informacoes minimas e com limite de registros.
	 * 
	 * @param criterios
	 *            Os criterios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaProxyResumido(ConsultaDto<PessoaPlataformaVO> criterios)
			throws BancoobException;

	/**
	 * Consultar.
	 *
	 * @param pessoa o valor de pessoa
	 * @param compartilhamentoCadastro o valor de compartilhamento cadastro
	 * @return PessoaCompartilhamento
	 */
	PessoaCompartilhamento consultar(Pessoa pessoa, CompartilhamentoCadastro compartilhamentoCadastro);

	/**
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaCompartilhamento> pesquisarParaIncorporacao(ConsultaDto<PessoaCompartilhamento> criterios)
			throws BancoobException;

	/**
	 * Obtem a data da ultima atualizacao para a pessoa de id {@code idPessoaCompartilhamento}
	 * 
	 * @param idPessoaCompartilhamento
	 *            o ID da pessoa da qual se deseja obter a data da ultima atualizacao
	 * @return a data da ultima atualizacao
	 */
	Date obterDataUltimaAtualizacao(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Lista as pessoas de um compartilhamento.
	 * 
	 * @param criterios
	 *            Os criterios da pesquisa.
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaCompartilhamento> listarCompartilhamento(ConsultaPessoaDTO criterios) throws BancoobException;

	/**
	 * @param compartilhamento
	 * @return
	 * @throws BancoobException
	 */
	Long obterMaiorIdPessoasPorCompartilhamento(CompartilhamentoCadastro compartilhamento) throws BancoobException;

	/**
	 * Altera o grupo de compartilhamento das pessoas de uma determinada instituicao, que ainda nao fazem parte
	 * deste grupo e que sao unicas na origem
	 * 
	 * @param idInstituicao
	 *            {@link br.com.sicoob.capes.negocio.entidades.Instituicao#getIdInstituicao()} de origem
	 * @param codigoCompartilhamento
	 *            {@link CompartilhamentoCadastro#getCodigo()} de destino
	 */
	void alterarGrupoPessoasInexistentesUnicas(Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException;

	/**
	 * Recupera as pessoas de uma determinada instituicao, que ainda nao fazem parte deste grupo e que sao
	 * compartilhadas na origem
	 * 
	 * @param criterios
	 *            os criterios para realizacao da pesquisa com os seguintes atributos do filtro preenchidos:
	 *            {@link br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO#getCodigoCompartilhamentoDestino(Short)} e
	 *            {@link br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO#getIdInstituicaoOrigem()}
	 * 
	 * @return as pessoas
	 */
	ConsultaDto<PessoaCompartilhamento> buscarPessoasInexistentesCompartilhadas(
			ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException;

	/**
	 * Obtém os dados da pessoa do legado para utilização na plataforma de atendimento
	 * 
	 * @param cpfCnpj
	 *            O CPF (ou CNPJ) da pessoa
	 * @param idInstituicao
	 *            O ID da instituição
	 * 
	 * @return um mapa com os dados
	 */
	Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Realiza a consulta do componente de procurar pessoas externo CAPES
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios)
			throws BancoobException;

	/**
	 * Lista todos os registros de PessoaCompartilhamento de uma Pessoa.
	 * 
	 * @param cpfCnpj
	 *            o CPF/CNPJ da Pessoa
	 * @return lista de PessoaCompartilhamento
	 * @see Pessoa
	 */
	List<PessoaCompartilhamento> listarPessoasMesmoDocumento(String cpfCnpj) throws BancoobException;
	
	/**
	 * Consulta as filiais de um cnpj.
	 * 
	 * @param codCompartilhamentoCadastro
	 * @param cnpj
	 * @return lista com as pessoasCompartilhamento
	 */
	List<PessoaCompartilhamento> consultarFiliais(Short codCompartilhamentoCadastro, String cnpj) throws BancoobException;
	
	void atualizaCodTipoEmpresa(PessoaJuridica pessoa) throws BancoobException;

	/**
	 * Alterar o perfil do cadastro.
	 * 
	 * @param pessoaCompartilhamento
	 */
	void alterarPerfilCadastro(PessoaCompartilhamento pessoaCompartilhamento, String usuario) throws BancoobException;
	
	/**
	 * Inclui o histórico da pessoa compartilhamento ao alterar o perfil de cadastro.
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	void incluirHistoricoPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;
	
}
