package projeto.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.dao.ProdutoDAO;
import projeto.modelo.Produto;
import projeto.servicos.DbConnection;

public class ControladorJModProduto {
    @FXML
    private TextField nomeProduto;

    @FXML
    private Button modificarBnt;

    @FXML
    private TextField precoProduto;

    @FXML
    private TextArea descProduto;

    @FXML
    private Label erroLabel;

    private Produto produtoModificado;

    @FXML
    void modificarProduto() {
        produtoModificado.setNome(nomeProduto.getText().toUpperCase());
        produtoModificado.setDescricao(descProduto.getText().toUpperCase());
        produtoModificado.setPreco(Double.parseDouble(precoProduto.getText()));
        ProdutoDAO produtoDAO = new ProdutoDAO(DbConnection.getConexao());
        produtoDAO.update(produtoModificado);
        Stage stage = (Stage) modificarBnt.getScene().getWindow();
        stage.close();
    }



    void adicionarInformacoes(Produto p){
        nomeProduto.setText(p.getNome());
        descProduto.setText(p.getDescricao());
        precoProduto.setText(String.valueOf(p.getPreco()));
        produtoModificado = p;
    }
}
