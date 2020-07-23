package br.com.sicoob.capes.comum.componentes.dominios {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class DominioEdicao extends DominioEdicaoView {
		
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private var servico: ServicoJava;
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function DominioEdicao() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function obterClasseServico() : String {
			return null;
		}
		
		protected override function init(evento : FlexEvent) : void {
			
			super.init(evento);
			this.servicoEdicao = ServicoJavaUtil.getServico(obterClasseServico(), 
				"Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(obterClasseServico(),
				"Salvando dados...");
			this.servico = ServicoJavaUtil.getServico(obterClasseServico(), 
				"Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			this.subtitulo.texto = obterSubtitulo();
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
			(this.servicoInclusao as ServicoJava).configurarDestino(destino);
			(this.servicoEdicao as ServicoJava).configurarDestino(destino);
			
			servico.getOperation(obterOperacaoObterDefinicoes()).send(instanciarDTO());
		}
		
		protected function obterSubtitulo() : String {
			return null;
		}
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : Object = objeto;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = (this.descricao.text != "") || (this.codigo.text != ""); 
			} else {
				alterado = (this.codigo.valor != objetoAntigo.codigo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase());
				
			}
			return alterado;
		}
		
		protected function instanciarVO() : Object {
			return null;
		}
		
		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
		}
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		protected override function preencherCampos():void {
			this.codigo.valor = this.objeto.codigo;
			this.descricao.text = this.objeto.descricao;	
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : Object = instanciarVO();
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			
			var dto : Object = instanciarDTO();
			dto.dados[obterChaveMapa()] = vo;
			return dto as RequisicaoDTO;
		}
		
		protected function obterChaveMapa() : String {
			return "vo";
		}
		
		protected function instanciarDTO() : RequisicaoDTO {
			return null;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
															   habilitar : Boolean) : void {
			
			if (componente.name == "codigo") {
				habilitar = (MODO_INCLUSAO == modo);
			}
			super.configurarEstadoComponente(componente, habilitar);
		}		
		
		private function retornoObterDefinicoes(event: ResultEvent): void { 
			this.codigo.valor = event.result.dados.codigo as Number;
			this.descricao.setFocus();
		}
	}
}