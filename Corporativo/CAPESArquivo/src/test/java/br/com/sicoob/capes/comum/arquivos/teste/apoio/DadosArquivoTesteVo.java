package br.com.sicoob.capes.comum.arquivos.teste.apoio;

import java.util.HashMap;
import java.util.Map;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.IdentificadorLinha;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.MapeamentoLinhas;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * Classe VO que representa um arquivo fictício usado nos testes do componente.
 * 
 * Criado em 22/7/2014
 * 
 * @author rodrigo.chaves
 */
@Arquivo(caracteresPorLinha = 200, ignoraRegistroInvalido = false)
@MapeamentoLinhas(inicio = 0, tamanho = 1, tipoPadrao = EntidadeTeste.class, identificadores = {
		@IdentificadorLinha(value = "C", tipoRegistro = CabecalhoEntidadeTeste.class),
		@IdentificadorLinha(value = "R", tipoRegistro = RodapeEntidadeTeste.class) })
public class DadosArquivoTesteVo extends DadosArquivoVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6315933557153078350L;
	
	/** A constante TIPO_CABECALHO. */
	private static final Character TIPO_CABECALHO = 'C';
	
	/** A constante TIPO_RODAPE. */
	private static final Character TIPO_RODAPE = 'R';

	/** O atributo tiposRegistro. */
	private static Map<Character, Class<? extends RegistroArquivo>> tiposRegistro;

	static {
		tiposRegistro = new HashMap<Character, Class<? extends RegistroArquivo>>();
		tiposRegistro.put(TIPO_CABECALHO, CabecalhoEntidadeTeste.class);
		tiposRegistro.put(TIPO_RODAPE, RodapeEntidadeTeste.class);
	}

	// @Override
	// public Class<? extends RegistroArquivo> getTipoRegistro(String linha, int i, boolean temMaisRegistros) {
	//
	// Character tipo = linha.charAt(0);
	// Class<? extends RegistroArquivo> tipoRegistro = tiposRegistro.get(tipo);
	// if (tipoRegistro == null) {
	// tipoRegistro = EntidadeTeste.class;
	// }
	//
	// return tipoRegistro;
	// }

}
