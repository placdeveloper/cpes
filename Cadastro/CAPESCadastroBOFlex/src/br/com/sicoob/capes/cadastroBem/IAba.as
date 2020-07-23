package br.com.sicoob.capes.cadastroBem {

	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Interface padrão para as abas das telas de bens.
	 */
    public interface IAba {

		/**
		 * Inicializa os serviços e componentes após a construção da tela.
		 */
    	function inicializar():void;
    	
		/**
		 * Obtém as definições dos campos da tela.
		 */
    	function obterDefinicoes(evento:ResultEvent):void;

		/**
		 * Preenche os campos, quando em modo de alteração.
		 */
		function preencherCampos(bem:BemVO):void;
		
		/**
		 * Atualiza o objeto ao salvar a tela.
		 */
		function atualizarCamposRegistro(bem:BemVO):void;
		
		/**
		 * Verifica se a tela teve alguma alteração.
		 */
    	function verificarAlteracoes(bem:BemVO = null):Boolean;
		
		/**
		 * Verifica se a aba foi preenchida
		 */
		function verificarPreenchimento():Boolean;
		
		/**
		 * Limpa os campos.
		 */
    	function limpar():void;
		
		/**
		 * Acionado quando uma aba é trocada.
		 */
		function abaTrocada():void;
		
		/**
		 * Faz a configuração dos destinos dos serviços.
		 */
		function configurarDestino(destino:DestinoVO):void;
		
		/**
		 * Marca os campos obrigatórios.
		 */
		function marcarCamposObrigatorios():void;
		
		/**
		 * Bloqueia os campos adicionais no modo visualização.
		 */
		function bloquearCampoModoVisualizacao(bloquear:Boolean):void;
		
		/**
		 * Realiza a validação dos campos antes de gravar;
		 */
		function validar():Boolean;
    }
}