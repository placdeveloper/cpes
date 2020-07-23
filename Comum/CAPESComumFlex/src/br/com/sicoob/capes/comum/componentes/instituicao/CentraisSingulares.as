package br.com.sicoob.capes.comum.componentes.instituicao {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.componentes.instituicao.vo.CentralSingularVO;
	import br.com.sicoob.capes.comum.componentes.instituicao.vo.UnidadeVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Componente utilizado geralmente nas telas de relatório para selecionar as instituições (Centrais, singulares e unidades).
	 **/
	public class CentraisSingulares extends CentraisSingularesView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.CentraisSingularesFachada";
		private static const INDICADOR_OBRIGATORIEDADE:String = " *";
		
		private var servico:ServicoJava = new ServicoJava();
		
		private var _tamanhoCombos:Number;
		private var _utilizarUnidades: Boolean = false;
		private var _utilizarNucleos: Boolean = false;
		private var _utilizarGerentes: Boolean = false;
		private var _centralObrigatoria: Boolean = false;
		private var _singularObrigatoria: Boolean = false;
		private var _unidadeObrigatoria: Boolean = false;
		private var _nucleoObrigatorio: Boolean = false;
		private var _gerenteObrigatorio: Boolean = false;
		
		public static const EVENTO_SINGULAR_SELECIONADA:String = "SingularSelecionada";
		
		private static const MENSAGEM_CENTRAL:String = "Obtendo centrais...";
		private static const MENSAGEM_SINGULAR:String = "Obtendo singulares...";
		private static const MENSAGEM_UNIDADE:String = "Obtendo unidades...";
		private static const MENSAGEM_NUCLEO:String = "Obtendo núcleos...";
		private static const MENSAGEM_GERENTE:String = "Obtendo gerentes...";
				
		/**
		 * Método Construtor
		 **/
		public function CentraisSingulares(){
			super();
			servico.source = CLASSE_SERVICO;
			
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO", CentralSingularVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO", UnidadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evt:FlexEvent=null):void {
			comboCentral.addEventListener(Event.CHANGE, obter_singulares);
			comboCentral.validarObrigatorio = _centralObrigatoria;
			rotuloCentral.text = "Central" + (_centralObrigatoria ? INDICADOR_OBRIGATORIEDADE : "") + ":";

			comboSingular.addEventListener(Event.CHANGE, ao_trocar_singular);
			comboSingular.validarObrigatorio = _singularObrigatoria;
			rotuloSingular.text = "Instituição" + (_singularObrigatoria ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			
			verificarComboAdicionais();
			configurarTamanhoCombos();
			configurarPosicionamentoCombos();
		}
		
		private function obter_centrais():void {
			MostraCursor.setBusyCursor(MENSAGEM_CENTRAL, Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico.obterCentrais.addEventListener(ResultEvent.RESULT, retorno_obter_centrais);
			servico.mensagemEspera = MENSAGEM_CENTRAL;
			servico.bloquearOperacao = true;
			servico.getOperation("obterCentrais").send(new RequisicaoReqDTO());
		}
		
		private function obter_singulares(evento:Event = null):void {
			if(comboCentral.selectedItem != null) {
				MostraCursor.setBusyCursor(MENSAGEM_SINGULAR, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.obterSingulares.addEventListener(ResultEvent.RESULT, retorno_obter_singulares);
				servico.mensagemEspera = MENSAGEM_SINGULAR;
				servico.bloquearOperacao = true;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.numeroCentral = comboCentral.selectedItem.numeroCooperativa;
				
				servico.getOperation("obterSingulares").send(dto);
			}
		}
		
		private function obter_unidades(evento:Event = null):void {
			if(_utilizarUnidades) {
				MostraCursor.setBusyCursor(MENSAGEM_UNIDADE, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.obterUnidades.addEventListener(ResultEvent.RESULT, retorno_obter_unidades);
				servico.mensagemEspera = MENSAGEM_UNIDADE;
				servico.bloquearOperacao = true;
				
				var instituicao:CentralSingularVO = comboSingular.selectedItem as CentralSingularVO;
				if(instituicao != null){
					var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
					dto.dados.numeroInstituicao = comboSingular.selectedItem.numeroCooperativa;
					
					servico.getOperation("obterUnidades").send(dto);
				}
			}
		}
		
		private function obter_nucleos(evento:Event = null): void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var instituicao:CentralSingularVO = comboSingular.selectedItem as CentralSingularVO;
			
			if(instituicao != null){
				dto.dados.idInstituicao = instituicao.idInstituicao;
				
				MostraCursor.setBusyCursor(MENSAGEM_NUCLEO, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.addEventListener(ResultEvent.RESULT, retorno_obter_nucleos);
				servico.mensagemEspera = MENSAGEM_NUCLEO;
				servico.bloquearOperacao = true;
				servico.getOperation("obterNucleos").send(dto);
			}
		}
		
		private function obter_gerentes(evento:Event = null): void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var instituicao:CentralSingularVO = comboSingular.selectedItem as CentralSingularVO;
			
			if(instituicao != null){
				dto.dados.idInstituicao = instituicao.idInstituicao;
				
				MostraCursor.setBusyCursor(MENSAGEM_GERENTE, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.addEventListener(ResultEvent.RESULT, retorno_obter_gerentes);
				servico.mensagemEspera = MENSAGEM_GERENTE;
				servico.bloquearOperacao = true;
				servico.getOperation("obterGerentes").send(dto);
			}
		}
		
		private function retorno_obter_centrais(evento:ResultEvent = null):void {
			FlexUtil.atualizarCombo(comboCentral, evento.result.dados.listaCentrais);
			if((evento.result.dados.listaCentrais as ArrayCollection).length == 1){
				comboCentral.selectedIndex = 1;
				comboCentral.enabled = false;
			}
			MostraCursor.removeBusyCursor();
			
			obter_singulares();
		}
		
		private function retorno_obter_singulares(evento:ResultEvent = null):void {
			FlexUtil.atualizarCombo(comboSingular, evento.result.dados.listaSingulares);
			var tamLista : int;
			tamLista = (evento.result.dados.listaSingulares as ArrayCollection).length;
			
			comboSingular.enabled = true;
			if(tamLista == 1){
				comboSingular.selectedIndex = 1;
				comboSingular.enabled = false;
			} else {
				if (tamLista > 1) {
					comboSingular.selectedIndex = 1;
				}
			}
			
			MostraCursor.removeBusyCursor();
			
			if(_utilizarUnidades){
				obter_unidades();
			}
			
			if(_utilizarNucleos){
				obter_nucleos();
			}
			
			if(_utilizarGerentes){
				obter_gerentes();
			}
			
			this.dispatchEvent(new ObjetoEvent(EVENTO_SINGULAR_SELECIONADA));
		}
		
		private function retorno_obter_unidades(evento:ResultEvent = null):void {
			comboUnidades.labelFunction = labelFunctionUnidades;
			FlexUtil.atualizarCombo(comboUnidades, evento.result.dados.listaUnidades);
			if((evento.result.dados.listaUnidades as ArrayCollection).length == 1){
				comboUnidades.selectedIndex = 1;
				comboUnidades.enabled = false;
			}
			
			FlexUtil.adicionarItemOpcional(comboUnidades);
			comboUnidades.selectedIndex = 0;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retorno_obter_nucleos(evento:ResultEvent):void {
			comboNucleos.labelFunction = labelFunctionNucleos;
			if(evento.result != null && evento.result.dados != null && evento.result.dados.listaNucleos != null) {
				FlexUtil.atualizarCombo(comboNucleos, evento.result.dados.listaNucleos);
			}
			
			FlexUtil.adicionarItemOpcional(comboNucleos);
			comboNucleos.selectedIndex = 0;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retorno_obter_gerentes(evento:ResultEvent):void {
			comboGerentes.labelFunction = labelFunctionGerentes;
			if(evento.result != null && evento.result.dados != null) {
				FlexUtil.atualizarCombo(comboGerentes, evento.result.dados.listaGerentes);
			}
			
			FlexUtil.adicionarItemOpcional(comboGerentes);
			comboGerentes.selectedIndex = 0;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function verificarComboAdicionais():void {
			if(_utilizarUnidades){
				canvasUnidade.visible = _utilizarUnidades;
				comboUnidades.validarObrigatorio = _unidadeObrigatoria;
				rotuloUnidade.text = "Unidade" + (_unidadeObrigatoria ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			}
			
			if(_utilizarNucleos){
				canvasNucleo.visible = _utilizarNucleos;
				comboNucleos.validarObrigatorio = _nucleoObrigatorio;
				rotuloNucleo.text = "Núcleo" + (_nucleoObrigatorio ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			}
			
			if(_utilizarGerentes){
				canvasGerente.visible = _utilizarGerentes;
				comboGerentes.validarObrigatorio = _gerenteObrigatorio;
				rotuloGerente.text = "Gerente" + (_gerenteObrigatorio ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			}
		}
		
		private function configurarTamanhoCombos():void {
			if(!isNaN(_tamanhoCombos) && _tamanhoCombos > 0) {
				comboCentral.width = _tamanhoCombos;
				comboSingular.width = _tamanhoCombos;
				
				if(_utilizarUnidades){
					comboUnidades.width = _tamanhoCombos;
				}
				
				if(_utilizarNucleos){
					comboNucleos.width = _tamanhoCombos;
				}
				
				if(_utilizarGerentes){
					comboGerentes.width = _tamanhoCombos;
				}
			}
		}
		
		private function configurarPosicionamentoCombos():void {
			if(_utilizarUnidades) {
				rotuloUnidade.y = 62;
				comboUnidades.y = 60;
			}
			
			if(_utilizarNucleos) {
				if(_utilizarUnidades){
					rotuloNucleo.y = 92;
					comboNucleos.y = 90;
				}else {
					rotuloNucleo.y = 62;
					comboNucleos.y = 60;
				}
			}
			
			if(_utilizarGerentes) {
				if(_utilizarUnidades && _utilizarNucleos){
					rotuloGerente.y = 122;
					comboGerentes.y = 120;
				}else if(_utilizarUnidades && !_utilizarNucleos){
					rotuloGerente.y = 92;
					comboGerentes.y = 90;
				}else {
					rotuloGerente.y = 62;
					comboGerentes.y = 60;
				}
			}
		}
		
		private function ao_trocar_singular(evento:Event = null): void {
			if(_utilizarUnidades){
				obter_unidades();
			}
			
			if(_utilizarNucleos){
				obter_nucleos();
			}
			
			if(_utilizarGerentes){
				obter_gerentes();
			}
			MostraCursor.removeBusyCursor();
		}
		
		private function labelFunctionUnidades(item:Object):String{
			return item.codigo + " - " + item.descricao;
		}
		
		private function labelFunctionNucleos(item:Object):String{
			return item.descricao;
		}
		
		private function labelFunctionGerentes(item:Object):String{
			return item.pessoa.pessoaCompartilhamento.nomePessoa;
		}
		
		//Métodos de acesso
		public function set tamanhoCombos(valor:Number):void {
			_tamanhoCombos = valor;
		}
		
		public function set utilizarUnidades(valor:Boolean): void {
			_utilizarUnidades = valor;
		}
		
		public function set utilizarNucleos(valor:Boolean): void {
			_utilizarNucleos = valor;
		}
		
		public function set utilizarGerentes(valor:Boolean): void {
			_utilizarGerentes = valor;
		}
		
		public function get central():CentralSingularVO {
			var retorno:CentralSingularVO = null;
			if(comboCentral.selectedItem != null){
				retorno = comboCentral.selectedItem as CentralSingularVO;
			}
			return retorno;
		}
		
		public function get singular():CentralSingularVO {
			var retorno:CentralSingularVO = null;
			if(comboSingular.selectedItem != null){
				retorno = comboSingular.selectedItem as CentralSingularVO;
			}
			return retorno;
		}
		
		public function get unidade():UnidadeVO {
			var retorno:UnidadeVO = null;
			if(comboUnidades.selectedItem != null){
				retorno = comboUnidades.selectedItem as UnidadeVO;
			}
			return retorno;
		}
		
		public function get nucleo():NucleoVO {
			var retorno: NucleoVO = null;
			if(comboNucleos.selectedItem != null){
				retorno = comboNucleos.selectedItem as NucleoVO;
			}
			return retorno;
		}
		
		public function get gerente():FuncionarioVO {
			var retorno:FuncionarioVO = null;
			if(comboGerentes.selectedItem != null){
				retorno = comboGerentes.selectedItem as FuncionarioVO;
			}
			return retorno;
		}
		
		public function limpar():void {
			comboCentral.selectedIndex = 0;
			comboSingular.selectedIndex = 0;
			
			if(_utilizarUnidades){
				comboUnidades.selectedIndex = 0;
			}
			
			if(_utilizarNucleos){
				comboNucleos.selectedIndex = 0;
			}
			
			if(_utilizarGerentes){
				comboGerentes.selectedIndex = 0;
			}
		}
		
		public function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				servico.configurarDestino(destino);
				obter_centrais();
			}
		}
		
		public function bloquearComboUnidade(valor:Boolean):void {
			comboUnidades.enabled = !valor;
		}
		
		public function bloquearComboNucleo(valor:Boolean):void {
			comboNucleos.enabled = !valor;
		}
		
		public function bloquearComboGerente(valor:Boolean):void {
			comboGerentes.enabled = !valor;
		}

		public function get centralObrigatoria():Boolean{
			return _centralObrigatoria;
		}

		public function set centralObrigatoria(value:Boolean):void{
			_centralObrigatoria = value;
		}

		public function get gerenteObrigatorio():Boolean{
			return _gerenteObrigatorio;
		}

		public function set gerenteObrigatorio(value:Boolean):void{
			_gerenteObrigatorio = value;
		}

		public function get nucleoObrigatorio():Boolean{
			return _nucleoObrigatorio;
		}

		public function set nucleoObrigatorio(value:Boolean):void{
			_nucleoObrigatorio = value;
		}

		public function get unidadeObrigatoria():Boolean{
			return _unidadeObrigatoria;
		}

		public function set unidadeObrigatoria(value:Boolean):void{
			_unidadeObrigatoria = value;
		}

		public function get singularObrigatoria():Boolean{
			return _singularObrigatoria;
		}

		public function set singularObrigatoria(value:Boolean):void{
			_singularObrigatoria = value;
		}
	}
}