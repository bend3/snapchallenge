# Mad Doctor (💡💡 15 pts)
ID: mad-doctor
## Problem statement:
<p>
The famous Mad Doctor holds monsters captive for various experiments in his lab.

They need to be fed every day, so if the Mad Doctor doesn't provide meals to them, he can be the next meal easily.

The monsters are locked up in their cells next to each other. Each cell has a number that represents the rating value of the monster. Mad Doctor wants to give at least 1 portion of food to each monster. If two monsters are next to each other, then the one with the higher rating must get more portions of food. Mad Doctor wants to minimize the total number of food portions he must buy.

Help him to calculate the minimum amount of food!
</p>

### Example
`arr = [1, 1, 2, 3, 3, 3, 2, 10]`</br>
He gives the monsters food in the following minimal amounts: `[1, 1, 2, 3, 1, 2, 1, 2]`. He must buy a minimum of 13 portion of food.

Note that when two monsters have equal ratings, they are allowed to have different amounts of food.

### Constraints
<p>
<code>1 ≤ n ≤ 105</code></br>
<code>0 ≤ arr[i] ≤ 10^5</code>

Where n is the number of the monsters, <code>arr[i]</code> is an integer indicating the rating of the monster at position <code>i</code>.



The input for this problem looks like this: <code>[1, 1, 2, 3, 3, 3, 2, 10]</code>

Where the numbers are the rating scores of the monsters.



The output should look like this: 13

Where the number is the minimum amount of food portion the Mad Doctor has to buy.



Available amount of samples: 1 (so the maximum sample_index is 0)
</p>