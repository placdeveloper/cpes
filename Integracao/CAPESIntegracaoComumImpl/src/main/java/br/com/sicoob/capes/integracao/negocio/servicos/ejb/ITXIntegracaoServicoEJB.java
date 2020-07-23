package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.DadosCNPJVO;
import br.com.sicoob.capes.comum.negocio.vo.DadosCPFVO;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.integracao.negocio.excecao.ITXIntegracaoNegocioException;
import br.com.sicoob.capes.integracao.negocio.rest.Resposta;
import br.com.sicoob.capes.integracao.negocio.rest.RequisicaoRestful;
import br.com.sicoob.capes.integracao.negocio.rest.apimanager.RequisicaoApiManager;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.ITXIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.ITXIntegracaoServicoRemote;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.DetalheFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.MarcaFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.ModeloFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.VeiculoFipeVO;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.delegate.ConsultasDelegate;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.delegate.IntegracoesExtIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.dto.IObjRetornoReceitaFisica;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.dto.IObjRetornoReceitaJuridica;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.dto.IRetornoConsulta;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.dto.ITXObjectFactory;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.dto.filtro.FiltroConsultaOrgaosExternosITX;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.dto.filtro.IFiltroConsultaServico;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.enums.ParametroAdicionalConsultaEnum;
import br.com.sicoob.sisbr.integracoesext.integracao.negocio.enums.ServicoOrgaoExternoITXEnum;
import br.com.sicoob.tipos.DateTime;

