package com.example.pitangua.psystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericDAO<Entity> {

	public abstract void insert(Entity entity) throws SQLException;

	public abstract void remove(Entity entity) throws SQLException;

	public abstract void update(Entity entity) throws SQLException;

	protected PreparedStatement createPreparedStatement(Connection conn, String sql, String param) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, param);
		return ps;
	}

	protected PreparedStatement createPreparedStatement(Connection conn, String sql, int param) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, param);
		return ps;
	}
}
