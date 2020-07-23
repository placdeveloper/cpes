/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPK;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 *
 */
public class AtualizacaoCooperativaPessoaFachada {

	/** O atributo delegate. */
	private transient PessoaDelegate delegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate();
 
	/**
	 * O método Atualizar cooperativa pessoa.
	 *
	 * @param transicaoBancoob o valor de transicao bancoob
	 * @param transicoes o valor de transicoes
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void atualizarCooperativaPessoa(TransicaoPessoa transicaoBancoob, List<TransicaoPessoa> transicoes) 
			throws BancoobException {

		Integer numPessoaBancoob = transicaoBancoob.getIdPessoaLegado();	
		List<Cooperativa> cooperativas = new ArrayList<Cooperativa>();

		if(transicoes != null) {
			Cooperativa cooperativa = null;
			for (TransicaoPessoa transicao : transicoes) {
				cooperativa = criarCooperativa(transicao.getInstituicao());
				cooperativas.add(cooperativa);
			}
		}
		
		SicoobLoggerPadrao.getInstance(getClass()).info("ATUALIZANDO AS COOPERATIVAS DA PESSOA: ", String.valueOf(numPessoaBancoob), " Cooperativas: ", String.valueOf(cooperativas));
		
		delegate.atualizarCooperativasPessoa(numPessoaBancoob, cooperativas);
	}
	
	/**
	 * Criar cooperativa.
	 *
	 * @param instituicao o valor de instituicao
	 * @return Cooperativa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Cooperativa criarCooperativa(Instituicao instituicao) throws BancoobException {
		
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		
		Integer idInstituicao = instituicao.getIdInstituicao();
		Integer idUnidadeInst = instituicao.getIdUnidadeInst();
		Integer numCooperativa = sciDelegate.obterNumeroCooperativa(idInstituicao);
		
		CooperativaPK pk = new CooperativaPK();
		pk.setNumCooperativa(numCooperativa);
		pk.setNumPac(idUnidadeInst);
	
		Cooperativa cooperativa = new Cooperativa();
		cooperativa.setId(pk);
		cooperativa.setIdInstituicao(idInstituicao);
		cooperativa.setIdUnidadeInst(idUnidadeInst);
		return cooperativa;
	}
	
}