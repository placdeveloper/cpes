package br.com.sicoob.capes.processamento.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

public interface BaixarAnotacoesVencidasServicoLocal {

	void baixarAnotacao(Anotacao anotacao, String usuario) throws BancoobException;

}