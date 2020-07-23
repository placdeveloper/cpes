package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;
import br.com.sicoob.capes.api.persistencia.dao.DadosEtiquetaDAO;

/**
 * A Classe DadosEtiquetaDAOImpl.
 */
public class DadosEtiquetaDAOImpl extends CAPESApiDAO<DadosEtiquetaVO> implements DadosEtiquetaDAO {
	
	/**
	 * Instancia um novo DadosEtiquetaDAOImpl.
	 */
	public DadosEtiquetaDAOImpl(){
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DadosEtiquetaVO> listarDadosEtiqueta(Integer idInstituicao, List<Integer> listaIdPessoaLegado) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;

		List<DadosEtiquetaVO> listaRetorno = new ArrayList<DadosEtiquetaVO>();

		try {

			conx = estabelecerConexao();

			comando = getComando("LISTAR_DADOS_ETIQUETAS");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("listaIdPessoaLegado", listaIdPessoaLegado);
			comando.configurar();

			getLogger().info(comando.getSqlEmUso());

			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				DadosEtiquetaVO vo = new DadosEtiquetaVO();

				vo.setIdPessoaLegado(rset.getInt("IDPESSOALEGADO"));
				vo.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				vo.setNomePessoa(rset.getString("NOMEPESSOA"));
				vo.setDescEndereco(rset.getString("DESCLOGRADOURO"));
				vo.setDescNumero(rset.getString("DESCNUMERO"));
				vo.setDescComplemento(rset.getString("DESCCOMPLEMENTO"));
				vo.setNomeBairro(rset.getString("NOMEBAIRRO"));
				vo.setNumCep(rset.getString("CODCEP"));
				vo.setIdTipoLogradouro(rset.getShort("IDTIPOLOGRADOURO"));
				vo.setIdLocalidade(rset.getInt("IDLOCALIDADE"));
				vo.setCodTipoEndereco(rset.getShort("CODTIPOENDERECO"));
				vo.setDescTipoEndereco(rset.getString("DESCTIPOENDERECO"));

				listaRetorno.add(vo);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(Integer idInstituicao, Short codTipoPessoa, Date dataNascimentoInicio, Date dataNascimentoFinal, 
					Character tipoSexo, String mesDiaAniversarioInicio, String mesDiaAniversarioFinal) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;

		List<DadosEtiquetaVO> listaRetorno = new ArrayList<DadosEtiquetaVO>();

		try {

			conx = estabelecerConexao();

			comando = getComando("DADOS_ETIQUETA_POR_PESSOA_LEGADO_INSTITUICAO");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("codTipoPessoa", codTipoPessoa);
			comando.adicionarVariavel("dataNascimentoInicio", dataNascimentoInicio);
			comando.adicionarVariavel("dataNascimentoFinal", dataNascimentoFinal);
			comando.adicionarVariavel("tipoSexo", tipoSexo != null ? tipoSexo.toString() : null);
			comando.adicionarVariavel("mesDiaAniversarioInicio", mesDiaAniversarioInicio);
			comando.adicionarVariavel("mesDiaAniversarioFinal", mesDiaAniversarioFinal);
			comando.configurar();
			
			getLogger().info(comando.getSqlEmUso());

			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				
				DadosEtiquetaVO vo = new DadosEtiquetaVO();
				
				vo.setIdPessoaLegado(rset.getInt("IDPESSOA"));
				vo.setIdInstituicao(rset.getInt("IDUNIDADEINST"));
				vo.setNomePessoa(rset.getString("NOMEPESSOA"));
				vo.setDescEndereco(rset.getString("DESCLOGRADOURO"));
				vo.setDescNumero(rset.getString("DESCNUMERO"));
				vo.setDescComplemento(rset.getString("DESCCOMPLEMENTO"));
				vo.setNomeBairro(rset.getString("NOMEBAIRRO"));
				vo.setNumCep(rset.getString("CODCEP"));
				vo.setIdTipoLogradouro(rset.getShort("IDTIPOLOGRADOURO"));
				vo.setIdLocalidade(rset.getInt("IDLOCALIDADE"));
				vo.setCodTipoPessoa(rset.getShort("CODTIPOPESSOA"));
				vo.setNumCpfCnpj(rset.getString("NUMCPFCNPJ"));
				vo.setCodTipoSexo(rset.getString("CODTIPOSEXO"));
				vo.setDataNascimento(rset.getDate("DATANASCIMENTO"));
				
				listaRetorno.add(vo);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return listaRetorno;
	}

}