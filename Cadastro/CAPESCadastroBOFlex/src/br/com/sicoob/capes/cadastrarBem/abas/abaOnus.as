package br.com.sicoob.capes.cadastrarBem.abas{
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.sicoob.capes.cadastrarBem.AbaCrudController;
	import br.com.sicoob.capes.cadastrarBem.IAbaCrud;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemOnusVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SituacaoBemVO;
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;


	public class abaOnus extends abaOnusView implements IAbaCrud {
		
		private var editarOnus:editarOnusView = new editarOnusView();
		private var janela:Janela = new Janela();
		private var controller:AbaCrudController;
		private var bemOnus:BemOnusVO;

		public function init():void {
			this.janela.title = "Ônus";
	    	this.janela.addChild(DisplayObject(this.editarOnus));

			controller = new AbaCrudController(this.gridOnus, this, this.barraBotoes, this.editarOnus.barraBotoesPopup);
			controller.init();
		}
		
    	public function carregarDefinicoes(evt:ResultEvent):void {

    	}

		public function atualizarCamposRegistro(bem:BemVO):void {
			var index:Object = null;  
			var bensOnus:ListCollectionView = this.gridOnus.dataProvider as ListCollectionView;
			
			for (index in bensOnus) {
				BemOnusVO(bensOnus[index]).bem = bem;
			}
			
			bem.bensOnus = bensOnus;
    	}

		public function limparFormIncluir():void {
			if (this.gridOnus.dataProvider != null) 
				(this.gridOnus.dataProvider as ListCollectionView).removeAll();

			this.gridOnus.dataProvider = new ArrayCollection();
			this.enabled = false;
    	}

    	public function preencherCampos(bem:BemVO):void {
			this.gridOnus.dataProvider = bem.bensOnus;
    	}

    	public function preencherCamposPopUp(objeto:Object):void {
    		var onus:BemOnusVO = objeto as BemOnusVO;
    		editarOnus.descricao.text = onus.descricao;
    		editarOnus.grau.valor = onus.numeroGrau;
    		editarOnus.instituicaoCredora.text = onus.nomeInstituicaoCredora;
    		editarOnus.valor.valor = onus.valor;

   			var dataPrevisao:Date = null;
    		if (onus.dataPrevistaLiberacao != null){
    			dataPrevisao = onus.dataPrevistaLiberacao.data;	
    		}
    		editarOnus.liberacao.selectedDate = dataPrevisao;
    	}

		public function salvar(objeto:Object):void {
    		var onus:BemOnusVO = objeto as BemOnusVO;
    		onus.descricao = editarOnus.descricao.text;
    		onus.numeroGrau = editarOnus.grau.valor;
    		onus.nomeInstituicaoCredora = editarOnus.instituicaoCredora.text;
    		onus.valor = editarOnus.valor.valor;
    		onus.dataPrevistaLiberacao = DateTimeBase.getDateTimeEntity(editarOnus.liberacao.selectedDate);
    	}

		public function novaInstanciaRegistro():Object {
			return new BemOnusVO();
		}

    	public function verificarAlteracoes(bem:BemVO):Boolean {
    		
    		if (!enabled 
    			|| ListCollectionView(this.gridOnus.dataProvider).length == bem.bensOnus.length)
    			return true;
    		
    		return false;
    	}

    	public function obterJanela():Janela {
		  	return janela;
    	}

    	public function obterGrid():Grid {
    		return this.gridOnus;
    	}

    	public function ativarAba(object:Object):Boolean {
    		if (object != null && object is SituacaoBemVO) {
	    		var situacaoBem:SituacaoBemVO = SituacaoBemVO(object);
				return situacaoBem != null && situacaoBem.cadastraOnus.valor;
    		}
    		return false;
	   	}
	   	
	   	public function configurarBarraBotoesCrud():void {
	   		if (this.gridOnus.selectedItem == null) {
		   		this.barraBotoes.btAlterar.enabled = false;
		   		this.barraBotoes.btExcluir.enabled = false;
		   	}
	   	}
	}
}