package br.com.sicoob.capes.comum.vo {

    import br.com.bancoob.vo.BancoobVO;

    public class CamposFichaCadastralVOBase extends BancoobVO {

        private var _atual:Boolean;
		private var _atualCompleto:Boolean;
		private var _historicoEm:Boolean;
		private var _emBranco:Boolean;
        private var _data:Date;
		private var _dataFim:Date;
        private var _historico:Boolean;
        private var _posicao:Boolean;
		
		private var _autorizacao:Boolean;
        private var _bens:Boolean;
        private var _certidoes:Boolean;
        private var _emails:Boolean;
        private var _enderecos:Boolean;
        private var _produtores:Boolean;
        private var _fontesDeRenda:Boolean;
        
        private var _qtdBens:Number;
        private var _qtdBensOnus:Number;
        private var _qtdBensPosse:Number;
        private var _qtdBensRegistro:Number;
        private var _qtdCertidoes:Number;
        private var _qtdEmails:Number;
        private var _qtdEnderecos:Number;
        private var _qtdProdutores:Number;
        private var _qtdProdutividades:Number;
        private var _qtdReferencias:Number;
        private var _qtdRelacionamentos:Number;
        private var _qtdTelefones:Number;
        private var _referencias:Boolean;
        private var _relacionamentos:Boolean;
        private var _telefones:Boolean;
        private var _tipoPessoa:Number;
        private var _qtdFontesRenda:Number;
        private var _qtdBensImoveis:Number;

		public function CamposFichaCadastralVOBase() {
			super();
		}

        public function set atual(value:Boolean):void {
            _atual = value;
        }
        public function get atual():Boolean {
            return _atual;
        }
		
		public function set atualCompleto(value:Boolean):void {
			_atualCompleto = value;
		}
		public function get atualCompleto():Boolean {
			return _atualCompleto;
		}
		
		public function set historicoEm(value:Boolean):void {
			_historicoEm = value;
		}
		
		public function get historicoEm():Boolean {
			return _historicoEm;
		}
		
		public function set emBranco(value:Boolean):void{
			_emBranco = value;
		}
		
		public function get emBranco():Boolean{
			return _emBranco;
		}
		
		public function set autorizacao(value:Boolean):void {
			_autorizacao = value;
		}
		public function get autorizacao():Boolean {
			return _autorizacao;
		}

        public function set bens(value:Boolean):void {
            _bens = value;
        }
        public function get bens():Boolean {
            return _bens;
        }

        public function set certidoes(value:Boolean):void {
            _certidoes = value;
        }
        public function get certidoes():Boolean {
            return _certidoes;
        }

        public function set data(value:Date):void {
            _data = value;
        }
        public function get data():Date {
            return _data;
        }
		
		public function set dataFim(value:Date):void {
			_dataFim = value;
		}
		public function get dataFim():Date {
			return _dataFim;
		}

        public function set emails(value:Boolean):void {
            _emails = value;
        }
        public function get emails():Boolean {
            return _emails;
        }

        public function set enderecos(value:Boolean):void {
            _enderecos = value;
        }
        public function get enderecos():Boolean {
            return _enderecos;
        }

        public function set historico(value:Boolean):void {
            _historico = value;
        }
        public function get historico():Boolean {
            return _historico;
        }

        public function set posicao(value:Boolean):void {
            _posicao = value;
        }
        public function get posicao():Boolean {
            return _posicao;
        }

        public function set produtores(value:Boolean):void {
            _produtores = value;
        }
        public function get produtores():Boolean {
            return _produtores;
        }

        public function set fontesDeRenda(value:Boolean):void {
            _fontesDeRenda = value;
        }
        public function get fontesDeRenda():Boolean {
            return _fontesDeRenda;
        }

        public function set qtdBens(value:Number):void {
            _qtdBens = value;
        }
        public function get qtdBens():Number {
            return _qtdBens;
        }

        public function set qtdBensOnus(value:Number):void {
            _qtdBensOnus = value;
        }
        public function get qtdBensOnus():Number {
            return _qtdBensOnus;
        }

        public function set qtdBensPosse(value:Number):void {
            _qtdBensPosse = value;
        }
        public function get qtdBensPosse():Number {
            return _qtdBensPosse;
        }

        public function set qtdProdutividades(value:Number):void {
            _qtdProdutividades = value;
        }
        public function get qtdProdutividades():Number {
            return _qtdProdutividades;
        }

        public function set qtdBensRegistro(value:Number):void {
            _qtdBensRegistro = value;
        }
        public function get qtdBensRegistro():Number {
            return _qtdBensRegistro;
        }

        public function set qtdCertidoes(value:Number):void {
            _qtdCertidoes = value;
        }
        public function get qtdCertidoes():Number {
            return _qtdCertidoes;
        }

        public function set qtdEmails(value:Number):void {
            _qtdEmails = value;
        }
        public function get qtdEmails():Number {
            return _qtdEmails;
        }

        public function set qtdEnderecos(value:Number):void {
            _qtdEnderecos = value;
        }
        public function get qtdEnderecos():Number {
            return _qtdEnderecos;
        }

        public function set qtdProdutores(value:Number):void {
            _qtdProdutores = value;
        }
        public function get qtdProdutores():Number {
            return _qtdProdutores;
        }

        public function set qtdReferencias(value:Number):void {
            _qtdReferencias = value;
        }
        public function get qtdReferencias():Number {
            return _qtdReferencias;
        }

        public function set qtdRelacionamentos(value:Number):void {
            _qtdRelacionamentos = value;
        }
        public function get qtdRelacionamentos():Number {
            return _qtdRelacionamentos;
        }

        public function set qtdTelefones(value:Number):void {
            _qtdTelefones = value;
        }
        public function get qtdTelefones():Number {
            return _qtdTelefones;
        }

        public function set qtdFontesRenda(value:Number):void {
            _qtdFontesRenda = value;
        }
        public function get qtdFontesRenda():Number {
            return _qtdFontesRenda;
        }

        public function set qtdBensImoveis(value:Number):void {
            _qtdBensImoveis = value;
        }
        public function get qtdBensImoveis():Number {
            return _qtdBensImoveis;
        }

        public function set referencias(value:Boolean):void {
            _referencias = value;
        }
        public function get referencias():Boolean {
            return _referencias;
        }

        public function set relacionamentos(value:Boolean):void {
            _relacionamentos = value;
        }
        public function get relacionamentos():Boolean {
            return _relacionamentos;
        }

        public function set telefones(value:Boolean):void {
            _telefones = value;
        }
        public function get telefones():Boolean {
            return _telefones;
        }

        public function set tipoPessoa(value:Number):void {
            _tipoPessoa = value;
        }
        public function get tipoPessoa():Number {
            return _tipoPessoa;
        }

    }
}