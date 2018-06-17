package projeto.dao;

import projeto.modelo.Estado;
import projeto.servicos.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements IDAO<Estado> {

    private Connection conexao;

    private static final String ADD_ESTADO = "insert into tb_estado(nome_estado,uf_estado) values(?,?);";
    private static final String REMOVE_ESTADO = "delete from tb_estado where cod_estado= ?;";
    private static final String UPDATE_ESTADO = "update tb_estado set nome_estado=? , uf_estado=? , ind_situacao=? " +
            "where cod_estado=?;";
    private static final String GETALL_ESTADO = "select * from tb_estado;";
    private static final String GETBYID_ESTADO = "select * from tb_estado where cod_estado=?;";

    //////////////////////////////////////////////////////////////////////////////////////////////
    private static final String COLUNA_CODIGO = "cod_estado";
    private static final String COLUNA_NOME = "nome_estado";
    private static final String COLUNA_UF = "uf_estado";
    private static final String COLUNA_SITUACAO = "ind_situacao";


    public EstadoDAO(Connection connection) {
        this.conexao = connection;
    }

    @Override
    public void add(Estado estado) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(ADD_ESTADO);
            preparedStatement.setString(1,estado.getNome().toUpperCase());
            preparedStatement.setString(2,estado.getUf().toUpperCase());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Estado estado) {
        //esse método , para evitar codigo repitido , ira chamar o remove(int id)
        remove(estado.getCodigo());
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(REMOVE_ESTADO);
            preparedStatement.setInt(1,id);//transforma o valor do id para uma string
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e ) {
            e.printStackTrace();
            //SQLIntegrityConstraintViolationException já é pega pelo SQLException
        }
    }

    @Override
    public void update(Estado estado) {
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(UPDATE_ESTADO);

            preparedStatement.setString(1, estado.getNome().toUpperCase());
            preparedStatement.setString(2, estado.getUf().toUpperCase());
            preparedStatement.setString(3, estado.getSituacao().toUpperCase());
            preparedStatement.setInt(4, estado.getCodigo());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Estado> getAll() {
        List<Estado> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(GETALL_ESTADO);
            while (resultSet.next()){
                Estado estadoAux = new Estado();

                estadoAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                estadoAux.setNome(resultSet.getString(COLUNA_NOME));
                estadoAux.setUf(resultSet.getString(COLUNA_UF));
                estadoAux.setSituacao(resultSet.getString(COLUNA_SITUACAO));

                lista.add(estadoAux);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Estado getById(int id) {
        Estado estadoAux = null;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(GETBYID_ESTADO);
            preparedStatement.setString(1,String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                estadoAux = new Estado();
                estadoAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                estadoAux.setNome(resultSet.getString(COLUNA_NOME));
                estadoAux.setUf(resultSet.getString(COLUNA_UF));
                estadoAux.setSituacao(resultSet.getString(COLUNA_SITUACAO));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estadoAux;
    }
}
