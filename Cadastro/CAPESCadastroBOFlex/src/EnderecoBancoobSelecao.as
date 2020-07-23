package
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.alterarEnderecoBancoob.frmListarEnderecosBancoob;
	import br.com.sicoob.capes.cadastrarContato.BotoesOpcoesContatos;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoBaseVO;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.ListEvent;
	
	public class EnderecoBancoobSelecao extends TelaPlataformaAtendimentoCAPESBase {
			
		private var telaLista:frmListarEnderecosBancoob = new frmListarEnderecosBancoob();
		private var botoesOpcoes:BotoesOpcoesContatos = new BotoesOpcoesContatos();
		
		public function EnderecoBancoobSelecao() {
			super();
			registrarClasses();
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
		
		private function registrarClasses(): void {
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';		
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.EnderecoBase", EnderecoBaseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Endereco", EnderecoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Localidade", LocalidadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UF", UFVO);			
		}
		
		private function init(event:Event):void{
			
			this.textoAcao = "ENDEREÇO";
			this.textoModulo = this.produtosBancoob ? "PRODUTOS BANCOOB - ENDEREÇO" :
					"ENDEREÇO";
			this.iconModulo = "br/com/bancoob/imagens/icos/Enderecos.png";

			this.setTelaLista(telaLista);
			incluirBotoesAdicionais();
			telaLista.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
			telaLista.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
		}		

		private function incluirBotoesAdicionais(): void {

			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btCorrespondencia.addEventListener(MouseEvent.CLICK, 
					tornarCorrespondenciaClicado);
			exibirBotoesAdicionais();
		}

	    //--------------------------------------------------------------------------
	    //  Listener dos botões
	    //--------------------------------------------------------------------------		
	    protected function tornarCorrespondenciaClicado(evt:Event = null):void {
	    	Alerta.show("Você confirma a operação?", "Confirmação", Alerta.ALERTA_PERGUNTA, 
	    			null, correspondenciaConfirmada);
	    }

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------	
		protected override function configurarDestinosServicos(destino:DestinoVO):void{
			telaLista.configurarDestinosServicos(destino);
		}
		
		protected function correspondenciaConfirmada(evt:Event):void {
			telaLista.tornarCorrespondenciaPadrao();	
		}		
		
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------		
		private function registroSelecionado(evt:Event):void {
			itemLista = telaLista.obterGrid().selectedItem;	
			mostraBotoesMudaGrid();
			
			exibirBotoesAdicionais(itemLista);
		}			
		
		protected override function alterarClicado(event:Event=null):void {
		}
				
	    //--------------------------------------------------------------------------
	    //  Controle de exibição de botões.
	    //--------------------------------------------------------------------------
	    
		private function exibirBotoesAdicionais(endereco:Object=null): void {
			botoesOpcoes.btCorrespondencia.visible = true;
			botoesOpcoes.btCorrespondencia.enabled =  endereco != null && !endereco.padraoCorrespondencia;
		}
			    
		protected override function mostraBotoesEdicao(consulta:Boolean=false):void {
			desabilitarBotoes();												
		}
		
		protected override function mostraBotoesLista():void	{
			desabilitarBotoes();
		}		
		
		protected override function mostraBotoesMudaGrid(evt:ListEvent = null):void{
			desabilitarBotoes();
		}				
		
		private function desabilitarBotoes():void {
			listaBotoes.mostraBotao(listaBotoes.botAlterar, false);
			listaBotoes.mostraBotao(listaBotoes.botCancelar, false);
			listaBotoes.mostraBotao(listaBotoes.botExcluir, false);
			listaBotoes.mostraBotao(listaBotoes.botNovo, false);
			listaBotoes.mostraBotao(listaBotoes.botOk, false);
			listaBotoes.mostraBotao(listaBotoes.botVer, false);
			listaBotoes.mostraBotao(listaBotoes.botVoltar, false);
		}
	}
}