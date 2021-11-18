#!/usr/bin/env python

import sys
from datetime import datetime

for line in sys.stdin:
    line = line.strip()
    words = line.split(',')
    date = datetime.strptime(words[2], "%m/%d/%Y")
    year = date.strftime('%Y')
    print("%s\t%s\t%s" % (words[1], year, words[4]))
