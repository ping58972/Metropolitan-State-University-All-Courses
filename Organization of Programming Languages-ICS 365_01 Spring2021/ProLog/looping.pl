count_to_10(10) :-
    write(10), nl.

count_to_10(X) :-
    write(X), nl,
    Y is X +1,
    count_to_10(Y).

count_down(Low, High) :-
    between(Low, High, Y),
    Z is High - Y,
    write(Z).

guess_num :- loop(start).

loop(15) :- write('You gussed it').

loop(X) :-
    X \= 15,
    write("Guess Number "),
    read(Guess),
    write(Guess),
    write(' is not the number '), nl,
    loop(Guess).

wrtie_list([]).

write_list([Head | Tail]) :-
    write(Head), nl,
    write_list(Tail).

join_str(Str1, Str2, Str3) :-
    name(Str1, StrList1),
    name(Str2, StrList2),
    append(StrList1, StrList2, StrList3),
    name(Str3, StrList3), nl.