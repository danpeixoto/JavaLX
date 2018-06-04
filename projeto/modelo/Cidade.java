package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Cidade {

    private StringProperty nome;
    private StringProperty cep;
    private IntegerProperty codigo;
    private Estado estado;
    private StringProperty situacao;

    public Cidade(StringProperty nome, StringProperty cep, Estado estado) {
        this.nome = nome;
        this.cep = cep;
        this.estado = estado;
    }

    public Cidade(){}
    public String getSituacao() {
        return situacao.get();
    }

    public StringProperty situacaoProperty() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao.set(situacao);
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

    public String getCep() {
        return cep.get();
    }

    public StringProperty cepProperty() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep.set(cep);
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
