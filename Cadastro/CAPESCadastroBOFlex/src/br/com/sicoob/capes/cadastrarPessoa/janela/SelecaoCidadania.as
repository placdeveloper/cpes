package br.com.sicoob.capes.cadastrarPessoa.janela {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CidadaniaVO;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoFiscalVO;
	import br.com.sicoob.capes.comum.vo.entidades.NacionalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CidadaniaPK;
	import br.com.sicoob.capes.comum.vo.entidades.pk.EnderecoFiscalPK;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class SelecaoCidadania extends SelecaoCidadaniaView {
		
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		private static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.NacionalidadeFachada";
		private static const OPERACAO_PESQUISAR:String = "pesquisar";
		private static const MENSAGEM_FILTRO_MINIMO: String = 
			"O filtro precisa conter pelo menos 3 (três) caracteres!";
		private static const MENSAGEM_NACIONALIDADE_NAO_SELECIONADO: String = 
			"Selecione uma nacionalidade!";
		private static const MENSAGEM_NUMERO_DOCUMENTO_NAO_PREENCHIDO: String = 
			"Preencha o Documento Fiscal!";
		private static const MENSAGEM_NACIONALIDADE_JA_SELECIONADA: String = 
			"Nacionalidade já foi selecionada!";
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		private var servicoPesquisarNacionalidade:ServicoJava = new ServicoJava();
		
		private var apresentarDocumentoFiscal:Boolean = false;
		
		private var grid:Grid = null;
		
		public function SelecaoCidadania(apresentarDocumentoFiscal:Boolean, grid:Grid) {
			super();
			this.apresentarDocumentoFiscal = apresentarDocumentoFiscal;
			this.grid = grid;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evt:FlexEvent):void {
			inicializarServicos();
			servicoPesquisarNacionalidade.source = CLASSE_SERVICO;
			servicoPesquisarNacionalidade.mensagemEspera = "Pesquisando...";
			servicoPesquisarNacionalidade.bloquearOperacao = true;
			servicoPesquisarNacionalidade.addEventListener(ResultEvent.RESULT, retornoPesquisa);
			
			exibirJanela(true);
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			this.labelDocumentoFiscal.visible = apresentarDocumentoFiscal;
			this.txtDocumentoFiscal.visible = apresentarDocumentoFiscal;
			
			btnPesquisar.addEventListener(MouseEvent.CLICK, pesquisarNacionalidade);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnOk.addEventListener(MouseEvent.CLICK, acaoBotaoOk);
		}
		
		protected function retornoPesquisa(event:ResultEvent):void {
			tabela.dataProvider = event.result.dados.lista;
		}
		
		public function validarFiltro():Boolean {
			var retorno:Boolean = true;
			
			if(StringUtils.trim(txtDescricao.text).length < 3){
				Alerta.show(MENSAGEM_FILTRO_MINIMO, "Atenção", Alerta.ALERTA_OK, txtDescricao);
				retorno = false;
			}
			
			return retorno;
		}	
		
		protected function pesquisarNacionalidade(event:MouseEvent):void {
			if(validarFiltro()) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.descricao = txtDescricao.text;	
				servicoPesquisarNacionalidade.getOperation(OPERACAO_PESQUISAR).send(dto);
			}
		}
		
		
		protected function acaoBotaoOk(event:MouseEvent):void {
			if(validarPreenchimentoCampos()) {
				
				if(grid.dataProvider == null) {
					grid.dataProvider = new ArrayCollection();
				}

				if(apresentarDocumentoFiscal) {
					preencherEnderecoFiscal();
				} else {
					preencherCidadania();
				}
			}
		}
		
		private function preencherCidadania():void {
				var item:CidadaniaVO = new CidadaniaVO();
				item.nacionalidade = (tabela.selectedItem as NacionalidadeVO);
				item.pk = new CidadaniaPK();
				item.pk.codigoNacionalidade = tabela.selectedItem.codigo;
				item.pk.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
				(grid.dataProvider as ArrayCollection).addItem(item);
				fechar();
		}
		
		private function preencherEnderecoFiscal():void {
			var item:EnderecoFiscalVO = new EnderecoFiscalVO();
			item.nacionalidade = (tabela.selectedItem as NacionalidadeVO);
			item.codigoFiscal = txtDocumentoFiscal.text;
			item.pk = new EnderecoFiscalPK();
			item.pk.codigoNacionalidade = tabela.selectedItem.codigo;
			item.pk.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
			(grid.dataProvider as ArrayCollection).addItem(item);
			grid.validateNow();
			fechar();
		}
		
		private function validarPreenchimentoCampos():Boolean {
			var retorno:Boolean = true;
			
			for (var i:int = 0; i < 1; i++) {
				if(tabela.selectedItem == null){
					Alerta.show(MENSAGEM_NACIONALIDADE_NAO_SELECIONADO, "Atenção", Alerta.ALERTA_OK, txtDescricao);
					retorno = false;
					break;
				}
				
				if(apresentarDocumentoFiscal && StringUtils.trim(txtDocumentoFiscal.text).length == 0){
					Alerta.show(MENSAGEM_NUMERO_DOCUMENTO_NAO_PREENCHIDO, "Atenção", Alerta.ALERTA_OK, txtDocumentoFiscal);
					retorno = false;
					break;
				}
				
				var itens:ArrayCollection = grid.dataProvider as ArrayCollection;
				var nacionalidade:NacionalidadeVO = (tabela.selectedItem as NacionalidadeVO);
				if(apresentarDocumentoFiscal) {
					for each(var item:EnderecoFiscalVO in itens) {
						if(item.nacionalidade.descricao == nacionalidade.descricao) {
							Alerta.show(MENSAGEM_NACIONALIDADE_JA_SELECIONADA, "Atenção", Alerta.ALERTA_OK, btnOk);
							retorno = false;
							break;
						}
					}
				} else {
					for each(var iten:CidadaniaVO in itens) {
						if(iten.nacionalidade.descricao == nacionalidade.descricao) {
							Alerta.show(MENSAGEM_NACIONALIDADE_JA_SELECIONADA, "Atenção", Alerta.ALERTA_OK, btnOk);
							retorno = false;
							break;
						}
					}
				}
			}
			
			
			return retorno;
		}		
		
		private function exibirJanela(valor:Boolean):void {
			pegaJanela().visible = valor;
		}
		
		public function fechar(evento:Event = null):void {
			pegaJanela().fecharJanela();
		}
		
		private function inicializarServicos():void {
			if(this.destino == null || destino.tipo == DestinoVO.CANAL_NET) {
				PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestino);
			} else {
				configurarDestino();
			}
		}
		
		private function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				this.destino = destino;
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
			}
			servicoPesquisarNacionalidade.configurarDestino(this.destino);
		}
		
	}
}