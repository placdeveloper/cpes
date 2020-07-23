/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.builder;

import java.util.HashSet;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * Builder para pessoas f�sicas.
 * 
 * @author Erico.Junior
 */
public abstract class PessoaFisicaBuilder implements
		PessoaCompartilhamentoBuilder<PessoaFisica> {

	/** A constante NAO_INFORMADO. */
	protected static final String NAO_INFORMADO = "N�O INFORMADO";
	
	/** A constante NOME_PAI. */
	protected static final String NOME_PAI = "NOME DO PAI N�O INFORMADO";
	
	/** A constante QUANTIDADE_DEPENDENTES. */
	protected static final short QUANTIDADE_DEPENDENTES = (short)0;
	
	/** A constante TIPO_PESSOA. */
	private static final TipoPessoa TIPO_PESSOA = new TipoPessoa();
	
	static {
		TIPO_PESSOA.setCodTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
	}

	/** O atributo pessoaFisica. */
	private transient PessoaFisica pessoaFisica;
	
	/**
	 * Instancia um novo PessoaFisicaBuilder.
	 */
	public PessoaFisicaBuilder() {
		
		pessoaFisica = new PessoaFisica();
		Pessoa pessoa = new Pessoa();
		
		pessoa.setTipoPessoa(TIPO_PESSOA);
		pessoa.setCompartilhamentos(new HashSet<PessoaCompartilhamento>());
		pessoa.getCompartilhamentos().add(pessoaFisica);
		
		pessoaFisica.setPessoa(pessoa);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public void criarDadosEspecificos() {
		criarNomePai();
		criarNumeroDocumento();
		criarOrgaoExpedidorDocumento();
		criarOcupacaoProfissional();
		criarEstadoCivil();
		criarQuantidadeDependentes();
		criarGrauInstrucao();
		criarVinculoEmpregaticio();
		criarTipoSexo();
		criarTipoDocumento();
		criarNacionalidade();
	}

	/**
	 * O m�todo Criar nome pai.
	 */
	public abstract void criarNomePai();

	/**
	 * O m�todo Criar numero documento.
	 */
	public abstract void criarNumeroDocumento();

	/**
	 * O m�todo Criar orgao expedidor documento.
	 */
	public abstract void criarOrgaoExpedidorDocumento();

	/**
	 * O m�todo Criar ocupacao profissional.
	 */
	public abstract void criarOcupacaoProfissional();

	/**
	 * O m�todo Criar estado civil.
	 */
	public abstract void criarEstadoCivil();

	/**
	 * O m�todo Criar quantidade dependentes.
	 */
	public abstract void criarQuantidadeDependentes();

	/**
	 * O m�todo Criar grau instrucao.
	 */
	public abstract void criarGrauInstrucao();

	/**
	 * O m�todo Criar vinculo empregaticio.
	 */
	public abstract void criarVinculoEmpregaticio();

	/**
	 * O m�todo Criar tipo sexo.
	 */
	public abstract void criarTipoSexo();

	/**
	 * O m�todo Criar tipo documento.
	 */
	public abstract void criarTipoDocumento();

	/**
	 * O m�todo Criar nacionalidade.
	 */
	public abstract void criarNacionalidade();

	/**
	 * {@inheritDoc}
	 */
	public PessoaFisica getPessoaCompartilhamento() {
		return pessoaFisica;
	}	
	
}
