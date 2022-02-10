from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField
# PasswordField, , BooleanField
from wtforms.validators import DataRequired, Length, EqualTo
    #, Email, EqualTo



class TestGUI(FlaskForm):
    input = StringField('Input to Guess',
                           validators=[DataRequired(), Length(min=5, max=5)])

    submit = SubmitField('Guess')