Problem:


1. (60%) Write a singly linked list java class yourself, and implement the following methods: 

a) Remove all elements from a linked list of a specific value val.
     Example input: 1 2 3 5 6 3 4 3, val = 3
  Example output: 1 2 5 6 4

b) Determine whether there is a cycle in the linked list. For example, your method should return true for the following linked list.
 
c) Reverse the linked list with recursion.
    Example input: 1 2 3 4 5 6
 Example output: 6 5 4 3 2 1

d) Find a node that two linked lists intersects with each other
     Example input: a1 a2 a3 c1 c2 c3; b1 b2 c1 c2 c3
  Example output: c1

2. (40%) War is a children's card game played in many parts of the world. No strategy is involved - simply the ability to recognize which of two cards is higher in rank, and to follow the procedure of the game. In the basic game there are two players, player 1 and player 2. They use a standard pack of 52 cards. Cards are alternatively dealt to the two players so that each player gets 26 cards (first card is always dealt to player 1). Players do not look at their cards, but keep them in a pile face down. The object of the game is to win all the cards.
In each round, both players turn their top cards face up and place them on the table. The player who has the higher card takes both cards and adds them (face down) to the bottom of her pile. Then both players turn up their new top card and so on. If the turned up cards have equal values then there is a war and the round continues. The tied cards stay on the table and both players play the next card of their pile face down and then another card face-up. Again, the player who has the higher of the new face-up cards wins the war and adds all six cards face-down to the bottom of her pile. If the new face-up cards are equal the war continues: each player puts another card face-down and one face-up. The war goes on like this as long as the face-up cards continue to be equal. As soon as they are different the player with the higher card wins all the cards in the war and the round ends. The cards are always added to the bottom of the winner's pile in the exact order in which they were played: the first card of first player, then the first card of second player, then the second card of first player, and so on. The game continues until one player has all the cards and wins. If one player runs out of cards in the middle of a war, the other player automatically wins. If both players run out of cards at the same time, then the game is a draw.
A game of war can take a long time to finish, and your task is to simulate the game to determine the winner and the total number of rounds played.
Write a java program to simulate the game. You should use write your own java classes. Using any of the classes in the Java collections framework is not allowed.
Input: You should read from the standard input several lines, each containing an initial card deck. Since card suits are ignored in the game, only the 52 card values are specified, in the order in which they are dealt to the two players. Note that cards dealt first end up at the bottom of the player piles, not at the top.
Output: For each input line you must print one of the following messages: "X wins after Y rounds" or "draw after X rounds". If the game lasts more than 10000 rounds then print "draw after 10000 rounds".
Sample Input
2 2 2 2 3 3 3 3 4 4 4 4 5 5 5 5 6 6 6 6 7 7 7 7 8 8 8 8 9 9 9 9 10 10 10 10 J J J J Q Q Q Q K K K K A A A A 
2 8 2 8 2 9 2 9 3 9 3 9 3 10 3 10 4 10 4 10 4 J 4 J 5 J 5 J 5 Q 5 Q 6 Q 6 Q 6 K 6 K 7 K 7 K 7 A 7 A 8 A 8 A 
6 4 5 7 2 3 10 5 2 4 J 7 3 Q 10 J A A K 8 2 2 3 3 9 9 4 9 8 A Q K Q J A 9 8 6 8 10 6 10 5 Q 7 4 6 K 7 K J 5 
2 5 7 Q 9 7 A 3 J K 9 Q 10 A 10 8 9 6 4 2 8 K Q 3 5 9 6 3 A J Q 10 5 6 2 10 4 3 4 4 A J 7 J K 6 5 8 7 8 2 K 
6 3 9 6 4 Q 4 5 8 A Q 2 3 8 3 4 9 2 8 7 10 J K Q J 5 3 A 10 A 6 5 8 2 J 10 9 K 4 Q 7 7 A 6 10 2 5 7 J K K 9
Expected output for the sample input:
draw after 1 rounds
2 wins after 26 rounds
2 wins after 921 rounds
1 wins after 440 rounds
draw after 10000 rounds





