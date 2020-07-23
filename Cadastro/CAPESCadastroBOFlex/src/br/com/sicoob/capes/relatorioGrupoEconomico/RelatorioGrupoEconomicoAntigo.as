package br.com.sicoob.capes.relatorioGrupoEconomico {
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	
	public class RelatorioGrupoEconomicoAntigo extends RelatorioGrupoEconomicoView {
		
		private var servico:ServicoJava = new ServicoJava();
		private var dataProviderSingular:ArrayCollection = new ArrayCollection();
		
		private static const DESTINO_CAPES : String = "destinoCAPES";
		
		public function RelatorioGrupoEconomicoAntigo() {
			include '../utils/RegistroClasses.as';
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario",FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo",NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao",FuncaoVO);
			
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
		}
		
		private function onCreateComplete(event:FlexEvent): void {
			servico = ServicoJavaUtil.getServico("br.com.sicoob.capes.cadastro.fachada.RelatorioGrupoEconomicoFachada");
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			
			btnGerarRelatorio.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			btnLimpar.addEventListener(MouseEvent.CLICK, acaoBtnLimpar);
			btnFechar.addEventListener(MouseEvent.CLICK, acaoBtnFechar);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoCAPESRecuperado);
		}
		
		protected function acaoBtnFechar(event:MouseEvent):void {
			var modulo:Modulo = this.parent.parent as Modulo;
			modulo.fecharJanela();
		}
		
		protected function acaoBtnLimpar(event:MouseEvent):void {
			componenteCentraisSingulares.limpar();
			campoCpfCnpj.limpar();
			dataFim.selectedDate = null;
			dataInicio.selectedDate = null;
			tipoRelatorio.selectedValue = "1";
		}
		
		private function onDestinoCAPESRecuperado(destino:DestinoVO):void {
			var modulo:Modulo = this.parent.parent as Modulo;
			modulo.destino = destino;
			servico.configurarDestino(destino);
			componenteCentraisSingulares.configurarDestino(destino);
			campoCpfCnpj.configurarDestino(destino);
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void{	
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			servico.mensagemEspera = "Obtendo Definições";
			servico.bloquearOperacao = true;
			servico.obterDefinicoes();
			return;
		}
		
		private function obterDefinicoesOnResult(event:ResultEvent):void {
			dataFim.selectedDate = null;
			dataInicio.selectedDate = null;
			MostraCursor.removeBusyCursor();
		}
		
		private function emitirRelatorio(event:MouseEvent):void{
			var modulo:Modulo = this.parent.parent as Modulo;
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.dataInicio = DateTimeBase.getDateTime(dataInicio.selectedDate);
			dto.dados.dataFim = DateTimeBase.getDateTime(dataFim.selectedDate);
			dto.dados.cpfCnpj = campoCpfCnpj.txtCodigo.text;
			dto.dados.idInstituicao = componenteCentraisSingulares.singular.idInstituicao;
			dto.dados.numCentral = componenteCentraisSingulares.central != null ? componenteCentraisSingulares.central.numeroCooperativa : null;
			dto.dados.numSingular = componenteCentraisSingulares.singular != null ? componenteCentraisSingulares.singular.numeroCooperativa : null;
			dto.dados.nomeGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.pessoa.pessoaCompartilhamento.nomePessoa : null;
			
			if(radioTodos.selected) {
				dto.dados.numTipoRegistro = 1;
			} else if(radioSomenteAutomaticos.selected) {
				dto.dados.numTipoRegistro = 2;
			} else {
				dto.dados.numTipoRegistro = 3;
			}
			dto.dados.numUnidade = componenteCentraisSingulares.unidade != null ? componenteCentraisSingulares.unidade.codigo : null;
			dto.dados.idNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.idNucleo : null;
			dto.dados.nomeNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.descricao : null;
			dto.dados.numGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.idFuncionario : null;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioGrupoEconomicoServicoRemote", dto, 
				"RelatorioGrupoEconomico", modulo.destino);
		}
		
	}
}