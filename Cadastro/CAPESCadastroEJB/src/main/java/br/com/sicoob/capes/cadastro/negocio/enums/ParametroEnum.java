/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum dos parâmetros
 * 
 * @author Paulo.Stoppa
 *
 */
public enum ParametroEnum {

	/** Parâmetro de serviços do Grupo Econômico. */
	SERVICOS_GRUPO_ECONOMICO(1),
	/** Parâmetro PARA HABILITAR a criação de Grupo Econômico automático. */
	CRIACAO_GRUPO_ECONOMICO_AUTOMATICO(2),
	/** Parâmetro PARA HABILITAR a alteração de nome de Grupo Econômico automático. */
	ALTERAR_NOME_GRUPO_ECONOMICO_AUTOMATICO(3),
	/** Parâmetro PARA HABILITAR Obrigatoriedade de campos. */
	OBRIGATORIEDADE_DE_CAMPOS(4),
	/** Parâmetro PARA HABILITAR Obrigatoriedade de campos. */
	LIMITE_DIAS_HISTORICO_GRUPO_ECONOMICO(5),
	/** Parâmetro PARA VALIDAR EXCLUSAO DE BEM ANTIGO. */
	VALIDAR_EXCLUSAO_BEM_ANTIGO(6),	
	/** Parâmetro PARA HABILITAR Gestor da Cooperativa. */
	GESTOR(7),
	/** Parâmetro PARA HABILITAR Gestor Cadastro Instituicao. */
	GESTOR_CADASTRO_INSTITUICAO(8),
	/** Parâmetro PARA EXIBIR AbaAutoAtendimento na Autorizacao. */
	EXIBIR_ABA_AUTOATENDIMENTO_NA_AUTORIZACAO(9),
	/** Parâmetro PARA EXIBIR BOTAO BAIXAR ANOTACAO. */
	EXIBIR_BOTAO_BAIXAR_ANOTACAO(10),
	/** Parâmetro cancelar a Autorizacao quando existir uma instanciada no ged. */
	CANCELAR_FLUXO_AUTORIZACAO(11);

	private final Integer codigo;

	private ParametroEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

}
