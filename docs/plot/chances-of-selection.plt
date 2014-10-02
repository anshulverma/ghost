#!/usr/local/bin/gnuplot
#
# A exponentially increasing function between [0, 1]

unset log
unset label
set output "chances-of-selection.png"
set xtic auto
set ytic auto
set terminal png enhanced font 'Verdana,10'
set border linewidth 1.5
set style line 1 linecolor rgb '#0060ad' linetype 1 linewidth 2
set style line 2 linecolor rgb '#dd181f' linetype 1 linewidth 2
set style line 3 linecolor rgb '#18dd1f' linetype 1 linewidth 2
set title "chances of selection of next letter based on winning probability"
set xlabel "probability that opponent will win"
set ylabel "probability of selection"

fn(x, df) = (exp(x) * (x ** df))
A = 0; B = 1
scale(x, df) = fn(0, df) - (fn(0, df) - fn(1, df)) * ((fn(x, df) - A) / (B - A))
Ps(x, df) = (scale(x, df) / scale(1, df))

set xrange [0:1]
plot Ps(x, 1) title 'easy P_s(x)' with lines linestyle 3,\
     Ps(x, 3) title 'medium P_s(x)' with lines linestyle 1,\
     Ps(x, 10) title 'hard P_s(x)' with lines linestyle 2
