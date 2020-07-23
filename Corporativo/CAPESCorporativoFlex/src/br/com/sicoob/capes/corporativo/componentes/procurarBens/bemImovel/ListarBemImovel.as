package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
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
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoUsoBemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.ITelaCrudBem;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.PesquisarBem;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.containers.HBox;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Tela de listagem do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class ListarBemImovel extends ListarBemImovelView implements ITelaCrudBem {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarBemImovelFachada";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const OPERACAO_OBTER_TIPOS_USO_BEM:String = "obterTiposUsoBem";
		private static const OPERACAO_OBTER_POR_CODIGO:String = "obterPorCodigo";
		private static const MENSAGEM_OBTENDO_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_REALIZANDO_OPERACAO:String = "Realizando a operação...";
		private static const MENSAGEM_OBTENDO_TIPOS_USO_BEM:String = "Obtendo os tipos de uso do bem...";
		
		private var servico:ServicoJava;
		private var _codigo:Number;
		
		private var botaoSelecionar:Botao;
		
		/**
		 * Construtor.
		 */
		public function ListarBemImovel(){
			super();
			servicoRecuperacaoInformacoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTENDO_DEFINICOES, ResultEvent.RESULT, retornoObterDefinicoes);
			servicoExclusao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_REALIZANDO_OPERACAO, ResultEvent.RESULT);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent):void {
			if(initialized) {
				barraBotoesListaCadastro.exibirBotaoAjuda = false;
				barraBotoesListaCadastro.exibirBotaoExcluir = false;
				
				PainelListaBanco(painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
				PainelListaBanco(painelLista).funcaoConfiguracaoDto = configurarDtoConsulta;
				
				painelFiltro.botaoPesquisar.addEventListener(MouseEvent.CLICK, pesquisar);
				painelFiltro.comboTipoLocalizacao.addEventListener(ListEvent.CHANGE, obterPorTipoLocalizacao);
				painelFiltro.comboTipoUso.addEventListener(ListEvent.CHANGE, obterTipoBem);
				
				//formularioCadastro.addEventListener(BensUtils.EVENTO_REGISTRO_INCLUIDO, objetoIncluido);
				painelLista.tabelaPaginada.grdDados.addEventListener(ListEvent.ITEM_CLICK, habilitarBotaoSelecionar);
				painelLista.tabelaPaginada.grdDados.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, itemSelecionado);
				
				painelFiltro.botaoLimpar.addEventListener(MouseEvent.CLICK, limparFiltro);
				
				botaoSelecionar = new Botao();
				botaoSelecionar.label = "Selecionar";
				botaoSelecionar.enabled = false;
				botaoSelecionar.addEventListener(MouseEvent.CLICK, botaoSelecionarClicado);
				botaoSelecionar.width = 100;
				botaoSelecionar.height = 22;
				var box:HBox = (barraBotoesListaCadastro.getChildAt(0) as HBox);
				box.addChildAt(botaoSelecionar, 0);
				
				obterDefinicoes();
			}
		}
		
		/**
		 * Obtém as definições da tela.
		 */
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTENDO_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			servicoRecuperacaoInformacoes.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		/**
		 * Retorno do método de obter definições.
		 */
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			painelFiltro.comboTipoLocalizacao.dataProvider = evento.result.dados.listaTiposLocalizacao;
			painelFiltro.comboTipoUso.dataProvider = evento.result.dados.listaTiposUso;
			painelFiltro.comboTipoBem.dataProvider = evento.result.dados.listaTiposBem;
			
			//this.painelFiltro.componentePesquisaMunicipio.configurarDestino(this.destino);
			this.painelFiltro.componenteProcurarPessoa.configurarDestino(this.destino);
			
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Carrega as combos de filtro dependentes do tipo de localização.
		 */
		private function obterPorTipoLocalizacao(evento:Event):void {
			var tipoLocalizacao:TipoLocalizacaoBemVO = painelFiltro.comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			FlexUtil.atualizarCombo(painelFiltro.comboTipoUso, null, true);
			FlexUtil.atualizarCombo(painelFiltro.comboTipoBem, null, true);
			
			if(tipoLocalizacao != null){
				MostraCursor.setBusyCursor(MENSAGEM_OBTENDO_TIPOS_USO_BEM, Application.application, MostraCursor.CURSOR_PROGRESSO);
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoLocalizacaoBem = tipoLocalizacao;
				
				servico.addEventListener(ResultEvent.RESULT, retornoObterTipoUsoBem);
				servico.getOperation(OPERACAO_OBTER_TIPOS_USO_BEM).send(dto);
			}
		}
		
		/**
		 * Retorno do método que carrega a combo do tipo de uso do bem.
		 */
		private function retornoObterTipoUsoBem(evento:ResultEvent):void {
			FlexUtil.atualizarCombo(painelFiltro.comboTipoUso, evento.result.dados.listaTiposUsoBem, true);
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Obtém os tipos de bem.
		 */
		private function obterTipoBem(evento:Event):void {
			var tipoUsobemImovel:TipoUsoBemImovelVO = painelFiltro.comboTipoUso.selectedItem as TipoUsoBemImovelVO;
			if(tipoUsobemImovel != null){
				FlexUtil.atualizarCombo(painelFiltro.comboTipoBem, tipoUsobemImovel.tiposBemImovel, true);
			} else {
				FlexUtil.atualizarCombo(painelFiltro.comboTipoBem, null, true);
			}
		}
		
		/**
		 * Configura o DTO a ser utilizado como filtro na consulta dos registros.
		 */
		private function configurarDtoConsulta(dto:ConsultaDto):void {
			var filtro:BemImovelVO = new BemImovelVO();
			
			if(painelFiltro.comboTipoLocalizacao.selectedItem != null){
				filtro.codigoTipoLocalizacaoBem = painelFiltro.comboTipoLocalizacao.selectedItem.codigo;
			}
			
			if(painelFiltro.comboTipoUso.selectedItem != null){
				filtro.codigoTipoUsoBem = painelFiltro.comboTipoUso.selectedItem.codigo;
			}
			
			if(painelFiltro.comboTipoBem.selectedItem != null){
				filtro.codigoTipoBem = painelFiltro.comboTipoBem.selectedItem.codigo;
			}
			
			if(painelFiltro.campoDescricao.text != ""){
				filtro.denominacao = painelFiltro.campoDescricao.text;
			}
			
			if(painelFiltro.componenteProcurarPessoa.registro != null) {
				var pessoa:PessoaPlataformaVO = painelFiltro.componenteProcurarPessoa.registro as PessoaPlataformaVO;
				filtro.idPessoaCartorio = pessoa.idPessoa;
			}
			
			if(painelFiltro.campoMatricula.text != ""){
				filtro.matricula = painelFiltro.campoMatricula.text;
			}
			
			if(painelFiltro.campoNirf.text != ""){
				filtro.nirf = painelFiltro.campoNirf.text;
			}
			
			if(painelFiltro.campoIncra.text != ""){
				filtro.incra = painelFiltro.campoIncra.text;
			}
			
			dto.filtro = filtro;
		}
		
		/**
		 * Monta o DTO com o identificador do registro à ser excluído. 
		 */
		override protected function montarDtoExclusao(item:Object):RequisicaoDTO {
			var vo:BemImovelVO = new BemImovelVO();
			vo.idBem = item.idBem;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemImovel = vo;
			return dto;
		}
		
		/**
		 * Monta o DTO para ser usado para alteração.
		 */
		override protected function montarDtoRecuperacao(item:Object):RequisicaoDTO {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemImovel = item as BemImovelVO;
			return dto;
		}
		
		/**
		 * Obtém o painel de filtros.
		 */
		private function get painelFiltro():PainelFiltroBemImovelView {
			return this.painelLista.painelFiltro as PainelFiltroBemImovelView;
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
		protected override function exibirComponente(componente:UIComponent, barraBotoes:BarraBotoes, width:int, height:int):void {
			//TODO: pegar o tamanho da janela. pegaJanela();
			super.exibirComponente(componente, barraBotoes, 780, 600);
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this.destino = destino;
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			servico.configurarDestino(destino);
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
		 * Ação do botão 'SELECIONAR', que foi adicionado à tela.
		 */
		private function botaoSelecionarClicado(evento:MouseEvent = null):void {
			var objeto:BemImovelVO = recuperarItemSelecionado() as BemImovelVO;
			
			if(objeto != null){
				dispatchEvent(new ObjetoEvent(PesquisarBem.EVENTO_OBJETO_INCLUIDO, objeto));
				pegaJanela().fecharJanela();
			}
		}
		
		/**
		 * Obtém o registro que deveria ser incluído e informa o componente.
		 */
		private function objetoIncluido(evento:ObjetoEvent):void {
			//var objeto:BemImovelVO = evento.objeto as BemImovelVO;
			pegaJanela().fecharJanela();
		}
		
		/**
		 * Habilita o botão selecionar quando um registro for selecionado.
		 */
		private function habilitarBotaoSelecionar(evento:ListEvent):void {
			botaoSelecionar.enabled = this.painelLista.tabelaPaginada.grdDados.selectedItem != null;
		}
		
		/**
		 * Método chamado ao selecionar o registro diretamente na grid. (double-click).
		 */
		private function itemSelecionado(event:ListEvent):void {
			botaoSelecionarClicado();
		}
		
		/**
		 * Realiza a limpeza dos filtros que foram utilizados na consulta.
		 */
		private function limparFiltro(evento:MouseEvent = null):void {
			limpar();
		}
		
		/**
		 * @inheritDoc
		 */
		public function limpar():void {
			if(initialized) {
				_novo = false;
				FlexUtil.selecionarItemCombo(painelFiltro.comboTipoLocalizacao, null);
				FlexUtil.selecionarItemCombo(painelFiltro.comboTipoUso, null);
				FlexUtil.selecionarItemCombo(painelFiltro.comboTipoBem, null);
				painelFiltro.campoDescricao.text = "";
				painelFiltro.componenteProcurarPessoa.limpar();
				painelFiltro.campoMatricula.text = "";
				painelFiltro.campoNirf.text = "";
				painelFiltro.campoIncra.text = "";
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
			if(painelFiltro.comboTipoLocalizacao.selectedItem == null
				|| (painelFiltro.comboTipoUso.selectedItem == null
				&& painelFiltro.comboTipoBem.selectedItem == null
				&& painelFiltro.campoDescricao.text == ""
				&& painelFiltro.componenteProcurarPessoa.registro == null
				&& painelFiltro.campoMatricula.text == ""
				&& painelFiltro.campoNirf.text == ""
				&& painelFiltro.campoIncra.text == "")) {
					Alerta.show("São necessários pelo menos dois filtros para efetuar a pesquisa.", "Erro", Alerta.ALERTA_ERRO);
					return false;
				}
			return true;
		}
	}
}