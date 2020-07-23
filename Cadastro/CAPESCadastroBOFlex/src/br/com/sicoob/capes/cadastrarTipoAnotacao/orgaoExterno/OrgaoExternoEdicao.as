package br.com.sicoob.capes.cadastrarTipoAnotacao.orgaoExterno {
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.vo.entidades.MapaTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoConsultaOrigemVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class OrgaoExternoEdicao extends OrgaoExternoEdicaoView {
		
		public static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.TipoAnotacaoFachada";
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		public static const EVENTO_ADICIONAR_LISTA : String = "adicionarListaMapa";
		public static const EVENTO_FECHAR_JANELA : String = "fecharJanelaOrgaoExterno";
		
		public var servico: ServicoJava;
				
		public function OrgaoExternoEdicao() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//**************
        // Eventos:
        //**************
        protected override function init(event: FlexEvent): void {
            super.init(event);
			
			this.barraBotoes.btnSalvar.addEventListener(MouseEvent.CLICK, adicionarItemLista);
			this.barraBotoes.btnFechar.addEventListener(MouseEvent.CLICK, fecharJanelaOrgaoExterno);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
  
		public function limparForm(): void {
			this.cmbTipoConsultaOrigem.selectedIndex = 0;
			this.codTipoOrigemInformacao.text = ""; 
		}
		
		private function montarObjeto(): MapaTipoAnotacaoVO {
			var vo: MapaTipoAnotacaoVO = new MapaTipoAnotacaoVO();
			vo.tipoConsultaOrigem = TipoConsultaOrigemVO(this.cmbTipoConsultaOrigem.selectedItem);
			vo.codigoTipoOrigemInformacao = this.codTipoOrigemInformacao.text;
			return vo;
		}
		
		/**
		 * Retorno dos dados da definição.
		 *  
		 * @param event O evento com o resultado do método.
		 */
		private function retornoObterDefinicoes(event: ResultEvent): void {
			this.cmbTipoConsultaOrigem.dataProvider = event.result.dados.listaTipoConsultaOrigem;
			limparForm();
		}
		
		private function adicionarItemLista(event:MouseEvent=null):void{
			var objeto:MapaTipoAnotacaoVO = montarObjeto();
			
			if(objeto.codigoTipoOrigemInformacao != null && objeto.codigoTipoOrigemInformacao != '' && objeto.tipoConsultaOrigem != null){
				var evento:Event = new ObjetoEvent(EVENTO_ADICIONAR_LISTA, objeto);
				this.dispatchEvent(evento);
				
				limparForm();
			}
		}
		
		private function fecharJanelaOrgaoExterno(event:MouseEvent=null):void {
			var evento:Event = new ObjetoEvent(EVENTO_FECHAR_JANELA, objeto);
			this.dispatchEvent(evento);
		}
		
		override protected function limparFormIncluir():void {
			limparForm();	
		}
			
	}
}