parent(albert, bob).
parent(albert, betsy).
parent(albert, bill).

parent(alice, bob).
parent(alice, betsy).
parent(alice, bill).

parent(bob, carl).
parent(bob, charlie).

related(X, Y) :-
    parent(X, Y).

related(X, Y) :-
    parent(X, Z),
    related(Z, Y).

double_digit(X, Y) :-
    Y is X*2.

say_hi :-
    write('what is your name? '),
    read(X),
    format('Hi ~w!', [X]).

fav_char :-
    write('what is your fav character?'),
    get(X),
    format('The Ascii value ~w is ', [X]),
    put(X), nl.
