/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.builder;

import br.com.sicoob.capes.cadastro.negocio.enums.AtividadeEconomicaEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.EstadoCivilEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.NacionalidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;
import br.com.sicoob.capes.processamento.negocio.dominio.builder.PessoaFisicaBuilder;

/**
 * Builder para pessoas físicas criadas a partir do INSS.
 * @author Erico.Junior
 */
public class PessoaFisicaINSSBuilder extends PessoaFisicaBuilder {

	/** A constante CODIGO_VINCULO. */
	private static final short CODIGO_VINCULO = (short)2;
	
	/** A constante CODIGO_GRAU_INSTRUCAO. */
	private static final short CODIGO_GRAU_INSTRUCAO = (short)5;
	
	/** A constante ID_OCUPACAO_PROFISSIONAL. */
	private static final int ID_OCUPACAO_PROFISSIONAL = 11676;
	
	/** A constante ATIVIDADE. */
	private static final AtividadeEconomica ATIVIDADE = new AtividadeEconomica();
	
	/** A constante OCUPACAO. */
	private static final OcupacaoProfissional OCUPACAO = new OcupacaoProfissional();
	
	/** A constante ESTADO_CIVIL. */
	private static final EstadoCivil ESTADO_CIVIL = new EstadoCivil();
	
	/** A constante GRAU_INSTRUCAO. */
	private static final GrauInstrucao GRAU_INSTRUCAO = new GrauInstrucao();
	
	/** A constante VINCULO. */
	private static final VinculoEmpregaticio VINCULO = new VinculoEmpregaticio();
	
	/** A constante TIPO_DOCUMENTO. */
	private static final TipoDocumentoIdentificacao TIPO_DOCUMENTO = new TipoDocumentoIdentificacao();
	
	/** A constante NACIONALIDADE. */
	private static final Nacionalidade NACIONALIDADE = new Nacionalidade();

	static {
		ATIVIDADE.setCodigo(AtividadeEconomicaEnum.PESSOA_FISICA.getCodigo());		
		OCUPACAO.setId(ID_OCUPACAO_PROFISSIONAL);
		ESTADO_CIVIL.setCodigo(EstadoCivilEnum.SOLTEIRO.getCodigo());
		GRAU_INSTRUCAO.setCodigo(CODIGO_GRAU_INSTRUCAO);
		VINCULO.setCodigo(CODIGO_VINCULO);
		TIPO_DOCUMENTO.setCodigo(TipoDocumentoIdentificacaoEnum.CARTEIRA_IDENTIDADE.getCodigo());
		NACIONALIDADE.setCodigo(NacionalidadeEnum.BRASILEIRA.getCodigo());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarAtividadeEconomica() {
		super.getPessoaCompartilhamento().setAtividadeEconomica(ATIVIDADE);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarObservacaoPessoa() {
		super.getPessoaCompartilhamento().setDescricao("INCLUÍDO PELO INSS/DATAPREV");
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public void criarApelido(){
		super.getPessoaCompartilhamento().setNomeApelido(NAO_INFORMADO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarNomePai(){
		super.getPessoaCompartilhamento().setNomePai(NOME_PAI);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public void criarNumeroDocumento(){
		super.getPessoaCompartilhamento().setNumeroDocumento(NAO_INFORMADO);
	}

	/**
	 * {@inheritDoc}
	 */
	public void criarOrgaoExpedidorDocumento(){
		super.getPessoaCompartilhamento().setOrgaoExpedidorDocumento("N.INFORM.");
	}

	/**
	 * {@inheritDoc}
	 */
	public void criarOcupacaoProfissional() {
		super.getPessoaCompartilhamento().setOcupacaoProfissional(OCUPACAO);
	}

	/**
	 * {@inheritDoc}
	 */
	public void criarEstadoCivil() {
		super.getPessoaCompartilhamento().setEstadoCivil(ESTADO_CIVIL);
	}

	/**
	 * {@inheritDoc}
	 */
	public void criarQuantidadeDependentes(){
		super.getPessoaCompartilhamento().setQuantidadeDependentes(QUANTIDADE_DEPENDENTES);
	}

	/**
	 * {@inheritDoc}
	 */
	public void criarGrauInstrucao() {
		super.getPessoaCompartilhamento().setGrauInstrucao(GRAU_INSTRUCAO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarVinculoEmpregaticio() {
		super.getPessoaCompartilhamento().setVinculoEmpregaticio(VINCULO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarTipoSexo() {
		super.getPessoaCompartilhamento().setTipoSexo(TipoSexoEnum.MASCULINO.getCodigo());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarTipoDocumento(){
		super.getPessoaCompartilhamento().setTipoDocumento(TIPO_DOCUMENTO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarNacionalidade() {
		super.getPessoaCompartilhamento().setNacionalidade(NACIONALIDADE);
	}

}
