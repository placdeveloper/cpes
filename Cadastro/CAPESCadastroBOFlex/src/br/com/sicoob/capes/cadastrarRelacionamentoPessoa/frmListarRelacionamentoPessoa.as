package br.com.sicoob.capes.cadastrarRelacionamentoPessoa {
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.RelacionamentoPessoaVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarRelacionamentoPessoa extends frmListarRelacionamentoPessoaView 
			implements IListaPlataformaAtendimentoCAPES {
				
		private static const ABA_CEDIDOS : int = 0;
		private static const ABA_EXERCIDOS : int = 1;
		
		private static const CLASSE_SERVICO : String = 
				"br.com.sicoob.capes.cadastro.fachada.RelacionamentoPessoaFachada";
		public static const TAB_CHANGED : String = "abaChanged";
						
		private var _servicoConsulta : ServicoJava;
		private var _produtosBancoob : Boolean = false;
		
		[Bindable]
		private var relacionamentosCedidos : ArrayCollection = new ArrayCollection();
		private var relacionamentosExercidos : ArrayCollection = new ArrayCollection();
		
		public function frmListarRelacionamentoPessoa() {
			super();
			_servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo dados...", 
					ResultEvent.RESULT, onDadosRecuperados);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		public function isAbaCedidosSelecionada() : Boolean {
			return this.navegacaoTab.selectedIndex == ABA_CEDIDOS;
		}

		public function isAbaExercidosSelecionada() : Boolean {
			return this.navegacaoTab.selectedIndex == ABA_EXERCIDOS;
		}

		//-----------
		// Listeners
		//-----------
		private function init(evento : FlexEvent) : void {
			
			this.navegacaoTab.creationPolicy = "all";
			this.navegacaoTab.addEventListener(Event.CHANGE, onTabChanged);

			this.abaCedidos.gridRelacionamento.dataProvider = this.relacionamentosCedidos;
			this.abaExercidos.gridRelacionamento.dataProvider = this.relacionamentosExercidos;
			obterLista();
		}
		
		private function onDadosRecuperados(evento : ResultEvent) : void {
			this.relacionamentosCedidos.list = evento.result.dados.relacionamentosCedidos;
			this.relacionamentosExercidos.list = evento.result.dados.relacionamentosExercidos;
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}
		
		private function onTabChanged(evento : Event) : void {
			this.dispatchEvent(new Event(TAB_CHANGED));
		}
		
		//--------------------------------------------
		// Implementação: IListaPlataformaAtendimento
		//--------------------------------------------
		public function obterLista():void {
			var vo : RelacionamentoPessoaVO = new RelacionamentoPessoaVO();
			vo.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma()).pessoa;
			vo.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma()).pessoaCompartilhamento;
			var dto : ConsultaDto = new ConsultaDto();
			dto.filtro = vo;
			dto.valor = this.produtosBancoob;// verificar possibilidade de utilizar outro atributo
			servicoConsulta.getOperation("obterDadosSelecao").send(dto);
		}
		public function obterGrid():Grid {
			var grid : Grid = this.abaCedidos.gridRelacionamento;
			if (isAbaExercidosSelecionada()) {
				grid = this.abaExercidos.gridRelacionamento;
			}
			grid.validateNow();
			return grid;
		}

		//---------
		// get/set
		//---------
		public function get produtosBancoob() : Boolean {
			return _produtosBancoob;
		} 
		public function set produtosBancoob(valor:Boolean) : void {
			_produtosBancoob = valor;
		}

		//---------
		// Serviço
		//---------
		public function get servicoConsulta() : ServicoJava {
			return _servicoConsulta;
		}
	}
}