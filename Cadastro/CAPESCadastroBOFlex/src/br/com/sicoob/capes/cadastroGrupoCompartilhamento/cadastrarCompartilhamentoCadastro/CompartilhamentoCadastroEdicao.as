package br.com.sicoob.capes.cadastroGrupoCompartilhamento.cadastrarCompartilhamentoCadastro {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.campos.CampoNumerico;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class CompartilhamentoCadastroEdicao extends CompartilhamentoCadastroEdicaoView {
		
		public var itemEdicao:Object;
		public var isIncluir:Boolean;
		public var isHouveAlteracao:Boolean;
		
		private var servico:ServicoJava = new ServicoJava();
		private var _destino:DestinoVO;

		private var requisicaoDto:RequisicaoReqDTO;
		
		public var RECARREGAR_COMBO:String = "RECARREGAR_COMBO_GRUPOS";
		
		public function CompartilhamentoCadastroEdicao():void {
			servico.source = "br.com.sicoob.capes.cadastro.fachada.GrupoCompartilhamentoFachada";
			servico.obterDefinicoesCompartilhamentoCadastro.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			servico.incluirCompartilhamentoCadastro.addEventListener(ResultEvent.RESULT, salvarOnResult);
			servico.alterarCompartilhamentoCadastro.addEventListener(ResultEvent.RESULT, salvarOnResult);
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void{
			toolTipUtil.targets = [codigo, descricao];
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnCancelar.addEventListener(MouseEvent.CLICK, preencherCampos);
			btnSalvar.addEventListener(MouseEvent.CLICK, salvar);
			
			if(isIncluir){
				pesquisarProximoCodigo();
			}
		}
		
		public function pesquisarProximoCodigo():void{
			requisicaoDto = new RequisicaoReqDTO();			
			MostraCursor.setBusyCursor("Buscando Sugestão de Código",this,MostraCursor.CURSOR_PESQUISAR);
			servico.obterDefinicoesCompartilhamentoCadastro(requisicaoDto);
		}

		private function obterDefinicoesOnResult(event:ResultEvent):void{
			codigo.valor = Number(event.result.dados.codigo);
			itemEdicao.codigo = codigo.valor;
			MostraCursor.removeBusyCursor();
		}
		
		public function preencherCampos(event:Event=null):void{
			codigo.valor = itemEdicao.codigo;
			descricao.text = itemEdicao.descricao;
			isHouveAlteracao = false;
			codigo.enabled = isIncluir;
		}
		
		public function limpar():void{
			codigo.text = "";
			descricao.text = "";
			itemEdicao = new Object();
			srfManter.selected = true;
		}
		
		private function salvar(event:Event=null):void{
			if(!validarCampos()){
				return;
			}
			
			MostraCursor.setBusyCursor("salvando", this, MostraCursor.CURSOR_PESQUISAR);
			
			requisicaoDto = new RequisicaoReqDTO();
			
			itemEdicao.codigo = codigo.valor;
			itemEdicao.descricao = descricao.text;
			requisicaoDto.dados.codigo = itemEdicao.codigo;
			requisicaoDto.dados.descricao = itemEdicao.descricao;
			
			var valorSRFSelecionado:Object = grupoSRF.selectedValue;
			if(valorSRFSelecionado != null && valorSRFSelecionado != "Manter"){
				requisicaoDto.dados.habilitarIntegracaoSRF = new Boolean(valorSRFSelecionado);
			}
			
			if(isIncluir){
				servico.incluirCompartilhamentoCadastro(requisicaoDto);
			}else{
				servico.alterarCompartilhamentoCadastro(requisicaoDto);
			}
			
			var evento:Event = new Event(RECARREGAR_COMBO);
			this.dispatchEvent(evento);
		}
		
		private function salvarOnResult(event:ResultEvent):void{
			isHouveAlteracao = true;
			MostraCursor.removeBusyCursor();
			fechar();
		}
		
		private function validarCampos():Boolean{
			if(codigo.valor == 0){
				codigo.errorString = "Preencha o campo Código."
				return false;
			}

			if(codigo.valor > Number(CampoNumerico.SHORT_POSITIVO)){
				codigo.errorString = "Valor deve ser menor que "+CampoNumerico.SHORT_POSITIVO;
				return false;
			}

			if(descricao.text == ""){
				descricao.errorString = "Preencha o campo Descrição.";
				return false;
			}
			return true;
		}
		
		private function fechar(event:Event=null):void{
			fecharJanela();		
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
		}
	}
}