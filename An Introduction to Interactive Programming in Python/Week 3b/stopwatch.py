#####################
# Example of event-driven code, buggy version

import simplegui

size = 10
radius = 10

width_size = 5
width = 5

# Define event handlers.

def incr_button_handler():
    """Increment the size."""
    global size
    size += 1
    label.set_text("Value: " + str(size))
    
def decr_button_handler():
    """Decrement the size."""
    global size
    # Insert check that size > 0, to make sure it never goes negative.
    if(size>0 ):
        size -= 1
        label.set_text("Value: " + str(size))

def incr_w_button_handler():
    """Increment the width_size."""
    global width_size
    width_size += 1
    
def decr_w_button_handler():
    """Decrement the width_size."""
    global width_size
    # Insert check that width_size > 0, to make sure it never goes negative.
    if(width_size>0 ):
        width_size -= 1
        
def change_circle_handler():
    """Change the circle radius."""
    global radius
    radius = size
    # Insert code to make radius label change.
    radiuslabel.set_text("Radius: " + str(radius))
    
def change_circle_handler_w():
    """Change the circle radius."""
    global width
    width = size
    
def draw_handler(canvas):
    """Draw the circle."""
    canvas.draw_circle((100, 100), radius, width, "Red")

# Create a frame and assign callbacks to event handlers.

frame = simplegui.create_frame("Home", 500, 500)
label = frame.add_label("Value: " + str(size))
frame.add_button("Increase", incr_button_handler)
frame.add_button("Decrease", decr_button_handler)
radiuslabel = frame.add_label("Radius: " + str(radius))
frame.add_button("Change circle", change_circle_handler)

frame.add_button("Increase width", incr_w_button_handler)
frame.add_button("Decrease width", decr_w_button_handler)
frame.add_button("Change circle-width", change_circle_handler_w)
frame.set_draw_handler(draw_handler)

# Start the frame animation

frame.start()