package requests;

import java.io.Serializable;

public class EntityRequestWithId<T extends Serializable> implements Serializable
{
	private int entityId;
	private T entity;
	
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
}
