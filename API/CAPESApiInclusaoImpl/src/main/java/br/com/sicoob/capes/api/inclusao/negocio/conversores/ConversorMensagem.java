package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.MensagemDTO;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.TipoDestinoExibicao;
import br.com.sicoob.capes.negocio.entidades.TipoMensagem;

/**
 * A Classe ConversorMensagem.
 * 
 * @author bruno.carneiro
 */
public class ConversorMensagem extends ConversorAbstrato<Mensagem, MensagemDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Mensagem entidade, MensagemDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoMensagem tipoMensagem = new TipoMensagem();
		tipoMensagem.setCodTipoMensagem(dto.getCodigoTipoMensagem());
		entidade.setTipoMensagem(tipoMensagem);

		TipoDestinoExibicao tipoDestinoExibicao = new TipoDestinoExibicao();
		tipoDestinoExibicao.setCodTipoDestinoExibicao(dto.getCodigoTipoDestinoExibicao());
		entidade.setTipoDestinoExibicao(tipoDestinoExibicao);
		
		entidade.setValidade(criarDateTimeDB(dto.getDataValidade()));
		entidade.setPessoa(obterPessoa(dto.getIdPessoa()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(MensagemDTO dto, Mensagem entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
	}

}