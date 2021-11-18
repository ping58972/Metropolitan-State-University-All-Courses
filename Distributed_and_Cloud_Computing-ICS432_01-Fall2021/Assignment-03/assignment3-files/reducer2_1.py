#!/usr/bin/env python

import sys

current_id = None
id = None

for line in sys.stdin:
    line = line.strip()
    (id, desc, price) = line.split('\t')
    if current_id == id:
        continue
    else:
        current_id = id
        print(id, desc, price)
