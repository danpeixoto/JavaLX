package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Estado {
    private IntegerProperty codigo;
    private StringProperty nome;
    private StringProperty uf;
    private StringProperty situacao;

    public Estado(){}

    public Estado(StringProperty nome, StringProperty uf) {
        this.nome = nome;
        this.uf = uf;
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

    public String getUf() {
        return uf.get();
    }

    public StringProperty ufProperty() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf.set(uf);
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
}
