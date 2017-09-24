# Mini-project #6 - Blackjack

import simplegui
import random

# load card sprite - 949x392 - source: jfitz.com
CARD_SIZE = (73, 98)
CARD_CENTER = (36.5, 49)
card_images = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/cards.jfitz.png")

CARD_BACK_SIZE = (71, 96)
CARD_BACK_CENTER = (35.5, 48)
card_back = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/card_back.png")    

# initialize some useful global variables
in_play = False
outcome = ""
score = 0

# define globals for cards
SUITS = ('C', 'S', 'H', 'D')
RANKS = ('A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K')
VALUES = {'A':1, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9, 'T':10, 'J':10, 'Q':10, 'K':10}


# define card class
class Card:
    def __init__(self, suit, rank):
        if (suit in SUITS) and (rank in RANKS):
            self.suit = suit
            self.rank = rank
        else:
            self.suit = None
            self.rank = None
            print "Invalid card: ", suit, rank

    def __str__(self):
        return self.suit + self.rank

    def get_suit(self):
        return self.suit

    def get_rank(self):
        return self.rank

    def draw(self, canvas, pos):
        card_loc = (CARD_CENTER[0] + CARD_SIZE[0] * RANKS.index(self.rank), 
                    CARD_CENTER[1] + CARD_SIZE[1] * SUITS.index(self.suit))
        canvas.draw_image(card_images, card_loc, CARD_SIZE, [pos[0] + CARD_CENTER[0], pos[1] + CARD_CENTER[1]], CARD_SIZE)
        
# define hand class
class Hand:
    def __init__(self, cards_in_hand):
        self.cards_in_hand = cards_in_hand	# replace with your code

    def __str__(self):
        string = ""
        for c in self.cards_in_hand:
            string += "\nCard:" + str(c) + "\t\tValue:" + str(VALUES[c.get_rank()])
        return string

    def add_card(self, card):
        self.cards_in_hand.append(card)

    # count aces as 1, if the hand has an ace, then add 10 to hand value if it doesn't bust
    def get_value(self):
        aces = 0
        val = 0
        for c in self.cards_in_hand:
            val += VALUES[c.get_rank()]
            if(c.get_rank() == 'A'):
                aces += 1
        if(aces==0):
            return val
        elif(aces == 1):
            if (val + 10 <= 21):
                return val + 10
            else:
                return val
        else:
            if (val + 20 <= 21):
                return val + 20
            elif (val + 10 <= 21):
                return val + 10
            else:
                return val

    def busted(self):
        if(self.get_value() >= 21):
            return True
        return False
    
    def draw(self, canvas, p):
        cards = self.cards_in_hand[-1:-6]
        for i in cards:
            i.draw(canvas, [(p[0] + CARD_SIZE[0] + 30)*(-1 * cards.index(i)), p[1]])
 
        
# define deck class
class Deck:
    def __init__(self):
        self.cards_in_deck = []
        for s in SUITS:
            for r in RANKS:
                self.cards_in_deck.append(Card(s,r));
                
    # add cards back to deck and shuffle
    def shuffle(self):
        random.shuffle(self.cards_in_deck)

    def deal_card(self,hand):
        hand.add_card(self.cards_in_deck.pop(random.randrange(0, len(self.cards_in_deck))))
    
    def __str__(self):
        pass	
    
#define event handlers for buttons
def deal():
    global outcome, in_play, hand_player, hand_dealer, deck
    outcome = ""
    hand_player = Hand([])
    hand_dealer = Hand([])
    deck = Deck()
    hit()
    deck.deal_card(hand_dealer)
    hit()
    deck.deal_card(hand_dealer)
    in_play = True
    
def hit():
    global deck, in_play, score, outcome, hand_player
    deck.deal_card(hand_player)
    if(hand_player.get_value() > 21):
        outcome = "You have bust"
        in_play = False
        score -= 1
       
def stand():
    global hand_dealer, in_play, outcome, score, hand_player, deck
    if(hand_player.busted()):
        outcome = "You have already been bust"
    else:
        while (hand_dealer.get_value() <= 17):
            deck.deal_card(hand_dealer)
        if(hand_dealer.get_value() > 21):
            outcome = "Dealer busted"
            score += 1
            in_play = False
            return
        if(hand_player.get_value() > hand_dealer.get_value()):
            outcome = "You won"
            score += 1
            in_play = False
            return
        else:
            outcome = "You lose"
            score -= 1
            in_play = True
            return
    

# draw handler    
def draw(canvas):
    global hand_dealer, hand_player
    hand_dealer.draw(canvas, [60, 80])
    hand_player.draw(canvas, [60, 200])
    canvas.draw_text("Blackjack", [100,100],35,"Aqua")
    canvas.draw_text(outcome, [200,350],20,"Black")
    canvas.draw_text("Score "+str(score), [400,95],20,"Black")

# initialization frame
frame = simplegui.create_frame("Blackjack", 600, 600)
frame.set_canvas_background("Green")

#create buttons and canvas callback
frame.add_button("Deal", deal, 200)
frame.add_button("Hit",  hit, 200)
frame.add_button("Stand", stand, 200)
frame.set_draw_handler(draw)

# deal an initial hand
deal()

# get things rolling
frame.start()


# remember to review the gradic rubric