package br.com.sicoob.capes.comum.componentes.pesquisaendereco {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.LocalidadeVO;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	/**
	 * Componente para pesquisa de endereços.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class PesquisaEndereco extends PesquisaEnderecoView {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.PesquisaEnderecoFachada";
		private static const OPERACAO_PESQUISAR:String = "pesquisarEndereco";
		private static const OPERACAO_PESQUISAR_POR_ID_LOGRADOURO:String = "pesquisarEnderecoPorIdLogradouro";
		private static const MENSAGEM_PESQUISAR:String = "Obtendo o endereço...";
		
		private var servicoPesquisa:ServicoJava;
		private var servicoPesquisaPorId:ServicoJava;
		
		private var _janela:Janela;
		private var _destino:DestinoVO;
		private var _registro:LocalidadeVO;
		
		private var _idLogradouro:Number;
		private var _valorAtual:String = "";
		
		private var telaPesquisa:TelaPesquisaEndereco = new TelaPesquisaEndereco();
		
		/**
		 * Método construtor.
		 */
		public function PesquisaEndereco() {
			super();
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO", LocalidadeVO);
			servicoPesquisa = ServicoJavaUtil.getServico(CLASSE_SERVICO, "OBTENDO DADOS...", ResultEvent.RESULT, retornoPesquisa);
			servicoPesquisaPorId = ServicoJavaUtil.getServico(CLASSE_SERVICO, "OBTENDO DADOS...", ResultEvent.RESULT, retornoPesquisaPorId);
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent):void {
			campoCEP.addEventListener(FocusEvent.FOCUS_OUT, aoPerderFocoCampoCEP);
			botaoPesquisaCep.addEventListener(MouseEvent.CLICK, abrirJanelaPesquisa);
			telaPesquisa.addEventListener(TelaPesquisaEndereco.EVENTO_REGISTRO_SELECIONADO, obterRegistroSelecionado);
			
			configurarJanela();
		}
		
		/**
		 * Configura a janela e suas propriedades.
		 */
		private function configurarJanela():void {
			_janela = new Janela();
			_janela.width = 880;
			_janela.height = 500;
			_janela.title = "Pesquisar Endereço";
			_janela.addEventListener(CloseEvent.CLOSE, fecharJanelaPesquisa);
			_janela.addChild(telaPesquisa);
		}
		
		/**
		 * Configura o destino dos serviços.
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this._destino = destino;
			servicoPesquisa.configurarDestino(destino);
			servicoPesquisaPorId.configurarDestino(destino);
		}
		
		/**
		 * Método chamado quando o campo CEP perde o foco. 
		 */
		private function aoPerderFocoCampoCEP(evento:FocusEvent):void {
			if (StringUtil.trim(campoCEP.text).length == 0) {
				limpar();
			} else {
				if(realizaConsulta()){
					pesquisar();
				}
			}
		}
		
		/**
		 * Obtém o registro pesquisado.
		 */
		private function obterRegistroSelecionado(evento:ObjetoEvent = null):void {
			if (evento.objeto != null) {
				this._registro = evento.objeto as LocalidadeVO;
				_valorAtual = _registro.numCep;
				this.campoCEP.text = _registro.numCep;
				notificarSelecao();
				_janela.fecharJanela();
			} else {
				limpar();
			}
		}
		
		/**
		 * Limpa os campos para uma nova pesquisa.
		 */
		public function limpar():void {
			if(initialized){
				campoCEP.text = "";
				_valorAtual = "";
				_idLogradouro = NaN;
				this._registro = null;
				telaPesquisa.limpar();
				
				notificarSelecao();
			}
		}
		
		/**
		 * Notifica quando um registro foi pesquisado.
		 */
		private function notificarSelecao():void {
			this.dispatchEvent(new ObjetoEvent(TelaPesquisaEndereco.EVENTO_REGISTRO_SELECIONADO, this._registro));
		}
		
		/**
		 * Abre a janela de pesquisa.
		 */
		private function abrirJanelaPesquisa(evento:MouseEvent = null):void {
			telaPesquisa.configurarDestino(_destino);
			telaPesquisa.adicionarCEPPesquisado(this.campoCEP.text);
			_janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Método que executa a pesquisa.
		 */
		public function pesquisar():void {
			if(validarPesquisar()) {
				MostraCursor.setBusyCursor(MENSAGEM_PESQUISAR, Application.application, MostraCursor.CURSOR_PESQUISAR);
				_valorAtual = campoCEP.text;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.numCep = FlexUtil.stringVaziaParaNulo(campoCEP.text);
				
				servicoPesquisa.getOperation(OPERACAO_PESQUISAR).send(dto);
			}
		}
		
		/**
		 * Valida a pesquisa.
		 */
		private function validarPesquisar():Boolean {
			var cep:String = StringUtils.trim(campoCEP.text);
			
			if(cep != null && cep != "") {
				if(cep.length == 8 && cep != _valorAtual) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Retorno da pesquisa.
		 */
		private function retornoPesquisa(evento:ResultEvent):void {
			var lista:ArrayCollection = evento.result.dados.lista;
			if(lista.length == 1){
				_idLogradouro = NaN;
				this._registro = lista.getItemAt(0) as LocalidadeVO;
				this.campoCEP.text = _registro.numCep;
				notificarSelecao();
			}else {
				telaPesquisa.limpar();
				telaPesquisa.adicionarLista(lista);
				abrirJanelaPesquisa();
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Método de pesquisa por ID.
		 */
		private function pesquisarEnderecoPorIdLogradouro():void {
			MostraCursor.setBusyCursor(MENSAGEM_PESQUISAR, Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idLogradouro = FlexUtil.campoNumericoZeroParaNulo(_idLogradouro);
			
			servicoPesquisaPorId.getOperation(OPERACAO_PESQUISAR_POR_ID_LOGRADOURO).send(dto);
		}
		
		/**
		 * Retorno do método de pesquisa.
		 */
		private function retornoPesquisaPorId(evento:ResultEvent):void {
			var localidade:LocalidadeVO = evento.result.dados.localidade;
			if(localidade != null){
				_idLogradouro = NaN;
				_valorAtual = localidade.numCep;
				this.campoCEP.text = localidade.numCep;
				this._registro = localidade;
				notificarSelecao();
			}else {
				telaPesquisa.limpar();
			}
		}
		
		/**
		 * Carrega o registro à partir do Identificador. 
		 */
		public function carregarEndereco(idLogradouro:Number):void {
			if(idLogradouro && idLogradouro > 0 && _idLogradouro != idLogradouro){
				this._idLogradouro = idLogradouro;
				pesquisarEnderecoPorIdLogradouro();
			}
		}
		
		/**
		 * Verifica se a consulta pode ser realizada.
		 */
		private function realizaConsulta():Boolean {
			if(servicoPesquisa == null || _valorAtual == campoCEP.text){
				return false;
			}
			return true;
		}
		
		/**
		 * Fecha a janela de pesquisa.
		 */
		private function fecharJanelaPesquisa(evento:Event = null):void {
			_janela.fecharJanela();
		}
		
		/**
		 * Retorna o registro que foi pesquisado.
		 */
		public function obterRegistro():LocalidadeVO {
			return this._registro;
		}
	}
}