package
{
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarCertidao.frmEditarCertidao;
	import br.com.sicoob.capes.cadastrarCertidao.frmListarCertidao;
	import br.com.sicoob.capes.comum.vo.entidades.ArquivoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.OrgaoEmissorCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.SubTipoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAbrangenciaCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObjetoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPrazoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.ValorChaveGEDVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.core.Container;
	
	public class CertidaoSelecao extends TelaPlataformaAtendimentoCAPESBase
	{

		private var telaLista:frmListarCertidao = new frmListarCertidao();
		private var telaEdicao:frmEditarCertidao = new frmEditarCertidao();

		public function CertidaoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Arquivo", 
					ArquivoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ValorChaveGED",
					ValorChaveGEDVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio",
					DocumentoComprobatorioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Certidao",
					CertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao",
					OrgaoEmissorCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.SubTipoCertidao",
					SubTipoCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAbrangenciaCertidao",
					TipoAbrangenciaCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoObjetoCertidao",
					TipoObjetoCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoCertidao",
					TipoCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPrazoCertidao",
					TipoPrazoCertidaoVO);

			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}

		private function init(evt:Event):void {

			this.setTelaLista(telaLista);
			this.setTelaEdicao(telaEdicao);

			this.textoAcao = "CERTIDÃO";
			this.textoModulo = "CERTIDÃO";
			this.iconModulo = "br/com/bancoob/imagens/icos/sinfo_32.png";
			this.textoOpcoes = "OPÇÕES";
			this.iconOpcoes = "br/com/bancoob/imagens/icos/opts_24.png";
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaEdicao.dispose();
			telaEdicao = null;
		}

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.configurarDestinosServicos(destinoVO);
			telaEdicao.configurarDestinosServicos(destinoVO);
		}
		
		override protected function habilitarControles(cont:Container):void {
			super.habilitarControles(cont);

			telaEdicao.dtDataCadastro.enabled = false;
		}
		
		protected override function registroExcluido(event : Event) : void {
			super.registroExcluido(event);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
		}
	}
}