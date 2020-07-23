package br.com.sicoob.capes.corporativo.componentes.procurarBens.janelas {
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemProprietarioVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	/**
	 * Tela de associação de proprietários aos bens.
	 * 
	 * @author bruno.carneiro
	 */
	public class AssociarProprietario extends AssociarProprietarioView {
		public static const EVENTO_REGISTRO_GRAVADO:String = "EVENTO_REGISTRO_GRAVADO_ASSOCIAR_PROPRIETARIO";
		
		private var _proprietario:BemProprietarioVO = null;
		private var _idBemPessoaCompartilhamento:Number;
		
		/**
		 * Construtor.
		 */
		public function AssociarProprietario(){
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evento:FlexEvent = null):void {
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			var botaoLimpar:Botao = BarraBotoesFormularioCadastroView(barraBotoes).btnCancelar;
			botaoLimpar.visible = botaoLimpar.includeInLayout = false;
			
			barraBotoes.btnSalvar.addEventListener(MouseEvent.CLICK, salvarProprietario);
			//barraBotoes.btnCancelar.addEventListener(MouseEvent.CLICK, limpar);
			barraBotoes.btnFechar.addEventListener(MouseEvent.CLICK, fechar);
						
			if(_proprietario != null) {
				carregarRegistro(_proprietario);
			}
		}
		
		/**
		 * Verifica o preenchimento dos campos e chama o método gravar.
		 */
		private function salvarProprietario(evento:Event = null):void {
			executarSeValido(gravar);
		}
		
		/**
		 * Preenche o registro e notifica a tela que o registro foi preenchido.
		 */
		private function gravar():void {
			var pessoaSelecionada:PessoaPlataformaVO = new PessoaPlataformaVO();
			FlexUtil.copiarPropriedades(componenteProcurarPessoa.registro, pessoaSelecionada);
			
			var proprietarioBem:BemProprietarioVO = new BemProprietarioVO();
			proprietarioBem.idPessoa = pessoaSelecionada.idPessoa;
			proprietarioBem.cpfCnpj = pessoaSelecionada.cpfCnpj;
			proprietarioBem.codigoTipoPessoa = pessoaSelecionada.codTipoPessoa;
			proprietarioBem.nomePessoa = pessoaSelecionada.nomePessoa;
			//proprietarioBem.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaSelecionada);
			proprietarioBem.percentualProprietario = campoPorcentagem.valor;
			proprietarioBem.areaPosse = campoAreaPosse.valor;
			
			dispatchEvent(new ObjetoEvent(EVENTO_REGISTRO_GRAVADO, proprietarioBem));
			
			fechar();
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fechar(evento:Event = null):void {
			fecharJanela();
		}
		
		/**
		 * Realiza a limpeza dos campos.
		 */
		public function limpar(evento:Event = null):void {
			if(initialized) {
				componenteProcurarPessoa.limpar();
				campoPorcentagem.text = "";
				_proprietario = null;
				verificarExibicaoComponente();
			}
		}
		
		/**
		 * Faz a configuração do destino dos serviços.
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this.destino = destino;
			componenteProcurarPessoa.configurarDestino(destino);
		}
		
		/**
		 * Carrega o registro pelo registro selecionado.
		 */
		public function carregarRegistro(proprietario:BemProprietarioVO):void {
			limpar();
			_proprietario = proprietario;
			if(initialized) {
				componenteProcurarPessoa.procurarPorCodigoCompartilhamento(proprietario.idPessoa);
				campoPorcentagem.valor = proprietario.percentualProprietario;
				campoAreaPosse.valor = proprietario.areaPosse;
				verificarExibicaoComponente();
			}
		}
		
		private function verificarExibicaoComponente():void {
			componenteProcurarPessoa.bloquearCampos(_proprietario == null);
		}
		
		public function adicionarAreaTotal(valor:Number):void {
			campoAreaTotal.valor = valor;
			
			if(!isNaN(valor) && valor > 0) {
				campoAreaPosse.validarObrigatorio = true;
				canvasImovel.includeInLayout = 
					canvasImovel.visible = true;
			}
		}
	}
}