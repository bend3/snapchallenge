# The Escape (💡💡💡 20 pts)
ID: the-escape

## Problem statement:
Here goes another problem for you:

You just woke up in a cold, dark and wet maze. There is an exit on the other side of the maze but it is locked, so you must find the key first.

You are not alone though. There is a creature that is following you. It likes to play with the victims a bit, then it is going to kill you and eat most of your body. Therefore you don't have much time to waste.

Find the shortest way to the key and the exit to survive!

The input json for this problem looks like this:
```json
[
  ["#", "E", "#", "#", "#"],
  ["#", ".", "#", "K", "#"],
  ["#", ".", "#", ".", "#"],
  ["#", ".", ".", ".", "#"],
  ["#", "S", "#", "#", "#"]
]
```
Where S is the start field, K is the key field, E is the exit field, # is a wall field, . is a path field.

You start from the S field and need to provide steps to reach the K field first, then the E field on the shortest path to escape. How you implement that, is up to you...

In the end, you need to provide us an array with the step objects.

The output should look like this:
```json
[
  { "x": 1, "y": 4 },
  { "x": 1, "y": 3 },
  { "x": 2, "y": 3 },
  { "x": 3, "y": 3 },
  { "x": 3, "y": 2 },
  { "x": 3, "y": 1 },
  { "x": 3, "y": 2 },
  { "x": 3, "y": 3 },
  { "x": 2, "y": 3 },
  { "x": 1, "y": 3 },
  { "x": 1, "y": 2 },
  { "x": 1, "y": 1 },
  { "x": 1, "y": 0 }
]
```
Where "{ "x": 1, "y": 4 }" is the coordinate of a step.