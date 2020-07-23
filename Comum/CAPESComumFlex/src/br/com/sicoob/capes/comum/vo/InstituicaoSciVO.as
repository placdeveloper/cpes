package br.com.sicoob.capes.comum.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.InstituicaoSciVO", InstituicaoSciVO);
	[Bindable]	
	public class InstituicaoSciVO extends BancoobVO 
	{
		private var  CONFEDERACAO:Number = 3;
		private var _descNomeCoop:String;
		private var _idInstituicao:int;
		private var _numCooperativa:int;
//		private var _idUnidadeInstituicao:int;
//		private var _descSiglaCoop:String;
		private var _codTipoGrauCoop:int;
//		private var _numCoopPai:int;
//		
//		private var _idGerente:Number;
//		
//		private var _singulares:ArrayCollection = new ArrayCollection();
//		
//		private var _valorIntegralizacaoIncial:Number;
//		private var _valorIntegralizacaoMensal:Number;
//		private var _valorMinimoEntrada:Number;
//		private var _numMaximoParcelamento:int;
//		
//		private var _valorDestinadoJuros:Number;
//		private var _valorPatrimonio:Number;
//		private var _valorRateioPago:Number;
//		
//		private var _valorDiaCobrancaSubscricaoMensal:Number;
//		private var _valorDiaCobrancasPacoteTarifa:Number;
//		
//		private var _valorLimiteProdutos:Number;
//		
//		private var _configuracaoInstituicao:ArrayCollection = new ArrayCollection();
//		
//		private var _criterioOrdenacaoInstitucaoDisponiveis:ArrayCollection = new ArrayCollection();
//		private var _criterioOrdenacaoInstituicaoVinculadas:ArrayCollection = new ArrayCollection();
//		
//		private var _diasDeCobrancaDisponiveis:ArrayCollection = new ArrayCollection();
//		private var _diasDeCobrancaVinculadas:ArrayCollection = new ArrayCollection();
//		
//		private var _idCorretor:Number;
//		private var _idSeguradora:Number;
//		
//		private var _segmento:String;
//		private var _dataBase:Date;
//		
//		private var _bolAtivo:Boolean;
		
		public function get idInstituicao():int
		{
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:int):void
		{
			_idInstituicao = value;
		}
		
//		public function get idUnidadeInstituicao():int
//		{
//			return _idUnidadeInstituicao;
//		}
//		
//		public function set idUnidadeInstituicao(value:int):void
//		{
//			_idUnidadeInstituicao = value;
//		}
		
		public function get numCooperativa():int
		{
			return _numCooperativa;
		}
		
		public function set numCooperativa(value:int):void
		{
			_numCooperativa = value;
		}
		
		public function get descNomeCoop():String
		{
			return _descNomeCoop;
		}
		
		public function set descNomeCoop(value:String):void
		{
			_descNomeCoop = value;
		}
		
//		public function get descSiglaCoop():String
//		{
//			return _descSiglaCoop;
//		}
//		
//		public function set descSiglaCoop(value:String):void
//		{
//			_descSiglaCoop = value;
//		}
		
		public function get codTipoGrauCoop():int
		{
			return _codTipoGrauCoop;
		}
		
		public function set codTipoGrauCoop(value:int):void
		{
			_codTipoGrauCoop = value;
		}
		
//		public function get numCoopPai():int
//		{
//			return _numCoopPai;
//		}
//		
//		public function set numCoopPai(value:int):void
//		{
//			_numCoopPai = value;
//		}
//		
//		public function isCentral():Boolean 
//		{
//			if(isNaN(this.codTipoGrauCoop))
//			{
//				return false;
//			}
//			return TipoGrauCoopEnum.CENTRAL == this.codTipoGrauCoop;
//		}
//		
//		public function isSingular():Boolean 
//		{
//			if(isNaN(this.codTipoGrauCoop) || 300 == numCooperativa)
//			{
//				return false;
//			}
//			return TipoGrauCoopEnum.SINGULAR == this.codTipoGrauCoop;
//		}
//		
		public function isConfederacao():Boolean 
		{
			if (CONFEDERACAO || 300 == numCooperativa){
				return true;
			}
			return false;
		}
//		
//		
//		
//		public function get valorIntegralizacaoIncial():Number
//		{
//			return _valorIntegralizacaoIncial;
//		}
//		
//		public function set valorIntegralizacaoIncial(value:Number):void
//		{
//			_valorIntegralizacaoIncial = value;
//		}
//		
//		public function get valorLimiteProdutos():Number
//		{
//			return _valorLimiteProdutos;
//		}
//		
//		public function set valorLimiteProdutos(value:Number):void
//		{
//			_valorLimiteProdutos = value;
//		}
//		
//		
//		public function get diasDeCobrancaDisponiveis():ArrayCollection
//		{
//			return _diasDeCobrancaDisponiveis;
//		}
//		
//		public function set diasDeCobrancaDisponiveis(value:ArrayCollection):void
//		{
//			_diasDeCobrancaDisponiveis = value;
//		}
//		
//		public function get diasDeCobrancaVinculadas():ArrayCollection
//		{
//			return _diasDeCobrancaVinculadas;
//		}
//		
//		public function set diasDeCobrancaVinculadas(value:ArrayCollection):void
//		{
//			_diasDeCobrancaVinculadas = value;
//		}
//		
//		public function get valorIntegralizacaoMensal():Number
//		{
//			return _valorIntegralizacaoMensal;
//		}
//		
//		public function set valorIntegralizacaoMensal(value:Number):void
//		{
//			_valorIntegralizacaoMensal = value;
//		}
//		
//		public function get valorMinimoEntrada():Number
//		{
//			return _valorMinimoEntrada;
//		}
//		
//		public function set valorMinimoEntrada(value:Number):void
//		{
//			_valorMinimoEntrada = value;
//		}
//		
//		public function get numMaximoParcelamento():int
//		{
//			return _numMaximoParcelamento;
//		}
//		
//		public function set numMaximoParcelamento(value:int):void
//		{
//			_numMaximoParcelamento = value;
//		}
//		
//		public function get criterioOrdenacaoInstitucaoDisponiveis():ArrayCollection
//		{
//			return _criterioOrdenacaoInstitucaoDisponiveis;
//		}
//		
//		public function set criterioOrdenacaoInstitucaoDisponiveis(value:ArrayCollection):void
//		{
//			_criterioOrdenacaoInstitucaoDisponiveis = value;
//		}
//		
//		public function get criterioOrdenacaoInstituicaoVinculadas():ArrayCollection
//		{
//			return _criterioOrdenacaoInstituicaoVinculadas;
//		}
//		
//		public function set criterioOrdenacaoInstituicaoVinculadas(value:ArrayCollection):void
//		{
//			_criterioOrdenacaoInstituicaoVinculadas = value;
//		}
//		
//		public function get singulares():ArrayCollection
//		{
//			return _singulares;
//		}
//		
//		public function set singulares(value:ArrayCollection):void
//		{
//			_singulares = value;
//		}
//		
//		public function get toStringLabel() : String {
//			var numZeros: Number = 4 - this.numCooperativa.toString().length;
//			var zeros:String = "";
//			for(var i: int = 0; i < numZeros; i ++){
//				zeros = zeros + '0';
//			}
//			return  zeros + this._numCooperativa + " - " + this.descSiglaCoop;
//		}
//		
//		public function get numCooperativaAsString() : String {
//			var numZeros: Number = 4 - this.numCooperativa.toString().length;
//			var zeros:String = "";
//			for(var i: int = 0; i < numZeros; i ++){
//				zeros = zeros + '0';
//			}
//			return  zeros + this._numCooperativa ;
//		}
//		
//		public function get idCorretor():Number
//		{
//			return _idCorretor;
//		}
//		
//		public function set idCorretor(value:Number):void
//		{
//			_idCorretor = value;
//		}
//		
//		public function get idSeguradora():Number
//		{
//			return _idSeguradora;
//		}
//		
//		public function set idSeguradora(value:Number):void
//		{
//			_idSeguradora = value;
//		}
//		
//		public function get segmento():String
//		{
//			return _segmento;
//		}
//		
//		public function set segmento(value:String):void
//		{
//			_segmento = value;
//		}
//		
//		public function get dataBase():Date
//		{
//			return _dataBase;
//		}
//		
//		public function set dataBase(value:Date):void
//		{
//			_dataBase = value;
//		}
//		
//		public function get idGerente():Number
//		{
//			return _idGerente;
//		}
//		
//		public function set idGerente(value:Number):void
//		{
//			_idGerente = value;
//		}
//		
//		public function get valorDiaCobrancaSubscricaoMensal():Number
//		{
//			return _valorDiaCobrancaSubscricaoMensal;
//		}
//		
//		public function set valorDiaCobrancaSubscricaoMensal(value:Number):void
//		{
//			_valorDiaCobrancaSubscricaoMensal = value;
//		}
//		
//		public function get valorDiaCobrancasPacoteTarifa():Number
//		{
//			return _valorDiaCobrancasPacoteTarifa;
//		}
//		
//		public function set valorDiaCobrancasPacoteTarifa(value:Number):void
//		{
//			_valorDiaCobrancasPacoteTarifa = value;
//		}
//		
//		public function get bolAtivo():Boolean
//		{
//			return _bolAtivo;
//		}
//		
//		public function set bolAtivo(value:Boolean):void
//		{
//			_bolAtivo = value;
//		}
//		
//		public function get configuracaoInstituicao():ArrayCollection
//		{
//			return _configuracaoInstituicao;
//		}
//		
//		public function set configuracaoInstituicao(value:ArrayCollection):void
//		{
//			_configuracaoInstituicao = value;
//		}
//		
//		public function get valorDestinadoJuros():Number
//		{
//			return _valorDestinadoJuros;
//		}
//		
//		public function set valorDestinadoJuros(value:Number):void
//		{
//			_valorDestinadoJuros = value;
//		}
//		
//		public function get valorPatrimonio():Number
//		{
//			return _valorPatrimonio;
//		}
//		
//		public function set valorPatrimonio(value:Number):void
//		{
//			_valorPatrimonio = value;
//		}
//		
//		public function get valorRateioPago():Number
//		{
//			return _valorRateioPago;
//		}
//		
//		public function set valorRateioPago(value:Number):void
//		{
//			_valorRateioPago = value;
//		}
		
		
	}
}

