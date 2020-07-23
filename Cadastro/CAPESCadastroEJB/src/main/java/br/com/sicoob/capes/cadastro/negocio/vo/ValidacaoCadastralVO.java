/*
 * SICOOB
 * 
 * ValidacaoCadastralVO.java(br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO)
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum;

/**
 * The Class ValidacaoCadastralVO.
 */
public class ValidacaoCadastralVO extends BancoobVo {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1415430960304912841L;

	/** O atributo id pessoa compartilhamento. */
	private Long idPessoaCompartilhamento;

	/** O atributo codigo. */
	private Short codigo;

	/** O atributo funcionalidade. */
	private String funcionalidade;

	/** O atributo mensagem. */
	private String mensagem;

	/** O atributo tipo. */
	private String tipo;
	
	/** O atributo perfil cdastro. */
	private String perfilCadastro;

	/**
	 * Cria uma nova instância de validacao cadastral vo.
	 */
	public ValidacaoCadastralVO() {

	}

	/**
	 * Cria uma nova instância de validacao cadastral vo.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o id pessoa compartilhamento
	 * @param codigo
	 *            o codigo da regra ({@code ValidacaoCadastralRegra})
	 * @param funcionalidade
	 *            a funcionalidade ({@code FuncionalidadeValidacaoCadastralEnum}
	 * @param mensagem
	 *            a mensagem de erro
	 * @param tipo
	 *            o tipo da regra ({@code TipoRegraValidacaoCadastralEnum})
	 * 
	 * @see br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra
	 * @see br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum#getDescricao()
	 */
	public ValidacaoCadastralVO(Long idPessoaCompartilhamento, Short codigo,
			FuncionalidadeValidacaoCadastralEnum funcionalidade, String mensagem, String tipo) {

		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
		this.codigo = codigo;
		this.funcionalidade = funcionalidade.getDescricao();
		this.mensagem = mensagem;
		this.tipo = tipo;
	}
	
	/**
	 * Cria uma nova instância de validacao cadastral vo.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o id pessoa compartilhamento
	 * @param codigo
	 *            o codigo da regra ({@code ValidacaoCadastralRegra})
	 * @param funcionalidade
	 *            a funcionalidade ({@code FuncionalidadeValidacaoCadastralEnum}
	 * @param mensagem
	 *            a mensagem de erro
	 * @param tipo
	 *            o tipo da regra ({@code TipoRegraValidacaoCadastralEnum})
	 *            
	 * @oaram perfilCadastro
	 * 			  o perfil cadastro que a regra se encontra ({@code PerfilCadastro})
	 * 
	 * @see br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra
	 * @see br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum#getDescricao()
	 * @see br.com.sicoob.capes.negocio.entidades.PerfilCadastro
	 */
	public ValidacaoCadastralVO(Long idPessoaCompartilhamento, Short codigo,
			FuncionalidadeValidacaoCadastralEnum funcionalidade, String mensagem, String tipo, String perfilCadastro) {

		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
		this.codigo = codigo;
		this.funcionalidade = funcionalidade.getDescricao();
		this.mensagem = mensagem;
		this.tipo = tipo;
		this.perfilCadastro = perfilCadastro;
	}

	/**
	 * Recupera id pessoa compartilhamento.
	 * 
	 * @return id pessoa compartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {

		return idPessoaCompartilhamento;
	}

	/**
	 * Preenche id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o novo id pessoa compartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {

		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Short getCodigo() {

		return codigo;
	}

	/**
	 * Preenche codigo.
	 * 
	 * @param codigo
	 *            o novo codigo
	 */
	public void setCodigo(Short codigo) {

		this.codigo = codigo;
	}

	/**
	 * Recupera funcionalidade.
	 * 
	 * @return funcionalidade
	 */
	public String getFuncionalidade() {

		return funcionalidade;
	}

	/**
	 * Preenche funcionalidade.
	 * 
	 * @param funcionalidade
	 *            o novo funcionalidade
	 */
	public void setFuncionalidade(String funcionalidade) {

		this.funcionalidade = funcionalidade;
	}

	/**
	 * Recupera mensagem.
	 * 
	 * @return mensagem
	 */
	public String getMensagem() {

		return mensagem;
	}

	/**
	 * Preenche mensagem.
	 * 
	 * @param mensagem
	 *            o novo mensagem
	 */
	public void setMensagem(String mensagem) {

		this.mensagem = mensagem;
	}

	/**
	 * Recupera tipo.
	 * 
	 * @return tipo
	 */
	public String getTipo() {

		return tipo;
	}

	/**
	 * Preenche tipo.
	 * 
	 * @param tipo
	 *            o novo tipo
	 */
	public void setTipo(String tipo) {

		this.tipo = tipo;
	}

	/**
	 * Recupera perfil cadastro.
	 * 
	 * @return perfil cadastro
	 */
	public String getPerfilCadastro() {

		return perfilCadastro;
	}

	/**
	 * Preenche perfil cadastro.
	 * 
	 * @param perfil cadastro
	 *           
	 */
	public void setPerfilCadastro(String perfilCadastro) {

		this.perfilCadastro = perfilCadastro;
	}

}