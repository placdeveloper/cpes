package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RetornoDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.componentes.instituicao.vo.UnidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaProdutorVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.relatorioClienteProduto.RelClienteProdutoView;
	import br.com.sicoob.capes.relatorioClienteProduto.vo.RelClienteProdutoVO;

		
	public class RelClienteProduto extends RelClienteProdutoView
	{
		private static const SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.RelatorioClienteProdutoFachada";
		private var destinoVO:DestinoVO;
		private var servico:ServicoJava = new ServicoJava();
		private var arrMunicipio:ArrayCollection = new ArrayCollection();
		private var cooperativa:Number;
		
		public function RelClienteProduto()
		{
			super();
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO", UnidadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomico", GrupoEconomicoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CategoriaProdutor", CategoriaProdutorVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Localidade", LocalidadeVO);
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		private function init(event:FlexEvent):void{
			botOk.addEventListener(MouseEvent.CLICK,ok);
			botCancelar.addEventListener(MouseEvent.CLICK,cancelar);
			botFechar.addEventListener(MouseEvent.CLICK,fechar);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos["destinoCAPES"], destinoRecuperado);
			
			cboGerenteResp.labelFunction = cboGerenteResplf;
			dtNascInicio.selectedDate = null;
			dtNascFim.selectedDate = null;
		}
		
		private function destinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
			inicializaServico();
		}
		
		private function inicializaServico():void{
			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO;
			obterDefinicoes();
		}
		
		private function ok(event:MouseEvent):void{
			emitirRelatorio();	
		}
		
		private function cancelar(event:MouseEvent):void{
			obterDefinicoes();
			
			chkContaCapital.selected = false;
			chkContaCorrente.selected = false;
			chkAplicacoes.selected = false;
			chkPoupanca.selected = false;
			chkOpCredito.selected = false;
			chkCartaoCredito.selected = false;
			chkDebitoAutomatico.selected = false;
			txtRendaMin.text = null;
			txtRendaMax.text = null;
			dtNascInicio.selectedDate = null;
			dtNascFim.selectedDate = null;
			chkPessoaFisica.selected = false;
			chkPessoaJuridica.selected = false;
			chkDependenteSim.selected = false;
			chkDependenteNao.selected = false;
			chkMasculino.selected = false;
			chkFeminino.selected = false;
			rdOrdenarProCodigo.selected = true;
		}
		
		private function fechar(event:MouseEvent):void{
			this.fecharJanela();
		}
		
		private function emitirRelatorio():void {
			var dto:ParametroDTO = new ParametroDTO();	
			var vo:RelClienteProdutoVO = new RelClienteProdutoVO();
			
			if (validarCompos()) {
				
				if (cboCooperativaPac.selectedLabel != "TODOS") {
					vo.pac = cboCooperativaPac.selectedItem.codigo;	
				}
				vo.nomeCoopPac = cboCooperativaPac.selectedLabel;
				
				if (cboNucleo.selectedLabel != "TODOS") {
					vo.nucleo = cboNucleo.selectedItem.id;	
				}
				vo.nomeNucleo = cboNucleo.selectedLabel;
				
				/*if (cboGrupoEconomico.selectedLabel != "TODOS") {
					vo.grupoEconomico = cboGrupoEconomico.selectedItem.idGrupoEconomico;	
				}
				vo.nomeGrupoEconomico = cboGrupoEconomico.selectedLabel;*/
				
				if (cboGerenteResp.selectedLabel != "TODOS") {
					vo.gerente = cboGerenteResp.selectedItem.idPessoaLegado;	
				}
				vo.nomeGerente = cboGerenteResp.selectedLabel;
				
				if (chkPessoaFisica.selected == true && chkPessoaJuridica.selected == false) {
					vo.tipoPessoa = 0;
				} else if (chkPessoaFisica.selected == false && chkPessoaJuridica.selected == true) {
					vo.tipoPessoa = 1;
				}
				
				/* Filtros de negócio */
				vo.contaCapital = chkContaCapital.selected
				vo.contaCorrente = chkContaCorrente.selected
				vo.aplicacoes = chkAplicacoes.selected
				vo.poupanca = chkPoupanca.selected
				vo.opCredito = chkOpCredito.selected
				vo.cartaoCredito = chkCartaoCredito.selected	
				vo.debitoAutomatico = chkDebitoAutomatico.selected
				
				/* Outros filtros */
				vo.rendaMin = txtRendaMin.valor;
				vo.rendaMax = txtRendaMax.valor;
				vo.dtNascInicio = dtNascInicio.selectedDate;
				vo.dtNascFim = dtNascFim.selectedDate;
				
				if (chkDependenteSim.selected == true && chkDependenteNao.selected == false) {
					vo.dependente = 1
				} else if (chkDependenteSim.selected == false && chkDependenteNao.selected == true) {
					vo.dependente = 0
				}
				
				if (chkMasculino.selected == true && chkFeminino.selected == false) {
					vo.sexo = 1
				} else if (chkMasculino.selected == false && chkFeminino.selected == true) {
					vo.sexo = 0
				}
				
				if (cboMunicipio.selectedLabel != "TODOS") {
					vo.municipio = cboMunicipio.selectedItem.idLocalidade;			
				}
				vo.nomeMunicipio = cboMunicipio.selectedLabel;
				
				if (cboCategoriaProdutor.selectedLabel != "TODOS") {
					vo.categoriaProdutor = cboCategoriaProdutor.selectedItem.codigo;
				}
				vo.nomeCategoriaProdutor = cboCategoriaProdutor.selectedLabel
				
				if (rdOrdenarProCodigo.selected) {
					vo.ordenacao = 0
				} else if (rdOrdenarProNome.selected) {
					vo.ordenacao = 1
				}
				
				vo.cooperativa = cooperativa;
				
				dto.dados.parametros = vo;
				
				RelatorioUtil.create().emitirRelatorio
					("capes/relatorio/RelatorioClienteProdutoServicoRemote", dto, "RelClienteProduto", destinoVO);
			}
			
		}
			
		private function obterDefinicoes():void {
			servico.addEventListener(ResultEvent.RESULT,retornoObterDefinicoes);
			var dto:RetornoDTO = new RetornoDTO;
		 	servico.obterDefinicoes();	
			MostraCursor.setBusyCursor("Obter Definições",this);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			var arrMunicipio:ArrayCollection = new ArrayCollection();
			
			cooperativa = event.result.dados.numCooperativa;
				
			servico.removeEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			MostraCursor.removeBusyCursor();
			
			cboCooperativaPac.dataProvider = event.result.dados["listaPac"];
			//cboGrupoEconomico.dataProvider = event.result.dados["listaGrupoEconomico"];
			cboNucleo.dataProvider = event.result.dados["listaNucleo"];
			cboGerenteResp.dataProvider = event.result.dados["listaFuncionarioGerente"];
			cboCategoriaProdutor.dataProvider = event.result.dados["listaCategoriaProdutor"];
			cboMunicipio.dataProvider = event.result.dados["listaMunicipio"];
			
		}
		
		private function cboGerenteResplf(item:Object):String {
			return String(item.nomePessoaLegado);
		}
		
		private function validarCompos():Boolean{
			
			if (cboCooperativaPac.selectedLabel == ""){
				Alerta.show("Selecione uma cooperativa/PAC.","Atenção!");
				return false;
			}else if (cboNucleo.selectedLabel == ""){
				Alerta.show("Selecione um Núcleo.","Atenção!");
				return false;
			}/*else if (cboGrupoEconomico.selectedLabel == ""){
				Alerta.show("Selecione um Grupo Econômico.","Atenção!");
				return false;
			}*/else if (cboGerenteResp.selectedLabel == ""){
				Alerta.show("Selecione um Gerente Responsável.","Atenção!");
				return false;
			}else if (cboMunicipio.selectedLabel == ""){
				Alerta.show("Selecione um Município.","Atenção!");
				return false;
			}else if (cboCategoriaProdutor.selectedLabel == ""){
				Alerta.show("Selecione um Produtor Rural.","Atenção!");
				return false;
			}else if (txtRendaMin.valor > txtRendaMax.valor){
				Alerta.show("A renda mínima deve ser menor que a renda máxima.","Atenção!");
				return false
			}else if (dtNascInicio.selectedDate > dtNascFim.selectedDate){
				Alerta.show("A data início deve ser menor ou igual à data fim.","Atenção!");
				return false;
			}
			return true;
		}
	}
}