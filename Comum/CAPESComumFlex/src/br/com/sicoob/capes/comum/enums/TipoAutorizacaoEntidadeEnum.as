package br.com.sicoob.capes.comum.enums {
	import flash.utils.IDataInput;
	import flash.utils.IExternalizable;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum")]
	public class TipoAutorizacaoEntidadeEnum extends Enum implements IExternalizable {
		
		public static const BEM_NOVO : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("BEM_NOVO", TipoAutorizacaoEnum.BEM_NOVO, "br.com.sicoob.capes.negocio.entidades.bem.Bem", _);
		public static const BEM : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("BEM", TipoAutorizacaoEnum.BEM, "br.com.sicoob.capes.negocio.entidades.bemantigo.Bem", _);
		public static const CERTIDAO : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("CERTIDAO", TipoAutorizacaoEnum.CERTIDAO, "br.com.sicoob.capes.negocio.entidades.vigente.Certidao", _);
		public static const EMAIL : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("EMAIL", TipoAutorizacaoEnum.EMAIL, "br.com.sicoob.capes.negocio.entidades.vigente.Email", _);
		public static const ENDERECO : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("ENDERECO", TipoAutorizacaoEnum.ENDERECO, "br.com.sicoob.capes.negocio.entidades.vigente.Endereco", _);
		public static const FONTE_RENDA : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("FONTE_RENDA", TipoAutorizacaoEnum.FONTE_RENDA, "br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda", _);
		public static const TRIBUTACAO : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("TRIBUTACAO", TipoAutorizacaoEnum.TRIBUTACAO, "br.com.sicoob.capes.negocio.entidades.vigente.Tributacao", _);
		public static const PESSOA : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("PESSOA", TipoAutorizacaoEnum.PESSOA, "br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento", _);
		public static const PRODUTIVIDADE : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("PRODUTIVIDADE", TipoAutorizacaoEnum.PRODUTIVIDADE, "br.com.sicoob.capes.negocio.entidades.vigente.Produtividade", _);
		public static const PRODUTOR : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("PRODUTOR", TipoAutorizacaoEnum.PRODUTOR, "br.com.sicoob.capes.negocio.entidades.vigente.Produtor", _);
		public static const REFERENCIA : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("REFERENCIA", TipoAutorizacaoEnum.REFERENCIA, "br.com.sicoob.capes.negocio.entidades.vigente.Referencia", _);
		public static const RELACIONAMENTO : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("RELACIONAMENTO", TipoAutorizacaoEnum.RELACIONAMENTO, "br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa", _);
		public static const RESPONSAVEL : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("RESPONSAVEL", TipoAutorizacaoEnum.RESPONSAVEL, "br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro", _);
		public static const TELEFONE : TipoAutorizacaoEntidadeEnum = new TipoAutorizacaoEntidadeEnum("TELEFONE", TipoAutorizacaoEnum.TELEFONE, "br.com.sicoob.capes.negocio.entidades.vigente.Telefone", _);
															
		private var _tipoAutorizacao : TipoAutorizacaoEnum;
		private var _tipo : String;
															
		function TipoAutorizacaoEntidadeEnum(name:String = null, tipoAutorizacao:TipoAutorizacaoEnum = null, tipo:String = null, restrictor:* = null) {
			super((name || BEM_NOVO.name), restrictor);
			if (restrictor != null) {
				this._tipoAutorizacao = tipoAutorizacao;
				this._tipo = tipo;
			}
		}
		
		public function get tipoAutorizacao():TipoAutorizacaoEnum {
			return _tipoAutorizacao;
		}
		
		public function get tipo():String {
			return _tipo;
		}
		
		public static function get constants():Array {
			return [BEM_NOVO, BEM, CERTIDAO, EMAIL, ENDERECO, FONTE_RENDA, TRIBUTACAO, PESSOA, 
				PRODUTIVIDADE, PRODUTOR, REFERENCIA, RELACIONAMENTO, RESPONSAVEL, TELEFONE];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoAutorizacaoEntidadeEnum = valueOf(name);
			_tipoAutorizacao = constantObject._tipoAutorizacao;
			_tipo = constantObject.tipo;
		}
		
		public static function valueOf(name:String):TipoAutorizacaoEntidadeEnum {
			return TipoAutorizacaoEntidadeEnum(BEM_NOVO.constantOf(name));
		}
	}
}