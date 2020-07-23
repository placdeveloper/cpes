package br.com.sicoob.capes.cadastro.fachada;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.DestinoExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.VisualizarExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;

/**
 * A Classe VisualizarExportacaoFachada.
 */
public class VisualizarExportacaoFachada extends CAPESCadastroBOFachada {

	/** O atributo destinoExportacaoDelegate. */
	private transient DestinoExportacaoDelegate destinoExportacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarDestinoExportacaoDelegate();

	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("listaDestinos", destinoExportacaoDelegate.listar());
		return retorno;
	}

	/**
	 * Obter arquivo exportacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterArquivoExportacao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		DestinoExportacao destino = (DestinoExportacao) dto.getDados().get("destino");
		DateTimeDB dataMovimento = new DateTimeDB(((Date) dto.getDados().get("dataMovimento")).getTime());

		VisualizarExportacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarVisualizarExportacaoDelegate();
		ArquivoExportacaoVO arquivo = delegate.obterArquivoExportacao(destino, dataMovimento);

		retorno.getDados().put("arquivo", arquivo);

		return retorno;
	}
}