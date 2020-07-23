package br.com.sicoob.capes.cadastro.fachada;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.NacionalidadeDelegate;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;

/**
 * A Classe NacionalidadeFachada.
 */
@RemoteService
public class NacionalidadeFachada extends CAPESCadastroBOCrudDominioFachada<Nacionalidade> {

	/**
	 * Construtor
	 */
	public NacionalidadeFachada() {
		super("nacionalidade");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NacionalidadeDelegate obterDelegate() {
		return this.obterFabricaDelegates().criarNacionalidadeDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Nacionalidade obterEntidade(RequisicaoReqDTO dto) {
		return (Nacionalidade) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("codigo", obterDelegate().pesquisarProximoCodigo());
		return retorno;
	}
	
	/**
	 * Pesquisar.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO pesquisar(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ConsultaDto<Nacionalidade> criterios = new ConsultaDto<Nacionalidade>();
		Nacionalidade nacionalidade = new Nacionalidade();
		nacionalidade.setDescricao(dto.getDados().get("descricao").toString());
		criterios.setFiltro(nacionalidade);
		ConsultaDto<Nacionalidade> resultado = obterDelegate().pesquisar(criterios);
		
		retorno.getDados().put("lista", resultado.getResultado());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherFiltroPesquisaComponente(Nacionalidade entidade, RequisicaoReqDTO dto) {
		Integer codigo = (Integer) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setCodigo(codigo != null ? codigo.shortValue() : null);
		entidade.setDescricao(descricao);
	}
}