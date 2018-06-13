package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Usuario {
    private SimpleIntegerProperty codigo = new SimpleIntegerProperty();
    private SimpleStringProperty nome = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty celular = new SimpleStringProperty();
    private SimpleStringProperty senha = new SimpleStringProperty();
    private SimpleStringProperty situacao = new SimpleStringProperty();
    private Cidade cidade;

    public Usuario(){}

    public Usuario(String nome, String email, String celular, String senha , Cidade cidade) {
        this.nome.set(nome);
        this.email.set(email);
        this.celular.set(celular);
        this.senha.set(senha);
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return getNome();
    }

    public int getCodigo() {
        return codigo.get();
    }

    public SimpleIntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCelular() {
        return celular.get();
    }

    public SimpleStringProperty celularProperty() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public String getSenha() {
        return senha.get();
    }

    public SimpleStringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public String getSituacao() {
        return situacao.get();
    }

    public SimpleStringProperty situacaoProperty() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao.set(situacao);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
