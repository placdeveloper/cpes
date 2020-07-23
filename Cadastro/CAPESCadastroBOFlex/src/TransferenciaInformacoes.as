package {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.PerfilTarifarioPK;
	import br.com.sicoob.capes.transferenciaInformacoes.TransferenciaInformacoesView;
	import br.com.sicoob.capes.transferenciaInformacoes.monitoracao.MonitoracaoTransferenciaInformacoes;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class TransferenciaInformacoes extends TransferenciaInformacoesView {
		private static var GERENTE:int = 0;
		private static var UNIDADE:int = 1;
		//private static var GRUPO:int = 2;
		private static var NUCLEO:int = 2;
		private static var CPFCNPJ:int = 3;
		
		private var servico:ServicoJava;
		private var dto:RequisicaoReqDTO;
		
		private var janela:Janela = new Janela();
		private var monitoracaoTransferencia:MonitoracaoTransferenciaInformacoes = new MonitoracaoTransferenciaInformacoes();
		private static const TITULO_JANELA_STATUS:String = "Status das transferências";
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function TransferenciaInformacoes()
		{
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Instituicao", InstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao", FuncaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomico", GrupoEconomicoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa", GrupoEconomicoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao", PessoaInstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifario", PerfilTarifarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifarioPK", PerfilTarifarioPK);
			
			servico = ServicoJavaUtil.getServico("br.com.sicoob.capes.cadastro.fachada.TransferenciaInformacoesFachada");
			servico.bloquearOperacao = true;
			servico.mensagemEspera = "Processando Solicitação";
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, mudarTipoTransferencia);
			servico.verificarTransferencias.addEventListener(ResultEvent.RESULT, resultTransferir);
			servico.transferirInformacoes.addEventListener(ResultEvent.RESULT, resultTransferirInformacoes);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void{
			btOk.addEventListener(MouseEvent.CLICK, transferir);
			btStatus.addEventListener(MouseEvent.CLICK, abrirJanelaStatus);
			gerenteOrigem_gerente.labelFunction = cmbGerentelblFunction;
			gerenteDestino_gerente.labelFunction = cmbGerentelblFunction;
			gerenteDestino_unidInst.labelFunction = cmbGerentelblFunction;
			gerenteDestino_cpfCnpj.labelFunction = cmbGerentelblFunction;
			unidInstOrigem_gerente.labelFunction = cmbPaclblFunction;
			unidInstOrigem_unidInst.labelFunction = cmbPaclblFunction;
			unidInstDestino_unidInst.labelFunction = cmbPaclblFunction;
			unidInstDestino_gerente.labelFunction = cmbPaclblFunction;
			unidInstDestino_cpfCnpj.labelFunction = cmbPaclblFunction;
			
			var lista : ArrayCollection = new ArrayCollection();
			lista.addItemAt("Gerente de Conta", GERENTE);
			lista.addItemAt("Unidade Institucional", UNIDADE);
			//lista.addItemAt("Grupo Econômico", GRUPO);
			lista.addItemAt("Núcleo", NUCLEO);
			lista.addItemAt("CPF/CNPJ", CPFCNPJ);
			
			tipoTransferencia.dataProvider = lista;
			tipoTransferencia.addEventListener("change", obterDefinicoesTipoTransferencia);
			
			gerenteOrigem_gerente.dataProvider = new ArrayCollection();
			gerenteDestino_gerente.dataProvider = new ArrayCollection();
			gerenteDestino_unidInst.dataProvider = new ArrayCollection();
			gerenteDestino_cpfCnpj.dataProvider = new ArrayCollection();
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			
			obterDefinicoesTipoTransferencia();
		}
		
		private function obterDefinicoesTipoTransferencia(evt:Event=null):void{
			dto = new RequisicaoReqDTO();
			dto.dados.tipo = tipoTransferencia.selectedIndex;
			servico.obterDefinicoes(dto);
		}
		
		private function mudarTipoTransferencia(evt:ResultEvent):void{
			MostraCursor.removeBusyCursor();
			switch(tipoTransferencia.selectedIndex){
				case GERENTE: 
					unidInstOrigem_gerente.dataProvider = evt.result.dados.unidades;	
					unidInstDestino_gerente.dataProvider = evt.result.dados.unidadesAtivas;
					preencherCombosGerente(gerenteOrigem_gerente);
					preencherCombosGerente(gerenteDestino_gerente);
					
					preencherCombosGerente(gerenteOrigem_gerente, evt.result.dados.gerentes); 
					preencherCombosGerente(gerenteDestino_gerente, evt.result.dados.gerentesAtivos); 
					break;		
				case UNIDADE: 
					unidInstOrigem_unidInst.dataProvider = evt.result.dados.unidades;	
					unidInstDestino_unidInst.dataProvider = evt.result.dados.unidadesAtivas;	
					preencherCombosGerente(gerenteDestino_unidInst, evt.result.dados.gerentesAtivos);
					break;		
//				case GRUPO: 
//					grupoEcoOrigem_grupoEco.dataProvider = evt.result.dados.grupos;	
//					grupoEcoDestino_grupoEco.dataProvider = evt.result.dados.grupos;	
//					break;		
				case NUCLEO: 
					nucleoOrigem_nucleo.dataProvider = evt.result.dados.nucleos;	
					nucleoDestino_nucleo.dataProvider = evt.result.dados.nucleos;	
					break;
				case CPFCNPJ:
					unidInstDestino_cpfCnpj.dataProvider = evt.result.dados.unidadesAtivas;
					nucleoDestino_cpfCnpj.dataProvider = evt.result.dados.nucleos;
					preencherCombosGerente(gerenteDestino_cpfCnpj);
					preencherCombosGerente(gerenteDestino_cpfCnpj, evt.result.dados.gerentesAtivos);
					break;
			}
			containerGerente.visible = tipoTransferencia.selectedIndex == 0;
			containerUnidadeInstitucional.visible = tipoTransferencia.selectedIndex == 1;
			containerGrupoEconomico.visible = tipoTransferencia.selectedIndex == 2;
			containerNucleo.visible = tipoTransferencia.selectedIndex == 3;
			containerCpfCnpj.visible = tipoTransferencia.selectedIndex == 4;
		}
		
		private function transferir(evt:MouseEvent):void{
			if(isCamposValidos()){
				dto.dados.tipo = tipoTransferencia.selectedIndex;
				servico.verificarTransferencias(dto);
			}
		}
		
		private function resultTransferir(evt:ResultEvent):void{		
			MostraCursor.removeBusyCursor();
			if(tipoTransferencia.selectedIndex != 4){
				if(evt.result.dados.total > 0){
					Alerta.show("Serão alterados " + evt.result.dados.total 
						+ " registros, confirma a operação?","Transferência de Informações",
						Alerta.ALERTA_PERGUNTA, null, transferirInformacoes);							
				} else {
					Alerta.show("Não foram encontrados dados para transferência.","Atenção",Alerta.ALERTA_OK);
				}
			}else{
				Alerta.show("Serão alterados " + contaCpfCnpj(txtAreaCpfCnpj.text).length
					+ " registros, confirma a operação?","Transferência de Informações",
					Alerta.ALERTA_PERGUNTA, null, transferirInformacoes);
			}
		}
		
		private function contaCpfCnpj(cpfCnpj:String):Array{
			var result:String = cpfCnpj;
			while(cpfCnpj.indexOf(",,") >= 0){
				result = cpfCnpj.replace(",,",",");
				cpfCnpj = result;
			}
			var lastChar:String = result.substr(-1);
			if(lastChar == ","){
				result = result.slice(0, cpfCnpj.length - 1);
			}
			
			txtAreaCpfCnpj.text = result;
			var tempArray:Array = result.split(",");
			return tempArray;
		}
		
		private function clearDelimeters(formattedString:String):String {     
			return formattedString.replace(/[\u000d\u000a\u0008\u0020]+/g,""); 
		}
		
		private function transferirInformacoes(evt:MouseEvent):void{
			servico.transferirInformacoes(dto);
		}
		
		private function resultTransferirInformacoes(evt:ResultEvent):void{		
			MostraCursor.removeBusyCursor();
			Alerta.show("Transferência solicitada com sucesso.","Transferência de Informações",Alerta.ALERTA_INFORMACAO);
		}
		
		private function isCamposValidos():Boolean{
			switch(tipoTransferencia.selectedIndex){
				case GERENTE: 
					return validarGerente();				
				case UNIDADE: 
					return validarUnidadeInstitucional();				
//				case GRUPO: 
//					return validarGrupoEconomico();				
				case NUCLEO: 
					return validarNucleo();	
				case CPFCNPJ:
					return validarCpfCnpj();
			}
			return false;
		}
		
		private function validarGerente():Boolean{
			if(unidInstOrigem_gerente.selectedIndex == 0){
				Alerta.show("Informe a Unidade Institucional de Origem.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(gerenteOrigem_gerente.selectedIndex == 0){
				Alerta.show("Informe o Gerente de Origem.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(unidInstDestino_gerente.selectedIndex == 0){
				Alerta.show("Informe a Unidade Institucional de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(gerenteDestino_gerente.selectedIndex == 0){
				Alerta.show("Informe o Gerente de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(gerenteOrigem_gerente.selectedItem.idFuncionario == gerenteDestino_gerente.selectedItem.idFuncionario){
				Alerta.show("Gerente de Origem e Destino devem ser diferentes.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			dto = new RequisicaoReqDTO();
			dto.dados.unidadeOrigem = unidInstOrigem_gerente.selectedItem;
			dto.dados.gerenteOrigem = gerenteOrigem_gerente.selectedItem;
			dto.dados.unidadeDestino = unidInstDestino_gerente.selectedItem;
			dto.dados.gerenteDestino = gerenteDestino_gerente.selectedItem;
			return true;
		}
		private function validarUnidadeInstitucional():Boolean{
			if(unidInstOrigem_unidInst.selectedIndex == 0){
				Alerta.show("Informe a Unidade Institucional de Origem.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(unidInstDestino_unidInst.selectedIndex == 0){
				Alerta.show("Informe a Unidade Institucional de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(gerenteDestino_unidInst.selectedIndex == 0){
				Alerta.show("Informe o Gerente de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			dto = new RequisicaoReqDTO();
			dto.dados.unidadeOrigem = unidInstOrigem_unidInst.selectedItem;
			dto.dados.unidadeDestino = unidInstDestino_unidInst.selectedItem;
			dto.dados.gerenteDestino = gerenteDestino_unidInst.selectedItem;
			return true;
		}
		private function validarGrupoEconomico():Boolean{
			if(grupoEcoOrigem_grupoEco.selectedIndex == 0){
				Alerta.show("Informe o Grupo Econômico de Origem.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(grupoEcoDestino_grupoEco.selectedIndex == 0){
				Alerta.show("Informe o Grupo Econômico de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(grupoEcoOrigem_grupoEco.selectedIndex == grupoEcoDestino_grupoEco.selectedIndex){
				Alerta.show("Grupo Econômico de Origem e Destino devem ser diferentes.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			dto = new RequisicaoReqDTO();
			dto.dados.idGrupoOrigem = grupoEcoOrigem_grupoEco.selectedItem.idGrupoEconomico;
			dto.dados.idGrupoDestino = grupoEcoDestino_grupoEco.selectedItem.idGrupoEconomico;
			return true;
		}
		private function validarNucleo():Boolean{
			if(nucleoOrigem_nucleo.selectedIndex == 0){
				Alerta.show("Informe o Núcleo de Origem.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(nucleoDestino_nucleo.selectedIndex == 0){
				Alerta.show("Informe o Núcleo de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(nucleoOrigem_nucleo.selectedIndex == nucleoDestino_nucleo.selectedIndex){
				Alerta.show("Núcleo de Origem e Destino devem ser diferentes.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			dto = new RequisicaoReqDTO();
			dto.dados.nucleoOrigem = nucleoOrigem_nucleo.selectedItem;
			dto.dados.nucleoDestino = nucleoDestino_nucleo.selectedItem;
			return true;
		}
		
		private function verificaSeTemDelimitador(texto:String):Boolean{
			if(texto.indexOf(",") > 0){
				return true;
			}
			return false;
		}
		
		private function validaCpfCnpj(array:Array):String{
			var tempVar:String = "";
			for (var i:int = 0; i < array.length; i++){
				if(isTamanhoCpfCnpjValido(array[i])){
					tempVar = array[i];
					return tempVar;
				}
			}
			return tempVar;
		}
		
		private function isTamanhoCpfCnpjValido(texto:String):Boolean{
			if(texto.length < 11 || (texto.length > 11 && texto.length < 14) || texto.length > 14){
				return true;
			}
			return false;
		}
		
		private function padronizaCampoCpfCnpj(texto:String):void{
			txtAreaCpfCnpj.text = clearDelimeters(texto);
		}	
		
		private function validarCpfCnpj():Boolean{
			
			if(txtAreaCpfCnpj.text == ""){
				Alerta.show("Informe a Lista de CPFs ou CNPJs.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			
			padronizaCampoCpfCnpj(txtAreaCpfCnpj.text);
			
			if(verificaSeTemDelimitador(txtAreaCpfCnpj.text)){
				var numCpfCnpjInvalido:String = validaCpfCnpj(contaCpfCnpj(txtAreaCpfCnpj.text));
				if(numCpfCnpjInvalido != ""){
					Alerta.show("O CPF/CNPJ " + numCpfCnpjInvalido +" é inválido.","Atenção",Alerta.ALERTA_OK);
					return false;
				}
			}else if(isTamanhoCpfCnpjValido(txtAreaCpfCnpj.text)){
					Alerta.show("O CPF/CNPJ " + txtAreaCpfCnpj.text +" é inválido.","Atenção",Alerta.ALERTA_OK);
					return false;
			}
			
			if(unidInstDestino_cpfCnpj.selectedIndex == 0){
				Alerta.show("Informe a Unidade Institucional de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			if(gerenteDestino_cpfCnpj.selectedIndex == 0){
				Alerta.show("Informe o Gerente de Destino.","Atenção",Alerta.ALERTA_OK);
				return false;
			}
			
			dto = new RequisicaoReqDTO();
			dto.dados.cpfCnpj = txtAreaCpfCnpj.text;
			dto.dados.unidadeDestino = unidInstDestino_cpfCnpj.selectedItem;
			dto.dados.gerenteDestino = gerenteDestino_cpfCnpj.selectedItem;
			dto.dados.nucleoDestino = nucleoDestino_cpfCnpj.selectedIndex != 0 ? nucleoDestino_cpfCnpj.selectedItem : null;
			return true;
		}
		
		private function cmbPaclblFunction(item: Object):String{
			return item.idUnidadeInst + " - " + item.nomeUnidade;
		}

		private function cmbGerentelblFunction(item: Object):String{
			return item.pessoa.pessoaCompartilhamento.nomePessoa;
		}

		private function preencherCombosGerente(combo:Combo,gerentes:Object=null):void{
			combo.enabled = gerentes != null;
			while(ListCollectionView(combo.dataProvider).length > 1){
				ListCollectionView(combo.dataProvider).removeItemAt(1);
			}
			if(gerentes != null){
				ListCollectionView(combo.dataProvider).addAll(gerentes as ArrayCollection);
			}
		}
		
		private function abrirJanelaStatus(evt:MouseEvent):void{
			janela.title = TITULO_JANELA_STATUS;
			janela.width = 950;
			janela.height = 620;
			janela.addChild(monitoracaoTransferencia as DisplayObject);
			janela.abrir(DisplayObject(Application.application), true);
		}

	}
}