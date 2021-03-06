/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Aug 08 13:43:13 BRT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (CertidaoBaseVO.as).
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
    public class CertidaoBaseVOBase extends EntidadeCadastroBaseVO {

        private var _dataEmissao:IDateTime;
		private var _dataHoraInicio:IDateTime;
        private var _dataVencimento:IDateTime;
        private var _numero:String;
        private var _numeroCrea:String;
        private var _observacao:String;
        private var _pessoa:PessoaCompartilhamentoVO;
        private var _tipoCertidao:TipoCertidaoVO;
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}

        public function set dataEmissao(value:IDateTime):void {
            _dataEmissao = value;
        }
        public function get dataEmissao():IDateTime {
            return _dataEmissao;
        }

        public function set dataVencimento(value:IDateTime):void {
            _dataVencimento = value;
        }
        public function get dataVencimento():IDateTime {
            return _dataVencimento;
        }

        public function set numero(value:String):void {
            _numero = value;
        }
        public function get numero():String {
            return _numero;
        }

        public function set numeroCrea(value:String):void {
            _numeroCrea = value;
        }
        public function get numeroCrea():String {
            return _numeroCrea;
        }

        public function set observacao(value:String):void {
            _observacao = value;
        }
        public function get observacao():String {
            return _observacao;
        }

        public function set pessoa(value:PessoaCompartilhamentoVO):void {
            _pessoa = value;
        }
        public function get pessoa():PessoaCompartilhamentoVO {
            return _pessoa;
        }

        public function set tipoCertidao(value:TipoCertidaoVO):void {
            _tipoCertidao = value;
        }
        public function get tipoCertidao():TipoCertidaoVO {
            return _tipoCertidao;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
        	_dataEmissao = input.readObject() as IDateTime;
			_dataHoraInicio = input.readObject() as IDateTime;
        	_dataVencimento = input.readObject() as IDateTime;
            _numero = input.readObject() as String;
            _numeroCrea = input.readObject() as String;
            _observacao = input.readObject() as String;
            _pessoa = input.readObject() as PessoaCompartilhamentoVO;
            _tipoCertidao = input.readObject() as TipoCertidaoVO;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_dataEmissao);
			output.writeObject(_dataHoraInicio);        	
           	output.writeObject(_dataVencimento);
            output.writeObject(_numero);
            output.writeObject(_numeroCrea);
            output.writeObject(_observacao);
            output.writeObject(_pessoa);
            output.writeObject(_tipoCertidao);
        }
    }
}