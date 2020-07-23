/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.bancoob.validacao.Validacao;
import br.com.bancoob.validacao.ValidacaoCnpj;
import br.com.sicoob.capes.cadastro.negocio.excecao.AtividadeEconomicaTipoPessoaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * Classe utilizada na validação dos dados de entrada de pessoa jurídica.
 *  
 * @author erico.junior
 */
public class ValidacaoPessoaJuridica extends ValidacaoPessoaCompartilhamento<PessoaJuridica> {

	/** A constante DATA_CONSTITUICAO. */
	private static final String DATA_CONSTITUICAO = "Data da Constituição";
	
	/** A constante DATA_ORGAO. */
	private static final String DATA_ORGAO = "Data do Registro";
	
	/** A constante DATA_ALTERACAO. */
	private static final String DATA_ALTERACAO = "Data de Alteração";
	
	/** A constante DATA_ATA. */
	private static final String DATA_ATA = "Data de Registro da ATA";
	
	/** A constante NUMERO_ORGAO. */
	private static final String NUMERO_ORGAO = "Nº do Registro no Órgão";
	
	/** A constante NUMERO_ALTERACAO. */
	private static final String NUMERO_ALTERACAO = "Nº da Última Alteração (Arquivamento)";
	
	/** A constante NUMERO_REPRESENTACAO. */
	private static final String NUMERO_REPRESENTACAO = "Nº do Registro de Representação";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoDocumento() {
		return "CNPJ";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNome() {
		return "Razão Social";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNomeCompleto() {
		return "Razão Social Completa";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocioEspecifico(PessoaJuridica pessoa) throws BancoobException {
		
		
		DateTimeDB dataConstituicao = pessoa.getDataConstituicao();
		DateTimeDB dataRegistroJunta = pessoa.getDataRegistroJuntaComercial();
		DateTimeDB dataRegistroAta = pessoa.getDataRegistroRepresentacao();
		DateTimeDB dataUltima = pessoa.getDataUltimaAlteracaoContratoSocial();
		DateTimeDB dataInclusao = pessoa.getDataInclusaoSistema();
		Date dataAtual = DataUtil.obterDataAtual();
		
		if(dataConstituicao != null) {
			validarDataInicioMenor(dataConstituicao, dataAtual, DATA_CONSTITUICAO, DATA_ATUAL);
			validarDataInicioMenor(dataConstituicao, dataInclusao,
					DATA_CONSTITUICAO, DATA_INCLUSAO);
		}
		
		if(dataRegistroJunta != null) {
			validarDataInicioMenor(dataRegistroJunta, dataAtual, DATA_ORGAO, DATA_ATUAL);
			validarDataInicioMenor(dataRegistroJunta, dataInclusao, DATA_ORGAO, DATA_INCLUSAO);
			validarDataMaior(dataRegistroJunta, dataConstituicao,  DATA_ORGAO, DATA_CONSTITUICAO);
		}

		if(dataUltima != null) {
			validarDataInicioMenor(dataUltima, dataAtual, DATA_ALTERACAO, DATA_ATUAL);
			validarDataMaior(dataUltima, dataConstituicao, DATA_ALTERACAO, DATA_CONSTITUICAO);
		}
		
		if(dataRegistroAta != null) {
			validarDataInicioMenor(dataRegistroAta, dataAtual, DATA_ATA, DATA_ATUAL);
		}	
		
		validarDataMaior(dataRegistroAta, dataConstituicao, DATA_ATA, DATA_CONSTITUICAO);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarObrigatoriedadeCondicionalEspecifica(PessoaJuridica pessoa)
			throws CampoNaoInformadoException {
		
		validarNumeroDataPreenchidos(pessoa.getDataRegistroJuntaComercial(), 
				pessoa.getNumeroRegistroJuntaComercial(), DATA_ORGAO, NUMERO_ORGAO);

		validarNumeroDataPreenchidos(pessoa.getDataUltimaAlteracaoContratoSocial(), 
				pessoa.getNumeroUltimaAlteracaoContratoSocial(), DATA_ALTERACAO, NUMERO_ALTERACAO);

		validarNumeroDataPreenchidos(pessoa.getDataRegistroRepresentacao(), 
				pessoa.getNumeroRegistroRepresentacao(), DATA_ATA, NUMERO_REPRESENTACAO);
	}
	
	/**
	 * O método Validar numero data preenchidos.
	 *
	 * @param data o valor de data
	 * @param numero o valor de numero
	 * @param campoData o valor de campo data
	 * @param campoNumero o valor de campo numero
	 * @throws CampoNaoInformadoException lança a exceção CampoNaoInformadoException
	 */
	private void validarNumeroDataPreenchidos(Date data, String numero, 
			String campoData, String campoNumero) throws CampoNaoInformadoException {

		boolean dataPreenchida = data != null;
		boolean numeroPreenchido = StringUtils.isNotBlank(numero);
		
		if (dataPreenchida && !numeroPreenchido) {
			throw new CampoNaoInformadoException(campoNumero);		
		}

		if (numeroPreenchido && !dataPreenchida) {
			throw new CampoNaoInformadoException(campoData);		
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarAtividadeEconomica(AtividadeEconomica atividade)
			throws AtividadeEconomicaTipoPessoaException {

		 if (atividade != null && isAtividadeEconomicaPessoaFisica(atividade)) {
			throw new AtividadeEconomicaTipoPessoaException(
					TipoPessoaEnum.PESSOA_JURIDICA.getDescricao());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Validacao obterValidacaoDocumento(String cpfCnpj) {
		return new ValidacaoCnpj(cpfCnpj, "", getNomeAtributoDocumento());
	}
}