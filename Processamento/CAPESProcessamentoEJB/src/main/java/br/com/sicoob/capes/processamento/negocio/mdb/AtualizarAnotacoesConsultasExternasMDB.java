/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.mdb;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.mdb.BancoobMdb;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.comum.util.json.FabricaDatas;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.AtualizarAnotacoesConsultasExternasServicoLocal;
import flexjson.JSONDeserializer;
import flexjson.JSONException;

/**
 * MDB utilizado para atualizar anotações das consultas externas.
 * 
 * @author Erico.Junior
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AtualizarAnotacoesConsultasExternasMDB extends BancoobMdb {

	/** O atributo servicoAtualizacao. */
	@EJB(mappedName = "capes/processamento/AtualizarAnotacoesConsultasExternasServico")
	private transient AtualizarAnotacoesConsultasExternasServicoLocal servicoAtualizacao;
	
	/**
	 * {@inheritDoc}
	 */
	public void onMessage(Message mensagem) {
		if(mensagem != null) {
			try {
				String msg = ((TextMessage) mensagem).getText();
				getLogger().info("(AnotacoesConsultasExternas) - Mensagem recebida: ", msg);
	
				if(msg != null) {
					JSONDeserializer<AnotacaoExternaDTO> deserializer = 
							new JSONDeserializer<AnotacaoExternaDTO>();
					deserializer.use(Date.class, new FabricaDatas());
					Serializable msgSerializada = deserializer.deserialize(msg);
		
					AnotacaoExternaDTO dto = (AnotacaoExternaDTO) msgSerializada;
					servicoAtualizacao.atualizarAnotacoesConsultasExternas(dto);
				}
			} catch (JSONException e) {
				getLogger().erro(e, "Erro ao deserializar a mensagem do consultas externas.");
			} catch (JMSException excecao) {
				getLogger().erro(excecao, "Erro ao processar a mensagem do consultas externas.");
				throw new BancoobRuntimeException(excecao);
			} catch (BancoobException excecao) {
				getLogger().erro(excecao, "Erro ao processar a mensagem do consultas externas.");
				throw new BancoobRuntimeException(excecao);
			}
		}
	}
}
