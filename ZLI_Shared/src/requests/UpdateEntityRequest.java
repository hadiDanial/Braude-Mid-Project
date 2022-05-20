package requests;

import java.io.Serializable;

public class UpdateEntityRequest<T extends Serializable> implements Serializable
{
	private int entityId;
	private T updatedEntity;
	
	private static final long serialVersionUID = -5169005239090688111L;

	public int getEntityId()
	{
		return entityId;
	}

	public void setEntityId(int entityId)
	{
		this.entityId = entityId;
	}

	public T getUpdatedEntity()
	{
		return updatedEntity;
	}

	public void setUpdatedEntity(T updatedEntity)
	{
		this.updatedEntity = updatedEntity;
	}
}
