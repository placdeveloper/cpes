package br.com.sicoob.capes.cadastrarBem.abas{
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.ProcurarGeralV2;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarBem.IAbaForm;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;
	
	public class abaImovel extends abaImovelView implements IAbaForm {
		
		private var ufMunicipio:String;
		private var ufComarca:String;
		private static var CODIGO_TIPO_BEM_IMOVEL:Number = 1;
		
		public function init():void {
			inicializarServicos();
		}
    	
    	public function carregarDefinicoes(evt:ResultEvent):void {
    		this.localizacao.dataProvider = obterLocalizacoes();
    	}
    	
    	private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoPortalRecuperado);
		}
		
		private function onDestinoPortalRecuperado(destino:DestinoVO):void {
			initComponenteProcuraCidade(procuraMunicipio, destino);
			initComponenteProcuraCidade(procuraComarca,destino);
			
			procuraMunicipio.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, atribuirUFMunicipio); 
			procuraComarca.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, atribuirUFComarca); 
		}
		
		private function initComponenteProcuraCidade(procurarGeral:ProcurarGeralV2, destino:DestinoVO):void {
			procurarGeral.configurarDestino(destino);
			procurarGeral.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, atribuirUFMunicipio); 
			
			var obj: Object = new Object();
			obj.nome = "IDTIPOLOCALIDADE";
			obj.valor = "";

			var arr: ArrayCollection = new ArrayCollection();
			arr.addItem(obj);

			procurarGeral.parametros = arr;
            procurarGeral.filtroAdicional = 90051;
		}
		    	
    	private function atribuirUFMunicipio(evnt:SelecaoEvent):void {
    		if (evnt.objeto != null)
    			ufMunicipio = evnt.objeto.UF;
    	}
    	
    	private function atribuirUFComarca(evnt:SelecaoEvent):void {
    		if (evnt.objeto != null)
    			ufMunicipio = evnt.objeto.UF;
    	}
    	
    	public function obterLocalizacoes():ListCollectionView {
    		var optionRural:Object = new Object();
    		var optionUrbano:Object = new Object();
    		
    		optionRural.codigo = "R";
    		optionRural.descricao = "RURAL";
    		
    		optionUrbano.codigo = "U";
    		optionUrbano.descricao = "URBANO";
    		
    		var localizacoes:ArrayCollection = new ArrayCollection();
    		localizacoes.addItem(optionRural);
    		localizacoes.addItem(optionUrbano);
    		
    		return localizacoes;
    	}
    	
		public function atualizarCamposRegistro(bem:BemVO):void {
			if (bem.subTipo != null 
				&& bem.subTipo.codTipoBem == CODIGO_TIPO_BEM_IMOVEL 
				&& bem is BemImovelVO) {
				var bemImovel:BemImovelVO = bem as BemImovelVO;
				bemImovel.denominacao = this.denominacao.text;

				if (this.localizacao.selectedItem != null) 
					bemImovel.codLocalizacao = this.localizacao.selectedItem.codigo;

				bemImovel.benfeitoria = this.benfeitorias.text;
				bemImovel.valorBenfeitoria = this.valorBenfeitorias.valor;
				bemImovel.area = this.areaTotal.valor;
				bemImovel.unidadeMedida = this.unidadeMedida.selectedItem as UnidadeMedidaVO;
				
				bemImovel.idLocalidadeImovel = procuraMunicipio.txtValor.valor;
				bemImovel.siglaUFMunicipio = ufMunicipio;
				
				bemImovel.idLocalidadeComarca = procuraComarca.txtValor.valor;
				bemImovel.siglaUFComarca = ufComarca;
			}
    	}

    	public function preencherCampos(bem:BemVO):void {

			if (bem.subTipo != null 
				&& bem.subTipo.tipoBem.codigo == CODIGO_TIPO_BEM_IMOVEL 
				&& bem is BemImovelVO) {//Se for imóvel

				this.enabled = true;
    			var bemImovel:BemImovelVO = bem as BemImovelVO;
    			this.denominacao.text = bemImovel.denominacao;
    			this.localizacao.procuraItemPorNome(bemImovel.codLocalizacao,"codigo");
    			this.benfeitorias.text = bemImovel.benfeitoria;
    			this.valorBenfeitorias.valor = bemImovel.valorBenfeitoria;
    			this.areaTotal.valor = bemImovel.area;
    			
    			if (bemImovel.unidadeMedida != null)
    				this.unidadeMedida.procuraItemPorNome(bemImovel.unidadeMedida.codigo,"codigo");
    				
    			this.ufMunicipio = bemImovel.siglaUFMunicipio;
    			this.ufComarca = bemImovel.siglaUFComarca;

				var idLocalidadeImovel:Number = bemImovel.idLocalidadeImovel;
				if (!isNaN(idLocalidadeImovel) && idLocalidadeImovel != 0) {
					this.procuraMunicipio.txtValor.valor = idLocalidadeImovel;
					this.procuraMunicipio.pesquisar();
				} else {
					this.procuraMunicipio.lblDescricao.text = bemImovel.municipio + " - " + bemImovel.siglaUFMunicipio;
				}

				var idLocalidadeComarca:Number = bemImovel.idLocalidadeComarca;
				if (!isNaN(idLocalidadeComarca) && idLocalidadeComarca != 0) {
					this.procuraComarca.txtValor.valor = idLocalidadeComarca;
					this.procuraComarca.pesquisar();
				} else {
					this.procuraComarca.lblDescricao.text = bemImovel.comarca + " - " + bemImovel.siglaUFComarca;
				}

	   		} else {
	   			limparFormIncluir();
	   		}
    	}

    	public function limparFormIncluir():void {

			this.enabled = false;
			this.denominacao.text = "";
			this.localizacao.selectedIndex = 0;
			this.benfeitorias.text = "";
			this.valorBenfeitorias.valor = 0;
			this.areaTotal.valor = 0;
			this.unidadeMedida.selectedIndex = 0;
			this.procuraComarca.limpar();
			this.procuraMunicipio.limpar();
    	}

    	public function verificarAlteracoes(bem:BemVO):Boolean {

			if (bem.subTipo != null 
				&& bem.subTipo.codTipoBem == CODIGO_TIPO_BEM_IMOVEL 
				&& bem is BemImovelVO) {
				var bemImovel:BemImovelVO = bem as BemImovelVO;

				if (bemImovel.denominacao == this.denominacao.text
					&& this.localizacao.selectedItem != null
					&& bemImovel.codLocalizacao == this.localizacao.selectedItem.codigo
					&& bemImovel.benfeitoria == this.benfeitorias.text	
					&& bemImovel.valorBenfeitoria == this.valorBenfeitorias.valor
					&& bemImovel.area == this.areaTotal.valor
					&& this.unidadeMedida.selectedItem != null
					&& bemImovel.unidadeMedida != null
					&& (bemImovel.unidadeMedida.codigo == this.unidadeMedida.selectedItem.codigo)) {
					return true;
				}

				return false;
			} 

    		return true;
    	}

    	public function ativarAba(object:Object):Boolean {
    		var subTipoBem:SubTipoBemVO = SubTipoBemVO(object);
    		return subTipoBem != null && subTipoBem.codTipoBem == CODIGO_TIPO_BEM_IMOVEL;
    	}
	}
}