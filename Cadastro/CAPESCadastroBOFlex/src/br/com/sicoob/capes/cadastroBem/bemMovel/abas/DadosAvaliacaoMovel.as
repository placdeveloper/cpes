package br.com.sicoob.capes.cadastroBem.bemMovel.abas {
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastroBem.IAba;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelAvaliacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoEstadoConservacaoBemVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	/**
	 * Aba de dados avaliação do bem móvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class DadosAvaliacaoMovel extends DadosAvaliacaoMovelView implements IAba {
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			obterAbaDadosBasicos().comboTipoBem.addEventListener(ListEvent.CHANGE, aoTrocarTipoBem);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			comboEstadoConservacao.dataProvider = evento.result.dados.listaTiposEstadoConservacao;
			componenteSelecaoTipoOnus.adicionarListaRegistrosDisponiveis(evento.result.dados.listaTiposOnus);
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			if(bem is BemMovelAvaliacaoVO) {
				var bemMovelAvaliacao:BemMovelAvaliacaoVO = BemMovelAvaliacaoVO(bem);
				
				bemMovelAvaliacao.dataAvaliacao = campoDataAvaliacao.selectedDate;
				bemMovelAvaliacao.dataCompraVenda = campoDataCompraVenda.selectedDate;
				bemMovelAvaliacao.processoAquisicao = checkProcessoAquisicao.selected;
				bemMovelAvaliacao.tipoEstadoConservacao = comboEstadoConservacao.selectedItem as TipoEstadoConservacaoBemVO;
				bemMovelAvaliacao.valorAvaliacao = FlexUtil.campoNumericoZeroParaNulo(campoValorAvaliacao.valor);
				bemMovelAvaliacao.valorCompraVenda = FlexUtil.campoNumericoZeroParaNulo(campoValorCompraVenda.valor);
				bemMovelAvaliacao.tiposOnus = ListCollectionView(componenteSelecaoTipoOnus.obterItensSelecionados());
				
				var pessoa:PessoaPlataformaVO = componenteProcurarPessoa.registro as PessoaPlataformaVO;
				var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa)
				bemMovelAvaliacao.idPessoaCompartilhamentoAvaliador = pessoaCompartilhamento != null ? pessoaCompartilhamento.idPessoaCompartilhamento : NaN;				
			}
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			limpar();
			var bemMovelAvaliacao:BemMovelAvaliacaoVO = bem as BemMovelAvaliacaoVO;
			if(bemMovelAvaliacao != null) {
				FlexUtil.selecionarItemCombo(comboEstadoConservacao, bemMovelAvaliacao.tipoEstadoConservacao);
				checkProcessoAquisicao.selected = bemMovelAvaliacao.processoAquisicao;
				campoValorCompraVenda.valor = bemMovelAvaliacao.valorCompraVenda;
				campoDataCompraVenda.selectedDate = bemMovelAvaliacao.dataCompraVenda;
				campoValorAvaliacao.valor = bemMovelAvaliacao.valorAvaliacao;
				campoDataAvaliacao.selectedDate = bemMovelAvaliacao.dataAvaliacao;
				componenteProcurarPessoa.procurarPorCodigoCompartilhamento(bemMovelAvaliacao.idPessoaCompartilhamentoAvaliador);
				componenteSelecaoTipoOnus.adicionarListaRegistrosSelecionados(bemMovelAvaliacao.tiposOnus);
			}
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			comboEstadoConservacao.selectedIndex = 0;
			checkProcessoAquisicao.selected = false;
			campoValorCompraVenda.text = "";
			campoDataCompraVenda.selectedDate = null;
			campoValorAvaliacao.text = "";
			campoDataAvaliacao.selectedDate = null;
			componenteProcurarPessoa.limpar();
			componenteSelecaoTipoOnus.limpar();
    	}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			marcarCamposObrigatorios();
			var tipoBem:TipoBemMovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemMovelVO;
			
			if(tipoBem != null) {
				rotuloTitulo.texto = "Avaliação - " + tipoBem.descricao;
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			
		}

		/**
		 * @inheritDoc
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			if(bem == null) {
				var onus:ArrayCollection = componenteSelecaoTipoOnus.obterItensSelecionados();
				
				if(comboEstadoConservacao.selectedItem != null
					|| checkProcessoAquisicao.selected
					|| campoValorCompraVenda.valor != 0
					|| campoDataCompraVenda.selectedDate != null
					|| campoValorAvaliacao.valor != 0
					|| campoDataAvaliacao.selectedDate != null
					|| componenteProcurarPessoa.registro != null
					
					|| (onus != null && onus.length > 0)){
					return true;
				}
				return false;
			} else if(!(bem is BemMovelAvaliacaoVO)) {
				if(comboEstadoConservacao.selectedItem != null
					|| checkProcessoAquisicao.selected
					|| campoValorCompraVenda.valor != 0
					|| campoDataCompraVenda.selectedDate != null
					|| campoValorAvaliacao.valor != 0
					|| campoDataAvaliacao.selectedDate != null
					|| componenteProcurarPessoa.registro != null
					|| (onus != null && onus.length > 0)){
					return true;
				}
			} else if(bem is BemMovelAvaliacaoVO) {
				var bemavaliacao:BemMovelAvaliacaoVO = BemMovelAvaliacaoVO(bem);
				
				if(bemavaliacao != null) {
					if(FlexUtil.compararObjetos(bemavaliacao.tipoEstadoConservacao, comboEstadoConservacao.selectedItem)
						&& bemavaliacao.processoAquisicao == checkProcessoAquisicao.selected
						&& FlexUtil.compararValoresNumericos(bemavaliacao.valorCompraVenda, campoValorCompraVenda.valor)
						&& bemavaliacao.dataCompraVenda == campoDataCompraVenda.selectedDate
						&& FlexUtil.compararValoresNumericos(bemavaliacao.valorAvaliacao, campoValorAvaliacao.valor)
						&& bemavaliacao.dataAvaliacao == campoDataAvaliacao.selectedDate) {
						return false;
					}
					return true;
				}
				return false;
			}
			return false;
    	}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return (comboEstadoConservacao.selectedItem != null
				|| checkProcessoAquisicao.selected
				|| campoValorCompraVenda.valor != 0
				|| campoDataCompraVenda.selectedDate != null
				|| campoValorAvaliacao.valor != 0
				|| campoDataAvaliacao.selectedDate != null
				|| componenteProcurarPessoa.registro != null);
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosMovel {
			return this.parent.getChildByName("abaDadosBasicos") as DadosBasicosMovel;
		}
		
		/**
		 * Método chamado ao trocar o tipo de bem na tela de 
		 * dados basicos para habilitar/desabilitar os campos dependentes.
		 */
		private function aoTrocarTipoBem(evento:Event = null):void {
			var tipoBem:TipoBemMovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemMovelVO;
			if(tipoBem != null && obterModo() != 3){
				comboEstadoConservacao.enabled = tipoBem.habilitaTipoEstadoConservacao;
				if(!tipoBem.habilitaTipoEstadoConservacao) {
					comboEstadoConservacao.selectedIndex = 0;
				}
			} else {
				comboEstadoConservacao.selectedIndex = 0;
				comboEstadoConservacao.enabled = false;
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function bloquearCampoModoVisualizacao(bloquear:Boolean):void {
			comboEstadoConservacao.enabled = !bloquear;
			componenteSelecaoTipoOnus.enabled = !bloquear;
			
			aoTrocarTipoBem();
		}
		
		/**
		 * @inheritDoc
		 */
		public function marcarCamposObrigatorios():void {
			desmarcarTodosCampos();
			if(verificarAlteracoes() && obterModo() != 3) {
				
				if(comboEstadoConservacao.enabled) {
					comboEstadoConservacao.validarObrigatorio = true;
				}
				
				if(checkProcessoAquisicao.selected) {
					campoValorCompraVenda.validarObrigatorio = true;
					campoDataCompraVenda.validarObrigatorio = true;
				}
				
				campoValorAvaliacao.validarObrigatorio = true;
				campoDataAvaliacao.validarObrigatorio = true;
				componenteProcurarPessoa.validarObrigatorio = true;
			}
		}
		
		/**
		 * Desmarca a obrigatoriedade de todos os campos.
		 */
		private function desmarcarTodosCampos():void {
			comboEstadoConservacao.validarObrigatorio = false;
			campoValorCompraVenda.validarObrigatorio = false;
			campoDataCompraVenda.validarObrigatorio = false;
			campoValorAvaliacao.validarObrigatorio = false;
			campoDataAvaliacao.validarObrigatorio = false;
			componenteProcurarPessoa.validarObrigatorio = false;
			componenteSelecaoTipoOnus.validarObrigatorio = false;
		}
		
		/**
		 * Obtém o modo atual
		 */
		private function obterModo():int {
			return FormularioCadastro(this.parent.parent).modo;
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			return true;
		}
	}
}