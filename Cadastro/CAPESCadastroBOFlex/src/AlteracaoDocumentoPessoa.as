package
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.alterarCpfCnpj.AlteracaoDocumentoPessoaView;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	
	public class AlteracaoDocumentoPessoa extends AlteracaoDocumentoPessoaView {
		
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.AlteracaoDocumentoPessoaFachada";
		public static const LIMPAR_TELA: String = "limparTela";
		private static const DESTINO_CAPES:String = "destinoCAPES";
			
		//**************
		// Construtores:
		//**************
		
		public function AlteracaoDocumentoPessoa() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		//**************
		// Eventos:
		//**************				
		protected function init(event: FlexEvent): void {
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';	

			telaPesquisa.addEventListener(LIMPAR_TELA, limpar);
			telaPesquisa.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			
			telaAlteracao.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			telaPesquisa.configurarDestinos(destino);
			telaAlteracao.configurarDestinos(destino);
		}
		
		public function registroGravado(evt:Event):void {
			limpar(evt);
			MostraCursor.removeBusyCursor();
		}
		
		private function registroCarregado(event:Event): void {

			var pessoa: PessoaVO = telaPesquisa.obterPessoa();
			telaAlteracao.exibir(pessoa);
			
			MostraCursor.removeBusyCursor();
		}				
	
		private function limpar(evt:Event):void {
			// Pesquisa
			telaPesquisa.limpar();
						
			// Alteracao
			telaAlteracao.limpar();
			telaAlteracao.visible = false;
		}	
	}
}