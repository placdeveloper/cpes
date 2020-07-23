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
	import br.com.sicoob.capes.monitoracaoFilasCapes.MonitoracaoFilasCapesView;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class MonitoracaoFilasCapes extends MonitoracaoFilasCapesView implements IModuloPlataformaMonitoracao { 
		private var tmp:Timer;
		private var telaWidget:Object;
		
		private var servicoPesquisa:ServicoJava = new ServicoJava();
		private var destinoVO:DestinoVO;

		private static const CLASSE_SERVICO: String =
				"br.com.sicoob.capes.cadastro.fachada.MonitoracaoFilasCapesFachada";
		private static const OPERACAO_OBTER_DADOS: String = "obterDados";
		
		public function MonitoracaoFilasCapes() {
			super();
	
			servicoPesquisa = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoPesquisa.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);	
		}
		
		public function init(evt:FlexEvent):void {
			
			//cria timer de requisição de dados. 10 minutos
			tmp = new Timer(600000);
			tmp.addEventListener(TimerEvent.TIMER, pesquisar);	
			
			btAtualizar.addEventListener(MouseEvent.CLICK, pesquisar);
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
		    pesquisar();
		}

	    //--------------------------------------------------------------------------
	    //  Métodos de callback.
	    //--------------------------------------------------------------------------				
		private function retornoPesquisar(event: ResultEvent): void {

			MostraCursor.removeBusyCursor();
			var dlqConsultasExternas: Object = event.result.dados["dlqConsultasExternas"];
			var dlqReplicacao: Object = event.result.dados["dlqReplicacao"];
			var filaConsultasExternas: Object = event.result.dados["filaConsultasExternas"];
			var filaReplicacao: Object = event.result.dados["filaReplicacao"];
			var naoEnviadas : Object = event.result.dados["mensagensNaoEnviadas"];
			var naoProcessadas : Object = event.result.dados["mensagensNaoProcessadas"];
			var dataConsulta: Date = event.result.dados["dataConsulta"];
			
			this.canvasNaoEnviadas.carregarDados(naoEnviadas);
			this.canvasNaoProcessadas.carregarDados(naoProcessadas);
			this.canvasFilaConsultasExternas.carregarDados(filaConsultasExternas);
			this.canvasFilaAtualizacaoCadastral.carregarDados(filaReplicacao);
			this.canvasDlqConsultasExternas.carregarDados(dlqConsultasExternas);
			this.canvasDlqAtualizacaoCadastral.carregarDados(dlqReplicacao);
			this.lbDataConsulta.text = formatarData(dataConsulta);
			
			iniciarTempo();
		}
		
		private function formatarData(valor: Date): String {
			return FormataData.formataDataHora(valor is IDateTime ? (valor as IDateTime).data : valor as Date);
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
        
		// Metodos necessarios para a plataforma (IModuloPlataformaMonitoracao)
		public function setWidget(tela:Object):void {
			telaWidget = tela;
		}
        
        public function iniciarTempo():void {
        	telaWidget.imgCarregando.visible = true;
           	tmp.start();
		}
		
		public function pararTempo():void {
			telaWidget.imgCarregando.visible = false;
            tmp.stop();        
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