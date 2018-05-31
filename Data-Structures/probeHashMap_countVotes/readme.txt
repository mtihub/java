Background

You work for a company that is implementing an electronic voting
system. The system allows voting by e-mail, and works as
follows. Every registered voter sends an encrypted e-mail
message containing the numeric ID of the candidate for which the
vote is cast (IDs are 9 digit positive integers). E-mail messages
are decrypted and the votes are appended to a list in the order in
which they are received. Your job is to write a program that takes
the combined list and determines if any candidate has received a
strict majority (strictly more than half) of the votes cast. If there is
such a candidate, he/she will be declared the winner of the
election, otherwise the election is undecided.
Implementation requirements
To receive full credit, your program must have no worse than
linear expected time. Among other methods, this may be achieved
by using hashing (hint: if there is a candidate who wins a strict
majority of the votes, then her ID must be the median of the set of
votes).

Program Input
The program must read from the standard input a first line
containing the number n of votes cast, followed by n lines with
one vote per line.

Program Output
The program should print to the standard output a new-line
terminated line in the format shown in the samples below.

Sample Input 1:8
123456789
111111111
987654321
111111111
987654321
987654321
987654321
987654321

Output for Sample Input 1:
987654321 wins with 5 votes out of 8

Sample Input 2:
2
123456789
111111111

Output for Sample Input 2:
no candidate received a majority of the votes

