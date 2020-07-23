/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoCorrespondenciaDelegate;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * @author Erico.Junior
 * 
 */
public class ConversorEnderecoCorrespondencia extends Conversor<Endereco, EnderecoCorrespondencia> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Endereco obterEntidadeReplicacao(AtualizacaoCadastralDTO<EnderecoCorrespondencia> dto) throws BancoobException {
		EnderecoCorrespondencia entidade = dto.getEntidadeCadastro();
		EnderecoCorrespondenciaDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarEnderecoCorrespondenciaDelegate();
		EnderecoCorrespondencia enderecoCorrespondencia = delegate.consultar(entidade.getPessoaCompartilhamento(), obterInstituicao(entidade.getIdInstituicao()));
		
		// Replicação
		Pessoa pessoa = obterPessoa(dto);

		Endereco endereco = new Endereco();
		endereco.setIdEnderecoPessoaDB2(enderecoCorrespondencia.getEndereco().getIdEndereco());
		endereco.setPessoa(pessoa);
		return endereco;
	}

	/**
	 * Obter instituicao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Instituicao
	 */
	private Instituicao obterInstituicao(Integer idInstituicao) {
		Instituicao retorno = new Instituicao();
		retorno.setIdInstituicao(idInstituicao);
		return retorno;
	}

}