package br.com.sicoob.capes.cadastrarGrupoEconomicoNovo {
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.componentes.procurarCliente.SelecionarPessoa;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoAutomaticoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoManualPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoGrupoEconomicoVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoa.SelecionarPessoaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	
	public class GrupoEconomicoNovoEdicao extends GrupoEconomicoNovoEdicaoView {
		
		private const janelaExclusao:JanelaExclusao = new JanelaExclusao();
		private const listaExclusao:ArrayCollection = new ArrayCollection();
		private var selecionarPessoa:SelecionarPessoaCAPES = null;
		private var janela:Janela;
		private var servicoValidacao:ServicoJava;
		private var pessoaTemp:PessoaCompartilhamentoVO;
		private var grupoAutomaticoTemp:GrupoEconomicoNovoVO;
		public var permitirAlterarNomeAutomatico:Boolean;
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		protected override function init(event : FlexEvent):void {
			super.init(event);
			aplicarPermissaoComponente(btAdicionar);
			aplicarPermissaoComponente(btExcluir);
			this.btAdicionar.addEventListener(MouseEvent.CLICK, exibirTelaSelecionar);				
			this.btExcluir.addEventListener(MouseEvent.CLICK, btExcluirPessoaAcionado);
			
			// servicoValidacao
			this.servicoValidacao = ServicoJavaUtil.getServico(
				"br.com.sicoob.capes.cadastro.fachada.GrupoEconomicoNovoFachada",
				"Validando a pessoa selecionada...", ResultEvent.RESULT, onPessoaValidada);
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
			focarComponente(txtNome);
			txtNome.setSelection(0,0);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servicoEdicao.configurarDestino(destino);
			servicoInclusao.configurarDestino(destino);
			servicoValidacao.configurarDestino(destino);
		}

		private function exibirTelaSelecionar(event : MouseEvent) : void {
			
			if(selecionarPessoa == null) {
				janela = new Janela();
				janela.title = "CADASTRO DE GRUPO ECONÔMICO";
				selecionarPessoa = new SelecionarPessoaCAPES();
				selecionarPessoa.fecharNoDuploClique = false;
				selecionarPessoa.addEventListener(SelecionarPessoa.EVENTO_REGISTROSELECIONADO, onPessoaAdicionada);
				selecionarPessoa.apenasClientes = false;
				selecionarPessoa.tipoPessoa = -1;
			}
			janela.removeAllChildren();
			janela.addChild(selecionarPessoa);
			janela.abrir(this, true);
			selecionarPessoa.setFocus();
		}

		private function btExcluirPessoaAcionado(event:MouseEvent) : void {
			var itemSelecionado:Object = gridPessoas.selectedItem;
			if (itemSelecionado && itemSelecionado is GrupoEconomicoNovoManualPessoaVO) {
				janelaExclusao.textoTitulo = "EXCLUSÃO DE PARTICIPANTES DO GRUPO ECONÔMICO";
				janelaExclusao.callbackConfirmacao = removerPessoa;
				janelaExclusao.abrir(this,true);
			}
		}
		
		private function removerPessoa(motivoExclusao:String):void {
			MostraCursor.setBusyCursor("Removendo integrante...", 
				Application.application, MostraCursor.CURSOR_EXCLUIR);
			var itemSelecionado:GrupoEconomicoNovoManualPessoaVO = gridPessoas.selectedItem as GrupoEconomicoNovoManualPessoaVO;
			if (itemSelecionado.id && modo == MODO_EDICAO) {
				itemSelecionado.motivoExclusao = motivoExclusao;
				listaExclusao.addItem(itemSelecionado);
			}
			var indexItem:int = listaPessoasTree.getItemIndex(itemSelecionado);
			listaPessoasTree.removeItemAt(indexItem);
			MostraCursor.removeBusyCursor();
			janelaExclusao.fecharJanela();
		}
		
		/**
		 * Método chamado quando uma pessoa é selecionada na tela de seleção de pessoas
		 */
		private function onPessoaAdicionada(event : EventData):void {
			var proxy:PessoaPlataformaVO = PessoaPlataformaVO(event.data);
			if(proxy) {
				var grupoPessoa:GrupoEconomicoNovoManualPessoaVO;
				// Verifica se a pessoa já está adicionada na tela 
				for each (grupoPessoa in this.listaPessoasTree) {
					if (grupoPessoa.pessoaCompartilhamento.pessoa.idPessoa == proxy.idPessoa) {
						Alerta.show("Pessoa já pertence ao grupo", "Atenção", Alerta.ALERTA_INFORMACAO);
						return;
					} else if (grupoPessoa.integrantesAutomatico) {
						for each (var grupoAutomaticoPessoa:GrupoEconomicoNovoAutomaticoPessoaVO in grupoPessoa.integrantesAutomatico) {
							if (grupoAutomaticoPessoa.pessoaCompartilhamento.pessoa.idPessoa == proxy.idPessoa) {
								Alerta.show("Pessoa já pertence ao grupo", "Atenção", Alerta.ALERTA_INFORMACAO);
								return;
							}
						}
					}
				}
				for each (grupoPessoa in GrupoEconomicoNovoVO(objeto).integrantesManual) {
					if (grupoPessoa.pessoaCompartilhamento.pessoa.idPessoa == proxy.idPessoa) {
						if(listaExclusao.contains(grupoPessoa)) {
							grupoPessoa.motivoExclusao = null;
							listaExclusao.removeItemAt(listaExclusao.getItemIndex(grupoPessoa));
						}
						this.listaPessoasTree.addItem(grupoPessoa);
						return;
					}
				}
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(proxy);
				dto.dados.grupoEconomico = objeto;
				this.servicoValidacao.getOperation("validarPessoa").send(dto);
			}
		}
		
		private function onPessoaValidada(event : ResultEvent) : void {
			this.pessoaTemp = PessoaCompartilhamentoVO(event.result.dados.pessoa);
			this.grupoAutomaticoTemp = GrupoEconomicoNovoVO(event.result.dados.grupoAutomatico);
			var origemGrupoTemp:TipoGrupoEconomicoVO = TipoGrupoEconomicoVO(event.result.dados.origemGrupo);
			if (event.result.dados.erro) {
				if(origemGrupoTemp.codigo == 1) {
					Alerta.show(event.result.dados.erro, "ATENÇÃO!", Alerta.ALERTA_PERGUNTA, null,adicionarPessoa,naoAdicionarPessoa);
				} else {
					Alerta.show(event.result.dados.erro, "ATENÇÃO!", Alerta.ALERTA_INFORMACAO, null, naoAdicionarPessoa);
				}
			} else {
				adicionarPessoa();
			}
		}
		
		private function naoAdicionarPessoa(event:MouseEvent = null):void {
			this.pessoaTemp = null;
			this.grupoAutomaticoTemp = null;
		}
		
		private function adicionarPessoa(event:MouseEvent = null):void {
			var grupoPessoa:GrupoEconomicoNovoManualPessoaVO = new GrupoEconomicoNovoManualPessoaVO();
			grupoPessoa.grupoEconomico = new GrupoEconomicoNovoVO;
			grupoPessoa.grupoEconomico.tipo = new TipoGrupoEconomicoVO;
			grupoPessoa.grupoEconomico.tipo.codigo = 2;
			grupoPessoa.grupoEconomico.tipo.descricao = "Manual";
			grupoPessoa.grupoEconomicoAutomatico = this.grupoAutomaticoTemp;
			grupoPessoa.pessoaCompartilhamento = this.pessoaTemp;
			this.listaPessoasTree.addItem(grupoPessoa);
			naoAdicionarPessoa(); // chamar para limpar os temporarios
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
				habilitar : Boolean) : void {
			if (componente.id == "txtNome") {
				if(modo == MODO_INCLUSAO || (modo == MODO_EDICAO && objeto.tipo.codigo == 2) || (modo == MODO_EDICAO && objeto.tipo.codigo == 1 && permitirAlterarNomeAutomatico)) {
					habilitar = true;
				} else {
					habilitar = false;
				}
			}
			super.configurarEstadoComponente(componente, habilitar);
		}		
		
		public override function set objeto(valor:Object):void {
			var vo:GrupoEconomicoNovoVO = GrupoEconomicoNovoVO(valor);
			if (valor == null) {
				vo = new GrupoEconomicoNovoVO();
				vo.tipo = new TipoGrupoEconomicoVO;
				vo.tipo.codigo = 2;
			}
			this.listaPessoasTree.removeAll();
			listaExclusao.removeAll();
			super.objeto = vo; 
		}
		
		protected override function houveAlteracoes():Boolean {
			if (MODO_INCLUSAO == modo) {
				if (StringUtil.trim(this.txtNome.text) != "") {
					return true;
				} else if (this.listaPessoasTree.length > 0) {
					return true;
				} 
			} else if (MODO_EDICAO == modo) {
				if (StringUtil.trim(this.txtNome.text) != objeto.nome) {
					return true;
				}
				if(objeto.tipo.codigo == 2) {
					if (objeto.integrantesManual.length != this.listaPessoasTree.length) {
						return true;
					} else {
						for each(var voOrignal:GrupoEconomicoNovoManualPessoaVO in objeto.integrantesManual) {
							var achou:Boolean = false;
							for each (var novoVO:GrupoEconomicoNovoManualPessoaVO in this.listaPessoasTree) {
								if (voOrignal.pessoaCompartilhamento.idPessoaCompartilhamento 
										== novoVO.pessoaCompartilhamento.idPessoaCompartilhamento) {
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
			}
			return false;
		}

		protected override function limparFormIncluir():void {
			this.txtNome.text = "";
			this.listaPessoasTree.removeAll();
			listaExclusao.removeAll();
		}

		protected override function preencherCampos():void {
			this.txtNome.text = objeto.nome;
			preencherCamposPessoas();
		}
		
		private function preencherCamposPessoas():void {
			this.listaPessoasTree.removeAll();
			listaExclusao.removeAll();
			if(objeto.tipo && objeto.tipo.codigo == 1) {
				var lista:ArrayCollection = new ArrayCollection;
				var chaveIdPessoaCompartilhamento:Object = new Object;
				for each(var pessoaAutomatico:GrupoEconomicoNovoAutomaticoPessoaVO in objeto.integrantesAutomatico) {
					if(!chaveIdPessoaCompartilhamento[pessoaAutomatico.pessoaCompartilhamento.idPessoaCompartilhamento]) {
						lista.addItem(pessoaAutomatico);
						chaveIdPessoaCompartilhamento[pessoaAutomatico.pessoaCompartilhamento.idPessoaCompartilhamento] = pessoaAutomatico.pessoaCompartilhamento;
					}
				}
				this.listaPessoasTree.addAll(lista);
			} else {
				this.listaPessoasTree.addAll(objeto.integrantesManual);
			}
			
		}
		
		protected override function montarDto():RequisicaoDTO {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var vo:GrupoEconomicoNovoVO = new GrupoEconomicoNovoVO();
			
			// outros dados
			vo.nome = StringUtil.trim(this.txtNome.text);
			if (MODO_EDICAO == modo) {
				vo.dataHoraCadastro = objeto.dataHoraCadastro;
				vo.id = objeto.id;
				vo.idUsuarioCadastro = objeto.idUsuarioCadastro;
				vo.tipo = objeto.tipo;
				vo.integrantesAutomatico = objeto.integrantesAutomatico;
				vo.integrantesManual = objeto.integrantesManual;
				vo.integrantesManualExclusao = listaExclusao;
			}
			
			// integrantes
			for each (var grupoPessoa:Object in this.listaPessoasTree) {
				if(grupoPessoa is GrupoEconomicoNovoManualPessoaVO) {
					grupoPessoa.grupoEconomico = vo;
				}
			}
			
			if(modo == MODO_INCLUSAO || (modo == MODO_EDICAO && objeto.tipo.codigo == 2)) {
				vo.integrantesManual = this.listaPessoasTree;
			}
			
			dto.dados.grupoEconomico = vo;
			return dto;
		}
		
		/**
		 * Método responsável pelas validações ( ContainerValidavel )
		 */
		public override function realizarValidacao():ResultadoValidacaoDTO {
			var resultado:ResultadoValidacaoDTO = super.realizarValidacao();
			if(resultado.valido) {
				validarLista(resultado);
			}
			return resultado;
		}
		
		private function validarLista(resultadoValidacao:ResultadoValidacaoDTO):void {
			if((modo == MODO_INCLUSAO || (modo == MODO_EDICAO && objeto.tipo.codigo == 2)) && this.listaPessoasTree.length < 2) {
				resultadoValidacao.valido = false;
				resultadoValidacao.adicionarMensagem("É necessário mais de um participante no grupo.");
				resultadoValidacao.campoFoco = gridPessoas;
			}
		}

	}
}