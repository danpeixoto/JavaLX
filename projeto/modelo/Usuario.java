package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private IntegerProperty codigo;
    private StringProperty nome;
    private StringProperty email;
    private StringProperty celular;
    private StringProperty senha;
    private Cidade cidade;
    private StringProperty situacao;

    public Usuario(){}

    public Usuario(StringProperty nome, StringProperty email, StringProperty celular, StringProperty senha, Cidade cidade) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
        this.cidade = cidade;
    }

    public String getSituacao() {
        return situacao.get();
    }

    public StringProperty situacaoProperty() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao.set(situacao);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCelular() {
        return celular.get();
    }

    public StringProperty celularProperty() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public String getSenha() {
        return senha.get();
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
