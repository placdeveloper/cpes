package br.com.sicoob.capes.utils.plataformaatendimento
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.IValidarAbertura;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IListaPlataformaAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.TelaPlataformaAtendimentoBase;
	import br.com.bancoob.sisbr.eventos.EventValidacaoAbertura;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ConfiguracoesCAPES;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TelaPlataformaAtendimentoCAPESBase extends TelaPlataformaAtendimentoBase implements IValidarAbertura {
		
		private var servico:ServicoJava = new ServicoJava();
		private var destinoVO:DestinoVO;
		protected static var pessoa:PessoaPlataformaVO;
		
		private static const PRODUTOS_BANCOOB : String = "produtosBancoob";
		public static const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.ProdutosBancoobFachada";
		private var _produtosBancoob:Boolean = false;
		
		//		public static const MENSAGEM_ALTERACAO_CORPO:String = "Não existem alterações a serem salvas neste registro.";
		//		public static const MENSAGEM_ALTERACAO_CABECALHO:String = "REGISTRO NÃO ALTERADO";
		
		public function TelaPlataformaAtendimentoCAPESBase() {
			super();
			include '../RegistroClasses.as';
			
			servico.source = CLASSE_SERVICO;
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;        	
			servico.mensagemEspera = "Validando pessoa selecionada...";
			servico.addEventListener(ResultEvent.RESULT, validarAbertura_onResult);
			servico.addEventListener(FaultEvent.FAULT, validarAbertura_onError);
			
			pessoa = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		}
		
		//		protected override function gravarClicado(event:MouseEvent=null):void {
		//			
		//			if (!_novo && _telaEdicao.verificarAlteracoes()) {
		//				Alerta.show(MENSAGEM_ALTERACAO_CORPO, MENSAGEM_ALTERACAO_CABECALHO, Alerta.ALERTA_INFORMACAO);
		//			} else {
		//				_telaEdicao.gravarRegistro();
		//			}
		//		}
		
		public function get produtosBancoob():Boolean {
			return _produtosBancoob;
		}
		
		//--------------------------------------------------------------------------
		//  Métodos da interface.
		//--------------------------------------------------------------------------	
		public function validarAbertura(params:Object=null):void {
			this._produtosBancoob = (this.parametros != null) 
				&& (this.parametros.indexOf(PRODUTOS_BANCOOB) >= 0);
			inicializarServicos();			
		}
		
		public function validarAbertura_onResult(evt:ResultEvent):void {
			this.dispatchEvent(new EventValidacaoAbertura(
				EventValidacaoAbertura.EVENTO_VALIDACAO_OK));
		}
		
		public function validarAbertura_onError(evt:FaultEvent):void {	
			this.dispatchEvent(new EventValidacaoAbertura(
				EventValidacaoAbertura.EVENTO_VALIDACAO_ERRO));
		}
		
		/**
		 * Método para ser sobreescrito nos módulos.
		 * Deverá remover os listeners e objetos que podem ficar na memória.
		 **/
		public override function dispose():void {
			super.dispose();
			servico = null;
			pessoa = null;
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------		
		private function inicializarServicos():void {
			destinoVO = ConfiguracoesCAPES.destinoCAPES;
			configurarDestinos();
		}
		
		private function configurarDestinos():void{
			servico.configurarDestino(destinoVO);
			configurarDestinosServicos(destinoVO);
			validarProdutosBancoob();		
		}
		
		/** Função que deve ser sobrescrita para carregar os serviços desejados. */
		protected function configurarDestinosServicos(destinoVO:DestinoVO):void{
		}
		
		override protected function registroCarregado(evt:Event):void
		{
			exibirBotaoAlteracao();
			
			super.registroCarregado(evt);
			
			verificarRegistroEmAlteracao();
		}
		
		protected function verificarRegistroEmAlteracao():void
		{
			if (isRegistroEmAprovacao()) {
				Alerta.show("O registro encontra-se em alteração, favor aguardar a finalização do fluxo de autorização.","Aviso");
			}
		}
		
		protected function exibirBotaoAlteracao():void
		{
			if (isRegistroEmAprovacao()) {
				listaBotoes.botOk.enabled = false;
				listaBotoes.botCancelar.enabled = false;
			} else {
				listaBotoes.botOk.enabled = true;
				listaBotoes.botCancelar.enabled = true;
			}
		}
		
		override protected function registroGravado(event:Event):void {
			super.registroGravado(event);
			
			verificarOperacaoPendente();
		}
		
		//Listener da chamada de negocio *excluirRegistro*
		override protected function registroExcluido(evt:Event):void {
			MostraCursor.removeBusyCursor();
			
			mostraBotoesLista();
			
			if(possuiLista && !isRegistroEmAprovacao())
				_telaLista.obterGrid().dataProvider.removeItemAt(_telaLista.obterGrid().selectedIndex);
			
			verificarOperacaoPendente();
		}
		
		protected function verificarOperacaoPendente():void {
			if (isRegistroEmAprovacao()) {
				Alerta.show("Operação enviada para autorização do responsável, acesse o módulo \"Cliente\" opção \"Autorização\" para acompanhar.","Aviso");			
			}
		} 
		
		protected function isRegistroEmAprovacao():Boolean {
			if (super._telaEdicao is IEdicaoPlataformaAtendimentoCAPES) {
				return (super._telaEdicao as IEdicaoPlataformaAtendimentoCAPES).isRegistroBloqueadoAlteracao();
			}
			return false;
		}
		
		//--------------------------------------------------------------------------
		//  Consultar cliente migrado.
		//--------------------------------------------------------------------------			
		private function validarProdutosBancoob(): void {
			
			if (this.produtosBancoob) {
				var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa);
				servico.getOperation("validarAberturaProdutosBancoob").send(dto);
			} else {
				validarAbertura_onResult(null);
			}
		}
		
		//--------------------------------------------------------------------------
		//  Tratamento da seleção de cliente no CAPES.
		//--------------------------------------------------------------------------					
		public static function getPessoaPlataforma(): PessoaPlataformaVO {
			return pessoa;
		}
		
		public static function getPessoaSelecionada():PessoaCompartilhamentoVO {
			return ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa);
		}
		
		public function adicionaTela(arg:ITelaBasePlataformaAtendimento):void {
			vsTelas.addChild(DisplayObject(arg));
		}
		
		//Metodo usado para selecionar tela no viewStack da interface
		public function selecionaTela(arg:ITelaBasePlataformaAtendimento):void {
			for(var k:int = 0; k<vsTelas.numChildren; k++){
				if(vsTelas.getChildAt(k) == arg){
					vsTelas.selectedIndex = k;
					break;
				}
			}
		}
		
		//Metodo usado para selecionar tela no viewStack da interface
		public function selecionaTelaListaPlataforma(arg:IListaPlataformaAtendimento):void {
			for(var k:int = 0; k<vsTelas.numChildren; k++){
				if(vsTelas.getChildAt(k) == arg){
					vsTelas.selectedIndex = k;
					break;
				}
			}
		}
	}
}