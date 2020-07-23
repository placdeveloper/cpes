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
	 * M�todo que inclui um telefone N�O-VIGENTE, para que este passe por todo fluxo de autoriza��o do GED/GFT.
	 * 
	 * @param telefone
	 *            VO com os dados obrigat�rios para inclus�o de um telefone <br>
	 *            Os dados obrigat�rios s�o:
	 *            <ul>
	 *            <li>Telefone {@link TelefonePessoaVO#setTelefone(String)}</li>
	 *            <li>Tipo de telefone {@link TelefonePessoaVO#setCodigoTipoTelefone(Short)} <br>
	 * @see {@link TipoTelefoneEnum}</li>
	 *      <li>ID da intitui��o</li>
	 *      <li>CPF/CNPJ da pessoa respons�vel</li>
	 *      <ul>
	 * @throws BancoobException
	 */
	void incluir(TelefonePessoaVO telefone) throws BancoobException;

	/**
	 * M�todo que lista todos os telefones VIGENTES e N�O-VIGENTES por pessoa, institui��o
	 * 
	 * @param idPessoa
	 *            ID pessoa
	 * @param idInstituicao
	 *            ID institui��o
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
