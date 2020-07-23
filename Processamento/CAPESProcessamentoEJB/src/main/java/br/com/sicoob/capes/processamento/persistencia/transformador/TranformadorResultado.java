package br.com.sicoob.capes.processamento.persistencia.transformador;

import java.sql.ResultSet;
import java.util.Collection;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroDadosArquivoBaseVO;

/**
 * 
 * @author bruno.carneiro
 */
public interface TranformadorResultado<C extends RegistroDadosArquivoBaseVO> {

	/**
	 * Transformar.
	 *
	 * @param rs o valor de rs
	 * @return Collection
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Collection<C> transformar(ResultSet rs) throws BancoobException;

}