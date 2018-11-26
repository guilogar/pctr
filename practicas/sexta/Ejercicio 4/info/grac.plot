set xlabel "N (números de tareas)"
set ylabel "f(n) (speed up)"

set style data linespoints

set terminal x11 1
plot "resImagen.txt" title "Secuencial", \
     "resImagenParFin.txt" title "Concurrente Hilo Fino", \
     "resImagenParGru.txt" title "Concurrente Hilo Grueso"

pause -1 "[Intro] para terminar\n"
