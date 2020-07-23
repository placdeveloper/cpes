/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * @author erico.junior
 *
 */
public interface PessoaCompartilhamentoServico extends EntidadeCadastroServico<PessoaCompartilhamento> {

	/**
	 * Consulta a pessoa a partir do codigo dela no legado na cooperativa do
	 * usuario logado.
	 * 
	 * @param idPessoaLegado
	 *            O codigo da pessoa no legado da cooperativa do usuario logado.
	 * @return A Pessoa consultado para o codigo do legado.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	PessoaCompartilhamento consultarPessoaPorIdPessoaLegado(Integer idPessoaLegado)
			throws BancoobException;

	/**
	 * Consulta a pessoa usando a TransicaoPessoa como filtro.
	 * 
	 * @param pessoa
	 *            A transicaoPessoa que sera usada como filtro.
	 * @return se a pessoa consultada.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	PessoaCompartilhamento consultarPessoaPorTransicaoPessoa(TransicaoPessoa transicaoPessoa)
			throws BancoobException;

	/**
	 * Consulta a pessoa a partir do identificador da pessoa no legado e na
	 * instituicao informada.
	 * 
	 * @param idPessoaLegado
	 *            O identificador da pessoa no legado.
	 * @param instituicao
	 *            A instituicao na qual a pessoa sera consultada.
	 * @return a pessoa a partir do identificador da pessoa no legado e na
	 *         instituicao informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	PessoaCompartilhamento consultarPessoaPorIdPessoaLegado(Integer idPessoaLegado, Instituicao instituicao)
			throws BancoobException;

	/**
	 * Consulta o documento cpf ou cnpj para inclusao.
	 * @param cpfCnpj O cpf ou cnpj da pessoa que sera incluida.
	 * @param tipoPessoa O tipo da pessoa.
	 * @return A pessoa encontrada ou null, caso o documento nao esteja cadastrado na base.
	 */
	PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa) throws BancoobException;
	
	/**
	 * Consulta o documento cpf ou cnpj para inclusao na cooperativa informada.
	 * @param cpfCnpj O cpf ou cnpj da pessoa que sera incluida.
	 * @param tipoPessoa O tipo da pessoa.
	 * @param numeroCooperativa
	 * @return A pessoa encontrada ou null, caso o documento nao esteja cadastrado na base.
	 * @throws BancoobException
	 */
	PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Servico disponibilizado enquanto o servico de replicacao de pessoas do sql para o DB2
	 * estiver funcionando. FIXME 
	 * @param pessoa A pessoa a ser alterada.
	 * @throws BancoobException Caso ocorra alguma excecao.
	 */
	void alterarSemReplicacao(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Servico disponibilizado enquanto o servico de replicacao de pessoas do sql para o DB2
	 * estiver funcionando. FIXME 
	 * @param pessoa A pessoa a ser incluida.
	 * @throws BancoobException Caso ocorra alguma excecao.
	 */
	void incluirSemReplicacao(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Pesquisa pessoas com relacionamento na instituicao do usuario logado, utilizando os 
	 * criterios informados.
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
	 * Pesquisa pessoas com relacionamento na instituicao do usuario logado, utilizando os 
	 * criterios informados.
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
	 * @throws BancoobException lança a exceção BancoobException
	 */
	PessoaCompartilhamento consultar(Pessoa pessoa, CompartilhamentoCadastro compartilhamentoCadastro) 
			throws BancoobException;

	/**
	 * Consultar pessoa por documento.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	PessoaCompartilhamento consultarPessoaPorDocumento(Integer idInstituicao, String cpfCnpj) 
			throws BancoobException;
	
	/**
	 * Pesquisar codigo compartilhamento.
	 *
	 * @param instituicao o valor de instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer pesquisarCodigoCompartilhamento(Instituicao instituicao) throws BancoobException;
	
	/**
	 * Inclui a pessoa informada.
	 * @param pessoaCompartilhamento A pessoa a ser incluida 
	 * @param dataInclusao A data de inclusao no sistema e no SFN.
	 * @param instituicao A instituicao que sera responsavel pelo cadastro.
	 * @return A pesso incluida
	 * @throws BancoobException Caso ocorra alguma excecao. 
	 */
	PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DateTimeDB dataInclusao, 
			Instituicao instituicao) throws BancoobException;
	
	/**
	 * Altera o nome da mae da pessoa fisica.
	 * @param pessoa A pessoa fisica que tera o nome da mae alterado.
	 * @param nomeMae O novo nome da mae.
	 * @throws BancoobException Caso ocorra alguma excecao.
	 */
	void alterarNomeMae(PessoaFisica pessoa, String nomeMae) throws BancoobException;

	/**
	 * Altera o beneficiario do INSS.
	 * @param beneficiario O beneficiario do INSS que tera o cadastro alterado.
	 * @throws BancoobException Caso ocorra alguma excecao.
	 */
	void alterarBeneficiarioINSS(PessoaFisica beneficiario) throws BancoobException;
	
	/**
	 * Pesquisa as pessoas de uma instituicao para a incorporacao.
	 * @param criterios Os criterios de pesquisa da consulta.
	 * @param instituicao A instituicao que esta sendo incorporada.
	 * @return  as pessoas de uma instituicao para a incorporacao.
	 * @throws BancoobException Caso ocorra alguma excecao.
	 */
	ConsultaDto<PessoaCompartilhamento> pesquisarParaIncorporacao(ConsultaDto<PessoaCompartilhamento> criterios,
			Instituicao instituicao) throws BancoobException;
	
	/**
	 * Obtem a data da ultima atualizacao para a pessoa de id {@code idPessoaCompartilhamento}
	 * 
	 * @param idPessoaCompartilhamento
	 *            o ID da pessoa da qual se deseja obter a data da ultima atualizacao
	 * @return a data da ultima atualizacao
	 */
	Date obterDataUltimaAtualizacao(Long idPessoaCompartilhamento)
			throws BancoobException;
	
	/**
	 * Altera a data de inclusao SFN da pessoa
	 * @param idPessoaLegado
	 * @param instituicao
	 * @param dataInclusaoSFN
	 * @throws BancoobException
	 */
	void alterarDataCadastramentoSFN(Integer idPessoaLegado, Instituicao instituicao, DateTimeDB dataInclusaoSFN) 
			throws BancoobException;

	/**
	 * Lista as pessoas de um compartilhamento.
	 * @param criterios Os criterios da pesquisa.
	 * @return 
	 * @throws BancoobException
	 */
	List<PessoaCompartilhamento> listarCompartilhamento(ConsultaPessoaDTO criterios)
			throws BancoobException;
	
	/**
	 * @param compartilhamento
	 * @return
	 * @throws BancoobException
	 */
	Long obterMaiorIdPessoasPorCompartilhamento(CompartilhamentoCadastro compartilhamento)  
			throws BancoobException;

	/**
	 * Altera o grupo de compartilhamento das pessoas de uma determinada instituicao, que ainda nao
	 * fazem parte deste grupo e que sao unicas na origem
	 * 
	 * @param idInstituicao
	 *            {@link Instituicao#getIdInstituicao()} de origem
	 * @param codigoCompartilhamento
	 *            {@link CompartilhamentoCadastro#getCodigo()} de destino
	 */
	void alterarGrupoPessoasInexistentesUnicas(Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException;

	/**
	 * Recupera as pessoas de uma determinada instituicao, que ainda nao fazem
	 * parte deste grupo e que sao compartilhadas na origem
	 * 
	 * @param criterios
	 *            os criterios para realizacao da pesquisa com os seguintes
	 *            atributos do filtro preenchidos:
	 *            {@link DadosAlteracaoGrupoVO#getCodigoCompartilhamentoDestino(Short)}
	 *            e {@link DadosAlteracaoGrupoVO#getIdInstituicaoOrigem()}
	 * 
	 * @return as pessoas
	 */
	ConsultaDto<PessoaCompartilhamento> buscarPessoasInexistentesCompartilhadas(ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException;

	/**
	 * Incluir.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param dadosReceita o valor de dados receita
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento,
			DadosReceitaFederalVO dadosReceita) throws BancoobException;
	
	/**
	 * Incluir na cooperativa informada
	 * 
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException;
	
	/**
	 * Incluir na cooperativa informada.
	 * 
	 * @param pessoaCompartilhamento
	 * @param dadosReceita
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException;

	/**
	 * Obtém os dados da pessoa do legado para utilização na plataforma de
	 * atendimento
	 * 
	 * @param cpfCnpj
	 *            O CPF (ou CNPJ) da pessoa
	 * @param idInstituicao
	 *            O ID da instituição
	 * 
	 * @return um mapa com os dados
	 */
	Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao)
			throws BancoobException;

	/**
	 * Consulta {@link PessoaCompartilhamento} por documento CPF/CNPJ e o código do compartilhamento
	 * cadastro
	 * 
	 * @param codCompartilhamentoCadastro Código do compartilhamento {@link CompartilhamentoCadastro}
	 * @param documento CPF/CNPJ da pessoa
	 * @return {@link PessoaCompartilhamento}
	 * @throws BancoobException
	 */
	PessoaCompartilhamento consultarPessoaPorDocumentoCodCompartilhamento(
			Short codCompartilhamentoCadastro, String documento)throws BancoobException;

	/**
	 * Renova o cadastro da pessoa
	 * 
	 * @param pessoa
	 * @throws BancoobException
	 */
	void renovarCadastro(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Realiza a consulta do componente PROCURAR PESSOA EXTERNO CAPES
	 */
	ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException;

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
	/**
	 * 
	 * @param pessoa
	 * @throws BancoobException
	 */
	void atualizaCodTipoEmpresa(PessoaJuridica pessoa) throws BancoobException;

	/**
	 * Faz a consulta da pessoaCompartilhamento pelo identificador da pessoa e
	 * instituição.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
	PessoaCompartilhamento consultarPorIdPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Altera o perfil de cadastro.
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	void alterarPerfilCadastro(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Atualiza tipoRenovacaoCadastral, dataRenovacaoCadastral e usuarioRenovacao.
	 * @param aprovavel 
	 * @param idPessoa
	 * @param usuarioRenovacao
	 */
	void renovarCadastroAutomatico(Aprovavel aprovavel, Integer idPessoa, String usuarioRenovacao) throws BancoobException;
	
	/**
	 * Metodo para recuperar a pessoa por tipo, cooperativa e cpf/cnpj
	 * 
	 * @param cpfCnpj
	 * @param tipoPessoa
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	PessoaCompartilhamento consultarPessoa(String cpfCnpj, TipoPessoa tipoPessoa, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Verifica se o cadastro deve ser incluído pelo faça-parte ou não
	 * 
	 * @param codigoTipoPessoa
	 *            o codigo do tipo da pessoa
	 * @return se o cadastro deverá ser efetuado pelo faça-parte ou não
	 * @throws BancoobException
	 */
	boolean obterParametroInclusaoFacaParte(Short codigoTipoPessoa) throws BancoobException;

}