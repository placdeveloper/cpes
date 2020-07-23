package br.com.sicoob.capes.cadastro.negocio.enums;

import java.io.Serializable;

import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Enum utilizado para representar o tipo de autorizacao da autorizacao.
 *
 * @author Juan.Damasceno
 *
 */
public enum TipoAutorizacaoEntidadeEnum implements Serializable {

	// Constantes declaradas em ordem alfabética de descrição
	BEM_NOVO(TipoAutorizacaoEnum.BEM_NOVO, Bem.class),
	
	/** O atributo BEM. */
	BEM(TipoAutorizacaoEnum.BEM, br.com.sicoob.capes.negocio.entidades.bemantigo.Bem.class),
	
	/** O atributo CERTIDAO. */
	CERTIDAO(TipoAutorizacaoEnum.CERTIDAO, Certidao.class),
	
	/** O atributo ENDERECO. */
	ENDERECO(TipoAutorizacaoEnum.ENDERECO, Endereco.class),
	
	/** O atributo FONTE_RENDA. */
	FONTE_RENDA(TipoAutorizacaoEnum.FONTE_RENDA, FonteRenda.class),
	
	/** O atributo TRIBUTACAO. */
	TRIBUTACAO(TipoAutorizacaoEnum.TRIBUTACAO, Tributacao.class),
	
	/** O atributo PESSOA. */
	PESSOA(TipoAutorizacaoEnum.PESSOA, PessoaCompartilhamento.class),
	
	/** O atributo PRODUTIVIDADE. */
	PRODUTIVIDADE(TipoAutorizacaoEnum.PRODUTIVIDADE, Produtividade.class),
	
	/** O atributo PRODUTOR. */
	PRODUTOR(TipoAutorizacaoEnum.PRODUTOR, Produtor.class),
	
	/** O atributo RELACIONAMENTO. */
	RELACIONAMENTO(TipoAutorizacaoEnum.RELACIONAMENTO, RelacionamentoPessoa.class),
	
	/** O atributo RESPONSAVEL. */
	RESPONSAVEL(TipoAutorizacaoEnum.RESPONSAVEL, ResponsavelCadastro.class);

	/** O atributo tipoAutorizacao. */
	private TipoAutorizacaoEnum tipoAutorizacao;
	
	/** O atributo tipo. */
	private Class<?> tipo;

	/**
	 * Instancia um novo TipoAutorizacaoEntidadeEnum.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @param tipo o valor de tipo
	 */
	private TipoAutorizacaoEntidadeEnum(TipoAutorizacaoEnum tipoAutorizacao, Class<?> tipo) {
		this.tipoAutorizacao = tipoAutorizacao;
		this.tipo = tipo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return this.tipoAutorizacao.getDescricao();
	}

	/**
	 * Recupera o valor de tipo.
	 *
	 * @return o valor de tipo
	 */
	public Class<?> getTipo() {
		return this.tipo;
	}

	/**
	 * Indica se o tipo de autorização é responsável pelo próprio cadastro
	 *
	 * @return <code>true</code> se for o responsável
	 */
	public boolean isResponsavel() {
		return this.tipoAutorizacao.isResponsavel();
	}

	/**
	 * Obtém o tipo de autorização.
	 * 
	 * @return
	 */
	public TipoAutorizacaoEnum getTipoAutorizacao() {
		return tipoAutorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "=" + name();
	}
	
	/**
	 * Value of.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return TipoAutorizacaoEntidadeEnum
	 */
	public static TipoAutorizacaoEntidadeEnum valueOf(TipoAutorizacaoEnum tipoAutorizacao) {

		TipoAutorizacaoEntidadeEnum value = null;
		try {
			value = TipoAutorizacaoEntidadeEnum.valueOf(tipoAutorizacao.name());
		} catch (IllegalArgumentException e) {
			TipoAutorizacaoEntidadeEnum[] values = TipoAutorizacaoEntidadeEnum.values();
			for (int i = 0; (i < values.length) && (value == null); i++) {
				if (values[i].tipoAutorizacao.equals(tipoAutorizacao)) {
					value = values[i];
				}
			}
			if (value == null) {
				throw new IllegalArgumentException("Nenhum enum encontrado para "
						+ tipoAutorizacao.getClass().getName() + "." + tipoAutorizacao.name(), e);
			}
		}
		return value;
	}
	
	public static TipoAutorizacaoEntidadeEnum obterPorClasse(Class<?> classe) {
		TipoAutorizacaoEntidadeEnum retorno = null;

		for (TipoAutorizacaoEntidadeEnum tipoAutorizacaoEntidade : values()) {
			if (tipoAutorizacaoEntidade.getTipo().isAssignableFrom(classe)) {
				return tipoAutorizacaoEntidade;
			}
		}

		return retorno;
	}
	
}