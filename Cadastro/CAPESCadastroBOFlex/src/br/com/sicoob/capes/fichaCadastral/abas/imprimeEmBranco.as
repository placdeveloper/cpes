package br.com.sicoob.capes.fichaCadastral.abas{

	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	
	public class imprimeEmBranco extends imprimeEmBrancoView  {
		
		private var destinoVO:DestinoVO = null;
		
	    /**
	     *  Construtor.
	     */
		public function imprimeEmBranco()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		
		}
		
		private function init(evt:FlexEvent):void 
		{
			inicializarServicos();
			btImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
		}

		public function cancelar(event:MouseEvent):void {

			limparForm();
		}
		
		private function imprimir(evt:MouseEvent):void 
		{	
			var codigoTipoPessoa:int;
			if (rdPF.selected) {
				codigoTipoPessoa = 0;
			} else if (rdPJ.selected) {
				codigoTipoPessoa = 1;
			}

			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.qtdBens = ctBens.value; 
			dto.dados.qtdCertidoes = ctCertidoes.value;
			dto.dados.qtdEmails = ctEmails.value;
			dto.dados.qtdEnderecos = ctEndereco.value;
			dto.dados.qtdReferencias = ctReferencias.value;
			dto.dados.qtdRelacionamentos = ctRelacionamento.value;
			dto.dados.qtdTelefones = ctTelefone.value;
			dto.dados.qtdFontesRenda = ctFonteRenda.value;
			dto.dados.qtdBensImoveis = ctBensImoveis.value;
			dto.dados.qtdProdutividades = ctProdutividades.value;
			dto.dados.codigoTipoPessoa = codigoTipoPessoa;

			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFichaCadastralEmBrancoServicoRemote", dto, 
				"relatorioFichaCadastralEmBranco", destinoVO, "Emitindo Ficha Cadastral...", "PDF", false);
		}
		
		public function limparForm():void {
			ctBens.value = 1;
			ctEndereco.value = 1;  
			ctCertidoes.value = 1;
			ctEmails.value = 1;
			ctReferencias.value = 1;
			ctRelacionamento.value = 1;
			ctTelefone.value = 1;
			ctFonteRenda.value = 1;
			ctBensImoveis.value = 1;
			ctProdutividades.value = 1;
		}
		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
		}
	}
}