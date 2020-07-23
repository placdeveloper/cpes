package br.com.sicoob.capes.utils.autorizacao{
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class DetalhamentoGedGft extends DetalhamentoGedGftView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.AutorizarFachada";
		private static const CONSULTAR_INFO: String = "obterDetalhamentoGEDGFT";
		private static const SERVICO_JAVA: String = "servicosJavaCapes";
		
		private var servicoConsulta:ServicoJava = new ServicoJava();
		private var _objeto:Object;
		private var _idTipoPessoaSelecionada:Number;
		private var destino:DestinoVO;
		
		public function get objeto():Object {
			return _objeto;
		}
		
		public function set objeto(value:Object):void{
			_objeto = value;
		}
		
		public function get idTipoPessoaSelecionada():Number {
			return _idTipoPessoaSelecionada;
		}
		
		public function set idTipoPessoaSelecionada(value:Number):void{
			_idTipoPessoaSelecionada = value;
		}
		
		/**
		 * Método Construtor 
		 **/
		public function DetalhamentoGedGft(){
			super();
			
			servicoConsulta.source = CLASSE_SERVICO;
			servicoConsulta.mensagemEspera = "Carregando dados...";
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			inicializarServicos();
		}
		
		public function obterLista():void{
			MostraCursor.setBusyCursor("Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			if(objeto != null){
				var dto: RequisicaoReqDTO = new RequisicaoReqDTO;
				dto.dados.idRegistroControlado = objeto.idRegistroControlado;
				dto.dados.idTipoPessoaSelecionada = idTipoPessoaSelecionada;
				
				servicoConsulta.getOperation(CONSULTAR_INFO).send(dto);
			}
		}
		
		public function retornoObterLista(event:ResultEvent):void{
			gridInfo.grid.dataProvider = event.result.dados["listaGFT"];
			gridDocs.grid.dataProvider = event.result.dados["listaGED"];
			
			atualizarNomeUsuario();
			MostraCursor.removeBusyCursor();
			
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
		}
		
		public function obterGrid():Grid {
			this.gridInfo.grid.validateNow();
			return this.gridInfo.grid;
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		
		private function inicializarServicos():void {
			if(this.destino == null || destino.tipo == DestinoVO.CANAL_NET) {
				PortalModel.portal.obterDefinicoesDestino(SERVICO_JAVA, configurarDestino);
			} else {
				configurarDestino();
			}
		}
		
		private function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				this.destino = destino;
				
			}
			servicoConsulta.configurarDestino(this.destino);
			obterLista();
		}
		
		private function atualizarNomeUsuario():void{
			var usuario:String = "-";
			if(objeto != null && objeto.hasOwnProperty("idUsuarioAprovacao") && objeto.idUsuarioAprovacao != null){
				usuario = objeto.idUsuarioAprovacao;
			}
			
			this.lblUsuario.text = usuario;
		}
		
	}
}