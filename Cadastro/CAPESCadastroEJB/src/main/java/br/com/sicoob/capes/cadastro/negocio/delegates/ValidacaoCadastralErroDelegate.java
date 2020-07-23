package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.ValidacaoCadastralErroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro;

/**
 * A Classe ValidacaoCadastralErroDelegate.
 */
public class ValidacaoCadastralErroDelegate extends CAPESCadastroCrudDelegate<ValidacaoCadastralErro, ValidacaoCadastralErroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralErroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarValidacaoCadastralErroServico();
	}

	/**
	 * Listar falhas regras validacao cadastral.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoRegraEnum o valor de tipo regra enum
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegraEnum) throws BancoobException {
		return getServico().listarFalhasRegrasValidacaoCadastral(idPessoaCompartilhamento, idInstituicao, tipoRegraEnum);
	}
	
	/**
	 * Listar falhas regras validacao cadastral baseado no perfil.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoRegraEnum o valor de tipo regra enum
	 * @param codPerfilCadastro o valor do perfil cadastrado
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastralPerfilCadastro(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegraEnum, Short codPerfilCadastro) throws BancoobException {
		return getServico().listarFalhasRegrasValidacaoCadastral(idPessoaCompartilhamento, idInstituicao, tipoRegraEnum, codPerfilCadastro);
	}
	
	
	public boolean isPossuiRegraCadastralRestritiva(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		return getServico().isPossuiRegraCadastralRestritiva(idPessoaCompartilhamento, idInstituicao);
	}


	/**
	 * O método Deletar erros pessoa.
	 *
	 * @param idPessoaCompartilhamento o valor de id pessoa compartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void deletarErrosPessoa(Long idPessoaCompartilhamento) throws BancoobException {
		getServico().deletarErrosPessoa(idPessoaCompartilhamento);
	}
	
	/**
	 * Deletar os erros gerados por uma determinada regra.
	 * 
	 * @param codigoRegra
	 *            o codigo da regra que gerou os erros
	 * @throws BancoobException
	 */
    public void deletarErrosRegra(Short codigoRegra) throws BancoobException {
		getServico().deletarErrosRegra(codigoRegra);
	}

	/**
	 * Deletar os erros de pessoas "validaveis". Ou seja, aquelas pessoas que
	 * nunca foram validadas ou que tenham sido atualizadas depois da ultima
	 * validacao.
	 * 
	 * @param codigoRegra
	 *            o codigo da regra
	 * @param dataValidacao
	 *            a data da validacao
	 * 
	 * @throws BancoobException
	 */
	public void deletarErros(Short codigoRegra, DateTimeDB dataValidacao) throws BancoobException {
	    getServico().deletarErros(codigoRegra, dataValidacao);
    }

	/**
	 * Verifica se existem erros cadastrados para a regra recebida como parametro
	 * 
	 * @param codigoRegra
	 *            o codigo da regra que se deseja verificar a exsistencia de erros
	 * @return {@code true} se existerem erros
	 */
	public boolean isExisteErro(Short codigoRegra) throws BancoobException {
	    return getServico().isExisteErro(codigoRegra);
    }

}