package ex02;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MemberDAO {

/*    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String user = "c##scott";
    private static final String pwd = "tiger";*/

    private PreparedStatement pstmt;
    private Connection con;
    private DataSource dataFactory;

    public MemberDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (NamingException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<MemberVO> listMembers() {
        List<MemberVO> list = new ArrayList<MemberVO>();
        try {
            //connDB();
            con = dataFactory.getConnection();
            String query = "select * from t_member ";
            System.out.println("pstmt " + query);
            pstmt = con.prepareStatement(query);
            System.out.println(query);
            ResultSet rs = pstmt.executeQuery();

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

    public void addMember(MemberVO memberVO) {
        try {
            con = dataFactory.getConnection();
            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            String query = "insert into t_member";
            query += " (id,pwd,name,email)";
            query += " values(?,?,?,?)";
            System.out.println("prepareStatememt: " + query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delMember(String id) {
        try {
            con = dataFactory.getConnection();
            String query = "delete from t_member" + " where id=?";
            System.out.println("prepareStatememt:" + query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*    private void connDB() {
        try {
            Class.forName(driver);
            System.out.println("Oracle Driver 로딩 성공");
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("connection 연결 성공");
            stmt = con.createStatement();
            System.out.println("statement 생성 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
