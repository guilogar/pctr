set xlabel "N (tareas)"
set ylabel "t(n) (tiempo)"

set style data linespoints

set terminal x11 1
plot "java_tiempo.txt" title "Tiempo Java", \
     "cpp_tiempo.txt" title "Tiempo C++"

pause -1 "[Intro] para seguir\n"

set ylabel "t(n) (speed up)"
plot "java_speed_up.txt" title "Speed Up Java", \
     "cpp_speed_up.txt" title "Speed Up C++"

pause -1 "[Intro] para terminar\n"
