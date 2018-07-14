set xlabel "N (números de elementos)"
set ylabel "t(n) (tiempo en milisegundos)"

set style data linespoints

set terminal x11 1
plot "curva_cores.txt" title "Curva de Tareas", \
     "speed_up.txt" title "Speed Up"

pause -1 "[Intro] para terminar\n"
