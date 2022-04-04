package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoCorte {

    public int      codtipoCorte;
    public String   desctipoCorte;

    @Override
    public String toString() {
        return "TipoCorte{" + "codtipoCorte=" + codtipoCorte + ", desctipoCorte=" + desctipoCorte + '}';
    }
           
    
    public boolean alterarCorte() {
        Connection con = Conexao.conectar();
        String  sql = "update TipoCorte set ";
                sql+= "codtipoCorte              = ?, ";
                sql+= "desctipoCorte             = ?, ";
               
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, this.codtipoCorte);
            stm.setString(2, this.desctipoCorte);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return true;
       
    }

    public boolean selecionarCorte() {
        String sql="insert into TipoCorte (codtipoCorte, desctipoCorte, ";
               sql+= "codtipoCorte, desctipoCorte,) ";
               sql+="values(?,?,?,?,?)";
        Connection  con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, this.codtipoCorte);
            stm.setString(2, this.desctipoCorte);
            stm.execute();
            
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
          return false;
        }
        return true;
    }
    
    public List<TipoCorte> lovTipoCorte() {
        List<TipoCorte> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select desctipoCorte, codtipoCorte ";
                sql += "from TipoCorte ";
                sql += "order by codtipoCorte";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                TipoCorte TipoCorte = new TipoCorte();
                TipoCorte.setCodtipoCorte(rs.getInt("codtipoCorte"));
                TipoCorte.setDesctipoCorte(rs.getString("desctipoCorte"));
                lista.add(TipoCorte);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<TipoCorte> consultarListaTipoCorte() {       
            List<TipoCorte> lista = new ArrayList<>();
            Connection con = Conexao.conectar();
            String  sql  = "select desctipoCorte, codtipoCorte,";
                            sql += "from TipoCorte ";
                            sql += "order by codtipoCorte";

            ResultSet rs = null;
            try{
              PreparedStatement stm = con.prepareStatement(sql);
              rs = stm.executeQuery();
              while (rs.next()) {
                  TipoCorte loc = new TipoCorte();
                  loc.setCodtipoCorte(rs.getInt("codtipoCorte"));
                  loc.setDesctipoCorte(rs.getString("desctipoCorte"));
                  lista.add(loc);
              }
              
            }catch (SQLException ex) {
            }
        return lista;    
        
    }
    
    //INCLUINDO GETTERS E SETTERS
    public int getCodtipoCorte() {
        return codtipoCorte;
    }

    public void setCodtipoCorte(int codtipoCorte) {
        this.codtipoCorte = codtipoCorte;
    }

    public String getDesctipoCorte() {
        return desctipoCorte;
    }

    public void setDesctipoCorte(String desctipoCorte) {
        this.desctipoCorte = desctipoCorte;
    }
}
