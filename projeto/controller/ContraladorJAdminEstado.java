package projeto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.dao.EstadoDAO;
import projeto.modelo.Estado;
import projeto.servicos.DbConnection;

public class ContraladorJAdminEstado {

    @FXML
    private TextField estado;

    @FXML
    private TextField uf;

    private Estado estadoAux;

    public void addEstado(){
        EstadoDAO estadoDAO = new EstadoDAO(DbConnection.getConexao());
        Estado estadoNovo = new Estado(estado.getText() , uf.getText());


        estadoDAO.add(estadoNovo);

    }

    public void updateEstado(){
        estadoAux.setUf(uf.getText());
        estadoAux.setNome(estado.getText());
        EstadoDAO estadoDAO = new EstadoDAO(DbConnection.getConexao());
        estadoDAO.update(estadoAux);

    }

    public void inserirInformacao(Estado e){
        estado.setText(e.getNome());
        uf.setText(e.getUf());
        estadoAux = e;
    }

}
