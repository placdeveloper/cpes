package br.com.sicoob.capes.cadastrarNucleo {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class NucleoEdicao extends NucleoEdicaoView {
		
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private var servico: ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.NucleoFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function NucleoEdicao() {
			super();
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.subtitulo.texto = "Tipo de Núcleo";
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			this.servicoEdicao.configurarDestino(destino);
			this.servicoInclusao.configurarDestino(destino);
			if(modo == MODO_INCLUSAO){
				servico.getOperation(obterOperacaoObterDefinicoes()).send(new RequisicaoReqDTO());
			}
		}
		
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : Object = objeto;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = (this.descricao.text != "") || (this.codigo.text != "") || (this.rdbSimNao.selectedValue != null) 
			} else {
				alterado = (this.codigo.valor != objetoAntigo.numNucleo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase())
					|| (this.rdbSimNao.selectedValue != objetoAntigo.ativo.valor);
			}
			return alterado;
		}
		
		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
			this.rdbSimNao.selectedValue = true;
		}
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		protected override function preencherCampos():void {
				this.codigo.text = this.objeto.numNucleo;
				this.descricao.text = this.objeto.descricao;	
				this.rdbSimNao.selectedValue = this.objeto.ativo.valor;	
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : NucleoVO = new NucleoVO();
			if (objeto != null) {
				vo.idInstituicao = objeto.idInstituicao;
				vo.idNucleo = objeto.idNucleo;
			}
			vo.numNucleo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			vo.ativo = new Booleano(rdbSimNao.selectedValue as Boolean);
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["nucleo"] = vo;
			return dto as RequisicaoDTO;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, habilitar : Boolean) : void {
			if (componente.name == "codigo") {
				habilitar = (MODO_INCLUSAO == modo);
			}
			super.configurarEstadoComponente(componente, habilitar);
		}		
		
		private function retornoObterDefinicoes(event: ResultEvent): void { 
			this.codigo.text = event.result.dados.codigo;
			this.descricao.setFocus();
		}
		
	}
}