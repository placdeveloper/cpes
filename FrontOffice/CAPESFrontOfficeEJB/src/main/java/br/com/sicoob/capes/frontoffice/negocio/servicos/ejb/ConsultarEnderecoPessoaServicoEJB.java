package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_COOP_REMETENTE;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_PESSOA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaEnderecoDTO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;

@Stateless
@Remote({ Transacao.class })
public class ConsultarEnderecoPessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(NUMERO_PESSOA.getRotulo(), parametros.get(NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(NUMERO_COOP_REMETENTE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.TIPO_PESSOA.getRotulo(), parametros.get(ParametroEnum.TIPO_PESSOA.getRotulo()).getValor()));
		return validadores;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, NUMERO_COOP_REMETENTE, Integer.class);
		Integer codigoTipoPessoa = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.TIPO_PESSOA, Integer.class);
		
		validarTipoPessoa(codigoTipoPessoa.shortValue());
		
		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa) ;
		
		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}
		
		validarTipoPessoa(pessoa.getCodTipoPessoa());
		
		EnderecoPessoaVO endereco = consultaEndereco(pessoa.getIdPessoa(), idInstituicao);
		if(endereco == null) {
			throw new NegocioException("Endereço não encontrado.");
		}
		
		LOCIntegracaoDelegate localidadeDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		LOCIntegracaoLocalidadeVO localidade = localidadeDelegate.obterLocalidade(endereco.getIdLocalidade());
		
		ConsultaEnderecoDTO dto = new ConsultaEnderecoDTO();
		dto.setIdEndereco(endereco.getIdEndereco());
		dto.setCodigoTipoEndereco(endereco.getCodigoTipoEndereco());
		dto.setCodigoTipoLogradouro(endereco.getIdTipoLogradouro().intValue());
		dto.setCodigoLocalidade(endereco.getIdLocalidade());
		dto.setCep(endereco.getCep());
		dto.setDescricao(endereco.getDescricao());
		dto.setNumero(endereco.getNumero());
		dto.setComplemento(endereco.getComplemento());
		dto.setBairro(endereco.getBairro());
		
		if (localidade != null) {
			dto.setNomeLocalidade(localidade.getNome());
			if (localidade.getUf() != null) {
				dto.setIdUf(localidade.getUf().getIdUF());
				dto.setSiglaUf(localidade.getUf().getSiglaUF());
			}
		}

		Resultado<ConsultaEnderecoDTO> resultado = new Resultado<ConsultaEnderecoDTO>();
		resultado.add(dto);
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}
	
	private void validarTipoPessoa(Short codigoTipoPessoa) throws BancoobException {
		if (TipoPessoaEnum.isPessoaJuridica(codigoTipoPessoa)) {
			throw new NegocioException("Transação indisponível para pessoa jurídica");
		}
	}
	
	private EnderecoPessoaVO consultaEndereco(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return CAPESApiFabricaDelegates.getInstance().criarEnderecoPessoaDelegate().obterEnderecoCorrespondenciaPorPessoaInstituicao(idPessoa, idInstituicao);
	}

}