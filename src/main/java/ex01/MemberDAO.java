package ex01;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MemberDAO {

    private PreparedStatement pstmt;
    private Connection con;
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String user = "c##scott";
    private static final String pwd = "tiger";

    public List<MemberVO> listMembers() {
        List<MemberVO> list = new ArrayList<MemberVO>();
        try {
            connDB();
            String query = "select * from t_member ";
            System.out.println(query);
            pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery(query);

            while (rs.next()) {
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("joinDate");

                MemberVO memberVO = new MemberVO();
                memberVO.setId(id);
                memberVO.setPwd(pwd);
                memberVO.setName(name);
                memberVO.setEmail(email);
                memberVO.setJoinDate(joinDate);
                list.add(memberVO);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void connDB() {
        try {
            Class.forName(driver);
            System.out.println("Oracle Driver 로딩 성공");
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("connection 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
