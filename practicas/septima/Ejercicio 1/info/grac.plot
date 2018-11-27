set xlabel "N (número de hebras)"
set ylabel "t(n) (tiempo en milisegundos)"

set style data linespoints

set terminal x11 1
plot "servidorconpool.txt" title "Servidor con Pool de Hebras", \
     "servidorsinpool.txt" title "Servidor sin Pool de Hebras"

pause -1 "[Intro] para terminar\n"
