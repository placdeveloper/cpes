/*
 * SICOOB
 * 
 * Pessoa.java(br.com.sicoob.capes.negocio.entidades.Pessoa)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum;
import br.com.sicoob.capes.comum.util.Constantes.Persistencia;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import flexjson.JSON;

/**
 * @author erico.junior
 *
 */
@Entity
@Table(name = "PESSOA", schema="CLI")
public class Pessoa extends CAPESEntidade<Integer> implements Serializable{

	/** Serial UID.*/
	private static final long serialVersionUID = -2595821323708215012L;

	/** O atributo id pessoa. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPessoa;

	/** O atributo cpf cnpj. */
	@Column(name = "NUMCPFCNPJ")
	private String cpfCnpj;

	/** O atributo nome rfb. */
	@Column(name = "NOMESRF", length = 200)
	private String nomeRFB;
	
	/** O atributo situacao cadastral rfb. */
	@Column(name = "CODSITUACAOSRF")
	@Enumerated(EnumType.ORDINAL)
	private SituacaoCadastralRFBEnum situacaoCadastralRFB;
	
	/** O atributo tipo pessoa. */
	@JoinColumn(name = "CODTIPOPESSOA", referencedColumnName = "CODTIPOPESSOA")
	@ManyToOne
	private TipoPessoa tipoPessoa;
	
	/** O atributo compartilhamentos. */
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, targetEntity = PessoaCompartilhamento.class)
	@Filter(name=Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, condition="codCompartilhamentoCadastro = :codigo")
	private Set<PessoaCompartilhamento> compartilhamentos;
	
	/** O atributo instituicoes negocio. */
	@OneToMany(mappedBy="pessoa", fetch = FetchType.LAZY)
	private Set<PessoaInstituicao> instituicoesNegocio;
	
	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return o valor de nomeRFB
	 */
	public String getNomeRFB() {
		return nomeRFB;
	}

	/**
	 * @param nomeRFB o valor de nomeRFB
	 */
	public void setNomeRFB(String nomeRFB) {
		this.nomeRFB = nomeRFB;
	}

	/**
	 * @return o valor de situacaoCadastralRFB
	 */
	public SituacaoCadastralRFBEnum getSituacaoCadastralRFB() {
		return situacaoCadastralRFB;
	}

	/**
	 * @param situacaoCadastralRFB o valor de situacaoCadastralRFB
	 */
	public void setSituacaoCadastralRFB(SituacaoCadastralRFBEnum codigoSituacaoRFB) {
		this.situacaoCadastralRFB = codigoSituacaoRFB;
	}

	/**
	 * @return the tipoPessoa
	 */
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	/**
	 * @param tipoPessoa the tipoPessoa to set
	 */
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * @return the compartilhamentos
	 */
	@JSON(include=false)
	public Set<PessoaCompartilhamento> getCompartilhamentos() {
		return compartilhamentos;
	}

	/**
	 * @param compartilhamentos the compartilhamentos to set
	 */
	public void setCompartilhamentos(Set<PessoaCompartilhamento> compartilhamentos) {
		this.compartilhamentos = compartilhamentos;
	}

	/**
	 * @return the instituicoesNegocio
	 */
	@JSON(include=false)
	public Set<PessoaInstituicao> getInstituicoesNegocio() {
		return instituicoesNegocio;
	}

	/**
	 * @param instituicoesNegocio the instituicoesNegocio to set
	 */
	public void setInstituicoesNegocio(Set<PessoaInstituicao> instituicoesNegocio) {
		this.instituicoesNegocio = instituicoesNegocio;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdPessoa(id);
	}
	
	/**
	 * Caso o filtro declarado em {@link #getCompartilhamentos()} esteja
	 * ativado, retorna a {@link PessoaCompartilhamento} referente ao grupo de
	 * compartilhamento do usuário logado. Caso contrário, retorna a primeira
	 * posição da lista de compartilhamentos ({@link #getCompartilhamentos()})
	 * 
	 * @return a {@code PessoaCompartilhamento}
	 * @throws BancoobRuntimeException
	 *             caso não exista uma {@link PessoaCompartilhamento} para esta
	 *             {@link Pessoa}, ou seja, se {@link #getCompartilhamentos()}
	 *             retornar uma lista vazia
	 */
	@JSON(include=false)
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		
		PessoaCompartilhamento compartilhamento = null;
		if (getCompartilhamentos() != null) {
			if (getCompartilhamentos().isEmpty()) {
				throw new BancoobRuntimeException("msg.erro.integridade",
						new Object[] { getIdPessoa() });
			}
			compartilhamento = getCompartilhamentos().iterator().next();
		}
		return compartilhamento;
	}
	
	@JSON(include=false)
	public PessoaCompartilhamento getPessoaCompartilhamentoPorGrupo(final Short codCompartilhamento) {
		
		if(getCompartilhamentos() == null || getCompartilhamentos().isEmpty()) {
				throw new BancoobRuntimeException("msg.erro.integridade",
						new Object[] { getIdPessoa() });
		} else {
			for(PessoaCompartilhamento obj : getCompartilhamentos()) {
				if(obj.getCodCompartilhamentoCadastro().equals(codCompartilhamento)) {
					return obj;
				}
			}
			throw new BancoobRuntimeException("msg.erro.integridade.grupoCompartilhamento",
						new Object[] { getIdPessoa(), codCompartilhamento });
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "cpfCnpj");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "cpfCnpj");
	}
	
}
