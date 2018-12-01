package projeto.modelo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Produto {
    private SimpleIntegerProperty codigo = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty descricao = new SimpleStringProperty();
    private SimpleDoubleProperty preco = new SimpleDoubleProperty();
    private SimpleIntegerProperty quantidade = new SimpleIntegerProperty();
    private TipoProduto tipo;
    private Usuario usuario;

    public Produto(){}

    public Produto(String nome, String descricao, String preco , Usuario usuario , TipoProduto tipo , int quantidade) {
        this.nome.setValue(nome);
        this.descricao.setValue(descricao);
        this.preco.setValue(Double.parseDouble(preco));
        this.usuario = usuario;
        this.tipo = tipo;
        this.quantidade.set(quantidade);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo);
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
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

    public StringProperty nomeProperty() {
       return nome;
   }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public double getPreco() {
        return preco.get();
    }

    public SimpleDoubleProperty precoProperty() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco.set(preco);
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void diminuirQuantidade(){
        this.quantidade.set(quantidade.getValue()-1);
    }

}
