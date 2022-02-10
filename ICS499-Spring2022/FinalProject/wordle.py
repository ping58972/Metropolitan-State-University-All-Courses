

class Wordle:
    def __init__(self, wordle, word):
        self.wordle = wordle
        self.word = word
    def result(self):
        if self.wordle == self.word:
            return True, "11111"
        ans = ""
        for i in range(len(self.wordle)):
            if (self.word[i] in self.wordle) and (self.word[i] == self.wordle[i]):
                ans += '1'
            elif (self.word[i] in self.wordle) and (self.word[i] != self.wordle[i]):
                ans += '2'
            else:
                ans += '0'
        return False, ans