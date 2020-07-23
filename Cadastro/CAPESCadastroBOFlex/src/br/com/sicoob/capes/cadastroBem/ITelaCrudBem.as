package br.com.sicoob.capes.cadastroBem {

	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	
	import flash.events.Event;
	
	/**
	 * Interface padrão para as telas de bens.
	 */
    public interface ITelaCrudBem {
		
		/**
		 * Configura o destino dos serviços passado para as telas de bens.
		 */
		function configurarDestino(destino:DestinoVO):void;
		
		/**
		 * Abre as telas no modo de inclusão. 
		 */
		function abrirModoInclusao():void;
		
		/**
		 * Abre as telas no modo de edição. 
		 */
		function abrirModoEdicao(objeto:BemVO):void;
		
		/**
		 * Realiza a pesquisa do componente de bens, pelo código do bem.
		 */
		function pesquisarPorCodigo(codigo:Number):void;
		
		/**
		 * Realiza a pesquisa dos bens.
		 */
		function pesquisar(evento:Event = null):void;
		
		/**
		 * Faz a limpeza dos campos de filtro 
		 */
		function limpar():void;
		
		/**
		 * Verifica a necessidade de fechara  tela de edição.
		 */
		function verificarFechamentoTelaEdicao():void;
    }
}