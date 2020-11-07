package com.java.project.checkin.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.java.project.checkin.models.Employee;

import javax.swing.JTabbedPane;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class CheckinMainFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel checkinPane;
	public static  JPasswordField txtCheckinPass;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	Timer timer; 
	JLabel lblClock;

	public static  JTextField txtNewEmployeeName;
	public static  JTextField txtNewEmployeeTel;
	public static  JTextField txtNewCheckinPdfPath;
	public static  JTextField txtNewEmployPdfPath;
	public static  JTextField txtNewEmail;
	
	
	public static  JPasswordField txtNewEmployeePass;
	public static  JPasswordField txtNewEmployeePassConfirm;
	public static  JPasswordField txtNewEmailPass;

	public static JScrollPane scrollPaneEmployee;
	public static JScrollPane scrollPaneCheckIn;
	public static JScrollPane scrollEmailPane;
	public static JScrollPane scrollPathsPane;

	
	public static  JTable tblPaths;
	public static  JTable tblEmail;
	public static  JTable tblCheckIn;
	public static  JTable tableEmployee;
	
	public static DefaultTableModel tableModelCheckin;
	public static DefaultTableModel tableModelEmployee;
	public static DefaultTableModel tableModelEmailConfig;
	public static DefaultTableModel tableModelPaths;
	
	public static  JComboBox<String> cmbCheckinEmployee;
	
	public static  Employee[] employeeList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckinMainFrame frame = new CheckinMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckinMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 684);
		checkinPane = new JPanel();
		checkinPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(checkinPane);
		checkinPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedCheckinPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedCheckinPane.setBackground(Color.WHITE);
		checkinPane.add(tabbedCheckinPane, BorderLayout.CENTER);
		
		JPanel checkinPanel = new JPanel();
		checkinPanel.setBackground(Color.WHITE);
		tabbedCheckinPane.addTab("Check In", null, checkinPanel, null);
		checkinPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CheckinMainFrame.class.getResource("/com/java/project/checkin/gui/makeUpImage.jpg")));
		lblNewLabel.setBounds(709, 12, 208, 131);
		checkinPanel.add(lblNewLabel);
		
		JLabel lblTitleCheckin = new JLabel("Checador");
		lblTitleCheckin.setForeground(new Color(102, 0, 153));
		lblTitleCheckin.setFont(new Font("Dialog", Font.BOLD, 45));
		lblTitleCheckin.setBounds(33, 22, 256, 53);
		checkinPanel.add(lblTitleCheckin);
		
		JComboBox cmbCheckinConcept = new JComboBox();
		cmbCheckinConcept.setModel(new DefaultComboBoxModel(new String[] {"ENTRADA", "SALIDA"}));
		cmbCheckinConcept.setBounds(401, 201, 160, 36);
		checkinPanel.add(cmbCheckinConcept);
		
		JLabel lblConcepto = new JLabel("Concepto:");
		lblConcepto.setFont(new Font("Dialog", Font.BOLD, 18));
		lblConcepto.setForeground(new Color(102, 0, 153));
		lblConcepto.setBounds(295, 206, 114, 25);
		checkinPanel.add(lblConcepto);
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setForeground(new Color(102, 0, 153));
		lblEmpleado.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmpleado.setBounds(284, 273, 114, 25);
		checkinPanel.add(lblEmpleado);
		
		cmbCheckinEmployee = new JComboBox();
		cmbCheckinEmployee.setBounds(401, 262, 289, 36);
		checkinPanel.add(cmbCheckinEmployee);
		
		JLabel lblEmpleado_1 = new JLabel("Contraseña:");
		lblEmpleado_1.setForeground(new Color(102, 0, 153));
		lblEmpleado_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmpleado_1.setBounds(273, 335, 136, 25);
		checkinPanel.add(lblEmpleado_1);
		
		txtCheckinPass = new JPasswordField();
		txtCheckinPass.setBounds(401, 331, 160, 36);
		checkinPanel.add(txtCheckinPass);
		
		lblClock = new JLabel("");
		lblClock.setForeground(new Color(102, 0, 153));
		lblClock.setFont(new Font("Dialog", Font.BOLD, 42));
		lblClock.setBounds(373, 91, 222, 67);
		checkinPanel.add(lblClock);
		lblClock.setText(sdf.format(new Date(System.currentTimeMillis())));
		
		JButton btnCheckTime = new JButton("Registrar");
		btnCheckTime.setBounds(408, 425, 139, 44);
		checkinPanel.add(btnCheckTime);
		timer = new Timer(500, this);
		timer.setRepeats(true);
		timer.start();
		
		JPanel employeePanel = new JPanel();
		employeePanel.setBackground(Color.WHITE);
		tabbedCheckinPane.addTab("Empleados", null, employeePanel, null);
		employeePanel.setLayout(null);
		
		JLabel lblTitleEmployee = new JLabel("Empleados");
		lblTitleEmployee.setForeground(new Color(102, 0, 153));
		lblTitleEmployee.setFont(new Font("Dialog", Font.BOLD, 45));
		lblTitleEmployee.setBounds(25, 24, 414, 53);
		employeePanel.add(lblTitleEmployee);
		
		scrollPaneEmployee = new JScrollPane();
		scrollPaneEmployee.setBounds(394, 127, 560, 341);
		employeePanel.add(scrollPaneEmployee);
		
		tableEmployee = new JTable();
		scrollPaneEmployee.setViewportView(tableEmployee);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(102, 0, 153));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNombre.setBounds(37, 122, 92, 25);
		employeePanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(new Color(102, 0, 153));
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTelefono.setBounds(25, 232, 114, 25);
		employeePanel.add(lblTelefono);
		
		JLabel lblRole = new JLabel("Puesto:");
		lblRole.setForeground(new Color(102, 0, 153));
		lblRole.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRole.setBounds(37, 175, 92, 25);
		employeePanel.add(lblRole);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(new Color(102, 0, 153));
		lblDireccion.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDireccion.setBounds(25, 287, 114, 25);
		employeePanel.add(lblDireccion);
		
		JLabel lblConcepto_4_1 = new JLabel("Contraseña:");
		lblConcepto_4_1.setForeground(new Color(102, 0, 153));
		lblConcepto_4_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblConcepto_4_1.setBounds(22, 426, 139, 25);
		employeePanel.add(lblConcepto_4_1);
		
		JLabel lblConcepto_4_2 = new JLabel("Confirmar:");
		lblConcepto_4_2.setForeground(new Color(102, 0, 153));
		lblConcepto_4_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblConcepto_4_2.setBounds(39, 478, 122, 25);
		employeePanel.add(lblConcepto_4_2);
		
		JButton btnSaveEmployee = new JButton("Registrar Empleado");
		btnSaveEmployee.setBounds(160, 539, 185, 37);
		employeePanel.add(btnSaveEmployee);
		
		txtNewEmployeeName = new JTextField();
		txtNewEmployeeName.setBounds(128, 119, 230, 33);
		employeePanel.add(txtNewEmployeeName);
		txtNewEmployeeName.setColumns(10);
		
		txtNewEmployeeTel = new JTextField();
		txtNewEmployeeTel.setColumns(10);
		txtNewEmployeeTel.setBounds(125, 229, 213, 33);
		employeePanel.add(txtNewEmployeeTel);
		
		JComboBox cmbRole = new JComboBox();
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Recepcionista", "Asistente", "Coordinador", "Director", "", ""}));
		cmbRole.setBounds(126, 172, 219, 33);
		employeePanel.add(cmbRole);
		
		JTextArea txtNewEmployeeAddress = new JTextArea();
		txtNewEmployeeAddress.setBackground(SystemColor.controlHighlight);
		txtNewEmployeeAddress.setBounds(143, 287, 230, 108);
		employeePanel.add(txtNewEmployeeAddress);
		
		txtNewEmployeePass = new JPasswordField();
		txtNewEmployeePass.setBounds(160, 421, 178, 37);
		employeePanel.add(txtNewEmployeePass);
		
		txtNewEmployeePassConfirm = new JPasswordField();
		txtNewEmployeePassConfirm.setBounds(160, 473, 178, 37);
		employeePanel.add(txtNewEmployeePassConfirm);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CheckinMainFrame.class.getResource("/com/java/project/checkin/gui/makeUpImage.jpg")));
		lblNewLabel_1.setBounds(708, 24, 208, 91);
		employeePanel.add(lblNewLabel_1);
		
		JButton btnEraseEmployee = new JButton("Borrar");
		btnEraseEmployee.setBounds(822, 479, 117, 25);
		employeePanel.add(btnEraseEmployee);
		
		JPanel adminPanel = new JPanel();
		adminPanel.setBackground(Color.WHITE);
		tabbedCheckinPane.addTab("Administracion", null, adminPanel, null);
		adminPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		adminPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("CheckIn", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblChecador = new JLabel("Checador");
		lblChecador.setBounds(41, 26, 276, 53);
		lblChecador.setForeground(new Color(102, 0, 153));
		lblChecador.setFont(new Font("Dialog", Font.BOLD, 45));
		panel.add(lblChecador);
		
		scrollPaneCheckIn = new JScrollPane();
		scrollPaneCheckIn.setBounds(41, 144, 898, 356);
		panel.add(scrollPaneCheckIn);
		
		tblCheckIn = new JTable();
		scrollPaneCheckIn.setViewportView(tblCheckIn);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(CheckinMainFrame.class.getResource("/com/java/project/checkin/gui/makeUpImage.jpg")));
		lblNewLabel_2.setBounds(750, 12, 202, 94);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Configuración Correo", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCorreoGestor = new JLabel("Correo Gestor");
		lblCorreoGestor.setForeground(new Color(102, 0, 153));
		lblCorreoGestor.setFont(new Font("Dialog", Font.BOLD, 45));
		lblCorreoGestor.setBounds(28, 23, 399, 53);
		panel_1.add(lblCorreoGestor);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(102, 0, 153));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmail.setBounds(224, 108, 81, 25);
		panel_1.add(lblEmail);
		
		JLabel lblApplicationPassword = new JLabel("Application Password:");
		lblApplicationPassword.setForeground(new Color(102, 0, 153));
		lblApplicationPassword.setFont(new Font("Dialog", Font.BOLD, 18));
		lblApplicationPassword.setBounds(66, 158, 239, 25);
		panel_1.add(lblApplicationPassword);
		
		txtNewEmail = new JTextField();
		txtNewEmail.setBounds(303, 106, 247, 31);
		panel_1.add(txtNewEmail);
		txtNewEmail.setColumns(10);
		
		txtNewEmailPass = new JPasswordField();
		txtNewEmailPass.setBounds(303, 151, 184, 31);
		panel_1.add(txtNewEmailPass);
		
		scrollEmailPane = new JScrollPane();
		scrollEmailPane.setBounds(105, 270, 732, 146);
		panel_1.add(scrollEmailPane);
		
		tblEmail = new JTable();
		scrollEmailPane.setViewportView(tblEmail);
		
		JButton btnSaveEmail = new JButton("Guardar");
		btnSaveEmail.setBounds(313, 204, 154, 31);
		panel_1.add(btnSaveEmail);
		
		JButton btnUpdateEmail = new JButton("Actualizar");
		btnUpdateEmail.setBounds(115, 436, 154, 31);
		panel_1.add(btnUpdateEmail);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(CheckinMainFrame.class.getResource("/com/java/project/checkin/gui/makeUpImage.jpg")));
		lblNewLabel_3.setBounds(731, 23, 216, 90);
		panel_1.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Configuración Paths", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblPathsDeReportes = new JLabel("Paths de Reportes");
		lblPathsDeReportes.setForeground(new Color(102, 0, 153));
		lblPathsDeReportes.setFont(new Font("Dialog", Font.BOLD, 45));
		lblPathsDeReportes.setBounds(36, 28, 495, 53);
		panel_2.add(lblPathsDeReportes);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(CheckinMainFrame.class.getResource("/com/java/project/checkin/gui/makeUpImage.jpg")));
		lblNewLabel_4.setBounds(704, 28, 217, 106);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblCheckinPdfPath = new JLabel("CheckIn Pdf Path:");
		lblCheckinPdfPath.setForeground(new Color(102, 0, 153));
		lblCheckinPdfPath.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCheckinPdfPath.setBounds(92, 147, 192, 25);
		panel_2.add(lblCheckinPdfPath);
		
		JLabel lblEmployeePdfPath = new JLabel("Employee Pdf Path:");
		lblEmployeePdfPath.setForeground(new Color(102, 0, 153));
		lblEmployeePdfPath.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmployeePdfPath.setBounds(78, 197, 206, 25);
		panel_2.add(lblEmployeePdfPath);
		
		txtNewCheckinPdfPath = new JTextField();
		txtNewCheckinPdfPath.setBounds(279, 143, 392, 35);
		panel_2.add(txtNewCheckinPdfPath);
		txtNewCheckinPdfPath.setColumns(10);
		
		txtNewEmployPdfPath = new JTextField();
		txtNewEmployPdfPath.setColumns(10);
		txtNewEmployPdfPath.setBounds(279, 193, 392, 35);
		panel_2.add(txtNewEmployPdfPath);
		
		JButton btnNewButton = new JButton("Guardar Paths");
		btnNewButton.setBounds(394, 241, 143, 40);
		panel_2.add(btnNewButton);
		
		scrollPathsPane = new JScrollPane();
		scrollPathsPane.setBounds(105, 303, 777, 205);
		panel_2.add(scrollPathsPane);
		
		tblPaths = new JTable();
		scrollPathsPane.setViewportView(tblPaths);
		
		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.setBounds(115, 520, 117, 25);
		panel_2.add(btnNewButton_1);
		
		///////////////BEGINS CHECKIN MODEL BUILDING //////////////////////////  		
		final String infoCheckinColumns[] = {"IdCheckin", "Concepto", "Empleado", "Cargo", "Fecha", "Hora Registro"};
		tableModelCheckin = new DefaultTableModel(infoCheckinColumns, 0);
		tblCheckIn = new JTable(tableModelCheckin);
		tblCheckIn.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblCheckIn.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblCheckIn.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblCheckIn.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblCheckIn.getColumnModel().getColumn(3).setPreferredWidth(200);
		tblCheckIn.getColumnModel().getColumn(4).setPreferredWidth(200);
		tblCheckIn.getColumnModel().getColumn(5).setPreferredWidth(130);
		scrollPaneCheckIn.setViewportView(tblCheckIn);
		
		///////////////BEGINS EMPLOYEE MODEL BUILDING //////////////////////////  		
		final String infoEmployeeColumns[] = {"IdEmpleado", "Nombre", "Cargo", "Telefono", "Direccion"};
		tableModelEmployee = new DefaultTableModel(infoEmployeeColumns, 0);
		tableEmployee = new JTable(tableModelEmployee);
		tableEmployee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEmployee.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableEmployee.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableEmployee.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableEmployee.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableEmployee.getColumnModel().getColumn(4).setPreferredWidth(300);
		scrollPaneEmployee.setViewportView(tableEmployee);
		
		///////////////BEGINS EMAIL CONFIG MODEL BUILDING //////////////////////////  		
		final String infoEmailConfigColumns[] = {"IdEmail", "Email", "Contraseña", "ServicioActivo"};
		tableModelEmailConfig = new DefaultTableModel(infoEmailConfigColumns, 0);
		tblEmail = new JTable(tableModelEmailConfig);
		tblEmail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblEmail.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblEmail.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblEmail.getColumnModel().getColumn(2).setPreferredWidth(250);
		tblEmail.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollEmailPane.setViewportView(tblEmail);
		
		///////////////BEGINS PATHS CONFIG MODEL BUILDING //////////////////////////  		
		final String infoPathsColumns[] = {"IdPath", "CheckInPDF", "EmpleadoPDF"};
		tableModelPaths = new DefaultTableModel(infoPathsColumns, 0);
		tblPaths = new JTable(tableModelPaths);
		tblPaths.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblPaths.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblPaths.getColumnModel().getColumn(1).setPreferredWidth(350);
		tblPaths.getColumnModel().getColumn(2).setPreferredWidth(350);
		scrollPathsPane.setViewportView(tblPaths);
		
		
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			lblClock.setText(sdf.format(new Date(System.currentTimeMillis())));
		}
	}


}
