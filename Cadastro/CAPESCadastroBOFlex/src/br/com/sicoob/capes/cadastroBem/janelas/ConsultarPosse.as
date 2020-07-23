package br.com.sicoob.capes.cadastroBem.janelas {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoClassificacaoBemVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Componente de consulta as posses do bem selecionado.
	 * 
	 * @author bruno.carneiro
	 */
	public class ConsultarPosse extends ConsultarPosseView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.bem.BemPessoaCompartilhamentoFachada";
		private static const OPERACAO_CONSULTAR_POSSES: String = "consultarPossesBem";
		private static const MENSAGEM_CONSULTAR_POSSES:String = "Consultando as posses do bem...";
		
		private var servico:ServicoJava;
		
		private var _idBem:Number;
		private var _tipoClassificacaoBemEnum:TipoClassificacaoBemEnum;
		
		/**
		 * Construtor.
		 */
		public function ConsultarPosse(){
			super();
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_CONSULTAR_POSSES, ResultEvent.RESULT, retornoConsulta);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent = null):void {
			limpar();
			botaoFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		/**
		 * Realiza a consulta à partir do bem selecionado.
		 */
		public function consultar():void {
			MostraCursor.setBusyCursor(MENSAGEM_CONSULTAR_POSSES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var tipoClassificacaoBem:TipoClassificacaoBemVO = new TipoClassificacaoBemVO();
			tipoClassificacaoBem.codigo = _tipoClassificacaoBemEnum.codigo;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = _idBem;
			dto.dados.tipoClassificacao = tipoClassificacaoBem;
			
			servico.getOperation(OPERACAO_CONSULTAR_POSSES).send(dto);
		}
		
		/**
		 * Retorno do método de consulta.
		 */
		private function retornoConsulta(evento:ResultEvent):void {
			gridProprietarios.dataProvider = evento.result.dados.listaProprietarios;
			gridParticipantes.dataProvider = evento.result.dados.listaParticipantes;
			
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Faz a limpeza dos campos.
		 */
		public function limpar():void {
			gridProprietarios.dataProvider = new ArrayCollection();
			gridParticipantes.dataProvider = new ArrayCollection();
			
			gridProprietarios.validateDisplayList();
			gridProprietarios.validateNow();
			
			gridParticipantes.validateDisplayList();
			gridParticipantes.validateNow();
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
			this.servico.configurarDestino(destinoVO);
		}
		
		/**
		 * Adiciona o bem a ser usado na consulta.
		 */
		public function preencherInformacoes(idBem:Number, tipoClassificacao:TipoClassificacaoBemEnum):void {
			this._idBem = idBem;
			this._tipoClassificacaoBemEnum = tipoClassificacao;
			canvasParticipantes.visible = canvasParticipantes.includeInLayout = TipoClassificacaoBemEnum.BEM_IMOVEL.equals(tipoClassificacao);
		}
		
	}
}