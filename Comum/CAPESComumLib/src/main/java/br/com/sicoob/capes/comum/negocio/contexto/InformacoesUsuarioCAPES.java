/*
 * SICOOB
 * 
 * InformacoesUsuarioCAPES.java(br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES)
 */
package br.com.sicoob.capes.comum.negocio.contexto;

import org.apache.commons.lang.builder.EqualsBuilder;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * The Class InformacoesUsuarioCAPES.
 * 
 * @author erico.junior
 */
public class InformacoesUsuarioCAPES {

	/** A Constante LOGGER. */
	private static final ISicoobLogger LOGGER = SicoobLoggerPadrao.getInstance(InformacoesUsuarioCAPES.class);

	/** Inforamções do usuário. */
	private static final ThreadLocal<InformacoesUsuarioCAPES> INSTANCIA = new ThreadLocal<InformacoesUsuarioCAPES>();

	/** O atributo informacoes usuario. */
	private InformacoesUsuario informacoesUsuario;

	/** O atributo codigo compartilhamento. */
	private Short codigoCompartilhamento;

	/** O atributo id instituicao. */
	private String idInstituicao;

	/** O atributo id sistema cooperativo. */
	private Integer idSistemaCooperativo;

	/**
	 * Cria uma nova instância de informacoes usuario capes.
	 * 
	 * @param informacoes
	 *            the informacoes
	 */
	public InformacoesUsuarioCAPES(InformacoesUsuario informacoes) {

		this.informacoesUsuario = informacoes;
		if (informacoes != null) {
			this.idInstituicao = informacoes.getIdInstituicao();
		}
	}

	/**
	 * Cria uma nova instância de informacoes usuario capes.
	 * 
	 * @param codigoCompartilhamento
	 *            the codigo compartilhamento
	 * @param idInstituicao
	 *            the id instituicao
	 */
	public InformacoesUsuarioCAPES(Short codigoCompartilhamento, String idInstituicao) {

		this.idInstituicao = idInstituicao;
		this.codigoCompartilhamento = codigoCompartilhamento;
	}

	/**
	 * Cria uma nova instância de informacoes usuario capes.
	 * 
	 * @param informacoesUsuario
	 *            the informacoes usuario
	 * @param codigoCompartilhamento
	 *            the codigo compartilhamento
	 */
	public InformacoesUsuarioCAPES(InformacoesUsuario informacoesUsuario, Short codigoCompartilhamento) {

		this(informacoesUsuario);
		this.codigoCompartilhamento = codigoCompartilhamento;
	}

	/**
	 * Retorna a instância das informações do usuário a ser usada.
	 * 
	 * @return a instância das informações do usuário a ser usada.
	 */
	public static InformacoesUsuarioCAPES getInstance() {

		InformacoesUsuarioCAPES instance = INSTANCIA.get();

		LOGGER.debug(InformacoesUsuarioCAPES.class.getSimpleName(), ".getInstance: ", String.valueOf(instance));

		return instance;
	}

	/**
	 * Preenche instance.
	 * 
	 * @param instance
	 *            o novo instance
	 */
	public static void setInstance(InformacoesUsuarioCAPES instance) {

		LOGGER.debug(InformacoesUsuarioCAPES.class.getSimpleName(), ".setInstance(inicio): ",
				String.valueOf(INSTANCIA.get()));

		INSTANCIA.set(instance);

		LOGGER.debug(InformacoesUsuarioCAPES.class.getSimpleName(), ".setInstance(fim): ", String.valueOf(instance));
	}

	/**
	 * Removes the instance.
	 */
	public static void removeInstance() {

		LOGGER.debug(InformacoesUsuarioCAPES.class.getSimpleName(), ".removeInstance(inicio): ",
				String.valueOf(INSTANCIA.get()));

		INSTANCIA.remove();

		LOGGER.debug(InformacoesUsuarioCAPES.class.getSimpleName(), ".removeInstance(fim): ",
				String.valueOf(INSTANCIA.get()));
	}

	/**
	 * Recupera codigo compartilhamento.
	 * 
	 * @return codigo compartilhamento
	 */
	public Short getCodigoCompartilhamento() {

		return codigoCompartilhamento;
	}

	/**
	 * Recupera login.
	 * 
	 * @return login
	 */
	public String getLogin() {

		return informacoesUsuario != null ? informacoesUsuario.getLogin() : null;
	}

	/**
	 * Recupera cooperativa.
	 * 
	 * @return cooperativa
	 */
	public String getCooperativa() {

		return informacoesUsuario != null ? informacoesUsuario.getCooperativa() : null;
	}

	/**
	 * Recupera pac.
	 * 
	 * @return pac
	 */
	public String getPac() {

		return informacoesUsuario != null ? informacoesUsuario.getPac() : null;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public String getIdInstituicao() {

		return idInstituicao;
	}

	/**
	 * Recupera id unidade instituicao.
	 * 
	 * @return id unidade instituicao
	 */
	public String getIdUnidadeInstituicao() {

		return informacoesUsuario != null ? informacoesUsuario.getIdUnidadeInstituicao() : null;
	}

	/**
	 * Recupera dominio.
	 * 
	 * @return dominio
	 */
	public String getDominio() {

		return informacoesUsuario != null ? informacoesUsuario.getDominio() : null;
	}

	/**
	 * Recupera id sistema cooperativo.
	 * 
	 * @return id sistema cooperativo
	 */
	public Integer getIdSistemaCooperativo() {

		Integer valor = null;
		if (idSistemaCooperativo != null) {
			valor = idSistemaCooperativo;
		} else if (informacoesUsuario != null) {
			valor = informacoesUsuario.getIdSistemaCooperativo();
		}
		return valor;
	}

	/**
	 * Preenche id sistema cooperativo.
	 * 
	 * @param idSistemaCooperativo
	 *            o novo id sistema cooperativo
	 */
	public void setIdSistemaCooperativo(Integer idSistemaCooperativo) {

		this.idSistemaCooperativo = idSistemaCooperativo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		return ReflexaoUtils.hashCode(this, "login", "idInstituicao", "idUnidadeInstituicao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {

		boolean test = (obj != null);
		if (test) {
			if (obj instanceof InformacoesUsuario) {
				InformacoesUsuario info = (InformacoesUsuario) obj;
				EqualsBuilder equalsBuilder = new EqualsBuilder();
				equalsBuilder.append(getLogin(), info.getLogin());
				equalsBuilder.append(getIdInstituicao(), info.getIdInstituicao());
				equalsBuilder.append(getIdUnidadeInstituicao(), info.getIdUnidadeInstituicao());
				test = equalsBuilder.isEquals();
			} else if (obj instanceof InformacoesUsuarioCAPES) {
				test = ReflexaoUtils.equals(this, obj, "login", "idInstituicao", "idUnidadeInstituicao");
			} else {
				test = false;
			}
		}
		return test;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return getClass().getSimpleName() + ": login#" + getLogin() + " / idInstituicao#" + getIdInstituicao()
				+ " / idUnidadeInst#" + getIdUnidadeInstituicao();
	}

}
