/*[rob, john, lisa, conner].
[josh, jake, eat(cheese)].

[Head|Tail] = [josh, jake, eat(cheese)].*/

/*member(X, [X|T]).

member(X, [H|T]) :-
    member(X, T).*/

/*[1, 2, 3, 4, 5]*/
/*member(X, [23,25,67,12,25,19,9,6]), Y is X*X, Y<400.*/

change(H, Q, D, N, P) :-
    member(H, [0,1,2]),
    member(Q, [0,1,2,3,4]),
    member(D, [0,1,2,3,4,5,6,7,8,9,10]),
    member(N, [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]),
    
  S is 50*H + 25*Q + 10*D + 5*N,
  S =< 100,
  P is 100-S.
