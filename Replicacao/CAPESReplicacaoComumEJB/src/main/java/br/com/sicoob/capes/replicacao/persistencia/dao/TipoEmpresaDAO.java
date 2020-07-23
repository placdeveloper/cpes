package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.TipoEmpresa;

public interface TipoEmpresaDAO extends CAPESReplicacaoComumCrudDaoIF<TipoEmpresa> {

	TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda renda) throws BancoobException;

}
