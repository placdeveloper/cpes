/* 
 * Sicoob
 * TipoPessoaDelegate.java 
 * Criado em: 07/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.MensagemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * @author juan.damasceno
 */
public class MensagemDelegate extends
		CAPESCadastroCrudDelegate<Mensagem, MensagemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarMensagemServico();
	}
	
	/**
	 * Exclui as mensagens informadas.
	 * @param mensagens as mensagens para serem excluídas.
	 * @throws BancoobException 
	 */
	public void excluir(List<Mensagem> mensagens) throws BancoobException {
		getServico().excluirMensagens(mensagens);
	}

	/**
	 * Lista as mensagens com 30 dias de vencida, ou seja, davaValidadeMsg menor que a data atual - 30.
	 * @param criterios DTO com o número de registros por pagina e o número da página.
	 * @throws BancoobException 
	 */
	public List<Mensagem> listarVencidas(ConsultaDto<Mensagem> criterios) throws BancoobException {
		return getServico().listarVencidas(criterios);
	}		
	
	/**
	 * Inclui a mensagem a partir dos dados informados.
	 * @param idPessoa
	 * @param idInstituicao
	 * @param descMensagem
	 * @param dataValidade
	 * @param destino
	 * @param string 
	 * @return
	 * @throws BancoobException
	 */
	public Mensagem incluir(Integer idPessoa, Integer idInstituicao, String descMensagem, Date dataValidade, Short codTipoMensagem, Short codTipoDestino, String loginUsuarioOperacao) throws BancoobException {
		return getServico().incluir(idPessoa, idInstituicao, descMensagem, dataValidade, codTipoMensagem, codTipoDestino, loginUsuarioOperacao);
	}

}