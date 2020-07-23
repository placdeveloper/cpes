package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.Collections;
import java.util.List;

import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

public class FichaCadastralContatoVO {
	private List<Email> emails;
	private List<EnderecoBase> enderecos;
	private List<Telefone> telefones;
	
	public List<Email> getEmails() {
		if(emails == null){
			return Collections.emptyList();
		}
		return emails;
	}
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	public List<EnderecoBase> getEnderecos() {
		if(enderecos == null){
			return Collections.emptyList();
		}
		return enderecos;
	}
	public void setEnderecos(List<EnderecoBase> enderecos) {
		this.enderecos = enderecos;
	}
	public List<Telefone> getTelefones() {
		if(telefones == null){
			return Collections.emptyList();
		}
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
}
