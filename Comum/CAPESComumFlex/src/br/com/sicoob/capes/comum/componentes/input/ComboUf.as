package br.com.sicoob.capes.comum.componentes.input
{
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class ComboUf extends Combo
	{
		private var servicoUF:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		
		static private const OPERACAO_OBTER_UFS: String = "obterUFs";
		public static const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.LocalidadeFachada";
			
		public function ComboUf(){
			super();
			this.labelField = "siglaUF";
			this.servicoUF.addEventListener(ResultEvent.RESULT, retornoObterUfs);
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		public function init(event:Event):void {
			inicializarServicos();
		} 		
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
		 	servicoUF.configurarDestino(destino);
		 	obterUFs();   
		}

	    //--------------------------------------------------------------------------
	    //  Listar as UFs.
	    //--------------------------------------------------------------------------		
		private function obterUFs():void {
			
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			servicoUF.getOperation(OPERACAO_OBTER_UFS).send(dto);
		}		

		private function retornoObterUfs(evt:ResultEvent):void {
			this.dataProvider = evt.result.dados.ufs;
			MostraCursor.removeBusyCursor();
		}

	}
}