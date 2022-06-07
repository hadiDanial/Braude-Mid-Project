package requests;

import java.io.Serializable;

import entities.users.User;

public class EntityRequestWithId<T> implements Serializable
{
	private int entityId;
	private T entity;
	private User user;

	private static final long serialVersionUID = -5169005239090688111L;

	
	/** 
	 * @return int
	 */
	public int getEntityId()
	{
		return entityId;
	}

	
	/** 
	 * @param entityId
	 */
	public void setEntityId(int entityId)
	{
		this.entityId = entityId;
	}

	
	/** 
	 * @return T
	 */
	public T getEntity()
	{
		return entity;
	}

	
	/** 
	 * @param updatedEntity
	 */
	public void setEntity(T updatedEntity)
	{
		this.entity = updatedEntity;
	}

	
	/** 
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}
	
	/** 
	 * @param user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
}
