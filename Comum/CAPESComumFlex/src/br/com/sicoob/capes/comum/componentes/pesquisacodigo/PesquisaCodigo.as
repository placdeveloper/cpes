package br.com.sicoob.capes.comum.componentes.pesquisacodigo {
	import flash.display.DisplayObject;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.EventoBarraBotoes;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	public class PesquisaCodigo extends PesquisaCodigoView {
		
		private var servico:ServicoJava;
		
		private var _exibirCampoCodigo:Boolean = true;
		private var _campoCodigoAlfanumerico:Boolean = false;
		private var _classeServico:String;
		private var _metodoPesquisa:String = "pesquisarEntidadeComponente";
		private var _campoCodigo:String = "codigo";
		private var _campoDescricao:String = "descricao";
		private var _tituloCampoCodigo:String = "CÓDIGO";
		private var _tituloCampoDescricao:String = "DESCRIÇÃO";
		private var _tituloJanelaPesquisa:String = "PESQUISAR";
		
		private var _quantidadeMinimaFiltroCodigo:Number = 1;
		private var _quantidadeMinimaFiltroDescricao:Number = 3;
		
		private var _quantidadeMaximaCaracteresCampoCodigo:Number = NaN;
		private var _quantidadeMaximaCaracteresCampoDescricao:Number = NaN;
		
		private var _registro:Object;
		private var _janela:Janela;
		private var _destino:DestinoVO;
		
		private var _valorAtual:String = "";
		
		private var telaPesquisa:TelaPesquisa = new TelaPesquisa();
		
		public function PesquisaCodigo() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evento:FlexEvent):void {
			if(!_campoCodigoAlfanumerico){
				textoCodigo.restrict = "0123456789";
			}
			
			textoCodigo.addEventListener(FocusEvent.FOCUS_OUT, aoPerderFocoCampoCodigo);
			botaoPesquisar.addEventListener(MouseEvent.CLICK, abrirJanelaPesquisa);
			telaPesquisa.addEventListener(TelaPesquisa.REGISTRO_SELECIONADO, obterRegistroSelecionado);
			
			if(!isNaN(_quantidadeMaximaCaracteresCampoCodigo)) {
				textoCodigo.maxChars = _quantidadeMaximaCaracteresCampoCodigo;
			}
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this._destino = destino;
			servico = ServicoJavaUtil.getServico(classeServico, "OBTENDO DADOS...");
			servico.configurarDestino(destino);
		}
		
		private function aoPerderFocoCampoCodigo(event:FocusEvent):void {
			if (StringUtil.trim(textoCodigo.text).length == 0) {
				limpar();
			} else {
				pesquisar();
			}
		}
		
		private function obterRegistroSelecionado(evento:ObjetoEvent = null):void {
			if (evento.objeto != null) {
				_registro = evento.objeto;
				atualizarCampos();
				notificarSelecao();
			} else {
				limpar();
			}
		}
		
		public function limpar():void {
			_valorAtual = "";
			_registro = null;
			atualizarCampos();
			notificarSelecao();
			
			telaPesquisa.limpar();
		}
		
		private function atualizarCampos():void {
			if (_registro != null) {
				this.textoDescricao.text = StringUtils.trim(formatarDescricao(String(ReflectionUtils.recuperarPropriedade(_registro, campoDescricao))));
				this.textoCodigo.text = StringUtils.trim(String(ReflectionUtils.recuperarPropriedade(_registro, campoCodigo)));
			} else {
				this.textoDescricao.text = "";
				this.textoCodigo.text = "";
			}
		}
		
		private function formatarDescricao(descricao:String):String {
			var texto:String = descricao;
			var tamanhoDisponivel:int = this.width - (textoCodigo.width + botaoPesquisar.width + 20); 
			
			if (texto != null) {
				// Se o tamanho do texto for maior que o tamanho na tela, devemos truncá-lo.
				while (textoDescricao.measureText(texto).width >= tamanhoDisponivel && texto.length > 0) {
					texto = texto.substring(0, texto.length - 1);
				}
			}else {
				texto = "";
			}
			return texto;
		}
		
		private function notificarSelecao():void {
			var evento:SelecaoEvent = new SelecaoEvent();
			evento.objeto = this._registro;
			dispatchEvent(evento);
		}
		
		private function abrirJanelaPesquisa(evento:MouseEvent = null):void {
			if (_janela == null) {
				_janela = new Janela();
				
				telaPesquisa.classeServico = classeServico;
				telaPesquisa.metodoPesquisa = metodoPesquisa;
				telaPesquisa.campoCodigo = campoCodigo;
				telaPesquisa.campoDescricao = campoDescricao;
				telaPesquisa.campoCodigoAlfanumerico = campoCodigoAlfanumerico;
				telaPesquisa.tituloCampoCodigo = tituloCampoCodigo;
				telaPesquisa.tituloCampoDescricao = tituloCampoDescricao;
				telaPesquisa.quantidadeMinimaFiltroCodigo = quantidadeMinimaFiltroCodigo;
				telaPesquisa.quantidadeMinimaFiltroDescricao = quantidadeMinimaFiltroDescricao;
				telaPesquisa.quantidadeMaximaCaracteresCampoCodigo = _quantidadeMaximaCaracteresCampoCodigo;
				telaPesquisa.quantidadeMaximaCaracteresCampoDescricao = _quantidadeMaximaCaracteresCampoDescricao;
				telaPesquisa.configurarDestino(_destino);
				
				_janela.width = 600;
				_janela.height = 420;
				_janela.title = tituloJanelaPesquisa;
				_janela.addEventListener(CloseEvent.CLOSE, fecharJanelaPesquisa);
				_janela.addChild(telaPesquisa);
				
				telaPesquisa.barraBotoes.addEventListener("ok", fecharJanelaPesquisa);
				telaPesquisa.barraBotoes.addEventListener("fechar", fecharJanelaPesquisa);
			}
			_janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		public function pesquisar():void {
			if (realizaConsulta()) {
				_valorAtual = textoCodigo.text;
				var operacao:Object = new Object();
				
				operacao[metodoPesquisa];
				servico.operations = operacao;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.codigo = _campoCodigoAlfanumerico ? StringUtils.trim(textoCodigo.text) : textoCodigo.valor;
				dto.dados.pagina = 1;
				
				servico.addEventListener(ResultEvent.RESULT, retornoPesquisa);
				servico.getOperation(metodoPesquisa).send(dto);
			}
		}
		
		private function retornoPesquisa(event:ResultEvent) : void {
			if (event.result.dados.lista.length > 0) {
				_registro = event.result.dados.lista[0];
				atualizarCampos();
				notificarSelecao();
			} else {
				limpar();
			}
		}
		
		private function realizaConsulta():Boolean {
			if(servico == null || _valorAtual == textoCodigo.text){
				return false;
			}
			return true;
		}
		
		private function fecharJanelaPesquisa(event:EventoBarraBotoes=null):void {
			_janela.fecharJanela();
		}
		
		public function obterRegistro():Object {
			return _registro;
		}
		
		public function set quantidadeMaximaCaracteresCampoCodigo(valor:Number):void {
			this._quantidadeMaximaCaracteresCampoCodigo = valor;
		}
		
		public function set quantidadeMaximaCaracteresCampoDescricao(valor:Number):void {
			this._quantidadeMaximaCaracteresCampoDescricao = valor;
		}
		
		public function get exibirCampoCodigo():Boolean {
			return _exibirCampoCodigo;
		}
		
		public function set exibirCampoCodigo(valor:Boolean):void {
			_exibirCampoCodigo = valor;
		}
		
		public function get classeServico():String {
			return _classeServico;
		}
		
		public function set classeServico(valor:String):void {
			_classeServico = valor;
		}
		
		public function get campoCodigo():String {
			return _campoCodigo;
		}
		
		public function set campoCodigo(valor:String):void {
			_campoCodigo = valor;
		}
		
		public function get campoDescricao():String {
			return _campoDescricao;
		}
		
		public function set campoDescricao(valor:String):void {
			_campoDescricao = valor;
		}
		
		public function get campoCodigoAlfanumerico():Boolean {
			return _campoCodigoAlfanumerico;
		}
		
		public function set campoCodigoAlfanumerico(valor:Boolean):void {
			_campoCodigoAlfanumerico = valor;
		}
		
		public function get tituloCampoCodigo():String {
			return _tituloCampoCodigo;
		}
		
		public function set tituloCampoCodigo(valor:String):void {
			_tituloCampoCodigo = valor;
		}
		
		public function get tituloCampoDescricao():String {
			return _tituloCampoDescricao;
		}
		
		public function set tituloCampoDescricao(valor:String):void {
			_tituloCampoDescricao = valor;
		}
		
		public function get tituloJanelaPesquisa():String {
			return _tituloJanelaPesquisa;
		}
		
		public function set tituloJanelaPesquisa(valor:String):void {
			_tituloJanelaPesquisa = valor;
		}
		
		public function get metodoPesquisa():String {
			return _metodoPesquisa;
		}
		
		public function set metodoPesquisa(valor:String):void {
			_metodoPesquisa = valor;
		}
		
		public function get quantidadeMinimaFiltroCodigo():Number {
			return _quantidadeMinimaFiltroCodigo;
		}
		
		public function set quantidadeMinimaFiltroCodigo(valor:Number):void {
			_quantidadeMinimaFiltroCodigo = valor;
		}
		
		public function get quantidadeMinimaFiltroDescricao():Number {
			return _quantidadeMinimaFiltroDescricao;
		}
		
		public function set quantidadeMinimaFiltroDescricao(valor:Number):void {
			_quantidadeMinimaFiltroDescricao = valor;
		}
		
	}
}