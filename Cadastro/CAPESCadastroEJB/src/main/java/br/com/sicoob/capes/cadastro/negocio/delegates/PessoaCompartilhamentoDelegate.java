/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaCompartilhamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * @author erico.junior
 * 
 */
public class PessoaCompartilhamentoDelegate extends
		EntidadeCadastroDelegate<PessoaCompartilhamento, PessoaCompartilhamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaCompartilhamentoServico localizarServico() {

		return CAPESCadastroServiceLocator.getInstance().localizarPessoaCompartilhamentoServico();
	}

	/**
	 * Consulta a pessoa a partir do codigo dela no legado na instituicao do usuario logado.
	 * 
	 * @param idPessoaLegado
	 *            O codigo da pessoa no legado da instituicao do usuario logado.
	 * @return A Pessoa consultado para o codigo do legado.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	public PessoaCompartilhamento consultarPessoaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException {

		return getServico().consultarPessoaPorIdPessoaLegado(idPessoaLegado);
	}

	/**
	 * Consulta a pessoa a partir do identificador da pessoa no legado e na instituicao informada.
	 * 
	 * @param idPessoaLegado
	 *            O identificador da pessoa no legado.
	 * @param instituicao
	 *            A instituicao na qual a pessoa sera consultada.
	 * @return a pessoa a partir do identificador da pessoa no legado e na instituicao informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	public PessoaCompartilhamento consultarPessoaPorIdPessoaLegado(Integer idPessoaLegado, Instituicao instituicao)
			throws BancoobException {

		return getServico().consultarPessoaPorIdPessoaLegado(idPessoaLegado, instituicao);
	}

	/**
	 * Serviço disponibilizado enquanto o serviço de replicação de pessoas do sql para o DB2 estiver funcionando. FIXME
	 * 
	 * @param pessoa
	 *            A pessoa a ser alterada.
	 * @return A pessoa incluída.
	 * @throws BancoobException
	 *             Caso ocorra alguma exception.
	 */
	public void alterarSemReplicacao(PessoaCompartilhamento pessoa) throws BancoobException {

		getServico().alterarSemReplicacao(pessoa);
	}

	/**
	 * Serviço disponibilizado enquanto o serviço de replicação de pessoas do sql para o DB2 estiver funcionando. FIXME
	 * 
	 * @param pessoa
	 *            A pessoa a ser alterada.
	 * @return A pessoa incluída.
	 * @throws BancoobException
	 *             Caso ocorra alguma exception.
	 */
	public void incluirSemReplicacao(PessoaCompartilhamento pessoa) throws BancoobException {

		getServico().incluirSemReplicacao(pessoa);
	}

	/**
	 * Consulta o documento cpf ou cnpj para inclusao.
	 * 
	 * @param cpfCnpj
	 *            O cpf ou cnpj da pessoa que sera incluida.
	 * @param tipoPessoa
	 *            O tipo da pessoa.
	 * @return A pessoa encontrada ou null, caso o documento nao esteja cadastrado na base.
	 */
	public PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa)
			throws BancoobException {

		return getServico().consultarCpfCnpjParaInclusao(cpfCnpj, tipoPessoa);
	}
	
	/**
	 * Consulta o documento cpf ou cnpj para inclusao na cooperativa informada.
	 * @param cpfCnpj O cpf ou cnpj da pessoa que sera incluida.
	 * @param tipoPessoa O tipo da pessoa.
	 * @param numeroCooperativa
	 * @return A pessoa encontrada ou null, caso o documento nao esteja cadastrado na base.
	 * @throws BancoobException
	 */
	public PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa, Integer numeroCooperativa) throws BancoobException {
		return getServico().consultarCpfCnpjParaInclusao(cpfCnpj, tipoPessoa, numeroCooperativa);
	}

	/**
	 * Metodo para recuperar a pessoa por tipo, cooperativa e cpf/cnpj
	 * @param cpfCnpj
	 * @param tipoPessoa
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	public PessoaCompartilhamento consultarPessoa(String cpfCnpj, TipoPessoa tipoPessoa, Integer numeroCooperativa) throws BancoobException {
		return getServico().consultarPessoa(cpfCnpj, tipoPessoa, numeroCooperativa);
	}

	/**
	 * Consultar.
	 *
	 * @param pessoa o valor de pessoa
	 * @param compartilhamentoCadastro o valor de compartilhamento cadastro
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public PessoaCompartilhamento consultar(Pessoa pessoa, CompartilhamentoCadastro compartilhamentoCadastro)
			throws BancoobException {

		return getServico().consultar(pessoa, compartilhamentoCadastro);
	}

	/**
	 * Inclui a pessoa informada.
	 * 
	 * @param pessoaCompartilhamento
	 *            A pessoa a ser incluida
	 * @param dataInclusao
	 *            A data de inclusao no sistema e no SFN.
	 * @param instituicao
	 *            A instituicao que sera responsavel pelo cadastro.
	 * @return A pesso incluida
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DateTimeDB dataInclusao,
			Instituicao instituicao) throws BancoobException {

		return getServico().incluir(pessoaCompartilhamento, dataInclusao, instituicao);
	}

	/**
	 * Consultar pessoa por documento.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public PessoaCompartilhamento consultarPessoaPorDocumento(Integer idInstituicao, String cpfCnpj)
			throws BancoobException {

		return getServico().consultarPessoaPorDocumento(idInstituicao, cpfCnpj);
	}

	/**
	 * Altera o nome da mae da pessoa fisica.
	 * 
	 * @param pessoa
	 *            A pessoa fisica que tera o nome da mae alterado.
	 * @param nomeMae
	 *            O novo nome da mae.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	public void alterarNomeMae(PessoaFisica pessoa, String nomeMae) throws BancoobException {

		getServico().alterarNomeMae(pessoa, nomeMae);
	}

	/**
	 * Altera o beneficiario do INSS.
	 * 
	 * @param beneficiario
	 *            O beneficiario do INSS que tera o cadastro alterado.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	public void alterarBeneficiarioINSS(PessoaFisica beneficiario) throws BancoobException {

		getServico().alterarBeneficiarioINSS(beneficiario);
	}

	/**
	 * Pesquisa as pessoas de uma instituicao para a incorporacao.
	 * 
	 * @param criterios
	 *            Os criterios de pesquisa da consulta.
	 * @param instituicao
	 *            A instituicao que esta sendo incorporada.
	 * @return as pessoas de uma instituicao para a incorporacao.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	public ConsultaDto<PessoaCompartilhamento> pesquisarParaIncorporacao(ConsultaDto<PessoaCompartilhamento> criterios,
			Instituicao instituicao) throws BancoobException {

		return getServico().pesquisarParaIncorporacao(criterios, instituicao);
	}

	/**
	 * Obtem a data da ultima atualizacao para a pessoa de id {@code idPessoaCompartilhamento}
	 * 
	 * @param idPessoaCompartilhamento
	 *            o ID da pessoa da qual se deseja obter a data da ultima atualizacao
	 * @return a data da ultima atualizacao
	 */
	public Date obterDataUltimaAtualizacao(Long idPessoaCompartilhamento) throws BancoobException {

		return getServico().obterDataUltimaAtualizacao(idPessoaCompartilhamento);
	}

	/**
	 * Altera a data de inclusao SFN da pessoa
	 * 
	 * @param idPessoaLegado
	 * @param instituicao
	 * @param dataInclusaoSFN
	 * @throws BancoobException
	 */
	public void alterarDataCadastramentoSFN(Integer idPessoaLegado, Instituicao instituicao, DateTimeDB dataInclusaoSFN)
			throws BancoobException {

		getServico().alterarDataCadastramentoSFN(idPessoaLegado, instituicao, dataInclusaoSFN);
	}

	/**
	 * Lista as pessoas de um compartilhamento.
	 * 
	 * @param criterios
	 *            Os criterios da pesquisa.
	 * @return
	 * @throws BancoobException
	 */
	public List<PessoaCompartilhamento> listarCompartilhamento(ConsultaPessoaDTO criterios) throws BancoobException {

		return getServico().listarCompartilhamento(criterios);
	}

	/**
	 * @param compartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public Long obterMaiorIdPessoasPorCompartilhamento(CompartilhamentoCadastro compartilhamento)
			throws BancoobException {

		return getServico().obterMaiorIdPessoasPorCompartilhamento(compartilhamento);
	}

	/**
	 * Altera o grupo de compartilhamento das pessoas de uma determinada instituicao, que ainda nao fazem parte
	 * deste grupo e que sao unicas na origem
	 * 
	 * @param idInstituicao
	 *            o ID da instituicao de origem
	 * @param codigoCompartilhamento
	 *            o codigo do {@link CompartilhamentoCadastro} de destino
	 */
	public void alterarGrupoPessoasInexistentesUnicas(Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException {

		getServico().alterarGrupoPessoasInexistentesUnicas(idInstituicao, codigoCompartilhamento);
	}

	/**
	 * Recupera as pessoas de uma determinada instituicao, que ainda nao fazem parte deste grupo e que sao
	 * compartilhadas na origem
	 * 
	 * @param criterios
	 *            os criterios para realizacao da pesquisa com os seguintes atributos do filtro preenchidos:
	 *            {@link DadosAlteracaoGrupoVO#getCodigoCompartilhamentoDestino(Short)} e
	 *            {@link DadosAlteracaoGrupoVO#getIdInstituicaoOrigem()}
	 * 
	 * @return as pessoas
	 */
	public ConsultaDto<PessoaCompartilhamento> buscarPessoasInexistentesCompartilhadas(
			ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException {

		return getServico().buscarPessoasInexistentesCompartilhadas(criterios);
	}

	/**
	 * Incluir.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param dadosReceita o valor de dados receita
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento,
			DadosReceitaFederalVO dadosReceita) throws BancoobException {

		return getServico().incluir(pessoaCompartilhamento, dadosReceita);
	}

	/**
	 * Consulta {@link PessoaCompartilhamento} por documento CPF/CNPJ e o código do compartilhamento cadastro
	 * 
	 * @param codCompartilhamentoCadastro
	 *            Código do compartilhamento {@link CompartilhamentoCadastro}
	 * @param documento
	 *            CPF/CNPJ da pessoa
	 * @return {@link PessoaCompartilhamento}
	 * @throws BancoobException
	 */
	public PessoaCompartilhamento consultarPessoaPorDocumentoCodCompartilhamento(Short codCompartilhamentoCadastro,
			String documento) throws BancoobException {

		return getServico().consultarPessoaPorDocumentoCodCompartilhamento(codCompartilhamentoCadastro, documento);
	}

	/**
	 * Renova o cadastro da pessoa
	 * 
	 * @param pessoa
	 * @throws BancoobException
	 */
	public void renovarCadastro(PessoaCompartilhamento pessoa) throws BancoobException {

		getServico().renovarCadastro(pessoa);
	}

	/**
	 * Lista todos os registros de PessoaCompartilhamento de uma Pessoa.
	 * 
	 * @param cpfCnpj
	 *            o CPF/CNPJ da Pessoa
	 * @return lista de PessoaCompartilhamento
	 * @see Pessoa
	 */
	public List<PessoaCompartilhamento> listarPessoasMesmoDocumento(String cpfCnpj) throws BancoobException {

		return getServico().listarPessoasMesmoDocumento(cpfCnpj);
	}
	
	/**
	 * Pesquisar codigo compartilhamento.
	 *
	 * @param instituicao o valor de instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer pesquisarCodigoCompartilhamento(Instituicao instituicao) throws BancoobException{
		return getServico().pesquisarCodigoCompartilhamento(instituicao);
	}
	
	/**
	 * Consulta as filiais de um cnpj.
	 * 
	 * @param codCompartilhamentoCadastro
	 * @param cnpj
	 * @return lista com as pessoasCompartilhamento
	 */
	public List<PessoaCompartilhamento> consultarFiliais(Short codCompartilhamentoCadastro, String cnpj) throws BancoobException {
		return getServico().consultarFiliais(codCompartilhamentoCadastro, cnpj);
	}

	/**
	 * Atualiza o Código do Tipo de Empresa pertencente ao uma Pessoa Juridica
	 * @param pessoa
	 * @throws BancoobException
	 */
	public void atualizaCodTipoEmpresa(PessoaJuridica pessoa) throws BancoobException {
		getServico().atualizaCodTipoEmpresa(pessoa);
	}
	
	/**
	 * Faz a consulta da pessoaCompartilhamento pelo identificador da pessoa e
	 * instituição.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
	public PessoaCompartilhamento consultarPorIdPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().consultarPorIdPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Incluir na cooperativa informada
	 * 
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		return getServico().incluir(pessoaCompartilhamento, numeroCooperativa, unidadeInstituicao);
	}

	/**
	 * Incluir na cooperativa informada.
	 * 
	 * @param pessoaCompartilhamento
	 * @param dadosReceita
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		return getServico().incluir(pessoaCompartilhamento, dadosReceita, numeroCooperativa, unidadeInstituicao);
	}
	
	/**
	 * Altera o perfil de cadastro.
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	public void alterarPerfilCadastro(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		getServico().alterarPerfilCadastro(pessoaCompartilhamento);
	}
	
	/**
	 * Renova o cadastro automatico.
	 * @param aprovavel
	 * @param idPessoa
	 * @param usuarioRenovacao
	 * @throws BancoobException
	 */
	public void renovarCadastroAutomatico(Aprovavel aprovavel, Integer idPessoa, String usuarioRenovacao) throws BancoobException {
		getServico().renovarCadastroAutomatico(aprovavel,idPessoa, usuarioRenovacao);
	}
	
	/**
	 * Verifica se o cadastro deve ser incluído pelo faça-parte ou não
	 * 
	 * @param codigoTipoPessoa
	 *            o codigo do tipo da pessoa
	 * @return se o cadastro deverá ser efetuado pelo faça-parte ou não
	 * @throws BancoobException
	 */
	public boolean obterParametroInclusaoFacaParte(Short codigoTipoPessoa) throws BancoobException {
		return getServico().obterParametroInclusaoFacaParte(codigoTipoPessoa);
	}

}