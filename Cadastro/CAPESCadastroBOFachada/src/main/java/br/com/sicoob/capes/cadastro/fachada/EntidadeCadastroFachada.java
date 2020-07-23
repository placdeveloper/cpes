package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * Fachada que disponibiliza os serviços de Crud de EntidadeCadastro
 * 
 * @author Juan.Damasceno
 */
public abstract class EntidadeCadastroFachada<T extends EntidadeCadastroBase & Vigente>
		extends CAPESCadastroBOCrudFachada<T> {
	
	/** A constante PRODUTOS_BANCOOB. */
	protected static final String PRODUTOS_BANCOOB = "produtosBancoob";
	
	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 *            a chave que será utilizada no mapa de {@link RetornoDTO}
	 */
	public EntidadeCadastroFachada(String chaveMapa) {
		super(chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected abstract EntidadeCadastroDelegate<T, ?> obterDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		T entidade = obterEntidade(dto);
		obterDelegate().excluirEntidade(entidade);
		return obterMapRetorno(this.chaveMapa, entidade);
	}
}