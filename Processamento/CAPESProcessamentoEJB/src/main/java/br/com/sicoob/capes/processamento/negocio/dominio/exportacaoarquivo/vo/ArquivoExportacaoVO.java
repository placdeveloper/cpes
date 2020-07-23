package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.IdentificadorLinha;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.MapeamentoLinhas;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * A Classe ArquivoExportacaoVO.
 */
@Arquivo(caracteresPorLinha = 1000)
@MapeamentoLinhas(inicio = 0, tamanho = 2, identificadores = {
        @IdentificadorLinha(value = RegistroHeaderVO.CODIGO_REGISTRO, 			tipoRegistro = RegistroHeaderVO.class),
        @IdentificadorLinha(value = RegistroPessoaFisicaVO.CODIGO_REGISTRO, 	tipoRegistro = RegistroPessoaFisicaVO.class),
        @IdentificadorLinha(value = RegistroPessoaJuridicaVO.CODIGO_REGISTRO,	tipoRegistro = RegistroPessoaJuridicaVO.class),
        @IdentificadorLinha(value = RegistroEnderecoVO.CODIGO_REGISTRO, 		tipoRegistro = RegistroEnderecoVO.class),
        @IdentificadorLinha(value = RegistroTelefoneVO.CODIGO_REGISTRO, 		tipoRegistro = RegistroTelefoneVO.class),
        @IdentificadorLinha(value = RegistroEmailVO.CODIGO_REGISTRO, 			tipoRegistro = RegistroEmailVO.class),
        @IdentificadorLinha(value = RegistroRendaVO.CODIGO_REGISTRO, 			tipoRegistro = RegistroRendaVO.class),
        @IdentificadorLinha(value = RegistroBemVO.CODIGO_REGISTRO, 				tipoRegistro = RegistroBemVO.class),
        @IdentificadorLinha(value = RegistroCooperativaVO.CODIGO_REGISTRO, 		tipoRegistro = RegistroCooperativaVO.class),
        @IdentificadorLinha(value = RegistroTrailerVO.CODIGO_REGISTRO, 			tipoRegistro = RegistroTrailerVO.class) })
public class ArquivoExportacaoVO extends DadosArquivoVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6861136771224605914L;

}