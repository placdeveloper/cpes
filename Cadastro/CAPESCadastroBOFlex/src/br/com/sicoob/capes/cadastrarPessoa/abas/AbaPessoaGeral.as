package br.com.sicoob.capes.cadastrarPessoa.abas
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.IAbaCadastroPessoa;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.vo.entidades.AtividadeEconomicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.CnaeFiscalVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilCadastroVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	
	public class AbaPessoaGeral extends AbaPessoaGeralView implements IAbaCadastroPessoa 
	{
		public const EVENTO_VALIDAR										: String = "validar";
		public const MENSAGEM_DE_AVANCO_CBOPERFILCADASTRO				: String = "Não é possível alterar a categoria para avançado, devido pendência(s) de validação cadastral!";//TODO buscar desc variaveis
		static private const MENSAGEM_DE_RETROCESSO_CBOPERFILCADASTRO	: String = "Não é possível retroceder a categoria de cadastro avançado para simples!";//TODO buscar desc variaveis
		
		private var isGestorCadastro:Boolean 						= false;
		private var registroBkpPerfilCadastro: PerfilCadastroVO 	= null;
		private var registroBkpCboPerfilCadastro: ArrayCollection 	= null;
		
		public function AbaPessoaGeral()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}

		private function init(event:Event):void{		
			procurarCNAE.textoCodigo.restrict = null;
			procurarCNAE.textoCodigo.maxChars = 8;
			procurarAtividadeEconomica.textoCodigo.maxChars = 5;
			desabilitarCamposData();
			
			this.cboPerfilCadastro.addEventListener(Event.CHANGE, mudaCategoriaCadastro);
		}
		
		private function desabilitarCamposData() :void {
			this.txtDataCadastro.enabled 	= false
			this.txtDataSFN.enabled 		= isGestorCadastro;
			this.txtDataInclusao.enabled 	= isGestorCadastro;
		}
		
		public function preencherCampos(pessoa:PessoaCompartilhamentoVO):void {
			var codigoAtividade: String 	= "";
			var descAtividade: String 		= "";
			var codigoCNAE: String 			= "";
			var descCNAE: String 			= "";
			var codigoPerfil: Object 		= null;
			var dataInclusaoSistema: Date 	= null;
			var dataInclusaoSFN: Date 		= null;
			var dataCadastro: Date 			= null;
						
			if(pessoa.atividadeEconomica != null) {
				this.procurarAtividadeEconomica.textoCodigo.text = String(pessoa.atividadeEconomica.codigo);
				this.procurarAtividadeEconomica.pesquisar();				
			}else{
				this.procurarAtividadeEconomica.textoCodigo.text = codigoAtividade;
				this.procurarAtividadeEconomica.textoDescricao.text = descAtividade;				
			}
						
			if(pessoa.cnae != null) {
				this.procurarCNAE.textoCodigo.text = pessoa.cnae.codigo;
				this.procurarCNAE.pesquisar();					
			}else{
				this.procurarCNAE.textoCodigo.text = null;
				this.procurarCNAE.textoDescricao.text = "";	
			}
			if(pessoa.perfilCadastro != null) {
				codigoPerfil = pessoa.perfilCadastro.codigo;	
				registroBkpPerfilCadastro = pessoa.perfilCadastro;
			}
			if(pessoa.dataInclusaoSistema != null) {
				dataInclusaoSistema = pessoa.dataInclusaoSistema.data;					
			}
			if(pessoa.dataInclusaoSFN != null) {
				dataInclusaoSFN = pessoa.dataInclusaoSFN.data;					
			}			
			if(pessoa.dataHoraInicio != null) {
				dataCadastro = pessoa.dataHoraInicio.data;					
			}				
			
			this.txtApelido.text = pessoa.nomeApelido;
			this.txtNomeReceita.text = pessoa.pessoa.nomeRFB;
			this.txtNomeCompleto.text = pessoa.nomeCompleto;
			this.txtNomePessoa.text = pessoa.nomePessoa;
			this.txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(pessoa.pessoa.cpfCnpj)
			if (pessoa.pessoa.situacaoCadastralRFB != null) {
				this.txtCpfCnpj.text += " - Situação na Receita Federal: " + pessoa.pessoa.situacaoCadastralRFB.descricao;
//				this.txtCpfCnpj.text += " (" + pessoa.pessoa.situacaoCadastralRFB.descricao;
//				if ((pessoa.pessoa.dataSituacaoRFB != null) && (pessoa.pessoa.dataSituacaoRFB.data != null)){
//					this.txtCpfCnpj.text += " em " + FormataData.formataData(pessoa.pessoa.dataSituacaoRFB.data);
//				}
//				this.txtCpfCnpj.text += ")";
			}
			this.txtDataCadastro.selectedDate = dataCadastro;
			this.txtDataInclusao.selectedDate = dataInclusaoSistema;
			this.txtDataSFN.selectedDate = dataInclusaoSFN;		
			this.txtObservacao.text = pessoa.descricao;
			this.chkAutorizaBacen.selected = pessoa.autorizaConsultaBacen.valor;
			this.chkImportador.selected = pessoa.importador.valor;
			this.chkExportador.selected = pessoa.exportador.valor;
			
			if(codigoPerfil != null) {
				this.cboPerfilCadastro.procuraItemPorNome(codigoPerfil, "codigo");
			}
			
			selecionarTipoPessoa(pessoa.pessoa.tipoPessoa);
			desabilitarCamposData();
			this.validateNow();
			if (pessoa.dataRenovacaoCadastral != null) {
				this.txtUltimaRenovacao.text = FormataData.formata(pessoa.dataRenovacaoCadastral.data, "dd/MM/yyyy") + " por " + pessoa.idUsuarioRenovacao + " - " + pessoa.idCooperativaRenovacao;
			}
		}
		
		public function atualizarCamposRegistro(registro:PessoaCompartilhamentoVO): PessoaCompartilhamentoVO {

			var atividade: AtividadeEconomicaVO = null;
			var cnae: CnaeFiscalVO = null;
			var dataInclusaoSistema: IDateTime = null;
			var dataSFN: IDateTime = null;
			
			if(procurarAtividadeEconomica.obterRegistro() != null) {
				atividade = procurarAtividadeEconomica.obterRegistro() as AtividadeEconomicaVO;
			}
			
			if(procurarCNAE.obterRegistro() != null) {
				cnae = procurarCNAE.obterRegistro() as CnaeFiscalVO;
			}
			
			if(txtDataInclusao.selectedDate != null) {
				dataInclusaoSistema = DateTimeBase.getDateTimeEntity(txtDataInclusao.selectedDate);
			}
			
			if(txtDataSFN.selectedDate != null) {
				dataSFN = DateTimeBase.getDateTimeEntity(txtDataSFN.selectedDate);
			}				
			
			registro.dataInclusaoSistema = dataInclusaoSistema;
			registro.dataInclusaoSFN = dataSFN;
			registro.nomePessoa = txtNomePessoa.text;
			registro.nomeApelido = txtApelido.text;
			registro.nomeCompleto = txtNomeCompleto.text;
			registro.descricao = txtObservacao.text;
			registro.autorizaConsultaBacen = new Booleano(chkAutorizaBacen.selected);
			registro.importador = new Booleano(chkImportador.selected);
			registro.exportador = new Booleano(chkExportador.selected);
			registro.atividadeEconomica = atividade;
			registro.cnae = cnae;						
			
			return registro;
		}
		
		public function retornoCarregarDefinicoes(event:ResultEvent): void {
			isGestorCadastro = event.result.dados.gestorCadastro;
			cboPerfilCadastro.dataProvider = event.result.dados.perfis;
			registroBkpCboPerfilCadastro = event.result.dados.perfis;
		}
		
		private function selecionarTipoPessoa(tipoPessoa:TipoPessoaVO):void {
				
			if (tipoPessoa.codTipoPessoa == FormatadorUtil.TIPO_PESSOA_FISICA){
				lblCpfCnpj.text = "CPF:"
				lblNomePessoa.text = "Nome:"
				lblNomeCompleto.text = "Nome Completo:"
				lblApelido.text = "Nome de Tratamento:"
				procurarAtividadeEconomica.enabled = false;
			} else {
				lblCpfCnpj.text = "CNPJ:"
				lblNomePessoa.text = "Razão Social:"
				lblNomeCompleto.text = "Razão Social Completa:"
				lblApelido.text = "Nome Fantasia:"
				procurarAtividadeEconomica.enabled = true;
			}			
		}

		public function verificarAlteracoes(registroBkp:PessoaCompartilhamentoVO):Boolean {
			
			var codigoAtividade: String = "";
			var codigoPerfil: Object = null;
			var codigoPerfilCombo: Object = null; 
			var cnae:String = "";
			var apelido: String = obterValorNaoNulo(registroBkp.nomeApelido);
			var observacao: String = obterValorNaoNulo(registroBkp.descricao);
												
			if(registroBkp.atividadeEconomica != null) {
				codigoAtividade = new String(registroBkp.atividadeEconomica.codigo);
			}
			if(registroBkp.perfilCadastro != null) {
				codigoPerfil = registroBkp.perfilCadastro.codigo;		
			}
			if(registroBkp.cnae != null) {
				cnae = StringUtils.trim(registroBkp.cnae.codigo);
			}
			
			return codigoPerfilCombo == codigoPerfil
				&& txtNomePessoa.text == registroBkp.nomePessoa
				&& txtNomeCompleto.text == registroBkp.nomeCompleto
				&& txtApelido.text == apelido
				&& txtObservacao.text == observacao
				&& chkAutorizaBacen.selected == registroBkp.autorizaConsultaBacen.valor
				&& procurarAtividadeEconomica.textoCodigo.text == codigoAtividade
				&& procurarCNAE.textoCodigo.text == cnae;
		}

		private function obterValorNaoNulo(valor:String):String {
			var retorno:String = "";
			if(valor != null) {
				retorno = valor;
			}
			return retorno;
		}
		
		private function mudaCategoriaCadastro(event:Event): void {
			var perfilCadastro: PerfilCadastroVO = PerfilCadastroVO( this.cboPerfilCadastro.selectedItem );
			if(perfilCadastro != null) {
				if (perfilCadastro.ordem < registroBkpPerfilCadastro.ordem)
				{
					Alerta.show(MENSAGEM_DE_RETROCESSO_CBOPERFILCADASTRO, "Atenção");
					voltarComboPerfilCadastro();
				}
				else if (perfilCadastro.ordem > registroBkpPerfilCadastro.ordem)
				{
					this.dispatchEvent(new Event(this.EVENTO_VALIDAR));
				}
			}
		}
		
		public function voltarComboPerfilCadastro():void {
			this.cboPerfilCadastro.dataProvider = registroBkpCboPerfilCadastro;
			this.cboPerfilCadastro.selectedIndex = 0;
			this.cboPerfilCadastro.procuraItemPorNome(registroBkpPerfilCadastro.codigo, "codigo");
			this.cboPerfilCadastro.setFocus();
		}
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------			
		public function configurarDestinosServicos(destino:DestinoVO):void{
			procurarAtividadeEconomica.configurarDestino(destino);
			procurarCNAE.configurarDestino(destino);
		}
	}
}