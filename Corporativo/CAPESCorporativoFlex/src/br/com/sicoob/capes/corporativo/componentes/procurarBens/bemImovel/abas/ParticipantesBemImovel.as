package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas{
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelTipoRelacionamentoVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.AssociarParceiro;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.AssociarParceiroEvent;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.CloseEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de participantes do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class ParticipantesBemImovel extends ParticipantesBemImovelView implements IAba {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarBemImovelFachada";
		
		private var _destino:DestinoVO;
		private var servico:ServicoJava;
		private var _janela:Janela;
		
		private var telaAssociarParceiro:AssociarParceiro;
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			
			botaoAdicionar.addEventListener(MouseEvent.CLICK, abrirJanelaAssociarParceiroInclusao);
			botaoEditar.addEventListener(MouseEvent.CLICK, abrirJanelaAssociarParceiroEdicao);
			botaoExcluir.addEventListener(MouseEvent.CLICK, acaoBotaoExcluir);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			var bemImovel:BemImovelVO = BemImovelVO(bem);
			for each(var objeto:BemImovelTipoRelacionamentoVO in gridParceiros.dataProvider) {
				//objeto.bemImovel = bemImovel;
			}
			
			//bemImovel.relacionamentoPessoas = ListCollectionView(gridParceiros.dataProvider);
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			var bemImovel:BemImovelVO = bem as BemImovelVO;
			
			/*var lista:ListCollectionView = bemImovel.relacionamentoPessoas;
			if(lista != null && lista.length > 0) {
				gridParceiros.dataProvider = bemImovel.relacionamentoPessoas;
			} else {
				obterRelacionamentos(bemImovel);
			}*/
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			gridParceiros.dataProvider = new ArrayCollection();
    	}

		/**
		 * Faz a limpeza dos campos de endereço.
		 */
		private function limparCamposEndereco():void {
		}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			
		}

		/**
		 * @inheritDoc
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			return true;
    	}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return false;
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this._destino = destino;
			servico.configurarDestino(destino);
		}
		
		/**
		 * @inheritDoc
		 */
		public function bloquearCampoModoVisualizacao(bloquear:Boolean):void {
			botaoAdicionar.enabled = !bloquear;
			botaoEditar.enabled = !bloquear;
			botaoExcluir.enabled = !bloquear;
		}
		
		/**
		 * @inheritDoc
		 */
		public function marcarCamposObrigatorios():void {
			
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			return true;
		}
		
		/**
		 * Obtém os tipos de relacionamento para preenchimento da tela.
		 */
		private function obterRelacionamentos(bem:BemVO):void {
			MostraCursor.setBusyCursor("Obtendo relacionamentos do bem...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bem = bem;
			
			servico.addEventListener(ResultEvent.RESULT, retornoObterRelacionamentos);
			servico.getOperation("obterTipoRelacionamentos").send(dto);
		}
		
		/**
		 * Retorno do método de obter relacionamentos.
		 */
		private function retornoObterRelacionamentos(evento:ResultEvent):void {
			gridParceiros.dataProvider = evento.result.dados.relacionamentos;
		}
		
		/**
		 * Abre a janela de associação de parceiros no modo de inclusão.
		 */
		private function abrirJanelaAssociarParceiroInclusao(evento:Event = null):void {
			abrirJanelaAssociarParceiro();
		}
		
		/**
		 * Abre a janela de associação de parceiros no modo de edição.
		 */
		private function abrirJanelaAssociarParceiroEdicao(evento:Event = null):void {
			var tipoRelacionamento:BemImovelTipoRelacionamentoVO = gridParceiros.selectedItem as BemImovelTipoRelacionamentoVO;
			
			if(tipoRelacionamento != null){
				abrirJanelaAssociarParceiro(tipoRelacionamento);
			}else {
				Alerta.show("Por favor, selecione um registro para edição.", "Erro", Alerta.ALERTA_ERRO);
			}
		}
		
		/**
		 * Abre a janela de associação de parceiros.
		 */
		private function abrirJanelaAssociarParceiro(tipoRelacionamento:BemImovelTipoRelacionamentoVO = null):void {
			if(_janela == null) {
				_janela = new Janela();
				
				telaAssociarParceiro = new AssociarParceiro();
				telaAssociarParceiro.addEventListener(AssociarParceiro.EVENTO_REGISTRO_SELECIONADO, parceiroSolicitado);
				
				_janela.width = 500;
				_janela.height = 275;
				_janela.title = "ASSOCIAR PARCEIRO";
				_janela.addEventListener(CloseEvent.CLOSE, fecharJanelaAssociacao);
				_janela.addChild(telaAssociarParceiro);
			}
			
			telaAssociarParceiro.configurarDestino(this._destino);
			telaAssociarParceiro.limpar();
			telaAssociarParceiro.objetoAlteracao(tipoRelacionamento);
			telaAssociarParceiro.adicionarAreaTotal(obterAbaDadosBasicos().campoAreaTotal.valor);
			
			if(tipoRelacionamento != null) {
				telaAssociarParceiro.preencherCampos();
			}
			
			_janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosImovel {
			return this.parent.getChildByName("abaDadosBasicos") as DadosBasicosImovel;
		}
		
		/**
		 * Obtém o parceiro selecionado na janela de associação.
		 */
		private function parceiroSolicitado(evento:AssociarParceiroEvent):void {
			var lista:ArrayCollection = gridParceiros.dataProvider as ArrayCollection;
			var tipoRelacionamento:BemImovelTipoRelacionamentoVO = evento.objeto as BemImovelTipoRelacionamentoVO;
			
			/*var validarPercentual:Boolean = verificarPercentual(lista, tipoRelacionamento);
			if(!validarPercentual){
				Alerta.show("O percentual de parceiros associados não pode ser maior que 100%.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}*/
			
			if(!evento.edicao && !validarTipoRelacionamento(lista, tipoRelacionamento.idPessoaCompartilhamento, tipoRelacionamento.tipoRelacionamento.codigo)) {
				Alerta.show("O parceiro já foi selecionado para esse tipo de relacionamento.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			if(lista == null){
				lista = new ArrayCollection();
			} else {
				for(var i:uint = 0; i < lista.length; i++){
					var tipo:BemImovelTipoRelacionamentoVO = lista.getItemAt(i) as BemImovelTipoRelacionamentoVO;
					
					if(tipo == tipoRelacionamento){
						lista.removeItemAt(i);
					}
				}
			}
			
			lista.addItem(tipoRelacionamento);
			
			gridParceiros.dataProvider = lista;
			FlexUtil.atualizarComponente(gridParceiros);
		}
		
		/**
		 * Verifica se o percentual dos parceiros associados é maior que 100%.
		 */
		/*private function verificarPercentual(lista:ArrayCollection, objeto:BemImovelTipoRelacionamentoVO):Boolean {
			var valor:Number = 0;
			
			if(lista != null){
				for each(var tipo:BemImovelTipoRelacionamentoVO in lista) {
					if(tipo != objeto) {
						valor += tipo.percentualRelacionamento;						
					}
				}
			}
			
			valor += objeto.percentualRelacionamento;
			
			return valor <= 100;
		}*/
		
		/**
		 * Verifica se a pessoa já faz parte desse relacionamento com esse tipo de relacionamento.
		 */
		private function validarTipoRelacionamento(lista:ArrayCollection, idPessoaCompartilhamento:Number, codigoTipoRelacionamento:Number):Boolean {
			if(lista != null){
				for each(var tipo:BemImovelTipoRelacionamentoVO in lista) {
					if(tipo.tipoRelacionamento.codigo == codigoTipoRelacionamento && tipo.idPessoaCompartilhamento == idPessoaCompartilhamento) {
						return false;
					}
				}
			}
			return true;
		}
		
		/**
		 * Ação do botão de exclusão de parceiros.
		 */
		private function acaoBotaoExcluir(evento:Event):void {
			var tipoRelacionamento:BemImovelTipoRelacionamentoVO = gridParceiros.selectedItem as BemImovelTipoRelacionamentoVO;
			
			if(tipoRelacionamento != null){
				var lista:ArrayCollection = gridParceiros.dataProvider as ArrayCollection;
				
				lista.removeItemAt(gridParceiros.selectedIndex);
				
				gridParceiros.dataProvider = lista;
				FlexUtil.atualizarComponente(gridParceiros);
			}else {
				Alerta.show("Por favor, selecione um registro para exclusão.", "Erro", Alerta.ALERTA_ERRO);
			}
		}
		
		/**
		 * Fecha a janela de associação de parceiros.
		 */
		private function fecharJanelaAssociacao(evento:Event = null):void {
			_janela.fecharJanela();
		}
	}
}