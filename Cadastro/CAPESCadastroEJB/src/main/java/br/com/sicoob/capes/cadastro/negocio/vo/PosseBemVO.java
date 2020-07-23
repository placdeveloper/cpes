package br.com.sicoob.capes.cadastro.negocio.vo;

import java.util.List;

import br.com.bancoob.negocio.vo.BancoobVo;

public class PosseBemVO extends BancoobVo {
	private static final long serialVersionUID = -4755584534563553419L;

	private List<ProprietarioBemVO> proprietarios;
	private List<ProprietarioBemVO> participantes;

	public List<ProprietarioBemVO> getProprietarios() {
		return proprietarios;
	}

	public void setProprietarios(List<ProprietarioBemVO> proprietarios) {
		this.proprietarios = proprietarios;
	}

	public List<ProprietarioBemVO> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<ProprietarioBemVO> participantes) {
		this.participantes = participantes;
	}

}