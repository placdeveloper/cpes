/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral;

import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemImovel;
import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.Mensagem;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.legado.Produtividade;
import br.com.sicoob.capes.negocio.entidades.legado.Produtor;
import br.com.sicoob.capes.negocio.entidades.legado.Referencia;
import br.com.sicoob.capes.negocio.entidades.legado.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Telefone;
import br.com.sicoob.capes.negocio.entidades.legado.historico.HistoricoAlteracaoCnpjCpf;
import br.com.sicoob.capes.negocio.entidades.legado.interfaces.Replicavel;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * Fábrica de delegates para atualização.
 * 
 * @author Erico.Junior
 */
public class FabricaAtualizacaoDelegates {

	/** Instancia do Fabrica de Delegates. */
	public static FabricaAtualizacaoDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static FabricaAtualizacaoDelegates getInstance() {
		if (fabrica == null) {
			synchronized (FabricaAtualizacaoDelegates.class) {
				if (fabrica == null) {
					fabrica = new FabricaAtualizacaoDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Obter delegate.
	 *
	 * @param classe o valor de classe
	 * @return EntidadeReplicavelDelegate
	 */
	@SuppressWarnings("rawtypes")
	public EntidadeReplicavelDelegate obterDelegate(Class<? extends Replicavel> classe) {

		EntidadeReplicavelDelegate<?, ?> delegate = null;
		CAPESReplicacaoComumFabricaDelegates fabrica = CAPESReplicacaoComumFabricaDelegates
				.getInstance();

		if (Referencia.class.equals(classe)) {
			delegate = fabrica.criarReferenciaDelegate();
		} else if (FonteRenda.class.equals(classe)) {
			delegate = fabrica.criarFonteRendaDelegate();
		} else if (BemImovel.class.equals(classe)) {
			delegate = fabrica.criarBemImovelDelegate();
		} else if (Bem.class.equals(classe)) {
			delegate = fabrica.criarBemDelegate();
		} else if (Produtor.class.equals(classe)) {
			delegate = fabrica.criarProdutorDelegate();
		} else if (HistoricoAlteracaoCnpjCpf.class.equals(classe)) {
			delegate = fabrica.criarHistoricoAlteracaoCnpjCpfDelegate();
		} else if (BemOnus.class.equals(classe)) {
			delegate = fabrica.criarBemOnusDelegate();
		} else if (BemPosse.class.equals(classe)) {
			delegate = fabrica.criarBemPosseDelegate();
		} else if (BemRegistro.class.equals(classe)) {
			delegate = fabrica.criarBemRegistroDelegate();
		} else if (Endereco.class.equals(classe)) {
			delegate = fabrica.criarEnderecoDelegate();
		} else if (Mensagem.class.equals(classe)) {
			delegate = fabrica.criarMensagemDelegate();
		} else if (Telefone.class.equals(classe)) {
			delegate = fabrica.criarTelefoneDelegate();
		} else if (Certidao.class.equals(classe)) {
			delegate = fabrica.criarCertidaoDelegate();
		} else if (Cliente.class.equals(classe)) {
			delegate = fabrica.criarClienteDelegate();
		} else if (PessoaFisica.class.equals(classe) || PessoaJuridica.class.equals(classe)) {
			delegate = fabrica.criarPessoaDelegate();
		} else if (RelacionamentoPessoa.class.equals(classe)) {
			delegate = fabrica.criarRelacionamentoPessoaDelegate();
		} else if (Produtor.class.equals(classe)) {
			delegate = fabrica.criarProdutorDelegate();
		} else if (Produtividade.class.equals(classe)) {
			delegate = fabrica.criarProdutividadeDelegate();
		} else if (AvaliacaoFinanceira.class.equals(classe)) {
			delegate = fabrica.criarAvaliacaoFinanceiraDelegate();
		}
		
		return delegate;
	}
}
