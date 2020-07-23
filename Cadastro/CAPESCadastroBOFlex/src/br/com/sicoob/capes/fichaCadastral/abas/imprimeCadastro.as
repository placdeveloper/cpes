package br.com.sicoob.capes.fichaCadastral.abas{

	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.input.Check;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class imprimeCadastro extends imprimeCadastroView  {
		
		private var destinoVO:DestinoVO = null;
		
	    /**
	     *  Construtor.
	     */
		public function imprimeCadastro()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		
		}
		
		private function init(evt:FlexEvent):void 
		{
			inicializarServicos();
			btImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			ckTodos.addEventListener(MouseEvent.CLICK, marcarTodos);
			rdAtual.selected = true;
			dtData.dataMaxima = new Date();
		}
		
		public function cancelar(event:MouseEvent):void {

			limparForm();
		}
		
		public function limparForm():void {
			
			for each (var checkbox1:Check in boxCampos.getChildren())
			{
			    checkbox1.selected = false;
			}
			
			for each (var checkbox2:Check in boxCampos.getChildren())
			{
			    checkbox2.enabled = true;
			}
			
			rdAtual.selected = true;
			dtData.selectedDate = new Date();
		}
		
		private function marcarTodos(event:MouseEvent):void {
			if (ckTodos.selected) {
				
				//Actionscript não possue variavel com escopo de bloco
				for each (var checkbox1:Check in boxCampos.getChildren())
				{
				    checkbox1.selected = true;
				}
				
				for each (var checkbox2:Check in boxCampos.getChildren())
				{
				    checkbox2.enabled = false;
				}
				
				ckTodos.enabled = true;
			} else {
				
				for each (var checkbox3:Check in boxCampos.getChildren())
				{
				   checkbox3.selected = false;
				}
				
				for each (var checkbox4:Check in boxCampos.getChildren())
				{
				    checkbox4.enabled = true;
				}
			} 	
		}
		
		private function imprimir(evt:MouseEvent):void 
		{	
			
			var pessoaSelecionada:PessoaCompartilhamentoVO = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());
			
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.atual = rdAtual.selected;
			dto.dados.historico = rdTudo.selected;
			dto.dados.posicao = rdPosicao.selected;

			dto.dados.bens = ckBem.selected;
			dto.dados.certidoes = ckCertidao.selected;
			dto.dados.data = dtData.selectedDate;
			dto.dados.emails = ckEmail.selected;
			dto.dados.enderecos = ckEndereco.selected;
			dto.dados.produtores = ckProdutor.selected;
			dto.dados.referencias = ckReferencia.selected;
			dto.dados.fontesDeRenda = ckFonteRenda.selected;
			dto.dados.telefones = ckTelefone.selected;
			dto.dados.relacionamentos = ckRelacionamento.selected;
			
			dto.dados.data = dtData.selectedDate;
			dto.dados.codigoTipoPessoa = pessoaSelecionada.pessoa.tipoPessoa.codTipoPessoa;
			dto.dados.idPessoa = pessoaSelecionada.pessoa.idPessoa;
			dto.dados.idPessoaCompartilhamento = pessoaSelecionada.idPessoaCompartilhamento;

			if(rdPosicao.selected){
				if(StringUtils.trim(dtData.compMask.text) == ""){
					Alerta.show("Favor preencher a data!", "Erro", Alerta.ALERTA_ERRO);
					return ;
				}
				
				if(validaData()){
					if(dtData.compDate.selectedDate > new Date()){
						Alerta.show("Data inválida! Data máxima para este campo é " + convertDataToString(new Date()) + ".", "Erro", Alerta.ALERTA_ERRO);
						dtData.setFocus();
						return;
					}
					RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFichaCadastralServicoRemote", dto,	"relatorioFichaCadastral", destinoVO, "Emitindo Ficha Cadastral...", "PDF", false);
					return;
				}else{
					Alerta.show("A data é inválida!","Erro", Alerta.ALERTA_ERRO);
					return;
				}
			}
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFichaCadastralServicoRemote", dto, 
				"relatorioFichaCadastral", destinoVO, "Emitindo Ficha Cadastral...", "PDF", false);
		}
		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
		}
		
		private function validaData():Boolean{
			return dtData.validar(new Date(dtData.compMask.text));
		}
	
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}
	}
}