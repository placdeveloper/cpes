package br.com.sicoob.capes.cadastrarTipoTelefone {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoTelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TipoTelefoneEdicao extends TipoTelefoneEdicaoView {
		
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private var servico: ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoTelefoneFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function TipoTelefoneEdicao() {
			super();
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.subtitulo.texto = "Tipo de Telefone";
			
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
				alterado = (this.descricao.text != "") || (this.codigo.text != "") 
					|| (this.checkDddObrigatorio.selected) 
					|| (this.cmbPessoaContato.selectedIndex > 0) 
					|| (this.rdbSimNao.selectedValue != null)
					|| (this.numMinDigitos.text != ""); 
			} else {
				alterado = (this.codigo.valor != objetoAntigo.codigo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase())
					|| (this.numMinDigitos != objetoAntigo.numMinDigitos)
					|| (this.checkDddObrigatorio.selected != objetoAntigo.dddObrigatorio.valor)
					|| (this.rdbSimNao.selectedValue != objetoAntigo.ativo.valor)
					|| (this.cmbPessoaContato.selectedItem != null && (this.cmbPessoaContato.selectedItem.codigo != objetoAntigo.tipoPessoaContato.codigo));
			}
			return alterado;
		}
		
		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.numMinDigitos.text = "";
			this.descricao.text = "";
			this.checkDddObrigatorio.selected = false;
			this.cmbPessoaContato.selectedIndex = 0;
			this.rdbSimNao.selectedValue = true;
		}
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		protected override function preencherCampos():void {
			this.codigo.valor = this.objeto.codigo;
			this.numMinDigitos.valor = this.objeto.numMinDigitos;
			this.descricao.text = this.objeto.descricao;	
			this.checkDddObrigatorio.selected = this.objeto.dddObrigatorio.valor;
			this.cmbPessoaContato.selectedItem = this.objeto.tipoPessoaContato;	
			this.rdbSimNao.selectedValue = this.objeto.ativo.valor;
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : TipoTelefoneVO = new TipoTelefoneVO();
			vo.ativo = new Booleano(rdbSimNao.selectedValue as Boolean);
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			vo.dddObrigatorio = new Booleano(checkDddObrigatorio.selected);
			vo.numMinDigitos = this.numMinDigitos.valor;
			vo.tipoPessoaContato = cmbPessoaContato.selectedItem as TipoPessoaContatoVO;
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["tipoTelefone"] = vo;
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
			if (modo == MODO_EDICAO || modo == MODO_VISUALIZACAO) {
				preencherCampos();
			}	
		}
		
	}
}