package com.location.inoarb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DB extends Default implements Runnable{

    private Connection conn;
    private String host = "serverinoarb.mysql.database.azure.com";
    private String db = "bd_inoarb_full";
    private int port = 3306;
    private String user = "wevertonfarias@servinoarb";
    private String pass = "Farias@3445";
    //private static final String url = "jdbc:mysql://<server>:<port>/<database>";
    private String url = "jdbc:mysql://%s:%d/%s";

    public DB(){
        super();
        this.url= String.format(this.url,this.host,this.port,this.db);

        this.conecta();
        this.disconecta();
        //Todo Abrir conexao
        //Todo Fechar conexao
    }

    //"jdbc:mysql:
    // servinoarb.mysql.database.azure.com:3306/{your_database}?useSSL=true&requireSSL=false";
    // myDbConn = DriverManager.getConnection(url, "wevertonfarias@servinoarb", {your_password});
    @Override
    public void run() {
        try{
            //Class.forName("jdbc:mysql://servinoarb.mysql.database.azure.com:3306");
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user,this.pass);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void conecta(){
        Thread thread = new Thread(this);
        thread.start();

        try{
            thread.join();
        }catch(Exception e){
            //  this.getMensagem();
        }
    }
    public void disconecta(){
        if(this.conn != null){
            try{
                this.conn.close();
            }catch (Exception e){

            }finally {
                this.conn = null;
            }
        }
    }

    public ResultSet execute(String query) throws Exception{

        if(this.conn == null || !this.conn.isValid(5)) {
            this.conecta();
        }

        ResultSet resultSet = null;

        resultSet  = new ExecuteDB(this.conn, query).execute().get();

        return resultSet;
    }


}
