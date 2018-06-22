package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import com.example.pitangua.psystem.domain.Document;

public interface IDocumentService {

	void insert(Document document) throws SQLException;

	void remove(Document document) throws SQLException;

	List<Document> getClientDocuments(int id);

	Integer getDocumentCount(int id);

	Document getByClientId(int id);

	Document getById(int id);

}
