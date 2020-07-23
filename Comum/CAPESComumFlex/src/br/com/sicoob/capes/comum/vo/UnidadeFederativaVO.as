package br.com.sicoob.capes.comum.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.UnidadeFederativaVO", UnidadeFederativaVO);
	[Bindable]
	public class UnidadeFederativaVO extends BancoobVO
	{
		
		private var _idUF:Number;
		private var _idRegiao:Number;
		private var _siglaUF:String;
		private var _nomeUF:String;
		private var  _codIBGE:String;
		private var  _codRF:String;
		private var  _codBACEN:String;
		
		public function get codBACEN():String
		{
			return _codBACEN;
		}

		public function set codBACEN(value:String):void
		{
			_codBACEN = value;
		}

		public function get codRF():String
		{
			return _codRF;
		}

		public function set codRF(value:String):void
		{
			_codRF = value;
		}

		public function get codIBGE():String
		{
			return _codIBGE;
		}

		public function set codIBGE(value:String):void
		{
			_codIBGE = value;
		}

		public function get idUF():Number
		{
			return _idUF;
		}

		public function set idUF(value:Number):void
		{
			_idUF = value;
		}

		public function get siglaUF():String
		{
			return _siglaUF;
		}

		public function set siglaUF(value:String):void
		{
			_siglaUF = value;
		}

		public function get idRegiao():Number
		{
			return _idRegiao;
		}

		public function set idRegiao(value:Number):void
		{
			_idRegiao = value;
		}

		public function get nomeUF():String
		{
			return _nomeUF;
		}

		public function set nomeUF(value:String):void
		{
			_nomeUF = value;
		}

	}
}