package bjoern.pluginlib.plugintypes;

import bjoern.pluginlib.connectors.BjoernProjectConnector;
import octopus.lib.plugintypes.OctopusProjectPlugin;

public class BjoernProjectPlugin extends OctopusProjectPlugin {

	public BjoernProjectPlugin()
	{
		setProjectConnector(new BjoernProjectConnector());
	}
}
