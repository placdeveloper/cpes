package  br.com.sicoob.capes.cadastroGrupoCompartilhamento.cadastrarGrupoCompartilhamento {
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class EditarGrupoCompartilhamento extends EditarGrupoCompartilhamentoView {
		
		private var servico:ServicoJava = new ServicoJava();
		private var _objeto:Object = new Object();
		private var _listaGrupo:ArrayCollection = new ArrayCollection();
		
		public var RECARREGAR_LISTA:String = "RECARREGAR_LISTA";
		
		private var SUBTITULO:String = "Alterar o vínculo da instituição ";

		public function EditarGrupoCompartilhamento() {
			super();
			
			servico.addEventListener(ResultEvent.RESULT, retornoAlterar);
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			servico.source = "br.com.sicoob.capes.cadastro.fachada.GrupoCompartilhamentoFachada";
			
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnAlterar.addEventListener(MouseEvent.CLICK, alterarGrupoCompartilhamento);
			
			toolTipUtil.targets = [btnAlterar];
		}
		
		private function alterarGrupoCompartilhamento(event:Event=null):void {
			MostraCursor.setBusyCursor("Alterando...", this, MostraCursor.CURSOR_GRAVAR);
			
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			req.dados.idGrupoCompartilhamento = _objeto.idGrupoCompartilhamento;
			req.dados.idCompartilhamentoCadastro = cmbGrupo.selectedItem.codigo;
			req.dados.integracaoSrf = chkSRF.selected;
			
			servico.alterarVinculoInstituicao(req);
		}
		
		protected function fechar(event:MouseEvent=null):void {
			fecharJanela();
		}
		
		public function atualizarListaGrupo(valor:ArrayCollection):void {
			_listaGrupo = valor;
			cmbGrupo.dataProvider = valor;
		}
				
		public function setarObjeto(valor:Object):void {
			_objeto = valor;
		}
		
		public function atualizarCampos():void {
			labelTitulo.texto = "";
			labelTitulo.texto = SUBTITULO + _objeto.numero;
			cmbGrupo.selectedIndex = getItemSelecionado(_objeto.codigo, _listaGrupo.source);
			chkSRF.selected = _objeto.integracaoSrf;
		}
		
		private function getItemSelecionado(codigo:Number, source:Array):int {
			var n:int = source.length;
			
			for (var i:int = 0; i < n; i++) {
				if (source[i].codigo == codigo)
					return i;
			}
			
			return -1;           
		}
		
		private function retornoAlterar(event:ResultEvent):void {
			fecharJanela();
			this.dispatchEvent(new Event(RECARREGAR_LISTA));
		}
	}
}