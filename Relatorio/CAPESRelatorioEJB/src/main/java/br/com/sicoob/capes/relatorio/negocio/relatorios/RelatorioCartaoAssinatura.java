package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * A Classe RelatorioCartaoAssinatura.
 */
public class RelatorioCartaoAssinatura extends RelatorioCapes {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5659748875184156299L;
	
	/** A constante ARQUIVO. */
	private static final String ARQUIVO = "FichaCadastral_CartaoAssinatura_NOVO.jasper";
	
	/** O atributo dados. */
	private List<PessoaCompartilhamento> dados;


	/**
	 * Instancia um novo RelatorioCartaoAssinatura.
	 *
	 * @param pessoas o valor de pessoas
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RelatorioCartaoAssinatura(Short codigoTipoPessoa, String nomeCompleto, String cpfCnpj, Boolean emBranco) {
		super(ARQUIVO);
		
		Map parametros = getParametros();
		
		parametros.put("nome", nomeCompleto);
		parametros.put("cpfCnpj", cpfCnpj);
		parametros.put("codPessoa", codigoTipoPessoa);
		parametros.put("emBranco", emBranco);
		
		this.dados = Arrays.asList(new PessoaFisica());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JRBeanCollectionDataSource(this.dados);
	}

}
