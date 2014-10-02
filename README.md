ghost
=====

[![Build Status](https://travis-ci.org/anjusuryawanshi/ghost.svg?branch=master)](https://travis-ci.org/anjusuryawanshi/ghost)

<!-- markdown-toc start - Don't edit this section. Run M-x markdown-toc/generate-toc again -->
**Table of Contents**

- [ghost](#ghost)
    - [Summary](#summary)
    - [Winning Strategy](#winning-strategy)
        - [Challenging](#challenging)
    - [Problem Statement](#problem-statement)
    - [Solution](#solution)
        - [Decision Tree](#decision-tree)
        - [Branch with Longest Word Wins](#branch-with-longest-word-wins)
        - [Probabilistic Decision Tree](#probabilistic-decision-tree)
            - [Probability of Knowing _P<sub>k</sub>_](#probability-of-knowing-psubksub)
            - [Probability of Winning _P<sub>w</sub>_](#probability-of-winning-psubwsub)
            - [Difficulty factors _D<sub>k</sub>_ and _D<sub>h</sub>_](#difficulty-factors-dsubksub-and-dsubhsub)
    - [Implementation](#implementation)
        - [`core` module](#core-module)
        - [`cli` and `web` modules](#cli-and-web-modules)
    - [REST API](#rest-api)
        - [GET](#get)
    - [Installation](#installation)
    - [Ideas for improvement](#ideas-for-improvement)
        - [Challenge system](#challenge-system)
        - [Learn from wikipedia](#learn-from-wikipedia)
        - [Android/iOS/Chrome/Firefox applications](#androidioschromefirefox-applications)
    - [Author](#author)
    - [License](#license)

<!-- markdown-toc end -->

## Summary

The game of ghost is usually a written or spoken word game in which
players take turns adding letters to a growing word fragment, trying not
to be the one to complete a valid word. A minimum length of word is set
to 4 letters for words that count. The player that completes a word
looses the round and earns a "letter". A player gets eliminated when
they have received all five letters of the word `ghost`.

## Winning Strategy

To win in the game of `ghost` the player must try not to complete a
word. Here are some sample games with two players alternating between
themselves.

> T-E-S-T   -- Player 1 wins

> G-H-O-S-T -- Player 2 wins

### Challenging

If a player feels there is no word that starts with the current prefix
(the starting few characters that have already been played) does not end
in a valid word, then he/she can challenge the opponent to prove the
validity of the word. If the opponent proves that the word exists, then
the challenger looses otherwise the challenger wins.

## Problem Statement

The goal is to build a optimal strategy for a computer to play against a
human player. Human may choose to start first.

Also, there must be a way to play at different difficulty levels.

The game can be played on the console or a web UI.

## Solution

According to [wikipedia][http://en.wikipedia.org/wiki/Ghost_(game)]
there have been several attempts at solving this game. In this section I
will talk about a variant of it and then I will provide a solution that
I implemented in this application.

### Decision Tree

A decision tree is a tool to support and validate decision making
with. In terms of probability theory, it would tell you the chance of
outcome A vs outcome B given certain parameters.

In the game of ghost, we have build a
[trie][http://en.wikipedia.org/wiki/Trie] like data structure which
servers as a decision tree for us. Every node of this tree has these
properties:

1. __value__ a character value which is makes up a prefix (path from
   root node to this node).
2. __winningProbability__ is the probability to win once the player
   selects this node as the next character to play.
3. list of __children__ comprise a list of nodes that follow the current
   node in sequence to make other words with same prefix.

### Branch with Longest Word Wins

Here is a simplistic algorithm to solve this problem:

- At every node, find the branch that has the longest depth.
- Select the first node from that branch to find the next character to
play.

Sounds simple enough. But, this method suffers from a serious flaw. It
assumes that your opponent will also play the same branch and _let you
follow the branch with highest depth_. This is not the case since we
have to assume that the opponent also plays optimally. Consider this
example:

![Optimal child node selection](https://raw.githubusercontent.com/anjusuryawanshi/ghost/master/docs/images/optimal-child.png "Optimal Child Node")

Let's assume the opponent plays the letter `a`. Now, as per the decision
tree, there are two options for us to choose from, `c` or `d`. Based on
the longest branch selection method, we will end up selecting `c`. This
is where the approach breaks as it also gives the opponent an
opportunity to choose the letter `e` in which case we will loose.

### Probabilistic Decision Tree

In the approach, the rule of thumb is _there is always a chance, however
small it may be, that you will win unless you complete a word_. This is
explained more clearly by this equation:

![Probability Equation](https://raw.githubusercontent.com/anjusuryawanshi/ghost/master/docs/images/equations.png "Probability Equation")

_legend_:

|_Symbol_         |Definition                                                                                            |
|-----------------|------------------------------------------------------------------------------------------------------|
|_n_              | _a node in the decision tree_                                                                        |
|_c_              | _number of children of node `n`_                                                                     |
|_n<sub>i</sub>_  | _a child `i` of node `n`_                                                                            |
|_P<sub>w</sub>_  | _probability of winning_                                                                             |
|_P<sub>k</sub>_  | _probability of knowing_                                                                             |
|_h(n)_           | _average height of node `n`_                                                                         |
|_d(n)_           | _depth of node `n`_                                                                                  |
|_D<sub>k</sub>_  | _difficulty level constant for opponent selecting a child based in its P<sub>k</sub>_                |
|_D<sub>h</sub>_  | _difficulty level constant for opponent selecting a child based on its average height P<sub>k</sub>_ |

#### Probability of Knowing _P<sub>k</sub>_

This is the probability at a node `n` that your opponent may not know
the word that starts with the prefix that ends with the character at
that node. It is important to keep track of it so that there is always a
chance of winning as long as there a non-leaf nodes left to select. Leaf
nodes correspond to letters that end a word.

The idea is that the probability of winning by selecting node `n` is
proportional to the probability of the opponent not winning by selecting
any of the child nodes.

The calculation of _P<sub>k</sub>_ depends on the length of the
prefix. As the length of the word increases, there is a higher chance
that your opponent may not know the word you are thinking of.

#### Probability of Winning _P<sub>w</sub>_

From the equation it is pretty clear that _probability of winning_ by
selecting a node `n` can be defined as:

> Probability of winning by selecting a node `n` is the probability that
> the opponent does not know any word that starts with the prefix formed
> by selecting that node and probability of opponent loosing by
> selecting one of the child nodes of `n`.

One other thing we may need consider is the probability that the child
will select a node `n` out of several possible candidates. One might
argue that a opponent is more likely to select a node that will yield
him or her victory. This can be visualized using this graph:

![Probability of Selection](https://raw.githubusercontent.com/anjusuryawanshi/ghost/master/docs/images/chances-of-selection.png "Probability of Selection")

#### Difficulty factors _D<sub>k</sub>_ and _D<sub>h</sub>_

To be able to control how much the height of a subtree and
_P<sub>k</sub>_ contribute to overall _probability of winning_ there are
these constants called _difficulty factors_. Their actual values can be
found in `com.mystique.ghost.core.model.DifficultyLevel`.

## Implementation

The implementation of this game is divided into three modules:

### `core` module

This is where most of the data crunching happens. The word list is read
from a file and a decision tree is generated as per the algorithm
defined above.

This is done in two phases:

1. Tree parsing phase: a `com.mystique.ghost.core.model.WordTree` is
   generated which represents all the words in the supplied word
   list. This is implemented as a `Trie`.
2. Descision tree generation phase: a second pass to the word tree is
   needed to calculate all winning probabilities. After this phase we
   have a final descision tree.

### `cli` and `web` modules

These are two ways to play the game. CLI module provides a simple to use
command line interface whereas the web module builds a RESTful web
service and a front end to let the user play the game in the browser.

## REST API

This is a simple HTTP GET API which lets the browser talk to the
game to facilitate a enjoyable game playing experience in the browser
itself. Let's assume you have build and deployed the application in your
local maching at port 8080.

### GET

```
HTTP GET http://localhost:8080/strategy/medium/re
```

Here you can see the difficulty level is `medium` and current prefix is
`re`. Response:

``` js
{
  "prefix" : "re",
  "nextCharacter" : "x",
  "status" : "SUCCESS"
}
```

There are four possible values for the `status` field:

1. `INVALID`: if there is no word that starts with the current prefix
2. `PREFIX_COMPLETE`: if the prefix is already a complete word
3. `COMPLETE`: if the computer selects a node which completes the word
4. `SUCCESS`: computer finds a next letter that is not a leaf node

## Installation

Use the provided `gradle` wrapper script

``` bash
$ ./gradlew clean build

```

There are two artifacts of interest: the `cli` jar and `web` war. Both
of these are located on the build directories of the respective modules.

## Ideas for improvement

There is always room for improvement

### Challenge system

I mentioned how this works in the game, but, we it is not implemented yet.

### Learn from wikipedia

One thought is to not assign same _P<sub>k</sub>_ to every node with
same _d(n)_ value, but, rather make it more realistic. For example, a
lot of people know a 9 letter word like `"something"`, but, its
_P<sub>k</sub>_ will not be indicative of this. To be more realistic,
one can run a `map-reduce` task on the all of wikipedia to find out
common words (wikipedia provides a monthly dump of its database).

### Android/iOS/Chrome/Firefox applications

Since, we built the application over a REST API, it should not be too
hard to port it to various other platforms too.

## Author

[Anju Suryawanshi][http://anjusuryawanshi.com/]
[github](https://github.com/anjusuryawanshi)

## License

BSD
