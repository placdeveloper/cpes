/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoEmpresaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.legado.TipoEmpresa;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.TipoEmpresaDelegate;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.FonteRendaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.FonteRendaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.FonteRendaDAO;

/**
 * Serviço utilizado para replicação de fontes de rendas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { FonteRendaServicoLocal.class })
@Remote( { FonteRendaServicoRemote.class })
public class FonteRendaServicoEJB extends EntidadeReplicavelServicoEJB<FonteRenda> implements
		FonteRendaServicoRemote, FonteRendaServicoLocal {

	@Inject
	@Default
	private transient FonteRendaDAO fonteRendaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaDAO getDAO() {
		return fonteRendaDAO;
	}
	
	@Override
	public FonteRenda incluir(FonteRenda objeto, Integer idInstituicao)throws BancoobException {
		FonteRenda renda = super.incluir(objeto, idInstituicao);
		atualizarTipoEmpresaIdeal(renda);
		return renda;
	}
	
	@Override
	public void excluir(FonteRenda objeto, Integer idInstituicao) throws BancoobException {
		super.excluir(objeto, idInstituicao);
		atualizarTipoEmpresaIdeal(objeto, true);
	}
	
	@Override
	public void alterar(FonteRenda objeto, Integer idInstituicao) throws BancoobException {
		super.alterar(objeto, idInstituicao);
		atualizarTipoEmpresaIdeal(objeto);
	}
	
	private void atualizarTipoEmpresaIdeal(FonteRenda fonteRenda) throws BancoobException {
    	atualizarTipoEmpresaIdeal(fonteRenda, false);
    }
	
	private void atualizarTipoEmpresaIdeal(FonteRenda renda, boolean exclusao) throws BancoobException {
		if(isRendaPessoaJuridica(renda)){
			PessoaJuridica pjTemp = (PessoaJuridica) renda.getPessoa();
			if (exclusao) {
				pjTemp.setTipoEmpresa(null);
			} else {
				if(renda.getBolPossuiAtivo()){
					pjTemp.setTipoEmpresa(TipoEmpresaEnum.GRANDE_EMPRESA.getCodigo());
				} else {
					Short codTipoEmp = obterTipoEmpresaPorFaixaDeRendaAnual(renda);
					pjTemp.setTipoEmpresa(codTipoEmp);
				}
			}
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate().atualizaCodTipoEmpresa(pjTemp);
		}
	}
	
	private Short obterTipoEmpresaPorFaixaDeRendaAnual(FonteRenda renda) throws BancoobException {
		TipoEmpresaDelegate empresaDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance().criarTipoEmpresaDelegate();
		TipoEmpresa tipoEmpresaRetorno = empresaDelegate.obterTipoEmpresaPorFaixaRendaAnual(renda);
		
    	return tipoEmpresaRetorno.getCodigo();
	}
	
	private boolean isRendaPessoaJuridica(FonteRenda renda) throws BancoobException{
    	TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(renda.getPessoa().getTipoPessoa());
    	return TipoPessoaEnum.PESSOA_JURIDICA == tipoPessoa;
    }

}
