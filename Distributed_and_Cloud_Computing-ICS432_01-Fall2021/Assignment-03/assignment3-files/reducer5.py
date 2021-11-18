#!/usr/bin/env python

import sys

key_ = "1"
count_ = 0

for line in sys.stdin:
    line = line.strip()
    (key, value) = line.split('\t')
    count = int(value)
    count_ += count

print(key_, count_)
