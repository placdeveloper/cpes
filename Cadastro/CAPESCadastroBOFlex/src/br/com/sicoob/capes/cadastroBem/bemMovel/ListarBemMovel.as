package br.com.sicoob.capes.cadastroBem.bemMovel {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ListCollectionView;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.botoes.BarraBotoes;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.cadastroBem.ITelaCrudBem;
	import br.com.sicoob.capes.cadastroBem.util.BensUtils;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	
	/**
	 * Classe de listagem dos bens móveis.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class ListarBemMovel extends ListarBemMovelView implements ITelaCrudBem {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.bem.BemMovelFachada";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const OPERACAO_OBTER_POR_CODIGO:String = "obterPorCodigo";
		private static const MENSAGEM_OBTENDO_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_REALIZANDO_OPERACAO:String = "Realizando a operação...";
		
		private var servicoPesquisaCodigo:ServicoJava;
		
		//private var botaoSelecionar:Botao;
		
		/**
		 * Construtor.
		 */
		public function ListarBemMovel(){
			super();
			servicoRecuperacaoInformacoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTENDO_DEFINICOES, ResultEvent.RESULT, retornoObterDefinicoes);
			servicoExclusao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_REALIZANDO_OPERACAO, ResultEvent.RESULT);
			servicoPesquisaCodigo = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_REALIZANDO_OPERACAO, ResultEvent.RESULT, retornoPesquisaPorCodigo);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent):void {
			barraBotoesListaCadastro.exibirBotaoAjuda = false;
			barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			PainelListaBanco(painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			PainelListaBanco(painelLista).funcaoConfiguracaoDto = configurarDtoConsulta;
			
			painelFiltro.botaoPesquisar.addEventListener(MouseEvent.CLICK, pesquisar);
			formularioCadastro.addEventListener(BensUtils.EVENTO_REGISTRO_INCLUIDO, objetoIncluido);
			//painelLista.tabelaPaginada.grdDados.addEventListener(ListEvent.ITEM_CLICK, habilitarBotaoSelecionar);
			//painelLista.tabelaPaginada.grdDados.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, itemSelecionado);
			
			painelFiltro.botaoLimpar.addEventListener(MouseEvent.CLICK, limparFiltro);
			
			/*botaoSelecionar = new Botao();
			botaoSelecionar.label = "Selecionar";
			botaoSelecionar.enabled = false;
			botaoSelecionar.addEventListener(MouseEvent.CLICK, botaoSelecionarClicado);
			botaoSelecionar.width = 100;
			botaoSelecionar.height = 22;
			var box:HBox = (barraBotoesListaCadastro.getChildAt(0) as HBox);
			box.addChildAt(botaoSelecionar, 0);*/
			
			obterDefinicoes();
		}
		
		/**
		 * Obtém as definições da tela.
		 */
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTENDO_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			servicoRecuperacaoInformacoes.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		/**
		 * Retorno do método obter definições.
		 */
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			painelFiltro.comboTipoBem.dataProvider = evento.result.dados.listaTiposBem;
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Cria o DTO para consulta com os filtros.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {
			var filtro:BemMovelVO = new BemMovelVO();
			
			if(this.painelFiltro.comboTipoBem.selectedItem != null) {
				filtro.tipoBem = painelFiltro.comboTipoBem.selectedItem as TipoBemMovelVO;
			}
			
			if(this.painelFiltro.campoDescricao.text != "") {
				filtro.descricao = painelFiltro.campoDescricao.text;
			}
			
			if(this.painelFiltro.campoNumeroChassi.text != "") {
				filtro.numeroChassi = painelFiltro.campoNumeroChassi.text;
			}
			
			if(this.painelFiltro.campoRenavam.text != "") {
				filtro.renavam = painelFiltro.campoRenavam.text;
			}
			
			if(this.painelFiltro.campoNumeroEmbarcacao.text != "") {
				filtro.inscricaoEmbarcacao = painelFiltro.campoNumeroEmbarcacao.text;
			}
			
			if(this.painelFiltro.campoMatriculaAeronave.text != "") {
				filtro.matriculaAeronave = painelFiltro.campoMatriculaAeronave.text;
			}
			
			dto.filtro = filtro;
		}
		
		/**
		 * Cria o DTO com o identificador do registro para exclusão.
		 */
		protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
			var vo:BemMovelVO = new BemMovelVO();
			vo.idBem = item.idBem;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemMovel = vo;
			return dto;
		}
		
		/**
		 * Cria o DTO para obter as informações para alteração.
		 */
		protected override function montarDtoRecuperacao(item:Object):RequisicaoDTO {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemMovel = item as BemMovelVO;
			return dto;
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this.destino = destino;
			servicoPesquisaCodigo.configurarDestino(destino);
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
		}
		
		/**
		 * Obtém o painel dos filtros para consulta.
		 */
		private function get painelFiltro():PainelFiltroBemMovelView {
			return this.painelLista.painelFiltro as PainelFiltroBemMovelView;
		}
		
		/**
		 * Instancia o DTO para consulta. 
		 */
		public function instanciarDTOConsulta():ConsultaDto {
			return new ConsultaDto();
		}
		
		/**
		 * Método sobreescrito com o tamanho da janela principal, pois estamos usando um componente de telas dentro de uma 
		 * janela e ao navegar entre as classes, o componente se perdia com os valores de tamanhos.
		 */
		protected override function exibirComponente(componente:UIComponent, barraBotoes:BarraBotoes, widht:int, height:int):void {
			//TODO: pegar o tamanho da janela. pegaJanela();
			super.exibirComponente(componente, barraBotoes, 780, 600);
		}
		
		/**
		 * @inheritDoc
		 */
		public function abrirModoInclusao():void {
			if(initialized) {
				_novo = true;
				exibirJanelaInclusao();
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function abrirModoEdicao(objeto:BemVO):void {
			if(initialized) {
				exibirJanelaAlteracao(objeto);
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function pesquisar(evento:Event = null):void {
			if(initialized){
				if(validarCamposPesquisa()) {
					PainelListaBanco(this.painelLista).pesquisar(1);
				}
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function pesquisarPorCodigo(codigo:Number):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var vo:BemMovelVO = new BemMovelVO();
			vo.idBem = codigo;
			dto.dados.bemMovel = vo;
			servicoPesquisaCodigo.getOperation(OPERACAO_OBTER_POR_CODIGO).send(dto);
		}
		
		/**
		 * Realiza a pesquisa do componente de bens utilizando o código informado.
		 */
		private function retornoPesquisaPorCodigo(evento:ResultEvent):void {
			var lista:ListCollectionView = evento.result.dados.lista;
			
			var objeto:Object = null;
			if(lista != null && lista.length > 0){
				objeto = lista[0];
			}
			dispatchEvent(new ObjetoEvent(BensUtils.EVENTO_REGISTRO_INCLUIDO, objeto));
		}
		
		/**
		 * Ação do botão selecionar.
		 */
		/*private function botaoSelecionarClicado(evento:MouseEvent = null):void {
			var objeto:BemMovelVO = recuperarItemSelecionado() as BemMovelVO;
			
			if(objeto != null){
				dispatchEvent(new ObjetoEvent(PesquisarBem.EVENTO_OBJETO_INCLUIDO, objeto));
				pegaJanela().fecharJanela();
			}
		}*/
		
		/**
		 * Informa o componente que o registro foi incluído/alterado.
		 */
		private function objetoIncluido(evento:ObjetoEvent):void {
			var objeto:BemMovelVO = evento.objeto as BemMovelVO;
			pegaJanela().fecharJanela();
		}
		
		/**
		 * Habilita o botão 'SELECIONAR' caso um registro tenha sido selecionado.
		 */
		/*private function habilitarBotaoSelecionar(evento:ListEvent):void {
			botaoSelecionar.enabled = painelLista.tabelaPaginada.grdDados.selectedItem != null;
		}*/
		
		/**
		 * Listener da grid para a seleção de um objeto utilizando o 'double-click'
		 */
		/*private function itemSelecionado(event:ListEvent):void {
			botaoSelecionarClicado();
		}*/
		
		/**
		 * Faz a limpeza dos campos de filtro.
		 */
		private function limparFiltro(evento:MouseEvent = null):void {
			limpar();
		}
		
		/**
		 * Realiza a limpeza dos campos.
		 */
		public function limpar():void {
			if(initialized) {
				_novo = false;
				FlexUtil.selecionarItemCombo(painelFiltro.comboTipoBem, null);
				painelFiltro.campoDescricao.text = "";
				painelFiltro.campoNumeroChassi.text = "";
				painelFiltro.campoRenavam.text = "";
				painelFiltro.campoNumeroEmbarcacao.text = "";
				painelFiltro.campoMatriculaAeronave.text = "";
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function verificarFechamentoTelaEdicao():void {
			if(initialized && !_novo) {
				fecharFormularioCadastro();
			}
		}
		
		/**
		 * Verifica se a consulta pode ser realizada
		 */
		private function validarCamposPesquisa():Boolean {
			if(painelFiltro.comboTipoBem.selectedItem == null
				|| (painelFiltro.campoDescricao.text == ""
				&& painelFiltro.campoNumeroChassi.text == ""
				&& painelFiltro.campoRenavam.text == ""
				&& painelFiltro.campoNumeroEmbarcacao.text == ""
				&& painelFiltro.campoMatriculaAeronave.text == "")){
					Alerta.show("É obrigatório o preenchimento do campo \"tipo bem\" e mais um campo dos demais.", "Erro", Alerta.ALERTA_ERRO);
					return false;
				}
			return true;
		}
		
	}
}