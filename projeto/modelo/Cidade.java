package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Cidade {

    private SimpleStringProperty nome = new SimpleStringProperty();
    private SimpleStringProperty cep = new SimpleStringProperty();
    private SimpleIntegerProperty codigo = new SimpleIntegerProperty();
    private Estado estado;
    private SimpleStringProperty situacao = new SimpleStringProperty();

    public Cidade(){}

    public Cidade(SimpleStringProperty nome, SimpleStringProperty cep, Estado estado) {
        this.nome = nome;
        this.cep = cep;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade)) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(cep, cidade.cep);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cep);
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

    public String getCep() {
        return cep.get();
    }

    public SimpleStringProperty cepProperty() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep.set(cep);
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
}
