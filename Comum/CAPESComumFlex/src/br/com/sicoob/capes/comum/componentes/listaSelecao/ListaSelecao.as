package br.com.sicoob.capes.comum.componentes.listaSelecao {
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.controls.List;
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.tipos.Booleano;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	
	/**
	 * Componente para seleção de itens em listas.
	 * 
	 * @author Bruno.Carneiro
	 **/
	public class ListaSelecao extends ListaSelecaoView {
		
		private var _modoVisualizacao:Boolean = false;
		private var _validarObrigatorio:Boolean = false;
		private var _propriedadeComparacao:String = "codigo";
		private var _listaDisponiveis:ArrayCollection = new ArrayCollection();
		private var _propriedadeItemObrigatorio:String = null;
		private var _exibirMensagemItemObrigatorio:Boolean = true;
		
		private static const TITULO_ITEM_OBRIGATORIO:String = "Item obrigatório";
		private static const MENSAGEM_ITEM_OBRIGATORIO:String = "O item selecionado é obrigatório e não pode ser removido.";
		private static const MENSAGEM_ITENS_OBRIGATORIOS_NAO_REMOVIDOS:String = "Foram removidos somente os itens que não são obrigatórios.";
		
		/**
		 * Método construtor.
		 */
		public function ListaSelecao():void {
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método executado ao inicializar.
		 */
		public function inicializar(evento:FlexEvent):void {
			botaoAdicionarTodos.addEventListener(MouseEvent.CLICK, adicionarTodos);
			botaoAdicionar.addEventListener(MouseEvent.CLICK, adicionarItem);
			botaoRemover.addEventListener(MouseEvent.CLICK, removerItem);
			botaoRemoverTodos.addEventListener(MouseEvent.CLICK, removerTodos);
			
			listaItensDisponiveis.dataProvider = new ArrayCollection();
			listaItensSelecionados.dataProvider = new ArrayCollection();
			
			listaItensSelecionados.validarObrigatorio = _validarObrigatorio;
			FlexUtil.atualizarComponente(listaItensSelecionados);
		}
		
		/**
		 * Seleciona o primeiro item na lista informada.
		 */
		private function selecionaPrimeiroItem(lista:List):void {
			if(lista.dataProvider.length > 0){
				lista.selectedItem = lista.dataProvider[0];
			}
		}
		
		/**
		 * Habilita/Desabilita os botões, de acordo com o modo de visualização.
		 */
		private function habilitarBotoes():void {
			botaoAdicionar.enabled = !_modoVisualizacao;
			botaoAdicionarTodos.enabled = !_modoVisualizacao;
			botaoRemover.enabled = !_modoVisualizacao;
			botaoRemoverTodos.enabled = !_modoVisualizacao;
		}
		
		/**
		 * Método de ação do botão de adicionar item.
		 */
		private function adicionarItem(evento:MouseEvent = null):void {
			var item:Object = obterItemSelecionadoListaSelecao();
			adicionarItemListaSelecionados(item);
		}
		
		/**
		 * Adiciona o botão à lista de selecionados.
		 */
		private function adicionarItemListaSelecionados(item:Object):void {
			if(item != null){
				adicionarItemListaItensSelecionados(item);
				removerItemListaDisponiveis(item);
				selecionaPrimeiroItem(listaItensDisponiveis);
				selecionaPrimeiroItem(listaItensSelecionados);
			}
		}
		
		/**
		 * Método de ação do botão de adicionar todos os itens. 
		 */
		private function adicionarTodos(evento:MouseEvent = null):void {
			ArrayCollection(listaItensSelecionados.dataProvider).addAll(ArrayCollection(listaItensDisponiveis.dataProvider));
			ArrayCollection(listaItensDisponiveis.dataProvider).removeAll();
		}
		
		/**
		 * Método de ação do botão de remover item
		 */
		private function removerItem(evento:MouseEvent = null):void {
			var item:Object = obterItemSelecionadoListaItensSelecionados();
			removeItemListaSelecionados(item);
		}
		
		/**
		 * Remove o item da lista de selecionados.
		 */
		private function removeItemListaSelecionados(item:Object):void {
			if(item != null) {
				if(propriedadeItemObrigatorioInformada()) {
					if(verificarItemObrigatorio(item)) {
						if(_exibirMensagemItemObrigatorio) {
							Alerta.show(MENSAGEM_ITEM_OBRIGATORIO, TITULO_ITEM_OBRIGATORIO, Alerta.ALERTA_ERRO);
						}
						return;
					}
				}
				
				adicionarItemListaSelecao(item);
				removerItemListaItensSelecionados(item);
				selecionaPrimeiroItem(listaItensDisponiveis);
				selecionaPrimeiroItem(listaItensSelecionados);
			}
		}
		
		/**
		 * Método de ação do botão de remoção de todos os itens.
		 */
		private function removerTodos(evento:MouseEvent = null):void {
			var itensRemover:ArrayCollection = new ArrayCollection();
			var itensPermanecer:ArrayCollection = new ArrayCollection();
			
			if(propriedadeItemObrigatorioInformada()) {
				var selecionados:ArrayCollection = ArrayCollection(listaItensSelecionados.dataProvider);
				if(selecionados != null && selecionados.length > 0) {
					for each (var item:Object in selecionados) {
						if(!verificarItemObrigatorio(item)) {
							itensRemover.addItem(item);
						} else {
							itensPermanecer.addItem(item);
						}
					}
				}
				
				if(_exibirMensagemItemObrigatorio) {
					Alerta.show(MENSAGEM_ITENS_OBRIGATORIOS_NAO_REMOVIDOS, TITULO_ITEM_OBRIGATORIO, Alerta.ALERTA_ERRO);
				}
				
			} else {
				itensRemover = ArrayCollection(listaItensSelecionados.dataProvider);
			}
			
			ArrayCollection(listaItensDisponiveis.dataProvider).addAll(itensRemover);
			ArrayCollection(listaItensSelecionados.dataProvider).removeAll();
			ArrayCollection(listaItensSelecionados.dataProvider).addAll(itensPermanecer);
		}
		
		/**
		 * Adiciona um item à lista de itens selecionados.
		 */
		private function adicionarItemListaItensSelecionados(item:Object):void {
			ArrayCollection(listaItensSelecionados.dataProvider).addItem(item);
		}
		
		/**
		 * Adiciona um item à lista de itens disponíveis.
		 */
		private function adicionarItemListaSelecao(item:Object):void {
			ArrayCollection(listaItensDisponiveis.dataProvider).addItem(item);
		}
		
		/**
		 * Obtém o item selecionado na lista de itens disponíveis.
		 */
		private function obterItemSelecionadoListaSelecao():Object {
			return listaItensDisponiveis.selectedItem;
		}
		
		/**
		 * Obtém o item selecionado na lista de itens selecionados.
		 */
		private function obterItemSelecionadoListaItensSelecionados():Object {
			return listaItensSelecionados.selectedItem;
		}
		
		/**
		 * Remove o objeto passado da lista lista de itens disponíveis.
		 */
		private function removerItemListaDisponiveis(item:Object):void {
			ArrayCollection(listaItensDisponiveis.dataProvider).removeItemAt(ArrayCollection(listaItensDisponiveis.dataProvider).getItemIndex(item));
		}
		
		/**
		 * Remove o objeto passado da lista de itens selecionados.
		 */
		private function removerItemListaItensSelecionados(item:Object):void {
			ArrayCollection(listaItensSelecionados.dataProvider).removeItemAt(ArrayCollection(listaItensSelecionados.dataProvider).getItemIndex(item));
		}
		
		/**
		 * Verifica se algum dos itens adicionados à lista de disponíveis é obrigatório.
		 */
		private function verificarItensObrigatorios():void {
			if(propriedadeItemObrigatorioInformada()) {
				var disponiveis:ArrayCollection = ArrayCollection(listaItensDisponiveis.dataProvider);
				if(disponiveis != null && disponiveis.length > 0) {
					for each (var item:Object in disponiveis) {
						if(verificarItemObrigatorio(item)) {
							removerItemListaDisponiveis(item);
							adicionarItemListaItensSelecionados(item);
						}
					}
				}
			}
		}
		
		/**
		 * Verifica se o item informado é obrigatório.
		 */
		private function verificarItemObrigatorio(item:Object):Boolean {
			var itemObrigatorio:Boolean = false;
			if(item != null) {
				var valor:Object = FlexUtil.obterValorPropriedade(item, _propriedadeItemObrigatorio);
				
				if(valor is Booleano) {
					var valorBooleano:Booleano = valor as Booleano;
					itemObrigatorio = valorBooleano.valor;
				} else if (valor is Boolean) {
					itemObrigatorio = valor;
				}
				
			}
			return itemObrigatorio;
		}
		
		/**
		 * Verifica se a propriedade do item obrigatório foi informada.
		 */
		private function propriedadeItemObrigatorioInformada():Boolean {
			return !FlexUtil.isTextoVazioOuNulo(_propriedadeItemObrigatorio);
		}
		
		/**
		 * Método sobreescrito para validar a exibição dos botões.
		 */
		public override function set enabled(valor:Boolean):void {
			super.enabled = true;
			_modoVisualizacao = !valor;
			
			if(initialized) {
				habilitarBotoes();
				
				listaItensDisponiveis.selectable = valor;
				listaItensSelecionados.selectable = valor;
				
				if(!valor) {
					listaItensDisponiveis.selectedIndex = -1;
					listaItensSelecionados.selectedIndex = -1;
				}
			}
		}
		
		//-----------------------------------------------------------------------------------------------
		// Métodos públicos.
		//-----------------------------------------------------------------------------------------------
		
		/**
		 * Adiciona a lista dos itens disponíveis a serem exibidos.
		 */
		public function adicionarListaRegistrosDisponiveis(itensDisponiveis:ListCollectionView):void {
			FlexUtil.clonarLista(_listaDisponiveis, itensDisponiveis);
			listaItensDisponiveis.dataProvider = itensDisponiveis;
			habilitarBotoes();
			verificarItensObrigatorios();
		}
		
		/**
		 * Adiciona os registros selecionados, que sairão da lista de disponíveis.
		 */
		public function adicionarListaRegistrosSelecionados(itensSelecionados:ListCollectionView):void {
			limpar();
			
			if(itensSelecionados != null && itensSelecionados.length > 0) {
				var itensDisponiveis:ArrayCollection = this.listaItensDisponiveis.dataProvider as ArrayCollection;
				if(itensDisponiveis != null && itensDisponiveis.length > 0) {
					var listaSelecionados:ArrayCollection = new ArrayCollection();
					for (var i:int = 0; i < itensSelecionados.length; i++) {
						var objetoSelecionado:Object = itensSelecionados[i];
						for (var j:int = 0; j < itensDisponiveis.length; j++) {
							var objetoDisponivel:Object = itensDisponiveis[j];
							if(objetoSelecionado[_propriedadeComparacao] == objetoDisponivel[_propriedadeComparacao]) {
								listaSelecionados.addItem(itensDisponiveis[j]);
								itensDisponiveis.removeItemAt(j);
							}
						}
					}
					
					listaItensDisponiveis.dataProvider = itensDisponiveis;
					listaItensSelecionados.dataProvider = listaSelecionados;
					
					FlexUtil.atualizarComponente(listaItensDisponiveis);
					FlexUtil.atualizarComponente(listaItensSelecionados);
				}
			}
		}
		
		/**
		 * Faz a limpeza dos campos, deixando apenas a lista de itens disponíveis da forma original. <br />
		 * 
		 * <b>
		 * 	Observação: Essa função limpa todos os campos e deixa apenas a lista de itens disponíveis original.
		 * 	Para exibir a lista dos itens selecionados, antes de fazer a limpeza, deverá chamar o método 
		 * 	'adicionarListaRegistrosSelecionados' novamente.
		 * </b>
		 */
		public function limpar():void {
			listaItensDisponiveis.dataProvider = new ArrayCollection();
			listaItensSelecionados.dataProvider = new ArrayCollection();
			FlexUtil.clonarLista(listaItensDisponiveis.dataProvider as ArrayCollection, _listaDisponiveis);
			FlexUtil.atualizarComponente(listaItensDisponiveis);
			FlexUtil.atualizarComponente(listaItensSelecionados);
		}
		
		/**
		 * Reliza a validação do componente.
		 **/
		public function validar():Boolean {
			return listaItensSelecionados.validar();
		}
		
		/**
		 * Limpa a lista dos itens selecionados. (equivale à função do botão 'Remover todos'.
		 */
		public function limparListaSelecionados():void {
			removerTodos();
			listaItensSelecionados.dataProvider = new ArrayCollection();
		}
		
		/**
		 * Limpa a lista dos itens disponíveis.
		 */
		public function limparListaDisponiveis():void {
			listaItensDisponiveis.dataProvider = null;
		}
		
		/**
		 * Obtém a lista dos registros selecionados.
		 */
		public function obterItensSelecionados():ArrayCollection{
			return ArrayCollection(listaItensSelecionados.dataProvider);
		}
		
		/**
		 * Informa o modo de visualização para exibição dos botões.
		 */
		public function set modoVisualizacao(valor:Boolean):void {
			_modoVisualizacao = valor;
		}
		
		/**
		 * Valida a obrigatoriedade do campo
		 */
		public function set validarObrigatorio(valor:Boolean): void {
			_validarObrigatorio = valor;
		}
		
		/**
		 * Informa a propriedade que será comparada nos objetos.
		 */
		public function set propriedadeComparacao(valor:String):void {
			_propriedadeComparacao = valor;
		}
		
		/**
		 * Define uma propriedade do objeto para saber se o item é obrigatório.
		 * 
		 * <pre>
		 * <b>A propriedade deverá retornar um boolean, informando se o item é obrigatório ou não.</b>
		 * <pre>
		 */
		public function set propriedadeItemObrigatorio(valor:String):void {
			_propriedadeItemObrigatorio = valor;
		}
		
		/**
		 * Define se a mensagem de item obrigatório será exibida.
		 */
		public function set exibirMensagemItemObrigatorio(valor:Boolean):void {
			_exibirMensagemItemObrigatorio = valor;
		}
		
		//TODO: remover os GETs e adicionar as propriedades após o CREATION_COMPLETE
		// Teste para saber se o flex utiliza a propriedade GET para criar o componente.
		
		/**
		 * Informa o modo de visualização para exibição dos botões.
		 */
		public function get modoVisualizacao():Boolean{
			return _modoVisualizacao;
		}
		
		/**
		 * Valida a obrigatoriedade do campo
		 */
		public function get validarObrigatorio():Boolean {
			return _validarObrigatorio;
		}
		
		/**
		 * Informa a propriedade que será comparada nos objetos.
		 */
		public function get propriedadeComparacao():String {
			return _propriedadeComparacao;
		}
		
		/**
		 * Define uma propriedade do objeto para saber se o item é obrigatório.
		 * 
		 * <pre>
		 * <b>A propriedade deverá retornar um boolean, informando se o item é obrigatório ou não.</b>
		 * <pre>
		 */
		public function get propriedadeItemObrigatorio():String {
			return _propriedadeItemObrigatorio;
		}
		
		/**
		 * Define se a mensagem de item obrigatório será exibida.
		 */
		public function get exibirMensagemItemObrigatorio():Boolean {
			return _exibirMensagemItemObrigatorio;
		}
	}
}