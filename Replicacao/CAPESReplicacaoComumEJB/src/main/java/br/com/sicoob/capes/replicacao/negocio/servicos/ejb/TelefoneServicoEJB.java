/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.legado.Telefone;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoCedenteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TelefoneServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TelefoneServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.TelefoneDAO;

/**
 * Servi�o para a replica��o dos telefones
 * 
 * @author erico.junior
 */
@Stateless
@Local( { TelefoneServicoLocal.class })
@Remote( { TelefoneServicoRemote.class })
public class TelefoneServicoEJB extends EntidadeReplicavelServicoEJB<Telefone> implements
		TelefoneServicoRemote, TelefoneServicoLocal {

	@Inject
	@Default
	private transient TelefoneDAO dao;

	/** O atributo servicoHistoricoCedente. */
	@EJB(mappedName = "capes/replicacao/HistoricoCedenteServico")
	private HistoricoCedenteServicoLocal servicoHistoricoCedente;
	
	/** O atributo servicoPessoa. */
	@EJB(mappedName = "capes/replicacao/PessoaServico") 
	private PessoaServicoLocal servicoPessoa;
	
	/** A constante FAX. */
	private static final short FAX = 5; 
	
	/** A constante CELULAR. */
	private static final short CELULAR = 6;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Telefone telefone, Integer idInstituicao)
			throws BancoobException {
		super.alterar(telefone, idInstituicao);
		atualizarTelefonePessoa(telefone, idInstituicao);
		incluirHistoricoCedente(telefone, idInstituicao);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Telefone telefone, Integer idInstituicao)
			throws BancoobException {
		super.excluir(telefone, idInstituicao);
		incluirHistoricoCedente(telefone, idInstituicao);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Telefone incluir(Telefone telefone, Integer idInstituicao)
			throws BancoobException {
		Telefone retorno = super.incluir(telefone, idInstituicao);
		atualizarTelefonePessoa(retorno, idInstituicao);
		incluirHistoricoCedente(retorno, idInstituicao);
		return retorno;
	}

	/**
	 * O m�todo Incluir historico cedente.
	 *
	 * @param telefone o valor de telefone
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	private void incluirHistoricoCedente(Telefone telefone, Integer idInstituicao) throws BancoobException {
		servicoHistoricoCedente.incluirHistoricoCendente(telefone.getPessoa(), idInstituicao);
 	}
	
	/**
	 * Atualiza o telefone no registro de pessoa se:
	 * 		1) O tipo do telefone � celular e � uma pessoa f�sica.
	 * 		2) O tipo do telefone � fax e � uma pessoa jur�dica.
	 * @param telefone
	 * @throws BancoobException
	 */
	private void atualizarTelefonePessoa(Telefone telefone, Integer idInstituicao) throws BancoobException {
		
		Short codTipoTelefone = telefone.getCodTipoTelefone();
		Pessoa pessoa = telefone.getPessoa();
		
		if((pessoa instanceof PessoaFisica && CELULAR == codTipoTelefone)
				|| (pessoa instanceof PessoaJuridica && FAX == codTipoTelefone)) { 
			servicoPessoa.atualizarTelefonePessoa(pessoa, telefone, idInstituicao);
		}
	}
	
}
