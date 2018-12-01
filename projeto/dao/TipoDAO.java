package projeto.dao;

import projeto.modelo.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDAO implements IDAO<TipoProduto> {

    private static final String ADD_TIPO = "insert into tb_tipo_produto(desc_tipo) values(?);";
    private static final String REMOVE_TIPO = "delete from tb_tipo_produto where cod_tipo= ?;";
    private static final String UPDATE_TIPO = "update tb_tipo_produto set desc_tipo=? , ind_situacao=? " +
            "where cod_tipo=?;";
    private static final String GETALL_TIPO = "select * from tb_tipo_produto;";
    private static final String GETBYID_TIPO = "select * from tb_tipo_produto where cod_tipo=?;";

    //////////////////////////////////////////////////////////////////////////////////////////////
    private static final String COLUNA_CODIGO = "cod_tipo";
    private static final String COLUNA_TIPO = "desc_tipo";
    private static final String COLUNA_SITUACAO = "ind_situacao";

    private Connection conexao;

    public TipoDAO(Connection connection) {
        this.conexao = connection;
    }

    @Override
    public void add(TipoProduto tipoProduto) {
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(ADD_TIPO);
            preparedStatement.setString(1,tipoProduto.getTipo().toUpperCase());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(TipoProduto tipoProduto) {
        remove(tipoProduto.getCodigo());
    }

    @Override
    public void remove(int id) {
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(REMOVE_TIPO);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(TipoProduto tipoProduto) {
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(UPDATE_TIPO);

            preparedStatement.setString(1,tipoProduto.getTipo().toUpperCase());
            preparedStatement.setString(2,tipoProduto.getSituacao().toUpperCase());
            preparedStatement.setInt(3,tipoProduto.getCodigo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoProduto> getAll() {
        List<TipoProduto> tipos = new ArrayList<>();
        try{
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(GETALL_TIPO);
            while (rs.next()){
                TipoProduto tipoAux = new TipoProduto();

                tipoAux.setCodigo(rs.getInt(COLUNA_CODIGO));
                tipoAux.setTipo(rs.getString(COLUNA_TIPO));
                tipoAux.setSituacao(rs.getString(COLUNA_SITUACAO));

                tipos.add(tipoAux);
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }

    @Override
    public TipoProduto getById(int id) {
        TipoProduto tipoBuscado =null;

        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(GETBYID_TIPO);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                tipoBuscado = new TipoProduto();
                tipoBuscado.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                tipoBuscado.setTipo(resultSet.getString(COLUNA_TIPO));
                tipoBuscado.setSituacao(resultSet.getString(COLUNA_SITUACAO));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoBuscado;

    }
}
