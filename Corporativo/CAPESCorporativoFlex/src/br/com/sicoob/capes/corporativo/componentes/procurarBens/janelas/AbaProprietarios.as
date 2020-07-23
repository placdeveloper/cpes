package br.com.sicoob.capes.corporativo.componentes.procurarBens.janelas{
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas.DadosBasicosImovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemProprietarioVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de associações do bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class AbaProprietarios extends AbaProprietariosView implements IAba {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarBemFachada";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterProprietarios";
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo proprietários ...";
		
		private var telaAssociarProprietarios:AssociarProprietario = new AssociarProprietario();
		private var destino:DestinoVO;
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			telaAssociarProprietarios.addEventListener(AssociarProprietario.EVENTO_REGISTRO_GRAVADO, proprietarioIncluido);
			botaoAdicionar.addEventListener(MouseEvent.CLICK, abrirJanelaCadastrarProprietarioInclusao);
			botaoEditar.addEventListener(MouseEvent.CLICK, abrirJanelaCadastrarProprietarioEdicao);
			botaoExcluir.addEventListener(MouseEvent.CLICK, excluirProprietario);
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
			if ((gridProprietarios.dataProvider as ArrayCollection).length <= 0) {
				return false;
			}
			return true;
		}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			//bem.proprietarios = ListCollectionView(gridProprietarios.dataProvider);
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = bem.idBem;
			
			var servicoProprietarios:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTER_DEFINICOES, ResultEvent.RESULT, retornoObterProprietarios);
			servicoProprietarios.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
    	}
		
		private function retornoObterProprietarios(evento:ResultEvent):void {
			gridProprietarios.dataProvider = evento.result.dados.proprietarios;
			MostraCursor.removeBusyCursor();
		}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			gridProprietarios.dataProvider = new ArrayCollection();
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
		 * Abre a tela de cadastros de proprietários para inclusão.
		 */
		private function abrirJanelaCadastrarProprietarioInclusao(evento:Event = null):void {
			abrirJanelaCadastrarProprietario();
		}
		
		/**
		 * Abre a tela de cadastros de proprietários para edição.
		 */
		private function abrirJanelaCadastrarProprietarioEdicao(evento:Event = null):void {
			var proprietario:BemProprietarioVO = gridProprietarios.selectedItem as BemProprietarioVO;
			
			if(proprietario == null){
				Alerta.show("Por favor, selecione um proprietário para edição.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			abrirJanelaCadastrarProprietario(proprietario);	
		}
		
		/**
		 * Abre a tela de cadastros de proprietários.
		 */
		private function abrirJanelaCadastrarProprietario(proprietario:BemProprietarioVO = null):void {
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
			var proprietarioBem:BemProprietarioVO = evento.objeto as BemProprietarioVO;
			
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
			var proprietario:BemProprietarioVO = gridProprietarios.selectedItem as BemProprietarioVO;
			
			if(proprietario == null){
				Alerta.show("Por favor, selecione um proprietário para exclusão.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			Alerta.show("Deseja realmente excluir o proprietário?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaExclusaoProprietario);
		}
		
		/**
		 * Confirma a exclusão de um proprietário.
		 */
		private function confirmaExclusaoProprietario(evento:Event):void {
			(gridProprietarios.dataProvider as ArrayCollection).removeItemAt(gridProprietarios.selectedIndex);
			FlexUtil.atualizarComponente(gridProprietarios);
		}
		
		/**
		 * Verifica se o proprietário já existe na lista.
		 */
		private function proprietarioExiste(proprietario:BemProprietarioVO):Boolean {
			for each (var objeto:BemProprietarioVO in gridProprietarios.dataProvider) {
				if(objeto.idPessoa == proprietario.idPessoa) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Faz a exclusão de um proprietário já existente.
		 */
		private function excluirProprietarioExistente(proprietario:BemProprietarioVO):void {
			var listaProprietarios:ArrayCollection = gridProprietarios.dataProvider as ArrayCollection;
			
			if(listaProprietarios != null && listaProprietarios.length > 0) {
				for(var i:uint = 0; i < listaProprietarios.length; i++){
					var objeto:BemProprietarioVO = listaProprietarios.getItemAt(i) as BemProprietarioVO;
					
					if(objeto.idPessoa == proprietario.idPessoa){
						listaProprietarios.removeItemAt(i);
					}
				}
			}
		}
		
		/**
		 * Verifica se a porcentagem a ser adicionada excede a porcentagem máxima(100%). 
		 */
		private function excedePorcentagemMaxima(porcentagem:Number):Boolean {
			var total:Number = 0;
			for each (var objeto:BemProprietarioVO in gridProprietarios.dataProvider) {
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
			for each (var objeto:BemProprietarioVO in gridProprietarios.dataProvider) {
				total += objeto.percentualProprietario;
			}
			
			if(total == 100) {
				return true;
			}
			
			return retorno;
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():IAba {
			return this.parent.getChildByName("abaDadosBasicos") as IAba;
		}
		
		/**
		 * Faz a conversão da lista de proprietários selecionados para o 'BemProprietarioVO'.
		 */
		/*private function obterProprietariosBem(bem:BemVO):ListCollectionView {
			var lista:ArrayCollection = new ArrayCollection();
			
			for each(var objeto:BemProprietarioVO in gridProprietarios.dataProvider) {
				var bemPessoaCompartilhamento:BemProprietarioVO = new BemProprietarioVO();
				bemPessoaCompartilhamento.idBemPessoaCompartilhamento = objeto.idBemPessoaCompartilhamento;
				bemPessoaCompartilhamento.idBem = bem.id;
				bemPessoaCompartilhamento.bem = bem;
				bemPessoaCompartilhamento.marcadoExclusao = new Booleano(objeto.marcadoExclusao);
				
				var pessoaCompartilhamento:PessoaCompartilhamentoVO;
				if(objeto.pessoaCompartilhamento != null) {
					pessoaCompartilhamento = objeto.pessoaCompartilhamento;
				}else {
					if(TipoPessoaEnum.PESSOA_FISICA.codigo == objeto.codigoTipoPessoa) {
						pessoaCompartilhamento = new PessoaFisicaVO();
					} else {
						pessoaCompartilhamento = new PessoaJuridicaVO();
					}
					pessoaCompartilhamento.idPessoaCompartilhamento = objeto.idPessoaCompartilhamento;
				}
				
				bemPessoaCompartilhamento.pessoaCompartilhamento = pessoaCompartilhamento;
				bemPessoaCompartilhamento.idPessoaCompartilhamento = pessoaCompartilhamento.idPessoaCompartilhamento;
				bemPessoaCompartilhamento.percentualProprietario = objeto.porcentagem;
				
				if(objeto.idPessoaCompartilhamento == pessoaSelecionada.idCompartilhamento) {
					bemPessoaCompartilhamento.pessoaResponsavel = new Booleano(true);
				}
				
				lista.addItem(bemPessoaCompartilhamento);
			}
			
			return ListCollectionView(lista);
		}*/
	}
}