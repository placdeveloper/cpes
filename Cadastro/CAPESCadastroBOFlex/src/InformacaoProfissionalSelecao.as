package
{
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarInformacaoProfissional.frmEditarInfoProfissional;
	import br.com.sicoob.capes.cadastrarInformacaoProfissional.frmListarInfoProfissional;
	import br.com.sicoob.capes.comum.vo.ConselhoRegionalVO;
	import br.com.sicoob.capes.comum.vo.InformacaoProfissionalVO;
	import br.com.sicoob.capes.comum.vo.TipoAfastamentoVO;
	import br.com.sicoob.capes.comum.vo.TipoFuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.TelefoneBaseVO;
	import br.com.sicoob.capes.comum.vo.entidades.TelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoTelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	public class InformacaoProfissionalSelecao extends TelaPlataformaAtendimentoCAPESBase
	{
		private var telaLista:frmListarInfoProfissional = new frmListarInfoProfissional();
		private var telaEdicao:frmEditarInfoProfissional = new frmEditarInfoProfissional();
		public static const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.InformacaoProfissionalFachada";		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		
		public function InformacaoProfissionalSelecao()
		{
			super();
			registrarClasses();
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);			
		}
		
		private function registrarClasses(): void {
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';	
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UF", UFVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TelefoneBase", TelefoneBaseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Telefone", TelefoneVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoaContato", TipoPessoaContatoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoTelefone", TipoTelefoneVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.ConselhoRegionalVO",ConselhoRegionalVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.InformacaoProfissionalVO",InformacaoProfissionalVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.TipoAfastamentoVO",TipoAfastamentoVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.TipoFuncionarioVO",TipoFuncionarioVO);
		}		
		
		private function init(evt:Event):void {
			
			this.textoAcao = "INFORMAÇÕES PROFISSIONAIS";
			this.textoModulo = this.produtosBancoob ? 
				"PRODUTOS BANCOOB - INFORMAÇÕES PROFISSIONAIS" :
				"INFORMAÇÕES PROFISSIONAIS";
			this.iconModulo = "br/com/bancoob/imagens/icos/Accountant.png";
			this.iconOpcoes = "br/com/bancoob/imagens/icos/sinfo_32.png";
			this.setTelaLista(telaLista);
			this.setTelaEdicao(telaEdicao);
			this.telaEdicao.produtosBancoob = this.produtosBancoob;
			this.telaLista.produtosBancoob = this.produtosBancoob;
			telaLista.obterLista();
			telaEdicao.carregarDefinicoes();
		}		
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------	
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.configurarDestinosServicos(destinoVO);
			telaEdicao.configurarDestinosServicos(destinoVO);
		}       
	}
}