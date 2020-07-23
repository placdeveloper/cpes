/*
 * SICOOB
 * 
 * ReferenciaBancaria.java(br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;

/**
 * The Class ReferenciaBancaria.
 */
@Entity
@Table(name = "REFERENCIABANCARIAPESSOA", schema = "CLI")
@CamposAutorizacao(id = "idReferenciaPessoa", camposExibicao = {
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "tipoReferencia.descricao", label = "TIPO DE REFERÊNCIA"),
		@CampoAutorizacao(propriedade = "pessoaReferencia.pessoa.cpfCnpj", label = "PESSOA RELACIONADA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorCpfCnpj"),
		@CampoAutorizacao(propriedade = "observacao", label = "DESCRIÇÃO"),
		@CampoAutorizacao(propriedade = "ddd", label = "DDD"),
		@CampoAutorizacao(propriedade = "telefone", label = "TELEFONE", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorTelefone") })
public class ReferenciaBancaria extends ReferenciaBancariaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7502356956362586254L;

}