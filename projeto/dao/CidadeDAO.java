package projeto.dao;

import projeto.modelo.Cidade;
import projeto.modelo.Estado;
import projeto.servicos.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO implements IDAO<Cidade> {


    private static final String ADD_CIDADE = "insert into tb_cidade(cod_estado , nome_cidade , cep_cidade) " +
            "values((select cod_estado from tb_estado where nome_estado=?),?,?);";
    private static final String REMOVE_CIDADE = "delete from tb_cidade where cod_cidade= ?;";
    private static final String UPDATE_CIDADE = "update tb_cidade set nome_cidade=? , cep_cidade=? , ind_situacao=? " +
            "where cod_cidade=?;";
    private static final String GETALL_CIDADE_BY_ESTADO = "select * from tb_cidade where cod_estado = ?;";
    private static final String GETBYID_TIPO = "select * from tb_cidade where cod_cidade=?;";


    //////////////////////////////////////////////////////////////////////////////////////////////
    private static final String COLUNA_CODIGO = "cod_cidade";
    private static final String COLUNA_NOME = "nome_cidade";
    private static final String COLUNA_CEP = "cep_cidade";
    private static final String COLUNA_SITUACAO = "ind_situacao";
    private static final String COLUNA_CODIGO_ESTADO = "cod_estado";

    private Connection conexao;

    public CidadeDAO(Connection connection) {
        this.conexao = connection;
    }

    @Override
    public void add(Cidade cidade) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(ADD_CIDADE);

            preparedStatement.setString(1, cidade.getEstado().getNome().toUpperCase());
            preparedStatement.setString(2, cidade.getNome().toUpperCase());
            preparedStatement.setString(3, cidade.getCep());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Cidade cidade) {
        remove(cidade.getCodigo());
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(REMOVE_CIDADE);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cidade cidade) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(UPDATE_CIDADE);

            preparedStatement.setString(1, cidade.getNome().toUpperCase());
            preparedStatement.setString(2, cidade.getCep());
            preparedStatement.setString(3, cidade.getSituacao().toUpperCase());
            preparedStatement.setInt(4,cidade.getCodigo());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cidade> getAll() {
        List<Cidade> cidades = new ArrayList<>();

        try {

            EstadoDAO estadoDAO = new EstadoDAO(conexao);
            List<Estado> estados = estadoDAO.getAll();
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_CIDADE_BY_ESTADO);

            for (Estado e : estados) {
                preparedStatement.setInt(1, e.getCodigo());
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Cidade cidadeAux = new Cidade();

                    cidadeAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                    cidadeAux.setNome(resultSet.getString(COLUNA_NOME));
                    cidadeAux.setCep(resultSet.getString(COLUNA_CEP));
                    cidadeAux.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                    cidadeAux.setEstado(e);

                    cidades.add(cidadeAux);
                }

                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidades;
    }

    public List<Cidade> getByEstado(Estado estado) {
        List<Cidade> cidades = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_CIDADE_BY_ESTADO);
            preparedStatement.setInt(1, estado.getCodigo());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cidade cidadeAux = new Cidade();

                cidadeAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                cidadeAux.setNome(resultSet.getString(COLUNA_NOME));
                cidadeAux.setCep(resultSet.getString(COLUNA_CEP));
                cidadeAux.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                cidadeAux.setEstado(estado);

                cidades.add(cidadeAux);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidades;
    }

    @Override
    public Cidade getById(int id) {
        Cidade cidadeBuscada = null;

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(GETBYID_TIPO);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                EstadoDAO estadoDAO = new EstadoDAO(conexao);
                Estado estadoAux = estadoDAO.getById(resultSet.getInt(COLUNA_CODIGO_ESTADO));

                cidadeBuscada = new Cidade();
                cidadeBuscada.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                cidadeBuscada.setNome(resultSet.getString(COLUNA_NOME));
                cidadeBuscada.setCep(resultSet.getString(COLUNA_CEP));
                cidadeBuscada.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                cidadeBuscada.setEstado(estadoAux);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidadeBuscada;
    }
}
