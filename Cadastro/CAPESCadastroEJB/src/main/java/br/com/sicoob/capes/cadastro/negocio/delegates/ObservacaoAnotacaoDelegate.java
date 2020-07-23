package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.ObservacaoAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.ObservacaoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao;

/**
 * A Classe ObservacaoAnotacaoDelegate.
 */
public class ObservacaoAnotacaoDelegate extends CAPESCadastroDelegate<ObservacaoAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ObservacaoAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarObservacaoAnotacaoServico();
	}
	
	/**
	 * Consultar observacao anotacao.
	 *
	 * @param codTipoObservacaoAnotacao o valor de cod tipo observacao anotacao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ObservacaoAnotacao> consultarObservacaoAnotacao(Short codTipoObservacaoAnotacao) throws BancoobException {
		ConsultaDto<ObservacaoAnotacao> consultaDto = new ConsultaDto<ObservacaoAnotacao>();
		ObservacaoAnotacao filtro = new ObservacaoAnotacao();
		TipoObservacaoAnotacao tipoObservacaoAnotacao = new TipoObservacaoAnotacao();
		tipoObservacaoAnotacao.setCodigo(codTipoObservacaoAnotacao);
		filtro.setTipoObservacaoAnotacao(tipoObservacaoAnotacao);
		consultaDto.setFiltro(filtro);
		return getServico().listar(consultaDto);
	}

}
