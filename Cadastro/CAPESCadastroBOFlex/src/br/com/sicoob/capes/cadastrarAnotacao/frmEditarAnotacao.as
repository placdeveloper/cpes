package br.com.sicoob.capes.cadastrarAnotacao
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class frmEditarAnotacao extends frmEditarAnotacaoView implements ITelaBasePlataformaAtendimento {

		static public const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.AnotacaoFachada";
		static public const CLASSE_SERVICO_RELATORIO: String = 
				"br.com.sicoob.capes.relatorio.negocio.fachada.AnotacaoFachada";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_OBTER_TIPO_ANOTACAO: String = "obterTipoAnotacao";
		static private const OPERACAO_OBTER_OBSERVACAO_ANOTACAO: String = "obterObservacaoAnotacao";

		private var servicoEdicao:ServicoJava;
		private var servicoConsulta:ServicoJava;
		private var listaTipoAnotacoes :ArrayCollection = new ArrayCollection();
		
	    /**
	     *  Construtor.
	     */
		public function frmEditarAnotacao(){
			super();
			servicoConsulta = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterTipoAnotacao);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterObservacaoAnotacao);
			
			servicoEdicao = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoEdicao);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
        /**
         * Eventos iniciais.
         * 
         * @param Passa um evento do tipo FlexEvent.
         */
		public function init(evt:FlexEvent):void {
		
			this.cmbTipoAnotacao.dataProvider = listaTipoAnotacoes;
			cmbTipoAnotacao.labelFunction = comboCodigoDescricao;
			cmbTipoAnotacao.addEventListener(ListEvent.CHANGE, obterTipoAnotacao);
			cboTipoExposicao.addEventListener(ListEvent.CHANGE, selecionarTipoExposicao);
		}

		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
		public function novoRegistro():void {
			
			if(listaTipoAnotacoes.length == 1) {
				obterDefinicoes();	
			}
			
			cmbTipoAnotacao.selectedIndex = -1;
			categoria.text = "";
			dtOcorrencia.selectedDate = dtOcorrencia.dataDefault;
			dtAnotacao.selectedDate = dtAnotacao.dataDefault;
			dtAnotacao.enabled = false;
			quantidade.text = "";
			valorAnotacao.text = "";
			observacao.text = "";
			cboTipoExposicao.selectedIndex = -1;
			cboTipoExposicao.enabled = false;
			
		}
		
		public function gravarRegistro():void {
			executarSeValido(gravarDados);
		}

	    //--------------------------------------------------------------------------
	    //  Métodos de auxiliares.
	    //--------------------------------------------------------------------------	
		private function obterDefinicoes(): void {
			MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
			servicoConsulta.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		private function obterTipoAnotacao(obj: Object): void {
						
			if (cmbTipoAnotacao.selectedIndex != 0) {				
			
				var tipoAnotacao: TipoAnotacaoVO = TipoAnotacaoVO(cmbTipoAnotacao.selectedItem);
				
				if(tipoAnotacao != null ) {
					MostraCursor.setBusyCursor("Obtendo definições ...", 
							Application.application, MostraCursor.CURSOR_PESQUISAR);
					var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
					dto.dados.tipoAnotacao = tipoAnotacao;
					servicoConsulta.getOperation(OPERACAO_OBTER_TIPO_ANOTACAO).send(dto);
				} 				
			} else {
				categoria.text = "";
			}		
		}			
		
		private function selecionarTipoExposicao(event:Event=null):void {	
			observacao.text = cboTipoExposicao.selectedLabel;
		}
								
		private function gravarDados(): void {
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			var anotacao: AnotacaoVO = new AnotacaoVO();
			
			if (cmbTipoAnotacao.selectedIndex != 0) {				
				anotacao.tipoAnotacao = TipoAnotacaoVO(cmbTipoAnotacao.selectedItem);
			}
			
			anotacao.descObservacao = observacao.text;
			anotacao.dataHoraAnotacao = DateTimeBase.getDateTimeEntity(dtAnotacao.selectedDate);
			anotacao.dataHoraOcorrencia = DateTimeBase.getDateTimeEntity(dtOcorrencia.selectedDate);
			anotacao.valor = valorAnotacao.valor;
			anotacao.quantidade = quantidade.valor; 
			anotacao.pessoaCompartilhamento = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();

			if(cboTipoExposicao.enabled) {
				anotacao.codigoTipoExposicao = cboTipoExposicao.selectedItem.codigo;
			}
			dto.dados.anotacao = anotacao;
			
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application, 
					MostraCursor.CURSOR_GRAVAR);
			servicoEdicao.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
									
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos de callback.
	    //--------------------------------------------------------------------------				
		private function retornoObterDefinicoes(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			if(event.result.dados.tiposAnotacao != null) {
				cmbTipoAnotacao.dataProvider = event.result.dados.tiposAnotacao;	
			}
		}		
		
		private function retornoObterObservacaoAnotacao(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			if(event.result.dados.listaObservacaoAnotacao != null) {
				cboTipoExposicao.dataProvider = event.result.dados.listaObservacaoAnotacao;	
			}
		}
		
		private function retornoObterTipoAnotacao(event: ResultEvent): void {
			
			MostraCursor.removeBusyCursor();
			var tipoAnotacao: TipoAnotacaoVO = event.result.dados.tipoAnotacao;
			
			if(tipoAnotacao != null) {
				categoria.text = tipoAnotacao.categoriaAnotacao.descCategoriaAnotacao;
				
				quantidade.enabled = quantidade.validarObrigatorio = tipoAnotacao.registraQuantidade.valor;
				valorAnotacao.enabled = valorAnotacao.validarObrigatorio = tipoAnotacao.registraValor.valor;
				
				if(!quantidade.enabled){
					quantidade.text = "";
				}
				
				if(!valorAnotacao.enabled){
					valorAnotacao.text = "";
				}
				
				var observacaoPreCadastrada: Boolean = tipoAnotacao.tipoObservacaoAnotacao.observacaoPreCadastrada.valor; 
				observacao.enabled = !observacaoPreCadastrada;
				cboTipoExposicao.enabled = observacaoPreCadastrada;
				cboTipoExposicao.validarObrigatorio = cboTipoExposicao.enabled;
				
				if(!observacaoPreCadastrada) {
					cboTipoExposicao.selectedIndex = -1;
					observacao.text = '';
				} else {
					MostraCursor.setBusyCursor("Obtendo definições ...", 
						Application.application, MostraCursor.CURSOR_PESQUISAR);
					var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
					dto.dados.codigoTipoObservacaoAnotacao = tipoAnotacao.tipoObservacaoAnotacao.codigo;
					servicoConsulta.getOperation(OPERACAO_OBTER_OBSERVACAO_ANOTACAO).send(dto);
				}
			}
		}		
		
		private function gerarRelatorio(event: ResultEvent): void {
			var dto:ParametroDTO = new ParametroDTO();
			var anotacao:AnotacaoVO = event.result.dados.anotacao as AnotacaoVO;
			dto.dados.idAnotacao = anotacao.idAnotacao;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioProvaVidaServicoRemote", dto, "Relatorio_Prova_Vida", this.destino, "EMITINDO RELATÓRIO...", "PDF", false);
		}
		
		private function retornoEdicao(event: ResultEvent): void {

			MostraCursor.removeBusyCursor();
			
			if (event.result.dados.imprimirRelatorio) {
				gerarRelatorio(event);				
			}

	  		this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}				

		public static function comboCodigoDescricao(item:Object=null):String {
			var codigo: String = item.codTipoAnotacao;
			var descricao: String = item.descTipoAnotacao;
			return codigo + " - " + descricao.toUpperCase();
		}
		
		public function verificarAlteracoes():Boolean {
			return true;
		}
		
		public function configurarDestinos(destino:DestinoVO):void {
			if(destino != null) {
				this.destino = destino;
				servicoConsulta.configurarDestino(destino); 
				servicoEdicao.configurarDestino(destino);
			}
		}
		
	}

}