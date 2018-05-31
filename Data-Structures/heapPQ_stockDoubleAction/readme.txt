Background

Stock trading is done using a so-called double auction system in which buyers and sellers can
enter competitive orders simultaneously. The orders are of the form "buy m shares at $x each"
or "sell n shares at $y each". Since the number of orders is very large, a computer program
must be used to continuously match orders and to store unmatched orders for subsequent
processing. In this project you are asked to write a Java program performing order matching
according to the rules of a double auction system.

The double action system operates as follows. When a new buy order of $x per share arrives, it
is simply stored for future use if none of the stored sell orders has an asking price lower than or
equal to $x per share. If at least one of the stored sell orders has an asking price of $x per
share or less, then the new buy order is matched with the sell order with the lowest asking
price. When two or more of the stored sell orders have lowest asking price, then the earliest sell
order must be matched first. If some shares of the sell order with lowest asking price remain
unmatched, they must be saved for future possible matchings. On the other hand, if some
shares of the new buy order remain unmatched, the above matching algorithm is repeated until
no more matches can be made.

Processing of a new sell order with an asking price of $y per share is done in a similar way. If
all stored buy orders have an offer price per share strictly lower than $y per share, then no
matches can be made and the new order is simply stored for possible future matches.
Otherwise, the new sell order is repeatedly matched with the buy order with the highest offer
price until no more matches are possible, handling ties and partial matches as above.
Implementation requirements!


Program Input
Your program should read from the standard input a list of buy and sell orders in the format
given in the samples below, with one order per line.

Program Output
The program must print to the standard output the total number and the total value of
exchanged shares (i.e., total amount paid by buyers). Use the format given in the examples
below, and follow the output with a new line. (Note that after processing the input there can be
orders or portions of orders left uncleared.)

Sample Input 1
sell 50 shares at 30 each
buy 100 shares at 20 each

Output for Sample Input 1
shares exchanged 0 total value 0 

Sample Input 2
sell 50 shares at 30 each
buy 100 shares at 20 each
buy 40 shares at 40 each
sell 50 shares at 10 each
buy 30 shares at 30 each

Output for Sample Input 2
shares exchanged 100 total value 2900

