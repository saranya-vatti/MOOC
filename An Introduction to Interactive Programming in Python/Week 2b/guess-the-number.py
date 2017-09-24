import simplegui
import random
import math

# low and high contain the range from where the secret number will be chosen
low = 0
high = 100

# These following variables are randomly initialized to have global scope. This will be overwritten when the game starts. [init() function]
secret_number = random.randrange(low, high)
counter = math.ceil ( math.log (high - low + 1 , 2) )
guess = -1

# event handlers for control panel
def init():
    # high and low have already been defined before entering this function
    global secret_number,low,high
    # now we can initialize secret_number and counter based on high and low
    secret_number = random.randrange(low, high)
    counter = math.ceil ( math.log (high - low + 1 , 2) )
    # Uncomment the following line for testing
    #print "secret no: " , secret_number
    print "New Game. Range is from ", low, " to ", high
    print "Number of remaining guesses is " , counter
    print
    
    
def range100():
    # button that changes range to range [0,100) and restarts
    global low,high
    low = 0
    high = 100
    init()

def range1000():
    # button that changes range to range [0,1000) and restarts
    global low,high
    low = 0
    high = 1000
    init()
    
def get_input(guess):
    global counter,secret_number,low,high,low_guess,high_guess
    guess = int(guess)
    #To print your input from the text box
    print "Guess was ", guess 
    if ((guess < secret_number) and (counter > 1)):
        print "Higher!"
        counter = counter - 1
        print "Number of remaining guesses is" , counter
        print
    elif ((guess > secret_number) and counter > 1):
        print "Lower!"
        counter = counter - 1
        print "Number of remaining guesses is" , counter
        print
    elif ((counter == 0) and (guess != secret_number)):
        print "You ran out of guesses. The number was", secret_number
        print
        init() #Game continues after player loses
    else:
        print "Correct!"
        print
        init()  #Game continues after player wins  
    
# creating frame
frame = simplegui.create_frame("Guess the Number", 200, 200)

# registering event handlers for control elements
button_range_1000 = frame.add_button("Range: 0 - 1000", range1000, 200)
button_range_100 = frame.add_button("Range: 0 - 100", range100, 200)
restart_game = frame.add_button("Restart game", init, 200)
guess = frame.add_input("Guess number", get_input, 200)

    
# frame start. Good Luck.
frame.start() 
init() #Game starts as soon as you press run