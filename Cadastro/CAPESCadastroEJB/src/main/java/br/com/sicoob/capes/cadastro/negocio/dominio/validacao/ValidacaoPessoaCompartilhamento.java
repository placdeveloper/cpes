/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.bancoob.validacao.ResultadoValidacao;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CnaeFiscalDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.AtividadeEconomicaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.AtividadeEconomicaTipoPessoaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoMenorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CodigoCnaeInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataMaiorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataMenorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 *
 */
public abstract class ValidacaoPessoaCompartilhamento<P extends PessoaCompartilhamento> implements Validacao<P> {

	/** A constante ATIVIDADE_ECONÔMICA. */
	private static final String ATIVIDADE_ECONÔMICA = "Atividade Econômica";
	
	/** A constante TIPO_PESSOA. */
	private static final String TIPO_PESSOA = "Tipo Pessoa";
	
	/** A constante DATA_INCLUSAO. */
	protected static final String DATA_INCLUSAO = "Data da Inclusão no Sistema";
	
	/** A constante DATA_ATUAL. */
	protected static final String DATA_ATUAL = "Data Atual";
	
	/** A constante CLIENTE_DESDE. */
	protected static final String CLIENTE_DESDE = "Cliente do SFN desde";
	
	/**
	 * Recupera o valor de nomeAtributoNome.
	 *
	 * @return o valor de nomeAtributoNome
	 */
	protected abstract String getNomeAtributoNome();
	
	/**
	 * Recupera o valor de nomeAtributoNomeCompleto.
	 *
	 * @return o valor de nomeAtributoNomeCompleto
	 */
	protected abstract String getNomeAtributoNomeCompleto();
	
	/**
	 * Recupera o valor de nomeAtributoDocumento.
	 *
	 * @return o valor de nomeAtributoDocumento
	 */
	protected abstract String getNomeAtributoDocumento();

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void validar(PessoaCompartilhamento pessoa) throws BancoobException {

		P pessoaTipada = (P) pessoa;
		
		validarObrigatoriedadeComum(pessoa);
		validarObrigatoriedadeCondicionalEspecifica(pessoaTipada);
		
		validarNegocioComum(pessoa);
		validarNegocioEspecifico(pessoaTipada);
		
		validarCNAE(pessoa.getCnae());
	}

	/**
	 * Valida a obrigatoriedade dos campos da pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @throws CampoNaoInformadoException
	 *             Caso algum atributo obrigatório não tenha sido informado.
	 */
	private void validarObrigatoriedadeComum(PessoaCompartilhamento pessoa)	throws CampoNaoInformadoException {
		
		if (pessoa.getPessoa().getTipoPessoa() == null) {
			throw new CampoNaoInformadoException(TIPO_PESSOA);
		}
		if (StringUtils.isBlank(pessoa.getNomePessoa())) {
			throw new CampoNaoInformadoException(getNomeAtributoNome());
		}
		if (StringUtils.isBlank(pessoa.getNomeCompleto())) {
			throw new CampoNaoInformadoException(getNomeAtributoNomeCompleto());
		}
		if (StringUtils.isBlank(pessoa.getPessoa().getCpfCnpj())) {
			throw new CampoNaoInformadoException(getNomeAtributoDocumento());
		}
		
		
		// Na alteração devemos validar as datas, na inclusão o próprio serviço as define. 
		if(pessoa.getId() != null) {
			
			if (pessoa.getAtividadeEconomica() == null) {
				throw new CampoNaoInformadoException(ATIVIDADE_ECONÔMICA);
			}
			
			if (pessoa.getDataInclusaoSistema() == null) {
				throw new CampoNaoInformadoException(DATA_INCLUSAO);
			}		
		}
	}
	
	/**
	 * Valida a obrigatoriedade condicional dos campos da pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @throws CampoNaoInformadoException
	 *             Caso algum atributo obrigatório não tenha sido informado.
	 */
	protected abstract void validarObrigatoriedadeCondicionalEspecifica(P pessoa)
			throws CampoNaoInformadoException;	

	/**
	 * Valida o negócio comum aos dois tipos de pessoa.
	 * @param pessoa A pessoa a ser validada.
	 * @throws FormatoInvalidoException Caso o documento não tenha um formato válido.
	 * @throws CampoMenorException Caso o nome completo seja menor que o nome da pessoa.
	 * @throws AtividadeEconomicaTipoPessoaException Caso a atividade econômica não seja compatível 
	 * com o tipo de pessoa.
	 */
	protected void validarNegocioComum(PessoaCompartilhamento pessoa) throws FormatoInvalidoException, 
		CampoMenorException, AtividadeEconomicaTipoPessoaException {
		
		validarCpfCnpj(pessoa.getPessoa().getCpfCnpj());

		String nomeCompleto = pessoa.getNomeCompleto();
		String nomePessoa = pessoa.getNomePessoa();
		if (nomeCompleto != null && nomeCompleto.trim().length() < nomePessoa.trim().length()) {
			throw new CampoMenorException(getNomeAtributoNomeCompleto(), getNomeAtributoNome());
		}		

		validarAtividadeEconomica(pessoa.getAtividadeEconomica());
	}
	
