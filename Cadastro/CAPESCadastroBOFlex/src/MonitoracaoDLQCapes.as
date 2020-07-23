package
{
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.plataformas.IModuloPlataformaMonitoracao;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.monitoracaoDlqCapes.MonitoracaoDLQCapesView;
	
	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class MonitoracaoDLQCapes extends MonitoracaoDLQCapesView implements IModuloPlataformaMonitoracao { 
		private var tmp:Timer;
		private var telaWidget:Object;

		private var servicoPesquisa:ServicoJava = new ServicoJava();
		private var destinoVO:DestinoVO;

		public static const FILA_REPROCESSADA: String = "filaReprocessada";
		public static const CLASSE_SERVICO: String =
				"br.com.sicoob.capes.cadastro.fachada.MonitoracaoDLQsCapesFachada";
		private static const OPERACAO_OBTER_DADOS: String = "obterDados";
		
		public function MonitoracaoDLQCapes() {
			super();
	
			servicoPesquisa = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoPesquisa.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);	
		}
		
		public function init(evt:FlexEvent):void {
			
			//cria timer de requisição de dados. 10 minutos
			tmp = new Timer(600000);
			tmp.addEventListener(TimerEvent.TIMER, pesquisar);	
			
			abaAtualizacaoCadastro.addEventListener(FILA_REPROCESSADA, filaReprocessada);
			inicializarServicos();
		}		
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
        
		private function onDestinoRecuperado(destino:DestinoVO):void {
		    
		    destinoVO = destino;
		    servicoPesquisa.configurarDestino(destinoVO);
		    
		    abaAtualizacaoCadastro.configurarDestino(destino);
			abaConsultasExternas.configurarDestino(destino);
		    pesquisar();
		}

	    //--------------------------------------------------------------------------
	    //  Métodos de callback.
	    //--------------------------------------------------------------------------				
		private function retornoPesquisar(event: ResultEvent): void {

			MostraCursor.removeBusyCursor();
			var dlqReplicacao: Object = event.result.dados["dlqReplicacao"];
			var dlqConsultasExternas: Object = event.result.dados["dlqConsultasExternas"];
			
			this.abaAtualizacaoCadastro.carregar(dlqReplicacao);
			this.abaConsultasExternas.carregar(dlqConsultasExternas);
			iniciarTempo();
		}
		
		private function filaReprocessada(evt:Event):void {
			pesquisar();
		}	

	    //--------------------------------------------------------------------------
	    //  Tratamento dos botões.
	    //--------------------------------------------------------------------------		
		private function pesquisar(evt:Event = null): void {
			
			pararTempo();
			
			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO;
            servicoPesquisa.getOperation(OPERACAO_OBTER_DADOS).send(dto);	
		}
		
		private function formatarData(valor: Date): String {
			return FormataData.formataDataHora(valor is IDateTime ? (valor as IDateTime).data : valor as Date);
		}
		
		// Metodos necessarios para a plataforma
		public function setWidget(tela:Object):void {
			telaWidget = tela;
		}
		
		public function pararTempo():void {
			telaWidget.imgCarregando.visible = false;
            tmp.stop();        
        }
        
        public function iniciarTempo():void {
        	telaWidget.imgCarregando.visible = true;
           	tmp.start();
		}
		
		public function mostraResumo():void	{
		}
		
		public function someResumo():void {
		} 
		
		public function mostraFiltro():void	{
		}
		
		public function someFiltro():void	{
		}
		// Fim métodos necessários para a plataforma      
	}
}