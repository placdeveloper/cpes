/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.TransicaoPessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Esta fachada visa enviar para as bases das cooperativas a atualização de cpf/cnpj de uma pessoa.
 * Como a alteração deve ser feita independentemente do grupo de compartilhamento, esta atualização é específica para
 * esse caso.
 * @author erico.junior
 */
public class AtualizacaoCpfCnpjFachada extends AtualizacaoCadastralFachada<HistoricoAlteracaoCpfCnpj> {

	/**
	 * Envia a atualização no sistema legado.
	 * 
	 * @param entidade
	 *            A entidade que sofreu atualização cadastral.
	 * @param tipoAtualizacao
	 *            O tipo de atualização (Inclusão, Alteração ou Exclusão).
	 * @throws BancoobException
	 */
	@Override
	public void executarAtualizacao(HistoricoAlteracaoCpfCnpj entidade, TipoAtualizacaoCadastralEnum tipoAtualizacao)
			throws BancoobException {

		TransicaoPessoaDelegate delegate = 
				CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
		
		List<TransicaoPessoaVO> lista = delegate.listarTodasTransicoes(entidade.getPessoa());
		
		if(lista != null && !lista.isEmpty()) {
			Instituicao instituicao = null;
			
			for (TransicaoPessoaVO vo : lista) {
				instituicao = new Instituicao();
				instituicao.setIdInstituicao(vo.getIdInstituicao());
				instituicao.setIdUnidadeInst(vo.getIdUnidadeInst());
				salvarAtualizacaoCadastral(entidade, instituicao, vo.getIdPessoaLegado(), tipoAtualizacao);
			}
		}
	}	
}
