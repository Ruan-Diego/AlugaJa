package com.alu.ufc.AlugaJa.modelsDTO;

// TODO Refactor

import com.google.api.client.util.ArrayMap;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDTO {

	String id;
	String cidade;
	String contato;
	String cpf;
	String email;
	String nome;
	String senha;
	String UF;

	public UsuarioDTO(){}

	public UsuarioDTO(String cidade, String contato, String cpf, String email, String nome, String senha, String UF) {
		this.cidade = cidade;
		this.contato = contato;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.UF = UF;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getUF(){
		return UF;
	}

	public void setUF(String UF){
		this.UF = UF;
	}
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [cidade=" + cidade + ", contato=" + contato + ", cpf=" + cpf + ", email=" + email
				+ ", nome=" + nome + ", senha=" + senha + "]";
	}
	
	
}
