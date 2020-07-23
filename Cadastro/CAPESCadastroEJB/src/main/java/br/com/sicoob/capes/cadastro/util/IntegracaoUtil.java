package br.com.sicoob.capes.cadastro.util;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.UF;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe IntegracaoUtil.
 */
public final class IntegracaoUtil {

	/**
	 * Instancia um novo IntegracaoUtil.
	 */
	private IntegracaoUtil() {

	}

	/**
	 * Converter localidade.
	 *
	 * @param vo o valor de vo
	 * @return Localidade
	 */
	public static Localidade converterLocalidade(LOCIntegracaoLocalidadeVO vo) {

		Localidade localidade = null;
		if (vo != null) {
			localidade = new Localidade();
			localidade.setCodigoIBGE(vo.getCodigoIBGE());
			localidade.setIdLocalidade(vo.getIdLocalidade());
			localidade.setNome(vo.getNome());
			localidade.setNomeLimpo(vo.getNomeLimpo());
			localidade.setNumeroCep(vo.getNumeroCep());
			if (vo.getUf() != null) {
				localidade.setUf(new UF());
				localidade.getUf().setIdUF(vo.getUf().getIdUF());
				localidade.getUf().setNomeUF(vo.getUf().getNomeUF());
				localidade.getUf().setSiglaUF(vo.getUf().getSiglaUF());
			}
		}
		return localidade;
	}

	/**
	 * Criar gft integracao dto.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @return GFTIntegracaoDTO
	 */
	public static GFTIntegracaoDTO criarGFTIntegracaoDTO(Autorizacao autorizacao) {

		GFTIntegracaoDTO dados = new GFTIntegracaoDTO();
		dados.setIdAutorizacao(autorizacao.getIdAutorizacao());
		dados.setSiglaProcesso(autorizacao.getSiglaProcesso());
		dados.setIdRegistroControlado(autorizacao.getIdRegistroControlado());
		dados.setIdInstituicaoOrigem(autorizacao.getInstituicaoOrigem().getIdInstituicao());
		dados.setIdUnidadeInstOrigem(autorizacao.getInstituicaoOrigem().getIdUnidadeInst());
		dados.setIdInstituicaoDestino(autorizacao.getInstituicaoDestino().getIdInstituicao());
		dados.setIdUnidadeInstDestino(autorizacao.getInstituicaoDestino().getIdUnidadeInst());
		return dados;
	}

	/**
	 * Criar ged integracao dto.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @return GEDIntegracaoDTO
	 */
	public static GEDIntegracaoDTO criarGEDIntegracaoDTO(Autorizacao autorizacao) {

		GEDIntegracaoDTO dadosGED = new GEDIntegracaoDTO();

		Pessoa pessoa = autorizacao.getPessoa();
		PessoaCompartilhamento pessoaCompartilhamento = pessoa.getPessoaCompartilhamento();
		Instituicao instituicao = autorizacao.getInstituicaoDestino();

		dadosGED.setIdPessoa(pessoa.getIdPessoa());
		dadosGED.setCpfCnpj(pessoa.getCpfCnpj());
		dadosGED.setCodTipoPessoa(pessoa.getTipoPessoa().getCodTipoPessoa());
		dadosGED.setCodCompartilhamentoCadastro(pessoaCompartilhamento.getCodCompartilhamentoCadastro());
		dadosGED.setIdRegistroControlado(autorizacao.getIdRegistroControlado());
		dadosGED.setIdInstituicaoDestino(instituicao.getIdInstituicao());
		dadosGED.setIdUnidadeInstDestino(instituicao.getIdUnidadeInst());
		for (AutorizacaoDocumento autorizacaoDocumento : autorizacao.getDocumentos()) {
			DocumentoComprobatorio documento = autorizacaoDocumento.getDocumento();
			if (documento != null) {
				dadosGED.addIdDocumento(documento.getIdDocumento());
			}
		}
		return dadosGED;
	}

	/**
	 * Converter uf.
	 *
	 * @param vo o valor de vo
	 * @return UF
	 */
	public static UF converterUF(LOCIntegracaoUFVO vo) {

		UF unidade = new UF();
		unidade.setIdUF(vo.getIdUF());
		unidade.setNomeUF(vo.getNomeUF());
		unidade.setSiglaUF(vo.getSiglaUF());
		return unidade;
	}

	/**
	 * Converter tipo logradouro.
	 *
	 * @param vo o valor de vo
	 * @return TipoLogradouro
	 */
	public static TipoLogradouro converterTipoLogradouro(LOCIntegracaoTipoLogradouroVO vo) {

		return new TipoLogradouro(vo.getIdTipoLogradouro(), vo.getNome());
	}

	/**
	 * Converter tipos logradouro.
	 *
	 * @param vos o valor de vos
	 * @return List
	 */
	public static List<TipoLogradouro> converterTiposLogradouro(List<LOCIntegracaoTipoLogradouroVO> vos) {

		List<TipoLogradouro> tipos = new ArrayList<TipoLogradouro>();
		for (LOCIntegracaoTipoLogradouroVO vo : vos) {
			tipos.add(converterTipoLogradouro(vo));
		}
		return tipos;
	}
}
