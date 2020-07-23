package br.com.sicoob.capes.tipoEmpresa {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmpresaVO;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TipoEmpresaEdicao extends TipoEmpresaEdicaoView {
		
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private var servico: ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoEmpresaFachada";
		
		public function TipoEmpresaEdicao() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.subtitulo.texto = "Tipo Empresa";
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			if(modo == MODO_INCLUSAO){
				servico.getOperation(obterOperacaoObterDefinicoes()).send(new RequisicaoReqDTO());
			}
		}
		
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : TipoEmpresaVO = objeto as TipoEmpresaVO;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = (this.descricao.text != "") || (this.codigo.text != "") 
					|| (this.campoValorInferior.valor) || (this.campoValorSuperior.valor)
					|| (this.rdbSimNao.selectedValue != null)
					|| (this.rdbSimplesSimNao.selectedValue != null)
					|| (this.rdbAtivoSuperior.selectedValue != null);
			} else {
				alterado = (this.codigo.valor != objetoAntigo.codigo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase())
					|| (this.campoValorInferior.valor != objetoAntigo.valorLimiteInferior)
					|| (this.campoValorSuperior.valor != objetoAntigo.valorLimiteSuperior)
					|| (this.rdbSimNao.selectedValue != objetoAntigo.ativo.valor)
					|| (this.rdbSimplesSimNao.selectedValue != objetoAntigo.isSimplesNacional.valor)
					|| (this.rdbAtivoSuperior.selectedValue != objetoAntigo.possuiAtivoSuperior.valor);
			}
			return alterado;
		}

		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
			this.campoValorInferior.text = "";
			this.campoValorSuperior.text = "";
			this.rdbSimNao.selectedValue = true;
			this.rdbSimplesSimNao.selectedValue = true;
			this.rdbAtivoSuperior.selectedValue = true;
		}		
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		protected override function preencherCampos():void {
			this.codigo.valor = this.objeto.codigo;
			this.descricao.text = this.objeto.descricao;	
			this.campoValorInferior.valor = this.objeto.valorLimiteInferior;
			this.campoValorSuperior.valor = this.objeto.valorLimiteSuperior;
			this.rdbSimNao.selectedValue = this.objeto.ativo.valor;
			this.rdbSimplesSimNao.selectedValue = this.objeto.isSimplesNacional.valor;
			this.rdbAtivoSuperior.selectedValue = this.objeto.possuiAtivoSuperior.valor;
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : TipoEmpresaVO = new TipoEmpresaVO();
			vo.ativo = new Booleano(rdbSimNao.selectedValue as Boolean);
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			vo.isSimplesNacional = new Booleano(rdbSimplesSimNao.selectedValue as Boolean); 
			vo.possuiAtivoSuperior = new Booleano(rdbAtivoSuperior.selectedValue as Boolean);
			vo.valorLimiteInferior = campoValorInferior.valor;
			vo.valorLimiteSuperior = campoValorSuperior.valor;
			
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["TipoEmpresa"] = vo;
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