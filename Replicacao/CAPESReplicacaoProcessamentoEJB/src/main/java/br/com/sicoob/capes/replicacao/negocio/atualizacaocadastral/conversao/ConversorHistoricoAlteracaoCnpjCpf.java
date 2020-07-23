/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.historico.HistoricoAlteracaoCnpjCpf;
import br.com.sicoob.capes.negocio.entidades.legado.pk.HistoricoAlteracaoCnpjCpfPK;

/**
 * Converte o histórico de alteração de cpf/cnpj do cuc para o legado.
 * 
 * @author Erico.Junior
 */
public class ConversorHistoricoAlteracaoCnpjCpf extends
		Conversor<HistoricoAlteracaoCnpjCpf, HistoricoAlteracaoCpfCnpj> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HistoricoAlteracaoCnpjCpf obterEntidadeReplicacao(
			AtualizacaoCadastralDTO<HistoricoAlteracaoCpfCnpj> dto) throws BancoobException {

		HistoricoAlteracaoCpfCnpj entidade = dto.getEntidadeCadastro();
		
		// Replicação
		Pessoa pessoa = obterPessoa(dto);
		String cpfCnpjAnterior = pessoa.getNumeroCgcCpf();
		String cpfCnpjNovo = entidade.getCpfCnpjNovo();
		pessoa.setNumeroCgcCpf(cpfCnpjNovo);
		
		HistoricoAlteracaoCnpjCpfPK pk = new HistoricoAlteracaoCnpjCpfPK();
		pk.setCpfCnpj(cpfCnpjAnterior);
		pk.setPessoa(pessoa);
		pk.setDataAlteracao(entidade.getDataHoraAlteracao());
		
		HistoricoAlteracaoCnpjCpf historico = new HistoricoAlteracaoCnpjCpf();
		historico.setDescMotivo(entidade.getMotivo());
		historico.setIdUsuario(entidade.getIdUsuario());
		historico.setNomeSolicitante(entidade.getNomeSolicitante());
		historico.setPk(pk);

		return historico;
	}
	

	

}
