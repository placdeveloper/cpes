package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.ExportacaoDAO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class ExportacaoDAOImpl extends CAPESCadastroCrudDao<Exportacao> implements ExportacaoDAO {

	/**
	 * Construtor
	 * 
	 * @param classe
	 * @param nomeComandoPesquisar
	 */
	public ExportacaoDAOImpl() {
		super(Exportacao.class, "PESQUISA_EXPORTACAO_POR_FILTRO");
	}

	/**
	 * {@inheritDoc}
	 */
	public Short obterNumeroSequencial(DestinoExportacao destino) throws BancoobException {
		Comando comando = null;
		Integer id = null;
		try {
			comando = getComando("OBTER_NUMERO_SEQUENCIAL_EXPORTACAO");
			comando.adicionarVariavel("destinoExportacao", destino.getCodigo());
			comando.configurar();
			id = (Integer) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}

		if (id == null) {
			id = NumberUtils.INTEGER_ONE;
		}

		return id.shortValue();
	}
}