# template for "Stopwatch: The Game"
import simplegui

# define global variables
time = 0
text = "str"

# define helper function format that converts integer
# counting tenths of seconds into formatted string A:BC.D
def format(t):
    global text
    D = int(t% 10)
    D = str(D)
    BC = int(t%60)
    if ( int(BC / 10) == 0):
        BC = "0" + str(BC)
    else:
        BC = str(BC)
    A = int(t/3600)
    text = str(A) + ":" + BC + "." + D + "abc" + str(t)
    
# define event handlers for buttons; "Start", "Stop", "Reset"
def start_game():
    global time
    time = 0
    timer.start()
    format(time)
    
def stop_game():
    timer.stop()
    
def reset_game():
    global time
    timer.stop()
    time = 0
    format(time)
    
# define event handler for timer with 0.1 sec interval
def tick():
    global time
    time = time + 1
    format(time)
    
def draw(canvas):
    canvas.draw_text(text, [50, 50] , 36, "Red")
    
# create frame
frame = simplegui.create_frame("Stopwatch", 500, 500)

# register event handlers
start_btn = frame.add_button("Start", start_game, 200)
stop_btn = frame.add_button("Stop", stop_game, 200)
reset_btn = frame.add_button("Reset Game", reset_game, 200)
timer = simplegui.create_timer(100, tick)
frame.set_draw_handler(draw)

# start timer and frame
frame.start()
timer.start()

# remember to review the grading rubric