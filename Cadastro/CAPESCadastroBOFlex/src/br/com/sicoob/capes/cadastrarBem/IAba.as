package br.com.sicoob.capes.cadastrarBem {

	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	
	import mx.rpc.events.ResultEvent;
	
    public interface IAba {

    	function init():void;
    	
    	function preencherCampos(objeto:BemVO):void;
    	
    	function carregarDefinicoes(evt:ResultEvent):void;

		function atualizarCamposRegistro(objeto:BemVO):void;

    	function verificarAlteracoes(bem:BemVO):Boolean;
    	
    	function ativarAba(object:Object):Boolean;
    	
    	function limparFormIncluir():void;
    }
}