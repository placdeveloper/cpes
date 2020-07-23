package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.EncaminharAutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.encaminharAutorizacao.BotoesEncaminhamento;
	import br.com.sicoob.capes.encaminharAutorizacao.frmEncaminharAutorizacao;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class EncaminharAutorizacao extends TelaPlataformaAtendimentoCustomizadoCAPES {
				
		static private const CLASSE_SERVICO_IMPRIMIR_FICHA: String = 
			"br.com.sicoob.capes.relatorio.negocio.fachada.FichaCadastralFachada";		
		static private const OPERACAO_IMPRIMIR_CADASTRO: String = "imprimirCadastro";
						
		private var telaEncaminhamento:frmEncaminharAutorizacao = new frmEncaminharAutorizacao();
		private var botoesOpcoes:BotoesEncaminhamento = new BotoesEncaminhamento();
		
		//**************
		// Construtores:
		//**************
		
		public function EncaminharAutorizacao() { 
			super();			
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';

			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.EncaminharAutorizacaoVO", EncaminharAutorizacaoVO);
			
			this.addEventListener("carregou", init);
		}

		//**************
		// Eventos:
		//**************				
		private function init(evt:Event):void {
			this.textoAcao = "ENCAMINHAR PARA AUTORIZAÇÃO";
			this.textoModulo = "ENCAMINHAR PARA AUTORIZAÇÃO";
			this.iconModulo = "br/com/bancoob/imagens/icos/opts_32.png";
			
			telaEncaminhamento.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			
			this.incluirBotoesAdicionais();
			this.adicionaTela(telaEncaminhamento);
			
			if(!pessoa.utilizaGedGft) {
				botoesOpcoes.btEncaminharAutorizacao.enabled = false;	
				botoesOpcoes.btExcluirAutorizacao.enabled = false;	
				botoesOpcoes.btImprimirFicha.enabled = false;	
			}
		}
		
		protected override function configurarDestinosServicos(destino:DestinoVO):void {			
			super.configurarDestinosServicos(destino);	
			telaEncaminhamento.configurarDestino(destino);	
			this.destino = destino;
		}
		
		//**************
		//  Listeners
		//**************
		protected function encaminharAutorizacaoClick(obj:Object):void {
			telaEncaminhamento.encaminharautorizacao();			
		}	
		
		protected function excluirAutorizacaoClick(event:MouseEvent = null): void {
			telaEncaminhamento.excluirAutorizacao();
		}
		
		protected function imprimirFichaClick(event:MouseEvent=null):void {			
			var pessoaSelecionada:PessoaCompartilhamentoVO = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
						
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.idPessoa = pessoaSelecionada.pessoa.idPessoa;
			dto.dados.idPessoaCompartilhamento = pessoaSelecionada.idPessoaCompartilhamento;
			dto.dados.autorizacao = true;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFichaCadastralServicoRemote", dto, 
				"relatorioFichaCadastral", destino, "Emitindo Ficha Cadastral...", "PDF", false);
		}	
		
		protected function registroCarregado(evt:Event):void
		{
			
		}
		
		//--------------------------------------------------------------------------
		//  Controle de exibição de telas.
		//--------------------------------------------------------------------------		
		
		private function incluirBotoesAdicionais(): void {
			
			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btEncaminharAutorizacao.addEventListener(MouseEvent.CLICK, encaminharAutorizacaoClick);
			botoesOpcoes.btImprimirFicha.addEventListener(MouseEvent.CLICK, imprimirFichaClick);
			botoesOpcoes.btExcluirAutorizacao.addEventListener(MouseEvent.CLICK, excluirAutorizacaoClick);
		}
		
			
	}
}