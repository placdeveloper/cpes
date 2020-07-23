package br.com.sicoob.sisbr.capes.anotacoes.testes;


/**
 * Classe de teste que envia mensagens contendo o seguinte objeto serializado:
 * br.com.sicoob.sisbr.integracoesext.integracaoreceitafederal.negocio.vo.RetornoConsultaCNPJ;
 * br.com.sicoob.sisbr.integracoesext.integracaoreceitafederal.negocio.vo.RetornoConsultaCPF
 * 
 * @author Marcelo.Onofre
 * 
 */
public class TestAtualizarAnotacoesReceita_ITX extends AbstractTestAtualizarAnotacoes {
	
//
//	/**
//	 * método executado no início dos testes e que
//	 * estabelece uma conexão com o serviço JMS
//	 * 
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		estabelecerConexao();
//	}
//	
//	/**
//	 * Teste de envio de mensagem para uma PF com o CPF que nada consta
//	 */
//	@Test
//	public void testMessageNadaConstaCPF() {
//			
//		RetornoConsultasExternasDTO dto = new RetornoConsultasExternasDTO();
//		dto.setTipoServico(ServicoOrgaoExternoEnum.RECEITA_FEDERAL_PF);
//		dto.setDescCpfCnpj("97679167338"); //CARMINDO SILVA NETO		
//		dto.setIdConsulta(999);
//		dto.setAnotacoesConsulta(getAnotacaoPFNadaConsta());
//		dto.setIdInstituicao(523);
//		
//		send(dto);
//		
//	}
//	
//	/**
//	 * Teste de envio de mensagem para uma PJ com o CNPJ que nada consta
//	 */
//	@Test
//	public void testMessageNadaConstaCNPJ() {
//		esperarExecucaoAnterior();
//		
//		RetornoConsultasExternasDTO dto = new RetornoConsultasExternasDTO();
//		dto.setTipoServico(ServicoOrgaoExternoEnum.RECEITA_FEDERAL_PJ);
//		dto.setDescCpfCnpj("25989666466853");//BORGES PEREIRA S/C LTDA
//		dto.setIdConsulta(999);
//		dto.setAnotacoesConsulta(getAnotacaoPJNadaConsta());
//		dto.setIdInstituicao(523);
//		
//		send(dto);
//	}
//	
//	/**
//	 * Teste de envio de mensagem para uma PF com o CPF irregular
//	 */
//	@Test
//	public void testMessageCPFIrregular() {
//		esperarExecucaoAnterior();
//		
//		RetornoConsultasExternasDTO dto = new RetornoConsultasExternasDTO();
//		dto.setTipoServico(ServicoOrgaoExternoEnum.RECEITA_FEDERAL_PF);
//		dto.setDescCpfCnpj("44584153795");	//ANIBAL RODRIGUES JUNIOR	
//		dto.setIdConsulta(999);
//		dto.setAnotacoesConsulta(getAnotacaoPFIrregular());
//		dto.setIdInstituicao(523);
//		
//		send(dto);
//	}
//	
//	/**
//	 * Teste de envio de mensagem para uma PJ com o CNPJ irregular
//	 */
//	@Test
//	public void testMessageCNPJIrregular() {
//		esperarExecucaoAnterior();
//		
//		RetornoConsultasExternasDTO dto = new RetornoConsultasExternasDTO();
//		dto.setTipoServico(ServicoOrgaoExternoEnum.RECEITA_FEDERAL_PJ);
//		dto.setDescCpfCnpj("25919666366823");//Boa Vista Telecomunicações LTDA EPP
//		dto.setIdConsulta(999);
//		dto.setAnotacoesConsulta(getAnotacaoPJIrregular());
//		dto.setIdInstituicao(523);
//		
//		send(dto);
//	}
//	
//	
//	/**
//	 * Teste de envio de mensagem para uma PJ com o CNPJ irregular
//	 */
//	@Test
//	public void testMessageCNPJIrregularEnvioDoc() {
//		esperarExecucaoAnterior();
//		
//		RetornoConsultasExternasDTO dto = new RetornoConsultasExternasDTO();
//		dto.setTipoServico(ServicoOrgaoExternoEnum.ENVIO_DOCUMENTO_RECEITA_FEDERAL);
//		dto.setDescCpfCnpj("52616648466718");
//		dto.setAnotacoesConsulta(getAnotacaoPJSuspensaDoc());
//				
//		send(dto);
//	}
//	
//	/**
//	 * Teste de envio de mensagem para uma PJ com o CNPJ irregular
//	 */
//	@Test
//	public void testMessagePFIrregularEnvioDoc() {
//		esperarExecucaoAnterior();
//		
//		RetornoConsultasExternasDTO dto = new RetornoConsultasExternasDTO();
//		dto.setTipoServico(ServicoOrgaoExternoEnum.ENVIO_DOCUMENTO_RECEITA_FEDERAL);
//		dto.setDescCpfCnpj("58589886280");
//		dto.setAnotacoesConsulta(getAnotacaoPFPendenteDoc());
//				
//		send(dto);
//	}
//	
//	/**
//	 * método executado no final dos testes e que fecha as conexões
//	 * do publicador, da sessão e da conexão
//	 *  
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//		finalizarConexao();		
//	}
//
//	/**
//	 * Mock de uma pessoa jurídica com {@link SituacaoCadastralRFBEnum} ativa
//	 * @return {@link RetornoConsultaCNPJ}
//	 */
//	private Serializable getAnotacaoPJNadaConsta() {
//		RetornoConsultaCNPJ pj = new RetornoConsultaCNPJ();
//		pj.setDataSituacaoCadastral(new Date());
//		pj.setCodSituacaoCadastral(SituacaoCadastralRFBEnum.ATIVA.getCodigoPJ());
//		pj.setDescSituacaoCadastral(SituacaoCadastralRFBEnum.ATIVA.getDescricao());
//		pj.setNomeEmpresarial("(ITX)BORGES PEREIRA S/C LTDA - COM MAIS DE 60 CARACTERES UTILIZADO NA ATUALIZAÇÃO DA ANOTAÇÃO");
//		
//		return pj;
//	}
//	
//	/**
//	 * Mock de uma pessoa jurídica com {@link SituacaoCadastralRFBEnum} inapta
//	 * @return {@link RetornoConsultaCNPJ}
//	 */
//	private Serializable getAnotacaoPJIrregular() {
//		RetornoConsultaCNPJ pj = new RetornoConsultaCNPJ();
//		pj.setDataSituacaoCadastral(new Date());
//		pj.setCodSituacaoCadastral(SituacaoCadastralRFBEnum.INAPTA.getCodigoPJ());
//		pj.setDescSituacaoCadastral(SituacaoCadastralRFBEnum.INAPTA.getDescricao());
//		pj.setNomeEmpresarial("(ITX)Boa Vista Telecomunicações LTDA EPP - COM MAIS DE 60 CARACTERES UTILIZADO NA ATUALIZAÇÃO DA ANOTAÇÃO");
//		
//		return pj;
//	}
//	
//	/**
//	 * Mock de uma pessoa jurídica com {@link SituacaoCadastralRFBEnum#SUSPENSA}
//	 * @return {@link RetornoConsultaCNPJ}
//	 */
//	private Serializable getAnotacaoPJSuspensaDoc() {
//		RetornoConsultaCNPJ pj = new RetornoConsultaCNPJ();
//		pj.setDataSituacaoCadastral(new Date());
//		pj.setCodSituacaoCadastral(SituacaoCadastralRFBEnum.SUSPENSA.getCodigoPJ());
//		pj.setDescSituacaoCadastral(SituacaoCadastralRFBEnum.SUSPENSA.getDescricao());
//		
//		return pj;
//	}
//	
//	/**
//	 * Mock de uma pessoa física com {@link SituacaoCadastralRFBEnum#PENDENTE_REGULARIZACAO} 
//	 * @return {@link RetornoConsultaCPF}
//	 */
//	private Serializable getAnotacaoPFPendenteDoc() {
//		RetornoConsultaCPF pf = new RetornoConsultaCPF();
//		pf.setDataAtualizacao(new Date());
//		pf.setCodSituacaoCadastral(SituacaoCadastralRFBEnum.PENDENTE_REGULARIZACAO.getCodigoPF());
//		pf.setDescSituacaoCadastral(SituacaoCadastralRFBEnum.PENDENTE_REGULARIZACAO.getDescricao());
//		
//		return pf;
//	}
//
//	/**
//	 * Mock de uma pessoa física com {@link SituacaoCadastralRFBEnum} regular
//	 * @return {@link RetornoConsultaCPF}
//	 */
//	private Serializable getAnotacaoPFNadaConsta() {
//		RetornoConsultaCPF pf = new RetornoConsultaCPF();
//		pf.setDataAtualizacao(new Date());
//		pf.setCodSituacaoCadastral(SituacaoCadastralRFBEnum.REGULAR.getCodigoPF());
//		pf.setDescSituacaoCadastral(SituacaoCadastralRFBEnum.REGULAR.getDescricao());
//		pf.setNome("(ITX)CARMINDO SILVA NETO - COM MAIS DE 60 CARACTERES UTILIZADO NA ATUALIZAÇÃO DA ANOTAÇÃO");
//		
//		return pf;
//	}
//	
//	/**
//	 * Mock de uma pessoa física com {@link SituacaoCadastralRFBEnum} pedente de regularização
//	 * @return {@link RetornoConsultaCPF}
//	 */
//	private Serializable getAnotacaoPFIrregular() {
//		RetornoConsultaCPF pf = new RetornoConsultaCPF();
//		pf.setDataAtualizacao(new Date());
//		pf.setCodSituacaoCadastral(SituacaoCadastralRFBEnum.PENDENTE_REGULARIZACAO.getCodigoPF());
//		pf.setDescSituacaoCadastral(SituacaoCadastralRFBEnum.PENDENTE_REGULARIZACAO.getDescricao());
//		pf.setNome("(ITX)ANIBAL RODRIGUES JUNIOR - COM MAIS DE 60 CARACTERES UTILIZADO NA ATUALIZAÇÃO DA ANOTAÇÃO");
//		
//		return pf;
//	}

	/**
	 * {@inheritDoc}
	 */
@Override
	protected String obterNomeDestino() { 
		return "QL.CONT.EXEC.CONS.EXT";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeFabrica() {
		return "/XAConnectionFactory";
	}
	
}
