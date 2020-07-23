package br.com.sicoob.capes.cadastrarTipoEmail {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.componentes.dominios.DominioEdicao;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmailVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TipoEmailEdicao extends TipoEmailEdicaoView {
		
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private var servico: ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoEmailFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function TipoEmailEdicao() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {	
			super.init(evento);
			
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.subtitulo.texto = "Tipo de Email";
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			this.servicoEdicao.configurarDestino(destino);
			this.servicoInclusao.configurarDestino(destino);
			if(modo == MODO_INCLUSAO){
				servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());	
			}
		}
		
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : Object = objeto;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = (this.descricao.text != "") || (this.codigo.text != "") || (this.cmbPessoaContato.selectedIndex > 0); 
			} else {
				alterado = (this.codigo.valor != objetoAntigo.codigo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase())
					|| (this.cmbPessoaContato.selectedItem != null && (this.cmbPessoaContato.selectedItem.codigo != objetoAntigo.tipoPessoaContato.codigo));
				
			}
			return alterado;
		}
		
		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
			this.cmbPessoaContato.selectedIndex = 0;
		}
		
		protected override function preencherCampos():void {
			this.codigo.valor = this.objeto.codigo;
			this.descricao.text = this.objeto.descricao;
			this.cmbPessoaContato.selectedItem = this.objeto.tipoPessoaContato;	
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : Object = new TipoEmailVO();
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			vo.tipoPessoaContato = this.cmbPessoaContato.selectedItem as TipoPessoaContatoVO;
			
			var dto : Object = new RequisicaoReqDTO();
			dto.dados["tipoEmail"] = vo;
			return dto as RequisicaoDTO;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, habilitar : Boolean) : void {
			if (componente.name == "codigo") {
				habilitar = (MODO_INCLUSAO == modo);
			}
			super.configurarEstadoComponente(componente, habilitar);
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void { 
			this.cmbPessoaContato.dataProvider = event.result.dados.listaPessoaContato;
			this.codigo.valor = event.result.dados.codigo as Number;
			this.descricao.setFocus();
		}
		
	}
}