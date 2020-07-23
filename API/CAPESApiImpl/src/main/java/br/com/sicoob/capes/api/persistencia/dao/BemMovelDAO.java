package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;

public interface BemMovelDAO extends CAPESApiDao {

	List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean avancado, boolean avaliacao, Short codigoTipoBem) throws BancoobException;
	
	BemMovelVO obterPorIdBem(Long idBem) throws BancoobException;

}