package requests;

import java.io.Serializable;

import entities.users.User;

public class EntityRequestWithId<T> implements Serializable
{
	private int entityId;
	private T entity;
	private User user;

	private static final long serialVersionUID = -5169005239090688111L;

	public int getEntityId()
	{
		return entityId;
	}

	public void setEntityId(int entityId)
	{
		this.entityId = entityId;
	}

	public T getEntity()
	{
		return entity;
	}

	public void setEntity(T updatedEntity)
	{
		this.entity = updatedEntity;
	}

	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
}
