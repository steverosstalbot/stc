PROTOCOL=$1;shift
SCRIBBLE=$1;shift
PACKAGE=$1;shift
FOLDER=$1;shift
rm -rf ${FOLDER}
mkdir ${FOLDER}
for i in $@
do
	echo projecting out $i from the $PROTOCOL protocol in $SCRIBBLE into an fsm
	./scribblec.sh $SCRIBBLE -fsm $PROTOCOL $i >${FOLDER}/$i.fsm
	echo transforming the fsm for $i into an easyFSM config file
	./fsmxlator.sh ${FOLDER}/${i}.fsm -easyFSM ${i} $PACKAGE >${FOLDER}/${i}_config.txt
	echo transforming the fsm for $i into prettier version
	./fsmxlator.sh ${FOLDER}/${i}.fsm -dot ${i} $PACKAGE >${FOLDER}/${i}_gen.fsm
	echo transforming the prettier version of $i into a png
	dot -Tpng ${FOLDER}/${i}_gen.fsm >${FOLDER}/${i}_gen.png
done