/**
 * EJB para integração com o sistema "Integrações Externas" (Consultas Externas)
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(ITXIntegracaoServicoLocal.class)
@Remote(ITXIntegracaoServicoRemote.class)
public class ITXIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements ITXIntegracaoServicoRemote, ITXIntegracaoServicoLocal {

	/** O atributo mapaServicoOrgaoExternoITXEnum. */
	private Map<String, ServicoOrgaoExternoITXEnum> mapaServicoOrgaoExternoITXEnum;

	/** A constante BACEN. */
	public static final String BACEN = "BACEN";

	/** A constante SERASA. */
	public static final String SERASA = "SERASA";

	/** A constante RECEITA. */
	public static final String RECEITA = "RECEITA";

	/** A constante INDICADOR_NOVA_CONSULTA. */
	private static final Boolean INDICADOR_NOVA_CONSULTA = Boolean.FALSE;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public DadosReceitaFederalVO obterDadosReceita(TipoPessoaEnum tipoPessoa, final String cpfCnpj) throws BancoobException {

		DadosReceitaFederalVO dados = null;
		IRetornoConsulta retornoConsulta = null;
		UsuarioBancoob usuario = obterUsuario();

		ServicoOrgaoExternoITXEnum tipoServico = ServicoOrgaoExternoITXEnum.RECEITA_PF;
		if (TipoPessoaEnum.PESSOA_JURIDICA.equals(tipoPessoa)) {
			tipoServico = ServicoOrgaoExternoITXEnum.RECEITA_PJ;
		}

		IFiltroConsultaServico filtro = ITXObjectFactory.getInstance().criarFiltroConsulta();
		filtro.setCpfCnpj(cpfCnpj);
		filtro.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		filtro.setIdUnidadeInstituicao(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		filtro.setLogin(usuario.getLogin());
		filtro.setServicoOrgaoExterno(tipoServico);

		try {
			ConsultasDelegate consultaDelegate = IntegracoesExtIntegracaoFabricaDelegates.getInstance().criarConsultasDelegate();
			retornoConsulta = consultaDelegate.realizarConsulta(filtro);
			if (retornoConsulta != null) {
				if (retornoConsulta.getObjConsulta() != null) {
					dados = converterDadosReceitaFederal(retornoConsulta, tipoPessoa);
				} else if (retornoConsulta.getDetalheErro() != null) {
					throw new NegocioException(retornoConsulta.getDetalheErro().getMsgErro());
				}
			}
			return dados;
		} catch (NegocioException e) {
			throw new ITXIntegracaoNegocioException(e, "Falha ao obter dados da Receita Federal");
		}
	}

	/**
	 * Converter dados receita federal.
	 * 
	 * @param dados
	 *            o valor de dados
	 * @param tipoPessoa
	 *            o valor de tipo pessoa
	 * @return DadosReceitaFederalVO
	 */
	private DadosReceitaFederalVO converterDadosReceitaFederal(Object dados, TipoPessoaEnum tipoPessoa) {

		/*
		 * Devido a utilizacao de classloaders isolados, nao se deve utilizar
		 * classes de outros projetos como atributo/retorno de metodos nem como
		 * atributos de classe em objetos gerenciados pelo container.
		 */
		IRetornoConsulta retornoConsulta = (IRetornoConsulta) dados;
		DadosReceitaFederalVO vo = null;
		if (TipoPessoaEnum.PESSOA_FISICA.equals(tipoPessoa)) {
			DadosCPFVO cpfVO = new DadosCPFVO();
			IObjRetornoReceitaFisica retornoConsultaCPF = (IObjRetornoReceitaFisica) retornoConsulta.getObjConsulta();
			cpfVO.setAnoObito(retornoConsultaCPF.getAnoObito());
			cpfVO.setCodigoSituacaoCadastral(retornoConsultaCPF.getCodSituacaoCadastral());
			cpfVO.setCodResidenteExterior(retornoConsultaCPF.getCodResidenteExterior());
			cpfVO.setCodSexo(retornoConsultaCPF.getCodSexo());
			cpfVO.setDataNascimento(obterData(retornoConsultaCPF.getDataNascimento()));
			cpfVO.setDataUltimaAtualizacao(obterData(retornoConsultaCPF.getDataAtualizacao()));
			cpfVO.setDescResidenteExterior(retornoConsultaCPF.getDescResidenteExterior());
			cpfVO.setDescSexo(retornoConsultaCPF.getDescSexo());
			cpfVO.setDescSituacaoCadastral(retornoConsultaCPF.getDescSituacaoCadastral());
			cpfVO.setNome(retornoConsultaCPF.getNome());
			cpfVO.setNomeMae(retornoConsultaCPF.getNomeMae());
			cpfVO.setCpf(retornoConsultaCPF.getNumeroCpf());
			cpfVO.setNumTituloEleitor(retornoConsultaCPF.getNumTituloEleitor());
			vo = cpfVO;
		} else if (TipoPessoaEnum.PESSOA_JURIDICA.equals(tipoPessoa)) {
			DadosCNPJVO cnpjVO = new DadosCNPJVO();
			IObjRetornoReceitaJuridica retornoConsultaCNPJ = (IObjRetornoReceitaJuridica) retornoConsulta.getObjConsulta();
			cnpjVO.setBairro(retornoConsultaCNPJ.getBairro());
			cnpjVO.setCep(retornoConsultaCNPJ.getCep());
			cnpjVO.setCidadeExterior(retornoConsultaCNPJ.getCidadeExterior());
			cnpjVO.setCnaePrincipal(retornoConsultaCNPJ.getCnaePrincipal());
			cnpjVO.setCodEstabelecimento(retornoConsultaCNPJ.getCodEstabelecimento());
			cnpjVO.setCodigoMunicipio(retornoConsultaCNPJ.getCodigoMunicipio());
			cnpjVO.setCodigoPais(retornoConsultaCNPJ.getCodigoPais());
			cnpjVO.setCodigoSituacaoCadastral(retornoConsultaCNPJ.getCodSituacaoCadastral());
			cnpjVO.setComplemento(retornoConsultaCNPJ.getComplemento());
			cnpjVO.setDataAbertura(obterData(retornoConsultaCNPJ.getDataAbertura()));
			cnpjVO.setDataUltimaAtualizacao(obterData(retornoConsultaCNPJ.getDataSituacaoCadastral()));
			cnpjVO.setDdd1(retornoConsultaCNPJ.getDdd1());
			cnpjVO.setDdd2(retornoConsultaCNPJ.getDdd2());
			cnpjVO.setEmail(retornoConsultaCNPJ.getEmail());
			cnpjVO.setLogradouro(retornoConsultaCNPJ.getLogradouro());
			cnpjVO.setNaturezaJuridica(retornoConsultaCNPJ.getNaturezaJuridica());
			cnpjVO.setNomeEmpresarial(retornoConsultaCNPJ.getNomeEmpresarial());
			cnpjVO.setNomeFantasia(retornoConsultaCNPJ.getNomeFantasia());
			cnpjVO.setNomeMunicipio(retornoConsultaCNPJ.getNomeMunicipio());
			cnpjVO.setNomePais(retornoConsultaCNPJ.getNomePais());
			cnpjVO.setCnpj(retornoConsultaCNPJ.getNumeroCnpj());
			cnpjVO.setNumeroLogradouro(retornoConsultaCNPJ.getNumeroLogradouro());
			cnpjVO.setTelefone1(retornoConsultaCNPJ.getTelefone1());
			cnpjVO.setTelefone2(retornoConsultaCNPJ.getTelefone2());
			cnpjVO.setTipoLogradouro(retornoConsultaCNPJ.getTipoLogradouro());
			cnpjVO.setUf(retornoConsultaCNPJ.getUf());
			vo = cnpjVO;
		}

		if (vo != null) {
			vo.setIdConsulta(retornoConsulta.getIdConsulta());
			vo.setIdInstituicao(retornoConsulta.getIdInstituicao());
			vo.setIdUnidadeInst(retornoConsulta.getIdUnidadeInst());
		}
		return vo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void requisitarConsultas(String cpfCnpj) throws BancoobException {
		ConsultasDelegate consultaDelegate = IntegracoesExtIntegracaoFabricaDelegates.getInstance().criarConsultasDelegate();
		consultaDelegate.requisitarConsultas(cpfCnpj);
	}

	/**
	 * {@inheritDoc}
	 */
	public byte[] gerarRelatorioConsulta(Integer idConsulta) throws BancoobException {
		byte[] retorno = null;
		if (idConsulta != null) {
			ConsultasDelegate consultaDelegate = IntegracoesExtIntegracaoFabricaDelegates.getInstance().criarConsultasDelegate();
			retorno = consultaDelegate.gerarRelatorioConsulta(idConsulta);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public byte[] gerarRelatorioConsulta(String tipoRelatorio, String idInstituicao, String idUnidadeInstituicao, String login, String mesConsultaBacen,
			String anoConsultaBacen, String cpfCnpj) throws BancoobException {
		ConsultasDelegate consultaDelegate = IntegracoesExtIntegracaoFabricaDelegates.getInstance().criarConsultasDelegate();

		ServicoOrgaoExternoITXEnum enumTipoRelatorio = getMapaServicoOrgaoExternoITXEnum().get(tipoRelatorio);
		FiltroConsultaOrgaosExternosITX filtro = montaFiltroConsulta(idInstituicao, idUnidadeInstituicao, login, mesConsultaBacen, anoConsultaBacen, cpfCnpj,
				enumTipoRelatorio);

		byte[] gerarRelatorioConsulta = null;
		try {
			gerarRelatorioConsulta = consultaDelegate.gerarRelatorioConsulta(filtro, INDICADOR_NOVA_CONSULTA);
		} catch (BancoobException e) {
			throw new NegocioException("Falha ao gerar o relatório.", e);
		}
		return gerarRelatorioConsulta;
	}

	/**
	 * Monta filtro consulta.
	 * 
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @param idUnidadeInstituicao
	 *            o valor de id unidade instituicao
	 * @param login
	 *            o valor de login
	 * @param mesConsultaBacen
	 *            o valor de mes consulta bacen
	 * @param anoConsultaBacen
	 *            o valor de ano consulta bacen
	 * @param cpfCnpj
	 *            o valor de cpf cnpj
	 * @param orgaoExternoEnum
	 *            o valor de orgao externo enum
	 * @return FiltroConsultaOrgaosExternosITX
	 */
	private FiltroConsultaOrgaosExternosITX montaFiltroConsulta(String idInstituicao, String idUnidadeInstituicao, String login, String mesConsultaBacen,
			String anoConsultaBacen, String cpfCnpj, ServicoOrgaoExternoITXEnum orgaoExternoEnum) {

		FiltroConsultaOrgaosExternosITX filtro = new FiltroConsultaOrgaosExternosITX();
		filtro.setCpfCnpj(cpfCnpj);
		filtro.setIdInstituicao(Integer.valueOf(idInstituicao));
		filtro.setIdUnidadeInstituicao(Integer.valueOf(idUnidadeInstituicao));
		filtro.setLogin(login);
		filtro.setServicoOrgaoExterno(orgaoExternoEnum);
		filtro.setParametrosAdicionais(new HashMap<ParametroAdicionalConsultaEnum, Object>());
		if (anoConsultaBacen != null) {
			HashMap<ParametroAdicionalConsultaEnum, Object> parametrosAdicionais = new HashMap<ParametroAdicionalConsultaEnum, Object>();
			parametrosAdicionais.put(ParametroAdicionalConsultaEnum.DATABASE, anoConsultaBacen + mesConsultaBacen);
			filtro.setParametrosAdicionais(parametrosAdicionais);
		}

		return filtro;
	}

	/**
	 * Recupera o valor de mapaServicoOrgaoExternoITXEnum.
	 * 
	 * @return o valor de mapaServicoOrgaoExternoITXEnum
	 */
	private Map<String, ServicoOrgaoExternoITXEnum> getMapaServicoOrgaoExternoITXEnum() {
		if (mapaServicoOrgaoExternoITXEnum == null) {
			mapaServicoOrgaoExternoITXEnum = new HashMap<String, ServicoOrgaoExternoITXEnum>();
			mapaServicoOrgaoExternoITXEnum.put(BACEN, ServicoOrgaoExternoITXEnum.BACEN_SCR);
			mapaServicoOrgaoExternoITXEnum.put(SERASA, ServicoOrgaoExternoITXEnum.SERASA_CONCENTRE);
			mapaServicoOrgaoExternoITXEnum.put(RECEITA, ServicoOrgaoExternoITXEnum.RECEITA_PF);
		}
		return mapaServicoOrgaoExternoITXEnum;
	}

	/**
	 * Cria uma instancia de {@link Date} forçando o retorno do tipo, pois o ITX
	 * estava enviando uma instancia de {@link DateTime} e o flex não conseguia
	 * converter, deixando as propriedade de data como nulas.
	 * 
	 * @param data
	 * @return
	 */
	private Date obterData(Date data) {
		if (data != null) {
			return new Date(data.getTime());
		}
		return data;
	}
	
	public MarcaFipeVO[] obterMarcasFIPE(String tipoVeiculo) throws BancoobException {
		Resposta<MarcaFipeVO[]> solicitacao = new RequisicaoApiManager("${capes.chave.acesso}")
			.comEndereco("/fipe/tipos/" + tipoVeiculo + "/marcas")
			.comMetodoHttpGET()
			.comEncode(RequisicaoRestful.ENCODE_UTF_8)
			.requisitar(MarcaFipeVO[].class);
		
		return solicitacao.getConteudo();
	}
	
	public VeiculoFipeVO[] obterVeiculosFIPE(String tipoVeiculo, Integer idMarca) throws BancoobException {
		Resposta<VeiculoFipeVO[]> solicitacao = new RequisicaoApiManager("${capes.chave.acesso}")
			.comEndereco("/fipe/tipos/" + tipoVeiculo + "/marcas/" + idMarca + "/veiculos")
			.comMetodoHttpGET()
			.comEncode(RequisicaoRestful.ENCODE_UTF_8)
			.requisitar(VeiculoFipeVO[].class);
		
		return solicitacao.getConteudo();
	}
	
	public ModeloFipeVO[] obterModelosFIPE(String tipoVeiculo, Integer idMarca, Integer idVeiculo) throws BancoobException {
		Resposta<ModeloFipeVO[]> solicitacao = new RequisicaoApiManager("${capes.chave.acesso}")
			.comEndereco("/fipe/tipos/" + tipoVeiculo + "/marcas/" + idMarca + "/veiculos/" + idVeiculo + "/modelos")
			.comMetodoHttpGET()
			.comEncode(RequisicaoRestful.ENCODE_UTF_8)
			.requisitar(ModeloFipeVO[].class);
		
		return solicitacao.getConteudo();
	}
	
	public DetalheFipeVO obterDetalheFIPE(String tipoVeiculo, Integer idMarca, Integer idVeiculo, Integer idModelo) throws BancoobException {
		Resposta<DetalheFipeVO> solicitacao = new RequisicaoApiManager("${capes.chave.acesso}")
			.comEndereco("/fipe/tipos/" + tipoVeiculo + "/marcas/" + idMarca + "/veiculos/" + idVeiculo + "/modelos/" + idModelo+ "/detalhes")
			.comMetodoHttpGET()
			.comEncode(RequisicaoRestful.ENCODE_UTF_8)
			.requisitar(DetalheFipeVO.class);
		
		return solicitacao.getConteudo();
	}
	
}