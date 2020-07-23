/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * Define as operações do serviço de Mensagem.
 * 
 * @author Juan.Damasceno
 */
public interface MensagemServico extends
		CAPESCadastroCrudServico<Mensagem> {
	
	/**
	 * Exclui as mensagens informadas.
	 * @param mensagens as mensagens para serem excluídas.
	 * @throws BancoobException 
	 */
	void excluirMensagens(List<Mensagem> mensagens) throws BancoobException;

	/**
	 * Lista as mensagens com 30 dias de vencida, ou seja, davaValidadeMsg menor que a data atual - 30.
	 * @param criterios DTO com o número de registros por pagina e o número da página.
	 * @throws BancoobException 
	 */
	List<Mensagem> listarVencidas(ConsultaDto<Mensagem> criterios) throws BancoobException;
	
	/**
	 * Inclui a mensagem a partir dos dados informados.
	 * @param idPessoa
	 * @param idInstituicao
	 * @param descMensagem
	 * @param dataValidade
	 * @param destino
	 * @return
	 * @throws BancoobException
	 */
	Mensagem incluir(Integer idPessoa, Integer idInstituicao, String descMensagem, Date dataValidade, Short codTipoMensagem, Short codTipoDestino, String loginUsuarioOperacao) throws BancoobException;

}