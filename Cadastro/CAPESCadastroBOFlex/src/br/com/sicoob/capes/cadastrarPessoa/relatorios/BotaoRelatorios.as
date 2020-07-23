package br.com.sicoob.capes.cadastrarPessoa.relatorios {
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cartaoAssinatura.frmCartaoAssinatura;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.fichaCadastral.frmFichaCadastral;
	import br.com.sicoob.capes.fichaCadastral.frmFichaCadastralNova;
	import br.com.sicoob.capes.relatorioDeclaracaoProposito.RelatorioDelacracaoProposito;
	
	public class BotaoRelatorios extends BotaoRelatoriosView {
		
		private var frmFichaCadastralPessoa:frmFichaCadastral = new frmFichaCadastral();
		private var frmFichaCadastralNovaPessoa:frmFichaCadastralNova = new frmFichaCadastralNova();
		private var frmCartao:frmCartaoAssinatura = new frmCartaoAssinatura();
		
		private var _destino:DestinoVO;
		private var _registro:PessoaCompartilhamentoVO;
		
	    /**
	     * Construtor.
	     */
		public function BotaoRelatorios()	{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			botFichaCadastral.addEventListener(MouseEvent.CLICK, fichaCadastralClicado);
			botFichaCadastralNova.addEventListener(MouseEvent.CLICK, fichaCadastralNovaClidado);
			btnImprimirCartaoAssinatura.addEventListener(MouseEvent.CLICK, cartaoAssinaturaClidado);
			//btnRelatorioDeclaracaoProposito.addEventListener(MouseEvent.CLICK, botaoDeclaracaoPropositoClicado);
			//btnFormDeVisita.addEventListener(MouseEvent.CLICK, exibirFormularioVisita);
		}
		
//		private function imprimirCartaoAssinatura(event:MouseEvent=null):void {
//			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
//			dto.dados.pessoa = this._registro as PessoaCompartilhamentoVO;
//			
//			var rl: RelatorioUtil = new RelatorioUtil();
//			rl.emitirRelatorio(OPERACAO_IMPRIMIR_CARTAO_ASSINATURA, CLASSE_SERVICO_RELATORIO, dto, "relatorioCartaoAssinatura", this._destino, "Emitindo Cartão Assinatura...", "PDF", false);
//			
//			fechar();
//		}
		
		private function cartaoAssinaturaClidado(event:MouseEvent=null):void{
			var janela:Janela = new Janela();
			janela.title = "Cartão de Assinatura";
			janela.addChild(frmCartao);
			janela.abrir(DisplayObject(Application.application), true);
			fechar();
		}
		
		private function fichaCadastralClicado(event:MouseEvent=null):void {
			var janela:Janela = new Janela();
			janela.title = "FICHA CADASTRAL";
			janela.addChild(frmFichaCadastralPessoa); 
			janela.abrir(DisplayObject(Application.application), true);
			janela.addEventListener(Janela.FECHAR_JANELA, frmFichaCadastralPessoa.fechar);
			fechar();
		}
		
		private function fichaCadastralNovaClidado(event:MouseEvent=null):void{
			var janela:Janela = new Janela();
			janela.title = "FICHA CADASTRAL NOVA";
			janela.addChild(frmFichaCadastralNovaPessoa); 
			janela.abrir(DisplayObject(Application.application), true);
			janela.addEventListener(Janela.FECHAR_JANELA, frmFichaCadastralNovaPessoa.fechar);
			fechar();
		}
		
		public function fechar(evento:Event = null):void {
			pegaJanela().fecharJanela();
		}
		
		private function botaoDeclaracaoPropositoClicado(event:MouseEvent=null):void {
			var janela:Janela = new Janela();
			var declaracaoProposito:RelatorioDelacracaoProposito = new RelatorioDelacracaoProposito();
			
			janela.title = "Declaração de propósito";
			janela.addChild(declaracaoProposito); 
			janela.abrir(DisplayObject(Application.application), true);
			
			fechar();
		}
		
		private function exibirFormularioVisita (event:MouseEvent = null):void{
			var pessoa:PessoaCompartilhamentoVO = this._registro as PessoaCompartilhamentoVO;
			
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.codigoTipoPessoa = pessoa.pessoa.tipoPessoa.codTipoPessoa;
			dto.dados.idPessoaCompartilhamento = pessoa.idPessoaCompartilhamento;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFormularioVisitaServicoRemote", dto, 
				"formularioVisita", destino, "Emitindo formulário", "PDF", false);
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this._destino = destino;
		}
		
		public function set registro(registro:PessoaCompartilhamentoVO):void {
			this._registro = registro;
		}
		
	}
}