package br.com.sicoob.capes.cadastrarCertidao{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.CertidaoVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarCertidao extends frmListarCertidaoView implements IListaPlataformaAtendimentoCAPES {
		
		private var listaCertidao:ArrayCollection;
		private static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.CertidaoFachada";

		private var servicoConsulta:ServicoJava;

		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmListarCertidao(){
			super();

			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {

			listaCertidao = new ArrayCollection();

			gridCertidao.dataProvider = listaCertidao;

			obterLista();
		}

		public function obterGrid():Grid{
			this.gridCertidao.validateNow();
			return this.gridCertidao;
		}

		public function obterLista():void {
			MostraCursor.setBusyCursor("Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);

			var dto:ConsultaDto = new ConsultaDto();
			dto.filtro = new CertidaoVO();
			CertidaoVO(dto.filtro).pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
					TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
					
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}

		private function retornoObterLista(event:ResultEvent):void{

			listaCertidao.list = event.result.dados["lista"];
			MostraCursor.removeBusyCursor();

			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}

		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			this.destino = destinoVO;
			servicoConsulta.configurarDestino(destinoVO);
		}
	}
}