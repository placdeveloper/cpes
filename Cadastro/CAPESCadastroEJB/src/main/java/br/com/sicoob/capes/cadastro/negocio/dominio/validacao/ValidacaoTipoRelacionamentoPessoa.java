/* 
 * Sicoob
 * ValidacaoTipoRelacionamentoPessoa.java 
 * Criado em: 17/11/2011
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;

/**
 * 17/11/2011
 * @author Rodrigo.Chaves
 */
public class ValidacaoTipoRelacionamentoPessoa implements Validacao<TipoRelacionamentoPessoa> {

	/**
	 * {@inheritDoc}
	 */
	public void validar(final TipoRelacionamentoPessoa tipoRelacionamento) throws BancoobException {
		validarCamposObrigatorios(tipoRelacionamento);
		validarNegocio(tipoRelacionamento);
	}

	/**
	 * Valida os dados da entidade.
	 * 
	 * @param tipoRelacionamento
	 *            A entidade que est� sendo validada.
	 * @throws CampoNaoInformadoException
	 *             Caso algum campo obrigat�rio n�o tenha sido preenchido.
	 */
	protected void validarCamposObrigatorios(final TipoRelacionamentoPessoa tipoRelacionamento)
			throws CampoNaoInformadoException {

		if (tipoRelacionamento.getCodigo() == null) {
			throw new CampoNaoInformadoException("C�digo");
		}

		if (StringUtils.isBlank(tipoRelacionamento.getDescricao())) {
			throw new CampoNaoInformadoException("Nome");
		}

		if ((tipoRelacionamento.getTiposPessoaRelacionamento() == null)
				|| (tipoRelacionamento.getTiposPessoaRelacionamento().isEmpty())) {
			throw new CampoNaoInformadoException("Tipo de Pessoa Relacionamento");
		}

		if ((tipoRelacionamento.getTiposPessoaRelacionada() == null)
				|| (tipoRelacionamento.getTiposPessoaRelacionada().isEmpty())) {
			throw new CampoNaoInformadoException("Tipo de Pessoa Relacionada");
		}

		if (tipoRelacionamento.getHabilitaCapitalSocial() == null) {
			throw new CampoNaoInformadoException("Habilita Capital Social");
		}

		if (tipoRelacionamento.getHabilitaPoderes() == null) {
			throw new CampoNaoInformadoException("Habilita Poderes");
		}

		if (tipoRelacionamento.getHabilitaEnvioCCS() == null) {
			throw new CampoNaoInformadoException("Habilita Envio ao CCS");
		}

		if (tipoRelacionamento.getHabilitaDadosRegistro() == null) {
			throw new CampoNaoInformadoException("Habilita Dados do Registro");
		}

		if (tipoRelacionamento.getCompoeAssinatura() == null) {
			throw new CampoNaoInformadoException("Comp�e Cart�o de Assinatura");
		}
	}

	/**
	 * Valida o neg�cio relacionado a entidade.
	 * 
	 * @param entidade
	 *            A entidade que est� sendo validada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	protected void validarNegocio(final TipoRelacionamentoPessoa tipoRelacionamento)
			throws BancoobException {

		TipoRelacionamentoPessoa reverso = tipoRelacionamento.getRelacionamentoReverso();
		if (reverso != null) {
			for (TipoPessoa tipoPessoaRelacionamento : tipoRelacionamento
					.getTiposPessoaRelacionamento()) {
				if (!reverso.getTiposPessoaRelacionada().contains(tipoPessoaRelacionamento)) {
					throw new NegocioException("MN133", reverso.getDescricao());
				}
			}
			
			for (TipoPessoa tipoPessoaRelacionada : tipoRelacionamento.getTiposPessoaRelacionada()) {
				if (!reverso.getTiposPessoaRelacionamento().contains(tipoPessoaRelacionada)) {
					throw new NegocioException("MN133", reverso.getDescricao());
				}
			}
		}
	}

}
