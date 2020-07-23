/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmpresaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFonteRendaDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataAtualException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FaixaRendaAnualException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TipoFonteRendaAplicacaoPessoaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ValorRendaBrutaMensalException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Validações para a fonte de renda.
 * @author Erico.Junior
 */
public class ValidacaoFonteRenda extends ValidacaoEntidadeCadastroAbstract<FonteRenda> {


	/** O atributo tipoDelegate. */
	private transient TipoFonteRendaDelegate tipoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarTipoFonteRendaDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(FonteRenda fonte) throws CampoNaoInformadoException {

		TipoFonteRenda tipo = fonte.getTipoFonteRenda();
		if(tipo == null || tipo.getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo de Renda");
		}
		
		if(fonte.getBolRendaFixa() == null) {
			throw new CampoNaoInformadoException("Forma de Renda");
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(FonteRenda fonte) throws BancoobException {
		validarEmpregador(fonte);
		validarValorReceitaBrutaMensal(fonte);
		validarDataValidadeRenda(fonte.getDataValidadeRenda());
		validarAplicacaoTipoPessoa(fonte);
		validarTipoPorteEmpresa(fonte);
	}
	
	private void validarTipoPorteEmpresa(FonteRenda fonte) throws BancoobException {
		if (isPessoaJuridica(fonte)) {
			TipoEmpresaDelegate empresaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoEmpresaDelegate();
			TipoEmpresa tipoEmpresaRetorno = empresaDelegate.obterTipoEmpresaPorFaixaRendaAnual(fonte);
			if (tipoEmpresaRetorno == null) {
				throw new FaixaRendaAnualException();
			}
		}
	}

	private boolean isPessoaJuridica(FonteRenda renda) throws BancoobException {
		TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(renda.getPessoaCompartilhamento().getPessoa().getTipoPessoa().getCodTipoPessoa());
		return TipoPessoaEnum.PESSOA_JURIDICA == tipoPessoa;
	}

	/**
	 * O método Validar empregador.
	 *
	 * @param fonte o valor de fonte
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarEmpregador(FonteRenda fonte) throws BancoobException {
		
		PessoaCompartilhamento empregador = fonte.getPessoaEmpregador();
		PessoaCompartilhamento pessoa = fonte.getPessoaCompartilhamento();
		
		if(empregador != null && empregador.getIdPessoaCompartilhamento().equals(
				pessoa.getIdPessoaCompartilhamento())) {
			throw new FormatoInvalidoException("Empregador"); 
		}
	}
	
	/**
	 * O valor da receita bruta mensal deve ser maior ou igual a zero.
	 * @param fonte A renda que está sendo validada.
	 * @throws ValorRendaBrutaMensalException Caso o valor da renda informado seja menor que zero.
	 */
	private void validarValorReceitaBrutaMensal(FonteRenda fonte) throws ValorRendaBrutaMensalException {
		if (fonte.getTipoFonteRenda() != null && fonte.getTipoFonteRenda().getValorObrigatorio()) {
			BigDecimal valor = fonte.getValorReceitaBrutaMensal();

			if (valor == null || BigDecimal.ZERO.compareTo(valor) == 1) {
				throw new ValorRendaBrutaMensalException();
			}
		}
	}
	
	/**
	 * Valida se o tipo de fonte de renda pode ser aplicado a pessoa.
	 * @param fonte A fonte de renda a ser validada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private void validarAplicacaoTipoPessoa(FonteRenda fonte) 
		throws BancoobException {
	
		Short codigo = fonte.getTipoFonteRenda().getCodigo();		
		PessoaCompartilhamento pessoa = fonte.getPessoaCompartilhamento();
		
		TipoFonteRenda tipoFonte = tipoDelegate.obter(codigo);
		Set<TipoPessoa> tiposPessoas = tipoFonte.getTiposPessoa();
		
		if(!tiposPessoas.contains(pessoa.getPessoa().getTipoPessoa())) {
			throw new TipoFonteRendaAplicacaoPessoaException();
		}
	}

	/**
	 * Valida se a data da validade da renda é maior que a data atual.
	 * @param dataValidadeRenda A data a ser validade.
	 * @throws DataAtualException Caso a data de validade seja menor ou igual a data atual.
	 */
	private void validarDataValidadeRenda(Date dataValidadeRenda) throws DataAtualException {

		if (dataValidadeRenda != null) {
			Date data1 = DataUtil.configurarPrimeiraDataIntervalo(dataValidadeRenda);
			Date data2 = DataUtil.configurarPrimeiraDataIntervalo(new Date());

			if (data1.before(data2) || data1.equals(data2)) {
				 throw new DataAtualException("Renda Provisória Até", "maior");
			}
		}
	}

}
