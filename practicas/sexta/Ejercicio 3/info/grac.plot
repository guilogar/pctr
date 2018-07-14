set xlabel "N (Numero de Hebras)"
set ylabel "Speed Up = f(n) = T(1) / T(N)"

set style data linespoints

set terminal x11 1
plot "speed_up.txt" title "Speed Up"

pause -1 "[Intro] para terminar\n"
