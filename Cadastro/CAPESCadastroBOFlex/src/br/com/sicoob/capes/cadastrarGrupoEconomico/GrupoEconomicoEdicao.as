package br.com.sicoob.capes.cadastrarGrupoEconomico {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.componentes.procurarCliente.SelecionarPessoa;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaInstituicaoVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoa.SelecionarPessoaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	
	public class GrupoEconomicoEdicao extends GrupoEconomicoEdicaoView {
		
		private var listaPessoas:SelecionarPessoaCAPES = null;
		private var janela:Janela;
		private var pessoas : ArrayCollection = new ArrayCollection();
		private var servicoValidacao : ServicoJava;
		private var pessoaTemp : PessoaInstituicaoVO;
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function GrupoEconomicoEdicao() {
			super();
		}
		
		protected override function init(event : FlexEvent) : void {
			
			super.init(event);
			this.btSelecionar.addEventListener(MouseEvent.CLICK, exibirTelaSelecionar);				
			this.btExcluir.addEventListener(MouseEvent.CLICK, excluirPessoa);	
			this.gridPessoas.dataProvider = this.pessoas;
			
			// servicoValidacao
			this.servicoValidacao = ServicoJavaUtil.getServico(
				"br.com.sicoob.capes.cadastro.fachada.GrupoEconomicoFachada",
				"Validando a pessoa selecionada...", ResultEvent.RESULT, onPessoaValidada);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servicoEdicao.configurarDestino(destino);
			servicoInclusao.configurarDestino(destino);
			servicoValidacao.configurarDestino(destino);
		}

		private function exibirTelaSelecionar(event : MouseEvent) : void {
			
			if(listaPessoas == null) {
				janela = new Janela();
				janela.title = "CADASTRO DE GRUPO ECONÔMICO";
				listaPessoas = new SelecionarPessoaCAPES();
				listaPessoas.fecharNoDuploClique = false;
				listaPessoas.addEventListener(SelecionarPessoa.EVENTO_REGISTROSELECIONADO, onPessoaAdicionada);
				listaPessoas.apenasClientes = true;
				listaPessoas.tipoPessoa = -1;
			}
			janela.removeAllChildren();
			janela.addChild(listaPessoas);
			janela.abrir(this, true);
			listaPessoas.setFocus();
		}

		private function excluirPessoa(event : MouseEvent) : void {
			var itens : ArrayCollection = new ArrayCollection(this.gridPessoas.selectedItems);
			if (itens.length > 0) {
				MostraCursor.setBusyCursor("Removendo integrantes...", 
					Application.application, MostraCursor.CURSOR_EXCLUIR);
					
				if (itens.length == this.pessoas.length) {
					this.pessoas.removeAll();
				} else {
					for (var i : int = 0; i < itens.length; i++) {
						var itemExclusao : GrupoEconomicoPessoaVO = GrupoEconomicoPessoaVO(itens.getItemAt(i));
						for (var index : int = 0; index < this.pessoas.length; index++) {
							var item : GrupoEconomicoPessoaVO = GrupoEconomicoPessoaVO(this.pessoas.getItemAt(index));
							if (itemExclusao.idGrupoEconomicoPessoa == item.idGrupoEconomicoPessoa) {
								this.pessoas.removeItemAt(index);
								break;
							}
						}
					}
				}
				MostraCursor.removeBusyCursor();
			}
		}
		
		/**
		 * Método chamado quando uma pessoa é selecionada na tela de seleção de pessoas
		 */
		private function onPessoaAdicionada(event : EventData) : void {
			
			var proxy : PessoaPlataformaVO = PessoaPlataformaVO(event.data);
			var grupoPessoa : GrupoEconomicoPessoaVO = null;
			for (var i : int = 0; i < this.pessoas.length; i++) {
				grupoPessoa = GrupoEconomicoPessoaVO(this.pessoas[i]);
				if (grupoPessoa.pessoaInstituicao.idPessoaInstituicao == proxy.idPessoaInstituicao) {
					return;
				}
			}
			for each (grupoPessoa in GrupoEconomicoVO(objeto).integrantes) {
				if (grupoPessoa.pessoaInstituicao.idPessoaInstituicao == proxy.idPessoaInstituicao) {
					this.pessoas.addItem(grupoPessoa);
					return;
				}
			}  
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = converterProxyParaPessoaInstituicao(proxy);
			dto.dados.grupoEconomico = objeto;
			this.servicoValidacao.getOperation("validarPessoa").send(dto);
		}
		
		private function onPessoaValidada(event : ResultEvent) : void {
			this.pessoaTemp = PessoaInstituicaoVO(event.result.dados.pessoa);
			if (event.result.dados.erro) {
				Alerta.show(event.result.dados.erro, "ATENÇÃO!", Alerta.ALERTA_PERGUNTA, null, adicionarPessoa);
			} else {
				adicionarPessoa();
			}
		}
		
		private function adicionarPessoa(event : MouseEvent = null):void {
			
			var grupoPessoa : GrupoEconomicoPessoaVO = new GrupoEconomicoPessoaVO();
			grupoPessoa.pessoaInstituicao = this.pessoaTemp;
			grupoPessoa.pessoaInstituicao.trocarGrupo = new Booleano(event != null);// true se o método foi chamado pelo Alerta
			this.pessoas.addItem(grupoPessoa);
			this.pessoaTemp = null;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
				habilitar : Boolean) : void {
					
			if (componente.id == "descricao") {
				habilitar = (MODO_INCLUSAO == modo);
			}
			super.configurarEstadoComponente(componente, habilitar);
		}		
		
		public override function set objeto(valor : Object) : void {
			var vo : GrupoEconomicoVO = GrupoEconomicoVO(valor);
			if (valor == null) {
				vo = new GrupoEconomicoVO();
				vo.integrantes = new ArrayCollection();
			}
			this.pessoas.removeAll();
			this.pessoas.addAll(vo.integrantes);
			super.objeto = vo; 
		}
		
		private function converterProxyParaPessoaInstituicao(proxy : PessoaPlataformaVO) : PessoaInstituicaoVO {
			
			var vo : PessoaInstituicaoVO = new PessoaInstituicaoVO();
			vo.idPessoaInstituicao = proxy.idPessoaInstituicao;
			
			/* Instituicao */
			vo.idInstituicao = proxy.idInstituicao;
			vo.idUnidadeInst = proxy.idUnidadeInst;
			
			/* Pessoa */
			vo.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(proxy).pessoa;
			
			return vo;
		}

		protected override function houveAlteracoes():Boolean {
			if (MODO_INCLUSAO == modo) {
				if (StringUtil.trim(this.descricao.text) != "") {
					return true;
				} else if (this.pessoas.length > 0) {
					return true;
				} 
			} else if (MODO_EDICAO == modo) {
				if (objeto.integrantes.length != this.pessoas.length) {
					return true;
				} else {
					for each(var voOrignal : GrupoEconomicoPessoaVO in objeto.integrantes) {
						var achou : Boolean = false;
						for each (var novoVO : GrupoEconomicoPessoaVO in this.pessoas) {
							if (voOrignal.pessoaInstituicao.idPessoaInstituicao 
									== novoVO.pessoaInstituicao.idPessoaInstituicao) {
								achou = true;
								break;
							} 
						}
						if (!achou) {
							return true;
						}
					}
				} 
			}
			return false;
		}

		protected override function limparFormIncluir():void {
			this.descricao.text = "";
			this.pessoas.removeAll();
		}

		protected override function preencherCampos():void {
			this.descricao.text = objeto.descricao;
			this.pessoas.removeAll();
			this.pessoas.addAll(objeto.integrantes);
		}

		protected override function montarDto():RequisicaoDTO {
			
			var vo : GrupoEconomicoVO = new GrupoEconomicoVO();
			
			// integrantes
			for each (var grupoPessoa : GrupoEconomicoPessoaVO in this.pessoas) {
				grupoPessoa.grupoEconomico = vo;
			} 
			vo.integrantes = this.pessoas;
			
			// outros dados
			vo.descricao = StringUtil.trim(this.descricao.text);
			if (MODO_EDICAO == modo) {
				vo.dataHoraCadastro = objeto.dataHoraCadastro;
				vo.idGrupoEconomico = objeto.idGrupoEconomico;
				vo.idInstituicao = objeto.idInstituicao;
			}
			
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.grupoEconomico = vo;
			return dto;
		}

	}
}