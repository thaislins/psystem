package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.pitangua.psystem.domain.Document;
import com.example.pitangua.psystem.exception.UnhandledException;

public class DocumentDAO extends GenericDAO<Document> {

	@Override
	public void insert(Document entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(Document entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Document entity) {
		// TODO Auto-generated method stub
	}

	public Integer getDocumentCount() {
		return getAllDocuments().size();
	}

	public List<Document> getAllDocuments() {
		List<Document> documents = new ArrayList<>();

		String sql = "SELECT * FROM document";
		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery();) {
			while (resultSet.next()) {
				documents.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return documents;
	}

	private Document fromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		Integer psychologist_id = rs.getInt("psychologist_id");
		Integer client_id = rs.getInt("client_id");
		Integer type = rs.getInt("type");
		Date date = rs.getDate("date");
		String text = rs.getString("text");

		return new Document(id, psychologist_id, client_id, type, date, text);
	}
}
