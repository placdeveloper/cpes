/*
 * SICOOB
 * 
 * TelefonePessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.TelefonePessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITelefonePessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TelefonePessoaServico;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;

/**
 * @author Lucas.Borges
 */
public class TelefonePessoaDelegate extends
		AbstractCAPESApiPessoaDelegate<TelefonePessoaVO,TelefonePessoaServico> implements ITelefonePessoaDelegate {
	
	/**
	 * 
	 */
	protected TelefonePessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TelefonePessoaDelegate getInstance() {
		return valueOf(TelefonePessoaDelegate.class);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TelefonePessoaServico localizarServico() {
		return getLocator()
				.localizarTelefonePessoaServico();
	}
		
	/**
	 * M�todo que inclui um telefone N�O-VIGENTE, para que este passe 
	 * por todo fluxo de autoriza��o do GED/GFT.
	 *  
	 * @param telefone VO com os dados obrigat�rios para inclus�o de um telefone
	 * 	<br>
	 * 	Os dados obrigat�rios s�o:
	 *  <ul>
	 *  	<li>Telefone {@link TelefonePessoaVO#setTelefone(String)}</li>
	 *  	<li>Tipo de telefone {@link TelefonePessoaVO#setCodigoTipoTelefone(Short)}
	 *  		<br> @see {@link TipoTelefoneEnum}
	 *  	</li>
	 *  	<li>ID da intitui��o</li>
	 *  	<li>CPF/CNPJ da pessoa respons�vel</li>
	 *  <ul>
	 * @throws BancoobException
	 */
	public void incluir(TelefonePessoaVO telefone) throws BancoobException{
		getServico().incluirTelefone(telefone);
	}

	/**
	 * M�todo que lista todos os telefones VIGENTES e N�O-VIGENTES por pessoa, institui��o
	 * 
	 * @param idPessoa ID pessoa 
	 * @param idInstituicao ID institui��o
	 * @return Lista de {@link br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO}
	 * @throws BancoobException
	 */
	public List<TelefonePessoaVO> obterNaoVigentePorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException{		
		return getServico().obterNaoVigenteByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	public List<TelefonePessoaVO> listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return getServico().listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}
