package br.com.sicoob.capes.cadastroBem.bemImovel{
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelTipoRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoRelacionamentoBemImovelVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Tela de associação de parceiros do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class AssociarParceiro extends AssociarParceiroView  {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.bem.BemImovelFachada";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoesAssociarParceiro";
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		
		public static const EVENTO_REGISTRO_SELECIONADO:String = "EVENTO_REGISTRO_SELECIONADO_ASSOCIAR_PARCEIRO";
		
		private var servico:ServicoJava;
		
		private var _objeto:BemImovelTipoRelacionamentoVO;
		
		/**
		 * Construtor.
		 */
		public function AssociarParceiro() {
			super();
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTER_DEFINICOES, ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(event:FlexEvent):void {
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			this.barraBotoes.btnSalvar.addEventListener(MouseEvent.CLICK, associarParceiro);
			this.barraBotoes.btnCancelar.addEventListener(MouseEvent.CLICK, limpar);
			this.barraBotoes.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			_novo = true;
			
			obterDefinicoes();
		}
		
		/**
		 * Obtém as definições da tela.
		 */
		private function obterDefinicoes():void {
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		/**
		 * Retorno do método de obter definições.
		 */
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			this.comboTipoRelacionamento.dataProvider = evento.result.dados.listaTiposRelacionamento;
			
			if(this._objeto != null) {
				preencherCampos();
			}
		}
		
		/**
		 * Verifica se os campos foram preenchidos e chama o método de gravação.
		 */
		private function associarParceiro(evento:Event = null):void {
			executarSeValido(gravar);
		}
		
		/**
		 * Preenche o objeto e notifica.
		 */
		private function gravar():void {
			if(_novo || _objeto == null){
				_objeto = new BemImovelTipoRelacionamentoVO();
			}
			
			var pessoa:PessoaPlataformaVO = componenteProcurarPessoa.registro as PessoaPlataformaVO;
			_objeto.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa);
			_objeto.idPessoaCompartilhamento = pessoa.idCompartilhamento;
			_objeto.tipoRelacionamento = comboTipoRelacionamento.selectedItem as TipoRelacionamentoBemImovelVO;
			_objeto.dataInicioRelacionamento = campoDataInicio.selectedDate;
			_objeto.dataFimRelacionamento = campoDataFim.selectedDate;
			_objeto.areaPosse = campoAreaPosse.valor;
			
			this.dispatchEvent(new AssociarParceiroEvent(EVENTO_REGISTRO_SELECIONADO, _objeto, !_novo));
			fechar();
		}
		
		/**
		 * limpa os campos.
		 */
		public function limpar(evento:Event = null):void {
			_novo = true;
			_objeto = null;
			componenteProcurarPessoa.limpar();
			comboTipoRelacionamento.selectedIndex = 0;
			campoDataInicio.selectedDate = null;
			campoDataFim.selectedDate = null;
			campoAreaPosse.text = "";
		}
		
		/**
		 * Preenche os campos de acordo com o registro informado.
		 */
		public function preencherCampos():void {
			if(initialized) {
				_novo = false;
				FlexUtil.selecionarItemCombo(comboTipoRelacionamento, _objeto.tipoRelacionamento); 
				campoDataInicio.selectedDate = _objeto.dataInicioRelacionamento;
				campoDataFim.selectedDate = _objeto.dataFimRelacionamento;
				campoAreaPosse.valor = _objeto.areaPosse;
				componenteProcurarPessoa.procurarPorCodigoCompartilhamento(_objeto.idPessoaCompartilhamento);
			}
		}
		
		/**
		 * fecha a janela.
		 */
		private function fechar(evento:Event = null):void {
			fecharJanela();
		}
		
		/**
		 * Realiza a configuração do destino dos serviços.
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this.destino = destino;
			servico.configurarDestino(destino);
			componenteProcurarPessoa.configurarDestino(destino);
		}
		
		/**
		 * Recebe o objeto a ser alterado.
		 */
		public function objetoAlteracao(valor:BemImovelTipoRelacionamentoVO):void {
			this._objeto = valor;
			if(valor != null) {
				_novo = false
			}
		}
		
		public function adicionarAreaTotal(valor:Number):void {
			campoAreaTotal.valor = valor;
		}
	}
}