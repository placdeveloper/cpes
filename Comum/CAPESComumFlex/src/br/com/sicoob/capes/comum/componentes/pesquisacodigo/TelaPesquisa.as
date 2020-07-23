package br.com.sicoob.capes.comum.componentes.pesquisacodigo {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.eventos.EventNavegacao;
	import br.com.bancoob.componentes.paginacao.BarraPaginacao;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TelaPesquisa extends TelaPesquisaView {
		
		private var servico:ServicoJava;
		
		private var _campoCodigoAlfanumerico:Boolean = false;
		private var _classeServico:String;
		private var _metodoPesquisa:String;
		
		private var _quantidadeMinimaFiltroCodigo:Number;
		private var _quantidadeMinimaFiltroDescricao:Number;
		
		private var _quantidadeMaximaCaracteresCampoCodigo:Number = NaN;
		private var _quantidadeMaximaCaracteresCampoDescricao:Number = NaN;
		
		public static const REGISTRO_SELECIONADO:String = "REGISTRO_SELECIONADO_PESQUISA_CODIGO";
		
		public function TelaPesquisa() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evento:FlexEvent):void {
			comboTipo.dataProvider = obterValoresCombo();
			
			botaoPesquisar.addEventListener(MouseEvent.CLICK, pesquisar);
			botaoOk.addEventListener(MouseEvent.CLICK, obterRegistro);
			botaoFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			tabela.doubleClickEnabled = true;
			tabela.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, obterRegistro);
			tabela.addEventListener(ListEvent.CHANGE, selecionarRegistro);
			barraPaginacao.addEventListener(BarraPaginacao.EVENT_NAVEGACAO, navegar);
			
			campoValor.addEventListener(FlexEvent.ENTER, botaoEnterPressionado);
			comboTipo.addEventListener(ListEvent.CHANGE, configurarCampoValor);
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			servico = ServicoJavaUtil.getServico(classeServico, "OBTENDO DADOS...");
			servico.configurarDestino(destino);
		}
		
		private function navegar(evento:EventNavegacao):void {
			procurar(evento.pagina);
		}
		
		private function pesquisar(evento:MouseEvent = null):void {
			procurar(1);
		}
		
		private function procurar(pagina:int):void {
			if (servico != null && validarFiltrosPesquisa()) {
				MostraCursor.setBusyCursor("REALIZANDO A CONSULTA...", Application.application, MostraCursor.CURSOR_PESQUISAR);
				var operacao:Object = new Object();
				
				operacao[metodoPesquisa];
				servico.operations = operacao;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.pagina = pagina;
				
				if(comboTipo.selectedIndex == 1){
					dto.dados.codigo = _campoCodigoAlfanumerico ? StringUtils.trim(campoValor.text) : campoValor.valor;
				}else {
					dto.dados.descricao = campoValor.text;
				}
				
				servico.addEventListener(ResultEvent.RESULT, retornoPesquisa);
				servico.getOperation(metodoPesquisa).send(dto);
			}
		}
		
		private function retornoPesquisa(evento:ResultEvent):void {
			barraPaginacao.pagina = evento.result.paginaAtual;
			barraPaginacao.totalPaginas = evento.result.qtdPaginas;
			
			tabela.dataProvider = evento.result.dados.lista;
			
			if(evento.result.dados.lista.length > 0) {
				tabela.setFocus();
				tabela.selectedIndex = 0;
				botaoOk.enabled = true;
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function obterRegistro(evento:Event = null):void {
			this.dispatchEvent(new ObjetoEvent(REGISTRO_SELECIONADO, tabela.selectedItem));
			this.fecharJanela();
		}
		
		private function selecionarRegistro(evento:ListEvent = null):void {
			botaoOk.enabled = (tabela.selectedIndex != -1);
		}
		
		private function obterValoresCombo():ArrayCollection {
			var retorno:ArrayCollection = new ArrayCollection();
			var objeto:Object = new Object();
			objeto.nome = tituloCampoDescricao;
			retorno.addItem(objeto);
			objeto = new Object()
			objeto.nome = tituloCampoCodigo;
			retorno.addItem(objeto);
			
			return retorno;
		}
		
		private function botaoEnterPressionado(evento:FlexEvent = null):void {
			pesquisar();
		}
		
		private function fechar(evento:Event = null):void {
			fecharJanela();
		}
		
		private function configurarCampoValor(evento:Event = null):void {
			campoValor.text = "";
			if(comboTipo.selectedIndex == 1 && !_campoCodigoAlfanumerico){
				campoValor.restrict = "0123456789";
			}else {
				campoValor.restrict = null;
			}
			
			if(comboTipo.selectedIndex == 1) {
				if(!isNaN(_quantidadeMaximaCaracteresCampoCodigo)) {
					campoValor.maxChars = _quantidadeMaximaCaracteresCampoCodigo;
				}
			} else {
				if(!isNaN(_quantidadeMaximaCaracteresCampoDescricao)) {
					campoValor.maxChars = _quantidadeMaximaCaracteresCampoCodigo;
				}
			}
		}
		
		private function validarFiltrosPesquisa():Boolean {
			var retorno:Boolean = true;
			
			if(comboTipo.selectedIndex == 0){
				if(StringUtils.trim(campoValor.text).length < quantidadeMinimaFiltroDescricao){
					Alerta.show("O Filtro precisa conter pelo menos " + quantidadeMinimaFiltroDescricao + " (" + FormataNumero.extenso(quantidadeMinimaFiltroDescricao, FormataNumero.EXTENSO_MASCULINO) + ") " + (quantidadeMinimaFiltroDescricao == 1 ? "caracter!" : "caracteres!") , "Atenção");
					retorno = false;
				}
			} else if (comboTipo.selectedIndex == 1){
				if(StringUtils.trim(campoValor.text).length < quantidadeMinimaFiltroCodigo){
					Alerta.show("O Filtro precisa conter pelo menos " + quantidadeMinimaFiltroCodigo + " (" + FormataNumero.extenso(quantidadeMinimaFiltroCodigo, FormataNumero.EXTENSO_MASCULINO) + ") " + (quantidadeMinimaFiltroCodigo == 1 ? "caracter!" : "caracteres!") , "Atenção");
					retorno = false;
				}
			}
			
			return retorno;
		}
		
		public function limpar():void {
			if(initialized){
				comboTipo.selectedIndex = 0;
				campoValor.text = "";
				tabela.dataProvider = new ArrayCollection();
			}
		}
		
		public function get campoCodigoAlfanumerico():Boolean {
			return _campoCodigoAlfanumerico;
		}
		
		public function set campoCodigoAlfanumerico(valor:Boolean):void {
			_campoCodigoAlfanumerico = valor;
		}
		
		public function get classeServico():String {
			return _classeServico;
		}
		
		public function set classeServico(valor:String):void {
			_classeServico = valor;
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
		
		public function set quantidadeMaximaCaracteresCampoCodigo(valor:Number):void {
			this._quantidadeMaximaCaracteresCampoCodigo = valor;
		}
		
		public function set quantidadeMaximaCaracteresCampoDescricao(valor:Number):void {
			this._quantidadeMaximaCaracteresCampoDescricao = valor;
		}
	}
}