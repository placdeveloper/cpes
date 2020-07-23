package br.com.sicoob.capes.cadastrarTipoRelacionamento {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.campos.CampoNumerico;
	import br.com.bancoob.componentes.eventos.EventoBarraBotoes;
	import br.com.bancoob.componentes.eventos.EventoCadastro;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.componentes.input.Check;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRelacionamentoPessoaVO;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	public class TipoRelacionamentoPessoaEdicao extends TipoRelacionamentoPessoaEdicaoView {
		
		private static const MENSAGEM_CONFIRMACAO : String = "Atenção!! Este Tipo de Relacionamento "
			+ "será adicionado ao \"${1}\" como seu \"reverso\".\n\n"
			+ "Deseja Continuar?";
		
		static private const CLASSE_SERVICO : String = 
			"br.com.sicoob.capes.cadastro.fachada.TipoRelacionamentoPessoaFachada";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private var servico : ServicoJava;
		
		public function TipoRelacionamentoPessoaEdicao() {
			super();
			addEventListener(FlexEvent.ADD, obterDefinicoes);
			addEventListener(EventoCadastro.INCLUSAO, prepararNovaInclusao);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo próximo código...");
			servico.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			this.chkReversoOMesmo.addEventListener(Event.CHANGE, changeReversoOMesmo);
			this.codigoReverso.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, reversoSelecionado);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
			(this.servicoEdicao as ServicoJava).configurarDestino(destino);
			(this.servicoInclusao as ServicoJava).configurarDestino(destino);
			
			codigoReverso.classeServico = CLASSE_SERVICO;
			codigoReverso.configurarDestino(destino);
			
			obterDefinicoes();
		}
		
		private function prepararNovaInclusao(evento : EventoCadastro = null) : void {
			habilitarProcuraGeral(true);
			obterDefinicoes();
		}
		
		private function obterDefinicoes(event : Event = null) : void {
			if (initialized && (MODO_INCLUSAO == modo)) {
				servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
			}
		}
		
		private function retornoObterDefinicoes(event : ResultEvent) : void {
			this.codigo.valor = event.result.dados.codigo;
			this.nome.getFocus();
		}
		
		private function reversoSelecionado(evento : SelecaoEvent) : void {
			if (evento.objeto && evento.objeto.codigo) {
				Alerta.show("O Tipo de Relacionamento Reverso selecionado não pode ser utilizado pois já " + 
					"possui um \"reverso\" associado!", "Reverso já associado", Alerta.ALERTA_ERRO,
					null, limparReverso);
			}
		}
		
		private function limparReverso(evento : Event = null) : void {
			this.codigoReverso.limpar();
			this.codigoReverso.textoCodigo.getFocus();
		}
		
		private function changeReversoOMesmo(evento : Event = null) : void {
			if (this.chkReversoOMesmo.selected) {
				this.codigoReverso.limpar();
			}
			habilitarProcuraGeral(!this.chkReversoOMesmo.selected);
		} 
		
		private function habilitarProcuraGeral(habilita : Boolean) : void {
			this.codigoReverso.textoCodigo.editable = habilita;
			this.codigoReverso.botaoPesquisar.enabled = habilita;
		}
		
		protected override function houveAlteracoes() : Boolean {
			var alterado : Boolean = false;
			
			if (MODO_INCLUSAO == modo) {
				alterado = alterado || StringUtil.trim(this.codigo.text).length > 0;
				alterado = alterado || StringUtil.trim(this.nome.text).length > 0;
				alterado = alterado || this.chkReversoOMesmo.selected;
				alterado = alterado || StringUtil.trim(this.codigoReverso.textoCodigo.text).length > 0;
				alterado = alterado || this.chkTipoPessoaRelacionadaFisica.selected;
				alterado = alterado || this.chkTipoPessoaRelacionadaJuridica.selected;
				alterado = alterado || this.chkTipoPessoaRelacionamentoFisica.selected;
				alterado = alterado || this.chkTipoPessoaRelacionamentoJuridica.selected;
				alterado = alterado || this.rdbCompoeCartaoAssinatura.selectedValue != false;
				alterado = alterado || this.rdbHabilitaCapitalSocial.selectedValue != false;
				alterado = alterado || this.rdbHabilitaDadosRegistro.selectedValue != false;
				alterado = alterado || this.rdbHabilitaEnvioCCS.selectedValue != false;
				alterado = alterado || this.rdbHabilitaPoderes.selectedValue != false;
				alterado = alterado || this.rdbHabilitaProdutosBancoob.selectedValue != false;
				alterado = alterado || this.rdbPermitirCompartilhamento.selectedValue != false;
				alterado = alterado || this.rdbHabilitaVerificacaoPendencia.selectedValue != false;
				alterado = alterado || this.rdbAtivo.selectedValue != false;
				alterado = alterado || this.rdbPermitirDuplicidade.selectedValue != false;
			} else if (MODO_EDICAO == modo) {
				var vo : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(this.objeto);
				alterado = alterado || this.nome.text.toUpperCase() != vo.descricao.toUpperCase();
				if (vo.relacionamentoReverso) {
					alterado = alterado || this.codigoReverso.textoCodigo.valor != vo.relacionamentoReverso.codigo;
				} else {
					alterado = alterado ||  StringUtil.trim(this.codigoReverso.textoCodigo.text).length > 0;
				}
				
				// Tipo Pessoa Relacionada
				var tiposRelacionadas : ListCollectionView = new ArrayCollection();
				for each (var tipoPessoaRelacionada : TipoPessoaVO in vo.tiposPessoaRelacionada) {
					tiposRelacionadas.addItem(tipoPessoaRelacionada.codTipoPessoa);
				}
				alterado = alterado || isCheckTipoPessoaAlterado(
					tiposRelacionadas, FormatadorUtil.TIPO_PESSOA_FISICA,
					this.chkTipoPessoaRelacionadaFisica.selected);
				alterado = alterado || isCheckTipoPessoaAlterado(
					tiposRelacionadas, FormatadorUtil.TIPO_PESSOA_JURIDICA,
					this.chkTipoPessoaRelacionadaJuridica.selected);
				
				// Tipo Pessoa Relacionamento
				var tiposRelacionamento : ListCollectionView = new ArrayCollection();
				for each (var tipoPessoaRelacionamento : TipoPessoaVO in vo.tiposPessoaRelacionamento) {
					tiposRelacionamento.addItem(tipoPessoaRelacionamento.codTipoPessoa);
				}
				alterado = alterado || isCheckTipoPessoaAlterado(
					tiposRelacionamento, FormatadorUtil.TIPO_PESSOA_FISICA,
					this.chkTipoPessoaRelacionamentoFisica.selected);
				alterado = alterado || isCheckTipoPessoaAlterado(
					tiposRelacionamento, FormatadorUtil.TIPO_PESSOA_JURIDICA,
					this.chkTipoPessoaRelacionamentoJuridica.selected);
				
				alterado = alterado || this.rdbCompoeCartaoAssinatura.selectedValue != vo.compoeAssinatura.valor;
				alterado = alterado || this.rdbHabilitaCapitalSocial.selectedValue != vo.habilitaCapitalSocial.valor;
				alterado = alterado || this.rdbHabilitaDadosRegistro.selectedValue != vo.habilitaDadosRegistro.valor;
				alterado = alterado || this.rdbHabilitaEnvioCCS.selectedValue != vo.habilitaEnvioCCS.valor;
				alterado = alterado || this.rdbHabilitaPoderes.selectedValue != vo.habilitaPoderes.valor;
				alterado = alterado || this.rdbPermitirDuplicidade.selectedValue != vo.permiteDuplicidade.valor;
				alterado = alterado || this.rdbPermitirCompartilhamento.selectedValue != vo.permiteCompartilhamento.valor;
				alterado = alterado || this.rdbHabilitaProdutosBancoob.selectedValue != vo.habilitaProdutosBancoob.valor;
				alterado = alterado || this.rdbHabilitaVerificacaoPendencia.selectedValue != vo.habilitaVerificacaoPendencia.valor;
				alterado = alterado || this.rdbAtivo.selectedValue != vo.ativo.valor;
				
			}
			return alterado;
		}
		
		private function isCheckTipoPessoaAlterado(listaCodigos : ListCollectionView, 
												   tipoPessoa : int, checkSelecionado : Boolean) : Boolean {
			var possuiTipoPessoa : Boolean = listaCodigos.contains(tipoPessoa);
			return ((possuiTipoPessoa && !checkSelecionado) || (!possuiTipoPessoa && checkSelecionado));
		}
		
		protected override function limparFormIncluir():void {
			this.codigo.text = "";
			this.nome.text = "";
			this.codigoReverso.limpar();
			this.chkReversoOMesmo.selected = false;
			this.chkTipoPessoaRelacionadaFisica.selected = false;
			this.chkTipoPessoaRelacionadaJuridica.selected = false;
			this.chkTipoPessoaRelacionamentoFisica.selected = false;
			this.chkTipoPessoaRelacionamentoJuridica.selected = false;
			this.rdbCompoeCartaoAssinatura.selectedValue = false;
			this.rdbHabilitaCapitalSocial.selectedValue = false;
			this.rdbHabilitaDadosRegistro.selectedValue = false;
			this.rdbHabilitaEnvioCCS.selectedValue = false;
			this.rdbHabilitaPoderes.selectedValue = false;
			this.rdbHabilitaProdutosBancoob.selectedValue = false;
			this.rdbHabilitaVerificacaoPendencia.selectedValue = false;
			this.rdbPermitirCompartilhamento.selectedValue = false;
			this.rdbPermitirDuplicidade.selectedValue = false;
			this.rdbAtivo.selectedValue = false;
			
			if(servico != null){
				servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
			}
		}
		
		protected override function montarDto():RequisicaoDTO {
			
			var tipoPessoa : TipoPessoaVO = null;
			var vo : TipoRelacionamentoPessoaVO = new TipoRelacionamentoPessoaVO();
			vo.codigo = this.codigo.valor;
			vo.descricao = this.nome.text;
			
			// Relacionamento reverso
			if (this.chkReversoOMesmo.selected) {
				vo.relacionamentoReverso = vo;
			} else if(StringUtil.trim(this.codigoReverso.textoCodigo.text).length > 0) {
				vo.relacionamentoReverso = new TipoRelacionamentoPessoaVO();
				vo.relacionamentoReverso.codigo = this.codigoReverso.textoCodigo.valor;
			}
			
			// Tipos de Pessoas Relacionadas
			vo.tiposPessoaRelacionada = new ArrayCollection();
			if (this.chkTipoPessoaRelacionadaFisica.selected) {
				tipoPessoa = new TipoPessoaVO();
				tipoPessoa.codTipoPessoa =  FormatadorUtil.TIPO_PESSOA_FISICA;
				vo.tiposPessoaRelacionada.addItem(tipoPessoa);
			}
			if (this.chkTipoPessoaRelacionadaJuridica.selected) {
				tipoPessoa = new TipoPessoaVO();
				tipoPessoa.codTipoPessoa =  FormatadorUtil.TIPO_PESSOA_JURIDICA;
				vo.tiposPessoaRelacionada.addItem(tipoPessoa);
			}
			
			// Tipos de Pessoas Relacionamento
			vo.tiposPessoaRelacionamento = new ArrayCollection();
			if (this.chkTipoPessoaRelacionamentoFisica.selected) {
				tipoPessoa = new TipoPessoaVO();
				tipoPessoa.codTipoPessoa =  FormatadorUtil.TIPO_PESSOA_FISICA;
				vo.tiposPessoaRelacionamento.addItem(tipoPessoa);
			}
			if (this.chkTipoPessoaRelacionamentoJuridica.selected) {
				tipoPessoa = new TipoPessoaVO();
				tipoPessoa.codTipoPessoa =  FormatadorUtil.TIPO_PESSOA_JURIDICA;
				vo.tiposPessoaRelacionamento.addItem(tipoPessoa);
			}
			vo.compoeAssinatura = new Booleano(this.rdbCompoeCartaoAssinatura.selectedValue);
			vo.habilitaCapitalSocial = new Booleano(this.rdbHabilitaCapitalSocial.selectedValue);
			vo.habilitaDadosRegistro = new Booleano(this.rdbHabilitaDadosRegistro.selectedValue);
			vo.habilitaEnvioCCS = new Booleano(this.rdbHabilitaEnvioCCS.selectedValue);
			vo.habilitaPoderes = new Booleano(this.rdbHabilitaPoderes.selectedValue);
			vo.habilitaProdutosBancoob = new Booleano(this.rdbHabilitaProdutosBancoob.selectedValue);
			vo.habilitaVerificacaoPendencia = new Booleano(this.rdbHabilitaVerificacaoPendencia.selectedValue);
			vo.permiteCompartilhamento = new Booleano(this.rdbPermitirCompartilhamento.selectedValue);
			vo.permiteDuplicidade = new Booleano(this.rdbPermitirDuplicidade.selectedValue);
			vo.ativo = new Booleano(this.rdbAtivo.selectedValue);
			
			// Monta o DTO, com o VO
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoRelacionamentoPessoa = vo;
			if (MODO_EDICAO == modo) {
				dto.dados.reversoOriginal =  TipoRelacionamentoPessoaVO(this.objeto).relacionamentoReverso;
			}
			return dto;
		}
		
		protected override function configurarEstadoComponente(componente:UIComponent, habilitar:Boolean):void {
			super.configurarEstadoComponente(componente, habilitar);
			if ((MODO_EDICAO == modo) && (componente.id == this.codigo.id)) {
				(componente as CampoNumerico).editable = false;
			} else if ((MODO_EDICAO == modo) && (componente.id == this.chkReversoOMesmo.id)) {
				Check(componente).enabled = false;
			}
		}
		
		protected override function preencherCampos() : void {
			
			var vo : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(this.objeto);
			this.codigo.valor = vo.codigo;
			this.nome.text = vo.descricao;
			this.chkReversoOMesmo.selected = false;
			if (vo.relacionamentoReverso) {
				this.codigoReverso.textoCodigo.valor = vo.relacionamentoReverso.codigo;
				this.codigoReverso.textoDescricao.text = vo.relacionamentoReverso.descricao;
			} else {
				this.codigoReverso.limpar();
			}
			
			// Preenche tipos de pessoas (relacionamento e relacionadas)
			var tiposPessoa : ArrayCollection = new ArrayCollection();
			
			// Tipos de Pessoas Relacionadas
			for each (var tipoPessoaRelacionada : TipoPessoaVO in vo.tiposPessoaRelacionada) {
				tiposPessoa.addItem(tipoPessoaRelacionada.codTipoPessoa);
			}
			this.chkTipoPessoaRelacionadaFisica.selected = tiposPessoa.contains(FormatadorUtil.TIPO_PESSOA_FISICA);
			this.chkTipoPessoaRelacionadaJuridica.selected = tiposPessoa.contains(FormatadorUtil.TIPO_PESSOA_JURIDICA);
			
			// Tipos de Pessoas Relacionamento
			tiposPessoa.removeAll();
			for each (var tipoPessoaRelacionamento : TipoPessoaVO in vo.tiposPessoaRelacionamento) {
				tiposPessoa.addItem(tipoPessoaRelacionamento.codTipoPessoa);
			}
			this.chkTipoPessoaRelacionamentoFisica.selected = tiposPessoa.contains(FormatadorUtil.TIPO_PESSOA_FISICA);
			this.chkTipoPessoaRelacionamentoJuridica.selected = tiposPessoa.contains(FormatadorUtil.TIPO_PESSOA_JURIDICA);
			
			this.rdbCompoeCartaoAssinatura.selectedValue = vo.compoeAssinatura.valor;
			this.rdbHabilitaCapitalSocial.selectedValue = vo.habilitaCapitalSocial.valor;
			this.rdbHabilitaDadosRegistro.selectedValue = vo.habilitaDadosRegistro.valor;
			this.rdbHabilitaEnvioCCS.selectedValue = vo.habilitaEnvioCCS.valor;
			this.rdbHabilitaPoderes.selectedValue = vo.habilitaPoderes.valor;
			this.rdbHabilitaProdutosBancoob.selectedValue = vo.habilitaProdutosBancoob.valor;
			this.rdbHabilitaVerificacaoPendencia.selectedValue = vo.habilitaVerificacaoPendencia.valor;
			this.rdbPermitirCompartilhamento.selectedValue = vo.permiteCompartilhamento.valor;
			this.rdbPermitirDuplicidade.selectedValue = vo.permiteDuplicidade.valor;
			this.rdbAtivo.selectedValue = vo.ativo.valor;
		}
		
		protected override function botaoOkPressionado(evento : EventoBarraBotoes) : void {
			
			executarSeValido(exibirMensagemConfirmacao);
		}
		
		private function exibirMensagemConfirmacao() : void {
			if ((StringUtil.trim(this.codigoReverso.textoCodigo.text).length > 0 
				&& (this.codigoReverso.textoCodigo.text != this.codigo.text))) {
				var mensagem : String = MENSAGEM_CONFIRMACAO.replace(/\$\{1\}/, 
					this.codigoReverso.textoDescricao.text);
				Alerta.show(mensagem, "Alteração em registro associado", Alerta.ALERTA_PERGUNTA,
					null, persistir);
			} else {
				persistir();
			}
		}
		
		private function persistir(evento : Event = null) : void {
			gravar();
		}
	}
}