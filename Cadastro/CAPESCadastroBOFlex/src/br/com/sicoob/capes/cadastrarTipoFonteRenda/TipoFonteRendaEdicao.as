package br.com.sicoob.capes.cadastrarTipoFonteRenda {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFonteRendaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import mx.collections.ArrayCollection;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.object_proxy;
	
	public class TipoFonteRendaEdicao extends TipoFonteRendaEdicaoView {
		
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private var servico: ServicoJava;
		private var servicoTpPessoa:ServicoJava;
		private var pessoaFisica : TipoPessoaVO;
		private var pessoaJuridica : TipoPessoaVO;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoFonteRendaFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function TipoFonteRendaEdicao() {
			super();
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo sugestão de código...", ResultEvent.RESULT, retornoObterDefinicoes);
			this.servicoTpPessoa = ServicoJavaUtil.getServico(classeServico, null, ResultEvent.RESULT, retornoObterTpsPessoa);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.subtitulo.texto = "Tipo de Fonte de Renda";
			
			this.validacoesAdicionais.addItem(new TipoPessoaValidator(tpPessoaFisica,tpPessoaJuridica));
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			servicoTpPessoa.configurarDestino(destino);
			this.servicoEdicao.configurarDestino(destino);
			this.servicoInclusao.configurarDestino(destino);
			
			obterDefinicoes();
			servicoTpPessoa.getOperation("obterTpsPessoa").send(new RequisicaoReqDTO());
		}
		
		private function obterDefinicoes():void {
			servico.getOperation(obterOperacaoObterDefinicoes()).send(new RequisicaoReqDTO());
		}
		
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : Object = objeto;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = (this.descricao.text != "") || (this.codigo.text != "") 
					|| (this.tpPessoaFisica.selected) || (this.tpPessoaJuridica.selected)
					|| (this.checkValorObrigatorio.selected); 
			} else {
				alterado = (this.codigo.valor != objetoAntigo.codigo) 
					|| (this.descricao.text.toUpperCase() != objetoAntigo.descricao.toUpperCase())
					|| (this.tpPessoaFisica.selected != objetoAntigo.pessoaFisica)
					|| (this.tpPessoaJuridica.selected != objetoAntigo.pessoaJuridica)
					|| (this.checkValorObrigatorio.selected != objetoAntigo.valorObrigatorio.valor);
					
			}
			return alterado;
		}

		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
			this.tpPessoaFisica.selected = false;
			this.tpPessoaJuridica.selected = false;
			this.checkValorObrigatorio.selected = false;
		}		
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		protected override function preencherCampos():void {
			this.codigo.valor = this.objeto.codigo;
			this.descricao.text = this.objeto.descricao;	
			this.tpPessoaFisica.selected = this.objeto.pessoaFisica;
			this.tpPessoaJuridica.selected = this.objeto.pessoaJuridica;
			this.checkValorObrigatorio.selected = this.objeto.valorObrigatorio.valor;
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : TipoFonteRendaVO = new TipoFonteRendaVO();
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			vo.tiposPessoa = new ArrayCollection();
			vo.valorObrigatorio = new Booleano(checkValorObrigatorio.selected);
			if(tpPessoaFisica.selected){
				vo.tiposPessoa.addItem(this.pessoaFisica);
			}
			if(tpPessoaJuridica.selected){
				vo.tiposPessoa.addItem(this.pessoaJuridica);
			} 
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["tipoFonteRenda"] = vo;
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
		
		private function retornoObterTpsPessoa(event: ResultEvent): void { 
			this.pessoaFisica = event.result.dados.pessoaFisica;
			this.pessoaJuridica = event.result.dados.pessoaJuridica;
		}
	}
}