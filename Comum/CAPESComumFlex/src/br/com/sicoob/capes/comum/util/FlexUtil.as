package br.com.sicoob.capes.comum.util {
	import flash.utils.ByteArray;
	import flash.utils.describeType;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.core.UIComponent;
	import mx.events.ListEvent;
	import mx.utils.ObjectUtil;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.util.StringUtils;
	
	/**
	 * Classe contendo métodos úteis para uso nas telas do flex.
	 * 
	 * @author Bruno.Carneiro
	 **/
	public class FlexUtil {
		
		/**
		 * Método construtor.
		 **/
		function FlexUtil() {
			
		}
		
		/**
		 * Atualiza a combo com a lista informada.
		 * 
		 * @param combo
		 * 			Combo a ter a lista adicionada.
		 * 
		 * @param lista
		 * 			Lista a ser adicionada na combo.
		 * 
		 * @param bolAdicionarItemOpcional
		 * 			Boolean informando se deve adicionar o item opcional. (Valor padrão: <code>false</code>)
		 * 
		 * @param labelItemOpcional 
		 * 			Texto do item opcional. (Valor padrão: <code>"SELECIONE"</code>)
		 * 
		 * @param selecaoAutomatica
		 * 			Boolean informando se caso a lista tenha somente um item a opção deverá ser selecionada automaticamente.
		 */
		public static function atualizarCombo(combo:Combo, lista:Object, bolAdicionarItemOpcional:Boolean = false, labelItemOpcional:String = "SELECIONE", selecaoAutomatica:Boolean = false):void {
			var colecao:IList;
			
			// Verifica se a lista informada não está nula, se estiver adiciona uma lista vazia.
			if (lista == null){
				colecao = new ArrayCollection();
			}else if (lista is Array) {
				colecao = new ArrayCollection(lista as Array);
			}else if (lista is IList) {
				colecao = new ListCollectionView(IList(lista));
			}
			
			// Verifica se a lista da combo foi inicializada, se não, inicializa.
			if (combo.dataProvider == null){
				combo.dataProvider = new ArrayCollection();
			}
			
			// Clona a lista e adiciona na combo.
			ListCollectionView(combo.dataProvider).removeAll();
			clonarLista(ListCollectionView(combo.dataProvider), colecao);
			
			// Adiciona um item opcional, caso seja solicitado.
			if(bolAdicionarItemOpcional){
				adicionarItemOpcional(combo, labelItemOpcional);
			}
			
			// Atualiza o componente e seleciona o primeiro objeto(Item opcional);
			atualizarComponente(combo);
			
			if(selecaoAutomatica && colecao.length == 1) {
				combo.selectedIndex = 1;
				combo.dispatchEvent(new ListEvent(ListEvent.CHANGE));
			}else {
				combo.selectedIndex = 0;
			}
		}
		
		/**
		 * Clona as listas para evitar problemas de referência.
		 * 
		 * @param listaDestino
		 *			Lista de destino para receber os objetos de origem (Obs: a lista pode estar nula).
		 * 
		 * @param listaOrigem
		 * 			Lista com os objetos a serem clonados para a outra lista.
		 */
		public static function clonarLista(listaDestino:IList, listaOrigem:IList):void {
			
			// se a lista de origem for nula, retorna.
			if(listaOrigem == null || listaOrigem.length == 0) {
				return;
			}
			
			// Se a lista de destino estiver nula, inicializa a mesma.
			if(listaDestino == null){
				listaDestino = new ArrayCollection();
			}
			
			// Adiciona na lista de destino cada objeto da lista de origem.
			for each (var elemento:Object in listaOrigem) {
				listaDestino.addItem(clonar(elemento));
			}
		}
		
		/**
		 * Adiciona o item opcional ao início da lista, se este ainda não existir
		 * 
		 * @param combo
		 * 			Combo a ser adicionado o item opcional.
		 * 
		 * @param label
		 * 			Texto do item opcional. (Valor padrão: <code>"TODOS"</code>)
		 */
		public static function adicionarItemOpcional(combo:Combo, label:String = "TODOS"):void {
			if (combo.dataProvider is IList) {
				var lista:IList = (combo.dataProvider as IList);
				if (lista.length == 0 || (lista.getItemAt(0) != null && 
					!lista.getItemAt(0).hasOwnProperty("idItemOpcionalCombo"))) {
					lista.addItemAt(criarItemOpcional(combo, label), 0);
				}
			}
		}
		
		/**
		 * Adiciona o item opcional ao início da lista, se este ainda não existir
		 */		
		private static function criarItemOpcional(combo:Combo, label:String):Object {
			var objeto:Object = new Object();
			
			objeto[combo.labelField] = label;
			objeto.idItemOpcionalCombo = -1;
			
			return objeto;
		}
		
		/**
		 * Seleciona um item dentro de uma combo.
		 * 
		 * @param combo
		 * 			A combo a ter o item selecionado.
		 * 
		 * @param objeto
		 * 			Objeto a ser selecionado dentro da combo.
		 * 
		 * @param propriedade
		 * 			A propriedade a ser utilizada na comparação do objeto. (Valor padrão: <code>codigo</code>)
		 * 
		 * @param dispararEventoMudanca
		 * 			Boolean informando se a combo deve disparar o evento de mudança(ListEvent.CHANGE). (Valor padrão: <code>true</code>).
		 */		
		public static function selecionarItemCombo(combo:Combo, objeto:Object, propriedade:String = "codigo", dispararEventoMudanca:Boolean = true):void {
			// Verifica se a combo está pronta e o objeto a ser selecionado está preenchido.
			if(combo != null && combo.initialized && objeto != null) {
				// Percorre os objetos na lista da combo.
				var lista:ListCollectionView = combo.dataProvider as ListCollectionView;
				for(var i:int = 0; i < lista.length; i++){
					var obj:Object = lista[i];
					
					// Verifica se o objeto possui a propriedade informada.
					if(obj.hasOwnProperty(propriedade) && objeto.hasOwnProperty(propriedade)) {
						// Se a propriedade for igual, seleciona na lista.
						if(obj[propriedade] == objeto[propriedade]) {
							combo.selectedIndex = i;
							break;
						}
					}
				}
				
				// Atualiza a combo.
				atualizarComponente(combo);
				
				// Se marcado, dispara o evento de mudança no objeto selecionado.
				if(dispararEventoMudanca) {
					combo.dispatchEvent(new ListEvent(ListEvent.CHANGE));
				}
			} else if(combo != null && combo.initialized) {
				combo.selectedIndex = 0;
			}
		}
		
		/**
		 * Seleciona um valor dentro de uma combo.
		 * 
		 * <br /> <b>Exemplo: </b> <br />
		 * 
		 * Sua combo é uma lista de certo objeto, porém você não salva o objeto, somente o valor do campo "descrição" então,
		 * você pode procurar os itens pelo valor de uma propriedade.
		 * 
		 * @param combo
		 * 			A combo a ter o valor selecionado.
		 * 
		 * @param valor
		 * 			O Valor a ser selecionado dentro da combo.
		 * 
		 * @param propriedade
		 * 			A propriedade a ser utilizada na comparação do objeto. (Valor padrão: <code>codigo</code>)
		 * 
		 * @param dispararEventoMudanca
		 * 			Boolean informando se a combo deve disparar o evento de mudança(ListEvent.CHANGE). (Valor padrão: <code>true</code>).
		 */
		public static function selecionarValorCombo(combo:Combo, valor:Object, propriedade:String = "codigo", dispararEventoMudanca:Boolean = true):void {
			var objeto:Object = new Object();
			objeto[propriedade] = valor;
			selecionarItemCombo(combo, objeto, propriedade, dispararEventoMudanca);
		}
		
		/**
		 * Método que valida/atualiza o componente, para refletir as mudanças de lista, etc...
		 * 
		 * @param componente
		 * 			Componente a ser validado/atualizado.
		 */
		public static function atualizarComponente(componente:UIComponent):void {
			componente.validateDisplayList();
			componente.validateNow();
			
			if(componente is Combo || componente is Grid) {
				var lista:ListCollectionView = obterValorPropriedade(componente, "dataProvider");
				if(lista != null) {
					lista.refresh();
				}
			}
		}
		
		/**
		 * Transforma as strings vazias (<code>""</code>) para nulo.
		 * 
		 * @param string
		 * 			A string que, se estiver vazia, será retornada como <code>null</code>.
		 * 
		 * @return <code>String</code> A string de retorno.
		 */
		public static function stringVaziaParaNulo(string:String):String {
			var retorno:String = StringUtil.trim(string);
			
			if(retorno != ""){
				return retorno;
			}
			
			return null;
		}
		
		/**
		 * Transforma os campos nulos para uma string vazia (<code>""</code>)
		 * 
		 * @param string
		 * 			A string que, se estiver nula, será retornada como vazia.
		 * 
		 * @return <code>String</code> A string de retorno.
		 */
		public static function nuloParaString(string:String):String {
			if(string != null) {
				return StringUtil.trim(string);
			}
			
			return "";
		}
		
		/**
		 * Transforma o valor de um campo vazio(zero) para nulo(NaN).
		 * 
		 * @param numero
		 * 			O <code>Number</code> que se tiver o valor 0(zero) será retornado como <code>null</code>(NaN).
		 * 
		 * @param ignorarNumerosNegativos
		 * 			Ignora os números que são menores que zero. <b>Default: true</b>
		 * 
		 * @return <code>Number</code> Número de retorno, podendo ser <code>null</code>(NaN).
		 */
		public static function campoNumericoZeroParaNulo(numero:Number, ignorarNumerosNegativos:Boolean = true):Number {
			if(!isNaN(numero) && numero != 0) {
				if(ignorarNumerosNegativos && numero < 0) {
					return NaN;
				} else {
					return numero;
				}
			}
			return NaN;
		}
		
		/**
		 * Compara os valores números mesmo que sejam nulos(NaN).
		 * 
		 * @param numero1
		 * 			O primeiro número a ser comparado.
		 * 
		 * @param numero2
		 * 			O segundo número a ser comparado.
		 * 
		 * @return <code>Boolean</code> O resultado da comparação.
		 */
		public static function compararValoresNumericos(numero1:Number, numero2:Number):Boolean {
			var num1:Number = campoNumericoZeroParaNulo(numero1);
			var num2:Number = campoNumericoZeroParaNulo(numero2);
			
			if(isNaN(num1) && isNaN(num2)) {
				return true;
			}
			
			return num1 == num2;
		}
		
		/**
		 * Compara os objetos usando a propriedade informada.
		 * 
		 * @param objeto1
		 * 			O primeiro objeto a ser usado na comparação.
		 * 
		 * @param objeto2
		 * 			O segundo objeto a ser usado na comparação.
		 * 
		 * @param propriedade
		 * 			A propriedade a ser usada para a comparação. (default: <code>"codigo"</code>)
		 * 
		 * @return <code>Boolean</code> O resultado da comparação.
		 */
		public static function compararObjetos(objeto1:Object, objeto2:Object, propriedade:String = "codigo"):Boolean {
			if(objeto1 == null && objeto2 == null) {
				return true;
			}
			
			var valorProp1:* = obterValorPropriedade(objeto1, propriedade);
			var valorProp2:* = obterValorPropriedade(objeto2, propriedade);
			
			return valorProp1 == valorProp2;
		}
		
		/**
		 * Recupera o valor de uma propriedade de um objeto. Aceita os valores separados por <code>"."</code>
		 * <br />
		 * Ex: <code>tipoPessoa.descricao</code>
		 * 
		 * @param objeto
		 * 			O objeto a ter a propriedade recuperada.
		 * 
		 * @param propriedade
		 * 			A propriedade a ser recuperada.
		 * 
		 * @return O valor da propriedade.
		 */
		public static function obterValorPropriedade(objeto:*, propriedade:String):* {
			var listaPropriedades:Array = propriedade.split(".");
			
			if(objeto != null && listaPropriedades != null) {
				var retorno:* = objeto;
				for(var i:uint = 0; i < listaPropriedades.length; i++) {
					var prop:String = listaPropriedades[i];
					
					// Verificamos se o objeto possui a propriedade e não paramos o laço, para o caso
					// das propriedades que são chamadas por "." Ex: pessoa.nome;
					if(retorno != null && retorno.hasOwnProperty(prop)) {
						retorno = retorno[prop];
					}
				}
				return retorno;
			} /*else {
				if(objeto.hasOwnProperty(propriedade)) {
					return objeto[propriedade];
				}
			}*/
			return undefined;
		}
		
		/**
		 * Verifica se um texto está vazio ou nulo.
		 * 
		 * @param texto
		 * 			O texto a ser verificado.
		 * 
		 * @return <code>Boolean</code> O resultado da verificação.
		 **/
		public static function isTextoVazioOuNulo(texto:String):Boolean {
			var isVazio:Boolean = texto == null;
			if(!isVazio) {
				var stringTrim:String = StringUtils.trim(texto);
				isVazio = stringTrim == "" || StringUtils.isWhitespace(stringTrim);
			}
			return isVazio;
		}
		
		/**
		 * Copia as propriedades comuns entre os objetos.
		 * 
		 * @param fonte
		 * 			O objeto fonte dos dados para serem copiados
		 * 
		 * @param destino
		 * 			O objeto destino que terá as informações que foram copiadas da fonte.
		 * 
		 * @param propriedadesIgnoradas
		 * 			<code>Array</code> com as propriedades a serem ignoradas na copia.
		 */
		public static function copiarPropriedades(fonte:Object, destino:Object, ... propriedadesIgnoradas):void {
			if((fonte) && (destino)) {
				var propriedades:Array = ObjectUtil.getClassInfo(fonte).properties;
				
				for each (var propriedade:QName in propriedades) {
					var nomePropriedade:String = propriedade.localName;
					
					if(!(isPropriedadeIgnorada(propriedadesIgnoradas, nomePropriedade)) && fonte[nomePropriedade] != null) {
						if(destino.hasOwnProperty(nomePropriedade)) {
							try {
								destino[nomePropriedade] = fonte[nomePropriedade];
							} catch (erro:*) {
								trace("Erro ao copiar a propriedade: ", nomePropriedade, " Erro(", erro, ")");
							}
						}
					}
				}
			}
		}
		
		/**
		 * Verifica se a propriedade está na lista de propriedades ignoradas.
		 * 
		 * @param array
		 * 			O <code>Array</code> com as propriedades ignoradas.
		 * 
		 * @param propriedade
		 * 			O nome da propriedade para ser verificada na lista.
		 * 
		 * @return <code>Boolean</code> se a propriedade está ou não na lista de propriedades ignoradas
		 **/
		private static function isPropriedadeIgnorada(array:Array, propriedade:String):Boolean {
			return array != null ? array.indexOf(propriedade) != -1 : false;
		}
		
		/**
		 * Clona um objeto, removendo suas referências. 
		 * (cópia profunda: uma nova instância com a mesma informação do original).
		 * 
		 * @param fonte
		 * 			O objeto a ser clonado
		 * 
		 * @return <code>Object</code> O clone do objeto.
		 */
		public static function clonar(fonte:Object):* {
			var buffer:ByteArray = new ByteArray();
			buffer.writeObject(fonte);
			buffer.position = 0;
			return buffer.readObject();
		}
		
		/**
		 * Copia os objetos utilizando seus métodos acessores.
		 * 
		 * @param fonte
		 * 			O objeto fonte dos dados para serem copiados
		 * 
		 * @param destino
		 * 			O objeto destino que terão as informações que foram copiadas da fonte.
		 * 
		 * @param propriedadesIgnoradas
		 * 			<code>Array</code> com as propriedades a serem ignoradas na copia.
		 */
		public static function copiarPropriedadesUtilizandoAcessores(fonte:Object, destino:Object, ... propriedadesIgnoradas):void {
			if((fonte) && (destino)) {
				try {
					var propriedades:XML = describeType(fonte);
					var propriedade:XML;
					
					/*for each(propriedade in propriedades.variable) {
						if(!(isPropriedadeIgnorada(propriedadesIgnoradas, propriedade.@name)) && fonte[propriedade.@name] != null){
							if(destino.hasOwnProperty(propriedade.@name)) {
								destino[propriedade.@name] = fonte[propriedade.@name];
							}
						}
					}*/
					
					for each(propriedade in propriedades.accessor) {
						if(propriedade.@access == "readwrite") {
							if(!(isPropriedadeIgnorada(propriedadesIgnoradas, propriedade.@name)) && fonte[propriedade.@name] != null){
								if(destino.hasOwnProperty(propriedade.@name)) {
									destino[propriedade.@name] = fonte[propriedade.@name];
								}
							}
						}
					}
				} catch (erro:*) {
					trace("Erro ao copiar a propriedades dos objetos. Erro(", erro, ")");
				}
			}
		}
		
		/**
		 * Ordena uma lista de objetos
		 * 
		 * @param lista
		 * 			A lista de objetos para ser ordenada.
		 * 
		 * @param nomeCampo
		 * 			O nome do campo do objeto para ser usado na ordenação.
		 * 
		 * @param isNumerico
		 * 			Informa se o campo para ordenação é numérico ou não.
		 */
		public static function ordenarColecao(lista:ListCollectionView, nomeCampo:String, isNumerico:Boolean = false, ascendente:Boolean = true):void {
			// Cria o objeto SortField para o campo do objeto na coleção e garantimos se é uma ordenação de campo numérico ou não
			var dataSortField:SortField = new SortField();
			dataSortField.name = nomeCampo;
			dataSortField.numeric = isNumerico;
			dataSortField.descending = !ascendente;
			
			// Cria o objeto Sort e adiciona o Sortfield criado acima para ordenar os campos.
			var sort:Sort = new Sort();
			sort.fields = [dataSortField];
			
			// Define a propriedade sorte da lista para o nosso ordenador e atualiza a lista.
			lista.sort = sort;
			lista.refresh();
		}
		
		/**
		 * Retorna o objeto e suas propriedades.
		 * 
		 * @param objeto
		 * 			O objeto que será transformado em uma String.
		 * 
		 * @param multiLinha
		 * 			Se o retorno terá quebra de linha ou não.
		 * 
		 * @return <code>String</code> As propriedades e valores do objeto.
		 */
		public static function objetoToString(objeto:Object, multiLinha:Boolean = true, ... propriedadesIgnoradas):String {
			var retorno:StringBuffer = new StringBuffer();
			
			if(objeto) {
				var informacaoClasse:Object = ObjectUtil.getClassInfo(objeto, propriedadesIgnoradas);
				
				retorno.append("(");
				retorno.append(informacaoClasse.name);
				retorno.append(")#");
				retorno.append(multiLinha ? "\n" : " ");
				
				var propriedades:Array = ObjectUtil.getClassInfo(objeto).properties;
				for each (var propriedade:QName in propriedades) {
					if(!(isPropriedadeIgnorada(propriedadesIgnoradas, nomePropriedade))){
						var nomePropriedade:String = propriedade.localName;
						retorno.append(nomePropriedade);
						retorno.append(" = ");
						retorno.append(ObjectUtil.toString(objeto[nomePropriedade]));
						retorno.append(multiLinha ? "\n" : " ");
					}
				}
			}
			
			return retorno.toString();
		}
		
	}
}