package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioValidacaoCadastralDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.capes.relatorio.negocio.enums.TipoRelatorioEnum;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralSinteticoVO;

/**
 * @author Bruno.Carneiro
 */
public class RelatorioValidacaoCadastralSintetico extends RelatorioValidacaoCadastral {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8743320282227289124L;


	/**
	 * Instancia um novo RelatorioValidacaoCadastralSintetico.
	 *
	 * @param filtro o valor de filtro
	 * @param dados o valor de dados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelatorioValidacaoCadastralSintetico(RelatorioValidacaoCadastralDTO filtro,
	        List<RelatorioValidacaoCadastralSinteticoVO> dados) throws BancoobException {
		
		super(OrientacaoPaginaEnum.PAISAGEM, dados);
		getParametros().put("FILTRO", filtro);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getIdRelatorio() {
	    return "CLI002";
    }

	/** 
     * {@inheritDoc}
     */
    @Override
    protected TipoRelatorioEnum getTipoRelatorio() {
	    return TipoRelatorioEnum.SINTETICO;
    }

}