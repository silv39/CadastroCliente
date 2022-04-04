package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produtos extends UnidMed {
    
    public String   codProduto;
    public String   descProduto;
    public String   codUnidMed;
    public double   vlrUnitario;

    @Override
    public String toString() {
        return "Produtos{" + "codProduto=" + codProduto + ", descProduto=" + descProduto + ", codUnidMed=" + codUnidMed + ", vlrUnitario=" + vlrUnitario + '}';
    }
       
    public boolean consultarProduto(){
 // declarando comando de execucao do banco de dados
            String sql = "select produto ";
                  sql += "(Produtos, descProduto, codUnidMed,vlrUnitario)";
                  sql += " where codProduto = ?";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
    
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString (1,this.codProduto);
                stm.setString (2, this.descProduto);
                stm.setString (3, this.codUnidMed);
                stm.setDouble (4, this.vlrUnitario);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
                return false;
            }            
            return true;   
	}
   
       public boolean incluirProduto () {
	 String sql = "insert into produto ";
                sql+= "codproduto, descProduto, codUnidMed, vlrUnitario ";
                sql+= "values(?,?,?,?)";
        Connection  con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString (1, this.codProduto);
            stm.setString (2, this.descProduto);
            stm.setString (3, this.codUnidMed);
            stm.setDouble (4, this.vlrUnitario);
            stm.execute();
            
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
          return false;
        }
        return true;	
    }
    
       public boolean alterarDescProduto () {
	// declarando comando de execucao do banco de dados
            String sql  = "UPDATE produtos ";
                   sql += "set descProduto = ?, ";
                   sql += "    codUnidMed    = ?, ";
                   sql += "    vlrUnitario = ?  ";
                   sql += "where produtos = ? ";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
            // 
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString (1, this.codProduto);
                stm.setString (2, this.descProduto);
                stm.setString (3, this.codUnidMed);
                stm.setDouble (4, this.vlrUnitario);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
                return false; 
            }
            return true;	
	}
       
        public boolean excluirProduto() {
	String sql  = "DELETE FROM codproduto ";
               sql += "WHERE produto = ? ";
        // conectando no banco de dados
        Connection con = Conexao.conectar();
        // 
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString (1, this.codProduto);
            stm.execute();   
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
            return false; 
        }
        return true;               
    }
        
    public List<Produtos> consultarlistaproduto(){
      List<Produtos> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String sql = "select * from Produtos order by descProduto";
        try {
            PreparedStatement stm = con.prepareStatement(sql);     
            java.sql.ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Produtos p = new Produtos();
               p.setDescProduto(rs.getString      ("descProduto"));
               p.setCodProduto (rs.getString      ("codProduto"));
               p.setCodUnidMed (rs.getString      ("codUnidMed"));
               p.setVlrUnitario((int) rs.getDouble("vlrUnitario"));     
               lista.add(p);
           } 
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
        }
        return lista;
    }
    
    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }
    
}
