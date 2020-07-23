package br.com.sicoob.capes.cadastrarBem {

	import br.com.bancoob.componentes.containers.validaveis.ModuloValidavel;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.sicoob.capes.cadastrarBem.abas.*;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.events.ListEvent;

	public class AbaCrudController {
		private var grid:Grid;
		private var abaCrud:IAbaCrud;
		private var barraBotoesCrud:barraBotoesCrudView;
		private var barraBotoesPopUp:barraBotoesPopUpView;
		private var registro:Object;
		private var novo:Boolean;

		public function AbaCrudController(grid:Grid, abaCrud:IAbaCrud, barraBotoesCrud:barraBotoesCrudView, barraBotoesPopUp:barraBotoesPopUpView) {
			super();
			this.grid = grid;
			this.abaCrud = abaCrud;
			this.barraBotoesCrud = barraBotoesCrud;
			this.barraBotoesPopUp = barraBotoesPopUp;
		}

		public function init():void {

			grid.addEventListener(ListEvent.ITEM_CLICK, registroSelecionado);
			grid.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, carregarPopupAlterar);
			grid.doubleClickEnabled = true;

			barraBotoesCrud.btAlterar.enabled = false;
			barraBotoesCrud.btExcluir.enabled = false;
			
			barraBotoesCrud.btNovo.addEventListener(MouseEvent.CLICK, carregarPopupNovo);
			barraBotoesCrud.btAlterar.addEventListener(MouseEvent.CLICK, carregarPopupAlterar);
			barraBotoesCrud.btExcluir.addEventListener(MouseEvent.CLICK, registroExcluido);
			barraBotoesPopUp.btOk.addEventListener(MouseEvent.CLICK, salvar);
			barraBotoesPopUp.btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
		}

		public function carregarPopupNovo(evt:MouseEvent=null):void
		{
			novo = true;
			registro = abaCrud.novaInstanciaRegistro();
			grid.selectedIndex = -1;
			barraBotoesCrud.btAlterar.enabled = false;
			barraBotoesCrud.btExcluir.enabled = false;
			carregarPopup();
			abaCrud.preencherCamposPopUp(registro);
		}

		public function carregarPopupAlterar(evt:Event=null):void
		{
			novo = false;
			carregarPopup();
			abaCrud.preencherCamposPopUp(registro);
		}

		public function salvar(evt:MouseEvent=null):void
		{
			ModuloValidavel(abaCrud.obterJanela().getChildAt(0)).executarSeValido(adicionarLinha);
		}

		private function adicionarLinha():void {
			abaCrud.salvar(registro);
			
			if (novo)
				ListCollectionView(grid.dataProvider).addItem(registro);
			
			abaCrud.obterJanela().fecharJanela();
			barraBotoesCrud.btAlterar.enabled = false;
			barraBotoesCrud.btExcluir.enabled = false;
			grid.selectedIndex = -1;
		}
		
		public function cancelar(evt:MouseEvent=null):void {
			abaCrud.obterJanela().fecharJanela();
		}

		public function registroSelecionado(evt:ListEvent=null):void
		{
			registro = evt.target.selectedItem;
			barraBotoesCrud.btAlterar.enabled = true;
			barraBotoesCrud.btExcluir.enabled = true;
		}
		
		public function registroExcluido(evt:Event):void {

			if(grid.selectedIndex != -1)
				grid.dataProvider.removeItemAt(grid.selectedIndex);

			barraBotoesCrud.btAlterar.enabled = false;
			barraBotoesCrud.btExcluir.enabled = false;
		}
		
		public function carregarPopup():void {
		  	abaCrud.obterJanela().abrir(DisplayObject(Application.application), true);
    	}
	}
}