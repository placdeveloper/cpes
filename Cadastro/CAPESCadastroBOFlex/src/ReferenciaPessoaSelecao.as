package
{
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarReferenciaPessoa.frmEditarReferenciaPessoa;
	import br.com.sicoob.capes.cadastrarReferenciaPessoa.frmListarReferenciaPessoa;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.vo.ContaVO;
	import br.com.sicoob.capes.comum.vo.entidades.ReferenciaBancariaVO;
	import br.com.sicoob.capes.comum.vo.entidades.ReferenciaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TelefoneBaseVO;
	import br.com.sicoob.capes.comum.vo.entidades.TelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoReferenciaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoTelefoneVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.Container;
	
	public class ReferenciaPessoaSelecao extends TelaPlataformaAtendimentoCAPESBase
	{

		private var telaLista:frmListarReferenciaPessoa = new frmListarReferenciaPessoa();
		private var telaEdicao:frmEditarReferenciaPessoa = new frmEditarReferenciaPessoa();

		public function ReferenciaPessoaSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ContaVO", ContaVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria", ReferenciaBancariaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TelefoneBase", TelefoneBaseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoaContato", TipoPessoaContatoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoReferencia", TipoReferenciaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoTelefone", TipoTelefoneVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Referencia", ReferenciaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Telefone", TelefoneVO);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}

		private function init(evt:Event):void {

			this.setTelaLista(telaLista);
			this.setTelaEdicao(telaEdicao);

			this.textoAcao = "REFERÊNCIA PESSOA";
			this.textoModulo = "REFERÊNCIAS DE PESSOA";
			this.iconModulo = "br/com/bancoob/imagens/icos/opnbr_32.png";
			this.textoOpcoes = "OPÇÕES";
			this.iconOpcoes = "br/com/bancoob/imagens/icos/opts_24.png";

		}

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			
			telaLista.servicoConsulta.configurarDestino(destinoVO);
			
			telaEdicao.servicoConsulta.configurarDestino(destinoVO);
			telaEdicao.servicoDefinicao.configurarDestino(destinoVO);
			telaEdicao.servicoSalvar.configurarDestino(destinoVO);
			telaEdicao.servicoExcluir.configurarDestino(destinoVO);
			telaEdicao.servicoListaTelefone.configurarDestino(destinoVO);
		}

		override protected function habilitarControles(cont:Container):void {
			super.habilitarControles(cont);

			telaEdicao.dataCadastro.enabled = false;
		}
		
		public static function defaultDDDLabelFunction(objeto:Object, coluna:DataGridColumn):String {     
        			
			var obj:Object = ReflectionUtils.recuperarPropriedade(objeto, recuperarNomePropriedade(coluna));

			var retorno:String = "";
			if(obj is Number 
				&& (obj as Number) != 0
				&& !isNaN((obj as Number))) {
				
				retorno = (String(obj));
			}
			 					
            return retorno;  
        } 		
        
		public static function recuperarNomePropriedade(coluna:DataGridColumn):String {
			return (coluna is ColunaGrid && ColunaGrid(coluna).propriedade != null)
					? ColunaGrid(coluna).propriedade
					: coluna.dataField;
		}
		
		protected override function registroExcluido(event : Event) : void {
			super.registroExcluido(event);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
		}
        
	}
}