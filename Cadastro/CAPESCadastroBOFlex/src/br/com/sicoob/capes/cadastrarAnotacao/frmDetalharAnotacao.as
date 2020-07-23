package br.com.sicoob.capes.cadastrarAnotacao
{
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.componentes.tabelapaginada.TabelaPaginadaUtils;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.portal.spool.ISpoolTemporarios;
	import br.com.bancoob.sisbr.portal.spool.SpoolTemporariosFactory;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DefinicaoDTO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmDetalharAnotacao extends frmDetalharAnotacaoView implements ITelaBasePlataformaAtendimento {

		static private const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDados";
		static private const TIPO_DATA: int = 1;
		static private const TIPO_CNPJ: int = 2;
		static private const TIPO_VALOR: int = 3;
		static private const TIPO_INTEIRO: int = 4;
			
		public var servicoDetalhe:ServicoJava;
		public var servicoRelatorio:ServicoJava;
		private var listaDetalheAnotacao:ArrayCollection = new ArrayCollection();
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmDetalharAnotacao() {
			super();
			servicoDetalhe = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO);
			servicoDetalhe.addEventListener(ResultEvent.RESULT, retornoDetalhar);
			servicoRelatorio = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO_RELATORIO);
			servicoRelatorio.addEventListener(ResultEvent.RESULT, retornoObterRelatorio);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
	    
		private function init(evt:FlexEvent=null):void {
			gridDetalhes.dataProvider = listaDetalheAnotacao;
		}
        
		public function obterGrid():Grid {
			this.gridDetalhes.validateNow();
			return this.gridDetalhes;
		}	
		
		public function detalhar(anotacao: AnotacaoVO):void {
			
			gridDetalhes.dataProvider = null;
			MostraCursor.setBusyCursor(
					"Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.anotacao = anotacao;
            servicoDetalhe.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);	
		}

		/**
		 * Retorno dos dados da definição.
		 *  
		 * @param event O evento com o resultado do método.
		 */
		public function retornoDetalhar(event: ResultEvent): void {

			listaDetalheAnotacao.list = event.result.dados["detalhes"];
			var listaDefinicoes: ArrayCollection = event.result.dados["definicoes"];
			
			definirColunas(listaDefinicoes);
			gridDetalhes.dataProvider = listaDetalheAnotacao;
			gridDetalhes.selectedIndex = -1;
			gridDetalhes.selectable = false;					
			MostraCursor.removeBusyCursor();
							
		}	
		       
		private function definirColunas(listaDefinicoes: ArrayCollection): void {
			var arrCols:Array = new Array();
              
			for each (var definicao:DefinicaoDTO in listaDefinicoes) {
				var coluna:ColunaGrid = obterColunaGrid(definicao);
				arrCols.push(coluna);
			}
			gridDetalhes.columns = arrCols;
		}
		
		private function obterColunaGrid(definicao:DefinicaoDTO): ColunaGrid {
			
			var campoDado: String = definicao.nomeAtributo; 
			var cabecalho: String = definicao.cabecalho;
			var largura: int = definicao.largura;
			var tipo: int = definicao.tipo;
			
			var funcao: Function = TabelaPaginadaUtils.defaultLabelFunction;
			var alinhamento: String = "left";
			
			if(tipo == TIPO_DATA) {
				funcao = TabelaPaginadaUtils.defaultDateLabelFunction;
				alinhamento = "center";
			} else if(tipo == TIPO_CNPJ) {
				funcao = TabelaPaginadaUtils.defaultCNPJLabelFunction;
				alinhamento = "center";
			} else if(tipo == TIPO_VALOR) {
				funcao = TabelaPaginadaUtils.defaultValorNumericoLabelFunction;
				alinhamento = "right";
			} else if(tipo == TIPO_INTEIRO) {
				funcao = defaultInteriroFunction;
				alinhamento = "left";
			}
			
			var coluna:ColunaGrid = new ColunaGrid();
			coluna.setStyle("textAlign", alinhamento);
            coluna.dataField = campoDado;
            coluna.headerText = cabecalho;
            coluna.labelFunction = funcao;
            if(largura > 0) {
            	coluna.width = largura;
            }
			
			if(definicao.nomeAtributo == "observacao") {
				coluna.dataTipField = campoDado;
				coluna.showDataTips = true;
			}
            return coluna;
		}

        public static function defaultInteriroFunction(objeto:Object, coluna:DataGridColumn):String {           			

			var campoDado: String = coluna.dataField;
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, campoDado);
			var numero: Number = Number(valor);

			return (valor == null || isNaN(numero)) ? "" : String(valor);
        }
		
		public function obterRelatorioDetalheAnotacao(idConsulta:Number): void {
			MostraCursor.setBusyCursor("Obtendo detalhes da consulta!", Application.application, MostraCursor.CURSOR_PESQUISAR);
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idConsulta = idConsulta;
			servicoRelatorio.getOperation("obterRelatorioDetalheAnotacao").send(dto);
		}
		
		public function retornoObterRelatorio(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			
			var ispool: ISpoolTemporarios = SpoolTemporariosFactory.getSpool("arquivosTemporariosCAPES");
			var nome:String = event.result.dados.nomeArquivoPDF as String;
			
			ispool.gravarItem(event.result.dados.arquivoPDF as ByteArray, nome);
			ispool.abrirItem(nome);
		}

		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
	}
}