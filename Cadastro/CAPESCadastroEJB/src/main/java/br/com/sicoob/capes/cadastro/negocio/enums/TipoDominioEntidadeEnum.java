package br.com.sicoob.capes.cadastro.negocio.enums;

import java.io.Serializable;

import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento;
import br.com.sicoob.capes.negocio.entidades.Funcao;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.negocio.entidades.SubTipoCertidao;
import br.com.sicoob.capes.negocio.entidades.TipoAbrangenciaCertidao;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;
import br.com.sicoob.capes.negocio.entidades.TipoBaixa;
import br.com.sicoob.capes.negocio.entidades.TipoCaptura;
import br.com.sicoob.capes.negocio.entidades.TipoCertidao;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.negocio.entidades.TipoObjetoCertidao;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;
import br.com.sicoob.capes.negocio.entidades.TipoPrazoCertidao;
import br.com.sicoob.capes.negocio.entidades.TipoReferencia;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;

/**
 * @author Marcelo.Onofre
 * 
 */
public enum TipoDominioEntidadeEnum implements Serializable {

	/** O atributo ATIVIDADE_ECONOMICA. */
	ATIVIDADE_ECONOMICA(AtividadeEconomica.class, TipoDominioEnum.ATIVIDADE_ECONOMICA),
	
	/** O atributo CATEGORIA_PRODUTOR. */
	CATEGORIA_PRODUTOR(CategoriaProdutor.class, TipoDominioEnum.CATEGORIA_PRODUTOR),
	
	/** O atributo CONSELHO_REGIONAL. */
	CONSELHO_REGIONAL(ConselhoRegional.class, TipoDominioEnum.CONSELHO_REGIONAL),
	
	/** O atributo ESTADO_CIVIL. */
	ESTADO_CIVIL(EstadoCivil.class, TipoDominioEnum.ESTADO_CIVIL),
	
	/** O atributo FUNCAO. */
	FUNCAO(Funcao.class, "idFuncao", "descricao", TipoDominioEnum.FUNCAO),
	
	/** O atributo FINALIDADE_EMPREENDIMENTO. */
	FINALIDADE_EMPREENDIMENTO(FinalidadeEmpreendimento.class, TipoDominioEnum.FINALIDADE_EMPREENDIMENTO),
	
	/** O atributo GRAU_INSTRUCAO. */
	GRAU_INSTRUCAO(GrauInstrucao.class, TipoDominioEnum.GRAU_INSTRUCAO),
	
	/** O atributo NACIONALIDADE. */
	NACIONALIDADE(Nacionalidade.class, TipoDominioEnum.NACIONALIDADE),
	
	/** O atributo ORGAO_EMISSAO_CERTIDAO. */
	ORGAO_EMISSAO_CERTIDAO(OrgaoEmissorCertidao.class, TipoDominioEnum.ORGAO_EMISSAO_CERTIDAO),
	
	/** O atributo REGIME_CASAMENTO. */
	REGIME_CASAMENTO(RegimeCasamento.class, TipoDominioEnum.REGIME_CASAMENTO),
	
	/** O atributo SUB_TIPO_CERTIDAO. */
	SUB_TIPO_CERTIDAO(SubTipoCertidao.class, TipoDominioEnum.SUB_TIPO_CERTIDAO),
	
	/** O atributo TIPO_ABRANGENCIA_CERTIDAO. */
	TIPO_ABRANGENCIA_CERTIDAO(TipoAbrangenciaCertidao.class, TipoDominioEnum.TIPO_ABRANGENCIA_CERTIDAO),
	
	/** O atributo TIPO_BEM. */
	TIPO_BEM(TipoBem.class, TipoDominioEnum.TIPO_BEM),
	
	/** O atributo TIPO_CAPTURA. */
	TIPO_CAPTURA(TipoCaptura.class, "idTipoCaptura", "descTipoCaptura", TipoDominioEnum.TIPO_CAPTURA),
	
	/** O atributo TIPO_PODER_RELACIONAMENTO. */
	TIPO_PODER_RELACIONAMENTO(TipoPoderRelacionamento.class, TipoDominioEnum.TIPO_PODER_RELACIONAMENTO),
	
	/** O atributo TIPO_PESSOA. */
	TIPO_PESSOA(TipoPessoa.class, "codTipoPessoa", "descTipoPessoa", TipoDominioEnum.TIPO_PESSOA),
	
	/** O atributo TIPO_OBJETO_CERTIDAO. */
	TIPO_OBJETO_CERTIDAO(TipoObjetoCertidao.class, TipoDominioEnum.TIPO_OBJETO_CERTIDAO),
	
	/** O atributo TIPO_FUNCIONARIO. */
	TIPO_FUNCIONARIO(TipoFuncionario.class, TipoDominioEnum.TIPO_FUNCIONARIO),
	
	/** O atributo TIPO_FORMA_CONSTITUICAO. */
	TIPO_FORMA_CONSTITUICAO(TipoFormaConstituicao.class, TipoDominioEnum.TIPO_FORMA_CONSTITUICAO),
	
	/** O atributo TIPO_FONTE_RENDA. */
	TIPO_FONTE_RENDA(TipoFonteRenda.class, TipoDominioEnum.TIPO_FONTE_RENDA),
	
	/** O atributo TIPO_ENDERECO. */
	TIPO_ENDERECO(TipoEndereco.class, TipoDominioEnum.TIPO_ENDERECO),
	
	/** O atributo TIPO_EMPRESA. */
	TIPO_EMPRESA(TipoEmpresa.class, TipoDominioEnum.TIPO_EMPRESA),
	
