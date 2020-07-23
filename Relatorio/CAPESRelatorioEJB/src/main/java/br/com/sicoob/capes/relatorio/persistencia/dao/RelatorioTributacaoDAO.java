package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioTributacaoVO;

/**
 * @author Rodrigo.Chaves
 */
public interface RelatorioTributacaoDAO {

	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<RelatorioTributacaoVO> pesquisar(ConsultaDto<RelatorioTributacaoVO> dto)
	        throws BancoobException;

}
