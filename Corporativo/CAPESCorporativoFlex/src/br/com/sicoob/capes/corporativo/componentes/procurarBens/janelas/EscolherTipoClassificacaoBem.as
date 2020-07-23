package br.com.sicoob.capes.corporativo.componentes.procurarBens.janelas {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoClassificacaoBemVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Janela com as opções do tipo de classificação do bem.
	 * 
	 * @author bruno.carneiro
	 */
	public class EscolherTipoClassificacaoBem extends EscolherTipoClassificacaoBemView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.bem.BemPessoaCompartilhamentoFachada";
		private static const OPERACAO_CONSULTAR_TIPOS_CLASSIFICACAO:String = "obterClassificacoesBem";
		private static const MENSAGEM_CONSULTAR_TIPOS_CLASSIFICACAO:String = "Obtendo definições...";
		
		private var servico:ServicoJava;
		
		public static const EVENTO_TIPO_CLASSIFICACAO_SELECIONADO:String = "eventoTipoClassificacaoBemSelecionado";
		
		/**
		 * Construtor.
		 */
		public function EscolherTipoClassificacaoBem(){
			super();
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_CONSULTAR_TIPOS_CLASSIFICACAO, ResultEvent.RESULT, retornoConsulta);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent = null):void {
			limpar();
			botaoSelecionar.addEventListener(MouseEvent.CLICK, selecionarRegistro);
			botaoFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		/**
		 * Realiza a consulta à partir do bem selecionado.
		 */
		private function consultar():void {
			MostraCursor.setBusyCursor(MENSAGEM_CONSULTAR_TIPOS_CLASSIFICACAO, Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico.getOperation(OPERACAO_CONSULTAR_TIPOS_CLASSIFICACAO).send(new RequisicaoReqDTO());
		}
		
		/**
		 * Retorno do método de consulta.
		 */
		private function retornoConsulta(evento:ResultEvent):void {
			comboClassificacao.dataProvider = evento.result.dados.listaTipoClassificacaoBem;
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Faz a limpeza dos campos.
		 */
		public function limpar():void {
			comboClassificacao.dataProvider = new ArrayCollection();
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(evento:Event = null):void {
			fecharJanela();
		}
		
		/**
		 * Realiza a configuração do destino dos serviços.
		 */
		public function configurarDestino(destinoVO:DestinoVO):void {
			servico.configurarDestino(destinoVO);
			consultar();
		}
		
		/**
		 * Seleciona o registro
		 */
		private function selecionarRegistro(evento:Event):void {
			executarSeValido(gravar);
		}
		
		/**
		 * Seleciona o registro
		 */
		private function gravar(evento:Event = null):void {
			var tipoClassificacaoBem:TipoClassificacaoBemVO = comboClassificacao.selectedItem as TipoClassificacaoBemVO;
			
			if(tipoClassificacaoBem != null) {
				dispatchEvent(new ObjetoEvent(EVENTO_TIPO_CLASSIFICACAO_SELECIONADO, tipoClassificacaoBem));
			}
			
			fecharJanela();
		}
	}
}