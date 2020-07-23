package {
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarTipoRelacionamento.TipoRelacionamentoPessoaSelecaoView;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRelacionamentoPessoaVO;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;

	public class TipoRelacionamentoPessoaSelecao extends TipoRelacionamentoPessoaSelecaoView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function TipoRelacionamentoPessoaSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa", TipoRelacionamentoPessoaVO);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			
			// remove o botão de exclusão
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			
			//realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		private function instanciarDTOConsulta() : ConsultaDto {
			return new ConsultaDto();
		}
	}
}