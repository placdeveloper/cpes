/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Nov 07 11:45:35 BRST 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (EnderecoBaseVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades {

    import br.com.bancoob.tipos.IDateTime;
    import br.com.sicoob.capes.comum.vo.entidades.EntidadeCadastroBaseVO;
    import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class EnderecoBaseVOBase extends EntidadeCadastroBaseVO {

        private var _bairro:String;
        private var _cep:String;
        private var _complemento:String;
		private var _dataHoraInicio:IDateTime;
        private var _descricao:String;
        private var _localidade:LocalidadeVO;
        private var _numero:String;
        private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
        private var _tipoEndereco:TipoEnderecoVO;
        private var _tipoLogradouro:TipoLogradouroVO;
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}

        public function set bairro(value:String):void {
            _bairro = value;
        }
        public function get bairro():String {
            return _bairro;
        }

        public function set cep(value:String):void {
            _cep = value;
        }
        public function get cep():String {
            return _cep;
        }

        public function set complemento(value:String):void {
            _complemento = value;
        }
        public function get complemento():String {
            return _complemento;
        }

        public function set descricao(value:String):void {
            _descricao = value;
        }
        public function get descricao():String {
            return _descricao;
        }

        public function set localidade(value:LocalidadeVO):void {
            _localidade = value;
        }
        public function get localidade():LocalidadeVO {
            return _localidade;
        }

        public function set numero(value:String):void {
            _numero = value;
        }
        public function get numero():String {
            return _numero;
        }

        public function set pessoaCompartilhamento(value:PessoaCompartilhamentoVO):void {
            _pessoaCompartilhamento = value;
        }
        public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
            return _pessoaCompartilhamento;
        }

        public function set tipoEndereco(value:TipoEnderecoVO):void {
            _tipoEndereco = value;
        }
        public function get tipoEndereco():TipoEnderecoVO {
            return _tipoEndereco;
        }

        public function set tipoLogradouro(value:TipoLogradouroVO):void {
            _tipoLogradouro = value;
        }
        public function get tipoLogradouro():TipoLogradouroVO {
            return _tipoLogradouro;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _bairro = input.readObject() as String;
            _cep = input.readObject() as String;
            _complemento = input.readObject() as String;
			_dataHoraInicio = input.readObject() as IDateTime;
            _descricao = input.readObject() as String;
            _localidade = input.readObject() as LocalidadeVO;
            _numero = input.readObject() as String;
            _pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
            _tipoEndereco = input.readObject() as TipoEnderecoVO;
            _tipoLogradouro = input.readObject() as TipoLogradouroVO;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_bairro);
            output.writeObject(_cep);
            output.writeObject(_complemento);
			output.writeObject(_dataHoraInicio);        	
           	output.writeObject(_descricao);
            output.writeObject(_localidade);
            output.writeObject(_numero);
            output.writeObject(_pessoaCompartilhamento);
            output.writeObject(_tipoEndereco);
            output.writeObject(_tipoLogradouro);
        }
    }
}