# implementation of card game - Memory

import simplegui
import random
cards = []
exposed = []
state = 0
turns = 0

# helper function to initialize globals
def init():
    global cards, exposed, turns
    cards = [int(i//2) for i in range(16)]
    exposed = [False for i in range(16)]
    random.shuffle(cards)
    turns = 0
     
# define event handlers
def mouseclick(pos):
    global cards, exposed, state, last_second_card, last_card, current_card, turns
    mouse_pos = list(pos)
    tmp = mouse_pos[0]//50
    if(not exposed[tmp]):
        if state == 0:
            state = 1
            current_card = tmp
        elif state == 1:
            state = 2
            last_card = current_card
            current_card = tmp
        else:
            state = 1 
            last_second_card = last_card
            last_card = current_card
            current_card = tmp
            if (cards[last_second_card] != cards[last_card]):
                exposed[last_second_card] = False
                exposed[last_card] = False
        exposed[current_card] = True
        turns += 1
         
# cards are logically 50x100 pixels in size    
def draw(canvas):
    global cards, exposed, state, l
    x=0
    for i in range(16):
        if(exposed[i]):
            canvas.draw_text(str(cards[i]), [x+10,75], 50, "White")
        else:
            canvas.draw_polygon([(x, 0), (x+50, 0), (x+50, 100), (x,100)], 1, "Green")
        x += 50
    l.set_text("Moves =" + str(turns))


# create frame and add a button and labels
frame = simplegui.create_frame("Memory", 800, 100)
frame.add_button("Restart", init)
l=frame.add_label("Moves = 0")

# initialize global variables
init()

# register event handlers
frame.set_mouseclick_handler(mouseclick)
frame.set_draw_handler(draw)

# get things rolling
frame.start()


# Always remember to review the grading rubric