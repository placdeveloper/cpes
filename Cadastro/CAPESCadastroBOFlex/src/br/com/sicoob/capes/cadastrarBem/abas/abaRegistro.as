package br.com.sicoob.capes.cadastrarBem.abas{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.sicoob.capes.cadastrarBem.AbaCrudController;
	import br.com.sicoob.capes.cadastrarBem.IAbaCrud;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemRegistroVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	
	import flash.display.DisplayObject;
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;

	public class abaRegistro extends abaRegistroView implements IAbaCrud {
		private var telaEdicao:editarRegistro = new editarRegistro();
		private var janela:Janela = new Janela();
		private var controller:AbaCrudController;

		public function init():void {
			this.janela.title = "Registro";
	    	this.janela.addChild(DisplayObject(this.telaEdicao));
			controller = new AbaCrudController(this.gridRegistro, this, this.barraBotoes, this.telaEdicao.barraBotoesPopup);
			controller.init();
			telaEdicao.dataPosse.addEventListener(FocusEvent.FOCUS_OUT, validarDataAtual);
		}

		public function novaInstanciaRegistro():Object {
			return new BemRegistroVO();
		}

    	public function carregarDefinicoes(evt:ResultEvent):void {

    	}

    	public function validarDataAtual(evt:FocusEvent):void {

    		if(telaEdicao.dataPosse.selectedDate != null 
    			&& compararDataAtual(telaEdicao.dataPosse.selectedDate)){
				Alerta.show("O campo Posse deve ser menor que a data atual", "Erro", Alerta.ALERTA_ERRO, telaEdicao.dataPosse);
    		}
    	}

		public function compararDataAtual(dataComparada:Date):Boolean{
			dataComparada["hours"] = 0;
			dataComparada["milliseconds"] = 0;
			dataComparada["minutes"] = 0;
			dataComparada["seconds"] = 0;
			
			var dataAtual:Date = new Date();
			
			dataAtual["hours"] = 0;
			dataAtual["milliseconds"] = 0;
			dataAtual["minutes"] = 0;
			dataAtual["seconds"] = 0;
			
			if(dataComparada.getTime() >= dataAtual.getTime()){
				return true;
			} else {
				return false;
			}
		}
		
		public function atualizarCamposRegistro(bem:BemVO):void {

			var index:Object = null;  
			var bensRegistro:ListCollectionView = this.gridRegistro.dataProvider as ListCollectionView;

			for (index in bensRegistro) {
				BemRegistroVO(bensRegistro[index]).bem = bem;
			}
			
			bem.bensRegistro = bensRegistro; 
    	}

		public function limparFormIncluir():void {
			if (this.gridRegistro.dataProvider != null) 
				(this.gridRegistro.dataProvider as ListCollectionView).removeAll();
			
			this.gridRegistro.dataProvider = new ArrayCollection();
			this.enabled = false;
    	}

		public function salvar(objeto:Object):void {
			var registro:BemRegistroVO = objeto as BemRegistroVO;
			
    		registro.dataUltimaMatricula = DateTimeBase.getDateTimeEntity(this.telaEdicao.dataPosse.selectedDate);
    		registro.folha = this.telaEdicao.folha.text;
    		registro.incra = this.telaEdicao.incra.text;
    		registro.ipr = this.telaEdicao.itr.text;
    		registro.matriculaRegistro = this.telaEdicao.matricula.text;
    		registro.observacaoMatricula = this.telaEdicao.observacao.text;
    		registro.livro = this.telaEdicao.livro.text;
    		registro.areaMatricula = this.telaEdicao.qtdArea.valor;
    	}

    	public function preencherCampos(bem:BemVO):void {
    		this.gridRegistro.dataProvider = bem.bensRegistro;
    	}

    	public function preencherCamposPopUp(objeto:Object):void {
    		var registro:BemRegistroVO = objeto as BemRegistroVO;
			
			var dataUltima:Date = null; 
			if (registro.dataUltimaMatricula != null) {
    			dataUltima = registro.dataUltimaMatricula.data;
   			}
    		
    		this.telaEdicao.dataPosse.selectedDate = dataUltima;
    		this.telaEdicao.folha.text = registro.folha;
    		this.telaEdicao.incra.text = registro.incra;
    		this.telaEdicao.itr.text = registro.ipr;
    		this.telaEdicao.matricula.text = registro.matriculaRegistro;
    		this.telaEdicao.observacao.text = registro.observacaoMatricula;
    		this.telaEdicao.livro.text = registro.livro;
    		this.telaEdicao.qtdArea.valor = registro.areaMatricula;
    	}

    	public function verificarAlteracoes(bem:BemVO):Boolean {

    		if (!enabled || ListCollectionView(this.gridRegistro.dataProvider).length == bem.bensRegistro.length) {
    			return true;
    		}

    		return false;
    	}

    	public function obterGrid():Grid {
    		return this.gridRegistro;
    	}

    	public function obterJanela():Janela {
    		return this.janela;
    	}
    	
    	public function ativarAba(object:Object):Boolean {
    		
    		var subTipoBem:SubTipoBemVO = SubTipoBemVO(object);
    		if (subTipoBem == null || subTipoBem.cadastraRegistro == null) {
    			return false;
    		}
    		return subTipoBem.cadastraRegistro.valor;
	   	}
	   	
	   	public function configurarBarraBotoesCrud():void {
	   		if (this.gridRegistro.selectedItem == null) {
		   		this.barraBotoes.btAlterar.enabled = false;
		   		this.barraBotoes.btExcluir.enabled = false;
		   	}
	   	}
	}
}