package com.example.pitangua.psystem.dao;

public abstract class GenericDAO<Entity> {
	public abstract void insert(Entity entity);

	public abstract void remove(Entity entity);

	public abstract void find(Entity entity);

	public abstract void update(Entity entity);
}
