/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Tue Jan 17 09:51:01 BRST 2012.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (CompartilhamentoCadastroVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades {

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class CompartilhamentoCadastroVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _codigo:Number;
        private var _descricao:String;
        private var _nomeGrupoCta:String;
		private var _nomeGrupoCtaCadastroInstituicao:String;
		private var _utilizaGedGft:Booleano = new Booleano();

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is CompartilhamentoCadastroVO) || (property as CompartilhamentoCadastroVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set codigo(value:Number):void {
            _codigo = value;
        }
        public function get codigo():Number {
            return _codigo;
        }

        public function set descricao(value:String):void {
            _descricao = value;
        }
        public function get descricao():String {
            return _descricao;
        }
        
        public function set nomeGrupoCta(value:String):void {
            _nomeGrupoCta = value;
        }
        public function get nomeGrupoCta():String {
            return _nomeGrupoCta;
        }        
        
        public function set nomeGrupoCtaCadastroInstituicao(value:String):void {
            _nomeGrupoCtaCadastroInstituicao = value;
        }
        public function get nomeGrupoCtaCadastroInstituicao():String {
            return _nomeGrupoCtaCadastroInstituicao;
        }        
		
		public function get utilizaGedGft():Booleano {
			return _utilizaGedGft;
		}
		public function set utilizaGedGft(value:Booleano):void {
			_utilizaGedGft = value;
		}

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
                _codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _descricao = input.readObject() as String;
                _nomeGrupoCta = input.readObject() as String;
				_nomeGrupoCtaCadastroInstituicao = input.readObject() as String;
				_utilizaGedGft = SerializacaoObjetos.lerBooleano(input);
            }
            else {
                _codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
            	output.writeObject(_codigo);
            	output.writeObject(_descricao);
            	output.writeObject(_nomeGrupoCta);
            	output.writeObject(_nomeGrupoCtaCadastroInstituicao);
				SerializacaoObjetos.escreverBooleano(_utilizaGedGft, output);
            }
            else {
                output.writeObject(_codigo);
            }
        }
    }
}