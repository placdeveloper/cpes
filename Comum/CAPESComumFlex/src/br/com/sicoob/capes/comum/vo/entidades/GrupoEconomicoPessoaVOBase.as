package br.com.sicoob.capes.comum.vo.entidades{
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;
	
	[Bindable]
	public class GrupoEconomicoPessoaVOBase extends GrupoEconomicoPessoaBaseVO implements VigenteVO {
		
        private var __laziness:String = null;
		
		private var _gravarHistorico : Booleano;
		private var _idGrupoEconomicoPessoa : Number;
		
		meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is GrupoEconomicoPessoaVO) || (property as GrupoEconomicoPessoaVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
		}

		public function set gravarHistorico(value:Booleano):void {
			_gravarHistorico = value;
		}
		public function get gravarHistorico():Booleano {
			return _gravarHistorico;
		}
		
		public function set idGrupoEconomicoPessoa(value:Number):void {
			_idGrupoEconomicoPessoa = value;
		}
		public function get idGrupoEconomicoPessoa():Number {
			return _idGrupoEconomicoPessoa;
		}
		
        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
            	_gravarHistorico = SerializacaoObjetos.lerBooleano(input);
                _idGrupoEconomicoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
            else {
                _idGrupoEconomicoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }
        
        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
        	SerializacaoObjetos.escreverBooleano(_gravarHistorico, output);
            output.writeObject(_idGrupoEconomicoPessoa);
            }
            else {
                output.writeObject(_idGrupoEconomicoPessoa);
            }
        }

	}
}