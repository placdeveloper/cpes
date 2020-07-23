package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.sicoob.capes.api.inclusao.negocio.dto.AnotacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.MensagemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ReferenciaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RelacionamentoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * A Classe FabricaConversores.
 * 
 * @param <E>
 *            o tipo generico
 * @param <D>
 *            o tipo generico
 * @author bruno.carneiro
 */
public class FabricaConversores<E extends CAPESEntidade<?>, D extends RegistroInclusaoDTO<?>> {

	/** O atributo instancia. */
	private static FabricaConversores<CAPESEntidade<?>, RegistroInclusaoDTO<?>> instancia;

	/**
	 * Obter instancia.
	 * 
	 * @return FabricaConversores
	 */
	public static FabricaConversores<CAPESEntidade<?>, RegistroInclusaoDTO<?>> obterInstancia() {
		if (instancia == null) {
			synchronized (FabricaConversores.class) {
				if (instancia == null) {
					instancia = new FabricaConversores<CAPESEntidade<?>, RegistroInclusaoDTO<?>>();
				}
			}
		}
		return instancia;
	}

	/**
	 * Obter conversor.
	 * 
	 * @param dto
	 *            o valor de dto
	 * @return Conversor
	 */
	@SuppressWarnings("unchecked")
	public Conversor<E, D> obterConversor(D dto) {
		Conversor<?, ?> conversor = null;
		Class<D> classeDTO = (Class<D>) dto.getClass();

		if (AnotacaoDTO.class.equals(classeDTO)) {
			conversor = new ConversorAnotacao();
		} else if (BemDTO.class.isAssignableFrom(classeDTO)) {
			conversor = new ConversorBem();
		} else if (ClienteDTO.class.equals(classeDTO)) {
			conversor = new ConversorCliente();
		} else if (EmailDTO.class.equals(classeDTO)) {
			conversor = new ConversorEmail();
		} else if (EnderecoDTO.class.equals(classeDTO)) {
			conversor = new ConversorEndereco();
		} else if (FonteRendaDTO.class.equals(classeDTO)) {
			conversor = new ConversorFonteRenda();
		} else if (InformacaoProfissionalDTO.class.equals(classeDTO)) {
			conversor = new ConversorInformacaoProfissional();
		} else if (MensagemDTO.class.equals(classeDTO)) {
			conversor = new ConversorMensagem();
		} else if (PessoaDTO.class.isAssignableFrom(classeDTO)) {
			conversor = new ConversorPessoa();
		} else if (ReferenciaDTO.class.equals(classeDTO)) {
			conversor = new ConversorReferencia();
		} else if (RelacionamentoDTO.class.equals(classeDTO)) {
			conversor = new ConversorRelacionamento();
		} else if (TelefoneDTO.class.equals(classeDTO)) {
			conversor = new ConversorTelefone();
		}
		return (Conversor<E, D>) conversor;
	}

	/**
	 * Obter conversor.
	 * 
	 * @param entidade
	 *            o valor de entidade
	 * @return Conversor
	 */
	@SuppressWarnings("unchecked")
	public Conversor<E, D> obterConversor(E entidade) {
		Conversor<?, ?> conversor = null;
		Class<E> classeEntidade = (Class<E>) entidade.getClass();

		if (Anotacao.class.equals(classeEntidade)) {
			conversor = new ConversorAnotacao();
		} else if (Bem.class.isAssignableFrom(classeEntidade)) {
			conversor = new ConversorBem();
		} else if (PessoaInstituicao.class.equals(classeEntidade)) {
			conversor = new ConversorCliente();
		} else if (Email.class.equals(classeEntidade)) {
			conversor = new ConversorEmail();
		} else if (Endereco.class.equals(classeEntidade)) {
			conversor = new ConversorEndereco();
		} else if (FonteRenda.class.equals(classeEntidade)) {
			conversor = new ConversorFonteRenda();
		} else if (InformacaoProfissional.class.equals(classeEntidade)) {
			conversor = new ConversorInformacaoProfissional();
		} else if (Mensagem.class.equals(classeEntidade)) {
			conversor = new ConversorMensagem();
		} else if (PessoaCompartilhamento.class.isAssignableFrom(classeEntidade)) {
			conversor = new ConversorPessoa();
		} else if (Referencia.class.equals(classeEntidade)) {
			conversor = new ConversorReferencia();
		} else if (RelacionamentoPessoa.class.equals(classeEntidade)) {
			conversor = new ConversorRelacionamento();
		} else if (Telefone.class.equals(classeEntidade)) {
			conversor = new ConversorTelefone();
		}
		return (Conversor<E, D>) conversor;
	}
}