package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.TipoEmpresa;

/**
 * Serviço para tipo empresa.
 * @author kelisson.leite
 */
public interface TipoEmpresaServico extends EntidadeDominioReplicavelServico<TipoEmpresa> {

	TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda renda) throws BancoobException;

}
