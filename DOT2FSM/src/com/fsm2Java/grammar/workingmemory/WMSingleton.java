package com.fsm2Java.grammar.workingmemory;

import java.util.ArrayList;
import java.util.HashMap;

public class WMSingleton {

	static MemoryModel	m_workingMemory = null;
	
	private WMSingleton()
	{

	}
	
	static public MemoryModel getWorkingMemory()
	{
		if (m_workingMemory == null)
			m_workingMemory = new MemoryModel();
		return m_workingMemory;
	}
}
