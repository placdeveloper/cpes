package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoProxy;

public interface RelClienteProdutoDao {

	List<RelClienteProdutoProxy> obterDadosRelatorio(Map<String, Object> map) throws BancoobException;
}
