for i in loginsvc  requestor  authorisersvc  filtersvc  suppliersvc  contractsvc
#for i in C P
#for i in C S
#for i in A B
do
	echo Doing $i
	./scribblec.sh SupplierInfoNoFairBeta.scr -fsm PartnershipSupplier $i >$i.fsm
	#./scribblec.sh Nego3.scr -fsm Negotiate $i >$i.fsm
	#./scribblec.sh Fib.scr -fsm Adder $i >$i.fsm
	#./scribblec.sh Fibo.scr -fsm Fibonacci $i >$i.fsm
	./fsmxlator.sh ${i}.fsm -easyFSM ${i} org.tw.scribble.example >${i}_config.txt
	./fsmxlator.sh ${i}.fsm -dot ${i} org.tw.scribble.example >${i}_gen.fsm
	dot -Tpng ${i}_gen.fsm >${i}_gen.png
done
