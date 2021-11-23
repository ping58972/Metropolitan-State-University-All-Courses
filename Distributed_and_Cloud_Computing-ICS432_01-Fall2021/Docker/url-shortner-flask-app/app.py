from flask import Flask, render_template, request, redirect, jsonify
import json
import os.path

app = Flask(__name__)

@app.route('/simple')
def simple():
    return jsonify(messge='Hello World!')


@app.route('/parameters')
def parameters():
    name = request.args.get('name')
    age = int(request.args.get('age'))
    if age < 18:
        return jsonify(message = "Sorry " + name + ", you are not old enough"), 404
    else:
        return jsonify(message = "Welcome " + name + ", you are old enough")

@app.route('/url_variables/<string:name>/<int:age>')
def url_variables(name: str, age:int):
    if age < 18:
        return jsonify(message = "Sorry " + name + ", you are not old enough"), 404
    else:
        return jsonify(message = "Welcome " + name + ", you are old enough")


@app.route('/')
def home():
    return render_template('home.html', name='Thanaa')

@app.route('/about')
def about():
    return 'this is a url shortner'

@app.route('/your_url', methods =['GET','POST'])
def your_url():
    if request.method == 'POST':
        urls = {}
        if os.path.exists('urls.json'):
            with open('urls.json') as urls_file:
                urls = json.load(urls_file)
        urls[request.form['code']] = request.form['url']
        #Note that the user must enter a correct URL format for it to be stored
        with open('urls.json','w') as url_file:
            json.dump(urls,url_file)
        return render_template('your_url.html',code=request.form['code'])
    else:
        return redirect('/')

@app.route('/api')
def get_codes():
    urls = {}
    if os.path.exists('urls.json'):
        with open('urls.json') as urls_file:
            urls = json.load(urls_file)
        return jsonify(list(urls))
    else:
        return 'there are no urls stored'

if __name__ == "__main__":
    app.run()
