package br.com.sicoob.capes.cadastrarPessoa.abas
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.IAbaCadastroPessoa;
	import br.com.sicoob.capes.comum.util.CadastroUnicoUtil;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.NacionalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaJuridicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmpresaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFormaConstituicaoVO;
	
	public class AbaPessoaJuridica extends AbaPessoaJuridicaView implements IAbaCadastroPessoa
	{
		
		public var servicoConsulta:ServicoJava;
		private var forma: Object = null;
		
		private static const CLASSE_SERVICO : String = "br.com.sicoob.capes.cadastro.fachada.PessoaFachada";
		
		public function AbaPessoaJuridica()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterEsferasAdministrativas);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		private function init(event:Event):void{
			procurarNacionalidade.configurarDestino(destino);
			txtDataConstituicao.dataDefault = null;
			txtDataRegistroAta.dataDefault = null;
			txtDataRegistroJunta.dataDefault = null;
			txtDataUltimaAlteracao.dataDefault = null;
			txtInscricaoMunicipal.text = "";
			this.cboFormaConstituicao.addEventListener(ListEvent.CHANGE, onChangeCboFormaConstituicao);
		}
		
		public function preencherCampos(registro:PessoaCompartilhamentoVO):void {
			
			var pessoa: PessoaJuridicaVO = PessoaJuridicaVO(registro);
			
			var dataRegistroAta :Date = null;
			var dataUltima :Date = null;
			var dataRegistroJunta: Date = null;
			var dataConstituicao: Date = null;
			var nacionalidade: NacionalidadeVO = pessoa.nacionalidade;
			
			if(pessoa.dataConstituicao != null) {
				dataConstituicao = pessoa.dataConstituicao.data;					
			}
			if(pessoa != null && pessoa.tipoEmpresa != null) {
				setaTipoEmpresa(pessoa.tipoEmpresa);
			}
			if(pessoa != null && pessoa.formaConstituicao != null) {
				forma = pessoa.formaConstituicao.codigo;
			}
			if(pessoa.dataRegistroJuntaComercial != null) {
				dataRegistroJunta = pessoa.dataRegistroJuntaComercial.data;
			}
			if(pessoa.dataUltimaAlteracaoContratoSocial != null) {
				dataUltima = pessoa.dataUltimaAlteracaoContratoSocial.data;
			}
			
			if(pessoa.dataRegistroRepresentacao != null) {
				dataRegistroAta = pessoa.dataRegistroRepresentacao.data;
			}
			
			if(nacionalidade != null) {
				this.procurarNacionalidade.textoCodigo.text = String(nacionalidade.codigo);
				this.procurarNacionalidade.pesquisar();
			}
			
			this.txtDataConstituicao.selectedDate = dataConstituicao; 
			this.txtCapitalSocial.valor = pessoa.valorCapitalSocial;
			this.txtInscricaoEstadual.text = pessoa.inscricaoEstadual;
			this.txtNumeroRegistroOrgao.text = pessoa.numeroRegistroJuntaComercial;			
			this.txtNumeroUltimaAlteracao.text = pessoa.numeroUltimaAlteracaoContratoSocial;
			this.txtNumeroRegistroRepresentacao.text = pessoa.numeroRegistroRepresentacao;
			this.txtDataRegistroJunta.selectedDate = dataRegistroJunta;
			this.txtDataUltimaAlteracao.selectedDate = dataUltima; 
			this.txtDataRegistroAta.selectedDate = dataRegistroAta;
			this.cboEsferaAdministrativa.procuraItemPorNome(pessoa.codigoEsferaAdministrativa, "codigo");
			this.txtInscricaoMunicipal.text = pessoa.inscricaoMunicipal;
			
			this.cboFormaConstituicao.procuraItemPorNome(forma, "codigo");
			// FIXME bruno.carneiro: Remover na proxima demanda.
			var tipoForma:TipoFormaConstituicaoVO = cboFormaConstituicao.selectedItem as TipoFormaConstituicaoVO;
			if(tipoForma != null) {
				var coop = DadosLogin.coop;
				if (coop == '0300') {
					cboFormaConstituicao.enabled = true;
				} else {
					if(tipoForma.codigo == 214) { // Cooperativa
						cboFormaConstituicao.enabled = false;
					}
				}
			}
			
			this.validateNow();
		}
		
		private function setaTipoEmpresa(tipoEmp:TipoEmpresaVO):void{
			tipoPorte.text = tipoEmp.descricao;
			lblLimitesTipoEmpresa.text = "Faixa de faturamento adimitido, de R$ " + FormataNumero.formata(Number(tipoEmp.valorLimiteInferior)) + " a R$ " + FormataNumero.formata(Number(tipoEmp.valorLimiteSuperior));
		}
		
		public function atualizarCamposRegistro(pessoa:PessoaCompartilhamentoVO): PessoaCompartilhamentoVO {
			
			var dataConstituicao: IDateTime = null;
			var dataRegistroJunta: IDateTime = null;
			var dataUltimaAlteracao: IDateTime = null;
			var dataRegistroAta: IDateTime = null;
			var codigoEsfera:Number = NaN;
			var nacionalidade: NacionalidadeVO = null;
			
			if(procurarNacionalidade.obterRegistro() != null) {
				var nac:NacionalidadeVO = procurarNacionalidade.obterRegistro() as NacionalidadeVO;
				nacionalidade = new NacionalidadeVO();
				nacionalidade.codigo = nac.codigo;
				nacionalidade.descricao = nac.descricao;
			}
			if(cboEsferaAdministrativa.selectedItem != null) {
				codigoEsfera = cboEsferaAdministrativa.selectedItem.codigo as Number;
			}
			var registro:PessoaJuridicaVO = PessoaJuridicaVO(pessoa);
			registro.valorCapitalSocial = txtCapitalSocial.valor;
			registro.inscricaoEstadual = txtInscricaoEstadual.text;
			registro.codigoEsferaAdministrativa = codigoEsfera;
			if(cboFormaConstituicao.selectedItem != null) {
				registro.formaConstituicao = cboFormaConstituicao.selectedItem as TipoFormaConstituicaoVO;
			}
			registro.nacionalidade = nacionalidade;
			registro.numeroRegistroJuntaComercial = txtNumeroRegistroOrgao.text;
			registro.numeroUltimaAlteracaoContratoSocial = txtNumeroUltimaAlteracao.text;
			registro.numeroRegistroRepresentacao = txtNumeroRegistroRepresentacao.text;
			
			if(txtDataConstituicao.selectedDate != null) {
				dataConstituicao = DateTimeBase.getDateTimeEntity(txtDataConstituicao.selectedDate);
			}	
			if(txtDataRegistroJunta.selectedDate != null) {
				dataRegistroJunta = DateTimeBase.getDateTimeEntity(txtDataRegistroJunta.selectedDate);
			}			
			if(txtDataUltimaAlteracao.selectedDate != null) {
				dataUltimaAlteracao = DateTimeBase.getDateTimeEntity(txtDataUltimaAlteracao.selectedDate);
			}			
			if(txtDataRegistroAta.selectedDate != null) {
				dataRegistroAta = DateTimeBase.getDateTimeEntity(txtDataRegistroAta.selectedDate);
			}			
			registro.dataConstituicao = dataConstituicao;
			registro.dataRegistroJuntaComercial = dataRegistroJunta;
			registro.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracao;
			registro.dataRegistroRepresentacao = dataRegistroAta;
			registro.inscricaoMunicipal = txtInscricaoMunicipal.text;
			validarObrigatoriedadeCondicional()
			return registro;
		}		
		
		
		public function retornoCarregarDefinicoes(event:ResultEvent): void {
			cboEsferaAdministrativa.dataProvider = event.result.dados.esferasAdministrativas;
			
			cboFormaConstituicao.dataProvider = new ArrayCollection();
			adicionarItemOpcional(cboFormaConstituicao);
			FlexUtil.clonarLista(ListCollectionView(cboFormaConstituicao.dataProvider), event.result.dados.tiposForma)
		}
		
		public function verificarAlteracoes(registroBkp:PessoaCompartilhamentoVO):Boolean {
			
			var registro:PessoaJuridicaVO = PessoaJuridicaVO(registroBkp);
			var dataConstituicao: Date = null;
			var dataRegistroJuntaComercial: Date = null;
			var dataUltimaAlteracaoContratoSocial: Date = null;
			var dataRegistroRepresentacao: Date = null;
			var codigoNacionalidade: String = "";
			
			if(registro.dataConstituicao != null) {
				dataConstituicao = registro.dataConstituicao.data;
			}
			if(registro.dataRegistroJuntaComercial != null) {
				dataRegistroJuntaComercial = registro.dataRegistroJuntaComercial.data;
			}
			if(registro.dataUltimaAlteracaoContratoSocial != null) {
				dataUltimaAlteracaoContratoSocial = registro.dataUltimaAlteracaoContratoSocial.data;
			}
			if(registro.dataRegistroRepresentacao != null) {
				dataRegistroRepresentacao = registro.dataRegistroRepresentacao.data;
			}
			if(registro.nacionalidade != null) {
				codigoNacionalidade = new String(registro.nacionalidade.codigo);
			}
			
			return txtCapitalSocial.valor == registro.valorCapitalSocial
				&& txtInscricaoEstadual.text == registro.inscricaoEstadual
				&& compararCombos(registro)
				&& txtNumeroRegistroOrgao.text == registro.numeroRegistroJuntaComercial
				&& txtNumeroUltimaAlteracao.text == registro.numeroUltimaAlteracaoContratoSocial
				&& txtNumeroRegistroRepresentacao.text == registro.numeroRegistroRepresentacao
				&& DateUtils.equals(txtDataConstituicao.selectedDate, dataConstituicao)
				&& DateUtils.equals(txtDataRegistroJunta.selectedDate, dataRegistroJuntaComercial)
				&& DateUtils.equals(txtDataUltimaAlteracao.selectedDate, dataUltimaAlteracaoContratoSocial)
				&& procurarNacionalidade.textoCodigo.text == codigoNacionalidade
				&& DateUtils.equals(txtDataRegistroAta.selectedDate, dataRegistroRepresentacao)
				&& txtInscricaoMunicipal.text == registro.inscricaoMunicipal;			
		}		
		
		private function compararCombos(registro: PessoaJuridicaVO): Boolean {
			
			var codigoEsferaAdministrativa: Object = null;
			var codigoTipoEmpresa: Object = null;
			var formaConstituicao: Object = null;
			
			if(!isNaN(registro.codigoEsferaAdministrativa)) {
				codigoEsferaAdministrativa = String(registro.codigoEsferaAdministrativa);
			}
			
			if(registro.tipoEmpresa != null) {
				codigoTipoEmpresa = registro.tipoEmpresa.codigo;		
			}
			if(registro.formaConstituicao != null) {
				formaConstituicao = registro.formaConstituicao.codigo;		
			}
			
			// Itens de combo
			var codigoEsferaAdministrativaCombo: Object = null; 			
			var codigoTipoEmpresaCombo: Object = null; 
			var formaConstituicaoCombo: Object = null;
			
			if(cboEsferaAdministrativa.selectedItem != null) {
				codigoEsferaAdministrativaCombo = cboEsferaAdministrativa.selectedItem.codigo;
			}		
			if(cboFormaConstituicao.selectedItem != null) {
				formaConstituicaoCombo = cboFormaConstituicao.selectedItem.codigo;
			}		
			
			return codigoEsferaAdministrativaCombo == Number(codigoEsferaAdministrativa)
				&& codigoTipoEmpresaCombo == codigoTipoEmpresa
				&& formaConstituicaoCombo == formaConstituicao;	
		}
		
		private function obterValorNaoNulo(valor:String):String {
			var retorno:String = "";
			if(valor != null) {
				retorno = valor;
			}
			return retorno;
		}
		
		private function onChangeCboFormaConstituicao(evento:ListEvent = null):void {
			if(cboFormaConstituicao.selectedItem != null){
				var formaConstituicao:TipoFormaConstituicaoVO = cboFormaConstituicao.selectedItem as TipoFormaConstituicaoVO;
				if(formaConstituicao != null && !isNaN(formaConstituicao.codigo)){
					var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
					dto.dados.codigoFormaConstituicao = formaConstituicao.codigo;
					
					MostraCursor.setBusyCursor("Obtendo Esferas administrativas...", Application.application, MostraCursor.CURSOR_PROGRESSO);
					servicoConsulta.getOperation("obterEsferasAdministrativas").send(dto);
				}
			}
		}
		
		private function retornoObterEsferasAdministrativas(evento:ResultEvent=null):void {
			if(evento.result.dados.esferasAdministrativas != null){
				FlexUtil.atualizarCombo(cboEsferaAdministrativa, evento.result.dados.esferasAdministrativas);
				FlexUtil.adicionarItemOpcional(cboEsferaAdministrativa, "SELECIONE");
				cboEsferaAdministrativa.selectedIndex = 0;
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function adicionarItemOpcional(combo:Combo): void {
			if (combo.dataProvider is IList) {
				var lista: IList = (combo.dataProvider as IList);
				if (lista.length == 0 || (lista.getItemAt(0) != null && 
					!lista.getItemAt(0).hasOwnProperty("idItemOpcionalCombo"))) {
					lista.addItemAt(criarItemOpcional(combo),0);
				}
			}
		}
		
		protected function criarItemOpcional(combo:Combo):Object {
			var objeto:Object = new Object();
			objeto[combo.labelField] = "---";
			objeto.idItemOpcionalCombo = -1;
			
			return objeto;
		}
		
		//--------------------------------------------------------------------------
		//  Validacoes obrigatorias dependentes.
		//--------------------------------------------------------------------------		
		private function validarObrigatoriedadeCondicional(): void {
			
			var registroOrgaoPreenchido: Boolean = 
				!CadastroUnicoUtil.isVazio(txtNumeroRegistroOrgao.text) 
				|| txtDataRegistroJunta.selectedDate != null;
			
			txtNumeroRegistroOrgao.validarObrigatorio = registroOrgaoPreenchido;
			txtDataRegistroJunta.validarObrigatorio = registroOrgaoPreenchido;
			
			var alteracaoPreenchida: Boolean = !CadastroUnicoUtil.isVazio(txtNumeroUltimaAlteracao.text) 
				|| txtDataUltimaAlteracao.selectedDate != null;
			txtNumeroUltimaAlteracao.validarObrigatorio = alteracaoPreenchida;
			txtDataUltimaAlteracao.validarObrigatorio = alteracaoPreenchida;
			
			var ataPreenchida: Boolean = !CadastroUnicoUtil.isVazio(txtNumeroRegistroRepresentacao.text) 
				|| txtDataRegistroAta.selectedDate != null;
			txtNumeroRegistroRepresentacao.validarObrigatorio = ataPreenchida;
			txtDataRegistroAta.validarObrigatorio = ataPreenchida;
		}	
		
		//--------------------------------------------------------------------------
		//  Configuracaoo de destino dos servicos.
		//--------------------------------------------------------------------------			
		public function configurarDestinosServicos(destino:DestinoVO):void{
			this.destino = destino;
			servicoConsulta.configurarDestino(destino);
		}
	}
}