package br.com.sicoob.capes.cadastroRegraValidacaoCadastral {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.eventos.EventoBarraBotoes;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastroRegraValidacaoCadastral.validacao.ValidadorQuery;
	import br.com.sicoob.capes.cadastroRegraValidacaoCadastral.validacao.ValidadorSituacao;
	import br.com.sicoob.capes.comum.enums.FuncionalidadeValidacaoCadastralEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilCadastroVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRegraValidacaoCadastralVO;
	import br.com.sicoob.capes.comum.vo.entidades.ValidacaoCadastralRegraVO;
	
	import flash.events.TextEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

    public class CadastroRegraValidacaoCadastralEdicao extends CadastroRegraValidacaoCadastralEdicaoView {

		static public const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.ValidacaoCadastralRegraFachada";
		static private const OPERACAO_OBTER_DADOS: String = "obterDados";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static public const CHAVE_MAPA:String = "regra";

		private var servicoCodigo: ServicoJava;
		
		private var codigoSugerido:Number;
		private var funcionalidades:ArrayCollection = new ArrayCollection();
		private var tipos:ArrayCollection = new ArrayCollection();
		private var perfilCadastro:ArrayCollection = new ArrayCollection();
		
		public function CadastroRegraValidacaoCadastralEdicao() {
			super();
			this.servicoCodigo = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Obtendo próximo código...", ResultEvent.RESULT, retornoObterCodigo);
        }
		
		public function configurarDestino(destino:DestinoVO):void {
			servicoCodigo.configurarDestino(destino);
			ServicoJava(this.servicoEdicao).configurarDestino(destino);
			ServicoJava(this.servicoInclusao).configurarDestino(destino);
		}
		
		protected override function init(event:FlexEvent):void {
			this.abaDadosBasicos.cmbFuncionalidade.dataProvider = this.funcionalidades;
			this.abaDadosBasicos.cmbTipo.dataProvider = this.tipos;
			this.abaDadosBasicos.cmbPerfilCadastro.dataProvider = this.perfilCadastro;

			super.init(event);
			
			aplicarPermissaoComponente(abaDetalhamento);
			
			this.validacoesAdicionais.addItem(new ValidadorSituacao(
				this.abaDadosBasicos.situacao, 
				this.query));
			this.validacoesAdicionais.addItem(new ValidadorQuery(
				this.query));
			
			this.query.restrict = "^%";// removendo a restricao da aspa simples e restringindo o %
		}
		
		protected override function limparFormIncluir() : void {
			this.codigoSugerido = NaN;
			this.abaDadosBasicos.codigo.text = "";
			this.abaDadosBasicos.cmbFuncionalidade.selectedItem = null;
			this.abaDadosBasicos.cmbTipo.procuraItemPorNome(TipoRegraValidacaoCadastralVO.RESTRITIVA, "codigo");
			this.abaDadosBasicos.cmbPerfilCadastro.procuraItemPorNome(PerfilCadastroVO, "codigo");
			this.abaDadosBasicos.situacao.selectedValue = false;
			this.abaDadosBasicos.descricao.text = "";
			this.abaDadosBasicos.msgErro.text = "";
			this.abaDadosBasicos.checkExecutarRegra.selected = false;
			this.query.text = "";
			servicoCodigo.getOperation("obterProximoCodigo").send(new RequisicaoReqDTO());
		}		
		
		protected override function preencherCampos():void {
			var regra : ValidacaoCadastralRegraVO = ValidacaoCadastralRegraVO(this.objeto);
			this.abaDadosBasicos.codigo.valor = regra.codigoRegra;
			this.abaDadosBasicos.cmbFuncionalidade.procuraItemPorNome(regra.funcionalidade.name,"name");
			this.abaDadosBasicos.cmbTipo.procuraItemPorNome(regra.tipoRegra.codigo, "codigo");
			this.abaDadosBasicos.cmbPerfilCadastro.procuraItemPorNome(regra.perfilCadastro.codigo, "codigo");
			this.abaDadosBasicos.situacao.selectedValue = Booleano(regra.ativo).valor;
			this.abaDadosBasicos.descricao.text = regra.descricao;
			this.abaDadosBasicos.msgErro.text = regra.mensagemErro;
			this.abaDadosBasicos.checkExecutarRegra.selected = Booleano(regra.executarRegra).valor;
			this.query.text = regra.query;
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : ValidacaoCadastralRegraVO = new ValidacaoCadastralRegraVO();
			
			if (MODO_INCLUSAO == modo) {
				vo.codigoRegra = this.abaDadosBasicos.codigo.valor;
			} else if (MODO_EDICAO == modo) {
				vo.codigoRegra = this.objeto.codigoRegra;
			}
			vo.descricao = this.abaDadosBasicos.descricao.text;
			vo.funcionalidade = FuncionalidadeValidacaoCadastralEnum(
				this.abaDadosBasicos.cmbFuncionalidade.selectedItem);
			vo.mensagemErro = this.abaDadosBasicos.msgErro.text;
			vo.ativo = new Booleano(this.abaDadosBasicos.situacao.selectedValue);
			vo.tipoRegra = TipoRegraValidacaoCadastralVO(this.abaDadosBasicos.cmbTipo.selectedItem);
			vo.perfilCadastro = PerfilCadastroVO(this.abaDadosBasicos.cmbPerfilCadastro.selectedItem);
			vo.query = this.query.text;
			vo.executarRegra = new Booleano(this.abaDadosBasicos.checkExecutarRegra.selected);
				
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados[CHAVE_MAPA] = vo;
			return dto as RequisicaoDTO;
		}

		protected override function configurarEstadoFormularioCadastro():void {
			super.configurarEstadoFormularioCadastro();
			this.navegacao.selectedIndex = 0;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
															   habilitar : Boolean) : void {
			
			if (componente.name == "codigo") {
				habilitar = (MODO_INCLUSAO == modo);
			} else if (componente.name == "cmbSituacao") {
				habilitar = (MODO_EDICAO == modo);
			}
			super.configurarEstadoComponente(componente, habilitar);
		}		
		
		protected override function configurarEstadoBarraBotoes():void {
			super.configurarEstadoBarraBotoes();
		}
		
		protected override function houveAlteracoes():Boolean {
			var alterado : Boolean = false;
			
			if (MODO_INCLUSAO == modo) {
				alterado = alterado || ((this.abaDadosBasicos.codigo.text != "") && (this.abaDadosBasicos.codigo.valor != this.codigoSugerido));
				alterado = alterado || this.abaDadosBasicos.cmbFuncionalidade.selectedItem != null;
				alterado = alterado || this.abaDadosBasicos.situacao.selectedValue;
				alterado = alterado || TipoRegraValidacaoCadastralVO(this.abaDadosBasicos.cmbTipo.selectedItem).codigo != TipoRegraValidacaoCadastralVO.RESTRITIVA;
				alterado = alterado || this.abaDadosBasicos.descricao.text != "";
				alterado = alterado || this.abaDadosBasicos.msgErro.text != "";
				alterado = alterado || this.abaDadosBasicos.checkExecutarRegra.selected;
				alterado = alterado || this.query.text != "";
			} else if (MODO_EDICAO == modo) {
				var regra:ValidacaoCadastralRegraVO = ValidacaoCadastralRegraVO(this.objeto);
				alterado = alterado || this.abaDadosBasicos.codigo.valor != regra.codigoRegra;
				alterado = alterado || FuncionalidadeValidacaoCadastralEnum(
					this.abaDadosBasicos.cmbFuncionalidade.selectedItem).name != regra.funcionalidade.name;
				alterado = alterado || new Boolean(this.abaDadosBasicos.situacao.selectedValue) != regra.ativo.valor;
				alterado = alterado || TipoRegraValidacaoCadastralVO(
					this.abaDadosBasicos.cmbTipo.selectedItem).codigo != regra.tipoRegra.codigo;
				alterado = alterado || PerfilCadastroVO(
					this.abaDadosBasicos.cmbPerfilCadastro.selectedItem).codigo != regra.perfilCadastro.codigo;
				alterado = alterado || this.abaDadosBasicos.descricao.text != regra.descricao;
				alterado = alterado || this.abaDadosBasicos.msgErro.text != regra.mensagemErro;
				alterado = alterado || new Boolean(this.abaDadosBasicos.checkExecutarRegra.selected) != regra.executarRegra.valor;
				alterado = alterado || this.query.text != regra.query;
			}
			return alterado;
		}

		public function onResultDefinicoes(event: ResultEvent): void { 
			var dados:Object = event.result.dados;
			this.funcionalidades.addAll(dados.funcionalidades);
			this.tipos.addAll(dados.tipos);
			this.perfilCadastro.addAll(dados.perfilCadastro);
		}

		private function retornoObterCodigo(event:ResultEvent):void {
			this.codigoSugerido = event.result.dados.codigo;
			this.abaDadosBasicos.codigo.valor = this.codigoSugerido;
		}

		protected override function botaoOkPressionado(evento:EventoBarraBotoes):void {
			this.query.validarObrigatorio = false;
			executarSeValido(gravar);
		}
		
		protected override function itemIncluido(evento:ResultEvent):void {
			super.itemIncluido(evento);
			confirmaCancelar();
		}
		
		private function interceptChar(event:TextEvent):void {
			var input:String = event.text; //pega item digitado
			
			//ignora caracteres de controle
			var codigo:Number = input.charCodeAt();
			
			if(event.isDefaultPrevented()) {
				Alerta.show("\"" + input + "\"", "INTERCEPTED CHAR");
			}

		}
    }
}