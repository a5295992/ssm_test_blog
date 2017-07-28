package com.zking.commom;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Dbutils {


	public static SqlSession getSession(String resource,boolean isAutoCommit) {

		InputStream ins = Dbutils.class.getResourceAsStream(resource);

		SqlSessionFactory sqls = new SqlSessionFactoryBuilder().build(ins);

		SqlSession session;
		try {
			session = sqls.openSession(isAutoCommit);
		}finally{
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return session;
	}
	
	public static void closeSession(SqlSession session){
		
		session.close();
	}
}
