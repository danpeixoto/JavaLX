package projeto.servicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String USUARIO = "student";
    private static final String SENHA = "student";
    private static final String NOME_DB = "javalx";
    private static final String CONN = "jdbc:mysql://127.0.0.1:3306/"+NOME_DB+"?autoReconnect=false&useSSL=false" +
            "&useTimezone=true&serverTimezone=UTC";

    private static Connection conexao = null;



    public static Connection getConexao(){

        if(conexao != null){
            return conexao;
        }

        try{
            conexao = DriverManager.getConnection(CONN , USUARIO , SENHA);
        }catch (SQLException e ){
            e.printStackTrace();
        }

        return conexao;
    }



}
