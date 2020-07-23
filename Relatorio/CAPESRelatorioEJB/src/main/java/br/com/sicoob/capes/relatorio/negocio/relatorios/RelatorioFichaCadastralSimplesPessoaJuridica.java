package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;

public class RelatorioFichaCadastralSimplesPessoaJuridica extends RelatorioFichaCadastralSimples{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioFichaCadastralSimplesPessoaJuridica(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super(fichaCadastralVO);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected Object recuperaRelatorioDadosPessoais() throws JRException {
		return recuperarRelatorio("FichaCadastralSimples_DadosPessoaisPJ.jasper");
	}

	@Override
	protected EnderecoBase recuperaEnderecoComercialPessoa(List<EnderecoBase> enderecos) throws BancoobException {
		for (EnderecoBase enderecoBase : enderecos) {
			if(enderecoBase.getTipoEndereco().getCodigo().equals(getEnderecoComercial())){
				return enderecoBase;
			}
		}
		return null;
	}

	@Override
	protected Telefone recuperaTelefoneComercialPessoa(List<Telefone> telefones)throws BancoobException {
		if(telefones != null && !telefones.isEmpty()){
			Collections.sort(telefones, new Comparator<Telefone>() {
				public int compare(Telefone t1, Telefone t2) {
					return t1.getDataHoraInicio().compareTo(t2.getDataHoraInicio());
				}
			});
			
			for (Telefone telefone : telefones) {
				if(telefone.getTipoTelefone().getCodigo().equals(getTelefoneComercial())){
					return telefone;
				}
			}
		}
		return null;
	}

	@Override
	protected EnderecoBase recuperaEnderecoResidencialPessoa(List<EnderecoBase> enderecos) throws BancoobException {
		return null;
	}

	@Override
	protected Telefone recuperaTelefoneResidencialPessoa(List<Telefone> telefones) throws BancoobException {
		return null;
	}

}
