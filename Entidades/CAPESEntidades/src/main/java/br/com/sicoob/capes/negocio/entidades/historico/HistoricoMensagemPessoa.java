package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.MensagemBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * A Classe HistoricoMensagemPessoa.
 */
@Entity
@Table(name = "HISTMENSAGEMPESSOA", schema = "CLI")
public class HistoricoMensagemPessoa extends MensagemBase implements Historico {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7542377696357320556L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTMENSAGEMPESSOA")
	private Long idHistorico;
	
	/** O atributo id informacao. */
	@Column(name="IDMENSAGEMPESSOA")
	private Integer idMensagem;

	/** O atributo data hora fim. */
	@Column(name="DATAFIMCADASTRO")
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCLUSAO", length = 40)
	private String idUsuarioExclusao;


	/**
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getIdMensagem();
	}

	/**
	 * @return the idHistorico
	 */
	public Long getIdHistorico() {
		return idHistorico;
	}

	/**
	 * @param idHistorico the idHistorico to set
	 */
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	/**
	 * @return the idMensagem
	 */
	public Integer getIdMensagem() {
		return idMensagem;
	}

	/**
	 * @param idMensagem the idMensagem to set
	 */
	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdHistorico();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdHistorico(id);
	}

	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * @param dataHoraFim the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/**
	 * @return the idUsuarioExclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/**
	 * @param idUsuarioExclusao the idUsuarioExclusao to set
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

}
