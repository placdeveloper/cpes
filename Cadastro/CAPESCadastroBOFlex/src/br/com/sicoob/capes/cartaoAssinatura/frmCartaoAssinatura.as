package br.com.sicoob.capes.cartaoAssinatura {

	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class frmCartaoAssinatura extends frmCartaoAssinaturaView {
		
		private var _registro:PessoaCompartilhamentoVO;
		private var _destinoVO:DestinoVO = null;
		
		public function frmCartaoAssinatura() {
			super();			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void{
			inicializarServicos();
			btImprimir.addEventListener(MouseEvent.CLICK, imprimirCartaoAssinatura);
		}
		
		private function imprimirCartaoAssinatura(event:MouseEvent=null):void {
			var pessoaSelecionada:PessoaCompartilhamentoVO = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());
			
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.cartaoEmBranco = rdCartaoEmBranco.selected;
			dto.dados.cartaoPreenchido = rdCartaoPreenchido.selected;
			dto.dados.nomeCompleto = pessoaSelecionada.nomeCompleto;
			dto.dados.cpfCnpj = pessoaSelecionada.pessoa.cpfCnpj;
			dto.dados.codigoTipoPessoa = pessoaSelecionada.pessoa.tipoPessoa.codTipoPessoa;
				
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioPessoaCompartilhamentoServicoRemote", dto, 
				"relatorioCartaoAssinatura", _destinoVO, "Emitindo Cartão Assinatura...", "PDF", false);
		}
		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			this._destinoVO = destino;
		}
	}
}