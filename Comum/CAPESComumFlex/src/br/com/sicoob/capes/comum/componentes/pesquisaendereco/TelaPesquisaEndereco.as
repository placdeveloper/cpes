package br.com.sicoob.capes.comum.componentes.pesquisaendereco {
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;

	/**
	 * Tela de pesquisa de endereços.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class TelaPesquisaEndereco extends TelaPesquisaEnderecoView {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.PesquisaEnderecoFachada";
		private static const OPERACAO_PESQUISAR:String = "pesquisarEndereco";
		
		private var servico:ServicoJava;
		
		public static const EVENTO_REGISTRO_SELECIONADO:String = "EVENTO_REGISTRO_SELECIONADO_CONSULTA_ENDERECO";
		
		/**
		 * Método construtor.
		 */
		public function TelaPesquisaEndereco() {
			super();
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO", LocalidadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UF", UFVO);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent = null):void {
			botaoPesquisar.addEventListener(MouseEvent.CLICK, consultar);
			botaoLimpar.addEventListener(MouseEvent.CLICK, limpar);
			
			gridEnderecos.addEventListener(ListEvent.ITEM_CLICK, registroSelecionado);
			gridEnderecos.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, registroSelecionado);
			gridEnderecos.doubleClickEnabled = true;
			
			campoCepLocalidade.addEventListener(KeyboardEvent.KEY_DOWN, enterPressionado);
			comboUF.addEventListener(KeyboardEvent.KEY_DOWN, enterPressionado);
			campoMunicipio.addEventListener(KeyboardEvent.KEY_DOWN, enterPressionado);
			campoBairro.addEventListener(KeyboardEvent.KEY_DOWN, enterPressionado);
			campoLogradouro.addEventListener(KeyboardEvent.KEY_DOWN, enterPressionado);
		}	 
		
		/**
		 * Evento quando a tecla 'Enter' é pressionada.
		 */
		private function enterPressionado(evento:KeyboardEvent = null):void {
			if(Keyboard.ENTER == evento.keyCode){
				consultar();
			}
		}
		
		/**
		 * Notifica que o registro foi selecionado para o módulo principal.
		 */
		private function registroSelecionado(evento:ListEvent = null):void {
			dispatchEvent(new ObjetoEvent(EVENTO_REGISTRO_SELECIONADO, evento.target.selectedItem));
		}
		
		/**
		 * Realiza a consulta à partir dos filtros.
		 */
		private function consultar(evento:MouseEvent = null):void {
			MostraCursor.setBusyCursor("OBTENDO DADOS...", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.nomeBairro = FlexUtil.stringVaziaParaNulo(campoBairro.text);
			dto.dados.nomeLocalidade = FlexUtil.stringVaziaParaNulo(campoMunicipio.text);
			dto.dados.nomeLogradouro = FlexUtil.stringVaziaParaNulo(campoLogradouro.text);
			dto.dados.numCep = FlexUtil.stringVaziaParaNulo(campoCepLocalidade.text);
			
			var uf:UFVO = comboUF.selectedItem as UFVO;
			if(uf != null) {
				dto.dados.siglaUF = uf.siglaUF;
			}
			
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			servico.getOperation(OPERACAO_PESQUISAR).send(dto);
		}
		
		/**
		 * Faz a limpeza dos campos.
		 */
		public function limpar(evento:MouseEvent = null):void {
			if(initialized){
				campoCepLocalidade.text = "";
				comboUF.selectedIndex = 0;
				campoMunicipio.text = "";
				campoBairro.text = "";
				campoLogradouro.text = "";
			}
		}
		
		/**
		 * Retorno do método pesquisar.
		 */
		private function retornoPesquisar(evento:ResultEvent):void {
			gridEnderecos.dataProvider = evento.result.dados.lista;
			dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
			gridEnderecos.validateNow();
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Adiciona os registros à grid.
		 */
		public function adicionarLista(lista:ArrayCollection):void {
			gridEnderecos.dataProvider = lista;
			dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
			gridEnderecos.validateNow();
		}
		
		/**
		 * Preenche o campo de CEP com o valor consultado.
		 */
		public function adicionarCEPPesquisado(cep:String):void {
			campoCepLocalidade.text = cep;
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		/**
		 * Faz a configuração do destino dos serviços.
		 */
		public function configurarDestino(destino:DestinoVO):void {
			servico.configurarDestino(destino);
		}
	}
}