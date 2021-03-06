/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Wed Apr 27 11:08:21 BRT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (BemBaseVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades.bemantigo {

    import br.com.bancoob.tipos.IDateTime;
    import br.com.sicoob.capes.comum.vo.entidades.EntidadeCadastroBaseVO;
    import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.meta;

    use namespace meta;

    public class BemBaseVOBase extends EntidadeCadastroBaseVO {

		private var _dataHoraInicio:IDateTime;
        private var _dataVencimentoSeguro:IDateTime;
        private var _descricao:String;
		private var _idBemNovo:Number;
		private var _idPessoaCompartilhamento:Number;
        private var _nomeSeguradora:String;
        private var _percentual:Number;
        private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
        private var _situacao:SituacaoBemVO;
        private var _subTipo:SubTipoBemVO;
        private var _valorAtualMercado:Number;
        private var _valorSeguro:Number;
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}

        public function set dataVencimentoSeguro(value:IDateTime):void {
            _dataVencimentoSeguro = value;
        }
        public function get dataVencimentoSeguro():IDateTime {
            return _dataVencimentoSeguro;
        }

        public function set descricao(value:String):void {
            _descricao = value;
        }
        public function get descricao():String {
            return _descricao;
        }
		
		public function set idBemNovo(value:Number):void {
			_idBemNovo = value;
		}
		public function get idBemNovo():Number {
			return _idBemNovo;
		}

        public function set idPessoaCompartilhamento(value:Number):void {
			_idPessoaCompartilhamento = value;
        }
        public function get idPessoaCompartilhamento():Number {
            return _idPessoaCompartilhamento;
        }

        public function set nomeSeguradora(value:String):void {
            _nomeSeguradora = value;
        }
        public function get nomeSeguradora():String {
            return _nomeSeguradora;
        }

        public function set pessoaCompartilhamento(value:PessoaCompartilhamentoVO):void {
            _pessoaCompartilhamento = value;
        }
        public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
            return _pessoaCompartilhamento;
        }

        public function set situacao(value:SituacaoBemVO):void {
            _situacao = value;
        }
        public function get situacao():SituacaoBemVO {
            return _situacao;
        }

        public function set subTipo(value:SubTipoBemVO):void {
            _subTipo = value;
        }
        public function get subTipo():SubTipoBemVO {
            return _subTipo;
        }

        public function set valorAtualMercado(value:Number):void {
            _valorAtualMercado = value;
        }
        public function get valorAtualMercado():Number {
            return _valorAtualMercado;
        }

        public function set valorSeguro(value:Number):void {
            _valorSeguro = value;
        }
        public function get valorSeguro():Number {
            return _valorSeguro;
        }

        public function set percentual(value:Number):void {
            _percentual = value;
        }
        public function get percentual():Number {
            return _percentual;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
			_dataHoraInicio = input.readObject() as IDateTime;
        	_dataVencimentoSeguro = input.readObject() as IDateTime;
            _descricao = input.readObject() as String;
			_idBemNovo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _nomeSeguradora = input.readObject() as String;
            _percentual = input.readObject() as Number;
            _pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
            _situacao = input.readObject() as SituacaoBemVO;
            _subTipo = input.readObject() as SubTipoBemVO;
            _valorAtualMercado = input.readObject() as Number;
            _valorSeguro = input.readObject() as Number;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
			output.writeObject(_dataHoraInicio);        	
        	output.writeObject(_dataVencimentoSeguro);
            output.writeObject(_descricao);
			output.writeObject(isNaN(_idBemNovo) ? null : _idBemNovo);
            output.writeObject(_idPessoaCompartilhamento);
            output.writeObject(_nomeSeguradora);
            output.writeObject(_percentual);
            output.writeObject(_pessoaCompartilhamento);
            output.writeObject(_situacao);
            output.writeObject(_subTipo);
            output.writeObject(_valorAtualMercado);
            output.writeObject(_valorSeguro);
        }
    }
}