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

    JPanel panelInicioSesion, panelNuevoUsuario, panelClientes;
    JTextField txtusuario;
    JPasswordField txtcontra;
    usuario misUsuarios[] = new usuario[10];
    cliente misClientes[] = new cliente[20];

    public ventana() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        misUsuarios[0] = new usuario("Admin", "123");

        misUsuarios[1] = new usuario("otro", "321");

        misClientes[0] = new cliente("741", "Roberto", 24, 'M');
        misClientes[1] = new cliente("742", "Carlos", 39, 'M');
        misClientes[2] = new cliente("743", "Diego", 30, 'M');
        misClientes[3] = new cliente("744", "Ian", 24, 'M');
        misClientes[4] = new cliente("745", "Quevin", 22, 'M');
        misClientes[5] = new cliente("746", "Dayana", 27, 'F');

    }

    public void componetesInicioSesion() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        this.setTitle("Inicio Sesion");
        JLabel lblUsuario = new JLabel("Ingresar usuario");
        lblUsuario.setBounds(50, 15, 150, 15);
        panelInicioSesion.add(lblUsuario);
        JLabel lblcontra = new JLabel("Ingresar contraseña");
        lblcontra.setBounds(50, 50, 150, 15);
        panelInicioSesion.add(lblcontra);
        txtusuario = new JTextField();
        txtusuario.setBounds(210, 15, 150, 25);
        panelInicioSesion.add(txtusuario);
        txtcontra = new JPasswordField();
        txtcontra.setBounds(210, 50, 150, 25);
        panelInicioSesion.add(txtcontra);
        JButton btnIniciar = new JButton("Ingresar");//ingresar
        btnIniciar.setBounds(200, 150, 100, 30);
        panelInicioSesion.add(btnIniciar);
        ActionListener iniciarSesion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                String contra = txtcontra.getText();
                if (buscarUsuario(usuario, contra)) {
                    panelInicioSesion.setVisible(false);
                    componentesClientes();
                }
            }
        };
        btnIniciar.addActionListener(iniciarSesion);

//registrar usuario
        JButton btnNuevoUsuario = new JButton("Registrar");//registrar
        btnNuevoUsuario.setBounds(340, 150, 100, 30);
        panelInicioSesion.add(btnNuevoUsuario);
        ActionListener nuevoUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
            }
        };
        btnNuevoUsuario.addActionListener(nuevoUsuario);
        panelInicioSesion.repaint();
    }

    public boolean buscarUsuario(String usuario, String contra) {
        boolean encontrado = false;
        String Name = "";
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(usuario) && misUsuarios[i].getPass().equals(contra)) {
                    encontrado = true;
                    Name = misUsuarios[i].getName();
                    break;
                }
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + Name);
        } else {
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }
        return encontrado;

    }

    public void componentesNuevoUsuario() {
        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
        this.setTitle("Crear nueva cuenta");
        JLabel nuevoNombre = new JLabel("ingrese nombre de usurio: ");
        nuevoNombre.setBounds(80, 25, 170, 20);
        panelNuevoUsuario.add(nuevoNombre);

//agregar nombre de usuario
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(250, 25, 150, 25);
        panelNuevoUsuario.add(txtNombre);
        JLabel nuevaContra = new JLabel("ingrese una contraseña: ");
        nuevaContra.setBounds(80, 65, 170, 20);
        panelNuevoUsuario.add(nuevaContra);
        JPasswordField txtNuevaContra = new JPasswordField();
        txtNuevaContra.setBounds(250, 65, 150, 25);
        panelNuevoUsuario.add(txtNuevaContra);
        JButton btnguardar = new JButton("Guardar");
        btnguardar.setBounds(200, 150, 100, 30);
        panelNuevoUsuario.add(btnguardar);
        ActionListener almacenar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String contra = txtNuevaContra.getText();
                if (guardarUsuario(nombre, contra)) {
                    txtNombre.setText("");
                    txtNuevaContra.setText("");
                }
            }
        };

        btnguardar.addActionListener(almacenar);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 150, 100, 30);
        panelNuevoUsuario.add(btnVolver);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componetesInicioSesion();
                panelInicioSesion.setVisible(true);
                panelNuevoUsuario.setVisible(false);
            }
        };

        btnVolver.addActionListener(volver);
    }

    public boolean guardarUsuario(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
// funcion para comprobar duplicados
            if (comprobarDuplicadosUsuarios(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe el usuario");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= misUsuarios.length - 1; i++) {
                    if (misUsuarios[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;
                    }
                }
                if (vacio) {
                    misUsuarios[posicion] = new usuario(nombre, contra);
                    JOptionPane.showMessageDialog(null, "Usuario almacenado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Ya no se pueden guardar usuarios");
                }
            }
        }
        return guardado;

    }

    public boolean comprobarDuplicadosUsuarios(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(nombre)) {
                    duplicado = true;
                    break;
                }
            }

        }
        return duplicado;

    }

//------------------------------Componentes para mostrar clientes-----------------------------------------
    public void componentesClientes() {
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        this.setTitle("Dashboard de clientes");
        JLabel lblClientes = new JLabel("Clientes Almacenados");
        lblClientes.setBounds(10, 10, 150, 15);
        panelClientes.add(lblClientes);
//datos de pueba
        DefaultTableModel datos = new DefaultTableModel();
        datos.addColumn("NIT");
        datos.addColumn("Nombre");
        datos.addColumn("Edad");
        datos.addColumn("Genero");
        String prueba1[] = {"1", "uno", "1", "f"};
        datos.addRow(prueba1);
        for (int i = 0; i < misClientes.length - 1; i++) {
            if (misClientes[i] != null) {
                String temporal[] = {misClientes[i].getNit(), misClientes[i].getNombre(), String.valueOf(misClientes[i].getEdad()), String.valueOf(misClientes[i].getGenero())};
                datos.addRow(temporal);
            }
        }

//Creando tabla
       JTable tablaClientes = new JTable(datos);
       
//creando scroll para la tabla
        JScrollPane barraClientes = new JScrollPane(tablaClientes);
        barraClientes.setBounds(25, 40, 350, 250);
        panelClientes.add(barraClientes);
        // grafico de patel
        DefaultPieDataset generoGrafico = new DefaultPieDataset();
        generoGrafico.setValue("Masculino", 7);
        generoGrafico.setValue("Femenino", 10);
        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos", generoGrafico, true, true, false);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        this.setSize(850, 400);
        panelCircular.setBounds(400, 20, 300, 225);
        panelClientes.add(panelCircular);
    }
}
