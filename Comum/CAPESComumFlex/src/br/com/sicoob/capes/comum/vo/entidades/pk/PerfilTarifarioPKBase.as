package br.com.sicoob.capes.comum.vo.entidades.pk{

    import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
    import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class PerfilTarifarioPKBase extends BancoobChavePrimaria {

        private var __laziness:String = null;

        private var _idInstituicao:Number;
        private var _codPerfilTarifario:Number;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is PerfilTarifarioVO) || (property as PerfilTarifarioVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set idInstituicao(value:Number):void {
            _idInstituicao = value;
        }
        public function get idInstituicao():Number {
            return _idInstituicao;
        }

        public function set codPerfilTarifario(value:Number):void {
            _codPerfilTarifario = value;
        }
        public function get codPerfilTarifario():Number {
            return _codPerfilTarifario;
        }

        override public function readExternal(input:IDataInput):void {
            _codPerfilTarifario = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(_codPerfilTarifario);
            output.writeObject(_idInstituicao);
        }
    }
}