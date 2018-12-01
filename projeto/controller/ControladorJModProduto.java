package projeto.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projeto.dao.ProdutoDAO;
import projeto.modelo.Produto;
import projeto.servicos.DbConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorJModProduto implements Initializable {
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

    @FXML
    private Spinner<Integer> qntSpinner;

    private Produto produtoModificado;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        qntSpinner.setValueFactory(spinnerValueFactory);
        qntSpinner.setEditable(true);
    }



    @FXML
    void modificarProduto() {

        if ((nomeProduto != null && !nomeProduto.getText().isEmpty()) && (precoProduto != null && !precoProduto.getText().isEmpty())
                && (descProduto != null && !descProduto.getText().isEmpty())) {
            produtoModificado.setNome(nomeProduto.getText().toUpperCase());
            produtoModificado.setDescricao(descProduto.getText().toUpperCase());
            produtoModificado.setPreco(Double.parseDouble(precoProduto.getText()));
            produtoModificado.setQuantidade(qntSpinner.getValue());


            ProdutoDAO produtoDAO = new ProdutoDAO(DbConnection.getConexao());
            produtoDAO.update(produtoModificado);
            Stage stage = (Stage) modificarBnt.getScene().getWindow();
            stage.close();
        }else{
            erroLabel.setVisible(false);
        }

        nomeProduto.setText("");
        descProduto.setText("");
        precoProduto.setText("");

    }



    void adicionarInformacoes(Produto p){
        nomeProduto.setText(p.getNome());
        descProduto.setText(p.getDescricao());
        precoProduto.setText(String.valueOf(p.getPreco()));
        produtoModificado = p;
    }
}
