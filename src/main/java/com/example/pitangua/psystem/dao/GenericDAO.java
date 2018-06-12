package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.exception.UnhandledException;

public abstract class GenericDAO<Entity> {
	
	public abstract void insert(Entity entity);

	public abstract void remove(Entity entity);

	public abstract void find(Entity entity);

	public abstract void update(Entity entity);
}
