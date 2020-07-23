package {
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarMensagem.frmEditarMensagem;
	import br.com.sicoob.capes.cadastrarMensagem.frmListarMensagem;
	import br.com.sicoob.capes.comum.vo.entidades.MensagemVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoDestinoExibicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoMensagemVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.core.Container;
	
	public class MensagemSelecao extends TelaPlataformaAtendimentoCAPESBase {

		private var telaLista:frmListarMensagem = new frmListarMensagem();
		private var telaEdicao:frmEditarMensagem = new frmEditarMensagem();

		public function MensagemSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoMensagem", TipoMensagemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Mensagem", MensagemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoDestinoExibicao",TipoDestinoExibicaoVO);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}

		private function init(evt:Event):void {
			this.setTelaLista(telaLista);
			this.setTelaEdicao(telaEdicao);

			this.textoAcao = "MENSAGEM PESSOA INDIVIDUAL";
			this.textoModulo = "MENSAGEM PESSOA INDIVIDUAL";
			this.textoOpcoes = "OPÇÕES";
			this.iconModulo = "br/com/bancoob/imagens/icos/mensagemPessoa.png";
		}

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.configurarDestino(destinoVO);
			telaEdicao.configurarDestino(destinoVO);
		}

		override protected function habilitarControles(cont:Container):void {
			super.habilitarControles(cont);
			telaEdicao.dataHoraInicio.enabled = false;
		}
	}
}