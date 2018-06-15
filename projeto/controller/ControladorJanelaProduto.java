package projeto.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import projeto.modelo.Produto;

public class ControladorJanelaProduto {


    @FXML
    private ImageView imagemProduto;

    @FXML
    private Label nomeProdLabel;

    @FXML
    private Label precoLabel;

    @FXML
    private Label vendedorLabel;

    @FXML
    private Label contatoLabel;

    @FXML
    private TextArea descField;

        public void mostrarProduto(Produto produto){
            nomeProdLabel.setText(produto.getNome());
            precoLabel.setText(String.valueOf(produto.getPreco()));
            vendedorLabel.setText(produto.getUsuario().getNome());
            contatoLabel.setText(produto.getUsuario().getCelular());
            descField.setText(produto.getDescricao());
        }

}
