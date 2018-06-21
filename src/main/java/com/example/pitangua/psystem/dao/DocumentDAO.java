package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.Document;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class DocumentDAO extends GenericDAO<Document> {

	@Override
	public void insert(Document entity) throws SQLException {
		String sql = "INSERT INTO document(psychologist_id, client_id, type, date, text) VALUES(?, ?, ?, ?, ?)";
		LocalDateTime date = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setInt(1, entity.getPsychologistId());
			ps.setInt(2, entity.getClientId());
			ps.setInt(3, entity.getType());
			date = LocalDateTime.parse(entity.getDate(), dateFormat);
			ps.setTimestamp(4, Timestamp.valueOf(date));
			ps.setString(5, entity.getText());
			ps.execute();
		}
	}

	@Override
	public void remove(Document entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Document entity) {
		// TODO Auto-generated method stub
	}

	public Integer getDocumentCount(int psychologistId) {
		int count = 0;
		String sql = "SELECT count(*) FROM document WHERE psychologist_id=?;";
		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, psychologistId);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				count = resultSet.getInt("count(*)");
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return count;
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

	public List<Document> getClientDocuments(int clientId) {
		List<Document> documents = new ArrayList<>();

		String sql = "SELECT * FROM document d WHERE EXISTS (SELECT * FROM client c WHERE c.id=d.client_id AND d.client_id=?)";
		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, clientId);
				ResultSet resultSet = ps.executeQuery()) {
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
		String date = rs.getString("date");
		String text = rs.getString("text");

		return new Document(id, psychologist_id, client_id, type, date, text);
	}
}
