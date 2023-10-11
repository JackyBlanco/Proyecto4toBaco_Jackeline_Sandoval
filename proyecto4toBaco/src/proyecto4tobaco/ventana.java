package proyecto4tobaco;

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

    JPanel panelInicioSesion, panelNewUser, panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    usuario misUsuarios[] = new usuario[10];
    cliente misClientes[] = new cliente[100];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new usuario("Admin", "123");
        misUsuarios[1] = new usuario("Otro", "321");
        misClientes[0] = new cliente("741", "Roberto", 24, 'M');
        misClientes[1] = new cliente("742", "Carlos", 39, 'M');
        misClientes[2] = new cliente("743", "Diego", 30, 'M');
        misClientes[3] = new cliente("744", "Ian", 24, 'M');
        misClientes[4] = new cliente("745", "Quevin", 22, 'M');
        misClientes[5] = new cliente("746", "Dayana", 27, 'F');
    }

    public void componentesInicioSesion() {
//Panel
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        this.setTitle("Inicio Sesion");
//ETIQUETAS
        JLabel lblUsuario = new JLabel("Ingrese su usuario");
        lblUsuario.setBounds(50, 50, 150, 20);
        panelInicioSesion.add(lblUsuario);
        JLabel lblContra = new JLabel("Ingrese su Password");
        lblContra.setBounds(50, 80, 150, 20);
        panelInicioSesion.add(lblContra);
//CUADROS DE TEXTO
        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 50, 150, 20);
        panelInicioSesion.add(txtUsuario);
        txtContra = new JPasswordField();
        txtContra.setBounds(180, 80, 150, 20);
        panelInicioSesion.add(txtContra);
        JButton btnIniciar = new JButton("Log in");
        btnIniciar.setBounds(50, 110, 150, 20);
        panelInicioSesion.add(btnIniciar);
        ActionListener InicioSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtContra.getText();
                if (BuscarUser(usuario, contra)) {
                    panelInicioSesion.setVisible(false);
                    componentesClientes();
                }
            }
        };
        btnIniciar.addActionListener(InicioSesion);
//boton nuevo usuario
        JButton btnNewUser = new JButton("Register");
        btnNewUser.setBounds(50, 140, 150, 20);
        panelInicioSesion.add(btnNewUser);
        ActionListener newuser = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNewUser();
                panelInicioSesion.setVisible(false);
                componentesNewUser();
            }
        };
        btnNewUser.addActionListener(newuser);
        panelInicioSesion.repaint();
    }

    public boolean BuscarUser(String usuario, String contra) {
        boolean encontrado = false;
        String nombre = "";
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(usuario) && misUsuarios[i].getPass().equals(contra)) {
                    encontrado = true;
                    nombre = misUsuarios[i].getName();
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

    public void componentesNewUser() {
//2do panel
        panelNewUser = new JPanel();
        panelNewUser.setLayout(null);
        this.getContentPane().add(panelNewUser);
        this.setTitle("New User");
        JLabel NewNombre = new JLabel("Ingrese nombre de usuario");
        NewNombre.setBounds(50, 50, 250, 20);
        panelNewUser.add(NewNombre);
        JLabel NewContra = new JLabel("Ingrese su contraseña de usuario");
        NewContra.setBounds(50, 80, 250, 20);
        panelNewUser.add(NewContra);
//CUADROS DE TEXTO
        JTextField txtUsuarioo = new JTextField();
        txtUsuarioo.setBounds(250, 50, 150, 20);
        panelNewUser.add(txtUsuarioo);
        JTextField txtContraa = new JPasswordField();
        txtContraa.setBounds(250, 80, 150, 20);
        panelNewUser.add(txtContraa);
//BOTONES
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 150, 150, 20);
        panelNewUser.add(btnGuardar);
        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtUsuarioo.getText();
                String contra = txtContraa.getText();
                SaveUser(nombre, contra);
                txtUsuarioo.setText("");
                txtContraa.setText("");
            }
        };
        btnGuardar.addActionListener(guardar);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 180, 150, 20);
        panelNewUser.add(btnVolver);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelNewUser.setVisible(false);
            }
        };
        btnVolver.addActionListener(volver);
        panelNewUser.repaint();
    }

    public void SaveUser(String nombre, String contra) {
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

    public boolean comprobarDuplicadoUser(String nombre) {
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
        int ContadorMan = 0;
        int ContadorFwoman = 0;
//3er panel
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        this.setTitle("Dashboard de clientes");
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
                    ContadorMan = ContadorMan + 1;
                } else if (misClientes[i].getGenero() == (F)) {
                    ContadorFwoman = ContadorFwoman + 1;
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
        generoGrafico.setValue("Masculino", ContadorMan);
        generoGrafico.setValue("Femenino", ContadorFwoman);
        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos", generoGrafico, true, true, false);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(500, 80, 300, 200);
        panelClientes.add(panelCircular);
        this.setSize(850, 400);
        //Boton panel Nuevo Cliente
        JButton btnNewClient = new JButton("Nuevo Cliente");
        btnNewClient.setBounds(50, 20, 150, 20);
        panelClientes.add(btnNewClient);
        ActionListener nuevocl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNuevoCliente();
                panelClientes.setVisible(false);
            }
        };
        btnNewClient.addActionListener(nuevocl);
        //Boton regresar
        JButton btnBack = new JButton("Volver");
        btnBack.setBounds(240, 20, 150, 20);
        panelClientes.add(btnBack);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelClientes.setVisible(false);
            }
        };
        btnBack.addActionListener(volver);
    }

   
