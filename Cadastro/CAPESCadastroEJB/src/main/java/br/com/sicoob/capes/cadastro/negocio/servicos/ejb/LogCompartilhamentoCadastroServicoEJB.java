/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.LogCompartilhamentoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.LogCompartilhamentoCadastroServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.LogCompartilhamentoCadastroDAO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.LogCompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * EJB contendo servicos relacionados a LogCompartilhamentoCadastro.
 */
@Stateless
@Local( { LogCompartilhamentoCadastroServicoLocal.class })
@Remote( { LogCompartilhamentoCadastroServicoRemote.class })
public class LogCompartilhamentoCadastroServicoEJB extends
		CAPESCadastroCrudServicoEJB<LogCompartilhamentoCadastro> implements
		LogCompartilhamentoCadastroServicoRemote, LogCompartilhamentoCadastroServicoLocal {

	@Inject
	@Default
	private LogCompartilhamentoCadastroDAO dao;
	
	/** O atributo responsavelCadastroServico. */
	@EJB(mappedName = "capes/cadastro/ResponsavelCadastroServico") 
	private ResponsavelCadastroServicoLocal responsavelCadastroServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected LogCompartilhamentoCadastroDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void compartilhar(PessoaCompartilhamento pessoa) throws BancoobException {

		LogCompartilhamentoCadastro filtro = new LogCompartilhamentoCadastro();
		filtro.setPessoaCompartilhamento(pessoa);
		filtro.setCompartilhado(false);
		filtro.setIdInstituicaoDemandante(obterInstituicaoUsuarioLogado().getIdInstituicao());
		filtro.setIdUsuario(recuperarLoginSemDominio());
	
		ConsultaDto<LogCompartilhamentoCadastro> criterios = 
				new ConsultaDto<LogCompartilhamentoCadastro>();
		criterios.setFiltro(filtro);

		List<LogCompartilhamentoCadastro> lista = super.listar(criterios);
		LogCompartilhamentoCadastro log = null;
		
		if(lista != null && !lista.isEmpty()) {
			log = lista.get(0); 
			log.setCompartilhado(true);
			super.alterar(log);			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public LogCompartilhamentoCadastro incluir(PessoaCompartilhamento pessoa) throws BancoobException {

		Instituicao instituicaoUsuario = obterInstituicaoUsuarioLogado();
		
		LogCompartilhamentoCadastro log = new LogCompartilhamentoCadastro();
		log.setDataHoraConsulta(new DateTimeDB());
		log.setIdInstituicaoDemandante(instituicaoUsuario.getIdInstituicao());
		log.setIdUsuario(recuperarLoginSemDominio());
		log.setPessoaCompartilhamento(pessoa);
	
		ResponsavelCadastro responsavel = responsavelCadastroServico.consultar(pessoa);
		if(responsavel != null && responsavel.getIdInstituicao() != null) { 
			log.setIdInstituicaoResponsavel(responsavel.getIdInstituicao());
		}
		
		return super.incluir(log);
	}

}
