package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_COOP_REMETENTE;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_PESSOA;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaFonteRendaDTO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

@Stateless
@Remote({ Transacao.class })
public class ConsultarFonteRendaServicoEJB extends CAPESTransacaoServicoEJB {

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
		
		if(TipoPessoaEnum.isPessoaJuridica(codigoTipoPessoa.shortValue())) {
			throw new NegocioException("Transação indisponível para pessoa jurídica");
		}
		
		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa) ;
		List<FonteRendaPessoaVO> listaVO = obterListaFonteRenda(idPessoaLegado, idInstituicao);
		
		Resultado<ConsultaFonteRendaDTO> resultado = new Resultado<ConsultaFonteRendaDTO>();
		for (FonteRendaPessoaVO vo : listaVO) {
			ConsultaFonteRendaDTO dto = new ConsultaFonteRendaDTO();
			dto.setIdFonteRenda(vo.getIdFonteRenda());
			dto.setCodigoTipoFonteRenda(vo.getCodigoTipoFonteRenda());
			dto.setDescricaoTipoRenda(vo.getDescricaoTipoFonteRenda());
			dto.setBolRendaFixa(vo.isRendaFixa());
			dto.setDataValidadeRenda(vo.getDataValidadeRenda());
			dto.setValorReceitaBrutaMensal(tratarValor(vo.getValorReceitaBrutaMensal()));
			dto.setDescFonteRenda(vo.getDescricao());
			dto.setIdPessoaEmpregador(vo.getIdPessoaEmpregador());
			dto.setBolSimplesNacional(vo.getBolSimplesNacional());
			dto.setBolPossuiAtivo(vo.getBolPossuiAtivo());
			resultado.add(dto);
		}
		
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}
	
	private BigDecimal tratarValor(BigDecimal valor) {
		BigDecimal novoValor = null;
		if (valor != null) {
			novoValor = valor.divide(BigDecimal.ONE, 2, RoundingMode.CEILING);
		}
		return novoValor;
	}

	private List<FonteRendaPessoaVO> obterListaFonteRenda(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return CAPESApiFabricaDelegates.getInstance().criarFonteRendaPessoaDelegate().listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}