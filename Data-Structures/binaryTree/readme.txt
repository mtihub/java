Given	a	binary	tree,	implement	the	following	methods	
a)	return	the	level	order	traversal	of	its	nodes'	values.	(ie,	from	left	to	right,	level	by	
level).	 !
For	example:	
Given	binary	tree	{3,9,20,#,#,15,7},	 !
    3	
			/	\	
		9		20	
/		\	
15			7
return	its	level	order	traversal	as	[[3],	[9,20],	[15,7]] !
!b)	invert	a	binary	tree.	
For	example	
If	the	tree	is
					4	
			/			\	
		2					7	
	/	\		/	\	
1		3	 6		9	
you	should	invert	it to	
					4	
			/			\	
		7					2	
	/	\		 /	\	
9	6		  3		1
c)	D ! etermine	if	it	is	height-balanced.	
For	this	problem,	a	height-balanced	binary	tree	is	de8ined	as	a	binary	tree	in	which	
the	depth	of	the	two	subtrees	of	every	node	never	differ	by	more	than	1. !
d)	Check	whether	it	is	a	mirror	of	itself	(i.e. ! ,	symmetric	around	its	center).	
For	example,	this	binary	tree	is	symmetric:
				 1	
			/		\	
		2					2	
	/	\			/	\	
3		4		4		3 !
But	the	following	is	not:
				1	
			/		\	
		2				2	
						/	\	
						4		3