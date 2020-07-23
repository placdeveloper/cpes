package br.com.sicoob.capes.relatorioClienteProduto.vo
{
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoVO", RelClienteProdutoVO);
	
	public class RelClienteProdutoVO extends BancoobVO
	{
		private var _cooperativa:Number;
		private var _nomeCoopPac:String;
		private var _pac:Number;
		private var _nucleo:Number;
		private var _nomeNucleo:String;
		private var _grupoEconomico:Number;
		private var _nomeGrupoEconomico:String;
		private var _gerente:Number;
		private var _nomeGerente:String;
		
		private var _contaCapital:Boolean;
		private var _contaCorrente:Boolean;
		private var _aplicacoes:Boolean;
		private var _poupanca:Boolean;
		private var _opCredito:Boolean;
		private var _cartaoCredito:Boolean;
		private var _debitoAutomatico:Boolean;
		
		private var _rendaMin:Number;
		private var _rendaMax:Number;
		private var _dtNascInicio:Date;
		private var _dtNascFim:Date;
		private var _tipoPessoa:Number;
		private var _dependente:Number;		
		private var _sexo:Number;
		private var _municipio:Number;
		private var _nomeMunicipio:String;
		private var _categoriaProdutor:Number;
		private var _nomeCategoriaProdutor:String;
		private var _ordenacao:int;
		
		public function get cooperativa():Number{
			return _cooperativa;
		}
		public function set cooperativa(vlr:Number):void{
			_cooperativa = vlr;
		}
		
		public function get nomeCoopPac():String{
			return _nomeCoopPac;
		}
		public function set nomeCoopPac(vlr:String):void{
			_nomeCoopPac = vlr;
		}
		
		public function get pac():Number{
			return _pac;
		}
		public function set pac(vlr:Number):void{
			_pac = vlr;
		}
		
		public function get nucleo():Number{
			return _nucleo;
		}
		public function set nucleo(vlr:Number):void{
			_nucleo = vlr;
		}
		
		public function get nomeNucleo():String{
			return _nomeNucleo;
		}
		public function set nomeNucleo(vlr:String):void{
			_nomeNucleo = vlr;
		}
		
		public function get grupoEconomico():Number{
			return _grupoEconomico;
		}
		public function set grupoEconomico(vlr:Number):void{
			_grupoEconomico = vlr;
		}
		
		public function get nomeGrupoEconomico():String{
			return _nomeGrupoEconomico;
		}
		public function set nomeGrupoEconomico(vlr:String):void{
			_nomeGrupoEconomico = vlr;
		}
		
		public function get gerente():Number{
			return _gerente;
		}
		public function set gerente(vlr:Number):void{
			_gerente = vlr;
		}
		
		public function get nomeGerente():String{
			return _nomeGerente;
		}
		public function set nomeGerente(vlr:String):void{
			_nomeGerente = vlr;
		}
		
		public function get tipoPessoa():Number{
			return _tipoPessoa;
		}
		public function set tipoPessoa(vlr:Number):void{
			_tipoPessoa = vlr;
		}
		
		public function get contaCapital():Boolean{
			return _contaCapital;
		}
		public function set contaCapital(vlr:Boolean):void{
			_contaCapital = vlr;
		}
		
		public function get contaCorrente():Boolean{
			return _contaCorrente;
		}
		public function set contaCorrente(vlr:Boolean):void{
			_contaCorrente = vlr;
		}
		
		public function get aplicacoes():Boolean{
			return _aplicacoes;
		}
		public function set aplicacoes(vlr:Boolean):void{
			_aplicacoes = vlr;
		}
		
		public function get poupanca():Boolean{
			return _poupanca;
		}
		public function set poupanca(vlr:Boolean):void{
			_poupanca = vlr;
		}
		
		public function get opCredito():Boolean{
			return _opCredito;
		}
		public function set opCredito(vlr:Boolean):void{
			_opCredito = vlr;
		}
		
		public function get cartaoCredito():Boolean{
			return _cartaoCredito;
		}
		public function set cartaoCredito(vlr:Boolean):void{
			_cartaoCredito = vlr;
		}
		
		public function get debitoAutomatico():Boolean{
			return _debitoAutomatico;
		}
		public function set debitoAutomatico(vlr:Boolean):void{
			_debitoAutomatico = vlr;
		}
		
		public function get rendaMin():Number{
			return _rendaMin;
		}
		public function set rendaMin(vlr:Number):void{
			_rendaMin = vlr;
		}
		
		public function get rendaMax():Number{
			return _rendaMax;
		}
		public function set rendaMax(vlr:Number):void{
			_rendaMax = vlr;
		}
		
		public function get dependente():Number{
			return _dependente;
		}
		public function set dependente(vlr:Number):void{
			_dependente = vlr;
		}
		
		public function get sexo():Number{
			return _sexo;
		}
		public function set sexo(vlr:Number):void{
			_sexo = vlr;
		}
		
		public function get dtNascInicio():Date{
			return _dtNascInicio;
		}
		public function set dtNascInicio(vlr:Date):void{
			_dtNascInicio = vlr;
		}
		
		public function get dtNascFim():Date{
			return _dtNascFim;
		}
		public function set dtNascFim(vlr:Date):void{
			_dtNascFim = vlr;
		}
		
		public function get municipio():Number{
			return _municipio;
		}
		public function set municipio(vlr:Number):void{
			_municipio = vlr;
		}
		
		public function get categoriaProdutor():Number{
			return _categoriaProdutor;
		}
		public function set categoriaProdutor(vlr:Number):void{
			_categoriaProdutor = vlr;
		}
		
		public function get nomeMunicipio():String{
			return _nomeMunicipio;
		}
		public function set nomeMunicipio(vlr:String):void{
			_nomeMunicipio = vlr;
		}
		
		public function get nomeCategoriaProdutor():String{
			return _nomeCategoriaProdutor;
		}
		public function set nomeCategoriaProdutor(vlr:String):void{
			_nomeCategoriaProdutor = vlr;
		}
		
		public function get ordenacao():int{
			return _ordenacao;
		}
		public function set ordenacao(vlr:int):void{
			_ordenacao = vlr;
		}

	}
}