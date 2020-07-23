/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.sicoob.capes.cadastro.negocio.dto.AtualizacaoDataSFNDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.AvalFinanceiraDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Fábrica de conversores de entidades para replicação.
 * 
 * @author Erico.Junior
 */
public class FabricaConversoresReplicacao<R extends EntidadeReplicavel<?>, 
		C extends br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel> {

	/** O atributo instance. */
	private static FabricaConversoresReplicacao<EntidadeReplicavel<?>,
			br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel> instance;

	/**
	 * Cria a fábrica de comandos
	 * 
	 * @return a fábrica de comandos
	 */
	public static FabricaConversoresReplicacao<EntidadeReplicavel<?>, 
			br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel> getInstance() {
		if (instance == null) {
			synchronized (FabricaConversoresReplicacao.class) {
				if (instance == null) {
					instance = new FabricaConversoresReplicacao<EntidadeReplicavel<?>, 
							br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel>();
				}
			}
		}
		return instance;
	}

	/**
	 * Recupera o conversor das entidades.
	 * 
	 * @param dto
	 *            O dto com os dados da replicação.
	 * @return O conversor das entidades.
	 */
	@SuppressWarnings("unchecked")
	public Conversor<R, C> obterConversor(C replicacao) {

		Conversor<?, ?> conversor = null;
		Class<? extends br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel> 
				classeEntidade = replicacao.getClass();
		
		if (Referencia.class.isAssignableFrom(classeEntidade)) {
			conversor = new ConversorReferencia();
		} else if (FonteRenda.class.equals(classeEntidade)) {
			conversor = new ConversorFonteRenda();
		} else if (HistoricoAlteracaoCpfCnpj.class.equals(classeEntidade)) {
			conversor = new ConversorHistoricoAlteracaoCnpjCpf();
		} else if (BemImovel.class.equals(classeEntidade)) {
			conversor = new ConversorBemImovel();
		} else if (Bem.class.equals(classeEntidade)) {
			conversor = new ConversorBem();
		} else if (BemOnus.class.equals(classeEntidade)) {
			conversor = new ConversorBemOnus();
		} else if (BemPosse.class.equals(classeEntidade)) {
			conversor = new ConversorBemPosse();
		} else if (BemRegistro.class.equals(classeEntidade)) {
			conversor = new ConversorBemRegistro();
		} else if (Mensagem.class.equals(classeEntidade)) {
			conversor = new ConversorMensagem();
		} else if (Endereco.class.equals(classeEntidade)) {
			conversor = new ConversorEndereco();
		} else if (Telefone.class.equals(classeEntidade)) {
			conversor = new ConversorTelefone();
		} else if (Certidao.class.equals(classeEntidade)) {
			conversor = new ConversorCertidao();
		} else if (Tributacao.class.equals(classeEntidade)){
			conversor = new ConversorCliente();
		} else if (PessoaInstituicao.class.equals(classeEntidade)){
			conversor = new ConversorPessoaInstituicao();
		} else if (PessoaFisica.class.equals(classeEntidade)){
			conversor = new ConversorPessoaFisica();
		} else if (PessoaJuridica.class.equals(classeEntidade)){
			conversor = new ConversorPessoaJuridica();
		} else if (Anotacao.class.equals(classeEntidade)) {
			conversor = new ConversorAnotacao();
		} else if (RelacionamentoPessoa.class.equals(classeEntidade)
				|| RegistroRelacionamento.class.equals(classeEntidade)) {
			conversor = new ConversorRelacionamentoPessoa();
		} else if(EnderecoCorrespondencia.class.equals(classeEntidade)) {
			conversor = new ConversorEnderecoCorrespondencia();
		} else if (Produtor.class.equals(classeEntidade)){
			conversor = new ConversorProdutor();
		} else if (Produtividade.class.equals(classeEntidade)) {
			conversor = new ConversorProdutividade();
		} else if (Email.class.equals(classeEntidade)) {
			conversor = new ConversorEmail();
		} else if (AtualizacaoDataSFNDTO.class.equals(classeEntidade)) {
			conversor = new ConversorDataSFN();
		} else if (AvalFinanceiraDTO.class.equals(classeEntidade)) {
			conversor = new ConversorAvalFinanceira();
		}
		return (Conversor<R, C>) conversor;
	}
}