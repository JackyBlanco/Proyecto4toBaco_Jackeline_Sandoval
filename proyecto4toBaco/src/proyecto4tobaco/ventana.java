package proyecto4tobaco;

import static java.awt.Color.GRAY;
import static java.awt.Color.GREEN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.cyan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNuevoUsuario, panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    usuario misUsuarios[] = new usuario[10];
    cliente misClientes[] = new cliente[100];
    int contadorClientes = 6;

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
       misUsuarios[0] = new usuario("Admin", "123");
       
        misClientes[0] = new cliente("741", "Andy", 24, 'M');
        misClientes[1] = new cliente("762", "Misael", 39, 'M');
        misClientes[2] = new cliente("743", "Jose", 30, 'M');
        misClientes[3] = new cliente("714", "Daniel", 24, 'M');
        misClientes[4] = new cliente("745", "Derick", 22, 'M');
        misClientes[5] = new cliente("746", "Mayelen", 27, 'F');
        misClientes[6] = new cliente("134", "Alasha", 27, 'F');
        misClientes[7] = new cliente("689", "Carol", 27, 'F');
        misClientes[8] = new cliente("783", "Brandon", 27, 'M');
        misClientes[9] = new cliente("723", "Lucia", 27, 'F');
        
    }

    public void componentesInicioSesion() {
//Panel
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        this.setTitle("Inicio Sesion");
        panelInicioSesion.setBackground(GREEN);
        
//ETIQUETAS
        JLabel lblUsuario = new JLabel("Ingrese su usuario");
        lblUsuario.setBounds(50, 50, 150, 20);
        panelInicioSesion.add(lblUsuario);
        JLabel lblContra = new JLabel("Ingrese su Contraseña");
        lblContra.setBounds(50, 80, 150, 20);
        panelInicioSesion.add(lblContra);
//CUADROS DE TEXTO
        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 50, 150, 20);
        panelInicioSesion.add(txtUsuario);
        txtContra = new JPasswordField();
        txtContra.setBounds(180, 80, 150, 20);
        panelInicioSesion.add(txtContra);
        JButton btnIniciar = new JButton("ingresar");
        btnIniciar.setBounds(50, 130, 150, 20);
        panelInicioSesion.add(btnIniciar);
        ActionListener InicioSesion = (ActionEvent e) -> {
            String usuario = txtUsuario.getText();
            String contra = txtContra.getText();
            if (BuscarUsuario(usuario, contra)) {
                panelInicioSesion.setVisible(false);
                componentesClientes();
            }
        };
        btnIniciar.addActionListener(InicioSesion);
//boton nuevo usuario
        JButton btnNewUser = new JButton("Registrar");
        btnNewUser.setBounds(220, 130, 150, 20);
        panelInicioSesion.add(btnNewUser);
        ActionListener newuser = (ActionEvent e) -> {
            componentesNuevoUsuario();
            panelInicioSesion.setVisible(false);
            componentesNuevoUsuario();
        };
        btnNewUser.addActionListener(newuser);
        panelInicioSesion.repaint();
    }

    public boolean BuscarUsuario(String usuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(usuario) && misUsuarios[i].getPass().equals(contra)) {
                    encontrado = true;
                    misUsuarios[i].getName();
                    break;
                }
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido");
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
        return encontrado;
    }

    public void componentesNuevoUsuario() {
//2do panel
        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
        this.setTitle("Nuevo Usuario");
        panelNuevoUsuario.setBackground(cyan);
        
        
        JLabel NewNombre = new JLabel("Ingrese nombre de usuario");
        NewNombre.setBounds(50, 50, 250, 20);
        panelNuevoUsuario.add(NewNombre);
        JLabel NewContra = new JLabel("Ingrese su contraseña de usuario");
        NewContra.setBounds(50, 80, 250, 20);
        panelNuevoUsuario.add(NewContra);
//CUADROS DE TEXTO
        JTextField txtUsuarioo = new JTextField();
        txtUsuarioo.setBounds(250, 50, 150, 20);
        panelNuevoUsuario.add(txtUsuarioo);
        JTextField txtContraa = new JPasswordField();
        txtContraa.setBounds(250, 80, 150, 20);
        panelNuevoUsuario.add(txtContraa);
//BOTONES
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 150, 150, 20);
        panelNuevoUsuario.add(btnGuardar);
        ActionListener guardar = (ActionEvent e) -> {
            String nombre = txtUsuarioo.getText();
            String contra = txtContraa.getText();
            GuardarUsuario(nombre, contra);
            txtUsuarioo.setText("");
            txtContraa.setText("");
        };
        btnGuardar.addActionListener(guardar);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 180, 150, 20);
        panelNuevoUsuario.add(btnVolver);
        ActionListener volver = (ActionEvent e) -> {
            componentesInicioSesion();
            panelNuevoUsuario.setVisible(false);
        };
        btnVolver.addActionListener(volver);
        panelNuevoUsuario.repaint();
    }

    public void GuardarUsuario(String nombre, String contra) {
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            boolean vacio = false;
            int posicion = -1;
            for (int i = 0; i <= misUsuarios.length - 1; i++) {
                if (misUsuarios[i] != null) {
                    vacio = true;
                    posicion = i;
                    break;
                }
            }
            if (vacio) {
                misUsuarios[posicion] = new usuario(nombre, contra);
                JOptionPane.showMessageDialog(null, "Usuario guardado correctamente");
            } else {
                JOptionPane.showConfirmDialog(null, "El almacenamiento esta lleno");
            }
        }
    }

    public boolean comprobarDuplicadoUsuario(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(nombre)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }
