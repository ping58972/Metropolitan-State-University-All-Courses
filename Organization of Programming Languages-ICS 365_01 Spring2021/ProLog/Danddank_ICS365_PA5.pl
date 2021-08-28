/*
Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
Email: nalongsone.danddank@my.metrostate.edu
ICS 365. Organization of Programming Languages. 
Programming Assignment 5
*/

/*Question #1
Given two medications, if they interact, tell what the effect of the interaction may be. 
Note there may be more than one result of the interaction.
*/
interaction_effect(Base_Drug, Interacts_Drug) :-
    findall(Effect, interacts(Base_Drug, Interacts_Drug, Effect), Result_List),
    write('The effect list of the interaction: '),
    write(Result_List), nl.

/*Question #2
Given a medical condition, list the drug interactions that may have caused it.
*/
list_drug_interactions(Medical_Condition) :-
    findall(Interacts_Drug, interacts(_, Interacts_Drug, Medical_Condition), L),
    sort(L, Result_List),
    write('The list of the drug interactions: '),
    write(Result_List), nl.

/*Question #3
Given a drug, list the drugs, which have no interactions with it.
*/
list_no_interacts_drug(Drug) :-
    findall(Interacts_Drug, interacts(Drug, Interacts_Drug, _), L),
    sort(L, Uniq_L),
    findall(All_Interacts_Drug, interacts(_, All_Interacts_Drug, _), All_L),
    sort(All_L, Uniq_All_L),
    subtract(Uniq_All_L, Uniq_L, Result_List),
    format('The list of the drug with no interactions with ~w is: ', Drug),
    write(Result_List), nl.

/*Question #4
Given a drug, list the drugs, which do interact with it.
*/
list_interacts_drug(Drug) :-
    findall(Interacts_Drug, interacts(Drug, Interacts_Drug, _), L),
    sort(L, Result_L),
    format('The list of the drug with interactions with ~w is: ', Drug),
    write(Result_L), nl.
