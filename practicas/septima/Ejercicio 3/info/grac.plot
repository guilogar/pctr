set xlabel "N (número de intentos)"
set ylabel "t(n) (tiempo en milisegundos)"

set style data linespoints

set terminal x11 1
plot "intparalelaunicont.txt" title "Implementación con variable comun (exclusión mutua bajo cerrojo)", \
     "intparalelamulticont.txt" title "Implementación con variables locales", \
     "intparalelafuturecont.txt" title "Implementación con interfaz Callable"

pause -1 "[Intro] para terminar\n"
