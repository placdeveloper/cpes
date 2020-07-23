/*
 * SICOOB
 * 
 * EstadoExecucaoCargaConsultasExternas.java(br.com.sicoob.capes.processamento.negocio.anotacao.carga.consultasexternas.EstadoExecucaoCargaConsultasExternas)
 */
package br.com.sicoob.capes.processamento.negocio.anotacao.carga.consultasexternas;


/**
 * Singleton para guardar o estado da execu��o da carga das anota��es.
 * @author Erico.Junior
 */
public final class EstadoExecucaoCargaConsultasExternas {
	
	/** O atributo instancia. */
	private static EstadoExecucaoCargaConsultasExternas instancia = new EstadoExecucaoCargaConsultasExternas();
	
	/** O atributo em execucao. */
	private boolean emExecucao;

	/**
	 * Recupera uma inst�ncia �nica de EstadoExecucaoCargaConsultasExternas.
	 * 
	 * @return uma int�ncia �nica EstadoExecucaoCargaConsultasExternas
	 */
	public static EstadoExecucaoCargaConsultasExternas getInstance() {
		return instancia;
	}

	/**
	 * Cria uma nova inst�ncia de estado execucao carga consultas externas.
	 */
	private EstadoExecucaoCargaConsultasExternas() {
	}
	
	/**
	 * @return the emExecucao
	 */
	public boolean isEmExecucao() {
		return emExecucao;
	}

	/**
	 * @param emExecucao the emExecucao to set
	 */
	public void setEmExecucao(boolean emExecucao) {
		this.emExecucao = emExecucao;
	}
}
