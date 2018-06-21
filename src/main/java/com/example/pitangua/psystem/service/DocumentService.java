package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.DocumentDAO;
import com.example.pitangua.psystem.domain.Document;

@Service
public class DocumentService implements IDocumentService {

	@Autowired
	private DocumentDAO documentDAO;

	@Override
	public void insert(Document document) throws SQLException {
		documentDAO.insert(document);
	}

	@Override
	public void remove(Document document) throws SQLException {
		documentDAO.remove(document);
	}

	@Override
	public List<Document> getClientDocuments(int id) {
		return documentDAO.getClientDocuments(id);
	}

	@Override
	public Integer getDocumentCount(int id) {
		return documentDAO.getDocumentCount(id);
	}

}
