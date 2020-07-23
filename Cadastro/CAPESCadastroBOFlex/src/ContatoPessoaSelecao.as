package {

	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarContato.BotoesOpcoesContatos;
	import br.com.sicoob.capes.cadastrarContato.IEdicaoContato;
	import br.com.sicoob.capes.cadastrarContato.frmEditarEmail;
	import br.com.sicoob.capes.cadastrarContato.frmEditarEndereco;
	import br.com.sicoob.capes.cadastrarContato.frmEditarTelefone;
	import br.com.sicoob.capes.cadastrarContato.frmListarContatos;
	import br.com.sicoob.capes.comum.vo.entidades.EmailVO;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoProxy;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.TelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmailVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoLogradouroVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoTelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.events.ListEvent;
	import mx.managers.IFocusManagerContainer;
	
	public class ContatoPessoaSelecao extends TelaPlataformaAtendimentoCAPESBase {
		
		private var telaLista:frmListarContatos = new frmListarContatos();
		private var telaEdicaoEndereco:frmEditarEndereco = new frmEditarEndereco();
		private var telaEdicaoTelefone:frmEditarTelefone = new frmEditarTelefone();
		private var telaEdicaoEmail:frmEditarEmail = new frmEditarEmail();
		
		private var botoesOpcoes:BotoesOpcoesContatos = new BotoesOpcoesContatos();
		
		private static const TELA_LISTA:int = 0;
		private static const TELA_EDICAO_ENDERECO:int = 1;
		private static const TELA_EDICAO_TELEFONE:int = 2;
		private static const TELA_EDICAO_EMAIL:int = 3;
		
		private var telasEdicaoAdicionadas:Boolean = false;
		
		public static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		public static const OPERACAO_INCLUIR:String = "incluirDados";
		public static const OPERACAO_ALTERAR:String = "alterarDados";
		public static const OPERACAO_OBTER:String = "obterDados";
		public static const OPERACAO_EXCLUIR:String = "excluirDados";
		
		/**
		 * Construtor.
		 */
		public function ContatoPessoaSelecao(){
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Email", EmailVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Endereco", EnderecoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Localidade", LocalidadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Telefone", TelefoneVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoEmail", TipoEmailVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoaContato", TipoPessoaContatoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoEndereco", TipoEnderecoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoLogradouro",TipoLogradouroVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoTelefone", TipoTelefoneVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UF", UFVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.proxies.EnderecoProxy", EnderecoProxy);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
		
		private function init(event:Event):void{
			this.textoAcao = "CONTATO";
			this.textoModulo = "CONTATOS";
			this.iconModulo = "br/com/bancoob/imagens/icos/Enderecos.png";
			
			telaLista.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
			telaLista.addEventListener(frmListarContatos.CLICK_ABAS, clickAbas);
			telaLista.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			telaLista.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, alterarClicado);
			
			telaEdicaoEndereco.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			telaEdicaoTelefone.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			telaEdicaoEmail.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			
			telaEdicaoEndereco.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			telaEdicaoTelefone.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			telaEdicaoEmail.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			
			telaEdicaoEndereco.addEventListener(Modulo.REGISTRO_EXCLUIDO, registroExcluido);
			telaEdicaoTelefone.addEventListener(Modulo.REGISTRO_EXCLUIDO, registroExcluido);
			telaEdicaoEmail.addEventListener(Modulo.REGISTRO_EXCLUIDO, registroExcluido);
			
			incluirBotoesAdicionais();
			setTelaLista(telaLista);
			exibirTelaListar();
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaEdicaoEndereco.dispose();
			telaEdicaoTelefone.dispose();
			telaEdicaoEmail.dispose();
			botoesOpcoes = null;
			telaEdicaoEndereco = null;
			telaEdicaoTelefone = null;
			telaEdicaoEmail = null;
		}

		private function incluirBotoesAdicionais(): void {
			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btCorrespondencia.addEventListener(MouseEvent.CLICK, tornarCorrespondenciaClicado);
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.configurarDestinosServicos(destinoVO);
			telaEdicaoEndereco.configurarDestinosServicos(destinoVO);
			telaEdicaoTelefone.configurarDestinosServicos(destinoVO);
			telaEdicaoEmail.configurarDestinosServicos(destinoVO);
		}
		
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function registroSelecionado(evt:Event):void {
			itemLista = (evt.target as frmListarContatos).obterGrid().selectedItem;
			mostraBotoesMudaGrid();
			exibirBotoesAdicionais(itemLista);
		}
		
		protected override function registroExcluido(evt:Event):void {
			MostraCursor.removeBusyCursor();
			exibirTelaListar();
			this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
		}
		
		protected override function registroCarregado(evt:Event):void {
			super.registroCarregado(evt);
			exibirBotaoAlteracao();
			verificarRegistroEmAlteracao();
		}
		
		protected function clickAbas(event:Event):void{
			MostraCursor.removeBusyCursor();
			var grid:Grid = telaLista.obterGrid();
			
			if( grid != null && grid.dataProvider.length > 0) {
				grid.selectedIndex = -1;
				grid.setFocus();
			} else {
				if(listaBotoes.botNovo.enabled){
					listaBotoes.botNovo.setFocus();
				}
			}
			
			mostraBotoesLista();
			exibirBotoesAdicionais(grid.selectedItem);
		}
		
		//--------------------------------------------------------------------------
		//  Listener dos botões
		//--------------------------------------------------------------------------
		protected function tornarCorrespondenciaClicado(evt:Event = null):void {
			Alerta.show("Você confirma a operação?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, correspondenciaConfirmada);
		}

		protected function correspondenciaConfirmada(evt:Event):void {
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			telaLista.tornarCorrespondenciaPadrao();	
		}
		
		override protected function consultarClicado(evt:MouseEvent=null):void {
			txtAcao.text = "< CONSULTANDO " + textoAcao.toUpperCase() + " >";
			
			listaBotoes.botOk.enabled = false;
			listaBotoes.botCancelar.enabled = false;
			
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			tela.carregarRegistro(itemLista);
		}
		
		override protected function alterarClicado(event:Event=null):void {
			if(listaBotoes.botVer.enabled && !listaBotoes.botAlterar.enabled){
				consultarClicado();
				return;
			}
			else if(!listaBotoes.botAlterar.enabled) {
				return;
			}
			
			txtAcao.text = "< EDITANDO " + textoAcao.toUpperCase() + " >";
			
			listaBotoes.botOk.enabled = true;
			listaBotoes.botCancelar.enabled = true;
			
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			tela.carregarRegistro(itemLista);
		}
		
		override protected function cancelarClicado(event:MouseEvent=null):void {
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			tela.restaurarRegistro();
			tela.preencherCampos();
		}

		override protected function gravarClicado(event:MouseEvent=null):void {
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			tela.gravarRegistro();
		}
			
		override protected function voltarClicado(event:Event=null):void {
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			
			if(possuiEdicao){
				tela.atualizarCamposRegistro();
			}
			
			if(_novo){
				exibirTelaListar();
				return;
			}
			
			if(tela.verificarAlteracoes()){
				exibirTelaListar();
			}else{
				Alerta.show("Tem certeza que deseja trocar de tela sem salvar?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, exibirTelaListar);
			}
		}
		
		private function exibirBotoesAdicionais(endereco:Object=null): void {
			var enderecoSelecionado: Boolean = telaLista.isAbaEnderecoSelecionada();
			botoesOpcoes.btCorrespondencia.visible = enderecoSelecionado;
			if(enderecoSelecionado) {
				botoesOpcoes.btCorrespondencia.enabled =  endereco != null && !endereco.padraoCorrespondencia;
			}
		}
		
		//--------------------------------------------------------------------------
		//  Controle de exibição de telas.
		//--------------------------------------------------------------------------
		protected override function exibirTelaConsulta(consulta:Boolean=false):void{
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			_novo = false;
			
			super.selecionaTela(tela);
			mostraBotoesEdicao(consulta);
			
			this.validateNow();
			(tela as Container).validateNow();
			
			desabilitarControles(tela as Container);
			tela.preencherCampos();
			systemManager.activate(Application.application as IFocusManagerContainer);
		}
		
		protected override function exibirTelaListar(evt:Event = null):void{
			_novo = false;
			txtAcao.text = "";
			
			var telaExibicao:int = vsTelas.selectedIndex;
			var aba:int = telaLista.navegacaoTab.selectedIndex;
			
			if(telaExibicao == TELA_EDICAO_ENDERECO){
				aba = frmListarContatos.ABA_ENDERECO;
			} else if(telaExibicao == TELA_EDICAO_TELEFONE) {
				aba = frmListarContatos.ABA_TELEFONE;
			} else if(telaExibicao == TELA_EDICAO_EMAIL) {
				aba = frmListarContatos.ABA_EMAIL;
			}
			
			telaLista.listar(aba);
			super.selecionaTelaListaPlataforma(telaLista);
			
			mostraBotoesLista();
			exibirBotoesAdicionais();
			systemManager.activate(Application.application as IFocusManagerContainer);
		}
		
		protected override function exibirTelaEdicao(consulta:Boolean=false):void{
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			_novo = false;

			super.selecionaTela(tela);
			mostraBotoesEdicao();

			this.validateNow();
			(tela as Container).validateNow();

			habilitarControles(tela as Container);
			tela.preencherCampos();
			systemManager.activate(Application.application as IFocusManagerContainer);
		}	
		
		protected override function abrirInclusao():void {
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			_novo = true;
			
			txtAcao.text = "< ADICIONANDO " + textoAcao.toUpperCase() + " >";

			super.selecionaTela(tela);
			mostraBotoesEdicao();
			
			habilitarControles(tela as Container);
			tela.novoRegistro();
			systemManager.activate(Application.application as IFocusManagerContainer);
		}		
		
		protected override function exclusaoConfirmada(evt:Event):void {
			var tela:IEdicaoContato = obterTelaEdicaoContato();
			tela.excluirRegistro(itemLista);
		}
		
		//--------------------------------------------------------------------------
		//  Métodos auxiliares
		//--------------------------------------------------------------------------
		private function obterTelaEdicaoContato(): IEdicaoContato {
			if(!telasEdicaoAdicionadas) {
				this.adicionaTela(telaEdicaoEndereco);
				this.adicionaTela(telaEdicaoTelefone);
				this.adicionaTela(telaEdicaoEmail);
				telasEdicaoAdicionadas = true;
			}
			
			var tela:IEdicaoContato = null;
			if(telaLista.isAbaEnderecoSelecionada()) {
				tela = telaEdicaoEndereco;
			} else if(telaLista.isAbaTelefoneSelecionada()) {
				tela = telaEdicaoTelefone;
			} else {
				tela = telaEdicaoEmail;
			}
			
			return tela;
		}
	}
}