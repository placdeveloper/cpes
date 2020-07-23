/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;


import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.CertidaoDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CertidaoPK;

/**
 * Conversor de certidao do Cadastro único em mensagem da replicação.
 * 
 * @author juan.damasceno
 */
public class ConversorCertidao extends
		ConversorAbstract<Certidao, br.com.sicoob.capes.negocio.entidades.vigente.Certidao> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Certidao instanciarEntidade() {
		return new Certidao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Certidao converter(
			br.com.sicoob.capes.negocio.entidades.vigente.Certidao entidade,
			Certidao certidao, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {
		
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);
		
		certidao.setDataCadastro(entidade.getDataHoraInicio());
		certidao.setDataEmissao(entidade.getDataEmissao());
		certidao.setDataVencimentoDocumento(entidade.getDataVencimento());
		certidao.setIdCertidaoDB2(entidade.getIdCertidao());
		certidao.setIdTipoCertidao(entidade.getTipoCertidao().getCodigo());
		certidao.setNumero(entidade.getNumero());
		certidao.setObservacao(entidade.getObservacao());
		
		CertidaoPK pk = certidao.getCertidaoPK();
		
		if(pk == null || pk.getSeqCertidao() == null) {
			pk = new CertidaoPK();
			pk.setPessoa(pessoa);
			pk.setSeqCertidao(obterDelegate().obterMaxSequencialPorPessoa(pessoa));
			certidao.setCertidaoPK(pk);
		}
		
		return certidao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoDelegate obterDelegate() {
		return obterFabricaDelegates().criarCertidaoDelegate();
	}
}
