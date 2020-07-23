package br.com.sicoob.capes.cadastrarPessoa
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.UploadArquivo;
	import br.com.bancoob.sisbr.Configuracoes;
	import br.com.bancoob.util.Servicos;
	import br.com.sicoob.capes.cadastrarPessoa.dto.PessoaLegadoReqDTO;
	import br.com.sicoob.capes.comum.vo.PessoaLegadoVO;
	
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.MouseEvent;
	import flash.events.ProgressEvent;
	import flash.filters.DropShadowFilter;
	import flash.filters.GlowFilter;
	import flash.net.FileFilter;
	import flash.utils.ByteArray;
	
	import mx.core.Application;
	import mx.core.IFlexDisplayObject;
	import mx.effects.Fade;
	import mx.effects.Move;
	import mx.effects.Parallel;
	import mx.effects.Resize;
	import mx.events.EffectEvent;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class frmImagensPessoaCUC extends frmImagensPessoaCUCView
	{
		
		public var registroImg:PessoaLegadoVO = null;
		public var registroBkpImagem:PessoaLegadoVO = null;		
		private var servicos:Servicos = new Servicos();			
		
		private var compVisualizarTelaCheia:visualisarTelaCheia;
		
		[Embed(source="br/com/bancoob/imagens/imgAssinaturaPessoa.png")]
        [Bindable]
        public var imgAssinaturaProvisoria:Class;
        
        [Embed(source="br/com/bancoob/imagens/imgFotoPessoa.png")]
        [Bindable]
        public var imgFotoProvisoria:Class;
        
        public var byteArrayAssinatura:ByteArray;
        public var byteArrayFoto:ByteArray;
        
        private var msgMaxizarImagem:String  = "Clique duas vezes para Maximizar a imagem.";
        
		public var glowFilter:GlowFilter;
		public var dropShadowFilter:DropShadowFilter;
		private var fadeIn:Fade = new Fade();
		
		private var urlFotoBkp:String;
		private var urlAssinaturaBkp:String;
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmImagensPessoaCUC()
		{
			super();			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		public function init(evt:FlexEvent):void{	
			obterTamanhoMaxUpload();
			btGravar.addEventListener(MouseEvent.CLICK,gravarImagens);
			btCancelar.addEventListener(MouseEvent.CLICK,cancelarImagens);
			btVoltar.addEventListener(MouseEvent.CLICK,fecharJanelaImagens);
			
			glowFilter = new GlowFilter(0x6c7d6c, .50, 6, 6, 2, 0,false, false);
			dropShadowFilter = new DropShadowFilter();
	        dropShadowFilter.alpha = 0.35;
	        dropShadowFilter.distance = 6;
			
			imagAssinutura.addEventListener(Event.COMPLETE,onLoadImagAssinutura);
			imagAssinutura.addEventListener(IOErrorEvent.IO_ERROR,ImgRowError);
			imagFoto.addEventListener(Event.COMPLETE,onLoadImagFoto);
			imagAssinutura.addEventListener(ProgressEvent.PROGRESS,progressImagAssinutura);
			imagFoto.addEventListener(ProgressEvent.PROGRESS,progressImagFoto);
			imagFoto.addEventListener(IOErrorEvent.IO_ERROR,ImgRowError);
			
			btLimparAssinatura.addEventListener(MouseEvent.CLICK,limparAssinatura);
			btLimparFoto.addEventListener(MouseEvent.CLICK,limparFoto);
			
			btSelecionarAssinatura.addEventListener(UploadArquivo.UPLOAD_FINALIZADO,uploadFinalizadoAssinatura);
			btSelecionarFoto.addEventListener(UploadArquivo.UPLOAD_FINALIZADO,uploadFinalizadoFoto);
			btSelecionarAssinatura.urlServidorUpload = Configuracoes.urlServidorUpload;
			btSelecionarAssinatura.urlUpload = Configuracoes.urlUpload;
			btSelecionarFoto.urlServidorUpload = Configuracoes.urlServidorUpload;
			btSelecionarFoto.urlUpload = Configuracoes.urlUpload;
			
			imagFoto.doubleClickEnabled = false;
			imagAssinutura.doubleClickEnabled = false;
			imagFoto.addEventListener(MouseEvent.DOUBLE_CLICK,zoomImage);
			imagAssinutura.addEventListener(MouseEvent.DOUBLE_CLICK,zoomImage);
			
	        imagAssinutura.filters = [dropShadowFilter];
			imagFoto.filters = [dropShadowFilter];
			
			imagAssinutura.source = null;
			imagFoto.source = null;
			
			fadeIn.duration = 900;
			fadeIn.alphaFrom = 0.00;
			fadeIn.alphaTo = 1.00;
			
			btSelecionarAssinatura.tiposArquivos = configTipoImagem();
			btSelecionarFoto.tiposArquivos = configTipoImagem();
			
		}

		public function cancelarImagens(evt:MouseEvent=null):void{
			registroImg = ObjectUtil.copy(registroBkpImagem) as PessoaLegadoVO;
			loadAssinaturaPessoa();
			loadFotoPessoa();
		}

		private function gravarImagens(evt:MouseEvent):void {
			if (verificarDadosAlteradosImagens()){		
				return;	
			}

			var req:PessoaLegadoReqDTO = new PessoaLegadoReqDTO(); 
			req.DadosPessoa = registroImg;
			
			MostraCursor.setBusyCursor("Gravando Imagens!", Application.application);
			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Pessoa",gravarImagens_onResult, null, Configuracoes.destinoVOPadrao).gravarImagens(req);			
		}

		private function gravarImagens_onResult(evt:ResultEvent):void {	
			MostraCursor.removeBusyCursor();	
			this.fecharJanela();
		}
		
		private function fecharJanelaImagens(evt:MouseEvent=null):void{
			this.fecharJanela();	
		}

		private function verificarDadosAlteradosImagens():Boolean{
			var isAlterar:Boolean=true;
			//foto
			if(registroImg.FotoPessoa != registroBkpImagem.FotoPessoa){
				isAlterar = false;
				//Verificar se a imagem foi limpa	
				if(registroImg.FotoPessoa == null){
					registroImg.acaoImagemFoto = "EXCLUIR";
				}else{
					registroImg.acaoImagemFoto = "INCLUIR";	
				}
			}else{
				registroImg.acaoImagemFoto = "SEM_ALTERACAO";
			}
			
			//assinatura
			if(registroImg.AssinaturaPessoa != registroBkpImagem.AssinaturaPessoa){
				isAlterar = false;
				//Verificar se a imagem foi limpa	
				if(registroImg.AssinaturaPessoa == null){
					registroImg.acaoImagemAssinatura = "EXCLUIR";
				}else{
					registroImg.acaoImagemAssinatura = "INCLUIR";	
				}
			}else{
				registroImg.acaoImagemAssinatura = "SEM_ALTERACAO";				
			}
			return isAlterar;
	
		}

		private function obterTamanhoMaxUpload():void{			
			//Tamanho dos arquivos
 			MostraCursor.setBusyCursor("Obter Definições ...", Application.application, MostraCursor.CURSOR_PESQUISAR);			
			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Pessoa",
									obterTamanhoMaxUpload_onResult, null, Configuracoes.destinoVOPadrao)
									.obterDefinicoes();
		}

		private function obterTamanhoMaxUpload_onResult(evt:ResultEvent):void {
			var tamanhoMaximoArquivo:Number = Number(evt.result.listas["TAMANHO_MAXIMO_DOS_ARQUIVO_DE_IMAGENS"])*1024;
			btSelecionarAssinatura.tamanhoMaximo = tamanhoMaximoArquivo;
			btSelecionarFoto.tamanhoMaximo = tamanhoMaximoArquivo;
			MostraCursor.removeBusyCursor();
		}		
			
		public function loadAssinaturaPessoa():void{
			if(registroImg != null){
				if(registroImg.AssinaturaPessoa != null){
					isAssinatura();
				}else{
					notAssinatura();
				}
			}
			else{
				registroImg = new PessoaLegadoVO();
				notAssinatura();
				//notFoto();
			}
		}
		
		public function cancelarTelaImagens(evt:MouseEvent=null):void {			
			if(registroImg.AssinaturaPessoa == null){
			    notAssinatura();
			}else{
			   	isAssinatura();
			}
		}
		
		public function onLoadImagAssinutura(event:Event):void{
			MostraCursor.removeBusyCursor();			
			//loadFotoPessoa();
		}
		
		public function loadFotoPessoa():void{
			if(registroImg != null){
				if(registroImg.FotoPessoa != null){
					isFoto();
				}else{
					notFoto();
				}
			}
			else{
				notFoto();
			}
		}
		
		private function isFoto(url:String=null):void{
			if(url){
				imagFoto.load(url);
			}else if(byteArrayFoto == null){
				imagFoto.load(urlFotoBkp);
			}else{
				imagFoto.load(byteArrayFoto);
			}
			btLimparFoto.enabled = true;
			imagFoto.doubleClickEnabled = true;				
			imagFoto.toolTip = msgMaxizarImagem;
			imagFoto.scaleContent = true;
		}
		
		private function notFoto():void{
			imagFoto.source = imgFotoProvisoria;
			boxFoto.addChildAt(imagFoto,boxFoto.getChildIndex(imagFoto));
			btLimparFoto.enabled = false;
			imagFoto.doubleClickEnabled = false;
			imagFoto.toolTip = "Foto não cadastrada.";
			imagFoto.load();
			imagFoto.scaleContent = false;
		}
		
		private function ImgRowError(event:IOErrorEvent):void{
			Alerta.show(event.text,"Erro ao Carregar Imagem.",Alerta.ALERTA_ERRO);
		}
		
		public function onLoadImagFoto(event:Event):void{
			imagFoto.validateNow();
			MostraCursor.removeBusyCursor();
		}
		
		public function progressImagAssinutura(event:ProgressEvent):void{
			MostraCursor.setBusyCursor("Carregando Assinatura",Application.application,MostraCursor.CURSOR_PROGRESSO);			
			MostraCursor.setaProgresso(event.bytesTotal, event.bytesLoaded);
			//trace("Carregando Assinatura "+Math.round((event.bytesLoaded/event.bytesTotal)*100)+"%");
		}
		
		public function progressImagFoto(event:ProgressEvent):void{
			MostraCursor.setBusyCursor("Carregando Foto",Application.application,MostraCursor.CURSOR_PROGRESSO);
			MostraCursor.setaProgresso(event.bytesTotal, event.bytesLoaded);
			//trace("Carregando Foto "+Math.round((event.bytesLoaded/event.bytesTotal)*100)+"%");			
		}
		
		public function limparAssinatura(event:Event=null):void{
			registroImg.AssinaturaPessoa = null;
			fadeIn.play([imagAssinutura]);			
			notAssinatura();
		}
		
		private function isAssinatura(url:String=null):void{
			if(url){
				imagAssinutura.load(url);
			}else if(byteArrayAssinatura == null){
				imagAssinutura.load(urlAssinaturaBkp);
			}else{
				imagAssinutura.load(byteArrayAssinatura);
			}
			btLimparAssinatura.enabled = true;
			imagAssinutura.doubleClickEnabled = true;				
			imagAssinutura.toolTip = msgMaxizarImagem;
			imagAssinutura.scaleContent = true;	
		}
		
		private function notAssinatura():void{
			imagAssinutura.source = imgAssinaturaProvisoria;
			boxAssinatura.addChildAt(imagAssinutura,boxAssinatura.getChildIndex(imagAssinutura));
			btLimparAssinatura.enabled = false;
			imagAssinutura.doubleClickEnabled = false;
			imagAssinutura.toolTip = "Assinatura não cadastrada.";
			imagAssinutura.load();			
			imagAssinutura.scaleContent = false;
			loadFotoPessoa();
		}
		
		public function limparFoto(event:Event=null):void{
			registroImg.FotoPessoa = null;
			fadeIn.play([imagFoto]);			
			notFoto();			
		}
		
		public function uploadFinalizadoAssinatura(event:Event):void{
			registroImg.AssinaturaPessoa = event.target.arquivoEnviado;
			urlAssinaturaBkp = event.target.caminhoRelativo;	
			isAssinatura(event.target.caminhoRelativo);
		}
		public function uploadFinalizadoFoto(event:Event):void{
			registroImg.FotoPessoa = event.target.arquivoEnviado;
			urlFotoBkp = event.target.caminhoRelativo;
			isFoto(event.target.caminhoRelativo);
		}
		
		public function zoomImage(event:Event=null):void{
			compVisualizarTelaCheia = new visualisarTelaCheia();
			compVisualizarTelaCheia.visible = false;
			//this.addChild(compVisualizarTelaCheia);
			PopUpManager.addPopUp(compVisualizarTelaCheia,parent, true);
			
			compVisualizarTelaCheia.img.addEventListener(ProgressEvent.PROGRESS,progressImg);
			compVisualizarTelaCheia.img.addEventListener(IOErrorEvent.IO_ERROR,ImgRowError);

			var iconBitmapData:BitmapData = new BitmapData(event.target.content.width,event.target.content.height,true,0);
			iconBitmapData.draw(event.target.content);
			compVisualizarTelaCheia.img.source = (new Bitmap(iconBitmapData));
			//compVisualizarTelaCheia.img.source = String(event.currentTarget.source);
			//compVisualizarTelaCheia.img.load(String(event.currentTarget.source));

			compVisualizarTelaCheia.width = Application.application.width;
			compVisualizarTelaCheia.height = Application.application.height;

			compVisualizarTelaCheia.setStyle("horizontalAlign","center");
			compVisualizarTelaCheia.setStyle("verticalAlign","middle");

			compVisualizarTelaCheia.addEventListener("close",removePopUp);			

			compVisualizarTelaCheia.y = event.currentTarget.y;
			compVisualizarTelaCheia.x = event.currentTarget.x;

			compVisualizarTelaCheia.visible = true;

			var resize:Resize = new Resize(compVisualizarTelaCheia);
			resize.widthTo = Application.application.width;
			resize.heightTo = Application.application.height;			
			
			var move:Move = new Move(compVisualizarTelaCheia);
			move.xTo = 0;
			move.yTo = 0;
			
			var paralel:Parallel = new Parallel();
			paralel.addChild(resize);
			paralel.addChild(move);			
			paralel.play();
		}
		
		private function progressImg(event:ProgressEvent):void{
			compVisualizarTelaCheia.status = "Carregando Imagem: "+Math.round((event.bytesLoaded/event.bytesTotal)*100)+"%";
			if(Math.round((event.bytesLoaded/event.bytesTotal)*100) == 100)
			compVisualizarTelaCheia.status = "";
		}
		
		private function removePopUp(event:Event):void{
			var resize:Resize = new Resize();
			resize.widthTo = 0;
			resize.heightTo = 50;
			
			var move:Move = new Move();
			move.xTo = Application.application.width/2;//containerCompsImagens.x+(containerCompsImagens.width/2);//
			move.yTo = Application.application.height/2;
			
			var paralel:Parallel = new Parallel(event.currentTarget);
			paralel.addChild(resize);
			paralel.addChild(move);
			paralel.addEventListener(EffectEvent.EFFECT_END,removecontainerCompsImagens);
			paralel.play();
		}
		
		private function removecontainerCompsImagens(event:EffectEvent):void{
			PopUpManager.removePopUp(event.effectInstance.target as IFlexDisplayObject);
		}
		
		private function configTipoImagem():Array{
			var arrTipos:Array = new Array();
			var tipo:FileFilter = new FileFilter("Imagens", "*.jpg");
			arrTipos.push(tipo);
			return arrTipos;
		}
		
	}
}