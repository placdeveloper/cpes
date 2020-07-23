package br.com.sicoob.capes.cadastrarBem {
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.Grid;
	
	import flash.events.MouseEvent;
	
    public interface IAbaCrud extends IAba {

    	function obterGrid():Grid;
    	function obterJanela():Janela;
    	function preencherCamposPopUp(objeto:Object):void;
    	function salvar(objeto:Object):void;
    	function novaInstanciaRegistro():Object;
    	function executarSeValido(funcao:Function, argumentos:Array=null):void;
    	function configurarBarraBotoesCrud():void;
    }
}