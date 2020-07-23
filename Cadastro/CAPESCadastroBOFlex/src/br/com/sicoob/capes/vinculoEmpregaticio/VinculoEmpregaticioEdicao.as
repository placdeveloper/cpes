package br.com.sicoob.capes.vinculoEmpregaticio {
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.VinculoEmpregaticioVO;
	
	public class VinculoEmpregaticioEdicao extends VinculoEmpregaticioEdicaoView {
		
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private var servico: ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.VinculoEmpregaticioFachada";
		
		public function VinculoEmpregaticioEdicao() {
			super();
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.subtitulo.texto = "Vínculo Empregatício";
		}
		
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : VinculoEmpregaticioVO = objeto as VinculoEmpregaticioVO;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = (this.descricao.text != "") || (this.codigo.text != "") 
					|| (this.campoValorMinimo.valor); 
			} else {
				alterado = (this.codigo.valor != objetoAntigo.codigo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase())
					|| (this.campoValorMinimo.valor != objetoAntigo.valorRendaMinimaObrigatoria);
			}
			return alterado;
		}

		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
			this.campoValorMinimo.text = "";
			servico.getOperation(obterOperacaoObterDefinicoes()).send(new RequisicaoReqDTO());
		}		
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		protected override function preencherCampos():void {
			this.codigo.valor = this.objeto.codigo;
			this.descricao.text = this.objeto.descricao;	
			this.campoValorMinimo.text = this.objeto.valorRendaMinimaObrigatoria;
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : VinculoEmpregaticioVO = new VinculoEmpregaticioVO();
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			vo.valorRendaMinimaObrigatoria = campoValorMinimo.valor;
			
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["VinculoEmpregaticio"] = vo;
			return dto as RequisicaoDTO;
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