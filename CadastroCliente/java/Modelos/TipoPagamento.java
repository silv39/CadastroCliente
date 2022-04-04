package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamento {

    public String codTipoPagto;
    public String descTipoPagto;

    @Override
    public String toString() {
        return "TipoPagamento{" + "codTipoPagto=" + codTipoPagto + ", descTipoPagto=" + descTipoPagto + '}';
    }

    public boolean incluirTipoPagto() {
        // declarando comando de execucao do banco de dados
            String sql  = "INSERT INTO TipoPagamento ";
                   sql += "(codTipoPagto,descTipoPagto) ";
                   sql += "VALUES(?,?)";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
            // 
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString   (1, this.codTipoPagto);
                stm.setString (2, this.descTipoPagto);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
                return false;
            }            
            return true;
    }

    public boolean alterarTipoPagto() {
        Connection con = Conexao.conectar();
        String  sql  = "update TipoPagamento set ";
                sql += "codTipoPagto    = ?, ";
                sql += "descTipoPagto   = ?, ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString (1, this.codTipoPagto);
            stm.setString (2, this.descTipoPagto);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return true;
    }
    
    public List<TipoPagamento> lovTipoPagamento() {
        List<TipoPagamento> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select codTipoPagto, DescTipoPagamento ";
                sql += "from TipoPagamento ";
                sql += "order by codTipoPagto";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                TipoPagamento TipoPagamento = new TipoPagamento();
                TipoPagamento.setCodTipoPagto (rs.getString("codTipoPagto"));
                TipoPagamento.setDescTipoPagto(rs.getString("TipoPagamento"));
                lista.add(TipoPagamento);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<TipoPagamento> consultarListaGeralTipoPagamento(){
        List<TipoPagamento> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String sql = "select * from tipoPagamento order by codTipoPagto";
        try {
            PreparedStatement stm = con.prepareStatement(sql);     
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               TipoPagamento tip = new TipoPagamento();
               tip.setCodTipoPagto      (rs.getString("codTipoPagto"));
               tip.setDescTipoPagto     (rs.getString("descTipoPagto"));
               lista.add(tip);
           } 
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
        }
        return lista;
    }
    
    public String getCodTipoPagto() {
        return codTipoPagto;
    }

    public void setCodTipoPagto(String codTipoPagto) {
        this.codTipoPagto = codTipoPagto;
    }

    public String getDescTipoPagto() {
        return descTipoPagto;
    }

    public void setDescTipoPagto(String descTipoPagto) {
        this.descTipoPagto = descTipoPagto;
    }
    
}
