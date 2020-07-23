package br.com.sicoob.capes.cadastrarBem.abas{
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.sicoob.capes.cadastrarBem.AbaCrudController;
	import br.com.sicoob.capes.cadastrarBem.IAbaCrud;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemPosseVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoPosseBemVO;
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;


	public class abaPosse extends abaPosseView implements IAbaCrud {
		private var telaEdicao:editarPosse = new editarPosse();
		private var janela:Janela = new Janela();
		private var tiposPosseBem:ArrayCollection;
		private var controller:AbaCrudController;

		public function init():void {
			this.janela.title = "Posse";
			this.janela.addChild(DisplayObject(this.telaEdicao));
			controller = new AbaCrudController(this.gridPosse, this, this.barraBotoes, this.telaEdicao.barra);
			controller.init();
		}

    	public function carregarDefinicoes(evt:ResultEvent):void {
    		tiposPosseBem = evt.result.dados.tiposPosseBem;
    	}
		
		public function atualizarCamposRegistro(bem:BemVO):void {
			var index:Object = null;  
			var bensPosse:ListCollectionView = this.gridPosse.dataProvider as ListCollectionView;

			for (index in bensPosse) {
				BemPosseVO(bensPosse[index]).bem = bem;
			}

			bem.bensPosse = bensPosse;
    	}

		public function limparFormIncluir():void {
			if (this.gridPosse.dataProvider != null) 
				(this.gridPosse.dataProvider as ListCollectionView).removeAll();
				
			this.gridPosse.dataProvider = new ArrayCollection();			
			this.enabled = false;
    	}

		public function novaInstanciaRegistro():Object {
			return new BemPosseVO();
		}
		
		public function salvar(objeto:Object):void {
    		var posse:BemPosseVO = objeto as BemPosseVO;
    		
    		posse.tipoPosseBem = this.telaEdicao.cmbTipoPosse.selectedItem as TipoPosseBemVO;
    		posse.area = this.telaEdicao.areaPosse.valor;
    	}

    	public function preencherCampos(objeto:BemVO):void {
			this.gridPosse.dataProvider = objeto.bensPosse;
    	}

    	public function preencherCamposPopUp(objeto:Object):void {
    		carregarDefinicoesPopup();
    		
    		var posse:BemPosseVO = objeto as BemPosseVO;
    		
    		if (posse.tipoPosseBem != null)
    			this.telaEdicao.cmbTipoPosse.procuraItemPorNome(posse.tipoPosseBem.codigo,"codigo");
    		
    		this.telaEdicao.areaPosse.valor = posse.area;
    	}

    	public function verificarAlteracoes(bem:BemVO):Boolean {

    		if (!enabled 
    			|| ListCollectionView(this.gridPosse.dataProvider).length == bem.bensPosse.length) {
    			return true;
    		}
    		
    		return false;
    	}
    	
    	public function carregarDefinicoesPopup():void {
    		this.telaEdicao.cmbTipoPosse.dataProvider = tiposPosseBem;
    	}
    	
    	public function obterGrid():Grid {
    		return this.gridPosse;
    	}
    	
    	public function obterJanela():Janela {
    		return this.janela;
    	}
    	
    	public function ativarAba(object:Object):Boolean {
    		
    		var subTipoBem:SubTipoBemVO = SubTipoBemVO(object);
    		if (subTipoBem == null || subTipoBem.cadastraPosse == null) {
    			return false;
    		}
    		return subTipoBem.cadastraPosse.valor;
	   	}
	   	
	   	public function configurarBarraBotoesCrud():void {
	   		
	   		if (this.gridPosse.selectedItem == null) {
		   		this.barraBotoes.btAlterar.enabled = false;
		   		this.barraBotoes.btExcluir.enabled = false;
		   	}
	   	}
	}
}