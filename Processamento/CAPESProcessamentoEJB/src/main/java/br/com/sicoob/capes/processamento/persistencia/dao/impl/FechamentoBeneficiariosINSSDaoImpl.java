/**
 * 
 */
package br.com.sicoob.capes.processamento.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.dto.InconsistenciaContaDTO;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoDao;
import br.com.sicoob.capes.processamento.persistencia.dao.FechamentoBeneficiariosINSSDao;

/**
 * @author Erico.Junior
 * 
 */
public class FechamentoBeneficiariosINSSDaoImpl extends CAPESProcessamentoDao implements
		FechamentoBeneficiariosINSSDao {

	/**
	 * {@inheritDoc}
	 */
	public List<BeneficiarioDTO> listarBeneficiariosImportacao(
			Integer numCooperativa, Date dataProduto) throws BancoobException {

		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BeneficiarioDTO> lista = new ArrayList<BeneficiarioDTO>();

		try {

			conx = estabelecerConexao(numCooperativa);
			comando = getComando("LISTAR_BENEFICIARIOS_INSS_IMPORTACAO");
			comando.adicionarVariavel("dataProduto", dataProduto);
			comando.configurar();

			rset = comando.executarConsulta(conx);
			BeneficiarioDTO dto = null;
			Date dataNascimento = null;

			while (rset.next()) {

				dataNascimento = rset.getDate("DataNascBeneficiario");
				dto = new BeneficiarioDTO();
				dto.setBairro(rset.getString("Bairro"));
				dto.setUf(rset.getString("DescUF"));
				dto.setCep(rset.getString("CEP"));
				dto.setEndereco(rset.getString("EndBeneficiarioINSS"));
				dto.setEnderecoCooperativa(rset.getString("endCooperativa"));
				dto.setIdInstituicao(rset.getInt("IdInstituicao"));
				dto.setIdUnidadeInst(rset.getInt("IdUnidadeInst"));
				dto.setIdLocalidade(rset.getInt("IdLocalidade"));
				dto.setNome(rset.getString("NomeBeneficiario"));
				dto.setNomeMae(rset.getString("NomeMaeBeneficiario"));
				dto.setNumBeneficiario(rset.getString("NB_NumBeneficiario"));
				dto.setNumContaCorrente(rset.getString("NumContaCorrente"));
				dto.setNumCooperativa(rset.getInt("NumCooperativa"));
				dto.setNumCpf(rset.getString("NumCPF"));
				dto.setNumPac(rset.getInt("NumPac"));
				dto.setNumTrabalhador(rset.getString("NIT_NumTrabalhador"));
				if (dataNascimento != null) {
					dto.setDataNascimento(new DateTimeDB(dataNascimento
							.getTime()));
				}
				lista.add(dto);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharComando(comando);
			fecharResultSet(rset);
			fecharConexao(conx);
		}
		return lista;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void gravarLogInconsistenciaBeneficiario(Integer numCooperativa, 
			InconsistenciaContaDTO inconsistencia) throws BancoobException {
		
		Comando comando = null;
		Connection conx = null;
		
		try {

			comando = getComando("SPU_INCONSISTENCIA_BENEFICIARIO_INSS");
			comando.adicionarVariavel("codErro", inconsistencia.getNumErro());
			comando.adicionarVariavel("dataProcessamento", inconsistencia.getDataProcessamento());
			comando.adicionarVariavel("numBeneficiario", inconsistencia.getNumBeneficiario());
			comando.adicionarVariavel("numTrabalhador", inconsistencia.getNumTrabalhador());
			comando.adicionarVariavel("codLoteOrigem", inconsistencia.getCodLoteOrigem());
			comando.configurar();
			
			conx = estabelecerConexao(numCooperativa);
			comando.executarStoredProcedure(conx);

		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
		
	}

}
