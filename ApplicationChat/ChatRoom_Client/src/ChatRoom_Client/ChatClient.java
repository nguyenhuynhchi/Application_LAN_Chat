package ChatRoom_Client;

import View.V_FrmChat_Client;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nguyen Huynh Chi
 */
public class ChatClient {

    private static final String URL = "localhost";
    private static final int PORT_INFO = 5000;

    private static ChatClient instance;
    private V_FrmChat_Client vFC;
    public String clientName;
    public String clientID;
    public boolean connect;

    private Socket socket;
//    private Socket chatSocket;
    private MessageSender messageSender;
    private Client_Listener client_Listener;
    
    public Map<String, List<String>> listGroups = new HashMap<>();

    public ChatClient(V_FrmChat_Client vFC) {
        this.vFC = vFC;
    }

    public static synchronized ChatClient getInstance(V_FrmChat_Client vFC) {
        if (instance == null) {
            instance = new ChatClient(vFC);
        }
        return instance;
    }

    public void StartClient() {
        try {
            clientName = vFC.userName;
            clientID = vFC.ID;

            socket = new Socket(URL, PORT_INFO); // Cổng 5000

            System.out.println("Connected to server");

            System.out.println("Name(ID) của bạn: " + clientName + "(" + clientID + ")");

            // Gửi tên của chính client lên server
            OutputStream InfoOutput = socket.getOutputStream();
            InfoOutput.write(("InfoNewClients|" + clientName + "|" + clientID + "\n").getBytes());

            // Tạo và khởi chạy thread cho chatInfo_Listener để nhận thông tin các client từ server gửi về
            client_Listener = new Client_Listener(socket, vFC);
            new Thread(client_Listener).start();

            messageSender = new MessageSender(socket, vFC);
            new Thread(messageSender).start();
            messageSender.setClientName(clientName);
            messageSender.setClientID(clientID);

            connect = true; //Kiểm tra kết nối thành công thì không hiện panel thông báo
        } catch (java.net.ConnectException e) {
            System.out.println("Lỗi: Không thể kết nối đến server. Vui lòng kiểm tra server và thử lại.");
            connect = false; //Kiểm tra kết nối không thành công thì hiện panel thông báo
        } catch (Exception e) {
            System.out.println("Lỗi ở chatClient");  // Xử lý các ngoại lệ I/O khác
            connect = false; //Kiểm tra kết nối không thành công thì hiện panel thông báo
        }
        vFC.hienThongBaoKetNoi(connect);
    }
    
    public synchronized void createGroup(String groupName, String quantityInGroup, List<String> clientsInGroup) {
        listGroups.put(groupName, clientsInGroup);
        System.out.println("\n🔔 Bạn vừa được thêm vào nhóm '" + groupName + "', với " + quantityInGroup + " thành viên: ");
        for (String clients : clientsInGroup) {
            System.out.println(clients);
        }
        vFC.addGroupToList(groupName, quantityInGroup);
        System.out.println("Danh sách nhóm có bạn là thành viên: "+listGroups);
        vFC.addMessage("\n🔔 Bạn vừa được thêm vào nhóm '" + groupName + "', với " + quantityInGroup + " thành viên: ", "in");
    }

    public List<String> getClientsInGroup(String groupName) {
		if (!listGroups.containsKey(groupName)) {
			System.out.println("Nhóm '" + groupName + "' không tồn tại trong HashMap");
		}
		return listGroups.getOrDefault(groupName, Collections.emptyList());
	}
}
