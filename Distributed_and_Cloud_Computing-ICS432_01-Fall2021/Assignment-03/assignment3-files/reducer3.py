#!/usr/bin/env python

import sys

current_word = None
current_max = float('-inf')
word = None

for line in sys.stdin:
    line = line.strip()
    (word, price, volume) = line.split('\t')
    price = float(price)
    if current_word == word:
        current_max = max(current_max, price)
    else:
        if current_word:
            print(current_word, current_max, volume)
        current_max = price
        current_word = word

if current_word == word:
    print(current_word, current_max, volume)
