package {
    import br.com.bancoob.componentes.painellista.PainelListaBanco;
    import br.com.bancoob.dto.ConsultaDto;
    import br.com.bancoob.dto.RequisicaoDTO;
    import br.com.bancoob.dto.RequisicaoReqDTO;
    import br.com.bancoob.negocio.vo.AplicacaoVO;
    import br.com.bancoob.sisbr.portal.PortalModel;
    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.util.StringUtils;
    import br.com.bancoob.util.servico.ServicoJava;
    import br.com.bancoob.vo.DestinoVO;
    import br.com.sicoob.capes.cadastroRegraValidacaoCadastral.CadastroRegraValidacaoCadastralEdicao;
    import br.com.sicoob.capes.cadastroRegraValidacaoCadastral.CadastroRegraValidacaoCadastralSelecaoView;
    import br.com.sicoob.capes.cadastroRegraValidacaoCadastral.PainelFiltroRegrasView;
    import br.com.sicoob.capes.comum.enums.FuncionalidadeValidacaoCadastralEnum;
    import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
    import br.com.sicoob.capes.comum.vo.entidades.PerfilCadastroVO;
    import br.com.sicoob.capes.comum.vo.entidades.TipoReferenciaVO;
    import br.com.sicoob.capes.comum.vo.entidades.TipoRegraValidacaoCadastralVO;
    import br.com.sicoob.capes.comum.vo.entidades.ValidacaoCadastralRegraVO;
    
    import flash.events.MouseEvent;
    import flash.net.registerClassAlias;
    
    import mx.collections.ArrayCollection;
    import mx.events.FlexEvent;
    import mx.rpc.events.ResultEvent;
    
    public class CadastroRegraValidacaoCadastralSelecao extends CadastroRegraValidacaoCadastralSelecaoView {
        
        private static const DESTINO_CAPES : String = "destinoCAPES";

        protected 	var servicoDefinicoes : ServicoJava;
		private 	var funcionalidades:ArrayCollection 	= new ArrayCollection();
		private 	var tipos:ArrayCollection 				= new ArrayCollection();
		private 	var perfilCadastro:ArrayCollection 		= new ArrayCollection();

        public function CadastroRegraValidacaoCadastralSelecao(){
            super();
			registrarClasses();
            servicoDefinicoes = ServicoJavaUtil.getServico(
                "br.com.sicoob.capes.cadastro.fachada.ValidacaoCadastralRegraFachada", 
                "Obtendo definições...");
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, onResultDefinicoes);
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, telaEdicao.onResultDefinicoes);
        }

		private function registrarClasses():void {
            include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum", 
				FuncionalidadeValidacaoCadastralEnum);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral",
				TipoRegraValidacaoCadastralVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoReferencia",
				TipoReferenciaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra", 
				ValidacaoCadastralRegraVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilCadastro", 
				PerfilCadastroVO);
		}
		
        protected override function init(event : FlexEvent) : void {
            super.init(event);
            var painelLista : PainelListaBanco = PainelListaBanco(this.painelLista)
            painelLista.funcaoCriacaoDto = instanciarDtoConsulta;
            painelLista.funcaoConfiguracaoDto = configurarDtoConsulta;
            
			painelFiltro.btLimpar.addEventListener(MouseEvent.CLICK, onClickBtLimpar);
			painelFiltro.cmbFuncionalidade.dataProvider = this.funcionalidades;
			painelFiltro.cmbTipo.dataProvider = this.tipos;
			painelFiltro.cmbPerfilCadastro.dataProvider = this.perfilCadastro;
            var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
            PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoCAPESRecuperado);
        }
		
        private function onDestinoCAPESRecuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			ServicoJava(servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			telaEdicao.configurarDestino(destino);
            servicoDefinicoes.configurarDestino(destino);
			
            servicoDefinicoes.getOperation("obterDefinicoes").send(new RequisicaoReqDTO());
        }

        protected function onResultDefinicoes(event : ResultEvent) : void {
			this.funcionalidades.addAll(event.result.dados.funcionalidades);
			this.tipos.addAll(event.result.dados.tipos);
			this.perfilCadastro.addAll(event.result.dados.perfilCadastro);
            PainelListaBanco(this.painelLista).pesquisar(1);
        }
        
		private function onClickBtLimpar(event:MouseEvent):void {
			painelFiltro.codigoRegra.text = "";
			painelFiltro.cmbFuncionalidade.selectedItem = null;
			painelFiltro.cmbSituacao.selectedItem = null;
			painelFiltro.cmbTipo.selectedItem = null;
			painelFiltro.cmbPerfilCadastro.selectedItem = null;
            PainelListaBanco(this.painelLista).pesquisar(1);
		}

		public function instanciarDtoConsulta(): ConsultaDto {
            return new ConsultaDto();
        }
        
        public function configurarDtoConsulta(dto:ConsultaDto): void {
            
            var filtro:ValidacaoCadastralRegraVO = new ValidacaoCadastralRegraVO();
            if (StringUtils.trim(painelFiltro.codigoRegra.text) != "") {
                filtro.codigoRegra = painelFiltro.codigoRegra.valor;
            }
			if (painelFiltro.cmbFuncionalidade.selectedItem != null) {
				filtro.funcionalidade = FuncionalidadeValidacaoCadastralEnum(
					painelFiltro.cmbFuncionalidade.selectedItem); 
			}
			if (painelFiltro.cmbSituacao.valorSelecionado != null) {
				filtro.ativo = new Booleano(painelFiltro.cmbSituacao.valorSelecionado == "true"); 
			}
			if (painelFiltro.cmbTipo.selectedItem != null) {
				filtro.tipoRegra = TipoRegraValidacaoCadastralVO(painelFiltro.cmbTipo.selectedItem); 
			}
			if (painelFiltro.cmbPerfilCadastro.selectedItem != null) {
				filtro.perfilCadastro = PerfilCadastroVO(painelFiltro.cmbPerfilCadastro.selectedItem); 
			}
            dto.filtro = filtro;
        }
        
        private function get painelFiltro() : PainelFiltroRegrasView {
            return this.painelLista.painelFiltro as PainelFiltroRegrasView;
        }
        
		private function get telaEdicao(): CadastroRegraValidacaoCadastralEdicao {
			return CadastroRegraValidacaoCadastralEdicao(formularioCadastro);
		}
		
        protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
            
            var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
            dto.dados[CadastroRegraValidacaoCadastralEdicao.CHAVE_MAPA] = item;
            return dto;
        }
		
		protected override function montarDtoRecuperacao(item:Object):RequisicaoDTO {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados[CadastroRegraValidacaoCadastralEdicao.CHAVE_MAPA] = item;
			return dto;
		}

    }
}