package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Caixa extends Agendamento {

    public String   nrPedido;
    public int      nrAgendamento;
    public Date     dataLancamento;
    public int      codTipoPagto;
    public int      codProduto;
    public int      codUnidMed;
    public int      nrLancamento;
    public double   qtdProduto;
    public double   vlrTotalProduto;
    public double   vlrDesconto;
    public double   vlrTotalPedido;

    @Override
    public String toString() {
        return "Caixa{" + "nrPedido=" + nrPedido + ", nrAgendamento=" + nrAgendamento + ", dataLancamento=" + dataLancamento + ", codTipoPagto=" + codTipoPagto + ", codProduto=" + codProduto + ", codUnidMed=" + codUnidMed + ", nrLancamento=" + nrLancamento + ", qtdProduto=" + qtdProduto + ", vlrTotalProduto=" + vlrTotalProduto + ", vlrDesconto=" + vlrDesconto + ", vlrTotalPedido=" + vlrTotalPedido + '}';
    }
    
    public boolean incluirTipoPagto() {
        // declarando comando de execucao do banco de dados
            String sql  = "INSERT INTO Caixa ";
                   sql += "(nrPedido,nrAgendamento,dataLancamento,codTipoPagto,codProduto,codUnidMed,"
                       +  "nrLancamento,qtdProduto,vlrTotalProduto,vlrDesconto,vlrTotalPedido) ";
                   sql += "VALUES(?,?)";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
            // 
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1,  this.nrPedido);
                stm.setInt   (2,  this.nrAgendamento);
                stm.setDate  (3,  this.dataLancamento);
                stm.setInt   (4,  this.codTipoPagto);
                stm.setInt   (5,  this.codProduto);
                stm.setInt   (6,  this.codUnidMed);
                stm.setInt   (7,  this.nrLancamento);
                stm.setDouble(8,  this.qtdProduto);
                stm.setDouble(9,  this.vlrTotalProduto);
                stm.setDouble(10, this.vlrDesconto);
                stm.setDouble(11, this.vlrTotalPedido);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
                return false;
            }            
            return true;
    }

    public boolean alterarTipoPagto() {
        Connection con = Conexao.conectar();
        String  sql = "update from Caixa ";
                sql+= "descTipoPagto ";
                sql+= "where nrPedido,nrLancamento = ?,? ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.nrPedido);
            stm.setInt   (2, this.nrLancamento);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return true;
    }

    public boolean IncluirDesconto() throws SQLException {
        String sql = "insert into caixa";
               sql+= " vlrDesconto";
        Connection  con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setDouble   (1, this.vlrDesconto);
            stm.execute();
             } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
          return false;
        }
        return true;
    }

    public boolean incluirProduto() {
        String sql = "insert into caixa ";
               sql+= "codproduto, codUniMed, qtdProduto ";
               sql+= "values(?,?,?,?,?)";
        Connection  con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt    (1, this.codProduto);
            stm.setInt    (2, this.codUnidMed);
            stm.setDouble (3, this.qtdProduto);
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
            stm.setInt    (1, this.codProduto);
            stm.execute();   
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
            return false; 
        }
        return true;
    }
    
    public List<Caixa> lovCaixa() {
        List<Caixa> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select NrAgendamento, CpfCliente ";
                sql += "from Caixa ";
                sql += "order by nrPedido, dataLancamento, codProduto, nrLancamento ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                Caixa Caixa = new Caixa();
                Caixa.setCodTipoPagto  (rs.getInt   ("codTipoPagto"));
                Caixa.setCodUnidMed    (rs.getInt   ("codUnidMed"));
                Caixa.setDataLancamento(rs.getDate  ("dataLancamento"));
                Caixa.setNrAgendamento (rs.getInt   ("nrAgendamento"));
                Caixa.setNrLancamento  (rs.getInt   ("nrLancamento"));
                Caixa.setNrPedido      (rs.getString("nrPedido"));
                Caixa.setQtdProduto    (rs.getInt   ("qtdProduto"));
                Caixa.setVlrDesconto   (rs.getInt   ("vlrDesconto"));
                lista.add(Caixa);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Caixa> consultarListaGeralCaixa(){
        List<Caixa> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String sql = "select * from caixa order by codTipoPagto";
        try {
            PreparedStatement stm = con.prepareStatement(sql);     
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Caixa  cx = new Caixa();
               cx.setNrPedido        (rs.getString("nrPedido"));
               cx.setNrAgendamento   (rs.getInt   ("nrAgendamento"));
               cx.setDataLancamento  (rs.getDate  ("dataLancamento"));
               cx.setCodTipoPagto    (rs.getInt   ("codTipoPagto"));
               cx.setCodProduto      (rs.getInt   ("codProduto"));
               cx.setCodUnidMed      (rs.getInt   ("codUnidMed"));
               cx.setNrLancamento    (rs.getInt   ("nrLancamento"));
               cx.setQtdProduto      (rs.getDouble("qtdProduto"));
               cx.setVlrTotalProduto (rs.getDouble("vlrTotalProduto"));
               cx.setVlrDesconto     (rs.getDouble("vlrDesconto"));
               cx.setVlrTotalPedido  (rs.getDouble("vlrTotalPedido"));            
               lista.add(cx);
           } 
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
        }
        return lista;
    }
    
    public String getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(String nrPedido) {
        this.nrPedido = nrPedido;
    }

    public int getNrAgendamento() {
        return nrAgendamento;
    }

    public void setNrAgendamento(int nrAgendamento) {
        this.nrAgendamento = nrAgendamento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getCodTipoPagto() {
        return codTipoPagto;
    }

    public void setCodTipoPagto(int codTipoPagto) {
        this.codTipoPagto = codTipoPagto;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getCodUnidMed() {
        return codUnidMed;
    }

    public void setCodUnidMed(int codUnidMed) {
        this.codUnidMed = codUnidMed;
    }

    public int getNrLancamento() {
        return nrLancamento;
    }

    public void setNrLancamento(int nrLancamento) {
        this.nrLancamento = nrLancamento;
    }

    public double getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(double qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public double getVlrTotalProduto() {
        return vlrTotalProduto;
    }

    public void setVlrTotalProduto(double vlrTotalProduto) {
        this.vlrTotalProduto = vlrTotalProduto;
    }

    public double getVlrDesconto() {
        return vlrDesconto;
    }

    public void setVlrDesconto(double vlrDesconto) {
        this.vlrDesconto = vlrDesconto;
    }

    public double getVlrTotalPedido() {
        return vlrTotalPedido;
    }

    public void setVlrTotalPedido(double vlrTotalPedido) {
        this.vlrTotalPedido = vlrTotalPedido;
    }
}
