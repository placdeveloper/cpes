package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o Bem Móvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemMovel", BemMovelVO);
    public class BemMovelVO extends BemVO {
		
		private var _anoConstrucaoAeronave:Number;
		private var _anoConstrucaoEmbarcacao:Number;
		private var _anoFabricacaoAutomovel:Number;
		private var _anoModeloAutomovel:Number;
		private var _inscricaoEmbarcacao:String;
		private var _matriculaAeronave:String;
		private var _numeroChassi:String;
		private var _placa:String;
		private var _renavam:String;
		private var _tipoBem:TipoBemMovelVO;
		private var _tipoChassi:TipoChassiBemVO;
		private var _tipoCorAutomovel:TipoCorAutomovelBemVO;
		private var _uf:String;
		
		public function get anoConstrucaoAeronave():Number {
			return this._anoConstrucaoAeronave;
		}
		
		public function set anoConstrucaoAeronave(valor:Number):void {
			this._anoConstrucaoAeronave = valor;
		}
		
		public function get anoConstrucaoEmbarcacao():Number {
			return this._anoConstrucaoEmbarcacao;
		}
		
		public function set anoConstrucaoEmbarcacao(valor:Number):void {
			this._anoConstrucaoEmbarcacao = valor;
		}
		
		public function get anoFabricacaoAutomovel():Number {
			return this._anoFabricacaoAutomovel;
		}
		
		public function set anoFabricacaoAutomovel(valor:Number):void {
			this._anoFabricacaoAutomovel = valor;
		}
		
		public function get anoModeloAutomovel():Number {
			return this._anoModeloAutomovel;
		}
		
		public function set anoModeloAutomovel(valor:Number):void {
			this._anoModeloAutomovel = valor;
		}
		
		public function get inscricaoEmbarcacao():String {
			return this._inscricaoEmbarcacao;
		}
		
		public function set inscricaoEmbarcacao(valor:String):void {
			this._inscricaoEmbarcacao = valor;
		}
		
		public function get matriculaAeronave():String {
			return this._matriculaAeronave;
		}
		
		public function set matriculaAeronave(valor:String):void {
			this._matriculaAeronave = valor;
		}
		
		public function get numeroChassi():String {
			return this._numeroChassi;
		}
		
		public function set numeroChassi(valor:String):void {
			this._numeroChassi = valor;
		}
		
		public function get placa():String {
			return this._placa;
		}
		
		public function set placa(valor:String):void {
			this._placa = valor;
		}
		
		public function get renavam():String {
			return this._renavam;
		}
		
		public function set renavam(valor:String):void {
			this._renavam = valor;
		}
		
		public function get tipoBem():TipoBemMovelVO {
			return this._tipoBem;
		}
		
		public function set tipoBem(valor:TipoBemMovelVO):void {
			this._tipoBem = valor;
		}
		
		public function get tipoChassi():TipoChassiBemVO {
			return this._tipoChassi;
		}
		
		public function set tipoChassi(valor:TipoChassiBemVO):void {
			this._tipoChassi = valor;
		}
		
		public function get tipoCorAutomovel():TipoCorAutomovelBemVO {
			return this._tipoCorAutomovel;
		}
		
		public function set tipoCorAutomovel(valor:TipoCorAutomovelBemVO):void {
			this._tipoCorAutomovel = valor;
		}
		
		public function get uf():String {
			return this._uf;
		}
		
		public function set uf(valor:String):void {
			this._uf = valor;
		}
		
        override public function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_anoConstrucaoAeronave = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_anoConstrucaoEmbarcacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_anoFabricacaoAutomovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_anoModeloAutomovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_inscricaoEmbarcacao = input.readObject() as String;
			_matriculaAeronave = input.readObject() as String;
			_numeroChassi = input.readObject() as String;
			_placa = input.readObject() as String;
			_renavam = input.readObject() as String;
			_tipoBem = input.readObject() as TipoBemMovelVO;
			_tipoChassi = input.readObject() as TipoChassiBemVO;
			_tipoCorAutomovel = input.readObject() as TipoCorAutomovelBemVO;
			_uf = input.readObject() as String;
        }

        override public function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_anoConstrucaoAeronave);
			output.writeObject(_anoConstrucaoEmbarcacao);
			output.writeObject(_anoFabricacaoAutomovel);
			output.writeObject(_anoModeloAutomovel);
			output.writeObject(_inscricaoEmbarcacao);
			output.writeObject(_matriculaAeronave);
			output.writeObject(_numeroChassi);
			output.writeObject(_placa);
			output.writeObject(_renavam);
			output.writeObject(_tipoBem);
			output.writeObject(_tipoChassi);
			output.writeObject(_tipoCorAutomovel);
			output.writeObject(_uf);
        }
    }
}