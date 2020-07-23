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
import br.com.sicoob.capes.api.inclusao.negocio.delegates.CAPESApiInclusaoFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaTelefonePessoaDTO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

@Stateless
@Remote({ Transacao.class })
public class CriarTelefonePessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_PESSOA.getRotulo(), parametros.get(ParametroEnum.NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.CODIGO_TIPO_TELEFONE.getRotulo(), parametros.get(ParametroEnum.CODIGO_TIPO_TELEFONE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.DDD.getRotulo(), parametros.get(ParametroEnum.DDD.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.TELEFONE.getRotulo(), parametros.get(ParametroEnum.TELEFONE.getRotulo()).getValor()));
		return validadores;
	}
	
	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		TelefoneDTO telefone = obterTelefone(mensagem, parametros);
		telefone = incluirTelefone(telefone);
		
		Resultado<ConsultaTelefonePessoaDTO> resultado = new Resultado<ConsultaTelefonePessoaDTO>();
		if (telefone.getIdTelefonePessoa() != null) {
			ConsultaTelefonePessoaDTO dto = new ConsultaTelefonePessoaDTO();
			dto.setIdTelefonePessoa(telefone.getIdTelefonePessoa());
			resultado.add(dto);
		}
		
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}

	private TelefoneDTO incluirTelefone(TelefoneDTO dto) throws BancoobException {
		return CAPESApiInclusaoFabricaDelegates.obterInstancia().criarTelefoneDelegate().incluir(dto);
	}

	private TelefoneDTO obterTelefone(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, NUMERO_COOP_REMETENTE, Integer.class);
		Short codigoTipoTelefone = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.CODIGO_TIPO_TELEFONE, Short.class);
		String telefone = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.TELEFONE, String.class);
		String ddd = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DDD, String.class);
		String ramal = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.RAMAL, String.class);
		String observacao = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_OBSERVACAO, String.class);
		
		String idUsuarioAprovacao = obterUsuarioCanal(mensagem.getCodigoCanal());

		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa);

		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
		
		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}

		TelefoneDTO telefoneDTO = new TelefoneDTO();
		telefoneDTO.setIdPessoa(pessoa.getIdPessoa());
		telefoneDTO.setIdInstituicao(pessoa.getIdInstituicao());
		telefoneDTO.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		telefoneDTO.setCodigoTipoTelefone(codigoTipoTelefone);
		telefoneDTO.setTelefone(telefone);
		telefoneDTO.setDdd(ddd);
		telefoneDTO.setRamal(ramal);
		telefoneDTO.setObservacao(observacao);
		telefoneDTO.setIdUsuarioAprovacao(idUsuarioAprovacao);

		return telefoneDTO;
	}

}