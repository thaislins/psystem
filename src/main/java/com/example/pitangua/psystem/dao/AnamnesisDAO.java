package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.Anamnesis;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class AnamnesisDAO extends GenericDAO<Anamnesis> {

	@Override
	public void insert(Anamnesis entity) throws SQLException {
		upsert(entity);
	}

	@Override
	public void remove(Anamnesis entity) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Anamnesis entity) throws SQLException {
		upsert(entity);
	}

	private void upsert(Anamnesis entity) throws SQLException {
		String sql = "INSERT INTO anamnesis(client_id, date, humor, perception, desire, information, language, appeareance, thought, affection)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY" +
				" UPDATE date=?, humor=?, perception=?, desire=?, information=?, language=?, appeareance=?, thought=?, affection=?";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setInt(1, entity.getClientId());
			ps.setString(2, entity.getDate());
			ps.setString(3, entity.getHumor());
			ps.setString(4, entity.getPerception());
			ps.setString(5, entity.getDesire());
			ps.setString(6, entity.getInformation());
			ps.setString(7, entity.getLanguage());
			ps.setString(8, entity.getAppeareance());
			ps.setString(9, entity.getThought());
			ps.setString(10, entity.getAffection());
			ps.setString(11, entity.getDate());
			ps.setString(12, entity.getHumor());
			ps.setString(13, entity.getPerception());
			ps.setString(14, entity.getDesire());
			ps.setString(15, entity.getInformation());
			ps.setString(16, entity.getLanguage());
			ps.setString(17, entity.getAppeareance());
			ps.setString(18, entity.getThought());
			ps.setString(19, entity.getAffection());
			ps.execute();
		}
	}

	public Anamnesis getByClientId(int id) {
		String sql = "SELECT * FROM anamnesis WHERE client_id=?";

		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, id);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return null;
	}

	private Anamnesis fromResultSet(ResultSet rs) throws SQLException {
		Integer clientId = rs.getInt("client_id");
		String date = rs.getString("date");
		String humor = rs.getString("humor");
		String perception = rs.getString("perception");
		String desire = rs.getString("desire");
		String information = rs.getString("information");
		String language = rs.getString("language");
		String appeareance = rs.getString("appeareance");
		String thought = rs.getString("thought");
		String affection = rs.getString("affection");

		return new Anamnesis(clientId, date, humor, perception, desire, information, language, appeareance, thought,
				affection);
	}

}
