package kr.mem.model;
import java.sql.*;
import java.util.ArrayList;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	//초기화 블럭
	static {
		try {
			//드라이버 동적로딩 -> 일반적으로 코드내에 로딩하는게 아닌 프로그램이 실행될때 로딩되게 하는 방법 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//DriverManager 클래스를 로딩하게됨
		} catch (Exception e/*예외들의 부모*/) {
			e.printStackTrace();
		}
	}
	public Connection getConnect() {
		//@앞 프로토콜 -> 정해졌음 @뒤 서브프로토콜 -> 바뀔수 있음.
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user="hr";
		String password="hr";
		
		//DriverManager의 모든 메소드는 스태틱이다 따라서 new로 생성X
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public int memberInsert(MemberVO vo) {
		conn=getConnect();
		//MyBatis 프레임워크 -> SQL문을 코드에 선언하지않고 파일에 써놓고 연결해서 사용
		String SQL="insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		//위 쿼리문을 분리해서 XML 파일로 저장
		//파라미터가 있는 SQL문
		int cnt = -1; // -1(보통 실패의 의미)
		try {
			ps=conn.prepareStatement(SQL);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddr());
			ps.setDouble(4, vo.getLat());
			ps.setDouble(5, vo.getLng());
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	public ArrayList<MemberVO> memberAllList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn=getConnect();
		String SQL ="select * from tblMem order by num desc";
		try {
			ps=conn.prepareStatement(SQL);
			rs=ps.executeQuery();
			while(rs.next()) {
				int num=rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				MemberVO vo = new MemberVO(num,name,phone,addr,lat,lng);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	public int memberDelete(int num) {
		conn=getConnect();
		String SQL="delete from tblMem where num=?";
		int cnt=-1;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt = ps.executeUpdate();  //성공시 1
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	public MemberVO memberContent(int num) {
		MemberVO vo = null;
		conn=getConnect();
		String SQL = "select * from tblMem where num = ?";
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				num=rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				vo = new MemberVO(num,name,phone,addr,lat,lng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	public int memberUpdate(MemberVO vo) {
		conn=getConnect();
		String SQL = "update tblMem set phone=?, addr=? where num=?";
		int cnt=-1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1,vo.getPhone());
			ps.setString(2,vo.getAddr());
			ps.setInt(3, vo.getNum());
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return cnt;
				
	}
	
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
