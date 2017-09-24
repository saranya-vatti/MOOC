# Rock-paper-scissors-lizard-Spock template


# The key idea of this program is to equate the strings
# "rock", "paper", "scissors", "lizard", "Spock" to numbers
# as follows:
#
# 0 - rock
# 1 - Spock
# 2 - paper
# 3 - lizard
# 4 - scissors

import random
# helper functions

def number_to_name(number):
    if(number==0):
        return "rock"
    elif (number==1):
        return "Spock"
    elif (number==2):
        return "paper"
    elif (number==3):
        return "lizard"
    else:
        return "scissors"

    
def name_to_number(name):
    if(name=="rock"):
        return 0
    elif(name=="Spock"):
        return 1
    elif(name=="paper"):
        return 2
    elif(name=="lizard"):
        return 3
    elif (name=="scissors"):
        return 4
    else:
        return -1


def rpsls(name): 
    player_number = name_to_number(name)
    print ("Player chooses "+ name)
    if(player_number == -1):
        print("Wrong input from player")
    else:
        comp_number = random.randrange(0,4)
        print ("Computer chooses "+ number_to_name(comp_number))
        diff = (player_number - comp_number) % 5
        if(diff==0):
            print("Player and computer tie!\n")
        elif(diff<=2):
            print("Player wins!\n")
        else:
            print("Computer wins!\n")

    
# testing the code
rpsls("rock")
rpsls("Spock")
rpsls("paper")
rpsls("lizard")
rpsls("scissors")


