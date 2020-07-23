package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.List;

/**
 * A Classe FichaCadastralBemVO.
 */
public class FichaCadastralBemNovoVO {

	
	private List<FichaCadastralBemMovelNovoVO> bensMoveis;
	
	private List<FichaCadastralBemImovelNovoVO> bensImoveis;
	
	private List<FichaCadastralBemVO> bensDTO;
	
	
	
	public List<FichaCadastralBemMovelNovoVO> getBensMoveis() {
		return bensMoveis;
	}

	public void setBensMoveis(List<FichaCadastralBemMovelNovoVO> bensMoveis) {
		this.bensMoveis = bensMoveis;
	}

	public List<FichaCadastralBemImovelNovoVO> getBensImoveis() {
		return bensImoveis;
	}

	public void setBensImoveis(List<FichaCadastralBemImovelNovoVO> bensImoveis) {
		this.bensImoveis = bensImoveis;
	}

	public List<FichaCadastralBemVO> getBensDTO() {
		return bensDTO;
	}

	public void setBensDTO(List<FichaCadastralBemVO> bensDTO) {
		this.bensDTO = bensDTO;
	}


	
}