package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidMed {
    // Variaveis 
    public String   codUnidMed;
    public String   descUniMed;

    @Override
    public String toString() {
        return "UnidMed{" + "codUnidMed=" + codUnidMed + '}';
    }

     public boolean incluirUnidMed() {
        String sql="insert into UnidMed (codUnidMed, descUniMed)";
               sql+="values(?,?)";
        Connection  con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.codUnidMed);
            stm.setString(2, this.descUniMed);
            stm.execute();
            
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
          return false;
        }
        return true;	
	}
       public boolean incluirDescUnidMed () {
        // declarando comando de execucao do banco de dados
        String sql = "INSERT INTO UnidMed ";
               sql +="(codUnidMed, descUnidMed)";
               sql +="VALUES(?,?)";
        // conectando no banco de dados
        Connection con = Conexao.conectar();
        // 
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.codUnidMed);
            stm.setString(2, this.descUniMed);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
            return false;
        }
        return true;
    }
         private boolean alterarUnidMed () {
	// declarando comando de execucao do banco de dados
            String sql  = "UPDATE UnidMed ";
                   sql += "set descUniMed = ?, ";
                   sql += "where UnidMed  = ? ";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
            // 
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, this.codUnidMed);
                stm.setString(2, this.descUniMed);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
                return false; 
            }
            return true;	
	}
    public boolean exluircodUnidMed() {
	String sql  = "DELETE FROM UnidMed ";
               sql += "WHERE codUnidMed = ? ";
        // conectando no banco de dados
        Connection con = Conexao.conectar();
        // 
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.codUnidMed);
            stm.execute();   
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
            return false; 
        }
        return true;               
    }    
    
    public UnidMed consultarUnidMed(String pcodUnidMed) {
        UnidMed unid = null;
        this.codUnidMed = pcodUnidMed;
            String  sql  = "codUnidMed";
                    sql += "descUnidMed";
                    sql += "from UnidMed ";
                    sql += "where codUnidMed = ?";
            Connection con = Conexao.conectar();
      
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, this.codUnidMed);
                ResultSet rs = stm.executeQuery();
                
                if(rs.next()){
                   unid = new UnidMed();
                   unid.setCodUnidMed (rs.getString("codUnidMed"));
                   unid.setDescUniMed (rs.getString("descUniMed"));
                  } 
                
                } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            return unid;
    }
    
    public List<UnidMed> lovUnidMed() {
        List<UnidMed> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select codUnidMed, descUniMed ";
                sql += "from UnidMed ";
                sql += "order by codUnidMed";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                UnidMed UnidMed = new UnidMed();
                UnidMed.setCodUnidMed(rs.getString("codUnidMed"));
                UnidMed.setDescUniMed(rs.getString("descUniMed"));
                lista.add(UnidMed);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
     public List<UnidMed> consultarlistaUnidMed(){
        List<UnidMed> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String sql = "select * from UnidMed order by codUnidMed";
        try {
            PreparedStatement stm = con.prepareStatement(sql);     
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               UnidMed u = new UnidMed();
               u.setCodUnidMed(rs.getString("codUnidMed"));
               u.setCodUnidMed(rs.getString("descUniMed"));  
               lista.add(u);
           } 
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
        }
        return lista;
    }

    public String getCodUnidMed() {
        return codUnidMed;
    }

    public void setCodUnidMed(String codUnidMed) {
        this.codUnidMed = codUnidMed;
    }

    public String getDescUniMed() {
        return descUniMed;
    }

    public void setDescUniMed(String descUniMed) {
        this.descUniMed = descUniMed;
    }
}
