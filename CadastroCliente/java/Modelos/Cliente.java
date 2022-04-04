package Modelos;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    
    public String   cpf;
    public String   nome;
    public String   telefone;
    public String   email;

    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + '}';
    }

     
    public boolean incluirCliente() {
        // declarando comando de execucao do banco de dados
            String sql  = "INSERT INTO cliente ";
                   sql += "(cpf, nome, telefone, email) ";
                   sql += "VALUES(?,?,?,?)";
            // conectando no banco de dados
            Connection con = Conexao.conectar();

            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, this.cpf);
                stm.setString(2, this.nome);
                stm.setString(3, this.telefone);
                stm.setString(4, this.email);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage() + sql);
                return false;
            }            
            return true;  
            
            //TESTANDO INSERÇÃO
    }

    public boolean alterarCliente() {
        // declarando comando de execucao do banco de dados
            String sql  =   "UPDATE cliente ";
                   sql +=   "set nome  = ?, ";
                   sql +=   "telefone  = ?, ";
                   sql +=   "email     = ? ";
                   sql +=   "where cpf = ?  ";
            // conectando no banco de dados
            Connection con = Conexao.conectar();
            // 
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                
                stm.setString(1, this.nome);
                stm.setString(2, this.telefone);
                stm.setString(3, this.email);
                stm.setString(4, this.cpf);
                stm.execute();   
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
                return false; 
            }
            return true;	
	}

    public boolean excluirCliente() {
        String sql  = "DELETE cliente ";
               sql += "WHERE cpf = ? ";
        // conectando no banco de dados
        Connection con = Conexao.conectar();
        // 
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.cpf);
            stm.setString(2, this.nome);
            stm.setString(3, this.telefone);
            stm.setString(4, this.email);
            stm.execute();   
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
            return false; 
        }
        return true;               
    }
    
    public Cliente consultarCliente(String pcpf) {
        Cliente cli = null;
        this.cpf = pcpf;
            String  sql  = "select cpf, nome, telefone, email ";
                    sql += "from Cliente ";
                    sql += "where cpf = ?";
            Connection con = Conexao.conectar();
            try {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, this.cpf);
                ResultSet rs = stm.executeQuery();
                
                if(rs.next()){
                   cli = new Cliente();
                   cli.setCpf      (rs.getString("cpf"));
                   cli.setNome     (rs.getString("nome"));
                   cli.setTelefone (rs.getString("telefone"));
                   cli.setEmail    (rs.getString("email"));
                } 
                
                } catch (SQLException ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            return cli;
    }
    
    public List<Cliente> lovCliente() {
        List<Cliente> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String  sql  = "select cpf, nome, telefone, email ";
                sql += "from Cliente ";
                sql += "order by cpf";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                Cliente Cliente = new Cliente();
                Cliente.setCpf      (rs.getString("cpf"));
                Cliente.setNome     (rs.getString("nome"));
                Cliente.setTelefone (rs.getString("telefone"));
                Cliente.setEmail    (rs.getString("email"));
                lista.add(Cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Cliente> consultarListaGeralCliente(){
        List<Cliente> lista = new ArrayList<>();
        Connection con = Conexao.conectar();
        String sql = "select * from cliente order by cpf";
        try {
            PreparedStatement stm = con.prepareStatement(sql);     
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Cliente  cli = new Cliente();
               cli.setCpf      (rs.getString("cpf"));
               cli.setNome     (rs.getString("nome"));
               cli.setTelefone (rs.getString("telefone"));
               cli.setEmail    (rs.getString("email"));
               lista.add(cli);
           } 
        } catch (SQLException ex) {
          System.out.println("Erro:" + ex.getMessage());
        }
        return lista;
    }
    
    //INCLUINDO GETTERS E SETTERS
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
