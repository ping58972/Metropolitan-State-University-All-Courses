course(cse110, mon, wed, 11, 12, 11, 12, holton, bryce, coor105).

course(
    cse340,
    day(tues, thurs),
    time(1200, 1300),
    instructor(bryce, holton),
    coor300
).
course(
    cse340,
    day(tues, thurs),
    time(1200, 1300),
    instructor(john, johnson),
    coor500
).

instructor(Instructor, Class) :-
    course(Class,
            _,
            _,
            Instructor,
            _
    ).