package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import Controller.ControllerFormChat;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Image;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URL;

public class V_FrmChat_Client extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JPanel panel_TaoNhom;
    public JPanel panel_GuiNhieuTin;
    public JButton btn_taoNhom;
    public JButton btn_guiNhieuTin;
    public JPanel panel_caiDat;
    private JTextField textField;

    private DefaultListModel<String> model;
    public JList<String> list_UIDName_onl;
    public JPanel panel_ThongBaoKetNoi;
    public JPanel panel_Chinh;
    public JPanel panel_nguoidung;
    public JPanel panel_chucNang;

    public String userName;
    public String ID;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    V_FrmChat_Client frame = new V_FrmChat_Client();
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
    public V_FrmChat_Client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("LAN Chat Application - Client");

        URL urlIconFrame = V_FrmChat_Client.class.getResource("/Images/App_server.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconFrame);
        setIconImage(img);

        try {
            // Lấy địa chỉ IP của máy tính
            InetAddress ip = InetAddress.getLocalHost();
            String ipAddress = ip.getHostAddress();

            // Chuyển đổi IP thành số 3 chữ số
            int ipHash = ipAddress.hashCode(); // Lấy giá trị băm của địa chỉ IP
            int ipNumber = Math.abs(ipHash) % 1000;
            ID = String.format("%03d", ipNumber);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            ID = 000 + "";
        }

        nhapTen();

        //ActionListener ac = new ControllerFormChat(this);
        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
//        setBounds(0, 0, 1543, 879);
        setBounds(0, 0, width, height);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        setJMenuBar(menuBar);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JMenu jm_Options = new JMenu("Options");
        jm_Options.setMnemonic(KeyEvent.VK_O);
        JMenuItem jmit_display = new JMenuItem("DisPlay");
        jm_Options.add(jmit_display);

        JMenu jm_Help = new JMenu("Help");
        jm_Help.setMnemonic(KeyEvent.VK_H);
        JMenuItem jmit_welcome = new JMenuItem("Welcome");
        jm_Help.add(jmit_welcome);

        menuBar.add(jm_Options);
        menuBar.add(jm_Help);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel_Chinh = new JPanel();
        panel_Chinh.setBackground(new Color(128, 128, 128));
        panel_Chinh.setBounds(300, 0, 1236, 827);
        panel_Chinh.setLayout(null);
        contentPane.add(panel_Chinh);

        JPanel panel_chat = new JPanel();
        panel_chat.setBounds(0, 0, 1236, 827);
        panel_chat.setLayout(null);
        panel_Chinh.add(panel_chat);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBounds(115, 763, 1028, 44);
        textField.setColumns(10);
        panel_chat.add(textField);

        JButton btn_guiTin = new JButton("Đi");
        btn_guiTin.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btn_guiTin.setBounds(1150, 762, 76, 44);
        panel_chat.add(btn_guiTin);

        JPanel panel_TinNhan = new JPanel();
        panel_TinNhan.setBackground(new Color(192, 192, 192));
//		panel_TinNhan.setBounds(0, 44, 1236, 697);
        panel_TinNhan.setLayout(new BoxLayout(panel_TinNhan, BoxLayout.Y_AXIS));
//		panel_chat.add(panel_TinNhan);

        JScrollPane scrollPane_TinNhan = new JScrollPane(panel_TinNhan);
        scrollPane_TinNhan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_TinNhan.setBounds(0, 49, 1236, 704);
        panel_chat.add(scrollPane_TinNhan);

        panel_GuiNhieuTin = new JPanel();
        panel_GuiNhieuTin.setBounds(194, 176, 1042, 651);
        panel_GuiNhieuTin.setBackground(new Color(255, 255, 255));
        panel_GuiNhieuTin.setVisible(false);
        panel_GuiNhieuTin.setLayout(null);
        panel_Chinh.add(panel_GuiNhieuTin);

        JLabel lbl_guiTin = new JLabel("Gửi tin nhắn:");
        lbl_guiTin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_guiTin.setBounds(0, 0, 90, 45);
        panel_GuiNhieuTin.add(lbl_guiTin);

        panel_caiDat = new JPanel();
        panel_caiDat.setBounds(79, 82, 1157, 745);
        panel_caiDat.setVisible(false);
        panel_caiDat.setLayout(null);
        panel_Chinh.add(panel_caiDat);

        JLabel lbl_caiDat = new JLabel("Cài đặt: ");
        lbl_caiDat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_caiDat.setBounds(10, 10, 59, 27);
        panel_caiDat.add(lbl_caiDat);

        panel_TaoNhom = new JPanel();
        panel_TaoNhom.setBounds(309, 259, 927, 568);
        panel_TaoNhom.setBackground(new Color(255, 255, 255));
        panel_TaoNhom.setVisible(false);
        panel_TaoNhom.setLayout(null);
        panel_Chinh.add(panel_TaoNhom);

        JLabel lbl_taoNhom = new JLabel("Tạo nhóm: ");
        lbl_taoNhom.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_taoNhom.setBounds(0, 0, 78, 44);
        panel_TaoNhom.add(lbl_taoNhom);

        panel_nguoidung = new JPanel();
        panel_nguoidung.setBackground(new Color(192, 192, 192));
        panel_nguoidung.setBounds(0, 0, 300, 750);
        contentPane.add(panel_nguoidung);
        panel_nguoidung.setLayout(null);

        JPanel panel_UIDName = new JPanel();
        panel_UIDName.setBackground(new Color(192, 192, 192));
        panel_UIDName.setBounds(0, 0, 300, 50);
        panel_nguoidung.add(panel_UIDName);
        panel_UIDName.setLayout(null);

        JLabel lbl_IDNguoiDung = new JLabel("ID: " + ID); //chưa thêm id
        lbl_IDNguoiDung.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_IDNguoiDung.setBackground(new Color(255, 255, 255));
        lbl_IDNguoiDung.setBounds(10, 0, 250, 25);
        panel_UIDName.add(lbl_IDNguoiDung);

        JLabel lbl_tenNguoiDung = new JLabel(userName);
        lbl_tenNguoiDung.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_tenNguoiDung.setBounds(10, 25, 250, 25);
        panel_UIDName.add(lbl_tenNguoiDung);
        panel_TinNhan.setLayout(new BoxLayout(panel_TinNhan, BoxLayout.Y_AXIS));

        JLabel lbl_chatUID = new JLabel("000");
        lbl_chatUID.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lbl_chatUID.setBounds(10, 10, 76, 29);
        panel_chat.add(lbl_chatUID);

        JLabel lbl_chatName = new JLabel("Tên gì đó");
        lbl_chatName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_chatName.setBounds(186, 10, 174, 30);
        panel_chat.add(lbl_chatName);

        model = new DefaultListModel<>();
        list_UIDName_onl = new JList<>(model);
        list_UIDName_onl.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JScrollPane scrollPane_listUIDName = new JScrollPane(list_UIDName_onl);
        scrollPane_listUIDName.setBounds(0, 50, 300, 700);
        panel_nguoidung.add(scrollPane_listUIDName);

        panel_ThongBaoKetNoi = new JPanel();
        panel_ThongBaoKetNoi.setBackground(new Color(128, 128, 128));
        panel_ThongBaoKetNoi.setBounds(0, 0, 1536, 827);
        panel_ThongBaoKetNoi.setLayout(new BorderLayout(0, 0));
        panel_ThongBaoKetNoi.setVisible(false);
        contentPane.add(panel_ThongBaoKetNoi);

        JLabel lbl_ThongBaoKetNoi = new JLabel("Không thể kết nối đến server");
        lbl_ThongBaoKetNoi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
        panel_ThongBaoKetNoi.add(lbl_ThongBaoKetNoi, BorderLayout.CENTER);

        panel_chucNang = new JPanel();
        panel_chucNang.setBackground(new Color(255, 255, 255));
        panel_chucNang.setBounds(0, 750, 300, 77);
        contentPane.add(panel_chucNang);
        panel_chucNang.setLayout(null);

        btn_guiNhieuTin = new JButton("Gửi nhiều");
        btn_guiNhieuTin.setBounds(37, 10, 85, 49);
        panel_chucNang.add(btn_guiNhieuTin);
//		btn_guiNhieuTin.addActionListener(ac);

        btn_taoNhom = new JButton("Tạo nhóm");
        btn_taoNhom.setBounds(163, 10, 85, 49);
        panel_chucNang.add(btn_taoNhom);
//		btn_taoNhom.addActionListener(ac);
    }

    // Phương thức để cập nhật trạng thái của panel
    public void hienThongBaoKetNoi(boolean hienThi) {
        if (hienThi) {
            panel_ThongBaoKetNoi.setVisible(true); // Hiển thị thông báo nếu kết nối không thành công
            panel_Chinh.setVisible(false);
            panel_chucNang.setVisible(false);
            panel_nguoidung.setVisible(false);
        }
    }

    public void addClientToList(String IDCln, String NameCln) {
        String newClient = IDCln + " | " + NameCln;
        model.addElement(newClient); // Thêm clientID vào JList
    }

    public void removeClientFromList(String clientName) {
        for (int i = 0; i < model.getSize(); i++) {
            String clientInfo = model.getElementAt(i);
            if (clientInfo.contains(clientName)) {
                model.remove(i);
                break;
            }
        }
    }

    public void nhapTen() {
        while (userName == null || userName.trim().isEmpty()) {
            userName = JOptionPane.showInputDialog(null, "Vui lòng nhập tên của bạn:", "Nhập tên",
                    JOptionPane.PLAIN_MESSAGE);

            if (userName == null || userName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bạn phải nhập tên để sử dụng ứng dụng!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
