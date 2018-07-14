set xlabel "N (números de elementos)"
set ylabel "t(n) (tiempo en milisegundos)"

set style data linespoints

set terminal x11 1
plot "matVectorSec.txt" title "matVector Secuencial", \
     "matVectorConcurrente.txt" title "matVector Cocurrente", \
     "matProd.txt" title "matProd Secuencial", \
     "matProdConcurrente.txt" title "matProd Concurrente"

pause -1 "[Intro] para terminar\n"
