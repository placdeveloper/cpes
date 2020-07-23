package {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.FuncionalidadeValidacaoCadastralEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRegraValidacaoCadastralVO;
	import br.com.sicoob.capes.comum.vo.entidades.ValidacaoCadastralRegraVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.ValidacaoCadastral;
	import br.com.sicoob.capes.relatorioValidacaoCadastral.RelatorioValidacaoCadastralView;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class RelatorioValidacaoCadastral extends RelatorioValidacaoCadastralView {
		
		private static const CLASSE_SERVICO_RELATORIO:String = "br.com.sicoob.capes.relatorio.negocio.fachada.RelatorioValidacaoCadastralFachada";
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_PESQUISAR:String = "Gerando relatório...";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const OPERACAO_PESQUISAR:String = "pesquisar";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var servico:ServicoJava;
		
		public function RelatorioValidacaoCadastral() {
			super();
			
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem", TipoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral", TipoRegraValidacaoCadastralVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum", FuncionalidadeValidacaoCadastralEnum);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra", ValidacaoCadastralRegraVO);
			
			servico = new ServicoJava();
			servico.source = CLASSE_SERVICO_RELATORIO;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		protected function iniciar(evento : FlexEvent) : void {
			
			botaoPesquisar.addEventListener(MouseEvent.CLICK, pesquisar);
			botaoLimpar.addEventListener(MouseEvent.CLICK, limpar);
			botaoFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			comboFuncionalidade.addEventListener(Event.CHANGE, obterTipoErros);
			grupoTipo.addEventListener(Event.CHANGE, bloquearCampos);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			servico.configurarDestino(destino);
			componenteCentraisSingulares.configurarDestino(destino);
			campoCpfCnpj.configurarDestino(destino);
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoes_resultado);
			servico.mensagemEspera = MENSAGEM_OBTER_DEFINICOES;
			servico.bloquearOperacao = true;
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		private function obterDefinicoes_resultado(evento:ResultEvent):void {
			if(evento.result != null && evento.result.dados != null) {
				comboRestricao.dataProvider = evento.result.dados.tipoRegras;
				comboFuncionalidade.dataProvider = evento.result.dados.funcionalidades;
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function bloquearCampos(evento:Event = null):void {
			if(grupoTipo.selectedValue == ValidacaoCadastral.TIPO_RELATORIO_SINTETICO){
				campoCpfCnpj.enabled = false;
				comboFuncionalidade.enabled = false;
				comboTipoErro.enabled = false;
				
				componenteCentraisSingulares.bloquearComboUnidade(false);
				componenteCentraisSingulares.bloquearComboNucleo(false);
				componenteCentraisSingulares.bloquearComboGerente(false);
			} else {
				campoCpfCnpj.enabled = true;
				comboFuncionalidade.enabled = true;
				comboTipoErro.enabled = true;
				
				componenteCentraisSingulares.bloquearComboUnidade(false);
				componenteCentraisSingulares.bloquearComboNucleo(false);
				componenteCentraisSingulares.bloquearComboGerente(false);
			}
		}
		
		private function obterTipoErros(evento:Event = null): void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var funcionalidade:FuncionalidadeValidacaoCadastralEnum = comboFuncionalidade.selectedItem as FuncionalidadeValidacaoCadastralEnum;
			dto.dados.funcionalidade = funcionalidade;
			
			servico.addEventListener(ResultEvent.RESULT, retorno_obter_tipos_erro);
			servico.mensagemEspera = MENSAGEM_OBTER_DEFINICOES;
			servico.bloquearOperacao = true;
			servico.getOperation("obterTipoErros").send(dto);
		}
		
		private function retorno_obter_tipos_erro(evento:ResultEvent):void {
			if(evento.result != null && evento.result.dados != null) {
				FlexUtil.atualizarCombo(comboTipoErro, evento.result.dados.listaTipoErros);
			}else {
				FlexUtil.atualizarCombo(comboTipoErro, new ArrayCollection());
			}
			
			FlexUtil.adicionarItemOpcional(comboTipoErro);
			comboTipoErro.selectedIndex = 0;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function pesquisar(evento:MouseEvent = null):void {
			if(grupoTipo.selectedValue != null) {
				
				var nomeRelatorio:String = "";
				var dto:ParametroDTO = new ParametroDTO();
				
				if(grupoTipo.selectedValue == ValidacaoCadastral.TIPO_RELATORIO_ANALITICO) {
					if(componenteCentraisSingulares.singular == null || isNaN(componenteCentraisSingulares.singular.numeroCooperativa)){
						Alerta.show("Por favor, informe a instituição.", "Aviso", Alerta.ALERTA_ERRO);
						return;
					} else {
						dto.dados.nomeNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.descricao : null;
						dto.dados.nomeGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.pessoa.pessoaCompartilhamento.nomePessoa : null;
						dto.dados.cpfCnpj = campoCpfCnpj.txtCodigo.text;
						dto.dados.funcionalidade = comboFuncionalidade.selectedItem != null ? comboFuncionalidade.selectedItem.name : null;
						dto.dados.tipoErro = comboTipoErro.selectedItem != null ? comboTipoErro.selectedItem.codigoRegra : null;
						dto.dados.descricaoErro = comboTipoErro.selectedItem != null ? comboTipoErro.selectedItem.descricao : null;
						
						nomeRelatorio = ValidacaoCadastral.NOME_RELATORIO_ANALITICO;
					}
				} else if(grupoTipo.selectedValue == ValidacaoCadastral.TIPO_RELATORIO_SINTETICO){
					if(componenteCentraisSingulares.central == null || isNaN(componenteCentraisSingulares.central.numeroCooperativa)){
						Alerta.show("Por favor, informe a central.", "Aviso", Alerta.ALERTA_ERRO);
						return;
					} else {
						nomeRelatorio = ValidacaoCadastral.NOME_RELATORIO_SINTETICO;
					}
				}
				
				dto.dados.nucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.idNucleo : null;
				dto.dados.gerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.idFuncionario : null;
				
				dto.dados.central = componenteCentraisSingulares.central != null ? componenteCentraisSingulares.central.numeroCooperativa : null;
				dto.dados.singular = componenteCentraisSingulares.singular != null ? componenteCentraisSingulares.singular.numeroCooperativa : null;
				dto.dados.idInstituicao = componenteCentraisSingulares.singular.idInstituicao;
				dto.dados.unidade = componenteCentraisSingulares.unidade != null ? componenteCentraisSingulares.unidade.codigo : null;
				dto.dados.tipo = grupoTipo.selectedValue;
				dto.dados.restricao = comboRestricao.selectedItem != null ? comboRestricao.selectedItem.codigo : null;
				
				RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioValidacaoCadastralServicoRemote", dto,	nomeRelatorio, destino);
			}
		}
		
		private function limpar(evento:MouseEvent = null):void {
			componenteCentraisSingulares.limpar();
			grupoTipo.selectedValue = ValidacaoCadastral.TIPO_RELATORIO_ANALITICO;
			campoCpfCnpj.limpar(); 
			comboFuncionalidade.selectedIndex = 0;
			comboTipoErro.selectedIndex = 0;
			comboRestricao.selectedIndex = 0;
			
			if(comboTipoErro.enabled){
				(comboTipoErro.dataProvider as ArrayCollection).removeAll();
			}
			bloquearCampos();
		}
		
		private function fechar(evento:MouseEvent = null):void {
			pegaJanela().fecharJanela();
		}
	}
}