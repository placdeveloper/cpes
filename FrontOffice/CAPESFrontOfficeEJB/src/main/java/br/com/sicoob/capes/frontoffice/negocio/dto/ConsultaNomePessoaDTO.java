package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultaNomePessoaDTO extends BancoobDto {
	private static final long serialVersionUID = -7157892499436532748L;

	@AtributoRetorno(posicao = 1)
	private String nomePessoa;

	public ConsultaNomePessoaDTO(String nomePessoa) {
		setNomePessoa(nomePessoa);
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

}