# Implementation of classic arcade game Pong

import simplegui
import random

# initialize globals - pos and vel encode vertical info for paddles
WIDTH = 600
HEIGHT = 400       
BALL_RADIUS = 20
PAD_WIDTH = 8
PAD_HEIGHT = 80
HALF_PAD_WIDTH = PAD_WIDTH / 2
HALF_PAD_HEIGHT = PAD_HEIGHT / 2

# helper function that spawns a ball, returns a position vector and a velocity vector
# if right is True, spawn to the right, else spawn to the left
def ball_init(right):
    global ball_pos, ball_vel # these are vectors stored as lists
    ball_pos = [WIDTH / 2, HEIGHT / 2]
    ran = [random.randrange(120, 240)/100,-random.randrange(60, 180)/100]
    if(right == True):
        ball_vel = [ran[0],ran[1]]
    else:
        ball_vel = [-ran[0],ran[1]]

# define event handlers
def init():
    global paddle1_pos, paddle2_pos, paddle1_vel, paddle2_vel  # these are floats
    global score1, score2  # these are ints
    paddle1_pos = [HALF_PAD_WIDTH , HEIGHT/2]
    paddle2_pos = [HALF_PAD_WIDTH , HEIGHT/2]
    paddle1_vel = 0
    paddle2_vel = 0
    score1 = 0
    score2 = 0
    ball_init(False)

def draw(c):
    global score1, score2, paddle1_pos, paddle2_pos, ball_pos, ball_vel
 
    # update paddle's vertical position, keep paddle on the screen
    paddle1_pos[1] += paddle1_vel
    paddle2_pos[1] += paddle2_vel
    if paddle1_pos[1] <= HALF_PAD_HEIGHT:
        paddle1_pos[1] = HALF_PAD_HEIGHT
    if paddle2_pos[1] <= HALF_PAD_HEIGHT:
        paddle2_pos[1] = HALF_PAD_HEIGHT
    if paddle1_pos[1] >= HEIGHT - HALF_PAD_HEIGHT:
        paddle1_pos[1] = HEIGHT - HALF_PAD_HEIGHT
    if paddle2_pos[1] >= HEIGHT - HALF_PAD_HEIGHT:
        paddle2_pos[1] = HEIGHT - HALF_PAD_HEIGHT
    # draw mid line and gutters
    c.draw_line([WIDTH / 2, 0],[WIDTH / 2, HEIGHT], 1, "White")
    c.draw_line([PAD_WIDTH, 0],[PAD_WIDTH, HEIGHT], 1, "White")
    c.draw_line([WIDTH - PAD_WIDTH, 0],[WIDTH - PAD_WIDTH, HEIGHT], 1, "White")
    
    # draw paddles
    c.draw_polygon([[0, paddle1_pos[1] - HALF_PAD_HEIGHT], [PAD_WIDTH, paddle1_pos[1] - HALF_PAD_HEIGHT], [PAD_WIDTH, paddle1_pos[1] + HALF_PAD_HEIGHT], [0, paddle1_pos[1] + HALF_PAD_HEIGHT]], 2, 
          "White", "White")
    c.draw_polygon([[WIDTH - PAD_WIDTH, paddle2_pos[1] - HALF_PAD_HEIGHT], [WIDTH, paddle2_pos[1] - HALF_PAD_HEIGHT], [WIDTH, paddle2_pos[1] + HALF_PAD_HEIGHT], [WIDTH - PAD_WIDTH,  paddle2_pos[1] + HALF_PAD_HEIGHT]], 2, 
          "White", "White")
    # update ball
    ball_pos[0] += ball_vel[0]
    ball_pos[1] += ball_vel[1]
    if ball_pos[1] <= BALL_RADIUS:
        ball_vel[1] = - ball_vel[1]
    elif ball_pos[1] >= HEIGHT - BALL_RADIUS:
        ball_vel[1] = - ball_vel[1]
    if ball_pos[0] <= PAD_WIDTH + BALL_RADIUS:
        if (ball_pos[1] >= paddle1_pos[1] - BALL_RADIUS - HALF_PAD_HEIGHT) and (ball_pos[1] <= paddle1_pos[1] + HALF_PAD_HEIGHT + BALL_RADIUS):
            ball_vel[0] = - ball_vel[0]
            ball_vel[0] += (10/100)*ball_vel[0]
            ball_vel[1] += (10/100)*ball_vel[1]
        else:
            ball_init(True)
            score2 +=1
    elif ball_pos[0] >= WIDTH - PAD_WIDTH - BALL_RADIUS:
        if (ball_pos[1] >= paddle2_pos[1] - BALL_RADIUS - HALF_PAD_HEIGHT) and (ball_pos[1] <= paddle2_pos[1] + HALF_PAD_HEIGHT + BALL_RADIUS):
            ball_vel[0] = - ball_vel[0]
            ball_vel[0] += (10/100)*ball_vel[0]
            ball_vel[1] += (10/100)*ball_vel[1]
        else:
            ball_init(False)
            score1 += 1
    # draw ball and scores
    c.draw_circle(ball_pos, BALL_RADIUS, 2, "White", "White")
    c.draw_text(str(score1), [150, 25], 20, "White")
    c.draw_text(str(score2), [450, 25], 20, "White")

def keydown(key):
    global paddle1_vel, paddle2_vel
    if key==simplegui.KEY_MAP["up"]:
        paddle2_vel -= 6
    elif key==simplegui.KEY_MAP["down"]:
        paddle2_vel += 6
    elif key==simplegui.KEY_MAP["w"]:
        paddle1_vel -= 6
    elif key==simplegui.KEY_MAP["s"]:
        paddle1_vel += 6
        
def keyup(key):
    global paddle1_vel, paddle2_vel
    paddle1_vel = 0
    paddle2_vel = 0


# create frame
frame = simplegui.create_frame("Pong", WIDTH, HEIGHT)
frame.set_draw_handler(draw)
frame.set_keydown_handler(keydown)
frame.set_keyup_handler(keyup)
frame.add_button("Restart", init, 100)


# start frame
init()
frame.start()