//------------------------------componentes para mostrar clientes

    public void componentesClientes() {
        char M = 'M';
        char F = 'F';
        int ContadorM = 0;
        int ContadorF = 0;
//3er panel
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        this.setTitle("Dashboard de clientes");
        panelClientes.setBackground(ORANGE);
        
        //boton

        //tabla
        JLabel lblClientes = new JLabel("Clientes almacenados");
        lblClientes.setBounds(50, 50, 250, 30);
        panelClientes.add(lblClientes);
        DefaultTableModel datos = new DefaultTableModel();
        datos.addColumn("NIT");
        datos.addColumn("Nombre");
        datos.addColumn("Edad");
        datos.addColumn("Genero");
        for (int i = 0; i <= misClientes.length - 1; i++) {
            if (misClientes[i] != null) {
                String temporal[] = {misClientes[i].getNit(), misClientes[i].getNombre(), String.valueOf(misClientes[i].getEdad()), String.valueOf(misClientes[i].getGenero())};
                datos.addRow(temporal);
            }
        }
        //contador para grafica
        for (int i = 0; i <= misClientes.length - 1; i++) {
            if (misClientes[i] != null) {
                if (misClientes[i].getGenero() == (M)) {
                    ContadorM = ContadorM + 1;
                } else if (misClientes[i].getGenero() == (F)) {
                    ContadorF = ContadorF + 1;
                }
            }
        }
//codigo
        JTable tablaClientes = new JTable(datos);
//Scroll para tabla
        JScrollPane barraClientes = new JScrollPane(tablaClientes);
        barraClientes.setBounds(50, 80, 450, 250);
        panelClientes.add(barraClientes);
        DefaultPieDataset generoGrafico = new DefaultPieDataset();
        generoGrafico.setValue("Masculino", ContadorM);
        generoGrafico.setValue("Femenino", ContadorF);
        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos", generoGrafico, true, true, false);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(500, 80, 300, 200);
        panelClientes.add(panelCircular);
        this.setSize(850, 400);
        //Boton panel Nuevo Cliente
        JButton btnNewClient = new JButton("Nuevo Cliente");
        btnNewClient.setBounds(50, 20, 150, 20);
        panelClientes.add(btnNewClient);
        ActionListener nuevocl = (ActionEvent e) -> {
            componentesNuevoCliente();
            panelClientes.setVisible(false);
        };
        btnNewClient.addActionListener(nuevocl);
        //Boton regresar
        JButton btnBack = new JButton("Volver");
        btnBack.setBounds(240, 20, 150, 20);
        panelClientes.add(btnBack);
        ActionListener volver = (ActionEvent e) -> {
            componentesInicioSesion();
            panelClientes.setVisible(false);
        };
        btnBack.addActionListener(volver);
    }

    public void componentesNuevoCliente() {
        // 4th panel for adding a new client
        JPanel panelNuevoCliente = new JPanel();
        panelNuevoCliente.setLayout(null);
        this.getContentPane().add(panelNuevoCliente);
        this.setTitle("Nuevo Cliente");
        panelNuevoCliente.setBackground(GRAY);
        

        JLabel lblNit = new JLabel("NIT del Cliente");
        lblNit.setBounds(50, 50, 250, 20);
        panelNuevoCliente.add(lblNit);
        JLabel lblNombre = new JLabel("Nombre del Cliente");
        lblNombre.setBounds(50, 80, 250, 20);
        panelNuevoCliente.add(lblNombre);
        JLabel lblEdad = new JLabel("Edad del Cliente");
        lblEdad.setBounds(50, 110, 250, 20);
        panelNuevoCliente.add(lblEdad);
        JLabel lblGenero = new JLabel("Género del Cliente (M/F)");
        lblGenero.setBounds(50, 140, 250, 20);
        panelNuevoCliente.add(lblGenero);

        JTextField txtNit = new JTextField();
        txtNit.setBounds(250, 50, 150, 20);
        panelNuevoCliente.add(txtNit);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(250, 80, 150, 20);
        panelNuevoCliente.add(txtNombre);
        JTextField txtEdad = new JTextField();
        txtEdad.setBounds(250, 110, 150, 20);
        panelNuevoCliente.add(txtEdad);
        JTextField txtGenero = new JTextField();
        txtGenero.setBounds(250, 140, 150, 20);
        panelNuevoCliente.add(txtGenero);

        JButton btnGuardarCliente = new JButton("Guardar Cliente");
        btnGuardarCliente.setBounds(50, 180, 150, 20);
        panelNuevoCliente.add(btnGuardarCliente);

        ActionListener saveClient = (ActionEvent e) -> {
            String nit = txtNit.getText();
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            char genero = txtGenero.getText().charAt(0);
            
            AgregarCliente(nit, nombre, edad, genero);
            txtNit.setText("");
            txtNombre.setText("");
            txtEdad.setText("");
            txtGenero.setText("");
        };
        btnGuardarCliente.addActionListener(saveClient);

        JButton btnRegresaar = new JButton("Volver a Clientes");
        btnRegresaar.setBounds(50, 210, 150, 20);
        panelNuevoCliente.add(btnRegresaar);

        ActionListener backToClients = (ActionEvent e) -> {
            panelNuevoCliente.setVisible(false);
            componentesClientes();
        };
        btnRegresaar.addActionListener(backToClients);

        panelNuevoCliente.repaint();
    }

    public void AgregarCliente(String nit, String nombre, int edad, char genero) {
        if (contadorClientes < misClientes.length) {
            misClientes[contadorClientes] = new cliente(nit, nombre, edad, genero);
            contadorClientes++;
            JOptionPane.showMessageDialog(null, "Cliente guardado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El almacenamiento de clientes está lleno.");
        }
    }
}
