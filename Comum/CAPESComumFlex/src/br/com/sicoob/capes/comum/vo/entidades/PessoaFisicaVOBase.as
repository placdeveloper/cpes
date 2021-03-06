/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Tue Jan 17 09:51:01 BRST 2012.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (PessoaFisicaVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades {

    import br.com.bancoob.sisbr.util.log.SisbrLogger;
    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.IDateTime;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    import br.com.bancoob.util.log.ILog;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    import mx.utils.ObjectUtil;
    
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class PessoaFisicaVOBase extends PessoaCompartilhamentoVO {

		private var _conjuge:PessoaCompartilhamentoVO;
        private var _dataEmissaoDocumento:IDateTime;
        private var _dataNascimento:IDateTime;
        private var _descNaturalidade:String;
        private var _estadoCivil:EstadoCivilVO;
        private var _grauInstrucao:GrauInstrucaoVO;
        private var _idNaturalidade:Number;
		private var _listaCidadania:ListCollectionView;
		private var _listaEnderecoFiscal:ListCollectionView;
        private var _menorEmancipado:Booleano;
        private var _nacionalidade:NacionalidadeVO;
        private var _nomeMae:String;
        private var _nomePai:String;
        private var _numeroDocumento:String;
        private var _ocupacaoProfissional:OcupacaoProfissionalVO;
        private var _orgaoExpedidorDocumento:String;
        private var _quantidadeDependentes:Number;
        private var _regimeCasamento:RegimeCasamentoVO;
        private var _tipoDocumento:TipoDocumentoIdentificacaoVO;
        private var _tipoSexo:String;
        private var _ufOrgaoExpedidorDocumento:String;
        private var _uniaoEstavel:Booleano;
        private var _vinculoEmpregaticio:VinculoEmpregaticioVO;
		
		public function set conjuge(value:PessoaCompartilhamentoVO):void {
			_conjuge = value;
		}
		public function get conjuge():PessoaCompartilhamentoVO {
			return _conjuge;
		}
		
        public function set dataEmissaoDocumento(value:IDateTime):void {
            _dataEmissaoDocumento = value;
        }
        public function get dataEmissaoDocumento():IDateTime {
            return _dataEmissaoDocumento;
        }

        public function set dataNascimento(value:IDateTime):void {
            _dataNascimento = value;
        }
        public function get dataNascimento():IDateTime {
            return _dataNascimento;
        }

        public function set descNaturalidade(value:String):void {
            _descNaturalidade = value;
        }
        public function get descNaturalidade():String {
            return _descNaturalidade;
        }

        public function set estadoCivil(value:EstadoCivilVO):void {
            _estadoCivil = value;
        }
        public function get estadoCivil():EstadoCivilVO {
            return _estadoCivil;
        }

        public function set grauInstrucao(value:GrauInstrucaoVO):void {
            _grauInstrucao = value;
        }
        public function get grauInstrucao():GrauInstrucaoVO {
            return _grauInstrucao;
        }

        public function set idNaturalidade(value:Number):void {
            _idNaturalidade = value;
        }
        public function get idNaturalidade():Number {
            return _idNaturalidade;
        }
		
		public function get listaCidadania():ListCollectionView {
			return _listaCidadania;
		}
		
		public function set listaCidadania(value:ListCollectionView):void {
			_listaCidadania = value;
		}
		
		public function get listaEnderecoFiscal():ListCollectionView {
			return _listaEnderecoFiscal;
		}
		
		public function set listaEnderecoFiscal(value:ListCollectionView):void {
			_listaEnderecoFiscal = value;
		}

        public function set menorEmancipado(value:Booleano):void {
            _menorEmancipado = value;
        }
        public function get menorEmancipado():Booleano {
            return _menorEmancipado;
        }

        public function set nacionalidade(value:NacionalidadeVO):void {
            _nacionalidade = value;
        }
        public function get nacionalidade():NacionalidadeVO {
            return _nacionalidade;
        }

        public function set nomeMae(value:String):void {
            _nomeMae = value;
        }
        public function get nomeMae():String {
            return _nomeMae;
        }

        public function set nomePai(value:String):void {
            _nomePai = value;
        }
        public function get nomePai():String {
            return _nomePai;
        }

        public function set numeroDocumento(value:String):void {
            _numeroDocumento = value;
        }
        public function get numeroDocumento():String {
            return _numeroDocumento;
        }

        public function set ocupacaoProfissional(value:OcupacaoProfissionalVO):void {
            _ocupacaoProfissional = value;
        }
        public function get ocupacaoProfissional():OcupacaoProfissionalVO {
            return _ocupacaoProfissional;
        }

        public function set orgaoExpedidorDocumento(value:String):void {
            _orgaoExpedidorDocumento = value;
        }
        public function get orgaoExpedidorDocumento():String {
            return _orgaoExpedidorDocumento;
        }

        public function set quantidadeDependentes(value:Number):void {
            _quantidadeDependentes = value;
        }
        public function get quantidadeDependentes():Number {
            return _quantidadeDependentes;
        }

        public function set regimeCasamento(value:RegimeCasamentoVO):void {
            _regimeCasamento = value;
        }
        public function get regimeCasamento():RegimeCasamentoVO {
            return _regimeCasamento;
        }

        public function set tipoDocumento(value:TipoDocumentoIdentificacaoVO):void {
            _tipoDocumento = value;
        }
        public function get tipoDocumento():TipoDocumentoIdentificacaoVO {
            return _tipoDocumento;
        }

        public function set tipoSexo(value:String):void {
            _tipoSexo = value;
        }
        public function get tipoSexo():String {
            return _tipoSexo;
        }

        public function set ufOrgaoExpedidorDocumento(value:String):void {
            _ufOrgaoExpedidorDocumento = value;
        }
        public function get ufOrgaoExpedidorDocumento():String {
            return _ufOrgaoExpedidorDocumento;
        }

        public function set uniaoEstavel(value:Booleano):void {
            _uniaoEstavel = value;
        }
        public function get uniaoEstavel():Booleano {
            return _uniaoEstavel;
        }

        public function set vinculoEmpregaticio(value:VinculoEmpregaticioVO):void {
            _vinculoEmpregaticio = value;
        }
        public function get vinculoEmpregaticio():VinculoEmpregaticioVO {
            return _vinculoEmpregaticio;
        }
		
        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
            if (meta::isInitialized()) {
				_conjuge = input.readObject() as PessoaCompartilhamentoVO;
            	_dataEmissaoDocumento = input.readObject() as IDateTime;
            	_dataNascimento = input.readObject() as IDateTime;
                _descNaturalidade = input.readObject() as String;
                _estadoCivil = input.readObject() as EstadoCivilVO;
                _grauInstrucao = input.readObject() as GrauInstrucaoVO;
                _idNaturalidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_listaCidadania = input.readObject() as ListCollectionView;
				_listaEnderecoFiscal = input.readObject() as ListCollectionView;
				_menorEmancipado = SerializacaoObjetos.lerBooleano(input);
                _nacionalidade = input.readObject() as NacionalidadeVO;
                _nomeMae = input.readObject() as String;
                _nomePai = input.readObject() as String;
                _numeroDocumento = input.readObject() as String;
                _ocupacaoProfissional = input.readObject() as OcupacaoProfissionalVO;
                _orgaoExpedidorDocumento = input.readObject() as String;
                _quantidadeDependentes = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _regimeCasamento = input.readObject() as RegimeCasamentoVO;
                _tipoDocumento = input.readObject() as TipoDocumentoIdentificacaoVO;
                _tipoSexo = input.readObject() as String;
                _ufOrgaoExpedidorDocumento = input.readObject() as String;
            	_uniaoEstavel = SerializacaoObjetos.lerBooleano(input);
                _vinculoEmpregaticio = input.readObject() as VinculoEmpregaticioVO;
            }
        }

        override public function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
            if (meta::isInitialized()) {
			output.writeObject(_conjuge);
        	output.writeObject(_dataEmissaoDocumento);
			output.writeObject(_dataNascimento);
			output.writeObject(_descNaturalidade);
			output.writeObject(_estadoCivil);
			output.writeObject(_grauInstrucao);
			output.writeObject(_idNaturalidade);
			output.writeObject(_listaCidadania);
			output.writeObject(_listaEnderecoFiscal);
			SerializacaoObjetos.escreverBooleano(_menorEmancipado, output);
			output.writeObject(_nacionalidade);
			output.writeObject(_nomeMae);
			output.writeObject(_nomePai);
			output.writeObject(_numeroDocumento);
			output.writeObject(_ocupacaoProfissional);
			output.writeObject(_orgaoExpedidorDocumento);
			output.writeObject(_quantidadeDependentes);
			output.writeObject(_regimeCasamento);
			output.writeObject(_tipoDocumento);
			output.writeObject(_tipoSexo);
			output.writeObject(_ufOrgaoExpedidorDocumento);
			SerializacaoObjetos.escreverBooleano(_uniaoEstavel, output);
			output.writeObject(_vinculoEmpregaticio);
			}
        }
    }
}