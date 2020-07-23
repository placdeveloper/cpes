package br.com.sicoob.capes.autorizarAlteracoes {
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.containers.HBox;
	import mx.controls.Label;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.Rotulo;
	import br.com.bancoob.componentes.RotuloMultLine;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.componentes.input.TextoArea;
	import br.com.bancoob.componentes.titulo.LinhaView;
	import br.com.bancoob.componentes.titulo.SubtituloView;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.CampoTelaListaVO;
	import br.com.sicoob.capes.comum.vo.CampoTelaVO;
	import br.com.sicoob.capes.comum.vo.CamposTelaVO;
	import br.com.sicoob.capes.comum.vo.OcorrenciaAtividadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoDocumentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.DocumentoComprobatorioVO;
	
	public class AutorizacaoEdicao extends AutorizacaoEdicaoView {
		
		static public const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.AutorizarFachada";
		static private const BOTAO_EXECUTAR:String = "btnExecutar";
		static private const OPERACAO_OBTER_DADOS: String = "obterDados";
		
		[Embed(source="br/com/bancoob/imagens/icos/listaAplicativos/edit_16.png")]
		private var iconeDadosFluxo:Class;
		
		private var _listaProcedimento : ListCollectionView;
		private var _historico : ListCollectionView;
		private var _documentosVigentes : ListCollectionView;
		private var _exbirJustificativa : Boolean = true;
		private var documentosNovos : ListCollectionView;
		private var botaoExecutar : Botao;
		private var _camposTelaVO:CamposTelaVO;
		
		/*[Bindable]
		private var atividades:ListCollectionView = new ArrayCollection();*/
		
		[Bindable]
		private var procedimentos:ListCollectionView = new ArrayCollection();
		
		public function AutorizacaoEdicao() {
			super();
			this.addEventListener(FlexEvent.ADD, montarTela);
		}
		
		//***********
		// Callbacks:
		//***********
		
		protected override function init(event: FlexEvent): void {
			super.init(event);

			this.botaoExecutar = new Botao();
			this.botaoExecutar.id = this.botaoExecutar.name = BOTAO_EXECUTAR;
			this.botaoExecutar.label = "Executar"; 
			this.botaoExecutar.addEventListener(MouseEvent.CLICK, onClickBotaoExecutar);
			this.botaoExecutar.height = 22;
			
			this.subTituloInfoFluxo.caminhoIcone = this.iconeDadosFluxo;
			
			/*this.atividade.addEventListener(ListEvent.CHANGE, onChangeAtividade);
			this.atividade.dataProvider = atividades;*/
			
			/*this.procedimento.dataProvider = atividades;*/
			this.procedimento.dataProvider = procedimentos;
			
			//se a largura for <90 define como 90 (padrão)
			this.botaoExecutar.width = this.botaoExecutar.width < 90 ? 90 : this.botaoExecutar.width;
			BarraBotoesFormularioCadastroView(this.barraBotoes).botoesFormularioCadastro.addChildAt(this.botaoExecutar, 0);
			
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			montarTela();
		}
		
		private function onClickBotaoExecutar(event : MouseEvent) : void {
			executarSeValido(confirmarExecucao);
		}
		
		private function confirmarExecucao():void {
			Alerta.show("Tem certeza de que deseja executar o procedimento \""
				+ this.procedimento.selectedItem.nomeProcedimento + "\" ?", "CONFIRMAÇÃO", Alerta.ALERTA_PERGUNTA,
				this.procedimento, executarProcedimento);
		}
		
		protected function executarProcedimento(event:MouseEvent = null) : void {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.item = this.objeto;
			dto.dados.justificativa = this.justificativa.text;
			/*dto.dados.atividade = this.atividade.selectedItem;*/
			dto.dados.procedimento = this.procedimento.selectedItem;
			this.servicoEdicao.getOperation("executarProcedimento").send(dto);
		} 
		
		/*private function onChangeAtividade(event:ListEvent = null):void {
			var atividade:OcorrenciaAtividadeVO = converterOcorrenciaAtividadeVO(this.atividade.selectedItem);
			if (atividade && atividade != null) { 
				this.procedimentos.removeAll();
				this.procedimentos.addAll(atividade.procedimentosDestino as ArrayCollection);
				for each(var proc:ProcedimentoVO in this.procedimentos) {
					if (proc.procedimentoPadrao) {
						this.procedimento.procuraItemPorNome(proc.id, "id");
						break;
					}
				}
			}
		}*/
		
		//*************************************
		// Métodos Auxiliares: montagem da tela
		//*************************************
		
		private function montarTela(event : FlexEvent = null) : void {
			if (initialized) {
				var linhas:ArrayCollection = new ArrayCollection();
				for (var i:int; i < this.camposTela.campos.length; i++) { 
					var campo:CampoTelaVO = CampoTelaVO(this.camposTela.campos.getItemAt(i));
					linhas.addItem(criarLinhaCampos(campo));
				}
				
				for (var j:int; j < this.camposTela.listas.length; j++) { 
					var campoLista:CampoTelaListaVO = CampoTelaListaVO(this.camposTela.listas.getItemAt(j));
					linhas.addAll(criarLinhaCamposLista(campoLista));
				}
				
				adicionarCampos(linhas);
				preencherCamposFixos(AutorizacaoVO(this.objeto));
				configurarAbaHistorico();
				configurarAbaDocumentos();
				this.painelAbas.selectedIndex = 0;
				
				container.verticalScrollPosition = 0;
			}
		}
		
		private function criarLinhaCampos(vo : CampoTelaVO): HBox {
			// cria uma linha horizontal
			var linha:HBox = new HBox();
			linha.percentWidth = 100;
			
			// cria o label
			var label : Label = vo.label.length > 20 ? new RotuloMultLine() : new Rotulo();
			label.enabled = true;
			label.setStyle("textAlign", "right");
			label.width = LARGURA_PADRAO_LABEL;
			label.text = vo.label + ":";
			
			// cria o campo para o valor
			var campoAntigo : UIComponent;
			var campoNovo : UIComponent;
			
			var valorAntigo:String = vo.valorAntigo;
			var valorNovo:String = vo.valorNovo;
			
			if ((valorAntigo.length > 42) || (valorNovo.length > 42) || (label is RotuloMultLine)) {
				var textoArea : TextoArea;
				
				textoArea = new TextoArea();
				textoArea.text = valorAntigo.toUpperCase();
				textoArea.editable = false;
				textoArea.height = 50;
				campoAntigo = textoArea;
				
				textoArea = new TextoArea();
				textoArea.text = valorNovo.toUpperCase();
				textoArea.editable = false;
				textoArea.height = 50;
				campoNovo = textoArea;
			} else {
				var texto : Texto;
				
				texto = new Texto();
				texto.text = valorAntigo.toUpperCase();
				texto.editable = false;
				campoAntigo = texto;
				
				texto = new Texto();
				texto.text = valorNovo.toUpperCase();
				texto.editable = false;
				campoNovo = texto;
			}
			
			if (vo.alterado) {
				campoNovo.errorString = "VALOR PROPOSTO";
			}
			
			// define a largura dos campos
			campoAntigo.width = LARGURA_PADRAO_CAMPOS;
			campoNovo.width = LARGURA_PADRAO_CAMPOS;
			
			// adiciona os campos na linha
			linha.addChildAt(label, 0);
			linha.addChildAt(campoAntigo, 1);
			linha.addChildAt(campoNovo, 2);
			return linha;
		}
		
		private function criarLinhaCamposLista(vo:CampoTelaListaVO):ArrayCollection {
			var retorno:ArrayCollection = new ArrayCollection();
			retorno.addItem(criarSubtituloLista(vo.nomeAtributo));
			
			var contador:Number = 0;
			for each (var campo:CampoTelaVO in vo.campos) {
				retorno.addItem(criarLinhaCampos(campo));
				
				contador++;
				if(contador == vo.contadorSeparador) {
					retorno.addItem(criarSeparador());
					contador = 0;
				}
			}
			
			return retorno;
		}
		
		private function criarSubtituloLista(valor:String):HBox {
			// cria uma linha horizontal
			var linha:HBox = new HBox();
			linha.percentWidth = 100;
			
			// cria o label
			var label:SubtituloView = new SubtituloView();
			label.enabled = true;
			label.setStyle("textAlign", "right");
			label.texto = valor;
			
			// adiciona os campos na linha
			linha.addChild(label);
			
			return linha;
		}
		
		private function criarSeparador():HBox {
			// cria uma linha horizontal
			var linha:HBox = new HBox();
			linha.percentWidth = 100;
			
			var separador:LinhaView = new LinhaView();
			separador.enabled = true;
			separador.percentWidth = 100;
			
			// adiciona os campos na linha
			linha.addChild(separador);
			
			return linha;
		}
		
		private function preencherCamposFixos(vo:AutorizacaoVO = null):void {
			
			if (vo != null) {
				this.codigoAutorizacao.valor = vo.idAutorizacao;
				this.cpfCnpj.tipoCampo = vo.pessoa.cpfCnpj.length > 11 ? 
					InputCPFCNPJ.TIPO_CNPJ : InputCPFCNPJ.TIPO_CPF;
				this.cpfCnpj.text = vo.pessoa.cpfCnpj;
				this.dataOperacao.selectedDate = IDateTime(vo.dataHoraSolicitacao).data;
				this.numeroCooperativa.text = FlexUtil.obterValorPropriedade(vo, propriedadeCooperativaGrid.toString());
				this.tipoAutorizacao.text = vo.tipoAutorizacao.descricao;
				this.tipoOperacao.text = vo.tipoOperacao.descricao;
				this.unidadeDestino.valor = vo.instituicaoDestino.idUnidadeInst;
			} else {
				this.codigoAutorizacao.text = "";
				this.cpfCnpj.tipoCampo = null;
				this.cpfCnpj.text = "";
				this.dataOperacao.selectedDate = null;
				this.numeroCooperativa.text = "";
				this.tipoAutorizacao.text = "";
				this.tipoOperacao.text = "";
				this.unidadeDestino.text = "";
			}
		}
		
		private function adicionarCampos(linhas : ArrayCollection) : void {
			limparTela();
			for (var i : int = 0; i < linhas.length; i++) {
				var componente : UIComponent = UIComponent(linhas.getItemAt(i));
				adicionarComponente(componente);
			}
			configurarComponentesAcao();
		}
		
		private function adicionarComponente(componente:DisplayObject):void {
			this.container.addChildAt(componente, this.container.numChildren);
		}
		
		protected function adicionarSubtitulo(texto : String, enabled : Boolean = true) : void {        
			var subtitulo : SubtituloView = new SubtituloView();
			subtitulo.texto = texto;
			subtitulo.enabled = enabled;
			adicionarComponente(subtitulo);
		}
		
		private function configurarComponentesAcao() : void {
			
			this.procedimentos.removeAll();
			this.procedimentos.addAll(this.listaProcedimento);
			/*onChangeAtividade();*/
			
			var habilitar:Boolean = existeAcao();
			this.botaoExecutar.enabled = habilitar;
			this.justificativa.editable = habilitar;
			/*this.atividade.enabled = habilitar;*/
			this.procedimento.enabled = habilitar;
		}
		
		private function existeAcao() : Boolean {
			return (this.procedimento.dataProvider.length > 0) 
					/*&& (this.atividade.dataProvider.length > 0)*/;
		}
		
		private function limparTela() : void {
			this.container.removeAllChildren();
			preencherCamposFixos();
			this.justificativa.text = "";
			this.procedimentos.removeAll();
			/*this.atividades.removeAll();*/
		}
		
		private function configurarAbaHistorico() : void {
			this.abaHistorico.grid.dataProvider = this.historico;
		}
		
		private function configurarAbaDocumentos() : void {
			this.gridVigente.grid.dataProvider = this.documentosVigentes;
			this.gridNovo.grid.dataProvider = this.documentosNovos;
		}
		
		//****************************
		// Métodos Auxiliares: get/set
		//****************************
		
		public function set camposTela(valor : CamposTelaVO) : void {
			this._camposTelaVO = valor;
		}
		public function get camposTela() : CamposTelaVO {
			return this._camposTelaVO;
		}
		
		public function set listaProcedimento(valor : ListCollectionView) : void {
			this._listaProcedimento = valor;
		}
		public function get listaProcedimento() : ListCollectionView {
			return this._listaProcedimento;
		}
		
		public function set historico(valor : ListCollectionView) : void {
			this._historico = valor;
		}
		public function get historico() : ListCollectionView {
			return this._historico;
		} 
		
		public function set documentosVigentes(valor : ListCollectionView) : void {
			this._documentosVigentes = valor;
		}
		public function get documentosVigentes() : ListCollectionView {
			return this._documentosVigentes;
		} 
		
		public function set exbirJustificativa(valor : Boolean) : void {
			this._exbirJustificativa = valor;
		}
		public function get exbirJustificativa() : Boolean {
			return this._exbirJustificativa;
		}
		
		public override function set objeto(valor:Object):void {
			
			super.objeto = valor;
			var vo:AutorizacaoVO = AutorizacaoVO(valor)
			var index:int = 1;
			this.documentosNovos = new ArrayCollection();
			for each (var doc:AutorizacaoDocumentoVO in vo.documentos){
				var documento:DocumentoComprobatorioVO = doc.documento;
				documento.nome = "Documento " + index++;
				this.documentosNovos.addItem(documento);
			}
		}
		
		private function converterOcorrenciaAtividadeVO(objeto:Object):OcorrenciaAtividadeVO{
			if(objeto != null){
				var vo:OcorrenciaAtividadeVO = new OcorrenciaAtividadeVO();
				vo.data = objeto.data;
				vo.idOcorrenciaAtividade = objeto.idOcorrenciaAtividade;
				vo.idOcorrenciaProcesso = objeto.idOcorrenciaProcesso;
				vo.idUsuario = objeto.idUsuario;
				vo.justificativa = objeto.justificativa;
				vo.nomeProcedimento = objeto.nomeProcedimento;
				return vo;
			}
			return null;
		}
	}
}