package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * A Classe ConversorEmail.
 */
public class ConversorEmail extends Conversor<Pessoa, Email> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pessoa obterEntidadeReplicacao(AtualizacaoCadastralDTO<Email> dto)
			throws BancoobException {

		Email email = dto.getEntidadeCadastro();
		String tipoPessoa = email.getPessoaCompartilhamento().getClass().getSimpleName();
		getLogger().debug("Convertendo e-mail: ", tipoPessoa, " = ",
				String.valueOf(dto.getIdPessoaLegado()), ", idInstituicao=",
				String.valueOf(dto.getInstituicao().getIdInstituicao()));
		
		Pessoa pessoa = obterPessoa(dto);
		pessoa.setEmail(email.getDescricao());
		return pessoa;
	}

}
