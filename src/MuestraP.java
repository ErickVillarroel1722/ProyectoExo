import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MuestraP {
    private JPanel Panel1;
    private JTextField textCod;
    private JTextField textci;
    private JTextField textnom;
    private JTextField textFec;
    private JComboBox comboZod;
    private JButton buscarPorCODButton;
    private JButton buscarPorNombreButton;
    private JButton buscarPorSignoButton;
    private JButton mostrarRegistroButton;
    private JButton limpiarButton;
    private JButton borrarRegistroButton;

    static final String DB_URL = "jdbc:mysql://localhost:3306;databaseName=registro";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY_SELECT_CODIGO = "SELECT * FROM personas WHERE id = ?";
    static final String QUERY_SELECT_SIGNO = "SELECT * FROM personas WHERE signo = ?";
    static final String QUERY_SELECT_NOMBRE = "SELECT * FROM personas WHERE nombre = ?";
    static final String QUERY_DELETE = "DELETE FROM personas WHERE id = ?";


    public static void main(String[] args) {
        JFrame frame = new JFrame("MuestraP");
        frame.setContentPane(new MuestraP().Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    /*Botones*/
    public MuestraP() {
        buscarPorCODButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = textCod.getText();
                Funcion_Mostrar_Codigo(QUERY_SELECT_CODIGO.replace("?", codigo));
            }
        });

        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textnom.getText();
                Funcion_Mostrar_Nombre(QUERY_SELECT_NOMBRE.replace("?", nombre));
            }
        });

        buscarPorSignoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String signo = comboZod.getSelectedItem().toString();
                Funcion_Mostrar_Signo(QUERY_SELECT_SIGNO.replace("?", signo));
            }
        });

        borrarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBorrar = textCod.getText();
                Funcion_Borrar(QUERY_DELETE, idBorrar);
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textCod.setText("");
                textci.setText("");
                textnom.setText("");
                textFec.setText("");
            }
        });
    }

    /*FUNCIONES*/
    private void Funcion_Mostrar_Codigo(String query) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Texto_Mostrar(rs);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    private void Funcion_Mostrar_Nombre(String query) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Texto_Mostrar(rs);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    private void Funcion_Mostrar_Signo(String query) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Texto_Mostrar(rs);
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    private void Texto_Mostrar(ResultSet rs)throws SQLException {
        System.out.println("\n*******************");
        System.out.println("Código:"+rs.getInt("id"));
        System.out.println("Cédula:"+rs.getInt("cedula"));
        System.out.println("Nombre:"+rs.getString("nombre"));
        System.out.println("Fecha Nacimiento:"+rs.getString("fecha"));
        System.out.println("Signo:"+rs.getString("signo"));
        System.out.println("********************");
    }

    private void Funcion_Borrar(String query, String idBorrar) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, idBorrar);
            stmt.executeUpdate();
            // Realiza cualquier acción adicional después de borrar el registro
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }
}










