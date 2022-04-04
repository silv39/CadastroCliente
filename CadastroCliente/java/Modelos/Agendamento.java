package Modelos;
 
import Utils.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agendamento extends Cliente {
    
    public int      nrAgendamento;
    public Date     datahoraAg;
    public String   statusAgenda;
    public int      codtipoCorte;
    public String   cpfCliente;

    @Override
    public String toString() {
        return "Agendamento{" + "nrAgendamento=" + nrAgendamento + ", datahoraAg=" + datahoraAg + ", statusAgenda=" + statusAgenda + ", codtipoCorte=" + codtipoCorte + ", cpfCliente=" + cpfCliente + '}';
    }
    
    public boolean incluirAgendamento() {
        String sql=  "insert into agendamento";
               sql+= "(nrAgendamento, datahoraAg, statusAgenda, codtipoCorte, cpfCliente) ";
               sql+= "values(?,?,?,?,?)";
        Connection  con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt     (1, this.nrAgendamento);
            stm.setDate    (2, this.datahoraAg);
            stm.setString  (3, this.statusAgenda);
            stm.setInt     (4, this.codtipoCorte);
            stm.setString  (5, this.cpfCliente);
            stm.execute();
            
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
          return false;
        }
        return true;	
    }

    public boolean alterarAgendamento() {
        // declarando comando de execucao do banco de dados
            String sql  = "UPDATE from agendamento ";
                   sql += "set nrAgendamento = ?, ";
                   sql += "datahoraAg, ststusAgenda, = ? ";
                   sql += "codTipoCorte, cpfCliente  = ? ";
                   sql += "where nrAgendamento       = ? ";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
            
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt     (1, this.nrAgendamento);
                stm.setDate    (2, this.datahoraAg);
                stm.setString  (3, this.statusAgenda);
                stm.setInt     (4, this.codtipoCorte);
                stm.setString  (5, this.cpfCliente);
                stm.execute(); 
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
              return false; 
            }
        return true;	
    }

    public boolean excluirAgendamento() {
        String sql  = "DELETE FROM agendamento ";
               sql += "WHERE nrAgendamento = ? ";
        // conectando no banco de dados
        Connection con = Conexao.conectar();
        
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt (1, this.nrAgendamento);
            stm.execute();   
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
            return false; 
        }
        return true;               
    }

    public Agendamento consultarAgendamento(int pnrAgendamento) {
        
        Agendamento ag = null;
        this.nrAgendamento = pnrAgendamento;
            String  sql  = "select nrAgendamento, cpfCliente, codTipoCorte ";
                    sql += "dataHoraAgenda, statusAgenda ";
                    sql += "from agendamento ";
                    sql += "where nrAgendamento = ?";
            Connection con = Conexao.conectar();
      
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, this.nrAgendamento);
                ResultSet rs = stm.executeQuery();
                
                if(rs.next()){
                   ag = new Agendamento();
                   ag.setNrAgendamento (rs.getInt("nrAgendamento"));
                   ag.setDatahoraAg    (rs.getDate  ("datahoraAg"));
                   ag.setStatusAgenda  (rs.getString("statusAgenda"));
                   ag.setCodtipoCorte  (rs.getInt   ("codtipoCorte"));
                   ag.setCpfCliente    (rs.getString("cpfCliente"));  
                } 
                
                } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        return ag;
    }
    
    public List<Agendamento> lovAgendamento() {
        List<Agendamento> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select NrAgendamento, CpfCliente ";
                sql += "from Agendamento ";
                sql += "order by nrAgendamento";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                Agendamento Agendamento = new Agendamento();
                Agendamento.setCpfCliente(rs.getString("cpfCliente"));
                Agendamento.setNrAgendamento(rs.getInt("nrAgendamento"));
                lista.add(Agendamento);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
     }
    
    public List<Agendamento> ConsultarListaAgendamento() {
        List<Agendamento> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select * from Agendamento order by nrAgendamento ";
              
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                Agendamento ag = new Agendamento();
                ag.setNrAgendamento(rs.getInt("nrAgendamento"));
                ag.setDatahoraAg   (rs.getDate("datahoraAg"));
                ag.setStatusAgenda (rs.getString("statusAgenda"));
                ag.setCodtipoCorte (rs.getInt("codtipoCorte"));
                ag.setCpfCliente   (rs.getString("cpfCliente"));
                lista.add(ag);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    //INCLUINDO GETTERS E SETTERS
    public int getNrAgendamento() {
        return nrAgendamento;
    }

    public void setNrAgendamento(int nrAgendamento) {
        this.nrAgendamento = nrAgendamento;
    }

    public Date getDatahoraAg() {
        return datahoraAg;
    }

    public void setDatahoraAg(Date datahoraAg) {
        this.datahoraAg = datahoraAg;
    }

    public String getStatusAgenda() {
        return statusAgenda;
    }

    public void setStatusAgenda(String statusAgenda) {
        this.statusAgenda = statusAgenda;
    }

    public int getCodtipoCorte() {
        return codtipoCorte;
    }

    public void setCodtipoCorte(int codtipoCorte) {
        this.codtipoCorte = codtipoCorte;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}

