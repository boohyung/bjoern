package bjoern.pluginlib.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bjoern.nodeStore.NodeTypes;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

import bjoern.structures.edges.EdgeTypes;
import octopus.lib.structures.OctopusNode;

public class BasicBlock extends OctopusNode
{

	private List<Instruction> instructions;

	public BasicBlock(Vertex vertex)
	{
		super(vertex, NodeTypes.BASIC_BLOCK);
	}

	public List<Instruction> getInstructions()
	{
		if (instructions == null)
		{
			instructions = new ArrayList<Instruction>();
			for (Vertex instruction : getNode().getVertices(Direction.OUT,
					EdgeTypes.IS_BB_OF))
			{

				instructions.add(new Instruction(instruction));
			}
			Collections.sort(instructions);
		}
		return instructions;
	}

	public Instruction getEntry()
	{
		return getInstructions().get(0);
	}

	public Instruction getExit()
	{
		return getInstructions().get(getInstructions().size() - 1);
	}
}
