package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoNegocioException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoRuntimeException;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.EnderecoServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.EnderecoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * EJB contendo servicos relacionados a Endereco.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(EnderecoServicoLocal.class)
@Remote(EnderecoServicoRemote.class)
public class EnderecoServicoEJB extends CAPESApiInclusaoServicoEJB<EnderecoDTO, Endereco> implements EnderecoServicoLocal, EnderecoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoEndereco", "codigoTipoLogradouro", "codigoLocalidade", "cep", "bairro", "descricao" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idEndereco");
		return lista.toArray(new String[lista.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void tornarPadraoCorrespondencia(Long idEndereco, Integer idInstituicao) throws BancoobException {
		getLogger().debug("Tornando o endereco de ID=", String.valueOf(idEndereco), " como correspondencia da instituicao=", String.valueOf(idInstituicao));
		try {
			Endereco endereco = new Endereco();
			endereco.setIdEndereco(idEndereco);

			Instituicao instituicao = new Instituicao();
			instituicao.setIdInstituicao(idInstituicao);

			EnderecoDelegate enderecoDelegate = obterFabricaCadastro().criarEnderecoDelegate();
			enderecoDelegate.tornarPadraoCorrespondencia(endereco, instituicao);
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusão] Erro ao incluir: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusão] Erro ao incluir");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusão] Erro ao incluir");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD
			getLogger().erro(e, "[CAPES Api Inclusão] Erro ao incluir");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
}