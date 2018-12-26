package cn.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class TestJDBC {
	@Test
	public void test1() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBCPUtil.getConnection();
			ps = conn.prepareStatement("...");
			/**
			 * ÔöÉ¾¸Ä²é
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCPUtil.closeAll(null, ps, conn);
		}
	}
}
