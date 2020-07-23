package br.com.sicoob.capes.relatorioGrupoEconomicoNovo {
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.sicoob.capes.comum.util.DateUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	
	public class RelatorioGrupoEconomicoNovo extends RelatorioGrupoEconomicoNovoView {
		
		private static const DESTINO_CAPES : String = "destinoCAPES";
		
		public function RelatorioGrupoEconomicoNovo() {
			include '../utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario",FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo",NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao",FuncaoVO);
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
		}
		
		private function onCreateComplete(event:FlexEvent): void {
			var modulo:Modulo = this.parent.parent as Modulo;
			btnGerarRelatorio.addEventListener(MouseEvent.CLICK, btnEmitirRelatorio);
			btnLimpar.addEventListener(MouseEvent.CLICK, acaoBtnLimpar);
			btnFechar.addEventListener(MouseEvent.CLICK, acaoBtnFechar);
			
			componenteCentraisSingulares.configurarDestino(modulo.destino);
			campoCpfCnpj.configurarDestino(modulo.destino);
			acaoBtnLimpar();
			campoCpfCnpj.txtNome.width = campoCpfCnpj.width - campoCpfCnpj.txtNome.x;
		}
		
		protected function acaoBtnFechar(event:MouseEvent):void {
			var modulo:Modulo = this.parent.parent as Modulo;
			modulo.fecharJanela();
		}
		
		protected function acaoBtnLimpar(event:MouseEvent = null):void {
			componenteCentraisSingulares.limpar();
			tipoOrigemGrupo.selectedValue = "0";
			tipoVigencia.selectedValue = "1";
			campoCpfCnpj.limpar();
			dataInicio.selectedDate = null;
			dataFim.selectedDate = null;
			dataInicio.compMask.errorString = "";
			dataFim.compMask.errorString = "";
		}
		
		private function btnEmitirRelatorio(event:MouseEvent):void{
			if(dataInicio.compMask.errorString) {
				Alerta.show(dataInicio.compMask.errorString, "Atenção", Alerta.ALERTA_OK,  dataInicio);
				return ;
			}
			if(dataFim.compMask.errorString) {
				Alerta.show(dataFim.compMask.errorString, "Atenção", Alerta.ALERTA_OK,  dataFim);
				return ;
			}
			if(dataInicio.selectedDate && dataFim.selectedDate && ObjectUtil.dateCompare(dataInicio.selectedDate, dataFim.selectedDate) > 0 ) {
				Alerta.show("Data final está menor do que a data inicial!", "Atenção", Alerta.ALERTA_OK,  dataFim);
				return ;
			}
			if(tipoVigencia.selectedValue != 2 && diasFiltroHistorico != 0) {
				if(!dataInicio.selectedDate && !dataFim.selectedDate) {
					Alerta.show("Data inicial e final devem ser fonecidas em pesquisas históricas!", "Atenção", Alerta.ALERTA_OK,  dataFim);
					return ;
				}
				var dataFimTratada:Date = DateUtil.minusDays(diasFiltroHistorico, dataFim.selectedDate);
				if(ObjectUtil.dateCompare(dataInicio.selectedDate, dataFimTratada) < 0) {
					Alerta.show("O intervalo de dias não pode ser menor que zero em pesquisas históricas!", "Atenção", Alerta.ALERTA_OK,  dataFim);
					return ;
				}
					
			}
			emitirRelatorio();
		}
		
		private function emitirRelatorio():void{
			var modulo:Modulo = this.parent.parent as Modulo;
			var dto:ParametroDTO = new ParametroDTO();
			
			dto.dados.idInstituicao = componenteCentraisSingulares.singular.idInstituicao;
			dto.dados.numSingular = componenteCentraisSingulares.singular.numeroCooperativa;
			dto.dados.numCentral = componenteCentraisSingulares.central.numeroCooperativa;
			dto.dados.numUnidade = componenteCentraisSingulares.unidade != null ? componenteCentraisSingulares.unidade.codigo : null;
			dto.dados.numNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.numNucleo : null;
			dto.dados.nomeNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.descricao : null;
			dto.dados.numGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.idFuncionario : null;
			dto.dados.nomeGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.pessoa.pessoaCompartilhamento.nomePessoa : null;
			dto.dados.numTipoOrigemRegistro = tipoOrigemGrupo.selectedValue;
			dto.dados.numTipoVigencia = tipoVigencia.selectedValue;
			dto.dados.dataInicio = DateTimeBase.getDateTime(dataInicio.selectedDate);
			dto.dados.dataFim = DateTimeBase.getDateTime(dataFim.selectedDate);
			dto.dados.cpfCnpj = campoCpfCnpj.txtCodigo.text;
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioGrupoEconomicoNovoServicoRemote", dto, 
				"RelatorioGrupoEconomicoNovo", modulo.destino);
		}
		
	}
}