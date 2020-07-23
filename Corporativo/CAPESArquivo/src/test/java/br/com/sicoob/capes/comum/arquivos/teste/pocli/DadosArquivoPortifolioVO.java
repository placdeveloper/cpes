package br.com.sicoob.capes.comum.arquivos.teste.pocli;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.IdentificadorLinha;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.MapeamentoLinhas;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * A Classe DadosArquivoPortifolioVO.
 */
@Arquivo(caracteresPorLinha = 115, ignoraRegistroInvalido = false)
@MapeamentoLinhas(inicio = 0, tamanho = 1, identificadores = {
		@IdentificadorLinha(value = "0", tipoRegistro = CabecalhoPortifolio.class),
		@IdentificadorLinha(value = "1", tipoRegistro = DetalhePortifolio.class),
		@IdentificadorLinha(value = "9", tipoRegistro = RodapePortifolio.class) })
public class DadosArquivoPortifolioVO extends DadosArquivoVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5912226486722888859L;

}