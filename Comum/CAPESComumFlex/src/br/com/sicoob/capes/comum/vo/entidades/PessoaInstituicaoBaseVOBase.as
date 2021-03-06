/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Feb 27 09:39:01 BRT 2012.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (PessoaInstituicaoBaseVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades{

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.IDateTime;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class PessoaInstituicaoBaseVOBase extends CAPESEntidadeVO {

        private var _dataCadastro:IDateTime;
        private var _dataEnquadramentoRisco:IDateTime;
        private var _dataHoraInicio:IDateTime;
        private var _emiteAvisoLancamento:Booleano;
        private var _funcionario:FuncionarioVO;
        private var _gruposEconomicos:ListCollectionView;
        private var _idInstituicao:Number;
        private var _idUnidadeInst:Number;
		private var _idUsuarioAprovacao:String;
        private var _motivoRisco:String;
        private var _nivelRisco:String;
        private var _nucleo:NucleoVO;
        private var _parecerGerencia:String;
        private var _perfilTarifario:PerfilTarifarioVO;
        private var _pessoa:PessoaVO;
		
		public function set dataCadastro(value:IDateTime):void {
			_dataCadastro = value;
		}
		public function get dataCadastro():IDateTime {
			return _dataCadastro;
		}

        public function set dataEnquadramentoRisco(value:IDateTime):void {
            _dataEnquadramentoRisco = value;
        }
        public function get dataEnquadramentoRisco():IDateTime {
            return _dataEnquadramentoRisco;
        }

        public function set dataHoraInicio(value:IDateTime):void {
            _dataHoraInicio = value;
        }
        public function get dataHoraInicio():IDateTime {
            return _dataHoraInicio;
        }

        public function set emiteAvisoLancamento(value:Booleano):void {
            _emiteAvisoLancamento = value;
        }
        public function get emiteAvisoLancamento():Booleano {
            return _emiteAvisoLancamento;
        }

        public function set funcionario(value:FuncionarioVO):void {
            _funcionario = value;
        }
        public function get funcionario():FuncionarioVO {
            return _funcionario;
        }

        public function set gruposEconomicos(value:ListCollectionView):void {
            _gruposEconomicos = value;
        }
        public function get gruposEconomicos():ListCollectionView {
            return _gruposEconomicos;
        }

        public function set idInstituicao(value:Number):void {
            _idInstituicao = value;
        }
        public function get idInstituicao():Number {
            return _idInstituicao;
        }

        public function set idUnidadeInst(value:Number):void {
            _idUnidadeInst = value;
        }
        public function get idUnidadeInst():Number {
            return _idUnidadeInst;
        }

        public function set motivoRisco(value:String):void {
            _motivoRisco = value;
        }
        public function get motivoRisco():String {
            return _motivoRisco;
        }

        public function set nivelRisco(value:String):void {
            _nivelRisco = value;
        }
        public function get nivelRisco():String {
            return _nivelRisco;
        }

        public function set nucleo(value:NucleoVO):void {
            _nucleo = value;
        }
        public function get nucleo():NucleoVO {
            return _nucleo;
        }

        public function set parecerGerencia(value:String):void {
            _parecerGerencia = value;
        }
        public function get parecerGerencia():String {
            return _parecerGerencia;
        }

        public function set perfilTarifario(value:PerfilTarifarioVO):void {
            _perfilTarifario = value;
        }
        public function get perfilTarifario():PerfilTarifarioVO {
            return _perfilTarifario;
        }

        public function set pessoa(value:PessoaVO):void {
            _pessoa = value;
        }
        public function get pessoa():PessoaVO {
            return _pessoa;
        }
		
		public function get idUsuarioAprovacao():String{
			return _idUsuarioAprovacao;
		}
		
		public function set idUsuarioAprovacao(valor:String):void{
			this._idUsuarioAprovacao = valor;
		}

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
				_dataCadastro = input.readObject() as IDateTime;
            	_dataEnquadramentoRisco = input.readObject() as IDateTime;
            	_dataHoraInicio = input.readObject() as IDateTime;
            	_emiteAvisoLancamento = SerializacaoObjetos.lerBooleano(input);
                _funcionario = input.readObject() as FuncionarioVO;
                _gruposEconomicos = input.readObject() as ListCollectionView;
                _idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _idUnidadeInst = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idUsuarioAprovacao = input.readObject() as String;
                _motivoRisco = input.readObject() as String;
                _nivelRisco = input.readObject() as String;
                _nucleo = input.readObject() as NucleoVO;
                _parecerGerencia = input.readObject() as String;
                _perfilTarifario = input.readObject() as PerfilTarifarioVO;
                _pessoa = input.readObject() as PessoaVO;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
			output.writeObject(_dataCadastro);
			output.writeObject(_dataEnquadramentoRisco);
			output.writeObject(_dataHoraInicio);
        	SerializacaoObjetos.escreverBooleano(_emiteAvisoLancamento, output);
            output.writeObject(_funcionario);
            output.writeObject(_gruposEconomicos);
            output.writeObject(_idInstituicao);
            output.writeObject(_idUnidadeInst);
            output.writeObject(_idUsuarioAprovacao);
            output.writeObject(_motivoRisco);
            output.writeObject(_nivelRisco);
            output.writeObject(_nucleo);
            output.writeObject(_parecerGerencia);
            output.writeObject(_perfilTarifario);
            output.writeObject(_pessoa);
        }
    }
}