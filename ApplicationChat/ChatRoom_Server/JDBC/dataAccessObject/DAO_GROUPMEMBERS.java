package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import data.JDBC_Util;
import model.GROUPMEMBERS_model;
import model.GROUPS_model;

public class DAO_GROUPMEMBERS implements interface_DAO<GROUPMEMBERS_model>{

	@Override
	public int insert(GROUPMEMBERS_model t) {
		try {
			// Tạo kết nối đến csdl
			Connection conn = JDBC_Util.getConnection();

			if (conn != null) {
				// Thực thi lệnh sql
				String sql = "INSERT INTO GROUPMEMBERS () "
						+ "VALUES (?)";

				// Tạo đối tượng PreparedStatement
				PreparedStatement pstmt = conn.prepareStatement(sql);

				// Thiết lập các giá trị cho PreparedStatement
//				pstmt.setInt(1, t.getUser_id()); // Sử dụng userId nhận được từ DAO_nguoichoi
				

				// thực thi lệnh SQL
				int affectedRows = pstmt.executeUpdate();

				// Kiểm tra xem có dòng nào bị ảnh hưởng không (nghĩa là dữ liệu đã được chèn)
				if (affectedRows > 0) {
					System.out.println("\n- Bạn đã thực thi câu lệnh: " + sql);
					System.out.println("- Có " + affectedRows + " dòng bị thay đổi");
				}

				// Ngắt kết nối
				JDBC_Util.closeConnection(conn);
			} else {
				System.out.println("Kết nối không thành công.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Error: " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int update(GROUPMEMBERS_model t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(GROUPMEMBERS_model t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<GROUPMEMBERS_model> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GROUPMEMBERS_model selectById(GROUPMEMBERS_model t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GROUPMEMBERS_model> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findByCondition(String condition) {
		// TODO Auto-generated method stub
		return 0;
	}

}
