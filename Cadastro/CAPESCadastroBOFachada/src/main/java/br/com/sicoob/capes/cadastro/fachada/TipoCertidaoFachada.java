/* 
 * Sicoob
 * TipoCertidaoFachada.java 
 * Criado em: 13/07/2011
 */
package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoCertidaoDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoCertidao;

/**
 * 13/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoCertidaoFachada extends CAPESCadastroBOCrudFachada<TipoCertidao> {

	/**
	 * Construtor
	 */
	public TipoCertidaoFachada() {
		super("tipoCertidao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoCertidaoDelegate obterDelegate() {
		return obterFabricaDelegates().criarTipoCertidaoDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoCertidao obterEntidade(RequisicaoReqDTO dto) {
		return (TipoCertidao) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		ConsultaDto<TipoCertidao> consultaDto = montarConsultaDto(dto, TipoCertidao.class);
		consultaDto.setOrdenacao("codigo");
		return montarResultado(obterDelegate().pesquisar(consultaDto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDefinicoes(dto);
		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		retorno.getDados().put("subTipos", fabrica.criarSubTipoCertidaoDelegate().listar());
		retorno.getDados().put("tiposObjeto", fabrica.criarTipoObjetoCertidaoDelegate().listar());
		retorno.getDados().put("tiposPrazo", fabrica.criarTipoPrazoCertidaoDelegate().listar());
		retorno.getDados().put("tiposAbrangencia", fabrica.criarTipoAbrangenciaCertidaoDelegate().listar());
		retorno.getDados().put("orgaosEmissores", fabrica.criarOrgaoEmissorCertidaoDelegate().listar());
		retorno.getDados().put("codigo", obterDelegate().pesquisarProximoCodigo());
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void preencherFiltroPesquisaComponente(TipoCertidao entidade, RequisicaoReqDTO dto) {
		Integer codigo = (Integer) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setCodigo(codigo != null ? codigo.shortValue() : null);
		entidade.setNome(descricao);
	}
	
	@Override
	protected ConsultaDto<?> realizarConsultaEntidadeComponente(TipoCertidao entidade, ConsultaDto<TipoCertidao> criterios) throws BancoobException {
		criterios.setTipoProcura("COMPONENTE_PESQUISA_CODIGO");
		ConsultaDto<?> resultado = obterDelegate().pesquisar(criterios);
		return resultado;
	}
	
}