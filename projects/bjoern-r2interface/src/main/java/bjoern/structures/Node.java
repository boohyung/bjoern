package bjoern.structures;

import java.util.HashMap;
import java.util.Map;

public abstract class Node
{
	private long address;
	private String type;
	private String comment;
	private NodeKey nodeKey;

	public Node(long address, String type)
	{
		setAddr(address);
		setType(type);
	}

	private void setAddr(long addr)
	{
		address = addr;
	}

	public Long getAddress()
	{
		return address;
	}

	public String getAddressAsHexString()
	{
		return Long.toHexString(getAddress());
	}

	public String getType()
	{
		return type;
	}

	private void setType(String type)
	{
		this.type = type;
	}


	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public NodeKey createKey()
	{
		if (nodeKey == null)
		{
			nodeKey = new NodeKey(getAddress(), getType());
		}
		return nodeKey;
	}

	public String getKey()
	{
		return getType() + "_" + getAddressAsHexString();
	}

	public Map<String, Object> getProperties()
	{
		Map<String, Object> properties = new HashMap<>();
		properties.put(BjoernNodeProperties.KEY, getKey());
		properties.put(BjoernNodeProperties.TYPE, getType());
		properties.put(BjoernNodeProperties.ADDR, getAddressAsHexString());
		properties.put(BjoernNodeProperties.COMMENT, getComment());
		return properties;
	}

	@Override
	public String toString()
	{
		return getKey();
	}
}
