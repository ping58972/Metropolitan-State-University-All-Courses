#!/usr/bin/env python

import sys

current_word = None
current_year = None
price_max = float('-inf')
word = None
year = None

for line in sys.stdin:
    line = line.strip()
    (word, year, price) = line.split('\t')
    price = float(price)
    if current_word == word and current_year == year:
        current_max = max(current_max, price)
    else:
        if current_word:
            print(current_word, current_year, current_max)
        current_max = price
        current_word = word
        current_year = year

if current_word == word and current_year == year:
    print(current_word, current_year, current_max)
