package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoProduto {

    private SimpleIntegerProperty codigo = new SimpleIntegerProperty();
    private SimpleStringProperty tipo = new SimpleStringProperty();
    private SimpleStringProperty situacao = new SimpleStringProperty();

    public TipoProduto(){}

    public TipoProduto(String tipo) {
        this.tipo.setValue(tipo);
    }

    public int getCodigo() {
        return codigo.get();
    }

    @Override
    public String toString() {
        return getTipo();
    }

    public SimpleIntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getTipo() {
        return tipo.get();
    }

    public SimpleStringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
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
