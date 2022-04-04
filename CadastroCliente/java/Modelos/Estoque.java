package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Estoque extends Produtos {

    public String   codProduto;
    public double   qtdeestoque;
    public double   qtdeStqMinimo;

    @Override
    public String toString() {
        return "Estoque{" + "codProduto=" + codProduto + ", qtdeestoque=" + qtdeestoque + ", qtdeStqMinimo=" + qtdeStqMinimo + '}';
    }
  
    public boolean incluirCodProduto() {
        Connection con = Conexao.conectar();
        String  sql = "update Estoque     ";
                sql+= "codProduto    = ?, ";
                sql+= "qtdeestoque   = ?, ";
                sql+= "qtdeStqMinimo = ?, ";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString (1, this.codProduto);
            stm.setDouble (2, this.qtdeestoque);
            stm.setDouble (3, this.qtdeStqMinimo);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return true;
    }

    public List<Estoque> exibirEstoque() {
         List<Estoque> lista = new ArrayList<>();
            Connection con   = Conexao.conectar();
            String  sql      = "select codProduto, qtdeestoque, qtdeStqMinimo,";
                    sql     += "from Estoque ";
                    sql     += "order by codProduto";  

            ResultSet rs = null;
            try{
              PreparedStatement stm = con.prepareStatement(sql);
              rs = stm.executeQuery();
              while (rs.next()) {
                  Estoque est = new Estoque();
                  est.setCodProduto   (rs.getString("codProduto"));
                  est.setQtdeestoque  (rs.getDouble("qtdeestoque"));
                  est.setQtdeStqMinimo(rs.getDouble("qtdeStqMinimo"));
                  lista.add(est);
              }
              
            }catch (SQLException ex) {
            }
        return lista;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public double getQtdeestoque() {
        return qtdeestoque;
    }

    public void setQtdeestoque(double qtdeestoque) {
        this.qtdeestoque = qtdeestoque;
    }

    public double getQtdeStqMinimo() {
        return qtdeStqMinimo;
    }

    public void setQtdeStqMinimo(double qtdeStqMinimo) {
        this.qtdeStqMinimo = qtdeStqMinimo;
    }
}
