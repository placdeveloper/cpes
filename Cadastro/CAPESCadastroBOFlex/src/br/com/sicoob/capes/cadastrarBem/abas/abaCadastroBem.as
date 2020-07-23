package br.com.sicoob.capes.cadastrarBem.abas{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.sicoob.capes.cadastrarBem.IAbaForm;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SituacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoBemVO;
	
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.events.ListEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	public class abaCadastroBem extends abaCadastroBemView implements IAbaForm {
		private static var CODIGO_TIPO_BEM_IMOVEL:Number = 1;
		static private const OPERACAO_OBTER_SUBTIPOS_BEM: String = "obterSubTiposBem";
		static private const OPERACAO_OBTER_DEPENDENCIAS_SUBTIPOS_BEM: String = "obterSituacoesBem";

		public function init():void {
			this.cmbTipoBem.addEventListener(ListEvent.CHANGE, obterSubTiposBem);
			this.servicoSubTiposBem.addEventListener(ResultEvent.RESULT, retornoObterSubTiposBem);

			this.cmbSubTipoBem.addEventListener(ListEvent.CHANGE, obterDependenciasSubTiposBem);
			this.servicoDependenciasSubTipoBem.addEventListener(ResultEvent.RESULT, retornoObterDependenciasSubTiposBem);
			this.valor.addEventListener(FocusEvent.FOCUS_OUT, calcularValorPropriedade);
			this.percentual.addEventListener(FocusEvent.FOCUS_OUT, calcularValorPropriedade);
		}

    	public function carregarDefinicoes(evt:ResultEvent):void {

    		this.cmbTipoBem.dataProvider = evt.result.dados.tiposBem;
    	}

		public function atualizarCamposRegistro(bem:BemVO):void {
			bem.situacao = cmbSituacaoBem.selectedItem as SituacaoBemVO;
			bem.subTipo = cmbSubTipoBem.selectedItem as SubTipoBemVO;
			bem.nomeSeguradora = seguradora.text;
			bem.descricao = taDescricao.text;
			bem.valorAtualMercado = valor.valor;
			bem.valorSeguro = valorSegurado.valor;
			bem.dataVencimentoSeguro = DateTimeBase.getDateTimeEntity(vencimento.selectedDate);
			bem.percentual = percentual.valor;
    	}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataHoraInicio.visible = dataHoraInicio.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}

    	public function preencherCampos(bem:BemVO):void {
    		
    		var dataVencimento :Date = null;
    		var situacao: Object = null; 
    		var subTipo: Object = null; 
    		var tipoBem: Object = null;
			

			habilitaCamposExtras(bem == null);
    		
			if(bem.dataVencimentoSeguro != null) {
				dataVencimento = bem.dataVencimentoSeguro.data;
			}
    		if (bem.situacao != null) {
				situacao = bem.situacao.codigo;
    		}
			if (bem.subTipo != null) {	
				subTipo = bem.subTipo.codigo
			}
			if (bem.subTipo != null && bem.subTipo.tipoBem != null) {	
				tipoBem = bem.subTipo.tipoBem.codigo;
			}
			
			this.dataHoraInicio.text = bem.dataHoraInicio != null ? convertDataToString(bem.dataHoraInicio.data) : null;
			this.usuarioAlteracao.text = bem.idUsuarioAprovacao;
			this.cmbSituacaoBem.procuraItemPorNome(situacao, "codigo");
			this.cmbSubTipoBem.procuraItemPorNome(subTipo, "codigo");
			this.cmbTipoBem.procuraItemPorNome(tipoBem, "codigo");			
			this.cmbTipoBem.enabled = bem.id == 0;
			this.seguradora.text = bem.nomeSeguradora;
			this.taDescricao.text = bem.descricao;
			this.valor.valor = bem.valorAtualMercado;
			this.valorSegurado.valor = bem.valorSeguro;
		
			this.vencimento.selectedDate = dataVencimento;
			this.percentual.valor = bem.percentual;
			this.valorPropriedade.valor = bem.valorAtualMercado * (bem.percentual / 100);
    	}

    	public function limparFormIncluir():void {
			
			this.cmbSituacaoBem.selectedIndex = 0;
			atualizarCombo(this.cmbSituacaoBem, null);
			
			this.cmbSubTipoBem.selectedIndex = 0;
			atualizarCombo(this.cmbSubTipoBem, null);

			this.cmbTipoBem.selectedIndex = 0;
			this.cmbTipoBem.enabled = true;
			this.seguradora.text = "";
			this.taDescricao.text = "";
			this.valor.valor = 0;
			this.valorSegurado.valor = 0;
			this.vencimento.selectedDate = null;
			this.percentual.valor = 0;
			this.valorPropriedade.valor = 0;
    	}

    	public function verificarAlteracoes(bem:BemVO):Boolean {
    		
    		if (
    			this.cmbSituacaoBem.selectedItem != null 
    			&& bem.situacao != null
    			&& this.cmbSituacaoBem.selectedItem.codigo == bem.situacao.codigo
    			&& this.cmbSubTipoBem.selectedItem != null
    			&& bem.subTipo != null
    			&& this.cmbSubTipoBem.selectedItem.codigo == bem.subTipo.codigo
    			&& this.cmbTipoBem.selectedItem != null
    			&& bem.subTipo.tipoBem != null
    			&& this.cmbTipoBem.selectedItem.codigo == bem.subTipo.tipoBem.codigo
    			&& this.seguradora.text == bem.nomeSeguradora
    			&& this.taDescricao.text == bem.descricao
    			&& this.valor.valor == bem.valorAtualMercado
    			&& this.valorSegurado.valor == bem.valorSeguro) {
	    			return true;
    			}
    		return false;
    	}
		
		private function obterSubTiposBem(evt:ListEvent):void {

			var tipoBem:TipoBemVO = this.cmbTipoBem.selectedItem as TipoBemVO;

			if (tipoBem != null && tipoBem.codigo == CODIGO_TIPO_BEM_IMOVEL) {
				obterAbaImovel().enabled = true;
			} else {
				IAbaForm(obterAbaImovel()).limparFormIncluir();
				obterAbaImovel().enabled = false;
			}

			if (tipoBem == null) {
				atualizarCombo(this.cmbSubTipoBem, null);
				return;
			}

			MostraCursor.setBusyCursor("Obtendo Subtipos do Bem ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoBem = tipoBem;
			servicoSubTiposBem.getOperation(OPERACAO_OBTER_SUBTIPOS_BEM).send(dto);
		}
		
		private function retornoObterSubTiposBem(evt:ResultEvent):void {
			atualizarCombo(this.cmbSubTipoBem, evt.result.dados.subTiposBem);
			
			MostraCursor.removeBusyCursor();
		}

		private function obterDependenciasSubTiposBem(evt:ListEvent):void {

			var subTipo:SubTipoBemVO = this.cmbSubTipoBem.selectedItem as SubTipoBemVO;

			if (subTipo == null) {
				atualizarCombo(this.cmbSituacaoBem, null);
				atualizarCombo(this.obterAbaImovel().unidadeMedida, null);
				return;
			}
			
			atualizarCombo(this.obterAbaImovel().unidadeMedida, subTipo.unidadesMedida);
			if (subTipo.unidadesMedida != null && subTipo.unidadesMedida.length > 0){
				this.obterAbaImovel().unidadeMedida.validarObrigatorio = true;
			} else{
				this.obterAbaImovel().unidadeMedida.validarObrigatorio = false;
			}

			MostraCursor.setBusyCursor("Obtendo dependências do Subtipo do bem ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.subTipo = subTipo;
			servicoDependenciasSubTipoBem.getOperation(OPERACAO_OBTER_DEPENDENCIAS_SUBTIPOS_BEM).send(dto);
		}

		private function retornoObterDependenciasSubTiposBem(evt:ResultEvent):void {
			atualizarCombo(this.cmbSituacaoBem, evt.result.dados.situacoesBem);
						
			MostraCursor.removeBusyCursor();
		}

    	public function calcularValorPropriedade(evt:FocusEvent):void {
			this.valorPropriedade.valor = valor.valor * (percentual.valor/100);
    	}
    	
		private function obterAbaImovel():abaImovelView {
			return this.parent.getChildByName("abaImovel") as abaImovelView;
		}

    	public function ativarAba(object:Object):Boolean {
    		return true;
    	}
    	
		public override function validarCampos():Boolean {

			var retorno:Boolean = true;

			if ((seguradora.text != "")
				|| (valorSegurado.valor != 0)
				|| (vencimento.selectedDate != null)) {

				if (seguradora.text == "") {
					Alerta.show("A Seguradora deve ser informada!", "Erro", Alerta.ALERTA_ERRO, seguradora);
					retorno = false;
				} else if (valorSegurado.valor == 0) { 
					Alerta.show("O Valor segurado deve ser informado!", "Erro", Alerta.ALERTA_ERRO, valorSegurado);
					retorno = false;
				} else if (vencimento.selectedDate == null) {
					Alerta.show("O Vencimento deve ser informado!", "Erro", Alerta.ALERTA_ERRO, seguradora);
					retorno = false;
				}
			}
			
			return retorno && super.validarCampos();
		}
		
		public function atualizarCombo(combo:Combo, lista:ListCollectionView):void {

			if (combo.dataProvider == null){
				combo.dataProvider = new ArrayCollection();
			}

			if (lista == null){
				lista = new ArrayCollection();
			}

			ListCollectionView(combo.dataProvider).removeAll();
			adicionarItemOpcional(combo);
			cloneArrayCollection(ListCollectionView(combo.dataProvider), lista);
			
			combo.validateDisplayList();
			combo.validateNow();
		}
		
		/**
		 * Adiciona o item opcional ao início da lista, se este ainda não existir
		 */
		private function adicionarItemOpcional(combo: Combo): void {
			if (combo.dataProvider is IList) {
				var lista: IList = (combo.dataProvider as IList);
				if (lista.length == 0 || (lista.getItemAt(0) != null && 
											!lista.getItemAt(0).hasOwnProperty("idItemOpcionalCombo"))) {
					lista.addItemAt(criarItemOpcional(combo),0);
				}
			}
			habilitaCamposExtras(true);
		}
		
		/**
		 * Adiciona o item opcional ao início da lista, se este ainda não existir
		 */		
		protected function criarItemOpcional(combo: Combo):Object {
			var objeto:Object = new Object();
			
			objeto[combo.labelField] = "---";
			objeto.idItemOpcionalCombo = -1;

			return objeto;
		}
		
		private function cloneArrayCollection(objDestino:ListCollectionView, objOrigem:ListCollectionView):void {
			
            for each (var elemento:Object in objOrigem) {
	            objDestino.addItem(elemento);
            }
		} 
	}
}