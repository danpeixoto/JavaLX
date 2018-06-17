package projeto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import projeto.dao.CidadeDAO;
import projeto.dao.EstadoDAO;
import projeto.modelo.Cidade;
import projeto.modelo.Estado;
import projeto.servicos.DbConnection;

public class ControladorJAdminCidade  {
    @FXML
    private TextField cidadeField;

    @FXML
    private TextField cepField;

    private Cidade cidadeAux;

    public void addCidade(Estado estado){
        CidadeDAO cidadeDAO = new CidadeDAO(DbConnection.getConexao());
        Cidade cidadeNova = new Cidade(cidadeField.getText() , cepField.getText() , estado);


        cidadeDAO.add(cidadeNova);

    }

    public void updateCidade(){
        cidadeAux.setCep(cepField.getText());
        cidadeAux.setNome(cidadeField.getText());
        CidadeDAO cidadeDAO = new CidadeDAO(DbConnection.getConexao());

        cidadeDAO.update(cidadeAux);

    }

    public void inserirInformacao(Cidade c){
        cidadeField.setText(c.getNome());
        cepField.setText(c.getCep());
        cidadeAux = c;
    }


}
