likes(dan, sally).
likes(sally, dan).
likes(josh, brittney).

dating(X, Y) :-
likes(X, Y),
likes(Y, X).

friendship(X, Y) :-
likes(X, Y);
likes(Y, X).


/*weather(City, Season, Temp).

# weather(phoenix, summer, hot).
# weather(la, summer, warm).
# weather(phoenix, winter, warm).
*/

weather(phoenix, hot, summer).
weather(la, warm, summer).

warmer_then(C1, C2) :-
    weather(C1, hot, summer),
    weather(C2, warm, summer),
    write(C1), write(' is warmer than '), write(C2), nl.