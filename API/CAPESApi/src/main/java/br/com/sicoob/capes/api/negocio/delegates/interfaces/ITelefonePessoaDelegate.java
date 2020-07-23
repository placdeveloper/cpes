package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ITelefonePessoaDelegate extends IAbstractCAPESApiPessoaDelegate<TelefonePessoaVO> {

	/**
	 * Método que inclui um telefone NÃO-VIGENTE, para que este passe por todo fluxo de autorização do GED/GFT.
	 * 
	 * @param telefone
	 *            VO com os dados obrigatórios para inclusão de um telefone <br>
	 *            Os dados obrigatórios são:
	 *            <ul>
	 *            <li>Telefone {@link TelefonePessoaVO#setTelefone(String)}</li>
	 *            <li>Tipo de telefone {@link TelefonePessoaVO#setCodigoTipoTelefone(Short)} <br>
	 * @see {@link TipoTelefoneEnum}</li>
	 *      <li>ID da intituição</li>
	 *      <li>CPF/CNPJ da pessoa responsável</li>
	 *      <ul>
	 * @throws BancoobException
	 */
	void incluir(TelefonePessoaVO telefone) throws BancoobException;

	/**
	 * Método que lista todos os telefones VIGENTES e NÃO-VIGENTES por pessoa, instituição
	 * 
	 * @param idPessoa
	 *            ID pessoa
	 * @param idInstituicao
	 *            ID instituição
	 * @return Lista de {@link br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO}
	 * @throws BancoobException
	 */
	List<TelefonePessoaVO> obterNaoVigentePorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * 
	 * @param idPessoaLegado
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<TelefonePessoaVO> listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

}
