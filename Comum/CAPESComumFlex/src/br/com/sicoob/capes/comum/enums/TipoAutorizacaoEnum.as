package br.com.sicoob.capes.comum.enums
{
	import flash.utils.IDataInput;
	import flash.utils.IExternalizable;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum")]
	public class TipoAutorizacaoEnum extends Enum implements IExternalizable
	{
		public static const BEM_NOVO : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("BEM_NOVO", "Bem (NOVO)", "br.com.sicoob.capes.negocio.entidades.bem.Bem", _); 
		public static const BEM : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("BEM", "Bens", "br.com.sicoob.capes.negocio.entidades.bemantigo.Bem", _); 
		public static const CERTIDAO : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("CERTIDAO", "Certidão", "br.com.sicoob.capes.negocio.entidades.vigente.Certidao", _); 
		public static const EMAIL : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("EMAIL", "E-mail", "br.com.sicoob.capes.negocio.entidades.vigente.Email", _); 
		public static const ENDERECO : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("ENDERECO", "Endereço", "br.com.sicoob.capes.negocio.entidades.vigente.Endereco", _); 
		public static const FONTE_RENDA : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("FONTE_RENDA", "Fonte de Renda", "br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda", _); 
		public static const TRIBUTACAO : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("TRIBUTACAO", "Perfil Tributário", "br.com.sicoob.capes.negocio.entidades.vigente.Tributacao", _); 
		public static const PESSOA : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("PESSOA", "Pessoa", "br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento", _); 
		public static const PRODUTIVIDADE : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("PRODUTIVIDADE", "Produtividade", "br.com.sicoob.capes.negocio.entidades.vigente.Produtividade", _);
		public static const PRODUTOR : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("PRODUTOR", "Produtor", "br.com.sicoob.capes.negocio.entidades.vigente.Produtor", _);
		public static const REFERENCIA : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("REFERENCIA", "Referência Pessoal", "br.com.sicoob.capes.negocio.entidades.vigente.Referencia", _); 
		public static const RELACIONAMENTO : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("RELACIONAMENTO", "Relacionamento", "br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa", _); 
		public static const RESPONSAVEL : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("RESPONSAVEL", "Responsável pelo Cadastro", "br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro", _); 
		public static const TELEFONE : TipoAutorizacaoEnum = new TipoAutorizacaoEnum("TELEFONE", "Telefone", "br.com.sicoob.capes.negocio.entidades.vigente.Telefone", _); 
										
		private var _descricao:String;
		private var _tipo:String;
												
		function TipoAutorizacaoEnum(name:String = null, descricao:String = null, tipo:String = null, restrictor:* = null) {
			super((name || BEM_NOVO.name), restrictor);
			if (restrictor != null) {
				this._descricao = descricao.toUpperCase();
				this._tipo = tipo;
			}
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public function get tipo():String {
			return _tipo;
		}
		
		public static function get constants():Array {
			return [BEM_NOVO, BEM, CERTIDAO, EMAIL, ENDERECO, FONTE_RENDA, TRIBUTACAO, PESSOA, PRODUTIVIDADE, 
				PRODUTOR, REFERENCIA, RELACIONAMENTO, RESPONSAVEL, TELEFONE];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoAutorizacaoEnum = valueOf(name);
			_descricao = constantObject.descricao;
			_tipo = constantObject.tipo;
		}
		
		public static function valueOf(name:String):TipoAutorizacaoEnum {
			return TipoAutorizacaoEnum(BEM_NOVO.constantOf(name));
		}
	}
}