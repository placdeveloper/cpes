package {
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.input.Data;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CooperativaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.MapaTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.OrigemInformacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PeriodicidadeAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoBaixaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCapturaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoConsultaOrigemVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObservacaoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CooperativaAnotacaoPK;
	import br.com.sicoob.capes.relatorioAnotacao.RelatorioAnotacaoSelecaoView;

	public class RelatorioAnotacaoSelecao extends RelatorioAnotacaoSelecaoView {
		
		private static const CLASSE_SERVICO_RELATORIO : String =
			"br.com.sicoob.capes.relatorio.negocio.fachada.AnotacaoFachada";
		
		static private const SITUACAO_DEFAULT : String = FormatadorUtil.SITUACAO_ANOTACAO_VIGENTES;
		private var servicoDefinicoes : ServicoJava;
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function RelatorioAnotacaoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao",CategoriaAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.OrigemInformacao",OrigemInformacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao",PeriodicidadeAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao", TipoObservacaoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAnotacao",TipoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoCaptura",TipoCapturaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Anotacao", AnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoBaixa", TipoBaixaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao", MapaTipoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem", TipoConsultaOrigemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.CooperativaAnotacaoPK", CooperativaAnotacaoPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CooperativaAnotacao", CooperativaAnotacaoVO);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(event : FlexEvent) : void {
			this.servicoDefinicoes = ServicoJavaUtil.getServico(CLASSE_SERVICO_RELATORIO, 
				"Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			this.cpfCnpj.enabled = false;
			this.cpfCnpj.addEventListener(Event.CHANGE, onChangeCpfCnpj);
			this.cmbSituacao.selectedItem = SITUACAO_DEFAULT;
			this.cmbTipoPessoa.addEventListener(ListEvent.CHANGE, onChangeTipoPessoa);
			this.dataInicio.selectedDate = null;
			this.dataInicio.dataMaxima = obterDataAtual();
			this.dataInicio.addEventListener(Event.CHANGE, onChangeDataInicio);
			this.dataFim.selectedDate = null;
			this.dataFim.dataMaxima = obterDataAtual();
			this.btnGerar.addEventListener(MouseEvent.CLICK, botaoGerarPressionado);
			this.btnLimpar.addEventListener(MouseEvent.CLICK, botaoLimparPressionado);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			this.servicoDefinicoes.configurarDestino(destino);
			
			this.servicoDefinicoes.getOperation("obterDefinicoesRelatorio").send();
		}
		
		private function obterDataAtual() : Date {
			var data : Date = new Date();
			data.hours = 0;
			data.minutes = 0;
			data.seconds = 0;
			data.milliseconds = 0;
			return data;
		}
		
		private function isDataValida(input : Data) : Boolean {
			
			input.autoValidacao = false;
			var valido : Boolean = input.validar(montarDataMascara(input));
			input.autoValidacao = true;
			return valido;
		}
		
		private function botaoGerarPressionado(event : MouseEvent) : void {
			
			if (!isDataValida(this.dataInicio)) {
				Alerta.show(this.dataInicio.compMask.errorString, 'ATENÇÃO!', Alerta.ALERTA_ERRO, 
						this.dataInicio.compMask);
				return;
			}
			if (!isDataValida(this.dataFim)) {
				Alerta.show(this.dataFim.compMask.errorString, 'ATENÇÃO!', Alerta.ALERTA_ERRO, 
						this.dataFim.compMask);
				return;
			}
			
			executarSeValido(gerarRelatorio);
		}
		
		private function gerarRelatorio() : void {
			var tipoPessoa:TipoPessoaVO = this.cmbTipoPessoa.selectedItem as TipoPessoaVO;
			var tipoAnotacao:TipoAnotacaoVO = this.cmbTipoAnotacao.selectedItem as TipoAnotacaoVO;
			
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.idTipoPessoa = tipoPessoa != null ? tipoPessoa.codTipoPessoa : NaN;
			dto.dados.nomeTipoPessoa = tipoPessoa != null ? tipoPessoa.descTipoPessoa : null;
			dto.dados.idTipoAnotacao = tipoAnotacao != null ? tipoAnotacao.codTipoAnotacao : NaN;
			dto.dados.nomeTipoAnotacao = tipoAnotacao != null ? tipoAnotacao.descTipoAnotacao : null;
			dto.dados.cpfCnpj = StringUtil.trim(this.cpfCnpj.text);
			dto.dados.baixadas = this.cmbSituacao.valorSelecionado != null ? this.cmbSituacao.valorSelecionado == "true" : this.cmbSituacao.valorSelecionado;	
			dto.dados.dataInicio = this.dataInicio.selectedDate;
			dto.dados.dataFim = this.dataFim.selectedDate;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioAnotacaoServicoRemote", dto, 
				"CAPES_Relatorio_Anotacao", destino, "Emitindo relatório...", "PDF", false);
		}
		
		public function botaoLimparPressionado(event : MouseEvent) : void {
			
			this.cmbTipoPessoa.selectedItem = null;
			onChangeTipoPessoa();
			onChangeCpfCnpj();
			this.cmbTipoPessoa.dispatchEvent(new ListEvent(ListEvent.CHANGE));
			this.dataInicio.selectedDate = null;
			this.dataFim.selectedDate = null;
		}
		
		private function onChangeDataInicio(event : Event) : void {
			var textLength : int = StringUtils.trim(this.dataInicio.compMask.text).length;
			if ((textLength == 8) || (textLength == 0)) {
				this.dataFim.dataMinima = montarDataMascara(this.dataInicio);
			}
		}
		
		protected function montarDataMascara(input : Data):Date {
			var format:String = StringUtils.replace(
					StringUtils.replace(input.mascara, "/", ""), "-", "");
			return DateUtils.tratarBugData(
					DateUtils.stringToDate(
							StringUtils.trim(input.compMask.text),
							format));
		}

		private function onChangeCpfCnpj(event : Event = null) : void {
			if ((StringUtil.trim(this.cpfCnpj.text).length > 0) 
					&& (this.cpfCnpj.errorString == "")) {
				this.cmbTipoAnotacao.selectedItem = null;
				this.cmbTipoAnotacao.enabled = false;
				this.cmbSituacao.selectedItem = null;
				this.cmbSituacao.enabled = true;
			} else {
				this.cmbTipoAnotacao.selectedItem = null;
				this.cmbTipoAnotacao.enabled = true;
				this.cmbSituacao.selectedItem = SITUACAO_DEFAULT;
				this.cmbSituacao.enabled = false;
			}
		}
		
		private function onChangeTipoPessoa(event : ListEvent = null) : void {
			var valorCpfCnpj : String = StringUtil.trim(this.cpfCnpj.text);
			var tipoPessoa : TipoPessoaVO = TipoPessoaVO(this.cmbTipoPessoa.selectedItem);
			if (tipoPessoa) {
				this.cpfCnpj.enabled = true;
				if (tipoPessoa.codTipoPessoa == FormatadorUtil.TIPO_PESSOA_FISICA) {
					this.cpfCnpj.tipoCampo = InputCPFCNPJ.TIPO_CPF;
				} else {
					this.cpfCnpj.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
				}
			} else {
				this.cpfCnpj.text = "";
				this.cpfCnpj.tipoCampo = null;
				this.cpfCnpj.validateNow();
				this.cpfCnpj.enabled = false;
			}
			onChangeCpfCnpj();
		}
				
		/**
		 * Retorno dos dados da definição.
		 *  
		 * @param event O evento com o resultado do método.
		 */
		private function retornoObterDefinicoes(event: ResultEvent): void {
			
			this.cmbTipoPessoa.dataProvider = event.result.dados.tiposPessoa;
			this.cmbTipoAnotacao.dataProvider = event.result.dados.tiposAnotacao;
		}

	}
}