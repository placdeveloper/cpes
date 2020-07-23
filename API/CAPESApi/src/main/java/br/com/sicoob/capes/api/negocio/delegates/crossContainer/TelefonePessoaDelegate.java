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
	 * Método que inclui um telefone NÃO-VIGENTE, para que este passe 
	 * por todo fluxo de autorização do GED/GFT.
	 *  
	 * @param telefone VO com os dados obrigatórios para inclusão de um telefone
	 * 	<br>
	 * 	Os dados obrigatórios são:
	 *  <ul>
	 *  	<li>Telefone {@link TelefonePessoaVO#setTelefone(String)}</li>
	 *  	<li>Tipo de telefone {@link TelefonePessoaVO#setCodigoTipoTelefone(Short)}
	 *  		<br> @see {@link TipoTelefoneEnum}
	 *  	</li>
	 *  	<li>ID da intituição</li>
	 *  	<li>CPF/CNPJ da pessoa responsável</li>
	 *  <ul>
	 * @throws BancoobException
	 */
	public void incluir(TelefonePessoaVO telefone) throws BancoobException{
		getServico().incluirTelefone(telefone);
	}

	/**
	 * Método que lista todos os telefones VIGENTES e NÃO-VIGENTES por pessoa, instituição
	 * 
	 * @param idPessoa ID pessoa 
	 * @param idInstituicao ID instituição
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
