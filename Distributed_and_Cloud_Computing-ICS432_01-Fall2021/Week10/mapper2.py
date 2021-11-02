#!/usr/bin/env python

import sys
import re

for line in sys.stdin:
    line = line.strip().lower()
    words = line.split()
    for word in words:
        word = re.sub('[^A-Za-z0-9]+', '', word)
        if(len(word) > 0):
            print("%s\t%s" % (word, 1))
