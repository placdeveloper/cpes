package  br.com.sicoob.capes.cadastroGrupoCompartilhamento.cadastrarGrupoCompartilhamento {
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.toolTipUtil.ToolTipUtil;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class GrupoCompartilhamentoEdicao extends GrupoCompartilhamentoEdicaoView {
		
		private var _grupoSelecionado:Object;
		private var servico:ServicoJava = new ServicoJava();

		private var winConfirm:WindowConfirmaSelecao;
		public var isHouveAlteracao:Boolean;

		private var sort:Sort;

		private var sortfieldNumero:SortField;

		private var listGrupoCompartilhamento:ArrayCollection = new ArrayCollection();
		
		private var _destino:DestinoVO;
		
		[Bindable]
		private var listSelecionados:ArrayCollection;
		
		public function GrupoCompartilhamentoEdicao() {
			super();
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			
			sort = new Sort();
			sortfieldNumero = new SortField("numero", false,false,true);
			sort.fields = [sortfieldNumero];						
		}
		
		private function init(event:FlexEvent):void {
			servico.source = "br.com.sicoob.capes.cadastro.fachada.GrupoCompartilhamentoFachada";
			servico.listarInstuicoesSemGrupo.addEventListener(ResultEvent.RESULT, listarInstituicoesOnResult);
			servico.incluirDados.addEventListener(ResultEvent.RESULT, incluirDadosOnResult);
			
			btnCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnSalvar.addEventListener(MouseEvent.CLICK, confirmaSalvar);
			
			toolTipUtil.targets = [btnSalvar];
			
			gridListar.dataProvider = listGrupoCompartilhamento;
			columnNumCnpj.labelFunction = labelCnpj;
			
			listarInstuicoesSemGrupo();
		}
		
		private function labelCnpj(item:Object, column:DataGridColumn):String
		{
			if(item.hasOwnProperty("numCNPJ")){
				return FormatUtil.formataCPFCNPJ(String(item.numCNPJ));
			}else{
				return "";
			}
		}
		
		private function incluirDadosOnResult(event:ResultEvent):void
		{
			MostraCursor.removeBusyCursor();
			isHouveAlteracao = true;
			fechar();
		}
		
		public function listarInstuicoesSemGrupo():void{
			MostraCursor.setBusyCursor("Buscando Instituições",this,MostraCursor.CURSOR_PESQUISAR); 
			servico.listarInstuicoesSemGrupo(new RequisicaoReqDTO());
			isHouveAlteracao = false;
		}
		
		private function listarInstituicoesOnResult(event:ResultEvent):void
		{	
			listGrupoCompartilhamento.list = event.result.dados.listaGrupoCompartilhamento;
			listGrupoCompartilhamento.sort = sort;
			MostraCursor.removeBusyCursor();
		}
		
		protected function confirmaSalvar(event:MouseEvent):void
		{
			//Filtrar instituições selecionadas
			listSelecionados = ObjectUtil.copy(gridListar.dataProvider) as ArrayCollection;
			listSelecionados.filterFunction = filterSelecionados;
			listSelecionados.refresh();
			if(listSelecionados.length>0){
				//Exibe alerta com instituições selecionadas
				winConfirm = new WindowConfirmaSelecao();
				PopUpManager.addPopUp(winConfirm ,this,true);
				winConfirm.funcSim = salvar;
				winConfirm.gridListar.dataProvider = listSelecionados;
				winConfirm.btSim.setFocus();
			}else{				
				btnSalvar.errorString = "Selecione ao menos uma Instituição!";
				toolTipUtil.posicionar(ToolTipUtil.ACIMA);
			}
		}
		
		private function filterSelecionados(item:Object):Boolean
		{
			if(item.selecionado)
				return true;
			
			return false;
		}
		
		private function salvar(event:Event=null):void{
			MostraCursor.setBusyCursor("Salvando...", this, MostraCursor.CURSOR_GRAVAR);
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var listGrupo:Array = new Array();
			//,idGrupoCompartilhamento:listSelecionados.getItemAt(i).idGrupoCompartilhamento}
			for(var i:uint = 0;i<listSelecionados.length;i++){
				listGrupo.push(listSelecionados.getItemAt(i).idInstituicao);
			}
			req.dados.instituicoes = listGrupo;
			req.dados.idGrupoCompartilhamento = grupoSelecionado.codigo;
			servico.incluirDados(req);
		}
		
		protected function fechar(event:MouseEvent=null):void
		{
			fecharJanela();
		}
		
		protected function cancelar(event:MouseEvent):void
		{
			//Desmarcar todas as instituições
			for(var i:uint=0;i<gridListar.dataProvider.length;i++){
				gridListar.dataProvider.getItemAt(i).selecionado = false;
			}
			gridListar.dataProvider.refresh();
		}
		
		public function get grupoSelecionado():Object
		{
			return _grupoSelecionado;
		}

		public function set grupoSelecionado(value:Object):void
		{
			_grupoSelecionado = value;
			labelTitulo.texto =  "Vincular Instituições ao Grupo Compartilhamento "+value.descricao;
			labelSubTitulo.text = "Selecione uma ou várias Instituições para serem vinculadas ao Grupo "+value.descricao;
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
		}
	}
}