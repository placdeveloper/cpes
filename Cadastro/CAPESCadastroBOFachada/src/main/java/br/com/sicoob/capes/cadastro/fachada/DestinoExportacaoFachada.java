package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.DestinoExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoInformacaoDelegate;
import br.com.sicoob.capes.comum.negocio.enums.CodificacaoArquivoEnum;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * A Classe DestinoExportacaoFachada.
 */
@RemoteService
public class DestinoExportacaoFachada extends CAPESCadastroBOCrudFachada<DestinoExportacao> {

	/** O atributo delegate. */
	private DestinoExportacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarDestinoExportacaoDelegate();

	/**
	 * Instancia um novo DestinoExportacaoFachada.
	 */
	public DestinoExportacaoFachada() {
		super("destinoExportacao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DestinoExportacao obterEntidade(RequisicaoReqDTO dto) {
		return (DestinoExportacao) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DestinoExportacaoDelegate obterDelegate() {
		return delegate;
	}
	
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ConsultaDto<TipoInformacao> criterios = new ConsultaDto<TipoInformacao>();
		TipoInformacao filtro = new TipoInformacao();
		filtro.setInformacaoExportacao(Boolean.TRUE);
		criterios.setFiltro(filtro);
		
		TipoInformacaoDelegate tipoInformacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoInformacaoDelegate();
		ConsultaDto<TipoInformacao> consulta = tipoInformacaoDelegate.pesquisar(criterios);
		
		retorno.getDados().put("tiposInformacao", consulta.getResultado());
		retorno.getDados().put("listaTiposCodificacao", listarTiposCodificacao());
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		DestinoExportacao entidade = obterEntidade(dto);
		obterDelegate().excluir(entidade.getCodigo());
		return obterMapRetorno(this.chaveMapa, entidade);
	}
	
	/**
	 * Obtém os tipos de codificação de arquivos.
	 */
	private CodificacaoArquivoEnum[] listarTiposCodificacao() {
		return CodificacaoArquivoEnum.values();
	}

}