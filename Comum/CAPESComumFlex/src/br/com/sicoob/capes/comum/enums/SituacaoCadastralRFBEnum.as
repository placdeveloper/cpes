package br.com.sicoob.capes.comum.enums
{
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum")]
	public class SituacaoCadastralRFBEnum extends Enum
	{
		public static const ATIVA:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("ATIVA", NaN, 2, "Ativa", _);
		public static const BAIXADA:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("BAIXADA", NaN, 8, "Baixada", _);
		public static const CANCELADA_ENCERRAMENTO_ESPOLIO:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("CANCELADA_ENCERRAMENTO_ESPOLIO", 1, NaN, "Cancelada por encerramento de espólio", _);
		public static const CANCELADA_MULTIPLICIDADE:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("CANCELADA_MULTIPLICIDADE", 5, NaN, "Cancelada por multiplicidade", _);
		public static const CANCELADA_OBITO_SEM_ESPOLIO:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("CANCELADA_OBITO_SEM_ESPOLIO", 3, NaN, "Cancelada por óbito sem espólio", _);
		public static const CANCELADA_OFICIO:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("CANCELADA_OFICIO", 9, NaN, "Cancelada de ofício", _);
		public static const INAPTA:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("INAPTA", NaN, 4, "Inapta", _);
		public static const NULA:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("NULA", 8, 1, "Nula", _);
		public static const PENDENTE_REGULARIZACAO:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("PENDENTE_REGULARIZACAO", 4, NaN, "Pendente de regularização", _);
		public static const REGULAR:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("REGULAR", 0, NaN, "Regular", _);
		public static const SUSPENSA:SituacaoCadastralRFBEnum = new SituacaoCadastralRFBEnum("SUSPENSA", 2, 3, "Suspensa", _);
		
		private var _codigoPF:Number;
		private var _codigoPJ:Number;
		private var _descricao:String;
		
		/**
		 * Construtor
		 * 
		 * @param codigoPF
		 * @param codigoPJ
		 * @param descricao
		 */
		function SituacaoCadastralRFBEnum(name:String = null, codigoPF:Number = NaN, codigoPJ:Number = NaN, descricao:String = null, restrictor:* = null) {
			super((name || ATIVA.name), restrictor);
			if (restrictor != null) {
				_codigoPF = codigoPF;
				_codigoPJ = codigoPJ;
				_descricao = descricao;
			}
		}
		
		public function get codigoPF():Number {
			return _codigoPF;
		}
		
		public function get codigoPJ():Number {
			return _codigoPJ;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public static function get constants():Array {
			return [ATIVA, BAIXADA, CANCELADA_ENCERRAMENTO_ESPOLIO, CANCELADA_MULTIPLICIDADE,
				CANCELADA_OBITO_SEM_ESPOLIO, CANCELADA_OFICIO, INAPTA, NULA, PENDENTE_REGULARIZACAO,
				REGULAR, SUSPENSA];
		}
		
		public static function valueOf(name:String):SituacaoCadastralRFBEnum {
			return SituacaoCadastralRFBEnum(ATIVA.constantOf(name));
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:SituacaoCadastralRFBEnum = valueOf(name);
			_codigoPF = constantObject.codigoPF;
			_codigoPJ = constantObject.codigoPJ;
			_descricao = constantObject.descricao;
		}
	}
}