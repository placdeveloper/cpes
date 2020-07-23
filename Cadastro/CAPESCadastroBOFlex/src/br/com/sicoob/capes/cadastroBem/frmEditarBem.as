package br.com.sicoob.capes.cadastroBem {
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.DadosListagemBemVO;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemPessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoClassificacaoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Formulário para edição dos bens.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class frmEditarBem extends frmEditarBemView implements IEdicaoPlataformaAtendimentoCAPES {

		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.bem.BemPessoaCompartilhamentoFachada";
		
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_GRAVANDO_DADOS:String = "Gravando dados...";
		private static const MENSAGEM_EXCLUIR_DADOS:String = "Excluindo Registro...";
		private static const MENSAGEM_OBTER_DADOS:String = "Obtendo registro...";
		private static const MENSAGEM_OBTER_INFORMACOES_BEM:String = "Obtendo as informações do bem...";
		
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const OPERACAO_INCLUIR_DADOS:String = "incluirDados";
		private static const OPERACAO_ALTERAR_DADOS:String = "alterarDados";
		private static const OPERACAO_EXCLUIR_DADOS:String = "excluirDados";
		private static const OPERACAO_OBTER_DADOS:String = "obterDados";
		private static const OPERACAO_OBTER_INFORMACOES_BEM:String = "obterInformacoesBem";
		
		private static const ABA_DOCUMENTOS:Number = 1;
		
		private var servicoSalvar:ServicoJava;
		private var servicoExcluir:ServicoJava;
		private var servicoDefinicao:ServicoJava;
		private var servicoObterProprietarios:ServicoJava;
		
		private var _isRegistroBloqueado:Boolean = false;
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		[Bindable]
		private var bemPessoaCompartilhamento:DadosListagemBemVO = new DadosListagemBemVO();
		
		/**
		 * Construtor.
		 */
		public function frmEditarBem(){
			super();
			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTER_DEFINICOES, ResultEvent.RESULT, retornoObterDefinicoes);
			servicoSalvar = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_GRAVANDO_DADOS, ResultEvent.RESULT, retornoSalvar);
			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_EXCLUIR_DADOS, ResultEvent.RESULT, retornoExcluir);
			servicoObterProprietarios = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTER_INFORMACOES_BEM, ResultEvent.RESULT, retornoObterInformacoesBem);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			//componenteBem.configurarDestino(this.destino);
			
			comboClassificacao.addEventListener(ListEvent.CHANGE, habilitarComponentes);
			//componenteBem.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, obterInformacoesBem);
			
			carregarDefinicoes();
		}
		
		/**
		 * Realiza a configuração dos destinos dos serviços.
		 */
		public function configurarDestinosServicos(destino:DestinoVO):void{
			this.destino = destino;
			servicoDefinicao.configurarDestino(destino);
			//servicoConsulta.configurarDestino(destino);
			servicoSalvar.configurarDestino(destino);
			servicoExcluir.configurarDestino(destino);
			servicoObterProprietarios.configurarDestino(destino);
		}
		
		/**
		 * Carrega o registro para edição.
		 */
		public function carregarRegistro(objeto:Object):void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DADOS, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dadosListagem:DadosListagemBemVO = objeto as DadosListagemBemVO;
			var tipoClassificacao:TipoClassificacaoBemVO = new TipoClassificacaoBemVO();
			tipoClassificacao.codigo = dadosListagem.codigoTipoClassificacaoBem;
			
			FlexUtil.selecionarItemCombo(comboClassificacao, tipoClassificacao);
			
			var tipoClassificacaoEnum:TipoClassificacaoBemEnum = TipoClassificacaoBemEnum.obterPorCodigo(dadosListagem.codigoTipoClassificacaoBem);
			//componenteBem.tipoClassificacaoBem = tipoClassificacaoEnum;
			//componenteBem.carregarRegistro(dadosListagem.idBem);
			
			_novo = false;
			bemPessoaCompartilhamento = dadosListagem;
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}
		
		/**
		 * Obtém as definições da tela.
		 */
		public function carregarDefinicoes(objeto:Object = null):void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		/**
		 * Habilita os componentes à partir do tipo de classificação do bem selecionado.
		 */
		private function habilitarComponentes(evento:Event):void {
			var tipoClassificacao:TipoClassificacaoBemVO = comboClassificacao.selectedItem as TipoClassificacaoBemVO;
			
			if(tipoClassificacao != null){
				var tipoClassificacaoEnum:TipoClassificacaoBemEnum = TipoClassificacaoBemEnum.obterPorCodigo(tipoClassificacao.codigo);
				
				//componenteBem.limpar();
				if(tipoClassificacaoEnum != null){
					//componenteBem.tipoClassificacaoBem = tipoClassificacaoEnum;
					rotuloTitulo.texto = "Associar " + tipoClassificacaoEnum.descricao;
					rotuloBem.text = tipoClassificacaoEnum.descricao + ": ";
					
					canvasBem.visible = canvasBem.includeInLayout = true;
				}
			} else {
				canvasBem.visible = canvasBem.includeInLayout = false;
			}
		}
		
		/**
		 * Faz a limpeza dos campos.
		 */
		private function limpar():void {
			comboClassificacao.selectedIndex = 0;
			canvasBem.visible = canvasBem.includeInLayout = false;
			//componenteBem.limpar();
		}
		
		/**
		 * Retorno do método de obter definições.
		 */
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			comboClassificacao.dataProvider = evento.result.dados.listaTipoClassificacaoBem;
		}
		
		/**
		 * Preenche os campos para alteração.
		 */
		public function preencherCampos():void {
			if(!_novo) {
				comboClassificacao.enabled = false;
				//componenteBem.configurarModoEdicao();
			}
		}
		
		/**
		 * Prepara a tela para a inclusão de um novo registro.
		 */
		public function novoRegistro():void {
			bemPessoaCompartilhamento = new DadosListagemBemVO();
			_novo = true;

			limpar();
		}
		
		/**
		 * Método chamado ao clicar no botão gravar.
		 * Faz as validações e salva o registro.
		 */
		public function gravarRegistro():void {
			if(!validarCampos()) {
				return;
			}
			onConfirmaGravarRegistro();
		}
		
		/**
		 * Faz a validação dos campos.
		 */
		private function validarCampos():Boolean {
			/*var bem:BemVO = componenteBem.obterRegistro();
			
			if(bem == null) {
				Alerta.show("Por favor, selecione um bem para continuar.", "Erro", Alerta.ALERTA_ERRO);
				return false;
			}*/
			
			/*if(!propriedadeCompleta()) {
				Alerta.show("O percentual de propriedade do bem deve ser igual a cem por cento (100%).", "Erro", Alerta.ALERTA_ERRO);
				return false;
			}*/
			
			return true;
		}
		
		/**
		 * Atualiza os campos do formulário e grava a solicitação.
		 **/
		private function onConfirmaGravarRegistro(evento:Event = null):void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}

		/**
		 * Método que seria utilizado para preencher os campos do registro.
		 * 
		 * Está sendo feito no método 'gravarDados'.
		 */
		public function atualizarCamposRegistro():void {
			
		}
		
		private function gravarDados():void {
			MostraCursor.setBusyCursor(MENSAGEM_GRAVANDO_DADOS, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			/*var bem:BemVO = componenteBem.obterRegistro();
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bem = bem;
			
			if (isNaN(bem.id)) {
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			} else {
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}*/
		}
		
		/**
		 * Faz a exclusão de um compartilhamento, pela tela do módulo principal.
		 */
		public function excluirRegistro(objeto:Object):void {
			MostraCursor.setBusyCursor(MENSAGEM_EXCLUIR_DADOS, Application.application, MostraCursor.CURSOR_EXCLUIR);
			
			var registro:DadosListagemBemVO = objeto as DadosListagemBemVO;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = registro.idBem;
			dto.dados.bemInterno = registro.bemInterno;
			dto.dados.idBemPessoaCompartilhamento = registro.idBemPessoaCompartilhamento;
			
			dto.dados.bemPessoaCompartilhamento = objeto as BemPessoaCompartilhamentoVO;
			
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_DADOS).send(dto);
		}
		
		/**
		 * Método chamado pelo botão 'cancelar', que remove as alterações do registro.
		 */
		public function restaurarRegistro():void{
			if(_novo) {
				limpar();
			} else {
				//componenteBem.carregarRegistro(bemPessoaCompartilhamento.idBem);
			}
		}
		
		/**
		 * Retorno do método salvar.
		 */
		private function retornoSalvar(evento:ResultEvent):void {
			bemPessoaCompartilhamento = null;
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}
		
		/**
		 * Retorno do método excluir.
		 */
		private function retornoExcluir(evento:ResultEvent):void {
			bemPessoaCompartilhamento = null;
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));
		}

		/**
		 * Verifica se houve alguma alteração na tela para a execução do 
		 * método gravar e exibição da mensagem ao sair da tela sem salvar.
		 */
		public function verificarAlteracoes():Boolean {
			return true;
		}
		
		/**
		 * Verifica se o registro está bloqueado para alteração. (passando pelo fluxo de aprovação).
		 */
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado;
		}

		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		/**
		 * Obtém as informações do bem selecionado no componente.
		 */
		private function obterInformacoesBem(evento:SelecaoEvent):void {
			var bem:BemVO = evento.objeto as BemVO;
			
			if(bem != null) {
				MostraCursor.setBusyCursor(MENSAGEM_OBTER_INFORMACOES_BEM, Application.application, MostraCursor.CURSOR_PROGRESSO);
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.bem = bem;
				servicoObterProprietarios.getOperation(OPERACAO_OBTER_INFORMACOES_BEM).send(dto);
			}
		}
		
		/**
		 * Retorno do método que obtém informações do Bem.
		 */
		private function retornoObterInformacoesBem(evento:ResultEvent):void {
			var lista:ArrayCollection = evento.result.dados.lista;

			//Verifica se o registro está bloqueado para alteração.
			_isRegistroBloqueado = evento.result.dados.isRegistroBloqueadoAlteracao;
			
			MostraCursor.removeBusyCursor();
		}
		
	}
}