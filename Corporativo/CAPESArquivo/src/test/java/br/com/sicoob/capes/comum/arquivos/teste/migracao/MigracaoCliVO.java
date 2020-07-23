package br.com.sicoob.capes.comum.arquivos.teste.migracao;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.MapeamentoLinhas;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * A Classe MigracaoCliVO.
 */
@Arquivo(caracteresPorLinha = 816, ignoraRegistroInvalido = false, caracteresMinimosPorLinha = true)
@MapeamentoLinhas(tipoPadrao = CliVO.class)
public class MigracaoCliVO extends DadosArquivoVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2886895984429998685L;

}
