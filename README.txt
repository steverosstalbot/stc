com.fsm.example is the main package with the finite state machines.
Program.java is a simple FSM that is controlled by a user but it does 
not communicate with anything.
SocketProgram.java is the FSM communicating FSM implementation.

The xlator stuff is all in com.fsm2Java.*.

When you export to a jar to run in a terminal window you need to do the
following:
export com.fsm.example, com.fsm.fakedns and lib for the FSM's - I use fsm.jar.

export com.fsm2Java.grammar.grammaerhandler, come.fsm2Java.grammar.workingmemory 
and lib for the xlator stuff - I use fsmxlator.jar

The shell folder has the shell scripts that I use to invoke stuff in a terminal
window. 
doall.sh takes the current SupplierInfoNoFairBeta.scr as input and generates all
that is needed to run the example as a set of communicating FSM's.
fsm.sh is invoked as follows:

	./fsm.sh role easyFSMConfig dnsConfig [client|server]
	
For example

	./fsm.sh authorisersvc authorisersvc_config.txt ipconfig.txt server
	./fsm.sh requestor requestor_config.txt ipconfig.txt client
	
And so on.

Still not generating the FSM's correctly which is in part a limitation of easyFSM.
The issue concerns ambiguity in naming states which stems from the FSM (dot file) 
generated from the scribble (see interactions between the authorisersvc and the 
filtersvc).

The running FSM will output "Command:" to indicate it is ready to move states
based on a message from another FSM.
 
The running FSM will output "Command?" when it requires you to choose. The options
are numerically encoded.
  