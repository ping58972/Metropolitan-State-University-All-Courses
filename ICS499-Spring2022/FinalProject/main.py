from flask import Flask, render_template, url_for, flash, redirect
from GUI import TestGUI
from wordle import Wordle

app = Flask(__name__)
app.config['SECRET_KEY'] = 'f64ff9a9e4930c4f5eee3b6147327204'

words=["world", "email", "login", "class"]
# @app.route("/")
@app.route("/", methods=['GET', 'POST'])
def index():
    gui = TestGUI()
    if gui.validate_on_submit():
        word = Wordle(words[0], gui.input.data)
        result = word.result()
        if result[0]:
            return render_template("index.html", result=result[1],  gui=gui)
        else:
            return render_template("index.html", result=result[1], gui=gui)
    return render_template("index.html", result=None, gui=gui)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    app.run(debug=True)


