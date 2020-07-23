package
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.sisbr.Constantes;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarFonteRenda.frmEditarFonteRenda;
	import br.com.sicoob.capes.cadastrarFonteRenda.frmListarFonteRenda;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.vo.FonteRendaProxy;
	import br.com.sicoob.capes.comum.vo.entidades.FonteRendaBaseVO;
	import br.com.sicoob.capes.comum.vo.entidades.FonteRendaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmpresaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFonteRendaVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	public class FonteRendaSelecao extends TelaPlataformaAtendimentoCAPESBase {

		private var telaLista:frmListarFonteRenda = new frmListarFonteRenda();
		private var telaEdicao:frmEditarFonteRenda = new frmEditarFonteRenda();

		private var pessoa:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();

		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.FonteRendaFachada";

		public function FonteRendaSelecao() {
			super();
			registrarClasses();
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}

		private function registrarClasses(): void {
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';		
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda", FonteRendaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.FonteRendaBase", FonteRendaBaseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoEmpresa",	TipoEmpresaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFonteRenda",	TipoFonteRendaVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.proxies.FonteRendaProxy", FonteRendaProxy);
		}
		
		private function init(evt:Event):void {

			this.setTelaLista(telaLista);
			this.setTelaEdicao(telaEdicao);

			carregarTextos();
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaEdicao.dispose();
			telaEdicao = null;
		}
		
		private function carregarTextos(): void {
			this.iconModulo = "br/com/bancoob/imagens/icos/fonteRenda.png";
			
			if(FormatadorUtil.TIPO_PESSOA_FISICA == pessoa.codTipoPessoa) {
				this.textoAcao = "FONTE DE RENDA";
				this.textoModulo = "FONTES DE RENDA";
			} else if(FormatadorUtil.TIPO_PESSOA_JURIDICA == pessoa.codTipoPessoa) {
				this.textoAcao = "FATURAMENTO";
				this.textoModulo = "FATURAMENTO";
			} else {
				this.textoAcao = "FONTE DE RENDA";
				this.textoModulo = "FONTES DE RENDA";
			}
		}

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.servicoConsulta.configurarDestino(destinoVO);
			telaEdicao.configurarDestinosServicos(destinoVO);
		}
		
		protected override function registroExcluido(event : Event) : void {
			super.registroExcluido(event);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
		}
		
		protected override function adicionarClicado(event:MouseEvent=null):void{
			if(getTipoPessoaSelecionadaJuridica()){
				if(!(telaLista.returnSizeList() > 0)){
					super.adicionarClicado(event);
				}else{
					Alerta.show("É permitido cadastrar apenas um faturamento por pessoa juridica!", "ATENÇÃO", Alerta.ALERTA_INFORMACAO);
				}
			}else{
				super.adicionarClicado(event);
			}
		}
		
		
		
		private function getTipoPessoaSelecionadaJuridica():Boolean{
			return pessoa.codTipoPessoa == Constantes.COD_TIPO_PES_JURIDICA;
		}
	}
}