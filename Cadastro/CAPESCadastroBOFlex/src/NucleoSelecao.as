package {
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarNucleo.NucleoEdicao;
	import br.com.sicoob.capes.cadastrarNucleo.NucleoSelecaoView;
	import br.com.sicoob.capes.cadastrarNucleo.PainelFiltroNucleoView;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;

	public class NucleoSelecao extends NucleoSelecaoView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function NucleoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
		}
		
		protected override function init(evento:FlexEvent) : void {
			super.init(evento);
			
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			PainelListaBanco(this.painelLista).funcaoConfiguracaoDto = configurarDtoConsulta;
			
			// adiciona a tela de edição
			adicionarFormularioCadastro();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil.getServico(obterClasseServico(), "Recuperando dados...");
			servicoExclusao = ServicoJavaUtil.getServico(obterClasseServico(), "Excluindo dados...");
			
			painelFiltro.comboSimNao.dataProvider = obterListaComboSimNao();
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:NucleoVO = new NucleoVO();
			
			if(painelFiltro.campoCodigo.valor != 0){
				filtro.numNucleo = painelFiltro.campoCodigo.valor;
			}
			
			if(painelFiltro.campoDescricao.text != ""){
				filtro.descricao = painelFiltro.campoDescricao.text;
			}
			
			if(painelFiltro.comboSimNao.selectedItem != null) {
				filtro.ativo = new Booleano(painelFiltro.comboSimNao.selectedItem.codigo as Boolean);
			} else {
				filtro.ativo = null;
			}
			
			dto.filtro = filtro;
		}
		
		private function obterListaComboSimNao():ArrayCollection {
			var lista:ArrayCollection = new ArrayCollection();
			var objetoSim:Object = new Object();
			objetoSim.codigo = true;
			objetoSim.descricao = "Sim";
			lista.addItem(objetoSim);
			
			var objetoNao:Object = new Object();
			objetoNao.codigo = false;
			objetoNao.descricao = "Não";
			lista.addItem(objetoNao);
			return lista;
		}
		
		private function get painelFiltro():PainelFiltroNucleoView {
			return this.painelLista.painelFiltro as PainelFiltroNucleoView;
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			servicoExclusao.configurarDestino(destino);
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["nucleo"] = item;
			return dto;
		}
		
		protected function adicionarFormularioCadastro() : void {
			this.formularioCadastro = new NucleoEdicao();
		}
		
		protected function obterClasseServico() : String {
			return "br.com.sicoob.capes.cadastro.fachada.NucleoFachada";
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new NucleoEdicao();
			super.botaoIncluirPressionado();
		}
	}
}