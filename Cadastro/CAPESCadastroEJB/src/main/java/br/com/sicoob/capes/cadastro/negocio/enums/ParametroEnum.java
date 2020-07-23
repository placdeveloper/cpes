/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum dos par�metros
 * 
 * @author Paulo.Stoppa
 *
 */
public enum ParametroEnum {

	/** Par�metro de servi�os do Grupo Econ�mico. */
	SERVICOS_GRUPO_ECONOMICO(1),
	/** Par�metro PARA HABILITAR a cria��o de Grupo Econ�mico autom�tico. */
	CRIACAO_GRUPO_ECONOMICO_AUTOMATICO(2),
	/** Par�metro PARA HABILITAR a altera��o de nome de Grupo Econ�mico autom�tico. */
	ALTERAR_NOME_GRUPO_ECONOMICO_AUTOMATICO(3),
	/** Par�metro PARA HABILITAR Obrigatoriedade de campos. */
	OBRIGATORIEDADE_DE_CAMPOS(4),
	/** Par�metro PARA HABILITAR Obrigatoriedade de campos. */
	LIMITE_DIAS_HISTORICO_GRUPO_ECONOMICO(5),
	/** Par�metro PARA VALIDAR EXCLUSAO DE BEM ANTIGO. */
	VALIDAR_EXCLUSAO_BEM_ANTIGO(6),	
	/** Par�metro PARA HABILITAR Gestor da Cooperativa. */
	GESTOR(7),
	/** Par�metro PARA HABILITAR Gestor Cadastro Instituicao. */
	GESTOR_CADASTRO_INSTITUICAO(8),
	/** Par�metro PARA EXIBIR AbaAutoAtendimento na Autorizacao. */
	EXIBIR_ABA_AUTOATENDIMENTO_NA_AUTORIZACAO(9),
	/** Par�metro PARA EXIBIR BOTAO BAIXAR ANOTACAO. */
	EXIBIR_BOTAO_BAIXAR_ANOTACAO(10),
	/** Par�metro cancelar a Autorizacao quando existir uma instanciada no ged. */
	CANCELAR_FLUXO_AUTORIZACAO(11);

	private final Integer codigo;

	private ParametroEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

}
