package
{
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.autorizarAlteracoes.AutorizacaoSelecaoView;
	import br.com.sicoob.capes.autorizarAlteracoes.abas.AbaBase;
	import br.com.sicoob.capes.autorizarAlteracoes.abas.IAbaAutorizacao;
	import br.com.sicoob.capes.comum.enums.SituacaoCadastralRFBEnum;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEntidadeEnum;
	import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEnum;
	import br.com.sicoob.capes.comum.enums.TipoOperacaoEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.CampoTelaListaVO;
	import br.com.sicoob.capes.comum.vo.CampoTelaVO;
	import br.com.sicoob.capes.comum.vo.CamposTelaVO;
	import br.com.sicoob.capes.comum.vo.ItemAutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.OcorrenciaAtividadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoDocumentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.ResponsavelCadastroVO;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class AutorizacaoSelecao extends AutorizacaoSelecaoView {
		
		private static const ABA_AUTORIZAR:int = 0;
		private static const ABA_DEVOLVIDAS:int = 1;
		private static const ABA_ENCAMINHADAS:int = 2;
		private static const ABA_NAO_ENCAMINHADAS:int = 3;
		private static const ABA_AUTO_ATENDIMENTO:int = 4;
		private static const DESTINO_CAPES : String = "destinoCAPES";
		
		protected var servicoDefinicoes : ServicoJava;

		public function AutorizacaoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum", SituacaoCadastralRFBEnum);			
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum", TipoAutorizacaoEnum);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum", TipoOperacaoEnum);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Autorizacao",	AutorizacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento", AutorizacaoDocumentoVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CampoTelaVO", CampoTelaVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CampoTelaListaVO", CampoTelaListaVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CamposTelaVO", CamposTelaVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ItemAutorizacaoVO", ItemAutorizacaoVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO", OcorrenciaAtividadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro", ResponsavelCadastroVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum", TipoAutorizacaoEntidadeEnum);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);

			servicoDefinicoes = ServicoJavaUtil.getServico("br.com.sicoob.capes.cadastro.fachada.AutorizarFachada", "Obtendo definições...", ResultEvent.RESULT, onResultDefinicoes);
		}
		
		private function init(event:FlexEvent):void {
			this.navegacaoTab.addEventListener(Event.CHANGE, onChangeTab);
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoCAPESRecuperado);
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			if(navegacaoTab != null) {
				navegacaoTab.removeEventListener(Event.CHANGE, onChangeTab);
				navegacaoTab.removeAllChildren();
				navegacaoTab = null;
			}
		}
		
		private function onDestinoCAPESRecuperado(destino:DestinoVO):void {
			servicoDefinicoes.configurarDestino(destino);
			getAba(ABA_AUTORIZAR).configurarDestino(destino);
			getAba(ABA_DEVOLVIDAS).configurarDestino(destino);
			getAba(ABA_ENCAMINHADAS).configurarDestino(destino);
			getAba(ABA_NAO_ENCAMINHADAS).configurarDestino(destino);
			getAba(ABA_AUTO_ATENDIMENTO).configurarDestino(destino);
			
			trace("Destinos recuperados");
			
			servicoDefinicoes.getOperation("obterDefinicoes").send(new RequisicaoReqDTO());
		}

		private function onChangeTab(event:Event):void {
			this.navegacaoTab.selectedChild.dispatchEvent(event);
		}
		
		protected function onResultDefinicoes(event : ResultEvent):void {
			trace("Retorno do obter definições.");
			
			var abaAutorizar: AbaBase = getAba(ABA_AUTORIZAR) as AbaBase;
			abaAutorizar.preencherDefinicoes(event);
			
			var abaDevolvidas: AbaBase = getAba(ABA_DEVOLVIDAS) as AbaBase;
			abaDevolvidas.preencherDefinicoes(event);
			
			var abaEncaminhadas: AbaBase = getAba(ABA_ENCAMINHADAS) as AbaBase;
			abaEncaminhadas.preencherDefinicoes(event);
			
			abaAutorizar.pesquisar();
			
			var abaAutoAtendimento: AbaBase = getAba(ABA_AUTO_ATENDIMENTO) as AbaBase;
			abaAutoAtendimento.preencherDefinicoes(event);
			
			retornoPermissaoExibirAbaAutoAtendimento(event);
		}
		
		private function getAba(index:int):IAbaAutorizacao {
			return this.navegacaoTab.getChildAt(index) as IAbaAutorizacao;
		}
		
		public function retornoPermissaoExibirAbaAutoAtendimento(event:ResultEvent): void {
			var exibirAbaAutoAtendimento:Boolean = event.result.dados.exibirAbaAutoAtendimento;
			
			if(exibirAbaAutoAtendimento==false){
				this.navegacaoTab.removeChild(this.abaAutoAtendimento);
			}
		}
		
	}
}