#!/usr/bin/env python

import sys

for line in sys.stdin:
    line = line.strip()
    words = line.split(',')
    if int(words[7]) > 250000:
        print("%s\t%s\t%s" % (words[1], words[4], words[7]))
