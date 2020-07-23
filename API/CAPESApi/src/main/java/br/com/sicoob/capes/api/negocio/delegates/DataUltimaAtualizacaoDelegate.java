/*
 * SICOOB
 * 
 * DataUltimaAtualizacaoDelegate.java(br.com.sicoob.capes.api.negocio.delegates.DataUltimaAtualizacaoDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IDataUltimaAtualizacaoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.DataUltimaAtualizacaoServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * Delegate responsavel por retornar a data da utlima atualizacao
 * 
 * @author Marcelo.Onofre
 * 
 */
public class DataUltimaAtualizacaoDelegate extends CAPESApiDelegate<DataUltimaAtualizacaoServico> implements IDataUltimaAtualizacaoDelegate {

	/**
	 * Localiza o servico <code>locator.capesintegracao.DataUltimaAtualizacaoServico</code>
	 */
	@Override
	protected DataUltimaAtualizacaoServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarDataUltimaAtualizacaoServico();
	}

	/**
	 * Metodo que consulta a ultima data de alteracao pelo ID pessoa e ID instituicao.
	 * 
	 * @param idPessoa
	 *            ID da pessoa
	 * @param idInstituicao
	 *            ID da instituicao
	 * @return Retorna a data da ultima alteracao
	 * @throws BancoobException
	 */
	public Date obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Método que consulta a última data de alteração de pessoas que tenham sido atualizadas no mesmo dia ou depois da
	 * data de referencia.
	 * 
	 * @param idInstituicao
	 *            ID da instituicao
	 * @param dataReferencia
	 *            a data de referencia para filtro
	 * @param idsPessoas
	 *            IDs das pessoas
	 * @return lista com a data da ultima alteracao de cada pessoa alterada no mesmo dia ou após a data de referencia
	 * @throws BancoobException
	 */
	public List<DataUltimaAtualizacaoVO> consultarPorDataReferenciaPessoas(Integer idInstituicao, Date dataReferencia,
			Integer... idsPessoas) throws BancoobException {
		return getServico().consultarPorDataReferenciaPessoas(idInstituicao, dataReferencia, idsPessoas);
	}
}