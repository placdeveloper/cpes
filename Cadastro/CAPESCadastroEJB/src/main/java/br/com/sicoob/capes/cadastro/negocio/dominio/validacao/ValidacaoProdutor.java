/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * Validação para Produtor.
 * @author Erico.Junior
 */
public class ValidacaoProdutor extends ValidacaoEntidadeCadastroAbstract<Produtor> {
	
	/** A constante TAMANHO_MES_ANO. */
	private static final int TAMANHO_MES_ANO = 6;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(Produtor produtor)
			throws CampoNaoInformadoException {
				
		if(StringUtils.isEmpty(produtor.getCodigoInscricao())) {
			throw new CampoNaoInformadoException("Código de Inscrição");
		}
		
		CategoriaProdutor categoria = produtor.getCategoria();
		if(categoria == null || categoria.getCodigo() == null) {
			throw new CampoNaoInformadoException("Categoria");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(Produtor produtor) throws BancoobException {
		validarMesAno(produtor.getMesAnoPermanente());
		validarMesAno(produtor.getMesAnoTemporario());
	}

	/**
	 * O método Validar mes ano.
	 *
	 * @param valor o valor de valor
	 * @throws FormatoInvalidoException lança a exceção FormatoInvalidoException
	 */
	private void validarMesAno(String valor) throws FormatoInvalidoException {
		
		if(valor != null && StringUtils.isNotEmpty(valor.trim())) {
			
			if(valor.trim().length() < TAMANHO_MES_ANO){
				throw new FormatoInvalidoException("Mês/Ano");
			}
			
			try {
				DateFormat formatador = new SimpleDateFormat("MMyyyy");
				formatador.setLenient(false);
				formatador.parse(valor);
			}catch (ParseException ex) {
				throw new FormatoInvalidoException("Mês/Ano", ex);
			}
		}
	}
	

}
