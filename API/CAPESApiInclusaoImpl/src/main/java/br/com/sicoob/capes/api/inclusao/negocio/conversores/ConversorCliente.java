package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PerfilTarifarioDelegate;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;
import br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * O conversor para a entidade/dto de cliente.
 * 
 * @author Bruno.Carneiro
 */
public class ConversorCliente extends ConversorAbstrato<PessoaInstituicao, ClienteDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(PessoaInstituicao entidade, ClienteDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		entidade.setPessoa(obterPessoa(dto.getIdPessoa()));
		entidade.setFuncionario(obterFuncionario(dto.getCodigoFuncionario()));
		entidade.setNucleo(obterNucleo(dto.getCodigoNucleo()));
		PerfilTarifarioPK pkPerfilTarifario = new PerfilTarifarioPK();
		pkPerfilTarifario.setCodPerfilTarifario(dto.getCodigoPerfilTarifario());
		pkPerfilTarifario.setIdInstituicao(dto.getIdInstituicao());
		entidade.setPerfilTarifario(obterPerfilTarifario(pkPerfilTarifario));

		entidade.setDataEnquadramentoRisco(criarDateTimeDB(dto.getDataEnquadramentoRisco()));

		PessoaInstituicao pessoaInstituicao = obterFabricaCadastro().criarPessoaInstituicaoDelegate().obterPorPessoaInstituicaoSemValidacao(entidade);
		if (pessoaInstituicao != null) {
			entidade.setId(pessoaInstituicao.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(ClienteDTO dto, PessoaInstituicao entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
	}
	
	/**
	 * Obtém o perfil tarifário à partir dos parâmetros informados.
	 * 
	 * @param pk
	 * @return
	 * @throws BancoobException
	 */
	private PerfilTarifario obterPerfilTarifario(PerfilTarifarioPK pk) throws BancoobException {
		PerfilTarifarioDelegate perfilTarifarioDelegate = obterFabricaCadastro().criarPerfilTarifarioDelegate();
		return perfilTarifarioDelegate.obter(pk);
	}
	
	/**
	 * Obtém o funcionário à partir dos parâmetros informados.
	 * 
	 * @param idFuncionario
	 * @return
	 * @throws BancoobException
	 */
	private Funcionario obterFuncionario(Integer idFuncionario) throws BancoobException {
		FuncionarioDelegate funcionarioDelegate = obterFabricaCadastro().criarFuncionarioDelegate();
		return funcionarioDelegate.obter(idFuncionario);
	}
	
	/**
	 * Obtém o núcleo à partir dos parâmetros informados.
	 * 
	 * @param idNucleo
	 * @return
	 * @throws BancoobException
	 */
	private Nucleo obterNucleo(Integer idNucleo) throws BancoobException {
		NucleoDelegate nucleoDelegate = obterFabricaCadastro().criarNucleoDelegate();
		return nucleoDelegate.obter(idNucleo);
	}
}