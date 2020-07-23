package {
	
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.tipoEmpresa.TipoEmpresaEdicao;
	import br.com.sicoob.capes.tipoEmpresa.TipoEmpresaView;
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;

	public class TipoEmpresaSelecao extends TipoEmpresaView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private var CLASSE_SERVICO : String = "br.com.sicoob.capes.cadastro.fachada.TipoEmpresaFachada";
		
		public function TipoEmpresaSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoEmpresa", TipoEmpresaVO);
		}

		protected override function init(evento:FlexEvent) : void {
			super.init(evento);
			
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			
			// adiciona a tela de edição
			this.formularioCadastro = new TipoEmpresaEdicao();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Recuperando dados...");
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			(PainelListaBanco(this.painelLista).servicoPesquisa as ServicoJava).configurarDestino(destino);
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new TipoEmpresaEdicao();
			super.botaoIncluirPressionado();
		}
	}
}