package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;

/**
 * A Interface InstituicaoResponsavelDAO.
 */
public interface InstituicaoResponsavelDAO extends CAPESApiDaoIF<InstituicaoResponsavelVO> {
	
	/**
	 * Obter instituicao responsavel por cpf cnpj.
	 *
	 * @param filtroConsulta o valor de filtro consulta
	 * @return InstituicaoResponsavelVO
	 */
	InstituicaoResponsavelVO obterInstituicaoResponsavelPorCpfCnpj(ConsultaDto<InstituicaoResponsavelVO> filtroConsulta);
	
	/**
	 * Obter instituicao responsavel por cpf cnpj.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return InstituicaoResponsavelVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	InstituicaoResponsavelVO obterInstituicaoResponsavelPorCpfCnpj(String cpfCnpj) throws BancoobException;

}