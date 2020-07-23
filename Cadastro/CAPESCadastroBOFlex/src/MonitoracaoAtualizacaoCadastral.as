package {
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.plataformas.IModuloPlataformaMonitoracao;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.MensagemReplicacaoVO;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.MonitoracaoAtualizacaoCadastralView;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas.IAbaMonitoracaoAtualizacaoCadastral;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas.abaNaoEnviadas;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas.abaNaoProcessadas;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas.abaProcessadasComErro;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class MonitoracaoAtualizacaoCadastral extends MonitoracaoAtualizacaoCadastralView 
			implements IModuloPlataformaMonitoracao {

		private static const MARCADOR_ABA : String = "* "; 
		public static const CLASSE_SERVICO: String =
				"br.com.sicoob.capes.cadastro.fachada.MonitoracaoAtualizacaoCadastralFachada";
		private static const OPERACAO_OBTER_DADOS: String = "obterDados";
		public static const ABA_NAO_ENVIADAS : int = 0;
		public static const ABA_NAO_PROCESSADAS : int = 1;
		public static const ABA_PROCESSADAS_COM_ERRO : int = 2;
		public static const TAB_CHANGED : String = "abaChanged";

		private var timer:Timer;
		private var telaWidget:Object;
		private var servicoPesquisa:ServicoJava;
		private var valorAnteriorTipoPesquisa : Object = null;
		
		public function MonitoracaoAtualizacaoCadastral() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.MensagemReplicacao", 
					MensagemReplicacaoVO);
			this.servicoPesquisa = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo dados...", 
					ResultEvent.RESULT, onResultPesquisar);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);	
		}
		
		public function init(evt:FlexEvent):void {

			this.navegacaoTab.creationPolicy = "all";
			this.navegacaoTab.addEventListener(Event.CHANGE, onTabChanged);
			
			// (10 minutos) * (60 segundos) * (1000 milisegundos) 
			this.timer = new Timer(10 * 60 * 1000);
			timer.addEventListener(TimerEvent.TIMER, pesquisar); 

			this.btPesquisar.addEventListener(MouseEvent.CLICK, pesquisar);

			this.naoEnviadas.addEventListener(abaNaoEnviadas.EVENTO_MENSAGENS_ENVIADAS, pesquisar);
			this.naoProcessadas.addEventListener(abaNaoProcessadas.EVENTO_MENSAGENS_REENVIADAS, pesquisar);
			this.processadasComErro.addEventListener(abaProcessadasComErro.EVENTO_ATUALIZACOES_REPROCESSADAS, pesquisar);
			this.processadasComErro.addEventListener(abaProcessadasComErro.EVENTO_ATUALIZACAO_EXCLUIDA, pesquisar);
			inicializarServicos();
		}
		
		private function pesquisar(evt:Event = null): void {
			
			pararTempo();
			
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO()
			if (this.grupoAtualizacao.selectedValue) {
				dto.dados.situacao = this.navegacaoTab.selectedChild.id;
			}
            servicoPesquisa.getOperation(OPERACAO_OBTER_DADOS).send(dto);	
		}

		private function onResultPesquisar(event: ResultEvent): void {
			
			if (rdTodas.selected) {
				this.naoEnviadas.dados = event.result.dados.naoEnviadas;
				marcarAba(this.naoEnviadas, !isAbaNaoEnviadasSelecionada()
						&& (this.naoEnviadas.mensagens.length > 0));
				
				this.naoProcessadas.dados = event.result.dados.naoProcessadas;
				marcarAba(this.naoProcessadas, !isAbaNaoProcessadasSelecionada() 
						&& (this.naoProcessadas.mensagens.length > 0));				
				
				this.processadasComErro.dados = event.result.dados.processadasComErro;
				marcarAba(this.processadasComErro, !isAbaProcessadasComErroSelecionada()
						&& (this.processadasComErro.mensagens.length > 0));				
			} else {
				var aba : IAbaMonitoracaoAtualizacaoCadastral = this.navegacaoTab.selectedChild 
						as IAbaMonitoracaoAtualizacaoCadastral;
				var id : String = aba.id;
				aba.dados = event.result.dados[id];
				if (this.valorAnteriorTipoPesquisa != null) {
					this.grupoAtualizacao.selectedValue = this.valorAnteriorTipoPesquisa;
					this.valorAnteriorTipoPesquisa = null;
				}
			}
			iniciarTempo();
		}
		
		public function isAbaNaoEnviadasSelecionada() : Boolean {
			return this.navegacaoTab.selectedIndex == ABA_NAO_ENVIADAS;
		}
		
		public function isAbaNaoProcessadasSelecionada() : Boolean {
			return this.navegacaoTab.selectedIndex == ABA_NAO_PROCESSADAS;
		}
		
		public function isAbaProcessadasComErroSelecionada() : Boolean {
			return this.navegacaoTab.selectedIndex == ABA_PROCESSADAS_COM_ERRO;
		}
		
		private function onTabChanged(evento : Event) : void {
			var aba : IAbaMonitoracaoAtualizacaoCadastral = evento["relatedObject"] as IAbaMonitoracaoAtualizacaoCadastral;
			marcarAba(aba, false);
		}
		
		private function marcarAba(aba:IAbaMonitoracaoAtualizacaoCadastral, marcar:Boolean = true):void {
			
			if (aba != null) {
				if ((aba.label.indexOf(MARCADOR_ABA) != 0) && marcar) {
					aba.label = MARCADOR_ABA + aba.label;
				} else if ((aba.label.indexOf(MARCADOR_ABA) == 0) && !marcar) {
					aba.label = aba.label.substring(MARCADOR_ABA.length);
				}
			}
		}
		
		// CONFIGURACAO DO DESTINO DOS SERVICOS
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
        
		private function onDestinoRecuperado(destino:DestinoVO):void {
		    
		    servicoPesquisa.configurarDestino(destino);
		    
		    this.naoEnviadas.configurarDestino(destino);
		    this.naoProcessadas.configurarDestino(destino);
		    this.processadasComErro.configurarDestino(destino);
//		    pesquisar();
		}
		
		// Implementação de IModuloPlataformaMonitoracao
		public function setWidget(tela:Object):void {
			telaWidget = tela;
		}
        
        public function iniciarTempo():void {
        	telaWidget.imgCarregando.visible = true;
        	timer.start();
		}
		
		public function pararTempo():void {
			telaWidget.imgCarregando.visible = false;
			timer.stop();
        }
		
		public function mostraFiltro():void	{
		}
		
		public function someFiltro():void {
		}
		
		public function mostraResumo():void {
		}
		
		public function someResumo():void {
		} 
		
	}
}