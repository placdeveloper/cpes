package br.com.sicoob.capes.cadastrarInformacaoProfissional
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class frmListarInfoProfissional extends frmListarInfoProfissionalView implements IListaPlataformaAtendimentoCAPES
	{
			
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
				
		public var servicoConsulta:ServicoJava;
		
		private var _produtosBancoob : Boolean = false;		
		private var listaInfoProfissional:ArrayCollection;
						
		public function frmListarInfoProfissional()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(InformacaoProfissionalSelecao.CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
						
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			listaInfoProfissional = new ArrayCollection();
			grdTrabalha.dataProvider = listaInfoProfissional;
			colunaSituacao.labelFunction = situacaoLabelFunction;
		}
		
		public function set produtosBancoob(value:Boolean):void {
			_produtosBancoob = value;
		}
		public function get produtosBancoob():Boolean {
			return _produtosBancoob;
		}		
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoConsulta.configurarDestino(destinoVO);
		}				
		
		public function obterLista():void
		{
			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var pessoa:PessoaPlataformaVO = TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma();
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idPessoa = pessoa.idPessoa;
			dto.dados.produtosBancoob = this.produtosBancoob;

            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);	
		}
				
		public function retornoObterLista(event:ResultEvent):void{
			listaInfoProfissional.list = event.result.dados["lista"];
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
		}		

		public function obterGrid():Grid
		{
			this.grdTrabalha.validateNow();
			return this.grdTrabalha;
		}
		
		private function situacaoLabelFunction(objeto:Object, coluna:DataGridColumn):String
		{
			var obj:Object = ReflectionUtils.recuperarPropriedade(objeto, recuperarNomePropriedade(coluna));
			
			var label:String = "";
			if (obj == 0) {
				label = "Inativo";
			} else if (obj == 1) {
				label = "Ativo";
			}
			return label;
		} 		
		
		public static function recuperarNomePropriedade(coluna:DataGridColumn):String {
			return (coluna is ColunaGrid && ColunaGrid(coluna).propriedade != null)
			? ColunaGrid(coluna).propriedade
				: coluna.dataField;
		}
		
	}
}