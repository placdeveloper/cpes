package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;

/**
 * A Interface ProdutorDAO.
 */
public interface ProdutorDAO extends CAPESApiDaoIF<ProdutorVO> {

	/**
	 * Obter produtor.
	 *
	 * @param criterios o valor de criterios
	 * @return ProdutorVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ProdutorVO obterProdutor(ConsultaDto<ProdutorVO> criterios) throws BancoobException;
	
	/**
	 * Obtém a lista dos Tipos de beneficiários do SICOR.
	 * @return
	 * @throws BancoobException
	 */
	List<TipoBeneficiarioSicorVO> obterListaTipoBeneficiariosSicor() throws BancoobException;
	
}