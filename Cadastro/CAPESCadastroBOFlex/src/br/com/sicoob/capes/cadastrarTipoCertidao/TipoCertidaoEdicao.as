package br.com.sicoob.capes.cadastrarTipoCertidao {
	
	import br.com.bancoob.componentes.eventos.EventoCadastro;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.OrgaoEmissorCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.SubTipoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAbrangenciaCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObjetoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPrazoCertidaoVO;
	
	import flash.events.Event;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TipoCertidaoEdicao extends TipoCertidaoEdicaoView {

		static public const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.TipoCertidaoFachada";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		public var servico: ServicoJava;

		public function TipoCertidaoEdicao() {
			super();
			addEventListener(FlexEvent.ADD, obterDefinicoes);
			addEventListener(EventoCadastro.INCLUSAO, obterDefinicoes);
		}
		
		protected override function init(evento:FlexEvent):void {
			super.init(evento);
			this.tipoPrazo.addEventListener(ListEvent.CHANGE, tipoPrazoAlterado);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo sugestão de código...");
			servico.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			ServicoJava(this.servicoInclusao).configurarDestino(destino);
			ServicoJava(this.servicoEdicao).configurarDestino(destino);
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes(event : Event = null) : void {
			if (initialized) {
				servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
			}
		}
		
		private function retornoObterDefinicoes(event : ResultEvent) : void {
			this.subTipo.dataProvider = event.result.dados.subTipos;
			this.tipoObjeto.dataProvider = event.result.dados.tiposObjeto;
			this.tipoPrazo.dataProvider = event.result.dados.tiposPrazo;
			this.tipoAbrangencia.dataProvider = event.result.dados.tiposAbrangencia;
			this.orgaoEmissor.dataProvider = event.result.dados.orgaosEmissores;
			limpar();			
			if (initialized && (MODO_INCLUSAO == modo)) {
				this.codigo.valor = event.result.dados.codigo;
			}
		}

		private function tipoPrazoAlterado(event : ListEvent = null) : void {
			var itemSelecionado : Object = this.tipoPrazo.selectedItem;
			var identificador : String = this.tipoPrazo.identificadorItem;
			if (MODO_VISUALIZACAO == modo) {
				this.prazoValidade.enabled = true;
				this.prazoValidade.valorMinimo = 0;				
			} else {
				if (isComboSelecionada(this.tipoPrazo) 
					&& ((itemSelecionado[identificador] == FormatadorUtil.TIPO_PRAZO_CERTIDAO_DIAS) 
					|| (itemSelecionado[identificador] == FormatadorUtil.TIPO_PRAZO_CERTIDAO_MESES))) {
					this.prazoValidade.enabled = true;
					this.prazoValidade.valorMinimo = 1;				
				} else {
					this.prazoValidade.enabled = false;
					this.prazoValidade.valorMinimo = 0;	
					this.prazoValidade.valor = 0;			
				}
			}
		}	
		  	
	  	protected override function houveAlteracoes(): Boolean {
			var objetoAntigo: TipoCertidaoVO = TipoCertidaoVO(objeto);

			if (objetoAntigo == null) {
				objetoAntigo = new TipoCertidaoVO();
				objetoAntigo.codigo = 0;
				objetoAntigo.sigla = "";
				objetoAntigo.nome = "";
				objetoAntigo.finalidade = "";
				objetoAntigo.prazoValidade = 0;
			}
			var codigo : Boolean = objetoAntigo.codigo != this.codigo.valor;
			var sigla : Boolean = objetoAntigo.sigla.toUpperCase() != this.sigla.text.toUpperCase();
			var nome : Boolean = objetoAntigo.nome.toUpperCase() != this.nome.text.toUpperCase();
			var finalidade : Boolean = objetoAntigo.finalidade.toUpperCase() != this.finalidade.text.toUpperCase();
			var subTipo : Boolean = isValorComboAlterado(this.subTipo, objetoAntigo.subTipo);
			var tipoObjeto : Boolean = isValorComboAlterado(this.tipoObjeto, objetoAntigo.tipoObjeto);
			var tipoPrazo : Boolean = isValorComboAlterado(this.tipoPrazo, objetoAntigo.tipoPrazo);
			var prazoValidade : Boolean = objetoAntigo.prazoValidade != this.prazoValidade.valor;
			var dataCancelamento : Boolean = objetoAntigo.dataCancelamento != this.dataCancelamento.selectedDate;
			var tipoAbrangencia : Boolean = isValorComboAlterado(this.tipoAbrangencia, objetoAntigo.tipoAbrangencia); 
			var orgaoEmissor : Boolean = isValorComboAlterado(this.orgaoEmissor, objetoAntigo.orgaoEmissor); 
			return codigo || sigla || nome || finalidade || subTipo || tipoObjeto || tipoPrazo 
				|| prazoValidade || dataCancelamento || tipoAbrangencia || orgaoEmissor;
		} 
  
		protected override function limparFormIncluir() : void {
			if (initialized && (MODO_INCLUSAO != modo)) {
				this.codigo.text = "";
			}
			
			this.sigla.text = "";
			this.nome.text = "";
			this.finalidade.text = "";
			this.subTipo.selectedIndex = 0;
			this.tipoObjeto.selectedIndex = 0;
			this.tipoPrazo.selectedIndex = 0;
			this.prazoValidade.text = "";
			this.dataCancelamento.selectedDate = null;
			this.tipoAbrangencia.selectedIndex = 0;
			this.orgaoEmissor.selectedIndex = 0;
		}
		
		override protected function montarDto(): RequisicaoDTO {
			
			var vo: TipoCertidaoVO = new TipoCertidaoVO();
			vo.codigo = this.codigo.valor;
			vo.sigla = this.sigla.text;
			vo.nome = this.nome.text;
			vo.finalidade = this.finalidade.text;
			vo.subTipo = SubTipoCertidaoVO(this.subTipo.selectedItem);
			vo.tipoObjeto = TipoObjetoCertidaoVO(this.tipoObjeto.selectedItem);
			vo.tipoPrazo = TipoPrazoCertidaoVO(this.tipoPrazo.selectedItem);
			vo.prazoValidade = this.prazoValidade.valor;
			vo.dataCancelamento = this.dataCancelamento.selectedDate;
			vo.tipoAbrangencia = TipoAbrangenciaCertidaoVO(this.tipoAbrangencia.selectedItem);
			vo.orgaoEmissor = OrgaoEmissorCertidaoVO(this.orgaoEmissor.selectedItem);
			
			// Monta o DTO, com o VO, para retorno
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoCertidao = vo;
			return dto;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
				habilitar : Boolean) : void {
				
			super.configurarEstadoComponente(componente, habilitar);
			if (componente.id == "codigo") {
				this.codigo.enabled = (MODO_EDICAO != modo);
			}
		}		
		
		override protected function preencherCampos():void {
			var vo: TipoCertidaoVO = TipoCertidaoVO(objeto);
			this.codigo.valor = vo.codigo;
			this.sigla.text = vo.sigla;
			this.nome.text = vo.nome;
			this.finalidade.text = vo.finalidade;
			this.subTipo.procuraItemPorNome(vo.subTipo.codigo, "codigo");
			this.tipoObjeto.procuraItemPorNome(vo.tipoObjeto.codigo, "codigo");
			this.tipoPrazo.procuraItemPorNome(vo.tipoPrazo.codigo, "codigo");
			this.prazoValidade.valor =  vo.prazoValidade;
			this.dataCancelamento.selectedDate = vo.dataCancelamento;
			this.tipoAbrangencia.procuraItemPorNome(vo.tipoAbrangencia.codigo, "codigo");
			this.orgaoEmissor.procuraItemPorNome(vo.orgaoEmissor.codigo, "codigo");
			tipoPrazoAlterado();
		}		

		private function isComboSelecionada(combo : Combo) : Boolean {
			return combo.isSelecionado() && (combo.selectedItem != combo.labelItemOpcional) 
		}
		
		private function isValorComboAlterado(combo : Combo, vo : Object) : Boolean {
			
			var alterado : Boolean;
			if (isComboSelecionada(combo)) {
				var itemSelecionado : Object = combo.selectedItem;
				var identificador : String = combo.identificadorItem;
				alterado = (vo == null) || (vo[identificador] != itemSelecionado[identificador]);
			} else {
				alterado = vo != null;
			}
			return alterado;
		}
	}
}