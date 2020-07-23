// 09/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ReplicacaoCadastroServico;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Interface ReplicacaoCadastroServicoLocal.
 */
public interface ReplicacaoCadastroServicoLocal extends ReplicacaoCadastroServico {
	
	PessoaCompartilhamento iniciarRelacionamentoInstituicao(Long idPessoaCompartilhamento, Instituicao destino) throws BancoobException;
	
	PessoaCompartilhamento iniciarRelacionamentoInstituicao(Long idPessoaCompartilhamento) throws BancoobException;

}