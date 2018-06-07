package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Estado {
    private SimpleIntegerProperty codigo = new SimpleIntegerProperty();
    private SimpleStringProperty nome = new SimpleStringProperty();
    private SimpleStringProperty uf = new SimpleStringProperty();;
    private SimpleStringProperty situacao = new SimpleStringProperty();
    /*SimpleStringProperty utilizado para facilitar o uso das variaves em tableviews*/

    public Estado(){}

    public Estado(String nome, String uf) {
        this.nome.set(nome);
        this.uf.set(uf);
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

    public String getUf() {
        return uf.get();
    }

    public SimpleStringProperty ufProperty() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf.set(uf);
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
