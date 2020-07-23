package br.com.sicoob.capes.cadastroBem{
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.cadastroBem.bemImovel.abas.DadosBasicosImovel;
	import br.com.sicoob.capes.cadastroBem.janelas.AssociarProprietario;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemPessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	/**
	 * Aba de associações do bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class AbaProprietarios extends AbaProprietariosView implements IAba {
		
		private var telaAssociarProprietarios:AssociarProprietario = new AssociarProprietario();
		private var pessoaSelecionada:PessoaPlataformaVO;
		private var destino:DestinoVO;
		private var pessoaSelecionadaExcluida:Boolean = false;
		
		/*[Bindable]
		private var lista:ArrayCollection = new ArrayCollection();*/
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			telaAssociarProprietarios.addEventListener(AssociarProprietario.EVENTO_REGISTRO_GRAVADO, proprietarioIncluido);
			botaoAdicionarPessoaAtual.addEventListener(MouseEvent.CLICK, abrirJanelaCadastrarProprietarioInclusaoPessoaAtual);
			botaoAdicionar.addEventListener(MouseEvent.CLICK, abrirJanelaCadastrarProprietarioInclusao);
			botaoEditar.addEventListener(MouseEvent.CLICK, abrirJanelaCadastrarProprietarioEdicao);
			botaoExcluir.addEventListener(MouseEvent.CLICK, excluirProprietario);
			
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			
			/*lista.filterFunction = exibirNaGrid;
			lista.refresh();*/
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			
    	}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			if(!pessoaSelecionadaExcluida) {
				if(!isPessoaSelecionadaComPatrimonio()) {
					Alerta.show("A pessoa selecionada deve fazer parte dos proprietários do bem.", "Erro", Alerta.ALERTA_ERRO);
					return false;
				}
			}
			return true;
		}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			var lista:ArrayCollection = new ArrayCollection();
			
			for each(var objeto:BemPessoaCompartilhamentoVO in gridProprietarios.dataProvider) {
				objeto.bem = bem;
				objeto.idBem = bem.id;
				
				if(objeto.idPessoaCompartilhamento == pessoaSelecionada.idCompartilhamento) {
					objeto.pessoaResponsavel = true;
				}
				
				lista.addItem(objeto);
			}
			
			bem.proprietarios = ListCollectionView(lista);
    	}
		
		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			if(bem != null && bem.proprietarios != null && bem.proprietarios.length > 0) {
				gridProprietarios.dataProvider = bem.proprietarios;
			}
			verificarInclusaoProprietario();
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			gridProprietarios.dataProvider = new ArrayCollection();
			
			if(initialized && pessoaSelecionada != null) {
				verificarInclusaoProprietario();
			}
    	}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			var abaDadosBasicos:IAba = obterAbaDadosBasicos();
			var visivel:Boolean = false;
			if(abaDadosBasicos is DadosBasicosImovel) {
				visivel = true;
			}
			colunaAreaPosse.visible = visivel;
			
			verificarInclusaoProprietario();
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
			return true;			
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this.destino = destino;
		}
		
		/**
		 * @inheritDoc
		 */
		public function bloquearCampoModoVisualizacao(bloquear:Boolean):void {
			botaoAdicionarPessoaAtual.enabled = !bloquear;
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
		 * Abre a tela de cadastros de proprietários com a pessoa atual selecionada na plataforma.
		 */
		private function abrirJanelaCadastrarProprietarioInclusaoPessoaAtual(evento:Event = null):void {
			var proprietarioBem:BemPessoaCompartilhamentoVO = new BemPessoaCompartilhamentoVO();
			proprietarioBem.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
			proprietarioBem.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaSelecionada);
			
			abrirJanelaCadastrarProprietario(proprietarioBem);
		}
		
		/**
		 * Abre a tela de cadastros de proprietários para inclusão.
		 */
		private function abrirJanelaCadastrarProprietarioInclusao(evento:Event = null):void {
			abrirJanelaCadastrarProprietario();
		}
		
		/**
		 * Abre a tela de cadastros de proprietários para edição.
		 */
		private function abrirJanelaCadastrarProprietarioEdicao(evento:Event = null):void {
			var proprietario:BemPessoaCompartilhamentoVO = gridProprietarios.selectedItem as BemPessoaCompartilhamentoVO;
			if(proprietario == null){
				Alerta.show("Por favor, selecione um proprietário para edição.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			abrirJanelaCadastrarProprietario(proprietario);	
		}
		
		/**
		 * Abre a tela de cadastros de proprietários.
		 */
		private function abrirJanelaCadastrarProprietario(proprietario:BemPessoaCompartilhamentoVO = null):void {
			var janela:Janela = new Janela();
			
			janela.width = 500;
			janela.height = 185;
			janela.title = "ASSOCIAR PROPRIETÁRIOS";
			
			var areaTotal:Number = NaN;
			var abaDadosBasicos:IAba = obterAbaDadosBasicos();
			
			if(abaDadosBasicos != null && abaDadosBasicos is DadosBasicosImovel) {
				var abaDadosBasicosImovel:DadosBasicosImovel = abaDadosBasicos as DadosBasicosImovel;
				areaTotal = abaDadosBasicosImovel.campoAreaTotal.valor;
				janela.height = 245;
			}
			
			janela.addChild(telaAssociarProprietarios);
			janela.abrir(Application.application as DisplayObject, true, true);
			
			telaAssociarProprietarios.limpar();
			telaAssociarProprietarios.configurarDestino(destino);
			telaAssociarProprietarios.adicionarAreaTotal(areaTotal);
			if(proprietario != null){
				telaAssociarProprietarios.carregarRegistro(proprietario);
			}
		}
		
		/**
		 * Verifica a inclusão de um proprietário pela tela de cadastros de proprietários.
		 */
		private function proprietarioIncluido(evento:ObjetoEvent):void {
			var proprietarioBem:BemPessoaCompartilhamentoVO = evento.objeto as BemPessoaCompartilhamentoVO;
			
			if(proprietarioBem != null) {
				if(proprietarioExiste(proprietarioBem)) {
					excluirProprietarioExistente(proprietarioBem);
				}
				
				if(excedePorcentagemMaxima(proprietarioBem.percentualProprietario)) {
					Alerta.show("O valor excede a porcentagem máxima.", "Erro", Alerta.ALERTA_ERRO);
				}
				
				(gridProprietarios.dataProvider as ArrayCollection).addItem(proprietarioBem);
			}
		}
		
		/**
		 * Faz a exclusão de um proprietário da lista.
		 */
		private function excluirProprietario(evento:Event = null):void {
			var proprietario:BemPessoaCompartilhamentoVO = gridProprietarios.selectedItem as BemPessoaCompartilhamentoVO;
			
			if(proprietario == null){
				Alerta.show("Por favor, selecione um proprietário para exclusão.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			/*if(proprietario.idPessoaCompartilhamento == pessoaSelecionada.idCompartilhamento) {
				Alerta.show("O proprietário atual não pode ser excluido.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}*/
			
			
			Alerta.show("Deseja realmente excluir o proprietário?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaExclusaoProprietario);
			
		}
		
		/**
		 * Confirma a exclusão de um proprietário.
		 */
		private function confirmaExclusaoProprietario(evento:Event):void {
			if(!gridProprietarios.selectedItem) {
				return;
			}
			
			var proprietarioBemVO:BemPessoaCompartilhamentoVO = gridProprietarios.selectedItem as BemPessoaCompartilhamentoVO;
			proprietarioBemVO.marcadoExclusao = true;
			proprietarioBemVO.percentualProprietario = 0;
			
			FlexUtil.atualizarComponente(gridProprietarios);
			
			// Verifica se a pessoa selecionada na plataforma está sendo excluída do bem.
			if(proprietarioBemVO.idPessoaCompartilhamento == pessoaSelecionada.idCompartilhamento) {
				pessoaSelecionadaExcluida = true;
			}
		}
		
		/**
		 * Verifica se o proprietário selecionado já existe na lista.
		 */
		private function proprietarioExiste(proprietario:BemPessoaCompartilhamentoVO):Boolean {
			for each (var objeto:BemPessoaCompartilhamentoVO in gridProprietarios.dataProvider) {
				if(objeto.idPessoaCompartilhamento == proprietario.idPessoaCompartilhamento) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Verifica se a pessoa selecionada já existe na lista de proprietários.
		 */
		private function isPessoaSelecionadaComPatrimonio():Boolean {
			var proprietario:BemPessoaCompartilhamentoVO = new BemPessoaCompartilhamentoVO();
			proprietario.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
			return proprietarioExiste(proprietario);
		}
		
		/**
		 * Faz a exclusão de um proprietário já existente.
		 */
		private function excluirProprietarioExistente(proprietario:BemPessoaCompartilhamentoVO):void {
			var listaProprietarios:ArrayCollection = gridProprietarios.dataProvider as ArrayCollection;
			
			if(listaProprietarios != null && listaProprietarios.length > 0) {
				var listaTempAremover:ArrayCollection = new ArrayCollection(); 
				for(var i:uint = 0; i < listaProprietarios.length; i++){
					var objeto:BemPessoaCompartilhamentoVO = listaProprietarios.getItemAt(i) as BemPessoaCompartilhamentoVO;
					if(objeto.idPessoaCompartilhamento == proprietario.idPessoaCompartilhamento){
						listaTempAremover.addItem(objeto);
					}
				}
				for each (var objeto:BemPessoaCompartilhamentoVO in listaTempAremover) {
					listaProprietarios.removeItemAt(listaProprietarios.getItemIndex(objeto));	
				}

			}
		}
		
		/**
		 * Verifica se a porcentagem a ser adicionada excede a porcentagem máxima(100%). 
		 */
		private function excedePorcentagemMaxima(porcentagem:Number):Boolean {
			var total:Number = 0;
			for each (var objeto:BemPessoaCompartilhamentoVO in gridProprietarios.dataProvider) {
				total += objeto.percentualProprietario;
			}
			
			total += porcentagem;
			if(total > 100) {
				return true;
			}
			
			return false;
		}
		
		/**
		 * Verifica se a propriedade está completa, ou seja, possui 100% de propriedade. 
		 */
		private function propriedadeCompleta():Boolean {
			var retorno:Boolean = false;
			
			var total:Number = 0;
			for each (var objeto:BemPessoaCompartilhamentoVO in gridProprietarios.dataProvider) {
				total += objeto.percentualProprietario;
			}
			
			if(pessoaSelecionadaExcluida && total == 0) {
				return true;
			} else if(total == 100) {
				return true;
			}
			
			return retorno;
		}
		
		private function verificarInclusaoProprietario():void {
			var proprietarios:ArrayCollection = gridProprietarios.dataProvider as ArrayCollection;
			if(obterModo() != 3 && (proprietarios == null || proprietarios.length <= 0)) {
				adicionarPessoaCompartilhamento();
			}
		}
		
		/**
		 * Adiciona à pessoa selecionada na plataforma à lista de proprietários.
		 */
		private function adicionarPessoaCompartilhamento():void {
			var proprietarioBem:BemPessoaCompartilhamentoVO = new BemPessoaCompartilhamentoVO();
			proprietarioBem.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
			proprietarioBem.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaSelecionada);
			proprietarioBem.percentualProprietario = 100;
			proprietarioBem.areaPosse = 0;
			
			var lista:ArrayCollection = new ArrayCollection();
			lista.addItem(proprietarioBem);
			
			gridProprietarios.dataProvider = lista;
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():IAba {
			return this.parent.getChildByName("abaDadosBasicos") as IAba;
		}
		
		/**
		 * Obtém o modo atual
		 */
		private function obterModo():int {
			return FlexUtil.obterValorPropriedade(this.parent.parent, "modo");
		}
		
		/*private function exibirNaGrid(item:BemPessoaCompartilhamentoVO):Boolean{
			if(item != null && item.marcadoExclusao) {
				return true;
			}
			return false;
		}*/
	}
}