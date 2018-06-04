package projeto.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class TipoProduto {

    private IntegerProperty codigo;
    private StringProperty tipo;
    private StringProperty situacao;

    public TipoProduto(){}

    public TipoProduto(StringProperty tipo) {
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo.get();
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
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