	/**
	 * Valida se o cpf ou cnpj informado é válido.
	 * 
	 * @param cpfCnpj
	 *            O cpf ou cnpj para validação
	 * @throws FormatoInvalidoException
	 *             Caso o documento seja inválido.
	 */
	private void validarCpfCnpj(String cpfCnpj) throws FormatoInvalidoException {

		br.com.bancoob.validacao.Validacao validacao = obterValidacaoDocumento(cpfCnpj);
		ResultadoValidacao resultado = validacao.executar();
		
		if (!resultado.isValido()) {
			throw new FormatoInvalidoException(resultado.getParametrosMensagem()[0]);
		}
	}		
	
	/**
	 * Valida o negócio específico de acordo com o tipo da pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa a ser validada.
	 * @throws BancoobException
	 *             Caso alguma validação falhe.
	 */
	protected abstract void validarNegocioEspecifico(P pessoa) throws BancoobException;	
	
	/**
	 * Valida a atividade economica de acordo com o tipo da pessoa.
	 * 
	 * @param atividade
	 *            A atividade a ser verificada.
	 * @throws AtividadeEconomicaTipoPessoaException
	 *             Caso a atividade não seja compatível com o tipo de pessoa.
	 */
	protected abstract void validarAtividadeEconomica(AtividadeEconomica atividade)
			throws AtividadeEconomicaTipoPessoaException;
	
	/**
	 * Recupera a validação a ser utilizada para o documento dependendo do tipo de pessoa.
	 * @param cpfCnpj O número do documento a ser validado.
	 * @return a validação a ser utilizada para o documento dependendo do tipo de pessoa.
	 */
	protected abstract br.com.bancoob.validacao.Validacao obterValidacaoDocumento(String cpfCnpj);

	/**
	 * Verifica se a atividade economica informada é "Pessoa Física = 55"
	 * 
	 * @param atividade
	 *            A atividade a ser verificada.
	 * @return se a atividade economica informada é "Pessoa Física = 55" ou não.
	 */
	protected boolean isAtividadeEconomicaPessoaFisica(AtividadeEconomica atividade) {
		Short codigoAtividade = atividade.getCodigo();
		return AtividadeEconomicaEnum.PESSOA_FISICA.getCodigo().equals(codigoAtividade);
	}
	
	/**
	 * O método Validar data inicio menor.
	 *
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @param nomeDataInicio o valor de nome data inicio
	 * @param nomeDataFim o valor de nome data fim
	 * @throws DataMaiorException lança a exceção DataMaiorException
	 */
	protected void validarDataInicioMenor(Date dataInicio, Date dataFim, String nomeDataInicio,
			String nomeDataFim) throws DataMaiorException {

		if (dataInicio != null && dataFim != null) {
			Date data1 = DataUtil.configurarPrimeiraDataIntervalo(dataInicio);
			Date data2 = DataUtil.configurarPrimeiraDataIntervalo(dataFim);

			if (data2.before(data1)) {
				 throw new DataMaiorException(nomeDataInicio, nomeDataFim);
			}
		}
	}

	/**
	 * O método Validar data maior.
	 *
	 * @param dataMaior o valor de data maior
	 * @param dataMenor o valor de data menor
	 * @param nomeDataMaior o valor de nome data maior
	 * @param nomeDataMenor o valor de nome data menor
	 * @throws DataMenorException lança a exceção DataMenorException
	 */
	protected void validarDataMaior(Date dataMaior, Date dataMenor, String nomeDataMaior,
			String nomeDataMenor) throws DataMenorException {

		if (dataMaior != null && dataMenor != null) {
			Date data1 = DataUtil.configurarPrimeiraDataIntervalo(dataMaior);
			Date data2 = DataUtil.configurarPrimeiraDataIntervalo(dataMenor);

			if (data1.before(data2)) {
				 throw new DataMenorException(nomeDataMaior, nomeDataMenor);
			}
		}
	}
	
	/**
	 * O método Validar cnae.
	 *
	 * @param cnaeFiscal o valor de cnae fiscal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarCNAE(CnaeFiscal cnaeFiscal) throws BancoobException {
		CnaeFiscalDelegate cnaeFiscalDelegate = CAPESCadastroFabricaDelegates.getInstance().criarCnaeFiscalDelegate();

		if(cnaeFiscal != null){
			CnaeFiscal cnaeConsulta = cnaeFiscalDelegate.obter(cnaeFiscal.getId());
			
			if(cnaeConsulta == null){
				throw new CodigoCnaeInvalidoException();
			}
		}
	}
}