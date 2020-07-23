package br.com.sicoob.capes.cadastrarTipoAnotacao.componentes {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class SelecionarCooperativas extends SelecionarCooperativasView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.TipoAnotacaoFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var _valorAtual:String = "";
		private var _instituicao:InstituicaoVO;
		private var _instituicoes:ListCollectionView;
		
		private var servico:ServicoJava = new ServicoJava();
		
		public static const EVENTO_EXCLUIR_INSTITUICAO:String = "eventoExcluirInstituicao";
		
		public function SelecionarCooperativas() {
			super();
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO", InstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAnotacao", TipoAnotacaoVO);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
        protected function init(event:FlexEvent): void {
			botaoProcurar.addEventListener(MouseEvent.CLICK, botaoProcurarClicado);
			botaoFechar.addEventListener(MouseEvent.CLICK, botaoFecharClicado);
			listaInstituicoes.addEventListener(EVENTO_EXCLUIR_INSTITUICAO, eventoExcluirInstituicao);
			
			campoNumeroCooperativa.addEventListener(FlexEvent.ENTER, acaoEnter);
			campoNumeroCooperativa.addEventListener(FocusEvent.FOCUS_OUT, aPerderOFoco);
			campoNumeroCooperativa.addEventListener(Event.CHANGE, aoTrocar);
		}
		
		public function configurar_destino(destino:DestinoVO):void {
			servico.configurarDestino(destino);
		}
		
		private function botaoProcurarClicado(evento:MouseEvent = null):void {
			_valorAtual = campoNumeroCooperativa.text;
			
			if((_valorAtual != null) && (_valorAtual != "")) {
				if(!contemNaLista(new Number(_valorAtual))){
					MostraCursor.setBusyCursor("Obtendo a Instituição...", Application.application, MostraCursor.CURSOR_PROGRESSO);
					servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo a Instituição...", ResultEvent.RESULT, retornoObterInstituicao);
					var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
					dto.dados.numeroCooperativa = campoNumeroCooperativa.valor;
					servico.getOperation("obterInstituicao").send(dto);
				}
			}
		}
		
		private function retornoObterInstituicao(evento:ResultEvent):void {
			var instituicao:InstituicaoVO = evento.result.dados.instituicao;
			
			if(instituicao != null){
				_instituicao = instituicao;
				Alerta.show("Deseja adicionar a instituição " + instituicao.nome + " à lista?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmarAdicionar, confirmarCancelar, Alerta.ALERTA_OK);
			} else {
				Alerta.show("Instituição não encontrada.", "Aviso", Alerta.ALERTA_INFORMACAO);
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function confirmarAdicionar(evento:Event = null):void {
			_instituicoes.addItem(_instituicao);
			listaInstituicoes.dataProvider = _instituicoes;
		}
		
		private function confirmarCancelar(evento:Event = null):void {
			
		}
		
		private function contemNaLista(numeroCooperativa:Number):Boolean {
			for each(var instituicao:InstituicaoVO in _instituicoes){
				if(instituicao.numero == numeroCooperativa){
					return true;
				}
			}
			return false;
		}
		
		private function botaoFecharClicado(evento:MouseEvent = null):void {
			pegaJanela().fecharJanela();
		}
		
		private function eventoExcluirInstituicao(evento:ObjetoEvent):void {
			for(var i:uint = 0; i < _instituicoes.length; i++){
				var instituicao:InstituicaoVO = _instituicoes.getItemAt(i) as InstituicaoVO;
				
				if(instituicao.idInstituicao == evento.objeto.idInstituicao){
					_instituicoes.removeItemAt(i);
				}
			}
		}
		
		private function acaoEnter(evento:FlexEvent = null):void {
			if(deveRealizarConsulta()){				
				botaoProcurarClicado();
			}
		}
		
		private function aPerderOFoco(evento:Event):void {
			if(!campoNumeroCooperativa.enabled) {
				return;
			}
			
			if(deveRealizarConsulta()) {
				botaoProcurarClicado();
			}
		}
		
		private function aoTrocar(evento:Event = null):void {
			if(deveRealizarConsulta()){
				_valorAtual = "";
			}
		}
		
		private function deveRealizarConsulta():Boolean {
			var retorno:Boolean = true;
			var _codigo:String = campoNumeroCooperativa.text;
			
			if(_codigo == null || "" == _codigo){
				retorno = false;
			}else if(_codigo == _valorAtual) {
				retorno = false;
			}
			
			return retorno;
		}
		
		public function get instituicoes():ListCollectionView {
			return _instituicoes;
		}
		
		public function set instituicoes(valor:ListCollectionView):void {
			this._instituicoes = valor;
		}
		
		public function limpar():void {
			_instituicao = null;
			_instituicoes = new ArrayCollection();
			
			if(listaInstituicoes != null){
				listaInstituicoes.dataProvider = new ArrayCollection();
			}
			
			if(campoNumeroCooperativa != null){
				campoNumeroCooperativa.text = "";
			}
		}
		
		public function carregarInstituicoes(): void { 
			listaInstituicoes.dataProvider = _instituicoes;
		}
	}
}