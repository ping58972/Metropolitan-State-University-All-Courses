w_b(penguin).
w_b(human).

p_m(penguin).
p_m(human).

have_feathers(penguin).
have_hair(human).

mammal(X) :-
    w_b(X),
    p_m(X),
    have_hair(X).
    