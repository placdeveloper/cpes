package {
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.util.vo.RelatorioVO;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.componentes.instituicao.vo.CentralSingularVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.relatoriotributacao.RelatorioTributacaoView;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class RelatorioTributacao extends RelatorioTributacaoView {

		private static const CLASSE_SERVICO_RELATORIO:String = "br.com.sicoob.capes.relatorio.negocio.fachada.RelatorioTributacaoFachada";
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_PESQUISAR:String = "Gerando relatório...";
		private static const OPERACAO_GERAR_RELATORIO:String = "gerarRelatorio";
		private static const DESTINO_CAPES:String = "destinoCAPES";

		private var servico:ServicoJava;

		
		public function RelatorioTributacao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as'
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO", CentralSingularVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.bancoob.negocio.relatorios.vo.RelatorioVO", RelatorioVO);
			
			servico = new ServicoJava();
			servico.bloquearOperacao = true;
			servico.source = CLASSE_SERVICO_RELATORIO;
			servico[OPERACAO_GERAR_RELATORIO].addEventListener(ResultEvent.RESULT, onResultGerarRelatorio);
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(evento : FlexEvent) : void {

			this.btGerar.addEventListener(MouseEvent.CLICK, onClickGerar);
			this.btLimpar.addEventListener(MouseEvent.CLICK, limpar);
			this.btFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoRecuperado);
		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			this.destino = destino;
			servico.configurarDestino(destino);
			componenteCentraisSingulares.configurarDestino(destino);
		}
		
		private function onClickGerar(event:MouseEvent):void {
			executarSeValido(gerar);
		}
		
		private function gerar():void {
			
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.central = componenteCentraisSingulares.central != null ? componenteCentraisSingulares.central.numeroCooperativa : null;
			dto.dados.singular = componenteCentraisSingulares.singular != null ? componenteCentraisSingulares.singular.numeroCooperativa : null;
			dto.dados.unidade = componenteCentraisSingulares.unidade != null ? componenteCentraisSingulares.unidade.codigo : null;
			dto.dados.gerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.idFuncionario : null;
			dto.dados.nomeGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.pessoa.pessoaCompartilhamento.nomePessoa : null;
			dto.dados.nucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.idNucleo : null;
			dto.dados.nomeNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.descricao : null;
			dto.dados.ordenacao = radioGrupoOrdenacao.selectedValue;
			dto.dados.iof = radioGrupoIOF.selectedValue != "Todos" ? radioGrupoIOF.selectedValue : null;
			dto.dados.ir = radioGrupoIR.selectedValue != "Todos" ? radioGrupoIR.selectedValue : null;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioTributacaoServicoRemote", dto, 
				"RelatorioTributacao", destino);
		}
		
		private function onResultGerarRelatorio(event:ResultEvent):void {
			limpar();
		}
		
		private function limpar(evento:MouseEvent = null):void {
			componenteCentraisSingulares.limpar();
			radioGrupoIOF.selectedValue = null;
			radioGrupoIR.selectedValue = null;
			radioGrupoOrdenacao.selectedValue = "nome";
		}
		
		private function fechar(evento:MouseEvent = null):void {
			pegaJanela().fecharJanela();
		}
	}
}