	/** O atributo TIPO_EMAIL. */
	TIPO_EMAIL(TipoEmail.class, TipoDominioEnum.TIPO_EMAIL),
	
	/** O atributo TIPO_DOCUMENTO_IDENTIFICACAO. */
	TIPO_DOCUMENTO_IDENTIFICACAO(TipoDocumentoIdentificacao.class, TipoDominioEnum.TIPO_DOCUMENTO_IDENTIFICACAO),
	
	/** O atributo TIPO_CERTIDAO. */
	TIPO_CERTIDAO(TipoCertidao.class, "codigo", "nome", TipoDominioEnum.TIPO_CERTIDAO),
	
	/** O atributo TIPO_BAIXA. */
	TIPO_BAIXA(TipoBaixa.class, "idTipoBaixa", "descTipoBaixa", TipoDominioEnum.TIPO_BAIXA),
	
	/** O atributo TIPO_AFASTAMENTO. */
	TIPO_AFASTAMENTO(TipoAfastamento.class, TipoDominioEnum.TIPO_AFASTAMENTO),
	
	/** O atributo TIPO_POSSE_BEM. */
	TIPO_POSSE_BEM(TipoPosseBem.class, TipoDominioEnum.TIPO_POSSE_BEM),
	
	/** O atributo TIPO_PRAZO_CERTIDAO. */
	TIPO_PRAZO_CERTIDAO(TipoPrazoCertidao.class, TipoDominioEnum.TIPO_PRAZO_CERTIDAO),
	
	/** O atributo TIPO_REFERENCIA. */
	TIPO_REFERENCIA(TipoReferencia.class, TipoDominioEnum.TIPO_REFERENCIA),
	
	/** O atributo TIPO_RELACIONAMENTO. */
	TIPO_RELACIONAMENTO(TipoRelacionamentoPessoa.class, TipoDominioEnum.TIPO_RELACIONAMENTO),
	
	/** O atributo TIPO_TELEFONE. */
	TIPO_TELEFONE(TipoTelefone.class, TipoDominioEnum.TIPO_TELEFONE),
	
	/** O atributo UNIDADE_MEDIDA. */
	UNIDADE_MEDIDA(UnidadeMedida.class, TipoDominioEnum.UNIDADE_MEDIDA),
	
	/** O atributo VINCULO_EMPREGATICIO. */
	VINCULO_EMPREGATICIO(VinculoEmpregaticio.class, TipoDominioEnum.VINCULO_EMPREGATICIO);

	/** O atributo tipoDominio. */
	private TipoDominioEnum tipoDominio;
	
	/** O atributo tipo. */
	private Class<? extends CAPESEntidade<Short>> tipo;
	
	/** O atributo propriedadeCodigo. */
	private String propriedadeCodigo;
	
	/** O atributo propriedadeDescricao. */
	private String propriedadeDescricao;

	/**
	 * Instancia um novo TipoDominioEntidadeEnum.
	 *
	 * @param tipo o valor de tipo
	 * @param tipoDominio o valor de tipo dominio
	 */
	private TipoDominioEntidadeEnum(Class<? extends CAPESEntidade<Short>> tipo, TipoDominioEnum tipoDominio) {
		this.tipo = tipo;
		this.tipoDominio = tipoDominio;
		this.propriedadeCodigo = "id";
		this.propriedadeDescricao = "descricao";
	}

	/**
	 * Instancia um novo TipoDominioEntidadeEnum.
	 *
	 * @param tipo o valor de tipo
	 * @param propriedadeCodigo o valor de propriedade codigo
	 * @param propriedadeDescricao o valor de propriedade descricao
	 * @param tipoDominio o valor de tipo dominio
	 */
	private TipoDominioEntidadeEnum(Class<? extends CAPESEntidade<Short>> tipo, String propriedadeCodigo, String propriedadeDescricao,
			TipoDominioEnum tipoDominio) {
		this.tipo = tipo;
		this.tipoDominio = tipoDominio;
		this.propriedadeCodigo = propriedadeCodigo;
		this.propriedadeDescricao = propriedadeDescricao;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public Class<? extends CAPESEntidade<Short>> getTipo() {
		return this.tipo;
	}

	/**
	 * Recupera o valor de propriedadeCodigo.
	 *
	 * @return o valor de propriedadeCodigo
	 */
	public String getPropriedadeCodigo() {
		return this.propriedadeCodigo;
	}

	/**
	 * Recupera o valor de propriedadeDescricao.
	 *
	 * @return o valor de propriedadeDescricao
	 */
	public String getPropriedadeDescricao() {
		return this.propriedadeDescricao;
	}

	/**
	 * Value of.
	 *
	 * @param tipoDominio o valor de tipo dominio
	 * @return TipoDominioEntidadeEnum
	 */
	public static TipoDominioEntidadeEnum valueOf(TipoDominioEnum tipoDominio) {
		
		TipoDominioEntidadeEnum value = null;
		try {
			value = TipoDominioEntidadeEnum.valueOf(tipoDominio.name());
		} catch (IllegalArgumentException e) {
			TipoDominioEntidadeEnum[] values = TipoDominioEntidadeEnum.values();
			for (int i = 0; (i < values.length) && (value == null); i++) {
				if (values[i].tipoDominio.equals(tipoDominio)) {
					value = values[i];
				}
			}
			if (value == null) {
				throw new IllegalArgumentException("Nenhum enum encontrado para "
						+ tipoDominio.getClass().getName() + "." + tipoDominio.name(), e);
			}
		}
		return value;
	}
	
}
